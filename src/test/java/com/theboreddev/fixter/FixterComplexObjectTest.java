package com.theboreddev.fixter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixterComplexObjectTest {

    @Test
    public void shouldPopulateACollectionOfObjects() {

        List<Employee> employees = new Fixter<Employee>(10, Employee.class).apply();

        assertThat(employees).hasSize(10).allMatch(this::isValidEmployee);
    }

    @Test
    public void shouldPopulateACollectionOfObjectsRestrictingTheValueOfOneField() {

        List<Employee> employees = new Fixter<Employee>(10, Employee.class)
                .withFieldSupplier("age", () -> Random.randomInt(18, 150))
                .apply();

        assertThat(employees).hasSize(10).allMatch(employee -> employee.getAge() >= 18 && employee.getAge() <= 150);
    }

    @Test
    public void shouldPopulateACollectionOfObjectsRestrictingTheValueOfMultipleFields() {

        List<Employee> employees = new Fixter<Employee>(10, Employee.class)
                .withFieldSupplier("age", () -> Random.randomInt(18, 150))
                .withFieldSupplier("salary", () -> Random.randomInt(8000, 100000))
                .apply();

        assertThat(employees).hasSize(10)
                .allMatch(employee -> employee.getAge() >= 18 && employee.getAge() <= 150)
                .allMatch(employee -> employee.getSalary() >= 8000 && employee.getSalary() <= 100000);
    }

    @Test
    public void shouldRaiseExceptionIfFieldSupplierIsSpecifiedForASimpleType() {

        new Fixter<String>(10, String.class).withFieldSupplier("field", () -> Random.randomAlphaNumeric(10));
    }

    @Test
    public void shouldPopulateACollectionOfObjectsWithACustomSupplier() {

        List<Employee> employees = new Fixter<Employee>(10, Employee.class).withSupplier(() -> new Employee(
                Random.randomAlphaNumeric(10),
                Random.randomAlphaNumeric(20),
                Random.randomInt(100),
                Random.randomDouble(100000),
                new Address(Random.randomAlphaNumeric(30), Random.randomInt(100), Random.randomAlphaNumeric(6))
        )).apply();

        assertThat(employees).hasSize(10).allMatch(this::isValidEmployeeWithSomeRestrictions);
    }


    private boolean isValidEmployee(Employee employee) {
        return employee != null &&
                employee.getName().length() > 0 &&
                employee.getSurname().length() > 0 &&
                employee.getAge() > 0 && employee.getAge() <= 100 &&
                employee.getSalary() >= 0;
    }

    private boolean isValidEmployeeWithSomeRestrictions(Employee employee) {
        return employee != null &&
                employee.getName().length() == 10 &&
                employee.getSurname().length() == 20 &&
                employee.getAge() > 0 && employee.getAge() <= 100 &&
                employee.getSalary() >= 0 && employee.getSalary() <= 100000.0;
    }

    static class Employee {
        private final String name;
        private final String surname;
        private final int age;
        private final double salary;
        private final Address address;


        public Employee(String name, String surname, int age, double salary, Address address) {
            this.name = name;
            this.surname = surname;
            this.age = age;
            this.salary = salary;
            this.address = address;
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

        public Address getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    ", address=" + address +
                    '}';
        }
    }

    static class Address {
        private final String addressLine;
        private final int houseNumber;
        private final String postCode;

        public Address(String addressLine, int houseNumber, String postCode) {
            this.addressLine = addressLine;
            this.houseNumber = houseNumber;
            this.postCode = postCode;
        }

        public String getAddressLine() {
            return addressLine;
        }

        public int getHouseNumber() {
            return houseNumber;
        }

        public String getPostCode() {
            return postCode;
        }
    }
}
