package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)

public class CommentServiceTest extends AbstractTest{
//Service Under test=======================================================
	
	@Autowired
	private CommentService commentService;
	
	@Test
	public void testFindAll(){
	Collection<Comment> all=commentService.findAll();	
	for(Comment c:all){
		System.out.println(c.getId()+" ,"+ c.getUserName()+" -"+c.getText());
		}
	}
	//ESTO PUEDE ESTAR SUJETO A CAMBIOS YA QUE EL ID PUEDE VARIAR SI AÑADIMOS COSAS
	@Test
	public void testFindOne(){
		Integer commentId;
		commentId=41;
		Comment c=commentService.findOne(commentId);
		System.out.println(c.getId()+", "+ c.getUserName()+" -"+c.getText()+
				"================================="+"El resultado esperado debería ser este: " +
				"32, User1 -Text 1");
	}
	@Test
	public void testSave(){
	Integer commentId;
	commentId=41;
	Comment c=commentService.findOne(commentId);
	c.setText("Shounen yo shinwa ni nare");
	commentService.save(c);
	System.out.println("Se espera 'Shounen yo shinwa ni nare' como resultado. El resultado es: "
	+c.getText());
	}
	@Test
	public void testDelete(){
		authenticate("admin1");
		Integer commentId;
		commentId=41;
		Comment c=commentService.findOne(commentId);
		commentService.delete(c);
		System.out.println("Debe estar borrado el comentario 1 cuyo texto era el editado antes");
		for(Comment com:commentService.findAll()){
			System.out.println(com.getId()+" ,"+ com.getUserName()+" -"+com.getText());
			}
		authenticate(null);
		}
	}

