package xsgzgl.pjpy.general.tjcx.hjjehz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.PjpyTjcxDAO;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_�񽱽�����_ͨ��_DAO��
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

public class TjcxHjjehzDAO extends PjpyTjcxDAO {

	/**
	 * ��ȡ�ҵ���������������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getHjjehzList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = searchModel.getSearch_tj_xn();
		if (xn == null || xn.length == 0) {
			xn = new String[] { jbszForm.getPjxn() };
		}
		searchModel.setSearch_tj_xn(xn);
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(searchModel);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xn||a.xq||a.xmlx||a.xmmc||a.xh pk, ");
		sql.append(" a.xn,a.xh,b.xm,b.nj,b.xydm,b.zydm,b.zymc,b.bjmc, ");
		sql.append(" b.bjdm,b.xymc,a.xmmc,a.hdsj,a.xmje,a.xmlx, ");
		sql.append(" decode(a.xmlx,'01','��ѧ��','�����ƺ�') xmlxmc ");
		sql.append(" from xg_pjpy_pjlsxxb a ");
		sql.append(" left join xg_view_pjpy_pjryk b ");
		sql.append(" on a.xh=b.xh ");
		sql.append("order by a.xn,a.xmmc,b.bjmc,a.xh ");
		sql.append(" )a ");

		sql.append(query);

		String[] colList = { "pk", "xn", "xh", "xm", "nj", "bjmc", "xmlxmc",
				"xmmc", "hdsj", "xmje" };
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonQuery(sql.toString(), "", inputV, colList, myForm);

		return list;
	}


	/**
	 * ���ָ��ѧ��ѧ�ڵĽ�ѧ����ͳ���б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getJxjjeList(String xn, String[] xmmc,
			String[] nj, String[] xydm, String[] zydm, String[] bjdm) {

		DAO dao = DAO.getInstance();
		
		ArrayList<String>colList=new ArrayList<String>();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		
		List<HashMap<String,String>>xmxzList=getXmxzList();
		
		List<HashMap<String,String>>xmmcList=getXmmcBylx("02", xn);
		StringBuffer sql = new StringBuffer();
		StringBuilder maxSql=new StringBuilder();
		StringBuilder caseSql = new StringBuilder();
		StringBuilder searchZd = new StringBuilder();
		

		for(int i=0;i<xmxzList.size();i++){
			HashMap<String,String>xmxzMap=xmxzList.get(i);
			caseSql.append(" ,case when xmxz = '"+xmxzMap.get("xzdm")+"' and xmlx='01' then  xmmc else  ''  end jxjmc_"+i+" ");
			maxSql.append(",max(jxjmc_"+i+")jxjmc_"+i+" ");
			searchZd.append(",a.jxjmc_"+i);
			colList.add("jxjmc_"+i);
		}
		
		for(int i=0;i<xmmcList.size();i++){
			HashMap<String,String>xmmcMap=xmmcList.get(i);
			caseSql.append(" ,case when xmmc = '"+xmmcMap.get("xmmc")+"' and xmlx='02' then  xmmc else  ''  end rychmc_"+i+" ");
			maxSql.append(",max(rychmc_"+i+")rychmc_"+i+" ");
			searchZd.append(",a.rychmc_"+i);
			colList.add("rychmc_"+i);
		}
		

		sql.append("select a.xh,a.xm,a.xymc,a.zymc"+searchZd+",a.je, ");
		sql.append("rank() over(partition by a.xydm order by a.xh desc nulls last) num, ");
		sql.append("b.yhkh,a.xydm||'luojw'||c.zs||'luojw'||nvl(c.zje,0) zj from ");
		sql.append("(select a.xh,b.xm,b.nj,b.xydm,b.xymc, ");
		sql.append("b.zydm,b.zymc,b.bjdm,b.bjmc,a.je ");
		sql.append(searchZd);
		sql.append(" from (  ");

		sql.append(" select xn,xq,xh"+maxSql+",sum(xmje) je ");
		sql.append(" from (select xn,xq,xmlx,xmmc,xh,xmje,hdsj,bz ");
		sql.append(caseSql);
		sql.append(" from xg_pjpy_pjlsxxb a) group by xn, xq, xh ");
		
		sql.append(" ) a,xg_view_pjpy_pjryk b ");
		sql.append("where a.xh = b.xh ");
		//sql.append("and a.xmlx = '01' ");
		sql.append("and a.xn=? ");
		//��Ŀ����
		if (xmmc != null && xmmc.length > 0) {
			sql.append("and a.xmmc in ( ");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		//sql.append("and a.xmmc=? ");
		
		// �꼶
		if (nj != null && nj.length > 0) {
			sql.append("and nj in ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(nj[i]);
			}
			sql.append(")");
		}

		// ѧԺ
		if (xydm != null && xydm.length > 0) {
			sql.append("and b.xydm in ( ");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}

		// רҵ
		if (zydm != null && zydm.length > 0) {
			sql.append("and b.zydm in ( ");
			for (int i = 0; i < zydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zydm[i]);
			}
			sql.append(")");
		}

		// �༶
		if (bjdm != null && bjdm.length > 0) {
			sql.append("and b.bjdm in ( ");
			for (int i = 0; i < bjdm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(bjdm[i]);
			}
			sql.append(")");
		}
		
		

		params.add(xn);
		
		
		
		sql.append(") a left join xsxxb b on a.xh = b.xh ");
		sql.append("left join (select xydm,count(1) zs,sum(a.xmje) zje ");
		sql.append("from xg_pjpy_pjlsxxb a, xg_view_pjpy_pjryk b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xn = ? ");
		//sql.append("and a.xmmc = ? ");
		//��Ŀ����
		if (xmmc != null && xmmc.length > 0) {
			sql.append("and a.xmmc in ( ");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		// �꼶
		if (nj != null && nj.length > 0) {
			sql.append("and nj in ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(nj[i]);
			}
			sql.append(")");
		}

		// ѧԺ
		if (xydm != null && xydm.length > 0) {
			sql.append("and b.xydm in ( ");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}

		// רҵ
		if (zydm != null && zydm.length > 0) {
			sql.append("and b.zydm in ( ");
			for (int i = 0; i < zydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(zydm[i]);
			}
			sql.append(")");
		}

		// �༶
		if (bjdm != null && bjdm.length > 0) {
			sql.append("and b.bjdm in ( ");
			for (int i = 0; i < bjdm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(bjdm[i]);
			}
			sql.append(")");
		}
		sql.append("group by xydm) c on a.xydm = c.xydm");
		
		String[] colArr=new String[] { "xh", "xm", "xymc",
				"zymc",  "je", "yhkh", "num", "zj" };
		
		colArr=dao.unionArray(colArr,colList.toArray(new String[]{}));
		
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), params
				.toArray(new String[] {}), colArr);

		return list;
	}
	
	/**
	 * ��ȡ��Ŀ�����б�
	 * @return
	 */
	public List<HashMap<String,String>>getXmxzList(){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("   select xzdm,xzmc from xg_pjpy_xmxzb a where exists(select 1 from xg_pjpy_pjxmwhb b where a.xzdm=b.xmxz and b.xmlx<>'02') ");
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xzdm","xzmc"});
	}
	
	/**
	 * ������Ŀ���ͻ�ȡ��Ŀ����
	 * @return
	 */
	public List<HashMap<String,String>>getXmmcBylx(String xmlx,String xn){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select distinct(xmmc)xmmc from xg_pjpy_pjlsxxb where xmlx=? and xn=? ");
		
		return dao.getList(sql.toString(), new String[]{xmlx,xn}, new String[]{"xmmc"});
	}
	
	/**
	 * ��ȡ��������������Ϣ
	 * 
	 * @author ��ǿ
	 */
	public ArrayList<String[]> getCMHJMDHZList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = searchModel.getSearch_tj_xn();
		if (xn == null || xn.length == 0) {
			xn = new String[] { jbszForm.getPjxn() };
		}
		searchModel.setSearch_tj_xn(xn);
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(searchModel);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,xn||xq||xh pk ,a.* from (select xn,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xh,xm,");
		sql.append("wm_concat(case when xmmc='һ�Ƚ�ѧ��' or xmmc='���Ƚ�ѧ��' or xmmc='���Ƚ�ѧ��' then xmmc end ) jxj,");
		sql.append("wm_concat(case when xmmc='����ѧ��' then xmmc end ) shxs,");
		sql.append("wm_concat(case when xmmc='�����Ÿ�' or xmmc='����ѧ���ɲ�' then xmmc end) yxxsgb,");
		sql.append("wm_concat(case when xmmc='��Ṥ�����㽱' or xmmc='ѧϰ�ɼ�������' or xmmc='ѧϰ�ɼ����㽱' or xmmc='˼��Ʒ�����㽱' or xmmc='Ӱ��������Ʒ���㽱' then xmmc end) dxjhdz,");
		sql.append("sum(xmje) ffje,yhkh,wm_concat(bz) bz from (select a.xn,a.xq,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.xh,b.xm,a.xmmc,a.xmje,b.yhkh,a.bz from xg_pjpy_pjlsxxb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh) group by xn,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xh,xm,yhkh order by xn,xq,bjdm,xh) a");
		
		sql.append(query);

		String[] colList = { "pk","bjmc","xm","jxj","shxs","yxxsgb","dxjhdz",
				"ffje", "yhkh", "bz" };
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonQuery(sql.toString(), "", inputV, colList, myForm);

		return list;
	}
	

	/**
	 * ��ý������ͳ���б�
	 * 
	 * @author ��ǿ
	 */
	public List<HashMap<String, String>> getCmhjmdList(String xn, 
			String[] nj, String[] xydm, String[] zydm, String[] bjdm) {

		DAO dao = DAO.getInstance();
		

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.*,rownum from (select xn||xq||xh pk ,a.* from (select xn,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xh,xm,");
		sql.append("wm_concat(case when xmmc='һ�Ƚ�ѧ��' or xmmc='���Ƚ�ѧ��' or xmmc='���Ƚ�ѧ��' then xmmc end ) jxj,");
		sql.append("wm_concat(case when xmmc='����ѧ��' then xmmc end ) shxs,");
		sql.append("wm_concat(case when xmmc='�����Ÿ�' or xmmc='����ѧ���ɲ�' then xmmc end) yxxsgb, ");
		sql.append("wm_concat(case when xmmc='��Ṥ�����㽱' or xmmc='ѧϰ�ɼ�������' or xmmc='ѧϰ�ɼ����㽱' or xmmc='˼��Ʒ�����㽱' or xmmc='Ӱ��������Ʒ���㽱' then xmmc end) dxjhdz,");
		sql.append("sum(xmje) ffje,yhkh,wm_concat(bz) bz from (select a.xn,a.xq,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.xh,b.xm,a.xmmc,a.xmje,b.yhkh,a.bz from xg_pjpy_pjlsxxb a");
		sql.append(" left join view_xsxxb b on a.xh = b.xh) group by xn,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xh,xm,yhkh order by xn,xq,bjdm,xh) a where 1=1 ");

		if (!StringUtils.isNull(xn)) {
			sql.append(" and xn='"+xn+"'");
		}
		if (nj != null && nj.length > 0) {
			sql.append(" and nj in (");
			for (String s : nj) {
				sql.append("'"+s+"',");
			}
			sql.append("'')");
		}
		if (xydm != null && xydm.length > 0) {
			sql.append(" and xydm in (");
			for (String s : xydm) {
				sql.append("'"+s+"',");
			}
			sql.append("'')");
		}
		if (zydm != null && zydm.length > 0) {
			sql.append(" and zydm in (");
			for (String s : zydm) {
				sql.append("'"+s+"',");
			}
			sql.append("'')");
		}
		if (bjdm != null && bjdm.length > 0) {
			sql.append(" and bjdm in (");
			for (String s : bjdm) {
				sql.append("'"+s+"',");
			}
			sql.append("'')");
		}
		sql.append("  order by xn,xq,bjdm,xh) a");
		
		String[] colArr=new String[] { "bjmc","xm","jxj","shxs","yxxsgb","dxjhdz","xymc",
				"ffje", "yhkh", "bz"};
		
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), 
				new String[]{} , colArr);

		return list;
	}
}
