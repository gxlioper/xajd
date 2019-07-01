/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����04:50:47 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �������� Dao
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����04:50:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdsqDao extends SuperDAOImpl<CdsqForm> {

	/**
	 * @���ԣ� KEY_NAME ������
	 */
	private static final String KEY_NAME = "sqid";
	/**
	 * @���ԣ� TABLE_NAME ����
	 */
	private static final String TABLE_NAME = "XG_XLJK_CDGL_CDSQB";
	/**
	 * @���ԣ� MODEL_CLAZZ class ����
	 */
	private static final Class<CdsqForm> MODEL_CLAZZ = CdsqForm.class;
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdsqForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.sqid,")
		.append("       a.cdid,")
		.append("       a.xh,")
		.append("       a.fzrxm,")
		.append("       a.fzrlxfs,")
		.append("       a.bmlbdm,")
		.append("       d.bmmc bmlbmc,")
		.append("       to_char(to_date(a.sqsj , 'yyyy-MM-dd HH24:mi:ss') , 'yyyy-MM-dd') sqsj,")
		.append("       a.sqsjdkssj,")
		.append("       a.sqsjdjssj,")
		.append("       a.sqly,")
		.append("       a.shzt,")
		.append("       a.splcid,")
		.append("       b.xm,")
		.append("       b.nj,")
		.append("       b.xydm,")
		.append("       b.xymc,")
		.append("       b.zydm,")
		.append("       b.zymc,")
		.append("       b.bjdm,")
		.append("       b.bjmc,")
		.append("       c.cdmc,")
		.append("       c.ld,")
		.append("       c.fj,")
		.append("       c.rnrs,")
		.append("       c.sfbz,")
		.append("       c.dwkfsjkssj,")
		.append("       c.dwkfsjjssj,")
		.append("       c.yt,")
		.append("       c.jbsbjs,")
		.append("       c.sfkfsq,")
		.append("       decode(a.shzt ,'0' ,'δ�ύ' , '1' , 'ͨ��' , '2' , '��ͨ��' , '3' , '�˻�' , '5' , '�����') shztmc,")
		.append("       a.sqsjdkssj || '��' ||  a.sqsjdjssj sqsjd, c.splcid splcidnew")
		.append("  from XG_XLJK_CDGL_CDSQB a")
		.append("  left join view_xsjbxx b")
		.append("    on a.xh = b.xh")
		.append("  left join zxbz_xxbmdm d on a.bmlbdm = d.bmdm ")
		.append("  left join XG_XLJK_CDGL_CDXXB c")
		.append("    on a.cdid = c.cdid) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	public List<HashMap<String, String>> getPageListOfSqjl(CdsqForm t, User user)
		throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("  select a.*,b.cdmc, a.sqsjdkssj || '��' ||  a.sqsjdjssj sqsjd from (");
		sql.append("select a.cdid,a.sqsjdkssj,a.sqsjdjssj from XG_XLJK_CDGL_CDSQB a where a.shzt='5' ");
		sql.append(" union all ");
		sql.append(" select b.cdid,b.sqsjdkssj,b.sqsjdjssj from XG_XLJK_CDGL_CDSQJGB b)a ");
		sql.append("  left join XG_XLJK_CDGL_CDXXB b on a.cdid=b.cdid where 1=1");
		if (!StringUtil.isNull(t.getCdmc())) {
			sql.append("  and b.cdmc like '%'||?||'%'");
			params.add(t.getCdmc());
		}
		if (!StringUtil.isNull(t.getSqsjdkssj())) {
			sql.append("  and a.sqsjdjssj >= ?");
			params.add(t.getSqsjdkssj());
		}
		if (!StringUtil.isNull(t.getSqsjdjssj())) {
			sql.append("  and a.sqsjdjssj <= ?");
			params.add(t.getSqsjdjssj());
		}
	    return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * ��ȡ����������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-29 ����10:19:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getCdsqxx(String sqid){
		StringBuffer sql = new StringBuffer();
		if ("10346".equals(Base.xxdm)) {
			sql.append("select b.xfgfsyxy,a.cyrs,a.fzrxm,a.fzrlxfs,a.sqid , a.cdid , a.xh , a.bmlbdm , a.sqsj , a.sqsjdkssj , a.sqsjdjssj , a.sqly , a.shzt ,b.cdmc , b.ld , b.fj , b.rnrs , b.sfbz , b.dwkfsjkssj, b.dwkfsjjssj , b.yt , b.jbsbjs , b.sfkfsq , b.splcid ,");
			sql.append(" b.lxr,b.lxfs,b.filepath,c.xb , c.xm , c.nj , c.xymc , c.zymc , c.bjmc , d.bmmc,k.pj,k.pjbz ");
			sql.append(" from XG_XLJK_CDGL_CDSQB a ");
			sql.append(" left join xg_xljk_cdgl_cdsqjgb k on a.sqid=k.sqid ");
		}else {
			sql.append("select b.xfgfsyxy,a.cyrs,a.fzrxm,a.fzrlxfs,a.sqid , a.cdid , a.xh , a.bmlbdm , a.sqsj , a.sqsjdkssj , a.sqsjdjssj , a.sqly , a.shzt ,b.cdmc , b.ld , b.fj , b.rnrs , b.sfbz , b.dwkfsjkssj, b.dwkfsjjssj , b.yt , b.jbsbjs , b.sfkfsq , b.splcid ,");
			sql.append(" b.lxr,b.lxfs,b.filepath,c.xb , c.xm , c.nj , c.xymc , c.zymc , c.bjmc , d.bmmc ");
			sql.append(" from XG_XLJK_CDGL_CDSQB a ");
		}
		
		sql.append(" left join XG_XLJK_CDGL_CDXXB b ");
		sql.append(" on a.cdid = b.cdid ");
		sql.append(" left join view_xsjbxx c ");
		sql.append(" on a.xh = c.xh ");
		sql.append(" left join zxbz_xxbmdm d on a.bmlbdm = d.bmdm where a.sqid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @����:��ȡ�Ѿ��������еĻ����Ѿ�����ͨ���ĳ��������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����02:15:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getYxCdsq(String cdid, String sqid){
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select sqsjdkssj,sqsjdjssj,cdid from XG_XLJK_CDGL_CDSQJGB ");
		sql.append(" union all ");
		sql.append(" select sqsjdkssj,sqsjdjssj,cdid from XG_XLJK_CDGL_CDSQB where SHZT not in ('0','2','3') ");
		if (!StringUtil.isNull(sqid)){
			sql.append(" and sqid <> ? ");
			params.add(sqid);
		}
		sql.append(" ) a where a.cdid = ? ");
		params.add(cdid);
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{ }));
	}
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ������˵�����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����02:39:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getCdsqInSpcByXh(String xh){
		String sql = "select * from XG_XLJK_CDGL_CDSQB a where a.shzt ='5' and a.xh = ?";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CdsqDao.MODEL_CLAZZ);
		super.setKey(CdsqDao.KEY_NAME);
		super.setTableName(CdsqDao.TABLE_NAME);
	}


	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:25:35
	 * @param jddm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkSfktj(String cdid) {
		
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from XG_XLJK_CDGL_CDXXB t2 ");
		sql.append("  where t2.sfkfsq = '1'       ");
		sql.append("    and t2.cdid = ? ");
		num = dao.getOneRs(sql.toString(), new String[] { cdid }, "num");
	
		return num;
	}
}
