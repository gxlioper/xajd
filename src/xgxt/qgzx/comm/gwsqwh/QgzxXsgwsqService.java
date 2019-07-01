package xgxt.qgzx.comm.gwsqwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧ_ѧ����λ����-service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class QgzxXsgwsqService {

	QgzxXsgwsqDao qgzxDao = new QgzxXsgwsqDao();
	
	/**
	 * ͨ��������ѯ�б����ݣ������������б�����
	 * @param tableName
	 * @param outZdArray
	 * @param wheresql
	 * @return
	 */
	public List<HashMap<String, String>> getLbList(String tableName, String[] outZdArray, String wheresql) {
		return qgzxDao.getLbList(tableName, outZdArray, wheresql);
	}
	
	/**
	 * ��λ�����б���������
	 * @return
	 */
	public List<HashMap<String, String>> getGwxzList() {
		return getLbList("gwxzdmb", new String[]{"gwxzdm", "gwxzmc"}, "");
	}
	
	/**
	 * ѧ���û��ĸ�λ������Ϣ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqxxByStuTitle() {
		DAO dao = DAO.getInstance();
		return dao.arrayToList(new String[]{ "pk",  "gwxzmc", "yrdwmc", "gwdm",
				"sqsyrs", "sqsyknss", "gwsqjssj", "shzt", "cz" }, new String[]{"pk", "��λ����", "���˵�λ", "��λ����",
				"��Ҫ����", "��Ҫ����������", "�������ʱ��", "Ŀǰ���״̬", "����" });
	}
	
	/**
	 * ��ʦ�û��ĸ�λ������Ϣ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqxxByTeaTitle() {
		DAO dao = DAO.getInstance();
		return dao.arrayToList(new String[]{ "pk",  "gwxzmc", "yrdwmc", "gwdm",
				"sqsyrs", "sqsyknss", "gwsqjssj", "cz" }, new String[]{"pk", "��λ����", "���˵�λ", "��λ����",
				"��Ҫ����", "��Ҫ����������", "�������ʱ��", "����" });
	}
	
	/**
	 *��ѯ��λ��������Ϣ
	 * 
	 * @param qgzxForm
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwxxByResult(
			QgzxXsgwsqForm qgzxForm) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isNotNull(qgzxForm.getXh())) {//ѧ��������
			//��λ��Ϣ�б�
			rs = qgzxDao.queryXsgwxxByStu(qgzxForm);
			
			//���������
			boolean ishmd = queryStuIshmd(qgzxForm);
			
			DAO dao = DAO.getInstance();
			boolean iskns = dao.isKns(qgzxForm.getXh());
			if (rs != null && rs.size() > 0) {
				for (HashMap<String, String> map : rs) {
					if (ishmd) {//���������
						map.put("cz", "hmd");
					} else {
						if (map.get("sqsyrs").equalsIgnoreCase(map.get("sqsyknss")) && !iskns) {//��ʹ�����������������ʱֻ�����Ѳ�������
							map.put("cz", "bksq");
						} else if (!StringUtils.isNull(map.get("xyyj"))
								&& !StringUtils.isNull(map.get("xxyj"))
								&& (!"δ���".equalsIgnoreCase(map.get("xyyj"))
								|| !"δ���".equalsIgnoreCase(map.get("xxyj")))) {//�����޸�
							map.put("cz", "ck");
						} else if ("δ���".equalsIgnoreCase(map.get("xyyj")) && "δ���".equalsIgnoreCase(map.get("xxyj"))) {//���޸�
							map.put("cz", "xg");
						}
					}
				}
			}
		} else {//��ʦ����
			rs = qgzxDao.queryXsgwsqxx(qgzxForm);
		}
		if (rs != null && rs.size() > 0) {
			for (HashMap<String, String> map : rs) {
				if (!StringUtils.isNull(map.get("sqjssj")) && map.get("sqjssj").length() == 14) {
					String time = map.get("sqjssj");
					map.put("sqjssj", time.substring(0, 4) + "��"
							+ time.substring(4, 6) + "��"
							+ time.substring(6, 8) + "�� "
							+ time.substring(8, 10) + ":"
							+ time.substring(10, 12) + ":"
							+ time.substring(12, 14));
				}
			}
		}
		return rs;
	}
	
	/**
	 * ��ȡ��λ��ϸ��Ϣ
	 * @param form
	 * @return
	 */
	public Map<String, String> getGwxx(QgzxXsgwsqForm form){
		return qgzxDao.viewGwxx(form.getGwdmsbsj());
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public Map<String, String> getXsxx(String xh){
		return qgzxDao.viewXsxx(xh);
	}
	
	/**
	 * ��ȡѧ����λ������Ϣ
	 * @param xh ѧ��
	 * @param pkValue ��λ����+��λ�ϱ�ʱ��
	 * @return
	 */
	public Map<String, String> getSqxx(String xh,String pkValue){
		return qgzxDao.viewSqxx(xh, pkValue);
	}
	
	/**
	 * ����ѧ�Ż�ȡ�����Ƿ��Ѿ���ʼ��ˣ���Ϊ0��δ��ʼ��ˣ������޸�������Ϣ���������޸�������Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public String getShCount(String xh,String pkValue){
		return qgzxDao.getShCount(xh,pkValue);
	}
	
	/**
	 * ��������ѯ�б�
	 * @param form
	 * @param userType
	 * @return
	 */
	public List<String[]> queryXsgwsqxx(QgzxXsgwsqForm form, String userType) throws Exception {
		if ("stu".equalsIgnoreCase(userType)) {
			return qgzxDao.queryXsgwsqxxByStu(form);
		} else if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
			return qgzxDao.queryXsgwsqxxByYrdw(form);
		} else {
			return qgzxDao.queryXsgwsqxxByYrdw(form);
		}
	}
	
	/**
	 * ��������ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqTitle() {
		DAO dao = DAO.getInstance();
		return dao.arrayToList(new String[] { "pk", "dis", "r", "xn", "nd",
				"xqmc", "xh", "xm", "bjmc", "gwdm", "sfpks", "xyyj", "xxyj" },
				new String[] { "pk", "dis", "�к�", "ѧ��", "���", "ѧ��", "ѧ��", "����",
						"�༶", "��λ����", "�Ƿ�ƶ����", "���˵�λ���", "ѧУ���" });

	}
	
	/**
	 * ɾ��ѧ����λ������Ϣ
	 * @param qgzxForm
	 * @return
	 * @throws SQLException
	 */
	public String deleteXsgwsqxx(QgzxXsgwsqForm qgzxForm) throws SQLException {
		if (qgzxForm.getPrimarykey_cbv() == null) {
			return "error";
		}
		int[] result = qgzxDao.deleteXsgwsqxx(qgzxForm);
		String flag = "";
		for (int j = 0; j < result.length; j++) {
			if (result[j] > -1) {
				
			} else {
				flag += (j+1) + ",";
			}
		}
		return flag != "" ? flag.substring(0,flag.length() - 1) : "succ";
	}
	
	/**
	 * ����������ʾ��Ϣ
	 * @param msg
	 * @return
	 */
	public String promptMsg(String msg) {
		if ("succ".equalsIgnoreCase(msg)) {
			return "�����ɹ���";
		} else if ("error".equalsIgnoreCase(msg)) {
			return "����ʧ�ܣ�";
		} else {
			return "������ɣ����е�" + msg + "������ɾ��ʧ�ܣ�";
		}
	}
	
	/**
	 * ��֤ѧ���Ƿ������
	 * @param form
	 * @return
	 */
	public boolean queryStuIshmd(QgzxXsgwsqForm form) {
		BaseDAO dao = new BaseDAO();
		return dao.checkExists("qgzx_zgdzdx_hmdb", "xh", form.getXh());
	}
	
	public HashMap<String, String> queryGwsqsjb() {
		return qgzxDao.queryGwsqsjb();
	}
}
