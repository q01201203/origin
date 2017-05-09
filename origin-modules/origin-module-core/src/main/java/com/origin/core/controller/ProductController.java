package com.origin.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.origin.common.dto.AjaxResult;
import com.origin.core.dto.ProductDTO;
import com.origin.core.service.ProductService;
import com.origin.core.util.Constants;
import com.origin.data.entity.IProduct;

/**
 * Controller
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	Logger log = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String stock = request.getParameter("stock");
		String description = request.getParameter("description");
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
		IProduct params = new ProductDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(name)){
			params.setName(name);
		}
		if(StringUtils.isNotBlank(stock)){
			params.setStock( Integer.parseInt(stock));
		}
		if(StringUtils.isNotBlank(description)){
			params.setDescription(description);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IProduct> products = productService.find(params);
    	model.addAttribute("products", products);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "product列表");

    	return "product/product_list";
	}
    @RequestMapping("/dialog/product_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		IProduct product = productService.findById(Integer.parseInt(id));
    		model.addAttribute("product", product);

		}
    	return "product/dialog/product_edit";
    }
    @RequestMapping("/ajax/update")
    @ResponseBody
    public AjaxResult ajaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);

    	try {
    		String id = request.getParameter("id");
    		String name = request.getParameter("name");
    		String stock = request.getParameter("stock");
    		String description = request.getParameter("description");
    		IProduct product = new ProductDTO();
    		if(StringUtils.isNotBlank(id)){
				product = productService.findById(Integer.parseInt(id));
    		}else{
				product = new ProductDTO();
    		}
			product.setName(name);
			product.setStock( Integer.parseInt(stock));
			product.setDescription(description);
    		if(StringUtils.isNotBlank(id)){
				productService.update(product);
    		}else{
				productService.save(product);
    		}

    		ajaxResult.setSuccess(true);

		} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}

    	return ajaxResult;
    }
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public AjaxResult ajaxDelete(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);
    	try {
    		String id = request.getParameter("id");
			productService.delete(Integer.parseInt(id));
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}