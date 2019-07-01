package xgxt.pjpy.comm.pjpy.tjsz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.String.StringUtils;

public class PjpyTjszUtils {
	
	/**
	 * 获得某学生申请某项目时所有的条件
	 * @param xh
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String, String>> getAllTj(String xh, String xmdm){
		String sql = "select * from xg_view_pjpy_tjms where xmdm=? and " +
					 "(tjfw=(select xydm from view_xg_pjpy_ryqd where xh=?) or tjfw='all')";
		
		DAO dao = DAO.getInstance();

		String[] output = new String[] { "tjdm", "gx", "tjz", "tjms", "tjlx",
				"tablename", "zd", "condition", "xn", "xq", "nd", "tsgs",
				"bzd", "xmdm" };
		
		return dao.getList(sql, new String[] { xmdm, xh }, output);
	}
	
	/**
	 * 判断条件
	 * @param xh
	 * @param map
	 * @return
	 */
	public String judgeTj(String xh, Map<String, String> map){
		String yx = "";
		String tjdm = map.get("tjdm");
		
		if("cj".equalsIgnoreCase(tjdm)){
			yx = judgeCj(xh,map,null);
		}else if("zpcj".equalsIgnoreCase(tjdm)){//总评成绩
			yx = judgeCj(xh,map,"总评");
		}else if("tycj".equalsIgnoreCase(tjdm)){
			yx = judgeTyf(xh,map);
		}else if("cql".equalsIgnoreCase(tjdm)){
			yx = judgeCql(xh,map);
		}else if("cxpd".equalsIgnoreCase(tjdm)){
			yx = judgeCxpd(xh,map);
		}else if("avgcj".equalsIgnoreCase(tjdm)){//平均成绩
			yx = judgeAvgCj(xh,map);
		}else if("cxf".equalsIgnoreCase(tjdm)){//操行分
			yx = judgeCxf(xh,map);
		}else if("pjjd".equalsIgnoreCase(tjdm)){
			yx = judgePjjd(xh,map);
		}
		
		return yx;
	}
	
	/**
	 * 成绩条件判断
	 * @param xh
	 * @param map
	 * @return
	 */
	public String judgeCj(String xh, Map<String, String> map,String lx){
		String message = "";
		String sqzq = map.get("sqzq");
		
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select min(cj) cj from xg_view_cjxx_dkcj where xh=? ");
		sql.append(Base.isNull(lx) ? "" : " and lx = '" + lx + "' ");
		List<String> input = new ArrayList<String>();
		
		input.add(xh);
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		// 申请周期不同学年，学期，年度
		if("xn".equalsIgnoreCase(sqzq)){
			sql.append("and xn=? ");
			
			input.add(pjxn);
		}else if("xq".equalsIgnoreCase(sqzq)){
			sql.append("and xn=? and xq=? ");
			
			input.add(pjxn);
			input.add(pjxq);
		}else {
			sql.append("and nd=?");
			
			input.add(pjnd);
		}
		
		Map<String, String> cjMap = dao.getMapNotOut(sql.toString(), (String[])input.toArray(new String[]{}));
		String mbz = cjMap.get("cj");
		mbz = StringUtils.isNull(mbz) ? "0" : mbz;
		
		String tjz = map.get("tjz");
		String gx = map.get("gx");
		String tjms = map.get("tjms");
		
		boolean flag = compareTo(mbz, tjz, gx);
		
		if (!flag) {
			if(Base.isNull(lx)){
				message = "申请该项目需要：" + tjms + ",最低分数" + mbz + ",不满足申请条件！";
			}else{
				message = "申请该项目需要：" + tjms + ",申请者存在某门课程" +lx+"为"+ mbz + "分,不满足申请条件！";
			}
		}
		return message;
	}
	
	/**
	 * 目标值和确定值比较
	 * @param mbz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	public boolean compareTo(String mbz,String tjz,String gx){
		boolean flag = false;
		if(StringUtils.isNotNull(mbz) && StringUtils.isNotNull(tjz)){
			if("=".equalsIgnoreCase(gx)){
				flag = mbz.equalsIgnoreCase(tjz) ? true : false;
			}else if(">".equalsIgnoreCase(gx)){
				if(true){ // 是否数字判断
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)>0 ? true : false;
				}
			}else if("<".equalsIgnoreCase(gx)){
				if(true){
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)<0 ? true : false;
				}
			}else if(">=".equalsIgnoreCase(gx)){
				if(true){
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)>0 || bmbz.compareTo(btjz) ==0 ? true : false;
				}
			}else if("<=".equalsIgnoreCase(gx)){
				if(true){
					BigDecimal bmbz = new BigDecimal(mbz);
					BigDecimal btjz = new BigDecimal(tjz);
					
					flag = bmbz.compareTo(btjz)<0 || bmbz.compareTo(btjz) ==0 ? true : false;
				}
			}
		}
		
		return flag;
	}
	
	
	/**
	 * 判断操行评定
	 * @param xh
	 * @param map
	 * @return
	 */
	public String judgeCxpd(String xh, Map<String, String> map){
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		DAO dao = DAO.getInstance();

		// 返回信息
		String message = null;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select cxf from xg_pjpy_cxfpdb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		sql.append(" and pjxn = '" + pjxn + "' ");
		sql.append(" and pjxq = '" + pjxq + "' ");
		sql.append(" and pjnd = '" + pjnd + "' ");
		
		// 出勤率
		String cxf = dao.getOneRs(sql.toString(), new String[] {}, "cxf");
		
		boolean flag = compareTo(cxf, tjz, gx);
		
		if (!flag) {
			message = "申请该项目需要：" + tjms + ",不满足申请条件";
		}

		return message;
	}
	
	//==========================以下伟大的骆==============================================
	/**
	 * 获得评奖评优体育分条件
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String judgeTyf(String xh, Map<String, String> jxjMap){

		//TODO
		//体育分成绩取自视图xg_view_cjxx_tyf，何种课程属于体育分，可以由相关人员修改该视图决定
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		DAO dao = DAO.getInstance();

		// 返回信息
		String message = null;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 申请周期
		String sqzq = jxjMap.get("sqzq");
		// 项目名称
		//String xmmc = jxjMap.get("xmmc");
		// 条件说明
		String tjms = jxjMap.get("tjms");
		// 关系
		String gx = jxjMap.get("gx");
		// 条件值
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select avg(tycj) tycj from (");
		sql.append(" select tycj from xg_view_cjxx_tyf ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");

		if ("xn".equalsIgnoreCase(sqzq)) {// 申请周期：学年
			sql.append(" and xn = '" + pjxn + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 申请周期：学期
			sql.append(" and xn = '" + pjxn + "' ");
			sql.append(" and xq = '" + pjxq + "' ");
		} else {// 申请周期：年度
			sql.append(" and nd = '" + pjnd + "' ");
		}
		sql.append(" ) ");

		// 体育成绩
		String tycj = dao.getOneRs(sql.toString(), new String[] {}, "tycj");
		tycj = StringUtils.isNull(tycj) ? "0" : tycj;
		
		boolean flag = compareTo(tycj, tjz, gx);

		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者分数为" + tycj + ",不满足申请条件";
		}

		return message;
	}

	/**
	 * 获得评奖评优出勤率条件
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String judgeCql(String xh, Map<String, String> jxjMap){

		//TODO
		//出勤率取自表xg_pjpy_cqlb，cql字段必须是数字，如果是95%，请维护95
		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 返回信息
		String message = null;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 条件说明
		String tjms = jxjMap.get("tjms");
		// 关系
		String gx = jxjMap.get("gx");
		// 条件值
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select cql from xg_pjpy_cqlb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		sql.append(" and pjxn = '" + pjxn + "' ");
		sql.append(" and pjxq = '" + pjxq + "' ");
		sql.append(" and pjnd = '" + pjnd + "' ");

		// 出勤率
		String cql = dao.getOneRs(sql.toString(), new String[] {}, "cql");
		cql = StringUtils.isNull(cql) ? "0" : cql;
		
		boolean flag = compareTo(cql, tjz, gx);

		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者出勤率为" + cql + "%,不满足申请条件！";
		}

		return message;
	}
	
	/**
	 * 获得评奖评优操行分条件
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String judgeCxf(String xh, Map<String, String> jxjMap){

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 返回信息
		String message = null;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 条件说明
		String tjms = jxjMap.get("tjms");
		// 关系
		String gx = jxjMap.get("gx");
		// 条件值
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select jjf,fz from zjjt_cxflrb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and pjxh = '" + xh + "' ");
		sql.append(" and xn = '" + pjxn + "' ");
		sql.append(" and xq = '" + pjxq + "' ");
		//sql.append(" and shjg = '通过' ");
		
		// 操行分
		int cxf = 0;
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "jjf", "fz" });

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// 加减分
				String jjf = map.get("jjf");
				// 分值
				String fz = map.get("fz");

				if ("加分".equalsIgnoreCase(jjf)) {
					cxf += Integer.parseInt(fz);
				} else if ("减分".equalsIgnoreCase(jjf)) {
					cxf -= Integer.parseInt(fz);
				}
			}
		}
		
		boolean flag = compareTo(String.valueOf(cxf), tjz, gx);

		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者操行分为" + cxf + ",不满足申请条件！";
		}

		return message;
	}
	
	/**
	 * 判断评奖评优平均分成绩条件
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String judgeAvgCj(String xh, Map<String, String> jxjMap){

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 返回信息
		String message = null;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 条件说明
		String tjms = jxjMap.get("tjms");
		// 申请周期
		String sqzq = jxjMap.get("sqzq");
		// 关系
		String gx = jxjMap.get("gx");
		// 条件值
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select round(avg(cj),3) pjf from xg_view_cjxx_pjf ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		
		if ("xn".equalsIgnoreCase(sqzq)) {// 申请周期：学年
			sql.append(" and xn = '" + pjxn + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 申请周期：学期
			sql.append(" and xn = '" + pjxn + "' ");
			sql.append(" and xq = '" + pjxq + "' ");
		} else {// 申请周期：年度
			sql.append(" and nd = '" + pjnd + "' ");
		}
		
		// 平均分
		String pjf = dao.getOneRs(sql.toString(), new String[] {}, "pjf");
		pjf = StringUtils.isNull(pjf) ? "0" : pjf;
		
		boolean flag = compareTo(pjf, tjz, gx);

		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者平均分为" + pjf + ",不满足申请条件！";
		}

		return message;
	}
	
	/**
	 * 判断评奖评优平均分成绩条件
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String judgePjjd(String xh, Map<String, String> jxjMap){

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		// 返回信息
		String message = null;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 条件说明
		String tjms = jxjMap.get("tjms");
		// 关系
		String gx = jxjMap.get("gx");
		// 条件值
		String tjz = jxjMap.get("tjz");

		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(jd)/sum(xf)*100 pjjd from xg_view_cjxx_pjjd ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = '" + xh + "' ");
		sql.append(" and xn = '" + pjxn + "' ");
		
		// 平均分
		String pjjd = dao.getOneRs(sql.toString(), new String[] {}, "pjjd");
		pjjd = StringUtils.isNull(pjjd) ? "0" : pjjd;
		
		boolean flag = compareTo(pjjd, tjz, gx);

		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者平均绩点为" + pjjd + ",不满足申请条件！";
		}

		return message;
	}
	
	/**
	 * 获得学生评奖资格
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public String getStuPjzg(String xh, Map<String, String> map){
		
		// 条件类型
		String tjlx = map.get("tjlx");

		// 提示信息
		String message = "";

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// 逻辑关系
			message = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// 反向逻辑关系
			message = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// 最小分关系
			message = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// 平均分关系
			message = judgeAvgRelation(xh, map);
		}else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// 某情况关系
			message = judgeInstanceReverse(xh, map);
		}else {//调用方法单独处理
			message =specialtiesOperation(xh,map);
		}
		
		return message;
	}
	
	/**
	 * 判断逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicRelation(String xh, Map<String, String> map) {	
		
		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// 比较值
		String bjz = getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, true);
			
			//不满足条件的话
			if (!flag) {
				message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
						+ ",不满足申请条件！";
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}
		
		return message;
	}
	
	/**
	 * 判断反向逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicReverse(String xh, Map<String, String> map) {	
		
		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// 比较值
		String bjz = getBjz(xh, map, "");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, false);
			
			// 不满足条件的话
			if (!flag) {
				message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
						+ ",不满足申请条件！";
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}
		
		return message;
	}
	
	/**
	 * 判断逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeMinRelation(String xh, Map<String, String> map) {	
		
		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// 比较值
		String bjz = getBjz(xh, map, "Min");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, true);
			
			//不满足条件的话
			if (!flag) {
				message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
						+ ",不满足申请条件！";
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}
		
		return message;
	}
	
	/**
	 * 判断逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeAvgRelation(String xh, Map<String, String> map) {	
		
		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// 比较值
		String bjz = getBjz(xh, map, "AVG");
		bjz = Base.isNull(bjz) ? "0" : bjz;
		bjz = String.valueOf(Math.round(Double.parseDouble(bjz)));
		
		try {
			
			boolean flag = compareTo(bjz, tjz, gx, true);
			
			//不满足条件的话
			if (!flag) {
				message = "申请该项目需要：" + tjms + ",申请者为" + bjz + tsgs
						+ ",不满足申请条件！";
			}
		} catch (Exception e) {
			System.out.println("表（" + tablename + "）中存在非数字（" + zd + "）记录，请检查");
		}
		
		return message;
	}
	
	/**
	 * 判断逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		String bjz = getBjz(xh, map, "count");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		//if ("是".equalsIgnoreCase(tjz)) {// 存在该现象
			if (Integer.parseInt(bjz) > 0) {
				flag = true;
			}
		//}

		// 不满足条件的话
		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者为不满足该条件！";
		}

		return message;
	}
	
	/**
	 * 目标值和确定值比较
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private boolean compareTo(String bjz, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// 比较值和条件值非空
		if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
			// 关系为"="
			if ("=".equalsIgnoreCase(gx)) {
				flag = bjz.equalsIgnoreCase(tjz) ? true : false;
			}
			// 关系为">"
			else if (">".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0 ? true : false;
				}
			}
			// 关系为">="
			else if (">=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) > 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) > 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}
			}
			// 关系为"<"
			else if ("<".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0 ? true : false;
				}

			}
			// 关系为"<="
			else if ("<=".equalsIgnoreCase(gx)) {

				BigDecimal bbjz = new BigDecimal(bjz);
				BigDecimal btjz = new BigDecimal(tjz);

				if (relation) {
					flag = bbjz.compareTo(btjz) < 0
							|| bbjz.compareTo(btjz) == 0 ? true : false;
				} else {
					flag = btjz.compareTo(bbjz) < 0
							|| btjz.compareTo(bbjz) == 0 ? true : false;
				}

			}
		}

		return flag;
	}
	
	/**
	 * 特殊业务判断(单个申请) 
	 * ps 单个与批量由sqlx变量区分
	 * @param xh
	 * @param map
	 * @return
	 */
	public String specialtiesOperation(String xh,Map<String,String> map){
		
		//条件类型
		String tjlx = map.get("tjlx");
		String message="";
		
		try {
			HashMap<String,Object>tjInfo=new HashMap<String,Object>();
			
			PjpyTjszMethod pjpyTjszMethod=new PjpyTjszMethod();
			Class myClass = pjpyTjszMethod.getClass();
			
			tjInfo.putAll(map);
			tjInfo.put("sqlx", "sq");
			tjInfo.put("xh", new String[]{xh});
			
			message = (String) myClass
					.getMethod(tjlx,(Class[]) new Class[]{HashMap.class}).invoke(pjpyTjszMethod,
							tjInfo);
		} catch (Exception e) {
			System.out.println("评奖条件设置,method:"+tjlx+"遇到问题;");
			e.printStackTrace();
		}
		
		return message;
	}
	
	/**
	 * 特殊业务判断(单个申请) 
	 * ps 单个与批量由sqlx变量区分
	 * @param xh
	 * @param map
	 * @return
	 */
	public String specialtiesOperation(String[]xh,Map<String,String> map){
		
		//条件类型
		String tjlx = map.get("tjlx");
		String message="";
		
		try {
			HashMap<String,Object>tjInfo=new HashMap<String,Object>();
			
			PjpyTjszMethod pjpyTjszMethod=new PjpyTjszMethod();
			Class myClass = pjpyTjszMethod.getClass();
			
			tjInfo.putAll(map);
			tjInfo.put("sqlx", "sb");
			tjInfo.put("xh", xh);
			
			message = (String) myClass
					.getMethod(tjlx,(Class[]) new Class[]{HashMap.class}).invoke(pjpyTjszMethod,
							tjInfo);
		} catch (Exception e) {
			System.out.println("评奖条件设置,method:"+tjlx+"遇到问题;");
			e.printStackTrace();
		}
		
		return message;
	}
	
	/**
	 * 获得比较值
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String getBjz(String xh, Map<String, String> map, String lx) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 项目代码
		String xmdm = map.get("xmdm");
		// 条件代码
		String tjdm = map.get("tjdm");
		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 限制条件
		String condition = map.get("condition");
		// 特殊格式
		String tsgs = map.get("tsgs");
		// 学年限制
		String xn = map.get("xn");
		// 学期限制
		String xq = map.get("xq");
		// 年度限制
		String nd = map.get("nd");
		// 表字段
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 当前学年
		String dqxn = Base.currXn;
		// 当前学期
		String dqxq = Base.currXq;
		// 当前年度
		String dqnd = Base.currNd;

		// 过滤输入值
		ArrayList<String> inputV = new ArrayList<String>();
		inputV.add(xh);

		if ("cxpd无".equalsIgnoreCase(tjdm)) {
			inputV.add(xmdm);
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" select bjz from (");
		sql.append(" select ");

		// 类型非空
		if (!Base.isNull(lx)) {
			sql.append(lx);
			sql.append("(");
			sql.append(zd);
			sql.append(")");
		} else {
			sql.append(zd);
		}
		sql.append(" bjz from ");
		sql.append(tablename);
		sql.append(" a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		// 限制条件
		sql.append(Base.isNull(condition) ? "" : condition);

		// 需要控制学年
		if ("pjxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(pjxn);
		} else if ("dqxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(dqxn);
		}

		// 需要控制学期
		if ("pjxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(pjxq);
		} else if ("dqxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(dqxq);
		}

		// 需要控制年度
		if ("pjnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(pjnd);
		} else if ("dqnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(dqnd);
		}

		sql.append(" ) where rownum = 1 ");

		DAO dao = DAO.getInstance();
		// 比较值
		System.out.println(sql);
		String bjz = dao.getOneRs(sql.toString(), inputV
				.toArray(new String[] {}), "bjz");

		return bjz;
	}
	
	//=======================项目上报条件控制===============================
	/**
	 * 获得所上报的条件
	 * @param bjdm
	 * @param xmdm
	 * @return
	 */
	public List<HashMap<String, String>> getXmTj(String bjdm, String xmdm){
		String sql = "select * from xg_view_pjpy_tjms where xmdm=? and " +
					 "(tjfw=(select distinct xydm from view_xg_pjpy_ryqd where pjbjdm=?) or tjfw='all')";
		
		DAO dao = DAO.getInstance();

		String[] output = new String[] { "tjdm", "gx", "tjz", "tjms", "tjlx",
				"tablename", "zd", "condition", "xn", "xq", "nd", "tsgs",
				"bzd", "xmdm" };
		
		return dao.getList(sql, new String[] { xmdm, bjdm }, output);
	}
	
	/**
	 * 获得无评奖资格的学号
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public String getNoPjzgXh(String[] xh, Map<String, String> map) {

		// 条件类型
		String tjlx = map.get("tjlx");
		// 条件说明
		String tjms = map.get("tjms");

		// 无资格评奖学号
		String noPjzgXh = "";

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// 逻辑关系
			noPjzgXh = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// 反向逻辑关系
			noPjzgXh = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// 最小分关系
			noPjzgXh = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// 平均分关系
			noPjzgXh = judgeAvgRelation(xh, map);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// 某情况关系
			noPjzgXh = judgeInstanceReverse(xh, map);
		}else {//特殊业务
			noPjzgXh = specialtiesOperation(xh, map);
		}

		// 不满足条件的话
		if (!Base.isNull(noPjzgXh)) {
			DAO dao = DAO.getInstance();
			String xm = dao.getOneValue("view_xsjbxx", "xm", "xh", noPjzgXh);
			noPjzgXh = "申请该项目需要：" + tjms + "," + xm + "(" + noPjzgXh
					+ ")不满足申请条件！";
		}

		return noPjzgXh;
	}
	
	/**
	 * 判断逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicRelation(String[] xh, Map<String, String> map) {

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * 判断反向逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicReverse(String[] xh, Map<String, String> map) {

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, false);
		
		return noPjzgXh;

	}
	
	/**
	 * 判断小值关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeMinRelation(String[] xh, Map<String, String> map) {	

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "Min");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * 判断平均值关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeAvgRelation(String[] xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "AVG");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);

		return noPjzgXh;
	}
	
	/**
	 * 判断象关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String[] xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "count");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * 获得比较值
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private List<HashMap<String, String>> getBjz(String xh[],
			Map<String, String> map, String lx) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 项目代码
		String xmdm = map.get("xmdm");
		// 条件代码
		String tjdm = map.get("tjdm");
		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 限制条件
		String condition = map.get("condition");
		// 特殊格式
		String tsgs = map.get("tsgs");
		// 学年限制
		String xn = map.get("xn");
		// 学期限制
		String xq = map.get("xq");
		// 年度限制
		String nd = map.get("nd");
		// 表字段
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 当前学年
		String dqxn = Base.currXn;
		// 当前学期
		String dqxq = Base.currXq;
		// 当前年度
		String dqnd = Base.currNd;

		// 过滤输入值
		ArrayList<String> inputV = new ArrayList<String>();

		if ("cxpd无".equalsIgnoreCase(tjdm)) {
			inputV.add(xmdm);
		}

		StringBuilder sql = new StringBuilder();

		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(" union all ");
			}
			sql.append(" select a.xh,nvl(b.bjz,0) bjz from ");	
			sql.append(" (select '" + xh[i] + "' xh from dual) a left join ");
			sql.append(" (");
			sql.append(" select ");

			// 类型非空
			if (!Base.isNull(lx)) {
				sql.append(lx);
				sql.append("(");
				sql.append(zd);
				sql.append(")");
			} else {
				sql.append(zd);
			}
			sql.append(" bjz from ");
			sql.append(tablename);
			sql.append(" a ");
			sql.append(" where 1 = 1 ");
			sql.append(" and xh = '" + xh[i] + "' ");

			// 限制条件
			sql.append(Base.isNull(condition) ? "" : condition);

			// 需要控制学年
			if ("pjxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = ? ");
				inputV.add(pjxn);
			} else if ("dqxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = ? ");
				inputV.add(dqxn);
			}

			// 需要控制学期
			if ("pjxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = ? ");
				inputV.add(pjxq);
			} else if ("dqxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = ? ");
				inputV.add(dqxq);
			}

			// 需要控制年度
			if ("pjnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = ? ");
				inputV.add(pjnd);
			} else if ("dqnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = ? ");
				inputV.add(dqnd);
			}

			sql.append(" ) b on 1=1");
		}

		DAO dao = DAO.getInstance();

		// 比较值
		List<HashMap<String, String>> list = dao.getList(sql.toString(), inputV
				.toArray(new String[] {}), new String[] { "xh", "bjz" });

		return list;
	}
	
	/**
	 * 目标值和确定值比较
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private String compareTo(String[] xh,
			List<HashMap<String, String>> bjzList, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// 无资格学号
		String noPjzgXh = "";

		for (int i = 0; i < bjzList.size(); i++) {

			HashMap<String, String> bjzMap = bjzList.get(i);
			String bjz = bjzMap.get("bjz");

			// 比较值和条件值非空
			if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
				// 关系为"="
				if ("=".equalsIgnoreCase(gx)) {
					if("是".equalsIgnoreCase(tjz) && !"0".equalsIgnoreCase(bjz)){
						bjz = "是";
					}
					flag = bjz.equalsIgnoreCase(tjz) ? true : false;
				}
				// 关系为">"
				else if (">".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0 ? true : false;
					}
				}
				// 关系为">="
				else if (">=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}
				}
				// 关系为"<"
				else if ("<".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0 ? true : false;
					}

				}
				// 关系为"<="
				else if ("<=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}

				}
			}

			if (!flag) {
				noPjzgXh = bjzMap.get("xh");
				break;
			}
		}

		return noPjzgXh;
	}

	//=======================项目上报条件控制(修正版)===============================
	/**
	 * 获得无评奖资格的学号
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public String[] getNoPjzgXh(String[] xh, Map<String, String> map, String lx) {

		// 条件类型
		String tjlx = map.get("tjlx");
		// 条件说明
		String tjms = map.get("tjms");

		// 无资格评奖学号
		String[] noPjzgXh = null;

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// 逻辑关系
			noPjzgXh = judgeLogicRelation(xh, map, null);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// 反向逻辑关系
			noPjzgXh = judgeLogicReverse(xh, map, null);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// 最小分关系
			noPjzgXh = judgeMinRelation(xh, map, null);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// 平均分关系
			noPjzgXh = judgeAvgRelation(xh, map, null);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// 某情况关系
			noPjzgXh = judgeInstanceReverse(xh, map, null);
		}

		return noPjzgXh;
	}
	
	/**
	 * 判断逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeLogicRelation(String[] xh, Map<String, String> map,
			String lx) {

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, true,null);
		
		return noPjzgXh;
	}
	
	/**
	 * 判断反向逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeLogicReverse(String[] xh, Map<String, String> map,
			String lx) {

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, false,null);
		
		return noPjzgXh;

	}
	
	/**
	 * 判断小值关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeMinRelation(String[] xh, Map<String, String> map,
			String lx) {	

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "Min");

		String[] noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true,null);
		
		return noPjzgXh;
	}
	
	/**
	 * 判断平均值关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeAvgRelation(String[] xh, Map<String, String> map,
			String lx) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "AVG");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, true,null);

		return noPjzgXh;
	}
	
	/**
	 * 判断象关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String[] judgeInstanceReverse(String[] xh, Map<String, String> map,
			String lx) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = getBjz(xh, map, "count");

		String[] noPjzgXh = compareTo(xh, bjzList, tjz, gx, true, null);

		return noPjzgXh;
	}
	
	/**
	 * 目标值和确定值比较
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private String[] compareTo(String[] xh,
			List<HashMap<String, String>> bjzList, String tjz, String gx,
			boolean relation,String lx) {

		boolean flag = false;

		// 无资格学号
		ArrayList<String> noPjzgXh = new ArrayList<String>();

		for (int i = 0; i < bjzList.size(); i++) {

			HashMap<String, String> bjzMap = bjzList.get(i);
			String bjz = bjzMap.get("bjz");

			// 比较值和条件值非空
			if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
				// 关系为"="
				if ("=".equalsIgnoreCase(gx)) {
					if("是".equalsIgnoreCase(tjz) && !"0".equalsIgnoreCase(bjz)){
						bjz = "是";
					}
					flag = bjz.equalsIgnoreCase(tjz) ? true : false;
				}
				// 关系为">"
				else if (">".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0 ? true : false;
					}
				}
				// 关系为">="
				else if (">=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}
				}
				// 关系为"<"
				else if ("<".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0 ? true : false;
					}

				}
				// 关系为"<="
				else if ("<=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}

				}
			}

			if (!flag) {
				noPjzgXh.add(bjzMap.get("xh"));
			}
		}

		return noPjzgXh.toArray(new String[] {});
	}
}
