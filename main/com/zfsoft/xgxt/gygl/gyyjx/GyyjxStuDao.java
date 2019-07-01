/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����04:11:55 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-24 ����04:11:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyyjxStuDao extends SuperDAOImpl<GyyjxForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GyyjxForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
 		//Ȩ�޿���
		String searchTjQx="";
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";				//��Ԣ����Ա���ݷ�Χ��������
 		
		 /* ���û�Ϊ��Ԣ����Աʱ��xg_gygl_new_gyfdyb �� ���ڸ��û�����
		 * ����������ΪѧԺ���߸���Ա��Ȩ�޿��ƣ�ֻ����¥�����ݷ�Χ
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//�û�Ϊ��Ԣ����Ա
			searchTjQx = searchTjByGyfdy;
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			searchTjQx = searchTjByUser;
		}
 		
 		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t1.* from (select a.*, a.gyyjid||'@@'||a.fkqk gyyjid_fkqk, f.yjflmc, b.nj , b.xm, b.xb, b.xydm , b.xymc , b.zydm , b.zymc , b.bjdm , b.bjmc , d.lddm , d.ldmc,c.qsh ,c.cwh,e.ch,e.qsxb,")
		.append("  decode(a.fkqk ,'0' ,'δ����' , '1' , '�ѷ���' , 'δ֪') fkqkmc ")
		.append("  from XG_GYGL_GYYJX_GYYJ a ")
		.append("  left join view_xsjbxx b ")
		.append("    on a.xh = b.xh ")
		.append("  left join XG_GYGL_NEW_CWXXB c ")
		.append("    on a.xh = c.xh ")
		.append("  left join xg_gygl_new_ldxxb d on c.lddm = d.lddm ")
		.append("  left join xg_gygl_new_qsxxb e on c.lddm = e.lddm and c.qsh = e.qsh left join XG_GYGL_GYYJX_YJFL f on a.YJFLDM = f.YJFLDM ) t1 where 1=1 ")
		.append(searchTj).append("  ").append(searchTjQx);
		
		return getPageList(t, sql.toString(), inputV);
	}

	public HashMap<String , String> getModelMap(String gyyjid){
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*, a.gyyjid||'@@'||a.fkqk gyyjid_fkqk, f.yjflmc, b.nj , b.xm, b.xb, b.xydm , b.xymc , b.zydm , b.zymc , b.bjdm , b.bjmc , d.lddm , d.ldmc,c.qsh ,c.cwh,e.ch,e.qsxb,")
		.append("  decode(a.fkqk ,'0' ,'δ����' , '1' , '�ѷ���' , 'δ֪') fkqkmc ")
		.append("  from XG_GYGL_GYYJX_GYYJ a ")
		.append("  left join view_xsjbxx b ")
		.append("    on a.xh = b.xh ")
		.append("  left join XG_GYGL_NEW_CWXXB c ")
		.append("    on a.xh = c.xh ")
		.append("  left join xg_gygl_new_ldxxb d on c.lddm = d.lddm ")
		.append("  left join xg_gygl_new_qsxxb e on c.lddm = e.lddm and c.qsh = e.qsh left join XG_GYGL_GYYJX_YJFL f on a.YJFLDM = f.YJFLDM ) t1 where 1=1 and gyyjid = ? ");

		return dao.getMapNotOut(sql.toString(), new String[]{gyyjid});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(GyyjxForm.class);
		setKey("GYYJID");
		setTableName("XG_GYGL_GYYJX_GYYJ");
	}

}
