package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{

		public Message(){
			super();
		}
		
		
	private String sender;
	private String recipient;
	private String subject;
	private String body;
	private Date sentMoment;
	
	@NotBlank
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@NotBlank
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	@NotBlank
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@NotBlank
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Past
	public Date getSentMoment() {
		return sentMoment;
	}
	public void setSentMoment(Date sentMoment) {
		this.sentMoment = sentMoment;
	}
	
	//RELATIONSHIP
	
	
	private SystemFolder systemFolder;

	@Valid
	@NotNull
	@ManyToOne(optional=true)
	public SystemFolder getSystemFolder() {
		return systemFolder;
	}
	public void setSystemFolder(SystemFolder systemFolder) {
		this.systemFolder = systemFolder;
	}
	
	
}
