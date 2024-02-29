/**
 * VehicleFactory 类提供了创建不同类型的车辆对象的功能。
 * 它继承自 MotoVehicle 类，并添加了根据车辆类型创建具体车辆对象的工厂方法。
 */
public abstract class VehicleFactory extends MotoVehicle {

    public VehicleFactory(String plateNumber, double dailyRent, String brand) {
        super(plateNumber, dailyRent, brand);
    }

    //根据车辆类型、品牌和型号或座位数/吨位创建车辆对象的静态工厂方法
    public static MotoVehicle createVehicle(String type, String brand, String modelOrSeatsOrTonnage) throws VehicleNotFoundException {
        switch (type) {
            case "car":
                return createCar(brand, modelOrSeatsOrTonnage);
            case "bus":
                return createBus(brand, modelOrSeatsOrTonnage);
            case "truck":
                return createTruck(brand, modelOrSeatsOrTonnage);
            default:
                throw new VehicleNotFoundException("Invalid vehicle type: " + type);
        }
    }

    private static Car createCar(String brand, String model) throws VehicleNotFoundException {
        String plateNumber = getPlateNumberByTypeAndBrand("car", brand, model);
        double dailyRent = getDailyRentByType("car", brand, model);
        return new Car(plateNumber, dailyRent, brand, model);
    }

    private static Bus createBus(String brand, String seatCountStr) throws VehicleNotFoundException {
        String plateNumber = getPlateNumberByTypeAndBrand("bus", brand, seatCountStr);
        double dailyRent = getDailyRentByType("bus", brand, seatCountStr);
        return new Bus(plateNumber, dailyRent, brand, seatCountStr);
    }

    private static Truck createTruck(String brand, String tonnage) throws VehicleNotFoundException {
        String plateNumber = getPlateNumberByTypeAndBrand("truck", brand, "");
        double dailyRent = getDailyRentByType("truck", brand, tonnage);
        return new Truck(plateNumber, dailyRent, brand, Double.parseDouble(tonnage));
    }

    //根据车辆类型、品牌和型号或座位数/吨位获取日租金。
    private static double getDailyRentByType(String type, String brand, String modelOrSeatsOrTonnage) throws VehicleNotFoundException {
        int seatCount = type.equalsIgnoreCase("bus") ? Bus.convertSeatCount(modelOrSeatsOrTonnage) : -1;
        if (type.equalsIgnoreCase("truck")) {
            modelOrSeatsOrTonnage = "";
        }

        for (VehicleInfo vehicle : RentMgrSys.vehicles) {
            if (vehicle.getType().equalsIgnoreCase(type)
                    && vehicle.getBrand().equalsIgnoreCase(brand)
                    && (type.equalsIgnoreCase("bus") ? vehicle.getModel().equals(String.valueOf(seatCount)) : vehicle.getModel().equalsIgnoreCase(modelOrSeatsOrTonnage))) {
                return vehicle.getDailyRent();
            }
        }
        throw new VehicleNotFoundException("No vehicle found with type: " + type + ", brand: " + brand + ", and model: " + modelOrSeatsOrTonnage);
    }

    //根据车辆类型和品牌获取车牌号。
    private static String getPlateNumberByTypeAndBrand(String type, String brand, String modelOrSeatsOrTonnage) throws VehicleNotFoundException {
        int seatCount = type.equalsIgnoreCase("bus") ? Bus.convertSeatCount(modelOrSeatsOrTonnage) : -1;
        if (type.equalsIgnoreCase("truck")) {
            modelOrSeatsOrTonnage = "";
        }

        for (VehicleInfo vehicleInfo : RentMgrSys.vehicles) {
            if (vehicleInfo.getType().equalsIgnoreCase(type)
                    && vehicleInfo.getBrand().equalsIgnoreCase(brand)
                    && (type.equalsIgnoreCase("bus") ? vehicleInfo.getModel().equals(String.valueOf(seatCount)) : vehicleInfo.getModel().equalsIgnoreCase(modelOrSeatsOrTonnage))) {
                return vehicleInfo.getPlateNumber();
            }
        }
        throw new VehicleNotFoundException("No vehicle found with type: " + type + ", brand: " + brand + ", and model: " + modelOrSeatsOrTonnage);
    }

    //异常类
    static class VehicleNotFoundException extends Exception {
        public VehicleNotFoundException(String message) {
            super(message);
        }
    }
}



