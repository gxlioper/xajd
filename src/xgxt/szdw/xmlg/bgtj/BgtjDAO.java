package xgxt.szdw.xmlg.bgtj;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.xmlg.XmlgSzdwDAO;
import xgxt.utils.FormModleCommon;

public class BgtjDAO extends XmlgSzdwDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @describe 获得提交类型
	 * @author luo
	 */
	public List<HashMap<String, String>> getLxList(String tableName) {
		String sql = "select dm,mc from " + tableName + " order by dm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @describe 保存报告提交
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public boolean saveBg(String tableName, BgtjModel model, String[] colList,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] inputList = FormModleCommon.modelToStrings(colList, model);
		boolean flg = StandardOperation.insert(tableName, colList, inputList,
				request);
		return flg;
	}
}
