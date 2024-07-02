package com.services.comune;

import com.model.Comune;
import java.util.List;

public interface ComuneService{

	List<Comune> findAll();

	Comune findbyId(Long id) throws Exception;

	Comune save(Comune comune);

	void deleteById(Long id) throws Exception;

	Comune updateById(Long id, Comune comune) throws Exception;
	
	//void saveCSV() throws Exception;
}

