document.addEventListener("DOMContentLoaded", function() {
    fetch('/fasciaorariaAD/all')
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
            const fasciaOrariaList = document.getElementById('fasciaOrariaList');
            if (data.length === 0) {
                fasciaOrariaList.innerHTML = '<tr><td colspan="4">Nessuna fascia oraria trovata</td></tr>';
            } else {
                data.forEach(fasciaOraria => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${fasciaOraria.id}</td>
                        <td>${fasciaOraria.nome}</td>
                        <td><a class="ms_modifica" href="http://localhost:8080/fascia_oraria/update/${fasciaOraria.id}">Modifica</a></td>
                        <td><a class="ms_elimina" href="" onclick="deleteFasciaOraria(${fasciaOraria.id})">Elimina</a></td>
                    `;
                    fasciaOrariaList.appendChild(row);
                });
            }
        })
        .catch(error => console.error('Error fetching fascia oraria:', error));
});

function deleteFasciaOraria(id) {
    console.log(id);
    fetch(`/fasciaorariaAD/cancella/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
		console.log(response);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.text();
    })
    .then(message => {
        alert(message);
		this.closest('tr').remove();
     
    })
    .catch(error => console.error('Error deleting fascia oraria:', error));
}