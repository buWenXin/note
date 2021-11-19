



# 设置动态路由

> 设置动态路由只需要两步 1. 从后端获取路由页面信息 2. 通过 router.addRoute()方法,把路由信息添加到Router中,完成配置

1. 先设置静态的路由页面 如 登录页面,404页面,错误页面 这些静态路由的页面不从后端获取
2. 在路由的钩子函数, router.beforeEach()中,获取动态路由信息,并将信息缓存到Vuex中,用于后面渲染菜单
3. 获取到路由信息后,将路由信息对象,通过 router.addRoute() 添加到Router对象中
4. 处理 在刷新时跳转404页面问题

```javascript
//设置动态路由
router.beforeEach((to, from, next) => {
   const token = getToken();
   //判断是否已经登录,并且不在登录页面
   if (to.fullPath != '/login' && token) {
      //通过判断Vuex中的数据,来决绝页面刷新 Vuex中的数据丢失问题, 如果Vuex中没有数据,则发送请求获取数据
      if (store.state.permission.routes == 0) {
         //通过Vuex来获取数据,并存储数据和把路由信息添加到router中
         store.dispatch('GenerateRoutes').then(res => {
            //配置*铺抓错误,重定向404
            let o = {
               path: '*',
               redirect: '/404'
            };
            //在所有的路由添加完成后 再添加铺抓页面,不然会出现问题
            router.addRoute(o);
            next({ ...to, replace: true }); // hack方法 确保addRoutes已完成
         });
      } else {
         next();
      }
   } else {
      next();
   }
});


//Vuex中----------------------------
const permission = {
   state: {
      routes: []
   },
   mutations: {
      SET_ROUTES: (state, routes) => {
         state.routes = routes;
      }
   },
   actions: {
      // 获取路由数据
      GenerateRoutes({ commit }) {
         return new Promise(resolve => {
            // 向后端请求路由数据
            getUserMenus().then(res => {
               //处理获取到的数据,并添加到Router中
               setRouter(res.data);
               //提交Vuex事件,将菜单信息添加到Vuex中
               commit('SET_ROUTES', res.data);
               resolve(res);
            });
         });
      }
   }
};

//配置路由映射数组
function setRouter(data) {
   let list = [];
    //遍历,获取所有路由信息
   data.forEach(item => {
      if (item.children) {
         list = list.concat(item.children);
      }
   });
   //所有路由信息, 进行遍历然后添加进路由中
   list.forEach(item => {
      //处理mate
      item.meta = {
         title: item.name
      };
      // 处理组件映射
      item.component = getView(item.component);
      // 添加进路由
      router.addRoute('level', item);
   });
}

//获取页面对象
function getView(viewUrl) {
   return (resolve) => require([`@/components/page/${viewUrl}`], resolve);
}

export default permission;
```









#### 动态路由刷新后跳转404页面问题

> 在配置完动态路由后,如果刷新页面 就会跳转到404页面

- 原因: 这是由于在进行路由跳转时,还没把动态路由添加进路由中,此时
- 解决方法: 在添加完所有的页面后,再把 通用匹配,重定向404的路由配置添加进去

