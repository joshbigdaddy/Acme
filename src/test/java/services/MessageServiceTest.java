package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Message;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class MessageServiceTest extends AbstractTest{
	@Autowired
	private MessageService messageService;
	@Test
	public void testCreate(){
		
		Message res=messageService.create();
		System.out.println(res);
	
	}
	@Test
	public void testFindAll(){
	Collection<Message> all=messageService.findAll();	
	for(Message c:all){
		System.out.println(c.getId()+"- "+c.getRecipient()+": "+c.getBody());
		}
	}
	//ESTO PUEDE ESTAR SUJETO A CAMBIOS YA QUE EL ID PUEDE VARIAR SI AÑADIMOS COSAS
	
	@Test
	public void testSave(){
	Integer messageId;
	messageId=28;
	Message c=messageService.findOne(messageId);
	
	c.setBody("Shounen yo shinwa ni nare");
	messageService.save(c);
	System.out.println("Se espera 'Shounen yo shinwa ni nare' como resultado. El resultado es: "
	+c.getBody());
	}
	@Test
	public void testDelete(){
		authenticate("admin1");
		Integer messageId;
		messageId=28;
		Message c=messageService.findOne(messageId);
		messageService.delete(c);
		System.out.println("Debe estar borrado el mensaje 1 cuyo cuerpo era el editado antes");
		for(Message mes:messageService.findAll()){
			System.out.println(mes.getBody());
			}
		authenticate(null);
		}
	}



