package com.services.fascia_oraria;

import java.util.List;

import com.model.FasciaOraria;

public interface FasciaOrariaService {
	
	
	// visualizza la fascia oraria in base a un id
    FasciaOraria getFasciaOrariaById(Long id);
    
    // Rimuovere una fascia oraria usando un id
    void removeFasciaOraria(Long id) throws Exception;

    
    FasciaOraria updateFasciaOraria(Long id,FasciaOraria fasciaOraria) throws Exception;
    
    FasciaOraria addFasciaOraria(FasciaOraria fasciaOraria);

    List<FasciaOraria> findAll() throws Exception;  // Metodo per trovare tutte le fasce orarie

   //void saveCsv() throws Exception;

	

}
