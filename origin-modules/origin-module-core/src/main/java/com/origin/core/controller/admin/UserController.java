package com.origin.core.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.common.util.ExcelUtils;
import com.origin.common.util.Md5Util;
import com.origin.core.dto.*;
import com.origin.core.service.*;
import com.origin.core.util.Constants;
import com.origin.core.util.WebHelper;
import com.origin.data.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 用户管理Controller
 * @author Jeff Xu
 *
 */
@Controller
@RequestMapping("/user")
@ApiIgnore
public class UserController{
	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String userName = request.getParameter("userName");
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");
		
		int currentPage = 1;
		int pageSize = Constants.DEFAULT_PAGESIZE;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		UserDTO params = new UserDTO();
		if(StringUtils.isNotBlank(userName)){
			params.setUsername(userName);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IUser> list = userService.findUsers(params);
		PageInfo<IUser> page = new PageInfo(list);
		model.addAttribute("page", page);
		model.addAttribute("userQueryDTO", params);
		model.addAttribute(Constants.MENU_NAME, Constants.MENU_USER_LIST);
		
		return "user/user_list";
	}
	
	/**
	 * 导出用户数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/export")
	public String exportOrder(HttpServletRequest request, HttpServletResponse response){	
		String userName = request.getParameter("userName");
		UserDTO params = new UserDTO();
		if(StringUtils.isNotBlank(userName)){
			params.setUsername(userName);
		}
		List<IUser> userList = this.userService.findUsers(params);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, String> headNameMap = new LinkedHashMap<String, String>();
		headNameMap.put("roleName", "角色");
		headNameMap.put("userName", "账号");
		headNameMap.put("realName", "姓名");
		headNameMap.put("mobile", "电话号码");
		headNameMap.put("createDate", "创建时间");
		headNameMap.put("status", "状态");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(userList != null && userList.size() > 0){
			for(IUser user : userList){
				String statusName = "正常";
				String createDate = "";
				String roleName = "";
				if(StringUtils.isNotBlank(user.getDeleteFlag()) && user.getDeleteFlag().equals(IUser.DELETE_FLAG_DELETED)){
					statusName = "删除";
				}
				
				if(user.getCreateDate() != null){
					createDate = sdf.format(user.getCreateDate());
				}
				
				List<IRole> roleSet = roleService.findByUser(user.getId());
				if(roleSet != null && roleSet.size() > 0){
					for(IRole r:roleSet){
						roleName +=r.getName()+" ";
					}
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("roleName", roleName);
				map.put("userName", user.getUsername());
				map.put("realName", user.getRealName());
				map.put("mobile", user.getMobile());
				map.put("createDate", createDate);
				map.put("status", statusName);
				list.add(map);
			}
		}
		try {
			ExcelUtils.exportXlsx(response, "用户数据", headNameMap, list);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跳转到用户编辑页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/dialog/edit")
	public String dialogEdit(HttpServletRequest request, Model model){
		
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			IUser user = userService.findById(Integer.parseInt(id));
			List<IRole> roles = roleService.findByUser(user.getId());
			user.setRoles(roles);
			model.addAttribute("user", user);
			
		}
		
		RoleDTO params = new RoleDTO();
		params.setDeleteFlag("0");
		List<IRole> roles = roleService.findByParams(params);
		model.addAttribute("roles", roles);
		
		return "user/dialog/user_edit";
	}
	
	/**
	 * 用户保存操作
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/save")
	@ResponseBody
	public AjaxResult ajaxSave(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		try {
			
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realName = request.getParameter("realName");
			String mobile = request.getParameter("mobile");
			String[] roleIds = request.getParameterValues("roleId");
			
			
			IUser user = null;
			if(StringUtils.isNotBlank(id)){
				user = userService.findById(Integer.parseInt(id));
			}else{
				user = new UserDTO();
				user.setUsername(StringUtils.trim(username));
				user.setStatus(IUser.STATUS_YES);
			}
			
			if(StringUtils.isNotBlank(password)){
				user.setPassword(Md5Util.generatePassword(password));
			}
			
			user.setRealName(StringUtils.trim(realName));
			user.setMobile(StringUtils.trim(mobile));
			
			
			if(StringUtils.isNotBlank(id)){
				userService.update(user);
			}else{
				user.setType(0);
				//lic
				user.prePersist();
				userService.save(user);
			}
			
			List<IUserRole> set = new ArrayList<IUserRole>();
			if(roleIds != null){
				for(String roleId : roleIds){
					/*IRole role = roleService.findById(Integer.parseInt(roleId));//.find(roleId);
					if(role != null){
						set.add(role);
					}*/
					IUserRole userRole = new UserRoleDTO();
					userRole.setRoleId(Integer.parseInt(roleId));
					userRole.setUserId(user.getId());
					set.add(userRole);
				}
			}
			if(set.size()>0){
				IUserRole del = new UserRoleDTO();
				del.setUserId(user.getId());
				userRoleService.delete(del);
				userRoleService.saveBatch(set);
			}
			ajaxResult.setSuccess(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ajaxResult;
	}
	
	
	/**
	 * 修改用户状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/upd/status")
	@ResponseBody
	public AjaxResult ajaxUpdStatus(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		try {
			String[] ids = request.getParameterValues("ids");
			String status = request.getParameter("status");
			Integer array[] = new Integer[ids.length];  
			for(int i=0;i<ids.length;i++)
			    array[i]=Integer.parseInt(ids[i]);   
			userService.updateStatus(array, Integer.parseInt(status));
			
			ajaxResult.setSuccess(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ajaxResult;
	}
	
	/**
	 * 皮肤列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/skin/list")
	public String skinList(HttpServletRequest request,Model model){
		model.addAttribute(Constants.MENU_NAME, Constants.MENU_UPDATE_SKIN);
		return "/user/skin_list";
	}
	
	/**
	 * 修改皮肤
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/upd/skin")
	@ResponseBody
	public AjaxResult ajaxUpdSkin(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		try {
			String currentSkin = request.getParameter("skin");
			IUser user = (IUser)request.getSession().getAttribute(WebHelper.SESSION_LOGIN_USER);
			if(user != null){
				user.setCurrentSkin(currentSkin);
				this.userService.update(user);
				request.getSession().setAttribute(WebHelper.SESSION_LOGIN_USER, user);
				ajaxResult.setSuccess(true);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxResult;
	}
	
	/**
	 * 校验用户是否存在
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/validator/username")
	@ResponseBody
	public Map<String, Object> ajaxValidatorUsername(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String username = request.getParameter("username");
		
		UserDTO params = new UserDTO();
		if(StringUtils.isNotBlank(username)){
			params.setUsername(StringUtils.trim(username));
		}
		
		List<IUser> users = userService.findUsers(params);
		if(users != null && !users.isEmpty()){
			map.put("error", "账号已经存在");
		}else{
			map.put("ok", "");
		}
		
		return map;
	} 
	
	/**
	 * 角色列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/role_list")
	public String roleList(HttpServletRequest request, Model model){		
		
		String name = request.getParameter("name");
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");
		
		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		RoleDTO params = new RoleDTO();
		if(StringUtils.isNotBlank(name)){
			params.setName(name);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IRole> roles = roleService.findByParams(params);
		PageInfo<IRole> page = new PageInfo(roles);
		model.addAttribute("page", page);
		model.addAttribute("roleQueryDTO", params);
		model.addAttribute(Constants.MENU_NAME, Constants.MENU_ROLE_LIST);
		
		return "user/role_list";
	}
	
	/**
	 * 跳转到角色编辑页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/dialog/role_edit")
	public String dialogRoleEdit(HttpServletRequest request, Model model){
		
		List<Map<String, Object>> resources = resourceService.findResourceMap();
		
		String roleId = request.getParameter("id");
		if(StringUtils.isNotBlank(roleId)){
			IRole role = roleService.findById(Integer.parseInt(roleId));//.find(roleId);
			List<IResource> rs = roleService.getResources(role.getId());
			role.setResources(rs);
			model.addAttribute("role", role);
			
			List<IResource> set = role.getResources();
			if(set != null && !set.isEmpty()){
				for(int i=0,size=resources.size();i<size;i++){
					Map<String, Object> map = resources.get(i);
					String id = map.get("id").toString();
					for(IResource resource : set){
						if(id.equals(resource.getId().toString())){
							map.put("checked", true);
							map.put("open", true);
							break;
						}
					}
				}
			}
			
		}
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String resourceJson = objectMapper.writeValueAsString(resources);
			model.addAttribute("resourceJson", resourceJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "user/dialog/role_edit";
	}
	
	/**
	 * 保存角色
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/save_role")
	@ResponseBody
	public AjaxResult ajaxSaveRole(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String[] rescoureIds = request.getParameterValues("rescoureIds");
		
		
		IRole role = null;
		if(StringUtils.isNotBlank(id)){
			role = roleService.findById(Integer.parseInt(id));//.get(id);
		}else{
			role = new RoleDTO();
		}
		
		role.setName(StringUtils.trim(name));
		role.setDescription(StringUtils.trim(description));
		
		
		if(role.getId()!=null){
			roleService.update(role);
		}else{
			roleService.save(role);
		}
		List<IRoleResource> resources = new ArrayList<IRoleResource>();
		if(rescoureIds != null){
			for(String rId : rescoureIds){
				IRoleResource irr = new RoleResourceDTO();
				irr.setResourcesId(Integer.parseInt(rId));
				irr.setRoleId(role.getId());
				resources.add(irr);
			}
		}
		if(resources.size()>0){
			IRoleResource delete = new RoleResourceDTO();
			delete.setRoleId(role.getId());
			roleResourceService.delete(delete);
			roleResourceService.saveBatch(resources);
		}
		
		ajaxResult.setSuccess(true);
		
		return ajaxResult;
	}
		

	/**
	 * 角色删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/upd_role/delete_flag")
	@ResponseBody
	public AjaxResult ajaxUpdRoleDeleteFlag(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		try {
			String[] ids = request.getParameterValues("ids");
			String deleteFlag = request.getParameter("deleteFlag");
			Integer[] its = new Integer[ids.length];
			for(int i=0;i<ids.length;i++){
				its[i]=Integer.parseInt(ids[i]);
			}
			roleService.updateDeleteFlag(its, deleteFlag);
			
			ajaxResult.setSuccess(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ajaxResult;
	}
	
	/**
	 * 跳转到菜单列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/menu_list")
	public String menuList(HttpServletRequest request, Model model){
		String name = request.getParameter("name");
		ResourceDTO params = new ResourceDTO();
		if(StringUtils.isNotBlank(name)){
			params.setName("%" + StringUtils.trim(name) + "%");
		}
		model.addAttribute("name", name);
		
		List<IResource> resources = resourceService.findByParams(params);
		model.addAttribute("resources", resources);
		model.addAttribute(Constants.MENU_NAME, Constants.MENU_NAME_LIST);
		
		return "user/menu_list";
	}
	
	/**
	 * 逻辑操作菜单状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/upd_menu/delete_flag")
	@ResponseBody
	public AjaxResult ajaxUpdMenuDeleteFlag(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		try {
			String[] ids = request.getParameterValues("ids");
			String deleteFlag = request.getParameter("deleteFlag");
			Integer[] its = new Integer[ids.length];
			for(int i=0;i<ids.length;i++){
				its[i] = Integer.parseInt(ids[i]);
			}
			resourceService.updateDeleteFlag(its, deleteFlag);
			
			ajaxResult.setSuccess(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ajaxResult;
	}
	
	/**
	 * 跳转到菜单编辑页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/dialog/menu_edit")
	public String dialogMenuEdit(HttpServletRequest request, Model model){
		
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			IResource resource = resourceService.findById(Integer.parseInt(id));
			IResource parent = resourceService.findById(resource.getParentId());
			resource.setParent(parent);
			model.addAttribute("resource", resource);
		}
		
		List<IResource> modelResources = resourceService.getRootResourceList();
		model.addAttribute("modelResources", modelResources);
		
		return "user/dialog/menu_edit";
	}
	
	/**
	 * 保存菜单
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/save_menu")
	@ResponseBody
	public AjaxResult ajaxSaveMenu(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			String parentId = request.getParameter("parentId");
			String url = request.getParameter("url");
			String icon = request.getParameter("icon");
			String orderNoStr = request.getParameter("orderNo");
			Integer orderNo = null;
			if(StringUtils.isNotBlank(orderNoStr)){
				orderNo = Integer.parseInt(orderNoStr);
			}
			
			IResource resource = null;
			if(StringUtils.isNotBlank(id)){
				resource = resourceService.findById(Integer.parseInt(id));
			}else{
				resource = new ResourceDTO();
			}
			resource.setName(StringUtils.trim(name));
			resource.setType(type);
			IResource parentResource = null;
			Integer pid = null;
			if(StringUtils.isNotBlank(parentId) && "page".equals(type)){
				parentResource = resourceService.findById(Integer.parseInt(parentId));
				pid = parentResource.getId();
			}
			resource.setParentId(pid);
			resource.setUrl(StringUtils.trim(url));
			resource.setIcon(StringUtils.trim(icon));
			resource.setOrderNo(orderNo);
			if(resource.getId() == null){
				resourceService.save(resource);
			}else{
				resourceService.update(resource);
			}
			
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ajaxResult;
	}
	
	/**
	 * 跳转到修改密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/update_pwd")
	public String updatePwd(HttpServletRequest request, Model model){
		
		model.addAttribute(Constants.MENU_NAME, Constants.MENU_UPDATE_PWD);
		
		return "user/update_pwd";
	}
	
	/**
	 * 保存密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/save_pwd")
	@ResponseBody
	public AjaxResult ajaxSavePwd(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		
		try {
			
			IUser user = WebHelper.getUser(request);
			
			String oldPwd = request.getParameter("oldPwd");
			String pwd = request.getParameter("pwd");
			
			user = userService.findById(user.getId());
			if(Md5Util.generatePassword(oldPwd).equals(user.getPassword())){
				
				user.setPassword(Md5Util.generatePassword(pwd));
				userService.update(user);
				
				ajaxResult.setSuccess(true);
			}else{
				ajaxResult.setMsg("原始密码输入不正确");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setMsg("修改失败");
		}
		
		return ajaxResult;
	}
	public static void main(String[] args) {
		/*Long i = 1L;
		String a = (String) i;
		log.debug(a);*/
	}
}
