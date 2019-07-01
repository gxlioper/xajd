/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����06:38:32 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqDao;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (���ֽ�����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-30 ����06:36:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjcshDao extends SuperDAOImpl<CfjcshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CfjcshForm t)
			throws Exception {
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfjcshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select * ");
		sql.append("          from (select e.nj,e.xymc,");
		sql.append("                       e.xydm,");
		sql.append("                       e.zydm,");
		sql.append("                       e.bjdm,");
		sql.append("                       e.zymc,e.sydm1 sydm,e.symc1 symc,");
		sql.append("                       e.bjmc, e.zybj, e.zybjmc,");
		sql.append("                       e.xh,");
		sql.append("                       e.xm,");
		sql.append("                       e.xb,");
		sql.append("                       f.xn,");
		sql.append("                       f.xq,");
		sql.append("                       a.cfid,");
		sql.append("                       (select xqmc from xqdzb t1 where t1.xqdm = f.xq) xqmc,");
		sql.append("                       (case when f.cfggw is not null then f.cfggw else (select cflbmc from xg_wjcf_cflbdmb t2 where t2.cflbdm = f.cflbdm) end) cflbmc,");
		sql.append("                       f.cflbdm,");
		sql.append("                       (select cfyymc from xg_wjcf_cfyydmb t2 where t2.cfyydm = f.cfyydm) cfyymc,");
		sql.append("                       f.cfyydm,");
		sql.append("                       c.mc || '[' || decode(b.shzt,0,'�����',1,'ͨ��',2,'��ͨ��',3,'�˻�','4','������',5,'�����','����') || ']' shzt,");
		sql.append("                       c.gwz,");
		sql.append("                       row_number() over(partition by a.cfid order by b.shsj desc) rn,");
		sql.append("                       a.jcid ywid,");
		sql.append("                       a.splcid,");
		sql.append("                       b.gwid,");
		sql.append("                       b.guid shid,b.shsj");
		sql.append("                  from xg_wjcf_wjcfjcsqb a");
		sql.append("                  left join xg_xtwh_shztb b");
		sql.append("                    on a.jcid = b.ywid");
		sql.append("                  left join xg_wjcf_wjcfb f");
		sql.append("                    on a.cfid=f.cfid");
		sql.append("                  left join xg_xtwh_spgw c");
		sql.append("                    on b.gwid = c.id");
		sql.append("                  left join xg_xtwh_spgwyh d");
		sql.append("                    on c.id = d.spgw");
		sql.append("                  left join view_xsjbxx e");
		sql.append("                    on f.xh = e.xh");
		sql.append("                 where d.spyh = '"+user.getUserName()+"'  and b.shzt<>9 ");
		String shlx = t.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}
		sql.append(") a  where rn = 1) a where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	@Override
	public CfjcshForm getModel(CfjcshForm t) throws Exception {
		String sql="select sqsj,sqly,jdxx,filepath,filepath2 from xg_wjcf_wjcfjcsqb where jcid=? ";
		HashMap<String, String> map=dao.getMapNotOut(sql, new String[]{t.getYwid()});
		CfjcshForm model=new CfjcshForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}

	@Override
	protected void setTableInfo() {

		this.setClass(CfjcshForm.class);
		this.setKey("jcid");
		this.setTableName("xg_wjcf_wjcfjcsqb");
	}


	/** 
	 * @����:(���ͨ����������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-30 ����03:11:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertWjjgk(CfjcshForm form) throws Exception{
		String sql="update xg_wjcf_wjcfb set jcwh=?,jcsj=?,filepath5=?,filepath6=? where cfid=?";
		
		return dao.runUpdateNotCommit(sql, new String[]{form.getJcwh(),form.getJcsj(),form.getFilepath(),form.getFilepath2(),form.getCfid()});
	}


	/**
	 * @throws Exception  
	 * @����:(�ع�Υ�ͽ����Ľ����˼�¼)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-30 ����03:52:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int udateJgk(String cfid) throws Exception {
		String sql="update xg_wjcf_wjcfb set jcwh='',jcsj='' where cfid=?";
		return dao.runUpdate(sql, new String[]{cfid}, "xg_wjcf_wjcfb");
	}
	/**
	 * 
	 * @����:���������ˮ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-29 ����07:31:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getJcLsh(CfjcshForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select (case when length(jclsh)<2 then '0'|| jclsh else jclsh end)  jclsh");
		sql.append("  from (select to_char((to_number(count(1))+1)) jclsh from(select a.*,b.xn,b.xq from xg_wjcf_wjcfjcsqb a left join xg_wjcf_wjcfb b on a.cfid=b.cfid");
		sql.append(" )where xn=? and xq=? and sqjg='1')  ");
		return dao.getOneRs(sql.toString(), new String[]{model.getXn(),model.getXq()},"jclsh");
	}
	/**
	 * 
	 * @����:��֤����ĺ��Ƿ��Ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����09:43:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistJcwh(CfjcshForm myForm) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_wjcf_wjcfb a ");
		sql.append(" where a.jcwh=?  ");
		
		// ����ID�������ȥ�ô���
		if(StringUtils.isNotNull(myForm.getCfid())){
			sql.append(" and a.cfid != '" + myForm.getCfid() + "' ");
		}
		String num = dao.getOneRs(sql.toString(), new String[] { myForm.getJcwh()}, "num");
		
		return num;
	}

}
