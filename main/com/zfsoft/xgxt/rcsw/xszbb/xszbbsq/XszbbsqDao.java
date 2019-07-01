/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����02:11:15 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsq;

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
 * @ģ������: ѧ��֤�����������ģ��
 * @�๦������: TODO(ѧ��֤��������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-17 ����02:11:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbsqDao extends SuperDAOImpl<XszbbsqForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("bbsqid");
		super.setTableName("xg_rcsw_xszbb_xszbbsqb");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbsqForm t)
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
	public List<HashMap<String, String>> getPageList(XszbbsqForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		if("11400".equals(Base.xxdm)){
			sql.append(" select a.*,b.splc splcidnew from (");
			sql.append(" select t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk, ");
			sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.xymc,t2.zymc,t2.zydm, t2.bjdm, t2.nj, t2.sfzh,");
			sql.append(" decode(t1.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
			sql.append(" '���˻�', '4', '������', '5', '�����', '', '�������', ");
			sql.append(" t1.shzt) shztmc,t3.xszbblxmc,t4.fdyxm,t4.ylbxh from xg_rcsw_xszbb_xszbbsqb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
			sql.append(" on t1.xszbblxdm = t3.xszbblxdm  " +
					" left join xsxxb t4 on t1.xh = t4.xh" +
					" ) a, xg_rcsw_xszbb_jcszb b where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}else{
			sql.append(" select a.*,b.splc splcidnew");
			//��������ְҵ����ѧԺ
			if("13871".equals(Base.xxdm)){
				sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm");
			}
			sql.append("  from (");
			sql.append(" select t1.dd,t1.sj,t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk, ");
			sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.xymc,t2.zymc,t2.zydm, t2.bjdm, t2.nj, t2.sfzh,");
			sql.append(" decode(t1.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
			sql.append(" '���˻�', '4', '������', '5', '�����', '', '�������', ");
			sql.append(" t1.shzt) shztmc,t3.xszbblxmc,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc from xg_rcsw_xszbb_xszbbsqb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
			sql.append(" on t1.xszbblxdm = t3.xszbblxdm  ) a left join  xg_rcsw_xszbb_jcszb b on 1 = 1  ");
			//��������ְҵ����ѧԺ
			if("13871".equals(Base.xxdm)){
				sql.append(" left join view_xg_bzrxx bzr on a.bjdm = bzr.bjdm");
			}
			sql.append(" where 1=1");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:TODO(�鿴��������������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:57:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneBbsqList(String  bbsqId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.bbsqid,t1.xh,t1.sqsj,t1.xszbblxdm,decode(t1.sfbbhcyhk, 'y','��', 'n', '��', t1.sfbbhcyhk) sfbbhcyhk, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc, ");
		sql.append(" decode(t1.shzt,  '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc,t3.xszbblxmc from xg_rcsw_xszbb_xszbbsqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh left join xg_rcsw_xszbb_bblxwhb t3 ");
		sql.append(" on t1.xszbblxdm = t3.xszbblxdm where t1.bbsqid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{bbsqId});
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
		sql.append(" select  splc from xg_rcsw_xszbb_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public HashMap<String, String> getBbsq(String bbsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_xszbb_xszbbsqb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.bbsqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{bbsqid});
	}
	
	
	/**
	 * 
	 * @����:TODO(��ȡ��������ά������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����02:46:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwdldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBblxwhList() {
		String sql = "select xszbblxdm,xszbblxmc from xg_rcsw_xszbb_bblxwhb where shlc is not null order by xszbblxdm ";
		return dao.getList(sql, new String[] {}, new String[] {"xszbblxdm", "xszbblxmc" });
	}
	
	
	public boolean isCanDel(String bbsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_xszbb_xszbbsqb where bbsqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{bbsqid});
		String bbsqzt=map.get("shzt");
		//���δ�ύ�ſ����ύ
		return null==bbsqzt||bbsqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:13:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBbsq(XszbbsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_xszbb_xszbbsqb ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where bbsqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getBbsqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����04:45:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getXszbbsqInfo(XszbbsqForm t){
		
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
	 * @����:TODO(����˵ļ�¼����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����10:10:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from xg_rcsw_xszbb_xszbbsqb where shzt='0' or shzt='5'  ";
		
		return dao.getOneRsint(sql);
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ���жϸ�ѧ��ѧ��֤�����Ƿ��Ѿ�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����08:40:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(XszbbsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_xszbb_xszbbsqb where xh=? and ( shzt<>'1' and shzt<>'2') and xszbblxdm = ?");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXszbblxdm()}, "num");
		return num;

	}
	
	/** 
	 * @����:��ȡ�𳵳˳���������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-23 ����05:06:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getHcccqj(String xh) {
		String sql = "select a.* from (select xh,ccqdz,cczdz,xn,xq,rownum rn from xg_rcsw_hcyhk_hcccqjjgb where xh = ? order by txsj desc) a where a.rn = 1";
		return dao.getMapNotOut(sql, new String[]{xh});
	}

}
