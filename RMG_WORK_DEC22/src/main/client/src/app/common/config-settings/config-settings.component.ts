import { Component, OnInit, AfterViewChecked, OnDestroy, TemplateRef, ViewChild } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';
import { ActivatedRoute, Router } from '@angular/router';
import { BsModalService } from 'ngx-bootstrap/modal';
import { Subscription } from 'rxjs/Subscription';
import { User, UserData, EmployeeData, EmployeeDetails, ConfigEmployeeData } from '../../app.interface';
import { Ng2Storage } from '../../service/storage';
import { DataService } from '../../service/DataService';
import { CommonService } from '../../service/common.service';
import { filter } from 'lodash';

@Component({
  selector: 'app-config-settings',
  templateUrl: './config-settings.component.html',
  styleUrls: ['./config-settings.component.css']
})
export class ConfigSettingsComponent implements OnInit {



  public userData: User;
  public empData;
  public confirmPopupData: any;
  public config = {
    animated: true,
    keyboard: true,
    backdrop: true,
    ignoreBackdropClick: false
  };
  public trReviwersListSelected;
  public trReviwersListModel: EmployeeDetails[];
  public trSubmitToListModel: EmployeeDetails[] = [];
  public trApproversListModel: EmployeeDetails[] = [];
  public trReviwersList: EmployeeDetails[] = [];
  public trSubmitToList: EmployeeDetails[] = [];
  public trApproversList: EmployeeDetails[] = [];
  public configEmpData: EmployeeData;
  public configAllEmpData: EmployeeData;
  
  public busy: Subscription;
  public emptyApprover:boolean=false;

  public mySettings = {
    enableSearch: true,
    checkedStyle: 'fontawesome',
    buttonClasses: 'btn btn-default btn-block',
    dynamicTitleMaxItems: 3,
    displayAllSelectedText: true
  };
  // Text configuration
  public myTexts = {
    checkAll: 'Select all',
    uncheckAll: 'Unselect all',
    checked: 'item selected',
    checkedPlural: 'items selected',
    searchPlaceholder: 'Find',
    searchEmptyResult: 'Nothing found...',
    searchNoRenderText: 'Type in search box to see results...',
    defaultTitle: 'Select',
    allSelected: 'All selected',
  };
  public trReviewers = [];
  public approvedReviewers = [];
  public notifiedReviewers = [];
   public approverIds;
  constructor(
    private storage: Ng2Storage,
    public bsModalRef: BsModalRef,
    private dataService: DataService,
    private currentRoute: ActivatedRoute,
    private commonService: CommonService,
    private modalService: BsModalService,
    private router: Router, ) { }

  public ngOnInit() {
    this.empData = this.storage.getSession('user_data');
    this.getEmpeDetails();
    this.getAllEmployeeDetails();
  }

  public isSelected = [{ "userId": "E000019", "userEmail": "kasukurthi.vr@cigniti.com", "userName": "Venkata Rama Chowdary Kasukurthi" }];
  private _disabledV: string = '0';
  public disabled: boolean = false;

  private get disabledV(): string {
    return this._disabledV;
  }

  private set disabledV(value: string) {
    this._disabledV = value;
    this.disabled = this._disabledV === '1';
  }

  public selected(value: any): void {
    this.approvedReviewers = [];
    let selectedItem = { userId: value.id, userName: value.text }
    this.approvedReviewers.push(selectedItem);
  }

  public removed(value: any): void {
    this.approvedReviewers = [];
  }



  public onSearch(event, name) {
    if (name == "timereportReviewNames") {
      this.trReviwersList = this.commonService.filterMultiSelectData(this.configEmpData.details, event.filter, 'userName');
    } else if (name == "notifyReviewNames") {
      this.trSubmitToList = this.commonService.filterMultiSelectData(this.configAllEmpData.details, event.filter, 'userName');
    } else {
      this.trApproversList = this.commonService.filterMultiSelectData(this.configEmpData.details, event.filter, 'userName');
    }
  }

  public getEmpeDetails() {
    // let lastDate = this.getLastDate();
    // let date = `${lastDate.year}-${lastDate.getMonth + 1}-${lastDate.date}`
    this.busy = this.dataService.getEmployeeDetails(this.empData.userId).subscribe((data: EmployeeData) => {
      //this.trReviwersListModel = [];
      this.configEmpData = data;
      this.trReviwersList = data.details;
      this.trSubmitToList = data.details;
      this.trApproversList = data.details;
      // setTimeout(() => {
      //   this.trReviwersListModel.push(this.trReviwersList[0]);
      // })
      setTimeout(() => {
        this.getConfigEmpDetails();
      })
    })
  }
    public getAllEmployeeDetails() {
    // let lastDate = this.getLastDate();
    // let date = `${lastDate.year}-${lastDate.getMonth + 1}-${lastDate.date}`
    this.busy = this.dataService.getAllEmployeeDetails().subscribe((data: EmployeeData) => {
      //this.trReviwersListModel = [];
      this.configAllEmpData = data;
      this.trSubmitToList = data.details;
      // setTimeout(() => {
      //   this.trReviwersListModel.push(this.trReviwersList[0]);
      // })
      // setTimeout(() => {
      //   this.getConfigEmpDetails();
      // })
    })
  }
  
  public getConfigEmpDetails() {
    // let lastDate = this.getLastDate();
    // let date = `${lastDate.year}-${lastDate.getMonth + 1}-${lastDate.date}`
    this.busy = this.dataService.getConfigEmpDetails(this.empData.userId).subscribe((data: ConfigEmployeeData) => {
     // this.trReviwersListModel = [];
      this.trSubmitToListModel = [];
      this.trApproversListModel = [];
      this.trReviewers = [];
      this.approvedReviewers = [];
      this.notifiedReviewers = [];
      let reviewerDeatils = data.details.superiorId;
      let approverrDetails = data.details.approverIds;
      let notifiers = data.details.copytoId;

      this.trReviwersListModel = this.trReviewers = reviewerDeatils.map(function (obj) {
        let b = obj.split('-');
        let c = {
          userId: b[0],
          userName: b[1]
        }
        return c;
      });
      this.trApproversListModel = this.approvedReviewers = approverrDetails.map(function (obj) {
        let b = obj.split('-');
        let c = {
          userId: b[0],
          userName: b[1]
        }
        return c;
      });
      this.trSubmitToListModel = this.notifiedReviewers = notifiers.map(function (obj) {
        let b = obj.split('-');
        let c = {
          userId: b[0],
          userName: b[1]
        }
        return c;
      });

      // this.trReviwersListModel = data.superiorDetails;
      // this.trSubmitToListModel= data.notifierDetails;
      // this.trApproversListModel = data.approverDetails;
      // this.trReviewers =data.superiorDetails;
      // this.notifiedReviewers=data.notifierDetails;
      // this.approvedReviewers=data.approverDetails;

      // setTimeout(() => {
      //   this.trReviwersListModel.push(this.trReviwersList[0]);
      // })
    })
  }

  public removeSlUsers(index, name) {
    if (name == "timereportReviewNames") {
      this.trReviewers.splice(index, 1);
      this.trReviwersListModel = this.trReviewers;
    } else if (name == "notifyReviewNames") {
      this.notifiedReviewers.splice(index, 1);
      this.trSubmitToListModel = this.notifiedReviewers;
    } else {
      this.approvedReviewers.splice(index, 1);
      this.trApproversListModel = [];
    }

  }

  public getUserDetails() {
    let userId = this.storage.getSession('user_data');
    this.dataService.getUser(userId.userId).subscribe((data: User) => {
      this.commonService.menuItems.emit(data.details.menuBeanList);
      this.userData = data;
    })
  }

  // public openModal(template: TemplateRef<any>) {
  //   this.getEmpeDetails();
  //   this.getAllEmployeeDetails();
  //   this.bsModalRef = this.modalService.show(ConfigSettingsComponent);
  // }

  public getTimeReport(name, type) {
    if(type == 'n'){
      this.notifiedReviewers = name;
    }else{
      this.trReviewers = name;
    }
  }
 
  public saveConfigDetails() {
    let reviwerIds = this.trReviwersListModel.map((obj) => {
      // return obj.userId + '-' + obj.userName;
      return obj.userId;
    });
    let notifierIds = this.trSubmitToListModel.map((obj) => {
      // return obj.userId + '-' + obj.userName;
      return obj.userId;
    });
    // let approverIds = this.trApproversListModel.map((obj) => {
    //   //return obj.userId + '-' + obj.userName;
    //   return obj.userId;
    // })
    this.approverIds=[];
    if(this.approvedReviewers.length>0){
    this.approverIds.push(this.approvedReviewers[0].userId);
    }else{
      this.approverIds=[];
    }

    var configObject = {
      empId: this.empData.userId,
      approverIds: this.approverIds,
      superiorId: reviwerIds,
      copytoId: notifierIds,
    };
    if (this.approverIds.length == 1) {
      this.busy = this.dataService.saveConfigEmployeeDetails(configObject).subscribe((res) => {
        //this.getTimeSheetDetails(this.allperiods.currentPeriodDetailsBean.timePeriodId);
        if (res.actionStatus) {
          this.bsModalRef.hide();
          this.commonService.configurSaving.emit(true);
          this.commonService.getAllConfigEmp.emit(true);
        } else {
          this.commonService.configurSaving.emit(false);
        }

      }, (err) => {
        console.log(err);
        this.commonService.configurSaving.emit(false);
      })
    }  else {
      this.emptyApprover=true;
    }


  }

}
