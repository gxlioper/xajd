package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmsq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class KycxxmsqDao extends SuperDAOImpl<KycxxmsqForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_kycxgl_kycxxmsqb");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KycxxmsqForm t)
			throws Exception {
		return null;
	}

		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(KycxxmsqForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.sqid,t1.xn,t1.xmmc,t1.zdls,t1.lbdm,t1.xmsqrxm,t1.xmsqsj,t1.czr,t1.czsj,t1.shzt,t1.splc,c.xmcy,");
		sql.append(" t2.lbmc, ");
		sql.append(" t3.xm zdlsxm, ");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '���˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc ");
		sql.append(" from xg_kycxgl_kycxxmsqb t1 ");
		
		sql.append(" left join ( ");
		sql.append(" SELECT cy.jgid,wm_concat('ѧ�ţ�'||cy.xh||'��������'||vx.xm||'����ϵ�绰��'||vx.sjhm) xmcy ");
		sql.append(" FROM xg_kycxgl_kycxxmcyb cy LEFT JOIN VIEW_XSBFXX vx ");
		sql.append(" ON cy.xh = vx.xh GROUP BY cy.jgid ");
		sql.append(" ) c on c.jgid = t1.sqid ");
		
		sql.append(" left join ( ");
		// =========== ������������ begin ==========
		sql.append("select a.* from ( ");
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_kycxgl_kycxxmlb a ");
		sql.append(") a where a.isopen='true' ");
		// =========== ������������ end ==========		
		sql.append(" ) t2 on t1.lbdm = t2.lbdm ");
		sql.append(" left join fdyxxb t3 on t1.zdls = t3.zgh ");
		// =========== ������������ begin ==========
		sql.append(" where t2.lbdm is not null ");
		// =========== ������������ end ==========
		sql.append(" ) a where 1 = 1 ");
		String userName = user.getUserName();
		String userType = user.getUserType();
		if (!"admin".equalsIgnoreCase(userType)) {
			sql.append(" and a.czr='" + userName + "' ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	public KycxxmsqForm getModel(KycxxmsqForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xmmc,t1.zdls,t1.lbdm,t1.xmsqrxm,t1.xmsqsj,t1.czr,t1.czsj,t1.shzt,t1.splc, ");
		sql.append(" t1.fjxx,t2.lbmc, ");
		sql.append(" t3.xm zdlsxm, ");
		sql.append(" decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '4', '������', '5', '�����', '','�������', t1.shzt) shztmc ");
		sql.append(" from xg_kycxgl_kycxxmsqb t1 ");
		sql.append(" left join xg_kycxgl_kycxxmlb t2 on t1.lbdm = t2.lbdm ");
		sql.append(" left join fdyxxb t3 on t1.zdls = t3.zgh ");
		sql.append(" where t1.sqid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
		KycxxmsqForm model=new KycxxmsqForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * �޸����״̬
	 */
	public boolean updateKycxxmsqShzt(KycxxmsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_kycxgl_kycxxmsqb ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.runUpdate(sql.toString(),inputV);
	}
	/**
	 * �Ƿ��Ѵ���
	 */
	public boolean checkExistSave(KycxxmsqForm model, User user) {
		String sqid = "-1";
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_kycxgl_kycxxmsqb where xn = ? and xmmc = ? and sqid <> ? ");
		if(model.getSqid() != null){
			sqid = model.getSqid();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(),model.getXmmc(),sqid }, "num");
		return "0".equals(num);
	}
	/**
	 * ��ȡ�������
	 */
	public String getShlcID(String lbdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_kycxgl_kycxxmlb where lbdm=? ");
		return dao.getOneRs(sql.toString(), new String[] { lbdm }, "splc");
	}
	
}
