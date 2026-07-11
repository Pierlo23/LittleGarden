package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrello implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Albero> alberi;
	
	public Carrello () {
		alberi = new ArrayList<Albero>();
	}

	public void addAlbero(Albero albero) {
		alberi.add(albero);
	}

	public void deleteAlbero(Albero albero) {
		for (Albero prod : alberi) {
			if (prod.getIdAlbero() == albero.getIdAlbero()) {
				alberi.remove(prod);
				break;
			}
		}
	}

	public List<Albero> getProducts() {
		return alberi;
	}

}
