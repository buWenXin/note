



### Vant下载与使用

- Vant 下载命令   cnpm i vant -S

- 使用方式  自动按需引入组件 

  - [babel-plugin-import](https://github.com/ant-design/babel-plugin-import) 是一款 babel 插件，它会在编译过程中将 import 的写法自动转换为按需引入的方式。
  - 下载命令   cnpm i babel-plugin-import -D
  - 然后在vue的 babel.config.js中配置  将下面代码放入即可
  - 这样插件就会根据使用的组件进行按需加载,减少文件体质

  ```javascript
  module.exports = {
    plugins: [
      ['import', {
        libraryName: 'vant',
        libraryDirectory: 'es',
        style: true
      }, 'vant']
    ]
  };
  ```

- 组件使用: 参考 vant官网 使用组件