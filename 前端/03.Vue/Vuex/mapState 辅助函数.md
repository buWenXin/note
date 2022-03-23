







#### 使用 `mapState` 辅助函数

> 作用: 通过 `mapState` 辅助函数可以将Vuex中的状态映射到Vue组件中的计算函数中
>
> 主要是获取数据的辅助函数，为了解决需要获取多个状态且不再那么重复以及冗余的问题，mapState会帮助我们自动生成计算属性。

```js
//1.导入 mapState函数
import { mapState } from 'vuex'

export default {
  // ...
  computed: mapState({
    // 箭头函数可使代码更简练  简化后的计算函数
    count: state => state.count,

    // 传字符串参数 'count' 等同于 `state => state.count`
    countAlias: 'count',

    // 为了能够使用 `this` 获取局部状态，必须使用常规函数
    countPlusLocalState (state) {
      return state.count + this.localCount
    }
  })
}
computed: {
    /**
     * 数组形式
     * 当映射的计算属性的名称与 state 的子节点名称相同时，我们也可以给 mapState 传一个字符串数组。
     * count 就会被映射到 state.cont
     * */
    ...mapState(["count", "name"]),
    /**
     * 对象形式
     */
    ...mapState({
      count, // 这种就是count:"count", 的简写
      count1: "count1",
      repeatCount: "count2", // 当组件中与vuex中的字符已经出现重复时，使用 repeatCount 来映射 store.state.count2
      count3: (state) => { // 映射 count3 为 store.state.conut3的值
        return state.count3
      },
      helloName: function (state) { // 为了能够使用 `this` 获取局部状态，必须使用常规函数，不能使用箭头函数
        return this.hello + state.name
      },
      addNumber: function (state) { // 为了能够使用 `this` 获取局部状态，必须使用常规函数，不能使用箭头函数
        return this.number + state.count
      }
    })
  },
```
