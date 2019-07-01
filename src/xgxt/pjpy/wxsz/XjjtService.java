package xgxt.pjpy.wxsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
/**
 * �Ƚ�������ѡ
 * @author �����
 * @version 1.0
 */
public class XjjtService {
	/**
	 * ��������
	 * @param saveForm
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjjt(SaveForm saveForm, XjjtModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(saveForm, model, request);
	}
	/**
	 * ȡ����
	 * @param tableName
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getXjjtList(String tableName, XjjtModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		XjjtDao dao = new XjjtDao();
		// TODO �Զ����ɷ������
		return dao.getXjjtList(tableName, model, colList);
	}
	/**
	 * ��������ȡ���ݸ�ֵ��Model
	 * @param model
	 * @param pkValue
	 * @return
	 */
	public XjjtModel getOneXjjt(XjjtModel model,String pkValue){
		XjjtDao dao = new XjjtDao();
		return dao.getOneXjjt( model,pkValue);
	}
	/**
	 * ɾ����������
	 * @param pkValue
	 */
	public void delXjjt(String pkValue) {
		DAO dao = DAO.getInstance();
		try {
			dao.runUpdate2("delete from xjjtb where jtmc||xn||xq = ?",
					new String[] { pkValue });
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	/**
	 * �Ƚ��������
	 * @param pkValue
	 * @param flag
	 * @param flag2
	 * @throws Exception
	 */
	public void shXjjt(String pkValue, String flag, String flag2)
			throws Exception {
		DAO dao = DAO.getInstance();
		if ("sh1".equalsIgnoreCase(flag) && "xysh".equalsIgnoreCase(flag2)) {
			dao.runUpdate2(
					"update xjjtb set xysh='��ͨ��' where jtmc||xn||xq = ?",
					new String[] { pkValue });
		} else if ("sh1".equalsIgnoreCase(flag)
				&& "xxsh".equalsIgnoreCase(flag2)) {
			dao.runUpdate2(
					"update xjjtb set xxsh='��ͨ��' where jtmc||xn||xq = ?",
					new String[] { pkValue });
		} else if ("sh2".equalsIgnoreCase(flag)
				&& "xysh".equalsIgnoreCase(flag2)) {
			dao.runUpdate2(
					"update xjjtb set xysh='δͨ��' where jtmc||xn||xq = ?",
					new String[] { pkValue });
		} else if ("sh2".equalsIgnoreCase(flag)
				&& "xxsh".equalsIgnoreCase(flag2)) {
			dao.runUpdate2(
					"update xjjtb set xxsh='δͨ��' where jtmc||xn||xq = ?",
					new String[] { pkValue });
		} else {
			return;
		}

	}
}
