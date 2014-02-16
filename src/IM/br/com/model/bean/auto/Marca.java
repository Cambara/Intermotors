package IM.br.com.model.bean.auto;



public enum Marca {
	
	ACURA("Acura"),	AGRALE("Agrale"), ALFAROMEU("Alfa Romeu"), ASTONMARTIN("Aston Martin"), 
	AUDI("Audi"), BMW("BMW"), CADILLAC("Cadillac"),	CHRYSLER("Chrysler"), CITROEN("Citroen"), FIAT("FIAT"), 
	FORD("Ford"), GM("GM"), HONDA("Honda"), HYUNDAI("Hyundai"), INFINITI("Infiniti"),	KIA("KIA"), 
	MERCEDESBENZ("Mercedez-Benz"), MITSUBISHI("Mitsubishi"), NISSAN("Nissan"), PEUGEOT("Peugeot"), 
	RENAULT("Renault"), TOYOTA("Toyota"), VOLKSWAGEN("Volkswagem");
	
	private String descricao;
	
	Marca(final String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public String getChave(){
		return name();
	}
	public static Marca[] getMarcas(){
		return values();
	}
}
