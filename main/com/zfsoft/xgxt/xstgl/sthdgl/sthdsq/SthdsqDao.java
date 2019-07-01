/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-27 ����05:03:37 
 */  
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-27 ����05:03:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SthdsqDao extends SuperDAOImpl<SthdsqForm> {
	@Override
	protected void setTableInfo() {
		super.setClass(SthdsqForm.class);
		super.setKey("hdid");
		super.setTableName("xg_sthd_dj");

	}


	@Override
	public List<HashMap<String, String>> getPageList(SthdsqForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(SthdsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t6.xm,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,");
		sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc, ");
	    sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append(" from xg_sthd_dj t1 ");
		sql.append(" left join view_xsjbxx t6 on t1.xh=t6.xh ");
		sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:��ȡ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-27 ����11:03:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_stgl_sthdgl_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	public SthdsqForm getSqxx(SthdsqForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.*,t6.xm,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,");
		sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.zzmmmc, t6.sydm1 sydm,t6.symc1 symc,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc, ");
		sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append(" from xg_sthd_dj t1 ");
		sql.append(" left join view_xsjbxx t6 on t1.xh=t6.xh ");
		sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where t.hdid=? ");
		return super.getModel(sql.toString(),new String[]{model.getHdid()});
	}
	
	
	/**
	 * 
	 * @����:�жϵ�ǰ��λ�Ƿ�����д��¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-27 ����11:05:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveSqJl(SthdsqForm model) throws Exception {
		String id = model.getHdid() == null ? "-1" : model.getHdid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from(select case when fwsj=?  then '0' else '1' end sfcd ");
		sql.append("from xg_sthd_dj where xh=? and  hdid <>?)where sfcd='1' ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getFwsj(),model.getXh(),id}, "num");
		return Integer.valueOf(num) > 0;
	}

	/**
	 * 
	 * @����:������Ŀ�б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-27 ����10:24:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStxmList()throws Exception{
		String sql ="select a.stid,a.stxmmc from xg_stgl_jtjg a left join xg_stgl_stlb b on a.stlbdm=b.stlbdm where b.stlbmc like '%־Ը��%'";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	

}
