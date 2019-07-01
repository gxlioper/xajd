package xgxt.rcgl.cdty.qjgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CdtyQjglService {
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
		String sql = "select a.*,(select jtszd from xsfzxxb b where b.xh=a.xh) jtdz  from view_xsjbxx a where a.xh=?";
		String[] colList = new String[]{"xh", "xm", "xymc", "zymc", "bjmc" , "sjhm", "lxdh", "jtdz"};
		
		DAO dao = DAO.getInstance();
		return dao.getMap(sql, new String[]{xh}, colList);
	}
	
	/**
	 * 保存学生请假信息
	 * @return
	 */
	public boolean saveXsqj(CdtyQjglForm model){
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
	
	public Map<String, Object> getShMap(String qx){
		String path = null;
		String[] outputs = null;
		String[] topTr = null;
		
		if("sh1".equalsIgnoreCase(qx)){
			path = "rcsw_cdty_qjsh1.do";
			outputs = new String[]{"pkValue","xh", "xn", "xqmc", "xm", "bjmc", "qjsj", "qjts", "sh1", "sh2"};
			topTr = new String[]{"主键", "学号", "学年", "学期", "姓名", "班级", "请假时间", "请假天数", "辅导员审核", "上级审核"};
		}else if("sh2".equalsIgnoreCase(qx)){
			path = "rcsw_cdty_qjsh2.do";
			outputs = new String[]{"pkValue","xh", "xn", "xqmc", "xm", "bjmc", "qjsj", "qjts", "sh2", "sh3"};
			topTr = new String[]{"主键", "学号", "学年", "学期", "姓名", "班级", "请假时间", "请假天数", "班主任审核", "上级审核"};
		}else if("sh3".equalsIgnoreCase(qx)){
			path = "rcsw_cdty_qjsh3.do"; 
			outputs = new String[]{"pkValue","xh", "xn", "xqmc", "xm", "bjmc", "qjsj", "qjts", "sh3", "sh4"};
			topTr = new String[]{"主键", "学号", "学年", "学期", "姓名", "班级", "请假时间", "请假天数", "系主任审核", "上级审核"};
		}else if("sh4".equalsIgnoreCase(qx)){
			path = "rcsw_cdty_qjsh4.do";
			outputs = new String[]{"pkValue","xh", "xn", "xqmc", "xm", "bjmc", "qjsj", "qjts", "sh4"};
			topTr = new String[]{"主键", "学号", "学年", "学期", "姓名", "班级", "请假时间", "请假天数", "学工处审核"};
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("path", path);
		map.put("outputs", outputs);
		map.put("topTr", topTr);
		
		return map;
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
	public List<String[]> getShList(CdtyQjglForm model, String qx, User user, String[] outPutList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String condition = "";
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "xn", "xq"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();
		
		if("sh1".equalsIgnoreCase(qx)){
			condition += user.getQueryCondition();
		}else if("sh2".equalsIgnoreCase(qx)){
			condition += " and qjts>=2 and sh1='通过'" + user.getQueryCondition();
		}else if("sh3".equalsIgnoreCase(qx)){
			condition += " and qjts >=4 and sh2='通过'" + user.getQueryCondition();
		}else if("sh4".equalsIgnoreCase(qx)){
			condition += " and qjts>7 and sh3='通过'" + user.getQueryCondition();
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from xg_view_rcsw_xsqj a ")
		   .append(condition);
		
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
	public List<String[]> getCxList(CdtyQjglForm model,User user, String[] outPutList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String condition = "";
		String userStatus = user.getUserStatus();
		
		MakeQuery makeQuery = new MakeQuery();
		
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "xn", "xq","qjts"};
		String[] colLikeList = new String[]{"xh", "xm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		
		condition = makeQuery.getQueryString();
		String[] inPutList = makeQuery.getInputList();
		
		condition += user.getQueryCondition();
		
		String qx = "1=2";
		
		if(!"xx".equalsIgnoreCase(userStatus)){
			qx = "sh1='通过' or sh2='通过' or sh3='通过' or sh4='通过'";
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.*,(case when (").append(qx)
		   .append(") then 'disabled' else '' end) disabled from xg_view_rcsw_xsqj a ")
		   .append(condition);
		
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
	public boolean batchSh(String[] pkValues, String qx, User user, String time, String shjg){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		String shjb = null;
		String shr = null;
		String shsj = null;
		
		if("sh1".equalsIgnoreCase(qx)){
			shjb = "sh1";
			shr = "shr1";
			shsj = "shsj1";
		}else if("sh2".equalsIgnoreCase(qx)){
			shjb = "sh2";
			shr = "shr2";
			shsj = "shsj2";
		}else if("sh3".equalsIgnoreCase(qx)){
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
			params.add(new String[]{shjg,user.getRealName(),time,pkVal});
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_rcsw_xsqjb set ").append(shjb).append("=?,")
			.append(shr).append("=?,").append(shsj).append("=? where xh||xn||xq||qjkssj||qjjssj=?");
		
		try {
			int[] rs = dao.runBatch(sql.toString(), params);
			flag = dao.checkBatchResult(rs);
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
		String tableName = "xg_view_rcsw_xsqj";
		
		String[] colList = new String[]{"xh","xm","xb","xn", "xq", "xymc", "zymc", "bjmc", "sjhm", "lxdh", "jtdz",
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
	public boolean updateQjgl(CdtyQjglForm model,String pkValue){
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
	 * 获取请假记录
	 * @param m
	 * @return
	 */
	public String checkQjjl(CdtyQjglForm m){
		String num="0";
		DAO dao = DAO.getInstance();
		String sql="select count(1) num from xg_rcsw_xsqjb where xh=? and xn=? and xq=? and qjkssj=? and qjjssj=?";
		num=dao.getOneRs(sql, new String[]{m.getXh(),m.getXn(),m.getXq(),m.getQjkssj(),m.getQjjssj()}, "num");
		return num;
	}
}
