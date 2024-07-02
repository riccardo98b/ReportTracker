package com.services.tipologia_crimine;

import java.io.IOException;
import java.util.List;
import com.model.TipologiaCrimine;


public interface TipologiaCrimineService {
	
	List<TipologiaCrimine> findAll();
	
	TipologiaCrimine findbyId(Integer id) throws Exception;
	
	// Salva un nuovo Author
	TipologiaCrimine save(TipologiaCrimine tipologia);
	
	// cancella un author di id specificato
	void deleteById(Integer id) throws Exception;
	
	// Aggiorna il tipo di crimine di id specificato
	TipologiaCrimine updateById(Integer id, TipologiaCrimine tipologia) throws Exception;
	
	/*
	void saveCSV() throws IOException;
	*/
}
