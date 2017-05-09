package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

public interface IResourceDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
	public List<T> findEnabledResources();
	public List<T> findChildren(Integer parendId);
}
