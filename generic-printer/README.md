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
