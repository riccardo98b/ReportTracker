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
        const segnalazioniList = document.getElementById('segnalazioniList');
        console.log(data);
        if (data.length === 0) {
            segnalazioniList.innerHTML = '<li>Nessun segnalazione trovata</li>';
        } else {
            let start = Math.max(data.length - 3, 0); // Calcola l'indice iniziale, assicurandosi di non andare sotto zero
            for (let i = data.length - 1; i >= start; i--) {
                let segnalazioni = data[i];
                segnalazioniList.innerHTML += `<div class="col-4 mb-4">
                    <div class="card">
                        <img src="${segnalazioni.foto_o_video}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <div class="row">
                                <h4 class="card-title col-6">${segnalazioni.tipologiaCrimine[0].nome}</h4>
                                <h5 class="card-title col-6 text-end">${segnalazioni.comune.nome}</h5>
                            </div>
                            <p class="card-text">${segnalazioni.descrizione}</p>
                        </div>
                    </div>
                </div>`;
            }
        }
    })
    .catch(error => console.error('Error fetching comuni:', error));   
});