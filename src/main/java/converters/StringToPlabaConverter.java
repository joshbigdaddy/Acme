package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PlabaRepository;
import domain.Plaba;

@Component
@Transactional
public class StringToPlabaConverter implements Converter<String, Plaba> {

	@Autowired
	PlabaRepository plabaRepository;

	@Override
	public Plaba convert(String text) {
		Plaba result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = plabaRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
