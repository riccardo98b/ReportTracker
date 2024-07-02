package com.services.tipologia_crimine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.TipologiaCrimine;
import com.my_methods.MyMethods;
import com.repositories.TipologiaCrimineRepository;


@Service
public class TipologiaCrimineServiceImpl extends MyMethods implements TipologiaCrimineService{
	
	@Autowired
	private TipologiaCrimineRepository repository;
	
	@Override
	public List<TipologiaCrimine> findAll(){
		return (List<TipologiaCrimine>) repository.findAll(); // cast
	}
	
	
	@Override
	public TipologiaCrimine findbyId(Integer id) throws Exception{
		Optional<TipologiaCrimine> optTipologiaCrimine = repository.findById(id);  // predefinito nel crud
		if(optTipologiaCrimine.isPresent()) {
			return optTipologiaCrimine.get();
		}
		throw new Exception("TipologiaCrimine con id " +id+ " non esiste");
	}
	
	
	@Override
	public TipologiaCrimine save(TipologiaCrimine tipologiacrimine) {
		return repository.save(tipologiacrimine);
	}
	
	
	@Override
	public void deleteById(Integer id) throws Exception {
		if(repository.existsById(id)) {
			repository.deleteById(id);
		} else {
		throw new Exception("TipologiaCrimine con id " +id+ " non esiste");
		}
	}
	
	
	@Override
	public TipologiaCrimine updateById(Integer id, TipologiaCrimine tipologiacrimine) throws Exception{
		if(repository.existsById(id)) {
			return repository.save(tipologiacrimine);
		} else {
			throw new Exception("TipologiaCrimine con id " +id+ " non esiste");
		}
	}
	
	
	/*
	public void saveCSV() throws IOException{
		
        String file = "C://Users/Nicolò/Desktop/cartella/java/progetto_finale/DatiTipologieDB.csv";
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
    }
	*/
}


