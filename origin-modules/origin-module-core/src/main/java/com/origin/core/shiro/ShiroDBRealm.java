package com.origin.core.shiro;




import com.origin.core.service.ResourceService;
import com.origin.core.service.RoleService;
import com.origin.core.service.UserService;
import com.origin.core.util.WebHelper;
import com.origin.data.entity.IResource;
import com.origin.data.entity.IRole;
import com.origin.data.entity.IUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
public class ShiroDBRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("renxinhua doGetAuthorizationInfo");
		String userName = (String) principals.getPrimaryPrincipal();
		IUser user = userService.findUserByName(userName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<IRole> roleList=roleService.findByUser(user.getId());

		Set<String> permissionSet = new HashSet<String>();
		for (IRole role : roleList) {
			if(StringUtils.isNotBlank(role.getRoleName())){
				info.addRole(role.getRoleName());
				List<IResource> resources = roleService.getResources(role.getId());
				if(resources!=null && !resources.isEmpty()){
					for(IResource r : resources){
						if(StringUtils.isNotBlank(r.getUrl())){
							permissionSet.add(r.getUrl());
						}
					}
				}
			}
		}
		
		info.addStringPermissions(permissionSet);

		return info;
	}

	/**
	 * 验证当前用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		System.out.println("renxinhua doGetAuthenticationInfo");
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        
        if(StringUtils.isEmpty(token.getUsername())){
        	return null;
        }
        
        IUser user = userService.findUserByName(token.getUsername());
        if(user != null){
        	
        	if(user.getStatus() == IUser.STATUS_NO){
        		throw new LockedAccountException();
        	}
        	
        	AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        	
        	setSession(WebHelper.SESSION_LOGIN_USER, user);
        	
        	initMenu(user.getId());
        	
        	return authcInfo;
        }
        
        return null;
	}
	
	private void initMenu(Integer userId){

		//菜单权限
		List<IResource> menuResources = resourceService.findAllMenu();
		
		List<IResource> hasResource = new ArrayList<IResource>();

		List<IRole> roleList=roleService.findByUser(userId);
		Map<Integer, Object> map = resourceService.findResourceMap(userId);
		
		if(menuResources != null && !menuResources.isEmpty()){
			for(IResource resource : menuResources){
				IResource retRes = hasResource(resource, map);
				if(retRes != null){
					hasResource.add(retRes);
				}
			}
		}
		
		setSession(WebHelper.SESSION_MENU_RESOURCE, hasResource);
		
	}
	
	private IResource hasResource(IResource resource, Map<Integer, Object> map){
		if(map.containsKey(resource.getId())){
			
			List<IResource> chResources = resourceService.findChildren(resource.getId());
			List<IResource> hasChResources = new ArrayList<IResource>();
			if(chResources != null && !chResources.isEmpty()){
				for(IResource chRes : chResources){
					IResource retRes = hasResource(chRes, map);
					if(retRes != null){
						hasChResources.add(retRes);
					}
				}
			}
			resource.setChildren(hasChResources);
			return resource;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	private void setSession(Object key, Object value){
		Subject subject = SecurityUtils.getSubject();
		if(subject != null){
			Session session = subject.getSession();
			if(session != null){
				session.setAttribute(key, value);
			}
		}
	}

}
