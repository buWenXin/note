# ES6中的异步编程

### Promise对象







### async await

> async await 是ES6中新的异步编程方案, 它是 Generator 函数的语法糖。

- ***async***
  - **`async`有什么作用?**
    - `async`作为一个关键字放在函数的前面，表示该函数是一个异步函数，意味着该函数的执行不会阻塞后面代码的执行 异步函数的调用跟普通函数一样
    - `async`函数会返回一个 Promise 对象。
    - `async`函数内部`return`语句返回的值，会成为`then`方法回调函数的参数
    - `async`函数内部抛出错误，会导致返回的 Promise 对象变为`reject`状态。抛出的错误对象会被`catch`方法回调函数接收到。
  - 怎么使用`async`
    - 在函数前面添加`async`关键字

- ***await***
  - **`await`有什么作用?**
  - **如果使用`await`?**
  - `await`翻译为等待,该关键字只能用于`async`函数中,用于等待一个异步函数执行完成
  - `await`命令后面如果是一个 Promise 对象，那么返回该对象的结果。如果不是 Promise 对象，就直接返回对应的值。
  - `await`命令后面的 Promise 对象如果变为`reject`状态，则`reject`的参数会被`catch`方法的回调函数接收到。
  - 任何一个`await`语句后面的 Promise 对象变为`reject`状态，那么整个`async`函数都会中断执行。

