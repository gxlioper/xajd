/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����10:25:33 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgDao;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgForm;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShForm;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ڶ�֤�����
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����10:25:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdshDao extends SuperDAOImpl<CdshForm> {
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
	private static final Class<CdshForm> MODEL_CLAZZ = CdshForm.class;
	
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CdshForm t)
			throws Exception {
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CdshForm t, User user)
			throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm", "bjdm");	
 		String qxfw = SearchService.getQxfw(user, "a.xtgwid", "a.qxfw", "a.bmlbdm",searchTjByUser);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.* from (select t1.*,row_number() over(partition by sqid order by shsj desc) rn  from ")
		.append("(select a.* , '[' || b.mc || ':' || decode(a.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',a.shzt) || ']' shztmc,b.gwz from (select a.sqid,")
		.append("       a.cdid,")
		.append("       a.xh,")
		.append("       a.fzrxm,")
		.append("       a.fzrlxfs,")
		.append("       a.bmlbdm,")
		.append("       e.bmmc bmlbmc,")
		.append("       a.qxfw,")
		.append("       to_char(to_date(a.sqsj , 'yyyy-MM-dd HH24:mi:ss') , 'yyyy-MM-dd') sqsj,")
		.append("       a.sqsjdkssj,")
		.append("       a.sqsjdjssj,")
		.append("       a.sqly,")
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
		.append("		d.gwid as xtgwid,")
		.append("       d.shzt,")
		.append("       d.shsj,")
		.append("       d.guid as shid,")
		.append("       a.sqsjdkssj || '��' ||  a.sqsjdjssj sqsjd")
		.append("  from XG_XLJK_CDGL_CDSQB a")
		.append("  left join view_xsjbxx b")
		.append("    on a.xh = b.xh")
		.append("  left join zxbz_xxbmdm e on a.bmlbdm = e.bmdm ")
		.append("  left join XG_XLJK_CDGL_CDXXB c")
		.append("    on a.cdid = c.cdid ")
		.append("  left join xg_xtwh_shztb d on a.sqid = d.ywid")
		.append(" ) a  left join xg_xtwh_spgw b on a.xtgwid = b.id where 1=1")
		.append(qxfw);
		
		if(DSH.equals(t.getType()))
			sql.append(" and a.shzt in ('0', '4')) t1) t2 where rn = 1  ");
		else if(YSH.equals(t.getType()))
			sql.append(" and a.shzt not in ('0', '4')) t1) t2 where rn = 1  ");
		
		sql.append(" ")
		.append(searchTj)
		.append(shgwzByUser);
		
		return  getPageList(t, sql.toString(), inputV);
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
	public List<HashMap<String , String>> getYxCdsq(String cdid){
		
		String sql = "select * from XG_XLJK_CDGL_CDSQJGB a WHERE a.cdid = ?";
		
		return dao.getListNotOut(sql, new String[]{cdid});
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(CdshDao.MODEL_CLAZZ);
		super.setKey(CdshDao.KEY_NAME);
		super.setTableName(CdshDao.TABLE_NAME);
	}
	
	/**
	 * 
	 * @����: �鿴ʹ���е���������
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-10-23 ����01:50:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSplcing(String splcid, String cdid){
		StringBuffer sb=new StringBuffer();
		sb.append("select sqid as ywid from XG_XLJK_CDGL_CDSQB a left join XG_XLJK_CDGL_CDXXB b on a.cdid = b.cdid where shzt in(?,?) and a.splcid=? and b.cdid=?");
		return dao.getListNotOut(sb.toString(), new String[]{Constants.YW_SHZ,Constants.YW_YTH,splcid,cdid});
	}
	

}
