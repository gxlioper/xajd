/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-25 ����12:14:35 
 */  
package xsgzgl.xtwh.general.news;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-4-25 ����12:14:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YydmdDao extends SuperDAOImpl<NewsManageForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(NewsManageForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		if(null==t.getTablename()||"".equals(t.getTablename())){
			t.setTablename("xg_tzgglljlb_1");
		}
//		 sql.append("select a.*,(case when a.yhlx='stu' then 'ѧ��' else '��ʦ' end)yhlxmc from "+t.getTablename()+" a where a.newsid=? ");

		sql.append("SELECT * FROM (");
		sql.append("SELECT t.*,z.BMMC FROM ");
		sql.append("(select a.*,(case when a.yhlx='stu' then 'ѧ��' else '��ʦ' end) yhlxmc,");
		sql.append("CASE WHEN a.yhlx = 'stu' THEN (SELECT xm FROM XSXXB WHERE xh = a.YHM)");
		sql.append("ELSE (SELECT xm FROM YHB WHERE YHM = a.YHM) END xm,");
		sql.append("CASE WHEN a.yhlx = 'stu' THEN (SELECT XYDM FROM VIEW_XSJBXX WHERE xh = a.YHM) ");
		sql.append("ELSE (SELECT SZBM FROM YHB WHERE YHM = a.YHM) END bmdm ");
		sql.append("from ");
		sql.append(t.getTablename());
		sql.append(" a) t LEFT JOIN ZXBZ_XXBMDM z ON t.bmdm = z.BMDM ");
		sql.append("where t.newsid=?");
		sql.append(") t");

		return getPageList(t, sql.toString(), new String[]{t.getNewsid()});
		}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(NewsManageForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}

}
