package xgxt.rcsw.nthy;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import common.newp.ArrayUtil;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.date.DateUtils;

/**
 * ѧ�ѻ�������SERVICE
 */
public class XfhjglService {

	XfhjglDao dao = new XfhjglDao();
	 
	/**
	 * ��ѯѧ�ѻ���������Ϣ
	 * @return
	 */
	public HashMap<String, String> queryXfhjkg() {
		return dao.queryXfhjkg(); 
	}
	
	/**
	 * ����ѧ�ѻ���������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjkg(XfhjglActionForm form) throws Exception{
		HashMap<String, String> map = queryXfhjkg();
		if ("0".equalsIgnoreCase(form.getKg())) {
			form.setHjjssj("");
			form.setHjkssj("");
		}
		if (map != null && map.size() > 0) {
			return dao.updateXfhjkg(form);
		} else {
			return dao.insertXfhjkg(form);
		}
	}
	
	/**
	 * ����ѧ���Ƿ����뻺����Ϣ
	 * @param xh
	 * @return
	 */
	public String checkXsxfsfhj(String xh) {
		XfhjglActionForm form = new XfhjglActionForm();
		form.setXh(xh);
		form.setXn(Base.currXn);
		HashMap<String, String> map = dao.queryXsxfsfqf(form);
		String data = "";
		if (map != null && map.size() > 0) {//����Ƿ�����
			map = new HashMap<String, String>();
			map = dao.queryXsxfhjxx(form);
			if (map == null || map.get("xh") == null) {//û�н��л�������
				HashMap<String, String> rs = dao.queryXfhjkg();
				if ("1".equalsIgnoreCase(rs.get("kg"))) {//�������뿪���Ѵ�
					String kssj = rs.get("chkhjkssj");
					String jssj = rs.get("chkhjjssj");
					String currsj = DateUtils.getTime();
					if (!StringUtil.isNull(kssj)
							&& !StringUtil.isNull(jssj)
							&& (Float.parseFloat(kssj) <= Float
									.parseFloat(currsj))
							&& (Float.parseFloat(currsj) <= Float
									.parseFloat(jssj))) {//��ǰʱ����Խ��л���
						data = "ksq";
					} else {
						data = "bksq";
					}
				} else if (rs == null || rs.size()<=0 || rs.get("kg") == null) {
					data = "ksq";
				} else {
					data = "bksq";
				}
			} else {
				if ("ͨ��".equalsIgnoreCase(map.get("xxsh")) && "ͨ��".equalsIgnoreCase(map.get("xysh")) && "ͨ��".equalsIgnoreCase(map.get("fdysh"))) {
					data = "yhj";
				} else {
					data = "shz";
				}
			}
		} else {
			data = "wqf";
		}
		return data;
	}
	
	/**
	 * ����ѧ���Ƿ���Խ���ѧ��ע��
	 * 
	 * @param xh  ѧ��
	 * @param xn  ѧ�� 
	 * @return  ����TRUE�����ע�ᣬ����FALSE���ܽ���ע�� ҪôǷ�ѣ�Ҫôû�н���ѧ�ѻ�����
	 */
	public boolean checkXssfkzc(String xh, String xn) {
		boolean flag = true;
		XfhjglActionForm form = new XfhjglActionForm();
		form.setXh(xh);
		form.setXn(xn);
		HashMap<String, String> map = dao.queryXsxfsfqf(form);
		if (map != null && map.get("xh")!= null) {//����Ƿ�����
			map = new HashMap<String, String>();
			map = dao.queryXsxfhjxx(form);
			if (map != null && "ͨ��".equalsIgnoreCase(map.get("xxsh"))
					&& "ͨ��".equalsIgnoreCase(map.get("xysh"))
					&& "ͨ��".equalsIgnoreCase(map.get("fdysh"))) {//�ѻ�����
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * ��ѯѧ��ѧ�ѻ����Ƿ�������
	 * @param form
	 * @return
	 */
	public HashMap<String, String> queryXfhjsqxx(XfhjglActionForm form) {
		return dao.queryXfhjsqxx(form);
	}
	
	/**
	 * ����ѧ�ѻ���������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjsqxx(XfhjglActionForm form) throws Exception{
		HashMap<String, String> rs = queryXfhjsqxx(form);
		if (rs != null && rs.size() >0) {
			return dao.updateXfhjsqxx(form);
		} else {
			return dao.insertXfhjsqxx(form);
		}
	}
	
	/**
	 * ��ѯѧ��ѧ�ѻ����Ƿ�������
	 * @param form
	 * @return
	 */
	public String checkXfhjsqxx(String xh, String xn) {
		XfhjglActionForm form = new XfhjglActionForm();
		form.setXh(xh);
		form.setXn(xn);
		HashMap<String, String> rs = dao.queryXfhjsqxx(form);
		if (rs == null || rs.get("xh")==null) {
			return "ksq";
		}
		if (rs != null &&  ("ͨ��".equalsIgnoreCase(rs.get("xxsh"))
				|| "ͨ��".equalsIgnoreCase(rs.get("xysh"))
				|| "ͨ��".equalsIgnoreCase(rs.get("fdysh")))) {
			return "shz";
		} else {
			return "";
		}
	}
	
	/**
	 * ��ѯѧ�ѻ�����˱�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryXfhjshTitle(XfhjglActionForm form) {
		String[] en = {"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg"};
		String[] cn = {"pk", "dis","�к�", "ѧ��", "ѧ��", "����", "�Ա�", "�༶", "���ʱ��", "ѧУ���"};
		
		if ("xy".equalsIgnoreCase(form.getUserType())) {
			en = new String[]{"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg", "sjsh"};
			cn = new String[]{"pk","dis", "�к�", "ѧ��", "ѧ��", "����", "�Ա�", "�༶", "���ʱ��", "ѧԺ���", "ѧУ���"};
		}
		if (form.isFdy()) {
			en = new String[]{"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg", "sjsh"};
			cn = new String[]{"pk", "dis","�к�", "ѧ��", "ѧ��", "����", "�Ա�", "�༶", "���ʱ��", "����Ա���", "ѧԺ���"};
		}
		return ArrayUtil.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯѧ�ѻ�����˽��
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjshResult(XfhjglActionForm form) throws Exception{
		if ("xy".equalsIgnoreCase(form.getUserType())) {
			if (form.isFdy()) {
				return dao.queryXfhjfdyshResult(form);
			} else {
				return dao.queryXfhjxyshResult(form);
			}
		} else {
			return dao.queryXfhjxxshResult(form);
		}
	}
	
	/**
	 * ��ѯѧ����˵�����Ϣ
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> queryXfhjshxx(String pk) { 
		return dao.queryXfhjshxx(pk);
	}
	/**
	 * �޸������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfhjshxx(XfhjglActionForm form) throws Exception{ 
		return dao.updateXfhjshxx(form);
	}
	
	/**
	 * �������ѧ�ѻ�����Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean plshXfhjxx(XfhjglActionForm form) throws Exception{ 
		if (form.getPk() == null || form.getPk().length==0) return false;
		StringBuffer sql = new StringBuffer();
		for (int i=0;i<form.getPk().length;i++) {
			sql.append("update xg_rcsw_nthy_xfhjsqb set ");
			if ("xy".equalsIgnoreCase(form.getUserType())) {
				if (form.isFdy()) {
					sql.append(" fdysh='");
					sql.append(form.getShjg());
					sql.append("',fdyyj='");
					sql.append(form.getShyj());
					sql.append("',fdyshsj='");
					sql.append(form.getShsj());
					sql.append("',fdyzgh='");
					sql.append(form.getZgh());
					sql.append("' where xh||xn = '");
					sql.append(form.getPk()[i]);
					sql.append("'!@");
				} else {
					sql.append(" xysh='");
					sql.append(form.getShjg());
					sql.append("',xyyj='");
					sql.append(form.getShyj());
					sql.append("',xyshsj='");
					sql.append(form.getShsj());
					sql.append("',xyzgh='");
					sql.append(form.getZgh());
					sql.append("' where xh||xn = '");
					sql.append(form.getPk()[i]);
					sql.append("'!@");
				}
			} else {
				sql.append(" xxsh='");
				sql.append(form.getShjg());
				sql.append("',xxyj='");
				sql.append(form.getShyj());
				sql.append("',xxshsj='");
				sql.append(form.getShsj());
				sql.append("',xxzgh='");
				sql.append(form.getZgh());
				sql.append("' where xh||xn = '");
				sql.append(form.getPk()[i]);
				sql.append("'!@");
			}
		}
		DAO mydao = DAO.getInstance();
		return mydao.checkBatch(mydao.runBatch(sql.toString().split("!@")));
	}
	
	/**
	 * ��ѯѧ�ѻ��������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjjg(XfhjglActionForm form) throws Exception { 
		return dao.queryXfhjjg(form);
	}
	
	/**
	 * ѧ�ѻ�����ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryXfhjjgTitle() {
		String[] en = {"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "fdysh", "xysh", "xxsh"};
		String[] cn = {"pk", "dis","�к�", "ѧ��", "ѧ��", "����", "�Ա�", "�༶", "����Ա���", "ѧԺ���", "ѧУ���"};
		return ArrayUtil.arrayToList(en, cn);
	} 
	
	/**
	 * ɾ��ѧ�ѻ���������Ϣ
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public boolean deleteXfhjsqxx(String[] pk) throws Exception{ 
		return dao.deleteXfhjsqxx(pk);
	}
}
