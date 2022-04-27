

思路:

- 在登录成功后，后端会返回给我们两个token，一个正常使用的通信token，一个用来刷新token的token。
- 刷新token的有效时间总会比通信token的时间长，所以在通信token过期后，就可以使用刷新token去从新获取新的token和刷新token。
- 有了这个刷新token后，就可以在axios的响应拦截中拦截响应，判断token 返回过期后，调用刷新token接口去更新token。



在响应拦截器中判断token是否已经过期，如果过期则调用刷新token的接口去更新token。

```ts
service.interceptors.response.use((res: AxiosResponse<ResponseData<any>>): Promise<ResponseData<any>> => {
    console.log(res);
    if (res.data.code == 200) {
        return Promise.resolve(res.data);
    } else if (res.data.code == 401) {
        ElMessage.error("认证失败");
        return Promise.reject();
    } else if (res.data.code == 409) {
        //token过期，调用接口更新token。
        /**
         * 1. 调用接口，刷新token
         * 2. token刷新完成后，重发该请求，然后响应。
         */

    } else {
        ElMessage.error(res.data.msg)
        return Promise.reject()
    }
}, () => {
    return Promise.reject();
})
```



问题：在刷新token期间，会其他的请求进来，要防止多次去刷新token，并且在token刷新完成后，去重新处理在刷新token这个时间内进来的其他请求：



- 定义一个变量：去控制是否已经在刷新token了，如果在刷新了就不再刷新。
- 定义一个数组：当第其他的请求进来，token正在刷新，将这个请求存到一个数组队列中，让这个请求处于等待中，一直等到刷新token后再逐个重试清空请求队列。
- 在这个拦截器中，返回的是一个promise对象，只要我们返回一个`pending`状态的`promise`对象（即不调用resolve），那么请求就会一直处于等待中，在token刷新完成后，再从数组队列中去拿请求，进行重发，然后在调用`resolve`将结果返回。

代码实现：

```ts
//用一个变量去控制是否已经在刷新token了，如果在刷新了就不再刷新。
let isRefresh: boolean = false;
//存储
let arr: Array<(token: string) => void> = []
service.interceptors.response.use((res: AxiosResponse<ResponseData<any>>): Promise<ResponseData<any>> => {
    console.log(res);
    if (res.data.code == 200) {
        return Promise.resolve(res.data);
    } else if (res.data.code == 409) {
        //跟后端约定的token过期状态码 409
        /**
         * 1.判断token是否正在刷新：
         *      没有在刷新，则正常去刷新，
         *      如果已经在刷新了，则将请求缓存到数组中去。
         */
        if (!isRefresh) {
            isRefresh = true;
            //刷新token的请求：

            //刷新完成后，去处理缓存的请求。
            arr.forEach(item => {
                item("token");
            })
        } else {
            /*
               token正在刷新，返回一个pending状态的promise(即不调用resolve)，并请请求存入到数组队列中，
                等待token刷新完成后，去重发请求然后调用resolve将结果返回。
             */
            return new Promise<ResponseData<any>>((resolve, reject) => {
                //将请求存入到一个队列中，以函数的方式存储。利用闭包，存储resolve
                arr.push((token: string): void => {
                    res.config.headers = {
                        token: token
                    };
                    resolve(service(config))
                })
            })
        }
    } else {
        ElMessage.error(res.data.msg)
        return Promise.reject()
    }
}, () => {
    return Promise.reject();
})
```



## 总结：

- 再响应拦截器中通过状态码去判断token是否已经过期，如果过期了则通过刷新token去跟新token。
- 在刷新token期间，会有其他的请求进来，要防止多次去刷新token，并且在token刷新完成后，去重新处理在刷新token这个时间内进来的其他请求：
  - 定义一个变量，控制当前是否在刷新token。
  - 定义一个数组队列，去存储在刷新token期间进来的token。
  - 如果token已经在刷新，则先返回一个pending状态的promise对象（即不调用resolve），让请求处于等待状态，然后将请求缓存进数组中。
  - 在token刷新完成后去从数组队列获取需要重发的请求，执行从新发送，并调用resolve将结果返回。
