Version 0.4:
+ Added PrintLevel functionality for deciding how detailed the output should be
+ Gave ShootingPhaseResult access to the attacking and defending unit so that Printer can print who attacked who
+ Made it so the hardcoded example has the space marines actually shoot back.
+ Separated woundroll into before and after saves to display unsaved Wounds prior to the damage step.

Version 0.3:
+ Refactored Result Classes again with an abstract class
+ BattleResult now has a list of phases
+ Main class that has hardcoded attacker and defender
+ Printer class for console output

Version 0.2:
+ Javadoc for classes and methods
+ Changed access modifiers for several methods in CombatRules
+ Refactored classes into packages
+ Seperated AttackResult class into Result classes for the battle, shooting phase and attack.
+ implemented BattlePrinter class to give output to console