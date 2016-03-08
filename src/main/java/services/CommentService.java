package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Comment;
import domain.Consumer;
import domain.Item;

import repositories.CommentRepository;
import repositories.ConsumerRepository;
import repositories.ItemRepository;

@Service
@Transactional
public class CommentService {
		// Managed repository -----------------------------------------------------

		@Autowired
		private CommentRepository commentRepository;
		@Autowired
		private ConsumerRepository consumerRepository;
		@Autowired
		private ItemRepository itemRepository;
		@Autowired
		private AdministratorService administratorService;
		
		public Comment create() {
			Comment result;

			result = new Comment();		

			return result;
		}
		
		public Collection<Comment> findAll() {
			Collection<Comment> result;

			result = commentRepository.findAll();		
			Assert.notNull(result);
			
			return result;
		}

		public Comment findOne(int commentId) {
			Comment result;
			
			result = commentRepository.findOne(commentId);		

			return result;
		}
		
		public void save(Comment comment) {
			Assert.notNull(comment);
			commentRepository.save(comment);
		}	
	
		public void delete(Comment admin) {
			Assert.notNull(admin);
			Administrator administrator=administratorService.findByPrincipal();
			Assert.notNull(administrator);
			Consumer cons=admin.getConsumer();
			Item it=admin.getItem();
			commentRepository.delete(admin);
			cons.getComment().remove(admin);
			it.getComment().remove(admin);
			itemRepository.save(it);
			consumerRepository.save(cons);
			}
			
		}

