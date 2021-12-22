# Vue中封装Axios

### 1.创建Axios实例

> 首先先创建Axios的实例，配置基本的公共信息，如baseURL，超时时间等等

```js
import axios from 'axios';
//设置默认请求头
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';
// 创建axios实例
const service = axios.create({
   // axios中请求配置有baseURL选项，表示请求URL公共部分
   baseURL: process.env.VUE_APP_BASE_API + '/api/', // 此处的 /api/ 地址，原因是后端的基础路径为 /api/
   // 超时
   timeout: 60000
});
```

### 2.设置请求拦截

> 请求拦截器 在请求发送前进行必要操作处理，例如添加统一`token`、请求体加验证、设置请求头，get请求映射params参数等等

```js
// 请求拦截器
service.interceptors.request.use(config => {
   // 是否需要设置 token
   const isToken = (config.headers || {}).isToken === false;
   if (getToken() && !isToken) {
      config.headers['token'] = getToken(); // 让每个请求携带自定义token 请根据实际情况自行修改
   }
   // get请求映射params参数
   if (config.method === 'get' && config.params) {
      let url = config.url + '?';
      for (const propName of Object.keys(config.params)) {
         const value = config.params[propName];
         var part = encodeURIComponent(propName) + '=';
         if (value !== null && typeof (value) !== 'undefined') {
            if (typeof value === 'object') {
               for (const key of Object.keys(value)) {
                  let params = propName + '[' + key + ']';
                  var subPart = encodeURIComponent(params) + '=';
                  url += subPart + encodeURIComponent(value[key]) + '&';
               }
            } else {
               url += part + encodeURIComponent(value) + '&';
            }
         }
      }
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
   }
   return config;
}, error => {
   Promise.reject(error);
});
```

### 3.响应拦截器

> *响应拦截器的作用在于：得到响应之后，对响应体的进行统一的处理，如判断token是否失效，统一处理响应状态码，对响应数据进行二次加工等等..*
>
> 在项目中，我们一般都会事先和后端约定`Token`过期的错误码，在响应拦截中，通过响应状态码来判断`Token`是否已经过期，如果`Token`已经过期，则让用户从新登录。
>
> 在响应拦截中，我们应该将所有的错误都拦截，在页面上提示错误信息，并通过`Promise.reject()`来将axios的状态改成失败，只有响应状态码为200时，我们才返回成功。*这样的好处在于，所有的错误都统一集中处理，不用再每一个使用axios的地方都去处理错误状态码*

```js
// 响应拦截器
service.interceptors.response.use(
    //对响应数据进行处理
    res => {
      // 获取响应状态码
      const code = res.data.code;
      // 获取错误信息
      const msg = res.data.message;
      if (code === 402) {//与后端约定的Token过期错误码402，在token失效后，让用户从新登录
         MessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
               confirmButtonText: '重新登录',
               cancelButtonText: '取消',
               type: 'warning'
            }
         ).then(() => {
            store.dispatch('LogOut').then(() => {
               location.href = '/index';
            });
         });
      } else if (code === 500) {//500错误
         Message({
            message: msg,
            type: 'error'
         });
         return Promise.reject(msg);
      } else if (code !== 200) {//其他状态码错误
         Notification.error({
            title: '错误',
            message: msg,
            duration: 3 * 1000
         });
         return Promise.reject(msg);
      } else {//200，成功
         return res.data;
      }
   },
   //对响应错误进行处理
   error => {
      let { message } = error;
      if (message === 'Network Error') {
         message = '后端接口连接异常';
      } else if (message.includes('timeout')) {
         message = '系统接口请求超时';
      } else if (message.includes('Request failed with status code')) {
         message = '系统接口' + message.substr(message.length - 3) + '异常';
      }
      Message({
         message: message,
         type: 'error'
      });
      return Promise.reject(error);
   }
);
```