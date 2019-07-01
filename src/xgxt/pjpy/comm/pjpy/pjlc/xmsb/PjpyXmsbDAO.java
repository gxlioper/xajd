package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ�ϱ�_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyXmsbDAO extends PjpyCommDAO {

	/**
	 * �����Ŀ�ϱ�ѧ���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXsList(PjpyXmsbForm model,
			User user) {

		PjpyCommService commService = new PjpyCommService();
		
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// ��Ŀ����
		String xmdm = model.getXmdm();

		// ��Ŀ����model��ʼ��
		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = commService.getXmszForXmdm(pk);

		// ��������
		String sqzq = xmszModel.getSqzq();

		if ("xn".equalsIgnoreCase(sqzq)) {
			pjxq = "��";
			pjnd = "��";
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			pjnd = "��";
		} else {
			pjxq = "��";
			pjxq = "��";
		}
		
		// �۲�����
		String zcpm = model.getZcpm();
		String pmzd = "";
		String zypmzd = "";
		if ("3".equalsIgnoreCase(zcpm)) {
			pmzd="zcfbjpm";
		} else if ("2".equalsIgnoreCase(zcpm)) {
			pmzd="zcfnjzypm";
		}
		
		String zypm = model.getZypm();
		if ("3".equalsIgnoreCase(zypm)) {
			zypmzd="zyfbjpm";
		} else if ("2".equalsIgnoreCase(zypm)) {
			zypmzd="zyfnjzypm";
		}
		
		// �༶����
		String bjdm = model.getBjdm();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.xh, a.xm, a.xb, a.pjbjmc bjmc,a.pjbjmc,b.zd1 zhf,");
		sql.append(" case when length(b."+pmzd+") = '1' then '0'||b."+pmzd);
		sql.append(" else b."+pmzd+" end pm,");
		sql.append(" case when length(b."+pmzd+") = '1' then '0'||b."+pmzd);
		sql.append(" else b."+pmzd+" end zcfbjpm,");
		sql.append(" b.zd3 zyf, ");
		sql.append(" case when length(b."+pmzd+") = '1' then '0'||b."+pmzd);
		sql.append(" else b."+pmzd+" end zyfpm,");
		
		sql.append(" c.xh ysq,'' cz  ");
		sql.append("from view_xg_pjpy_ryqd a ");
		//�۲�
		sql.append("left join xg_pjpy_zhcpb b on a.xh = b.xh ");
		sql.append("and a.pjxn = b.xn ");
		//������
		sql.append("left join (");
		sql.append("select xh from xg_pjpy_pjxmsqb ");
		sql.append("where 1 = 1 ");
		sql.append("and xmdm = '" + xmdm + "' ");
		sql.append("and pjxn = '" + pjxn + "' ");
		sql.append("and pjxq = '" + pjxq + "' ");
		sql.append("and pjnd = '" + pjnd + "' ");
		sql.append(") c on a.xh = c.xh ");
		
		sql.append("where a.pjbjdm = ? ");
		sql.append(") ");
		
		// ����
		String arrange = model.getArrange();
		// ����ʽ
		String fashion = model.getFashion();
		
		if ("no".equalsIgnoreCase(arrange)) {// ��������
			sql.append("order by to_number(" + pmzd + ") ");
		} else {
			sql.append("order by " + arrange + " ");
			sql.append(fashion);
		}

		return getRsList("", "", new String[] { bjdm }, new String[] { "xh",
				"xm", "xb", "pjbjmc", "zhf", "pm","zyf","zyfpm", "ysq","cz" }, sql.toString());
	}
	
	/**
	 * ����¼���ȡ��Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXmOption(PjpyXmsbForm model) {

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		
		String xmmc = model.getXmmc();// ��������
		String pjxn = jbszModel.getPjxn();// ����ѧ��
		String pjxq = jbszModel.getPjxq();// ��ѧ��
		String pjnd = jbszModel.getPjnd();// �������

		StringBuilder sql = new StringBuilder();

		sql.append("select dm,mc from (");
		sql.append(" select xmdm dm ,xmmc mc,xssx from xg_pjpy_pjxmwhb a ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(xmmc) ? "" : " and xmmc like '" + xmmc + "%' ");
		sql.append(" and pjxn=? ");
		sql.append(" and pjxq=? ");
		sql.append(" and pjnd=? ");
		sql.append(" and sfqy='��' ");
		sql.append(" and sqfs='lssb' ");
		sql.append(" and not exists ( ");
		sql.append(" select 1 from xg_pjpy_sjszb b ");
		sql.append(" where a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and to_char(Sysdate, 'YYYYMMDD') < b.sqkssj ");
		sql.append(" and to_char(Sysdate, 'YYYYMMDD') > b.sqjssj ");
		sql.append(" and b.sqkzkg ='1' ");
		sql.append(")");
		sql.append(")");
		sql.append(" where rownum <=10 ");
		sql.append(" order by to_number(xssx) ");

		System.out.println("sql:"+sql);
		System.out.println("pjxn:"+pjxn);
		System.out.println("pjxq:"+pjxq);
		System.out.println("pjnd:"+pjnd);
		return dao.getList(sql.toString(), new String[] {pjxn,pjxq,pjnd}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * �����Ŀ��������
	 * 
	 * @author ΰ�����
	 */
	public String getXmszrs(PjpyXmsbForm model) {
		
		PjpyXmszModel xmszModel = model.getXmszModel();
		String xmdm = model.getXmdm();
		String szfw = xmszModel.getKzfw();
		String bmdm = model.getBjdm();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select qdrs from xg_pjpy_rsszb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xmdm = ? ");
		sql.append(" and szfw = ? ");
		sql.append(" and bmdm = ? ");
		
		DAO dao = DAO.getInstance();
		
		String qdrs = dao.getOneRs(sql.toString(), new String[]{xmdm,szfw,bmdm}, "qdrs");
		
		return qdrs;
	}
	
	/**
	 * ������Ŀ�����
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveXmsqb(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ��Ŀ����model��ʼ��
		PjpyXmszModel xmszModel = model.getXmszModel();
		
		String pjxn = jbszModel.getPjxn();// ����ѧ��
		String pjxq = jbszModel.getPjxq();// ��ѧ��
		String pjnd = jbszModel.getPjnd();// �������
		String xmdm = model.getXmdm();// ��Ŀ����
		String[] xh = model.getPjxh();// ����ѧ��
		String sqsj = getNowTime("YYYY��MM��DD��");// ����ʱ��
		String sqly = "��ʦ�ϱ�";// ��������
		String tjr = user.getUserName();// �ύ��
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjxmsqb");
		sql.append("(xmdm,pjxn,pjxq,pjnd,xh,sqsj,sqly,tjr)");
		sql.append("values(?,?,?,?,?,?,?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		String tableName = "xg_pjpy_pjxmsqb";
		
		String sqzq = xmszModel.getSqzq();//��������
		if ("xn".equalsIgnoreCase(sqzq)) {
			pjxq = "��";
			pjnd = "��";
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			pjnd = "��";
		} else {
			pjxq = "��";
			pjxq = "��";
		}
		
		boolean flag = true;
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				String[] value = new String[] { xmdm, pjxn, pjxq, pjnd, xh[i],
						sqsj, sqly, tjr };
				params.add(value);
			}
			
			try {
				//����������Ϣ
				flag = saveArrDate(sql.toString(), params, tableName, user);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				flag = false;
			}
		}
		
		return flag;
	}
	
	/**
	 * ������Ŀ��˱�
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveXmshb(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// ��Ŀ����model��ʼ��
		PjpyXmszModel xmszModel = model.getXmszModel();
		
		String pjxn = jbszModel.getPjxn();// ����ѧ��
		String pjxq = jbszModel.getPjxq();// ��ѧ��
		String pjnd = jbszModel.getPjnd();// �������
		String xmdm = model.getXmdm();// ��Ŀ����
		String[] xh = model.getPjxh();// ����ѧ��
		String sqsj = getNowTime("YYYY��MM��DD��");// ����ʱ��
		String sqly = "��ʦ�ϱ�";// ��������
		String tjr = user.getUserName();// �ύ��
		List<HashMap<String, String>> shlc = xmszModel.getShlc();
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjxmshb");
		sql.append("(xmdm,pjxn,pjxq,pjnd,xh,xtgwid)");
		sql.append("values(?,?,?,?,?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		String tableName = "xg_pjpy_pjxmshb";
		
		String sqzq = xmszModel.getSqzq();//��������
		if ("xn".equalsIgnoreCase(sqzq)) {
			pjxq = "��";
			pjnd = "��";
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			pjnd = "��";
		} else {
			pjxq = "��";
			pjxq = "��";
		}
		
		boolean flag = true;
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				// ������Ŀ������̣�������Ŀ��˱���
				for (int j = 0; j < shlc.size(); j++) {
					Map<String, String> shMap = shlc.get(j);
					String spgwId = shMap.get("gwid");
					String[] value = new String[] { xmdm, pjxn, pjxq, pjnd,
							xh[i], spgwId };
					params.add(value);
				}

			}

			try {
				// ����������Ϣ
				flag = saveArrDate(sql.toString(), params, tableName, user);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				flag = false;
			}
		}

		return flag;
	}

	/**
	 * ���ѧ���ɼ��б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getCjList(PjpyXmsbForm model) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		String xmmc = model.getXmmc();// ��������
		String pjxn = jbszModel.getPjxn();// ����ѧ��
		String pjxq = jbszModel.getPjxq();// ����ѧ��
		String pjnd = jbszModel.getPjnd();// �������

		// ѧ��
		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();
		sql.append("select kcmc,kcxz,cj ");
		sql.append("from cjb ");
		sql.append("where xn = ? ");
		sql.append("and xh = ? ");
		sql.append("order by kcmc ");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, xh },
				new String[] { "kcmc", "kcxz", "cj" });

		return list;
	}
}
