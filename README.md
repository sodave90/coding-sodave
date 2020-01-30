# coding-sodave

This software is designed to receive and collect data about energy consumption from different villages. 
The system also supports on-demand report generation to analyse energy consumption per village for the last 24h.
REST Api's has been exposed to justify the above mentioned purposes.

# Technology Used

Language: Java (version 8)

Framwork: Spring MVC

Tool: Maven

To install maven dependencies, type ``` mvn clean install ```

To start the web application, type command ```  mvn jetty:run ```

To run the test case, please run the command ```   mvn test ```

# REST Api Endpoints

``` POST /village?village_name=TestVillage ```
 
 This Api is used to add village name to the data storage.
 
``` POST /counter_callback```
 
    {

        "counter_id": "1","amount": 10000.123
    }

This API captures consumptions details against a counter

``` GET /counter?id=1 ```

OUTPUT: 
        {

          "id": "1","village_name": "Villarriba"</code>
        }
        
This API gives additional information of the counter.

``` POST /consumption_report?duration=24h ```

The API generates a report which has output:

      {
        "villages": [{        "village_name": "Villarriba",        "consumption": 12345.123    },    {        "village_name": "Villabajo",        "consumption": 23456.123    }]
      }


