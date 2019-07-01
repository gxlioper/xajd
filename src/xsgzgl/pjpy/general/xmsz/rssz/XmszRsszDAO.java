package xsgzgl.pjpy.general.xmsz.rssz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XmszRsszDAO extends CommDAO {

	// ==================ִ�в�ѯ���� begin==============================
	/**
	 * ��ý����(�������á��꼶��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXmszRsszListByNj(PjpyGeneralForm model,
			String xmdm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.pk,a.nj,");
		tableSql.append("a.bmrs,a.szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from (");
		tableSql.append("select a.xmdm||a.szfw||a.bmdm pk, ");
		tableSql.append("b.nj, ");
		tableSql.append("a.bmrs,case when a.szbl is not null then a.szbl||'%' else '' end szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from xg_pjpy_rsszb a left join ");
		tableSql.append("(select distinct nj bmdm,nj from view_njxyzybj_all ) b ");
		tableSql.append("on a.bmdm = b.bmdm ");
		tableSql.append("where a.xmdm='" + xmdm + "'");
		tableSql.append(query);

		// ����
		String arrange = model.getSearchModel().getArrange();
		// ����ʽ
		String fashion = model.getSearchModel().getFashion();
		
		if(!"no".equalsIgnoreCase(arrange) && !"".equalsIgnoreCase(arrange)){			
			tableSql.append("order by to_number(" + arrange + ") ");
			tableSql.append(fashion);
		}
		
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "nj", "bmrs", "szbl", "jsrs",
				"mrrs", "qdrs" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ��ý����(�������á�ѧԺ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXmszRsszListByXy(PjpyGeneralForm model,
			String xmdm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

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

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.pk,a.xymc,");
		tableSql.append("a.bmrs,a.szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from (");
		tableSql.append("select a.xmdm||a.szfw||a.bmdm pk, ");
		tableSql.append("b.xydm,b.xymc, ");
		tableSql.append("a.bmrs,case when a.szbl is not null then a.szbl||'%' else '' end szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from xg_pjpy_rsszb a left join ");
		tableSql.append("(select distinct xydm bmdm,xydm,xymc from view_njxyzybj_all ) b ");
		tableSql.append("on a.bmdm = b.bmdm ");
		tableSql.append("where a.xmdm='" + xmdm + "'");
		tableSql.append("order by b.xydm ) a ");
		tableSql.append(query);

		// ����
		String arrange = model.getSearchModel().getArrange();
		// ����ʽ
		String fashion = model.getSearchModel().getFashion();
		
		if(!"no".equalsIgnoreCase(arrange) && !"".equalsIgnoreCase(arrange)){			
			if(!"xymc".equalsIgnoreCase(arrange) && !"szbl".equalsIgnoreCase(arrange)){
				tableSql.append("order by to_number(" + arrange + ") ");
			}else{
				tableSql.append("order by " + arrange + " ");
			}
			tableSql.append(fashion);
		}
		
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "xymc", "bmrs", "szbl", "jsrs",
				"mrrs", "qdrs" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ��ý����(�������á��꼶+ѧԺ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXmszRsszListByNjXy(PjpyGeneralForm model,
			String xmdm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

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

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.pk,a.nj,a.xymc,");
		tableSql.append("a.bmrs,a.szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from (");
		tableSql.append("select a.xmdm||a.szfw||a.bmdm pk, ");
		tableSql.append("b.nj,b.xydm,b.xymc, ");
		tableSql.append("a.bmrs,case when a.szbl is not null then a.szbl||'%' else '' end szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from xg_pjpy_rsszb a left join ");
		tableSql.append("(select distinct nj || xydm bmdm,nj,xydm,xymc from view_njxyzybj_all ) b ");
		tableSql.append("on a.bmdm = b.bmdm ");
		tableSql.append("where a.xmdm='" + xmdm + "'");
		tableSql.append("order by b.nj,b.xydm ) a ");
		tableSql.append(query);		

		// ����
		String arrange = model.getSearchModel().getArrange();
		// ����ʽ
		String fashion = model.getSearchModel().getFashion();
		
		if(!"no".equalsIgnoreCase(arrange) && !"".equalsIgnoreCase(arrange)){			
			if(!"xymc".equalsIgnoreCase(arrange) && !"szbl".equalsIgnoreCase(arrange)){
				tableSql.append("order by to_number(" + arrange + ") ");
			}else{
				tableSql.append("order by " + arrange + " ");
			}
			tableSql.append(fashion);
		}		
		
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "nj", "xymc", "bmrs", "szbl",
				"jsrs", "mrrs", "qdrs" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ��ý����(�������á��꼶+רҵ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXmszRsszListByNjZy(PjpyGeneralForm model,
			String xmdm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

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

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.pk,a.nj,a.xymc,a.zymc,");
		tableSql.append("a.bmrs,a.szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from (");
		tableSql.append("select a.xmdm||a.szfw||a.bmdm pk, ");
		tableSql.append("b.nj,b.xydm,b.xymc,b.zymc, ");
		tableSql.append("a.bmrs,case when a.szbl is not null then a.szbl||'%' else '' end szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from xg_pjpy_rsszb a left join ");
		tableSql.append("(select distinct nj || zydm bmdm,nj,zydm,xydm,xymc,zymc from view_njxyzybj_all ) b ");
		tableSql.append("on a.bmdm = b.bmdm ");
		tableSql.append("where a.xmdm='" + xmdm + "'");
		tableSql.append("order by b.nj,b.xydm,b.zydm ) a ");
		tableSql.append(query);
		
		// ����
		String arrange = model.getSearchModel().getArrange();
		// ����ʽ
		String fashion = model.getSearchModel().getFashion();
		
		if(!"no".equalsIgnoreCase(arrange) && !"".equalsIgnoreCase(arrange)){			
			if(!"xymc".equalsIgnoreCase(arrange) && !"zymc".equalsIgnoreCase(arrange) && !"szbl".equalsIgnoreCase(arrange)){
				tableSql.append("order by to_number(" + arrange + ") ");
			}else{
				tableSql.append("order by " + arrange + " ");
			}
			tableSql.append(fashion);
		}
				
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "bmrs",
				"szbl", "jsrs", "mrrs", "qdrs" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ��ý����(�������á��༶��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXmszRsszListByBj(PjpyGeneralForm model,
			String xmdm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

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

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.pk,a.nj,a.bjmc,");
		tableSql.append("a.bmrs,a.szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from (");
		tableSql.append("select a.xmdm||a.szfw||a.bmdm pk, ");
		tableSql.append("b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc, ");
		tableSql.append("a.bmrs,case when a.szbl is not null then a.szbl||'%' else '' end szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from xg_pjpy_rsszb a left join view_njxyzybj_all b ");
		tableSql.append("on a.bmdm = b.bjdm ");
		tableSql.append("where a.xmdm='" + xmdm + "'");
		tableSql.append("order by b.nj,b.bjdm ) a ");		
		tableSql.append(query);
		
		// ����
		String arrange = model.getSearchModel().getArrange();
		// ����ʽ
		String fashion = model.getSearchModel().getFashion();
		
		if(!"no".equalsIgnoreCase(arrange) && !"".equalsIgnoreCase(arrange)){			
			if(!"bjmc".equalsIgnoreCase(arrange) && !"szbl".equalsIgnoreCase(arrange)){
				tableSql.append("order by to_number(" + arrange + ") ");
			}else{
				tableSql.append("order by " + arrange + " ");
			}
			tableSql.append(fashion);
		}
		
		tableSql.append(") a ");		
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "nj", "bjmc", "bmrs", "szbl",
				"jsrs", "mrrs", "qdrs" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ��ý����(�������á������顿)
	 * 
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXmszRsszListByCpz(PjpyGeneralForm model,
			String xmdm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// �û�����
		String yhlx = model.getYhlx();

		// ====================��������===================================
		user.setUserStatus(yhlx);
		// �߼���ѯ����
		model.getSearchModel().setPath("pjpy_xmsz_rssz.do&searchType=cpz");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
//				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.pk,a.cpzmc,");
		tableSql.append("a.bmrs,a.szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from (");
		tableSql.append("select a.xmdm||a.szfw||a.bmdm pk, ");
		tableSql.append("b.cpzdm,b.cpzmc, ");
		tableSql.append("a.bmrs,case when a.szbl is not null then a.szbl||'%' else '' end szbl,a.jsrs,a.mrrs,a.qdrs ");
		tableSql.append("from xg_pjpy_rsszb a left join (select distinct cpzmc,cpzmc cpzdm from xg_pjpy_cpzb) b ");
		tableSql.append("on a.bmdm = b.cpzdm ");
		tableSql.append("where a.xmdm='" + xmdm + "'");
		tableSql.append("order by cpzdm ) a ");
		tableSql.append(query);

		// ����
		String arrange = model.getSearchModel().getArrange();
		// ����ʽ
		String fashion = model.getSearchModel().getFashion();
		
		if(!"no".equalsIgnoreCase(arrange) && !"".equalsIgnoreCase(arrange)){			
			if(!"cpzmc".equalsIgnoreCase(arrange) && !"szbl".equalsIgnoreCase(arrange)){
				tableSql.append("order by to_number(" + arrange + ") ");
			}else{
				tableSql.append("order by " + arrange + " ");
			}
			tableSql.append(fashion);
		}
		
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "cpzmc", "bmrs", "szbl",
				"jsrs", "mrrs", "qdrs" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	// ==================ִ�в�ѯ���� end==============================
	
	// ==================ִ�и��²��� end==============================
	/**
	 * �������ݣ�xg_pjpy_rsszb:�������ñ�
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateRsszb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, String xmdm, String szfw, String szbl, User user)
			throws Exception {

		// ����
		String tableName = "xg_pjpy_rsszb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_rsszb a ");
		sql.append("set szbl = ? ");
		sql.append(", jsrs = to_char(bmrs*?/100, 'fm999990.00') ");
		sql.append(", mrrs = round(bmrs*?/100) ");
		sql.append(", qdrs = round(bmrs*?/100) ");
		sql.append("where a.xmdm = ? ");
		sql.append("and a.szfw = ? ");
		sql.append("and exists (");
		sql.append("select 1 from view_njxyzybj_all b ");
		sql.append("where a.bmdm = ");
		if ("nj".equalsIgnoreCase(szfw)) {
			sql.append("b.nj ");
		} else if ("xy".equalsIgnoreCase(szfw)) {
			sql.append("b.xydm ");
		} else if ("njxy".equalsIgnoreCase(szfw)) {
			sql.append("b.nj||b.xydm ");
		} else if ("njzy".equalsIgnoreCase(szfw)) {
			sql.append("b.nj||b.zydm ");
		} else if ("bj".equalsIgnoreCase(szfw)) {
			sql.append("b.bjdm ");
		}
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		sql.append(")");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { szbl, szbl, szbl, szbl, xmdm, szfw });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * �������ݣ�xg_pjpy_rsszb:�������ñ�
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateRsszByCpz(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, String xmdm, String szfw, String szbl, User user)
			throws Exception {

		// ����
		String tableName = "xg_pjpy_rsszb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_rsszb a ");
		sql.append("set szbl = ? ");
		sql.append(", jsrs = to_char(bmrs*?/100, 'fm999990.00') ");
		sql.append(", mrrs = round(bmrs*?/100) ");
		sql.append(", qdrs = round(bmrs*?/100) ");
		sql.append("where a.xmdm = ? ");
		sql.append("and a.szfw = ? ");
		sql.append("and exists (");
		sql.append("select 1 from xg_pjpy_cpzb b ");
		sql.append("where a.bmdm = b.cpzmc ) ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { szbl, szbl, szbl, szbl, xmdm, szfw });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * �������ݣ�xg_pjpy_rsszb:�������ñ�
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateRsszb(String[] pkValue, String xmdm, String szfw,
			String szbl, User user) throws Exception {

		boolean flag = false;

		// ����
		String tableName = "xg_pjpy_rsszb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_rsszb a ");
		sql.append("set szbl = ? ");
		sql.append(", jsrs = to_char(bmrs*?/100, 'fm999990.00') ");
		sql.append(", mrrs = round(bmrs*?/100) ");
		sql.append(", qdrs = round(bmrs*?/100) ");
		sql.append("where a.xmdm = ? ");
		sql.append("and a.szfw = ? ");
		sql.append("and a.xmdm||a.szfw||a.bmdm = ? ");
		
		List<String[]> params = new ArrayList<String[]>();
		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				String[] value = new String[] { szbl, szbl, szbl, szbl, xmdm,
						szfw, pkValue[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * �������ݣ�xg_pjpy_rsszb:�������ñ�
	 * 
	 * @table �������
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateRsszbQdrs(String[] pkValue, String[] qdrs, User user)
			throws Exception {

		boolean flag = false;

		CommService service = new CommService();
		// ����
		String tableName = "xg_pjpy_rsszb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_rsszb a ");
		sql.append("set qdrs = ? ");
		sql.append("where a.xmdm||a.szfw||a.bmdm = ? ");

		List<String[]> params = new ArrayList<String[]>();
		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				String[] value = new String[] { qdrs[i], service.unicode2Gbk(pkValue[i]) };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	// ==================ִ�и��²��� end==============================
	
	// ==================ִ�г�ʼ������ begin ==============================	
	/**
	 * ��ʼ�����ݣ�xg_pjpy_rsszb:�������ñ��꼶����
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void initRsszbByNj(String xmdm, String tsrq, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		// ������Ⱥ����
		String condition = "";
		if (!"no".equalsIgnoreCase(tsrq)) {
			condition = getOneValue("xg_pjpy_pjtsrqb", "condition", "tsrqdm",
					tsrq);
		}

		sql.append("insert into xg_pjpy_rsszb ");
		sql.append("(xmdm,szfw,bmdm,bmrs) ");

		sql.append("select ? xmdm, 'nj' szfw, nj bmdm, count(1) bmrs ");
		sql.append("from (select a.xh, b.nj, b.xydm, ");
		sql.append("b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc ");
		sql.append("from (");
		sql.append("select a.* from xg_pjpy_pjrykb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(condition)) {
			sql.append(condition);
		}
		sql.append(") a ");
		sql.append("left join view_njxyzybj_all b ");
		sql.append("on a.bjdm = b.bjdm ");
		sql.append("where a.sfcp = 'yes') ");
		sql.append("group by nj ");

		dao.runUpdate(sql.toString(), new String[] { xmdm });
	}
	
	/**
	 * ��ʼ�����ݣ�xg_pjpy_rsszb:�������ñ�ѧԺ����
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void initRsszbByXy(String xmdm, String tsrq, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		// ������Ⱥ����
		String condition = "";
		if (!"no".equalsIgnoreCase(tsrq)) {
			condition = getOneValue("xg_pjpy_pjtsrqb", "condition", "tsrqdm",
					tsrq);
		}

		sql.append("insert into xg_pjpy_rsszb ");
		sql.append("(xmdm,szfw,bmdm,bmrs) ");

		sql.append("select ? xmdm, 'xy' szfw, xydm bmdm, count(1) bmrs ");
		sql.append("from (select a.xh, b.nj, b.xydm, ");
		sql.append("b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc ");
		sql.append("from (");
		sql.append("select a.* from xg_pjpy_pjrykb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(condition)) {
			sql.append(condition);
		}
		sql.append(") a ");
		sql.append("left join view_njxyzybj_all b ");
		sql.append("on a.bjdm = b.bjdm ");
		sql.append("where a.sfcp = 'yes') ");
		sql.append("group by xydm ");

		dao.runUpdate(sql.toString(), new String[] { xmdm });
	}
	
	/**
	 * ��ʼ�����ݣ�xg_pjpy_rsszb:�������ñ��꼶+ѧԺ����
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void initRsszbByNjXy(String xmdm, String tsrq, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		// ������Ⱥ����
		String condition = "";
		if (!"no".equalsIgnoreCase(tsrq)) {
			condition = getOneValue("xg_pjpy_pjtsrqb", "condition", "tsrqdm",
					tsrq);
		}

		sql.append("insert into xg_pjpy_rsszb ");
		sql.append("(xmdm,szfw,bmdm,bmrs) ");

		sql.append("select ? xmdm, 'njxy' szfw, nj||xydm bmdm, count(1) bmrs ");
		sql.append("from (select a.xh, b.nj, b.xydm, ");
		sql.append("b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc ");
		sql.append("from (");
		sql.append("select a.* from xg_pjpy_pjrykb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(condition)) {
			sql.append(condition);
		}
		sql.append(") a ");
		sql.append("left join view_njxyzybj_all b ");
		sql.append("on a.bjdm = b.bjdm ");
		sql.append("where a.sfcp = 'yes') ");
		sql.append("group by nj,xydm ");

		dao.runUpdate(sql.toString(), new String[] { xmdm });
	}
	
	/**
	 * ��ʼ�����ݣ�xg_pjpy_rsszb:�������ñ��꼶+רҵ����
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void initRsszbByNjZy(String xmdm, String tsrq, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		// ������Ⱥ����
		String condition = "";
		if (!"no".equalsIgnoreCase(tsrq)) {
			condition = getOneValue("xg_pjpy_pjtsrqb", "condition", "tsrqdm",
					tsrq);
		}

		sql.append("insert into xg_pjpy_rsszb ");
		sql.append("(xmdm,szfw,bmdm,bmrs) ");

		sql.append("select ? xmdm, 'njzy' szfw, nj||zydm bmdm, count(1) bmrs ");
		sql.append("from (select a.xh, b.nj, b.xydm, ");
		sql.append("b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc ");
		sql.append("from (");
		sql.append("select a.* from xg_pjpy_pjrykb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(condition)) {
			sql.append(condition);
		}
		sql.append(") a ");
		sql.append("left join view_njxyzybj_all b ");
		sql.append("on a.bjdm = b.bjdm ");
		sql.append("where a.sfcp = 'yes') ");
		sql.append("group by nj,zydm ");

		dao.runUpdate(sql.toString(), new String[] { xmdm });
	}
	
	/**
	 * ��ʼ�����ݣ�xg_pjpy_rsszb:�������ñ��༶����
	 * 
	 * @table �������ñ�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void initRsszbByBj(String xmdm, String tsrq, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		// ������Ⱥ����
		String condition = "";
		if (!"no".equalsIgnoreCase(tsrq)) {
			condition = getOneValue("xg_pjpy_pjtsrqb", "condition", "tsrqdm",
					tsrq);
		}

		sql.append("insert into xg_pjpy_rsszb ");
		sql.append("(xmdm,szfw,bmdm,bmrs) ");

		sql.append("select ? xmdm, 'bj' szfw, bjdm bmdm, count(1) bmrs ");
		sql.append("from (select a.xh, b.nj, b.xydm, ");
		sql.append("b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc ");
		sql.append("from (");
		sql.append("select a.* from xg_pjpy_pjrykb a ");
		sql.append("where 1=1 ");
		if (!Base.isNull(condition)) {
			sql.append(condition);
		}
		sql.append(") a ");
		sql.append("left join view_njxyzybj_all b ");
		sql.append("on a.bjdm = b.bjdm ");
		sql.append("where a.sfcp = 'yes') ");
		sql.append("group by bjdm ");

		dao.runUpdate(sql.toString(), new String[] { xmdm });
	}
	
	/**
	 * ��ʼ�����ݣ�xg_pjpy_rsszb:�������ñ������顿��
	 * 
	 * @table �������ñ�
	 * @author qlj
	 * @throws Exception
	 */
	public void initRsszbByCpz(String xmdm, String tsrq, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		// ������Ⱥ����
		String condition = "";
		if (!"no".equalsIgnoreCase(tsrq)) {
			condition = getOneValue("xg_pjpy_pjtsrqb", "condition", "tsrqdm",
					tsrq);
		}

		sql.append("insert into xg_pjpy_rsszb ");
		sql.append("(xmdm,szfw,bmdm,bmrs) ");

		sql.append(" select ? xmdm, 'cpz' szfw, cpz bmdm, count(1) bmrs ");
		sql.append(" from (select a.xh, a.cpz ");
		sql.append(" from (select a.* from xg_pjpy_pjrykb a  ");
		sql.append(" where 1=1 ");
		if (!Base.isNull(condition)) {
			sql.append(condition);
		}
		sql.append(") a ");
		sql.append("where a.sfcp = 'yes') ");
		sql.append("group by cpz ");

		dao.runUpdate(sql.toString(), new String[] { xmdm });
	}
	// ==================ִ�г�ʼ������ end ==============================
}
