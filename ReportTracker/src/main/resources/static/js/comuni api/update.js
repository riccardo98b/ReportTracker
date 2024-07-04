console.log("pro");
document.addEventListener("DOMContentLoaded", function() {
    // Gestione del submit del form di aggiornamento
    document.getElementById('updateComuneForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const id = document.getElementById('updateId').value;
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
            document.getElementById('updateId').value = "";
            document.getElementById('updateNome').value = "";
        })
        .catch(error => {
            console.error('Error updating comune:', error);
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_r'>Il comune non è stato aggiornato con successo</span>";
        });
    });
});