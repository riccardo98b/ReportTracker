package com.services.fascia_oraria;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.FasciaOraria;
import com.my_methods.MyMethods;
import com.repositories.FasciaOrariaRepository;


@Service
public class FasciaOrariaServiceImpl extends MyMethods implements FasciaOrariaService{
	
	@Autowired
	private FasciaOrariaRepository FascOrariaRepository;

	@Override
	public FasciaOraria getFasciaOrariaById(Long id) {
        return  FascOrariaRepository.findById(id).orElse(null);
	}

	@Override
	public void removeFasciaOraria(Long id) throws Exception {
		if(FascOrariaRepository.existsById(id)) {
			FascOrariaRepository.deleteById(id);
		}else {
			throw new Exception("ID non trovato"); 
		}
		
	}

	@Override
	public void updateFasciaOraria(FasciaOraria fasciaOraria) {
		 if (fasciaOraria.getId() != null && FascOrariaRepository.existsById(fasciaOraria.getId())) {
			 FascOrariaRepository.save(fasciaOraria);
	        }		
	}

	@Override
	public FasciaOraria addFasciaOraria(FasciaOraria fasciaOraria) {
        return FascOrariaRepository.save(fasciaOraria);

		
	}

	  
	@Override
	public List<FasciaOraria> findAll() throws Exception {
		return (List<FasciaOraria>) FascOrariaRepository.findAll();
	}
	
	
	
	
	

/*	@Override
	public void saveCsv() throws Exception {
		// commento il percorso 
		// Samuele String file = "C:\\Users\\Samuele\\OneDrive\\Documenti\\testJava/Dati Fascia Oraria DB - Foglio1.csv";
		// Riccardo String file = "/Users/riccardobelloni/Desktop/PROGETTO FINALE/caricamentoDB/fascia.csv";

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
	
	 

}
