
package xgxt.action.zgkd.sxjy;

import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.szdw.dao.common.CommonDAO;
/**
* <p>Title: 学工管理系统</p>
* <p>Description: 学生信息管理思想教育-中国矿大-学生生涯DAO类</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: zfsoft</p>
* @author 鲁宁
* @version 1.0
*/

public class XssyDAO extends CommonDAO{
   
	private static XssyDAO mydao = new XssyDAO();
	public static XssyDAO getInstance(){
		return mydao;
	}
	private DAO dao = DAO.getInstance();
	
	/**
	 * 返回过滤条件
	 * @return
	 */
	public List<HashMap<String,String>> getFilterCondi(){
		String[] value = {"0","1","2","3","4"};
		String[] condi = {"已填写","全部","未填写","已回复","未回复"};
		return dao.arrayToList(value, condi);
	}

	public List<HashMap<String,String>> getXzztList() {
		String[] value = {"发展对象","入党积极分子","预备党员","正式党员"};
		String[] condi = {"发展对象","入党积极分子","预备党员","正式党员"};
		//中国矿大没有入党积极分子
		if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(Base.xxdm)) {
			value = new String[]{"发展对象","预备党员","正式党员"};
			condi = new String[]{"发展对象","预备党员","正式党员"};
		}
		return dao.arrayToList(value, condi);
	}
}
