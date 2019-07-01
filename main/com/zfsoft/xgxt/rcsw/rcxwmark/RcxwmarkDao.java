/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-28 ����02:45:49 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import java.io.File;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-10-28 ����02:45:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwmarkDao extends SuperDAOImpl<RcxwmarkForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RcxwmarkForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RcxwmarkForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append(" select t.id rcxwjgid,");
		sql.append(" '' id,");
		sql.append(" t.xh,");
		sql.append(" t3.xm,");
		sql.append(" t3.xb,");
		sql.append(" t3.xydm,");
		sql.append(" t3.nj,");
		sql.append(" t3.zydm,");
		sql.append(" t3.bjdm,");
		sql.append(" t3.bjmc,");
		sql.append(" t.xn,");
		sql.append(" t.rcxwlbdldm,");
		sql.append(" t.rcxwlbdm,");
		sql.append(" t1.rcxwlbdlmc,");
		sql.append(" t2.rcxwlbmc,");
		sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz");
		sql.append(" from xg_rcsw_rcxwjg t");
		sql.append(" left join xg_rcsw_rcxwdbdlb t1");
		sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
		sql.append(" left join xg_rcsw_rcxwlbdmb t2");
		sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
		sql.append(" left join view_xsjbxx t3");
		sql.append(" on t.xh = t3.xh");
		sql.append(" where not exists");
		sql.append(" (select 1 from xg_rcsw_new_shxj_qtjxszb x where t.id = x.rcxwjgid)");
		sql.append(" )t");
		sql.append(" where 1=1");
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
		this.setTableName("xg_rcsw_new_shxj_qtjxszb");
		this.setClass(RcxwmarkForm.class);
		this.setKey("id");
	}
	
	/**
	 *
	 * @����: �Ѵ����ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-31 ����10:54:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYclList(RcxwmarkForm t, User user)
	throws Exception {
        // TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append(" select t.id rcxwjgid,t4.id,");
		sql.append(" t.xh,");
		sql.append(" t3.xm,");
		sql.append(" t3.xb,");
		sql.append(" t3.xydm,");
		sql.append(" t3.nj,");
		sql.append(" t3.zydm,");
		sql.append(" t3.bjdm,");
		sql.append(" t3.bjmc,");
		sql.append(" t4.pjxn xn,");
		sql.append(" t.rcxwlbdldm,");
		sql.append(" t.rcxwlbdm,");
		sql.append(" t1.rcxwlbdlmc,");
		sql.append(" t2.rcxwlbmc,");
		sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz,");
		sql.append(" t4.jxdm,");
		sql.append(" decode(t4.jxdm,'1','רҵ��ѧ��','2','������ѧ��') xmmc");
		sql.append(" from xg_rcsw_new_shxj_qtjxszb t4 ");
		sql.append(" left join xg_rcsw_rcxwjg t ");
		sql.append(" on t.id = t4.rcxwjgid");
		sql.append(" left join  xg_rcsw_rcxwdbdlb t1");
		sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
		sql.append(" left join xg_rcsw_rcxwlbdmb t2");
		sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
		sql.append(" left join view_xsjbxx t3");
		sql.append(" on t.xh = t3.xh");
		
//		sql.append(" left join xg_pjpy_new_pjxmb t5");
//		sql.append(" on t4.jxdm = t5.xmdm");
//		sql.append(" where  exists");
//		sql.append(" (select 1 from xg_rcsw_new_shxj_qtjxszb x where t.id = x.rcxwjgid)");
		sql.append(" ) t");
		sql.append(" where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
     }
	 
	/**
	 * 
	 * @����: ȡ�������Ͷ�дȨ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-1 ����02:20:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param username
	 * @param dyym
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	 public HashMap<String, String> getWriteAble(String username,String dyym){
		 StringBuffer sql = new StringBuffer();
		 sql.append(" select nvl(dxq, '0') dxq,");
		 sql.append(" (select gnmkmc");
		 sql.append(" from gnmkdmb");
		 sql.append(" where gnmkdm = substr(a.gnmkdm, 0, 3)) || '-' ||");
		 sql.append(" (select gnmkmc");
		 sql.append(" from gnmkdmb");
		 sql.append(" where gnmkdm = substr(a.gnmkdm, 0, 5)) || '-' || gnmkmc title,");
		 sql.append(" gnmkmc");
		 sql.append(" from view_yhqx a");
		 sql.append(" where yhm = ?");
		 sql.append(" and dyym = ?");
		 return dao.getMapNotOut(sql.toString(), new String[]{username,dyym});
	 }
	 
	 /**
	  * 
	  * @����:��ȡ�鿴����
	  * @���ߣ�yxy[���ţ�1206]
	  * @���ڣ�2016-11-1 ����06:56:53
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param id
	  * @return
	  * HashMap<String,String> �������� 
	  * @throws
	  */
	 public HashMap<String, String> getCkData(String id){
		 StringBuffer sql = new StringBuffer();
			sql.append(" select t.id rcxwjgid,");
			sql.append(" t.xh,");
			sql.append(" t3.xm,");
			sql.append(" t3.xydm,");
			sql.append(" t3.xymc,");
			sql.append(" t3.nj,");
			sql.append(" t3.zydm,");
			sql.append(" t3.zymc,");
			sql.append(" t3.bjdm,");
			sql.append(" t3.bjmc,");
			sql.append(" t4.pjxn xn,");
			sql.append(" t.rcxwlbdldm,");
			sql.append(" t.rcxwlbdm,");
			sql.append(" t1.rcxwlbdlmc,");
			sql.append(" t2.rcxwlbmc,");
			sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz,");
			sql.append(" t4.jxdm,");
			sql.append(" t4.bz,");
			sql.append(" decode(t4.jxdm,'1','רҵ��ѧ��','2','������ѧ��') xmmc");
			sql.append(" from xg_rcsw_new_shxj_qtjxszb t4 ");
			sql.append(" left join xg_rcsw_rcxwjg t ");
			sql.append(" on t.id = t4.rcxwjgid");
			sql.append(" left join  xg_rcsw_rcxwdbdlb t1");
			sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
			sql.append(" left join xg_rcsw_rcxwlbdmb t2");
			sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
			sql.append(" left join view_xsjbxx t3");
			sql.append(" on t.xh = t3.xh");
			sql.append(" where t4.id = ?");
		 return dao.getMapNotOut(sql.toString(), new String[]{id});
	 }
	 
	 /**
	  * 
	  * @����: �������ñ���
	  * @���ߣ�yxy[���ţ�1206]
	  * @���ڣ�2016-11-1 ����07:00:03
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * boolean �������� 
	  * @throws
	  */
	 public boolean insertData(String[] rcxwjgids,String jxdm,String pjxn,String bz,String czr){
		 StringBuffer sql = new StringBuffer();
		 List<String[]> params = new ArrayList<String[]>();
		 String czsj = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		 for (String strings : rcxwjgids) {
			 params.add(new String[]{strings,jxdm,pjxn,bz,czr,czsj});
		 }
		 sql.append(" insert into xg_rcsw_new_shxj_qtjxszb(rcxwjgid,jxdm,pjxn,bz,czr,czsj) values(?,?,?,?,?,?)");
		 boolean flag = true;
		 try {
			int[] lenArr = dao.runBatch(sql.toString(), params);
			for (int i = 0; i < lenArr.length; i++) {
				flag = (lenArr[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
			return flag;
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return false;
		}
	 }
	 
	 /**
	  * 
	  * @����:��ȡ�鿴����
	  * @���ߣ�yxy[���ţ�1206]
	  * @���ڣ�2016-11-1 ����06:56:53
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param id
	  * @return
	  * HashMap<String,String> �������� 
	  * @throws
	  */
	 public HashMap<String, String> getCkDataWcl(String rcxwjgid){
		 StringBuffer sql = new StringBuffer();
			sql.append(" select t.id rcxwjgid,");
			sql.append(" t.xh,");
			sql.append(" t3.xm,");
			sql.append(" t3.xydm,");
			sql.append(" t3.xymc,");
			sql.append(" t3.nj,");
			sql.append(" t3.zydm,");
			sql.append(" t3.zymc,");
			sql.append(" t3.bjdm,");
			sql.append(" t3.bjmc,");
			sql.append(" t.xn,");
			sql.append(" t.rcxwlbdldm,");
			sql.append(" t.rcxwlbdm,");
			sql.append(" t1.rcxwlbdlmc,");
			sql.append(" t2.rcxwlbmc,");
			sql.append(" decode(t2.rcxwfzlx,'01',to_number(t.fz),-to_number(t.fz)) fz");
			sql.append(" from ");
			sql.append(" xg_rcsw_rcxwjg t ");
			sql.append(" left join  xg_rcsw_rcxwdbdlb t1");
			sql.append(" on t.rcxwlbdldm = t1.rcxwlbdldm");
			sql.append(" left join xg_rcsw_rcxwlbdmb t2");
			sql.append(" on t.rcxwlbdm = t2.rcxwlbdm");
			sql.append(" left join view_xsjbxx t3");
			sql.append(" on t.xh = t3.xh");
			sql.append(" where t.id = ?");
		 return dao.getMapNotOut(sql.toString(), new String[]{rcxwjgid});
	 }
	 
}
