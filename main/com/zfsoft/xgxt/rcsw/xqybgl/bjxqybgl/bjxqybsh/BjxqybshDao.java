/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����01:46:16 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-31 ����01:46:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxqybshDao extends SuperDAOImpl<BjxqybshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybshForm t, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();				
		sql.append(" select a.* from ( select t1.sqid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd,substr(t1.yf,6,2)yf,t1.bygzkzqk, ");		
		sql.append(" t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj,t1.txr,t1.shzt,t1.splc,t1.bjdm,t2.xymc,t2.xydm,t2.nj,t2.bjmc,t2.zymc,t2.zydm,t7.xqmc,t3.xm lrrxm, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', 'δ���', '1', 'ͨ��','2','��ͨ��','3','�˻�','4','������', '5', '�����') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_bjzyy_xqyb_bjyb_sq t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);		
		
		return getPageList(t, sql.toString(), inputV);
		
	}

	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ��༶ѧ�������дҳ������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����02:18:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybshInfo(User user,BjxqybshForm model){
		
		StringBuffer sql = new StringBuffer();				
		sql.append(" select a.* from ( select t1.sqid,t1.xn,t1.xq,t1.yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj, ");		
		sql.append(" t1.txr,t1.shzt,t1.splc,t1.bjdm,t2.xymc,t2.bjmc,t2.zymc,t7.xqmc,t3.xm lrrxm, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', 'δ���', '1', 'ͨ��','2','��ͨ��','3','�˻�','4','������', '5', '�����') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_bjzyy_xqyb_bjyb_sq t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");		
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" where t5.spyh = ? ");
		sql.append(" ) a where sqid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{user.getUserName(),model.getSqid()});
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ����°༶ѧ������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-6 ����04:48:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBjxqybsq(String sqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_bjzyy_xqyb_bjyb_sq ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ�ɾ���༶ѧ���±����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-6 ����04:52:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteBjxqybjg(String sqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_bjzyy_xqyb_bjyb_jg ");
		sql.append(" where lylcywid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("sqid");
		super.setTableName("xg_bjzyy_xqyb_bjyb_sq");
		super.setClass(BjxqybshForm.class);
	}

}
