/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����08:52:28
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤����-�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����08:52:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbshDao extends SuperDAOImpl<XszbbshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("bbsqid");
		super.setTableName("xg_rcsw_xszbb_xszbbsqb");

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk,");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjdm, t2.nj, t2.zybj, t2.zybjmc, ");
		sql.append(" t3.xszbblxmc,t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' || "); 
		sql.append(" decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz,");
		sql.append(" row_number() over(partition by t1.bbsqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_xszbb_xszbbsqb t1  left join view_xsbfxx t2 on t1.xh = t2.xh  ");
		sql.append(" left join xg_rcsw_xszbb_bblxwhb t3  on t1.xszbblxdm = t3.xszbblxdm  left join xg_xtwh_shztb  ");
		sql.append(" t4 on t1.bbsqid = t4.ywid ");
		
		
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh = '"+user.getUserName()+"'   ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:TODO(�õ�ѧ��֤���������ϸ��Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����04:45:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getXszbbshInfo(XszbbshForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sj,t1.dd,t1.filepath,t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk, ");
		if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//�ൺ�Ƶ���Ի�
			sql.append("t1.ccqdz,t1.cczdz,t1.sfbbhcyhk sfbbhcyhkmc,");
		}
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj, ");
		sql.append(" decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc,t3.xszbblxmc from xg_rcsw_xszbb_xszbbsqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
		sql.append(" on t1.xszbblxdm = t3.xszbblxdm   where t1.bbsqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getBbsqid()});
	}
	

	/**
	 * 
	 * @����:TODO(����ѧ����������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-18 ����03:42:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsbbsq(String bbsqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_xszbb_xszbbsqb ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where bbsqid = ?");
		inputV[0] = shzt;
		inputV[1] = bbsqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-18 ����03:44:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbsqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteXsbbsq(String bbsqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_rcsw_xszbb_xszbbjgb ");
		sql.append(" where bbsqid = ? ");
		inputV[0] = bbsqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:����ID��ȡ������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-25 ����10:34:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneXsbbsqInfo(String id){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_rcsw_xszbb_xszbbsqb where bbsqid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
}
