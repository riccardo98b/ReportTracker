console.log("pro");
document.addEventListener("DOMContentLoaded", function() {
    // Ottieni il percorso dell'URL
    const pathArray = window.location.pathname.split('/');
    // L'ID del comune dovrebbe essere l'ultimo elemento del percorso
    const id = pathArray[pathArray.length - 1];

    if (!id || isNaN(id)) {
        const result = document.getElementById('result');
        result.innerHTML = "<span class='ms_color_r'>ID del comune non trovato nell'URL</span>";
        return;
    }

    // Gestione del submit del form di aggiornamento
    document.getElementById('updateComuneForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const nome = document.getElementById('updateNome').value;

        fetch(`/comuneAD/update/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nome: nome })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(comune => {
            // Aggiorna il risultato
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_g'>Il comune è stato aggiornato con successo</span>";

            // Pulisci il form
            document.getElementById('updateNome').value = "";
        })
        .catch(error => {
            console.error('Error updating comune:', error);
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_r'>Il comune non è stato aggiornato con successo</span>";
        });
    });
});