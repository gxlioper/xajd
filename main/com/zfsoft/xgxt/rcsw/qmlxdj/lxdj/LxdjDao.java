/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����08:57:49 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxdj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����08:57:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public final class LxdjDao extends SuperDAOImpl<LxdjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxdjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxdjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.* from (");
		sql.append(" select t.*,");
		sql.append(" rownum rnn,");
		sql.append(" t1.XM,");
		sql.append(" t1.XB,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.NJ,");
		sql.append(" t1.ZYMC,t1.mzmc,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.BJDM,t1.zybj,t1.zybjmc,t5.sydm,t6.symc,");
		sql.append(" t2.xqmc,");
		sql.append(" t3.mc lxgj,");
		sql.append(" t4.mc fxgj,");
		sql.append(" ssx.shengmc,");
		sql.append(" ssx.shimc,");
		sql.append(" ssx.xianmc,");
		sql.append(" ssx.shengmc || ssx.shimc || ssx.xianmc ssxdz,");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" 'δ�ύ',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '�˻�',");
		sql.append("  '5',");
		sql.append(" '�����',");
		sql.append(" t.shzt) shztmc");
		sql.append(" from xg_rcsw_lxqxdj_lxqxdjsqb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join XG_XTWH_SYBJGLB t5 on t1.bjdm = t5.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t6 on t5.sydm = t6.sydm ");
		sql.append(" left join xqdzb t2");
		sql.append(" on t.xq = t2.xqdm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t3");
		sql.append(" on t.lxjtgjdm = t3.dm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t4");
		sql.append(" on t.fxjtgjdm = t4.dm");
		sql.append(" left join xg_view_dmk_qx ssx");
		sql.append(" on ssx.qxdm = t.mddssx");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(LxdjForm.class);
		this.setKey("sqid");
		this.setTableName("xg_rcsw_lxqxdj_lxqxdjsqb");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����02:28:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotExist(String xh,String xn,String xq,String flag){
		StringBuffer sql = new StringBuffer();
		if("sq".equals(flag)){
			sql.append(" select count(1) num");
			sql.append(" from (select xh, xn, xq");
			sql.append(" from xg_rcsw_lxqxdj_lxqxdjsqb");
			sql.append(" union all");
			sql.append(" select xh, xn, xq");
			sql.append(" from xg_rcsw_lxqxdj_lxqxdjjgb)");
			sql.append(" where xh = ?");
			sql.append(" and xn = ?");
			sql.append(" and xq = ?");
			sql.append(" ");
		}else{
			sql.append(" select count(1) num");
			sql.append(" from xg_rcsw_lxqxdj_lxqxdjjgb");
			sql.append(" where xh = ?");
			sql.append(" and xn = ?");
			sql.append(" and xq = ?");
			sql.append(" ");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "num");	
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @����: dmList
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����03:15:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmList(){
		StringBuffer sql = new StringBuffer();
		sql.append("   select * from xg_rcsw_lxqxdj_dmwhb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	//��ȡ��У����
	public List<HashMap<String, String>> getLxlxList(){
		StringBuffer sql = new StringBuffer();
		sql.append("   select * from xg_rcsw_lxlx_dmwhb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * 
	 * @����: ��ȡ�鿴��Ϣmap
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����03:45:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCkxx(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,");
		sql.append(" t1.xqmc,");
		sql.append(" t2.mc lxgj,");
		sql.append(" t3.mc fxgj,t4.mc lxlxmc");
		sql.append(" from");
		sql.append(" xg_rcsw_lxqxdj_lxqxdjsqb t");
		sql.append(" left join xqdzb t1");
		sql.append(" on t.xq = t1.xqdm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t2");
		sql.append(" on t.lxjtgjdm = t2.dm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t3");
		sql.append(" on t.fxjtgjdm = t3.dm");
		sql.append(" left join xg_rcsw_lxlx_dmwhb t4 on t.lxlx = t4.dm ");
		sql.append(" where t.sqid = ?");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
}
