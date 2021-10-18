import {Headers, Http, RequestOptions} from '@angular/http';
import {Injectable} from '@angular/core';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';
import {Cfg} from '../config/cfg';
import {Ctx} from '../context/ctx';

declare var layui: any;

@Injectable()
export class Postman {

  constructor(private http: Http,
              private cfg: Cfg,
              private ctx: Ctx,
              private router: Router) {
  }

  public get(url: string, options: any = null, showLoading: boolean = true): Promise<any> {
    // const self = this;
    url = this.cfg.urlPrefix + url;
    let loadingLayerIndex: any = null;
    if (showLoading) {
      loadingLayerIndex = this.showLoadingLayer();
    }
    if (options == null) {
      const headers = new Headers({token: this.ctx.getToken()});
      options = new RequestOptions({headers});
    }
    return new Promise<any>((resolve, reject) => {
      this.http.get(url, options)
        .pipe(
          map((res: any) => res.json())
        )
        .subscribe(
          (res: any) => {
            this.handleResolve(resolve, res, loadingLayerIndex);
          },
          (error: any) => {
            this.handleError(reject, error, loadingLayerIndex);
          }
        );
    });
  }

  public post(url: string, body: any, options: any = null, showLoading: boolean = true): Promise<any> {
    url = this.cfg.urlPrefix + url;
    let loadingLayerIndex: any = null;
    if (showLoading) {
      loadingLayerIndex = this.showLoadingLayer();
    }
    if (options == null) {
      const headers = new Headers({'Content-Type': 'application/json', token: this.ctx.getToken()});
      options = new RequestOptions({headers});
    }

    return new Promise<any>((resolve, reject) => {
      this.http.post(url, body, options)
        .pipe(
          map((res: any) => res.json())
        )
        .subscribe(
          (res: any) => {
            this.handleResolve(resolve, res, loadingLayerIndex);
          },
          (error: any) => {
            this.handleError(reject, error, loadingLayerIndex);
          }
        );
    });
  }

  public put(url: string, body: any, options: any = null, showLoading: boolean = true): Promise<any> {
    url = this.cfg.urlPrefix + url;
    let loadingLayerIndex: any = null;
    if (showLoading) {
      loadingLayerIndex = this.showLoadingLayer();
    }
    if (options == null) {
      const headers = new Headers({'Content-Type': 'application/json', token: this.ctx.getToken()});
      options = new RequestOptions({headers});
    }

    return new Promise<any>((resolve, reject) => {
      this.http.put(url, body, options)
        .pipe(
          map((res: any) => res.json())
        )
        .subscribe(
          (res: any) => {
            this.handleResolve(resolve, res, loadingLayerIndex);
          },
          (error: any) => {
            this.handleError(reject, error, loadingLayerIndex);
          }
        );
    });
  }

  protected handleError(reject: any, error: any, loadingLayerIndex: any) {
    reject(error);
    if (loadingLayerIndex != null) {
      this.closeLoadingLayer(loadingLayerIndex);
    }
  }

  protected handleResolve(resolve: any, res: any, loadingLayerIndex: any) {
    if (res.code === 401) {
      alert('Session timeout, login please.');
      this.router.navigate(['/login']);
      if (loadingLayerIndex != null) {
        this.closeLoadingLayer(loadingLayerIndex);
      }
      return;
    }
    if (res.code === 403) {
      alert('Permission denied.');
      if (loadingLayerIndex != null) {
        this.closeLoadingLayer(loadingLayerIndex);
      }
      return;
    }
    if (res.code === 500) {
      alert('There may be some problems in the server, please try again later.');
      if (loadingLayerIndex != null) {
        this.closeLoadingLayer(loadingLayerIndex);
      }
      return;
    }
    resolve(res);
    if (loadingLayerIndex != null) {
      this.closeLoadingLayer(loadingLayerIndex);
    }
  }

  protected showLoadingLayer(): any {
    return layui.layer.load(1, {
      // shade: [0.5, '#f0f0f0']
    });
  }

  protected closeLoadingLayer(index: any): void {
    layui.layer.close(index);
  }
}
