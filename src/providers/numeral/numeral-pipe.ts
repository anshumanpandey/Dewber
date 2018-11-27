import {Pipe,PipeTransform,Injectable} from "@angular/core"
import * as numeral from 'numeral';

@Pipe({
name:'numeralPipe'
})

@Injectable()
export class NumeralPipe implements PipeTransform{
    transform(count:any):any{ //eg: in count has value something like 52456.0
      if (count > 9999) {
          var numericalFormat = numeral(count).format('0a');
          return numericalFormat; // set to format it like 52,5k
      }
      return count;

  }; 
} 

numeral.register('locale', 'en-gb', {
  abbreviations: {
      thousand: 'k',
      million: 'm',
      billion: 'b',
      trillion: 't'
  }
});