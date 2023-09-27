export interface CapGenericPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
