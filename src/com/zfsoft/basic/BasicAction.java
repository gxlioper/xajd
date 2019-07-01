package com.zfsoft.basic;

import com.mchange.v1.util.ArrayUtils;
import com.zfsoft.utils.Encrypt;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.LogInfo;
import com.zfsoft.xgxt.base.log.LogService;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.IPRequest;
import com.zfsoft.xgxt.xtgl.LoginService;
import common.exception.SystemException;
import eu.bitwalker.useragentutils.UserAgent;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchForm;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.comm.search.SearchTjByRoles;
import xgxt.form.User;
import xgxt.utils.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

public abstract class BasicAction extends DispatchAction{
	//TODO 测试时需要
//	BasicService service;
	//页面属性前缀
	public static String PRIFIX_SAVE = "save_";//需要保存到数据库的字段前缀
	public static String PRIFIX_PRIMARY_KEY = "primarykey_";//页面显示是主键的字段前缀
	//操作信息
	public static String MESSAGE_SUCCESS = "操作成功！";
	public static String MESSAGE_FAIL = "操作失败！";
	private static String MESSAGE_NOT_NULL = "必填字段必须填写内容！";
	private static String MESSAGE_NO_TABLE = "无法获取将要操作的数据库表名称！";
	public static String MESSAGE_EXISTS_ERROR = "增加的数据已经存在！";
	private static String MESSAGE_TOO_LARGE_ERROR = "插入的信息长度过长！";
	private static String MESSAGE_CLIENT_COLUMN_ERROR = "您的自定义字段有误！";
	private static String MESSAGE_UPDATE_EXISTS = "修改后的数据在数据库中已存在！";
	private static String MESSAGE_UPDATE_NOT_EXISTS = "您要修改的数据在数据库中不存在！";
	private static String MESSAGE_COLUM_DISUNION = "批量操作中要插入的字段不统一！";
	private static String MESSAGE_NO_DEL_CONDITION = "请选择要操作的数据！";
	private static String MESSAGE_SCARCITY_AUDITING_DATA = "审核字段或审核结果为空！";
	private static String MESSAGE_CANNOT_PAGINATION = "你的Form不可分页！";
	private static String MESSAGE_QUERY_DATA_NULL = "未查询到任何数据！";
	private static String MESSAGE_COLUMN_NOT_EXISTS = "部分页面属性不属于表的字段，无法保存！";
	public static final String QUERY = "query";
	public static final String VIEW = "view";
	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String DEL = "del";
	public static final String EXP = "exp";
	public static final String PLSH = "plsh";
	public static final String QXSH = "qxsh";
	public static final String SAVE_SUCCESS = "保存成功!";
	public static final String SAVE_FAIL = "保存失败!";
	public static final String DEL_SUCCESS = "删除成功!";
	public static final String DEL_FAIL = "删除失败!";
	public static final String SQ_SUCCESS = "申请成功!";
	public static final String SQ_FAIL = "申请失败!";
	public static final String ADD = "add";
	public static final String SUBMIT = "submit";
	/**
	 * 获取页面有特殊前缀的属性值，获取的值为字符串
	 * @param request
	 * @param prefix 属性前缀
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request, 
			                                   String prefix){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap() );
		}		
		//解决类型为multipart/form-data 获取不到数据的问题
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		HashMap<String, String> valueMap = new HashMap<String, String>();
		
		prefix = StringUtil.isNull(prefix) ? PRIFIX_SAVE : prefix;
		int prefixL = prefix.length();
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			Object value = entry.getValue();
			//页面上首字符为 prefix的属性为要保存到数据库中的属性
			if(!StringUtil.isNull(paramName) 
			      && paramName.length() > prefixL 
			      && prefix.equalsIgnoreCase(paramName.substring(0,prefixL))){
				String param = paramName.substring(prefixL,paramName.length());
				valueMap.put(param, value.getClass().isArray() 
					      ? (String)Array.get(value, 0) 
					      : value.toString());//字段值
				
			}							
		}
		return valueMap;
	}
	
	/**
	 * 获取页面有特殊前缀的属性值，获取的值为字符串数组
	 * @param request
	 * @param prefix 属性前缀
	 * */
	public HashMap<String, String[]> getValueArrayMap(HttpServletRequest request, 
            String prefix){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap());
		}	
		
		//解决类型为multipart/form-data 获取不到数据的问题
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
		
		prefix = StringUtil.isNull(prefix) ? PRIFIX_SAVE : prefix;
		int prefixL = prefix.length();
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			Object value = entry.getValue();
			//页面上首字符为prefix的属性为要保存到数据库中的属性
			if(!StringUtil.isNull(paramName) 
					&& paramName.length() > prefixL 
					&& prefix.equalsIgnoreCase(paramName.substring(0,prefixL))){
				String param = paramName.substring(prefixL,paramName.length());
				valueMap.put(param, value.getClass().isArray() 
			            ? (String[])value 
			            : new String[]{value.toString()});//字段值		
			}							
		}
		return valueMap;
	}
	
	/**
	 * 获取用户相关信息
	 * @param request
	 * @return User
	 * @throws Exception 
	 * */
	public User getUser(HttpServletRequest request){
		User user = new User();
		HttpSession session = request.getSession();
		String jyweb = session.getAttribute("jyweb") != null ? session.getAttribute("jyweb").toString() : "";
		
		if (!Base.isNull(jyweb) && "yes".equals(jyweb)) {
			user.setUserName((String) session.getAttribute("jyweb_userName"));
			user.setUserType((String) session.getAttribute("jyweb_userType"));
		} else {
			user.setUserName((String)session.getAttribute("userName"));
			user.setUserType((String)session.getAttribute("userType"));
			user.setIsFdy(String.valueOf(session.getAttribute("isFdy")));
			user.setUserDep((String)session.getAttribute("userDep"));
			user.setUserSyDep((String)session.getAttribute("userSyDep"));//用户书院
			user.setUserDepName((String)session.getAttribute("bmmc"));
			user.setUserMac((String) (session.getAttribute("userMac")) != null ? (String)session.getAttribute("userMac") : "");
			user.setRealName((String)(session.getAttribute("userNameReal") != null ? (String)session.getAttribute("userNameReal") :""));
			user.setChildId((String)(session.getAttribute("childId") != null ? (String)session.getAttribute("childId") :""));

			// ===========2011.3.16 edit by luojw===========
			// 用户类型
			String userType = session.getAttribute("userType") != null ? (String)session.getAttribute("userType") : "";
			// 辅导员权限
			String fdyQx = session.getAttribute("fdyQx") != null ? String.valueOf(session.getAttribute("fdyQx")) : "";
			// 班主任权限
			String bzrQx = session.getAttribute("bzrQx") != null ? String.valueOf(session.getAttribute("bzrQx")) : "";
			// 用户身份
			String userStatus = "";
			
			 // 使用用户角色
			String userRolesApply = user.getUserRolesApply();
			String userRoles = (String)(session.getAttribute("userRoles") != null ? (String)session.getAttribute("userRoles") : "");
			if(!Base.isNull(userRoles)){
				user.setUserRoles(userRoles.split("!!"));
			}
			
			if ("yes".equalsIgnoreCase(userRolesApply)) {
							
				String path = session.getAttribute("clickPath") != null ? session.getAttribute("clickPath").toString() : "";
				
				SearchTjByRoles rolesService = new SearchTjByRoles();
				
				SearchForm searchForm = new SearchForm();
				searchForm.setPath(path);
				
				// 用户角色
				List<HashMap<String, String>> userRolesList = null;
				
				try {
					userRolesList = rolesService.getUserGnmkRoles(searchForm, user);
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				// 用户身份
				userStatus = rolesService.getUserStatus(userRolesList, user);
				
			} else {
				if (Boolean.parseBoolean(bzrQx) && Boolean.parseBoolean(fdyQx)) {
					userStatus = "jd";// 班主任兼辅导员
				} else if (Boolean.parseBoolean(fdyQx)) {
					userStatus = "fdy";// 辅导员
				} else if (Boolean.parseBoolean(bzrQx)) {
					userStatus = "bzr";// 班主任
				} else if ("xy".equalsIgnoreCase(userType)) {
					userStatus = "xy";// 学院
				} else if ("sy".equalsIgnoreCase(userType)) {
					userStatus = "sy";// 书院
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					userStatus = "xx";// 学校用户（管理员）
				} else {
					userStatus = "stu";// 学生
				}
			}

			String gyglyQx = session.getAttribute("gyglyQx") != null ? session.getAttribute("gyglyQx").toString() : "";
			String syQx = session.getAttribute("syQx") != null ? session.getAttribute("syQx").toString() : "";
			
			user.setFdyQx(fdyQx);
			user.setBzrQx(bzrQx);
			user.setGyglyQx(gyglyQx);
			user.setSyQx(syQx);
			user.setUserStatus(userStatus);

			session.setAttribute("userStatus", userStatus);
		}
		
		user.setHost(request.getRemoteHost());
		user.setIp(request.getRemoteAddr());
		
		Map<String,String> authMap = (Map<String, String>) session.getAttribute("authMap");
		if (authMap == null){
			LoginService loginService = new LoginService();
			authMap = loginService.getUserAuth(user);
			session.setAttribute("authMap", authMap);
		}
		user.setAuthMap(authMap);	
		return user;
	}

	/**
	 *  根据用户名获取用户对象，仅包含简要信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-01 16:42
	 * @param userName
	 * @return xgxt.form.User
	 * @throw Exception
	 */
	public User getUser(String userName) throws Exception {
		BasicService service = new BasicService();
		return service.getUser(userName);
	}
	
	/**
	 * 单条记录插入，页面要保存的数据从request的Parameter中获取，要保存的页面属性前缀必须是save_。
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object insertOperation(HttpServletRequest request, String tabName ){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		User user = getUser(request);//用户相关信息
		boolean resultFlag = false;//执行结果
		boolean result = false;//最终操作结果
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){	
			//获取页面要保存到数据库的属性值
			valueMap = getValueMap(request,PRIFIX_SAVE);			
			//字段是否存在和主键是否有值判断
			resultFlag = basicValidator(request, tabName, valueMap);
			if(resultFlag){
				//进行插入操作
				try{
					resultFlag = service.insertPageData(tabName, 
							                            valueMap, 
							                            "insert",
							                            user);
					if (resultFlag) {
						//插入成功						
						request.setAttribute("message", SAVE_SUCCESS);
					} else {
						//插入失败						
						request.setAttribute("message", SAVE_FAIL);						
					}
					result = resultFlag;
				}catch(SQLException ex){	
					result = false;
					if(ex.getMessage().contains("ORA-00001")){//违反唯一约束
						request.setAttribute("message", MESSAGE_EXISTS_ERROR);
					} else if(ex.getMessage().contains("ORA-12899")){//插入的字段长度过长
						request.setAttribute("message", MESSAGE_TOO_LARGE_ERROR);
					}else{//其它
						request.setAttribute("message", SAVE_FAIL);
					}
					ex.printStackTrace();
				}				
			}			
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 单条记录插入，request参数只做记录操作结果使用。
	 * @param request
	 * @param valueMap
	 * @param tableMap
	 * @return Object
	 * */
	public Object insertOperation(HttpServletRequest request,
			                      HashMap<String, String> valueMap,
			                      HashMap<String, String> tableMap){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean operFlag = false;//执行结果
		boolean result = false;//操作结果
		String tabName = tableMap.get("tableName");
		
		if(!StringUtil.isNull(tabName)){			
			//字段是否存在和主键是否有值判断
			operFlag = basicValidator(request, tabName, valueMap);
			if(operFlag){
				//进行插入操作
				try{
					operFlag = service.insertPageData(tabName, 
							                          valueMap, 
							                          "insert", 
							                          user);
					if (operFlag) {
						//插入成功
						request.setAttribute("message", MESSAGE_SUCCESS);
					} else {
						//插入失败
						request.setAttribute("message", MESSAGE_FAIL);						
					}
					result = operFlag;
				}catch(SQLException ex){
					result = false;
					if(ex.getMessage().contains("ORA-00001")){//违反唯一约束
						request.setAttribute("message", MESSAGE_EXISTS_ERROR);
					} else if(ex.getMessage().contains("ORA-12899")){//插入的字段长度过长
						request.setAttribute("message", MESSAGE_TOO_LARGE_ERROR);
					}else{
						request.setAttribute("message", MESSAGE_FAIL);
					}
					ex.printStackTrace();
				}					
			}			
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		request.setAttribute("result", result);
		return valueMap;
	}
	
	
	/**
	 * 单条记录修改，页面要保存的数据从request的parameter中获取，要保存的页面属性前缀必须是save_。
	 * 要传入主键值，需要将pkValue存放到request的parameter中
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object updateOperation(HttpServletRequest request, String tabName ){
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		User user = getUser(request);
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		String pkValue = request.getParameter("pkValue");//页面传入主键值
		
		if(!StringUtil.isNull(tabName)){
			//获取页面要保存到数据库的属性值
			valueMap = getValueMap(request, PRIFIX_SAVE);			
			//字段是否存在和非空字段是否有值判断
			resultFlag = basicValidator(request, tabName, valueMap);
			if(resultFlag){
				//判断要修改的数据在数据库中是否存在
				resultFlag = service.checkDataExists(tabName, valueMap,pkValue);
				if(resultFlag){
					//进行修改操作
					try{
						if(StringUtil.isNull(pkValue)){
							//页面有主键值属性
							resultFlag = service.updatePageData(tabName, 
									                            valueMap,
									                            user);
						}else{
							resultFlag = service.updatePageData(tabName, 
									                            valueMap, 
									                            pkValue,
									                            user);
						}
						if(resultFlag){
							//成功
							request.setAttribute("message", MESSAGE_SUCCESS);
						}else{
							request.setAttribute("message", MESSAGE_UPDATE_EXISTS);
						}
						result = resultFlag;
					}catch (Exception e) {	
						result = false;
						request.setAttribute("message", MESSAGE_FAIL);
						e.printStackTrace();
					}					
				}else{
					result = false;
					request.setAttribute("message", MESSAGE_UPDATE_NOT_EXISTS);
				}
			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		request.setAttribute("result", result);		
		return valueMap;
	}
	
	/**
	 * 单条记录修改,request参数只做记录操作结果使用，要保存的数据存放到valueMap中。
	 * tableMap中必须存放tableName。
	 * 要传入主键值的，pkValue必须存放到tableMap中。
	 * @param request
	 * @param valueMap
	 * @param tableMap
	 * @return Object
	 * */
	public Object updateOperation(HttpServletRequest request,
			                      HashMap<String, String> valueMap,
			                      HashMap<String, String> tableMap){
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		String tabName = tableMap.get("tableName");
		String pkValue = tableMap.get("pkValue");//页面传入主键值
		
		if(!StringUtil.isNull(tabName)){			
			//字段是否存在和非空字段是否有值判断
			resultFlag = basicValidator(request, tabName, valueMap);
			if(resultFlag){
				//判断要修改的数据在数据库中是否存在
				resultFlag = service.checkDataExists(tabName, valueMap,pkValue);
				if(resultFlag){
					//进行修改操作
					try{
						if(StringUtil.isNull(pkValue)){
							//页面有主键值属性
							resultFlag = service.updatePageData(tabName, 
									                            valueMap,
									                            user);
						}else{
							resultFlag = service.updatePageData(tabName, 
									                            valueMap,
									                            pkValue,
									                            user);
						}
						if(resultFlag){
							//成功
							request.setAttribute("message", MESSAGE_SUCCESS);
						}else{
							request.setAttribute("message", MESSAGE_UPDATE_EXISTS);
						}
						result = resultFlag;
					}catch (Exception e) {	
						result = false;
						request.setAttribute("message", MESSAGE_FAIL);
						e.printStackTrace();
					}					
				}else{
					result = false;
					request.setAttribute("message", MESSAGE_UPDATE_NOT_EXISTS);
				}
			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		request.setAttribute("result", result);		
		return valueMap;
	}
	
	/**
	 * 批量记录插入，页面要保存的数据从requestd的paramter中获取，要保存的页面属性前缀必须是save_。
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object insertBatchOperation(HttpServletRequest request, String tabName ){
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);//用户相关信息
		HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		String pkValue = request.getParameter("pkValue") ;
		String pkString = request.getParameter("pkString");
		
		pkValue = StringUtil.isNull(pkValue) && request.getAttribute("pkValue") != null 
		           ? request.getAttribute("pkValue").toString() 
		           : pkValue;
		pkString = StringUtil.isNull(pkString) && request.getAttribute("pkString") != null 
		           ? request.getAttribute("pkString").toString() 
		           : pkString;		
		           
		if(!StringUtil.isNull(tabName)){
			//获取页面属性值
			valueMap = getValueArrayMap(request, PRIFIX_SAVE);		
			if(!(StringUtil.isNull(pkValue) || StringUtil.isNull(pkString))){
				valueMap.put("pkString", new String[]{pkString});
				valueMap.put("pkValue", new String[]{pkValue});
			}
			//判断非空字段是否都有值
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				//判断获取的字段的值的个数是否一致
				resultFlag = service.checkValueLeng(valueMap);
				if(resultFlag){
					//进行插入操作
					try{
						resultFlag = service.insertBatchPageData(tabName,
								                                 valueMap, 
								                                 user);
						if (resultFlag) {
							//成功
							request.setAttribute("message", MESSAGE_SUCCESS);
						} else {
							//失败
							request.setAttribute("message", MESSAGE_FAIL);						
						}
						result = resultFlag;
					}catch(SQLException ex){
						result = false;
						if(ex.getMessage().contains("ORA-00001")){//违反唯一约束
							request.setAttribute("message", MESSAGE_EXISTS_ERROR);
						} else if(ex.getMessage().contains("ORA-12899")){//字段长度过长
							request.setAttribute("message", MESSAGE_TOO_LARGE_ERROR);
						}else{//其它
							request.setAttribute("message", MESSAGE_FAIL);
						}
						ex.printStackTrace();
					}			
				}else{	
					result = false;
					request.setAttribute("message", MESSAGE_COLUM_DISUNION);
				}
			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}			
	
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 批量记录插入，request参数只做记录操作结果使用,要保存到数据库的字段和值存放到valueMap中，
	 * 表信息和主键信息存放到tableMap中。
	 * @param  request
	 * @param  valueMap
	 * @param  tableMap
	 * @return Object
	 * */
	public Object insertBatchOperation(HttpServletRequest request, 
			                           HashMap<String, String[]> valueMap, 
			                           HashMap<String, String> tableMap ){
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		String tabName = tableMap.get("tableName");
		String pkValue = tableMap.get("pkValue") ;
		String pkString = tableMap.get("pkString");
				           
		if(!(StringUtil.isNull(pkValue) || StringUtil.isNull(pkString))){
			valueMap.put("pkString", new String[]{pkString});
			valueMap.put("pkValue", new String[]{pkValue});
		}

		if(!StringUtil.isNull(tabName)){				
			//判断非空字段是否都有值
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				//判断获取的字段的值的个数是否一致
				resultFlag = service.checkValueLeng(valueMap);
				if(resultFlag){
					//进行插入操作
					try{
						resultFlag = service.insertBatchPageData(tabName,
								                                 valueMap,
								                                 user);
						if (resultFlag) {
							//成功
							request.setAttribute("message", MESSAGE_SUCCESS);
						} else {
							//失败
							request.setAttribute("message", MESSAGE_FAIL);						
						}
						result = resultFlag;
					}catch(SQLException ex){	
						result = false;
						if(ex.getMessage().contains("ORA-00001")){//违反唯一约束
							request.setAttribute("message", MESSAGE_EXISTS_ERROR);
						} else if(ex.getMessage().contains("ORA-12899")){//字段长度过长
							request.setAttribute("message", MESSAGE_TOO_LARGE_ERROR);
						}else{
							request.setAttribute("message", MESSAGE_FAIL);
						}
						ex.printStackTrace();
					}			
				}else{	
					result = false;
					request.setAttribute("message", MESSAGE_COLUM_DISUNION);
				}
			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}			
	
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 批量记录修改
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	private Object updateBatchOperation(HttpServletRequest request, String tabName ){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
		boolean resultFlag = false;// 执行结果
		boolean result = false;// 操作结果
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){				
			//获取页面属性值
			valueMap = getValueArrayMap(request, PRIFIX_SAVE);
			
			//判断非空字段是否都有值
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				try{
					//进行修改操作
					resultFlag = service.updateBatchPageData(tabName,
							                                 valueMap,
							                                 user);
					request.setAttribute("message", MESSAGE_SUCCESS);
					result = resultFlag;
				}catch(Exception e){		
					result = false;
					request.setAttribute("message", MESSAGE_FAIL);
					e.printStackTrace();
				}
			}
			
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 批量记录修改,要保存的信息存放到valueMap中,request只存放操作的结果信息，
	 * 表名等信息存放到tableMap中。
	 * @param request
	 * @param valueMap
	 * @param  tableMap
	 * @return Object
	 * */
	private Object updateBatchOperation(HttpServletRequest request, 
			                           HashMap<String, String[]> valueMap, 
			                           HashMap<String, String> tableMap ){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;// 执行结果
		boolean result = false;// 操作结果
		String tabName = tableMap.get("tableName");
		
		if(!StringUtil.isNull(tabName)){
			//判断非空字段是否都有值
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				try{
					//进行修改操作
					resultFlag = service.updateBatchPageData(tabName,
															 valueMap,
															 user);
					request.setAttribute("message", MESSAGE_SUCCESS);
					result = resultFlag;
				}catch(Exception e){		
					result = false;
					request.setAttribute("message", MESSAGE_FAIL);
					e.printStackTrace();
				}
			}
			
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 数据删除操作，删除数据的主键从reqeust的paramter中获取前缀为primarykey_的参数值。
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object deleteOperation(HttpServletRequest request, String tabName ){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
		boolean resultFlag =  false;//执行结果
		boolean result =  false;//操作结果
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){
			//获取页面主键
			valueMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			//判断是否有主键值
			resultFlag = service.checkParamNull(valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NO_DEL_CONDITION);
			}else{
				try{
					resultFlag = service.deletePageData(tabName,
														valueMap,
														user);
					request.setAttribute("message", DEL_SUCCESS);
					result = resultFlag;
				}catch(Exception e){
					result = false;
					request.setAttribute("message", DEL_FAIL);
					e.printStackTrace();
				}
			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);	
		}
		
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 数据删除操作，request中用于存放操作结果信息,删除数据主键从valueMap中获取，
	 * tableMap中存放表名称等信息。
	 * @param valueMap
	 * @param tableMap
	 * @return Object
	 * */
	public Object deleteOperation(HttpServletRequest request, 
			                      HashMap<String, String[]> valueMap, 
			                      HashMap<String, String> tableMap ){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag =  false;//执行结果
		boolean result =  false;//操作结果
		String tabName = tableMap.get("tableName");
		
		if(!StringUtil.isNull(tabName)){
			//判断是否有主键值
			resultFlag = service.checkParamNull(valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NO_DEL_CONDITION);
			}else{
				try{
					resultFlag = service.deletePageData(tabName,
														valueMap,
														user);
					request.setAttribute("message", MESSAGE_SUCCESS);
					result = resultFlag;
				}catch(Exception e){
					result = false;
					request.setAttribute("message", MESSAGE_FAIL);
					e.printStackTrace();
				}
			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);	
		}
		
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 数据审核操作,审核数据主键从request的paramter中获取前缀为primarykey_的参数值，
	 * 审核字段和审核结果从request的paramter或attribute中获取shzd和shjg的值。
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object auditingBatchOperation(HttpServletRequest request, String tabName ){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		HashMap<String, String[]> primaryMap = new HashMap<String, String[]>();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){
			//获取审核结果和审核字段
			String shjg = request.getParameter("shjg");
			String shzd = request.getParameter("shzd");
			
			shjg = StringUtil.isNull(shjg) ? request.getAttribute("shjg").toString() : shjg;
			shzd = StringUtil.isNull(shzd) ? request.getAttribute("shzd").toString() : shzd;
			if(!(StringUtil.isNull(shjg) || StringUtil.isNull(shzd))){
				//获取页面属性
				primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
				valueMap.put(shzd, shjg);	
				
				try{
					resultFlag = service.auditingPageData(tabName, 
														  primaryMap, 
														  valueMap,
														  user);
					request.setAttribute("message", MESSAGE_SUCCESS);	
					result = resultFlag;
				}catch(Exception e){
					result = false;
					request.setAttribute("message", MESSAGE_FAIL);
					e.printStackTrace();
				}
			}else{
				request.setAttribute("message", MESSAGE_SCARCITY_AUDITING_DATA);
			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		result = resultFlag;
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 数据审核操作,审核数据主键从primaryMap中获取,审核字段信息从valueMap中获取。
	 * @param request
	 * @param primaryMap
	 * @param valueMap
	 * @param tabName
	 * @return Object
	 * */
	public Object auditingBatchOperation(HttpServletRequest request, 
			                             HashMap<String, String[]> primaryMap, 
			                             HashMap<String, String> valueMap,
			                             String tabName){	
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		
		if(!StringUtil.isNull(tabName)){
			//获取审核结果和审核字段
//			String shjg = valueMap.get("shjg");
//			String shzd = valueMap.get("shzd");
//			if(!(StringUtil.isNull(shjg) || StringUtil.isNull(shzd))){
//				valueMap.put(shzd, shjg);					
				try{
					resultFlag = service.auditingPageData(tabName, 
							                              primaryMap, 
							                              valueMap,
							                              user);
					request.setAttribute("message", MESSAGE_SUCCESS);	
				}catch(Exception e){
					request.setAttribute("message", MESSAGE_FAIL);
					e.printStackTrace();
				}
//			}else{
//				request.setAttribute("message", MESSAGE_SCARCITY_AUDITING_DATA);
//			}
		}else{
			request.setAttribute("message", MESSAGE_NO_TABLE);
		}
		result = resultFlag;
		request.setAttribute("result", result);
		return valueMap;
	}
	
	/**
	 * 批量保存页面的数据。数据先从表中删除，后进行插入操作。
	 * 要保存的字段数据取request的paramter中前缀为save_的参数值，删除数据的主键取request中前缀为primarykey_的参数值
	 * @param request
	 * @param tabName
	 * @param viewName
	 * */
	public Object savePageDataBatch(HttpServletRequest request, 
			                        String tabName, 
			                        String viewName){
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
		HashMap<String, String[]> valueMap = getValueArrayMap(request, PRIFIX_SAVE);
		HashMap<String, String> tableMap = new HashMap<String, String>();
		tableMap.put("tableName", tabName);
		tableMap.put("viewName", viewName);
		
		if(primaryMap == null || primaryMap.size()<1){
			request.setAttribute("message", MESSAGE_NO_DEL_CONDITION);
		}else{
			resultFlag = service.checkValueLeng(valueMap);
			if(resultFlag){
				try{
					resultFlag = service.saveDataBatch(tableMap, 
							                           primaryMap, 
							                           valueMap,
							                           user);//保存数据
					if(resultFlag){
						request.setAttribute("message", MESSAGE_SUCCESS);
					}else{
						request.setAttribute("message", MESSAGE_FAIL);
					}
				}catch (Exception e) {
					request.setAttribute("message", MESSAGE_FAIL);
					e.printStackTrace();
				}
			}else{
				request.setAttribute("message", MESSAGE_COLUM_DISUNION);
			}
		}
		result = resultFlag;
		request.setAttribute("result", result);
		return primaryMap;
	}
	
	/**
	 * 批量保存页面的数据。数据先从表中删除，后进行插入操作。
	 * 要保存的字段数据取request的paramter中前缀为save_的参数值，
	 * 删除数据的主键取request的paramter中前缀为primarykey_的参数值联合primaryMap中的值
	 * @param request
	 * @param primaryArrayMap
	 * @param primaryMap
	 * @param tableMap
	 * @return Object
	 * */
	public Object savePageDataBatch(HttpServletRequest request,
			HashMap<String, String[]> primaryArrayMap, 
			HashMap<String, String> primaryMap, 
			HashMap<String, String> tableMap){
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//执行结果
		boolean result = false;//操作结果
		HashMap<String, String[]> valueMap = getValueArrayMap(request, PRIFIX_SAVE);
		
		if((primaryMap == null || primaryMap.size()<1) 
				|| (primaryArrayMap == null || primaryArrayMap.size()<1)){
			request.setAttribute("message", MESSAGE_NO_DEL_CONDITION);
		}else{
			resultFlag = service.checkValueLeng(valueMap);
			if(resultFlag){
				try{
					resultFlag = service.saveDataBatch(tableMap, 
							                           primaryArrayMap, 
							                           primaryMap, 
							                           valueMap,
							                           user);//保存数据
					if(resultFlag){
						request.setAttribute("message", MESSAGE_SUCCESS);
					}else{
						request.setAttribute("message", MESSAGE_FAIL);
					}					
				}catch(SQLException e){
					request.setAttribute("message", MESSAGE_FAIL);
					e.printStackTrace();
				}
			}else{
				request.setAttribute("message", MESSAGE_COLUM_DISUNION);
			}			
		}
		result = resultFlag;
		request.setAttribute("result", result);
		return primaryArrayMap;
	}
	
	/**
	 * 按主键查询单条数据
	 * @param request
	 * @param tabName
	 * @param viewName
	 * @param pkValue
	 * @return String
	 * */
	public String selectPageDataByOne(HttpServletRequest request, 
			                          String tabName,
			                          String viewName,
			                          String pkValue){
		Encrypt encrypt = new Encrypt();		
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		viewName = StringUtil.isNull(viewName) ? request.getParameter("viewName") : viewName;
		pkValue = StringUtil.isNull(pkValue) ? request.getParameter("pkValue") : pkValue;// TODO 要将获取的主键值解密 encrypt.AESDecryptMode()
		
		if(!(StringUtil.isNull(viewName) || StringUtil.isNull(pkValue))){
			request.setAttribute("rs", service.selectDataByPk(tabName, viewName, pkValue));
		}
		return pkValue;
	}
	
	/**
	 * 按条件查询数据（查询出的数据分页）
	 * @param request
	 * @param form
	 * @param tableName
	 * @param viewName
	 * @param outputColumn
	 * @return String
	 * */
	public Map<String, Object> selectPageDataByPagination(HttpServletRequest request, 
			                          ActionForm form,
			                          String tableName,
			                          String viewName,
			                          String[] outputColumn){
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap());
			
			if(paramMap.containsKey("userStatus")
					&& paramMap.containsKey("isFdy")){
				
				String value = paramMap.get("userStatus").getClass().isArray() 
	               ? (String) Array.get(paramMap.get("userStatus"), 0) 
	               : paramMap.get("userStatus").toString();
	               
                if("xy".equals(value)){
                	paramMap.put("isFdy", "false");
                }
			}
		}
		//解决类型为multipart/form-data 获取不到数据的问题
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		viewName = StringUtil.isNull(viewName) ? request.getParameter("viewName") : viewName;		
		ArrayList<String[]> rs = null;
		Class myClass = form.getClass();
		
		Pages pages = new Pages();
		try {
			pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(form,(Object[]) null);
		} catch (Exception e) {
			request.setAttribute("message", MESSAGE_CANNOT_PAGINATION);
			e.printStackTrace();
		}
		//获取自定义的查询字段
		//示例：request.setAttribute("clientColumns", " (case fdysh when '通过' then '#CCCCCC' else '#FFFFFF' end) bgcolor,");
		if(request.getAttribute("clientColumns") != null 
				&& !StringUtil.isNull(request.getAttribute("clientColumns").toString())){
			paramMap.put("clientColumns",request.getAttribute("clientColumns").toString());
		}
		if(request.getAttribute("annexTerm") != null 
				&& !StringUtil.isNull(request.getAttribute("annexTerm").toString())){
			paramMap.put("annexTerm",request.getAttribute("annexTerm").toString());
		}
		
		try{
			if(!(StringUtil.isNull(viewName) || outputColumn == null)){
				rs = service.queryData(tableName, viewName, paramMap, pages, outputColumn);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}catch(Exception e){
			if(paramMap.get("clientColumns") != null 
					&& !StringUtil.isNull(paramMap.get("clientColumns").toString())){
				request.setAttribute("message", MESSAGE_CLIENT_COLUMN_ERROR);
			}else{
				request.setAttribute("message", MESSAGE_QUERY_DATA_NULL);
			}
			e.printStackTrace();
		}
		request.setAttribute("topTr", service.getTopTr(viewName,outputColumn));
		return paramMap;
	}
	
	/**
	 * 按条件查询数据（查询出的数据未分页）
	 * @param request
	 * @param tableName
	 * @param viewName
	 * @param outputColumn
	 * @return String
	 * */
	public Map<String, Object> selectPageData(HttpServletRequest request, 
			                          String tableName,
			                          String viewName,
			                          String[] outputColumn){
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap());
			
			if(paramMap.containsKey("userStatus")
					&& paramMap.containsKey("isFdy")){
				
				String value = paramMap.get("userStatus").getClass().isArray() 
	               ? (String) Array.get(paramMap.get("userStatus"), 0) 
	               : paramMap.get("userStatus").toString();
	               
                if("xy".equals(value)){
                	paramMap.put("isFdy", "false");
                }
			}
		}
		//解决类型为multipart/form-data 获取不到数据的问题
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		viewName = StringUtil.isNull(viewName) 
		               ? request.getParameter("viewName") 
		               : viewName;
		ArrayList<String[]> rs = null;
		
		if(request.getAttribute("clientColumns") != null 
				&& !StringUtil.isNull(request.getAttribute("clientColumns").toString())){
			paramMap.put("clientColumns",request.getAttribute("clientColumns").toString());
		}
		if(request.getAttribute("annexTerm") != null 
				&& !StringUtil.isNull(request.getAttribute("annexTerm").toString())){
			paramMap.put("annexTerm",request.getAttribute("annexTerm").toString());
		}
		
		try{
			if(!(StringUtil.isNull(viewName) || outputColumn == null)){
				rs = service.queryData(tableName, viewName, paramMap, outputColumn);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}catch(Exception e){
			if(paramMap.get("clientColumns") != null 
					&& !StringUtil.isNull(paramMap.get("clientColumns").toString())){
				request.setAttribute("message", MESSAGE_CLIENT_COLUMN_ERROR);
			}else{
				request.setAttribute("message", MESSAGE_QUERY_DATA_NULL);
			}
			e.printStackTrace();
		}
		request.setAttribute("topTr", service.getTopTr(viewName,outputColumn));
		return paramMap;
	}
	
	/**
	 * 通用数据导出方法。查询条件从request的paramter中获取，获取方法同数据查询。
	 * 当outputColumn是null时，导出视图的所有字段
	 * @param  request
	 * @param  response
	 * @param  tableName
	 * @param  viewName
	 * @param  outputColumn
	 * @throws Exception
	 * */
	public void expPageData(HttpServletRequest request, 
			            HttpServletResponse response,
			            String tableName, 
			            String viewName, 
			            String[] outputColumn) throws Exception{
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		if(outputColumn == null){
			outputColumn = service.getTableColumn(viewName);
		}
		
		selectPageData(request, tableName, viewName, outputColumn);		
		List<String[]> list = (List<String[]>)request.getAttribute("rs");
		String[] colListCN = service.getColumnComment(viewName, outputColumn);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
	}
	
	/**
	 * 基础验证，判断字段在表中是否存在；判断非空字段是否都有值。
	 * @param  request
	 * @param  tabName
	 * @param  valueMap
	 * @return boolean 
	 * */
	private boolean basicValidator(HttpServletRequest request,
			                        String tabName,
			                        HashMap<String, String> valueMap){
		//TODO 程序运行时需要
		BasicService service = new BasicService();
		//判断页面上的字段在数据库表中是否存在
		String[] params = new String[valueMap.size()];
		int i=0;
		for(String key : valueMap.keySet()){
			params[i++] = key;
		}		
		boolean result = false;//操作结果
		boolean resultFlag = service.checkColumnExists(tabName,params);
		if(resultFlag){
			//判断非空字段是否都有值
			resultFlag = service.checkDataNull(tabName,valueMap);
			if(resultFlag){		
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				result = true;
			}
		}else{
			result = resultFlag;
			request.setAttribute("message", MESSAGE_COLUMN_NOT_EXISTS);
		}		
		
		request.setAttribute("result", result);
		return result;
	}
	
	//TODO 测试时需要
//	public BasicService getService() {
//		return service;
//	}
//
//	public void setService(BasicService service) {
//		this.service = service;
//	}	
	
	/**
	 * 手动异常处理<br>
	 * 需要自己捕获{@link common.exception.SystemException}<br>
	 * 该方法以在catch代码块调用 
	 */
	public void catchSystemException(HttpServletRequest request,
			SystemException e) {
		e.printStackTrace();
		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(e.getKey(),e.getValues());
		messages.add("error", message);
		request.setAttribute("org.apache.struts.action.ERROR", messages);
	}
	
	/**
	 * 把message封装为json对象
	 * @param message
	 * @return
	 */
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	
	
	/**
	 * 把message封装为json对象,传入key
	 * @param key
	 * @return
	 */
	protected JSONObject getJsonMessageByKey(String key){

		String message =  MessageUtil.getText(key);

		return getJsonMessage(message);
	}
	
	public void insertLog(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);
		String parameter = mapping.getParameter();
		String methodName = super.getMethodName(mapping, form, request, response, parameter);
		User user = getUser(request);
		String description = "";
		UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
		description+=searchTj+ArrayUtils.toString(inputV);
		LogInfo logInfo = new LogInfo();
		logInfo.setOsName(userAgent.getOperatingSystem().getName());
		logInfo.setBrowserName(userAgent.getBrowser().getName());
		logInfo.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
		logInfo.setIp(IPRequest.getIpAddress(request));
		logInfo.setDescription(description);
		logInfo.setClassName(mapping.getType());
		logInfo.setMethodName(methodName);
		
		if (user != null){
			logInfo.setUsername(user.getUserName());
			logInfo.setUserType(user.getUserType());
		}
		new LogService().runInsert(logInfo);
	}
	
}
