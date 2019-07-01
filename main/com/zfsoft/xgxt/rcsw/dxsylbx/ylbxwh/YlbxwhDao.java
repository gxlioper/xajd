/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:14:33 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ��ղ������͹���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-7 ����08:53:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxwhDao extends SuperDAOImpl<YlbxwhForm> {

	/**
	 * 
	 * @����:TODO(����ȫ�����Ա���ά��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����01:48:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCzqebzlxPageList(YlbxwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select czqebzdm,czqebzmc from xg_rcsw_ylbx_czqebzlxb a where 1 = 1 ");
		if (!StringUtil.isNull(model.getCzqebzmc())) {
			params.add(model.getCzqebzmc());
			sql.append(" and a.czqebzmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));

	}
	
	/**
	 * 
	 * @����:TODO(�α�״��ά���б�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����03:29:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzklxPageList(YlbxwhForm model)
	throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select cbzkdm,cbzkmc from xg_rcsw_ylbx_cbzklxb a where 1 = 1 ");
		if (!StringUtil.isNull(model.getCbzkmc())) {
			params.add(model.getCbzkmc());
			sql.append(" and a.cbzkmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));
	
	}
	
	/**
	 * 
	 * @����:TODO(���Ӳ���ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����03:27:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addCzqebzlxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_ylbx_czqebzlxb where czqebzmc=?  ";
		String num = dao.getOneRs(sql,
				new String[] {model.getCzqebzmc()}, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_rcsw_ylbx_czqebzlxb(czqebzdm,czqebzmc) values(?,?)";
			b = dao.runUpdate(sql, new String[] { model.getCzqebzdm(),model.getCzqebzmc() });
		} else {
			// msg="���������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_DXSYLBX_BZLXCZ);
		}
	
		return b;
	}
	
	/**
	 * 
	 * @����:TODO(�޸Ĳ���ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����03:28:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateCzqebzlxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_ylbx_czqebzlxb where czqebzmc=?  ";
		String num = dao.getOneRs(sql1,
				new String[] { model.getCzqebzmc() }, "num");
		//����ͬ�Ĳ���ȫ�����Ա������ƺͲ���ȫ�����Ա��ݴ���  ���Ҳ���ȫ�����Ա����Ƿ��Ѵ���
		sql = "select czqebzdm  from xg_rcsw_ylbx_czqebzlxb where czqebzdm = ?  and czqebzmc=? ";
		String czqebzdm = dao.getOneRs(sql, new String[] { model.getCzqebzdm(),model.getCzqebzmc()},"czqebzdm");
		if ((czqebzdm.equals(model.getCzqebzdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_ylbx_czqebzlxb set czqebzmc=? where czqebzdm=?";
			b = dao.runUpdate(sql,
					new String[] { model.getCzqebzmc(),model.getCzqebzdm() });
		} else {
			// msg="���������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_DXSYLBX_BZLXCZ);
		}
		return b;
	}
	
	
	/**
	 * 
	 * @����:TODO(��ȡ����ȫ�����Ա��ݴ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����10:05:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getMaxCzqelxdm() {
		String sql = "select max(to_number(czqebzdm)) max from xg_rcsw_ylbx_czqebzlxb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}

	
	/**
	 * 
	 * @����:TODO(��ȡ����ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����04:04:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param xszbblxdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm �������� 
	 * @throws
	 */
	public YlbxwhForm getCzqebzlxForm(YlbxwhForm t, String czqebzdm)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_ylbx_czqebzlxb a  ");
		sql.append(" where czqebzdm = ? ");

		return this.getModel(t, sql.toString(), new String[] { czqebzdm });

	}
	
	
	/**
	 * 
	 * @����:TODO(���Ӳα�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-7 ����08:41:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addCbzklxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		sql = "select count(1) num from xg_rcsw_ylbx_cbzklxb where cbzkmc=?  ";
		String num = dao.getOneRs(sql,
				new String[] {model.getCbzkmc()}, "num");
		if ("0".equals(num)) {
			
			sql = "insert into xg_rcsw_ylbx_cbzklxb(cbzkdm,cbzkmc) values(?,?)";
			b = dao.runUpdate(sql, new String[] { model.getCbzkdm(),model.getCbzkmc() });
			
		} else {
			throw new SystemException(MessageKey.RCSW_DXSYLBX_CBZKLXCZ);//�α�״�������Ѵ��ڣ�
		}
	
		return b;
	}
		
	
	/**
	 * 
	 * @����:TODO(�޸Ĳα�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����03:28:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateCbzklxInfo(YlbxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_ylbx_cbzklxb where cbzkmc=?  ";
		String num = dao.getOneRs(sql1,
				new String[] { model.getCbzkmc() }, "num");
		//����ͬ�Ĳα�״���������ƺͲα�״�����ʹ���  ���Ҳα�״�������Ƿ��Ѵ���
		sql = "select cbzkdm  from xg_rcsw_ylbx_cbzklxb where cbzkdm = ?  and cbzkmc=? ";
		String cbzkdm = dao.getOneRs(sql, new String[] { model.getCbzkdm(),model.getCbzkmc()},"cbzkdm");
		if ((cbzkdm.equals(model.getCbzkdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_ylbx_cbzklxb set cbzkmc=? where cbzkdm=?";
			b = dao.runUpdate(sql,new String[] {model.getCbzkmc(),model.getCbzkdm()});
		} else {
			throw new SystemException(MessageKey.RCSW_DXSYLBX_CBZKLXCZ);//�α�״�������Ѵ��ڣ�
		}
		return b;
	}
	
	
	
	/**
	 * 
	 * @����:TODO(��ȡ�α�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����10:05:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getMaxCbzklxdm() {
		String sql = "select max(to_number(cbzkdm)) max from xg_rcsw_ylbx_cbzklxb  ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @����:TODO(��ȡ�α�״��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-7 ����09:56:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param czqebzdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm �������� 
	 * @throws
	 */
	public YlbxwhForm getCbzklxForm(YlbxwhForm t, String cbzkdm)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_ylbx_cbzklxb a  ");
		sql.append(" where cbzkdm = ? ");
		return this.getModel(t, sql.toString(), new String[] { cbzkdm });
	}
	
	
	
	
	/**
	 * �����Ϊ������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXwdlList(YlbxwhForm model) throws Exception {
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


	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_rcsw_ylbx_czqebzlxb");
		super.setKey("czqebzdm");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxwhForm t)
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
	public boolean isCanDel(String czqebzdm){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) num from ( ");
		sql.append(" select xh  from xg_rcsw_ylbx_ylbxsqb where czqebzdm like '%"+czqebzdm+"%' union all ");
		sql.append(" select xh from xg_rcsw_ylbx_ylbxjgb where czqebzdm like '%"+czqebzdm+"%' ) ");
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");
		return num.equals("0")?true:false;
		
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
	public HashMap<String, String> getCzqebzdm(String czqebzdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.czqebzmc from xg_rcsw_ylbx_czqebzlxb a ");
		sb.append(" where  a.czqebzdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{czqebzdm});
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
	public HashMap<String, String> getCbzkdm(String cbzkdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.cbzkmc from xg_rcsw_ylbx_cbzklxb a ");
		sb.append(" where  a.cbzkdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{cbzkdm});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-16 ����05:06:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cbzkdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean cbzkdmIsCanDel(String cbzkdm){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) num from ( ");
		sql.append(" select xh  from xg_rcsw_ylbx_ylbxsqb where cbzkdm like '%"+cbzkdm+"%' union all ");
		sql.append(" select xh from xg_rcsw_ylbx_ylbxjgb where cbzkdm like '%"+cbzkdm+"%' ) ");
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");
		return num.equals("0")?true:false;
		
	}
	
	/**
	 * 
	 * @����:TODO(ɾ���α�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-21 ����05:08:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int  deleteCbzklxb (String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" delete from xg_rcsw_ylbx_cbzklxb where cbzkdm in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		return dao.runDelete(sql.toString(), params.toArray(new String[]{}) );
	}
	

}
