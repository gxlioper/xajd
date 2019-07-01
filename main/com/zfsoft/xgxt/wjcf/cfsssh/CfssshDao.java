/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����09:26:23 
 */  
package com.zfsoft.xgxt.wjcf.cfsssh;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (�������) 
 * @���ߣ�������[����:913]
 * @ʱ�䣺 2013-10-30 ����09:23:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfssshDao extends SuperDAOImpl<CfssshForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CfssshForm t)
			throws Exception {
		
		
		
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfssshForm t, User user)
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
		sql.append("                       e.bjmc,e.zybj,e.zybjmc,");
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
		sql.append("                       c.mc || '[' || decode(b.shzt,0,'�����',1,'ͨ��',2,'��ͨ��','3','�˻�','4','������',5,'�����','����') || ']' shzt,");
		sql.append("                       c.gwz,");
		sql.append("                       row_number() over(partition by a.cfid order by b.shsj desc) rn,");
		sql.append("                       a.ssid ywid,");
		sql.append("                       a.splcid,");
		sql.append("                       b.gwid,");
		sql.append("                       b.guid shid,b.shsj");
		sql.append("                  from xg_wjcf_wjcfssb a");
		sql.append("                  left join xg_xtwh_shztb b");
		sql.append("                    on a.ssid = b.ywid");
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
	public CfssshForm getModel(CfssshForm t) throws Exception {
		String sql="select ssfilepath,fjmc,cfid,sqsj,sqly from xg_wjcf_wjcfssb where ssid=?";
		CfssshForm model=new CfssshForm();
		HashMap<String, String> map=dao.getMapNotOut(sql, new String[]{t.getYwid()});
		BeanUtils.copyProperties(model, map);
		return model;
	}


	@Override
	protected void setTableInfo() {
		this.setClass(CfssshForm.class);
		this.setKey("ssid");
		this.setTableName("xg_wjcf_wjcfssb");
	}


	/** 
	 * @����:(����ͨ����������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-30 ����03:11:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertWjjgk(CfssshForm form) throws Exception{
		String sql="update xg_wjcf_wjcfb set sssj=?,sswh=?,ssjg=?,cfggw=?,ssfilepath=? where cfid=?";
		
		return dao.runUpdateNotCommit(sql, new String[]{form.getSssj(),form.getSswh(),form.getZzssjg(),form.getCfggw(),form.getSsfilepath(),form.getCfid()});
	}


	/**
	 * @throws Exception  
	 * @����:(�ع�Υ�ͽ�����������˼�¼)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-30 ����03:52:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int udateJgk(String cfid) throws Exception {
		String sql="update xg_wjcf_wjcfb set sssj='',sswh='',ssjg='',cfggw='' where cfid=?";
		return dao.runUpdate(sql, new String[]{cfid}, "xg_wjcf_wjcfb");
	}


	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public Blob fjCx(String sql, String[] inputList, String column) {
		return dao.getOneLong(sql, inputList, column);
	}

}
