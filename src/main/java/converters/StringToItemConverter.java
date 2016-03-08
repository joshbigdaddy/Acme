package converters;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ItemRepository;
import domain.Item;

@Component
@Transactional
public class StringToItemConverter implements Converter<String, Item> {

	@Autowired
	ItemRepository itemRepository;

	@Override
	public Item convert(String text) {
		Item result = null;
		String name;
		Collection<Item> items;
		try {
			name = String.valueOf(text);
			items = itemRepository.findAll();
			for(Item i: items){
			if(i.getName().equals(name)){
				result=i;
			}
			}
			/*
			id=Integer.valueOf(text);
			result = itemRepository.findOne(id);
			*/
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
