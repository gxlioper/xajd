/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-10-30 ����03:51:36 
 */  
package xgxt.dtjs.tsdzb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ɫ��֧������ģ��
 * @�๦������: (������һ�仰��������������) 
 * @���ߣ�����[����:1282]
 * @ʱ�䣺 2017-10-30 ����03:51:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsdzbDao extends SuperDAOImpl<TsdzbForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsdzbForm t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsdzbForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		String status = user.getUserStatus();
		String userName = user.getUserName();
		StringBuilder sql = new StringBuilder();
		String[] userCondition = new String[]{userName};
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select t.*,(case when length(t.dzbbmmc) > 10 then substr(t.dzbbmmc,0,10) || '...' else t.dzbbmmc end) bmmc from (");
		sql.append(" select t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.shzt,t1.cjr,t1.pf,t1.bz,");
		sql.append(" replace(wm_concat(t4.bjmc),';',',') dzbbmmc,");
		sql.append(" decode(t1.shzt,'1','ͨ��','2','��ͨ��','δ���') shztmc");
		sql.append(" from xg_dtjs_tsdzbb t1");
		sql.append(" left join xg_dtjs_tsdzbglb t2 on t1.dzbid = t2.dzbid");
		sql.append(" left join view_njxyzybj t4 on t2.bjid = t4.bjdm ");
		if("stu".equalsIgnoreCase(status)){
			sql.append(" where t1.cjrlx = 'stu' and t1.cjrzgh = ?");
		}else if ("xy".equalsIgnoreCase(status) || "bzr".equalsIgnoreCase(status) || "fdy".equalsIgnoreCase(status) || "jd".equalsIgnoreCase(status)){
			sql.append(" where t1.cjrlx = 'tea' and t1.cjrzgh = ?");
		}
		sql.append(" group by t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.cjr,t1.shzt,t1.pf,t1.bz order by t1.cjsj desc)  ");
		sql.append(" t where 1 = 1");
		sql.append(searchTj);
		//�����ΪУ���û���ֻ�ܿ����Լ�������֧��
		if("xy".equalsIgnoreCase(status) || "bzr".equalsIgnoreCase(status) || "fdy".equalsIgnoreCase(status) || "jd".equalsIgnoreCase(status) || "stu".equalsIgnoreCase(status)){
			return getPageList(t, sql.toString(), StringUtils.joinStrArr(userCondition,inputV));
		}else{			
			return getPageList(t, sql.toString(), inputV);
		}
	}
	
	/**
	 * @description	�� ��ɫ��֧�����
	 * @author 		�� ������1282��
	 * @date 		��2017-11-1 ����10:40:34
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageListForSh(TsdzbForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());	
		StringBuilder sql = new StringBuilder();
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String shlx = t.getShzt();
		sql.append(" select t.*,(case when length(t.dzbbmmc) > 10 then substr(t.dzbbmmc,0,10) else t.dzbbmmc end) bmmc from (");
		sql.append(" select t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.shzt,t1.cjr,t1.pf,");
		sql.append(" replace(wm_concat(t4.bjmc),';',',') dzbbmmc,");
		sql.append(" decode(t1.shzt,'1','ͨ��','2','��ͨ��','δ���') shztmc");
		sql.append(" from xg_dtjs_tsdzbb t1");
		sql.append(" left join xg_dtjs_tsdzbglb t2 on t1.dzbid = t2.dzbid");
		sql.append(" left join view_njxyzybj t4 on t2.bjid = t4.bjdm ");
		sql.append(" where 1 = 1");
		if (!("dsh").equals(shlx)) {
			sql.append(" and (t1.shzt <> '0')");
		} else {
			sql.append(" and (t1.shzt = '0')");
		}
		sql.append(" group by t1.dzbid,t1.dzbmc,t1.fzr,t1.lxfs,t1.cjsj,t1.cjr,t1.shzt,t1.pf order by t1.cjsj desc)  ");
		sql.append(" t where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(TsdzbForm.class);
		super.setKey("dzbid");
		super.setTableName("xg_dtjs_tsdzbb");		
	}
	
	/**
	 * @throws SQLException  
	 * @����:��������༶����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-30 ����06:49:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdms
	 * @param dzbId
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plInsert(String[] bjdms,String dzbId) throws SQLException{
		String sql = "insert into xg_dtjs_tsdzbglb (dzbid,bjid) values (?,?)";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : bjdms){
			String[] arr = new String[]{dzbId,str};
			list.add(arr);
		}
		return dao.runBatchBoolean(sql, list);			
	}
	
	/**
	 * @throws Exception  
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-30 ����07:01:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteByDzbId(String dzbId) throws Exception{
		String sql = "delete from xg_dtjs_tsdzbglb where dzbid = ?";
		return dao.runUpdate(sql, new String[]{dzbId});
	}
	
	/** 
	 * @����:�����û���¼��ɫ��ȡ�༶�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����09:53:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(User user,String dzbid){
		String stus = user.getUserStatus();
		StringBuilder sb = new StringBuilder();
		String condition = "and not exists (select 1 from xg_dtjs_tsdzbglb d where a.bjdm = d.bjid";
		String[] conditon_notEqual = null;
		if(null != dzbid){
			condition = condition + (" and d.dzbid <> ?");
			conditon_notEqual = new String[]{dzbid};
		}
		condition = condition + (")");
		if(stus.equalsIgnoreCase("stu")){
			sb.append("select a.bjdm,a.bjmc from view_njxyzybj a where a.bjdm = ?");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("jd")){
			sb.append("select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from bzrbbb b where a.bjdm = b.bjdm and b.zgh = ?) or exists (select 1 from fdybjb c where a.bjdm = c.bjdm and c.zgh = ?)");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep(),user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("bzr")){
			sb.append("select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from bzrbbb b where a.bjdm = b.bjdm and b.zgh = ?)");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep(),user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("fdy")){
			sb.append("select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from fdybjb b where a.bjdm = b.bjdm and b.zgh = ?)");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep(),user.getUserDep()},conditon_notEqual));
		}else if(stus.equalsIgnoreCase("xy")){
			sb.append("select a.bjdm,a.bjmc from view_njxyzybj a where a.xydm = ?");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{user.getUserDep()},conditon_notEqual));
		}else{
			sb.append("select a.bjdm,a.bjmc from view_njxyzybj a where 1 = 1");
			sb.append(condition);
			return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(new String[]{},conditon_notEqual));
		}
	}
	
	/**
	 * @throws SQLException  
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����03:47:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<String> getBjListByDzbId(String dzbId) throws SQLException{
		StringBuilder sb = new StringBuilder();
		sb.append("select bjid from xg_dtjs_tsdzbglb where dzbid = ?");
		return dao.getList(sb.toString(), new String[]{dzbId}, "bjid");
	}
	
	/** 
	 * @����:����IDSɾ������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-31 ����07:35:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @throws Exception
	 * void �������� 
	 * @throws 
	 */
	public void delGlByIds(String[] ids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_dtjs_tsdzbglb where dzbid in (");
		for(int i = 0;i<ids.length;i++){
			sb.append("?");
			if(i != ids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		dao.runUpdate(sb.toString(), ids);
	}
	
	
	/**
	 * @description	����ȡ������֧����Ϣ
	 * @author 		�� ������1282��
	 * @date 		��2017-11-1 ����08:54:51
	 * @param dzbid
	 * @return
	 */
	public String getTsdzbXX(String dzbid){
		StringBuilder sb = new StringBuilder();
		sb.append(" select replace(wm_concat(t3.bjmc),';',',') bjmc");
		sb.append(" from xg_dtjs_tsdzbb t1");
		sb.append(" left join xg_dtjs_tsdzbglb t2 on t1.dzbid = t2.dzbid");
		sb.append(" left join view_njxyzybj t3 on t2.bjid = t3.bjdm");
		sb.append(" where t1.dzbid = ?");
		return dao.getOneRs(sb.toString(), new String[]{dzbid}, "bjmc");		
	}
	
	/**
	 * @description	�� ��������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-1 ����03:29:17
	 * @param dzbids
	 * @return
	 * @throws SQLException
	 */
	public boolean cx(String[] dzbids) throws SQLException{
		String sql = "update xg_dtjs_tsdzbb set shzt = '0',shyj = null,pf = null where dzbid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : dzbids){
			String[] strr = new String[]{str};
			list.add(strr);
		}
		return dao.runBatchBoolean(sql, list);
	}
	
	
	/**
	 * @description	����ɫ��֧������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-2 ����10:18:04
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getAllListForTsdzbSh(TsdzbForm t, User user) throws Exception {		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return this.getPageListForSh(t, user);
	}
	
	/**
	 * @description	�� ͳ����ɫ��֧������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-3 ����05:06:48
	 * @return
	 */
	public boolean countTsdzbMc(String dzbmc,String dzbId){
		StringBuilder sb = new StringBuilder();
		String[] dzbmcs = new String[]{dzbmc};
		String[] dzbids = new String[]{};
		sb.append("select count(1) num from xg_dtjs_tsdzbb where dzbmc = ?");
		if(null != dzbId){
			sb.append(" and dzbid <> ?");
			dzbids = new String[]{dzbId};
		}
		String num = dao.getOneRs(sb.toString(), StringUtils.joinStrArr(dzbmcs,dzbids), "num");
		return Integer.valueOf(num) < 1;
	}
	
	/**
	 * @description	�� ��ȡѧԺ�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:11:29
	 * @return
	 */
	public List<HashMap<String,String>> getXyList(String userStatus,String userName){
		StringBuilder sql = new StringBuilder();
		String[] zghs = new String[]{};
		sql.append("select distinct a.xydm,a.xymc from view_njxyzybj a where 1 = 1");
		if(userStatus.equals("xy")){
			sql.append(" and exists (select 1 from fdyxxb b where a.xydm = b.bmdm and b.zgh = ?)");
			zghs = new String[]{userName};
		}
		return dao.getListNotOut(sql.toString(), zghs);
	}
	
	/**
	 * @description	�� ��ȡרҵ�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:12:00
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(String xydm,String userStatus,String userName){
		String[] zghs = new String[]{};
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct a.zydm,a.zymc from view_njxyzybj a where 1 = 1 ");
		if(userStatus.equals("xy")){
			sb.append(" and exists (select 1 from fdyxxb b where a.xydm = b.bmdm and b.zgh = ?)");
			zghs = new String[]{userName};
		}
		String[] xydms = new String[]{};
		if(StringUtils.isNotNull(xydm)){
			sb.append("and a.xydm = ?");
			xydms = new String[]{xydm};
		}
		return dao.getListNotOut(sb.toString(), StringUtils.joinStrArr(zghs,xydms));
	}
	
	/**
	 * @description	�� ��ȡ�༶�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:32:02
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getbjList(String dzbid,String xydm,String zydm,String nj,String userStatus,String userName){
		String[] dzbids = new String[]{}; 
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct a.bjdm,a.bjmc,(case when b.dzbid is not null then '1' else '0' end) zt from view_njxyzybj a left join xg_dtjs_tsdzbglb b on a.bjdm = b.bjid where 1 = 1 ");
		sql.append("and not exists (select 1 from xg_dtjs_tsdzbglb d where a.bjdm = d.bjid");
		if(StringUtils.isNotNull(dzbid)){
			sql.append(" and d.dzbid <> ?");
			dzbids = new String[]{dzbid};
		}
		sql.append(")");
		String[] zghs = new String[]{};
		String[] xyparams = new String[]{};
		String[] zyparams = new String[]{};
		String[] njparams = new String[]{};
		if(userStatus.equals("xy")){
			sql.append(" and exists (select 1 from fdyxxb b where a.xydm = b.bmdm and b.zgh = ?) ");
			zghs = new String[]{userName};
		}
		if(StringUtils.isNotNull(xydm)){
			sql.append("and a.xydm = ? ");
			xyparams = new String[]{xydm};
		}
		if(StringUtils.isNotNull(zydm)){
			sql.append("and a.zydm = ? ");
			zyparams = new String[]{zydm};
		}
		if(StringUtils.isNotNull(nj)){
			sql.append("and a.nj = ? ");
			njparams = new String[]{nj};
		}
		
		return dao.getListNotOut(sql.toString(), StringUtils.joinStrArr(dzbids,zghs,xyparams,zyparams,njparams));
	} 
	
	/**
	 * @description	�� ��ȡ�꼶�б�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-30 ����10:44:13
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getNjList(){
		String sql = "select distinct nj,nj as njmc from view_njxyzybj";
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
}
