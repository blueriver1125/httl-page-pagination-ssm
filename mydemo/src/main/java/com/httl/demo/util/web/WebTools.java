/**
 * 
 */
package com.httl.demo.util.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;

/**
 * web工具方法
 * @author longhp
 * @version 1.0
 */
public class WebTools {
	
	/**
	 * 获取客户端IP地址
	 * @param HttpServletRequest request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	
	/**
	 * 获取客户端IP地址
	 * @param ServerHttpRequest request
	 * @return
	 */
	public static String getRemoteHost(ServerHttpRequest request) {
	
		HttpHeaders header = request.getHeaders();
		String ip = header.getFirst("x-forwarded-for"); 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = header.getFirst("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = header.getFirst("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddress().getAddress().getHostAddress();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	/**
	 * 输出内容
	 * @param response
	 * @param content
	 */
	public static void printWriter(HttpServletResponse response, String content) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 查询时  获取（查询条件） 
	 * @param request
	 * @return
	 */
	 public static Map<String, Object> getParaMap(HttpServletRequest request)
	 {
		 Map <String,String []> reqmap=request.getParameterMap();
		 Map<String,Object> retmapMap=new HashMap<String, Object>();
		 for (Entry<String,String []> ea: reqmap.entrySet()) {
					retmapMap.put(ea.getKey(),ea.getValue()[0]);
			}
		 return retmapMap;
	 }
}
