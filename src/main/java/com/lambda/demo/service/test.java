package com.lambda.demo.service;



public class test {

  public static void shylock(PeopleService peopleService) {
    String msg = peopleService.socialize(
        "shylock"); //这里的shylock还是参数name 不过现在是把整个”行为“传入了进来，因为传入的是 "社交主持人:" +name 所以返回的是 社交主持人:shylock
    System.out.println(msg);
  }

  public static void shylock1(PeopleService peopleService) {
    String msg = peopleService.socialize(
        "shylock"); //这里的shylock还是参数name 不过现在是把整个”行为“传入了进来，因为传入的是 "社交主持人:" +name 所以返回的是 社交主持人:shylock
    System.out.println(msg);
  }

  public static void main(String[] args) {


    //jdk1.8之前 如果想要得到一个接口的方法 需要编写匿名内部类(或者编写实现类)
    PersonService personService = new PersonService() {
      @Override
      public void readingBook() {
        System.out.println("say yeah");
      }
    };

    //jdk1.8之后根据函数式接口的更新，则可以直接编写 ()->{}  "()"代表参数 ”->“lambda表达式的语法  ”{}“是方法体
    //第一种写法（最常用）
    PersonService personService1 = () -> {
      System.out.println("say yeah");
    };

    //第二种写法（方法体内只有一行代码）
    PersonService personService2 = () -> System.out.println("say yeah");

    //第三种 带参数并且有返回值的时候(带参且有返回值最常用)
    PeopleService peopleService = (name) ->
    {
      return "社交主持人:" + name;
    };

    //第四种 带参数并且有返回值的时候(方法体内只有一行代码可以直接省略返回值
    PeopleService peopleService1 = (name) -> "社交主持人:" + name;
    test.shylock(peopleService1);

    //当外部变量传入lambda表达式当中时 必须不可变 即 最好定义final类型 这里是隐式的final类型 不然会报错！
    final int i = 1;
//    test.shylock1((name) -> i + name); //把函数当作参数传入（传入行为）
  }


}
