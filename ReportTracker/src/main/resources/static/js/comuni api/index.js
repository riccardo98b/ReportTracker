document.addEventListener("DOMContentLoaded", function() {
    fetch('/comuneAD/all')
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
        const comuniList = document.getElementById('comuniList');
        if (data.length === 0) {
            comuniList.innerHTML = '<li>Nessun comune trovato</li>';
        } else {
            data.forEach(comune => {
                comuniList.innerHTML += "<tr> <td>" + comune.id + "</td> <td>" + comune.nome + "</td> <td><a href='http://localhost:8080/comuni/update/" + comune.id + "'>Modifica</a></td> <td><a href='#' class='delete-link' onclick='deleteComune("+comune.id+")'>Elimina</a></td> </tr>";
            });
        }
    })
    .catch(error => console.error('Error fetching comuni:', error));   
});

function deleteComune(id){
    console.log(id);
    fetch(`/comuneAD/delete/${id}`, {
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

        // Rimuovi la riga dalla tabella
        this.closest('tr').remove();
    })
    .catch(error => console.error('Error deleting comune:', error));

}