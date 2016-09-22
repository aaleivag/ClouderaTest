# Capacitacion
El fin de este Proyecto es mostrar un ejemplo de como se debe estructurar un proyecto Maven para realizar pruebas unitarias sobre scripts Hive.

# HIVERUNNER
Librerias Java basadas en Junit que permiten realizar pruebas unitarias sobre scrips Hive aprovechando la flexibilidad que Junit nos ofrece. Una ventaja importante es que se ejecuta completamente en momoria levantando un cluster en el momento de compilacion. Con lo anterior evitamos tener un hadoop corriendo en la maquina de pruebas.

## Como ejecutar
Para realizar la ejecucion de este proyecto de ejemplo lanzar el siguiente comando

	mvn clean assembly:assembly


