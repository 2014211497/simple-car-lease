import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AppUtil} from '../../util/AppUtil';
import {Ctx} from '../../context/ctx';
import {Postman} from '../../helper/postman';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public username = '';
  public password = '';

  constructor(private router: Router,
              private postman: Postman,
              private ctx: Ctx) {
  }

  ngOnInit(): void {

  }

  public onLoginButtonClicked(): void {
    const body = {
      username: this.username,
      password: AppUtil.encodeWithBase64AndMd5(this.password)
    };
    this.postman.post('/login', body, null).then((res: any) => {
      if (res.code == 200) {
        const token = res.data;
        // const payload = JSON.parse(JSON.parse(AppUtil.base64Decode(token.split('.')[1])).pld);
        this.ctx.setToken(token);
        this.postman.get('/user/profile', null).then((res: any) => {
          const profile = res.data;
          this.ctx.setNickname(profile.nickname);
          this.router.navigate(['/simplecarlease']).then(() => {
          });
        });
        return;
      }
      if (res.code == 200401) {
        alert('Invalid username or password');
        return;
      }
    });
  }

}
