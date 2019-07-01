package xgxt.pjpy.scjz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

public class PjpyScjzService {

	private PjpyScjzDAO dao = PjpyScjzDAO.getInstance();
	
	public static PjpyScjzService service = new PjpyScjzService();
	
	public static PjpyScjzService getInstance() {
		return service;
	}
	
	/**
	 * ��ȡѧ��������Ϣ��ѧϰ�ɼ���Ϣ�༶������רҵ����
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuDetailsInfo(String xh) throws Exception {
		return dao.getStuDetailsInfo(xh);
	}
	
	/**
	 * ��ȡѧ��������ѧ��Ĵ�����Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfInfo(String xh) throws Exception {
		return dao.getStuWjcfInfo(xh);
	}
	
	/**
	 * ���ѧ��������ѧ���Ƿ��гɼ�������
	 * @param rs
	 * @return
	 */
	public String chkCjtj(HashMap<String, String> rs) {
		if (rs != null) {
			String zdcj = rs.get("zdcj");
			double cj = StringUtils.isNull(zdcj) ? 0 : Double.parseDouble(zdcj);
			if (cj<60) {
				return "����������ѧ���б��޿γɼ������񣬲���������������";
			}
		}
		return "";
	}
	
	/**
	 * ���潱ѧ��������Ϣ
	 * @param model
	 * @param reuqest
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjsqInfo(ScjzModel model, HttpServletRequest reuqest)
			throws Exception {
		return dao.saveJxjsqInfo(model, reuqest);
	}
	
	/**
	 * �޸Ľ�ѧ����Ϣ
	 * @param model
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjsqInfo(ScjzModel model, String pkValue, HttpServletRequest request) throws Exception {
		return dao.updateJxjsqInfo(model, pkValue, request);
	}
	
	/**
	 * ɾ����ѧ����Ϣ
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteJxjSqInfo(String[] keys) throws Exception {
		return dao.deleteJxjSqInfo(keys);
	}
	
	public HashMap<String, String> viewJxjSqInfo(String pkValue) throws Exception {
		return dao.viewJxjSqInfo(pkValue);
	}
	
	/**
	 * ��ѯ��ѧ�������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjshInfo(String pkValue) throws Exception {
		return dao.getJxjshInfo(pkValue);
	}
	
	public String jxjsh(String userType, String isFdy, String cxcj, String yesNo, String shyj, String pkValue, String jxjdm, String jlje, String xydm) throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			if ("true".equalsIgnoreCase(isFdy)) {
				return dao.fdysh(pkValue, cxcj, yesNo, shyj, jxjdm, jlje, xydm);
			} else {
				return dao.xysh(pkValue, cxcj, yesNo, shyj, jxjdm, jlje, xydm);
			}
		} else {
			dao.xxsh(pkValue, yesNo, shyj);
			return "";
		}
	}
	
	public String[] getJe(String je) throws Exception {
		if (!StringUtils.isNull(je) && (je.lastIndexOf("-") != -1)) {
			return je.split("-");
		} else {
			return new String[]{"", ""};
		}
	}
}
