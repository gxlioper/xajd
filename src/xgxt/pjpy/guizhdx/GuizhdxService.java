package xgxt.pjpy.guizhdx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.lsxy.LsxyPjpyService;
import xgxt.pjpy.nbcs.pxpj.PxpjNbcsService;
import xgxt.pjpy.ntzydx.PjpyNtzydxService;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 贵州大学评奖
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 屈朋辉
 * @version 1.0
 */
public class GuizhdxService {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	GuizhdxDAO dao = new GuizhdxDAO();
	
	
	/**
	 * 获取 某班的班主任
	 * @param bjdm
	 * @return
	 * @throws SQLException
	 */
	public String getBzr(String bjdm) throws SQLException {
		
		return dao.getBzr(bjdm);
	}
	
	
	/**
	 * 发送页面参数
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request , String flg) {
		DAO dao = DAO.getInstance();
		GuizhdxDAO gDao = new GuizhdxDAO();
		if ("tjsz".equals(flg)) {
			List jxjList = dao.getWhList("jxjdmb", "jxjdm", "jxjmc", "", "sfqy", "yes");
			request.setAttribute("jxjList", jxjList);
			
			request.setAttribute("fjshList", getSelectList("fjsh"));
			
			request.setAttribute("isNot", getSelectList("isNot"));
			request.setAttribute("sqlb", getSelectList("sqlb"));
		} else if ("rych".equals(flg)) {
			List rychList = dao.getWhList("rychdmb", "rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);
			
			request.setAttribute("shjbList", getSelectList("fjsh"));
			request.setAttribute("isNot", getSelectList("isNot"));
		} else if ("priseCheck".equals(flg)) {
			request.setAttribute("xn", Base.getJxjsqxn());
			request.setAttribute("nd", Base.getJxjsqnd());
			
			List jxjList = dao.getWhList("jxjdmb", "jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);
		} else if ("creditCheck".equals(flg)) {
			request.setAttribute("xn", Base.getJxjsqxn());
			request.setAttribute("nd", Base.getJxjsqnd());
			
			List rychList = dao.getWhList("rychdmb", "rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);
		} else if ("zhcpjxj".equalsIgnoreCase(flg)){
			//综合测评奖学金
			List jxjList = dao.getWhList("zhcpjxjdmb", "jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);
		}
		
		List<HashMap<String, String>> shztList = getSelectList("shzt");
		request.setAttribute("shztList", shztList);
		
		
		//综测分
		String[] title = new String[] {"德育成绩","智育成绩","体育成绩","总分","智育成绩排名","总分排名"};
		request.setAttribute("zcTitle", dao.arrayToList(title, title));
		
		
		request.setAttribute("nowTime", getNow());
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
	}
	
	
	/**
	 * 下拉列表值
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String flg) {
		DAO dao = DAO.getInstance();
		String[] dm = null;
		String[] mc = null;
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		 if ("shzt".equalsIgnoreCase(flg)) {
			dm = new String[] { "未审核", "通过","不通过" };
			mc = new String[] { "未审核", "通过" ,"不通过"};
		} else if ("fjsh".equals(flg)){
			dm = new String[] { "1", "2","3" };
			mc = new String[] { "一级（非学生用户）", "二级("+Base.YXPZXY_KEY+"→学校)" ,"三级（辅导员→"+Base.YXPZXY_KEY+"→学校）"};
		} else if ("isNot".equals(flg)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否"};
		} else if ("sqlb".equals(flg)) {
			dm = new String[] { "竞赛", "著作/论文","专利、发明","其他" };
			mc = new String[] { "竞赛", "著作/论文","专利、发明","其他"};
		}
		 
		 
		return dao.arrayToList(dm, mc);
	}
	
	
	/**
	 * 获取 学生基本信息
	 * @param xh
	 * @return
	 */
	public HashMap<String , String> getStuInfo(String xh) {
		XsxxglService service = new XsxxglService();
		
		return service.selectStuinfo(xh);
	}
	
	
	/**
	 * 当前时间
	 * @return
	 */
	public String getNow() {
		
		return dao.getNow();
	}
	
	
	/**
	 * 获取奖学金审核级别
	 * @param jxjdm
	 * @return
	 */
	public String getShjb(String jxjdm) {
		
		return dao.getShjb(jxjdm);
	}
	
	
	/**
	 * 获取审核查询中要自定义的字段
	 * @param yhlx
	 * @param shjb
	 * @return String
	 * */
	public void getCustomAudiColumn(HttpServletRequest request, String yhlx, String shjb){
		
		StringBuilder sb = new StringBuilder();
		if("3".equalsIgnoreCase(shjb)){//三级审核
			if (yhlx.equalsIgnoreCase("xy")) {
				sb.append(" (case when xxsh='未审核' then '' else 'disabled' end) disabled,");
				sb.append(" (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if (yhlx.equalsIgnoreCase("fdy")){
				sb.append(" (case when xysh='未审核' then '' else 'disabled' end) disabled, ");
				sb.append(" (case when nvl(fdysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if (Base.isNull(yhlx)) {
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(shzt,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if ("stu".equals(yhlx)){
				sb.append(" (case when fdysh='未审核' then '' else 'disabled' end) disabled,");
			}else{
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}
		}else if("2".equalsIgnoreCase(shjb)){//二级审核
			if (yhlx.equalsIgnoreCase("xy")) {
				sb.append(" (case when xxsh='未审核' then '' else 'disabled' end) disabled,");
				sb.append(" (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else if (Base.isNull(yhlx)) {
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(shzt,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else{
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}
		} else if("1".equalsIgnoreCase(shjb)){//一级审核
			if (Base.isNull(yhlx)) {
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(shzt,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}else{
				sb.append(" '' disabled, ");
				sb.append(" (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
			}
		}
		
		request.setAttribute("clientColumns", sb.toString());
		
		if (Globals.XXDM_NBCSZYJSXY.equals(Base.xxdm) && Base.isNull(request.getParameter("jxjdm"))) {
			
			
			request.setAttribute("annexTerm", " and (not exists " +
					"(select 1 from (select a.*,case when a.shjb = 1 then " +
					"a.shzt else a.xxsh end shzz from view_typj_jxjsqb a) where xh=a.xh and  shzz='通过' " +
					"and xn=(select jxjsqxn from xtszb) and a.jxjmc != '素质与能力拓展成就奖学金') or shzt='通过' or xxsh='通过')");
		}
		
	}
	
	
	/**
	 * 获取学生的处分信息
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> getStuCfxx(String xh, String xn){
		
		PjpyCommonInterface pjpy = new PjpyCommonInterface();
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("xh", xh);
		map.put("xn", xn);
		
		return pjpy.queryStuCfxx(map);
	} 
	
	
	/**
	 * 查询学生成绩
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<String[]> getStuCjxx(String xh, String xn) {
		PjpyCommonInterface pjpy = new PjpyCommonInterface();
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("xh", xh);
		map.put("xn", xn);
		
		return pjpy.queryStuCjxx(map);
	}
	
	/**
	 * 查询学生成绩
	 * @param xh
	 * @param xn
	 * @return
	 */
	public List<HashMap<String,String>> getStuCjxx(HashMap<String, String> map) {
		
		
		return dao.queryStuCjxx(map);
	}
	
	
	/**
	 * 获取荣誉称号审核级别
	 * @param rychdm
	 * @return
	 */
	public String getShjbForRych(String rychdm) {
		
		return dao.getShjbForRych(rychdm);
	}

	
	/**
	 * 剩余名额
	 * @param map
	 * @return
	 */
    public String getSyme(HashMap<String, String> map) {
		
		String syme = "";
		
		if (!Base.isNull(map.get("jxjdm"))) {
			syme = dao.getSymeForJxj(map);
		} else if (!Base.isNull(map.get("rychdm"))) {
			syme = dao.getSymeForRych(map);
		}
		
		if (Base.isNull(syme)) {
			syme = "无限制";
		}
		return syme;
	}
    
    
    /**
     * 批量保存审核级别
     * @param dm
     * @param shjb
     * @param flg
     * @return
     * @throws SQLException 
     */
    public boolean saveBatchShjb(String[] dm,String shjb,String flg) throws SQLException {
    	
    	if ("jxj".equals(flg)) {
    		return dao.saveBatchShjbForJxj(dm, shjb);
    	} else {
    		return dao.saveBatchShjbForRych(dm, shjb);
    	}
    	
    }
    
    /**
	 * 综测分及排名
	 * @param xh
	 * @param xn
	 * @return
	 */
    public List<HashMap<String, String>> getZcfPm(String xh, String xn) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xh", xh);
		map.put("xn", xn);
		PjpyCommonInterface commService = new PjpyCommonInterface();

		List<HashMap<String, String>> zccj = commService
				.queryStuZhszcpCjAndPm(map);
		
		zccj.addAll(commService.queryStuCjAndPm(map));

		return zccj;
	}
	
    /**
	 * 综测分及排名
	 * @param xh
	 * @param xn
	 * @return
	 */
    public List<HashMap<String, String>> getZcfPm(String xh, String xn,String xq) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xq", xq);
		PjpyCommonInterface commService = new PjpyCommonInterface();

		List<HashMap<String, String>> zccj = commService
				.queryStuZhszcpCjAndPm(map);
		
		zccj.addAll(commService.queryStuCjAndPm(map));

		return zccj;
	}
    
    /**
	 * 综测分及排名
	 * @param xh
	 * @param xn
	 * @return
	 */
    public List<HashMap<String, String>> getZcfPm(String xh) {
    	PjpyTyService tyService = new PjpyZjkjxyService();
    	String zczq = tyService.getZhcpSqzq();
    	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xh", xh);	
		
		if("xq".equalsIgnoreCase(zczq) || "xn".equalsIgnoreCase(zczq)){
			map.put("xn", Base.getJxjsqxn());
		}
		if("xq".equalsIgnoreCase(zczq)){
			map.put("xq", Base.getJxjsqxq());
		}
		
		PjpyCommonInterface commService = new PjpyCommonInterface();

		List<HashMap<String, String>> zccj = commService.queryStuZhszcpCjAndPm(map);
		zccj.addAll(commService.queryStuCjAndPm(map));

		return zccj;
	}
	
	/**
	 * 学生所在班的班级人数
	 * @param xh
	 * @return
	 */
	public String getBjrs(String xh) {
		return CommonQueryDAO.getBjrs(xh);
	}


	/**
	 * 是否违纪
	 * @param xh
	 * @param xn
	 * @return
	 */
	protected boolean isWj(HashMap<String, String> map) {
		
		return Boolean.valueOf(dao.getIswj(map));
	}
	
	
	/**
	 * 
	 *贵州大学条件判断
	 */
	public HashMap<String, String> pjpyTjsz(HashMap<String, String> map) {
		List<HashMap<String, String>> tjList = dao.getTjList(map);
		List<HashMap<String, String>> zcfList = dao.getZcf(map);
		List<HashMap<String, String>> zfList = dao.getZf(map);
		String mc = "";//条件字段对应综测分里的名称
		String flg = "";//fs:分数、pm:名次
		String data = "0"; //条件字段对应综测分里的数据
		String msg = "";//返回消息
		boolean result = true;
		
		/*
		 * 根据条件结果循环判断是取相应数据
		 */
		if(zcfList.size()>0){
			for (HashMap<String, String> temp : tjList) {
				
				if ("dycpf".equals(temp.get("tjzd"))) {
					mc = "德育测评分";
					flg = "fs";
					msg = "德育测评分不满足条件!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zycpf".equals(temp.get("tjzd"))) {
					mc = "智育测评分";
					flg = "fs";
					msg = "智育测评分不满足条件!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("wtcpf".equals(temp.get("tjzd"))) {
					mc = "文体测评分";
					flg = "fs";
					msg = "文体测评分不满足条件!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zhszcpf".equals(temp.get("tjzd"))) {
					mc = "总分";
					flg = "fs";
					msg = "综合素质测评分不满足条件!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zhszcpfpm".equals(temp.get("tjzd"))) {
					mc = "总分";
					flg = "pm";
					msg = "综合素质测评分排名不满足条件!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zycpfpm".equals(temp.get("tjzd"))) {
					mc = "智育测评分";
					flg = "pm";
					msg = "智育测评分不满足条件!";
					data = getZcfxx(zcfList, mc, flg);
				} else if ("zdkccj".equals(temp.get("tjzd"))) {
					msg = "最低课程成绩不满足条件!";
					data = dao.getMincj(map);
				} else if ("bjgmc".equals(temp.get("tjzd"))) {
					msg = "不及格门次不满足条件";
					data = dao.getBjgmc(map);
				} else if("zyjd".equals(temp.get("tjzd"))){//智育总绩点
					mc="zyjd";
					msg="智成绩总绩点不满足条件！";
					flg="zyjd";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				} else if("zpmbl".equals(temp.get("tjzd"))){//总分排名比例
					mc="zpmbl";
					msg="总分排名比例不满足条件！";
					flg="zpmbl";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				} else if("zypm".equals(temp.get("tjzd"))){//智育成绩排名
					mc="zypm";
					msg="智育成绩排名不满足条件！";
					flg="zypm";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				} else if("zypmbl".equals(temp.get("tjzd"))){//智育成绩排名比例
					mc="zypmbl";
					msg="智育成绩排名比例不满足条件！";
					flg="zypmbl";
					data = getZf(zfList, mc, flg);
					if(data==null){
						result=false;
					}
				}
				
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//比较数据
				
				if (!result) {
					break;
				}
				
			}
		}
		
		if (isWj(map)) {
			result = false;
			msg = "您有违纪记录!";
		}
		
		HashMap<String, String> tjgl = new HashMap<String, String>();
		
		tjgl.put("message", msg);
		tjgl.put("result", String.valueOf(result));
		
		return tjgl;
	}
	
	/**
	 * 获取综测分结果集里相应的数据
	 * @param zcfList
	 * @param mc
	 * @param flg
	 * @return
	 */
	public String getZcfxx(List<HashMap<String, String>> zcfList,String mc,String flg) {
		
		for (HashMap<String, String> map : zcfList) {
			if (mc.equals(map.get("mc"))) {
				return map.get(flg);
			}
		}
		
		return null;
	}
	
	/**
	 * 获取总分信息
	 * @param zcfList
	 * @param mc
	 * @param flg
	 * @return
	 */
	public String getZf(List<HashMap<String, String>> zfList,String mc,String flg) {
		
		if(zfList.size()>0){
			HashMap<String, String> map =zfList.get(0);
			return map.get(flg);
		}
		
		return null;
	}
	
	
	/**
	 * 判断现有数据是否满足条件
	 * @param tjlx
	 * @param tjz
	 * @param data
	 * @return
	 */
	public boolean isFhtj(String tjlx,String tjz,String data) {
		
		if (Base.isNull(data)) {
			return false;
		}
		
		double dtjz = Double.valueOf(tjz);
		double ddata = Double.valueOf(data);
		
		if (">".equals(tjlx)) {
			return ddata > dtjz;
		} else if(">=".equals(tjlx)) {
			return ddata >= dtjz;
		} else if("=".equals(tjlx)) {
			return ddata == dtjz;
		} else if("<".equals(tjlx)) {
			return ddata < dtjz;
		} else if("<=".equals(tjlx)) {
			return ddata <= dtjz;
		}
		
		return true;
	}
	
	
	/**
	 * 先进班级的剩余名额 
	 * @param xn
	 * @param xydm
	 * @return
	 */
	public String getXjbjSyme(String xn, String xydm) {
		
		return dao.getXjbjSyme(xn, xydm);
	}
	
	
	/**
	 * 宁波城市条件判断
	 * @param map
	 * @return
	 */
	public HashMap<String, String> nbcxPjpyTjsz(HashMap<String, String> map) {
		List<HashMap<String, String>> tjList = dao.getTjList(map);

		String msg = "";
		String data = "0";
		boolean result = true;
		
		for (HashMap<String, String> temp : tjList) {

			if ("xjf".equals(temp.get("tjzd"))) {
				float bjrs = Float.parseFloat(CommonQueryDAO.getBjrs(map.get("xh")));
				float xjfpm = Base.isNull(getXjf(map).get("xjfpm")) ? bjrs : Float.parseFloat(getXjf(map).get("xjfpm"));
				data = String.valueOf(Double.valueOf((xjfpm/bjrs*100)));
				msg = "学绩分在班级排名百分比不满足!";
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);
			} else if ("pxdj".equals(temp.get("tjzd"))) {
				msg = "学生品行等级不满足条件!";
				data =  getPxdj(map);
				result = isPxdj(temp.get("tjlx"), temp.get("tjz"), data);
			}
			
			if (!result) {
				break;
			}
		}
		
		if (isWj(map)) {
			result = false;
			msg = "您有违纪记录!";
		} 
		if(dao.iscx(map)) {
			result =false;
			msg = "对不起，你的必修课中含有重修记录，不能申请该奖学金!";
		}
		
		
		HashMap<String, String> tjgl = new HashMap<String, String>();
		
		tjgl.put("message", msg);
		tjgl.put("result", String.valueOf(result));
		
		return tjgl;
	}
	
	
	/**
	 * 获取学生的品行等级
	 * @param map
	 * @return
	 */
	public String getPxdj(HashMap<String, String> map) {

		PjpyTyForm model = new PjpyTyForm();
		PxpjNbcsService service = new PxpjNbcsService();
		List<HashMap<String, String>> list = null;

		model.setBpjr(map.get("xh"));
		model.setXn(map.get("xn"));

		try {
			list = service.getPxpjInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if (null != list && !list.isEmpty()) {	
			return list.get(0).get("pj");
		} else {
			return "无";
		}
		
	}
	
	/**
	 * 判断现有数据是否满足条件
	 * @param tjlx
	 * @param tjz
	 * @param data
	 * @return
	 */
	public boolean isPxdj(String tjlx, String tjz, String data) {

		if (Base.isNull(data)) {
			return false;
		}

		if ("A".equals(tjz)) {
			if (">".equals(tjlx)) {
				return false;
			} else if(">=".equals(tjlx)) {
				return "A".equals(data);
			} else if("=".equals(tjlx)) {
				return "A".equals(data);
			} else if("<".equals(tjlx)) {
				return "B".equals(data) || "C".equals(data) || "D".equals(data);
			} else if("<=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data) || "D".equals(data);
			}
		} else if ("B".equals(tjz)) {
			if (">".equals(tjlx)) {
				return "A".equals(data);
			} else if(">=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data);
			} else if("=".equals(tjlx)) {
				return "B".equals(data);
			} else if("<".equals(tjlx)) {
				return "C".equals(data) || "D".equals(data);
			} else if("<=".equals(tjlx)) {
				return "B".equals(data) || "C".equals(data) || "D".equals(data);
			}
		} else if ("C".equals(tjz)) {
			if (">".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data);
			} else if(">=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data);
			} else if("=".equals(tjlx)) {
				return "C".equals(data);
			} else if("<".equals(tjlx)) {
				return "D".equals(data);
			} else if("<=".equals(tjlx)) {
				return "C".equals(data) || "D".equals(data);
			}
		} else if ("D".equals(tjz)) {
			if (">".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data);
			} else if(">=".equals(tjlx)) {
				return "A".equals(data) || "B".equals(data) || "C".equals(data) || "D".equals(data);
			} else if("=".equals(tjlx)) {
				return "D".equals(data);
			} else if("<".equals(tjlx)) {
				return false;
			} else if("<=".equals(tjlx)) {
				return "D".equals(data);
			}
		} else {
			return false;
		}

		return false;
	}
	
	
	/**
	 * 宁波城市学绩分
	 * @param map
	 * @return
	 */
	public HashMap<String,String> getXjf(HashMap<String, String> map) {
		
		return dao.getXjf(map);
	}
	
	
	/**
	 * 家庭辅助信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsfzxx(String xh) {
			return dao.getXsfzxx(xh);
	}
	
	/**
	 * 获取成绩排名表信息
	 */
	public HashMap<String,String>getCjpmbXx(String xh){
		return dao.getCjpmXx(xh);
	}
	
	/**
	 * 获取评奖周期
	 * @param xxdm
	 * @param xnnd
	 * @return String[]
	 * @author lr
	 * */
	public String[] getXnndArr(String xxdm, String[] xnnd){
		HashMap<String, String[]> xnndMap = new HashMap<String, String[]>();
		xnndMap.put(Globals.XXDM_LSXY, new String[]{"xn"});//丽水学院
		
		xnnd = xnndMap.get(xxdm) == null ? xnnd : xnndMap.get(xxdm);
		return xnnd;
	}
	
	/**
	 * 荣誉称号审核级别
	 * @param xxdm
	 * @return String
	 * @author lr
	 * */
	public String getRychShjb(String xxdm){
		String result = "3";
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
			result = "2";
		}
		return result;
	}
	
	/**
	 * 判断荣誉称号审核条件
	 * @param xxdm
	 * @param model
	 * @param shjb
	 * @return String
	 * @author lr
	 * */
	public String checkRychTj(String xxdm, 
							  String[] pkValue,
							  String shzd,
							  String shjg,
							  User user){
		String result = "";
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm) && "通过".equalsIgnoreCase(shjg)){//丽水学院
			for(int i=0; i<pkValue.length; i++){
				LsxyPjpyService service = new LsxyPjpyService();
				HashMap<String, String> map = service.getXsrychbByPk(pkValue[i]);
				map.put("jxjdm", map.get("rychdm"));
				boolean flag = service.checkRychRsxz(map);
				
				if (!flag) {
					result += (i+1);	
					result += ",";
					continue;
				}
				try{
					StandardOperation.update("xsrychb",new String[]{shzd},new String[]{shjg},"xh||xn||rychdm||xq",pkValue[i],user);
				} catch(Exception e){					
				}				
			}
		}else{
			for(int i=0; i<pkValue.length; i++){				
				try{
					StandardOperation.update("xsrychb",new String[]{shzd},new String[]{shjg},"xh||xn||rychdm||xq",pkValue[i],user);
				} catch(Exception e){	
					
				}				
			}
		}
		result = StringUtils.isNull(result) ? "操作成功！" 
				                            : "第" + result.substring(0, 
				                            		 result.length()-1) 
				                            		+ "条数据操作失败，人数超过设置人数！";
		return result;
	}
	
	/**
	 * 是否有审核条件
	 * @param xxdm
	 * @return boolean
	 * */
	public boolean hasShtj(String xxdm){
		boolean result = false;
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
			//丽水学院有人数限制
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 查询班级学生评奖评优信息
	 * @param map 里面key必须要有 bjdm,xn
	 * @return {"学年", "班级", "获奖奖项", "获奖人数"}
	 */
	public List<String[]> getStuPjpyxx(HashMap<String, String> map) {
		return dao.getStuPjpyxx(map);
	}
	
	/**
	 * 判断学生是否可申请
	 * @param xh
	 * @param xmdm
	 * @param key
	 * @return boolean
	 * */
	public boolean xssqFlag(String xh,String xmdm, String key){
		String tableName = "pjpy_jxjsqxsb";
		String pk = "xh";
		String pkValue = xh;
		
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pjzq = service.getPjpySqzq();//评奖周期
		
		if("xq".equalsIgnoreCase(pjzq)){
			pk += "||xn||xq||xmdm||key";
			pkValue += Base.getJxjsqxn()+Base.getJxjsqxq()+xmdm+key;
		}else if("xn".equalsIgnoreCase(pjzq)){
			pk += "||xn||xmdm||key";
			pkValue += Base.getJxjsqxn()+xmdm+key;
		}else if("nd".equalsIgnoreCase(pjzq)){
			pk += "||nd||xmdm||key";
			pkValue += Base.getJxjsqnd()+xmdm+key;
		}
		
		return dao.checkExists(tableName, pk, pkValue) ? true : false;
	}
	
	/**
	 * 判断班主任是否审核
	 * @return String
	 * */
	public String getBzrshFlag(){
		String bzrSh = "false";
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			bzrSh = "true";
		}		
		return bzrSh;
	}
	
	/**
	 * 国家奖学金申请周期获取申请时间信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxjSqsjMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyZjkjxyService service = new PjpyZjkjxyService();
		String pjzq = service.getPjpySqzq();//评奖周期
		
		if(!Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){//非浙江科技
			pjzq = "xn";
		}
		
		if("xq".equalsIgnoreCase(pjzq)){
			//学期
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", Base.getJxjsqxq());
			map.put("nd", "无");
			map.put("sqsj", "无");
		}else if("xn".equalsIgnoreCase(pjzq)){
			//学年
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", "无");
			map.put("nd", "无");
			map.put("sqsj", "无");
		}else if("nd".equalsIgnoreCase(pjzq)){
			//年度
			map.put("xn", "无");
			map.put("xq", "无");
			map.put("nd", Base.getJxjsqnd());
			map.put("sqsj", "无");
		}else{
			//无周期
			map.put("xn", "无");
			map.put("xq", "无");
			map.put("nd", "无");
			map.put("sqsj", GetTime.getNowTime2());
		}		
		return map;
	}
	
	/**
	 * 奖学金条件判断
	 * @param jxjpdModel
	 * @param lb
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> checkSqtj(JxjpdxxModel jxjpdModel, String lb){
		HashMap<String, String> tjInfo = new HashMap<String, String>();
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//浙江科技
			LsxyPjpyService lsxyService = new LsxyPjpyService();
			jxjpdModel.setXq("");//评奖周期为学期
			tjInfo = lsxyService.pdStuTjFlag(jxjpdModel,lb);
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//南通职业大学
			PjpyNtzydxService service = new PjpyNtzydxService();
			tjInfo = service.pdStuTjFlag(jxjpdModel, lb);
		}else {
			tjInfo.put("result","true");
			tjInfo.put("message","");
		}
		return tjInfo;
	}


	
	/**
	 * 补考科目数
	 * @param map
	 * @return
	 */
	public String getBkkms(HashMap<String,String> map) {
		return dao.getBkkms(map);
	}


	/**
	 * 综测总分及排名
	 * @param map
	 */
	public HashMap<String,String> getZcf(HashMap<String,String> map) {
		List<HashMap<String, String>> zcfList = dao.getZcf(map);
		
		String mc = "总分";
		String flg = "fs";
		String data = getZcfxx(zcfList, mc, flg);
		
		map.put("zcf", data);
		
		flg = "pm";
		data = getZcfxx(zcfList, mc, flg);
		map.put("zcfPm", data);
		
		return map;
	}
	
	
	/**
	 * 贵州大学综测和分数
	 * @param map
	 * @return
	 */
	public HashMap<String,String> getGuizhdxCj(HashMap<String,String> map){
		
		return dao.getGuizhdxCj(map);
	}
	
	/**
	 * 获取奖学金上报查询表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getJxjsbTitle(){
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = new String[]{"pk","xn","xqmc","xh","xm","nj","xymc","zymc","bjmc","jxjmc","je","xysh","shzt"};
		String[] cn = new String[]{"主键","学年","学期","学号","姓名","年级",Base.YXPZXY_KEY,"专业","班级","奖学金","金额","学院审核","审核状态"};
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询奖学金上报信息
	 * @param model
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsb(GuizhdxForm model,User user){
		String[] en = new String[]{"pk","xn","xqmc","xh","xm","nj","xymc","zymc","bjmc","jxjmc","je","xysh","shzt"};
		return dao.selectJxjsb(model, en,user);
	}
	
	/**
	 * 保存奖学金上报信息
	 * @param pkValues
	 * @param jxjdm
	 * @param user
	 * @return boolean
	 * */
	public boolean jxjSb(String[] pkValues, GuizhdxForm model, User user){
		return dao.saveJxjsb(pkValues, model, user);
	}
	
	/**
	 * 取消奖学金上报
	 * @param pkValues
	 * @param user
	 * @return boolean
	 * */
	public boolean jxjQxsb(String[] pkValues, User user){
		return dao.delJxjsb(pkValues, user);
	}
}

