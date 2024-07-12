document.addEventListener("DOMContentLoaded", function() {
    fetch('/segnalazioni/all')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        if (response.status === 204) {
            return [];
        }
        return response.json();
    })  
    .then(data => {
        console.log(data);
        const segnalazioniList = document.getElementById('segnalazioniList');
        if (data.length === 0) {
            segnalazioniList.innerHTML = '<li>Nessun segnalazione trovata</li>';
        } else {
            for (let i = data.length - 1; i >= 0; i--) {
                let segnalazioni = data[i];
                segnalazioniList.innerHTML += `<div class="row mt-5" id="segnalazione-${segnalazioni.id}">
                    <div class="col-12 row justify-content-center">
                        <div class="col-6 row justify-content-center" >
                            <div class="col-12 ms_box">
                                <div class="col-12 p-3 row">
                                    <h3 class="col-6">${segnalazioni.tipologiaCrimine[0].nome}</h3>
                                    <h4 class="col-6 text-end">${segnalazioni.comune.nome}</h4>
                                </div>
                                <div class="col-12 p-3"><img class="ms_immage ms_radius" src="${segnalazioni.foto_o_video}" alt=""></div>
                                <div class="col-12 p-3 fs-5">${segnalazioni.descrizione}</div>
                                <div class="row p-3">
                                    <div class="col-6 p-3 fs-6 "> ${segnalazioni.data}</div>
                                    <div class="col-6 p-3 fs-6 text-end"> ${segnalazioni.fasciaOraria.nome}</div>
                                </div>
                                <div class="col-12 p-3 fs-5 text-center"><a class="d-inline-blok ms_btn ms_btn_mod me-5" href="http://localhost:8080/segnalazioni/updateAD/${segnalazioni.id}">Modifica</a> <a class="d-inline-blok ms_btn ms_btn_elim ms-3" href='' onclick="deleteSegnalazione(${segnalazioni.id})">Elimina</a></div>
                            </div>
                        </div>
                    </div>
                </div>`;
            }
        }
    })
    .catch(error => console.error('Error fetching comuni:', error));   
});


function deleteSegnalazione(id){
    if (confirm("Sei sicuro di voler eliminare questa segnalazione?")) {
        fetch(`/segnalazioni/delete/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(message => {
            console.log(message);
            // Rimuovi l'elemento specifico dalla DOM
            const segnalazioneElement = document.getElementById(`segnalazione-${id}`);
            if (segnalazioneElement) {
                segnalazioneElement.remove();
            }
        })
        .catch(error => console.error('Error deleting comune:', error));
        //alert("La segnalazione è stata eliminata");
    } else {
        // Se l'utente clicca su "Cancel", non fare nulla
      alert("L'eliminazione è stata annullata");
    }

}