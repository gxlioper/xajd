/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 大学生生涯论坛注册Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-4 下午04:58:06</p>
 */
package xgxt.action.zgkd.userReg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.zgkd.SyltForm;

public class UserRegService {
	UserRegDao dao = null;
   /** 用户注册信息保存*/
   public boolean regToSave(String userType,String userName,UserRegModel model) throws Exception{
	   dao = new UserRegDao(); 
	   return dao.regToSave(model);	       
   }
   /**注册用户查询*/
   public List<String[]> getRegUserResult(SyltForm syltForm) throws Exception {
	   dao = new UserRegDao();
   	   return dao.getRegUserResult(syltForm);
   }
   public List<HashMap<String, String>> getregUserQerTitle() throws Exception {
	   dao = new UserRegDao(); 
	   return dao.getregUserpTitle();
   }
   public int regUserDel(String[] keys, HttpServletRequest request) throws Exception{
	   dao = new UserRegDao();
	   return dao.regUserDel(keys,request);
   }
}
