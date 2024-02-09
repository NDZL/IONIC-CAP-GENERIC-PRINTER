import { Component } from '@angular/core';
import { CapGenericPrinter } from 'generic-printer'

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  txtPrint: string = "'<h1>hello IONIC printer!</h1>'";

  constructor() {

   let echoed =  CapGenericPrinter.echo({value: 'hello world by Nik'})
    console.log(echoed)

  }

  btnPrint(ttp: string){
    //CapGenericPrinter.printHTML({html: '<h1>hello IONIC printer! </h1>'})
    CapGenericPrinter.printHTML({html: ttp})
  }

  btnDWssm(who:string){
    CapGenericPrinter.addDWProfileSSM({foo:who})
  }

  btnDWenterprise(who:string){
    CapGenericPrinter.addDWProfileENTERPRISE({foo:who})
  }

}
