A java application for simulating combat in the tabletop game "Warhammer 40k" by Games Workshop.

Goal of this application is to make a statistical evaluation of a single round of combat between 2 units.
Eventually this application is meant to be customizable for the user and compatible with special rules and toggle-able options.

++++++++++++ Current Features ++++++++++++

Shooting Phase simulation
Calculation of hit rolls, wound rolls, saving throws and damage.
Object oriented approach to separating Units into their respective models and the weapons of said models
Damage allocation for models
Removing/deletion of models due to inflicted damage
UI Output to console
dice dependant damage rolls (2d6+3)
Options for deciding on allocation group strategies

++++++++++++ Planned Features ++++++++++++

variable Starting distance (max 12 inches) to be used for charging
Charging Phase
Implement options to decide if a Unit (Defender or attacker) wants to try charging or stay stationary
Choosing Weapon profiles dynamically
Melee combat simulation
SQL Database for unit/model/weapon data
Web Interface