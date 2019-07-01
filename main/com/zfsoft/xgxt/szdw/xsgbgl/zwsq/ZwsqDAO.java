/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����4:18:18 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-8 ����2:30:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZwsqDAO extends SuperDAOImpl<ZwsqForm> {

	/*
	      ����:
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_szdw_xsgb_zwsqb");
		super.setClass(ZwsqForm.class);
	}
	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/*
	      ����:��ѯ��ѵ��Ŀ�����б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZwsqForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append(" select d.xh,d.xydm,d.zydm,d.bjdm,d.xymc,d.zymc,d.xm,");
		sql.append("d.xb,d.nj,d.bjmc,d.zzmmmc,d.mz,d.yhmc,d.yhkh,e.tc,e.XJLBMC, ");
		sql.append("sqid, sqsj, sqly,  b.*, a.shzt as shztdm," );
		sql.append("decode(shzt,0,'δ�ύ',1,'ͨ��',2,'��ͨ��',3,'�˻�',4,'������',5,'�����','����')shzt,  ");
		sql.append("a.splc,c.lxmc ");
		sql.append(" from xg_szdw_xsgb_zwsqb a " );
		sql.append("left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid " );
		sql.append("left join xg_szdw_xsgb_zwlxb c on b.lxdm = c.lxdm " );
		sql.append("left join view_xsjbxx d on a.xh = d.xh " );
		sql.append("left join view_xsxxb e on a.xh = e.xh " );
		sql.append(" where a.shzt <>9 ");
		sql.append(" order by a.sqsj desc)t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * @����:��ѯ�Ѿ����������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:35:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getSqCount(String xh,String zwid) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) from xg_szdw_xsgb_zwsqb b where b.xh = '"+xh+"' and b.zwid ='"+zwid+"' and b.shzt not in(9,2)");
		return dao.getOneRsint(sql.toString());
	}
	/**
	 * @����:ȡ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:35:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param spids
	 * @return
	 * @throws SQLException
	 * int[] ��������
	 */
	public int[] updateSq(String[] spids ) throws SQLException{
		String sql = " update xg_szdw_xsgb_zwsqb b set b.shzt='9' where b.sqid = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spids.length; i++) {
			params.add(new String[]{spids[i]});
		}
		return dao.runBatch(sql, params);
	}
	/**
	 * @����:����ְ������Ƿ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����2:36:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zwid
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getSqCountByZwid(String zwid) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(sl) from (");
		sql.append(" select count(1)sl from xg_szdw_xsgb_zwsqb b where b.shzt<>9 and  b.zwid in( ");
		sql.append(zwid);
		sql.append(" )   union all ");
		sql.append(" select count(1)sl from xg_szdw_xsgb_dwb b where b.zwid in(");
		sql.append(zwid);
		sql.append(" ))");
		return dao.getOneRsint(sql.toString());
	}
	
	public int updateZwsq(ZwsqForm model) throws Exception{
		String sql = " update xg_szdw_xsgb_zwsqb b set b.shzt=? ,splc=? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSplc(),model.getSqid()});
	}
}
