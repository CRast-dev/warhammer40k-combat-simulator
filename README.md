# Warhammer 40k Combat Simulator

A Java-based combat simulator for evaluating the statistical performance of
Warhammer 40k units in a single round of combat.

Currently the simulator is based on the rules for the 11th Edition of Warhammer 40k

The project started as a console-based Java application and has evolved into
a Spring Boot web application with a PostgreSQL-backed data model and REST API.

## Current Version

**1.0.0**

This is the first official release of the web-based version of the project.

---

## Features

### Combat Simulation

- Shooting phase simulation
- Charge phase simulation
- Melee combat simulation
- Hit, wound and saving throw resolution
- Damage calculation
- Dice-based attacks and damage
- Model damage and destruction
- Unit/model/weapon based combat resolution
- Configurable allocation strategies
- Character and non-character allocation groups
- Multiple simulation runs
- Statistical aggregation of simulation results

### Data Management

- PostgreSQL database
- Units, models and weapons stored as database entities
- Models and weapons can be shared between units
- REST API for retrieving units
- REST API for creating units
- REST API for deleting units
- Validation of incoming unit data
- Transactional database operations

### Web Interface

- Select attacker and defender
- Configure simulation parameters
- Configure starting distance
- Configure charge attempts
- Run repeated simulations
- Display aggregated combat statistics

---

## Used Technology

- **Java 21**
- **Spring Boot**
- **Maven**
- **PostgreSQL**
- **JDBC**
- **JUnit 5**
- **HTML / CSS / JavaScript**
- **Postman** (for API testing)

---

## Running the Application

Requirements
- **Java 21**
- **Maven**
- **PostgreSQL**

The application requires a PostgreSQL database containing the required
Warhammer unit, model and weapon tables.

### Start the application

Using Maven:
```
mvn spring-boot:run
```
The application will then be available at:

http://localhost:8080

## REST API

### Get all units

```
GET /units
```
### Get a units based on its id

```
GET /units/{id}
```

### Run a full simulation
```
POST /simulations
```

Example request:
```
{
"attackerID": 1,
"defenderID": 2,
"runs": 100,
"attackerStrategy": "WORST_SAVE_FIRST",
"defenderStrategy": "WORST_SAVE_FIRST",
"distance": 12,
"attackerCharge": true,
"defenderCharge": false
}
```
### Create a unit in the database
```
POST /units
```
Example unit data:
```
{
    "name": "",
    "models": [
        {
            "name": "Test Model",
            "toughness": 4,
            "save": 4,
            "invulnSave": 0,
            "maxWounds": 2,
            "movement": 6,
            "quantity": 1,
            "weapons": [
                {
                    "name": "Test Rifle",
                    "flatAttacks": 2,
                    "attackDiceSide": 0,
                    "attackDiceCount": 0,
                    "skill": 3,
                    "strength": 4,
                    "ap": 0,
                    "flatDamage": 1,
                    "damageDiceSide": 0,
                    "damageDiceCount": 0,
                    "range": 24,
                    "weaponType": "RANGED",
                    "quantity": 1
                }
            ],
            "isCharacter": false
        }
    ]
}
```

### Delete a unit from the database by its id
```
DELETE /units/{id}
```
Deletes the unit and removes associated models and weapons when they are no
longer shared by another unit. Will result in a 404 response if attempting to delete a nonexisting unit id.


## Development History

The project originally started as a console-based Java combat simulator.

Early development focused on the combat engine, result classes, allocation group
rules and unit/model/weapon representation in an object oriented approach.

The project later transitioned to:

PostgreSQL persistence
Spring dependency injection
REST endpoints
DTO-based API requests
Transactional services
A browser-based interface

Version 1.0.0 marks the first official release of this web-based architecture.



## Disclaimer

Warhammer 40,000 is a tabletop game owned by Games Workshop.
This project is an unofficial fan-made tool and is not affiliated with
or endorsed by Games Workshop.

The project does not include Games Workshop's copyrighted rules or
proprietary datasheet content.

The rules used to build the simulation engine are freely available as the "Core Rules" in the Downloads section of https://www.warhammer-community.com/