/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-27 ����04:11:23 
 */  
package com.zfsoft.xgxt.gygl.gyjl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ��Ԣ������ϢDAO
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-4-27 ����04:09:18 
 * @�汾��V5.9.17
 * @�޸ļ�¼:   
 */

public class GyjltjDAO   extends SuperDAOImpl<GyjltjForm>{

	/*
	 * ����: 
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjltjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: 
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjltjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����:  
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	/**
	 * @����:��ȡΥ�ʹ��ִ����б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-27 ����05:25:44
	 * @�޸ļ�¼: 
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDllbxx(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.gyjllbdldm pk ,t.gyjllbdlmc val from xg_gygl_new_gyjllbdlb t  ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**

	 * @����:��ȡѧ��Υ���б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-27 ����05:22:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<HashMap<String, String>> getXsWjlb(User user,SearchModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(model);
		String[] inputV = SearchService.getTjInput(model);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");	
		//sql.append(" select rownum ,c.* from (select  a.xymc,b.zymc,b.bjmc,b.xh,b.xm,b.xb,b.rxrq,a.qsh,a.xydm,a.zydm,a.bjdm ,  a.wjxn,a.wjxq,a.nj,a.shzt,a.lddm,a.cljg,a.gyjllbdldm,a.gyjllbdm,a.wjsj wjrq from XG_GYGL_NEW_GYJLB a left join  view_xsxxb  b on a.xh = b.xh   ");
		//sql.append(" group by a.xymc,b.xm,b.xb,b.rxrq,b.zymc,b.bjmc,b.xh,a.qsh,a.xydm,a.zydm,a.bjdm ,  a.wjxn,a.wjxq,a.nj,a.shzt,a.lddm,a.cljg,a.gyjllbdldm,a.gyjllbdm,a.wjsj) c ");
		//sql.append(" where 1=1  and cljg !='wcl'");
		sql.append(" select rownum ,c.* from (select  a.xymc,b.zymc,b.bjmc,b.xh,b.xm,b.xb,b.rxrq,a.qsh,a.xydm,a.zydm,a.bjdm from (select * from (select a.xymc,b.zymc,b.bjmc,b.xh,b.xm,b.xb,b.rxrq,a.qsh,a.xydm,a.zydm,a.bjdm ,  a.wjxn,a.wjxq,a.nj,a.shzt,a.lddm,a.cljg,a.gyjllbdldm,a.gyjllbdm,a.wjsj wjrq from XG_GYGL_NEW_GYJLB a left join view_xsxxb b  on a.xh = b.xh where a.shzt='tg')a where 1=1 ");
		sql.append(searchTj);
		sql.append(" ) a left join  view_xsxxb  b on a.xh = b.xh  ");
		sql.append(" group by a.xymc,b.xm,b.xb,b.rxrq,b.zymc,b.bjmc,b.xh,a.qsh,a.xydm,a.zydm,a.bjdm) c ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	/**
	 * @����:��ȡѧ��Υ��ͳ��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-27 ����05:26:24
	 * @�޸ļ�¼: 
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
/*	public Map<String,String> getXsWjTj(){
		StringBuffer sql = new StringBuffer();
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || substr(a.wjsj, 6, 2) || ',' || b.gyjllbdldm pk,	");
		sql.append("	               wm_concat(b.gyjllbmc) || ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	");
		sql.append("	         group by a.xh, substr(a.wjsj, 6, 2), b.gyjllbdldm)	");
		sql.append("	union all	");
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || 'qn' || ',' || b.gyjllbdldm pk,	");
		sql.append("	               ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	");
		sql.append("	         group by a.xh || ',' || 'qn' || ',' || b.gyjllbdldm)	");
		
		String[] outValues = {"pk","val"};
		Map<String,String> resultMap = new HashMap<String,String>();
		List<HashMap<String, String>> xswjTjList = dao.getListNotOut(sql.toString(), new String[]{},outValues);
		for(Map<String,String> map : xswjTjList){
			Iterator<Entry<String,String>> ite = map.entrySet().iterator();
			String pk = "";
			String val = "";
			while(ite.hasNext()){
				Entry<String,String> en = ite.next();
				if(en.getKey().equals("pk")){
					pk =en.getValue();
				}else if(en.getKey().equals("val")){
					val =en.getValue();
				}
			}
			resultMap.put(pk, val);
		}

		return resultMap;
	}*/
	public List<HashMap<String, String>> getXsWjTj(){
		StringBuffer sql = new StringBuffer();
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || substr(a.wjsj, 6, 2) || ',' || b.gyjllbdldm pk,	");
		sql.append("	               wm_concat(b.gyjllbmc) || ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	and a.shzt = 'tg' ");
		sql.append("	         group by a.xh, substr(a.wjsj, 6, 2), b.gyjllbdldm)	");
		sql.append("	union all	");
		sql.append("	select pk, decode(val, '-', null, val) val	");
		sql.append("	  from (select a.xh || ',' || 'qn' || ',' || b.gyjllbdldm pk,	");
		sql.append("	               ' -' || sum(b.gyjllbkf) val	");
		sql.append("	          from XG_GYGL_NEW_GYJLB a, xg_gygl_new_gyjllbdmb b	");
		sql.append("	         where a.gyjllbdm = b.gyjllbdm	and a.shzt = 'tg'");
		sql.append("	         group by a.xh || ',' || 'qn' || ',' || b.gyjllbdldm)	");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

}
