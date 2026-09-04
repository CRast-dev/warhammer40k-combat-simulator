# Changelog

## [1.0.0] - 2026-09-05

### Added
- Spring Boot web application
- REST API for units and simulations
- Web interface for configuring and running simulations
- PostgreSQL-backed unit, model and weapon data
- Unit creation through the REST API
- Unit deletion through the REST API
- Validation for unit creation requests
- Simulation statistics and average results
- Support for repeated simulations
- Safe deletion of shared models and weapons
- HTTP error handling for missing units

### Improved
- Refactored repositories to use Spring dependency injection
- Added service layer for application logic
- Added transactional unit creation and deletion
- Refactored simulation execution for repeated independent runs
- Improved separation between domain objects, API DTOs and application services

## [0.8] - old development milestone
+ Melee Combat and Charge phase implemented
+ new class for all the starting options

## [0.7] - old development milestone
+ made Model have a boolean isCharacter field
+ implemented allocationStrategies to choose
+ implemented the 11th edition allocation group rules for units with character and non-character models

## [0.6] - old development milestone
+ added destroyedModels into the battleprinter and result
+ some initial Junit testing for AllocationGroup and CombatSimulator
+ added Damage class to represent dice dependant damage (2D6 + 3 for example)

## [0.5] - old development milestone
+ replaced the phase strings with enums
+ Made BattleResult no longer a child of PhaseResult
+ incorporated allocationgroup logic according to 11th edition core rules
+ expanded AttackResult

## [0.4] - old development milestone
+ Added PrintLevel functionality for deciding how detailed the output should be
+ Gave ShootingPhaseResult access to the attacking and defending unit so that Printer can print who attacked who
+ Made it so the hardcoded example has the space marines actually shoot back.
+ Separated woundroll into before and after saves to display unsaved Wounds prior to the damage step.

## [0.3] - old development milestone
+ Refactored Result Classes again with an abstract class
+ BattleResult now has a list of phases
+ com.warhammer.Main class that has hardcoded attacker and defender
+ Printer class for console output

## [0.2] - old development milestone
+ Javadoc for classes and methods
+ Changed access modifiers for several methods in CombatRules
+ Refactored classes into packages
+ Seperated AttackResult class into Result classes for the battle, shooting phase and attack.
+ implemented BattlePrinter class to give output to console


