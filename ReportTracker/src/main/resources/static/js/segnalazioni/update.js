let comuni = [];
let fascie_orarie = [];
let tipologie_crimine = [];
document.addEventListener("DOMContentLoaded", function() {
    //COMUNI API
    fetch('/comuneAD/all')
    .then(response => response.json())
    .then(data => {
        const comuniList = document.getElementById('comune');
        data.forEach(comune => {
            comuni.push(comune);
            comuniList.innerHTML += `<option value="${comune.id}">${comune.nome}</option>`;
        });
    })
    .catch(error => console.error('Error fetching comuni:', error)); 

    //FASCIA ORARIA API
    fetch('/fasciaorariaAD/all')
    .then(response => response.json())
    .then(data => {
        const fascia_orariaList = document.getElementById('fascia_oraria');
        data.forEach(fascia_oraria => {
            fascie_orarie.push(fascia_oraria);
            fascia_orariaList.innerHTML += `<option value="${fascia_oraria.id}">${fascia_oraria.nome}</option>`;
        });
    })
    .catch(error => console.error('Error fetching fascia oraria:', error)); 

    //TIPOLOGIA CRIMINE API
    fetch('/tipologie_criminiAD/all')
    .then(response => response.json())
    .then(data => {
        const tipologia_crimineList = document.getElementById('tipologia_crimine');
        data.forEach(tipologia_crimine => {
            tipologie_crimine.push(tipologia_crimine);
            tipologia_crimineList.innerHTML += `<option value="${tipologia_crimine.id}">${tipologia_crimine.nome}</option>`;
        });
    })
    .catch(error => console.error('Error fetching tipologie crimini:', error)); 
    console.log(tipologie_crimine);
});


function creaSeg() {
    // Ottieni il percorso dell'URL
    const pathArray = window.location.pathname.split('/');
    // L'ID del comune dovrebbe essere l'ultimo elemento del percorso
    const id = pathArray[pathArray.length - 1];

    if (!id || isNaN(id)) {
        const result = document.getElementById('result');
        result.innerHTML = "<span class='ms_color_r'>ID del comune non trovato nell'URL</span>";
        return;
    }

    console.log("ciao");
    const descrizione = document.getElementById('descrizione').value;
    const data = document.getElementById('data').value;
    const foto_o_video = document.getElementById('foto_o_video').value;
    const comune = document.getElementById('comune').value;
    const fascia_oraria = document.getElementById('fascia_oraria').value;
    const tipologia_crimine = document.getElementById('tipologia_crimine').value;

	if (!data || descrizione.trim() === "" || !data || !comune || !fascia_oraria || !tipologia_crimine) {
		        const result = document.getElementById('result');
		        result.innerHTML = "<span class='ms_color_r'>Tutti i campi sono obbligatori</span>";
		        console.error('Tutti i campi sono obbligatori');
				return;
		}
    // Converti la data nel formato ISO 8601 (yyyy-MM-dd)
   const dataISO = new Date(data).toISOString().split('T')[0];
    // Rimuovi eventuali caratteri extra dopo la conversione
    const dataFormatted = dataISO.replace(/-/g, '/');
    let idC;
    let nomeC;

    let idF;
    let nomeF;

    let idTC;
    let nomeTC;

    console.log(comuni);

    comuni.forEach(element =>{
        if(comune == element.id){
            idC = element.id;
            nomeC = element.nome;
        }
    })

    fascie_orarie.forEach(element =>{
        if(fascia_oraria == element.id){
            idF = element.id;
            nomeF = element.nome;
        }
    })
    console.log(idF + " "+ nomeF);

    tipologie_crimine.forEach(element =>{
        if(tipologia_crimine == element.id){
            idTC = element.id;
            nomeTC = element.nome;
        }
    })

    console.log(idTC + " "+ nomeTC);

    let dati = {
        descrizione: descrizione,
        data: data,
        foto_o_video: foto_o_video,
        utente: {
        },
        comune: {
            id: idC,
            nome: nomeC
        },
        fasciaOraria: {
            id: idF,
            nome: nomeF
        },
        tipologiaCrimine: [
            {
                id: idTC,
                nome: nomeTC
            }
        ]        
    }; 

    const oggi = new Date();
    const dataSegnalazione = new Date(dataFormatted);

   if (dataSegnalazione > oggi) {
        const result = document.getElementById('result');
        result.innerHTML = "<span class='ms_color_r'>La data non può essere dopo la data odierna</span>";
        console.error('La data non può essere dopo la data odierna');
    } else if (descrizione.length > 250) {
		const result = document.getElementById('result');
		result.innerHTML = "<span class='ms_color_r'>La descrizione non può superare i 250 caratteri</span>";
		console.error('La descrizione non può superare i 250 caratteri');
	} else if (fotoUrl.length > 250 || videoUrl.length > 250) {
	    result.innerHTML = "<span class='ms_color_r'>L'URL di foto o video non può superare i 250 caratteri</span>";
	    console.error('L\'URL di foto o video non può superare i 250 caratteri');
    } else {
        
        fetch(`/segnalazioni/update/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dati)
        })
        .then(response => response.json(),
            console.log(dati)
        )
        .then(data => {
         console.log('Success:', data);
            // Pulisci il form
            document.getElementById('descrizione').value = "";
            document.getElementById('data').value = "";
            document.getElementById('tipologia_crimine').value= "";
            document.getElementById('comune').value = "";
            document.getElementById('fascia_oraria').value = "";
            document.getElementById('tipologia_crimine').value = "";

            // Aggiorna il risultato
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_g'>Segnalazione creata con successo</span>";
            window.location.href = 'http://localhost:8080/segnalazioni/logged';
        })
        .catch(error => {
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_r'>La segnalazione non è stata creata</span>";
            console.error('Error saving segnalazione:', error);
        });
    }   
}