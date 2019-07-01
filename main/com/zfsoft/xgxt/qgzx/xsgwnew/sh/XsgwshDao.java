/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-6 ����09:31:15 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.XsgwshDAO;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqDao;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: ѧ����λ���   
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-6 ����09:31:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsgwshDao extends SuperDAOImpl<XsgwshnewForm>{
	
	/**
	 * ������Դ�����룩
	 */
	public static final String _SJLY_SQ = "1";
	/**
	 * ������Դ���ֶ�ά����
	 */
	public static final String _SJLY_WH = "0";

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsgwshnewForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsgwshnewForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		XsgwsqDao xd = new XsgwsqDao();
		HashMap<String, String> map = xd.getCsszb();
		String qxfw = map.get("qxfw");
		String splc = map.get("xsgwsqsplc");
		SearchModel searchmodel = new SearchModel();
		searchmodel.setSearch_tj_qgrq(t.getSearchModel().getSearch_tj_qgrq());
		searchmodel.setSearch_tj_qgmx(t.getSearchModel().getSearch_tj_qgmx());
		if(null !=  searchmodel.getSearch_tj_qgrq() && searchmodel.getSearch_tj_qgrq().length != 0){
			t.getSearchModel().setSearch_tj_qgrq(null);
		}
		if(null !=  searchmodel.getSearch_tj_qgmx() && searchmodel.getSearch_tj_qgmx().length != 0){
			t.getSearchModel().setSearch_tj_qgmx(null);
		}
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "b",
				"xydm", "bjdm");

		// String yrdwTj=SearchService.getSearchYrdwTjByUser(user, "c");

		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct ");
		sql.append(" SQBH,");
		sql.append(" XH,");
		sql.append(" XM,");
		sql.append(" NJ,");
		sql.append(" ZYDM,");
		sql.append(" XB,");
		sql.append(" SQSJ,");
		sql.append(" SPLC,");
		sql.append(" YRDWMC,");
		sql.append(" YRDWDM,");
		sql.append(" GWXZDM,");
		sql.append(" SFYXGW,");
		sql.append(" GWKSSJ,");
		sql.append(" GWJSSJ,");
		sql.append(" SHZTS,");
		sql.append(" GWMC,");
		sql.append(" GWXZMC,");
		sql.append(" SHSJ,");
		sql.append(" GWID,");
		sql.append(" SHID,");
		sql.append(" SHZT,");
		sql.append(" GWZ,");
		sql.append(" RN,");
		sql.append(" XYDM,");
		sql.append(" BJDM,");
		sql.append(" XN,");
		sql.append(" XYMC,");
		sql.append(" ZYMC,");
		sql.append(" BJMC,");
		sql.append(" RDDC");
		sql.append(" from (");
		sql.append("select distinct b.* from (select b.*,t.rddc,y.qgrq,y.qgmx from (select a.sqbh,b.xh,b.xm,b.nj,b.zydm,");
		sql.append("(case b.xb when '1' then '��' when '2' then 'Ů' else b.xb end) xb,");
		sql.append("a.sqsj,a.gwdm,a.splc,c.yrdwmc,c.yrdwdm,c.gwxzdm,c.sfyxgw,c.gwkssj,c.gwjssj,d.shzt shzts,(case when a.gwdm is null then '������' else c.gwmc end) gwmc,c.gwxzmc,d.shsj,d.gwid,d.guid shid,");
		sql.append("f.mc ||'['|| decode(d.shzt,0,'�����',1,'ͨ��',2,'��ͨ��',3,'�˻�',4,'������',5,'�����','����')||']' shzt,f.gwz,");
		sql.append(" row_number()over(partition by a.sqbh order by d.shsj desc) rn, ");
		sql.append(" g.xydm,g.bjdm,c.xn,g.xymc,g.zymc,g.bjmc  from xg_qgzx_xsgwsqb a ");
		sql.append(" left join view_xg_qgzx_gwxxb c on a.gwdm = c.gwdm  ");
		sql.append(" left join xsxxb b on a.xh = b.xh  ");
		sql.append(" left join xg_xtwh_shztb d on a.sqbh = d.ywid ");
		String shlx = t.getShlx();
		/*
		 * if(!shlx.equals("dsh")){ sql.append(" and d.shzt<>0  "); }else{
		 * sql.append(" and d.shzt=0 "); }
		 */
		if (!shlx.equals("dsh")) {
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		} else {
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}
		sql.append(" left join xg_xtwh_spgwyh e on d.gwid = e.spgw  left join  xg_xtwh_spgw f on d.gwid = f.id ");
		sql.append(" left join view_njxyzybj_all g on b.bjdm=g.bjdm where e.spyh = '" + user.getUserName() + "' ) b ");
		if("10335".equals(Base.xxdm)){
			sql.append(" left join view_knsjgb_fqxrd t on b.xh = t.xh ");
			sql.append(" left join XG_QGZX_QGSQMXB y ");
			sql.append(" on b.xh = y.xh");
			sql.append(" ) b ");
		}else{
			sql.append(" left join ( select xh, xn, rddc from ( ");
	        sql.append("  select xh, xn, rddc, row_number() over(partition by xh, xn order by xq desc) rn ");
	        sql.append("  from XG_XSZZ_NEW_KNSJGB t) where rn = 1 ");
	        sql.append(" ) t ");
	        sql.append(" on b.xh = t.xh and b.xn = t.xn  ");
			sql.append(" left join XG_QGZX_QGSQMXB y ");
			sql.append(" on b.xh = y.xh");
			sql.append(" ) b");
		}
		sql.append(" where 1=1 ");
		sql.append(" and rn = 1 "); // ȡ������һ��
		sql.append(searchTj);
		sql.append(new XsgwshDAO().getZdysql(searchmodel));
		sql.append(shgwzByUser);
		//sql.append(searchTjByUser);
		//sql.append(searchTj);
		//sql.append(getQxfwSql(splc, qxfw, user.getUserName(),searchTjByUser));
		sql.append(" order by sqsj desc");
		sql.append(")");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(XsgwshnewForm.class);
		super.setTableName("xg_qgzx_xsgwsqb");
		super.setKey("sqbh");
	}
	
	/**
	 * 
	 * @����: ���ò���Ȩ�޷�Χ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-6 ����10:25:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param qxfw
	 * @param userName
	 * @param searchTjByUser
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getQxfwSql(String splc, String qxfw, String userName,String searchTjByUser) {
		// ���ò���Ȩ�޷�Χ
		StringBuffer sql = new StringBuffer();
		if(StringUtils.isNull(qxfw)){
			sql.append(searchTjByUser);
			return sql.toString();
		}
		// ��ȡ����������̵���˸�λ
		List<HashMap<String, String>> list = ShlcUtil.getSpbzBySplc(splc);
/*		List<String> shgws = new ArrayList<String>();
		for (HashMap<String, String> hm : list) {
			shgws.add(hm.get("spgw"));
		}*/
		int i = 0;
		String qxfws[] = qxfw.split(",");
		String shgw = null;
		if (list.size() < 0 || qxfws.length < 0) {
			return null;
		}
		if(null!=list&&list.size()!=0){
		
			sql.append(" and ( ");
			for (HashMap<String, String> hm : list) {
				shgw = hm.get("spgw");
				for (String qxgw : qxfws) {
					if (i > 0) {
						sql.append(" or ");
					}
					sql.append("(");
					// ���������̸�λ�����õĿ��Ƹ�λ
					if (shgw.equals(qxgw)) {
						sql.append(" yrdwdm = ( select szbm from yhb where yhm = '"
								+ userName + "') and ");
						sql.append(" gwid = '" + shgw + "')");
					}else{
						sql.append(" gwid = '" + shgw + "' ");
						sql.append(searchTjByUser);
						sql.append(")");
					}
					//sql.append(" gwid = '" + shgw + "' ");
					//sql.append(searchTjByUser);
					//sql.append(searchTj);
					i++;
				}
			}
			sql.append(")");
		}
		return sql.toString();
	}
	
	/**
	 * 
	 * @����: ��ȡ�ϼ����������Ϣ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-7 ����03:27:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param nowGwdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSjTzShxx(String ywid,String nowGwdm) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.*,");
		sb.append(" lead(t.gwid,1,null) over(partition by t.ywid order by t.shsj desc) as sjgwid");
		sb.append(" from xg_xtwh_shztb t where t.ywid=? and zd2<> ? and zd2 is not null");
		return dao.getMapNotOut(sb.toString(), new String[]{ywid,nowGwdm});
	}
	
	/**
	 * 
	 * @����: ���һ���������������ɾ��
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-7 ����05:38:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqbh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgForSq(String sqbh) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_qgzx_new_xsgwxxb where sqbh=?");
		int i = dao.runDelete(sb.toString(), new String[] { sqbh });
		return i >= 0 ? true : false;
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��������Ϣ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:30:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqbh
	 * @return
	 * @throws SQLException
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsSqxx(String sqbh) throws SQLException {
		String sql = " select * from xg_qgzx_xsgwsqb b where b.sqbh = ?";
		return dao.getMapNotOut(sql, new String[] { sqbh });
	}
	
	/**
	 * 
	 * @����: ��������ͨ����ѧ�������λ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:30:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @param xh
	 * @param sqbh
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean bcGwxs(String gwdm, String xh, String sqbh)
			throws SQLException {
		String sgsj="yyyyMMdd";
		if(Globals.XXDM_ZJJTZYJSXY.equals(Base.xxdm)){
			sgsj="yyyyMM";
		}
		String sql = " insert into xg_qgzx_new_xsgwxxb(gwdm,xh,sgsj,zgzt,sjly,sqbh)values(?,?,to_char(sysdate,'"+sgsj+"'),'zg',?,?)";
		return dao.insert(sql, new String[] { gwdm, xh, _SJLY_SQ, sqbh });
	}
	
	/**
	 * 
	 * @����: ���������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����10:40:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwdm
	 * @return
	 * Integer �������� 
	 * @throws
	 */
	public Integer getRskz(String gwdm) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) rs from xg_qgzx_xsgwxxb where gwdm= ? and zgzt = 'zg' ");
		
		String rs = dao.getOneRs(sb.toString(), new String[] { gwdm }, "rs");
		Integer yzrs = StringUtils.paseStringToInteger(rs);
		
		return yzrs;
	}
}
