package com.origin.core.dto;


import com.origin.data.entity.IProduct;

/**
* 
*/
public class ProductDTO implements IProduct {


    /** 主键 */
private Integer id;//;
    /** 产品名称 */
private String name;//;
    /** 产品库存 */
private Integer stock;// = Integer.valueOf(0);
    /** 产品详细描述 */
private String description;//;
public ProductDTO(){
}

public ProductDTO(Integer id){
	this.id = id;
}


public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return this.name;
}

public void setName(String name) {
this.name = name;
}

public Integer getStock() {
return this.stock;
}

public void setStock(Integer stock) {
this.stock = stock;
}

public String getDescription() {
return this.description;
}

public void setDescription(String description) {
this.description = description;
}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IProduct) {
			IProduct baseEntity = (IProduct) object;
			if (this.getId() == null || baseEntity.getId() == null) {
				return false;
			} else {
				return (this.getId().equals(baseEntity.getId()));
			}
		}
		return false;
	}
	
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
	}
	public String toString() {
		return this.getClass().getName() + "["
		+",id="+this.getId()
		+",name="+this.getName()
		+",stock="+this.getStock()
		+",description="+this.getDescription()
		+"]";
	}
}
