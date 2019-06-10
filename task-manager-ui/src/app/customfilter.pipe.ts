import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'customfilter'
})
export class CustomfilterPipe implements PipeTransform {

  transform(items: any, prop:string, args?: any): any {
    if (!items || !args) {
      return items;
    }
    // filter items array, items which match and return true will be
    // kept, false will be filtered out
    if(prop === "parentTask") {
      return items.filter(item => item["parentTask"] && item["parentTask"]["parentTask"].indexOf(args) !== -1);
    }
    else if(prop === "startDate" || prop === "endDate") {
      //let date = moment(args).format("YYYY-MM-DD");
      return items.filter(item => (moment(item[prop]).format("MM/DD/YYYY")).indexOf(args) !== -1);
    }
    return items.filter(item => item[prop].indexOf(args) !== -1);
  }

}