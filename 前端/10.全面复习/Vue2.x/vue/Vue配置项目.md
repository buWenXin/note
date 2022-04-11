[TOC]



## **Vue脚手架**

- 如果要使用vue脚手架,需要先安装node环境 
- 然后安装 vue脚手架



## **Vue项目文件结构**

- 



## **Vue打包项目**



## **Vue文件别名**

​		在vue.config.js中配置

```javascript
const path = require('path');//引入path模块
function resolve(dir) {
  return path.join(__dirname, dir)//path.join(__dirname)设置绝对路径
}
module.exports = {
  chainWebpack: (config) => {
    config.resolve.alias
      .set('@', resolve('./src'))
      .set('components', resolve('./src/components'))
      .set('views', resolve('src/views'))
      .set('assets', resolve('src/assets'))
      .set('network', resolve('src/network'))
      .set('common', resolve('src/common'))
    //set第一个参数：设置的别名，第二个参数：设置的路径
  }
}
```



## 项目部署

​	window下面的部署

- 使用Nginx软件来部署项目
- 下载稳定版
- 在官网下载并安装Nginx, 安装Nginx注意 需要放在根目录下面, 文件名不能是中文
- 运行exe文件 ,然后进入 http://localhost/ ,就能看到服务已经启动,就代表行了
- 然后将要部署的项目打包, 并将dist文件夹放入到Nginx里面,并修改配置,使入口指向dist
- 修改配置  找到 conf 里面的 nginx.conf  ,在nginx.conf里面找到 location,将指向html文件夹更改为 dist  然后重启服务,



## 解决移动端300延迟

