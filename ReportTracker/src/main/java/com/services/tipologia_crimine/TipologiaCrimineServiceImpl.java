package com.services.tipologia_crimine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.FasciaOraria;
import com.model.TipologiaCrimine;
import com.my_methods.MyMethods;
import com.repositories.TipologiaCrimineRepository;


@Service
public class TipologiaCrimineServiceImpl extends MyMethods implements TipologiaCrimineService{
	
	@Autowired
	private TipologiaCrimineRepository tipologiaCrimineRepository;
	
	@Override
	public List<TipologiaCrimine> findAll(){
		return (List<TipologiaCrimine>) tipologiaCrimineRepository.findAll(); // cast
	}
	
	
	@Override
	public TipologiaCrimine findbyId(Integer id) throws Exception{
		Optional<TipologiaCrimine> optTipologiaCrimine = tipologiaCrimineRepository.findById(id);  // predefinito nel crud
		if(optTipologiaCrimine.isPresent()) {
			return optTipologiaCrimine.get();
		}
		throw new Exception("TipologiaCrimine con id " +id+ " non esiste");
	}
	
	
	@Override
	public TipologiaCrimine save(TipologiaCrimine tipologiacrimine) {
		return tipologiaCrimineRepository.save(tipologiacrimine);
	}
	
	
	@Override
	public void deleteById(Integer id) throws Exception {
		if(tipologiaCrimineRepository.existsById(id)) {
			tipologiaCrimineRepository.deleteById(id);
		} else {
		throw new Exception("TipologiaCrimine con id " +id+ " non esiste");
		}
	}
	
	
	
	

	@Override
	public TipologiaCrimine updateById(Integer id, TipologiaCrimine tipologia) throws Exception {
		Optional<TipologiaCrimine> optc = tipologiaCrimineRepository.findById(id);
		if(optc.isPresent()) {
			TipologiaCrimine t = optc.get();
			t.setNome(tipologia.getNome());

			return tipologiaCrimineRepository.save(t);
			
		}
		else {
			throw new Exception("Tipologia crimine con id "+id+" non trovato.");
		}
	}
	
	/*
	public void saveCSV() throws IOException{
		
		//Riccardo String file = "/Users/riccardobelloni/Desktop/PROGETTO FINALE/caricamentoDB/tipologie.csv";
        //Nicolo  String file = "C://Users/Nicolò/Desktop/cartella/java/progetto_finale/Dati Tipologie DB - Foglio1.csv";
        // Samuele String file = "C:\\Users\\Samuele\\OneDrive\\Documenti\\testJava/Dati Tipologie DB - Foglio1.csv";
        //Christian String file = "/Users/christiancanicoba/Desktop/Dati Progetto/tipologie.csv";
        // Pasquale String file = "C://Users/Pasquale/Desktop/File DB/tipologie.csv";
        List<String> listFile = new ArrayList<>();
        
        try {
            listFile = lettura(file);

            for(String s: listFile) {
                TipologiaCrimine tipologiacrimine = new TipologiaCrimine();
                tipologiacrimine.setNome(s);
                repository.save(tipologiacrimine);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    } */

	
	

	@Override
	public TipologiaCrimine findByNomeTipologiaCrimine(String tipologiacrimine) throws Exception {
		Optional<TipologiaCrimine> optTC = tipologiaCrimineRepository.findByNome(tipologiacrimine);
		if(optTC.isPresent()) {
			return optTC.get();
		} 
		
		throw new Exception("Il la faccia oraria " + tipologiacrimine + " non e stata trovata"); 
	}
}


