package xgxt.szdw.xmlg.bgtj;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class BgtjService {

	BgtjDAO dao = new BgtjDAO();

	/**
	 * @describe ��ø���Ա��Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getFdyxx(String[] colList, String zgh) {
		return dao.getFdyxx(colList, zgh);
	}

	/**
	 * @describe ����ύ����
	 * @author luo
	 */
	public List<HashMap<String, String>> getLxList(String tableName) {
		return dao.getLxList(tableName);
	}

	/**
	 * @describe ����ύ����
	 * @author luo
	 */
	public List<HashMap<String, String>> getMcList(String tableName, String lx) {
		return dao.getMcList(tableName, lx);
	}

	/**
	 * @describe ���汨���ύ
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
