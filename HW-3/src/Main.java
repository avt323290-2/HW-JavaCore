public class Main {
    public static void main(String[] args) {
        // Создаем объекты сотрудников и добавляем их в коллекцию
        Freelancer freelancer1 = new Freelancer("Иванов Сергей ", 15.0, 45000);
        Freelancer freelancer2 = new Freelancer("Петрова Екатерина", 20.0, 12000);
        Worker worker1 = new Worker("Мезенцев Станислав", 6000.0);
        Worker worker2 = new Worker("Дулин Иван", 10000.0);

        EmployeeCollection employeeCollection = new EmployeeCollection();
        employeeCollection.addEmployee(freelancer1);
        employeeCollection.addEmployee(freelancer2);
        employeeCollection.addEmployee(worker1);
        employeeCollection.addEmployee(worker2);

        // Вывод данных с использованием foreach
        System.out.println("Сотрудники:");
        for (Employee employee : employeeCollection) {
            System.out.println("Имя: " + employee.getName() + ", Ежемесячная зарплата: " + employee.calculateMonthlySalary());
        }

        // Сортировка и вывод данных
        System.out.println("\nОтсортированные сотрудники:");
        employeeCollection.sortEmployees();
        for (Employee employee : employeeCollection) {
            System.out.println("Имя: " + employee.getName() + ", Ежемесячная зарплата: " + employee.calculateMonthlySalary());
        }

        // Обратная сортировка и вывод данных
        System.out.println("\nСотрудники, отсортированные в обратном порядке:");
        employeeCollection.reverseSortEmployees();
        for (Employee employee : employeeCollection) {
            System.out.println("Имя: " + employee.getName() + ", Ежемесячная зарплата: " + employee.calculateMonthlySalary());
        }
    }
}