package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsh.ZbshForm;

public class YbshDao extends SuperDAOImpl<YbshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(YbshForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YbshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select t.*,to_number(t.yf) || '��' yfmc,t1.xm,t2.bmmc xymc,");
		sql.append(" t4.shzt shztx,t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append(" decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,");
		sql.append("  row_number() over(partition by t.sbid order by t4.shsj desc) rn");
		sql.append(" from xg_xljk_xlwygl_ybsbb t");
		sql.append(" left join yhb t1");
		sql.append(" on t.txr =t1.yhm");
		sql.append(" left join ZXBZ_XXBMDM t2");
		sql.append(" on t.xydm = t2.bmdm");
		sql.append(" left join xg_xtwh_shztb t4");
		sql.append(" on t.sbid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5");
		sql.append(" on t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6");
		sql.append(" on t4.gwid = t6.id");
		sql.append(" where t5.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(")t where 1=1 and rn =1");
		sql.append(searchTj);
		sql.append(" order by xn,yf,txrq desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(YbshForm.class);
		this.setTableName("xg_xljk_xlwygl_ybsbb");
		this.setKey("sbid");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���ʱ����������Ϣ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����04:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean shUpdateWtxxb(String ybsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xljk_xlwygl_wtxxb set ybjgid = ybsqid where ybsqid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{ybsqid});
	}
	
	/**
	 * 
	 * @����: ����ʱ����������Ϣ��zbjgid�ÿ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����04:38:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sbsqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cxUpdateWtxxb(String ybsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xljk_xlwygl_wtxxb set ybjgid = '' where ybsqid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{ybsqid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ������������е�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����04:45:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgbRepeaData(YbshForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_ybsbjgb where xn = ? and yf = ? and xydm = ?  ");
		return dao.runUpdate(sql.toString(),new String[]{t.getXn(),t.getYf(),t.getXydm()});
	}
	
	/**
	 * 
	 * @����: ɾ��������Ϣ��������е�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����04:55:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgWtxxData(YbshForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where ybjgid in(");
		sql.append(" 	select jgid  from xg_xljk_xlwygl_ybsbjgb where xn = ? and yf = ? and xydm = ? ");
		sql.append(" )");
		return dao.runUpdate(sql.toString(),new String[]{t.getXn(),t.getYf(),t.getXydm()});
	}

}
