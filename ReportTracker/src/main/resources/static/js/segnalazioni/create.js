document.addEventListener("DOMContentLoaded", function() {
    //COMUNI API
    fetch('/comuneAD/all')
    .then(response => response.json())
    .then(data => {
        const comuniList = document.getElementById('comune');
        data.forEach(comuni => {
            comuniList.innerHTML += `<option value="${comuni.nome}">${comuni.nome}</option>`;
        });
    })
    .catch(error => console.error('Error fetching comuni:', error));  

    //FASCIA ORARIA API
    fetch('/fasciaorariaAD/all')
    .then(response => response.json())
    .then(data => {
        const fascia_orariaList = document.getElementById('fascia_oraria');
        data.forEach(fascia_oraria => {
            fascia_orariaList.innerHTML += `<option value="${fascia_oraria.nome}">${fascia_oraria.nome}</option>`;
        });
    })
    .catch(error => console.error('Error fetching fascia oraria:', error)); 

    //TIPOLOGIA CRIMINE API
    fetch('/tipologie_criminiAD/all')
    .then(response => response.json())
    .then(data => {
        const tipologia_crimineList = document.getElementById('tipologia_crimine');
        data.forEach(tipologia_crimine => {
            tipologia_crimineList.innerHTML += `<option value="${tipologia_crimine.nome}">${tipologia_crimine.nome}</option>`;
        });
    })
    .catch(error => console.error('Error fetching tipologie crimini:', error)); 
});

function creaSeg() {
    console.log("ciao");
    const descrizione = document.getElementById('descrizione').value;
    const data = document.getElementById('data').value;
    const fileInput = document.getElementById('fileInput').files[0];
    const comune = document.getElementById('comune').value;
    const fascia_oraria = document.getElementById('fascia_oraria').value;
    const tipologia_crimine = document.getElementById('tipologia_crimine').value;

    const dati = {
        descrizione: descrizione,
        data: data,
        comune: comune,
        fascia_oraria: fascia_oraria,
        tipologia_crimine: tipologia_crimine
    };

    const oggi = new Date();
    const dataSegnalazione = new Date(data);

    if (dataSegnalazione > oggi) {
        const result = document.getElementById('result');
        result.innerHTML = "<span class='ms_color_r'>La data non può essere dopo la data odierna</span>";
        console.error('La data non può essere dopo la data odierna');
    } else if (descrizione.trim() === "" || !data || !comune || !fascia_oraria || !tipologia_crimine) {
        const result = document.getElementById('result');
        result.innerHTML = "<span class='ms_color_r'>Tutti i campi sono obbligatori</span>";
        console.error('Tutti i campi sono obbligatori');
    } else {
        fetch('/segnalazioni/save/segnalazione', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dati)
        })
        .then(response => response.json())
        .then(data => {
            // Pulisci il form
            document.getElementById('descrizione').value = "";
            document.getElementById('data').value = "";
            document.getElementById('fileInput').value = "";
            document.getElementById('comune').value = "";
            document.getElementById('fascia_oraria').value = "";
            document.getElementById('tipologia_crimine').value = "";

            // Aggiorna il risultato
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_g'>Segnalazione creata con successo</span>";
        })
        .catch(error => {
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_r'>La segnalazione non è stata creata</span>";
            console.error('Error saving segnalazione:', error);
        });
    }
}