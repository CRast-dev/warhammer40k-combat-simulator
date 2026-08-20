Version 0.8
+ Melee Combat and Charge phase implemented
+ new class for all the starting options

Version 0.7:
+ made Model have a boolean isCharacter field
+ implemented allocationStrategies to choose
+ implemented the 11th edition allocation group rules for units with character and non-character models

Version 0.6:
+ added destroyedModels into the battleprinter and result
+ some initial Junit testing for AllocationGroup and CombatSimulator
+ added Damage class to represent dice dependant damage (2D6 + 3 for example)

Version 0.5:
+ replaced the phase strings with enums
+ Made BattleResult no longer a child of PhaseResult
+ incorporated allocationgroup logic according to 11th edition core rules
+ expanded AttackResult

Version 0.4:
+ Added PrintLevel functionality for deciding how detailed the output should be
+ Gave ShootingPhaseResult access to the attacking and defending unit so that Printer can print who attacked who
+ Made it so the hardcoded example has the space marines actually shoot back.
+ Separated woundroll into before and after saves to display unsaved Wounds prior to the damage step.

Version 0.3:
+ Refactored Result Classes again with an abstract class
+ BattleResult now has a list of phases
+ com.warhammer.Main class that has hardcoded attacker and defender
+ Printer class for console output

Version 0.2:
+ Javadoc for classes and methods
+ Changed access modifiers for several methods in CombatRules
+ Refactored classes into packages
+ Seperated AttackResult class into Result classes for the battle, shooting phase and attack.
+ implemented BattlePrinter class to give output to console