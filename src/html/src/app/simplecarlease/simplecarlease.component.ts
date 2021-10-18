import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Ctx} from '../../context/ctx';

declare var layui: any;

@Component({
  selector: 'app-simplecarlease',
  templateUrl: './simplecarlease.component.html',
  styleUrls: ['./simplecarlease.component.scss']
})
export class SimplecarleaseComponent implements OnInit {

  nickname: string | null = '';

  constructor(private router: Router,
              private ctx: Ctx) {
    this.nickname = this.ctx.getNickname();
  }

  ngOnInit(): void {
  }

  public navigate(path: string): void {
    this.router.navigate([path]);
  }

  public logout(): void {
    const self = this;
    layui.layer.confirm("Are you sure to logout？", {icon: 3, title: 'System Tips'}, function (index: any) {
      self.ctx.clearAll();
      self.router.navigate(['/login']);
      layui.layer.close(index);
    });
  }
}
