/**

 * @���ڣ�2016-6-20 ����10:33:02 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfdr;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-20 ����10:33:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfDrDao extends SuperDAOImpl<ZhfDrForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfDrForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfDrForm t, User user)
			throws Exception {
		//String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
//		sql.append(" select * from (select a.*,e.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,c.xmmkmc,d.fs,d.jfxmmc, ");
//		sql.append(" decode(a.shzt,'1','����','0','����') shztmc ");
		sql.append(" select * from (");
		sql.append(" select a.*,");
		sql.append(" case when to_number(length(a.lrr))>10 then 'ѧ������' else (select bmmc||'����' from view_fdyxx where zgh =a.lrr) end  lb,");
		sql.append(" (select xm from view_fdyxx where a.shr = zgh ) shrxm ,");
		sql.append(" e.xm,");
		sql.append(" e.xydm,");
		sql.append(" e.xymc,");
		sql.append(" e.zydm,");
		sql.append(" e.zymc,");
		sql.append(" e.bjdm,");
		sql.append(" e.bjmc,");
		sql.append(" e.nj,");
		sql.append(" c.xmmkmc,");
		sql.append(" d.fs,");
		sql.append(" d.jfxmmc,");
		sql.append(" decode(a.shzt,");
		sql.append(" '1',");
		sql.append(" '����','2','�˻�',");
		sql.append(" '0',");
		sql.append(" '����') shztmc");
		sql.append(" from xg_zjly_zhszf_drjlb a ");
		sql.append(" left join view_xsxxb e on a.xh = e.xh ");
//		sql.append(" left join view_njxyzybj b on b.bjdm = e.bjdm ");
		sql.append(" left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm ");
		sql.append(" left join xg_zjly_zhszf_jfxmb d on a.jfxmdm = d.jfxmdm ");
		sql.append(" where e.sfzx='��У')t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by xh,xmmkmc,jfxmmc");
		return getPageList(t, sql.toString(),inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(ZhfDrForm.class);
		this.setKey("id");
		this.setTableName("xg_zjly_zhszf_drjlb");
	}
	
	/**
	 * 
	 * @����:��ȡ����Ա��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����02:57:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFdyxx(String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdh,xm,zgh,bmmc");
		sql.append("  from view_fdyxx");
		sql.append(" where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 
	 * @����:xh,xm������֤�Ƿ����xsxxb
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-22 ����01:54:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXhXmIsExist(String xh,String xm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xsxxb where xh = ? and xm = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh,xm}, "num").equals("1") ? true : false;
	}
	
	/**
	 * 
	 * @����:xh,sxsm,cysj,jfxmdm������֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-22 ����02:05:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param cysj
	 * @param jfxmdm
	 * @param sxsm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXhXmSxsmJfxmdmCf(String xh,String cysj,String jfxmdm,String sxsm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjly_zhszf_drjlb where xh = ? and cysj = ? and jfxmdm = ? and sxsm = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh,cysj,jfxmdm,sxsm}, "num").equals("0") ? true : false;
	}
	
	/**
	 * 
	 * @����:��ʦ�������ݷ�Χ��֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-22 ����03:03:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkJsDrSjfw(String xh,ZhfDrForm t){
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(t.getUser(), "t", "xydm", "bjdm");
		sql.append(" select count(1) num from view_xsxxb t where xh = ?  ");
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num").equals("1") ? true : false;
	} 
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��������֤��������ݲ������ݿ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-23 ����09:23:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @param username
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zjly_zhszf_drjlb( ");
		sql.append(" xh, ");
		sql.append(" sxsm,");
		sql.append(" cysj,");
		sql.append(" lrsj,");
		sql.append(" lrr,");
		sql.append(" xmmkdm,");
		sql.append(" jfxmdm,");
		sql.append(" shzt");
		sql.append(" )values(?,?,?,?,?,?,?,1)");
 		return dao.runBatch(sql.toString(), paralist);
 		
	}
	
	/**
	 * 
	 * @����: �ж��Ƿ����ظ���¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-23 ����02:09:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotExists(ZhfDrForm t ){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1)  num from ");
		sql.append(" xg_zjly_zhszf_drjlb ");
		sql.append(" where jfxmdm = ? and sxsm = ? and xh = ? and cysj = ? and id != ?");
		sql.append(" ");
		sql.append(" ");
		return dao.getOneRs(sql.toString(), new String[]{t.getJfxmdm(),t.getSxsm(),t.getXh(),t.getCysj(),t.getId()}, "num").equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-23 ����04:50:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> viewJg(String id){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,");
		sql.append(" t1.xmmkmc,");
		sql.append(" t2.jfxmmc,");
		sql.append(" t2.fs,");
		sql.append(" t2.khyd,");
		sql.append(" t3.xm lrrname");
		sql.append(" from xg_zjly_zhszf_drjlb t");
		sql.append(" left join xg_zjly_zhszf_mkxmb t1");
		sql.append(" on t.xmmkdm = t1.xmmkdm");
		sql.append(" left join xg_zjly_zhszf_jfxmb t2");
		sql.append(" on t1.xmmkdm = t2.xmmkdm and t.jfxmdm = t2.jfxmdm");
		sql.append(" left join ");
		sql.append(" (");
		sql.append(" select xh yhm,xm from xsxxb");
		sql.append(" union all");
		sql.append(" select yhm,xm from yhb");
		sql.append(" )t3");
		sql.append(" ");
//		sql.append(" left join yhb t3");
		sql.append(" on t.lrr = t3.yhm");
		sql.append(" where id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����:������������ݲ�����ʱ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:16:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] saveDrDataIntoDblsb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_zjly_zhszf_drjlb_lsb( ");
		sql.append(" xh, ");
		sql.append(" sxsm,");
		sql.append(" cysj,");
		sql.append(" lrsj,");
		sql.append(" lrr,");
		sql.append(" xmmkdm,");
		sql.append(" jfxmdm");
		sql.append(" )values(?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ����ʱ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:17:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean dellsbsj() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjly_zhszf_drjlb_lsb");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����: �����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:21:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public ArrayList<HashMap<String, String>>  getLastDatasj(String username,String lrsj,String xmmkdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh, sxsm, cysj "+lrsj+" lrsj, "+username+",lrr"+xmmkdm+" xmmkdm, jfxmdm ");
		sql.append(" from (select xh, sxsm, cysj, jfxmdm, count(1) num");
		sql.append(" from XG_ZJLY_ZHSZF_DRJLB_lsb");
		sql.append(" group by jfxmdm, cysj, sxsm, xh) t");
		sql.append("  where t.num < 2");
		
	    return dao.getArrayList(sql.toString(), new String[]{},new String[]{"xh,sxsm,cysj,lrsj,lrr,xmmkdm,jfxmdm"});
	   
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����: �����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:21:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public ArrayList<HashMap<String, String>> getLastDatasjerror(String username,String lrsj,String xmmkdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select x.xh,x1.xm,x.sxsm,x.cysj,'excel�����ظ���¼��' error");
		sql.append(" (select xh, sxsm, cysj");
		sql.append(" from (select xh, sxsm, cysj, jfxmdm, count(1) num");
		sql.append(" from XG_ZJLY_ZHSZF_DRJLB_lsb");
		sql.append(" group by jfxmdm, cysj, sxsm, xh) t");
		sql.append("  where t.num > 1)x");
		sql.append(" left join view_xsxxb x1");
		sql.append("  on x.xh = x1.xh");
	    return 	 dao.getArrayList(sql.toString(), new String []{}, new String[]{"xh,xm,sxsm,cysj,error"});

	}
/**
 * ȡ��ɾ������ϸ��Ϣ
 */
	public HashMap<String, String> getJfxmxx(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,xh,jfxmdm,xmmkdm,sxsm,shzt,cysj,lrr,lrsj");
		sql.append("  from xg_zjly_zhszf_drjlb");
		sql.append(" where id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
/**
 * ������־
 */
	public boolean saveSd(String time, User user, HashMap<String, String> map) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_zjly_zhfsclog (logid,scrzgh,scrxm,scsj,xh,jfxmdm,xmmkdm,sxsm,shzt,cysj,lrr,lrsj)");
				sql.append("values ('" + map.get("id") + "'");
				sql.append(",'" + user.getUserName() + "'"); 
				sql.append(",'" + user.getRealName() + "'"); 
				sql.append(",'" + time + "'"); 
				sql.append(",'" + map.get("xh") + "'"); 
				sql.append(",'" + map.get("jfxmdm") + "'"); 
				sql.append(",'" + map.get("xmmkdm") + "'"); 
				sql.append(",'" + map.get("sxsm") + "'"); 
				sql.append(",'" + map.get("shzt") + "'"); 
				sql.append(",'" + map.get("cysj") + "'"); 
				sql.append(",'" + map.get("lrr") + "'"); 
				sql.append(",'" + map.get("lrsj") + "')"); 
				return dao.runUpdate(sql.toString(), new String[]{});
}
	
	public void addlog(String time, User user, String[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
			HashMap<String, String> map = getJfxmxx(ids[i]);
			saveSd(time,user,map);
		}
	}

	public void dellog(String[] ids) throws Exception {
		String sql=" delete from xg_zjly_zhfsclog where logid in(";
		for (int i = 0; i < ids.length; i++) {
			sql+=" '"+ids[i]+"',";
		}
		sql=sql.substring(0, sql.length()-1);
		sql+=")";
		dao.runUpdate(sql.toString(), new String[]{});
	}

	/**
	 * @throws Exception  
	 * @����:��ϸ����list
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-13 ����09:49:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXXsxList(ZhfDrForm t, User user) throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select a.*,");
		sql.append(" case when to_number(length(a.lrr))>10 then 'ѧ������' else (select bmmc||'����' from view_fdyxx where zgh =a.lrr) end  lb,");
		sql.append(" (select xm from view_fdyxx where a.shr = zgh ) shrxm ,");
		sql.append(" e.xm,");
		sql.append(" e.xydm,");
		sql.append(" e.xymc,");
		sql.append(" e.zydm,");
		sql.append(" e.zymc,");
		sql.append(" e.bjdm,");
		sql.append(" e.bjmc,");
		sql.append(" e.nj,");
		sql.append(" c.xmmkmc,");
		sql.append(" d.fs,");
		sql.append(" d.jfxmmc,");
		sql.append(" decode(a.shzt,");
		sql.append(" '1',");
		sql.append(" '����','2','�˻�',");
		sql.append(" '0',");
		sql.append(" '����') shztmc");
		sql.append(" from xg_zjly_zhszf_drjlb a ");
		sql.append(" left join view_xsxxb e on a.xh = e.xh ");
		sql.append(" left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm ");
		sql.append(" left join xg_zjly_zhszf_jfxmb d on a.jfxmdm = d.jfxmdm ");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by xh,xmmkdm,jfxmdm");
		return dao.getListNotOut(sql.toString(), inputV);
		
	}

	/**
	 * @throws  Exception
	 * @����:ȡ�� xh:ģ������list
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-14 ����10:59:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getMktsList(ZhfDrForm t, User user) throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct xh, WM_CONCAT(mkts) over(partition by xh order by xh) mkts from (");
		sql.append(" select xh, concat(xmmkmc,'&'||count(1)) mkts  from (select a.*,");
		sql.append("  case when to_number(length(a.lrr)) > 10 then 'ѧ������'else (select bmmc || '����' from view_fdyxx where zgh = a.lrr) end lb,");
		sql.append(" e.xm,");
		sql.append(" e.xydm,");
		sql.append(" e.xymc,");
		sql.append(" e.zydm,");
		sql.append(" e.zymc,");
		sql.append(" e.bjdm,");
		sql.append(" e.bjmc,");
		sql.append(" e.nj,");
		sql.append(" c.xmmkmc,");
		sql.append(" d.fs,");
		sql.append(" d.jfxmmc,");
		sql.append(" decode(a.shzt,");
		sql.append(" '1',");
		sql.append(" '����','2','�˻�',");
		sql.append(" '0',");
		sql.append(" '����') shztmc");
		sql.append(" from xg_zjly_zhszf_drjlb a ");
		sql.append(" left join view_xsxxb e on a.xh = e.xh ");
		sql.append(" left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm ");
		sql.append(" left join xg_zjly_zhszf_jfxmb d on a.jfxmdm = d.jfxmdm where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" )t group by xh,xmmkmc ) group by xh,mkts  ");
		
		return dao.getListNotOut(sql.toString(), inputV);
		
	}

	/** 
	 * @����:ȡ�� xh:�Ʒ���Ŀ����list
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-14 ����11:32:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmtsList(ZhfDrForm t, User user) throws Exception{
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct xh, WM_CONCAT(xmts) over(partition by xh order by xh) xmts from (");
		sql.append(" select xh, concat(jfxmmc,'&'||count(1)) xmts  from (select a.*,");
		sql.append("  case when to_number(length(a.lrr)) > 10 then 'ѧ������'else (select bmmc || '����' from view_fdyxx where zgh = a.lrr) end lb,");
		sql.append(" e.xm,");
		sql.append(" e.xydm,");
		sql.append(" e.xymc,");
		sql.append(" e.zydm,");
		sql.append(" e.zymc,");
		sql.append(" e.bjdm,");
		sql.append(" e.bjmc,");
		sql.append(" e.nj,");
		sql.append(" c.xmmkmc,");
		sql.append(" d.fs,");
		sql.append(" d.jfxmmc,");
		sql.append(" decode(a.shzt,");
		sql.append(" '1',");
		sql.append(" '����','2','�˻�',");
		sql.append(" '0',");
		sql.append(" '����') shztmc");
		sql.append(" from xg_zjly_zhszf_drjlb a ");
		sql.append(" left join view_xsxxb e on a.xh = e.xh ");
		sql.append(" left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm ");
		sql.append(" left join xg_zjly_zhszf_jfxmb d on a.jfxmdm = d.jfxmdm where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" )t group by xh,jfxmmc ) group by xh,xmts  ");
		return dao.getListNotOut(sql.toString(), inputV);
		
	}

	/**
	 * @throws  Exception
	 * @����:ȡ�� xh:����ģ�����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-17 ����02:23:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getMkfsList(ZhfDrForm t, User user) throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct xh, WM_CONCAT(zf) over(partition by xh order by xh) mkfs from (");
		sql.append(" select xh,concat(xmmkmc,'$'|| to_char(sum(xmfs), 'fm9999990.9999')) zf from(");
		sql.append("  select xh,xmmkmc,jfxmmc,case when to_number(fs) > to_number(xdfs) then xdfs else to_char(fs, 'fm9999990.9999') end xmfs from (");
		sql.append(" select a.xh,");
		sql.append(" e.xm,");
		sql.append(" e.xydm,");
		sql.append(" e.xymc,");
		sql.append(" e.zydm,");
		sql.append(" e.zymc,");
		sql.append(" e.bjdm,");
		sql.append(" e.bjmc,");
		sql.append(" e.nj,");
		sql.append(" c.xmmkmc,");
		sql.append(" d.fs,");
		sql.append(" d.jfxmmc,");
		sql.append("  nvl(d.xdfs,'100') xdfs");
		sql.append(" from xg_zjly_zhszf_drjlb a");
		sql.append(" left join view_xsxxb e");
		sql.append(" on a.xh = e.xh");
		sql.append(" left join xg_zjly_zhszf_mkxmb c");
		sql.append(" on a.xmmkdm = c.xmmkdm");
		sql.append(" left join xg_zjly_zhszf_jfxmb d");
		sql.append(" on a.jfxmdm = d.jfxmdm");
		sql.append(" where a.shzt ='1' ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" ))t group by xh,xmmkmc ) group by xh,zf order by xh  ");
		return dao.getListNotOut(sql.toString(), inputV);
		
	}

	/** 
	 * @����:ȡ�� xh:�����״̬ list
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-17 ����06:11:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZshztList(ZhfDrForm t, User user)throws Exception {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from  (select t.*, rank() over(partition by xh order by shztmc asc ) rn from (");
		sql.append(" select xh,shztmc from (select a.xh,decode(a.shzt, '1', '����', '2', '�˻�', '0', '����') shztmc,");
		sql.append(" e.xm,");
		sql.append(" e.xydm,");
		sql.append(" e.xymc,");
		sql.append(" e.zydm,");
		sql.append(" e.zymc,");
		sql.append(" e.bjdm,");
		sql.append(" e.bjmc,");
		sql.append(" e.nj ");
		sql.append(" from xg_zjly_zhszf_drjlb a");
		sql.append(" left join view_xsxxb e");
		sql.append(" on a.xh = e.xh ");
		sql.append(" left join xg_zjly_zhszf_jfxmb d");
		sql.append(" on a.jfxmdm = d.jfxmdm");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" ) group by xh,shztmc ) t ) where rn =1  ");
		return dao.getListNotOut(sql.toString(), inputV);
		
	}
}
