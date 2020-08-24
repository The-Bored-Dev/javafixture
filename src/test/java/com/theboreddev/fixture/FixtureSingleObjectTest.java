package com.theboreddev.fixture;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FixtureSingleObjectTest {

    @Test
    public void shouldCreateASingleObjectUsingDefaultSupplier() {

        Employee employee = Fixture.of(Employee.class).apply();

        assertThat(employee).matches(this::isValidEmployee);
    }

    @Test
    public void shouldCreateASingleObjectUsingCustomSupplier() {

        Employee employee = Fixture.of(Employee.class)
                .withSupplier(() -> new Employee(
                        Random.randomAlphaNumeric(10),
                        Random.randomAlphaNumeric(10),
                        Random.randomInt(10),
                        Random.randomDouble(10000, 100000)
                ))
                .apply();

        assertThat(employee).matches(this::isValidEmployee);
    }

    @Test
    public void shouldCreateASingleObjectUsingDefaultSupplierWithFieldSupplier() {

        Employee employee = Fixture.of(Employee.class)
                .withFieldSupplier("age", () -> Random.randomInt(18, 100))
                .apply();

        assertThat(employee).matches(this::isValidEmployee);
        assertThat(employee.getAge()).isBetween(18, 100);
    }


    private boolean isValidEmployee(Employee employee) {
        return employee != null &&
                employee.getName().length() > 0 &&
                employee.getSurname().length() > 0 &&
                employee.getAge() > 0 && employee.getAge() <= 100 &&
                employee.getSalary() >= 0;
    }

    static class Employee {
        private final String name;
        private final String surname;
        private final int age;
        private final double salary;


        public Employee(String name, String surname, int age, double salary) {
            this.name = name;
            this.surname = surname;
            this.age = age;
            this.salary = salary;
        }


        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }
}
