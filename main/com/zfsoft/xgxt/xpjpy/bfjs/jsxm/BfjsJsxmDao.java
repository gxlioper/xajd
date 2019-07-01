/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-22 ����02:45:33 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.jsxm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.bfjs.fswh.BfjsFswhModel;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��羺����Ŀ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-4-1 ����02:02:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BfjsJsxmDao extends SuperDAOImpl<BfjsJsxmModel> {

	protected void setTableInfo() {
		super.setTableName("xg_qzxy_bfjs_jsxm");
		super.setKey("xmdm");
		super.setClass(BfjsJsxmModel.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(BfjsJsxmModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(BfjsJsxmModel t, User user)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��ѯ������Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����02:02:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBfjsJsxmList(String xn,String xq){
		
		String sql = "select * from xg_qzxy_bfjs_jsxm where xn=? and xq=? order by xmmc";
		
		return dao.getListNotOut(sql, new String[]{xn,xq});
	}
	
	public List<HashMap<String,String>> getBfjsJsxmList2(String xn,String xq){
		
		String sql = "select xmdm,xmmc,fjdm,xmlx,qzbl,mrfs,jktb,nvl(zdfz,' ') zdfz,zxfz from xg_qzxy_bfjs_jsxm where xn=? and xq=? and (fjdm='N' or fjdm =(select xmdm from xg_qzxy_bfjs_jsxm t where t.xn=? and t.xq=? and fjdm='N'))";
		
		return dao.getListNotOut(sql, new String[]{xn,xq,xn,xq});
	}
	
	
	/**
	 * 
	 * @����:ɾ��������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����02:02:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXnBfjsJsxm(String xn) throws Exception {
		String sql = "delete from xg_qzxy_bfjs_jsxm where xq = 'on' and xn = ? ";
		return dao.runUpdate(sql, new String[]{xn});
	}
	
	
	
	
	
	/**
	 * 
	 * @����:������ʼ��������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����02:03:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean initBfjsJsxmList(List<String[]> params) throws SQLException{
		
		String sql = "insert into xg_qzxy_bfjs_jsxm(xmdm,xn,xq,xmmc,fjdm,xmlx,qzbl,mrfs,zdfz,zxfz,pfsm) values (?,?,?,?,?,?,?,?,?,?,?)";
		
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	
	/**
	 * 
	 * @����:ɾ��������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����02:03:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delBfjsJsxm(String xmdm) throws Exception{
		
		String sql = "delete from xg_qzxy_bfjs_jsxm where xmdm=? or fjdm=?";
		
		return dao.runDelete(sql, new String[]{xmdm,xmdm});
	}

	
	/**
	 * 
	 * @����:��ѯϵͳ�еľ�����Ŀ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����02:03:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getBfjsJsxmCount() throws SQLException{
		
		String sql = "select count(1) num from xg_qzxy_bfjs_jsxm";
		
		return dao.getOneRsint(sql);
	}
		
	/**
	 * 
	 * @����: ��ѯ������ڵľ�����Ŀ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-04-07 ����02:45:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param isXqJs true ѧ�ھ���  false ѧ�꾺��
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZjzqBfjsJsxm(boolean isXqjs){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_qzxy_bfjs_jsxm where substr(xn,6,9)||xq=(select max(substr(xn,6,9)||xq) from xg_qzxy_bfjs_jsxm");
		//ѧ�ھ���
		if (isXqjs){
			sql.append(" where xq <> 'on' ");
		}
		sql.append(" )");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����:����ѧ���ȡ���ѧ�ھ�����Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXnBfjsJsxmForXn(String xn){
		
		String sql = "select * from xg_qzxy_bfjs_jsxm where xn||xq = (select ?||max(xq) from xg_qzxy_bfjs_jsxm where xq <> 'on' and xn = ?)";
		
		return dao.getListNotOut(sql, new String[]{xn,xn});
	}
	
	
	
	/**
	 * 
	 * @����: ��ѯ����¼�뾺���ֵľ�����Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getAllowEditJsfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_qzxy_bfjs_jsxm t1 where not exists (");
		sql.append("select 1 from xg_qzxy_bfjs_jsxm t2 where t1.xmdm=t2.fjdm");
		sql.append(") and (xmlx='1' or xmlx='2' or xmlx='4')  ");
		sql.append("and xn||xq=(select xn||xq from XG_QZXY_BFJS_CSSZ where rownum=1) order by fjdm,xmmc");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/**
	 * 
	 * @����: ���Ӽ���Ŀ�ľ�����Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getNoChildJsfxm(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_qzxy_bfjs_jsxm t1 where not exists (");
		sql.append("select 1 from xg_qzxy_bfjs_jsxm t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from XG_QZXY_BFJS_CSSZ where rownum=1) ");
		sql.append(" order by xssx,fjdm,xmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����:��ѯǰ����������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getFirstAndSecondBfjsJsxm(){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_qzxy_bfjs_jsxm t1 where (fjdm='N' ");
		sql.append(" or exists (select 1 from xg_qzxy_bfjs_jsxm t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" and t1.xn||t1.xq=(select xn||xq from XG_QZXY_BFJS_CSSZ where rownum=1) order by xmlx desc");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), new String[]{});
		
		return rs;
	}
	
	
	public List<HashMap<String,String>> getFirstAndSecondBfjsJsxm(BfjsFswhModel t){

		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();

		sql.append(" select distinct xmdm,xmmc,xmlx from xg_qzxy_bfjs_jsxm t1 where ( fjdm = 'N' or ");
		sql.append(" exists (select 1 from xg_qzxy_bfjs_jsxm t2 where t1.fjdm=t2.xmdm and t2.fjdm='N')) and ");
		sql.append(getXnxqSql(t, params));
		sql.append(" order by xmlx desc ");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
	
		return rs;
	}

	
	public String getXnxqSql(BfjsFswhModel t, List<String> params){
		StringBuilder xnxqSql = new StringBuilder();
		if(StringUtils.isNull(t.getJszq())||t.getJszq().equals("xq")){
			if(null==t.getSearchModel().getSearch_tj_xq()||""==t.getSearchModel().getSearch_tj_xq()[0]){
				xnxqSql.append("t1.xn in ( ");
			}else{
				xnxqSql.append("t1.xn||t1.xq in ( ");
			}
		}else{
			xnxqSql.append("t1.xn||t1.xq in ( ");
		}
		for(int i=0; i< t.getSearchModel().getSearch_tj_xn().length; i++){
			if(StringUtils.isNull(t.getJszq())||t.getJszq().equals("xq")){
				//���ѧ��Ϊ�վͲ�ƴ��ѧ��
				if(null==t.getSearchModel().getSearch_tj_xq()){
					if(i==0){
						xnxqSql.append("?");
					}else{
						xnxqSql.append(",?");
					}
					params.add(t.getSearchModel().getSearch_tj_xn()[i]);
				}else{
					for(int j=0; j<t.getSearchModel().getSearch_tj_xq().length;j++){
						if(i==0&&j==0){
							xnxqSql.append("?");
						}else{
							xnxqSql.append(",?");
						}
						params.add(t.getSearchModel().getSearch_tj_xn()[i]+t.getSearchModel().getSearch_tj_xq()[j]);
					}
				}
				
			}else{
				if(i==0){
					xnxqSql.append("?");
				}else{
					xnxqSql.append(",?");
				}
				params.add(t.getSearchModel().getSearch_tj_xn()[i]+"on");
				}
 			}
		xnxqSql.append(" ) ");
		return xnxqSql.toString();
	}
	
	public List<HashMap<String,String>> getFirstAndSecondBfjsJsxm(String xn, String xq){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_qzxy_bfjs_jsxm t1 where (fjdm='N' ");
		sql.append(" or exists (select 1 from xg_qzxy_bfjs_jsxm t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" and t1.xn||t1.xq='"+xn+xq+"' order by xmlx desc");
		List<HashMap<String,String>> rs = dao.getListNotOut(sql.toString(), new String[]{});
		
		return rs;
	}
	
	
	/**
	 * 
	 * @����: ����������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZhcpTjxm(){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xmdm dm,t1.xn, t1.xn||'ѧ��'||t3.xqmc||t1.xmmc mc ");
		sql.append(" from xg_qzxy_bfjs_jsxm t1 left join xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(" where (t1.fjdm='N' or exists (select 1 from xg_qzxy_bfjs_jsxm t2 where t1.fjdm=t2.xmdm and t2.fjdm='N'))");
		sql.append(" order by t1.xn desc,t3.xqmc desc  ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @����: ��ѯ��ǰ���ڵ��ܷ���Ŀ
	  * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentZfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_qzxy_bfjs_jsxm t1 where fjdm = 'N' and exists (");
		sql.append("select 1 from XG_QZXY_BFJS_CSSZ t2 where t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(")");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}


	
	
	
	/**
	 * 
	 * @����: ��ѯ�ɵ�����ϸ�����ľ�����Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����04:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBfjsJsxmListByXxbl(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.xmdm,t1.xmmc,t1.fjdm,t2.fjdm ffjdm,t1.qzbl,");
		sql.append("'BfjsJsxm'|| rownum name from xg_qzxy_bfjs_jsxm t1 ");
		sql.append("left join xg_qzxy_bfjs_jsxm t2 on t1.fjdm = t2.xmdm ");
		sql.append("where t1.fjdm <> 'N' and t1.xmlx <> '3' ");
		sql.append("and t1.xn||t1.xq=(select xn||xq from XG_QZXY_BFJS_CSSZ)");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String,String>> getSzyfList(){
		StringBuilder sql = new StringBuilder();
		 sql.append("select case when a.xq = '01' then (substr(b.xn, 1, 4)) || yf else (substr(b.xn, 6, 9)) || yf");
         sql.append(" end szyf, case  when a.xq = '01' then (substr(b.xn, 1, 4)) || '��' || yf || '��' else");
         sql.append(" (substr(b.xn, 6, 9)) || '��' || yf || '��' end szyfmc from xg_pjpy_new_yfpz a, XG_QZXY_BFJS_CSSZ b");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/**
	 * 
	 * @����:��ѯ��ǰ����ѧ�����ֵ
	  * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-18 ����11:49:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getMaxJsxq(String xn) throws Exception {
		String sql = "select max(xq) xq from xg_qzxy_bfjs_jsxm where xn = ? and xq <> 'on'"	;
		
		return dao.getOneRs(sql, new String[]{xn}, "xq");
	}
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��Ŀ��Ϣ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-18 ����11:49:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getBfjsJsxm(String xmdm) throws Exception{
		String sql = "select * from xg_qzxy_bfjs_jsxm where xmdm = ?";
			
		return dao.getMapNotOut(sql, new String[]{xmdm});
	} 
	
	
	/**
	 * 
	 * @����:��ѯ�����Ƿ����
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-18 ����11:49:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xmmcExist(BfjsJsxmModel model){
		
		String sql = "select count(1) count from xg_qzxy_bfjs_jsxm where xn = ? and xq= ? and xmmc = ? ";
		
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXmmc()}, "count");
		
		return num;
	}
	
	
	/**
	 * 
	 * �ж��Ƿ�Ϊ��
	 * 
	 */
	public Boolean getIsblank(List<HashMap<String, String>> bjxxMap,BfjsFswhModel model){
		
		boolean isBlank = false;
		
		if(!org.apache.commons.lang.StringUtils.isBlank(model.getId())&&null!=bjxxMap&&0!=bjxxMap.size()){
			isBlank = true;
		}
		
		return isBlank;
	}
	


	

	
	
	
	
	/**
	 * 
	 * @����: ��ȡ��ǰ�������������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-18 ����02:51:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * List<String> �������� 
	 * @throws
	 */
	
	public List<HashMap<String, String>> getJktbS(String xmdm) throws SQLException{
		
		List<String> params = new ArrayList<String>();
		String[] xmdms = xmdm.split(",");
				
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from xg_qzxy_bfjs_jsxm where xmdm in (");
		for (int i = 0; i < xmdms.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmdms[i]);
		}
		
		sql.append(")");
		sql.append(" and jktb is not null ");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	

}
