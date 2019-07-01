package xgxt.pjpy.zjcm.xnjxj;

import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class XnjxjService {

	XnjxjDAO dao = new XnjxjDAO();

	XnjxjNewDAO myDAO = new XnjxjNewDAO();
	/**
	 * @author luo
	 * @describe ��ñ�ͷ
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	/**
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @describe ����ۺϷ��б�
	 */
	public ArrayList<String[]> getZhfList(String tableName, XnjxjModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getZhfList(tableName, model, colList);
	}

	/**
	 * @author luo
	 * @describe �ж��Ƿ����С���Ա
	 */
	public boolean isCpzCy(String xh, String xydm) {
		return dao.isCpzCy(xh, xydm);
	}

	/**
	 * @author luo
	 * @describe ���ѧ��������Ϣ
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return XnjxjDAO.getStuInfo(xh);
	}

	/**
	 * @author luo
	 * @describe �жϵ�½ʱ���Ƿ��������õ�ʱ�䷶Χ��
	 */
	public boolean inTime(String xydm) {
		return dao.inTime(xydm);
	}

	/**
	 * @author luo
	 * @describe ���ѧ���б�
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}

	/**
	 * ����걨����Ϣ
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbzXx(String xh, String xn, String xq) {
		return dao.getSbzXx(xh, xn, xq);
	}

	/**
	 * ����걨�߿��δ���
	 * 
	 * @throws Exception
	 */
	public String getKkcs(String xh, String xn, String xq) {
		return dao.getKkcs(xh, xn, xq);
	}
	
	/**
	 * ���Ӣ��ȼ������б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYyList() {
		return dao.getYyList();
	}

	/**
	 * ��ý�ѧ���б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlb) {
		return dao.getJxjList(jxjlb);
	}

	/**
	 * ��ü�����ȼ��б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJsjList() {
		return dao.getJsjList();
	}

	/**
	 * ���Υ�ʹ����б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		return dao.getWjcfList(xh);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ������
	 */
	public boolean saveJxjsq(XnjxjModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveJxjsq(model, request);
	}

	/**
	 * @author luo
	 * @describe ��ý�ѧ�������б�
	 */
	public ArrayList<String[]> getJxjSqList(String tableName, XnjxjModel model,
			String[] colList, String userType, String doType)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getJxjSqList(tableName, model, colList, userType, doType);
	}

	/**
	 * ���ѧ������
	 * 
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) {
		return dao.getXqmc(xqdm);
	}

	/**
	 * ��ý�ѧ������б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjLbList() {
		return dao.getJxjLbList();
	}

	/**
	 * ����걨��ѧ����Ϣ
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbJxjXx(String pk) {
		return dao.getSbJxjXx(pk);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ�����
	 */
	public boolean saveJxjsh(XnjxjModel model, String pk, String shzt,
			String userType, HttpServletRequest request) throws Exception {
		return dao.saveJxjsh(model, pk, shzt, userType, request);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ����μ���ѧ�����
	 */
	public boolean saveCjJxjsh(XnjxjModel model, String pk, String shzt,
			String userType) throws Exception {
		return dao.saveCjJxjsh(model, pk, shzt, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ�����
	 */
	public String saveJxjsh(String[] key, String jxjdm, String shzt,
			String userType) throws Exception {
		return dao.saveJxjsh(key, jxjdm, shzt, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ɾ����ѧ��
	 */
	public boolean delJxjSq(String[] key,String userType) throws Exception {
		return dao.delJxjSq(key,userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe �жϽ�ѧ������
	 */
	public String Jxjtj(XnjxjModel model, String zhpm) {
		return dao.Jxjtj(model, zhpm);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ���潱ѧ�����
	 */
	public boolean saveXwjxjSb(String[] key, String jxjdm) throws Exception {
		return dao.saveXwjxjSb(key, jxjdm);
	}

	/**
	 * @author luo
	 * @throws Exception 
	 * @describe �жϽ�ѧ�������
	 */
	public boolean hadJxj(String pk) {
		return dao.hadJxj(pk);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��ý�ѧ�����
	 */
	public String getJxjlb(String jxjdm) {
		return dao.getJxjlb(jxjdm);
	}

	/**
	 * ��ý�ѧ����������
	 */
	public String getJxjRs(String jxjdm, String bjdm) {
		return dao.getJxjRs(jxjdm, bjdm);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe ��ý�ѧ������
	 */
	public String getHdJxjRs(String jxjdm,String bjdm) {
		return dao.getHdJxjRs(jxjdm,bjdm);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe ����μ���ѧ�����
	 */
	public String saveCjJxjsh(String[] key, String jxjdm, String shzt)
			throws Exception {
		return dao.saveCjJxjsh(key, jxjdm, shzt);
	}
	
	/**
	 * �жϴμ�����
	 */
	public String getCjJxjRs(String jxjdm, String bjdm, String num) {
		return dao.getCjJxjRs(jxjdm, bjdm, num);
	}
	
	/**
	 * У�ڽ�ѧ���б�
	 * @param jxjlb
	 * @return
	 */
	public List<HashMap<String, String>> getXnjxjList(String jxjlb) {
		return dao.getXnjxjList(jxjlb);
	}
	
	
	/******************************** ���μ��� 2010-4-12 ********************************/
	public List<String[]> queryJxhshResult(XnjxjModel model, String userType,
			String isFdy, String userName) throws Exception {
		
		return myDAO.queryJxhshResult(model, userType, isFdy, userName);
	}
	
	/**
	 * ��ѯ��ѧ���ͷ
	 * 
	 * @param model
	 * @param userType
	 * @param isFdy
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjshTitle(XnjxjModel model,
			String userType, String isFdy) {
		String[] en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
				"jxjmc", "dypm", "zypm", "typm", "zhpm", "sh" };
		String[] cn = new String[] { "pk","color", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��",
				"��ѧ��", "��������", "��������", "��������", "�۲�����", "ѧУ���" };
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"jxjmc", "dypm", "zypm", "typm", "zhpm", "sh", "xysh",
						"xxsh" };
				cn = new String[] { "pk","color", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��",
						"��ѧ��", "��������", "��������", "��������", "�۲�����", "����Ա���", "ѧԺ���",
						"ѧУ���" };
			} else {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"jxjmc", "dypm", "zypm", "typm", "zhpm", "sh", "xxsh" };
				cn = new String[] { "pk","color", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��",
						"��ѧ��", "��������", "��������", "��������", "�۲�����", "ѧԺ���", "ѧУ���" };
			}
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯ�༶ʵ�ʻ�ͨ������
	 * @param model
	 * @param isFdy
	 * @return
	 */
	public String queryBjhjrs(XnjxjModel model, String isFdy) {
		String appendSql = " and xysh='ͨ��'";
		if (UserTypePd.isFdy(isFdy)) {
			appendSql = " and fdysh='ͨ��'";
		}
		return myDAO.queryBjhjtgrs(model, appendSql);
	}
	
	/**
	 * ��ѯ�༶������
	 * @param model
	 * @return
	 */
	public String queryBjhjme(XnjxjModel model) {
		return myDAO.queryBjhjme(model);
	}
	
	/**
	 * �޸Ľ�ѧ����˽��
	 * @param pkValue
	 * @param jg
	 * @param userType
	 * @param isFdy
	 * @return
	 * @throws SQLException
	 */
	public boolean updateJxjshResult(String[] pkValue, String jg,
			String userType, String isFdy) throws SQLException{
		if (pkValue != null && pkValue.length > 0) {
			return myDAO.updateJxjshResult(pkValue, jg, 
					UserTypePd.isXy(userType) ? 
							(UserTypePd.isFdy(isFdy) ? "fdysh" : "xysh") : "xxsh");
		} else {
			return false;
		}
	}
	
	/**
	 * ��ѯ������ѧ����ϸ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryXnjxjDetails(String pkValue,
			String userType, String fdyFlag) {
		return myDAO.queryXnjxjDetails(pkValue, userType, fdyFlag);
	}
	
	//����б�
	public List<HashMap<String, String>> getShList() {
		DAO dao = DAO.getInstance();
		return dao.getChkList(3);
	}
	
	/**
	 * �޸Ľ�ѧ�𵥸���˽��
	 * @param shzd fdysh,xysh,xxsh
	 * @param shyj fdyyj,xyyj,xxyj
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateXnjxjshResult(String userType, String isFdy,
			XnjxjModel model, String pkValue) throws Exception {
		String  zd = UserTypePd.isXy(userType) ? 
				(UserTypePd.isFdy(isFdy) ? "fdy" : "xy") : "xx";
		return myDAO.updateXnjxjshResult(zd + "sh", zd + "yj", zd + "shsj",
				model, pkValue);
	}
}
