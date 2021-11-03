







#### java 类的多态

> 多态指的是,在声明时是声明了一个父类类型,但是实际传入时,可以传入一个子类,并且如果子类重写了父类中的方法,那么在调用时,就会调用的是子类重写后的方法   *即:声明是父类类型,可以传入一个子类,如果子类有重写父类的方法,在调用时就会调用子类重写的方法*

```java
//父类
class Proson {
    void show() {
        System.out.println("我是Prosn");
    }
}
//继承类
class SuperMan extends Proson {
    @Override
    void show() {
        System.out.println("我是超人");
    }
}
//多态的体现
public class Test02 {
    @Test
    public void test() {
        //声明为父类,但是可以传入一个父类的继承类
        Proson proson = new SuperMan();
        //此时调用的是传入子类中重写的方法
        proson.show(); //输出 "我是超人"
        //即:声明是父类类型,但是传入了一个子类,如果子类有重写父类的方法,在调用时就会调用子类重写的方法
    }
}

//继承类2
class SuperWoman extends Proson {
    @Override
    void show() {
        System.out.println("我是女超人");
    }
}

/**
 * 在打架,要求来一个人
 */
class Fight {
    private Proson proson;
    //要求传入一个人的对象,或者人的子类
    Fight(Proson proson) {
        this.proson = proson;
    }
    void nowFight() {
        proson.show();
    }
}
//多态的作用
public class Test02 {
    @Test
    public void test2() {
        SuperMan superMan = new SuperMan();
        SuperWoman superWoman = new SuperWoman();
        //1.传入男超人
        Fight fight = new Fight(superMan);
        fight.nowFight(); //输出  我是男超人
        //2.传入女超人
        Fight fight2 = new Fight(superMan);
        fight2.nowFight(); //输出  我是女超人
    }
}
```

- **多态的特点**
  - 多态是运行时确定,而不是编译时, 在编译时 编译器无法确定调用的是哪一个方法,只有在运行时才能确定
  - Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
- **多态有什么作用?**
  - 在创建父类的时候,不想使用父类的方法,而是调用子类重写的方法, 就需要使用多态
  - 在作为形参时,指定为父类,可以传入子类,如果子类有覆写的方法,那么就会调用子类覆写的方法
