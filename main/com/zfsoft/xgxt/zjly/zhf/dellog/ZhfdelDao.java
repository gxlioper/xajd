/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-21 ����09:29:53 
 */  
package com.zfsoft.xgxt.zjly.zhf.dellog;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CP[����:1352]
 * @ʱ�䣺 2017-3-21 ����09:29:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfdelDao extends SuperDAOImpl<ZhfdelForm>{
	@Override
	public List<HashMap<String, String>> getPageList(ZhfdelForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(ZhfdelForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select logid,scrzgh,scrxm,scsj,xh,(select xm from view_xsxxb where a.xh=xh)xm,");
		sql.append(" (select jfxmmc from xg_zjly_zhszf_jfxmb b where a.jfxmdm = b.jfxmdm) jfxmmc,");
		sql.append(" (select xmmkmc from xg_zjly_zhszf_mkxmb c where a.xmmkdm = c.xmmkdm) xmmkmc,");
		sql.append(" sxsm,decode(shzt,'1','�����','0','δ��','2','�˻�') shzt,cysj,");
		sql.append(" case when length(lrr)>10 then (select xm from view_xsxxb where a.lrr = xh )");
		sql.append(" else (select xm from view_fdyxx where a.lrr = zgh ) end lrrxm,lrr,lrsj");
		sql.append(" from xg_zjly_zhfsclog a ");
		sql.append(" where 1= 1  ");
		sql.append(searchTj);
		sql.append(" order by scsj desc");
		return getPageList(t, sql.toString(),inputV);
	}
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}

}
