import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeCollection implements Iterable<Employee> {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void sortEmployees() {
        Collections.sort(employees);
    }

    public void reverseSortEmployees() {
        Collections.sort(employees, new ReverseNameComparator());
    }

    @Override
    public java.util.Iterator<Employee> iterator() {
        return employees.iterator();
    }
}