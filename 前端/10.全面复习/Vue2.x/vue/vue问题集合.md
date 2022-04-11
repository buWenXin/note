[TOC]





#### 跳转到非TabBar页面隐藏Tabbar

- 把TabBar的路由路径,放到一个数组中

- 使用计算属性,判断当前的路由路径是否是TabBar路径,如果不是就返回false

- 使用数组判断方法 `includes` 判断多个条件

- 使用v-show,判断计算属性的值,决定隐藏于显示

    ```vue
    <template>
    <!-- v-show 判断隐藏与显示 -->
      <div id="tabbar" v-show="control">
        <slot></slot>
      </div>
    </template>
    
    <script>
    export default {
      name: "tabbar",
      data() {
        return {
          // TabBar页面的路由路径
          path: ["/home", "/user", "/video"],
        };
      },
      computed: {
        // 判断是否在TabBar页面,如果不是就返回false
        control() {
          // 使用数组判断方法i ncludes 判断多个条件
          if (this.path.includes(this.$route.path)) {
            return true;
          }
          return false;
        },
      },
    };
    </script>
    ```


#### 在生命周期中获取不到数据?

- 在`computed`中或者`watch`中可以获取到最新的值

#### 多条件判断

- 使用数组的方法,  

#### 动态绑定图片地址问题

1. 问题描述:  如果直接通过 v-bind 来绑定图片的地址, 就会出现图片加载不出来
2. 解决  通过  url: require("@/assets/img/tabbar/home.svg"),来解决

#### 子组件图片路径问题

1. 向子组件传递静态图片路径一定要使用 require



### 组件的命名

- 公共组件直接命名
- 页面私有的组件使用 页面名+组件名 的命名法则

