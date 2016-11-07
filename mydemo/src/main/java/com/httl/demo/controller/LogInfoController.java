package com.httl.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.httl.demo.domain.LogInfo;
import com.httl.demo.domain.Page;
import com.httl.demo.util.web.WebTools;

/**
 * 日志 管理
 * @Description:TODO
 * @author liu hui
 * @time:2015-10-10 上午10:07:10
 */
@Controller
@RequestMapping("/page")
public class LogInfoController {

	/**
	 * 分页 查询日志列表
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="query")
	public String  queryLogInfo(@RequestParam(value="pageCurrent",defaultValue="1",required=false) String pageCurrent,
			@RequestParam(value="pageRows",defaultValue="5",required=false) String pageRows,Model model,HttpServletRequest request) throws Exception {
		
		Page<LogInfo> page = new Page<LogInfo>();
		page.setPageCurrent(Integer.parseInt(pageCurrent));
		page.setPageRows(Integer.parseInt(pageRows));
		
		page.setTotal(20);//总页数
		page.setRowSum(140);//总条数
		
		//用于 保存查询条件
		LogInfo logInfo=new LogInfo();
		Map <String,Object> params=WebTools.getParaMap(request);
		/*if(params.get("clientIP")!=null)
			logInfo.setClientIP(params.get("clientIP").toString());
		if(params.get("requestURL")!=null)
			logInfo.setRequestURL(params.get("requestURL").toString());
		if(params.get("responseCode")!=null)
			logInfo.setResponseCode(params.get("responseCode").toString());
		if(params.get("responseInfo")!=null)
			logInfo.setResponseInfo(params.get("responseInfo").toString());
		if(params.get("loginName")!=null)
			logInfo.getMember().setLoginName(params.get("loginName").toString());
		if(params.get("userName")!=null)
			logInfo.getMember().setUserName(params.get("userName").toString());*/
		
		//恩。假设在从数据库读取数据
		List<LogInfo> loList= new ArrayList<LogInfo>();
		for (int i=1; i<= page.getRowsCurrent();i++) {
			LogInfo log= new LogInfo();
			log.setId(page.getRowsCurrent()+page.getPageCurrent()+i+"");
			log.setClientIP("192.168.xxx."+log.getId());
			log.setRequestTime(new Date());
			log.setRequestURL("xxx/login");
			log.setResponseCode("xx11");
			log.setResponseTime(new Date());
			log.setResponseInfo("登录成功！");
			loList.add(log);
		}
		page.setRows(loList);
		
		//返回结果列表
		model.addAttribute("page", page);
		//返回  回显的查询条件
		model.addAttribute("logInfo", logInfo);
		return "/demo/view/log/logInfo-list";
	}
	
	/**
	 * 日志详情
	 * @return
	 */
	@RequestMapping(value="detail")
	public String  queryLogInfo(@RequestParam(value="logInfoID",defaultValue="",required=false) String logID,Model model)
	{
		LogInfo logInfo=new LogInfo();
		logInfo.setId(123+"");
		logInfo.setClientIP("127.0.0.1");
		logInfo.setLoginAccountId("1223");
		model.addAttribute("logInfo",logInfo);
		return "/demo/view/log/logInfo-detail";
	}
	
	
	public static void main(String[] args) {
		
	String str="{'header':{'clientMAC':'testcreateorder','loginAccountID':5688,'partyMemberId':'user11994'},";
		str=str+"'body':{'areaCode':'20618946','orderDetailList':[";
		str=str+"{'dispatchID':'user10040','orderNumber':'1','drugID':'e4def7b4-49dc-4b96-a122-20c05dd8fd23','remark':'222','code':'1005545'}]}}";
		
		System.out.println(str);
		RestTemplate rTemplate=new RestTemplate();
		//rTemplate.postForLocation(url, request)
	}
}
