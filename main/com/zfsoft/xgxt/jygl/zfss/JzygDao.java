/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-8 ����02:16:06 
 */  
package com.zfsoft.xgxt.jygl.zfss;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� huj
 * @ʱ�䣺 2013-6-8 ����02:16:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JzygDao extends SuperDAOImpl<JzygForm> {

	public List<HashMap<String, String>> getPageList(JzygForm t)
			throws Exception {
		return null;
	}

	public List<HashMap<String, String>> getPageList(JzygForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select a.zgh,a.xm,a.bmdm,decode(a.xb, '1', '��', '2', 'Ů', '��','��','Ů','Ů') as xb," +
				"  b.bmmc from fdyxxb a left join zxbz_xxbmdm b on a.bmdm=b.bmdm where 1=1 ) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	protected void setTableInfo() {

	}

	public HashMap<String,String> getJzygxx(String zgh){
		String sql = "select * from fdyxxb where zgh = ?";
		return dao.getMapNotOut(sql, new String[]{zgh});
	}

}
