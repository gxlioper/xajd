/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:40:57 
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �𳵳˳�����������ģ��
 * @�๦������: TODO(�𳵳˳�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:34:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjjgDao extends SuperDAOImpl<HcccqjjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_rcsw_hcyhk_hcccqjjgb");
		super.setKey("ccqjjgid");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (");
		sql.append(" select t2.sjhm,t2.xz,t2.jtdz,t2.sfzh,t2.rxrq,t1.ccqjjgid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,t1.bz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		if(Base.xxdm.equals("13011")){//�ൺ�Ƶ���Ի�
			sql.append(" t1.shwcsj, ");
		}
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm,");
		}
		sql.append(" t1.sjly,t1.ccqjtxid,t2.xm, (case t2.xb when '1' then  '��' when '2' then 'Ů' else t2.xb end) xb,t6.bjmc,t2.lxdh,t61.xydm,t61.xymc,t61.zymc,t61.zydm,t61.bjdm zybj,t61.bjmc zybjmc,t2.bjdm,t2.nj,t3.xqmc,t8.lxmc hcyhklxmc ");
		sql.append("  from xg_rcsw_hcyhk_hcccqjjgb ");
		sql.append(" t1  left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t6 on t2.bjdm = t6.bjdm  ");
		sql.append(" left join view_njxyzybj_all t61 on t2.zybj = t61.bjdm ");
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t8 on t1.hcyhklx = t8.lxdm ");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
		}
		sql.append(" ) a where 1 = 1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ�� ѧ���ж�ѧ��֤�����������Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����08:39:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(HcccqjjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_hcyhk_hcccqjjgb where xh=? and xn = ? and xq = ?");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;

	}
	
	/**
	 * 
	 * @����:TODO(����ѧ�� ѧ��ѧ�ںͽ��Id�ж�ѧ��֤�����������Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-17 ����02:34:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(HcccqjjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_hcyhk_hcccqjjgb where xh=? and xn = ? and xq = ? and ccqjjgid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq(),model.getCcqjjgid()}, "num");
		return num;

	}
	/**
	 * ���ͨ������ɾ��������
	 */
	public boolean deleteExist(HcccqjjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		" delete from xg_rcsw_hcyhk_hcccqjjgb where xh=? and xn = ? and xq = ? ");
		return dao.runUpdate(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()});
	}
	
	/**
	 * 
	 * @����:TODO(����������Դ�ж��Ƿ��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����01:34:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccqjjgid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String ccqjjgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_hcyhk_hcccqjjgb where ccqjjgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{ccqjjgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(�鿴�𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����01:34:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccqjjgid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getHcccqjjg(String ccqjjgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_hcyhk_hcccqjjgb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.ccqjjgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{ccqjjgid});
	}
	
	@Override
	public HcccqjjgForm getModel(HcccqjjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.lxmc hcyhklxmc from xg_rcsw_hcyhk_hcccqjjgb a ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx b on a.hcyhklx = b.lxdm ");
		sql.append(" where a.ccqjjgid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getCcqjjgid() });
	}

	/**
	 * 
	 * @����:TODO(����ѧ�Ų鿴�𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����01:31:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreHcccqjjgList(HcccqjjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] outputValue = new String[] { "xn","xqmc","hcccqjmc","bz"};
		if("10351".equals(Base.xxdm)){
			outputValue = new String[] { "xn","xqmc","hcccqjmc","hcyhklxmc","bz"};
		}
		String[] inputValue = new String[]{model.getXh()};
		sql.append(" select t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,t1.bz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc,t2.xqmc,t3.lxmc hcyhklxmc ");
		sql.append(" from  xg_rcsw_hcyhk_hcccqjjgb t1 left join  xqdzb t2 on t1.xq= t2.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t3 on t1.hcyhklx = t3.lxdm ");
		sql.append(" where t1.xh = ? order by xn ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputValue, outputValue, model);
	}


	
	
	
}
