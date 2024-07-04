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
                const li = document.createElement('li');
                li.textContent = comune.nome;
                comuniList.appendChild(li);
            });
        }
    })
    .catch(error => console.error('Error fetching comuni:', error));   
});