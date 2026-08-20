fetch("/units")
    .then(response => response.json())
    .then(units => {
        const attackerSelect = document.getElementById("attacker");
        const defenderSelect = document.getElementById("defender");
        fillDropdown(attackerSelect, units);
        fillDropdown(defenderSelect, units);

    });


function fillDropdown(select, units){
    units.forEach(unit => {
        const option = document.createElement("option");
        option.value = unit.id;
        option.textContent = unit.name;
        select.appendChild(option);
    });
}