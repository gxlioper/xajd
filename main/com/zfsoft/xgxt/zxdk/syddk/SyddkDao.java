/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-29 ����09:30:46 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-29 ����09:30:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SyddkDao extends SuperDAOImpl<SyddkModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(SyddkModel.class);
		super.setKey("id");
		super.setTableName("xg_zxdk_syddk");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SyddkModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SyddkModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx ");
		sql.append(" ,t2.zybj,t2.zybjmc,x1.sydm,x1.symc ");
		//�����Ƽ���ѧ���Ի�
		if(Base.xxdm.equals("10704")){
			sql.append(",t2.xb,t2.bysj,t2.jtdz,t2.qqhm,t7.dcmc kncd,t5.cyxm,t5.cylxdh,t6.fdyxm,t6.fdylxdh ");
		}
		if(Base.xxdm.equals("10704")){
			sql.append("from xg_zxdk_syddk t1 left join view_xsxxb t2 on t1.xh = t2.xh  ");
		}else{			
			sql.append("from xg_zxdk_syddk t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		}
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join dmk_yh t3 on t1.dkyh = t3.yhdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * 
	 * @����: ��ѧ�Ų�ѯ��Դ�ش���
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-27 ����09:31:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSydkList(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*,t3.yhmc ");
		sql.append("from xg_zxdk_syddk t1 ");
		sql.append("left join dmk_yh t3 on t1.dkyh = t3.yhdm ");
		sql.append("where t1.xh = ?");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 
	 * @����: �������Ӵ�����Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-29 ����03:20:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertDkxx(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_zxdk_sydxx(id,xn,xf,zsf,shf) values (?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����: ɾ��������ϸ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-29 ����03:22:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delDkxx(String[] ids) throws Exception{
		
		StringBuilder sql = new StringBuilder("delete from xg_zxdk_sydxx where id in (");
		
		for (int i = 0 ; i < ids.length ; i++){
			sql.append("?");
			
			if (i != ids.length - 1){
				sql.append(",");
			}
		}
		
		sql.append(")");
		return dao.runUpdate(sql.toString(), ids);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ������Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-29 ����03:27:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDkxxList(String id){
		
		String sql = "select * from xg_zxdk_sydxx where id = ?";
		return dao.getListNotOut(sql, new String[]{id});
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.String)
	 */
	
	@Override
	public SyddkModel getModel(String keyValue) throws Exception {
		
		String sql = "select t1.*,t2.yhmc from xg_zxdk_syddk t1 left join dmk_yh t2 on t1.dkyh = t2.yhdm where t1.id = ?";
		
		return super.getModel(sql, new String[]{keyValue});
	}
	
	/**
	 * 
	 * @����:�ظ��ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-13 ����09:05:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getExists(String xh,String xn) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zxdk_syddk where xh = ? and xn = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{xh,xn}, "num");	
		return num;
	}
	
	
}
