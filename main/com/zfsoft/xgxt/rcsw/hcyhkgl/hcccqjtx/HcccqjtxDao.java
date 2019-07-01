/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����02:11:15 
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx;

import java.sql.SQLException;
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
 * @ģ������: ��������д����ģ��
 * @�๦������: TODO(��������д) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:40:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjtxDao extends SuperDAOImpl<HcccqjtxForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo(){
		
		super.setKey("ccqjtxid");
		super.setTableName("xg_rcsw_hcyhk_hcccqjtxb");
		
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjtxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(HcccqjtxForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm");
		}
		sql.append(" from (");
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,t1.bz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		sql.append(" t1.shzt,t1.splc,t2.xm, (case t2.xb when '1' then  '��' when '2' then 'Ů' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t2.jtdz,t6.bjmc,t2.lxdh,t6.xydm,t6.zydm,t2.bjdm,t6.xymc,t6.zymc,t2.nj,t3.xqmc, ");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��','2','��ͨ��', '3','���˻�', '4','������', ");
		sql.append(" '5', '�����', '','�������',t1.shzt) shztmc from xg_rcsw_hcyhk_hcccqjtxb ");
		sql.append(" t1  left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t6 on t2.bjdm = t6.bjdm ");
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm ) a left join  xg_rcsw_hcyhk_jcszb b on 1=1 ");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on a.bjdm = bzr.bjdm");
		}
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	public HcccqjtxForm getModel(HcccqjtxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.lxmc hcyhklxmc,c.xqmc from xg_rcsw_hcyhk_hcccqjtxb a ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx b on a.hcyhklx = b.lxdm ");
		sql.append(" left join xqdzb c on a.xq=c.xqdm");
		sql.append(" where a.ccqjtxid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getCcqjtxid() });
	}

	/**
	 * 
	 * @����:TODO(��ȡ��������ID)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����02:19:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param Rcxwlbdldm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcsw_hcyhk_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public HashMap<String, String> getHcccqjtx(String ccqjtxid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_hcyhk_hcccqjtxb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.ccqjtxid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{ccqjtxid});
	}
	
	
	
	public boolean isCanDel(String ccqjtxid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_hcyhk_hcccqjtxb where ccqjtxid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{ccqjtxid});
		String bbsqzt=map.get("shzt");
		//���δ�ύ�ſ����ύ
		return null==bbsqzt||bbsqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-24 ����04:41:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccqjtxid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateHcccqjtx(HcccqjtxForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_hcyhk_hcccqjtxb ");
		sql.append(" set ");
		sql.append(" shzt = ? ,");
		sql.append(" splc = ? ");
		sql.append(" where ccqjtxid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getCcqjtxid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:TODO(�鿴��������д��Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����04:45:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getHcccqjtxInfo(HcccqjtxForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,t1.bz, ");
		sql.append(" (t1.ccqdz || '-' ||t1.cczdz) hcccqjmc,t3.xqmc,t4.lxmc hcyhklxmc ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjtxb t1 left join view_xsxxb t2 ");
		sql.append("  on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t4 on t1.hcyhklx=t4.lxdm ");
		sql.append("  where t1.ccqjtxid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getCcqjtxid()});
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ��, ѧ��,ѧ���жϻ𳵳˳�������д�����Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-13 ����05:25:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForHcccqjtxSave(HcccqjtxForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_hcyhk_hcccqjtxb where xh=? and xn = ?  and xq = ? and shzt <> '2' ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ��, ѧ��,ѧ��,������дid�жϻ𳵳˳�������д�����Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-17 ����03:14:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForHcccqjtxUpdate(HcccqjtxForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_hcyhk_hcccqjtxb where xh=? and xn = ?  and xq = ? and shzt <> '2' and ccqjtxid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq(),model.getCcqjtxid()}, "num");
		return num;
	}
	
	public int getDshCount() throws SQLException{
		String sql = "select count(1) num from xg_rcsw_hcyhk_hcccqjtxb where shzt='0' or shzt='5'  ";
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ��������Ĭ�ϵĳ˳����վ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-19 ����11:29:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXxccqdz() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select ccqdz from xg_rcsw_hcyhk_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "ccqdz");
		
	}
	
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ���³˳�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-28 ����11:51:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getHcccqj(String xh){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t1.*,(t1.ccqdz || '-' || t1.cczdz) hcccqjmc,row_number() over (partition by xh order by xn desc,xq desc) rm ");
		sql.append("from xg_rcsw_hcyhk_hcccqjjgb t1 ) where rm =1 and xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
		
	}
	
	/*
	 * ȡѧ������
	 */
	public String getXqmc(String xq){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xqmc from xqdzb where xqdm = ? ");
		
		return dao.getOneRs(sql.toString(), new String[] {xq}, "xqmc");
		
	}

}
