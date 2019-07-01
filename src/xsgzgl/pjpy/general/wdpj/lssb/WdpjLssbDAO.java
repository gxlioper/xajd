package xsgzgl.pjpy.general.wdpj.lssb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-11 ����10:17:45</p>
 */
public class WdpjLssbDAO extends CommDAO{

	// ------------------------��ʦ�ϱ���Ϣ��ѯ begin --------------------------------
	/**
	 * ��ȡ��ʦ�ϱ��༶ѧ��������Ŀ���
	 * author qlj
	 * @param myForm
	 * @param model
	 * @param user
	 * @return ArrayList<String[]>
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		DAO dao=DAO.getInstance();
		
		// �۲�dao
		PjpyZhcpDAO zhcpDAO=new PjpyZhcpDAO();
		// ��������������Ϣ
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		StringBuilder sql=new StringBuilder();
		// ��Ŀ����
		String xmdm=model.getXmdm();
		
		// --------------------------��ѯ�ֶ� begin-------------------------------
	
		String[]colList=new String[]{"xh","xm","nj","bjmc","zd3"};

		// ���������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zypmList=zhcpDAO.getZyPmArr("en");// ����������Ϣ��̬��ȡ
		
		colList=dao.unionArray(colList, zypmList);

		String[]other=new String[]{"zd1"};
		
		colList=dao.unionArray(colList, other);
		
		// �۲������ֶ� ���ݻ����������۲����������ó�(1,2,3,4,5,6,7 �����)
		String[]zcpmList=zhcpDAO.getZcPmArr("en");// ����������Ϣ��̬��ȡ
		
		colList=dao.unionArray(colList, zcpmList);
		
		other=new String[]{"sqqk","pkValue"};
		
		colList=dao.unionArray(colList, other);
		
		// --------------------------��ѯ�ֶ� end-------------------------------
		
		String bjdm=model.getBjdm();
		
		String pjxn=jbszModel.getPjxn();
		
		String pjxq=jbszModel.getPjxq();
		
		String pjnd=jbszModel.getPjnd();
		
		sql.append(" (select a.*, ");
		sql.append(" case when b.xh is null then 'wsq'  ");
		sql.append(" when c.xh is null then 'wsh' else 'ysh' end sqqk,b.pkValue   from ( ");
		
		// ------------------------�ۺϲ�����Ϣ being---------------------------
		sql.append(" select a.xh,a.xm,a.nj,a.xydm, ");
		sql.append(" a.xymc,a.zydm,a.zymc, a.bjdm, ");
		sql.append(" a.bjmc,b.zd1,b.zd3,b.zcfnjxypm, b.zcfnjzypm,b.zcfbjpm, ");
		sql.append(" b.zyfnjxypm, b.zyfnjzypm,b.zyfbjpm, b.zyfcpzpm,b.cpzpm ");
		sql.append(" from xg_view_pjpy_pjryk a ");
		sql.append(" left join xg_pjpy_zhcpb b on a.xh = b.xh ");
		sql.append(" where bjdm = ?  and xn = ? ");
		sql.append(" and xq = ? and nd = ?)a ");
		// ------------------------�ۺϲ�����Ϣ end ---------------------------
		
		sql.append(" left join ");
		
		// ------------------------����������Ϣ begin------------------------
		sql.append(" (select b.*, ");
		sql.append(" b.xh||'!!@@!!'|| b.xmdm||'!!@@!!'|| b.pjxn||'!!@@!!'|| b.pjxq||'!!@@!!'|| b.pjnd  ");
		sql.append(" pkValue from xg_pjpy_pjxmsqb b ");
		sql.append("  where b.pjxn=? ");
		sql.append("  and b.pjxq=? and b.pjnd=? ");
		sql.append("  and b.xmdm=?)b on a.xh=b.xh");
		// ------------------------����������Ϣ end------------------------	
		
		// ------------------------���������Ϣ begin------------------------
		sql.append(" left join (");
		sql.append(" select * from xg_pjpy_pjxmshb b ");
		sql.append(" where b.pjxn=?");
		sql.append(" and b.pjxq=? and b.pjnd=? ");
		sql.append(" and b.xmdm=? ");
		sql.append(" and (shzt is not null and shzt<>'wsh')");
		sql.append(" )c on a.xh=c.xh)");
		sql.append(" order by to_number(");
		sql.append(zcpmList[0]);
		sql.append(" )");
		// ------------------------���������Ϣ end------------------------
		
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[]{bjdm,pjxn,pjxq,pjnd,pjxn,pjxq,pjnd,xmdm,pjxn,pjxq,pjnd,xmdm} , colList , null);
	}
	// ------------------------��ʦ�ϱ���Ϣ��ѯ end --------------------------------	
	
	// ------------------------������ϸ��Ϣ begin --------------------------------	
	/**
	 * ѧ���ɼ���Ϣ(��ȡѧ���ɼ���Ϣ�б�)
	 * author qlj
	 * @param myForm
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getXscjList(
			String xh) {
		
		// ��������������Ϣ
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		
		DAO dao = DAO.getInstance();
		// ��������
		String pjzq=jbszModel.getPjzq();
		// ����ѧ��
		String xn=jbszModel.getPjxn();
		// ����ѧ��
		String xq=jbszModel.getPjxq();

		StringBuilder sql = new StringBuilder();
		
		// --------------------��ѯ��� begin-------------------------
		sql.append(" select xn,xq,kcmc,kcxz,cj,  ");
		sql.append(" (select xqmc from xqdzb b where a.xq=b.xqdm )xqmc ");
		sql.append(" from cjb  a where xh=? ");
		// --------------------��ѯ��� end-------------------------		
		
		// ----------------������� begin---------------------
		List<String>inputV=new ArrayList<String>();
		
		inputV.add(xh);
		// �����������ڻ�ȡ�ɼ�������
		if("xn".equalsIgnoreCase(pjzq)){// ѧ��
			sql.append(" and  xn=? ");
			inputV.add(xn);
		}else{ // ѧ��
			sql.append(" and xn=? ");
			sql.append(" and xq=? ");
			inputV.add(xn);
			inputV.add(xq);
		}
		// ----------------������� end---------------------
		
		sql.append(" order by xn,xq ");// ����
		
		return dao.getList(sql.toString(),inputV.toArray(new String[]{}), new String[] {
				"xn", "xqmc", "kcmc", "kcxz", "cj" });
	}
	// ------------------------������ϸ��Ϣ begin --------------------------------
}
