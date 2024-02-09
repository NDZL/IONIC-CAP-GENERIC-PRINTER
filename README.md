# generic-printer

LET'S PRINT!

## Install

```bash
npm install generic-printer
npx cap sync
```

## API

<docgen-index>

* [`printHTML(...)`](#printhtml)
* [`addDWProfileSSM(...)`](#adddwprofilessm)
* [`addDWProfileENTERPRISE(...)`](#adddwprofileenterprise)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->


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


### PRINTING!

Configure the Printing service according to your printer's brand

![image](https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/assets/11386676/54cda3cf-f366-4474-9776-eda8f952defb)

![image](https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/assets/11386676/5399c1ad-1f8d-4823-8451-0f51c025750b)


Run the app, type in the text you want to print

![image](https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/assets/11386676/f678ac40-4dbe-4bdd-a672-fd0b87bb10c4)

Push the PRINT button and adjust the preview settings if needed

![image](https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/assets/11386676/8f2b3257-48f5-4fcf-b0d7-e8d39bb66af1)


### DATAWDGE PROFILES!

This sample code is showing 2 ways to add a DW profile

- both require that you have previously exported a profile from Datawegde and imported into the Capacitor Android branch in the ```assets``` foler
  
    ![image](https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/assets/11386676/6b835c91-ee75-4bc7-9ad0-d3580d1e0243)

- to install a profile by means of Zebra Secure Storage Manager (SSM), press INSTALL DW PROFILE VIA SSM
  
- to install a profile by means of the Zebra Public folders and the auto-import feature, press INSTALL DW PROFILE VIA ENTERPRISE FOLDER
  


 
Enjoy printing and scanning!
 

### Coding tips
App's home page (Typescript)
https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/blob/master/src/app/home/home.page.ts
and
https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/blob/master/src/app/home/home.page.html
PRINTER PLUGIN (Java / Android)
https://github.com/NDZL/IONIC-CAP-GENERIC-PRINTER/tree/main/android/src/main/java/com/ndzl/cap/genericprinter

