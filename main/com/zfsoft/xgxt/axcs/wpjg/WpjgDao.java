/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-8 ����06:52:18 
 */
package com.zfsoft.xgxt.axcs.wpjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-8 ����06:52:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpjgDao extends SuperDAOImpl<WpjgForm> {



	@Override
	public List<HashMap<String, String>> getPageList(WpjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}



	@Override
	public List<HashMap<String, String>> getPageList(WpjgForm t, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (select t1.*,t2.xmmc,t2.xmlb,t2.xmxxjs,t3.mc xmlbmc,t4.xm, t4.xb,t4.bjmc,t4.xydm,t4.xymc,t4.zydm,t4.zymc,t4.zzmmmc, t4.bjdm, t4.nj ");
		sql.append(" from xg_axjz_axcsxmjgb t1 left join xg_xszz_axcsxmb t2 on t1.xmdm=t2.xmdm left join xg_xszz_axcslbb t3 on t2.xmlb = t3.dm");
		sql.append(" left join view_xsbfxx t4 on t1.xh = t4.xh)t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:����ѧ�ꡢѧ�š���Ŀ����ɾ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����07:23:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public boolean delWpjgxx(String xh, String xn, String xmdm) throws Exception {
		String sql = "delete from xg_axjz_axcsxmjgb where xh = ? and xn = ? and xmdm = ?";
		return dao.runUpdate(sql, new String[] { xh, xn, xmdm });
	}
	
	public boolean delWpjgBySqid(String sqid) throws Exception {
		String sql = "delete from xg_axjz_axcsxmjgb where sqid = ?";
		return dao.runUpdate(sql, new String[] { sqid});
	}

	@Override
	protected void setTableInfo() {
		super.setClass(WpjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_axjz_axcsxmjgb");

	}

	/**
	 * 
	 * @����:�жϽ�����е�ѧ���Ƿ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-9 ����04:33:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(WpjgForm model) {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select count(1) num from xg_axjz_axcsxmjgb where xh= ? and xn= ? and xmdm= ?");
		params.add(model.getXh());
		params.add(model.getXn());
		params.add(model.getXmdm());
		
		if(!StringUtils.isBlank(model.getJgid())){
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");

	}
	
	
	/**
	 * 
	 * @����:����ͨ�������ݲ������޸�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-9 ����04:33:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_axjz_axcsxmjgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
		
	}
	
	
	/**
	 * 
	 * @����:���������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-9 ����04:34:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> viewOneWpjgList(String jgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jgid,t1.sqid,t1.xn,t1.xh,t1.sqsj,t1.sqly,t1.sjly,t1.xn,t2.*,t3.*,t3.mc xmlbmc ");
		sql.append(" from xg_axjz_axcsxmjgb  t1 left join xg_xszz_axcsxmb t2 on t1.xmdm=t2.xmdm left join xg_xszz_axcslbb t3  on t2.xmlb = t3.dm ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{jgId});
	}
	
	
	public HashMap<String, String> getWpjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_axjz_axcsxmjgb a");
		sb.append(",view_xsbfxx xsxx where a.xh=xsxx.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}
	
	/**
	 * @throws Exception  
	 * @����:����ѧ��ѧ��,ѧ��,��Ŀ����ɾ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-4 ����11:19:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int delForXhxnxmdm(String xh, String xn, String xmdm) throws Exception {
		
		String sql = "delete from xg_rcsw_lstd_jgb where xh = ? and xn = ? and xmdm = ?" ; 
		
		return dao.runDelete(sql, new String[]{xh,xn,xmdm});
	}


}
