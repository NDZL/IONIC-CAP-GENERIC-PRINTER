import { WebPlugin } from '@capacitor/core';

import type { CapGenericPrinterPlugin } from './definitions';

export class CapGenericPrinterWeb extends WebPlugin implements CapGenericPrinterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
