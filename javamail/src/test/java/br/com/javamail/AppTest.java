package br.com.javamail;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@org.junit.Test
	public void testeEmail() throws Exception{
		
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("<h3>Olá</h3><br/>");
		stringBuilderTextoEmail.append("Vamos checar se o <b>negrito</b> está funcionando.<br/>");
		stringBuilderTextoEmail.append("Entre no site: <a target=\"_blank\" href=\"https://www.uol.com.br\" style=\"color:2525a7; padding: 14px 25px; text-align:center; text-decoration:none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border:3px solid green; background-color:#99DA39;\">UOL</a>");
		
		EnviarEmail enviarEmail = 
				new EnviarEmail(" ", //informe o e-mail do destinatario
								" ", //informe o nome do remetente
								"Java Mail", //informe o assunto do e-mail
								stringBuilderTextoEmail.toString());
		
		enviarEmail.enviarAnexo(true);
	}
}