package xgxt.pjpy.zjcm.rych;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.zjcm.xnjxj.XnjxjModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.UserTypePd;

public class RychService {
	RychDAO myDAO= new RychDAO();
	RychNewDAO dao = new RychNewDAO();
	public HashMap<String, String> serv_getXsInfo(String xh) {
		// TODO �Զ����ɷ������
		return CommonQueryDAO.commonQueryOne("view_stu_details", new String[]{"xh","xm","xb","xymc","zymc","bjmc","zzmmmc","mzmc","csrq"}, "xh", xh);
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe �������ѧ��ѧ��ѧ�������ͳɼ�
	 */
	public HashMap<String, String> getCjPm(String xh,String xn,String xq) {	
		HashMap<String, String> map= CommonQueryDAO.commonQueryOne("zjcm_zhf", new String[]{"dyf","zyf","zhf","zhpm"}, "xh||xn||xq", xh+xn+xq);
		map.put("dyfpm",myDAO.getPm(xh, xn, xq, "dyf"));
		map.put("zyfpm",myDAO.getPm(xh, xn, xq, "zyf"));
		return map;
	}
	
	/**
	 * ��ȡ�����ƺ��б�
	 * @return
	 */
	public List<HashMap<String,String>>serv_getRychList(){
		return myDAO.getRychList();
	}
	
	/**
	 * �жϸ��������������ƺ��Ƿ��ظ����Ƿ�ͨ�����
	 */
	public HashMap<String,String> serv_rychSqPd(RychModel model){
		String pk = "xh||xn||xqdm||rychdm";
		String querry = " where  "+pk+"='"+model.getXh()+Base.getJxjsqxn()+Base.getJxjsqxq()+model.getRychdm()+"' and xysh='ͨ��' ";
		return CommonQueryDAO.dao_getInfo("zjcm_rychsqb",new String[]{"xh"},querry);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe �ж������ƺ�����
	 */
	public String Rychtj(RychModel model, String zhpm) {
		return myDAO.Rychtj(model, zhpm);
	}

	public boolean serv_rychSave(RychModel model) throws Exception {
		// TODO �Զ����ɷ������
		return myDAO.serv_rychSave(model);
	}

	public boolean saveRychsh(String[] checkVal, String shrych, String shzt, String userType) throws SQLException {
		// TODO �Զ����ɷ������
		return myDAO.saveRychSh(checkVal,shrych,shzt,userType);
	}

	public boolean delRychSq(String[] checkVal) throws SQLException {
		// TODO �Զ����ɷ������
		return myDAO.delRychSq(checkVal);
	}

	public ArrayList<String[]> getRychSqList(String tableName, RychModel model, String[] colList, String userType, String string) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO �Զ����ɷ������
		return myDAO.getRychSqList(tableName, model, colList,userType, string);
	}

	public boolean saveRychsh(RychModel model, String pk, String shzt, String userType, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return myDAO.saveRychsh(model,pk, shzt, userType, request);
	}

	public HashMap<String, String> getXsRychXx(String pk) {
//		 TODO �Զ����ɷ������
		return myDAO.getXsRychXx(pk);
	}
	
	/**
	 * ���Υ�ʹ����б�
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		return myDAO.getWjcfList(xh);
	}
	
	/**
	 * ���Υ�ʹ����б�
	 * @throws SQLException 
	 * 
	 * @throws Exception
	 */
	public boolean delRych(String[] checkVal) throws SQLException {
		return myDAO.delRychSq(checkVal);
	}

	public HashMap<String, String> getPrintXx(HashMap<String, String> rs) {
		return myDAO.getPrintXx(rs);
	}
	
	//���������ƺŴ���������
	public String getRychmc(String rychdm) {
		return myDAO.getRychmc(rychdm);
	}
	
	
	/************************���μ��� 2010-4-20 ***********************/
	
	/**
	 * ��ѯ����ѧԺ���������ƺŵĲ�����Χ
	 * @param model
	 * @return ������ض�������,���������־ 2Ϊ���ض�������
	 * @throws SQLException
	 */
	public String queryRychCpfwByXydm(RychModel model) throws SQLException{
		return dao.queryRychCpfwByXydm(model);
	}
	
	/**
	 * ��ѯ�����ƺű�ͷ
	 * 
	 * @param model
	 * @param userType
	 * @param isFdy
	 * @return
	 */
	public List<HashMap<String, String>> queryRychshTitle(
			String userType, String isFdy) {
		String[] en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
				"rychmc", "dypm", "zypm", "typm", "zhpm", "sh" };
		String[] cn = new String[] { "pk","color", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��",
				"�����ƺ�", "��������", "��������", "��������", "�۲�����", "ѧУ���" };
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"rychmc", "dypm", "zypm", "typm", "zhpm", "sh", "xysh",
						"xxsh" };
				cn = new String[] { "pk","color", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��",
						"�����ƺ�", "��������", "��������", "��������", "�۲�����", "����Ա���", "ѧԺ���",
						"ѧУ���" };
			} else {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"rychmc", "dypm", "zypm", "typm", "zhpm", "sh", "xxsh" };
				cn = new String[] { "pk","color", "dis", "�к�", "ѧ��", "����", "�༶", "ѧ��", "ѧ��",
						"�����ƺ�", "��������", "��������", "��������", "�۲�����", "ѧԺ���", "ѧУ���" };
			}
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	public List<String[]> queryRychshResult(RychModel model, String userType,
			String fdyFlag, String userName) throws Exception {
		return dao.queryRychshResult(model, userType, fdyFlag, userName);
	}
	
	/**
	 * ��ѯ�����ƺŻ�����
	 * @param model
	 * @param bmdm
	 * @param bmlb
	 * @return
	 */
	public String queryRychHjrs(RychModel model,  String bmlb) {
		String bmdm = getBmdm(model, bmlb);
		return dao.queryRychHjrs(model, bmdm, bmlb);
	}

	private String getBmdm(RychModel model, String bmlb) {
		String bmdm = "xydm".equalsIgnoreCase(bmlb) ? model.getXydm() : ("zydm"
				.equalsIgnoreCase(bmlb) ? model.getZydm() : ("bjdm"
				.equalsIgnoreCase(bmlb) ? model.getBjdm() : ""));
		return bmdm;
	}
	
	/**
	 * ��ѯ���������ƺ�ͨ������
	 * @param model
	 * @param appendSql
	 * @return
	 */
	public String queryRychTgrs(RychModel model, String bmlb, String isFdy) {
		String bmdm = getBmdm(model, bmlb);
		StringBuilder appendSql = new StringBuilder(" and xysh='ͨ��'");
		if (UserTypePd.isFdy(isFdy)) {
			appendSql = new StringBuilder(" and fdysh='ͨ��'");
		}
		appendSql.append("  and exists (select 1 from view_xsjbxx b where a.pjxh=b.xh and ")
		.append(bmlb)
		.append(" = ?)");
		return dao.queryRychTgrs(model, appendSql.toString(), bmdm);
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
	public boolean updateRychResult(String[] pkValue, String jg,
			String userType, String isFdy) throws SQLException{
		if (pkValue != null && pkValue.length > 0) {
			return dao.updateRychResult(pkValue, jg, 
					UserTypePd.isXy(userType) ? 
							(UserTypePd.isFdy(isFdy) ? "fdysh" : "xysh") : "xxsh");
		} else {
			return false;
		}
	}
	
	/**
	 * ��ѯ���������ƺ���ϸ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryRychDetails(String pkValue,
			String userType, String fdyFlag) {
		return dao.queryRychDetails(pkValue, userType, fdyFlag);
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
	public boolean updateRychshResult(String userType, String isFdy,
			RychModel model, String pkValue) throws Exception {
		String  zd = UserTypePd.isXy(userType) ? 
				(UserTypePd.isFdy(isFdy) ? "fdy" : "xy") : "xx";
		return dao.updateRychshResult(zd + "sh", zd + "yj", zd + "shsj",
				model, pkValue);
	}
	
//	����б�
	public List<HashMap<String, String>> getShList() {
		DAO dao = DAO.getInstance();
		return dao.getChkList(3);
	}
}
