package br.com.javamail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class EnviarEmail {

	private String userName = " "; //informe seu e-mail
	private String password = " "; //informe sua senha
	
	private String destinatarios = "";
	private String remetente = "";
	private String assunto = "";
	private String texto = "";
	
	EnviarEmail(String destinatarios, String remetente, String assunto, String texto) {
		this.destinatarios = destinatarios;
		this.remetente = remetente;
		this.assunto = assunto;
		this.texto = texto;
		
		
	}

	public void enviar(boolean envioHtml) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); // autorização
		properties.put("mail.smtp.starttls", "true"); // autenticação
		properties.put("mail.smtp.host", " "); // informe seu servidor smtp
		properties.put("mail.smtp.port", " "); // informe a porta do servidor smtp
		properties.put("mail.smtp.socketFactory.port", " "); // informe a porta especifica de socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // classe socket de conexão

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}

		});

		Address[] toUser = InternetAddress.parse(destinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, remetente)); // quem envia o e-mail
		message.setRecipients(Message.RecipientType.TO, toUser); // destinatário
		message.setSubject(assunto); // assunto do e-mail
		
		if (envioHtml) {
			message.setContent(texto, "text/html; charset=utf-8");
			
		}else {
			message.setText(texto); // corpo do e-mail

		}
		Transport.send(message);

	}
	
	
	public void enviarAnexo(boolean envioHtml) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); // autorização
		properties.put("mail.smtp.starttls", "true"); // autenticação
		properties.put("mail.smtp.host", " "); // informe seu servidor smtp
		properties.put("mail.smtp.port", " "); // informe sua porta do servidor smtp
		properties.put("mail.smtp.socketFactory.port", " "); // informe sua porta especifica de socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // classe socket de conexão

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}

		});

		Address[] toUser = InternetAddress.parse(destinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, remetente)); // quem envia o e-mail
		message.setRecipients(Message.RecipientType.TO, toUser); // destinatário
		message.setSubject(assunto); // assunto do e-mail
		
		
		//Parte 1 do e-mail
		MimeBodyPart corpoemail = new MimeBodyPart();
		
		
		
		if (envioHtml) {
			corpoemail.setContent(texto, "text/html; charset=utf-8");
			
		}else {
			corpoemail.setText(texto); // corpo do e-mail
		}
			
		
		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorDePdf());
		arquivos.add(simuladorDePdf());
		arquivos.add(simuladorDePdf());
		arquivos.add(simuladorDePdf());
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoemail);
		
		int index = 0;
		
		for (FileInputStream fileInputStream : arquivos) {
		
			// Parte 2 - Anexo
			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
			anexoEmail.setFileName("anexoemail"+index+".pdf");
				
			multipart.addBodyPart(anexoEmail);
		
			index++;
		}
		
		message.setContent(multipart);
		
		Transport.send(message);

	}
	

	private FileInputStream simuladorDePdf() throws Exception{
		
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteúdo do pdf anexo com Java Mail"));
		document.close();
		
		return new FileInputStream(file);
		
	}
	
}
