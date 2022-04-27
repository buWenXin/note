



一个 `Promise对象` 有三种状态：

- *等待（pending）*: 初始状态，既没有被兑现，也没有被拒绝。
- *成功（fulfilled）*: 意味着操作成功完成。会调用promist对象的then方法注册的回调。
- *失败（rejected）*: 意味着操作失败。会调用promist对象的catch方法注册的回调。

## 1.基本使用

创建一个promise对象：

```ts
const promise = new Promise<number>((resolve, reject) => {
   let a = 1;
   a = a * 10;
   //3秒后调用resolve方法。
   setTimeout(() => {
      resolve(a)
   }, 3000)
})
//在三秒后，resolve方法被调用，promise的状态会变成 fulfilled，然后就会去调用.then方法。
promise.then(res => {
   console.log(res);
})

promise.catch(err => {
   console.log(err);
})
//finally,不管是成功和失败都会执行。
promise.finally(() => {
   
})
```

​	上面通过`new Promise`创建了一个promise对象，在创建的时候，要求传入一个函数，该函数接受两个参数`resolve`和 `reject`，分别代表了成功和失败两种状态。

- 如果调用了`resolve`，则会执行`promise`对象中的`then方法`。

- 如果调用了`reject`，则会执行`promise`对象中的`catch方法`。
- 如果两个方法都没有调用，则该`promise`对象会一直处于`pending`状态，直到调用两个方法之一。
- 调用了`resolve, reject中的一个`，不管成功还是失败`finally`方法都会去执行。

- `注意`：如果一直都没有调用上面两个方法之一，则promise的状态会一直是`pending`，会造成内存泄漏。

