package br.com.tao.model;

public enum Status {

	CANCELADO, CONCLUIDO, FALHOU;

	public String toString() {
		switch (this) {
		case CANCELADO:
			return "Cancelado";
		case CONCLUIDO:
			return "Concluído";
		default:
			return "Falhou";
		}
	}

}
