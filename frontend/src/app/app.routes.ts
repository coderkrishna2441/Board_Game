import { Routes } from '@angular/router';
import { HomepageComponent } from './component/homepage/homepage';
import { LoginComponent } from './component/login/login';
import { SignupComponent } from './component/signup/signup';
import { ErrorComponent } from './component/error/error';
import {MemberpageComponent} from './component/memberpage/memberpage' ;
import {UserProfileComponent} from './component/userprofile/userprofile';
import {UserInfoViewComponent} from './component/user-profile-display-component/user-profile-display-component';
import {EventinfoComponent} from "./component/eventinfo-component/eventinfo-component";
export const routes: Routes = [

  { path: '', redirectTo: 'home', pathMatch: 'full' },

  {
    path: 'home',
    component: HomepageComponent,
  },

  { path: 'login', component: LoginComponent },

  { path: 'signup', component: SignupComponent },
  
  { path: 'member/:username' , component:MemberpageComponent },

  { path: 'edituserinfo/:username' , component:UserProfileComponent},

  {path: 'userinfo/:username' , component:UserInfoViewComponent},

  {path: 'eventinfo/:eventname' , component:EventinfoComponent},

  { path: '**', component: ErrorComponent }

];