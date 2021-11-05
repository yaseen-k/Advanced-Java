package entities.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
	List<ProductWithId> productsList;
	
	public ProductRepository() {
		productsList = new ArrayList<ProductWithId>();
		
		List<String> sub = new ArrayList<String>();
		sub.add("Mobile Accessories");
		sub.add("Headphones & Headsets");
		sub.add("Bluetooth Earphones");
		sub.add("boAt");
		
		ProductWithId p1 = new ProductWithId();
		p1.setProductId(1);
		p1.setName("boAt Airdopes");
		p1.setPrice(899);
		p1.setDetails("BOAT Airdopes 131 - Wireless Earbuds");
		p1.setCategory("Electronics");
		p1.setSubcategory(sub);
		
		productsList.add(p1);
		
		List<String> sub1 = new ArrayList<String>();
		sub1.add("Mobile Accessories");
		sub1.add("Mobile");
		sub1.add("boAt");
		sub1.add("Headset");
		
		ProductWithId p2 = new ProductWithId();
		p2.setProductId(2);
		p2.setName("boAt Earphones");
		p2.setPrice(650);
		p2.setDetails("boAt Bassheads 100 Wired Headset  (Black, In the Ear)");
		p2.setCategory("Electronics");
		p2.setSubcategory(sub1);
		
		productsList.add(p2);
	}
	
	public ProductWithId addProduct(Product p) {
		ProductWithId product = new ProductWithId();
		product.setProductId(productsList.size()+1);
		product.setName(p.getName());
		product.setPrice(p.getPrice());
		product.setDetails(p.getDetails());
		product.setCategory(p.getCategory());
		product.setSubcategory(p.getSubcategory());
		
		productsList.add(product);
		
		return product;
	}
	
	public void modifyProduct(ProductWithId p) {
		for(ProductWithId product : productsList) {
			if(p.getProductId() == product.getProductId()) {
				product.setName(p.getName());
				product.setPrice(p.getPrice());
				product.setDetails(p.getDetails());
				product.setCategory(p.getCategory());
				product.setSubcategory(p.getSubcategory());
			}
		}
	}
	
	public ProductWithId getProductById(int pid) {
		for(ProductWithId p : productsList) {
			if(p.getProductId() == pid) {
				return p;
			}
		}
		
		return null;
	}
	
	public List<ProductWithId> allProductsByCategory(String category) {
		List<ProductWithId> sameCatProd = new ArrayList<ProductWithId>();
		for(ProductWithId p : productsList) {
			if(p.getCategory().equals(category)) {
				sameCatProd.add(p);
			}
		}
		
		return sameCatProd;
	}

	public List<ProductWithId> getProductBySearching(String category) {
		List<ProductWithId> sameCatProd = new ArrayList<ProductWithId>();
		for(ProductWithId p : productsList) {
			if(p.getName().indexOf(category) != -1) {
				sameCatProd.add(p);
			}
		}
		
		return sameCatProd;
	}
	
	public List<ProductWithId> getFilteredProduct(String category, int minPrice, int maxPrice) {
		List<ProductWithId> filteredProduct = new ArrayList<ProductWithId>();
		for(ProductWithId p : productsList) {
			if(p.getCategory().equals(category)) {
				filteredProduct.add(p);
			}
		}
		
		List<ProductWithId> temp = filteredProduct;
		
		for(ProductWithId p : productsList) {
			if(p.getPrice() < minPrice || p.getPrice() > maxPrice)
				temp.remove(p);
		}
		
		return temp;
	}
}
