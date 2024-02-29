/**
 * VehicleInfo 类用于存储单个车辆的详细信息。
 * 它包含了车辆的类型、品牌、型号、车牌号和日租金等信息。
 */
public class VehicleInfo {
    
    private final String type;            //车辆类型
    private final String brand;           //车辆品牌
    private final String model;           //轿车型号、客车座位数、卡车吨位
    private final String plateNumber;     //车牌号
    private final double dailyRent;       //日租金

    public VehicleInfo(String type, String brand, String model, String plateNumber, double dailyRent) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.plateNumber = plateNumber;
        this.dailyRent = dailyRent;
    }

    public String getType() {return type;}
    public String getBrand() {return brand;}
    public String getModel() {return model;}
    public String getPlateNumber() {return plateNumber;}
    public double getDailyRent() {return dailyRent;}
}
