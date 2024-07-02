package com.services.tipologia_crimine;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.TipologiaCrimine;
import com.repositories.TipologiaCrimineRepository;


@Service
public class TipologiaCrimineServiceImpl implements TipologiaCrimineService{
	
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
	
}


