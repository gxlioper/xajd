/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-22 ����02:45:33 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcxm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��-
 * @�๦������: �۲���Ŀ���� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-22 ����02:45:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcxmDao extends SuperDAOImpl<ZcxmModel> {

	protected void setTableInfo() {
		super.setTableName("xg_zhcp_zcxmb");
		super.setKey("xmdm");
		super.setClass(ZcxmModel.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(ZcxmModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(ZcxmModel t, User user)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�۲���Ŀ�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����04:32:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getZcxmList(String xn,String xq){
		
		String sql = "select xmdm,xmmc,fjdm,xmlx,qzbl,mrfs,jktb,nvl(zdfs,' ') zdfs,zxfs from xg_zhcp_zcxmb where xn=? and xq=? order by xssx,jktb nulls last,xmmc";
		
		return dao.getListNotOut(sql, new String[]{xn,xq});
	}
	
	public List<HashMap<String,String>> getZcxmList2(String xn,String xq){
		
		String sql = "select xmdm,xmmc,fjdm,xmlx,qzbl,mrfs,jktb,nvl(zdfs,' ') zdfs,zxfs from xg_zhcp_zcxmb where xn=? and xq=? and (fjdm='N' or fjdm =(select xmdm from xg_zhcp_zcxmb t where t.xn=? and t.xq=? and fjdm='N'))";
		
		return dao.getListNotOut(sql, new String[]{xn,xq,xn,xq});
	}
	
	
	/**
	 * 
	 * @����:ɾ��ѧ���۲���Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-6 ����04:19:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXnZcxm(String xn) throws Exception {
		String sql = "delete from xg_zhcp_zcxmb where xq = 'on' and xn = ? ";
		return dao.runUpdate(sql, new String[]{xn});
	}
	
	
	
	
	
	/**
	 * 
	 * @����: ������ʼ���۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����05:01:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean ��������
	 */
	public boolean initZcxmList(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_zhcp_zcxmb(xmdm,xn,xq,xmmc,fjdm,xmlx,qzbl,mrfs,jktb,zdfs,zxfs) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	
	/**
	 * 
	 * @����: ɾ���۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����10:40:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * int ��������
	 * @throws Exception 
	 */
	public int delZcxm(String xmdm) throws Exception{
		
		String sql = "delete from xg_zhcp_zcxmb where xmdm=? or fjdm=?";
		
		return dao.runDelete(sql, new String[]{xmdm,xmdm});
	}

	
	/**
	 * 
	 * @����: ��ѯϵͳ�е��۲���Ŀ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����02:31:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getZcxmCount() throws SQLException{
		
		String sql = "select count(1) num from xg_zhcp_zcxmb";
		
		return dao.getOneRsint(sql);
	}
		
	/**
	 * 
	 * @����: ��ѯ������ڵ��۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����02:45:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param isXqzc true ѧ���۲�  false ѧ���۲�
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZjzqZcxm(boolean isXqzc){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb where substr(xn,6,9)||xq=(select max(substr(xn,6,9)||xq) from xg_zhcp_zcxmb");
		//ѧ���۲�
		if (isXqzc){
			sql.append(" where xq <> 'on' ");
		}
		sql.append(" )");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����:����ѧ���ȡ���ѧ���۲���Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-6 ����04:08:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXnzcxmForXn(String xn){
		
		String sql = "select * from xg_zhcp_zcxmb where xn||xq = (select ?||max(xq) from xg_zhcp_zcxmb where xq <> 'on' and xn = ?)";
		
		return dao.getListNotOut(sql, new String[]{xn,xn});
	}
	
	
	
	/**
	 * 
	 * @����: ��ѯ����¼���۲�ֵ��۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����11:47:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by xssx,fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����: ��ѯ����¼���۲�ֵ��۲���Ŀ  ��֧�ֶ�ѡ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����11:47:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getAllowEditZcfxmByNj(List<HashMap<String,String>> bjxxMap, ZcfsModel model, User user){
		
		//�û�Ȩ��
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl) qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select nj from view_njxyzybj t1 where 1=1 ");
		
		if(getIsblank(bjxxMap,model)){
			sql.append(" and t1.bjdm in (");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(")");
		}
		sql.append(searchTjByUser);
		sql.append(" ) group by xmdm ) t2 where t1.xmdm=t2.xmdm and t2.qzbl='0')");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @����: ��ѯ����¼���۲�ֵ��۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����11:47:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getAllowEditZcfxmByXy(String bjdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t2 where t1.xmdm=t2.xmdm and ");
		sql.append("t2.bmdm=(select xydm from view_njxyzybj where bjdm=? and rownum=1) and t2.qzbl='0')");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), new String[]{bjdm});
	}
	
	/**
	 * 
	 * @����: ��ѯ����¼���۲�ֵ��۲���Ŀ	(֧�ֶ�ѡ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����11:47:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getAllowEditZcfxmByXy(List<HashMap<String,String>> bjxxMap, ZcfsModel model, User user){
		
		//�û�Ȩ��
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t3", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm");
		sql.append(") and jktb is null and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl)qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select xydm from view_njxyzybj t3 where 1=1 ");
		
		if(getIsblank(bjxxMap, model)){
			sql.append(" and t3.bjdm in (");
			for (int i = 0; i < bjxxMap.size(); i++) {
				if(0==i){
					sql.append("?");
				}else{
					sql.append(",?");
				}
				params.add(bjxxMap.get(i).get("bjdm"));
			}
			sql.append(") ");
		}
		sql.append(searchTjByUser);
		sql.append(" ) group by xmdm) t2 where t1.xmdm=t2.xmdm and t2.qzbl='0')");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @����: ���Ӽ���Ŀ���۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-26 ����11:42:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getNoChildZcfxm(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(" order by xssx,jktb nulls last,fjdm,xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����: ��ѧԺ���༶��ѯ��¼��������۲���Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-4-1 ����10:27:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByXy(String bjdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t2 where t1.xmdm=t2.xmdm and ");
		sql.append("t2.bmdm=(select xydm from view_njxyzybj where bjdm=? and rownum=1) and t2.qzbl='0')");
		sql.append("order by jktb nulls last,fjdm,xmmc  ");
		return dao.getListNotOut(sql.toString(), new String[]{bjdm});
	}
	
	
	/**
	 * 
	 * @����: ��ѧԺ���༶��ѯ��¼��������۲���Ŀs
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-4-1 ����10:27:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByXy(List<HashMap<String, String>> bjList, ZcfsModel model, User user)throws Exception{
		
		//�û�Ȩ��
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl)qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select xydm from (select a.xn,a.xq,a.tjzt,b.* from xg_zhcp_fstjjlb a left join view_njxyzybj b on a.bjdm=b.bjdm ");
		sql.append("where a.xn||a.xq = (select xn || xq from xg_pjpy_new_csszb where rownum = 1)) where 1=1 ");
		if(null!=model.getId()&&0!=model.getId().length()){
			
			sql.append(" and bjdm in ('");
			
			for (int i = 0; i < bjList.size(); i++) {
				if(i==0){
					sql.append(bjList.get(i).get("bjdm"));
				}else{
					sql.append("','"+bjList.get(i).get("bjdm"));
				}
			}
			sql.append("')");
		}
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		sql.append(" ) group by xmdm) t2 where t1.xmdm=t2.xmdm and t2.qzbl = '0') order by xssx,jktb nulls last,fjdm,xmmc  ");
		return dao.getListNotOut(sql.toString(), inputV);
		
	}
	
	
	/**
	 * 
	 * @����: ���꼶���༶��ѯ��¼��������۲���Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-4-1 ����10:27:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByNj(String bjdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t2 where t1.xmdm=t2.xmdm and ");
		sql.append("t2.bmdm=(select nj from view_njxyzybj where bjdm=? and rownum=1) and t2.qzbl='0')");
		sql.append("order by jktb nulls last,fjdm,xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{bjdm});
	}
	
	
	/**
	 * 
	 * @����: ���꼶���༶��ѯ��¼��������۲���Ŀ 
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-4-1 ����10:27:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditZcxmByNj(List<HashMap<String, String>> bjList, ZcfsModel model, User user) throws Exception{
		
		//�û�Ȩ��
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and not exists (select 1 from (select xmdm,sum(qzbl) qzbl from xg_zhcp_zcblb t2 where ");
		sql.append("t2.bmdm in (select a.nj from (select a.id,a.xn,a.xq,a.cpzdm,a.tjr,a.tjsj,a.tjzt,b.* from xg_zhcp_fstjjlb a left join view_njxyzybj b on a.bjdm=b.bjdm ) a ");
		sql.append("where a.xn||a.xq = (select xn || xq from xg_pjpy_new_csszb where rownum = 1) ");
		
		//���IDΪ�վ�˵����ȫѡ�������༶��֤
		if(null!=model.getId()&&0!=model.getId().length()){
			sql.append(" and a.bjdm in ('");
			
			for (int i = 0; i < bjList.size(); i++) {
				if(i==0){
					sql.append(bjList.get(i).get("bjdm"));
				}else{
					sql.append("','"+bjList.get(i).get("bjdm"));
				}
			}
			sql.append("')");
			
		}
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		sql.append(" ) group by xmdm ) t2 where t1.xmdm=t2.xmdm and t2.qzbl = '0') order by xssx,jktb nulls last,fjdm,xmmc ");
		
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	
	
	
	
	/**
	 * 
	 * @����:��ѯǰ�����۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����02:47:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getFirstAndSecondZcxm(){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zhcp_zcxmb t1 where (fjdm='N' ");
		sql.append(" or exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" and t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) order by xmlx desc");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), new String[]{});
		if ("12861".equals(Base.xxdm)){
			rs.add(this.getGxhxm_cur_xnxq_12861());
		}
		return rs;
	}
	
	
	public List<HashMap<String,String>> getFirstAndSecondZcxm(ZcfsModel t){

		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();

		sql.append(" select distinct xmmc,xmlx from xg_zhcp_zcxmb t1 where ( ");
		if(!"10335".equals(Base.xxdm)){
			sql.append(" fjdm='N' or ");
		}
		sql.append(" exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N')) and ");
		sql.append(getXnxqSql(t, params));
		sql.append(" order by xmlx desc ");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
		if ("12861".equals(Base.xxdm)){
			rs.add(this.getGxhxm_searchTj_xnxq_12861(t));
		}
		return rs;
	}
	
	/** 
	 * �㽭����ְҵ����ѧԺ ���Ի���Ŀ���߼���ѯ��
	 */
	public HashMap<String, String> getGxhxm_searchTj_xnxq_12861(ZcfsModel t){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zhcp_zcxmb t1 where t1.xmmc like '%��Ȩ%' ");
		sql.append("and ");
		sql.append(getXnxqSql(t, params));
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	/** 
	 * �㽭����ְҵ����ѧԺ ���Ի���Ŀ���ֶ�����ѧ�ꡢѧ�ڣ�
	 */
	public HashMap<String, String> getGxhxm_xnxq_12861(String xn, String xq){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zhcp_zcxmb t1 where t1.xmmc like '%��Ȩ%' ");
		sql.append("and t1.xn||t1.xq=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xn+xq});
	}
	/** 
	 * �㽭����ְҵ����ѧԺ ���Ի���Ŀ����ǰѧ��ѧ�ڣ�
	 */
	public HashMap<String, String> getGxhxm_cur_xnxq_12861(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zhcp_zcxmb t1 where t1.xmmc like '%��Ȩ%' ");
		sql.append("and t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String,String>> getZcxm_12861(ZcfsModel t){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		// ============ �۲���Ŀ���ڶ��������� sql begin ===============
		sql.append(" select * from ( ");
		sql.append(" select a.* from ( ");
		sql.append(" select a.xmdm, ");
		sql.append(" decode(b.xmmc, 'N1', '999', a.xssx) xssx, ");
		sql.append(" a.xmmc xmmc, ");
		sql.append(" b.xmmc zfflag, ");
		sql.append(" a.xmlx, ");
		sql.append(" a.qzbl, ");
		sql.append(" decode(b.xmmc, 'N1', a.xssx, b.xssx) fxssx ");
		sql.append(" from xg_zhcp_zcxmb a ");
		sql.append(" left join ( ");
		sql.append(" select t1.xmdm,t1.xssx,decode(t1.fjdm,'N','N1',t1.xmmc) xmmc,t1.qzbl from xg_zhcp_zcxmb t1 where (t1.fjdm='N' or  ");
		sql.append(" exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N')) and ");
		sql.append(getXnxqSql(t, params));
		sql.append(" ) b on a.fjdm = b.xmdm ");
		sql.append(" where b.xmdm is not null and a.xmmc not like '%��Ȩ%' ");
		sql.append(" ) a order by a.fxssx, a.xssx ");
		sql.append(" )  ");
		// ============ �۲���Ŀ���ڶ��������� sql end ===============
		sql.append(" union all ");
		// ============ �۲���Ŀ����һ���� sql begin ===============
		sql.append(" select xmdm,xssx,xmmc,'N0' zfflag,'1' xmlx,qzbl,xssx fxssx from xg_zhcp_zcxmb t1 where t1.fjdm='N' and ");
		sql.append(getXnxqSql(t, params));
		// ============ �۲���Ŀ����һ���� sql end ===============
		return dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	public String getXnxqSql(ZcfsModel t, List<String> params){
		StringBuilder xnxqSql = new StringBuilder();
		if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
			if(null==t.getSearchModel().getSearch_tj_xq()||""==t.getSearchModel().getSearch_tj_xq()[0]){
				xnxqSql.append("t1.xn in ( ");
			}else{
				xnxqSql.append("t1.xn||t1.xq in ( ");
			}
		}else{
			xnxqSql.append("t1.xn||t1.xq in ( ");
		}
		for(int i=0; i< t.getSearchModel().getSearch_tj_xn().length; i++){
			if(StringUtils.isNull(t.getZczq())||t.getZczq().equals("xq")){
				//���ѧ��Ϊ�վͲ�ƴ��ѧ��
				if(null==t.getSearchModel().getSearch_tj_xq()){
					if(i==0){
						xnxqSql.append("?");
					}else{
						xnxqSql.append(",?");
					}
					params.add(t.getSearchModel().getSearch_tj_xn()[i]);
				}else{
					for(int j=0; j<t.getSearchModel().getSearch_tj_xq().length;j++){
						if(i==0&&j==0){
							xnxqSql.append("?");
						}else{
							xnxqSql.append(",?");
						}
						params.add(t.getSearchModel().getSearch_tj_xn()[i]+t.getSearchModel().getSearch_tj_xq()[j]);
					}
				}
				
			}else{
				if(i==0){
					xnxqSql.append("?");
				}else{
					xnxqSql.append(",?");
				}
				params.add(t.getSearchModel().getSearch_tj_xn()[i]+"on");
				}
 			}
		xnxqSql.append(" ) ");
		return xnxqSql.toString();
	}
	
	public List<HashMap<String,String>> getFirstAndSecondZcxm(String xn, String xq){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zhcp_zcxmb t1 where (fjdm='N' ");
		sql.append(" or exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" and t1.xn||t1.xq='"+xn+xq+"' order by xmlx desc");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), new String[]{});
		if ("12861".equals(Base.xxdm)){
			rs.add(this.getGxhxm_xnxq_12861(xn, xq));
		}
		return rs;
	}
	
	
	/**
	 * 
	 * @����: �۲�������Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-21 ����10:01:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZhcpTjxm(){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xmdm dm,t1.xn, t1.xn||'ѧ��'||t3.xqmc||t1.xmmc mc ");
		sql.append(" from xg_zhcp_zcxmb t1 left join xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(" where (t1.fjdm='N' or exists (select 1 from xg_zhcp_zcxmb t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" order by t1.xn desc,t3.xqmc desc  ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @����: ��ѯ��ǰ���ڵ��ܷ���Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����11:54:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentZfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where fjdm = 'N' and exists (");
		sql.append("select 1 from xg_pjpy_new_csszb t2 where t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(")");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	
	
	/**
	 * 
	 * @����:���꼶�����۲����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����09:48:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertZcblByNj(ZcxmModel t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct ?,nj,? from view_njxyzybj ");
		
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm(),t.getQzbl()});
	}
	
	
	/**
	 * 
	 * @����:��ѧԺ�����۲����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����09:48:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertZcblByXy(ZcxmModel t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct ?,xydm,? from view_njxyzybj ");
		
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm(),t.getQzbl()});
	}
	
	
	/**
	 * 
	 * @����: ����Ŀ�����ѯ�޸Ĺ���ϸ����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����10:13:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getConutByUpdate(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) c from xg_zhcp_zcblb where ");
		sql.append("qzbl <> (select qzbl from xg_zhcp_zcxmb where xmdm=?)  and xmdm=?");
		return dao.getOneRs(sql.toString(), new String[]{xmdm,xmdm}, "c");
	}
	
	
	/**
	 * @throws Exception 
	 * @����: ���¸��꼶��Ժϵ���õ���ϸ�۲����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����10:51:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 */
	public boolean updateXxbl(ZcxmModel t) throws Exception{
		
		String sql = "update xg_zhcp_zcblb set qzbl = ? where xmdm=?";
		
		return dao.runUpdate(sql, new String[]{t.getQzbl(),t.getXmdm()});
	}
	
	
	
	/**
	 * 
	 * @����: ɾ���۲���ϸ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����10:57:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXxbl(String xmdm) throws Exception{
		
		String sql = "delete from xg_zhcp_zcblb where xmdm=?";
		
		return dao.runDelete(sql, new String[]{xmdm}) > 0;
	}
	
	
	
	/**
	 * 
	 * @����: ���꼶��ʼ���۲���ϸ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����11:09:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean initXxblByNj() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct t1.xmdm,t2.nj,t1.qzbl from ");
		sql.append("xg_zhcp_zcxmb t1,view_njxyzybj t2 where t1.fjdm <> 'N' ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t3 where t1.xmdm=t3.xmdm)");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * 
	 * @����: ��Ժϵ��ʼ���۲���ϸ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����11:10:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean initXxblByXy() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_zhcp_zcblb(xmdm,bmdm,qzbl) ");
		sql.append("select distinct t1.xmdm,t2.xydm,t1.qzbl from ");
		sql.append("xg_zhcp_zcxmb t1,view_njxyzybj t2 where t1.fjdm <> 'N' ");
		sql.append("and not exists (select 1 from xg_zhcp_zcblb t3 where t1.xmdm=t3.xmdm)");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����: ��ѯ���õ��꼶��ϸ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����02:02:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCountXxblByNj(String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from xg_zhcp_zcblb t1 ");
		sql.append("left join xg_zhcp_zcxmb t2 on t1.xmdm = t2.xmdm where ");
		sql.append("exists (select 1 from view_njxyzybj t3 where t1.bmdm = t3.nj) ");
		sql.append("and t2.xn=? and xq=?");
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq}, "count");
	}
	
	
	/**
	 * 
	 * @����: ��ѯ���õ�ѧԺ��ϸ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����02:02:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCountXxblByXy(String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from xg_zhcp_zcblb t1 ");
		sql.append("left join xg_zhcp_zcxmb t2 on t1.xmdm = t2.xmdm where ");
		sql.append("exists (select 1 from view_njxyzybj t3 where t1.bmdm = t3.xydm) ");
		sql.append("and t2.xn=? and t2.xq=?");
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq}, "count");
	}
	
	
	
	/**
	 * 
	 * @����: ɾ��ѧ�ꡢѧ������ϸ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����02:09:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXxbl(String xn,String xq) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_zhcp_zcblb t1 where exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where ");
		sql.append("t1.xmdm = t2.xmdm and t2.xn=? and t2.xq=?");
		sql.append(")");
		
		return dao.runDelete(sql.toString(), new String[]{xn,xq}) > 0;
	}
	
	
	
	/**
	 * 
	 * @����: ��ѯ�ɵ�����ϸ�������۲���Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����03:38:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcxmListByXxbl(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.xmdm,t1.xmmc,t1.fjdm,t2.fjdm ffjdm,t1.qzbl,");
		sql.append("'zcxm'|| rownum name from xg_zhcp_zcxmb t1 ");
		sql.append("left join xg_zhcp_zcxmb t2 on t1.fjdm = t2.xmdm ");
		sql.append("where t1.fjdm <> 'N' and t1.xmlx <> '3' ");
		sql.append("and t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb)");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String,String>> getSzyfList(){
		StringBuilder sql = new StringBuilder();
		 sql.append("select case when a.xq = '01' then (substr(b.xn, 1, 4)) || yf else (substr(b.xn, 6, 9)) || yf");
         sql.append(" end szyf, case  when a.xq = '01' then (substr(b.xn, 1, 4)) || '��' || yf || '��' else");
         sql.append(" (substr(b.xn, 6, 9)) || '��' || yf || '��' end szyfmc from xg_pjpy_new_yfpz a, xg_pjpy_new_csszb b");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	


	/**
	 * 
	 * @����: ���꼶��ѯ��ϸ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-31 ����10:34:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param zcxmList
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXxblListByNj(ZcxmModel t, List<HashMap<String,Object>> zcxmList) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.tjzt from (select nj bmdm,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("sum(");
			sql.append(zcxmList.get(i).get("name"));
			sql.append(") ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",sum(");
				sql.append(sjxmList.get(m).get("name"));
				sql.append(") ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from (select distinct t2.nj,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("case when t1.xmdm='");
			sql.append(zcxmList.get(i).get("xmdm"));
			sql.append("' then t1.qzbl else '0' end ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",case when t1.xmdm='");
				sql.append(sjxmList.get(m).get("xmdm"));
				sql.append("' then t1.qzbl else '0' end ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from xg_zhcp_zcblb t1");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.nj");
		sql.append(" ) group by nj ) t1 left join (");
		sql.append(" select max(tjzt) tjzt,nj from (");
		sql.append(" select t2.nj,case when t1.tjzt='1' then 1 else 0 end tjzt from xg_zhcp_fstjjlb t1");
		sql.append(" left join view_njxyzybj t2 on t1.bjdm=t2.bjdm");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb)");
		sql.append(" ) group by nj ) t2 on t1.bmdm = t2.nj");
		
		return super.getPageList(t, sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @����: ��ѧԺ��ѯ��ϸ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-31 ����10:36:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param zcxmList
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXxblListByXy(ZcxmModel t, List<HashMap<String,Object>> zcxmList) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.tjzt from (select xydm bmdm,xymc,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("sum(");
			sql.append(zcxmList.get(i).get("name"));
			sql.append(") ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",sum(");
				sql.append(sjxmList.get(m).get("name"));
				sql.append(") ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from (select distinct t2.xymc,t2.xydm,");
		
		for (int i = 0 , j = zcxmList.size(); i < j ; i++){
			
			sql.append("case when t1.xmdm='");
			sql.append(zcxmList.get(i).get("xmdm"));
			sql.append("' then t1.qzbl else '0' end ");
			sql.append(zcxmList.get(i).get("name"));
			
			List<HashMap<String,String>> sjxmList = (List<HashMap<String, String>>) zcxmList.get(i).get("sjxmList");
			
			for (int m = 0 , n = sjxmList.size() ; m < n ; m++){
				sql.append(",case when t1.xmdm='");
				sql.append(sjxmList.get(m).get("xmdm"));
				sql.append("' then t1.qzbl else '0' end ");
				sql.append(sjxmList.get(m).get("name"));
			}
			
			if (i != j -1){
				sql.append(",");
			}
		}
		
		sql.append(" from xg_zhcp_zcblb t1");
		sql.append(" left join view_njxyzybj t2 on t1.bmdm = t2.xydm");
		sql.append(" ) where xydm is not null group by xymc,xydm) t1 left join (");
		sql.append(" select max(tjzt) tjzt,xydm from (");
		sql.append(" select t2.xydm,case when t1.tjzt='1' then 1 else 0 end tjzt ");
		sql.append(" from xg_zhcp_fstjjlb t1 ");
		sql.append(" left join view_njxyzybj t2 on t1.bjdm=t2.bjdm ");
		sql.append(" where t1.xn||t1.xq=(select xn||xq from xg_pjpy_new_csszb) ");
		sql.append(" ) group by xydm ) t2 on t1.bmdm = t2.xydm ");
		
		return super.getPageList(t, sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @����: ���꼶��ѧԺ������Ŀ��ϸ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-31 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXxblByBmdm(ZcxmModel t) throws Exception{
		
		String sql = "update xg_zhcp_zcblb set qzbl=? where bmdm=? and xmdm=?";
		return dao.runUpdate(sql, new String[]{t.getQzbl(),t.getBmdm(),t.getXmdm()});
	}

	
	/**
	 * 
	 * @����:��ѯ��ǰ�۲�ѧ�����ֵ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-6 ����04:46:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getMaxZcxq(String xn) throws Exception {
		String sql = "select max(xq) xq from xg_zhcp_zcxmb where xn = ? and xq <> 'on'"	;
		
		return dao.getOneRs(sql, new String[]{xn}, "xq");
	}
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��Ŀ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-6 ����05:34:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getZcxm(String xmdm) throws Exception{
		String sql = "select * from xg_zhcp_zcxmb where xmdm = ?";
			
		return dao.getMapNotOut(sql, new String[]{xmdm});
	} 
	
	
	/**
	 * 
	 * @����:��ѯ�����Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-8 ����11:49:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xmmcExist(ZcxmModel model){
		
		String sql = "select count(1) count from xg_zhcp_zcxmb where xn = ? and xq= ? and xmmc = ? ";
		
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXmmc()}, "count");
		
		return num;
	}
	
	
	/**
	 * 
	 * �ж��Ƿ�Ϊ��
	 * 
	 */
	public Boolean getIsblank(List<HashMap<String, String>> bjxxMap,ZcfsModel model){
		
		boolean isBlank = false;
		
		if(!org.apache.commons.lang.StringUtils.isBlank(model.getId())&&null!=bjxxMap&&0!=bjxxMap.size()){
			isBlank = true;
		}
		
		return isBlank;
	}
	
	public boolean delZcyf(String xn,String xq) throws Exception{
		String sql = "delete from XG_PJPY_NEW_YFSZ where xn = ? and xq= ? ";
		
		return dao.runUpdate(sql, new String[]{xn,xq});
	}
	public boolean insertZcyf(List<String[]> params) throws Exception{
		String sql = "insert into XG_PJPY_NEW_YFSZ values(?,?,?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public List<String> getYszYf(String xn,String xq) throws Exception{
		String sql = "select yf from XG_PJPY_NEW_YFSZ where xn=? and xq=?";
		return dao.getList(sql, new String[]{xn,xq}, "yf");
	}
	
	public List<HashMap<String,String>> getZcYf(String xn,String xq) throws Exception{
		String sql = "select yf zcyf,substr(yf,0,4)||'��'||substr(yf,5,6)||'��' zcyfmc from XG_PJPY_NEW_YFSZ where xn=? and xq=?";
		return dao.getListNotOut(sql, new String[]{xn,xq});
	}
	
	
	/**
	 * 
	 * @����: ��ȡ��ǰ�۲����������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-25 ����02:51:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * List<String> �������� 
	 * @throws
	 */
	
	public List<HashMap<String, String>> getJktbS(String xmdm, String type) throws SQLException{
		
		List<String> params = new ArrayList<String>();
		String[] xmdms = xmdm.split(",");
				
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from xg_zhcp_zcxmb where xmdm in (");
		for (int i = 0; i < xmdms.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmdms[i]);
		}
		
		sql.append(")");
		if(!"12688".equals(type) && !"13431".equals(type))
		sql.append(" and jktb is not null ");
		//�ൺ����ѧԺ���Ի����ൺ�Ƶ����ѧԺ���Ի�
		if(!Base.xxdm.equals("10868") && !Base.xxdm.equals("13011")){			
			if("01".equals(type)){
				sql.append(" and xssx in ('01','02') ");
			} else if("02".equals(type)){
				sql.append(" and xssx in ('03','04','05','06') ");
			}
		}
		sql.append(" order by xssx");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/*
	 * ȡ������Ҫͬ�����۲����
	 */
	public List<HashMap<String,String>> getJktbSHHYAll(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists (");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append(" and jktb is not null ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append(" order by xssx,fjdm,xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

}
