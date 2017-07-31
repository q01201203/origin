package com.origin.core.exception;

import com.origin.common.model.mybatis.Result;
import com.origin.core.util.JsonUtil;
import com.origin.core.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring 全局异常处理 .自定义SimpleMappingExceptionResolver覆盖spring的SimpleMappingExceptionResolver.复写其doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)方法，通过修改该方法实现普通异常和ajax异常的处理
 */
public class CustomSimpleMappingExceptionHandler extends SimpleMappingExceptionResolver {

    //lic
    Logger log = LoggerFactory.getLogger(CustomSimpleMappingExceptionHandler.class);

    @Override
    protected ModelAndView doResolveException(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
        //lic on
        log.debug("renxinhua CustomSimpleMappingExceptionHandler");
        log.debug(ex.getMessage(),ex);
        //lic off
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ResponseBody body = handlerMethod.getMethodAnnotation(ResponseBody.class);
        // 判断有没有@ResponseBody的注解没有的话调用父方法
        if (body == null) {
            return super.doResolveException(request, response, handlerMethod, ex);
        }
        ModelAndView mv = new ModelAndView();
        response.setStatus(HttpStatus.OK.value()); // 设置状态码,注意这里不能设置成500，设成500JQuery不会出错误提示 并且不会有任何反应
        //lic modify
        Result ajaxResult = Result.createErrorResult();
        /*AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccess(false);*/
        if (ex instanceof BusinessException) {
            BusinessException e = (BusinessException) ex;
            String msg = e.getType() + "：<br/>" + e.getDetailMsg() + "(" + e.getSuggestionMsg() + ")";
            ajaxResult.setMessage(msg);
            //ajaxResult.setMsg(msg);
            // 已经封装过的异常
//            WebUtils.writeToBrowser(JsonUtil.object2Json(new JsonResultBean(JsonResultBean.FAULT, msg)), response);
        } else {
            ajaxResult.setMessage("Error");
        	//ajaxResult.setMsg("Error!");
            // 没有封装过的异常
            //WebUtils.writeToBrowser(JsonUtil.object2Json(new JsonResultBean(JsonResultBean.FAULT, "系统错误，请联系管理员！")), response);
        }
        WebUtils.writeToBrowser(JsonUtil.object2Json(ajaxResult), response);
        return mv;
    }
}