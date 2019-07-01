/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-18 ����11:04:34 
 */  
package com.zfsoft.xgxt.jskp.pjjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ�����۹���ģ��
 * @�๦������: ѧ�����۽��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-18 ����11:03:53 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjjgDao extends SuperDAOImpl<XspjjgForm>{
	private static final String XN_Yf = "09";	//�л�ѧ����·�

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,");
		sql.append(" a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,");
		sql.append(" decode(a.sjly,'1','�������','2','�������','3','����',a.sjly) sjly ");
		sql.append("from xg_xspj_new_pjjgb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		if("nlsy".equals(t.getType())){
			sql.append(" where e.xmlbmc like '%��������-%' ");
		}else{
			sql.append(" where e.xmlbmc not like '%��������-%' ");
		}
		sql.append(")t where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(XspjjgForm.class);
		this.setKey("guid");
		this.setTableName("xg_xspj_new_pjjgb");
	}
	
	/**
	 * @����: ͨ������idɾ�����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-18 ����05:29:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delShjgById(String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_xspj_new_pjjgb where guid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{sqid});
	}
	
	/**
	 * @����: ��Ŀ���List
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����09:11:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmlbList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select xmlbdm xmlb,xmlbmc from xg_jskp_xmlbb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ָ������List
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����09:11:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZdbmList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select bmdm zdbm,bmmc zdbmmc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��ѧ��List
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����11:49:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDxqList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select dxqdm,dxqmc from xg_jskp_dxq");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��֤ѧ���Ƿ����ѧ����Ϣ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����03:13:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXhisTrue(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xm from view_xg_xspj_xsxx where xh = ?");
		String xm = dao.getOneRs(sql.toString(), new String[]{xh}, "xm");
		if(StringUtils.isNull(xm)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @����: ��ָ֤������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����03:23:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yrdwmc
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkZdbm(String zdmbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select bmdm from ZXBZ_XXBMDM where bmmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{zdmbmc}, "bmdm");
	}
	
	/**
	 * @����: ���ѧ����ѧԺ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-24 ����05:46:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXsbmmc(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xymc from view_xg_xspj_xsxx where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xymc");
	}
	
	/**
	 * @����: ��֤��Ŀ����Ƿ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����03:28:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdmbmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkXmlb(String xmlbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmlbdm from xg_jskp_xmlbb where xmlbmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
	}
	
	/**
	 * @����: ��֤��Ŀ���Ϊ�������������ݲ��ܵ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-19 ����02:18:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmlbmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkNlsy(String xmlbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmlbdm from xg_jskp_xmlbb where xmlbmc = ? and xmlbmc not like '��������-%' ");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
	}
	
	/**
	 * @����: ��֤��ѧ���Ƿ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����03:51:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmlbmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkDxq(String dxqmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select dxqdm from xg_jskp_dxq where dxqmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{dxqmc}, "dxqdm");
	}
	
	/**
	 * @����: Ψһ������֤
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����03:59:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmc
	 * @param cysj
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String xmmc,String cysj,String xh,String id){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(1) num from xg_xspj_new_pjjgb where xmmc = ? and cysj = ? ");
		sql.append(" and xh = ?");
		paraList.add(xmmc);
		paraList.add(cysj);
		paraList.add(xh);
		if(StringUtils.isNotNull(id)){
			sql.append(" and juid != ?");
			paraList.add(id);
		}
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
	
	/**
	 * @����: �ж�ѧ���Ƿ����xsxxb
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-30 ����05:28:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXh(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*)cnt from view_xg_xspj_xsxx where xh = ?");
		String cnt = dao.getOneRs(sql.toString(), new String[]{xh}, "cnt");
		return "0".equals(cnt) ? false : true;
	}
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-4-20 ����04:22:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xspj_new_pjjgb( ");
		sql.append("xh,xn,fz,dxqdm,xmmc,zdbmdm,xmlbdm,cysj,fzrxm,fzrlxfs,");
		sql.append("zdlsxm,zdlslxfs,sqly,sjlrr,sjly,sjlrsj");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * @����: �ж�Ψһ����ѧ��(xh),��Ŀ����(xmmc),����ʱ��(cysj)
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-24 ����09:58:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xspj_new_pjjgb where xh = ? and xmmc = ? and cysj = ? ");
		paraList.add(model.getXh());
		paraList.add(model.getXmmc());
		paraList.add(model.getCysj());
		if(StringUtils.isNotNull(model.getGuid())){
			sql.append(" and guid <> ? ");
			paraList.add(model.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*��������ʱ����false��������ʱ����true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @����: �鿴
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-4-25 ����09:51:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,");
		sql.append(" decode(a.sjly,'1','�������','2','�������','3','����',a.sjly) sjly ");
		sql.append("from xg_xspj_new_pjjgb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(") where guid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/**
	 * @����: ��ȡ�����˵�ѧ�����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-4-25 ����10:23:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSingleSummary(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select xn,xmlbmc,sum(fz) fz from ( ");
		sql.append("select b.xmlbmc,fz,case when to_char(to_date(cysj,'yyyy-mm-dd'),'mm') >= "+XN_Yf);
		sql.append(" then to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy')||'-'||to_char(to_number(to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy'))+1) ");
		sql.append("else to_char(to_number(to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy'))-1)||'-'||to_char(to_date(cysj,'yyyy-mm-dd'),'yyyy') end xn ");
		sql.append(" from xg_xspj_new_pjjgb a left join xg_jskp_xmlbb b on a.xmlbdm = b.xmlbdm ");
		sql.append("where a.xh = ? ) group by xn,xmlbmc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @����: ���۽������ͳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-25 ����03:24:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param SearchXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDateForSearchXn (XspjjgForm t, User user, String SearchXn) throws Exception{

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select b.xh,b.xm,b.nj,b.xymc,b.zymc,b.bjmc,b.zf,b.xn,");
		sql.append("       b.hdjszf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.hdjszf desc) hdjsxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.hdjszf desc) hdjszypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.hdjszf desc) hdjsbjpm,");
		sql.append("       b.cxcyzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.cxcyzf desc) cxcyxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.cxcyzf desc) cxcyzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.cxcyzf desc) cxcybjpm,");
		sql.append("       b.dwjlzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.dwjlzf desc) dwjlxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.dwjlzf desc) dwjlzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.dwjlzf desc) dwjlbjpm,");
		sql.append("       b.gyfwzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.gyfwzf desc) gyfwxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.gyfwzf desc) gyfwzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.gyfwzf desc) gyfwbjpm,");
		sql.append("       b.shgzzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.shgzzf desc) shgzxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.shgzzf desc) shgzzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.shgzzf desc) shgzbjpm,");
		sql.append("       b.wthdzf,");
		sql.append("       dense_rank() over(partition by b.xymc, b.xn order by b.wthdzf desc) wthdxypm,");
		sql.append("       dense_rank() over(partition by b.zymc, b.xn order by b.wthdzf desc) wthdzypm,");
		sql.append("       dense_rank() over(partition by b.bjmc, b.xn order by b.wthdzf desc) wthdbjpm ");
		sql.append(" from(");
		sql.append("  select * from( ");
		sql.append("    select xh,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,");
		sql.append("           sum(fz)zf,");
		sql.append("           sum(case a.xmlbmc when '˼������-���ʵ' then to_number(a.fz) else 0 end) hdjszf,");
		sql.append("           sum(case a.xmlbmc when '��������-���´�ҵ' then to_number(a.fz) else 0 end) cxcyzf,");
		sql.append("           sum(case a.xmlbmc when '��������-���⽻��' then to_number(a.fz) else 0 end) dwjlzf,");
		sql.append("           sum(case a.xmlbmc when '��������-�������' then to_number(a.fz) else 0 end) gyfwzf,");
		sql.append("           sum(case a.xmlbmc when '��������-��Ṥ��' then to_number(a.fz) else 0 end) shgzzf,");
		sql.append("           sum(case a.xmlbmc when '��������-����' then to_number(a.fz) else 0 end) wthdzf ");
		sql.append("     from view_xg_xspj_pjjg a");
		sql.append("     where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append("     group by xh,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn");
		sql.append("  ) where 1 = 1");
		sql.append(")b order by xymc,zf desc,zymc,bjmc ");
		/*return dao.getListNotOut(sql.toString(),StringUtils.joinStrArr(new String[]{SearchXn},inputV)); �ȷ������Ա��õ�˫����*/
		return dao.getListNotOut(sql.toString(),inputV);
	}
}
