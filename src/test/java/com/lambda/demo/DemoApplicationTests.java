package com.lambda.demo;

import com.lambda.demo.model.Car;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

  //过滤 filter
  @Test
  public void filterTest(){
    // 初始化车辆
    List<Car> cars = Car.InitCar();
    // 筛选车辆时黑色的车 并打印
    cars.stream()
        .filter(car -> car.getColor().equals("black"))
        .forEach(c-> System.out.println(c.getCarNum()));
    //也可把筛选出的车辆转为集合
    List<Car> result = cars.stream().filter(car -> car.getColor().equals("black")).collect(Collectors.toList());


  }

  //排序 sorted 默认升序
  @Test
  public void sortTest(){
    int[] array = {2, 3, 7, 9, 1, 14, 5};
    Arrays.stream(array).sorted().forEach(System.out::print);
    //输出 1 2 3 5 7 9 14
  }

  //distinct 去重
  @Test
  public void distinctTest(){
    int[] ints = {5,6,5,6,27};
    // 5 6 27
    Arrays.stream(ints).distinct().forEach(System.out::println);
  }
  //截断 limit: 限流操作，截取Stream前n个元素，n为正整数且小于Stream总条数
  @Test
  public void limitTest(){
    int[] ints = {12,61,81,5};

    Arrays.stream(ints).limit(2).forEach(System.out::println);
    //输出 12 61
  }

  //跳跃 skip: 跳过Stream前n个元素，n为正整数且小于Stream总条数
  @Test
  public void skipTest(){
    int[] ints = {5,6,5,6,27};

    Arrays.stream(ints).skip(4).forEach(System.out::println);

    // 输出 27
  }
  //映射 map : 转换函数，接受一个函数为参数，将其映射在每一个元素上，转换成新的元素。
  @Test
  public void mapTest(){
    // 初始化车辆
    List<Car> cars = Car.InitCar();
    // 只获得车的车牌号
    cars.stream().limit(1)
        .map(Car::getCarNum)
        .forEach(System.out::println);

    //输出 浙A88888
  }

  //任意匹配 anyMatch ：函数任意匹配到流中的一个元素返回真。
  @Test
  public void anyMatchTest(){
    // 初始化车辆
    List<Car> cars = Car.InitCar();
    // 任意匹配红色的车
    boolean yello = cars.stream()
        .anyMatch(car -> car.getColor().equals("red"));
    System.out.println(yello);
    //输出：true
  }

  //完全匹配 allMatch：noneMatch函数没有匹配到流中的任意一个元素返回为真
  @Test
  public void noneMatchTest(){
    // 初始化车辆
    List<Car> cars = Car.InitCar();

    //若集合中全部都包含”88888“这几个数字则返回true，否则返回false
    boolean zb = cars.stream()
        .allMatch(car -> car.getColor().equals("88888"));
    System.out.println(zb);

    //输出 true

    //若集合中不存在“浙C88888”这个车牌的车则返回true，否则返回false
    boolean zc = cars.stream()
        .noneMatch(car -> car.getColor().equals("浙C88888"));
    System.out.println(zc);
    // 输出 true
  }

  // 随机从Stream中返回一个元素 findAny：函数任意查找流中的一个元素返回。
  @Test
  public void findAnyTest(){
    // 初始化车辆
    List<Car> cars = Car.InitCar();
    // 从stream中随机返回一个对象元素
    Optional<Car> anyCar = cars.stream().findAny();
    //若为空则new一个新的Car
    Car car = anyCar.orElse(new Car("无", "无"));

    System.out.println(car.getColor()+","+car.getCarNum());
    //输出：black,浙A88888
  }

  //获取Stream中第一个元素 findFirst：函数寻找流中的第一个元素。
  @Test
  public void findFirstTest(){
    // 初始化车辆
    List<Car> cars = Car.InitCar();
    // 从stream中返回第一个对象元素
    Optional<Car> anyCar = cars.stream().findFirst();
    System.out.println(anyCar.get().getColor()+","+anyCar.get().getCarNum());
    // 输出：black,浙A88888
  }
// reduce 归约: reduce函数将前一个入参数和后一个入参进行操作后的值做为第下一次操作的前一个入参，以此类推。
  @Test
  public void reduceTest() {

    //reduce第一个参数: identity
    //identity是初始值，identity的值会加上后面计算的值并一起返回(进行流操作的时候 identity会被当作集合元素之一)

    //Integer类型 测试
    List<Integer> iList = new ArrayList<>();
    iList.add(1);
    iList.add(2);
    iList.add(3);
    iList.add(4);
    //求元素中最小值                                           //这里用到了::写法，这是lambda表达式一种独特的写法
    Integer i =iList.stream().reduce(0, Integer::min);//表示对象调用方法，且这个方法需要传入的参数与lambda输出的参数一致

    //这就说明 管道流已经把0算集合内的的元素了,因为list的元素最小的是1。
    //结果：0

    Integer sum =iList.stream().reduce(0, Integer::sum);//求元素之和

    //结果：10

    Integer max =iList.stream().reduce(0, Integer::max);//求元素中最大值

    //结果：4

    Integer product =iList.stream().reduce(1,(i1,i2)-> i1 * i2);//求元素之积
//    System.out.println(product);
    //结果：24


    //String类型 测试
    String m = "小明";
    List<String> aList = new ArrayList<>();
    aList.add("回家的路上");
    aList.add("遇到一条狗狗");
    aList.add("它有两只雪白色的耳朵");
    aList.add("一双水汪汪的眼睛");
    aList.add("四条健硕的腿腿");
    String s =aList.stream().reduce(m,(s1,s2)->s1+s2);
    System.out.println(s);

    //结果：小明回家看书遇到一条狗它有两个雪白色的耳朵一双明亮的眼睛四条健硕的腿腿
  }

// 由文件创建流
  @Test
  public void buildStreamByFile(){
    try {
      Stream<String> lines = Files.lines(Paths.get("E:\\Github\\README.md"), Charset.defaultCharset());
      lines.map(s -> s.split(""))
          .flatMap(Arrays::stream)
          .forEach(System.out::print);
      //输出：测试目录：test/java/com/lambda/demo接口类，实体类：main 下
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
