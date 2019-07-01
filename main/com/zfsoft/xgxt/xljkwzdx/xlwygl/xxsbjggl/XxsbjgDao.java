/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����10:03:51 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����10:03:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbjgDao extends SuperDAOImpl<XxsbjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbjgForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*, b.xm , b.bjdm , b.bjmc , b.zydm , b.zymc , b.xydm , " +
				"b.xymc , b.nj , b.xb , c.ZBID,c.ZBLX,c.ZBZC,c.ZBKSRQ,c.ZBJSRQ,c.CZSJ,z.xqmc from XG_XLJK_XLWYGL_XLSBJGB a left join view_xsjbxx b " +
				"on a.xh = b.xh left join XG_XLJK_XLWYGL_ZBRCXXB c on a.sbzbid = c.zbid left join xqdzb z on a.xq = z.xqdm) t1 where 1=1 ");
				
		if(StringUtils.equals("0", t.getSblx())){
			sql.append("and t1.sblx = 0 ");
		}else if(StringUtils.equals("1", t.getSblx())){
			sql.append("and t1.sblx = 1 ");
		}else if(StringUtils.equals("2", t.getSblx())){
			sql.append("and t1.sblx = 2 ");
		}
		
		sql.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ȡ��ѯ��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-9 ����03:04:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqjgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getModelMap(String sqjgid )throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*, b.xm , b.bjdm , b.bjmc , b.zydm , b.zymc , b.xydm , " +
				"b.xymc , b.nj , b.xb , c.ZBID,c.ZBLX,c.ZBZC,c.ZBKSRQ,c.ZBJSRQ,c.CZSJ,z.xqmc, c.zbksrq||' ~ '||c.zbjsrq as zbqzrq from XG_XLJK_XLWYGL_XLSBJGB a left join view_xsjbxx b " +
				"on a.xh = b.xh left join XG_XLJK_XLWYGL_ZBRCXXB c on a.sbzbid = c.zbid left join xqdzb z on a.xq = z.xqdm) t1 where sbjgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{sqjgid});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(XxsbjgForm.class);
		setKey("sbjgid");
		setTableName("XG_XLJK_XLWYGL_XLSBJGB");
	}

	/**
	 * 
	 * @����:����sbsqidɾ��������¼
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-3 ����10:08:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sbsqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteBySqid(String sbsqid) throws Exception{
		String sql = "delete from XG_XLJK_XLWYGL_XLSBJGB a where a.sbsqid = ? ";
		return dao.runUpdate(sql, new String[]{sbsqid});
	}
	
	
	/**
	 * 
	 * @����:����ظ�
	 * @���ߣ�1036
	 * @���ڣ�2014-7-11 ����04:46:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param sblx
	 * @param zc
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExist(String xh , String sblx  , String zc)throws Exception{
		String sql = "select count(1) rs from XG_XLJK_XLWYGL_XLSBJGB a where a.xh = ? and a.sblx = ? and a.sbzbid = ? ";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh , sblx , zc}, "rs")) > 0 ? true : false;
	}
	
	
	public List<HashMap<String, String>> getXnList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xn dm,xn mc from XG_XLJK_XLWYGL_ZBRCXXB order by xn");
		return dao.getListNotOut(sql.toString(), null);	
	}
	
	public List<HashMap<String,String>> getZcList(String xn, String xq, String sblx) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select zbid,zbzc from XG_XLJK_XLWYGL_ZBRCXXB t ");
		sql.append(" where t.xn = '"+xn+"' and t.xq = '"+xq+"' and t.zblx = '"+sblx+"' "); 
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"zbid","zbzc"});
	}
	
	public List<HashMap<String,String>> getQzrq(String xn, String xq, String zbid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zbksrq||' ~ '||t.zbjsrq as zbqzrq from XG_XLJK_XLWYGL_ZBRCXXB t ");
		sql.append(" where t.xn = '"+xn+"' and t.xq = '"+xq+"' and t.zbid = '"+zbid+"' "); 
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"zbqzrq"});
	}
	
}
