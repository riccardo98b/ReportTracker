document.addEventListener("DOMContentLoaded", function() {
    // Gestione del submit del form
    document.getElementById('comuneForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const nome = document.getElementById('nome').value.trim();

        if(nome.trim() == " " || nome.trim() == ""){
            const result = document.getElementById('result');
            result.innerHTML = "<span class='ms_color_r'>Il comune non è stato aggiornato con successo</span>";
            console.error('Error updating comune:', error);
        }else{

            fetch('/comuneAD/save', {
                method: 'POST',
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
                // Pulisci il form
                document.getElementById('nome').value = "";
    
                // Aggiorna il risultato
                const result = document.getElementById('result');
                result.innerHTML = "<span class='ms_color_g'>Il comune è stato aggiunto con successo</span>";
            })
            .catch(error => {
                const result = document.getElementById('result');
                result.innerHTML = "<span class='ms_color_r'>Il comune non è stato aggiunto, potrebbe essere gia presente</span>";
                console.error('Error saving comune:', error);
            });
        }

    });
});