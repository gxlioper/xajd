/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-7 ����01:56:26 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl;

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
 * @ʱ�䣺 2014-5-7 ����01:56:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlzxglDao  extends SuperDAOImpl<XlzxglForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(XlzxglForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxglForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select pkid,sqid,zxid,xh,xm,nj,xymc,xydm,zymc,zydm,bjmc,bjdm,xb,sjhm,yyzxsj,yyzxzt,yyzt,zxsxm,zzaprq,zxsd,zxslxdh,zxdz,zxzt,xspjzt ")
		   .append("from (select t.sqid || t1.zxid pkid,t.sqid,t1.zxid,t.xh,t2.xm,t2.nj,t2.xymc,t2.xydm,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,t2.xb,t2.sjhm,t.yyzxsj,")
		   .append("t.yyzxzt,t.yyzt,t3.xm zxsxm,t1.zzaprq,")
		   .append("(case when t1.zxsdkssj is null then '' else t1.zxsdkssj || '��' end) || t1.zxsdjssj zxsd,")
		   .append("t1.zxslxdh,t1.zxdz,t1.zxzt,")
		   .append("(case when t1.xszxpj is null then 'δ����' else '������' end) xspjzt ")
		   .append("from XG_XLZX_YYSQB_WZDX t ")
		   .append("left join XG_XLZX_XLZXB_WZDX t1 on t.sqid = t1.sqid ")
		   .append("left join VIEW_XSBFXX t2 on t.xh = t2.xh ")
		   .append("left join VIEW_FDYXX t3 on t1.zxs = t3.zgh ")
		   .append("union ")
		   .append("select a1.sqid || a.zxid pkid,a1.sqid,a.zxid,a.xh,a2.xm,a2.nj,a2.xymc,a2.xydm,a2.zymc,a2.zydm,a2.bjmc,a2.bjdm,a2.xb,a2.sjhm,a1.yyzxsj,")
		   .append("a1.yyzxzt,a1.yyzt,a3.xm zxsxm,a.zzaprq,")
		   .append("(case when a.zxsdkssj is null then '' else a.zxsdkssj || '��' end) || a.zxsdjssj zxsd,")
		   .append("a.zxslxdh,a.zxdz,a.zxzt,")
		   .append("(case when a.xszxpj is null then 'δ����' else '������' end) xspjzt ")
		   .append("from XG_XLZX_XLZXB_WZDX a ")
		   .append("left join XG_XLZX_YYSQB_WZDX a1 ")
		   .append("on a.sqid = a1.sqid ")
		   .append("left join VIEW_XSBFXX a2 ")
		   .append("on a.xh = a2.xh ")
		   .append("left join VIEW_FDYXX a3 ")
		   .append("on a.zxs = a3.zgh) t ")
		   .append("where 1=1 ")
		   .append(searchTjByUser)
		   .append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:��ѯ��������
	 * @���ߣ���־��
	 * @���ڣ�2014-5-7 ����05:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxglList(XlzxglForm model, User user)
			throws Exception {
		Pages pages = (Pages) model.getClass().getMethod("getPages").invoke(model);
		pages.setPageSize(Integer.MAX_VALUE);
		
		model.getClass().getMethod("setPages",Pages.class).invoke(model, pages);
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from view_xlzx_xlzxgl t ");
		sql.append("where 1=1 ")
		   .append(searchTjByUser)
		   .append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

}
