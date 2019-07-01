/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����10:09:46 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ������Ϣά��Dao
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����10:09:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdxxwhDao extends SuperDAOImpl<CdxxwhForm> {

	/**
	 * @���ԣ� KEY_NAME ������
	 */
	private static final String KEY_NAME = "cdid";
	/**
	 * @���ԣ� TABLE_NAME ����
	 */
	private static final String TABLE_NAME = "XG_XLJK_CDGL_CDXXB";
	/**
	 * @���ԣ� MODEL_CLAZZ class ����
	 */
	private static final Class<CdxxwhForm> MODEL_CLAZZ = CdxxwhForm.class;
	
	/*
	 * 
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 *
	 * ��ҳ��ѯ
	 */
	@Override
	public List<HashMap<String, String>> getPageList(CdxxwhForm t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 
	 * ��ҳ��ѯ
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdxxwhForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select a.cdid,a.xfgfsyxy,")
		.append("       a.cdmc,")
		.append("       a.ld,")
		.append("       a.fj,")
		.append("       a.rnrs,")
		.append("       a.sfbz,")
		.append("       a.dwkfsjkssj,")
		.append("       a.dwkfsjjssj,")
		.append("       a.yt,")
		.append("       a.jbsbjs,")
		.append("       a.sfkfsq,")
		.append("       a.splcid,a.lxr,a.lxfs,")
		.append("       b.mc || '��' || replace(b.lcxx, ';', '->') lcxx,")
		.append("       a.dwkfsjkssj || '-' || a.dwkfsjjssj dwkfsj, ")
		.append("       decode(a.sfkfsq , '0' , '��' , '1' , '��' , 'δ֪') sfkfsqmc ")
		.append("  from XG_XLJK_CDGL_CDXXB a")
		.append("  left join (select splc, mc, lcxx")
		.append("               from (select splc,")
		.append("                            a.mc,")
		.append("                            to_char(WM_CONCAT(c.mc)")
		.append("                                    over(partition by splc order by xh)) lcxx,")
		.append("                            row_number() over(partition by splc order by xh desc) lcxh")
		.append("                       from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c")
		.append("                      where a.id = b.splc")
		.append("                        and b.spgw = c.id)")
		.append("              where lcxh = 1) b")
		.append("    on a.splcid = b.splc where 1=1 ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡpopup������Ϣ�б���Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����01:45:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPopupCdxx(CdxxwhForm model) throws Exception{
		StringBuffer searchTj = new StringBuffer();	
		
		//���ɸ߼���ѯ�������������ֵ 
		String search_cdmc = model.getSearch_cdmc();
		String search_rnrsmin = model.getSearch_rnrsmin();
		String search_rnrsmax = model.getSearch_rnrsmax();
		String search_yt = model.getSearch_yt();
		String search_dwkfsjkssj = model.getSearch_dwkfsjkssj();
		String search_dwkfsjjssj = model.getSearch_dwkfsjjssj();
		
		String search_sfkfsq  = model.getSearch_sfkfsq();
		
		if(StringUtils.isNotBlank(search_cdmc)){
			searchTj.append(" and cdmc like '%").append(search_cdmc).append("%' ");
		}
		if(StringUtils.isNotBlank(search_rnrsmin)){
			searchTj.append(" and to_number(a.rnrs) >= ").append(search_rnrsmin);
		}
		if(StringUtils.isNotBlank(search_rnrsmax)){
			searchTj.append(" and to_number(a.rnrs) <= ").append(search_rnrsmax);
		}
		if(StringUtils.isNotBlank(search_yt)){
			searchTj.append(" and a.yt like '%").append(search_yt).append("%' ");
		}
		if(StringUtils.isNotBlank(search_dwkfsjkssj)){
			searchTj.append(" and a.dwkfsjkssj <= '").append(search_dwkfsjkssj).append("' ");
		}
		if(StringUtils.isNotBlank(search_dwkfsjjssj)){
			searchTj.append(" and a.dwkfsjjssj >= '").append(search_dwkfsjjssj).append("' ");
		}
		if(StringUtils.isNotBlank(search_sfkfsq) && StringUtils.equals("2", search_sfkfsq)){
			searchTj.append(" and 1 = 1 ");
		}else if(StringUtils.isNotBlank(search_sfkfsq) && StringUtils.equals("1", search_sfkfsq)){
			searchTj.append(" and a.sfkfsq = '1' ");
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.cdid,")
		.append("       a.cdmc,")
		.append("       a.ld,")
		.append("       a.fj,")
		.append("       a.rnrs,")
		.append("       a.sfbz,")
		.append("       a.dwkfsjkssj,")
		.append("       a.dwkfsjjssj,")
		.append("       a.yt,")
		.append("       a.jbsbjs,")
		.append("       a.sfkfsq,")
		.append("       a.splcid,a.lxr,a.lxfs,")
		.append("       b.mc || '��' || replace(b.lcxx, ';', '->') lcxx,")
		.append("       a.dwkfsjkssj || '-' || a.dwkfsjjssj dwkfsj, ")
		.append("       decode(a.sfkfsq , '0' , '��' , '1' , '��' , 'δ֪') sfkfsqmc ")
		.append("  from XG_XLJK_CDGL_CDXXB a")
		.append("  left join (select splc, mc, lcxx")
		.append("               from (select splc,")
		.append("                            a.mc,")
		.append("                            to_char(WM_CONCAT(c.mc)")
		.append("                                    over(partition by splc order by xh)) lcxx,")
		.append("                            row_number() over(partition by splc order by xh desc) lcxh")
		.append("                       from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c")
		.append("                      where a.id = b.splc")
		.append("                        and b.spgw = c.id)")
		.append("              where lcxh = 1) b")
		.append("    on a.splcid = b.splc where  1 = 1 ")
		.append(searchTj);
		
		return getPageList(model, sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:���ݳ���ID��ȡ������ϢMap
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����04:21:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getCdxxByCdid(String cdid){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.cdid,a.xfgfsyxy,")
		.append("       a.cdmc,")
		.append("       a.ld,")
		.append("       a.fj,")
		.append("       a.rnrs,")
		.append("       a.sfbz,")
		.append("       a.dwkfsjkssj,")
		.append("       a.dwkfsjjssj,")
		.append("       a.yt,")
		.append("       a.jbsbjs,")
		.append("       a.sfkfsq,")
		.append("       a.splcid,")
		.append("       a.qxfw,a.lxr,a.lxfs,a.filepath,a.xysfilepath,")
		.append("       b.mc || '��' || replace(b.lcxx, ';', '->') lcxx,")
		.append("       a.dwkfsjkssj || '-' || a.dwkfsjjssj dwkfsj, ")
		.append("       decode(a.sfkfsq , '0' , '��' , '1' , '��' , 'δ֪') sfkfsqmc ")
		.append("  from XG_XLJK_CDGL_CDXXB a")
		.append("  left join (select splc, mc, lcxx")
		.append("               from (select splc,")
		.append("                            a.mc,")
		.append("                            to_char(WM_CONCAT(c.mc)")
		.append("                                    over(partition by splc order by xh)) lcxx,")
		.append("                            row_number() over(partition by splc order by xh desc) lcxh")
		.append("                       from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c")
		.append("                      where a.id = b.splc")
		.append("                        and b.spgw = c.id)")
		.append("              where lcxh = 1) b")
		.append("    on a.splcid = b.splc where 1=1 ")
		.append(" and cdid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{cdid});
	}
	
	/**
	 * 
	 * @����:���ݳ���id����Ƿ�����
	 */
	public boolean check(String cdid){
		String sql1 = "select * from XG_XLJK_CDGL_CDSQJGB a where a.cdid = ?";
		String sql2 = "select * from XG_XLJK_CDGL_CDSQB a where a.cdid = ?";
		return dao.getListNotOut(sql1, new String[]{cdid}).size() + dao.getListNotOut(sql2, new String[]{cdid}).size() >0 ? true : false;
	}
	
	/**
	 * 
	 * @����:���ݳ������ƻ�ȡ������
	 */
	public int getCdsByCdmc(String cdmc){
		String sql = "select count(1) cds from XG_XLJK_CDGL_CDXXB a where a.cdmc = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{cdmc}, new String[]{"cds"})[0]);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CdxxwhDao.MODEL_CLAZZ);
		super.setKey(CdxxwhDao.KEY_NAME);
		super.setTableName(CdxxwhDao.TABLE_NAME);
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-17 ����03:44:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCdxx(String sqid,String tableName){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.cdmc, t1.jbsbjs,t2.bmmc");
		sql.append(" from "+tableName+" t");
		sql.append(" left join XG_XLJK_CDGL_CDXXB t1");
		sql.append(" on t.cdid = t1.cdid");
		sql.append(" left join ZXBZ_XXBMDM t2");
		sql.append(" on t.bmlbdm = t2.bmdm");
		if(tableName.toUpperCase().equals("XG_XLJK_CDGL_CDSQB")){
			sql.append(" where t.sqid = ?");
		}else{
			sql.append(" where t.jgid = ?");
		}
		
		return dao.getMapNotOut(sql.toString(),new String[]{sqid});
	}
	
	/**
	 * 
	 * @����: ��ȡ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-17 ����04:24:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjList(String ywid){
		StringBuilder sql = new StringBuilder();
		sql.append("  select shyj from xg_xtwh_shztb where ywid = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{ywid});
	}

}
