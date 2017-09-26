package mypacks.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name ="product")
public class Product 
{
	@Id
	@GeneratedValue
	@Column(name ="product_id")
	private int productId;
	
	@Column(name ="product_name")
	@NotEmpty(message="Please enter Product Name")
	private String productName;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id"/*,referencedColumnName="category_id"*/)
	//@NotNull(message="Please select Category")
	//@Valid
	private Category category;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="productType")
	private List<Item> item;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
