package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Plaba;


	@Component
	@Transactional
	public class PlabaToStringConverter implements Converter<Plaba, String> {

		public String convert(Plaba  plaba) {
			String result;

			if (plaba == null)
				result = null;
			else
				result = String.valueOf(plaba.getId());

			return result;
		}

	}


