/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����05:42:19 
 */  
package com.zfsoft.xgxt.rcsw.xbzj;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: (ѧ��ϵ��֧�̹���--Ϋ��ѧԺ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-5 ����05:42:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XbzjDAO extends SuperDAOImpl<XbzjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XbzjForm t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XbzjForm model, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		/*StringBuilder sql = new StringBuilder("select * from (select a.id,a.xh,a.xn,a.xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,a.zjsj,a.bz,");
		sql.append("b.xm,b.xb,b.nj,b.xymc,b.zymc,b.bjmc,b.xydm,b.zydm,b.bjdm ");
		sql.append("from rcsw_xbzj a left join view_xsjbxx b on a.xh=b.xh) where 1=1 ");*/
		StringBuilder sql = new StringBuilder();
		sql.append("select * from VIEW_NEW_DC_RCSW_XBZJ a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	public XbzjForm getModel(XbzjForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc from rcsw_xbzj a where id=?");
		XbzjForm myForm=new XbzjForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{model.getId()});
		BeanUtils.copyProperties(myForm, map);
		return myForm;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("rcsw_xbzj");
		super.setKey("Id");
		super.setClass(XbzjForm.class);
		
	}

	/** 
	 * @����:(�жϸ�ѧ��ѧ�ڸ�ѧ���Ƿ���������֧��ѧ��)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����06:49:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(XbzjForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select *��from rcsw_xbzj where xh=? and xn=? and xq=?");
		List<HashMap<String, String>> list=dao.getArrayList(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getXq()}, new String[]{"xh"});
		return list.size()!=0;
	}

}
