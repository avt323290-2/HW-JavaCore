public class Freelancer extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public Freelancer(String name, double hourlyRate, double hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateMonthlySalary() {
        return 20.8 * 8 * hourlyRate;
    }
}