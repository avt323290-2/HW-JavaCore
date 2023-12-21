public abstract class Employee implements Comparable<Employee> {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateMonthlySalary();

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.calculateMonthlySalary(), other.calculateMonthlySalary());
    }
}