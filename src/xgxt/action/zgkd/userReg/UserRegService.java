/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ѧ��������̳ע��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-4 ����04:58:06</p>
 */
package xgxt.action.zgkd.userReg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.zgkd.SyltForm;

public class UserRegService {
	UserRegDao dao = null;
   /** �û�ע����Ϣ����*/
   public boolean regToSave(String userType,String userName,UserRegModel model) throws Exception{
	   dao = new UserRegDao(); 
	   return dao.regToSave(model);	       
   }
   /**ע���û���ѯ*/
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
