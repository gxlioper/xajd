/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-4 ����04:20:00 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-4 ����04:20:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmSqDao extends SuperDAOImpl<ZdzmSqForm> {
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmSqForm t)
			throws Exception {
		
		return null;
	}

	/**
	 * ��ҳ��ѯ
	 */
	@Override public List<HashMap<String, String>> getPageList(ZdzmSqForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.splcid splcidnew from (select a.*,")
		.append("b.xm,")
		.append("b.xb,")
		.append("b.nj,")
		.append("b.xydm,")
		.append("b.xymc,")
		.append("b.zydm,")
		.append("b.zymc,")
		.append("b.bjdm,")
		.append("b.bjmc,")
		.append("b.pycc,")
		.append("b.mzmc,")
		.append("b.sfzh,")
		.append("b.syd,")
		.append("b.csrq,")
		.append("(select pyccmc from xg_xsxx_pyccdmb where pyccdm = b.pycc ) pyccmc,")
		.append(" decode(a.shzt ,'0' ,'δ�ύ' , '1' , 'ͨ��' , '2' , '��ͨ��' , '3' , '�˻�' , '5' , '�����') shztmc")
		.append(" from XG_RCSW_ZDZM_ZDZMSQB a")
		.append(" left join view_xsxxb b")
		.append(" on a.xh = b.xh) t1 , XG_RCSW_ZDZM_JBSZ t2 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:����ѧ�Ż�ȡ�ڶ�֤����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-31 ����10:23:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmListByXh(String xh) throws Exception{
		String sql = "select a.* from XG_RCSW_ZDZM_ZDZMSQB a where a.xh = ? ";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ��������е����� 
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-31 ����10:24:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmInShlcByXh(String xh) throws Exception{
		String sql = "select a.* from XG_RCSW_ZDZM_ZDZMSQB a where a.xh = ? and shzt = '5' ";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		setTableName("XG_RCSW_ZDZM_ZDZMSQB");
		setKey("ZDZMSQID");
		setClass(ZdzmSqForm.class);
	}

}
