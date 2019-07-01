/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:45:46 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:45:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsglDao extends SuperDAOImpl<TsxsglForm> {
	


	
	public List<HashMap<String, String>> getPageList(TsxsglForm model, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r from  view_xsxx_tsxsxx   t where 1=1 ");
		sql.append(searchTjByUser);
		
		sql.append(searchTj);
		
		sql.append(" order by t.lrsj desc ");
		
		return getPageList(model, sql.toString(), inputV);
		
	}
	public List<HashMap<String, String>> getTsxsStuList(TsxsglForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from (select distinct t2.xh,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj");
		sql.append(" from view_xsbxxx t2 where t2.xh not in(select xh from xg_xsxs_tsxsb)");
		sql.append(")a where  1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	public TsxsglForm getModelByXh(TsxsglForm model) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xsxx_tsxsb where xh = ?");
		
		
		return getModel(sql.toString(), new String[]{model.getXh()});
	}
	
	/**
	 * ����Xh��ѯ����ѧ����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String xh){
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from view_xsxx_tsxsxx  where xh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	/**
	 * 
	 * @����:���ù�ע״̬
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����03:07:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param gzzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xsxx_tsxsb set ");
		String[] params =new String[id.length+1];
		params[0]=gzzt;
		if(!StringUtil.isNull(gzzt)){
			sql.append(" gzzt = ? where ");
		}
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("xh =?");
			
			if (i != n-1){
				sql.append(" or ");
			}
			params[i+1]=id[i];
		}
		boolean flag = dao.runUpdate(sql.toString(), params);
		return  flag;
		
	}
	


	
	protected void setTableInfo() {
		super.setTableName("xg_xsxx_tsxsb");
		super.setKey("xh");
		super.setClass(TsxsglForm.class);
		
	}
	

	public List<HashMap<String, String>> getPageList(TsxsglForm t)
			throws Exception {
		return null;
	}

}
