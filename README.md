# Java Fixture - by The Bored Dev (https://theboreddev.com)

 ## Introduction
 
Java Fixture is a library whose main purpose is to populate test data for our tests in an easy manner.

Java Fixture uses default random generators to populate data based on the type of each element but the default behaviour can be overriden using user-specified suppliers.

## Setup

To be able to use Java Fixture, you'll have to download its dependency in your project using your package manager.

(Pending - Package will be available soon)

## Usage

### Initialise a collection of a Java simple type

```java
List<Integer> integers = Fixture.of(10, Integer.class).apply();
```

Java Fixture supports most of the Java types: String, Integer, Double, Float, Long, BigDecimal and BigInteger.

It also supports any kind of enum, so it will assign a random value of your enum to your object or collection.

### Initialise an object

Java Fixture uses reflection to dynamically initialise objects, it will find a suitable constructor and initialise the object.

If the existing constructor in your object doesn't set all the fields you need, you will have to set a custom field supplier.

You don't have to worry about how complex your object is, the library is able to populate as many levels of nested objects as you may need. 

1. Using default populator
    ```java
    Employee employee = Fixture.of(Employee.class).apply();
    ```
2. Using custom object supplier
    ```java
    Employee employee = Fixture.of(Employee.class)
                    .withSupplier(() -> new Employee(
                            Random.randomAlphaNumeric(10),
                            Random.randomAlphaNumeric(10),
                            Random.randomInt(10),
                            Random.randomDouble(10000, 100000)
                    ))
                    .apply();
    ```
3. Using custom field suppliers
    ```java
    Employee employee = Fixture.of(Employee.class)
                    .withFieldSupplier("age", () -> Random.randomInt(18, 100))
                    .apply();
    ```
   
### Initialise a collection of objects

1. Using default populator
    ```java
   List<Employee> employees = Fixture.of(10, Employee.class).apply(); 
   ```
2. Using custom object supplier
    ```java
    List<Employee> employees = Fixture.of(10, Employee.class).withSupplier(() -> new Employee(
                    Random.randomAlphaNumeric(10),
                    Random.randomAlphaNumeric(20),
                    Random.randomInt(100),
                    Random.randomDouble(100000)
            )).apply();
   ```
3. Using custom field populators
    ```java
    List<Employee> employees = Fixture.of(10, Employee.class)
                    .withFieldSupplier("age", () -> Random.randomInt(18, 150))
                    .withFieldSupplier("salary", () -> Random.randomInt(8000, 100000))
                    .apply();
   ```
   
   
