package com.origin.data.entity;

import java.io.Serializable;

/**
* 
*/
public interface ITest extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 String getSss();

  void setSss(String sss);

 String getDf();

  void setDf(String df);

 String getDfs();

  void setDfs(String dfs);

}
