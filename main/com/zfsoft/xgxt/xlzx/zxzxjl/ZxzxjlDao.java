/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-22 ����10:53:51 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.zxsgly.ZxsglyDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-12-22 ����10:53:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxjlDao extends SuperDAOImpl<ZxzxjlModel>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxjlModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxjlModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.sjhm,t2.mzmc,t2.dzyx,t2.csrq,");
		sql.append(" (select count(1) from xg_xljk_bjzyy_zxzxjlb b where t1.xh = b.xh ");
		if(!new ZxsglyDao().isZxsGly(user.getUserName())){
			sql.append(" and b.txr='"+user.getUserName()+"'");
		}
		sql.append(" ) cs from xg_xljk_bjzyy_zxzxjbxxb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" ) t where 1 = 1 ");
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZxzxjlModel.class);
		super.setKey("xh");
		super.setTableName("xg_xljk_bjzyy_zxzxjbxxb");
		
	}
	
	/** 
	 * @����:��ȡ��¼��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����04:12:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getRecord(ZxzxjlModel model) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) num from xg_xljk_bjzyy_zxzxjbxxb where xh = ?");
		return dao.getOneRs(sb.toString(), new String[]{model.getXh()}, "num");
	}
	
	/** 
	 * @����:����ѧ������ɾ��������ѯ��¼(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-22 ����03:54:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhs
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteZxzxjl(String[] xhs) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from xg_xljk_bjzyy_zxzxjlb where xh in ( ");
		for(int i = 0;i<xhs.length;i++){
			sb.append("?");
			if(i != xhs.length -1){
				sb.append(",");
			}
		}
		sb.append(" )");
		return dao.runUpdate(sb.toString(), xhs);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��������ѯ��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-5 ����10:56:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxlzxxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,");
		sql.append(" t1.MZMC mz,");
		sql.append(" t1.ZJMC zjxy,");
		sql.append(" t1.XYMC xy,");
		sql.append(" t1.NJ,");
		sql.append(" t1.SJHM lxdh,");
		sql.append(" t1.ZYMC zy,");
		sql.append(" t1.DZYX yx,");
		sql.append(" t1.XB,");
		sql.append(" t1.XM,");
		sql.append(" to_char(to_date(t1.CSRQ, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy') n,");
		sql.append(" to_char(to_date(t1.CSRQ, 'yyyy-mm-dd hh24:mi:ss'), 'mm') y,");
		sql.append(" to_char(to_date(t.djrq, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy') nd,");
		sql.append(" to_char(to_date(t.djrq, 'yyyy-mm-dd hh24:mi:ss'), 'mm') mon,");
		sql.append(" to_char(to_date(t.djrq, 'yyyy-mm-dd hh24:mi:ss'), 'dd') d");
		sql.append(" from xg_xljk_bjzyy_zxzxjbxxb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" where t.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/** 
	 * @����:��ȡ��ѯidLIST
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-27 ����01:55:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxIdList(String xh){
		String sql = "select id,bh,zxsj from xg_xljk_bjzyy_zxzxjlb where xh = ?";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
}
