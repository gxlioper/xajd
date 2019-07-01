package xgxt.xtwh.xtwhCriterion.yhgl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.base.Encrypt;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class YhglDAO {
	/**
	 * 初始化所有用户密码
	 * @return
	 */
	public boolean initMm(String mm){
		boolean flag = false;

		DAO dao = DAO.getInstance();
		String sql = "update xg_xtwh_yhb set mm=?";
		
		try {
			flag = dao.runUpdate(sql, new String[]{mm});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 初始化指定用户的密码
	 * @param mm
	 * @param yhs
	 * @return
	 */
	public boolean initMm(String mm, String[] yhs){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		
		String  sql = "update xg_xtwh_yhb set mm='" + mm +"' where yhm=?";
		
		List<String[]> params = new ArrayList<String[]>();
		
		for(int i=0; i<yhs.length; i++){
			params.add(new String[]{yhs[i]});
		}
		
		try {
			int[] rs = dao.runBatch(sql, params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 初始化学生
	 * @param jsdm
	 * @return
	 */
	public Map<String, String> initXs(String jsdm){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		Map<String, String> map = new HashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) count from view_xsxxb a where not exists(select 1 from xg_xtwh_yhb b where b.yhm=a.xh)");
		
		Map<String, String> countMap = dao.getMapNotOut(sql.toString(), new String[]{});
		
		// 同步条目数
		map.put("count", countMap.get("count"));
		
		// 执行存储工程  
		try {
			flag = dao.runProcuder("{call xtwh_xsyh_init(?)}", new String[]{jsdm});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("flag", String.valueOf(flag));
		
		return map;
	}
	
	/**
	 * 获取用户详细信息
	 * @param yhm
	 * @return
	 */
	public Map<String, String> getYh(String yhm){
		String sql = "select a.*,(select bmmc from zxbz_xxbmdm b where b.bmdm=a.szbm)bmmc from xg_xtwh_yhb a where yhm=?";
		
		return DAO.getInstance().getMapNotOut(sql, new String[]{yhm});
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	public boolean saveYh(YhglForm model){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		String sql = "insert into xg_xtwh_yhb(yhm,mm,xm,szbm,kqzt,mrqx,bz) values(?,?,?,?,?,?,?)";
		
		String[] inputs = new String[]{model.getYhm(), new Encrypt().encrypt("888888"), model.getXm(), model.getSzbm(), model.getKqzt(), model.getMrqx(), model.getBz()};
		
		try {
			flag = dao.insert(sql, inputs);
			
			// 保存用户角色
			if(flag){
				String[] yyjs = model.getYyjs();	// 要保存的角色
				String[] pkValues = new String[yyjs.length];	// 主键
				String[][] params = new String[yyjs.length][];  // 参数 
				
				for(int i=0; i<yyjs.length; i++){
					String[] strs = yyjs[i].split("_");
					String jsqx = "gl".equalsIgnoreCase(strs[1]) ? "是" : "否";
					
					pkValues[i] = strs[0] + model.getYhm();
					params[i] = new String[]{strs[0], model.getYhm(), jsqx};
				}
				
				flag = batchSq(pkValues, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	public boolean modiYh(YhglForm model){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		String sql = "update xg_xtwh_yhb set xm=?,szbm=?,kqzt=?,mrqx=?,bz=? where yhm=?";
		String[] inputs = new String[]{model.getXm(), model.getSzbm(), model.getKqzt(), model.getMrqx(), model.getBz(), model.getYhm()};
		
		String delSql = "delete xg_xtwh_yhjsb where yhm=?";
		String[] delInputs = new String[]{model.getYhm()};
		
		try {
			flag = dao.runUpdate(sql, inputs);
			flag = dao.runUpdate(delSql, delInputs);
			
			// 保存用户角色
			if(flag){
				String[] yyjs = model.getYyjs();	// 要保存的角色
				String[] pkValues = new String[yyjs.length];	// 主键
				String[][] params = new String[yyjs.length][];  // 参数 
				
				for(int i=0; i<yyjs.length; i++){
					String[] strs = yyjs[i].split("_");
					String jsqx = "gl".equalsIgnoreCase(strs[1]) ? "是" : "否";
					
					pkValues[i] = strs[0] + model.getYhm();
					params[i] = new String[]{strs[0], model.getYhm(), jsqx};
				}
				
				flag = batchSq(pkValues, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 批量角色授予用户授权方法
	 * @param pkValues
	 * @param params
	 */
	public boolean batchSq(String[] pkValues, String[][] params){
		boolean flag = false;
		
		DAO dao = DAO.getInstance();
		List<String[]> list = new ArrayList<String[]>(); 
		
		boolean[] isExists = dao.checkExists("xg_xtwh_yhjsb", "jsdm||yhm", pkValues);
		
		for(int i=0; i<isExists.length; i++){
			if(!isExists[i]){
				list.add(params[i]);
			}
		}
	
		try {
			String jsSql = "insert into xg_xtwh_yhjsb(jsdm,yhm,jsqx) values(?,?,?)";
			int[] rs = dao.runBatch(jsSql, list);
			
			// 定义临时变量
			boolean temp = true;
			
			for(int i=0; i<rs.length; i++){
				if(rs[i] == Statement.EXECUTE_FAILED){
					flag = false;
					break;
				}
			}
			
			flag = temp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 删除用户权限
	 * @param yhm
	 * @return
	 */
	public boolean delYhqx(String yhm){
		DAO dao = DAO.getInstance();
		boolean flag = false;

		String sql = "delete from xg_xtwh_yhcdqxb where yhm=?";
		
		try {
			flag = dao.runUpdate(sql, new String[]{yhm});
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
	public boolean saveYhqx(List<String[]> params){
		boolean flag = false;
		String sql = "insert into xg_xtwh_yhcdqxb(yhm,gnmkdm,cdid,sqyh) values(?,?,?,?)";
		
		try {
			DAO.getInstance().runBatch(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 保存学生权限
	 * @param params
	 * @return
	 */
	public boolean saveXsqx(List<String[]> params){
		boolean flag = false;
		String sql = "insert into xg_xtwh_xscdqxb(xh,gnmkdm,cdid,sqyh) values(?,?,?,?)";
		
		try {
			DAO.getInstance().runBatch(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 查询出用户列表
	 * @param model
	 * @param otherQuery
	 * @return
	 */
	public List<String[]> queryYhList(YhglForm model,String[] colList, String[] colLikeList,String otherQuery){
	MakeQuery makeQuery = new MakeQuery();
		List<String[]> list = null;
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			// 查询语句
			String query = makeQuery.getQueryString();
			// 条件值
			String[] inputs = makeQuery.getInputList();
			String[] outputs = new String[]{"yhm", "xm", "bmmc", "kqzt", "yfpjs"};
			
			StringBuilder sql = new StringBuilder();
			sql.append("select rownum r,a.yhm,a.xm,a.szbm,a.bz,decode(a.kqzt,'1','是','否') kqzt,")
				.append("(select bmmc from zxbz_xxbmdm b where b.bmdm = a.szbm) bmmc,")
				.append("(select count(*) from xg_xtwh_yhjsb b where b.yhm=a.yhm) yfpjs ")
				.append("from xg_xtwh_yhb a")
				.append(query);
			
			// 通用查询带分页
			list = CommonQueryDAO.commonQuery(sql.toString(), otherQuery, inputs, outputs, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 获得某用户指定等级的功能模块
	 * @param lv:分 1， 2， 3
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String userName, int lv) {
		StringBuilder sql = new StringBuilder();
		String jbxz = "";
		String length = "3";
		
		// 获取功能菜单级别
		switch (lv) {
		case 1: jbxz="and length(a.gnmkdm)=3 "; length = "3"; break;
		case 2: jbxz="and length(a.gnmkdm)=5 "; length = "5"; break;
		case 3: jbxz="and length(a.gnmkdm)=7 "; length = "7"; break;
		default: jbxz=""; break;
		}
		
		sql.append("select rownum r,gnmkdm dm,gnmkmc mc from gnmkdmb a where a.sfqy='是' ");
		sql.append(jbxz);
		sql.append("and exists(select 1 from (select distinct a.yhm,b.gnmkdm from ");
		sql.append("(select yhm,jsdm from (select a.*,b.sfqy from xg_xtwh_yhjsb a,xg_xtwh_jswhb b where a.jsdm=b.jsdm) where yhm=? and sfqy='是') a, ");
		sql.append("xg_xtwh_jscdqxb b where a.jsdm=b.jsdm union select a.yhm,a.gnmkdm from ");
		sql.append("xg_xtwh_yhcdqxb a where a.yhm=?) b where substr(b.gnmkdm,0,?) = a.gnmkdm) order by gnmkdm");

		String[] inputs = new String[]{userName, userName, length};
		String[] colList = new String[]{"dm", "mc" };
		
		return DAO.getInstance().getList(sql.toString(), inputs, colList);
	}
	
	/**
	 * 获得指定功能模块的下级模块
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String userName, String gnmkdm){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select gnmkdm dm,gnmkmc mc,(case when length(gnmkmc) > 4 then substr(gnmkmc,0,4)||'...' else gnmkmc end) jxmc from gnmkdmb a ")
			.append("where a.gnmkdm like ? ")
			.append("and exists(select 1 from (select distinct a.yhm,b.gnmkdm from ")
			.append("(select yhm,jsdm from (select a.*,b.sfqy from xg_xtwh_yhjsb a,xg_xtwh_jswhb b where a.jsdm=b.jsdm) where yhm=? and sfqy='是') a, ")
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
	 */
	public List<HashMap<String, String>> getBtnList(String userName){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.gnmkdm,a.btndm,a.btnmc, decode(b.cdgndm, '', '', 'yes') jsyy,decode(c.cdid, '', '', 'yes') yhyy ")
			.append("from (select gnmkdm, 'view' btndm, '查询' btnmc from gnmkdmb where length(gnmkdm)=7 ")
			.append("union select a.gnmkdm,a.btndm,a.btnmc from xg_view_xtwh_gnmkbtn a) a ")
			.append("left join (select distinct gnmkdm,cdgndm from xg_xtwh_jscdqxb a ")
			.append("where exists (select 1 from xg_xtwh_yhjsb b where yhm = ? and b.jsdm = a.jsdm)) b ")
			.append("on a.gnmkdm = b.gnmkdm and a.btndm=b.cdgndm left join ")
			.append("(select gnmkdm,cdid from xg_xtwh_yhcdqxb a where a.yhm = ?) c on a.gnmkdm=c.gnmkdm and a.btndm = c.cdid");
		
		String[] inputs = new String[]{userName, userName};
		String[] colList = new String[]{"gnmkdm", "btndm", "btnmc", "jsyy", "yhyy"};
		
		List<HashMap<String, String>> list = DAO.getInstance().getList(sql.toString(), inputs, colList);
		return list;
	}
	
	/**
	 * 获得某个功能模块的下级模块
	 * @param lv
	 * @param dm
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String lv,String dm, String userName, String yhm) {
		StringBuilder sql = new StringBuilder();
		String[] inputs = null;
		String[] colList = null;
		
		if("3".equalsIgnoreCase(lv)){	// 按钮级别
		
			if(StringUtils.isNull(yhm)){
				sql.append("select 'view' dm, '查询' mc, '查询' jxmc,'' disabled from dual union select a.btndm dm,a.btnmc mc,")
					.append("(case when length(a.btnmc)>4 then substr(a.btnmc,0,4)||'...' else a.btnmc end) jxmc,'' disabled ")
					.append("from xg_view_xtwh_gnmkbtn a where a.gnmkdm=?");
				inputs = new String[]{dm};
			}else{
				sql.append("select dm, mc, jxmc, decode(b.cdgndm, '', '', 'yes') jsyy,decode(c.cdid, '', '', 'yes') yhyy ")
					.append("from (select 'view' dm, '查询' mc, '查询' jxmc from dual ")
					.append("union select a.btndm dm,a.btnmc mc,(case when length(a.btnmc)>4 then substr(a.btnmc,0,4)||'...' else a.btnmc end) jxmc ")
					.append("from xg_view_xtwh_gnmkbtn a where gnmkdm=?)a left join ")
					.append("(select distinct cdgndm from xg_xtwh_jscdqxb a where gnmkdm=? and exists (select 1 from ")
					.append("xg_xtwh_yhjsb b where yhm=? and b.jsdm=a.jsdm))b on a.dm = b.cdgndm ")
					.append("left join (select cdid from xg_xtwh_yhcdqxb a where a.yhm=? and a.gnmkdm=?) c on a.dm=c.cdid");
				
				inputs = new String[]{dm, dm, yhm, yhm, dm};
			}
			colList = new String[]{"dm", "mc", "jxmc", "jsyy", "yhyy"};
		}else{
			String length = "1".equalsIgnoreCase(lv) ? "5" : "7";
			
			sql.append("select gnmkdm dm,gnmkmc mc,(case when length(gnmkmc) > 4 then substr(gnmkmc,0,4)||'...' else gnmkmc end) jxmc from gnmkdmb a ")
				.append("where a.gnmkdm like ? ")
				.append("and exists(select 1 from (select distinct a.yhm,b.gnmkdm from ")
				.append("(select yhm,jsdm from xg_view_xtwh_yhjs where yhm=? and sfqy='是') a, ")
				.append("xg_xtwh_jscdqxb b where a.jsdm=b.jsdm union select a.yhm,a.gnmkdm from ")
				.append("xg_xtwh_yhcdqxb a where a.yhm=?) b where substr(b.gnmkdm,0,?) = a.gnmkdm) order by gnmkdm");
			
			colList = new String[]{"dm", "mc", "jxmc"};
			inputs = new String[]{dm + "__", userName, userName, length};
		}
		
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
	 * 批量删除用户
	 * @param pkValues
	 * @return
	 */
	public boolean delYh(String[] pkValues){
		boolean flag = true;
		String[] sqlArr = new String[3];
		DAO dao = DAO.getInstance();
		
		for(int i=0; i<pkValues.length; i++){
			sqlArr[0] = "delete from xg_xtwh_yhqxb where yhm='" + pkValues[i] + "'";
			sqlArr[1] = "delete from xg_xtwh_yhjsb where yhm='" + pkValues[i] + "'";
			sqlArr[2] = "delete from xg_xtwh_yhb where yhm='" + pkValues[i] + "'";
			
			try {
				int[] rs = dao.runBatch(sqlArr);
				for(int j=0; j<rs.length; j++){
					if(rs[j] == Statement.EXECUTE_FAILED){
						flag = false;
					}
				}
			} catch (SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	
	/**
	 * 查询出角色列表
	 * @param model
	 * @param otherQuery
	 * @return
	 */
	public List<String[]> queryJsList(YhglForm model, String otherQuery){
	MakeQuery makeQuery = new MakeQuery();
		List<String[]> list = null;
		
		String[] colList = new String[]{"jslxdm", "jscmdm"};
		String[] colLikeList = new String[]{"jsmc"};
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			// 查询语句
			String query = makeQuery.getQueryString();
			// 条件值
			String[] inputs = makeQuery.getInputList();
			String[] outputs = new String[]{"jsdm", "jsmc", "jslxmc", "jscmmc", "sfqy"};
			
			StringBuilder sql = new StringBuilder();
			sql.append("select rownum r,a.* from xg_view_xtwh_jswh a ")
				.append(query);
			
			// 通用查询带分页
			list = CommonQueryDAO.commonQuery(sql.toString(), otherQuery, inputs, outputs, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 查询出用户列表
	 * @param model
	 * @param otherQuery
	 * @return
	 */
	public List<String[]> queryXslxList(YhglForm model,String[] colList, String[] colLikeList){
		MakeQuery makeQuery = new MakeQuery();
		List<String[]> list = null;	
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			// 查询语句
			String query = makeQuery.getQueryString();
			// 条件值
			String[] inputs = makeQuery.getInputList();
			String[] outputs = new String[]{"xslxdm", "xslxmc", "xslxlbmc", "yfpjs"};
			
			StringBuilder sql = new StringBuilder();
			sql.append("select rownum r,a.* from xg_view_xtwh_xslx a ")
				.append(query);
			
			// 通用查询带分页
			list = CommonQueryDAO.commonQuery(sql.toString(), "", inputs, outputs, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 批量角色授予学生授权方法
	 * @param pkValues
	 * @param params
	 */
	public boolean batchSqForXs(String[] pkValues, String[][] params){
		boolean flag = false;
		
		DAO dao = DAO.getInstance();
		List<String[]> list = new ArrayList<String[]>(); 
		
		boolean[] isExists = dao.checkExists("xg_xtwh_xslxjsb", "xslxdm||jsdm", pkValues);
		
		for(int i=0; i<isExists.length; i++){
			if(!isExists[i]){
				list.add(params[i]);
			}
		}
	
		try {
			String jsSql = "insert into xg_xtwh_xslxjsb(xslxdm,jsdm,kssj,jssj) values(?,?,?,?)";
			int[] rs = dao.runBatch(jsSql, list);
			
			// 定义临时变量
			boolean temp = true;
			
			for(int i=0; i<rs.length; i++){
				if(rs[i] == Statement.EXECUTE_FAILED){
					flag = false;
					break;
				}
			}
			
			flag = temp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获取登陆用户拥有的管理角色List
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getJsListForUserName(String userName){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select jsdm,jsmc from xg_view_xtwh_yhjs where yhm=? and jsqx='是' and sfqy='是'")
			.append(" union select jsdm,jsmc from xg_xtwh_jswhb where cjr=? and sfqy='是'");
		
		return DAO.getInstance().getList(sql.toString(), new String[]{userName, userName}, new String[]{"jsdm", "jsmc"});
	}
	
	/**
	 * 获得用户角色的列表
	 * @param yhms
	 * @return
	 */
	public List<HashMap<String, String>> getYhjsList(String[] yhms){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_view_xtwh_yhjs where ");
		
		for(int i=0; i<yhms.length; i++){
			sql.append("yhm='").append(yhms[i]).append("' ");
			if(i<yhms.length-1){
				sql.append("or ");
			}
		}
		
		return DAO.getInstance().getList(sql.toString(), new String[]{}, new String[]{"yhm", "jsdm", "jsmc", "jsqx"});
	}
	
	/**
	 * 获取某用户权限
	 * @param yhm
	 * @return
	 */
	public List<String[]> getYhcdqx(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select yhm,gnmkdm,cdid from xg_xtwh_yhcdqxb where yhm=?");
		
		return DAO.getInstance().rsToVator(sql.toString(), new String[]{yhm}, new String[]{"yhm", "gnmkdm", "cdid"});
	}
}
