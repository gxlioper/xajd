/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-10 ����03:36:18 
 */  
package xsgzgl.gygl.rcjc.wmqsxsmd;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-5-10 ����03:36:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WmqsxsmdDao  extends SuperDAOImpl<WmqsxsmdForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_new_wmqsxsmdb");
		super.setKey("id");
		super.setClass(WmqsxsmdForm.class);
	}


	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsxsmdForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsxsmdForm model, User user)
			throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.*,b.xydm,b.xymc,b.xm,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,nvl(d.xqmc,a.xq) xqmc  from xg_gygl_new_wmqsxsmdb a "); 
		sql.append(" left join view_xsjbxx  b on a.xh=b.xh left join xqdzb d on a.xq=d.xqdm ) t where 1=1 "); 
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}


	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-10 ����05:07:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getWmqsxsmdById(WmqsxsmdForm myForm) {
		
		return null;
	}


	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-5-11 ����10:25:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String countXs(WmqsxsmdForm model) {
		String sql = "select count(0) c from XG_GYGL_NEW_WMQSXSMDB where xh=? and xn=? and xq=? ";
		return dao.getOneRs(sql, new String[]{model.getXh(),model.getXn(),model.getXq()}, "c");
	}

	
	
}
