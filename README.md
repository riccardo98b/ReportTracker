# ReportTracker


## Descrizione
**ReportTracker** è un sito progettato per la tracciabilità, organizzazione e gestione dei report di attività
di micro-criminalità.
Sviluppato principalmente in Java, con frontend web in HTML, CSS e JavaScript,
questo progetto permette  di gestire e monitorare i report in modo efficiente.

## Funzionalità
- **Creazione e Gestione Report**: Permette di aggiungere, modificare ed eliminare report.
- **Organizzazione**: Categorizzazione automatica dei report per una gestione più ordinata.
- **Interfaccia Utente Web**: Facile accesso e navigazione tramite un’interfaccia web intuitiva, supportata da un backend in Java.
- **Sicurezza dei Dati**: Implementazione di protocolli per la protezione delle informazioni aziendali.
  
## Struttura del Progetto
Il progetto è suddiviso nelle seguenti componenti principali:
- **Backend Java**: Gestisce le logiche di business, le operazioni CRUD e la comunicazione con il database.
- **Frontend Web**: Interfaccia utente costruita con HTML, CSS, Bootstrap e JavaScript per un'esperienza utente intuitiva.
- **Database**: Memorizzazione dei dati relativi ai report e agli utenti.

## Prerequisiti
Prima di iniziare, assicurati di avere:
- **Java Development Kit (JDK) 8 o superiore**
- **Un browser web** (consigliato Chrome o Firefox)
- **Un IDE compatibile con Java** (ad esempio, IntelliJ IDEA o Eclipse)
  
## Installazione
<p>Segui questi passaggi per configurare l'ambiente: </p>
<ul>
 <li>1. **Clona il repository**:
   ```bash
   git clone https://github.com/riccardo98b/ReportTracker.git
 </li>
 <li>Importa il progetto nel tuo IDE preferito.</li>
 <li>Compila ed esegui il backend Java per avviare il server.</li>
 <li>Accedi al frontend tramite il browser, inserendo l'URL fornito dal server (ad esempio, http://localhost:8080).</li>
</ul>
  
## Configurazione Database
<p>Assicurati che il database sia correttamente configurato:</p>
<ul>
<li>Imposta il database utilizzando il file di configurazione application.properties.</li>
<li>Avvia le tabelle iniziali tramite gli script SQL forniti nella cartella db/.</li>
</ul>

## Esempi di Utilizzo
<p>Aggiunta di un Report: Tramite l'interfaccia web, è possibile aggiungere un nuovo report selezionando le categorie e inserendo i dettagli necessari.
Ricerca Avanzata: Utilizza i filtri per cercare report specifici per data, categoria o autore.
Gestione Utenti: Gli amministratori possono aggiungere, rimuovere o modificare i permessi degli utenti.</p>
