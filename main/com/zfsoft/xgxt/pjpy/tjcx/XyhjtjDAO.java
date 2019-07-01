/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-16 ����05:06:37 
 */  
package com.zfsoft.xgxt.pjpy.tjcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �������� ͳ�Ʋ�ѯ ѧԺ��ͳ�Ʋ�ѯ
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-5-16 ����05:09:00 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class XyhjtjDAO extends SuperDAOImpl<XyhjtjForm>{
	/**
	 * @����:���������ʷ��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-16 ����05:19:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getPjlsxxb(){
		List<String> resultList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmmc  from xg_pjpy_pjlsxxb group by xmmc ");
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), new String[]{});
		Iterator<HashMap<String, String>> ite = result.iterator();
		while(ite.hasNext()){
			HashMap<String, String> map = ite.next();
			Iterator<Entry<String, String>> mapListIterator= map.entrySet().iterator();
			while(mapListIterator.hasNext()){
				Entry<String, String> entry = mapListIterator.next();
				if(entry.getKey().equals("xmmc")){
					resultList.add(entry.getValue());
				}
			}
		}
		return resultList;
	}
	/**
	 * @����:���������ʷ�� ͳ��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-16 ����05:19:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<List<String>> �������� 
	 * @throws
	 */
	public List<List<String>> getPjlsxxbTj(String [] st,User user){
		List<List<String>> resultList = new ArrayList<List<String>>();
		StringBuffer sql = new StringBuffer();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		//��ѯ��Ҫִ�е�sql ��ת��
		String[] str = dao.getOneRs(" select fun_xg_pjpy_shxjxy() str from dual ", new String[]{}, new String[]{"str"});
		sql.append(str[0]);
		sql.append(" where 1=1 ");
		sql.append(" and pjsj= '"+Base.currXn+"'");
		sql.append(searchTjByUser);
		List<HashMap<String, String>> result = dao.getArrayList(sql.toString(),new String[]{}, st);
		Iterator<HashMap<String, String>> ite = result.iterator();
		while(ite.hasNext()){
			List<String> inList = new ArrayList<String>();
			HashMap<String, String> map = ite.next();
			for (int i = 0; i < st.length; i++) {
				inList.add(map.get(st[i]));
			}
			resultList.add(inList);
		}
		return resultList;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyhjtjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyhjtjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
}
