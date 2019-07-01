/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����09:12:18 
 */  
package com.zfsoft.xgxt.zdxljk.tbxs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @�๦������: ���������--�ر����ѧ�� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-2-11 ����09:11:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TbxsDao extends SuperDAOImpl<TbxsModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {

	}

	public boolean saveThjl(TbxsModel model,List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_zdxljk_tbgxxsb(xh,thsj,gxlx,gzlx,qxyy,thnr,cljg,ftr) values (?,?,?,?,?,?,?,?)";
		
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	
	/**��ѧ�Ų�ѯ̸����¼**/
	public List<HashMap<String,String>> getThjlByXh(String xh){
		
		String sql = "select a.*,case when TO_NUMBER(sysdate - to_date(czsj,'yyyy-MM-dd HH24:mi:ss'))>1  then 'N' else 'Y' end sfkxg  from xg_zdxljk_tbgxxsb a where a.xh = ?";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	
	public boolean delThjl(String xh) throws Exception{
		String sql= "delete from xg_zdxljk_tbgxxsb where xh = ? and TO_NUMBER(sysdate - to_date(czsj,'yyyy-MM-dd HH24:mi:ss'))<1";
		return dao.runUpdate(sql, new String[]{xh});
	}
	public boolean updateGzlx(TbxsModel model) throws Exception{
		String sql= "update xg_zdxljk_tbgxxsb set gzlx=? ,qxyy=? where xh=?";
		return dao.runUpdate(sql, new String[]{model.getGzlx(),model.getQxyy(),model.getXh()});
	}
	
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(TbxsModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(TbxsModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (");
		sql.append(" select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.sfzx,case when t4.num>0 then 'N' else 'Y' end candel,");
		sql.append(" t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.qxsj ");
		sql.append(" from (select t1.*,row_number() over(partition by t1.xh order by t1.thsj desc) px from xg_zdxljk_tbgxxsb t1");
		sql.append(" ) t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join (select t1.*,row_number() over(partition by t1.xh order by t1.xh,t1.qxsj desc) px from ");
		sql.append(" xg_zdxljk_qxgzb t1) t3 on t1.xh = t3.xh  and t3.px=1 ");
		sql.append(" left join (select count(1) num,xh from xg_zdxljk_tbgxxsb where TO_NUMBER(sysdate - to_date(czsj,'yyyy-MM-dd HH24:mi:ss'))>1 group by xh) t4 on t1.xh=t4.xh");
		sql.append(" where t1.xh in(select t.xh from xg_zdxljk_tbgxxsb t where 1=1)");
		sql.append(searchTjByUser);
		sql.append(" and t1.px=1 and t2.sfzx=?) where 1=1 ");
		
		sql.append(searchTj);
		sql.append("order by case gzlx when 'Σ������' then 1");
		sql.append(" when '�ص��ע' then 2 when 'һ���ע' then 3 when 'ȡ����ע' then 4 when '����ע' then 5 end asc,czsj desc");
		
		String[] params = StringUtils.joinStrArr(new String[]{t.getZxzt()},inputV);
		
		return getPageList(t, sql.toString(), params);
	}
	
	
	/**������ѯ***/
	public List<HashMap<String, String>> getExportData(TbxsModel t, User user)
		throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		t.getPages().setPageSize(Integer.MAX_VALUE);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj from xg_zdxljk_tbgxxsb ");
		sql.append("t1 left join view_xsjbxx t2 on t1.xh = t2.xh and t2.sfzx=?) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append("order by case gzlx when 'Σ������' then 1");
		sql.append(" when '�ص��ע' then 2 when 'һ���ע' then 3 when 'ȡ����ע' then 4 when '����ע' then 5 end asc,thsj desc");
		String[] params = StringUtils.joinStrArr(new String[]{t.getZxzt()},inputV);
		return getPageList(t, sql.toString(), params);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zdxljk_tbgxxsb where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("xh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}

	
	
	public boolean saveSzgz(TbxsModel t, String[] ids) throws Exception {

		if (ids == null || ids.length == 0) {
			throw new NullPointerException();
		}
		String sql ="update xg_zdxljk_tbgxxsb set gzlx=? ,qxyy=? where xh=?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0, n = ids.length; i < n; i++) {
			if (StringUtil.isNull(ids[i])) {
				continue;
			}
			params.add(new String[] { t.getGzlx(),t.getQxyy(), ids[i] });
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @����:ɾ��ȡ����ע
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-10-19 ����02:59:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delQxgz(String[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zdxljk_qxgzb where ");
		sql.append("(");

		for (int i = 0, n = ids.length; i < n; i++) {
			sql.append("xh=?");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), ids);
	}
	

	/**ȡ����ע�б�*/
	public List<HashMap<String,String>> getQxgzList(String xh){
		
		String sql = "select * from xg_zdxljk_qxgzb where xh = ?";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
}
