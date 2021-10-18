import {Injectable} from '@angular/core';

@Injectable()
export class Ctx {

  public static INF = 10000;

  constructor() {
  }

  public setToken(token: string): void {
    sessionStorage.setItem('token', token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem('token');
  }

  public setNickname(nickname: string): void {
    sessionStorage.setItem('nickname', nickname);
  }

  public getNickname(): string | null {
    return sessionStorage.getItem('nickname');
  }

  public clearAll(): void {
    sessionStorage.clear();
  }
}
