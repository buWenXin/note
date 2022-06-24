

### 1. 下载 element-push

```sh
npm install element-plus --save   
```

### 2. 在项目中按需导入：

用到两个插件：`unplugin-vue-components`   `unplugin-auto-import`

```sh
npm install -D unplugin-vue-components unplugin-auto-import
```

然后在vite.config.ts中添加插件

```ts
plugins: [
    //按需导入插件
    AutoImport({
        resolvers: [ElementPlusResolver()],
    }),
    //按需导入插件
    Components({
        resolvers: [ElementPlusResolver()],
    }),
],
```



### 3.动态改变Element-push的主题颜色

使用VueUse库：useCssVar()函数；

```ts
const key = ref('--el-color-success')//定义的主题变量
const colorVal = useCssVar(key)
colorVal.value = "red"//修改css变量的值
```

### 4. 使用暗黑主题

在main.js中导入element-push的暗黑主题

```ts
//main.js中
import 'element-plus/theme-chalk/dark/css-vars.css'
```

使用VueUse库：useDark()函数，来控制主题切换，并且会将值存储到本地存储中进行持久化。

```ts
const isDark = useDark()

function three() {
   isDark.value = !isDark.value;
}
```