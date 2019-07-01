/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:02:46 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -������ѯ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����03:02:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlzxclDao extends SuperDAOImpl<XlzxclForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLZX_XLZXB_WZDX");
		setKey("zxid");
		setClass(XlzxclForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxclForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.zxid,t.sqid,t.xh,t1.xm,t1.xb,t1.nj,t1.xymc,t2.yyzxsj,t2.yyzt,t.zzaprq,t.zxzt,")
		   .append("(case when t.xszxpj is null then 'δ����' else '������' end) xspjzt ")
		   .append("from XG_XLZX_XLZXB_WZDX t ")
		   .append("left join VIEW_XSBFXX t1 on t.xh = t1.xh ")
		   .append("left join XG_XLZX_YYSQB_WZDX t2 on t.sqid = t2.sqid ")
		   .append("where 1=1 and t.zxs = '")
		   .append(t.getZxs()).append("' ")
		   .append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxclForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/**
	 * ��ѯ��ѯʦ��Ϣ
	 * @param zgh  ְ����
	 * @return
	 */
	public HashMap<String,String> getZxsxx(String zgh){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.zgh,t.xm,t.xb,")
		   .append("to_char(sysdate,'yyyy') - to_char(to_date(t.csrq,'yyyymmdd'), 'yyyy') nl,")
		   .append("t.bmmc,t.lxdh ")
		   .append("from VIEW_FDYXX t ")
		   .append("where t.zgh = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * ����ѧ��������ѯԤԼ����Ų�ѯ������ѯ����
	 * @param sqid 
	 * @return
	 */
	public HashMap<String,String> getXlzxclInfo(String sqid){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from XG_XLZX_XLZXB_WZDX t where t.sqid= ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * ����ѧ��������ѯԤԼ�����ɾ��������ѯ����
	 * @param sqid 
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteXlzxclBySqid(String sqid) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_XLZX_XLZXB_WZDX t where t.sqid=?");
	
		boolean result =  dao.runUpdate(sql.toString(), new String[]{sqid});
		return result;
	}

	/**
	 * 
	 * @����:��ѯ��������
	 * @���ߣ���־��
	 * @���ڣ�2014-5-8 ����15:58:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxclList(XlzxclForm model)
			throws Exception {
		Pages pages = (Pages) model.getClass().getMethod("getPages").invoke(model);
		pages.setPageSize(Integer.MAX_VALUE);
		
		model.getClass().getMethod("setPages",Pages.class).invoke(model, pages);
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from view_xlzx_xlzxcl ")
		   .append("where 1=1 and zxs = '")
		   .append(model.getZxs()).append("' ")
		   .append(searchTj).append(" order by zzaprq desc ");
		return getPageList(model, sql.toString(), inputV);
	}
}
