package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ShoppingCartItem;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Integer>{

}
