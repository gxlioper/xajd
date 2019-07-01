package com.zfsoft.xgxt.xszz.jtqkdc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��֮��ͥ������� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-18 ����06:25:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JtqkdcDao extends SuperDAOImpl<JtqkdcForm> {

	@Override
	public List<HashMap<String, String>> getPageList(JtqkdcForm t)
			throws Exception {
		return null;
	}

	
	public List<HashMap<String, String>> getPageList(JtqkdcForm model, User user)
			throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
//		sql.append("select * from (select t1.xh,t1.dcsj,t2.xm,t2.xb,t2.nj,t2.xydm, ");
//		sql.append("t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc ");
//		sql.append("from xg_xszz_new_jtqkdcb t1 ");
//		sql.append("left join view_xsjbxx t2 on t1.xh=t2.xh ) t1 where 1=1 ");
		
		sql.append("select t1.*,t2.sydm,t3.symc from (select * from xg_view_xszz_new_jtqkdc a ) t1 ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_XTWH_SYDMB t3 on t2.sydm = t3.sydm");
		sql.append(" where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("xh");
		super.setTableName("xg_xszz_new_jtqkdcb");
		super.setClass(JtqkdcForm.class);
	}

	
	/**
	 * ��ͥ��Ա�б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyList(String xh){
		
		String sql = "select a.*,b.mc cygxmc, nvl(c.mc,a.cyjkzk)cyjkzkmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx=b.dm left join xg_xsxx_jkztb c on a.cyjkzk=c.dm where a.xh = ? order by cygx";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	
	
	/**
	 * 
	 * @����:��ѧ��ɾ����ͥ��Ա��Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-18 ����06:24:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteJtcy(String[] xh) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_jtcyb where (");
		
		for (int i = 0 , n = xh.length ; i < n ; i++){
			sql.append("xh=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		
		return dao.runUpdate(sql.toString(), xh);
	}
	
	
	
	/**
	 * 
	 * @����:���������ͥ��Ա
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-19 ����08:39:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param keys
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJtcy(String[] keys ,List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		
		sql.append("insert into xg_xszz_new_jtcyb (");
		
		for (int i = 0,n = keys.length ; i < n ; i++){
			sql.append(keys[i]);
			
			temp.append("?");
			
			if (i < n -1){
				sql.append(",");
				temp.append(",");
			}
		}

		sql.append(") values (");
		sql.append(temp);
		sql.append(")");
		
		int[] result = dao.runBatch(sql.toString(), params);
		
		return dao.checkBatchResult(result);
	}


	/** 
	 * @����:����ѧ�Ż�ø�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-9-22 ����01:48:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * JtqkdcForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getfqInfo(String xh) {
		
			String sql = "select a.*, b.mc cygxmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx = b.dm where a.xh = ? and mc like '����' and  rownum =1";
		
		return dao.getMapNotOut(sql, new String[]{xh});
	}


	/** 
	 * @����:����ѧ�Ż��ĸ����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-9-22 ����01:48:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * JtqkdcForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getmqInfo(String xh) {
		
			String sql = "select a.*, b.mc cygxmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx = b.dm where a.xh = ? and mc like 'ĸ��' and  rownum =1";
		
			return dao.getMapNotOut(sql, new String[]{xh});
	}


	/** 
	 * @����:�Ϻ������ѧ���Ի���ѯ��ͥ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-10-29 ����04:51:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getJtqkInfo(String xh) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,dcsj,decode(sfgc,'1','��','0','��',sfgc) sfgc,decode(sfdq,'1','��','0','��',sfdq) sfdq,decode(lszn,'1','��','0','��',lszn) lszn, ");
		sql.append("decode(sfdb,'1','��','0','��',sfdb) sfdb,jthk,jtszdssxdm,decode(sfpkx,'1','��','0','��',sfpkx) sfpkx,pkxjb, ");
		sql.append("decode(sfqz,'1','��','0','��',sfqz) sfqz,decode(fmjk,'1','��','0','��',fmjk) fmjk,fmjz,jtdh,jtdz,jtrs,jtyb,jtrjsr,jtnzsr,jtrjysr, ");
		sql.append("jtsrly,mzbmtxdz,mzbmyzbm,mzbmlxdh,snjtsr,yhzzqk,jtszqk,tfsjqk,cjnmqk,jtsyqk,jtqzqk,jtqtqk,ylzd1, ");
		sql.append("decode(ylzd2,'1','��','0','��',ylzd2) ylzd2,decode(ylzd3,'1','��','0','��',ylzd3) ylzd3,ylzd4,ylzd5,ylzd6,ylzd7,ylzd8,ylzd9, ");
		sql.append("ylzd10 from xg_xszz_new_jtqkdcb where xh = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	
	/**
	 * 
	 * @����: ���ȡĬ��ǰ3����ͥ��Ա��Ϣ(�Ϻ�������Ի�)
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-11-7 ����10:12:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJtcyListSh(String xh){
		
		String sql = "select a.*,b.mc cygxmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx=b.dm where a.xh = ? order by guid ";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��д�����ģ�飬�㽭��ѧ�Ƿ�м���м�֤���������ҳ�������ϸ����Ƿ�м�ֵΪ�ǣ�1����ʱ��
	 * ȡcjbh(ylzd4)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-10-30 ����04:05:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
    public String getCjbh_10335(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select ylzd4 cjbh from xg_xszz_new_jtqkdcb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "cjbh");
	}
    
    
    /**
     * 
     * @����: ����
     * @���ߣ������� [���ţ�1123]
     * @���ڣ�2016-5-16 ����10:32:08
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @param user
     * @return
     * @throws Exception
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getAllList(JtqkdcForm model,
			User user) throws Exception {
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String view = "xg_view_xszz_new_jtqkdc";
		StringBuilder sql = new StringBuilder();

		sql.append("select t1.*,t2.sydm,t3.symc from (select * from ");
		sql.append(view);
		sql.append(" a ) t1 ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_XTWH_SYDMB t3 on t2.sydm = t3.sydm");
		sql.append(" where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return dao.getListNotOut(sql.toString(), inputV);
	}
    
    
    /**
	 * ��ͥ��Ա�б�(���4��)
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyListFour(String xh){
		
		String sql = " select a.* from (select rownum n, a.*, b.mc cygxmc, nvl(c.mc, a.cyjkzk) cyjkzkmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx = b.dm left join xg_xsxx_jkztb c on a.cyjkzk = c.dm where a.xh = ? order by cygx) a where a.n <= 4 ";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * @������ͨ��sql��ȡǰn����ͥ��Ա�б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��6�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getJtcyList(String xh,String n){
		String sql = "select t.* from (select a.*, b.mc cygxmc, nvl(c.mc, a.cyjkzk) cyjkzkmc from xg_xszz_new_jtcyb a "
				+ " left join xszz_jtcygxb b on a.cygx = b.dm left join xg_xsxx_jkztb c on a.cyjkzk = c.dm where a.xh = ? order by cygx)t where rownum<= ? ";
		return dao.getListNotOut(sql, new String[]{xh,n});
	}
	
	/**
	 * @����: ���ݺ�ʵ�˵��û�����ѯ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-8 ����11:27:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String yhxm (String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select xm yhxm from yhb where yhm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{yhm}, "yhxm");
	}
	
	/**
	 * @����: ����ѧ�Ż�ȡ��ͥ�������
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-9-11 ����05:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getJtsrqkByXh (String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select jtnzsr,jtnzsr / case when rs is null or rs = 0 then 1 else rs end jtrjnsr ");
		sql.append("from (select sum(nvl(cynsr, 0)) jtnzsr, count(*) rs from xg_xszz_new_jtcyb where xh = ?)");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
}
