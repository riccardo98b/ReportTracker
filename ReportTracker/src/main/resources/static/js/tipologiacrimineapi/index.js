document.addEventListener("DOMContentLoaded", function() {
    fetch('/tipologie_criminiAD/all')
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
            const tipologiaList = document.getElementById('tipologiaList');
            if (data.length === 0) {
                tipologiaList.innerHTML = '<tr><td colspan="4">Nessuna tipologia crimine trovata</td></tr>';
            } else {
                data.forEach(tipologia => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${tipologia.id}</td>
                        <td>${tipologia.nome}</td>
                        <td><a class="ms_modifica" href="http://localhost:8080/tipologiecrimini/update/${tipologia.id}">Modifica</a></td>
                        <td><a class="ms_elimina" href="" onclick="deleteTipologia(${tipologia.id}, event)">Elimina</a></td>
                    `;
                    tipologiaList.appendChild(row);
                });
            }
        })
        .catch(error => console.error('Error fetching tipologia crimine:', error));
});

function deleteTipologia(id, event) {
    event.preventDefault();
    console.log(id);
    fetch(`/tipologie_criminiAD/cancella/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.text();
    })
    .then(message => {
        alert(message);
        event.target.closest('tr').remove();
    })
    .catch(error => console.error('Error deleting tipologia crimine:', error));
}