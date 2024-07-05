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
            data.forEach(segnalazioni => {
                segnalazioniList.innerHTML += `<div class="row mt-5">
                <div class="col-12 row justify-content-center">
                    <div class="col-6 row justify-content-center" >
                        <div class="col-12 ms_box">
                            <div class="col-12 p-3">
                                <h3 class="col-4">${segnalazioni.comune.nome}</h3>
                            </div>
                            <div class="col-12 p-3"><img class="ms_immage ms_radius" src="${segnalazioni.foto_o_video}" alt=""></div>
                            <div class="col-12 p-3 fs-5">${segnalazioni.descrizione}</div>
                        </div>
                    </div>
                </div>
            </div>` ;
            });
        }
    })
    .catch(error => console.error('Error fetching comuni:', error));   
});
