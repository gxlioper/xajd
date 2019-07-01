/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-22 ����11:03:50 
 */  
package xgxt.xsxx.dagl.dacx;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯҳ��
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-22 ����11:03:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxDaxxDao extends SuperDAOImpl<XsxxDaxxForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XSXX_DAGL_DAXXB");
		//setKey("xqwdid");
		setClass(XsxxDaxxForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxDaxxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxDaxxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/**
	 * ��ѯ����һ��������Ϣ
	 * @param xm,sfzh
	 * @return
	 */
	public HashMap<String,String> getXsxxDaxx(String xm, String sfzh){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,rownum from ")
		   .append("(select t.xm,t1.* from VIEW_XSJBXX t left join XG_XSXX_DAGL_DAXXB t1 on t.xh = t1.xh where t.xm = ? and t.sfzh = ? order by t1.dazrsj desc) a where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{xm,sfzh});
	}

}
