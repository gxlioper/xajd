package xsgzgl.szdw.teainfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class TeaInfoDAO {

	DAO dao = DAO.getInstance();
	
	
	
	/**
	 * ����ְ���Ų�ѯ�Ƿ����
	 * @param model
	 * @return
	 */
	public String getTeaCount(TeaInfoForm model){
		
		String sql = "select count(1) count from fdyxxb where zgh=?";
		
		return dao.getOneRs(sql, new String[]{model.getZgh()}, "count");
	}
	
	
	
	/**
	 * ���ӽ�ʦ��һ��---����ְ���š�����
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean addTea(TeaInfoForm model) throws SQLException{
		
		String sql = "insert into fdyxxb (zgh,xm) values (?,?)";
		
		return dao.insert(sql, new String[]{model.getZgh(),model.getXm()});
	}
	
	
	
	/**
	 * ����ְ���Ų�ѯ��ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public Map<String,String> getTeaInfo(String zgh){
		
		String sql = "select * from fdyxxb where zgh = ?";
		
		return dao.getMapNotOut(sql, new String[]{zgh});
	}
	
	
	/**
	 * ����ְ���б�
	 * @return
	 */
	public List<HashMap<String,String>> getZwList(){
		
		String sql = "select zwdm,zwmc from zwb order by zwdm";		
		
		return dao.getList(sql, new String[] {}, new String[] { "zwdm","zwmc" });
	} 
	
	
	
	/**
	 * �����ʦ��Ϣ
	 */
	public boolean saveTeaInfo(TeaInfoForm model,String updateField ,String[] input) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("update fdyxxb set ")
		   .append(updateField)
		   .append(" where zgh=?");
		
		return dao.runUpdate2(sql.toString(), StringUtils.joinStrArr(input,new String[]{model.getZgh()}));
	}
	
	
	
	/**
	 * ��ʦ��Ϣ��ѯ
	 */
	public List<HashMap<String,String>> getTeaList(TeaInfoForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder(); 
		sql.append("select a.*,rownum r from view_fdyxx a where 1=1 ")
		   .append(searchTj);
		
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(),sql.toString(),inputV);
	}
	
	/**
	 * ��ʦ��Ϣ��ѯ
	 */
	public List<String[]> getExpTeaList(TeaInfoForm model,String[] colList) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder(); 
		sql.append("select a.*,rownum r from view_fdyxx a where 1=1 ")
		   .append(searchTj);
		
		return CommonQueryDAO.commonPageQuery(model.getPages(),sql.toString(),inputV,colList);
	}
	
	
	/**
	 * ��ѯ��ʦ�Ĵ�����Ϣ
	 * @param zgh
	 * @return
	 */
	public List<HashMap<String,String>> getTeaClass(String[] zgh){
		
		StringBuilder zghSQL = new StringBuilder();
		
		for (int i = 0 ; i < zgh.length ; i++){
		 zghSQL.append("'")
			   .append(zgh[i])
			   .append("'");
			if (i != zgh.length - 1 ){
				zghSQL.append(",");
			}
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.bjdm,t2.bjmc,'����Ա����' dbbz from fdybjb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm ")
		   .append("where t1.zgh in (")
		   .append(zghSQL)
		   .append(") union all ")
		   .append("select t1.bjdm,t2.bjmc,'�����δ���' dbbz from bzrbbb t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm ")
		   .append("where t1.zgh in (")
		   .append(zghSQL)
		   .append(") ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * ɾ����ʦ��Ϣ
	 * @param zgh
	 * @return
	 * @throws Exception
	 */
	public boolean delTeaInfo(String[] zgh) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from fdyxxb where zgh in (");
		
		for (int i = 0 ; i < zgh.length ; i++){
			sql.append("'")
			   .append(zgh[i])
			   .append("'");
			if (i != zgh.length - 1 ){
				sql.append(",");
			}
		}
		
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * ��ѯ��չ�ֶ�������Ϣ
	 * @param gnbs
	 * @return
	 */
	public List<HashMap<String, String>> getZdxxList(String gnbs) {
		
		String sql = "select * from xg_szdw_kzxxpzb where xxdm=? and gnbs=? order by xsqy,xslx,xssx";

		return dao.getListNotOut(sql, new String[] { Base.xxdm, gnbs });
	}
	
	
	
	/**
	 * ��ѡ׼��
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String,String>> getColList(String tableName){
		
		String sql = "select comments,lower(column_name) column_name from user_col_comments where table_name=upper(?)";
		
		return dao.getListNotOut(sql, new String[]{tableName});
	}
	
	
	
	/**
	 * �����б����ñ����ݲ�ѯ
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String,String>> getDataList(String tableName){
		
		String sql = "select * from "+tableName;
		
		return dao.getListNotOut(sql, new String[]{});
	}
	
	
	
	/**
	 * ͬ����ʦ��Ϣ���û���
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	public boolean tbTeaInfo(TeaInfoForm model,String[] pkValue) throws SQLException{
		
		StringBuilder insertYhbSQL = new StringBuilder();
		StringBuilder insertQxbSQL = new StringBuilder();
		Encrypt encrypt = new Encrypt();
		String password = encrypt.encrypt("888888");
		String dwdm = model.getDwdm();
		String zdm = model.getZdm();
		
		insertYhbSQL.append("insert into yhb (yhm, xm, szbm, kl, dwdm, zdm,qx) ")
					.append("select a.zgh,a.xm,a.bmdm,?,?,?,'1' ")
					.append("from fdyxxb a where a.zgh = ? ")
					.append("and not exists (select 1 from yhb where yhm = a.zgh)");
		
		insertQxbSQL.append("insert into yhqxb (yhm, gnmkdm, dxq) select ?,")
					.append("a.gnmkdm, a.dxq from yhzqxb a where a.zdm = ? ")
					.append("and not exists (select 1 from yhqxb where yhm = ?) ");
		
		List<String[]> yhbParams = new ArrayList<String[]>();
		List<String[]> qxbParams = new ArrayList<String[]>();
		
		for (int i = 0 ; i < pkValue.length; i++){
			yhbParams.add(new String[]{password,dwdm,zdm,pkValue[i]});
			qxbParams.add(new String[]{pkValue[i],zdm,pkValue[i]});
		}
		
		int[] yhbResult = dao.runBatch(insertYhbSQL.toString(), yhbParams);
		int[] qxbResult = dao.runBatch(insertQxbSQL.toString(), qxbParams);
		return dao.checkBatchResult(yhbResult) && dao.checkBatchResult(qxbResult);
	}
	
	
	/**
	 * ���ظ�λ����б�
	 * @return
	 */
	public List<HashMap<String,String>> getGwlbList(){
		
		String sql = "select gwlbdm,gwlbmc from xg_szdw_gwlb order by gwlbdm";
		
		return dao.getList(sql, new String[] {}, new String[] { "gwlbdm","gwlbmc" });
	}
	
	/**
	 * ���ؽ�ʦ����б�
	 * @return
	 */
	public List<HashMap<String,String>> getJslbList(){
		
		String sql = "select jslbdm,jslbmc from XG_SZDW_JSLB order by jslbdm";
		
		return dao.getList(sql, new String[] {}, new String[] { "jslbdm","jslbmc" });
	}
	
	/**
	 * ����ְ���б�
	 * @return
	 */
	public List<HashMap<String,String>> getZcList(){
		
		String sql = "select zcdm,zcmc from zcb order by zcdm";
		
		return dao.getList(sql, new String[] {}, new String[] { "zcdm","zcmc" });
	}
	/**
	 * ���ر����б�
	 * @return
	 */
	public List<HashMap<String,String>> getBzlxList(){
		
		String sql = "select lxdm,lxmc from fdybzlxb order by lxdm";
		
		return dao.getList(sql, new String[] {}, new String[] { "lxdm","lxmc" });
	}
	/**
	 * ����רְ����Ա��������б�
	 * @return
	 */
	public List<HashMap<String,String>> getZzFdyList(){
		
		String sql = "select lxdm,lxmc from zzfdysflxb order by lxdm";
		
		return dao.getList(sql, new String[] {}, new String[] { "lxdm","lxmc" });
	}
	
}
