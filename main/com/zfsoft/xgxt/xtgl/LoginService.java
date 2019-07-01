package com.zfsoft.xgxt.xtgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForward;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jglr.JglrService;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyService;
import com.zfsoft.xgxt.xszz.xszzbjpy.jcsz.JcszForm;


import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.form.User;
import xgxt.pjpy.WSForCjJd.WebserviceClient;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

public class LoginService {

	private LoginDao loginDao = new LoginDao();
	private static final String STUDENT = "student";
	private static final String TEACHER = "teacher";
	private static final String PARENT = "parent";

	
	public String checkUserLogin(String userName,String password){
		
		String stuCount = loginDao.getStuUser(userName, password);
		
		if (Integer.valueOf(stuCount) > 0){
			return STUDENT;
		} else {
			String teaCount = loginDao.getTeaUser(userName, password);
			
			if (Integer.valueOf(teaCount) > 0){
				return TEACHER;
			}
		}
		return null;
	}
	public String getStuUserByUserName(String userName){
		return loginDao.getStuUserByUserName(userName);
		
	}
	
	
	/*****修改密码******/
	public boolean editPwd(User user,String oldPwd,String newPwd) throws Exception{
		
		Encrypt encrypt = new Encrypt();
		
		if ("stu".equals(user.getUserType())){
			String count = loginDao.getStuUser(user.getUserName(), encrypt.encrypt(oldPwd));
			
			if (Integer.valueOf(count) == 0){
				return false;
			} else {
				loginDao.updateStuPwd(user.getUserName(), encrypt.encrypt(newPwd));
			}
			
		}else if("parent".equals(user.getUserType())){
			String count = loginDao.getParentUser(user.getUserName(), encrypt.encrypt(oldPwd));

			if (Integer.valueOf(count) == 0){
				return false;
			} else {
				loginDao.updateParentPwd(user.getUserName(), encrypt.encrypt(newPwd));
			}
		} else {
			String count = loginDao.getTeaUser(user.getUserName(), encrypt.encrypt(oldPwd));
			
			if (Integer.valueOf(count) == 0){
				return false;
			} else {
				loginDao.updateTeaPwd(user.getUserName(), encrypt.encrypt(newPwd));
			}
		}
		return true;
	}
	
	
	/*****用户权限******/
	public Map<String,String> getUserAuth(User user){
		
		List<HashMap<String,String>> authList = null;
		
		if ("stu".equals(user.getUserType())){
			authList = loginDao.getStuAuth();
		} else {
			authList = loginDao.getTeaAuth(user.getUserName());
		}
		
		Map<String,String> authMap = new HashMap<String, String>();
		
		if (authList != null){
			for(HashMap<String,String> map : authList){
				authMap.put(map.get("dyym"), map.get("dxq"));
			}
		}
		return authMap;
	}
	
	//移动学工首页加载我的应用
	public List<HashMap<String, String>> getIndexKjfs(User user) {
		List<HashMap<String, String>> kjfsList = loginDao.getIndexKjfs(user.getUserName());
		if (kjfsList.size() == 0) {
			List<HashMap<String, String>> allList = getAllKjfs(user);
			int num = allList.size() >= 8 ? 8 : allList.size();
			for (int i = 0; i < num; i++) {
				HashMap<String, String> temp = allList.get(i);
				temp.put("sfyx", "1");
				kjfsList.add(temp);
			}
		}
		if (kjfsList.size() < 8) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("sfyx", "0");
			kjfsList.add(temp);
		}
		return kjfsList;
	}
	
	//移动学工排除已选应用的应用List
	public List<HashMap<String, String>> getAllKjfs(User user) {
		String userType = user.getUserType();
		if (null == userType || "".equals(userType)) {
			userType = "-1";
		} else if (userType.equals("xx")||userType.equals("admin")
				||userType.equals("xy")||userType.equals("bzr")
				||userType.equals("fdy")||userType.equals("jd")) {
			userType = "1";
		} else if (userType.equals("stu")) {
			userType = "0";
		} else if(userType.equals("parent")){
			userType = "2";
		}
		List<HashMap<String, String>> allList = loginDao.getAllKjfs(user.getUserName(), userType);
		
		return allList;
	}
	
	//移动学工排除已选应用的应用List
	public List<HashMap<String, String>> getAllMyKjfs(User user) {
		String userType="1";
		if ("stu".equals(user.getUserType())) {
			userType = "0";
		}
		List<HashMap<String, String>> allList = loginDao.getAllMyKjfs(user.getUserName(), userType);
		return allList;
	}
	/**
	 * 
	 * @描述:困难生班级评议
	 * @作者：xiaixa [工号：1104]
	 * @日期：2015-12-31 下午05:27:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean knsbjPyqx(User user) throws Exception{
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		JglrService service = new JglrService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		HashMap<String,String> bjpyxzcyMap = service.queryBjpyxzcy(user.getUserName());
		if(jcszModel == null||bjpyxzcyMap.get("tjzt") == null||"0".equals(bjpyxzcyMap.get("tjzt"))){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @描述:资助班级评议
	 * @作者：xiaixa [工号：1104]
	 * @日期：2015-12-31 下午05:27:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean zzbjPyqx(User user) throws Exception{
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		HashMap<String,String> bjpyxzcyMap = new  com.zfsoft.xgxt.xszz.xszzbjpy.jglr.JglrService().queryBjpyxzcy(user.getUserName());
		if(jcszModel == null||bjpyxzcyMap.get("tjzt") == null||"0".equals(bjpyxzcyMap.get("tjzt"))){
			return false;
		}
		return true;
	}
	
	
	
	//移动学工保存我的应用
	public boolean insertKjfs(User user, String value) throws Exception {
		if (StringUtil.isNull(value)) {
			return false;
		}
		
		boolean result = loginDao.deleteKjfs(user.getUserName());
		if(result){
			List<String[]> params = new ArrayList<String[]>();
			String[] values = value.split(",");
			int i = 1;
			for (String string : values) {
				String[] temp = new String[6];
				temp[0] = UniqID.getInstance().getUniqIDHash();
				temp[1] = user.getUserName();
				temp[2] = string;
				temp[3] = DateUtils.getCurrTime();
				temp[4] = user.getUserType();
				temp[5] = String.valueOf(i++);
				params.add(temp);
			}
			result =  loginDao.insertKjfs(user.getUserName(), user.getUserType(),params);
		}
		return result;
	}
	//验证用户微信号是否绑定学号
	public HashMap<String, String> getUserInfoByUserName(String openid) throws Exception {
		return loginDao.getUserInfoByUserName(openid);
	}
	public boolean insertOpenid(String openid,String username,String password) throws Exception {
		return loginDao.insertOpenid(openid,username,password);
	}
	
	//解除绑定
	public boolean deleteOpenid(String username) throws Exception {
		return loginDao.deleteOpenid(username);
	}
	
	public String checkWebService(String userName,String password) throws Exception{
		String xxdm =Base.xxdm;
		String wsXxdm=loginDao.isCheckWs(xxdm);
		String flag ="0";
		if(StringUtils.isNotNull(wsXxdm)){
			String zfcaWsUrl = Base.initProperties.get("zfcaWsUrl");
			WebserviceClient wsc = new WebserviceClient(zfcaWsUrl,  "checkUse", new String[]{"username","password"});
			if (wsc.getCheckMsg(userName, password)) {
				String yhm =loginDao.isInSystem(userName);
				if(StringUtils.isNull(yhm)){
					flag="1";
				}else{
					flag="2";	
				}
			}
		}
		return flag;
	}
	public boolean  isInSystem(String userName) throws Exception{
		return StringUtils.isNotNull(loginDao.isInSystem(userName));
	}
	
	/**
	 * 获取周次
	 * @return
	 */
	public String getWeek(){
		return new LoginDao().getWeek();
	}

	public String checkParentUser(String userName, String password) {

		String parentCount = loginDao.getParentUser(userName, password);

		if (Integer.valueOf(parentCount) > 0){
			return PARENT;
		}
		return null;
	}
}
