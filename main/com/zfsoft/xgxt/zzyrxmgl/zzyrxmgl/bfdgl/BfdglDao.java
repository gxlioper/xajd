package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.bfdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @��������������������Ŀ����-����������dao
 * @author��Lu.Yao ��1271��
 * @date��2017-10-20 ����11:17:24 
 */
public class BfdglDao extends SuperDAOImpl<ZzyrxmglActionForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZZYR_FDFBB");
		super.setKey("fdfbid");
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZzyrxmglActionForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZzyrxmglActionForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select * from (select t1.fdfbid,t3.fdxxid,t1.fbrxh bfdrxh,t1.fbrxh xh,t1.fdkm,t1.fdsj,t1.fbbz,");
		sql.append(" t2.xm bfdrxm,t2.xymc bfdrxymc,");
		sql.append(" t4.xm fdrxm,t4.xymc fdrxymc,t3.fdrxh,t4.xydm,t4.zydm,t4.bjdm,t4.nj, ");
		sql.append(" case when t3.xscshzt='1' then '1' when t3.xscshzt='0' then '0' else '2'end shzt ");
		sql.append(" from xg_zzyr_fdfbb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fbrxh=t2.xh ");
		sql.append(" left join xg_zzyr_fdxxb t3 on t1.fdfbid=t3.fdfbid ");
		sql.append(" left join view_xsxxb t4 on t3.fdrxh=t4.xh where t1.fblx='1') ");
		sql.append(" union all ");
		sql.append(" select * from (select t1.fdfbid,t1.fdxxid,t1.bfdrxh,t1.bfdrxh xh,t4.fdkm,t4.fdsj,t4.fbbz,t3.xm bfdrxm,");
		sql.append(" t3.xymc bfdrxymc,t2.xm fdrxm,t2.xymc fdrxymc,t1.fdrxh,t2.xydm,t2.zydm,t2.bjdm,t2.nj,");
		sql.append(" case when t1.xscshzt='1' then '1' when t1.xscshzt='0' then '0' else '2' end shzt ");
		sql.append(" from xg_zzyr_fdxxb t1 left join view_xsxxb t2 on t1.fdrxh=t2.xh ");
		sql.append(" left join view_xsxxb t3 on t1.bfdrxh=t3.xh ");
		sql.append(" left join xg_zzyr_fdfbb t4 on t4.fdfbid = t1.fdfbid ");
		sql.append(" where not exists (select 1 from xg_zzyr_fdfbb a where t1.fdfbid = a.fdfbid and a.fbrxh=t1.bfdrxh) )");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		if("stu".equalsIgnoreCase(user.getUserStatus())){
			ArrayList<String> inputVs = new ArrayList<String>();
			if (StringUtils.isNotNull(t.getFdkm())) {
				sql.append(" and fdkm like ?");
				inputVs.add("%"+t.getFdkm()+"%");
			}
			return getPageList(t, sql.toString(), inputVs.toArray(new String[] {}));
		}else{
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			return getPageList(t, sql.toString(), inputV);
		}
	}

	/** 
	 * @description�����������ѧԺ��¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����03:11:02 
	 * @param kfxydm
	 * @param fdfbid
	 * void �������� 
	 * @throws 
	 */
	public boolean insertKfxydm(String kfxydm, String fdfbid) throws Exception{
		return dao.runInsert("xg_zzyr_fdfbxyglb", new String[]{"kfxydm","fdfbid"}, new String[]{kfxydm,fdfbid});
	}

	/** 
	 * @description����ѯ������¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����03:42:42 
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fdfbid,t1.fbrxh,t1.fbrxh xh,t1.fdkm,t1.fdsj,t1.fbbz,t1.xdrs, ");
		sql.append(" t2.xm fbrxm,t2.xymc fbrxymc");
		sql.append(" from xg_zzyr_fdfbb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fbrxh=t2.xh ");
		sql.append(" where t1.fdfbid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getFdfbid() });
	}

	/** 
	 * @description��ɾ������ѧԺ��¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����04:18:04 
	 * @param model
	 * void �������� 
	 * @throws 
	 */
	public int deleteKfxydm(ZzyrxmglActionForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zzyr_fdfbxyglb where fdfbid = ? ");
		return dao.runDelete(sql.toString(), new String[] { model.getFdfbid() });
	}

	/**
	 * @throws Exception  
	 * @description����������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����02:37:47 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean addFdjl(ZzyrxmglActionForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zzyr_fdxxb set xspjjg=? where fdxxid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{model.getXspjjg(),model.getFdxxid()});
	}

	/** 
	 * @description���ж��ܷ�ɾ��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����04:03:32 
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkCandel(String values) {
		String[] id = values.split(",");
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) num from xg_zzyr_fdxxb where fdfbid in (");
		for(int i = 0;i < id.length;i++){
			sql.append("'"+id[i]+"',");
		}
		return "0".equals(dao.getOneRs(sql.toString().substring(0, sql.length()-1)+")", new String[]{}, "num"));
	}

	/** 
	 * @description���ж�-������¼2�����ϲſ�����
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����04:16:04 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkFdjls(ZzyrxmglActionForm model) {
		String sql = "select count(*) num from xg_zzyr_fdjlb where fdxxid = ? and fdlx = ?";
		return Integer.parseInt(dao.getOneRs(sql, new String[]{model.getFdxxid(),model.getFdlx()}, "num")) >= 2;
	}
	
}
