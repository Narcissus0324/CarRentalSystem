import java.util.*;

public abstract class RentMgrSys extends VehicleFactory {
    // 存储所有车辆信息的列表
    public static final List<VehicleInfo> vehicles = new ArrayList<>();

    public RentMgrSys(String plateNumber, double dailyRent, String brand) {
        super(plateNumber, dailyRent, brand);
    }

    /**
     * 初始化车辆信息的静态方法。
     * 在系统启动时调用，用于加载车辆的基础信息。
     */
    private static void initializeVehicles() {
        vehicles.add(new VehicleInfo("car", "宝马", "X6", "京NY28588", 800));
        vehicles.add(new VehicleInfo("car", "宝马", "550i", "京CNY3284", 600));
        vehicles.add(new VehicleInfo("car", "别克", "林荫大道", "京NT37465", 300));
        vehicles.add(new VehicleInfo("car", "别克", "GL8", "京NT96968", 600));

        vehicles.add(new VehicleInfo("bus", "金杯", "16", "京6566754", 800));
        vehicles.add(new VehicleInfo("bus", "金杯", "16", "京8696997", 800));
        vehicles.add(new VehicleInfo("bus", "金龙", "34", "京9696996", 1500));
        vehicles.add(new VehicleInfo("bus", "金龙", "34", "京8696998", 1500));

        vehicles.add(new VehicleInfo("truck", "解放", "", "京GD56577", 800));
        vehicles.add(new VehicleInfo("truck", "东风", "", "京GD53456", 700));
    }

    public static void main(String[] args) throws VehicleNotFoundException {
        initializeVehicles();

        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用汽车租赁系统");

        System.out.println("1.轿车 2.客车 3.卡车");
        int vehicleType = scanner.nextInt();

        MotoVehicle vehicle = null;
        switch (vehicleType) {
            case 1:
                vehicle = leaseOutFlow(scanner, "car");
                break;
            case 2:
                vehicle = leaseOutFlow(scanner, "bus");
                break;
            case 3:
                vehicle = leaseOutFlow(scanner, "truck");
                break;
            default:
                System.out.println("无效的车辆类型！");
                break;
        }

        if (vehicle != null) {
            System.out.println("请输入您要租赁的天数：");
            int days = scanner.nextInt();
            double rent = vehicle.calculateRent(days);
            String plateNumber = vehicle.getPlateNumber();
            System.out.println("分配给您的汽车牌号是" + plateNumber);
            System.out.println("您需要支付的的租赁费用是：" + rent + "元");
        }

        scanner.close();
    }

    // 租车流程方法
    private static MotoVehicle leaseOutFlow(Scanner scanner, String vehicleType) throws VehicleNotFoundException {
        List<VehicleInfo> vehiclesOfType = filterVehiclesByType(vehicleType);

        System.out.println("请选择你要租赁的" + vehicleType + "品牌：");
        List<String> brands = new ArrayList<>();
        for (VehicleInfo vehicle : vehiclesOfType) {
            if (!brands.contains(vehicle.getBrand())) {
                brands.add(vehicle.getBrand());
            }
        }

        for (int i = 0; i < brands.size(); i++) {
            System.out.println((i + 1) + ". " + brands.get(i));
        }

        int brandChoice = scanner.nextInt();
        String selectedBrand = brands.get(brandChoice - 1);

        switch (vehicleType) {
            case "car":
                System.out.println("请选择你要租赁的" + selectedBrand + "的型号：");
                List<String> models = new ArrayList<>();
                for (VehicleInfo vehicle : vehiclesOfType) {
                    if (vehicle.getBrand().equals(selectedBrand) && !models.contains(vehicle.getModel())) {
                        models.add(vehicle.getModel());
                    }
                }

                for (int i = 0; i < models.size(); i++) {
                    System.out.println((i + 1) + ". " + models.get(i));
                }

                int modelChoice = scanner.nextInt();
                String selectedModel = models.get(modelChoice - 1);

                return VehicleFactory.createVehicle(vehicleType, selectedBrand, selectedModel);
            case "bus":
                System.out.println("请输入" + selectedBrand + "的座位数：");
                String seatCount = scanner.next();
                return VehicleFactory.createVehicle(vehicleType, selectedBrand, seatCount);
            case "truck":
                System.out.println("请输入" + selectedBrand + "的吨数：");
                String tonnage = scanner.next();
                return VehicleFactory.createVehicle(vehicleType, selectedBrand, tonnage);
        }

        return null;
    }

    // 根据车辆类型过滤车辆列表
    private static List<VehicleInfo> filterVehiclesByType(String type) {
        List<VehicleInfo> filteredList = new ArrayList<>();
        for (VehicleInfo vehicle : vehicles) {
            if (vehicle.getType().equals(type)) {
                filteredList.add(vehicle);
            }
        }
        return filteredList;
    }
}