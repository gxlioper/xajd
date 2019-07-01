/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-6 ����03:21:09 
 */  
package com.zfsoft.xgxt.gygl.gywp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: (��Ԣ��Ʒ����) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-6 ����03:21:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GywpxxDAO extends SuperDAOImpl<GywpxxForm> {

	
	
	@Override
	public List<HashMap<String, String>> getPageList(GywpxxForm model)
			throws Exception {
		return null;
	}

	
	
	@Override
	public List<HashMap<String, String>> getPageList(GywpxxForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		/*StringBuilder sql = new StringBuilder("select a.*,(select wpdlmc from xg_gygl_wfxy_qswpdlb e where a.wpdldm=e.wpdldm) wpdlmc, ");
		sql.append("(select wplbmc from xg_gygl_wfxy_qswplbb e where a.wplbdm=e.wplbdm) wplbmc,");
		sql.append("(select ldmc from xg_gygl_new_ldxxb e where a.lddm=e.lddm) ldmc from xg_gygl_wfxy_qswpxxb a where 1=1 ");
		sql.append(searchTj);
		sql.append("  order by lddm,qsh");*/
		StringBuilder sql = new StringBuilder();
		sql.append("select * from VIEW_NEW_DC_YGGL_WPXX where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	
	
	@Override
	protected void setTableInfo() {
		setTableName("xg_gygl_wfxy_qswpxxb");
		setKey("id");
		super.setClass(GywpxxForm.class);
		
	}

	/** 
	 * @����:(��ȡ���й�Ԣ¥��Ʒά����Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-6 ����03:42:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGywplrxxList(GywpxxForm model,
			User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select a.lddm||'@@'||a.qsh pk,a.*,(case when rs=0 then '��' else '��' end)sfdj from xg_view_gygl_wfxy_qswpxx a) where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/** 
	 * @����: (��ȡ��Ʒ����List)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����08:38:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWpdlList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select wpdldm,wpdlmc from xg_gygl_wfxy_qswpdlb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}



	/** 
	 * @����:(��ȡ��Ʒ���List)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����08:44:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWplbList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select wplbdm,wplbmc from xg_gygl_wfxy_qswplbb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * ����������Ʒ
	 */
	public boolean runInsert(GywpxxForm model) throws Exception{
		String ids=model.getIds();
		String[] id=ids.split(",");
		List<String[]> params=new ArrayList<String[]>();
		for (String string : id) {
			String[] param=new String[10];
			String[] ldqs=string.split("@@");
			param[0]=model.getWpmc();
			param[1]=model.getWpdldm();
			param[2]=model.getWplbdm();
			param[3]=model.getSl();
			param[4]=model.getSfzhgq();
			param[5]=model.getSfwh();
			param[6]=ldqs[0];
			param[7]=ldqs[1];
			param[8]=model.getHhyy();
			param[9]=model.getBz();
			params.add(param);
		}
		StringBuilder sql=new StringBuilder();
		sql.append("insert into xg_gygl_wfxy_qswpxxb(wpmc,wpdldm,wplbdm,sl,sfzhgq,sfwh,lddm,qsh,hhyy,bz) values(?,?,?,?,?,?,?,?,?,?)");
		int[] result=dao.runBatch(sql.toString(), params);
		return result.length==params.size();
	}
	/**
	 * ��ȡά����Ʒmodel
	 */
	@Override
	public GywpxxForm getModel (GywpxxForm model) throws Exception{
		GywpxxForm myForm = new GywpxxForm();
		StringBuilder sql=new StringBuilder();
		sql.append("select a.*,(select wpdlmc from xg_gygl_wfxy_qswpdlb e where a.wpdldm=e.wpdldm) wpdlmc,(select wplbmc from xg_gygl_wfxy_qswplbb e where a.wplbdm=e.wplbdm) wplbmc,(select ldmc from xg_gygl_new_ldxxb e where a.lddm=e.lddm)ldmc from xg_gygl_wfxy_qswpxxb a where 1=1 and id=?");
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{model.getId()});
		BeanUtils.copyProperties(myForm, map);
		return myForm;
	}



	/** 
	 * @����:(��ȡѧ��������ƷList)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����05:06:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<String[]> �������� 
	 * @throws 
	 */
	public List<String[]> getQswpList(String xh) {
		StringBuilder sql=new StringBuilder();
		sql.append("select b.*,(select wpdlmc from xg_gygl_wfxy_qswpdlb e where b.wpdldm=e.wpdldm) wpdlmc,");
		sql.append("(select wplbmc from xg_gygl_wfxy_qswplbb e where b.wplbdm=e.wplbdm) wplbmc ");
		sql.append("from xg_gygl_new_cwxxb a right join xg_gygl_wfxy_qswpxxb b on a.lddm=b.lddm and a.qsh=b.qsh where a.xh=?");
		
		return dao.rsToVator(sql.toString(), new String[]{xh}, new String[]{"wpmc","wpdlmc","wplbmc","sl","sfzhgq","sfwh"});
	}

}
