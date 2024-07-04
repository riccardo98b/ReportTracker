document.getElementById('fasciaOrariaForm').addEventListener('submit', function(event) {
    // Evita l'invio del form predefinito
    event.preventDefault();

    // Recupera il valore del campo 'nome'
    const nome = document.getElementById('nome').value;

    // Invia la richiesta al server per salvare la nuova fascia oraria
    fetch("/fasciaorariaAD/crea", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
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
        // Mostra un messaggio di successo
        document.getElementById('message').textContent = 'Fascia oraria creata con successo!';
        document.getElementById('message').classList.remove('alert-danger');
        document.getElementById('message').classList.add('alert', 'alert-success');

        // Pulisci il form
        document.getElementById('fasciaOrariaForm').reset();
    })
    .catch(error => {
        // Gestione degli errori
        console.error('Errore durante il salvataggio della fascia oraria:', error);
        document.getElementById('message').textContent = 'Errore durante il salvataggio della fascia oraria: ' + error.message;
        document.getElementById('message').classList.remove('alert-success');
        document.getElementById('message').classList.add('alert', 'alert-danger');
    });
});