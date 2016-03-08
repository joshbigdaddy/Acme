package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateConverters {

	/**
	 * Clase generadora de Converters automática, creada por Adrián Gómez
	 * Serrano,Estudiante de la ETSII de Sevilla, email: adrigomezse@gmail.com
	 * 
	 * Esta clase genera dos archivos txt con los converters tanto del
	 * objeto a string como de string al objeto, siguiendo el ejemplo visto
	 * en clase y reemplazando simplemente lo necesario.
	 * 
	 * Al correr esta clase creara ambos archivos y solo deberá cambiar la
	 * extensión .txt por .java y colocarla en el proyecto
	 */
	
	public static void main(String[] args) {
		
		// Creación del primer converter
		String clase = "Warehouse"; // Cambiar por la clase que se desee
		String firstConverterName = "StringTo" + clase + "Converter.txt";
		String firstConverterContent = "package converters;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.core.convert.converter.Converter;\nimport org.springframework.stereotype.Component;\nimport org.springframework.transaction.annotation.Transactional;\nimport repositories.ConsumerRepository;\nimport domain.Consumer;\n@Component\n@Transactional\npublic class StringToConsumerConverter implements Converter<String, Consumer> {\n\n	@Autowired\n	ConsumerRepository consumerRepository;\n\n	@Override\n	public Consumer convert(String text) {\n		Consumer result;\n		int id;\n\n		try {\n			id = Integer.valueOf(text);\n			result = consumerRepository.findOne(id);\n		} catch (Throwable oops) {\n			throw new IllegalArgumentException(oops);\n		}\n\n		return result;\n	}\n\n}";
		File converterFolder = new File(
				"C:\\Documents and Settings\\Student\\Desktop"); // Dirección
																	// por
																	// defecto
																	// de la
																	// maquina
																	// virtual
																	// de DP de
																	// alumno,
																	// cambiar
																	// si se
																	// desea
		File converter1 = new File(
				"C:\\Documents and Settings\\Student\\Desktop",
				firstConverterName);
		firstConverterContent = firstConverterContent.replaceAll("consumer",
				clase.toLowerCase());
		firstConverterContent = firstConverterContent.replaceAll("Consumer",
				clase);

		String[] content1 = firstConverterContent.split("\n");
		if (!converterFolder.exists()) {
			converterFolder.mkdir();
		}
		try {
			if (converter1.exists()) {
				converter1.delete();
			}
			converter1.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw;
		try {
			fw = new FileWriter(converter1, true);
			PrintWriter pw = new PrintWriter(fw);
			for (String s : content1) {
				pw.println(s);
			}
			pw.flush();

			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(firstConverterName + " creado con éxito.");
		// Creación del segundo converter
		String secondConverterName = clase + "ToStringConverter.txt";
		String secondConverterContent = "package converters;\n\nimport org.springframework.core.convert.converter.Converter;\nimport org.springframework.stereotype.Component;\nimport org.springframework.transaction.annotation.Transactional;\n\nimport domain.Consumer;\n\n@Component\n@Transactional\npublic class ConsumerToStringConverter implements Converter<Consumer, String> {\n\n	@Override\n	public String convert(Consumer consumer) {\n		String result;\n\n		if (consumer == null)\n			result = null;\n		else\n			result = String.valueOf(consumer.getId());\n\n		return result;\n	}\n\n}";

		File converter2 = new File(
				"C:\\Documents and Settings\\Student\\Desktop",
				secondConverterName);
		secondConverterContent = secondConverterContent.replaceAll("consumer",
				clase.toLowerCase());
		secondConverterContent = secondConverterContent.replaceAll("Consumer",
				clase);

		String[] content2 = secondConverterContent.split("\n");
		if (!converterFolder.exists()) {
			converterFolder.mkdir();
		}
		try {
			if (converter2.exists()) {
				converter2.delete();
			}
			converter2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw2;
		try {
			fw2 = new FileWriter(converter2, true);
			PrintWriter pw2 = new PrintWriter(fw2);
			for (String s : content2) {
				pw2.println(s);
			}
			pw2.flush();

			pw2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(secondConverterName + " creado con éxito.");
	}

}
