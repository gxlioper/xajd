/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @className	�� HdblsqshDao
 * @description	�� TODO(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-16 ����05:12:29
 * @version 	V1.0 
 */

public class HdblsqshDao extends SuperDAOImpl<HdblsqshForm> {

	/**
	 * @description	�� TODO
	 * @author 		��������1282��
	 * @date 		��2018-1-16 ����05:13:07
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdblsqshForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	�� TODO
	 * @author 		��������1282��
	 * @date 		��2018-1-16 ����05:13:07
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdblsqshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*");
		sql.append(" ,t2.xm,t2.xb,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,");
		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc,");
		sql.append(" t8.xqmc,t3.jzlxmc");
		sql.append(" from xg_hdgl_hdblsqb t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xqdzb t8");
		sql.append(" on t1.xq = t8.xqdm");
		sql.append(" left join xg_hdgl_jzlxdmb t3 on t1.jzlx = t3.jzlxdm");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * ��ȡ��ǰ���л
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public  List<HashMap<String, String>> getHdxxPageList(HdblsqshForm t, User user) throws Exception{
		//���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append("select t.*,(select a.hdlxmc from xg_hdgl_hdlxdmb a where t.hdlx = a.hdlxdm) hdlxmc from ( ");
		sql.append(" select hdid,hdmc,to_char(to_date(hdkssj,'yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd') hdsj,hdxs,hdlx,hddd,'��������' ly ,zbf,xsxxlx,hdkclx ");
		sql.append(" from XG_HDGL_HDXXB ");
		sql.append(" ) t ");
		sql.append(" ) ");

		sql.append(searchTj);

		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @description	�� TODO
	 * @author 		��������1282��
	 * @date 		��2018-1-16 ����05:13:07
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(HdblsqshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_hdgl_hdblsqb");	
	}
	
	/**
	 * @description	�� ��ȡ�����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-17 ����03:11:49
	 * @return
	 */
	public List<HashMap<String,String>> getHdlxList(){
		String sql = "select * from xg_hdgl_hdlxdmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @description	�� ��ȡ���¼��Ϣ
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����02:10:01
	 * @param model
	 * @return
	 */
	public HashMap<String,String> gethdblInfo(HdblsqshForm model){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.hdlxmc, c.hdbq, c.hdbqmc, d.nlbq, d.nlbqmc, e.xqmc, f.jzlxmc , g.mc zxkclxmc,h.mc zjrzcmc ");
		sql.append("  from xg_hdgl_hdblsqb a ");
		sql.append("left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm ");
		sql.append("left join (select t1.jgid, replace(wm_concat(t1.hdbq), ';', ',') hdbq, ");
		sql.append("  replace(wm_concat(t2.hdbqmc), ';', ',') hdbqmc ");
		sql.append("  from xg_hdgl_hdbqglb t1 left join xg_hdgl_hdbqdmb t2 on t1.hdbq = t2.hdbqdm ");
		sql.append("  group by t1.jgid) c on a.sqid = c.jgid ");
		sql.append("left join (select t3.jgid, replace(wm_concat(t3.nlbq), ';', ',') nlbq, ");
		sql.append("  replace(wm_concat(t4.nlbqmc), ';', ',') nlbqmc ");
		sql.append("  from xg_hdgl_nlbqglb t3 left join xg_hdgl_nlbqdmb t4 on t3.nlbq = t4.nlbqdm ");
		sql.append("  group by jgid) d on a.sqid = d.jgid ");
		sql.append("left join xqdzb e on a.xq = e.xqdm ");
		sql.append("left join xg_hdgl_jzlxdmb f on a.jzlx = f.jzlxdm ");
		sql.append("left join xg_hdgl_zxkclxdmb g on a.zxkclx = g.dm ");
		sql.append("left join xg_hdgl_zjrzcdmb h on a.zjrzc = h.dm ");
		sql.append(" where a.sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getSqid()});
	}

	/**
	 * ��ȡ���Ϣ������������
	 * @param hdid
	 * @return
	 */
	public HashMap<String,String> gethdInfo(String hdid){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.hdlxmc, c.hdbq, c.hdbqmc, d.nlbq, d.nlbqmc,  f.jzlxmc , g.mc zxkclxmc,h.mc zjrzcmc ");
		sql.append("  from xg_hdgl_hdxxb a ");
		sql.append("left join xg_hdgl_hdlxdmb b on a.hdlx = b.hdlxdm ");
		sql.append("left join (select t1.jgid, replace(wm_concat(t1.hdbq), ';', ',') hdbq, ");
		sql.append("  replace(wm_concat(t2.hdbqmc), ';', ',') hdbqmc ");
		sql.append("  from xg_hdgl_hdbqglb t1 left join xg_hdgl_hdbqdmb t2 on t1.hdbq = t2.hdbqdm ");
		sql.append("  group by t1.jgid) c on a.hdid = c.jgid ");
		sql.append("left join (select t3.jgid, replace(wm_concat(t3.nlbq), ';', ',') nlbq, ");
		sql.append("  replace(wm_concat(t4.nlbqmc), ';', ',') nlbqmc ");
		sql.append("  from xg_hdgl_nlbqglb t3 left join xg_hdgl_nlbqdmb t4 on t3.nlbq = t4.nlbqdm ");
		sql.append("  group by jgid) d on a.hdid = d.jgid ");
		sql.append("left join xg_hdgl_jzlxdmb f on a.jzlx = f.jzlxdm ");
		sql.append("left join xg_hdgl_zxkclxdmb g on a.zxkclx = g.dm ");
		sql.append("left join xg_hdgl_zjrzcdmb h on a.zjrzc = h.dm ");
		sql.append(" where a.hdid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{hdid});
	}
	
	/**
	 * @description	�� ��ȡ�����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-22 ����03:41:44
	 * @param hdlxdm
	 * @return
	 */
	public String getHdmc(String hdlxdm){
		String sql = "select hdlxmc from xg_hdgl_hdlxdmb where hdlxdm = ?";
		return dao.getOneRs(sql, new String[]{hdlxdm}, "hdlxmc");
	}


	/**
	 * @����:������ְ��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/3/21 16:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
    public List<HashMap<String,String>> getZjrzcList() {
		String sql = "select * from xg_hdgl_zjrzcdmb";
		return dao.getListNotOut(sql, new String[]{});
    }
}
