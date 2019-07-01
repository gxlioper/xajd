/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-19 ����01:49:12 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjtx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-19 ����01:49:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjDao extends SuperDAOImpl<XnxjForm> {
	
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	
	public HashMap<String , String> getXnxjInfo(String xh , String xn){
		String sql = "select * from xg_xsxx_xnxjb where xh = ? and xn = ?";
		return dao.getMapNotOut(sql, new String[]{xh , xn});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*, t2.spl shlcidnew from (select a.*,")
		.append(" b.id,")
		.append("nvl2(b.xn, b.xn, '"+t.getSearchXn()+"') xn,")
		.append("b.xjnr, ")
		.append(" to_char(to_date(b.txsj , 'yyyy-MM-dd,hh24:mi:ss'),'yyyy-MM-dd') txsj, ")
		.append(" nvl2(b.shjg , b.shjg , '-1') shjg, ")
		.append("decode(b.shjg ,'0' ,'δ�ύ' , '1' , 'ͨ��' , '2' , '��ͨ��' , '3' , '�˻�' , '4' , '�����' , '5' , '�����') shztmc,")
		.append("nvl2(b.id , '����' , 'δ��') txztmc,")
		.append(" nvl2(b.id , '1' , '0') txzt,")
		.append(" b.splid")
		.append(" from view_xsjbxx a")
		.append(" left join xg_xsxx_xnxjb b")
		.append(" on a.xh = b.xh) t1, xg_xsxx_xnxjkgb t2")
		.append(" where 1 = 1")
		.append(" ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:ѧ��С������б��ѯ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-21 ����02:22:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXnxjShPageList(XnxjForm t, User user)
		throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");	
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t2.* from (select t1.* , row_number() over(partition by id order by shsj desc) rn from (select b.*, ")
		.append("'[' || c.mc || ':' || decode(b.shzt, '0','�����','1', 'ͨ��', '2','��ͨ��','3', '�˻�', '4', '������', '5', '�����',b.shzt) || ']' shztmc,c.gwz ")
		.append("from (select a.xh,a.xn,   to_char(to_date(a.txsj , 'yyyy-MM-dd,hh24:mi:ss'),'yyyy-MM-dd') txsj,a.id,a.xjnr,a.shjg, a.splid, m.nj,m.xb, m.xm,m.xydm, m.zydm, m.bjdm,b.zd3 as dcmc, m.xymc,m.zymc,m.bjmc,b.shzt, ")
		.append("b.shsj, b.gwid as xtgwid, b.zd2 as rddc,b.guid as shid ")
		.append("from xg_xsxx_xnxjb a left join view_xsjbxx m on a.xh = m.xh ")
		.append("left join xg_xtwh_shztb b on a.id = b.ywid  where 1 = 1) b ")
		.append("left join xg_xtwh_spgw c on b.xtgwid = c.id where ")
		.append(" b.xtgwid in (select spgw from xg_xtwh_spgwyh where spyh = '").append(user.getUserName()).append("' ) ");
		
		if(DSH.equals(t.getShQryType()))
			sql.append("and b.shzt in ('0', '4')) t1 ) t2 where rn = 1 ");
		else if(YSH.equals(t.getShQryType()))
			sql.append("and b.shzt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);
		
		return getPageList(t, sql.toString(), inputV);
		
	}
	
	/**
	 * 
	 * @����:����ѧ�ź�ѧ���ѯѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-19 ����04:42:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param params
	 * @return
	 * @throws Exception
	 * KnsrdForm �������� 
	 * @throws
	 */
	public XnxjForm getModel(XnxjForm t ,String[] params) throws Exception {
		
		String sql = "select * from xg_xsxx_xnxjb where xh=? and xn=?";
		
		return super.getModel(t, sql, params);
	}

	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(XnxjForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_xnxjb");
	}
	
	/**
	 * ��ѯ���������������ʱ������
	 */
	public List<HashMap<String , String>> getXnxjShyjList(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.xm from xg_xtwh_shztb a ");
		sql.append(" left join fdyxxb b on a.shr=b.zgh ");
		sql.append(" where a.ywid = ? order by a.shsj asc ");
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����:��ѯҵ��ID�Ƿ��й��˻ؼ�¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-6 ����01:43:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShzt(String ywid){
		
		StringBuffer sql = new StringBuffer(" select count(1) num from xg_xtwh_shztb where shzt = '3' and  ywid = ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{ywid}, "num");
		
		return num;
	}

}
