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
	//TODO ����ʱ��Ҫ
//	BasicService service;
	//ҳ������ǰ׺
	public static String PRIFIX_SAVE = "save_";//��Ҫ���浽���ݿ���ֶ�ǰ׺
	public static String PRIFIX_PRIMARY_KEY = "primarykey_";//ҳ����ʾ���������ֶ�ǰ׺
	//������Ϣ
	public static String MESSAGE_SUCCESS = "�����ɹ���";
	public static String MESSAGE_FAIL = "����ʧ�ܣ�";
	private static String MESSAGE_NOT_NULL = "�����ֶα�����д���ݣ�";
	private static String MESSAGE_NO_TABLE = "�޷���ȡ��Ҫ���������ݿ�����ƣ�";
	public static String MESSAGE_EXISTS_ERROR = "���ӵ������Ѿ����ڣ�";
	private static String MESSAGE_TOO_LARGE_ERROR = "�������Ϣ���ȹ�����";
	private static String MESSAGE_CLIENT_COLUMN_ERROR = "�����Զ����ֶ�����";
	private static String MESSAGE_UPDATE_EXISTS = "�޸ĺ�����������ݿ����Ѵ��ڣ�";
	private static String MESSAGE_UPDATE_NOT_EXISTS = "��Ҫ�޸ĵ����������ݿ��в����ڣ�";
	private static String MESSAGE_COLUM_DISUNION = "����������Ҫ������ֶβ�ͳһ��";
	private static String MESSAGE_NO_DEL_CONDITION = "��ѡ��Ҫ���������ݣ�";
	private static String MESSAGE_SCARCITY_AUDITING_DATA = "����ֶλ���˽��Ϊ�գ�";
	private static String MESSAGE_CANNOT_PAGINATION = "���Form���ɷ�ҳ��";
	private static String MESSAGE_QUERY_DATA_NULL = "δ��ѯ���κ����ݣ�";
	private static String MESSAGE_COLUMN_NOT_EXISTS = "����ҳ�����Բ����ڱ���ֶΣ��޷����棡";
	public static final String QUERY = "query";
	public static final String VIEW = "view";
	public static final String SAVE = "save";
	public static final String UPDATE = "update";
	public static final String DEL = "del";
	public static final String EXP = "exp";
	public static final String PLSH = "plsh";
	public static final String QXSH = "qxsh";
	public static final String SAVE_SUCCESS = "����ɹ�!";
	public static final String SAVE_FAIL = "����ʧ��!";
	public static final String DEL_SUCCESS = "ɾ���ɹ�!";
	public static final String DEL_FAIL = "ɾ��ʧ��!";
	public static final String SQ_SUCCESS = "����ɹ�!";
	public static final String SQ_FAIL = "����ʧ��!";
	public static final String ADD = "add";
	public static final String SUBMIT = "submit";
	/**
	 * ��ȡҳ��������ǰ׺������ֵ����ȡ��ֵΪ�ַ���
	 * @param request
	 * @param prefix ����ǰ׺
	 * */
	public HashMap<String, String> getValueMap(HttpServletRequest request, 
			                                   String prefix){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap() );
		}		
		//�������Ϊmultipart/form-data ��ȡ�������ݵ�����
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
			//ҳ�������ַ�Ϊ prefix������ΪҪ���浽���ݿ��е�����
			if(!StringUtil.isNull(paramName) 
			      && paramName.length() > prefixL 
			      && prefix.equalsIgnoreCase(paramName.substring(0,prefixL))){
				String param = paramName.substring(prefixL,paramName.length());
				valueMap.put(param, value.getClass().isArray() 
					      ? (String)Array.get(value, 0) 
					      : value.toString());//�ֶ�ֵ
				
			}							
		}
		return valueMap;
	}
	
	/**
	 * ��ȡҳ��������ǰ׺������ֵ����ȡ��ֵΪ�ַ�������
	 * @param request
	 * @param prefix ����ǰ׺
	 * */
	public HashMap<String, String[]> getValueArrayMap(HttpServletRequest request, 
            String prefix){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap());
		}	
		
		//�������Ϊmultipart/form-data ��ȡ�������ݵ�����
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
			//ҳ�������ַ�Ϊprefix������ΪҪ���浽���ݿ��е�����
			if(!StringUtil.isNull(paramName) 
					&& paramName.length() > prefixL 
					&& prefix.equalsIgnoreCase(paramName.substring(0,prefixL))){
				String param = paramName.substring(prefixL,paramName.length());
				valueMap.put(param, value.getClass().isArray() 
			            ? (String[])value 
			            : new String[]{value.toString()});//�ֶ�ֵ		
			}							
		}
		return valueMap;
	}
	
	/**
	 * ��ȡ�û������Ϣ
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
			user.setUserSyDep((String)session.getAttribute("userSyDep"));//�û���Ժ
			user.setUserDepName((String)session.getAttribute("bmmc"));
			user.setUserMac((String) (session.getAttribute("userMac")) != null ? (String)session.getAttribute("userMac") : "");
			user.setRealName((String)(session.getAttribute("userNameReal") != null ? (String)session.getAttribute("userNameReal") :""));
			user.setChildId((String)(session.getAttribute("childId") != null ? (String)session.getAttribute("childId") :""));

			// ===========2011.3.16 edit by luojw===========
			// �û�����
			String userType = session.getAttribute("userType") != null ? (String)session.getAttribute("userType") : "";
			// ����ԱȨ��
			String fdyQx = session.getAttribute("fdyQx") != null ? String.valueOf(session.getAttribute("fdyQx")) : "";
			// ������Ȩ��
			String bzrQx = session.getAttribute("bzrQx") != null ? String.valueOf(session.getAttribute("bzrQx")) : "";
			// �û����
			String userStatus = "";
			
			 // ʹ���û���ɫ
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
				
				// �û���ɫ
				List<HashMap<String, String>> userRolesList = null;
				
				try {
					userRolesList = rolesService.getUserGnmkRoles(searchForm, user);
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				// �û����
				userStatus = rolesService.getUserStatus(userRolesList, user);
				
			} else {
				if (Boolean.parseBoolean(bzrQx) && Boolean.parseBoolean(fdyQx)) {
					userStatus = "jd";// �����μ渨��Ա
				} else if (Boolean.parseBoolean(fdyQx)) {
					userStatus = "fdy";// ����Ա
				} else if (Boolean.parseBoolean(bzrQx)) {
					userStatus = "bzr";// ������
				} else if ("xy".equalsIgnoreCase(userType)) {
					userStatus = "xy";// ѧԺ
				} else if ("sy".equalsIgnoreCase(userType)) {
					userStatus = "sy";// ��Ժ
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					userStatus = "xx";// ѧУ�û�������Ա��
				} else {
					userStatus = "stu";// ѧ��
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
	 *  �����û�����ȡ�û����󣬽�������Ҫ��Ϣ.
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
	 * ������¼���룬ҳ��Ҫ��������ݴ�request��Parameter�л�ȡ��Ҫ�����ҳ������ǰ׺������save_��
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object insertOperation(HttpServletRequest request, String tabName ){	
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		User user = getUser(request);//�û������Ϣ
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//���ղ������
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){	
			//��ȡҳ��Ҫ���浽���ݿ������ֵ
			valueMap = getValueMap(request,PRIFIX_SAVE);			
			//�ֶ��Ƿ���ں������Ƿ���ֵ�ж�
			resultFlag = basicValidator(request, tabName, valueMap);
			if(resultFlag){
				//���в������
				try{
					resultFlag = service.insertPageData(tabName, 
							                            valueMap, 
							                            "insert",
							                            user);
					if (resultFlag) {
						//����ɹ�						
						request.setAttribute("message", SAVE_SUCCESS);
					} else {
						//����ʧ��						
						request.setAttribute("message", SAVE_FAIL);						
					}
					result = resultFlag;
				}catch(SQLException ex){	
					result = false;
					if(ex.getMessage().contains("ORA-00001")){//Υ��ΨһԼ��
						request.setAttribute("message", MESSAGE_EXISTS_ERROR);
					} else if(ex.getMessage().contains("ORA-12899")){//������ֶγ��ȹ���
						request.setAttribute("message", MESSAGE_TOO_LARGE_ERROR);
					}else{//����
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
	 * ������¼���룬request����ֻ����¼�������ʹ�á�
	 * @param request
	 * @param valueMap
	 * @param tableMap
	 * @return Object
	 * */
	public Object insertOperation(HttpServletRequest request,
			                      HashMap<String, String> valueMap,
			                      HashMap<String, String> tableMap){	
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean operFlag = false;//ִ�н��
		boolean result = false;//�������
		String tabName = tableMap.get("tableName");
		
		if(!StringUtil.isNull(tabName)){			
			//�ֶ��Ƿ���ں������Ƿ���ֵ�ж�
			operFlag = basicValidator(request, tabName, valueMap);
			if(operFlag){
				//���в������
				try{
					operFlag = service.insertPageData(tabName, 
							                          valueMap, 
							                          "insert", 
							                          user);
					if (operFlag) {
						//����ɹ�
						request.setAttribute("message", MESSAGE_SUCCESS);
					} else {
						//����ʧ��
						request.setAttribute("message", MESSAGE_FAIL);						
					}
					result = operFlag;
				}catch(SQLException ex){
					result = false;
					if(ex.getMessage().contains("ORA-00001")){//Υ��ΨһԼ��
						request.setAttribute("message", MESSAGE_EXISTS_ERROR);
					} else if(ex.getMessage().contains("ORA-12899")){//������ֶγ��ȹ���
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
	 * ������¼�޸ģ�ҳ��Ҫ��������ݴ�request��parameter�л�ȡ��Ҫ�����ҳ������ǰ׺������save_��
	 * Ҫ��������ֵ����Ҫ��pkValue��ŵ�request��parameter��
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object updateOperation(HttpServletRequest request, String tabName ){
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		User user = getUser(request);
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		String pkValue = request.getParameter("pkValue");//ҳ�洫������ֵ
		
		if(!StringUtil.isNull(tabName)){
			//��ȡҳ��Ҫ���浽���ݿ������ֵ
			valueMap = getValueMap(request, PRIFIX_SAVE);			
			//�ֶ��Ƿ���ںͷǿ��ֶ��Ƿ���ֵ�ж�
			resultFlag = basicValidator(request, tabName, valueMap);
			if(resultFlag){
				//�ж�Ҫ�޸ĵ����������ݿ����Ƿ����
				resultFlag = service.checkDataExists(tabName, valueMap,pkValue);
				if(resultFlag){
					//�����޸Ĳ���
					try{
						if(StringUtil.isNull(pkValue)){
							//ҳ��������ֵ����
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
							//�ɹ�
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
	 * ������¼�޸�,request����ֻ����¼�������ʹ�ã�Ҫ��������ݴ�ŵ�valueMap�С�
	 * tableMap�б�����tableName��
	 * Ҫ��������ֵ�ģ�pkValue�����ŵ�tableMap�С�
	 * @param request
	 * @param valueMap
	 * @param tableMap
	 * @return Object
	 * */
	public Object updateOperation(HttpServletRequest request,
			                      HashMap<String, String> valueMap,
			                      HashMap<String, String> tableMap){
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
		String tabName = tableMap.get("tableName");
		String pkValue = tableMap.get("pkValue");//ҳ�洫������ֵ
		
		if(!StringUtil.isNull(tabName)){			
			//�ֶ��Ƿ���ںͷǿ��ֶ��Ƿ���ֵ�ж�
			resultFlag = basicValidator(request, tabName, valueMap);
			if(resultFlag){
				//�ж�Ҫ�޸ĵ����������ݿ����Ƿ����
				resultFlag = service.checkDataExists(tabName, valueMap,pkValue);
				if(resultFlag){
					//�����޸Ĳ���
					try{
						if(StringUtil.isNull(pkValue)){
							//ҳ��������ֵ����
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
							//�ɹ�
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
	 * ������¼���룬ҳ��Ҫ��������ݴ�requestd��paramter�л�ȡ��Ҫ�����ҳ������ǰ׺������save_��
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object insertBatchOperation(HttpServletRequest request, String tabName ){
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);//�û������Ϣ
		HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
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
			//��ȡҳ������ֵ
			valueMap = getValueArrayMap(request, PRIFIX_SAVE);		
			if(!(StringUtil.isNull(pkValue) || StringUtil.isNull(pkString))){
				valueMap.put("pkString", new String[]{pkString});
				valueMap.put("pkValue", new String[]{pkValue});
			}
			//�жϷǿ��ֶ��Ƿ���ֵ
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				//�жϻ�ȡ���ֶε�ֵ�ĸ����Ƿ�һ��
				resultFlag = service.checkValueLeng(valueMap);
				if(resultFlag){
					//���в������
					try{
						resultFlag = service.insertBatchPageData(tabName,
								                                 valueMap, 
								                                 user);
						if (resultFlag) {
							//�ɹ�
							request.setAttribute("message", MESSAGE_SUCCESS);
						} else {
							//ʧ��
							request.setAttribute("message", MESSAGE_FAIL);						
						}
						result = resultFlag;
					}catch(SQLException ex){
						result = false;
						if(ex.getMessage().contains("ORA-00001")){//Υ��ΨһԼ��
							request.setAttribute("message", MESSAGE_EXISTS_ERROR);
						} else if(ex.getMessage().contains("ORA-12899")){//�ֶγ��ȹ���
							request.setAttribute("message", MESSAGE_TOO_LARGE_ERROR);
						}else{//����
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
	 * ������¼���룬request����ֻ����¼�������ʹ��,Ҫ���浽���ݿ���ֶκ�ֵ��ŵ�valueMap�У�
	 * ����Ϣ��������Ϣ��ŵ�tableMap�С�
	 * @param  request
	 * @param  valueMap
	 * @param  tableMap
	 * @return Object
	 * */
	public Object insertBatchOperation(HttpServletRequest request, 
			                           HashMap<String, String[]> valueMap, 
			                           HashMap<String, String> tableMap ){
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
		String tabName = tableMap.get("tableName");
		String pkValue = tableMap.get("pkValue") ;
		String pkString = tableMap.get("pkString");
				           
		if(!(StringUtil.isNull(pkValue) || StringUtil.isNull(pkString))){
			valueMap.put("pkString", new String[]{pkString});
			valueMap.put("pkValue", new String[]{pkValue});
		}

		if(!StringUtil.isNull(tabName)){				
			//�жϷǿ��ֶ��Ƿ���ֵ
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				//�жϻ�ȡ���ֶε�ֵ�ĸ����Ƿ�һ��
				resultFlag = service.checkValueLeng(valueMap);
				if(resultFlag){
					//���в������
					try{
						resultFlag = service.insertBatchPageData(tabName,
								                                 valueMap,
								                                 user);
						if (resultFlag) {
							//�ɹ�
							request.setAttribute("message", MESSAGE_SUCCESS);
						} else {
							//ʧ��
							request.setAttribute("message", MESSAGE_FAIL);						
						}
						result = resultFlag;
					}catch(SQLException ex){	
						result = false;
						if(ex.getMessage().contains("ORA-00001")){//Υ��ΨһԼ��
							request.setAttribute("message", MESSAGE_EXISTS_ERROR);
						} else if(ex.getMessage().contains("ORA-12899")){//�ֶγ��ȹ���
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
	 * ������¼�޸�
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	private Object updateBatchOperation(HttpServletRequest request, String tabName ){	
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
		boolean resultFlag = false;// ִ�н��
		boolean result = false;// �������
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){				
			//��ȡҳ������ֵ
			valueMap = getValueArrayMap(request, PRIFIX_SAVE);
			
			//�жϷǿ��ֶ��Ƿ���ֵ
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				try{
					//�����޸Ĳ���
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
	 * ������¼�޸�,Ҫ�������Ϣ��ŵ�valueMap��,requestֻ��Ų����Ľ����Ϣ��
	 * ��������Ϣ��ŵ�tableMap�С�
	 * @param request
	 * @param valueMap
	 * @param  tableMap
	 * @return Object
	 * */
	private Object updateBatchOperation(HttpServletRequest request, 
			                           HashMap<String, String[]> valueMap, 
			                           HashMap<String, String> tableMap ){	
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;// ִ�н��
		boolean result = false;// �������
		String tabName = tableMap.get("tableName");
		
		if(!StringUtil.isNull(tabName)){
			//�жϷǿ��ֶ��Ƿ���ֵ
			resultFlag = service.checkDataNullBatch(tabName,valueMap);
			if(resultFlag){
				result = false;
				request.setAttribute("message", MESSAGE_NOT_NULL);
			}else{
				try{
					//�����޸Ĳ���
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
	 * ����ɾ��������ɾ�����ݵ�������reqeust��paramter�л�ȡǰ׺Ϊprimarykey_�Ĳ���ֵ��
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object deleteOperation(HttpServletRequest request, String tabName ){	
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
		boolean resultFlag =  false;//ִ�н��
		boolean result =  false;//�������
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){
			//��ȡҳ������
			valueMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			//�ж��Ƿ�������ֵ
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
	 * ����ɾ��������request�����ڴ�Ų��������Ϣ,ɾ������������valueMap�л�ȡ��
	 * tableMap�д�ű����Ƶ���Ϣ��
	 * @param valueMap
	 * @param tableMap
	 * @return Object
	 * */
	public Object deleteOperation(HttpServletRequest request, 
			                      HashMap<String, String[]> valueMap, 
			                      HashMap<String, String> tableMap ){	
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag =  false;//ִ�н��
		boolean result =  false;//�������
		String tabName = tableMap.get("tableName");
		
		if(!StringUtil.isNull(tabName)){
			//�ж��Ƿ�������ֵ
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
	 * ������˲���,�������������request��paramter�л�ȡǰ׺Ϊprimarykey_�Ĳ���ֵ��
	 * ����ֶκ���˽����request��paramter��attribute�л�ȡshzd��shjg��ֵ��
	 * @param request
	 * @param tabName
	 * @return Object
	 * */
	public Object auditingBatchOperation(HttpServletRequest request, String tabName ){	
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		HashMap<String, String[]> primaryMap = new HashMap<String, String[]>();
		HashMap<String, String> valueMap = new HashMap<String, String>();
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		
		if(!StringUtil.isNull(tabName)){
			//��ȡ��˽��������ֶ�
			String shjg = request.getParameter("shjg");
			String shzd = request.getParameter("shzd");
			
			shjg = StringUtil.isNull(shjg) ? request.getAttribute("shjg").toString() : shjg;
			shzd = StringUtil.isNull(shzd) ? request.getAttribute("shzd").toString() : shzd;
			if(!(StringUtil.isNull(shjg) || StringUtil.isNull(shzd))){
				//��ȡҳ������
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
	 * ������˲���,�������������primaryMap�л�ȡ,����ֶ���Ϣ��valueMap�л�ȡ��
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
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
		
		if(!StringUtil.isNull(tabName)){
			//��ȡ��˽��������ֶ�
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
	 * ��������ҳ������ݡ������ȴӱ���ɾ��������в��������
	 * Ҫ������ֶ�����ȡrequest��paramter��ǰ׺Ϊsave_�Ĳ���ֵ��ɾ�����ݵ�����ȡrequest��ǰ׺Ϊprimarykey_�Ĳ���ֵ
	 * @param request
	 * @param tabName
	 * @param viewName
	 * */
	public Object savePageDataBatch(HttpServletRequest request, 
			                        String tabName, 
			                        String viewName){
		BasicService service = new BasicService();
		User user = getUser(request);
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
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
							                           user);//��������
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
	 * ��������ҳ������ݡ������ȴӱ���ɾ��������в��������
	 * Ҫ������ֶ�����ȡrequest��paramter��ǰ׺Ϊsave_�Ĳ���ֵ��
	 * ɾ�����ݵ�����ȡrequest��paramter��ǰ׺Ϊprimarykey_�Ĳ���ֵ����primaryMap�е�ֵ
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
		boolean resultFlag = false;//ִ�н��
		boolean result = false;//�������
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
							                           user);//��������
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
	 * ��������ѯ��������
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
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		tabName = StringUtil.isNull(tabName) ? request.getParameter("tableName") : tabName;
		viewName = StringUtil.isNull(viewName) ? request.getParameter("viewName") : viewName;
		pkValue = StringUtil.isNull(pkValue) ? request.getParameter("pkValue") : pkValue;// TODO Ҫ����ȡ������ֵ���� encrypt.AESDecryptMode()
		
		if(!(StringUtil.isNull(viewName) || StringUtil.isNull(pkValue))){
			request.setAttribute("rs", service.selectDataByPk(tabName, viewName, pkValue));
		}
		return pkValue;
	}
	
	/**
	 * ��������ѯ���ݣ���ѯ�������ݷ�ҳ��
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
		//�������Ϊmultipart/form-data ��ȡ�������ݵ�����
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		
		//TODO ��������ʱ��Ҫ
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
		//��ȡ�Զ���Ĳ�ѯ�ֶ�
		//ʾ����request.setAttribute("clientColumns", " (case fdysh when 'ͨ��' then '#CCCCCC' else '#FFFFFF' end) bgcolor,");
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
	 * ��������ѯ���ݣ���ѯ��������δ��ҳ��
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
		//�������Ϊmultipart/form-data ��ȡ�������ݵ�����
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		//TODO ��������ʱ��Ҫ
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
	 * ͨ�����ݵ�����������ѯ������request��paramter�л�ȡ����ȡ����ͬ���ݲ�ѯ��
	 * ��outputColumn��nullʱ��������ͼ�������ֶ�
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
		//TODO ��������ʱ��Ҫ
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
	 * ������֤���ж��ֶ��ڱ����Ƿ���ڣ��жϷǿ��ֶ��Ƿ���ֵ��
	 * @param  request
	 * @param  tabName
	 * @param  valueMap
	 * @return boolean 
	 * */
	private boolean basicValidator(HttpServletRequest request,
			                        String tabName,
			                        HashMap<String, String> valueMap){
		//TODO ��������ʱ��Ҫ
		BasicService service = new BasicService();
		//�ж�ҳ���ϵ��ֶ������ݿ�����Ƿ����
		String[] params = new String[valueMap.size()];
		int i=0;
		for(String key : valueMap.keySet()){
			params[i++] = key;
		}		
		boolean result = false;//�������
		boolean resultFlag = service.checkColumnExists(tabName,params);
		if(resultFlag){
			//�жϷǿ��ֶ��Ƿ���ֵ
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
	
	//TODO ����ʱ��Ҫ
//	public BasicService getService() {
//		return service;
//	}
//
//	public void setService(BasicService service) {
//		this.service = service;
//	}	
	
	/**
	 * �ֶ��쳣����<br>
	 * ��Ҫ�Լ�����{@link common.exception.SystemException}<br>
	 * �÷�������catch�������� 
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
	 * ��message��װΪjson����
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
	 * ��message��װΪjson����,����key
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
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// �߼���ѯ����ֵ
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
