// 汽车基类及其子类定义

/**
 * MotoVehicle 类是一个抽象基类，定义了汽车的基本属性和功能。
 */
public abstract class MotoVehicle {
	
    private final String plateNumber;   // 车牌号
    private final double dailyRent;     // 每日租金
    private final String brand;         // 轿车型号、客车座位数、卡车吨位

    public MotoVehicle(String plateNumber, double dailyRent, String brand) {
        this.plateNumber = plateNumber;
        this.dailyRent = dailyRent;
        this.brand = brand;
    }

    // 抽象方法：计算租金
    public abstract double calculateRent(int days);

    // Getter 方法
    public String getPlateNumber() { return plateNumber; }
    public double getDailyRent() { return dailyRent; }
    public String getBrand() { return brand; }
}

/**
 * Car 类继承自 MotoVehicle 类，代表轿车。
 */
class Car extends MotoVehicle {
	
    public Car(String plateNumber, double dailyRent, String brand, String model) {
        super(plateNumber, dailyRent, brand);
    }

    //计算租金
    @Override
    public double calculateRent(int days) {
        double rent = days * getDailyRent();

        if (days > 150) return rent * 0.7;
        else if (days > 30) return rent * 0.8;
        else if (days > 7) return rent * 0.9;
        return rent;
    }
}

/**
 * Bus 类继承自 MotoVehicle 类，代表客车。
 */
class Bus extends MotoVehicle {
	
    private static int seatCount;  //客车座位数

    public Bus(String plateNumber, double dailyRent, String brand, String seatCountStr) {
        super(plateNumber, dailyRent, brand);
        seatCount = convertSeatCount(seatCountStr);
    }

    //计算租金
    @Override
    public double calculateRent(int days) {
        double rent = days * getDailyRent();

        if (days >= 150) return rent * 0.6;
        else if (days >= 30) return rent * 0.7;
        else if (days >= 7) return rent * 0.8;
        else if (days >= 3) return rent * 0.9;
        return rent;
    }

	//座位数有效性检查
    static int convertSeatCount(String seatCountStr) {
        try {
            int seatCount = Integer.parseInt(seatCountStr);
            if (seatCount <= 0) {
                throw new IllegalArgumentException("座位数必须大于0");
            } else if (seatCount <= 16) {
                return 16;
            } else {
                return 34;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("座位数必须为有效的整数");
        }
    }
}

/**
 * Truck 类继承自 MotoVehicle 类，代表卡车。
 */
class Truck extends MotoVehicle {
	
    private final double tonnage;  // 载重吨位

    public Truck(String plateNumber, double dailyRent, String brand, double tonnage) {
        super(plateNumber, dailyRent, brand);
        this.tonnage = tonnage;
    }

    //计算租金
    @Override
    public double calculateRent(int days) {
        double rentPerTon = getDailyRent();
        return tonnage * rentPerTon * days;
    }
}