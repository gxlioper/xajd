package xgxt.rcgl.zjjj.qjgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class ZjjjQjglService {
	public static Map<String, String> xqdzMap = new HashMap<String, String>();
	
	static {
		DAO dao = DAO.getInstance();
		String sql = "select xqdm,xqmc from xqdzb";
		
		List<String[]> list = dao.rsToVator(sql, new String[]{}, new String[]{"xqdm", "xqmc"});
		
		for(String[] strs : list){
			xqdzMap.put(strs[0], strs[1]);
		}
	}
	
	/**
	 * 获取请假学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		String sql = "select a.*," +
					"(select b.ldmc||'-'||b.qsh from view_xg_gygl_new_cwxx b where b.xh=a.xh) qsh from view_xsjbxx a where a.xh=?";
		String[] colList = new String[]{"xh", "xm", "xymc", "zymc", "bjmc", "qsh", "sjhm"};
		
		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[]{xh}, colList);
	}
	
	/**
	 * 保存学生请假信息
	 * @return
	 */
	public boolean saveXsqj(ZjjjQjglForm model){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		
		String sql = "insert into xg_rcsw_xsqjb(xh,xn,xq,qjkssj,qjjssj,qjts,qjsy,sfzx,bz,sh1,sh2,sh3,sh4,jzdh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int qjts = Integer.parseInt(model.getQjts());
		String sh1 = model.getSh1();
		String sh2 = model.getSh2();
		String sh3 = model.getSh3();
		String sh4 = model.getSh4();
		String jzdh = model.getJzdh();
		
		if(1 == qjts){
			sh2 = "无需审核";
			sh3 = "无需审核";
			sh4 = "无需审核";
		}else if(qjts>=2 && qjts<=3){
			sh3 = "无需审核";
			sh4 = "无需审核";
		}else if(qjts>=4 && qjts<=7){
			sh4 = "无需审核";
		}
		
		String[] input = new String[]{model.getXh(), model.getXn(), model.getXq(), model.getQjkssj(), model.getQjjssj(),
							          model.getQjts(), model.getQjsy(), model.getSfzx(), model.getBz(),sh1,sh2,sh3,sh4,jzdh};
		try {
			flag = dao.insert(sql, input);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获取审核信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getShList(ZjjjQjglForm model, String userStatus,String userName, String[] outPutList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String condition = "";
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "xn", "xq"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();
		String shzt=model.getSh1();
		
		if("fdy".equalsIgnoreCase(userStatus)){
			condition += " and exists(select 1 from fdybjb b where b.zgh='" + userName + "' and b.bjdm=a.bjdm)";
			condition += Base.isNull(shzt)?"":" and sh1 = '"+shzt+"' ";
		}else if("xy".equalsIgnoreCase(userStatus)){
			condition += " and qjts>=2";
			condition += Base.isNull(shzt)?"":" and sh2 = '"+shzt+"' ";
		}else if("zr".equalsIgnoreCase(userStatus)){
			condition += " and qjts >=4";
			condition += Base.isNull(shzt)?"":" and sh3 = '"+shzt+"' ";
		}else if("xx".equalsIgnoreCase(userStatus)){
			condition += " and qjts>7 and sh3='通过'";
			condition += Base.isNull(shzt)?"":" and sh4 = '"+shzt+"' ";
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from (");
		sql.append("select a.* from xg_view_rcsw_xssq a ")
		   .append(condition);
		sql.append(" order by qjkssj desc ");
		sql.append(" ) a ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", inPutList, outPutList, model);
	}
	
	/**
	 * 获得查询数据
	 * @param model
	 * @param userStatus
	 * @param userName
	 * @param outPutList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getCxList(ZjjjQjglForm model, String userStatus,String userName, String[] outPutList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String condition = "";
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "xn", "xq","qjts","lddm"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();
		
		// 修改，删除权限
		String qx = "1=2";
		
		if("stu".equalsIgnoreCase(userStatus)){
			qx = "sh1='通过' or sh2='通过' or sh3='通过' or sh4='通过'";
		}else if("fdy".equalsIgnoreCase(userStatus)){
			condition += " and exists(select 1 from fdybjb b where b.zgh='" + userName + "' and b.bjdm=a.bjdm)";
			qx = "sh2='通过' or sh3='通过' or sh4='通过'";
		}else if("xy".equalsIgnoreCase(userStatus)){
			qx = "sh3='通过' or sh4='通过'";
		}else if("zr".equalsIgnoreCase(userStatus)){
			qx = "sh4='通过'";
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from ( ")
		   .append("select a.*,(case when (").append(qx)
		   .append(") then 'disabled' else '' end) disabled from xg_view_rcsw_xssq a ")
		   .append(condition)
		   .append(" order by qjkssj desc ")
		   .append(" ) a ");
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", inPutList, outPutList, model);
	}
	
	/**
	 * 获取查询表头
	 * @param tableName
	 * @param cols
	 * @return
	 */
	public String[] getTitle(String tableName, String[] cols){
		DAO dao = DAO.getInstance();
		return dao.getColumnNameCN2(cols, tableName);
	}
	
	
	/**
	 * 批量删除
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "delete from xg_rcsw_xsqjb where xh||xn||xq||qjkssj||qjjssj=?";
		
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pk : pkValues){
			params.add(new String[]{pk});
		}
		try {
			dao.runBatch(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 批量审核
	 * @return
	 */
	public boolean batchSh(String[] pkValues, String userStatus, String userName, String time, String shjg){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		String shjb = null;
		String shr = null;
		String shsj = null;
		
		if("fdy".equalsIgnoreCase(userStatus)){
			shjb = "sh1";
			shr = "shr1";
			shsj = "shsj1";
		}else if("xy".equalsIgnoreCase(userStatus)){
			shjb = "sh2";
			shr = "shr2";
			shsj = "shsj2";
		}else if("zr".equalsIgnoreCase(userStatus)){
			shjb = "sh3";
			shr = "shr3";
			shsj = "shsj3";
		}else{
			shjb = "sh4";
			shr = "shr4";
			shsj = "shsj4";
		}
		
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pkVal : pkValues){
			params.add(new String[]{shjg,userName,time,pkVal});
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_rcsw_xsqjb set ").append(shjb).append("=?,")
			.append(shr).append("=?,").append(shsj).append("=? where xh||xn||xq||qjkssj||qjjssj=?");
		
		try {
			dao.runBatch(sql.toString(), params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获得学生请假信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getQjInfo(String pkValue){
		String tableName = "xg_view_rcsw_xssq";
		
		String[] colList = new String[]{"xh","xm", "xn", "xq", "xymc", "zymc", "bjmc", "qsh", "sjhm", 
										"sfzx", "qjkssj", "qjjssj", "qjts", "qjsy", "bz", "sh1", "shr1",
										"sh2", "shr2", "sh3", "shr3", "sh4", "shr4", "shsj1", "shsj2", "shsj3", "shsj4", "jzdh"};
		return CommonQueryDAO.commonQueryOne(tableName, colList, "pkValue", pkValue);
	}
	
	/**
	 * 修改请假管理
	 * @param model
	 * @param pkValue
	 * @return
	 */
	public boolean updateQjgl(ZjjjQjglForm model,String pkValue){
		boolean flag = false;
		
		int qjts = Integer.parseInt(model.getQjts());
		String sh1 = model.getSh1();
		String sh2 = model.getSh2();
		String sh3 = model.getSh3();
		String sh4 = model.getSh4();
		
		if(1 == qjts){
			sh2 = "无需审核";
			sh3 = "无需审核";
			sh4 = "无需审核";
		}else if(qjts>=2 && qjts<=3){
			sh3 = "无需审核";
			sh4 = "无需审核";
		}else if(qjts>=4 && qjts<=7){
			sh4 = "无需审核";
		}
		
		String sql = "update xg_rcsw_xsqjb set qjkssj=?,qjjssj=?,qjts=?,sh1=?,sh2=?,sh3=?,sh4=?," +
				"qjsy=?,bz=?,jzdh=? where xh||xn||xq||qjkssj||qjjssj=?";
		String[] input = new String[]{model.getQjkssj(), model.getQjjssj(), model.getQjts(), 
				sh1,sh2,sh3,sh4,model.getQjsy(), model.getBz(), model.getJzdh(), pkValue};
		
		DAO dao = DAO.getInstance();
		try {
			flag = dao.runUpdate(sql, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 获取幢号列表
	 * @return ldlist
	 */
	public List<HashMap<String, String>> getLdList() {
	    DAO dao = DAO.getInstance();
	    String sql = "select lddm,ldmc from xg_gygl_new_ldxxb";
	    sql = sql + " order by lddm";
	    return dao.getList(sql, new String[0], new String[] { "lddm", "ldmc" });
	}
}
