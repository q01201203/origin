package com.origin.data.entity;

import java.io.Serializable;

/**
* 
*/
public interface IProduct extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 String getName();

  void setName(String name);

 Integer getStock();

  void setStock(Integer stock);

 String getDescription();

  void setDescription(String description);

}