package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Item;
import domain.Stores;
import domain.Warehouse;

import repositories.StoresRepository;
import repositories.WarehouseRepository;

@Service
@Transactional
public class WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;
	@Autowired 
	private StoresRepository storesRepository;
	
//Supporting Services
	@Autowired
	private ItemService itemService;
	
//Constructor
	
	public WarehouseService(){
		super();
	}

	
	public Warehouse create(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		Warehouse w;
		w= new Warehouse();
		return w;
	}
	public Collection<Warehouse> showAll(){
		//Administrator administrator=administratorService.findByPrincipal();
		//Clerk clerk=clerkService.findByPrincipal();
		//Assert.isTrue(administrator!=null || clerk!=null);
		return warehouseRepository.findAll();
	}
	
	public List<Warehouse> findAll(){
		return warehouseRepository.findAll();
	}

	public void save(Warehouse w){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		warehouseRepository.save(w);
	}
	public void delete(Warehouse w){
		//Administrator administrator=administratorService.findByPrincipal();
		//Assert.notNull(administrator);
		for(Stores i : storesRepository.findAll()){
			if(i.getWarehouse().equals(w)){
		storesRepository.delete(i);
			}
		}
		warehouseRepository.delete(w);
		
	}
	
	public Warehouse findOne(int warehouseId) {
		Warehouse result;

		result = warehouseRepository.findOne(warehouseId);

		return result;
	}
	//Other bussines methods
	public void changeCuantityOfStoredItem (int id, Item i, Integer quantity){
		Warehouse warehouse=warehouseRepository.findById(id);
		Stores stores=null;
		for(Stores s: warehouse.getStores()){
			if(s.getItem().equals(i)){
				s.setUnits(quantity);
				stores=s;
				break;
			}
		}
		storesRepository.save(stores);
	}
	public void withdrawCuantityOfStoredItem (int id, String sku, Integer quantity){
		Item i=itemService.findOneBySKU(sku);
		Warehouse warehouse=warehouseRepository.findById(id);
		Stores stores=null;
		for(Stores s: warehouse.getStores()){
			if(s.getItem().equals(i)){
				int aux=s.getUnits()-quantity;
				if(aux<0){
					aux=0;
				}
				s.setUnits(aux);
				stores=s;
				break;
			}
		}
		storesRepository.save(stores);
	}
	public Collection<Warehouse> getWarehouseThatHaveItem(Item item){
		Collection<Warehouse> warehouses;
		warehouses=findAll();
		List<Warehouse> result=new ArrayList<Warehouse>();
		for(Warehouse w:warehouses){
			for(Stores s:w.getStores()){
				if(s.getItem().equals(item)&&s.getUnits()>0){
					result.add(w);
				}
			}
		}
		return result;
	}

}