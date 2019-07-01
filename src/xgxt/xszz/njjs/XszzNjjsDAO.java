package xgxt.xszz.njjs;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.dtjs.DtjsDAO;
import xgxt.utils.MakeQuery;
import xgxt.xszz.XszzTyForm;

public class XszzNjjsDAO extends DtjsDAO {
	
	/**
	 * @describe 同步学费信息
	 * @author luo
	 */
	public boolean tbXfxx() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call xszz_xfxx_tb()}", new String[] {});
	}
	
	/**
	 * @describe 获得收费相关信息
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getSfxxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] { "xn", "nd" };

		String[] queryLikeList = new String[] { "xh" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		StringBuilder sql = new StringBuilder();
		sql.append("select xfsfxm,xfyjje,xfsjje,xfsfqf,xfjmje from njjs_xfxxb ");
		sql.append(myQuery.getQueryString());
		sql.append(" order by xfsfxm");

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "xfsfxm", "xfyjje", "xfsjje",
				"xfsfqf", "xfjmje" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
}
