package com.origin.data.mybatis.entity;


import com.origin.data.entity.ITest;

/**
 * 
 */
public class Test implements ITest {

	private static final long serialVersionUID = 96314928860631871L;
	
	/**  */
	private Integer id;//;
	/**  */
	private String sss;//;
	/**  */
	private String df;//;
	/**  */
	private String dfs;//;
	
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSss() {
		return this.sss;
	}
	
	public void setSss(String sss) {
		this.sss = sss;
	}
	
	public String getDf() {
		return this.df;
	}
	
	public void setDf(String df) {
		this.df = df;
	}
	
	public String getDfs() {
		return this.dfs;
	}
	
	public void setDfs(String dfs) {
		this.dfs = dfs;
	}
	
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof ITest) {
			ITest baseEntity = (ITest) object;
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
		+",sss="+this.getSss()
		+",df="+this.getDf()
		+",dfs="+this.getDfs()
		+"]";
	}
}
