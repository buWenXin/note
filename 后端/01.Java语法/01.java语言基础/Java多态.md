







#### java 类的多态

> 多态：**在声明时是声明了一个父类类型,但是实际传入时,可以传入一个它的子类类型**

```java
//父类
class Proson {
    void show() {
        System.out.println("我是Prosn");
    }
}
//Proson的继承类 子类
class SuperMan extends Proson {
    @Override
    void show() {
        System.out.println("我是超人");
    }
}
```

```java
//定义的是一个父类类型
void getTest(Proson proson) {
    proson.show();
}
//使用:在使用的时候可以传入它的子类类型
void test() {
    getTest(new SuperMan());//输出 我是超人
}
```

- **多态的特点**
  - 多态是运行时确定,而不是编译时, 在编译时 编译器无法确定调用的是哪一个方法,只有在运行时才能确定
  - Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
- **多态有什么作用?**
  - 在创建父类的时候,不想使用父类的方法,而是调用子类重写的方法, 就需要使用多态
  - 在作为形参时,指定为父类,可以传入子类,如果子类有覆写的方法,那么就会调用子类覆写的方法
