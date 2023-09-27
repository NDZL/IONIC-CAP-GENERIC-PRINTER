import { registerPlugin } from '@capacitor/core';

import type { CapGenericPrinterPlugin } from './definitions';

const CapGenericPrinter = registerPlugin<CapGenericPrinterPlugin>('CapGenericPrinter', {
  web: () => import('./web').then(m => new m.CapGenericPrinterWeb()),
});

export * from './definitions';
export { CapGenericPrinter };
