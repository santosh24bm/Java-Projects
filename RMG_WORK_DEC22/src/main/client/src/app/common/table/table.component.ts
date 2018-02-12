import { Component, OnInit, Input, AfterViewChecked,ElementRef,OnChanges,SimpleChanges, ViewChild } from '@angular/core';
import { Timesheet,TimeSheetDetails } from '../../app.interface';
import { Ng2Storage} from '../../service/storage';
import { CommonService } from '../../service/common.service';
import { findIndex, find } from 'lodash';
@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit,OnChanges {
  @ViewChild('gridBlockElement') gridBlockElement:ElementRef;
  @Input() tabValue:Timesheet;
  @Input() chargeCode;
  @Input() parentElent:ElementRef;
  @Input() tabName:string;
  constructor(private storage:Ng2Storage,private commonService:CommonService) { }
  public userData;
  chargeCodes;
  private rowHeaderHeight:number = 58;
  private rowBodyHeight:number = 38;
  private originalRowsCountHeight:number;
  public rowsCount: any;
  public columnCount:any;
  public tsStatusTxt:string='new';
  public tableHeader:any[];
  public gridHeaderLeft=0;
  public gridBodyTop=0;
  public gridBodyHeight = 200;
  public overFlowClass:boolean = false;
  private keyCodes = [15,48,49,50,51,52,53,54,55,56,57,6,96,97,98,99,100,101,102,103,104,105,38,40,8,9,37,39,110,190,46];

  public ngOnInit() {

    this.userData = this.tabValue;
    let user = this.storage.getSession('user_data');
    if(this.userData.details.length >0){
      this.disableRestSelect(this.userData.details);
      if(this.userData.details[0].tstatus.toLowerCase()=="rejected" && this.tabName==="record"){
        this.tsStatusTxt="reopen";
      }
    }
    this.setRowsAndStatus(this.userData);
    
  }

  public ngOnChanges(changes: SimpleChanges) {
    if(changes.tabValue && changes.tabValue.currentValue){
      this.userData = changes.tabValue.currentValue;
      this.disableRestSelect(this.userData.details);
      let user = this.storage.getSession('user_data');
      this.setRowsAndStatus(this.userData);
    }
  }
  public moveGridHeader(event){
    this.gridHeaderLeft=-event.target.scrollLeft;
    this.gridBodyTop=-event.target.scrollTop;
  }

  private setRowsAndStatus( obj ){
    this.originalRowsCountHeight = ((obj.details.length + 1)) * 38;
    
    this.getRows(this.parentElent);
    if(this.userData.details.length >0){
      this.columnCount = this.userData.details[0].dataBean;
      this.tsStatusTxt = this.userData.details[0].tstatus.toLowerCase();
      this.tableHeader = this.userData.tableHeader;
      let user = this.storage.getSession('user_data');
      if(this.userData.details[0].tstatus.toLowerCase()=="rejected" && this.tabName==="record"){
        this.tsStatusTxt="reopen";
      }
    }else{
      this.columnCount = this.userData.tableHeader;
      this.tsStatusTxt = '';
      this.tableHeader = this.userData.tableHeader;
    }
  }
 
  public onSelectionChange(obj,ind,arr) {
    this.commonService.findDuplicates(arr)
  }

  private disableRestSelect( arr ){
    let len = arr.length;
    var selectedInd = 0;
    let findInd = findIndex(arr,( obj )=>{
        return !obj.assignmentId
    });
    if(findInd !== -1){
    var i=(findInd+1);
    for(i; i<len; i++){
      arr[i].isDisableSelect = true;
    };
  }
  }

  public stopString(evt){
   
    var keyCode = evt.keyCode ? evt.keyCode:evt.which;
    if( (this.keyCodes.indexOf(keyCode) == -1  && !evt.shiftKey) ||  (this.keyCodes.indexOf(keyCode) !== -1  && evt.shiftKey)){
      evt.stopPropagation();
      return false;
    }
  }
  public changeData(evt,tsdata, ind, rowInd, model) {
      tsdata.totalHours[ind].isValidHour = false;
    
    var modals = model.hours ? parseFloat(model.hours): 0;
    var keyCode = evt.keyCode ? evt.keyCode:evt.which;
    if( (this.keyCodes.indexOf(keyCode) == -1 && !evt.shiftKey)  || (modals>=24 && (keyCode!== 8 || keyCode!== 9) && !evt.shiftKey)){
      if(model.hours){
        model.hours = parseFloat(model.hours) >24?24:model.hours;
      }else{
        model.hours = model.hours !== 0 ?null:model.hours;
      }
      this.totalColCount(tsdata, ind, rowInd, model);
      evt.stopPropagation();
      return false;
    }
    
    if(keyCode === 38){
      if(modals>=24){
        model.hours = !model.hours? 0:model.hours;
      }else{model.hours
        modals++;
        model.hours += 1
      }
    }else if(keyCode === 40){
      if(modals<=0){
        model.hours = model.hours !== 0 ?null:model.hours;
      }else{
        modals--;
        model.hours -= 1;
      }
    }
    
    if(keyCode=== 9){
      model.hours = model.hours >24? 24:model.hours;
    }
    this.totalColCount(tsdata, ind, rowInd, model);
  }

  private getRows( ele:any){
    let offsetTop = ele.getBoundingClientRect().top;
    let wH = window.innerHeight;
    let getHeight = wH-offsetTop-40-this.rowHeaderHeight-97;
    this.rowsCount = Math.floor(((wH-offsetTop-40-this.rowHeaderHeight-this.originalRowsCountHeight)/this.rowBodyHeight));
    this.rowsCount = this.rowsCount <= 0? new Array(0):new Array(this.rowsCount-1);
   // this.gridBodyHeight = getHeight;
   this.gridBodyHeight = (this.rowsCount.length * 38)+this.originalRowsCountHeight-28;
    let eleMent = this.gridBlockElement.nativeElement.querySelector('.table-grid-block-body-center');
    setTimeout(()=>{
      this.overFlowClass = eleMent.clientHeight < eleMent.scrollHeight;
    },200);
  }

  public totalColCount(tsdata, ind, rowInd, model){
    var tsRowTotal = 0;
    var tsColumnTotal = 0;
    var tsTotalHours = 0;
    for (var i = 0; i < tsdata.details.length; i++) {
      var modal1 = tsdata.details[i].dataBean[ind].hours;
      var modals = modal1?parseFloat(tsdata.details[i].dataBean[ind].hours):0;
      tsColumnTotal = tsColumnTotal + modals;
      tsTotalHours = tsTotalHours + tsdata.details[i].tsTotal;
      if (rowInd == i) {
        for (var j = 0; j < tsdata.details[i].dataBean.length; j++) {
          var modal3 = tsdata.details[i].dataBean[j].hours;
          var modal2 = modal3? parseFloat(tsdata.details[i].dataBean[j].hours):0;
          tsRowTotal = tsRowTotal + modal2;
        }
      }
    }
    tsdata.details[rowInd].tsTotal = tsRowTotal;
    tsdata.totalHours[ind].dayHrs = tsColumnTotal;
    tsdata.totalTsHorsLogged = tsTotalHours;
  }

  public addRow( arr ){
    let data = arr[arr.length-1];
    let objs = <TimeSheetDetails>JSON.parse(JSON.stringify(Object.assign({},data)));
    objs.dataBean.forEach(( ele )=>{
      ele.hours = null;
    });
    objs.assignmentId = '';
    objs.tsTotal = 0;
    this.userData.details.push(objs);
    this.setRowsAndStatus(this.userData);
  }
}
