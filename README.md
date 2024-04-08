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
Data inserted in the CSV file has to be  consistent(to each managerId in the CSV file has to correspond an Employee id)

Loops in the tree are not accepted




## File configuration
The configuration file allow to set the following parameters:
- number of headers to skip from the csv file
- tree level of reporting line to consider
- min percentage salary
- max percentage salary
