/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:14:33 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

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

public class XszbblxwhDao extends SuperDAOImpl<XszbblxwhForm> {

	/**
	 * 
	 * @����:TODO(ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����08:42:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBblxPageList(XszbblxwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xszbblxdm,a.xszbblxmc,decode(a.zjlxbs,'1','��','��')zjlxbs, ");
		sql.append(" decode(e.lcxx,'','�������',mc || '��' || replace(lcxx, ';', '->')) lcxx ");
		sql.append(" from xg_rcsw_xszbb_bblxwhb a  left join ");
		sql.append(" (select splc, mc, lcxx from (select splc,");
		sql.append(" a.mc,to_char(WM_CONCAT(c.mc) over(partition by splc order by xh)) lcxx,");
		sql.append(" xh,row_number() over(partition by splc order by xh desc) as ddd ");
		sql.append(" from xg_xtwh_splc a,xg_xtwh_spbz b,xg_xtwh_spgw c ");
		sql.append(" where djlx = 'rcsw' and a.id = b.splc and b.spgw = c.id) b ");
		sql.append(" where ddd = 1) e on a.shlc = e.splc");
		sql.append(" where 1 = 1");
		
		
		if (!StringUtil.isNull(model.getXszbblxmc())) {
			params.add(model.getXszbblxmc());
			sql.append(" and a.xszbblxmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),
				params.toArray(new String[] {}));

	}
	
	
	
	/**
	 * 
	 * @����:TODO(����ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����08:43:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addBblxInfo(XszbblxwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_xszbb_bblxwhb where xszbblxmc=?  ";
		String num = dao.getOneRs(sql,
				new String[] {model.getXszbblxmc()}, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_xszbb_bblxwhb(xszbblxdm,xszbblxmc,shlc) values(?,?,?)";
			b = dao.runUpdate(sql, new String[] { model.getXszbblxdm(),
					model.getXszbblxmc(),model.getShlc() });
		} else {
			// msg="��ѧ��֤�������������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_XSZBB_BBLXMCCZ);
		}
	
		return b;
	}
	
	/**
	 * 
	 * @����:TODO(�޸�ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����09:18:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBblxInfo(XszbblxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_xszbb_bblxwhb where xszbblxmc=? and  xszbblxdm != ? ";
		String num = dao.getOneRs(sql1,
				new String[] {model.getXszbblxmc(), model.getXszbblxdm()}, "num");
		//����ͬ�Ĳ����������ƺͲ������ʹ���  ����ѧ��֤���������Ƿ��Ѵ���
		//sql = "select xszbblxdm  from xg_rcsw_xszbb_bblxwhb where xszbblxdm = ?  and xszbblxmc=? ";
		//String xszbblxdm = dao.getOneRs(sql, new String[] { model.getXszbblxdm(),model.getXszbblxmc()},"xszbblxdm");
		if ("0".equals(num)) {
			sql = "update xg_rcsw_xszbb_bblxwhb set xszbblxmc=?,shlc=? where xszbblxdm=?";
			b = dao.runUpdate(sql,
					new String[] { model.getXszbblxmc(),model.getShlc(),model.getXszbblxdm() });
		} else {
			// msg="��ѧ��֤�������������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_XSZBB_BBLXMCCZ);
		}
		return b;
	}
	
	
	/**
	 * 
	 * @����:TODO(��ȡѧ��֤�������ʹ���������ֵ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����10:05:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getMaxXszbblxdm() {
		String sql = "select max(to_number(xszbblxdm)) max from xg_rcsw_xszbb_bblxwhb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}

	
	/**
	 * 
	 * @����:TODO(�޸�ѧ��֤�������� ������ѯ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����10:56:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param xszbblxdm
	 * @return
	 * @throws Exception
	 * XszbblxwhForm �������� 
	 * @throws
	 */
	public XszbblxwhForm getXszbblxwhForm(XszbblxwhForm t, String xszbblxdm)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_xszbb_bblxwhb a  ");
		sql.append(" where xszbblxdm = ? ");

		return this.getModel(t, sql.toString(), new String[] { xszbblxdm });

	}
	/**
	 * �����Ϊ������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXwdlList(XszbblxwhForm model) throws Exception {
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
	 * @����:TODO(ɾ��ѧ��֤��������ά��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����11:21:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteXszbblxwhInfo(String[] values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_xszbb_bblxwhb where xszbblxdm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}

	/**
	 * 
	 * @����:TODO(�жϲ������������Ƿ��Ѿ����������� �� ������Ӧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����11:21:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkXszbbdmForbbsq(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.xszbblxmc from xg_rcsw_xszbb_bblxwhb a left join ");
		sql.append(" xg_rcsw_xszbb_xszbbsqb b on a.xszbblxdm = b.xszbblxdm ");
		sql.append(" left join xg_rcsw_xszbb_xszbbjgb c on a.xszbblxdm = c.xszbblxdm ");
		sql.append(" where a.xszbblxdm  in(" + values + ")  and  ( b.xszbblxdm is not null or  c.xszbblxdm is not null ) " );
		String[] xszbblxmc = dao.getRs(sql.toString(), new String[] {},"xszbblxmc");
		return xszbblxmc;
	}


	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_rcsw_xszbb_bblxwhb");
		super.setKey("xszbblxdm");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbblxwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XszbblxwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����12:33:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbblxdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String xszbblxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.xszbblxmc from xg_rcsw_xszbb_bblxwhb a left join ");
		sql.append(" xg_rcsw_xszbb_xszbbsqb b on a.xszbblxdm = b.xszbblxdm ");
		sql.append(" left join xg_rcsw_xszbb_xszbbjgb c on a.xszbblxdm = c.xszbblxdm ");
		sql.append(" where a.xszbblxdm  = ?   and  ( b.xszbblxdm is not null or  c.xszbblxdm is not null ) " );
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{xszbblxdm});
		String bblxmc = map.get("xszbblxmc");
		//���δ�ύ�ſ����ύ
		return bblxmc==null?true:false;
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����12:32:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbblxdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBbsq(String xszbblxdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.xszbblxmc from xg_rcsw_xszbb_bblxwhb a ");
		sb.append(" where  a.xszbblxdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{xszbblxdm});
	}
}
