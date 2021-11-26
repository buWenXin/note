

# Web打印控制Lodop在vue中的使用



### Lodop是什么?

- Lodop 是一个Web打印插件，相比于浏览器自带的打印功能,  使用这个插件可以跳过预览，直接进行打印。
- 不要小看直接打印这个功能，在很多的业务中，我们都是需要不预览直接进行打印，但是浏览器自带的打印功能根本无法实现，浏览器的打印功能必须要先进入浏览器的预览页面，然后点击打印才能进行打印。

### 如何在vue中使用 Lodop?

#### 1.  安装与导入

1. 首先在 `lodop` 官网下载 Lodop发行包 ,地址 [http://www.c-lodop.com/download.html)](http://www.c-lodop.com/download.html)  下载完成后进行解压

2. 解压完成后，点击exe文件进行安装

3. 从解压文件中找到LodopFuncs.js文件，将这个文件复制一份到你的项目中

   ![](C:\Users\Administrator\Desktop\日常笔记\插件\解压.png)

4. 在LodopFuncs.js文件中找到 getLodop 这个函数， 将其导出

5. 在需要使用打印的页面，引入 getLodop 函数

#### 2. 在vue中使用打印功能

```javascript
<template>
    <div>
        <div ref="print">
            <div>我是打印内容</div>
        </div>
        <div @click="printClick">打印</div>
    </div>
</template>

<script>
// 引入打印函数
import { getLodop } from './api/LodopFuncs';
export default {
    name: 'print',
    methods: {
        printClick() {
            // 调用打印函数
            this.lodopPrint();
        },
        lodopPrint() {
            //初始化打印函数
            let LODOP = getLodop();
            // 初始化打印
            LODOP.PRINT_INIT();
            // 设置纸张大小
            LODOP.SET_PRINT_PAGESIZE(1, '100mm', '150mm');
            // 设置打印内容大小
            LODOP.ADD_PRINT_HTM(0, 0, '283pt', '510pt', this.$refs.print.innerHTML);
            // 直接打印 
            // LODOP.PRINT();
            // 打印预览
            LODOP.PREVIEW();
        }
    }
};
</script>

```

> 以上只是简单介绍了Lodop插件如何在vue中使用，并没有涉及更多的打印功能使用设计。
