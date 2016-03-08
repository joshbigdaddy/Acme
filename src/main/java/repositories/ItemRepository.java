package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer>{


@Query("select i from Item i where i.unitsSold= (select max(i.unitsSold) from Item i)")
Collection<Item> bestSellingItem();


@Query("select i from Item i where i.unitsSold= (select min(i.unitsSold) from Item i)")
Collection<Item> worstSellingItem();

@Query("select i from Item i where i.comment.size=(select max(i.comment.size) from Item i)")
Collection<Item> itemWithMoreComments();
@Query("select i from Item i order by i.category")
Collection<Item> itemsGroupByCategory();

}

