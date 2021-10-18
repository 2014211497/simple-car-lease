import {Component, OnInit} from '@angular/core';
import {Postman} from '../../../helper/postman';
import {AppUtil} from '../../../util/AppUtil';

declare var layui: any;


@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss']
})
export class CarsComponent implements OnInit {

  public selectCarModelListParam: any;
  public createLeaseOrderParam: any;
  public selectedCarModelName: string = '';
  public beginTimeStr: string = '';
  public endTimeStr: string = '';

  constructor(private postman: Postman) {
  }

  ngOnInit(): void {
    this.initializeCreateLeaseOrderParam({});
    this.initializeSelectCarModelListParam();
    this.renderCarModelTable(true);
  }

  private initializeSelectCarModelListParam(): void {
    this.selectCarModelListParam = {
      page: 1,
      size: 10,
      available: null,
      orderBy: null,
      orderDesc: true
    }
  }

  private initializeCreateLeaseOrderParam(data: any): void {
    this.createLeaseOrderParam = {
      carModelId: data.id,
    }
  }

  public renderCarModelTable(initPage: boolean = false): Promise<any> {
    const self = this;
    if (initPage) {
      this.selectCarModelListParam.page = 1;
    }
    let url = '/car_models?page=' + this.selectCarModelListParam.page + '&size=' + this.selectCarModelListParam.size;
    if (this.selectCarModelListParam.available != null) {
      url += '&available=' + this.selectCarModelListParam.available;
    }
    return this.postman.get(url, null)
      .then((res: any) => {
        AppUtil.wrapTableListData(res.data.data, self.selectCarModelListParam);
        layui.table.render({
          elem: '#carModelTable',
          autoSort: false,
          cols: [[
            {field: '_seq', title: '', width: 60},
            {field: 'name', title: 'Car Model'},
            {field: 'stock', title: 'Stock'},
            {fixed: 'right', toolbar: '#cellToolbar_UD', width: 70}
          ]],
          data: res.data.data,
          limit: self.selectCarModelListParam.size,
          even: true
        });

        layui.table.on('tool(carModelTable)', (obj: any) => {
          switch (obj.event) {
            case 'BOOK':
              this.showBookingLayer(obj.data);
              break;
          }
        });

        layui.laypage.render({
          elem: 'carModelPaging',
          count: res.data.total,
          curr: self.selectCarModelListParam.page,
          limit: self.selectCarModelListParam.size,
          layout: ['prev', 'page', 'next', 'limit', 'skip', 'count'],
          jump: (obj: any, first: any) => {
            if (first) {
              return;
            }
            self.selectCarModelListParam.page = obj.curr;
            self.selectCarModelListParam.size = obj.limit;
            self.renderCarModelTable(false).then(r => {
            });
          }
        });
      });
  }

  private showBookingLayer(data: any): void {
    // console.log(data);
    if (data.stock == 0) {
      AppUtil.errTips('This car model is unavailable');
      return;
    }
    this.selectedCarModelName = data.name;
    this.beginTimeStr = '';
    this.endTimeStr = '';
    layui.layer.open({
      type: 1,
      area: ['600px', '600px'],
      content: layui.$('#bookingCarLayer'),
      title: 'Booking car',
      btn: ['Submit', 'Cancel'],
      btnAlign: 'c',
      success: (layero: any) => {
        layui.$('.layui-layer-shade').appendTo(layero.parent());
        layui.form.render();
      },
      yes: (index: any, layero: any) => {

        const reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/;
        const regExp = new RegExp(reg);
        if (!regExp.test(this.beginTimeStr)) {
          AppUtil.errTips('Invalid format of BeginTime');
          return;
        }
        if (!regExp.test(this.endTimeStr)) {
          AppUtil.errTips('Invalid format of EndTime');
          return;
        }

        const bt = new Date(this.beginTimeStr).getTime() / 1000;
        const et = new Date(this.endTimeStr).getTime() / 1000;
        const nt = new Date().getTime() / 1000;

        if (bt <= nt) {
          AppUtil.errTips('BeginTime cannot be early than now');
          return;
        }
        if (bt >= et) {
          AppUtil.errTips('EndTime cannot be early than BeginTime');
          return;
        }

        this.createLeaseOrderParam.carModelId = data.id;
        this.createLeaseOrderParam.beginTime = bt;
        this.createLeaseOrderParam.endTime = et;

        this.postman.post('/lease_orders', this.createLeaseOrderParam).then((res: any) => {
          if (res.code == 200) {
            AppUtil.okTips('Success!');
            this.renderCarModelTable(true);
            layui.layer.close(index);
          }
        });
      },
      cancel: (index: any, layero: any) => {
        layui.layer.close(index);
      },
      end: () => {
        layui.$('#bookingCarLayer').css('display', 'none');
      }
    });
  }

}
