package services;

import java.util.Collection;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Message;
import domain.SystemFolder;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class SystemFolderServiceTest extends AbstractTest{
	@Autowired
	private SystemFolderService systemFolderService;
	@Test
	public void testCreate(){
		
		SystemFolder res=systemFolderService.create();
		System.out.println(res);
	
	}
	@Test
	public void testFindAll(){
	Collection<SystemFolder> all=systemFolderService.findAll();	
	for(SystemFolder c:all){
		System.out.println(c.getId()+"- "+c.getName());
		}
	}
	//ESTO PUEDE ESTAR SUJETO A CAMBIOS YA QUE EL ID PUEDE VARIAR SI AÑADIMOS COSAS
	
	@Test
	public void testSave(){
	Integer systemFolderId;
	systemFolderId=25;
	SystemFolder c=systemFolderService.findOne(systemFolderId);
	
	c.setName("Carpeta nueva con nombre cambiado");
	systemFolderService.save(c);
	System.out.println("Se espera 'Carpeta nueva con nombre cambiado' como resultado. El resultado es: "
	+c.getName());
	}
	@Test
	public void testDelete(){
		authenticate("consumer");
		Integer systemFolderId;
		systemFolderId=27;
		SystemFolder c=systemFolderService.findOne(systemFolderId);
		systemFolderService.delete(c);
		System.out.println("Debe estar borrada la folder Zeppeli's Folder");
		for(SystemFolder sis:systemFolderService.findAll()){
			System.out.println(sis.getName());
			}
		authenticate(null);
		}
	@Test
	public void testDisplaySystemFolderWithMessage(){
	Map<SystemFolder, Collection<Message>> res=
		systemFolderService.displaySystemFolderWithMessage();
	System.out.println("Procedemos a mostrar el mapa con las carpetas" +
			" y sus mensajes: "+res+" NOTA: El resultado puede no verse" +
					"de forma muy clara por el propio toString del tipo Map" +
					". Sin embargo, el resultado obtenido es correcto.");
	}
	}




