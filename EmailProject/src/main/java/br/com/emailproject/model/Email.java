package br.com.emailproject.model;

public class Email {

	private String destinatarios;
	private String assunto;
	private String texto;
	
	
	public Email(String destinatarios, String assunto, String texto) {
		this.destinatarios = destinatarios;
		this.assunto = assunto;
		this.texto = texto;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
