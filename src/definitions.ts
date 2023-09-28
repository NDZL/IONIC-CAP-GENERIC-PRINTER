export interface CapGenericPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  printHTML(options: { html: string }): Promise<void>;
}
