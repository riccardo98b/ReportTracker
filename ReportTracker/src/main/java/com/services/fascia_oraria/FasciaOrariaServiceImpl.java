package com.services.fascia_oraria;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.FasciaOraria;
import com.my_methods.MyMethods;
import com.repositories.FasciaOrariaRepository;


@Service
public class FasciaOrariaServiceImpl extends MyMethods implements FasciaOrariaService{
	
	@Autowired
	private FasciaOrariaRepository fasciaOrariaRepository;

	@Override
	public FasciaOraria getFasciaOrariaById(Long id) {
        return  fasciaOrariaRepository.findById(id).orElse(null);
	}

	@Override
	public void removeFasciaOraria(Long id) throws Exception {
		if(fasciaOrariaRepository.existsById(id)) {
			fasciaOrariaRepository.deleteById(id);
		}else {
			throw new Exception("ID non trovato"); 
		}
		
	}

	@Override
	public FasciaOraria updateFasciaOraria(Long id,FasciaOraria fasciaOraria) throws Exception{
		Optional<FasciaOraria> optf = fasciaOrariaRepository.findById(id);
			if(optf.isPresent()) {
				FasciaOraria f = optf.get();
				f.setNome(fasciaOraria.getNome());
				return fasciaOrariaRepository.save(f);
				
			}
			else {
				throw new Exception("Fascia oraria con id "+id+" non trovato.");
			}
	}

	@Override
	public FasciaOraria addFasciaOraria(FasciaOraria fasciaOraria) {
        return fasciaOrariaRepository.save(fasciaOraria);

		
	}

	  
	@Override
	public List<FasciaOraria> findAll() throws Exception {
		return (List<FasciaOraria>) fasciaOrariaRepository.findAll();
	}
	
	
	
	
	/*
	@Override
	public void saveCsv() throws Exception {
		// commento il percorso 
		// Samuele String file = "C:\\Users\\Samuele\\OneDrive\\Documenti\\testJava/Dati Fascia Oraria DB - Foglio1.csv";
		// Riccardo String file = "/Users/riccardobelloni/Desktop/PROGETTO FINALE/caricamentoDB/fascia.csv";
		// Nicolò   String file = "C://Users/Nicolò/Desktop/cartella/java/progetto_finale/DatiFasciaOrariaDB.csv";
		//Christian String file = "/Users/christiancanicoba/Desktop/Dati Progetto/fascia.csv";
		// Pasquale String file = "C://Users/Pasquale/Desktop/File DB/fascia.csv";

	        List<String> listFile = new ArrayList<>();
	        try {

	            listFile = lettura(file);


	            for(String s: listFile) {
	                String [] sup = s.split(",");

	                FasciaOraria fasceOrarie = new FasciaOraria();
	             
	                fasceOrarie.setNome(sup[0]);
	  

	                FascOrariaRepository.save(fasceOrarie);
	            }

	        }catch(Exception e) {
	            e.printStackTrace();
	        }

	}*/
	
	 
	@Override
	public FasciaOraria findByNomeFasciaOraria(String fasciaOraria) throws Exception {
		Optional<FasciaOraria> optFO = fasciaOrariaRepository.findByNome(fasciaOraria);
		if(optFO.isPresent()) {
			return optFO.get();
		}
		
		throw new Exception("Il la faccia oraria " + fasciaOraria + " non e stata trovata"); 
	}

}
