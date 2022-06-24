

### TS2307: Cannot find module '*.vue' or its corresponding type declarations.

解决方式，在跟目录中添加 env.d.ts文件，在文件中添加

```ts
//解决组件类型报错
declare module "*.vue" {
   import { DefineComponent } from "vue";
   // eslint-disable-next-line @typescript-eslint/no-explicit-any, @typescript-eslint/ban-types
   const component: DefineComponent<{}, {}, any>;
   export default component;
}
```