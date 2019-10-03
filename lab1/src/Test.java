class Bicycle {
    private int gear;
    private int speed;

    Bicycle(int gear, int speed) {
        this.gear = gear;
        this.speed = speed;
    }

    public String toString() {
        return ("No of gears are " + gear + "\n" + "speed of bicycle is " + speed);
    }
}

class MountainBike extends Bicycle {
    private int seatHeight;

    MountainBike(int gear, int speed, int startHeight) throws Exception {
        super(gear, speed);

        if (startHeight < 20 || startHeight > 35) {
            throw new Exception("Invalid seat height");
        }

        seatHeight = startHeight;
    }

    @Override
    public String toString() {
        return (super.toString() + "\nseat height is " + seatHeight);
    }
}

public class Test {
    private static final int GEAR = 3;
    private static final int SPEED = 100;
    private static final int START_HEIGHT = 25;

    public static void main(String[] args) {
        try {
            MountainBike mb = new MountainBike(Test.GEAR, Test.SPEED, Test.START_HEIGHT);
            Test.printMessage(mb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printMessage(MountainBike mb) {
        System.out.println(mb.toString());
    }
} 