import {Component, OnInit} from '@angular/core';
import {Postman} from '../../../helper/postman';
import {AppUtil} from '../../../util/AppUtil';

declare var layui: any;


@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  public selectOrderListParam: any;

  constructor(private postman: Postman) {
  }

  ngOnInit(): void {
    this.initializeSelectOrderListParam();
    this.renderOrderTable(true);
  }

  private initializeSelectOrderListParam(): void {
    this.selectOrderListParam = {
      page: 1,
      size: 10,
      orderBy: 'create_time',
      orderDesc: true
    }
  }

  public renderOrderTable(initPage: boolean = false): Promise<any> {
    const self = this;
    if (initPage) {
      this.selectOrderListParam.page = 1;
    }
    let url = '/lease_orders?page=' + this.selectOrderListParam.page + '&size=' + this.selectOrderListParam.size;

    return this.postman.get(url, null)
      .then((res: any) => {
        AppUtil.wrapTableListData(res.data.data, self.selectOrderListParam);
        layui.table.render({
          elem: '#orderTable',
          autoSort: false,
          cols: [[
            {field: '_seq', title: '', width: 60},
            {field: 'carModelName', title: 'Car Model'},
            {field: 'carNumberPlate', title: 'Car Number Plate'},
            {field: 'createTimeStr', title: 'CreateTime'},
            {field: 'beginTimeStr', title: 'Begin Time'},
            {field: 'endTimeStr', title: 'End Time'},
            {field: 'isFinished', title: 'Is Finished'},
            {field: 'isCanceled', title: 'Is Canceled'},
            {fixed: 'right', toolbar: '#cellToolbar_FC', width: 150}
          ]],
          data: res.data.data,
          limit: self.selectOrderListParam.size,
          even: true
        });

        layui.table.on('tool(orderTable)', (obj: any) => {
          switch (obj.event) {
            case 'FINISH':
              this.finishLeaseOrder(obj.data);
              break;
            case 'CANCEL':
              this.cancelLeaseOrder(obj.data);
              break;
          }
        });

        layui.laypage.render({
          elem: 'orderPaging',
          count: res.data.total,
          curr: self.selectOrderListParam.page,
          limit: self.selectOrderListParam.size,
          layout: ['prev', 'page', 'next', 'limit', 'skip', 'count'],
          jump: (obj: any, first: any) => {
            if (first) {
              return;
            }
            self.selectOrderListParam.page = obj.curr;
            self.selectOrderListParam.size = obj.limit;
            self.renderOrderTable(false).then(r => {
            });
          }
        });
      });
  }

  private finishLeaseOrder(data: any): void {
    this.postman.put('/lease_orders/_finish', {id: data.id}, null)
      .then((res: any) => {
        if (res.code == 200 && res.data == true) {
          AppUtil.okTips('Success!');
        } else {
          AppUtil.errTips('Operation failed.');
        }
        this.renderOrderTable(true);
      });
  }

  private cancelLeaseOrder(data: any): void {
    this.postman.put('/lease_orders/_cancel', {id: data.id}, null)
      .then((res: any) => {
        if (res.code == 200 && res.data == true) {
          AppUtil.okTips('Success!');
        } else {
          AppUtil.errTips('Operation failed.')
        }
        this.renderOrderTable(true);
      });
  }
}
