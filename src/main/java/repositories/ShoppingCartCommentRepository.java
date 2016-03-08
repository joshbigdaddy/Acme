package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ShoppingCartComment;

@Repository
public interface ShoppingCartCommentRepository extends JpaRepository<ShoppingCartComment,Integer>{

}
