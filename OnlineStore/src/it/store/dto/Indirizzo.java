package it.store.dto;

import org.apache.commons.lang3.StringUtils;

/*
 * Data Transfer Object per gli indirizzi in rubrica utente
 */

public class Indirizzo {
	
	public Indirizzo() {}
	
	public int id_indirizzo = -1;
	
	public String email,
				  nome_cognome,
				  indirizzo_1,
				  indirizzo_2,
				  citta,
				  provincia,
				  cap,
				  paese,
				  telefono;
	
	/*
	 * controllo che l'indirizzo sia valido.
	 * @return: true=ok | false=non valido
	 */
	public boolean isValido() {
		if (StringUtils.isBlank(nome_cognome)) {
			return false;
		}
		
		if (StringUtils.isBlank(indirizzo_1)) {
			return false;
		}
		
		if (StringUtils.isBlank(citta)) {
			return false;
		}
		
		if (StringUtils.isBlank(provincia)) {
			return false;
		}
		
		if (StringUtils.isNumeric(cap)) {
			if(cap.length()!=5) {
				return false;
			}
		}
		
		if (StringUtils.isBlank(paese)) {
			return false;
		}
		
		if (!StringUtils.isBlank(telefono)) {
			if(telefono.length()<9 || !StringUtils.isNumeric(telefono)) {
				return false;
			}
		}
		
		return true;
	}
	
}
