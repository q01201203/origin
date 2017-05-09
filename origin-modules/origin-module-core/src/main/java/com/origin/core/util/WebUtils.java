package com.origin.core.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web层相关的实用工具类
 */
public class WebUtils {
	/**
	 * 将请求参数封装为Map<br>
	 * request中的参数t1=1&t1=2&t2=3<br>
	 * 形成的map结构：<br>
	 * key=t1;value[0]=1,value[1]=2<br>
	 * key=t2;value[0]=3<br>
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap<String, String> getPraramsAsMap(final HttpServletRequest request) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Map map = request.getParameterMap();
		Iterator keyIterator = (Iterator) map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = ((String[]) (map.get(key)))[0];
			hashMap.put(key, value);
		}
		return hashMap;
	}
	 public static void writeToBrowser(String str, HttpServletResponse response)
	  {
	    response.setCharacterEncoding("utf-8");
	    try
	    {
	      PrintWriter out = response.getWriter();
	      out.print(str);
	      out.flush();
	    } catch (IOException e1) {
	      e1.printStackTrace();
	    }
	  }

	  public static void writeJsonToBrowser(String str, HttpServletResponse response)
	  {
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("application/json");
	    try
	    {
	      PrintWriter out = response.getWriter();
	      out.print(str);
	      out.flush();
	    } catch (IOException e1) {
	      e1.printStackTrace();
	    }
	  }
}
