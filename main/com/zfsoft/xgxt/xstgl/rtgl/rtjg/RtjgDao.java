/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-7 ����10:41:27 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgDao;
import com.zfsoft.xgxt.xstgl.stgl.stjg.StjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����Դ[����:982]
 * @ʱ�䣺 2015-8-7 ����10:41:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RtjgDao extends SuperDAOImpl<RtjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RtjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RtjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,");
		sql.append("t1.rtid,");
		sql.append("t1.stid,");
		sql.append("t1.sqly,");
		sql.append("t1.rtxn,");
		sql.append("t1.rtxq,");
		sql.append("t1.tc,");
		sql.append("t5.stxmmc,");
		sql.append("t3.xmlbdm,");
		sql.append("t1.id,");
		sql.append("t1.sjly,");
		sql.append("t1.tnzt,");
		sql.append("t2.zydm,");
		sql.append("t2.zymc,");
		sql.append("t2.bjdm,");
		sql.append("t2.bjmc,");
		sql.append("t2.xydm,");
		sql.append("t2.xymc,");
		sql.append("t2.nj,");
		sql.append("t2.xb,");
		sql.append("t3.xmlbmc,");
		sql.append("t4.stlbdm,");
		sql.append("t4.stlbmc,");
		sql.append("t5.zdls,");
		sql.append("t5.xn, ");
		sql.append("t5.jtr, ");
		sql.append("t5.stfzr, ");
		sql.append("t5.kssj, ");
		sql.append("t5.jssj, ");
		sql.append("t6.xm zdlsxm,");
		sql.append(" case when t5.sqkg = 1 and sysdate between to_date(nvl(t5.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
		sql.append(" and to_date(nvl(t5.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from XG_RTGL_RTJG t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xg_stgl_jtjg t5");
		sql.append(" on t1.stid = t5.stid");
		sql.append(" left join xg_stgl_xmlb t3");
		sql.append(" on t5.xmlbdm = t3.xmlbdm");
		sql.append(" left join yhb t6 on t5.zdls = t6.yhm");
		sql.append(" left join xg_stgl_stlb t4");
		sql.append(" on t3.stlbdm = t4.stlbdm");
		sql.append(" order by t1.tnzt desc,t1.rtxn desc)t where 1= 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(RtjgForm.class);
		super.setKey("rtid");
		super.setTableName("XG_RTGL_RTJG");
	}
	
	//��ȡ������Ϣ�������ų�Ա��Ϣά���Լ��鿴
	public HashMap<String, String> getStxxMap(RtjgForm rtjg) throws Exception{
		StjgDao StDAO = new StjgDao();
		StjgForm stForm = StDAO.getModel(rtjg.getStid());
		String fzrlb = stForm.getFzrlb();
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.stxmmc,");
		sql.append(" t1.stid,");
		sql.append("t2.xmlbmc,");
		sql.append("t2.xmlbdm,");
		sql.append("t3.stlbmc,");
		sql.append("t1.xn,");
		sql.append("t1.kssj,");
		sql.append("t1.jssj,");
		sql.append("t1.stfzr,");
		sql.append("t1.jtr,");
		sql.append("t1.zdls,");
		sql.append("t1.gkdw,");
		sql.append("t1.sqsj,");
		sql.append("t4.xm jtrxm,");
		sql.append("t5.xm stfzrxm,");
		sql.append("x.zdlsxms zdlsxm");
		sql.append(" from xg_stgl_jtjg t1" +
				" left join (select a.stid,listagg(c.xm,',') within GROUP (order by c.xm) as zdlsxms from xg_stgl_jtjg a left join xg_stgl_zdlscy b on a.stid = b.stid left join fdyxxb c on b.zgh = c.zgh  group by a.stid) x on t1.stid = x.stid ");
		sql.append(" left join xg_stgl_xmlb t2");
		sql.append(" on t1.xmlbdm = t2.xmlbdm");
		sql.append(" left join xg_stgl_stlb t3");
		sql.append(" on t2.stlbdm = t3.stlbdm");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t4 on t1.jtr=t4.yhm");
		if(fzrlb.equals("��ʦ")){
			sql.append(" left join yhb t5 on t1.stfzr = t5.yhm");
		}else{
			sql.append(" left join xsxxb t5 on t1.stfzr = t5.xh ");
		}
		sql.append(" left join yhb t6 on t1.zdls = t6.yhm");
		sql.append(" where t1.stid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{rtjg.getStid()});
	}
	
	//��ȡ���ų�Ա��Ϣ�����usertype = stu,ֻ�ܲ鿴���˵���Ϣ��
	public List<HashMap<String, String>> getStxxCylist(RtjgForm rtjg,User user){
		StringBuilder sql = new StringBuilder();
		String[]inputValue = new String[]{rtjg.getStid()};
		sql.append("select t1.xh,");
		sql.append("t1.rtid,");
		sql.append("t1.sjly,");
		sql.append("t2.xm,");
		sql.append("t2.xb,");
		sql.append("t2.bjmc,t3.ldmc,t3.qsh,t3.qsdh,");
		sql.append("t2.xymc,");
		sql.append("t1.sqly,");
		sql.append("t1.tc,");
		sql.append("t1.rylbdm");
		sql.append(" from XG_RTGL_RTJG t1");
		sql.append(" left join view_xsjbxx t2 left join view_xg_gygl_new_cwxx t3 on t2.xh=t3.xh");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" where t1.stid = ? ");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and (t1.tnzt = '����' or t1.tnzt is null)");
		}
		if(user.getUserType().equalsIgnoreCase("stu") || (rtjg.getRtid() != null &&  !rtjg.getRtid().equals("") )  ){
			sql.append(" and  t1.rtid = ? ");
			inputValue = new String[]{rtjg.getStid(),rtjg.getRtid()};
		}
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	public String getSqShKg(String stid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from xg_stgl_jtjg t where 1=1 and t.stid = ?");
		return dao.getOneRs(sql.toString(),new String[]{stid},"sqkg");
	}
	
	public List<HashMap<String, String>> getXsxxList(RtjgForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhArr();
		StringBuilder sql = new StringBuilder("select * from(select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a ");
		if(xhs.length>0){
			sql.append("  where a.xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhs[i]+"' ");
			}
			sql.append(")");
			}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//���³�Ա��������(add) by yxy 
	public boolean saveAddRtCySl(String stid,String num) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_stgl_jtjg set cysl = nvl(cysl,0)+?  where stid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{num,stid});
	}
	
	//���³�Ա��������(del) by yxy 
	public boolean saveDelRtCySl(String stid,String num) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_stgl_jtjg set cysl = nvl(cysl,0)-?  where stid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{num,stid});
	}
	
	//��ȡ��Ա����б�
	public List<HashMap<String, String>>  getRylbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select rylbdm dm,rylbmc mc from xg_stgl_rylb");
		sql.append(" union select ' ' dm,' ' mc from dual");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//��ȡ������html
	public String getSelectOption(){
		List<HashMap<String, String>> rylblist = this.getRylbList();
		StringBuilder sql = new StringBuilder();
		sql.append("<select name='rylbdm'>");
	    for (HashMap<String, String> hashMap : rylblist) {
			sql.append("<option value='"+hashMap.get("dm")+"'>");
			sql.append(hashMap.get("mc"));
			sql.append("</option>");
		}
		sql.append("</select>");
		return sql.toString();
	}
}
