package com.services.comune;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Comune;
import com.repositories.ComuneRepository;

@Service
public class ComuneServiceImpl implements ComuneService{

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
	
	
}
