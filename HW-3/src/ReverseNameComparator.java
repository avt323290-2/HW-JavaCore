/**
 * Уникальный компаратор для сравнения сотрудников по имени в обратном порядке.
 */
import java.util.Comparator;

public class ReverseNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e2.getName().compareTo(e1.getName());
    }
}