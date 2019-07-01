package xgxt.xtwh.xtwhCriterion.jsgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class JsglDAO {

	
	/**
	 * @author luning
	 * @describe 获取角色操作范围列表
	 */
	public List<HashMap<String, String>> getJsczfwList() {
		DAO dao = new DAO();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		String sql = "select jscmdm,jscmmc,jscmsm from xg_xtwh_yhcmdmb";
		rychList = dao.getList(sql, new String[]{}, new String[]{"jscmdm", "jscmmc","jscmsm"});
		return rychList;
	}

	/**
	 * @author luning
	 * @describe 获取角色类型列表
	 */
	public List<HashMap<String, String>> getJslxList() {
		DAO dao = new DAO();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		String sql = "select jslxdm,jslxmc from xg_xtwh_jslxdmb";
		rychList = dao.getList(sql, new String[]{}, new String[]{"jslxdm", "jslxmc"});
		return rychList;
	}
	
	/**
	 * @author luning
	 * @param tableName 
	 * @param queryModel 
	 * @param colList 
	 * @describe 获取角色数据列表
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getJsQueryList(String tableName, JsglQueryModel queryModel, String[] colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "jslxdm", "jscmdm","sfqy" };
		String[] queryLikeList = new String[] { "jsmc"};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, queryModel);
		String query = myQuery.getQueryString() + "";
		return CommonQueryDAO.commonQuery(tableName, query, myQuery.getInputList(), colList, "", queryModel);
	}
	
	public Boolean BatchDel(String tableName, String[] delPkValue, String pkKey){
		StringBuilder sql = new StringBuilder("delete from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pkKey);
		sql.append("=?");
		DAO dao = DAO.getInstance();
		List<String []> params = new ArrayList<String []>();
		for(int i=0;i <delPkValue.length;i++){
			params.add(new String[]{delPkValue[i]});
		}
		Boolean flag = false;
		try {
			int[] res = dao.runBatch(sql.toString(),params);
			for (int i = 0; i < res.length; i++) {
				flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean jsToUser(String jsmc,String userName) {
		DAO dao = DAO.getInstance();
		Boolean update  = false; 
		String sql = "insert into xg_xtwh_yhjsb (jsdm,yhm,jsqx) select jsdm,?,? from xg_xtwh_jswhb where jsmc = ?";
		String [] input = new String[]{userName,"是",jsmc};
		try {
			update =  dao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}
	
	/**
	 * @author luning
	 * @param jsdm
	 * @param type 
	 * @return
	 */
	public List<HashMap<String, String>> getYhForJs(String jsdm, String type) {
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.yhm,b.xm,c.bmmc from xg_xtwh_yhjsb a,xg_xtwh_yhb b,");
		sql.append("zxbz_xxbmdm c where a.yhm = b.yhm and b.szbm =c.bmdm and a.jsdm=?");
		if(type.equalsIgnoreCase("gl")){
			sql.append(" and a.jsqx = '是'");
		}
		return dao.getList(sql.toString(), new String[]{jsdm}, new String[]{"yhm","xm","bmmc"});
	}

	public List<HashMap<String, String>> getGnmkList() {
			StringBuilder sql = new StringBuilder();
			
			sql.append("select gnmkdm dm,gnmkmc mc from gnmkdmb a where a.sfqy='是' ");
			sql.append("and gnmkdm like 'N__' order by gnmkdm");

			String[] inputs = new String[]{};
			String[] colList = new String[]{"dm", "mc"};
			
			return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}
	
	/**
	 * 获得某个功能模块的下级模块
	 * @param lv
	 * @param dm
	 * @param jsdm
	 * @param jsdm2 
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String lv,String dm, String userName, String jsdm) {
		StringBuilder sql = new StringBuilder();
		String[] inputs = null;
		String[] colList = null;
		
		if(StringUtils.isNull(jsdm)){
			if("3".equalsIgnoreCase(lv)){
				sql.append("select 'view' dm, '查询' mc, '查询' jxmc,'unchecked' styleclass from dual union select a.btndm dm,a.btnmc mc,")
					.append("(case when length(a.btnmc)>4 then substr(a.btnmc,0,4)||'...' else a.btnmc end) jxmc,'' disabled ")
					.append("from xg_view_xtwh_gnmkbtn a where a.gnmkdm=?");
				inputs = new String[]{dm};
			}else{
				sql.append("select gnmkdm dm,gnmkmc mc,(case when length(gnmkmc) > 4 then substr(gnmkmc,0,4)||'...' else gnmkmc end) jxmc,'unchecked' styleclass from gnmkdmb a ");
				sql.append("where sfqy='是' and gnmkdm like ? order by gnmkdm");
				inputs = new String[]{dm + "__"};
			}
		}else{
			if("3".equalsIgnoreCase(lv)){
				sql.append("select 'view' dm, '查询' mc, '查询' jxmc,'unchecked' styleclass from dual union select a.btndm dm,a.btnmc mc,")
					.append("(case when length(a.btnmc)>4 then substr(a.btnmc,0,4)||'...' else a.btnmc end) jxmc,'' disabled ")
					.append("from xg_view_xtwh_gnmkbtn a where a.gnmkdm=?");
				inputs = new String[]{dm};
			}else{
				sql.append("select gnmkdm dm,gnmkmc mc,(case when length(gnmkmc) > 4 then substr(gnmkmc,0,4)||'...' else gnmkmc end) jxmc,'unchecked' styleclass from gnmkdmb a ");
				sql.append("where sfqy='是' and gnmkdm like ? order by gnmkdm");
				inputs = new String[]{dm + "__"};
			}
		}
		
//		if("3".equalsIgnoreCase(lv)){	// 按钮级别
//		
//			if(StringUtils.isNull(jsdm)){
//				sql.append("select 'view' dm, '查询' mc, '查询' jxmc,'' disabled from dual union select a.btndm dm,a.btnmc mc,")
//					.append("(case when length(a.btnmc)>4 then substr(a.btnmc,0,4)||'...' else a.btnmc end) jxmc,'' disabled ")
//					.append("from xg_view_xtwh_gnmkbtn a where a.gnmkdm=?");
//				inputs = new String[]{dm};
//			}else{
//				sql.append("select dm,mc,jxmc,decode(b.cdgndm,'','','disabled') disabled ")
//					.append("from (select 'view' dm, '查询' mc, '查询' jxmc from dual ")
//					.append("union select a.btndm dm,a.btnmc mc,(case when length(a.btnmc)>4 then substr(a.btnmc,0,4)||'...' else a.btnmc end) jxmc ")
//					.append("from xg_view_xtwh_gnmkbtn a where gnmkdm=?)a left join ")
//					.append("(select cdgndm from xg_xtwh_jscdqxb a where gnmkdm=? and jsdm = ?)b on a.dm = b.cdgndm");
//				inputs = new String[]{dm, dm, jsdm};
//			}
//			colList = new String[]{"dm", "mc", "jxmc","disabled"};
//		}else{
//			sql.append("select gnmkdm dm,gnmkmc mc,(case when length(gnmkmc) > 4 then substr(gnmkmc,0,4)||'...' else gnmkmc end) jxmc from gnmkdmb a ")
//				.append("where exists (select 1 from xg_xtwh_jscdqxb b where b.gnmkdm = a.gnmkdm and b.jsdm= ?) and gnmkdm like ? order by gnmkdm");
//			
//			colList = new String[]{"dm", "mc", "jxmc"};
//			inputs = new String[]{userName, dm + "__"};
//		}
		colList = new String[]{"dm", "mc", "jxmc","styleclass"};
		List<HashMap<String, String>> list = DAO.getInstance().getList(sql.toString(), inputs, colList);
		
		if(list.isEmpty()){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "（无）");
			map.put("jxmc", "（无）");
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 获得某用户指定等级的功能模块
	 * @param lv:分 1， 2， 3
	 * @param userName
	 * @author sjf
	 * @return
	 */
	public List<HashMap<String, String>> getAllGnmkList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select gnmkdm dm,gnmkmc mc from gnmkdmb a where a.sfqy='是' ");
		
		String[] inputs = new String[]{};
		String[] colList = new String[]{"dm", "mc" };
		
		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}
	
	/**
	 * 获得指定功能模块的下级模块
	 * @return
	 * @author sjf
	 */
	public List<HashMap<String, String>> getGnmkList(String userName, String gnmkdm){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select gnmkdm dm,gnmkmc mc,(case when length(gnmkmc) > 4 then substr(gnmkmc,0,4)||'...' else gnmkmc end) jxmc from gnmkdmb a ")
			.append("where a.gnmkdm like ? ")
			.append("and exists(select 1 from (select distinct a.yhm,b.gnmkdm from ")
			.append("(select yhm,jsdm from xg_view_xtwh_yhjs where yhm=? and sfqy='是') a, ")
			.append("xg_xtwh_jscdqxb b where a.jsdm=b.jsdm union select a.yhm,a.gnmkdm from ")
			.append("xg_xtwh_yhcdqxb a where a.yhm=?) b where substr(b.gnmkdm,0,?) = a.gnmkdm) order by gnmkdm");
		
		String length = gnmkdm.length() == 3 ? "5" : "7";
		String[] colList = new String[]{"dm", "mc", "jxmc"};
		String[] inputs = new String[]{gnmkdm + "__", userName, userName, length};
		
		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}
	
	/**
	 * 获得功能按钮权限
	 * @param userName
	 * @return
	 * @author sjf
	 */
	public List<HashMap<String, String>> getBtnList(String jsdm){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.gnmkdm,a.btndm,a.btnmc,decode(b.jsdm,'','','yes') jsyy ")
			.append("from (select gnmkdm, 'view' btndm, '查询' btnmc from gnmkdmb where length(gnmkdm)=7 ")
			.append("union select a.gnmkdm,a.btndm,a.btnmc from xg_view_xtwh_gnmkbtn a) a ")
			.append("left join (select * from xg_xtwh_jscdqxb where jsdm=?) b on a.gnmkdm=b.gnmkdm and a.btndm = b.cdgndm order by a.gnmkdm");
		
		String[] inputs = new String[]{jsdm};
		String[] colList = new String[]{"gnmkdm", "btndm", "btnmc", "jsyy"};
		
		List<HashMap<String, String>> list = DAO.getInstance().getList(sql.toString(), inputs, colList);
		return list;
	}
	
	/**
	 * 根绝角色代码获取角色列表
	 * @param pkValues
	 * @return
	 */
	public List<HashMap<String, String>> getJsList(String[] pkValues){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_view_xtwh_jswh where ");
		
		for(int i=0; i<pkValues.length; i++){
			if(i==pkValues.length-1){
				sql.append("jsdm=? ");
			}else{
				sql.append("jsdm=? or");
			}
		}
		
		return DAO.getInstance().getList(sql.toString(), pkValues, new String[]{"jsdm","jsmc","jslxmc","jscmmc"});
	}
	
	/**
	 * 根据角色代码获取角色详细信息
	 * @param pkValues
	 * @return
	 */
	public HashMap<String, String> getJsInfo(String jsdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(select count(*) from xg_xtwh_yhjsb b where b.jsdm=a.jsdm) yhrs from xg_view_xtwh_jswh a where jsdm=?");
		
		return DAO.getInstance().getMapNotOut(sql.toString(), new String[]{jsdm});
	}
	
	/**
	 * 删除角色权限
	 * @param yhm
	 * @return
	 */
	public boolean delJsqx(String jsdm){
		DAO dao = DAO.getInstance();
		boolean flag = false;

		String sql = "delete from xg_xtwh_jscdqxb where jsdm=?";
		
		try {
			flag = dao.runUpdate(sql, new String[]{jsdm});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存用户权限
	 * @param params
	 * @return
	 */
	public boolean saveJsqx(List<String[]> params){
		boolean flag = false;
		String sql = "insert into xg_xtwh_jscdqxb(jsdm,gnmkdm,cdgndm) values(?,?,?)";
		
		try {
			DAO.getInstance().runBatch(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
