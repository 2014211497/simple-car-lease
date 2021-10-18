import {Base64} from 'js-base64/base64';
import {Md5} from 'ts-md5';

declare var layui: any;


export class AppUtil {

  public static base64Decode(base64: string): string {
    return Base64.decode(base64);
  }

  public static base64Encode(text: string): string {
    return Base64.encode(text);
  }

  public static encodeWithBase64AndMd5(text: string): string {
    return Md5.hashStr(this.base64Encode(text));
  }

  public static formatTimestamp(timestamp: any): string {
    const date = new Date(timestamp * 1000); // 根据时间戳生成的时间对象
    const yy = date.getFullYear();      // 年
    const mm = date.getMonth() + 1;     // 月
    const dd = date.getDate();          // 日
    const hh = date.getHours();         // 时
    const ii = date.getMinutes();       // 分
    const ss = date.getSeconds();       // 秒
    let clock = yy + '-';
    if (mm < 10) {
      clock += '0';
    }
    clock += mm + '-';
    if (dd < 10) {
      clock += '0';
    }
    clock += dd + ' ';
    if (hh < 10) {
      clock += '0';
    }
    clock += hh + ':';
    if (ii < 10) {
      clock += '0';
    }
    clock += ii + ':';
    if (ss < 10) {
      clock += '0';
    }
    clock += ss;
    return clock;
  }

  public static uuid32(): string {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
      // tslint:disable-next-line:no-bitwise
      const r = Math.random() * 16 | 0;
      // tslint:disable-next-line:no-bitwise
      const v = c === 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16).replace('/-/g', '');
    });
  }

  public static copy(e: any): any {
    return JSON.parse(JSON.stringify(e));
  }

  public static mapToArray(map: Map<any, any>): Array<any> {
    let array: any[];
    array = [];
    map.forEach(value => array.push(value));
    return array;
  }

  public static arrayToMap(array: Array<any>): Map<any, any> {
    const map = new Map<any, any>();
    array.forEach(value => map.set(value.id, value));
    return map;
  }

  public static setToArray(set: Set<any>): Array<any> {
    let array: any[];
    array = [];
    set.forEach(value => array.push(value));
    return array;
  }

  public static wrapTableListData(dataList: any, selectListParam: any): void {
    const startSeq = selectListParam.size * Math.max(0, selectListParam.page - 1) + 1;
    for (let i = 0; i < dataList.length; i++) {
      const e = dataList[i];
      e._seq = startSeq + i;
      e.createTimeStr = e.createTime == null ? '' : AppUtil.formatTimestamp(e.createTime);
      e.beginTimeStr = e.beginTime == null ? '' : AppUtil.formatTimestamp(e.beginTime);
      e.endTimeStr = e.endTime == null ? '' : AppUtil.formatTimestamp(e.endTime);
    }
  }

  public static tips(text: string): void {
    layui.layer.msg(text);
  }

  public static okTips(text: string): void {
    layui.layer.msg(text);
  }

  public static errTips(text: string): void {
    layui.layer.msg(text);
  }

  public static isNullOrEmpty(text: string | null): boolean {
    return text === null || text.trim().length === 0;
  }

  public static isNullOrUndefined(x: any): boolean {
    return x === null || x === undefined;
  }
}
