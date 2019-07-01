/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����10:50:13 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.jg;

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
 * @ʱ�䣺 2016-7-22 ����10:50:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TttzxmJgDao extends SuperDAOImpl<TttzxmJgForm> {
	/*
    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
*/

@Override
public List<HashMap<String, String>> getPageList(TttzxmJgForm t)
		throws Exception {
	// TODO �Զ����ɷ������
	return null;
}

/*
* ��ѯ
    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
*/

@Override
public List<HashMap<String, String>> getPageList(TttzxmJgForm t, User user)
		throws Exception {
	// TODO �Զ����ɷ������
	String searchTj = SearchService.getSearchTj(t.getSearchModel());
	String[] inputV = SearchService.getTjInput(t.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
	StringBuilder sql = new StringBuilder();
	sql.append("select * from (");
	sql.append(" select ");
	sql.append(" t.*,");
	sql.append(" nvl(t1.tdnum,0) tdnum,");
	sql.append(" t2.xm dzxm,");
	sql.append(" t2.xb,");
	sql.append(" t2.xymc,");
	sql.append(" t2.xydm,");
	sql.append(" t2.nj,");
	sql.append(" t2.zydm,");
	sql.append(" t2.zymc,");
	sql.append(" t2.bjdm,");
	sql.append(" t2.bjmc,");
	sql.append(" t3.xmmc,");
	sql.append(" t3.xmkssj,");
	sql.append(" t3.xfrdsqzt,");
	sql.append(" t3.xfrdjgzt,");
	sql.append(" t3.xn,");
	sql.append(" t3.xq,");
	sql.append(" t3.jcxf,");
	sql.append(" t3.sskmdm,");
	sql.append(" t3.xmjbdm,");
	sql.append(" t5.sskmmc,");
	sql.append(" t4.xmjbmc,");
	sql.append(" t.dzxh xh,");
	sql.append(" t6.xqmc");
	sql.append(" from xg_sztz_ttxmjg t ");
	sql.append(" left join (select count(1) tdnum,xmdm,ttsqid from xg_sztz_ttcy group by xmdm,ttsqid) t1");
	sql.append(" on t.ttjgid = t1.ttsqid");
	sql.append(" left join  view_xsjbxx t2");
	sql.append(" on t.dzxh = t2.xh");
	sql.append(" left join xg_sztz_xmjg t3");
	sql.append(" on t.xmdm = t3.xmdm");
	sql.append(" left join xg_sztz_xmjb t4");
	sql.append(" on t3.xmjbdm = t4.xmjbdm");
	sql.append(" left join xg_sztz_sskm t5");
	sql.append(" on t3.sskmdm = t5.sskmdm");
	sql.append(" left join xqdzb t6");
	sql.append(" on t3.xq = t6.xqdm");
	sql.append(") t where 1= 1  ");
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
	this.setClass(TttzxmJgForm.class);
	this.setTableName("xg_sztz_ttxmjg");
	this.setKey("ttjgid");
}

 /**
  * 
  * @����: ��ȡ�����Ա���ж�Ӧ��ѧ����¼
  * @���ߣ�yxy[���ţ�1206]
  * @���ڣ�2016-8-8 ����10:13:25
  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
  * @return
  * HashMap<String,String> �������� 
  * @throws
  */
 public HashMap<String, String> getttcyDyRecord(String xh,String xmdm){
	 StringBuilder sql = new StringBuilder();
	 sql.append(" select * from xg_sztz_ttcy where xmdm = ? and xh = ?");
	 return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xh});
 }


}
