package newdev.project01.configs;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import newdev.project01.entities.Category;
import newdev.project01.entities.Order;
import newdev.project01.entities.Product;
import newdev.project01.entities.User;
import newdev.project01.enums.OrderStatus;
import newdev.project01.repositories.CategoryRepository;
import newdev.project01.repositories.OrderRepository;
import newdev.project01.repositories.ProductRepository;
import newdev.project01.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category("Eletrônicos");
		Category cat2 = new Category("Livros");
		Category cat3 = new Category("Computadores");
		Category cat4 = new Category("Smartphones");
		
		Product p1 = new Product("The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product("Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product("Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product("PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product("Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().addAll(Arrays.asList(cat1, cat3));
		p3.getCategories().addAll(Arrays.asList(cat3, cat4));
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		
		
		User u1 = new User("Maria", "maria@gmail.com", "988888888", "123456");
		User u2 = new User("Alex", "alex@gmail.com", "9765479999", "12385246");
		User u3 = new User("Emma", "emma@gmail.com", "978235799", "12348965");
		User u4 = new User("David", "david@gmail.com", "977777777", "12378965");
		
		Order o1 = new Order(Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAGO, u1);
		Order o2 = new Order(Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.AGUARDANDO_PAGAMENTO, u2);
		Order o3 = new Order(Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.ENVIADO, u1);
		Order o4 = new Order(Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.PAGO, u3);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
		
	}
	

}
