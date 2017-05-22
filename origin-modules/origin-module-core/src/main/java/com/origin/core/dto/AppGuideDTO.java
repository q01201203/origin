package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.IAppGuide;

/**
* 
*/
public class AppGuideDTO implements IAppGuide {


    /**  */
private Integer id;//;
    /**  */
private Date createDate;// = CURRENT_TIMESTAMP;
    /**  */
private Date updateDate;// = CURRENT_TIMESTAMP;
    /**  */
private String guideName;//;
    /**  */
private String guideContent;//;
    /**  */
private Integer guideType;//;
    /**  */
private Integer deleteFlag;// = Integer.valueOf(0);
public AppGuideDTO(){
}

public AppGuideDTO(Integer id){
	this.id = id;
}


public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
}

public Date getCreateDate() {
return this.createDate;
}

public void setCreateDate(Date createDate) {
this.createDate = createDate;
}

public Date getUpdateDate() {
return this.updateDate;
}

public void setUpdateDate(Date updateDate) {
this.updateDate = updateDate;
}

public String getGuideName() {
return this.guideName;
}

public void setGuideName(String guideName) {
this.guideName = guideName;
}

public String getGuideContent() {
return this.guideContent;
}

public void setGuideContent(String guideContent) {
this.guideContent = guideContent;
}

public Integer getGuideType() {
return this.guideType;
}

public void setGuideType(Integer guideType) {
this.guideType = guideType;
}

public Integer getDeleteFlag() {
return this.deleteFlag;
}

public void setDeleteFlag(Integer deleteFlag) {
this.deleteFlag = deleteFlag;
}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IAppGuide) {
			IAppGuide baseEntity = (IAppGuide) object;
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
		+",createDate="+this.getCreateDate()
		+",updateDate="+this.getUpdateDate()
		+",guideName="+this.getGuideName()
		+",guideContent="+this.getGuideContent()
		+",guideType="+this.getGuideType()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
