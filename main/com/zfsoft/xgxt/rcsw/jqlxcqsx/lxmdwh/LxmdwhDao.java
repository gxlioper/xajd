/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-24 ����11:25:40 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-24 ����11:25:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxmdwhDao extends SuperDAOImpl<LxmdwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxmdwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select ");
		sql.append(" t.id,");
		sql.append(" t.xmid,");
		sql.append(" t.xh,");
		sql.append(" t.lxqksm,");
		sql.append(" t1.xmmc,");
		sql.append(" t1.lxkssj,");
		sql.append(" t1.lxjssj,");
		sql.append(" t1.lxkssj || ' �� ' || t1.lxjssj qzsj,");
		sql.append(" t1.lxxmsm,");
		sql.append(" t2.XM,");
		sql.append(" t2.BJDM,");
		sql.append(" t2.BJMC,");
		sql.append(" t2.XYDM,");
		sql.append(" t2.XYMC,");
		sql.append(" t2.NJ,");
		sql.append(" t2.XB,");
		sql.append(" t2.ZYDM,");
		sql.append(" t2.ZYMC,");
		sql.append(" t3.qsh,");
		sql.append(" t3.cwh,");
		sql.append(" t3.lddm,");
		sql.append(" t3.ldmc,");
		sql.append(" t3.ldmc || t3.qsh szqs");
		sql.append("  from ");
		sql.append(" xg_cqsx_jqlx_mdwh t");
		sql.append(" left join xg_cqsx_jqlx_xmsz t1");
		sql.append(" on t.xmid = t1.xmid");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t.xh = t2.XH");
		sql.append(" left join view_xg_gygl_new_cwxx t3");
		sql.append(" on  t.xh = t3.xh");
		sql.append(" ) t where 1= 1  ");
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
		this.setClass(LxmdwhForm.class);
		this.setKey("id");
		this.setTableName("xg_cqsx_jqlx_mdwh");
	}
	
	/**
	 * 
	 * @����: ��ȡ��У��Ŀ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:27:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmmcList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,t.lxkssj || t.lxkssj qzsj from xg_cqsx_jqlx_xmsz t ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡ��У��Ŀ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����10:27:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmmcMap(String xmid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,t.lxkssj || t.lxkssj qzsj from xg_cqsx_jqlx_xmsz t where t.xmid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xmid});
	}
	
	/**
	 * 
	 * @����:����ά��ʱ�ж��Ƿ���Ա��棬�ж�����xh,xmidΪΨһ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����11:15:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfCanSave(String[] xhs,String xmid){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num from xg_cqsx_jqlx_mdwh where xh in( ");
		for (int i = 0; i < xhs.length; i++) {
			sql.append("?");
			paraList.add(xhs[i]);
			if(i != xhs.length -1){
				sql.append(",");
			}
		}
		sql.append(" ) and xmid = ?");
		paraList.add(xmid);
		sql.append(" ");
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return "0".equals(num);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-25 ����11:32:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param xhs
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean savePlwh(LxmdwhForm t,String[] xhs) throws SQLException{
		StringBuffer sql = new StringBuffer();
		boolean flag = true;
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < xhs.length; i++) {
			paraList.add(new String[]{xhs[i],t.getXmid(),t.getLxqksm()});
		}
		sql.append("insert into xg_cqsx_jqlx_mdwh(xh,xmid,lxqksm) values(?,?,?)");
		int[] res = dao.runBatch(sql.toString(), paraList);
		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
		
	}
	
	/**
	 * @throws Exception 
	 * @����: ��ȡ������ӵ�ѧ��List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-28 ����01:32:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCanAddStuList(LxmdwhForm t, User user,String xhs) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from(");
		sql.append("  select * ");
		sql.append("  from view_xsjbxx t");
		sql.append("  where t.xh not in");
		sql.append("  (select xh from xg_cqsx_jqlx_mdwh where xmid = '"+t.getXmid()+"')");
		if(StringUtils.isNotNull(xhs)){
			String[] xhArr = xhs.split(","); 
			sql.append("  and xh not in (");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"'");
				if(i != xhArr.length-1){
					sql.append(",");
				}
			}
			sql.append("  )");
		}
		sql.append(" )t where 1=1");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @����:����id�����ȡ��У�����б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����3:49:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLxmdListByIds(String[] ids) {
		StringBuilder sql = new StringBuilder("select xh,xmid,lxqksm from xg_cqsx_jqlx_mdwh");
		sql.append(" where id = ");
		for(int i=0;i<ids.length;i++){
			sql.append(" ? ");
			if(i!=ids.length-1){
				sql.append(" or id = ");
			}
		}
		return dao.getListNotOut(sql.toString(),ids);
	}
}
