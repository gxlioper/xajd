package xgxt.xszz.tjgy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class XfjmDAO {

	private DAO dao = DAO.getInstance();
	
	
	/**
	 * �����Ŀ������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addXmsz(XfjmForm model) throws Exception{
		
		String sql = "insert into xg_tjgy_xszz_xfjmb(xmid,xmmc,sqkssj,sqjssj,sfqy) values (?,?,?,?,?)";
		
		return dao.runUpdate(sql, new String[]{model.getXmid(),model.getXmmc(),model.getSqkssj(),model.getSqjssj(),model.getSfqy()});
	}
	
	
	
	/**
	 * �޸���Ŀ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateXmsz(XfjmForm model) throws Exception{
		
		String sql = "update xg_tjgy_xszz_xfjmb set xmmc=?,sqkssj=?,sqjssj=?,sfqy=? where xmid=?";
		
		return dao.runUpdate(sql, new String[]{model.getXmmc(),model.getSqkssj(),model.getSqjssj(),model.getSfqy(),model.getXmid()});
	}
	
	
	/**
	 * ��Ŀ��������
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean addXmtj(XfjmForm model) throws SQLException{
		
		String[] tjmc = model.getTjmc();
		
		if (null != tjmc && tjmc.length > 0){
			
			List<String[]> params = new ArrayList<String[]>();
			String sql = "insert into xg_tjgy_xszz_xfjmtjb(xmid,tjmc) values (?,?)";
			
			for (int i = 0 ; i < tjmc.length ; i++){
				if (StringUtils.isNotNull(tjmc[i])){
					params.add(new String[]{model.getXmid(),tjmc[i]});
				}
			}
			
			int[] result = dao.runBatch(sql, params);
			return dao.checkBatchResult(result);
		}
		
		return true;
	}
	
	
	/**
	 * ɾ����Ŀ����
	 * @param xmid
	 * @return
	 * @throws Exception 
	 */
	public boolean delXmtj(String xmid) throws Exception{
		
		String sql = "delete from xg_tjgy_xszz_xfjmtjb where xmid=?";
		
		return dao.runUpdate(sql, new String[]{xmid});
	}
	
	
	
	/**
	 * ��Ŀ����ͳ�Ʋ�ѯ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getXmszList(XfjmForm model){
		
		String[] input = new String[]{};
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,rownum r from (select t1.xmid,t1.xmmc,t1.sqkssj,t1.sqjssj,t1.sfqy,")
		   .append("(select count(1) from xg_tjgy_xszz_xfjmtjb t2 ")
		   .append("where t1.xmid=t2.xmid) tjs,")
		   .append("(select count(1) from xg_tjgy_xszz_xfjmsqb t3 ")
		   .append("where t1.xmid=t3.xmid) zsqs,")
		   .append("(select count(1) from xg_tjgy_xszz_xfjmsqb t3 ")
		   .append("where t1.xmid=t3.xmid and t3.xn=(select dqxn from xtszb)) bxnsqs ")
		   .append("from xg_tjgy_xszz_xfjmb t1 ");
		
		if (StringUtils.isNotNull(model.getXmmc())){
			sql.append("where xmmc like '%'||?||'%'");
			input = new String[]{model.getXmmc()};
		}
		sql.append(") a where 1=1 ");
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(),sql.toString(),input);
		// dao.getListNotOut(sql.toString(), input);
	}
	
	
	/**
	 * ��ѯ��Ŀ������Ϣ
	 * @param xmid
	 * @return
	 */
	public HashMap<String,String> getXmszInfo(String xmid){
		
		String sql = "select t1.*,(select count(1) from xg_tjgy_xszz_xfjmsqb t3 where t1.xmid=t3.xmid) ysqrs from xg_tjgy_xszz_xfjmb t1 where xmid=?";
		return dao.getMapNotOut(sql, new String[]{xmid});
	}
	
	
	/**
	 * ��ѯ��Ŀ�����õ�����
	 * @param xmid
	 * @return
	 */
	public List<HashMap<String,String>> getXmtjList(String xmid){
		String sql = "select * from xg_tjgy_xszz_xfjmtjb where xmid=?";
		return dao.getListNotOut(sql, new String[]{xmid});
	}
	
	
	/**
	 * ɾ����Ŀ��Ϣ
	 * @param xmid
	 * @return
	 * @throws Exception 
	 */
	public boolean delXmsz(String[] xmid) throws Exception{
		
		StringBuilder delXmsz = new StringBuilder();
		StringBuilder delXmtj = new StringBuilder();
		
		delXmsz.append("delete from xg_tjgy_xszz_xfjmb where xmid in (");
		delXmtj.append("delete from xg_tjgy_xszz_xfjmtjb where xmid in (");
		
		for (int i = 0 ; i < xmid.length ; i++){
			delXmsz.append("'")
				   .append(xmid[i])
				   .append("'");
			delXmtj.append("'")
				   .append(xmid[i])
				   .append("'");
			
			if (i == xmid.length-1){
				delXmsz.append(")");
				delXmtj.append(")");
			} else {
				delXmsz.append(",");
				delXmtj.append(",");
			}
		}
		
		return dao.runUpdate(delXmsz.toString(), new String[]{}) && dao.runUpdate(delXmtj.toString(), new String[]{});
	}
	
	
	
	/**
	 * ����ѧ�Ų�ѯ������Ŀ���������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXmsqInfoByXh(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.xmid,t1.xmmc,t1.sqkssj,t1.sqjssj,")
		   .append("case when sysdate between to_date(t1.sqkssj,")
		   .append("'yyyy-mm-dd hh24:mi:ss') and to_date(t1.sqjssj,")
		   .append("'yyyy-mm-dd hh24:mi:ss') then 'zsqsj' else 'bzsqsj' end sfzsqsj,")
		   .append("? xh,t2.xysh from xg_tjgy_xszz_xfjmb t1 ")
		   .append("left join (select * from xg_tjgy_xszz_xfjmsqb ")
		   .append("where xn=(select dqxn from xtszb) and xh=?) t2 ")
		   .append("on t1.xmid = t2.xmid where t1.sfqy='��'");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh});
	}
	
	
	
	/**
	 * ������Ŀ������Ϣ
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean saveXmsqInfo(String[] input) throws Exception{
		
		String sql = "insert into xg_tjgy_xszz_xfjmsqb(xmid,xh,xn,yjxf,jmxf,sjhm,sqtj,bz) values (?,?,?,?,?,?,?,?)";
		
		return dao.runUpdate(sql, input);
	}
	
	
	
	/**
	 * �޸���Ŀ������Ϣ
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean updateXmsqInfo(String[] input) throws Exception{
		
		String sql = "update xg_tjgy_xszz_xfjmsqb set yjxf=?,jmxf=?,sjhm=?,sqtj=?,bz=? where xmid=? and xh=? and xn=?";
		
		return dao.runUpdate(sql, input);
	}


	
	/**
	 * ��ѯ������Ŀ������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXmsqInfo(XfjmForm model){
		
		String sql = "select * from xg_tjgy_xszz_xfjmsqb where xmid||xh||xn=?";
		
		return dao.getMapNotOut(sql, new String[]{model.getPkValue()});
	}
	
	
	
	/**
	 * ��Ŀ�����¼����˲�ѯ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXmsqList(XfjmForm model,User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,rownum r from (select t1.xmid,t1.xh,t1.xn,t1.yjxf,")
		   .append("t1.jmxf,t1.xysh,t1.xxsh,t2.xmmc,v.xm,v.nj,v.xydm,v.xymc,v.zydm,")
		   .append("v.zymc,v.bjdm,v.bjmc from xg_tjgy_xszz_xfjmsqb t1 ")
		   .append("left join xg_tjgy_xszz_xfjmb t2 on t1.xmid=t2.xmid ")
		   .append("left join view_xsjbxx v on t1.xh=v.xh where t1.xn=(select dqxn from xtszb)) a where 1=1 ")
		   .append(searchTj)
		   .append(searchTjByUser);
		
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(),sql.toString(),inputV);
	}


	
	/**
	 * ���浥�����
	 * @param field
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean saveDgsh(String[] field,String[] input) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_tjgy_xszz_xfjmsqb set ");
		for (int i = 0 ; i < field.length ; i++){
			sql.append(field[i])
			   .append("=?");
			
			if (i != field.length-1){
				sql.append(",");
			}
		}
		sql.append(" where xmid=? and xh=? and xn=?");
		return dao.runUpdate(sql.toString(),input);
	}
	
	
	
	/**
	 * ͳ�Ʒǵ�ǰ�����Ŀ�Ѿ�ͨ���ļ�¼��
	 * @param model
	 * @return
	 */
	public String getYshNotThisXm(XfjmForm model){
		
		String sql = "select count(1) count from xg_tjgy_xszz_xfjmsqb where xh=? and xmid<>? and xn=(select dqxn from xtszb) and xysh='ͨ��'";
		
		return dao.getOneRs(sql, new String[]{model.getXh(),model.getXmid()}, "count");
	}
	
	
	
	/**
	 * ������˱���
	 * @param field
	 * @param input
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	public boolean plxmsh(String[] field , String[] input, String[] pkValue) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		List<String[]> params = new ArrayList<String[]>();
		
		sql.append("update xg_tjgy_xszz_xfjmsqb set ");
		for (int i = 0 ; i < field.length ; i++){
			sql.append(field[i])
			   .append("=?");
			
			if (i != field.length-1){
				sql.append(",");
			}
		}
		sql.append(" where xmid||xh||xn=?");
		
		for (int i = 0 ; i < pkValue.length ; i++){
			params.add(StringUtils.joinStrArr(input,new String[]{pkValue[i]}));
		}
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);	
	}
	
	
	
	/**
	 * �����ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getJgcxList(XfjmForm model,User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,rownum r from (select t1.xmid,t1.xh,t1.xn,t1.yjxf,")
		   .append("t1.jmxf,t1.xysh,t1.xxsh,t2.xmmc,v.xm,v.nj,v.xydm,v.xymc,v.zydm,")
		   .append("v.zymc,v.bjdm,v.bjmc from xg_tjgy_xszz_xfjmsqb t1 ")
		   .append("left join xg_tjgy_xszz_xfjmb t2 on t1.xmid=t2.xmid ")
		   .append("left join view_xsjbxx v on t1.xh=v.xh) a where 1=1 ")
		   .append(searchTj)
		   .append(searchTjByUser);
		
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(),sql.toString(),inputV);
	}
	
	
	
	/**
	 * ɾ����Ŀ������Ϣ
	 * @param pkValues
	 * @return
	 * @throws Exception
	 */
	public boolean delXmsq(String[] pkValues) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_tjgy_xszz_xfjmsqb where xmid||xh||xn in (");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			sql.append("'")
			   .append(pkValues[i])
			   .append("'");
			if (i != pkValues.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * ����ͳ�Ƶ�������
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getExpXmsqList(XfjmForm model,User user) throws Exception{
		
		String[] colList = new String[]{"r","xymc","xh","xm","xb","zybj","yjxf","jmxf","bz","sjhm"};
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,a.zymc||','||a.bjmc zybj,rownum r from (select t1.xmid,t1.xh,t1.xn,t1.yjxf,")
		   .append("t1.jmxf,t1.xysh,t1.xxsh,t2.xmmc,v.xm,v.nj,v.xydm,v.xymc,v.zydm,t1.bz,t1.sjhm,")
		   .append("v.zymc,v.bjdm,v.bjmc,v.xb from xg_tjgy_xszz_xfjmsqb t1 ")
		   .append("left join xg_tjgy_xszz_xfjmb t2 on t1.xmid=t2.xmid ")
		   .append("left join view_xsjbxx v on t1.xh=v.xh) a where 1=1 ")
		   .append(searchTj)
		   .append(searchTjByUser);
		
		return CommonQueryDAO.commonPageQuery(model.getPages(),sql.toString(),inputV,colList);
	}
}
