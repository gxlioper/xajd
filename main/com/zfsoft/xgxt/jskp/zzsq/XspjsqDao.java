/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-11 ����09:09:46 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧϰ���۹���ģ��
 * @�๦������: ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-11 ����09:09:20 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjsqDao extends SuperDAOImpl<XspjsqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.sqid,");
		sql.append(" a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.shzt,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',a.shzt) shztmc,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,a.splcid ");
		sql.append("from xg_xspj_new_zzsqb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
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
		// TODO �Զ����ɷ������
		this.setClass(XspjsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_xspj_new_zzsqb");
	}
	
	/**
	 * @����: �ж�ѧ�����꼶�Ƿ���ڵ���2017
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-11 ����10:58:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean getCheckStuNj(String userName) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)cnt from view_xg_xspj_xsxx where nj >= 2017 and xh = ? ");
		String rs = dao.getOneRs(sql.toString(), new String[]{userName}, "cnt");
		return "1".equals(rs) ? true : false;
	}
	
	/**
	 * @����: ��ȡ�������̣����ϱ��ľͿ�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-11 ����11:09:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sb
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSplcForParam(String sb){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_csszb where lx = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{sb});
	}
	
	/**
	 * @����: ��ȡ��ѧ����ϢList
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-12 ����07:25:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDxqInfoList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select dxqdm,dxqmc from xg_jskp_dxq order by cast(dxqdm as int)");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: �ж�Ψһ����ѧ��(xh),��Ŀ����(xmmc),����ʱ��(cysj)
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-12 ����06:01:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjsqForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xspj_new_zzsqb where xh = ? and xmmc = ? and cysj = ? ");
		paraList.add(model.getXh());
		paraList.add(model.getXmmc());
		paraList.add(model.getCysj());
		if(StringUtils.isNotNull(model.getSqid())){
			sql.append(" and sqid <> ? ");
			paraList.add(model.getSqid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*��������ʱ����false��������ʱ����true*/
		return "0".equals(cnt) ? true : false;
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
	 * @����: ɾ��ʱ�������״̬���е���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����10:39:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xtwh_shztb where ywid in ( ");
		for (int i = 0; i < sqids.length; i++) {
			sql.append("?");
			if(i != sqids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), sqids);
	}
	
	/**
	 * @����: ��������id��ȡ�����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����02:05:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xspj_new_zzsqb where");
		for(int i = 0; i < sqidArr.length; i++){
			sql.append(" sqid = ? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	
	/**
	 * @����: ����ָ�����Ŵ���ȡ��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-13 ����04:52:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdbmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBmmcByZdbmdm(String zdbmdm){
		String sql = "select bmmc from zxbz_xxbmdm where bmdm = ? ";
		return dao.getOneRs(sql, new String[]{zdbmdm}, "bmmc");
	}
	
	/**
	 * @����: ��������ID���ѧ��������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-4-16 ����03:37:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.sqid,");
		sql.append(" a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.shzt,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',a.shzt) shztmc,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,a.splcid ");
		sql.append("from xg_xspj_new_zzsqb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(")t where t.sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * @����: ָ������List
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-22 ����09:02:07
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
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-22 ����09:01:53
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
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-5-22 ����09:48:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xspj_new_zzsqb( ");
		sql.append("sqid,xh,xn,fz,dxqdm,xmmc,zdbmdm,xmlbdm,cysj,fzrxm,fzrlxfs,");
		sql.append("zdlsxm,zdlslxfs,sqly,sjlrr,shzt,splcid,sjlrsj");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
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
		sql.append("select count(1) num from xg_xspj_new_zzsqb where xmmc = ? and cysj = ? ");
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
}
