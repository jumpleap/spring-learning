package com.example.log;

// 门面模式的实现
public class FacadePatternDemo {
    public static void main(String[] args) {
        LightFacade lightFacade = new LightFacade();
        lightFacade.lightOn();
        lightFacade.lightOff();
    }
}

interface light {
    void lightOn();
    void lightOff();
}

// 灯的门面
class LightFacade {
    private LivingRoomLight livingRoomLight = new LivingRoomLight();
    private HallLight hallLight = new HallLight();
    private DiningLight diningLight = new DiningLight();

    public void lightOn() {
        livingRoomLight.lightOn();
        hallLight.lightOn();
        diningLight.lightOn();
    }

    public void lightOff() {
        livingRoomLight.lightOff();
        hallLight.lightOff();
        diningLight.lightOff();
    }
}

class LivingRoomLight implements light {
    @Override
    public void lightOn() {
        System.out.println("打开客厅灯");
    }

    @Override
    public void lightOff() {
        System.out.println("关闭客厅灯");
    }
}

class HallLight implements light {

    @Override
    public void lightOn() {
        System.out.println("打开走廊灯");
    }

    @Override
    public void lightOff() {
        System.out.println("关闭走廊灯");
    }
}

class DiningLight implements light {

    @Override
    public void lightOn() {
        System.out.println("打开餐厅灯");
    }

    @Override
    public void lightOff() {
        System.out.println("关闭餐厅灯");
    }
}