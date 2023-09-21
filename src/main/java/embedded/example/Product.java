package embedded.example;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
  @Id
  public int id;

  @OneToMany(mappedBy = "product")
  public List<OrderItem> orderItems;

  public Product(int id) {
    this.id = id;
  }
}
