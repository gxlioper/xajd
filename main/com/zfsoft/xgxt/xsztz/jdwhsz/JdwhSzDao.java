/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-1 ����08:58:31 
 */  
package com.zfsoft.xgxt.xsztz.jdwhsz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-8-1 ����08:58:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdwhSzDao extends SuperDAOImpl<JdwhSzForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdwhSzForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ��ѯ
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdwhSzForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjOfSztzByUser(user, "t", "sbbmdm", "sbr");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xmjbmc,");
		sql.append(" t2.sskmmc,");
		sql.append(" t3.jdmc,");
		sql.append(" t3.jdid,");
		sql.append(" t3.jdsx,");
		sql.append(" nvl(t4.jdcynum,0) jdcynum,");
		sql.append(" decode(t.csms, '1', '����', '2', '����', t.csms) csmsmc,");
		sql.append(" case when (t.xfrdsqzt in ('0','3') and t.xfrdjgzt = '0') then 'δ�϶�'");
		sql.append(" else '���϶�' end rdzt,");
		sql.append(" t5.xqmc");
		sql.append(" from xg_sztz_xmjg t");
		sql.append(" left join xg_sztz_xmjb t1");
		sql.append(" on t.xmjbdm = t1.xmjbdm");
		sql.append(" left join xg_sztz_sskm t2");
		sql.append(" on t.sskmdm = t2.sskmdm");
		sql.append(" left join xg_sztz_xm_jd t3");
		sql.append(" on t.xmdm = t3.xmdm");
		sql.append(" left join (select count(1) jdcynum, jdid");
		sql.append(" from xg_sztz_xm_jdwh");
		sql.append(" group by jdid) t4");
		sql.append(" on t3.jdid = t4.jdid");
		sql.append(" left join xqdzb t5");
		sql.append(" on t.xq = t5.xqdm");
		sql.append(" ) t where jdcynum >= 0 and jdmc is not null ");
		sql.append(" ");
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
		this.setClass(JdwhSzForm.class);
		this.setTableName("xg_sztz_xm_jdwh");
		this.setKey("jdwhid");
	}
	
	/**
	 * 
	 * @����:������Ŀ�׶�ά��ѡ���Աҳ��Ĳ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����03:00:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmcyList(JdwhSzForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(t.getXhs())){
			xhArr = t.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t.*, ");
		sql.append(" t1.xm, ");
		sql.append(" t1.xb,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t1.nj");
		sql.append(" from xg_sztz_xs_sqjg t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" where t.xmdm = '"+t.getXmdm()+"'");
		if(xhArr.length > 0){
			sql.append(" and t.xh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1");
		sql.append(" ");
	//	sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:������Ŀ�׶�ά��ѡ���Աҳ��Ĳ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����11:35:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmcyList(JdwhSzForm t, User user)
	throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(t.getXhs())){
			xhArr = t.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t.*, t.dzxh xh,");
		sql.append(" t1.xm, ");
		sql.append(" t1.xb,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t1.nj");
		sql.append(" from xg_sztz_ttxmjg t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.dzxh = t1.xh");
		sql.append(" left join (select count(1) ttcys,ttsqid ttjgid   from xg_sztz_ttcy  group by ttsqid  ) t2");
		sql.append("  on t.ttjgid = t2.ttjgid");
		sql.append(" where t.xmdm = '"+t.getXmdm()+"'");
		if(xhArr.length > 0){
			sql.append(" and t.dzxh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1");
		sql.append(" ");
//		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡ������Ŀ��ά����׶�ά���������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����01:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmYwh(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.*,t1.xh, t1.jgid,t2.xm");
		sql.append(" from xg_sztz_xm_jdwh t");
		sql.append(" left join xg_sztz_xs_sqjg t1");
		sql.append(" on t.jdcy = t1.jgid");	
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" ");
		sql.append(" where t.jdid = ?");
		paralist.add(t.getJdid());
		sql.append(")a");
		sql.append(" where 1=1  ");
		if(StringUtils.isNotNull(t.getXh())){
			sql.append(" and( a.xh like ?");
			paralist.add("%"+t.getXh()+"%");
			sql.append(" or a.xm like ?)");
			paralist.add("%"+t.getXh()+"%");
		}
	//	sql.append(searchTjByUser);
		sql.append(" order by xh asc ");
	    return getPageList(t, sql.toString(), paralist.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡ������Ŀ��ά����׶�ά���������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����01:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmYwh(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.*, t1.tdmc, t1.dzxh xh,t1.ttjgid, t2.xm");
		sql.append(" from xg_sztz_xm_jdwh t");
		sql.append(" left join xg_sztz_ttxmjg t1");
		sql.append(" on t.jdcy = t1.ttjgid");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.dzxh = t2.xh");
		sql.append(" where t.jdid =?");
		paralist.add(t.getJdid());
		sql.append(")a");
		sql.append(" where 1=1  ");
		if(StringUtils.isNotNull(t.getXh())){
			sql.append(" and( a.xh like ?");
			paralist.add("%"+t.getXh()+"%");
			sql.append(" or a.xm like ?)");
			paralist.add("%"+t.getXh()+"%");
		}
//		sql.append(searchTjByUser);
		sql.append(" order by tdmc asc");
	    return getPageList(t, sql.toString(), paralist.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ��Ŀ�׶�ά����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����04:06:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmJdwhxx(String jdid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_sztz_xm_jd where jdid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{jdid});
	}
	
	/**
	 * 
	 * @����:��ȡѧ��ƴ���ַ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����04:57:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXhs(String jdid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(xh), ';', ',') xhs");
		sql.append(" from xg_sztz_xs_sqjg t");
		sql.append(" where t.jgid in (select jdcy from xg_sztz_xm_jdwh where jdid = ?)");
		return dao.getOneRs(sql.toString(), new String[]{jdid}, "xhs");
	}
	

	/**
	 * @throws SQLException 
	 * 
	 * @����:������ӽ׶����ó�Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����09:50:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJdszCy(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_sztz_xm_jdwh(xmdm,jdid,jdcy,jbf,jdsj) values(?,?,?,?,?)");
		int[] len = dao.runBatch(sql.toString(), params);
		return len != null && len.length > 0 ? true:false;
	}
	
	/**
	 * 
	 * @����:��ȡѧ��ƴ���ַ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����04:57:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDzXhs(String jdid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(dzxh), ';', ',') xhs");
		sql.append(" from xg_sztz_ttxmjg t");
		sql.append(" where t.ttjgid in (select jdcy from xg_sztz_xm_jdwh where jdid = ?)");
		return dao.getOneRs(sql.toString(), new String[]{jdid}, "xhs");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡ��Ŀ����list����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����03:01:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmmcList(User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t.*, nvl(t2.num, 0) num from xg_sztz_xmjg t ");
		sql.append(" left join (select count(1) num, xmdm");
		sql.append(" from xg_sztz_xm_jd");
		sql.append(" group by xmdm) t2");
		sql.append(" on t.xmdm = t2.xmdm");
		sql.append(" where t.xfrdsqzt in ('0','3') and t.xfrdjgzt = '0'");
		sql.append(" ");
		sql.append(" ) t where num>0");
		
		String searchTjByUser = SearchService.getSearchTjOfSztzByUser(user, "t", "sbbmdm", "sbr");
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡ��Ŀ����MAP����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����03:01:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXmmcMap(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_sztz_xmjg t where t.xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * 
	 * @����:��ȡ�׶���Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����05:25:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getJdList(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("  select t1.* from xg_sztz_xmjg t");
		sql.append("  left join xg_sztz_xm_jd t1");
		sql.append("  on t.xmdm = t1.xmdm");
		sql.append("  where t.xmdm = ?");
		sql.append(" ");
		List<HashMap<String, String>> list = dao.getListNotOut(sql.toString(), new String[]{xmdm});
		return list;
		
	}
	
	/**
	 * 
	 * @����:��鵼��ĸ�����Ŀ������Ϣ�Ƿ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����06:38:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public HashMap<String, String> checkGrIsExists(String xmdm,String xh){
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> resultmap = new HashMap<String, String>();
		sql.append(" select * from xg_sztz_xs_sqjg t where t.xmdm = ? and t.xh = ?");
	    List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), new String[]{xmdm,xh});
	    String flag = result != null && result.size() == 1 ? "true":"false";
	    resultmap.put("flag", flag);
	    if("true".equals(flag)){
	    	resultmap.put("jdcy",result.get(0).get("jgid") );
	    }else{
	    	resultmap.put("jdcy","" );
	    }
	    
	    return resultmap;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����06:53:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> checkTtIsExists(String xmdm,String tdmc){
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> resultmap = new HashMap<String, String>();
		sql.append(" select * from xg_sztz_ttxmjg t where t.xmdm = ? and t.tdmc = ?");
	    List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), new String[]{xmdm,tdmc});
	    String flag = result != null && result.size() == 1 ? "true":"false";
	    resultmap.put("flag", flag);
	    if("true".equals(flag)){
	    	resultmap.put("jdcy",result.get(0).get("ttjgid") );
	    }else{
	    	resultmap.put("jdcy","" );
	    }
	    
	    return resultmap;
	}
	
	/**
	 * 
	 * @����: ��֤����Լ��(xmdm, jdid, jdcy)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����07:32:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param jdid
	 * @param jdcy
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean checkZjIsRepeat(String xmdm ,String jdid,String jdcy){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_sztz_xm_jdwh t where t.xmdm = ? and t.jdid = ? and t.jdcy = ?");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		String num = dao.getOneRs(sql.toString(), new String[]{xmdm,jdid,jdcy}, "num");
		return StringUtils.isNotNull(num) && Integer.parseInt(num) == 0 ? true : false;
	}
	
	/**
	 * ��֤��ϵ����ݲ������ݿ�
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-3 ����09:29:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_sztz_xm_jdwh( ");
		sql.append(" xmdm, ");
		sql.append(" jdid,");
		sql.append(" jdcy,");
		sql.append(" jbf,");
		sql.append(" hdsc,");
		sql.append(" bz,");
		sql.append(" jdsj");
		sql.append(" )values(?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
 		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ѧ��������Ŀ��ѯ��ֻ��ѧ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-3 ����10:46:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXsGrxmCx(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.jbf,");
		sql.append(" t1.jdsj,");
		sql.append(" t2.jdmc,");
	    sql.append(" t3.jgid xsjgid,");
		sql.append(" t3.xh,");
		sql.append(" t4.xm,");
		sql.append(" t4.nj,");
		sql.append(" t4.xydm,");
		sql.append(" t4.xymc,");
		sql.append(" t4.zydm,");
		sql.append(" t4.zymc,");
		sql.append(" t4.bjdm,");
		sql.append(" t4.bjmc,");
		sql.append(" t5.xqmc,");
		sql.append(" t4.xb from xg_sztz_xmjg t");
		sql.append(" join xg_sztz_xm_jdwh t1");
		sql.append(" on t.xmdm = t1.xmdm");
		sql.append(" left join xg_sztz_xm_jd t2");
		sql.append(" on t1.jdid = t2.jdid ");
		sql.append(" left join xg_sztz_xs_sqjg t3");
		sql.append(" on t1.jdcy = t3.jgid ");
		sql.append(" left join view_xsjbxx t4");
		sql.append(" on t3.xh = t4.xh");
		sql.append(" left join xqdzb t5");
		sql.append(" on t.xq = t5.xqdm");
		sql.append(" where t.csms = '1' ");
		sql.append(") t ");
		sql.append(" where t.jdmc is not null and t.xm is not null ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ѧ��������Ŀ��ѯ��ֻ��ѧ��,�ݶ�ֻ�жӳ����Կ��ĵ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-3 ����10:48:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsTtxmCx(JdwhSzForm t, User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.jbf,");
		sql.append(" t1.jdsj,");
		sql.append(" t2.jdmc,");
		sql.append(" t3.ttjgid,");
		sql.append(" t3.tdmc,");
		sql.append(" t3.dzxh xh,");
		sql.append(" t4.xm,");
		sql.append(" t4.nj,");
		sql.append(" t4.xydm,");
		sql.append(" t4.xymc,");
		sql.append(" t4.zydm,");
		sql.append(" t4.zymc,");
		sql.append(" t4.bjdm,");
		sql.append(" t4.bjmc,");
		sql.append(" t4.xb,");
		sql.append(" t6.xqmc,");
		sql.append(" nvl(t5.cys,0) cys");
		sql.append("  from xg_sztz_xmjg t");
		sql.append(" join xg_sztz_xm_jdwh t1");
		sql.append(" on t.xmdm = t1.xmdm");
		sql.append(" left join xg_sztz_xm_jd t2")	;
		sql.append(" on t1.jdid = t2.jdid");
		sql.append(" left join xg_sztz_ttxmjg t3");
		sql.append(" on t1.jdcy = t3.ttjgid ");
		sql.append(" left join view_xsjbxx t4");
		sql.append(" on t3.dzxh = t4.xh");
		sql.append(" left join (select count(1) cys,jdid from xg_sztz_xm_jdwh group by jdid ) t5");
		sql.append(" on t1.jdid = t5.jdid");
		sql.append(" left join xqdzb t6 ");
		sql.append(" on t.xq = t6.xqdm");
		sql.append(" where t.csms = '2' ");
		sql.append(") t ");
		sql.append(" where t.jdmc is not null and t.tdmc is not null");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
		
	}
 	
}
