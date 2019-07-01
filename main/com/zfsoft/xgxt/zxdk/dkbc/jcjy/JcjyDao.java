/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-6 ����03:18:23 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;
import com.zfsoft.xgxt.zxdk.ysjxj.ysjxj.YsjxjForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-�����ҵ
 * @�๦������: ��ѯ
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-12-6 ����03:18:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcjyDao extends SuperDAOImpl<JcjyModel>{
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setClass(JcjyModel.class);
		super.setKey("juid");
		super.setTableName("xg_zxdk_jcjy");
	}
	
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(JcjyModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(JcjyModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.dzyx,t2.mzmc,t2.xb,t2.xz,t2.zzmmmc,t2.jtdzxx,t2.sfzh,t2.yhkh,t2.xm ");
		sql.append(" ,t1.*, t3.hylbmc from xg_zxdk_jcjy t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_jcjyhylb t3 on t1.hylb = t3.hylbdm  ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @����: ��ѯ��ҵ������ơ���ҵ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-12 ����09:08:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getHylbList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select hylbdm,hylbmc from xg_zxdk_jcjyhylb order by hylbdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ͬһ��ѧ�Ŵ������ֻ�ܴ���һ����¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-12 ����09:09:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param dclb
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecord(String xh,String dclb){
		String sql = " select count(1) num from xg_zxdk_jcjy where xh = ? and dclb = ? ";
		String num = dao.getOneRs(sql, new String[]{ xh,dclb }, "num");
		return Integer.valueOf(num)>0;	
	}
	
	/**
	 * @����: ȡ��ҵ�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-12 ����09:10:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHylb(String xh){
		String sql = " select a.hylbmc from xg_zxdk_jcjyhylb a left join xg_zxdk_jcjy b on a.hylbdm = b.hylb where b.xh = ? ";
		return dao.getOneRs(sql, new String[]{xh}, "hylbmc");
	}
}
