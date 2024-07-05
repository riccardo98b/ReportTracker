   document.addEventListener("DOMContentLoaded", function() {
    // Ottieni il percorso dell'URL
    const pathArray = window.location.pathname.split('/');
    // L'ID del comune dovrebbe essere l'ultimo elemento del percorso
    const id = pathArray[pathArray.length - 1];

    if (!id || isNaN(id)) {
        const result = document.getElementById('result');
        result.innerHTML = "<span class='ms_color_r'>ID della tipologia crimine è stata  trovata nell'URL</span>";
        return;
    }

    // Gestione del submit del form di aggiornamento
    document.getElementById('updateTipologiaForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const nome = document.getElementById('updateNome').value;

        fetch(`/tipologie_criminiAD/update/${id}`, {
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
        .then(data => {
            // Aggiorna il risultato
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_g'>La tipologia crimine è stata aggiornata con successo</span>";

            // Pulisci il form
            document.getElementById('updateNome').value = "";
        })
        .catch(error => {
            console.error('Error updating tipologia crimine:', error);
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_r'>La tipologia crimine non è stata aggiornata</span>";
        });
    });
});