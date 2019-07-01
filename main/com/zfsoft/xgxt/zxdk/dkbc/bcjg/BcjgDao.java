/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:43:18 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.bcjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @�๦������: �����
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:43:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BcjgDao extends SuperDAOImpl<BcjgModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(BcjgModel.class);
		super.setKey("id");
		super.setTableName("xg_zxdk_dkbcjgb");
	}


	@Override
	public List<HashMap<String, String>> getPageList(BcjgModel t)
			throws Exception {
		
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(BcjgModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,x1.sydm,x1.symc,t3.mc dclbmc,t10.mc yjxfmc ");
		sql.append("from xg_zxdk_dkbcjgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf=t10.dm ");
		sql.append(" left join dmk_yh t11 on t1.yhdm=t11.yhdm ");
		sql.append("left join xg_zxdk_dclbdmb t3 on t1.dclb=t3.dm ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	public BcjgModel getModel(String id) throws Exception{
        StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t3.mc yjxfmc,t4.mc dclbmc,t10.yhmc ");
		sql.append("from xg_zxdk_dkbcjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_xfdmb t3 on t1.yjxf=t3.dm ");
		sql.append(" left join dmk_yh t10 on t1.yhdm=t10.yhdm ");
		sql.append("left join xg_zxdk_dclbdmb t4 on t1.dclb=t4.dm ) t where id = ? ");
		return getModel(sql.toString(),new String[]{id});
	}
	/**��ѯ������ϸ **/
	public List<HashMap<String,String>> getBfxxList(String xh){
		
		String sql = "select t1.*,t2.mc bfqxmc from xg_zxdk_bfdcje t1 left join xg_zxdk_bfqxb t2 on t1.bfqx = t2.dm where t1.xh = ? order by t1.bfsj ";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**��ѧ��ѧ���ѯ�Ƿ������� */
	
	public String getCountByXhAndXn(BcjgModel model) {
		
		String sql = "select count(1) count from xg_zxdk_dkbcjgb where xh = ?";
		
		return dao.getOneRs(sql, new String[]{model.getXh()}, "count");
	}

	public boolean deleteByKey(String key) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zxdk_dkbcjgb where xh = ? ");
		return dao.runUpdate(sql.toString(), new String[]{key});
		
	}
	
	/**
	 * 
	 * @����:�����ҵ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-4 ����05:23:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcjyBcjglist(String xh){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paramater = new ArrayList<String>();
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t11.yhmc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.mc dclbmc,t10.mc yjxfmc ");
		sql.append("from xg_zxdk_dkbcjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_xfdmb t10 on t1.yjxf=t10.dm ");
		sql.append(" left join dmk_yh t11 on t1.yhdm=t11.yhdm ");
		sql.append("left join xg_zxdk_dclbdmb t3 on t1.dclb=t3.dm where t1.xh = ?");
		paramater.add(xh);
		return dao.getListNotOut(sql.toString(), paramater.toArray(new String[]{}));
	} 
}
