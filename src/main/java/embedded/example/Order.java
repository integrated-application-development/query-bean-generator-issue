package embedded.example;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Order {
  @Id
  public int id;

  @OneToMany(mappedBy = "order")
  public List<OrderItem> items;

  public Order(int id) {
    this.id = id;
  }
}
