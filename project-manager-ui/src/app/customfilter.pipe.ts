import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'customfilter'
})
export class CustomfilterPipe implements PipeTransform {

  transform(items: any, args?: any): any {
    if (!items || !args) {
      return items;
    }    
    return items.filter(item => JSON.stringify(item).toLowerCase().indexOf(args.toLowerCase()) !== -1);
  }

}