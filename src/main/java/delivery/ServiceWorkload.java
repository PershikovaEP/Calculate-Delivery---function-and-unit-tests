package delivery;

public class ServiceWorkload {

    //загруженность
    private double multiplier_workload;

    //конструктор, если не указана загруженность
    public ServiceWorkload() {
        multiplier_workload = 1;
    }

    //конструктор, если указана загруженность
    public ServiceWorkload(String workload) {

        if (workload == null) {
            multiplier_workload = 1;
            return;
        }

        if (workload.equals("very_high")) {
            multiplier_workload = 1.6;
        } else if (workload.equals("high")) {
            multiplier_workload = 1.4;
        } else if (workload.equals("elevated")) {
            multiplier_workload = 1.2;
        } else {
            multiplier_workload = 1;
        }
    }

    public double getMultiplier_workload() {
        return multiplier_workload;
    }
}
