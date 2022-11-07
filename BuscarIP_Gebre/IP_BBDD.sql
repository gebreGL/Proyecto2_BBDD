DROP DATABASE IF EXISTS BD_ips;
CREATE DATABASE IF NOT EXISTS BD_ips;

USE BD_ips;

DROP TABLE IF EXISTS DATOS;
CREATE TABLE IF NOT EXISTS DATOS (
	
    IP VARCHAR(20) NOT NULL,
    POSTAL VARCHAR(10) NOT NULL,
    CIUDAD VARCHAR(50) NOT NULL,
    PAIS VARCHAR(20) NOT NULL,
    LATITUD DOUBLE NOT NULL,
    LONGITUD DOUBLE NOT NULL,
    REGION VARCHAR(20) NOT NULL,
    CAPITAL VARCHAR(20) NOT NULL,
    PREFIJO VARCHAR(5) NOT NULL,
    
    PRIMARY KEY(IP)
	
) ENGINE INNODB;

INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("195.57.104.118","23009","Jaén","Spain",37.7795941,-3.7849057,"Andalusia","Madrid","34"); 
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("195.57.5.1","41001","Seville","Spain",37.3890924,-5.9844589,"Andalusia","Madrid","34"); 
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("195.57.104.99","28080","Madrid","Spain",40.4167754,-3.7037902,"Madrid","Madrid","34");
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("205.66.104.14","20500","Washington","United States",38.9071923,-77.0368707,"DC","Washington D.C.","1");
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("1.178.47.255","01005-000","São Paulo","Brazil",-23.5505199,-46.6333094,"State of São Paulo","Brasília","55");
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("102.132.31.255","1561","Yaounde","Cameroon",3.8480325,11.5020752,"Centre","Yaoundé","237");
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("103.203.183.255","8572","Bärnbach","Austria",47.0770947,15.1242042,"Styria","Vienna","43");
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("1.0.7.255","3805","Narre Warren","Australia",-38.018,145.3,"Victoria","Canberra","61");
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("103.187.242.255","724 00","Neapoli","Greece",35.2563455,25.6048531,"Region of Crete","Athens","30");
INSERT INTO DATOS (IP,POSTAL,CIUDAD,PAIS,LATITUD,LONGITUD,REGION,CAPITAL,PREFIJO)
 VALUES ("128.0.70.255","3912","Maniitsoq","Greenland",65.4076761,-52.9005718,"Kitaa","Nuuk","299");



SELECT * FROM DATOS;