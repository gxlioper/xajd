/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-16 ����01:34:17 
 */  
package com.zfsoft.xgxt.szdw.sdkj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cmj 
 * @ʱ�䣺 2013-5-16 ����01:34:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwSdkjZdjskhDAO extends SuperDAOImpl<ZdlskhForm> {

	protected void setTableInfo() {
		super.setTableName("zdlskhb");
		super.setKey("id");
		super.setClass(ZdlskhForm.class);
	}
	
	/**
	 * �����·ݲ鿴��ʦ������Ϣ
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-17 ����09:03:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListxx(ZdlskhForm t)
	throws Exception {
		StringBuilder sql=new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select * from zdlskhb where 1=1 ");
		
		if (!StringUtil.isNull(t.getNf())){
			params.add(t.getNf());
			sql.append("and nf like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getYf())){
			params.add(t.getYf());
			sql.append("and yf like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getXb())){
			params.add(t.getXb());
			sql.append("and xb like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getZdls())){
			params.add(t.getZdls());
			sql.append("and zdls like '%'||?||'%'");
		}
		if (!StringUtil.isNull(t.getZgh())){
			params.add(t.getZgh());
			sql.append("and zgh like '%'||?||'%'");
		}
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(ZdlskhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql=new StringBuilder();
		List<String> params = new ArrayList<String>();
		String nf="";
		if (!StringUtil.isNull(t.getNf())){
			nf=t.getNf();
		}else{
			nf=Base.currNd;
		}
		String[] yfs=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
		sql.append("select * from (");
		sql.append("select a.nf,a.yf,zrs,nvl(rs,0)rs from ( ");
		for (int i=0;i<yfs.length-1;i++) {
			sql.append("(select '"+nf+"' nf,'"+yfs[i]+"' yf,(select count(distinct zgh) from(select * from fdybjb union select * from bzrbbb))zrs from dual) union all ");
		}
		sql.append("(select '"+nf+"' nf,'"+yfs[11]+"' yf,(select count(distinct zgh) from(select * from fdybjb union select * from bzrbbb))zrs from dual)");
		sql.append(") a left join (select nf,yf,count(*)rs from zdlskhb group by nf,yf) b on a.nf=b.nf and a.yf=b.yf) where 1=1 ");
		
		if (!StringUtil.isNull(t.getYf())){
			params.add(t.getYf());
			sql.append(" and yf like '%'||?||'%'");
		}
		sql.append(" order by nf, yf");
		
		
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}


	
	@Override
	public List<HashMap<String, String>> getPageList(ZdlskhForm model, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql=new StringBuilder();
		sql.append("select a.nf,a.yf,zrs,nvl(rs,0)rs from (select '2013' nf, yf,(select count(distinct zgh) from(select * from fdybjb union all select * from bzrbbb))zrs from yfb) ");
		sql.append(" a left join (select nf,yf,count(*)rs from zdlskhb group by nf,yf) b on a.nf=b.nf and a.yf=b.yf");
		return getPageList(model, sql.toString(), inputV);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-23 ����10:04:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getndList() {
		// TODO �Զ����ɷ������
		return dao.getXnndList();
	}
}
