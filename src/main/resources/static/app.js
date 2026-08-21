fetch("/units")
    .then(response => response.json())
    .then(units => {
        const attackerSelect = document.getElementById("attacker");
        const defenderSelect = document.getElementById("defender");
        fillDropdown(attackerSelect, units);
        fillDropdown(defenderSelect, units);

    });



document.getElementById("simulateButton").addEventListener("click",runSimulation);



function runSimulation(){
    console.log("RUN SIMULATION CLICKED");
    const attackerID = Number(document.getElementById("attacker").value);
    const defenderID = Number(document.getElementById("defender").value);
    const runs = Number(document.getElementById("runs").value);
    const distance = Number(document.getElementById("distance").value);
    const attackerCharge = document.getElementById("attackerCharge").checked;
    const defenderCharge = document.getElementById("defenderCharge").checked;


    const request = {
        attackerID: attackerID, defenderID: defenderID,
        runs: runs,
        attackerStrategy: "WORST_SAVE_FIRST", defenderStrategy: "WORST_SAVE_FIRST",
        distance: distance,
        attackerCharge: attackerCharge, defenderCharge: defenderCharge
    };
console.log("Sending request:", request);
fetch("/simulations", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(request)
    })
        .then(response => { if (!response.ok) {
            throw new Error("Simulation req failed" + response.status);
            }
            return response.json();
        })
        .then(statistics => {
        displayStatistics(statistics);
        })
        .catch(error => {
        console.error("Error:", error);
        });
}


function displayStatistics(statistics) {

    document.getElementById("attackerAttacks").textContent =
        statistics.averageAttackerAttacks.toFixed(2);

    document.getElementById("attackerHits").textContent =
        statistics.averageAttackerHits.toFixed(2);

    document.getElementById("attackerWounds").textContent =
        statistics.averageAttackerWounds.toFixed(2);

    document.getElementById("attackerUnsavedWounds").textContent =
        statistics.averageAttackerUnsavedWounds.toFixed(2);

    document.getElementById("attackerDamage").textContent =
        statistics.averageAttackerDamage.toFixed(2);

    document.getElementById("attackerDestroyedModels").textContent =
        statistics.averageAttackerDestroyedModels.toFixed(2);


    document.getElementById("defenderAttacks").textContent =
        statistics.averageDefenderAttacks.toFixed(2);

    document.getElementById("defenderHits").textContent =
        statistics.averageDefenderHits.toFixed(2);

    document.getElementById("defenderWounds").textContent =
        statistics.averageDefenderWounds.toFixed(2);

    document.getElementById("defenderUnsavedWounds").textContent =
        statistics.averageDefenderUnsavedWounds.toFixed(2);

    document.getElementById("defenderDamage").textContent =
        statistics.averageDefenderDamage.toFixed(2);

    document.getElementById("defenderDestroyedModels").textContent =
        statistics.averageDefenderDestroyedModels.toFixed(2);

    document.getElementById("attackerCharges").textContent =
        statistics.totalAttackerSuccessfulCharges;

    document.getElementById("defenderCharges").textContent =
        statistics.totalDefenderSuccessfulCharges;
}


function fillDropdown(select, units){
    units.forEach(unit => {
        const option = document.createElement("option");
        option.value = unit.id;
        option.textContent = unit.name;
        select.appendChild(option);
    });
}