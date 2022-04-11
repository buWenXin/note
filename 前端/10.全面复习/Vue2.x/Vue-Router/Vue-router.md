[TOC]

### 01.路径的基本使用

- **创建路由对象**
  
- 导入VueRouter插件 ==> 通过Vue.use(插件)组件插件 ==> 通过new VueRouter创建路由对象 ==> 使用export default 将路由对象导出
  
- **创建路由组件**

    - 在`views`文件夹下创建路由组件

- **配置路由映射关系**

    - 在路由对象里面 使用 routes属性配置映射关系,routes是一个数组类型 ,将路径和组件用对象的语法进行配置

- **路由的懒加载**

    - 当打包构建应用时，JavaScript 包会变得非常大，影响页面加载,所有Vue-router为我们提供了懒加载的方式
    - 结合 Vue 的[异步组件](https://cn.vuejs.org/v2/guide/components-dynamic-async.html#异步组件)和 Webpack 的[代码分割功能](https://doc.webpack-china.org/guides/code-splitting-async/#require-ensure-/)，轻松实现路由组件的*懒加载*。
    - 语法: `const Foo = () => import('组件路径')`

- **挂载并使用**

    - 在main.js中,导入`vue-router`对象,*并挂载使用*

- **路由重定向**

    - 重定向也是通过 `routes` 配置来完成的
    - 语法: { path: '/', redirect: "/home" }

- 

    

    ```javascript
    import Vue from 'vue'; //导入 vue
    import VueRouter from 'vue-router'; //导入 Vue-router
    
    Vue.use(VueRouter); //安装 Vue-router
    
    // 路由的懒加载
    const Home = () => import("../views/home/Home.vue");
    const Video = () => import("../views/video/Video.vue");
    const User = () => import("../views/user/User.vue");
    
    //创建vue-router对象,并导出
    export const router = new VueRouter({
      base: '/', 
      mode: 'history', //路径模式
      routes: [  //routes配置路径映射,
        { path: '/', redirect: "/home" }, //路由重定向
        { path: '/home', component: Home },
        { path: '/home', component: Video },
        { path: '/home', component: User },
      ]
    });
    
    //main.js
    //导入vue-router对象
    import router from './router';
    new Vue({
      el: "#app",
      router, //挂载使用
    })
    ```

​	

### 02.编程式导航

- `this.$router.push('path')`  通过代码进行路由跳转
- `this.$router.replace() `  跟 `router.push` 很像，唯一的不同就是，它不能回退
- `this.$router.go()`    这个方法的参数是一个整数，意思是在 history 记录中向前或者后退多少步
- `this.$router.back()`    返回上层路由
- `this.$router.forward() `  



### 动态路由



### 03.携带参数的路由

> 进行路由跳转的时候,携带参数 有两种方式 `params`和 `query`
>

#### params的方式

- `params`方式就是通过动态路由来完成的

- 首先配置动态路由: `/router/:id`

- 传值的方式: 在`path`后面跟上对应的值 

- 传递后形成的路径: `/router/123`

- 在路由界面通过 `this.$route.params.id`来进行获取

- ```javascript
    //配置动态路由
     { path: '/playlist/:id', component: playlist },
       
    //进行路由跳转的时候携带参数 
     this.$router.push('/playlist/'+id)
    
    //在路由页面取出
    this.$route.params.id
    ```

    

#### query的方式

- 在路由跳转的时候是可以携带*查询参数*(`query`)

- 配置路由格式: `/router` 也就是普通的配置

- 传递的方式: 对象中使用`query`的*key*作为传递的方式

- 传递后形成的路径: `/router?id=123`

- ```javascript
    //配置路由
    { path: '/playlist', component: playlist },
    
    //进行路由跳转并携带查询参数
     this.$router.push({ path: "/playlist", query: { id:123 } })
    
    //在路由页面取出
    this.$route.query.id   //123
    ```

    



### 04.路由的嵌套

## 移动端回到顶部

- 获取到顶部的距离  document.documentElement.scrollTop
- 通过定时器一直减到顶部的距离 
- 当顶部距离为0清除定时器

