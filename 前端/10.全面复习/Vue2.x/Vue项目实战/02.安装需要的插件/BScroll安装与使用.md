

### Better-scroll的安装与使用

- 全能力下载与安装  命令   **cnpm install better-scroll --save**
- 使用与导入:
  - 全能力导入  **import BScroll from "better-scroll"**
  - 使用:     new BScroll("挂载的元素",配置对象)
- 注意事项:
  - 想要实现better-scroll的滑动,必须要有一个固定高度的父元素,且子元素的高度高于父元素才能使用滑动,且滑动只对挂载元素的第一个子元素生效
  - 尽量在vue的mounted钩子中的 this.nextTick()回调里面初期化better-scroll



### Better-scroll在vue中的使用

​	**需要对better-scroll进行封装,然后再使用,如果不封装,单个组件对它就会产生很大的依赖,对性能,和代码的维护带来很不好的效果,所以将它封装成一个公共vue的组件,需要使用时,将vue组件导入即可**

- 基本使用  :  通过new BScroll ()来创建BScroll对象,要求传入两个参数  第一: 挂载给哪个元素   第二: 传入一个配置对象 
- 在vue中使用:  
  - 创建一个组件,以组件的方式使用BScroll,这样更便于维护
  - 在vue的生命周期 **mounted** 的 **this.$nextTick** 中创建 `BScroll` 对象
  - 挂载元素时,必须使用 **ref** 来标识一个元素进行挂载 ,  new Bscroll(this.$refs.元素,{配置对象})
  - 实现滚动, 必须给挂载的元素一个固定高度, 并且内容的高度大于挂载元素的高度才能滚动
  - 在加载图片的时候,往往会出现滑动不了的问题,原因是因为在加载图片的时候,已经获取可滑动高度,解决需要从新获取滑动高度