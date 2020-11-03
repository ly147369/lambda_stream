package com.lambda.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Car {
String color;
String carNum;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getCarNum() {
    return carNum;
  }

  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }

  public Car(String color, String carNum) {
    this.color = color;
    this.carNum = carNum;
  }
  public static List<Car> InitCar(){
    ArrayList<Car> carList = new ArrayList<>();
    Car car1 = new Car("black", "浙A88888");
    Car car2 = new Car("white", "沪B88888");
    Car car3 = new Car("blue", "苏A88888");
    Car car4 = new Car("black", "皖B88888");
    Car car5 = new Car("red", "浙A66666");

    carList.add(car1);
    carList.add(car2);
    carList.add(car3);
    carList.add(car4);
    carList.add(car5);
    return carList;
  }
}
