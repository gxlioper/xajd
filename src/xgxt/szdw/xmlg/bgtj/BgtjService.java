package xgxt.szdw.xmlg.bgtj;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class BgtjService {

	BgtjDAO dao = new BgtjDAO();

	/**
	 * @describe 获得辅导员信息
	 * @author luo
	 */
	public HashMap<String, String> getFdyxx(String[] colList, String zgh) {
		return dao.getFdyxx(colList, zgh);
	}

	/**
	 * @describe 获得提交类型
	 * @author luo
	 */
	public List<HashMap<String, String>> getLxList(String tableName) {
		return dao.getLxList(tableName);
	}

	/**
	 * @describe 获得提交名称
	 * @author luo
	 */
	public List<HashMap<String, String>> getMcList(String tableName, String lx) {
		return dao.getMcList(tableName, lx);
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
		return dao.saveBg(tableName, model, colList, request);
	}
}
