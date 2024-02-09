# generic-printer

LET'S PRINT!

## Install

```bash
npm install generic-printer
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`printHTML(...)`](#printhtml)
* [`addDWProfileSSM(...)`](#adddwprofilessm)
* [`addDWProfileENTERPRISE(...)`](#adddwprofileenterprise)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### printHTML(...)

```typescript
printHTML(options: { html: string; }) => Promise<void>
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ html: string; }</code> |

--------------------


### addDWProfileSSM(...)

```typescript
addDWProfileSSM(options: { foo: string; }) => Promise<void>
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ foo: string; }</code> |

--------------------


### addDWProfileENTERPRISE(...)

```typescript
addDWProfileENTERPRISE(options: { foo: string; }) => Promise<void>
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ foo: string; }</code> |

--------------------

</docgen-api>


### See it in action

Configure the Printing service according to your printer's brand
   
Run the app, type in the text you want to print
 
Push the PRINT button and adjust the settings if needed
 
Enjoy your print. Good reading!
 
Coding tips
App's home page (Typescript)
https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/blob/master/src/app/home/home.page.ts
and
https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/blob/master/src/app/home/home.page.html
PRINTER PLUGIN (Java / Android)
https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/tree/main/android/src/main/java/com/ndzl/cap/genericprinter

