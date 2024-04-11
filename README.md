# Description
This  program read a CSV file containing

data employee according to the following pattern:

"Id,firstName,lastName,salary,managerId"

and provides in output this information:

- which managers earn less than they should, and by how much
- which managers earn more than they should, and by how much
- which employees have a reporting line which is too long, and by how much

## Running application

To run the application via maven, digit on the application root folder the following command
<pre>
mvn compile exec:java
</pre>
To run all the tests
<pre>
mvn test
</pre>


## Assumptions

- Employee record data in the CSV file may not be listed in the correct order 
- Only one CEO is present
- Data salary types are integer
- There are no data cycle in the input data(a cycle or an employee-> itself manager)
- In the case of managers that earn less than 20% of the average salary, the "diff below" might be negative 


## Check control
- Data inserted in the CSV file has to be  consistent(to each managerId in the CSV file has to correspond an Employee id)

- Loops in the tree are not accepted




## File configuration
The configuration file allow to set the following parameters:
- number of headers to skip from the csv file
- tree level of reporting line to consider
- min percentage salary
- max percentage salary


## Technical Architecture

In the Application  we declare the interfaces of the main components to run the business logic:(pattern: programming always by abstract/interface)

-	reader
-	treebuiler
-	employeeAnalyser
-	printer

The application class contains the main method
where the instances of all required dependencies are created,
wiring is performed and finally created an instance of the application.

Inside the run method are contained all the business logic.


N.B:the run method refers to the interfaces(program by abstract/interface), during the application instantiation we inject the concrete implementations.


The application contains the static method that instantiate the Application object
and inject in it the concrete interface's implementation.

First we created dependencies, next we performed wiring and then instantiated the Application.

Let's analyse each component:

- ApplicationProperties is a common component used to retrieve values to configure each specific component

- CsvReader=>injects CSV reader that needs: properties(to skip headers) and converter(to create Employee dto)

- TreeBuilder: because the records in the CSV file might be random ordered, I need

     - collect in a map all the records with key ID(so that we can recover the manager in any order was presented in the CSV file)

     - Cycle over the Map over all the employee
       -	adding to each employee his manager
       -	adding to the subordinates of the manager the emplyee himself

- EmployeeAnaliser: injections of: AboveAverageSalaryPredicate,BelowAverageSalaryPredicate,NestedLevelPredicate,AverageSalaryCalculator,NestedLevelCalculator in order to execute specific algorithms.
     Pattern:SINGLE RESPONSIBILITY, a single component performs a single check operation this is why we have three of them.
     each predicate use a specific calculator and properties configuration
    -	AboveAverageSalaryPredicate inject:AverageSalaryCalculator(calculate the average of all his subordinate salaries)and properties(to get the percentage)
         It IMPLEMENTS Predicate<Employee> and override the test method and is used in the class EmployeeAnaliser for the filter stream for the managers with salary above 1.5 the average
    -	exacly the same for BelowAverageSalaryPredicate and NestedLevelPredicate used as filter predicates in the EmployeeAnaliser class

Note: treebuilder+employee analiser => SEGREGATION INTERFACE(separation of duties even if they insist on the same tree)

-	ConsolePrinter:nothing to inject or making elaboration, it prints just the "pre-build" object as is.