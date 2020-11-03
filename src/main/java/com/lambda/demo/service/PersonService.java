package com.lambda.demo.service;

//这个注解主要是为了提醒维护人员或者其他项目人员这个类是函数式接口，只能编写一个抽象方法，如果编写其他抽象方法那么这个注解则会报错。
@FunctionalInterface
public interface PersonService {


  /**
   * JDK1.8以前不可以定义默认方法（default关键词修饰），1.8以后可以定义默认方法，并且可以直接在方法内实现(也可以编写实现类进行重写)！
   * 默认方法的出现主要是为了能够更方便的使用函数式编程（配合使用）！
   */
  default void playGame(){
    System.out.println("默认方法 玩游戏");
  }

  //默认方法可以有多个
  default void eatCake(){
    System.out.println("默认方法 吃蛋糕");
  }


  /**
   * 如果要使用函数式编程 就只能定义一个抽象方法(接口里定义的方法默认是抽象方法 abstract修饰)
   */
  void readingBook();

}
