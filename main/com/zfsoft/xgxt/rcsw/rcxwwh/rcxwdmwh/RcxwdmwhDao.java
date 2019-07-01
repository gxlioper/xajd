/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:14:33 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ ����ģ��
 * @�๦������: �ճ���Ϊ ����ά��
 * @���ߣ� dlq [���ţ�995]
 * @ʱ�䣺 2013-7-30 ����04:14:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RcxwdmwhDao extends SuperDAOImpl<RcxwdmwhForm> {

	/**
	 * 
	 * ��ȡ��Ϊ����
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����03:54:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXwdlPageList(RcxwdmwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

	/*	sql.append(" select d.rcxwlbdldm,d.rcxwlbdlmc,decode(e.lcxx,'','�������',e.lcxx)lcxx from  xg_rcsw_rcxwdbdlb d ");
		sql.append(" left join   ( select splc, mc || '��' || replace(max(lcxx), ',', '->') lcxx from (select splc, ");
		sql.append(" a.mc,to_char(WM_CONCAT(c.mc) over(partition by splc order by xh)) lcxx ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw' and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) group by splc, mc )  e on  d.splc = e.splc  where 1=1 ");*/

		sql.append(" select d.rcxwlbdldm,d.rcxwlbdlmc,d.sqkssj,d.sqjssj,d.sqkg,");
		sql.append(" decode(e.lcxx, '', '�������', mc || '��' || replace(lcxx, ';', '->')) lcxx ");
		sql.append(" from xg_rcsw_rcxwdbdlb d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh)) lcxx, xh,row_number() over(partition by splc order by xh desc ) as ddd ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'rcsw'and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splc = e.splc where 1 = 1 ");
	
		
		if (!StringUtil.isNull(model.getRcxwlbdldm())) {
			params.add(model.getRcxwlbdldm());
			sql.append(" and d.rcxwlbdldm = ? ");
		}

		if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
			params.add(model.getRcxwlbdlmc());
			sql.append(" and d.rcxwlbdlmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));

	}

	/**
	 * �����Ϊ������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXwdlList(RcxwdmwhForm model) throws Exception {
		String searchTj = "";
		List<String> inputV = new ArrayList<String>();
		String sql = "select * from (select a.*,rownum r from xg_rcsw_rcxwdbdlb a order by a.rcxwlbdldm) ";
		String[] output = new String[] { "RCXWLBDLDM", "RCXWLBDLDM",
				"RCXWLBDLMC" };
		return CommonQueryDAO.commonQuery(sql, searchTj,
				inputV.toArray(new String[] {}), output, model);

	}
	

	/**
	 * 
	 * ɾ��������Ϣ
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-7 ����10:54:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 *             int ��������
	 * @throws
	 */
	public int deleteXwdlInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_rcxwdbdlb where rcxwlbdldm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	
	/**
	 * ���ݴ�������ѯ��������
	 */
	public String getRcxwlbdlmcById(String id) throws Exception {
		String sql = "select rcxwlbdlmc from xg_rcsw_rcxwdbdlb where rcxwlbdldm=? ";
		return dao.getOneRs(sql, new String[] { id }, "rcxwlbdlmc");
	}

	/**
	 * 
	 * ������Ϊ��������Ƿ���ʹ��
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-7 ����10:14:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 *             String[] ��������
	 * @throws
	 */
	public String[] checkXwdlForXwlb(String values) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select distinct a.rcxwlbdlmc from xg_rcsw_rcxwdbdlb a, xg_rcsw_rcxwlbdmb b where a.rcxwlbdldm = b.rcxwlbdldm and b.SFQY='1' "
						+ " and a.rcxwlbdldm  in(" + values + ")");
		String[] rcxwlbdlmc = dao.getRs(sql.toString(), new String[] {},
				"rcxwlbdlmc");
		return rcxwlbdlmc;
	}
	/** 
	 * ��ȡ�����δ�����Ĵ���
	 */
	public List<HashMap<String,String>> getRcxwdlShwjs(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t3.rcxwlbdldm ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.rcxwlbdm, b.rcxwlbdldm ");
		sql.append(" from xg_rcsw_rcxwlbdmb a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" ) ");
		sql.append(" ) t3 on t1.rcxwlbdm = t3.rcxwlbdm ");
		sql.append(" where t1.shzt in ('4','5') "); // '������'��'�����'
		sql.append(" and t3.rcxwlbdldm in (" + values + ") ");
		return dao.getListNotOut(sql.toString(), new String[] {  });
	}
	/** 
	 * ��ȡ�����δ���������
	 */
	public List<HashMap<String,String>> getRcxwlbShwjs(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t1.rcxwlbdm ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.rcxwlbdm, b.rcxwlbdldm ");
		sql.append(" from xg_rcsw_rcxwlbdmb a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" ) ");
		sql.append(" ) t3 on t1.rcxwlbdm = t3.rcxwlbdm ");
		sql.append(" where t1.shzt in ('4','5') "); // '������'��'�����'
		sql.append(" and t1.rcxwlbdm in (" + values + ") ");
		return dao.getListNotOut(sql.toString(), new String[] {  });
	}
	/** 
	 * �������
	 */
	public boolean updateRcxwlbSfqy(RcxwdmwhForm model) throws Exception {
		String sql = "update xg_rcsw_rcxwlbdmb set sfqy=? where rcxwlbdm=?";
		return dao.runUpdate(sql, new String[] { model.getSfqy(), model.getRcxwlbdm() });
	}
	
	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_rcsw_rcxwdbdlb");
		super.setKey("rcxwlbdldm");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwdmwhForm model)
			throws Exception {
		// TODO �Զ����ɷ������

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select * from xg_rcsw_rcxwdbdlb  where 1=1");

		if (!StringUtil.isNull(model.getRcxwlbdldm())) {
			params.add(model.getRcxwlbdldm());
			sql.append(" and rcxwlbdldm = ? ");
		}

		if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
			params.add(model.getRcxwlbdlmc());
			sql.append(" and rcxwlbdlmc like '%'||?||'%'");
		}

		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));
	}

	
	/**
	 * 
	 * @����:��ȡ��Ϊ�������������ֵ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����12:41:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getMaxXwdldm() {
		String sql = "select max(to_number(RCXWLBDLDM)) max from xg_rcsw_rcxwdbdlb";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	/**
	 * 
	 * @����:��ȡ��Ϊ������������ֵ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����12:41:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getMaxXwlbdm() {
		String sql = "select max(to_number(rcxwlbdm)) max from xg_rcsw_rcxwlbdmb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	/**
	 * 
	 * @����:�����ճ���Ϊ������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����12:29:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addXwdlInfo(RcxwdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_rcxwdbdlb where RCXWLBDLMC=? and SPLC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getRcxwlbdlmc(),model.getSplc() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_rcxwdbdlb(RCXWLBDLDM,RCXWLBDLMC,SPLC) values(?,?,?)";
			b = dao.runUpdate(sql, new String[] { model.getRcxwlbdldm(),
					model.getRcxwlbdlmc(), model.getSplc() });
		} else {
			// msg="����Ϊ��������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_RCXWWH_XWDLCZ);
		}
	
		return b;
	}
	
	/**
	 * 
	 * @����:�޸��ճ���Ϊ������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����12:29:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXwdlInfo(RcxwdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_rcxwdbdlb where RCXWLBDLMC=? and SPLC= ? ";
		String num = dao.getOneRs(sql1,
				new String[] { model.getRcxwlbdlmc(),model.getSplc() }, "num");
		
		//����ͬ��������ƺʹ������  ������Ϊ�������Ƿ��Ѵ���
		sql = "select RCXWLBDLDM  from xg_rcsw_rcxwdbdlb where RCXWLBDLMC = ? and SPLC = ? ";
		String rcxwlbdldm = dao.getOneRs(sql, new String[] { model.getRcxwlbdlmc().trim(),model.getSplc() },
		"rcxwlbdldm");
		
		if ((rcxwlbdldm.equals(model.getRcxwlbdldm())) || "0".equals(num)) {
			sql = "update xg_rcsw_rcxwdbdlb set RCXWLBDLMC=?,SPLC=? where RCXWLBDLDM=?";
			b = dao.runUpdate(sql,
					new String[] { model.getRcxwlbdlmc().trim(), model.getSplc(),
							model.getRcxwlbdldm() });
		} else {
			// msg="����Ϊ��������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_RCXWWH_XWDLCZ);
		}
		
		
		
		
		
		return b;
	}

	
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwdmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * �����Ϊ�����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXwlbList(RcxwdmwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select *  from (select a.*,decode(a.rcxwfzlx,'01','�ӷ�','02','����','δ֪')fzlxmc,"
						+" decode(a.sfqy,'1','����','ͣ��') sfqymc, "
						+"(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then a.rcxwlbzdfz||'-'||a.rcxwlbzgfz when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is null then a.rcxwlbzdfz when a.rcxwlbzdfz is null and a.rcxwlbzgfz is not null then a.rcxwlbzgfz else '' end) fzqj,"
						+"b.rcxwlbdlmc  from xg_rcsw_rcxwlbdmb a "
						+ "left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm  ) a "
						+ "where 1=1");

		if (!StringUtil.isNull(model.getRcxwlbdm())) {
			params.add(model.getRcxwlbdm());
			sql.append(" and rcxwlbdm = ? ");
		}
		if (!StringUtil.isNull(model.getSfqy())) {
			params.add(model.getSfqy());
			sql.append(" and a.sfqy = ? ");
		}
		
		if(!StringUtil.isNull(model.getRcxwlbmc()) && !StringUtil.isNull(model.getRcxwlbdlmc())){
			params.add(model.getRcxwlbmc());
			sql.append(" and rcxwlbmc like '%'||?||'%'");
			
			params.add(model.getRcxwlbdlmc());
			sql.append(" and rcxwlbdlmc = ? ");
			
			
		}else{
			if (!StringUtil.isNull(model.getRcxwlbmc())) {
				params.add(model.getRcxwlbmc());
				sql.append(" and rcxwlbmc like '%'||?||'%'");
			}
			if (!StringUtil.isNull(model.getRcxwlbdlmc())) {
				params.add(model.getRcxwlbdlmc());
				sql.append(" and rcxwlbdlmc = ? ");
			}
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));

	}

	/**
	 * ������Ϊ�����Ϣ
	 * 
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean addXwlbInfo(RcxwdmwhForm rcxwdmwhForm, String type)
			throws Exception {

		boolean b = false;
		RcxwdmwhForm model = (RcxwdmwhForm) StringUtils.formatBean(rcxwdmwhForm);
		String sql;
		sql = "select count(1) num from xg_rcsw_rcxwlbdmb where rcxwlbmc = ? and rcxwlbdldm = ? ";
		String num = dao.getOneRs(sql, new String[] { model.getRcxwlbmc(),model.getRcxwlbdldm() },
				"num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_rcxwlbdmb(rcxwlbdm,rcxwlbmc,rcxwlbdldm,rcxwfzlx,rcxwlbbz,rcxwlbzgfz,rcxwlbzdfz) values(?,?,?,?,?,?,?)";
			b = dao.runUpdate(sql,
					new String[] { model.getRcxwlbdm(), model.getRcxwlbmc(),
							model.getRcxwlbdldm(),model.getRcxwfzlx(),model.getRcxwlbbz(),model.getRcxwlbzgfz(),model.getRcxwlbzdfz() });
		} else {

			throw new SystemException(MessageKey.RCSW_RCXWWH_XWLBCZ);
		}
		return b;
	}

	/**
	 * ������Ϊ�����Ϣ
	 * 
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwlbInfo(RcxwdmwhForm rcxwdmwhForm, String type)
			throws Exception {

		boolean b = false;
		RcxwdmwhForm model = (RcxwdmwhForm) StringUtils.formatBean(rcxwdmwhForm);
		String sql1;
		String sql;
		sql1 = "select count(1) num  from xg_rcsw_rcxwlbdmb where rcxwlbmc = ? and rcxwlbdldm = ? ";
		String num = dao.getOneRs(sql1, new String[] { model.getRcxwlbmc().trim(),model.getRcxwlbdldm() },
		"num");
		
		//����ͬ��������ƺʹ������  ������Ϊ�������Ƿ��Ѵ���
		sql = "select rcxwlbdm  from xg_rcsw_rcxwlbdmb where rcxwlbmc = ? and rcxwlbdldm = ? ";
		String rcxwlbdm = dao.getOneRs(sql, new String[] { model.getRcxwlbmc().trim(),model.getRcxwlbdldm() },
		"rcxwlbdm");
		
		if ((rcxwlbdm.equals(model.getRcxwlbdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_rcxwlbdmb set rcxwlbmc=?,rcxwlbdldm=? ,rcxwfzlx=?,rcxwlbbz=?,rcxwlbzgfz=?,rcxwlbzdfz=? where rcxwlbdm=?";
			b = dao.runUpdate(sql,
					new String[] { model.getRcxwlbmc().trim(), model.getRcxwlbdldm(),model.getRcxwfzlx(),
						model.getRcxwlbbz(),model.getRcxwlbzgfz(),model.getRcxwlbzdfz(), model.getRcxwlbdm() });
		} else {

			throw new SystemException(MessageKey.RCSW_RCXWWH_XWLBCZ);
		}
		
		return b;
	}

	/**
	 * �����Ϊ���List<HashMap<String,String>>
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getXwlbListMap(RcxwdmwhForm model) {
		String sql = "select rcxwlbdldm,rcxwlbdlmc from xg_rcsw_rcxwdbdlb order by rcxwlbdldm";
		return dao.getList(sql, new String[] {}, new String[] { "rcxwlbdldm",
				"rcxwlbdlmc" });
	}

	/**
	 * 
	 * �޸���Ϊ��� ������ѯ
	 * 
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����04:02:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param rcxwlbdm
	 * @return
	 * @throws Exception
	 *             RcxwdmwhForm ��������
	 * @throws
	 */
	public RcxwdmwhForm getRcxwdmwhForm(RcxwdmwhForm t, String rcxwlbdm)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*, b.rcxwlbdlmc from xg_rcsw_rcxwlbdmb a ");
		sql.append("left join xg_rcsw_rcxwdbdlb b ");
		sql.append("on a.rcxwlbdldm = b.rcxwlbdldm) c ");
		sql.append(" where rcxwlbdm = ? ");

		return this.getModel(t, sql.toString(), new String[] { rcxwlbdm });

	}

	/**
	 * ɾ�������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int deleteXwlbInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_rcxwlbdmb where rcxwlbdm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}

	/**
	 * 
	 * ������Ϊ�������Ƿ���ʹ��
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-7 ����10:55:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 *             String[] ��������
	 * @throws
	 */
	public String[] checkXwlbForXwwh(String values) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select distinct a.rcxwlbmc from xg_rcsw_rcxwlbdmb a, (select rcxwlbdm from xg_rcsw_rcxwxxwh union select rcxwlbdm from xg_rcsw_rcxwjg) b where a.rcxwlbdm = b.rcxwlbdm "
						+ " and a.rcxwlbdm in(" + values + ")");
		String[] rcxwlbmc = dao.getRs(sql.toString(), new String[] {},
				"rcxwlbmc");
		return rcxwlbmc;
	}
	/**
	 * 
	 * @����:��ȡ�ճ���Ϊ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-14 ����08:48:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcxwdlList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct RCXWLBDLDM,RCXWLBDLMC,RCXWDLJCF,RCXWDLFSSX from XG_RCSW_RCXWDBDLB");
		return dao.getListNotOut(sql.toString(), new String[]{});
						
		
	}

}
