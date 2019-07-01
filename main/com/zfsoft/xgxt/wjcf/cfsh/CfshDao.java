/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-28 ����10:56:12 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (�����ϱ����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-28 ����10:51:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfshDao extends SuperDAOImpl<CfshForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CfshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql=new StringBuilder();
		sql.append("select * from(select * from (");
		sql.append("select e.sydm1 sydm ,e.symc1 symc ,e.nj,e.xymc,e.xydm,e.zydm,e.bjdm,e.zymc,e.bjmc,e.zybj,e.zybjmc,a.xh,e.xm,e.xb,a.xn,a.xq,b.shsj,(select xqmc from xqdzb t1 where t1.xqdm=a.xq)xqmc,");
		sql.append(" (case when f.cfggw is not null then f.cfggw else (select cflbmc from xg_wjcf_cflbdmb t2 where t2.cflbdm=a.cflbdm) end) cflbmc,a.cflbdm,(select cfyymc from xg_wjcf_cfyydmb t2 where t2.cfyydm=a.cfyydm)cfyymc,a.cfyydm,f.cfsj,");
		sql.append("c.mc || '[' || decode(b.shzt,0,'�����',1,'ͨ��',2,'��ͨ��',3,'�˻�',4,'������',5,'�����','����') || ']' shzt,c.gwz, ");
		sql.append("row_number()over(partition by a.cfid order by b.shsj desc) rn ,");
		sql.append("a.cfid ywid,a.kzzd1,a.splcid,b.gwid,b.guid shid ");
		sql.append(",a.kzzd4 cffwqx ");//���ַ���Ȩ�ޣ������մɣ�
		sql.append(" from xg_wjcf_wjcfsbb a left join xg_xtwh_shztb b on a.cfid=b.ywid left join xg_xtwh_spgw c on b.gwid=c.id left join xg_xtwh_spgwyh d on c.id=d.spgw ");
		sql.append(" left join view_xsjbxx e on a.xh=e.xh left join xg_wjcf_wjcfb f on a.cfid=f.cfid where d.spyh='"+user.getUserName()+"'  and b.shzt<>9 ");
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
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("cfid");
		super.setTableName("xg_wjcf_wjcfsbb");
		super.setClass(CfshForm.class);

	}


	/** 
	 * @����:(��ȡ�����ϱ���Ϣ)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-28 ����03:20:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getCfsbxx(CfshForm model) {
		StringBuilder sql = new StringBuilder("select a.filepath,a.filepath2,a.filepath3,a.filepath4,a.cfid,xh,xn,a.xq,wjsj,cfyj,cflbdm,(select xqmc from xqdzb t where ");
		sql.append("a.xq=t.xqdm)xqmc,(case when b.cfggw is not null then b.cfggw else (select cflbmc from xg_wjcf_cflbdmb t where ");
		sql.append("t.cflbdm=a.cflbdm) end) cflbmc,(select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc,");
		sql.append("(select xm from fdyxxb t where t.zgh=a.sbb) sbbxm,wjssjg,bz,splcid,fjmc,b.cfwh,b.cfsj,b.cfdqsj,");
		sql.append("a.kzzd2,a.kzzd3,");//�����ϱ����еĴ��ַ�����Ϣ
		sql.append("a.kzzd4,");//�ϱ�ʱ������������
		sql.append("a.kzzd5 ");//�����ϱ����еĴ��ֵ���ʱ��
		sql.append("from xg_wjcf_wjcfsbb a left join (select cfid,cfwh,cfsj,cfdqsj,cfggw from xg_wjcf_wjcfb) b on a.cfid=b.cfid where a.cfid=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{model.getYwid()});
	}
	public HashMap<String, String> getCfsbxxForjg(CfshForm model) {
		String sql="select a.filepath,a.filepath2,a.filepath3,a.filepath4,b.ssfilepath,a.cfid,xh,xn,a.xq,wjsj,cfyj,cflbdm,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,"
				//+ "(case when a.cfggw is not null then a.cfggw else (select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm) end) "
				+ "(select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm) "
				+ "cflbmc,(select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc,wjssjg,a.cfwh,a.cfsj,a.cfdqsj,bz,b.splcid,a.fjmc,"
				+ "a.sswh,a.sssj,a.cfggw,(case when a.ssjg='wcycf' then 'ά��ԭ����' when a.ssjg= 'ggcf' then '���Ĵ���' when a.ssjg='cxcf' "
				+ "then '��������' else a.ssjg end) ssjg,a.jcwh,a.jcsj from xg_wjcf_wjcfb a left join xg_wjcf_wjcfssb b on b.cfid=a.cfid "
				+ "where a.cfid=?";
		
		return dao.getMapNotOut(sql, new String[]{model.getYwid()});
	}

	/** 
	 * @����:(��ȡ�������̸�λ)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-28 ����07:05:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * ArrayList<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public ArrayList<HashMap<String, String>> getSplcgw(CfshForm model) {
		String sql="select * from xg_xtwh_spbz where splc=? order by xh ";
		return (ArrayList<HashMap<String, String>>) dao.getListNotOut(sql, new String[]{model.getSplcid()});
	}


	/** 
	 * @����:TODO(��������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-28 ����05:11:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertWjjgk(CfshForm form) throws Exception{
		// TODO �Զ����ɷ������
		StringBuilder sql=new StringBuilder();
		sql.append("insert into xg_wjcf_wjcfb(filepath,filepath2,filepath3,filepath4,cfid,xh,xn,xq,cflbmc,cfyymc,cflbdm,cfyydm,cfsj,cfdqsj,cfwh,wjsj,cfyj,sbb,sbsj,wjssjg,bz,fjmc,fj,cflsh,sjly,cfsbid) ");
		sql.append("select filepath,filepath2,filepath3,filepath4,cfid,xh,xn,xq,(select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm)cflbmc,(select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc,cflbdm,cfyydm,? cfsj,? cfdqsj,? cfwh,wjsj,cfyj,sbb,sbsj,wjssjg,bz,fjmc,fj,? cflsh, ? sjly,? cfsbid from xg_wjcf_wjcfsbb a where cfid=?");
		
		return dao.runUpdate(sql.toString(), new String[]{form.getCfsj(),form.getCfdqsj(),form.getCfwh(),form.getCflsh(),form.getSjly(),form.getYwid(),form.getYwid()});
	}
	/**
	 * ��ȡ��ˮ��
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsh(HashMap<String,String> map){
		StringBuffer sql= new StringBuffer();
		sql.append("select cflsh from xg_wjcf_wjcfb")
		.append(" where xn=? and xq=? and cflsh is not null order by cflsh desc");
		return dao.getListNotOut(sql.toString(), new String[]{map.get("xn"),map.get("xq")});
	}
	public HashMap<String,String> getSbxx(CfshForm model){
		String sql="select * from xg_wjcf_wjcfsbb where cfid=?";
		return dao.getMapNotOut(sql, new String[]{model.getYwid()});
		
	}

	/** 
	 * @����:(�Ƿ��пɻع�)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����10:24:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean sfkcx(String ywid) {
		String sql="select * from (select cfid from xg_wjcf_wjcfssb union all select cfid from xg_wjcf_wjcfjcshb) where cfid=?";
		List<HashMap<String, String>> rs=dao.getListNotOut(sql, new String[]{ywid});
		return rs.size()==0;
	}


	/** 
	 * @����:(Υ�ͽ����ɾ��)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-29 ����10:55:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public int deleteJgk(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{ywid};
		sql.append("delete from ");
		sql.append(" xg_wjcf_wjcfb ");
		sql.append(" where ");
		sql.append(" cfid = ? ");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		return dao.runDelete(sql.toString(), new String[]{ywid});
	}
	/**
	 * 
	 * @����:�ж��Ƿ��ڽ�����д���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-9-18 ����04:36:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getJgk(String ywid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select xh from ");
		sql.append(" xg_wjcf_wjcfb ");
		sql.append(" where ");
		sql.append(" cfid = ? ");
		return dao.getOneRs(sql.toString(), new String[]{ywid},"xh");
	}


	/**
	 * ��ѯ������Ϣ
	 * @param
	 * @return
	 */
	public Blob fjCx(String sql, String[] inputList, String column) {
		return dao.getOneLong(sql, inputList, column);
	}


	/** 
	 * @����:��ȡ��˸�λ�ļ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��27�� ����7:42:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @param gwid
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getShgwjb(String splcid, String gwid) {
		String sql = "SELECT XH FROM xg_xtwh_spbz WHERE SPLC = ? AND SPGW = ?";
		String shgwjb = dao.getOneRs(sql, new String[]{splcid,gwid}, "xh");
		return shgwjb;
	}


	/** 
	 * @����:���ݴ���idȡ���������Ȩ�ޣ���kzzd4
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��15�� ����3:40:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getZcFwqxByCfid(String ywid) {
		String sql = "SELECT kzzd4 FROM XG_WJCF_WJCFSBB WHERE cfid = ?";
		String zcffqx = dao.getOneRs(sql, new String[]{ywid}, "kzzd4");
		return zcffqx;
	}

	public HashMap<String,String> getxsnl(String xh) {
		String sql = " select  floor(months_between(SYSDATE, to_date(a.csrq,'yyyy-mm-dd'))/ 12) as  xsnl from view_xsxxb a where a.xh =?";
		return dao.getMapNotOut(sql, new String[]{xh});
	}

	public HashMap<String,String> getbjrs(String bjdm) {
		String sql = " select count(a.xh) as bjrs from view_xsxxb a where a.bjdm =?";
		return dao.getMapNotOut(sql, new String[]{bjdm});
	}
}
