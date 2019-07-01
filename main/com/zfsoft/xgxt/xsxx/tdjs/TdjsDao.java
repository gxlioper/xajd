package com.zfsoft.xgxt.xsxx.tdjs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * �Ŷӽ���DAO
 * @author qph
 * 2013-4-8
 */
public class TdjsDao extends SuperDAOImpl<TdjsForm>{



	/**
	 * ��ͨ��ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(TdjsForm model) throws Exception{
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm zdls from(");
		sql.append("select t1.nj,t1.bmdm,t1.zydm,t1.bjdm,nvl(t4.bjrs,0) bjrs,");
		sql.append("t1.bjmc,t2.bmmc,t3.zymc,");
		sql.append("(select zgh from fdybjb t4 where t1.bjdm=t4.bjdm and rownum = 1) zgh ");
		sql.append("from bks_bjdm t1 ");
		sql.append("left join zxbz_xxbmdm t2 on t1.bmdm=t2.bmdm ");
		sql.append("left join bks_zydm t3 on t3.zydm=t1.zydm and t3.bmdm=t1.bmdm ");
		sql.append("left join (select bjdm,count(1) bjrs from view_xsjbxx group by bjdm) t4 on t1.bjdm=t4.bjdm ");
		sql.append("where 1=1 ");
		
		if (!StringUtil.isNull(model.getNj())){
			sql.append(" and t1.nj = ? ");
			params.add(model.getNj());
		}
		if (!StringUtil.isNull(model.getBmdm())){
			sql.append(" and t1.bmdm = ? ");
			params.add(model.getBmdm());
		}
		if (!StringUtil.isNull(model.getZydm())){
			sql.append(" and t1.zydm = ? ");
			params.add(model.getZydm());
		}
		if (!StringUtil.isNull(model.getBjmc())){
			sql.append(" and t1.bjmc like '%'||?||'%' ");
			params.add(model.getBjmc());
		}
		
		sql.append(") a left join fdyxxb b on a.zgh=b.zgh where 1=1 ");
		
		if (!StringUtil.isNull(model.getZdls())){
			sql.append(" and (b.xm like '%'||?||'%' or a.zgh=?) ");
			params.add(model.getZdls());
			params.add(model.getZdls());
		}
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}
	
	
	
	protected void setTableInfo() {
		super.setTableName("bks_bjdm");
		super.setKey("bjdm");
	}



	@Override
	public List<HashMap<String, String>> getPageList(TdjsForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	
	/**
	 * �Ŷ�ָ����ʦɾ�����������Ƿ��д�ѧ��
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public int delZdls(String bjdm) throws Exception{
		
		String sql = "delete from fdybjb where bjdm=?";
		
		return dao.runDelete(sql, new String[]{bjdm});
	}
	
	
	/**
	 * �Ŷ�ɾ��
	 */
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from bks_bjdm t1 where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("bjdm=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xsxxb t2 where t1.bjdm=t2.bjdm)");
		
		return dao.runDelete(sql.toString(), values);
	}

	
	/**
	 * �Ŷ�ָ����ʦɾ�� 
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public int delZdls(String[] values) throws Exception{
		
		if (values == null || values.length == 0){
			logger.error("ɾ��ָ����ʦ�������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from fdybjb t1 where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("bjdm=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xsxxb t2 where t1.bjdm=t2.bjdm)");
		return dao.runDelete(sql.toString(), values);
	}

	
	/**
	 * ����ָ����ʦ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZdls(TdjsForm model) throws Exception{
		
		String sql = "insert into fdybjb (zgh,bjdm) values (?,?)";
		
		return dao.runUpdate(sql, new String[]{model.getZgh(),model.getBjdm()});
	}



	/**
	 * ��ѯ�Ŷ���Ϣ
	 */
	public TdjsForm getModel(TdjsForm t) throws Exception {
		
		StringBuilder sql = new StringBuilder("select a.*,b.xm zdls from ");
		sql.append("( select t1.*,(select zgh from fdybjb t2 ");
		sql.append("where t1.bjdm=t2.bjdm and rownum=1) zgh ");
		sql.append("from bks_bjdm t1 where bjdm=?) a left join fdyxxb b on a.zgh=b.zgh");
		
		return super.getModel(t, sql.toString(), new String[]{t.getBjdm()});
	}
	
	
	
	/**
	 * ����ְ���Ų�ָ����ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getZdlsInfo(TdjsForm model){
		
		String sql = "select zgh,xm from fdyxxb where zgh=?";
		
		return dao.getMapNotOut(sql, new String[]{model.getZgh()});
	}
	


	/**
	 * �����Ŷ�
	 * @param zghList
	 * @return
	 */
	public List<HashMap<String,String>> sctdInfo(List<String> zghList){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select zgh,xm zdls,bmdm from fdyxxb where (");
		
		for (int i = 0, n = zghList.size() ; i < n ; i++){
			sql.append("zgh=?");
			if (i != n - 1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), zghList.toArray(new String[]{}));
	}


	
	/**
	 * ��ȡרҵ�б�
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(String xydm){
		
		String[] params = null;
		StringBuilder sql = new StringBuilder("select distinct zydm,zymc from bks_zydm t1 ");
		
		if (!StringUtils.isNull(xydm)){
			//where bmdm=? 
			sql.append("where bmdm=? ");
			params = new String[]{xydm};
		}
		
		return dao.getListNotOut(sql.toString(), params);
	}
	
	
	
	/**
	 * ��ȡ�༶����ID
	 * @return
	 * @throws SQLException
	 */
	public int getNewBjdmID() throws SQLException{
		
		String sql = "select SEQ_BKS_BJDM.NEXTVAL from bks_bjdm where rownum=1";
		return dao.getOneRsint(sql);
	}
	
	
	
	/**
	 * �����Ŷ���Ϣ
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean saveTdinfo(List<String[]> params) throws SQLException{
		
		String sql = "insert into bks_bjdm(nj,bmdm,zydm,bjmc,bjdm) values (?,?,?,?,?)";
		
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	
	/**
	 * �����Ŷ���ָ����ʦ��ϵ
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean saveDbInfo(List<String[]> params) throws SQLException{
		
		String sql = "insert into fdybjb(zgh,bjdm) values (?,?)";
		
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}




	/**
	 * ��ȡѧԺ�б���ʹ��BASE.JAVA��
	 * @return
	 */
	public List<HashMap<String,String>> getXyList(){
		//where exists (select 1 from xsxxb t2 where t1.bmdm=t2.xydm)
		String sql = "select bmdm xydm,bmmc xymc from zxbz_xxbmdm t1 ";
		
		return dao.getListNotOut(sql, new String[]{});
	}


	/**
	 * ��ȡרҵ�б���ʹ��BASE.JAVA��
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(){
		// where exists (select 1 from xsxxb t2 where t1.zydm=t2.zydm)
		String sql = "select zydm,zymc,bmdm from bks_zydm t1";
		
		return dao.getListNotOut(sql, new String[]{});
	}
	
	
	/**
	 * ��ȡ�༶�б���ʹ��BASE.JAVA��
	 * @return
	 */
	public List<HashMap<String,String>> getBjList(){
		//where exists (select 1 from xsxxb t2 where t1.bjdm=t2.bjdm)
		String sql = "select bjdm,bjmc from bks_bjdm t1 ";
		
		return dao.getListNotOut(sql, new String[]{});
	}


	/**
	 * ��ȡ�꼶�б���ʹ��BASE.JAVA��
	 * @return
	 */
	public List<HashMap<String,String>> getNjList(){
		
		String sql = "select distinct nj from xsxxb where nj is not null order by nj";
		
		return dao.getListNotOut(sql, new String[]{});
	}



	
	/**
	 * ���ݰ༶��������ŶӼ�ָ����ʦ��Ϣ
	 * @param values
	 * @return
	 */
	public List<HashMap<String,String>> getTdinfoList(String[] values){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,b.xm from ( ");
		sql.append("select t1.nj,t1.bmdm,t1.zydm,t1.bjdm,t1.bjmc,t2.zymc,t3.bmmc,");
		sql.append("(select zgh from fdybjb t4 where t1.bjdm=t4.bjdm and rownum=1) zgh ");
		sql.append("from bks_bjdm t1 left join bks_zydm t2 on t1.zydm=t2.zydm ");
		sql.append("left join zxbz_xxbmdm t3 on t1.bmdm=t3.bmdm where (");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("bjdm=?");
			if (i != n -1){
				sql.append(" or ");
			}
		}
		
		sql.append(")) a left join fdyxxb b on a.zgh=b.zgh ");
		
		return dao.getListNotOut(sql.toString(), values);
	}



	/**
	 * ��������ѧ�������༶
	 * @param bjdm
	 * @param xhs
	 * @return
	 * @throws Exception
	 */
	public boolean batchUpdateBjdm(String bjdm,List<String> xhs) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xsxxb set bjdm=? where (");
		
		for (int i = 0 , n = xhs.size(); i < n ; i++){
			sql.append("xh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		xhs.add(0, bjdm);
		
		return dao.runUpdate(sql.toString(), xhs.toArray(new String[]{}));
	}

	
	/**
	 * 
	 * @����:���༶����ѧ���б�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-10 ����01:58:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStudentsByBjdm(TdjsForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where bjdm=?");
		
		List<String> params = new ArrayList<String>();
		params.add(model.getBjdm());
		
		if (!StringUtil.isNull(model.getXh())){
			
			sql.append(" and xh like '%'||?||'%'");
			
			params.add(model.getXh());
		}
		
		if (!StringUtil.isNull(model.getXm())){
			sql.append(" and xm like '%'||?||'%'");
			params.add(model.getXm());
		}
		
		return super.getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

}
