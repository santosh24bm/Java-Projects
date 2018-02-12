import { Injectable }       from '@angular/core';
import { Ng2Storage } from './storage';
import {
  CanActivate, Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  CanActivateChild,
  NavigationExtras,
  CanLoad, Route
}                           from '@angular/router';
import { DataService } from '../service/DataService';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private authService: DataService, private router: Router,private storage: Ng2Storage) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const url = route.routeConfig.path;
    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    let metaData =  this.storage.getSession('user_data');
    if(!metaData){
      this.router.navigate(['/login']);
      return false;
    }
    if (metaData.userRoleName.toLocaleLowerCase() === 'reviewer' && url === 'superviser') {
      return true;
    }else if(metaData.userRoleName.toLocaleLowerCase() === 'reviewer' && url === 'userview'){
      this.router.navigate(['/app/superviser']);
      return false;
    }else if(metaData.userRoleName.toLocaleLowerCase() !== 'reviewer' && url === 'userview'){
      return true;
    }else if(metaData.userRoleName.toLocaleLowerCase() !== 'reviewer' && url === 'superviser'){
      this.router.navigate(['/app/userview']);
      return false;
    }
  }
}