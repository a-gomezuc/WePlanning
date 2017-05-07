import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../Class/user.model';

import { UserService } from '../Services/user.service';


@Component({
  selector: 'app-root',
  templateUrl: './modifyPhoto.component.html',
  styleUrls: ['../app.component.css']
})
export class ModifyPhotoComponent {

    private file : File;
    private fileShow:File;
    private user: User;
    private showPreview: boolean = false;

    constructor (private userService:UserService, private activatedRoute:ActivatedRoute, private router: Router){
      let id = activatedRoute.snapshot.params['id'];
      this.userService.getUser(id).subscribe(
        user => {
          this.user = user;
          console.log(user);
        }
      );
      this.userService.setUserS(this.user);
    }

    changeProfile(id:string){
      this.userService.selectProfileImage(this.file, id).subscribe(
        user => {
          this.userService.recharge(id);
        }
      );
    }
    changed(fileInput: any) {
        this.file = fileInput.target.files[0];
        this.showPreview = true;

        var reader = new FileReader();

        reader.onload = (event: any) => {
            this.fileShow = event.target.result;
        }
        reader.readAsDataURL(fileInput.target.files[0]);
    }
}
