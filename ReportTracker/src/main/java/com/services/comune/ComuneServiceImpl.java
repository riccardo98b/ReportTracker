package com.services.comune;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Comune;
import com.my_methods.MyMethods;
import com.repositories.ComuneRepository;

@Service
public class ComuneServiceImpl extends MyMethods implements ComuneService{

	@Autowired
	private ComuneRepository repository;
	
	@Override
	public List<Comune> findAll() {
		return (List<Comune>) repository.findAll();
	}

	@Override
	public Comune findbyId(Long id) throws Exception {
		Optional<Comune> optComune = repository.findById(id);
		if(optComune.isPresent()) {
			return optComune.get();
		}
		throw new Exception("Comune con id:"+id+" non esiste");
	}

	@Override
	public Comune save(Comune comune) {
		return repository.save(comune);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		if(repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new Exception("Comune con id:"+id+" non esiste");
		}
	}

	@Override
	public Comune updateById(Long id, Comune comune) throws Exception {
		if(repository.existsById(id)) {
			return repository.save(comune);
		} else {
			throw new Exception("Comune con id:"+id+" non esiste");
		}
	}
	
	
/*
	public void saveCSV() throws IOException{
		// Samuele String file = "C:\\Users\\Samuele\\OneDrive\\Documenti\\testJava/Dati Comune DB.csv";
		//Nicolo   String file = "C://Users/Nicolò/Desktop/cartella/java/progetto_finale/DatiComuneDB.csv"; 
		
		List<String> listFile = new ArrayList<>();
		try {
			
			listFile = lettura(file);
			
			
			for(String s: listFile) {
				String [] sup = s.split(",");
				
				Comune comuni = new Comune();
				
				comuni.setNome(sup[0]);
				
				repository.save(comuni);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}*/
	
}
