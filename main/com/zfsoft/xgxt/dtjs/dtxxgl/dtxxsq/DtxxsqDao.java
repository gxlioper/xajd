/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-10-25 ����10:39:09
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DtxxsqDao extends SuperDAOImpl<DtxxsqForm> {
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DtxxsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		//StringBuffer sql = new StringBuffer(getBaseSql());
		
		StringBuffer sql  = new StringBuffer("select a.*,t2.sydm,t3.symc from view_new_dc_dtxx_sq a ");

		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm = a.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t2.sydm = t3.sydm ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	/**
	 * 
	 * @����:��ÿ�������׶δ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-22 ����05:12:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getSqJdList(String jddmGet) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select jddm,jdmc,jdsx");
		sql.append(" from ( ");
		sql.append(" select d.jddm,d.jdmc,d.jdsx");
		sql.append(" from XG_DTJS_JBSZB d where d.SFKPZSHL=? and d.splc is not null and d.KSQKG=?");
		sql.append(" and ((KSQKSSJ is not null and KSQKSSJ <= to_char(sysdate,'yyyy-mm-dd')) or KSQKSSJ is null)");
		sql.append(" and ((KSQJSSJ is not null and KSQJSSJ >= to_char(sysdate,'yyyy-mm-dd')) or KSQJSSJ is null)");
		
		// �׶δ��벻Ϊ��
		if(StringUtils.isNotNull(jddmGet)){
			sql.append(" union select jddm,jdmc,jdsx ");
			sql.append("   from XG_DTJS_JBSZB ");
			sql.append(" where jddm = '" + jddmGet + "' ");
		}
		sql.append(" ) a ");
		sql.append(" order by to_number(jdsx) asc");
		return dao.getListNotOut(sql.toString(),
				new String[] { Constants.SHLC_SFKPZSHLC_KEYPZ,Constants.SHLC_KSQKG_KQ});
	}
	/**
	 * 
	 * @����:��ȡ��ǰ�׶δ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����03:33:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh ѧ��
	 * @return
	 * String �������� 
	 */
	public String getDqjddm(String xh) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select dtxxjlb.*,jbszb.jdmc dqjd from XG_DTJS_XSDTXXJLB dtxxjlb");
		sql
				.append(" left join XG_DTJS_JBSZB jbszb on jbszb.jddm =dtxxjlb.jddm ");
	    sql.append("right join(select max(a.jddm) jddm, xh  from XG_DTJS_XSDTXXJLB a  where a.xh=? group by a.xh)");
		 sql.append("t2 on dtxxjlb.xh = t2.xh and dtxxjlb.jddm = t2.jddm");
		String dqjd=DtxxsqService._WDQJDXX;
		HashMap<String, String> hm=dao.getMapNotOut(sql.toString(), new String[]{xh});
		if(null!=hm&&hm.size()>0){
			dqjd=hm.get("jddm");
		}
		return dqjd ;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DtxxsqForm t)
			throws Exception {
		List<String> param = new ArrayList<String>();
		StringBuffer sql = new StringBuffer(getBaseSql());
		if(StringUtils.isNotNull(t.getDtxxsqid())){
			sql.append(" and dtxxsqid=?");
			param.add(t.getDtxxsqid());
		}if(StringUtils.isNotNull(t.getXh())){
			sql.append(" and xh=?");
			param.add(t.getXh());
		}
		return this.getPageList(t, sql.toString(), param
				.toArray(new String[] {}));
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("dtxxsqid");
		this.setTableName("XG_DTJS_DTXXSQ");
		this.setClass(DtxxsqForm.class);
	}

	/**
	 * 
	 * @����:��ȡ����sql
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-25 ����10:39:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 * @throws
	 */
	public String getBaseSql() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql
				.append(" select a.*,b.jdmc,xsxx.xm,xsxx.xb,xsxx.csrq,xsxx.mzmc,xsxx.nj,xsxx.xymc,xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,xsxx.zzmmmc,");
		sql
				.append(" decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����','','�������', a.shzt) shztmc");
		sql.append(" from XG_DTJS_DTXXSQ a");
		// �׶���Ϣ
		sql.append(" left join XG_DTJS_JBSZB b on a.jddm=b.jddm");
		// ѧ����Ϣ
		sql.append(" left join view_xsxxb xsxx on a.xh=xsxx.xh) a where 1=1 ");
		return sql.toString();
	}
	

	public boolean updateDtxxsq(DtxxsqForm model) throws Exception{
		String[] inputV = new String[3]; 
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE XG_DTJS_DTXXSQ ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where dtxxsqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getDtxxsqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:25:35
	 * @param jddm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkSfktj(String jddm) {
		
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from XG_DTJS_JBSZB t2 ");
		sql.append("  where t2.ksqkg = '1'       ");
		sql.append("    and ((t2.KSQKSSJ is not null and ");
		sql.append("        t2.KSQKSSJ <= to_char(sysdate, 'yyyy-mm-dd')) or t2.KSQKSSJ is null) ");		
		sql.append("    and ((t2.KSQJSSJ is not null and ");
		sql.append("        t2.KSQJSSJ >= to_char(sysdate, 'yyyy-mm-dd')) or t2.KSQJSSJ is null) ");
		sql.append("    and t2.jddm = ? ");
		num = dao.getOneRs(sql.toString(), new String[] { jddm }, "num");
	
		return num;
	}
	
	/**
	 * @��������ȡԤ����Ա�Ľ׶δ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��5�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String ��������
	 */
	public String getYbdyDm(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select max(jddm) dm from XG_DTJS_JBSZB where jdmc ='Ԥ����Ա' ");
		return dao.getOneRs(sql.toString(), new String[] {}, "dm");
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ϣ��flag(sq:����;jg:���)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:00:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param flag
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxxMap(String id,String flag){
		StringBuilder sql = new StringBuilder();
		if("sq".equals(flag)){
			sql.append(" select xm,xb,mzmc,xh,sfzh,sqsj,xymc,bjmc,bjdm from view_new_dc_dtxx_sq where  dtxxsqid = ?");
		}else{
			sql.append(" select xm,xb,mzmc,xh,sfzh,jlsj sqsj,xymc,bjmc,bjdm from VIEW_NEW_DC_DTXX_JG where dtxxjgid = ?");
		}
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����: ��ȡ�������List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:17:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjjgList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xmmc, t.xn || t1.xqmc xnxq, t.sqsj");
		sql.append(" from xg_pjpy_new_pjjgb t");
		sql.append(" left join xqdzb t1");
		sql.append(" on t.xq = t1.xqdm");
		sql.append(" where t.xh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	
	/**
	 * 
	 * @����: ��ȡΥ�ʹ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-18 ����10:40:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjcfList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.cflbmc,t.CFSJ,t.XN || t1.xqmc xnxq,t.CFYYMC from xg_view_wjcf_wjcfb t");
		sql.append(" left join xqdzb t1");
		sql.append(" on t.XQ = t1.xqdm");
		sql.append(" where t.XH = ?");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	/**
	 * 
	 * @����: ��ȡѧ��ְ��
	 * @���ߣ� ����[���ţ�1529]
	 * @���ڣ�2017-11-30 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getZwmc(String xh){
		String sql = "select a.xh,wm_concat(zwmc) zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid where a.zzzt='1' and xh = ? group by a.xh";
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
	public HashMap<String,String> getBjgkc(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) num from view_zhhcjb  where cj <60 and xh=?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
	//ȡ�۲�������ƽ������
	public List<HashMap<String,String>> getZcfListByXh(String xh ,String xn ,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.fs,t1.cpzpm,t1.njzypm,t1.bjpm,");
		sql.append(" t1.xmdm,t2.xmmc from xg_zhcp_zcfsb t1 ");
		sql.append(" left join xg_zhcp_zcxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" where exists (select 1 from xg_zhcp_zcxmb t3 where t2.xmdm=t3.xmdm ");
		sql.append(" and t2.xn=t3.xn and t2.xq=t3.xq and ");
		sql.append(" (t3.fjdm='N' or t3.fjdm in (select xmdm from xg_zhcp_zcxmb where fjdm='N'))");
		sql.append(" ) and t1.xn=? and t1.xq=? and t1.xh=? order by xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn,xq});
	}

	/**
	 * @����:��ȡ�׶�����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/26 16:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [jddm]
	 * @return: java.lang.String
	 */
    public String getJdmc(String jddm) {
		String sql = "select jdmc from XG_DTJS_JBSZB where jddm=?";
		return dao.getOneRs(sql,new String[]{jddm},"jdmc");
    }
}
