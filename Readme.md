
# Minecraft project

The minecraft project is a project linking the modbus to the digital world of minecraft and to the internet via a database and a web interface.

The minecraft world emulates an electrical network with producers and consumers. And we come to add an element allowing the intelligent management of this last.
The goal is to use a minimum of resources to last two days.

To do this, a program coded in java and running on our PC makes the link between minecraft and databases / web page. 


# Presentation of the features

The program is globally divided into several functionalities:

Core component: Digital twin of the minecraft world, it contains all the values present on the game.

Modbus component: Allows to establish the communication between the core component and the minecraft world.

Database component: Allows to store the values on a time-series database of influxDB.

Web component: Allows to link to the monitoring/configuration web page. => relative path ~/project-minecraft/WebClient/index.html

Field component : Field connector is used to make the update of value from modbus to the program and from the program to minecraft


# Use 
1. Run minecraft client => command : .\gradlew runClient
2. Connect to web page => relative path : ~/project-minecraft/WebClient/index.html
3. Run Minecraft.jar






