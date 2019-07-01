package xsgzgl.xsxx.zjjtzyjsxy.xxxg.zxxs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��Уѧ��_ͨ��_DAO��
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

public class XsxxZxxsDAO extends CommDAO {
	
	// ==================ִ�в�ѯ���� begin========================
	/**
	 * ��ý����(��Уѧ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// �û�����
		String yhlx = model.getYhlx();

		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.* ");
		tableSql.append("from (");
		tableSql.append("select a.xh pk, a.xh, a.xm, a.xb, a.nj, a.bjmc, a.xjztm ");
		tableSql.append("from view_xsbfxx a ");
		tableSql.append("where (sfyby = '��' or sfyby is null) ");
		tableSql.append("and (sfzx = '��У' or sfzx is null) ");
		tableSql.append(searchTj);
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.nj,a.bjdm,a.xh ) a ");
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"xjztm" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/*
	 * ���ѧ��������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getZxxsInfo(String xh, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,a.xm,a.xb,a.csrq,a.mz,a.mzmc,a.zzmm,a.zzmmmc, ");
		sql.append("a.sfzh,a.jg,a.syd,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm, ");
		sql.append("a.bjmc,a.xz,a.sfzx,a.sfyby,a.sjhm,a.qqhm,a.dzyx,a.yhdm,a.yhkh, ");
		sql.append("(select yhmc from dmk_yh where yhdm=a.yhdm) yhmc,");
		sql.append("b.jtszd,b.yb,lxdh1, ");
		sql.append("b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_nl,b.jtcy1_sfzh,b.jtcy1_mz,");
		sql.append("(select mzmc from mzdmb where mzdm=b.jtcy1_mz) jtcy1_mzmc,");
		sql.append("(select zzmmmc from zzmmdmb where zzmmdm=b.jtcy1_zzmm) jtcy1_zzmmmc,");
		sql.append("b.jtcy1_zzmm,b.jtcy1_zy,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy1_gzdz, ");
		sql.append("b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_nl,b.jtcy2_sfzh,b.jtcy2_mz,b.jtcy2_zzmm,");
		sql.append("(select mzmc from mzdmb where mzdm=b.jtcy2_mz) jtcy2_mzmc,");
		sql.append("(select zzmmmc from zzmmdmb where zzmmdm=b.jtcy2_zzmm) jtcy2_zzmmmc,");
		sql.append("b.jtcy2_zy,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy2_gzdz, ");
		sql.append("a.jgmc,a.sydqmc ");
		sql.append("from view_xsxxb a ");
		sql.append("left join xsfzxxb b on a.xh = b.xh ");
		sql.append("where a.xh=? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { xh });
		return map;
	}
	// ==================ִ���������� end==============================
	
	// ==================ִ���޸Ĳ��� begin========================
	/**
	 * �������ݣ�xsxxb:ѧ����Ϣ��
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateXsxxb(String xh, String xm, String xb, String csrq,
			String mz, String zzmm, String sfzh, String pycc, String hkszd,
			String jg, String syd, String bjdm, String xjztm, String xz,
			String sfzc, String sfzd, String rxrq, String bysj, String sfbys,
			String sfyby, String sfzx, String lxdh, String sjhm, String qqhm,
			String dzyx, String yhdm, String yhkh, String fdyxm, String kh,
			String sg, String tz, String xmpy, String cym, String tc,
			String kslb, String rxfs, String pyfs, String jkzk, User user)
			throws Exception {

		// ����
		String tableName = "xsxxb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xsxxb a ");
		sql.append("set xm=?");
		sql.append(",xb=?");
		sql.append(",csrq=?");
		sql.append(",mz=?");
		sql.append(",zzmm=?");
		sql.append(",sfzh=?");
		sql.append(",pycc=?");
		sql.append(",hkszd=?");
		sql.append(",jg=?");
		sql.append(",syd=?");
		//sql.append(",bjdm=?");
		//sql.append(",xjztm=?");
		sql.append(",xz=?");
		sql.append(",sfzc=?");
		sql.append(",sfzd=?");
		sql.append(",rxrq=?");
		sql.append(",bysj=?");
		sql.append(",sfbys=?");
		sql.append(",sfyby=?");
		sql.append(",sfzx=?");
		sql.append(",lxdh=?");
		sql.append(",sjhm=?");
		sql.append(",qqhm=?");
		sql.append(",dzyx=?");
		sql.append(",yhdm=?");
		sql.append(",yhkh=?");
		sql.append(",fdyxm=?");
		sql.append(",kh=?");
		sql.append(",sg=?");
		sql.append(",tz=?");
		sql.append(",xmpy=?");
		sql.append(",cym=?");
		sql.append(",tc=?");
		sql.append(",kslb=?");
		sql.append(",rxfs=?");
		sql.append(",pyfs=?");
		sql.append(",jkzk=?");
		sql.append(" where a.xh = ?");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { xm, xb, csrq, mz, zzmm, sfzh, pycc,
				hkszd, jg, syd, xz, sfzc, sfzd, rxrq, bysj, sfbys, sfyby, sfzx,
				lxdh, sjhm, qqhm, dzyx, yhdm, yhkh, fdyxm, kh, sg, tz, xmpy,
				cym, tc, kslb, rxfs, pyfs, jkzk, xh };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * �������ݣ�xsfzxxb:ѧ��������Ϣ��
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateFzxxb(String xh, String lxdh1, String jtszd,
			String yb, String jjzk, String jtcy1_xm, String jtcy1_gx,
			String jtcy1_nl, String jtcy1_sfzh, String jtcy1_zy,
			String jtcy1_zw, String jtcy1_lxdh1, String jtcy1_lxdh2,
			String jtcy1_gzdz, String jtcy1_mz, String jtcy1_zzmm,
			String jtcy2_xm, String jtcy2_gx, String jtcy2_nl,
			String jtcy2_sfzh, String jtcy2_zy, String jtcy2_zw,
			String jtcy2_lxdh1, String jtcy2_lxdh2, String jtcy2_gzdz,
			String jtcy2_mz, String jtcy2_zzmm, String jtcy3_xm,
			String jtcy3_gx, String jtcy3_nl, String jtcy3_sfzh,
			String jtcy3_zy, String jtcy3_zw, String jtcy3_lxdh1,
			String jtcy3_lxdh2, String jtcy3_gzdz, String jtcy3_mz,
			String jtcy3_zzmm, User user) throws Exception {

		// ����
		String tableName = "xsfzxxb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xsfzxxb a ");
		sql.append("set lxdh1  =?");
		sql.append(",jtszd  =?");
		sql.append(",yb  =?");
		sql.append(",jjzk  =?");
		sql.append(",jtcy1_xm  =?");
		sql.append(",jtcy1_gx  =?");
		sql.append(",jtcy1_nl  =?");
		sql.append(",jtcy1_sfzh  =?");
		sql.append(",jtcy1_zy  =?");
		sql.append(",jtcy1_zw  =?");
		sql.append(",jtcy1_lxdh1  =?");
		sql.append(",jtcy1_lxdh2  =?");
		sql.append(",jtcy1_gzdz  =?");
		sql.append(",jtcy1_mz  =?");
		sql.append(",jtcy1_zzmm  =?");
		sql.append(",jtcy2_xm  =?");
		sql.append(",jtcy2_gx  =?");
		sql.append(",jtcy2_nl  =?");
		sql.append(",jtcy2_sfzh  =?");
		sql.append(",jtcy2_zy  =?");
		sql.append(",jtcy2_zw  =?");
		sql.append(",jtcy2_lxdh1  =?");
		sql.append(",jtcy2_lxdh2  =?");
		sql.append(",jtcy2_gzdz  =?");
		sql.append(",jtcy2_mz  =?");
		sql.append(",jtcy2_zzmm  =?");
		sql.append(",jtcy3_xm  =?");
		sql.append(",jtcy3_gx  =?");
		sql.append(",jtcy3_nl  =?");
		sql.append(",jtcy3_sfzh  =?");
		sql.append(",jtcy3_zy  =?");
		sql.append(",jtcy3_zw  =?");
		sql.append(",jtcy3_lxdh1  =?");
		sql.append(",jtcy3_lxdh2  =?");
		sql.append(",jtcy3_gzdz  =?");
		sql.append(",jtcy3_mz  =?");
		sql.append(",jtcy3_zzmm  =?");
		sql.append(" where a.xh = ?");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { lxdh1, jtszd, yb, jjzk, jtcy1_xm,
				jtcy1_gx, jtcy1_nl, jtcy1_sfzh, jtcy1_zy, jtcy1_zw,
				jtcy1_lxdh1, jtcy1_lxdh2, jtcy1_gzdz, jtcy1_mz, jtcy1_zzmm,
				jtcy2_xm, jtcy2_gx, jtcy2_nl, jtcy2_sfzh, jtcy2_zy, jtcy2_zw,
				jtcy2_lxdh1, jtcy2_lxdh2, jtcy2_gzdz, jtcy2_mz, jtcy2_zzmm,
				jtcy3_xm, jtcy3_gx, jtcy3_nl, jtcy3_sfzh, jtcy3_zy, jtcy3_zw,
				jtcy3_lxdh1, jtcy3_lxdh2, jtcy3_gzdz, jtcy3_mz, jtcy3_zzmm, xh };
		
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ���޸Ĳ��� end========================
	
	
	// -----------------2012.4.12 ---------------------
	// -----------------qlj ��ҵ���� begin--------------------
	
	public boolean xsxxInit(XsxxZxxsModel model, User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		String bjdm=model.getBjdm();
		
		String zydm=model.getZydm();
		
		String xydm=model.getXydm();
		
		String nj=model.getNj();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" insert into xsxxb (xh,xm,xb,sfzh,nj,xjztm,xz,xydm,zydm,bjdm,ksh) ");
		sql.append(" select xh,xm,xb,sfzh,nj,xjztm,xz,xydm,zydm,bjdm,ksh ");
		sql.append(" from view_xsjbxx a where not exists( ");
		sql.append(" select 1 from xsxxb b where a.xh=b.xh  ) ");
	
		
		if(!Base.isNull(bjdm)){
			
			inputV.add(bjdm);
			sql.append(" and bjdm=? ");
		}
		
		if(!Base.isNull(zydm)){
			
			inputV.add(zydm);
			sql.append(" and zydm=? ");
		}

		if(!Base.isNull(xydm)){
			
			inputV.add(xydm);
			sql.append(" and xydm=? ");
		}

		if(!Base.isNull(nj)){
			
			inputV.add(nj);
			sql.append(" and nj=? ");
		}
		
		return dao.runUpdate(sql.toString(),inputV.toArray(new String[]{}));
	}
	
	public boolean saveBycl(XsxxZxxsModel model, User user) throws Exception{
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		String bjdm=model.getBjdm();
		
		String zydm=model.getZydm();
		
		String xydm=model.getXydm();
		
		String nj=model.getNj();
		
		List<String>inputV=new ArrayList<String>();
		 
		sql.append(" update xsxxb set sfyby='��' ,sfzx='����У',byny=to_char(sysdate,'yyyyMMdd') where 1=1 ");
		
		if(!Base.isNull(bjdm)){
			
			inputV.add(bjdm);
			sql.append(" and bjdm=? ");
		}
		
		if(!Base.isNull(zydm)){
			
			inputV.add(zydm);
			sql.append(" and zydm=? ");
		}

		if(!Base.isNull(xydm)){
			
			inputV.add(xydm);
			sql.append(" and xydm=? ");
		}

		if(!Base.isNull(nj)){
			
			inputV.add(nj);
			sql.append(" and nj=? ");
		}
		
		return dao.runUpdate(sql.toString(),inputV.toArray(new String[]{}));
	}
	
	
	// -----------------qlj ��ҵ���� end--------------------
	
	
//	 ==================ִ�в�ѯ���� begin========================
	/**
	 * ��ý����(��Уѧ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// �û�����
		String yhlx = model.getYhlx();

		// ====================��������===================================
		user.setUserStatus(yhlx);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.* ");
		tableSql.append("from (");
		tableSql.append("select a.xh pk, a.xh, a.xm, a.xb, a.nj, a.bjmc, a.xjztm ");
		tableSql.append("from view_xsbfxx a ");
		tableSql.append("where (sfyby = '��') ");
		tableSql.append("and (sfzx = '����У') ");
		tableSql.append(searchTj);
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.nj,a.bjdm,a.xh ) a ");
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"xjztm" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
}
