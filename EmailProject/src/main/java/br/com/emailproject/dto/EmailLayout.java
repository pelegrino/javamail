package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

public class EmailLayout {
	
		private static final String QUEBRA_DE_LINHA_DUPLA = "<br><br>";

		//Podemos montar muitos métodos, para vários usuários diferentes, ex: adm e sec.
		public Email montarEmailAdministrador(String destinatario, String assunto) {
		
		StringBuilder texto = new StringBuilder();
		
		texto
			.append("A/c. Administrador")
			.append(QUEBRA_DE_LINHA_DUPLA);
		
		texto
			.append("Solicito acesso ao MailHug")
			.append(QUEBRA_DE_LINHA_DUPLA);
		
		
		gerarAssinatura(texto);
		gerarRodape(texto);
		
		return new Email(destinatario, assunto, texto.toString());
	}
		
		public Email montarEmailSecretario(String destinatarios, String assunto) {
			
			StringBuilder texto = new StringBuilder();
			
			texto
				.append("A/c. Secretario")
				.append(QUEBRA_DE_LINHA_DUPLA);
			
			texto
				.append("Solicito acesso ao MailHug")
				.append(QUEBRA_DE_LINHA_DUPLA);
			
			
			gerarAssinatura(texto);
			gerarRodape(texto);
			
			return new Email(destinatarios, assunto, texto.toString());
		}
	
	private String gerarAssinatura(StringBuilder texto) {
		return texto
				.append("Atenciosamente")
				.append(QUEBRA_DE_LINHA_DUPLA)
				.append("Operador")
				.append(QUEBRA_DE_LINHA_DUPLA)
				.toString();
		
	}

	private String gerarRodape(StringBuilder texto) {
		return texto.append("E-mail automático, favor não responder.").toString();
	}
	
}
