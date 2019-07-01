package xgxt.qgzx.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.dao.XsgwglDAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.hngydx.service.HngydxXsqgzxService.saveFreeTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学学生岗位管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-01-05</p>
 */
public class XsgwglService {
	QgzxDao dao = new QgzxDao();
	
	/**
	 * 获取广州大学学生空闲时间列表
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableGzdx(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri"};
		String[] freeTime = {"第一大节", "第二大节", "第三大节", "第四大节"};
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * 获取浙江传媒学生空闲时间列表
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableZjcm(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = {"第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节", "第8节" };
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * 获取上海工程学生空闲时间列表
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableShgc(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = {"第1-2节", "第3-4节", "午休", "第5-6节", "第7-8节", "晚上" };
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * 获取重庆科技学生空闲时间列表
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableCqkj(String xh, HttpServletRequest request) throws Exception{
		//重庆科技
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = { "早自修（7:30—8:20）", "第1-2节（8:30—10:05）", "第3-4节（10:25—12:00）", "午休（12:00—13:45）",
				"第5-6节（13:50—15:25）", "第7-8节（15:30—17:05）", "晚自修（17:50—20:15）" };
		
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * 获取中国地址大学学生空闲时间列表
	 * @param xh
	 * @return List
	 * @throws Exception
	 * */
	public void freeTimeTableZgdzdx(String xh, HttpServletRequest request) throws Exception{
		String[] week = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		String[] freeTime = {"第一大节", "第二大节", "第三大节", "第四大节"};
		String tableName = "xsqgzxsjb";
		fillArray(xh, tableName, freeTime, week, request);
	}
	
	/**
	 * 填充结果集
	 * @param xh
	 * @param tableName
	 * @param freeTime
	 * @param week
	 * @param request
	 * @throws Exception
	 * */
	@SuppressWarnings("unchecked")
	public void fillArray(String xh, String tableName,String[] freeTime, String[] week, HttpServletRequest request) throws Exception{
		List kxList = new ArrayList<HashMap>();
		int dataCount = dao.getDataCount(tableName, "xh", xh);
		if(xh != null && !xh.equalsIgnoreCase("") && dataCount>0){
			String[] kxbz = dao.getFreeTimeArray(xh);
			if(kxbz.length >= freeTime.length*week.length){
				String[] kx = new String[week.length];
				int j = 0;
				
				for (int i = 0; i < freeTime.length; i++) {
					for (int x = 0; x < week.length; x++) {
						
						kx[x] = kxbz[x + j];
					}
				j += week.length;
				HashMap<String, String> map2 = new HashMap<String, String>();
				for (int p = 0; p < week.length; p++) {
					map2.put(week[p], String.valueOf(kx[p]));
				}
				map2.put("sj", freeTime[i]);
				map2.put("sjIndex", String.valueOf(i));
				kxList.add(map2);
				}
			}else if(kxbz.length == 28){//中国地质大学填充学生空闲时间列表
				String[] kx = new String[week.length];
				int j = 0;
				
				for (int i = 0; i < freeTime.length; i++) {
					for (int x = 0; x < week.length; x++) {
						
						kx[x] = kxbz[x + j];
					}
				j += 7;
				HashMap<String, String> map2 = new HashMap<String, String>();
				for (int p = 0; p < week.length; p++) {
					map2.put(week[p], String.valueOf(kx[p]));
				}
				map2.put("sj", freeTime[i]);
				map2.put("sjIndex", String.valueOf(i));
				kxList.add(map2);
				}
			}else{
				for (int i = 0; i < freeTime.length; i++) {
					HashMap<String, String> map2 = new HashMap<String, String>();
					map2.put("sj", freeTime[i]);
					map2.put("sjIndex", String.valueOf(i));
					kxList.add(map2);
				}
			}
			request.setAttribute("kxList", kxList);
		}else{
			for (int i = 0; i < freeTime.length; i++) {
				HashMap<String, String> map2 = new HashMap<String, String>();
				map2.put("sj", freeTime[i]);
				map2.put("sjIndex", String.valueOf(i));
				kxList.add(map2);
			}
			request.setAttribute("whkxList", kxList);
			request.setAttribute("kxbz", "no");			
		}
	}
	
	/**
	 * 获取学生的个人信息
	 * @param xh
	 * @return HashMap<String,String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);		
	}
	
	/**
	 * 判断是否在设定的学生申请岗位时间内
	 * @return String 
	 * @throws SQLException
	 * */
	public String checkTime() throws SQLException{
		String sql = "select * from gwsqsjb where kssj<to_char(sysdate,'yyyymmddhh24miss') and jssj>to_char(sysdate,'yyyymmddhh24miss')";
		String tag = dao.returntag(sql, new String[] {});
		return tag;
	}
	
	/**
	 * 按岗位设置岗位可申请时间情况先的可申请岗位列表
	 * @return List
	 * */
	public List getKsqgw(){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getKsqgw();
	}
	
	/**
	 * 获取勤工助学参数设置信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxConfig(){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getQgzxConfig();
	}
	
	/**
	 * 保存学生申请岗位信息
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception  
	 * */
	public boolean saveGwsq(QgzxForm model, HttpServletRequest request) throws Exception{
		XsgwglDAO gwglDAO = new XsgwglDAO();
		boolean flag = false;
		String xh = DealString.toGBK(model.getXh());
		String xn = DealString.toGBK(model.getXn());
		String xq = DealString.toGBK(model.getXq());
		String nd = DealString.toGBK(model.getNd());
		String xmdm = DealString.toGBK(model.getXmdm());
		String jsjsp = DealString.toGBK(model.getJsjsp());
		String sffcfp = DealString.toGBK(model.getSffcfp());
		String lxdh = DealString.toGBK(model.getLxdh());
		String yhtc = DealString.toGBK(model.getYhtc());
		String mqqgzxqk = DealString.toGBK(model.getMqqgzxqk());
		
		String tableName = "xsgwxxb";
		String pk = "xh||gwdm||'-'||gwsbsj";
		String pkValue = xh+xmdm;
		String[] columns = {"jsjsp","sffcfp","lxdh","yhtc","mqqgzxqk"};
		String[] values = {jsjsp, sffcfp, lxdh, yhtc,mqqgzxqk};
		
		//判断学生是否在勤工助学黑名单中
		if(dao.checkExists("qgzx_zgdzdx_hmdb", "xh", xh)){
			request.setAttribute("isHmd", "true");
		}else{
			if(dao.checkExists(tableName, pk, pkValue)){
				//update
				flag = StandardOperation.update(tableName, columns, values, pk, pkValue, request);
			}else{
				//insert
				HashMap<String, String> map = gwglDAO.getGwxxByPk(xmdm);
				String gwdm = map.get("gwdm");
				String gwsbsj = map.get("gwsbsj");
				
				columns = new String[]{"xh","gwdm","gwsbsj","jsjsp","sffcfp","lxdh","yhtc","xn","nd","xq","mqqgzxqk"};
				values = new String[]{xh, gwdm, gwsbsj, jsjsp, sffcfp, lxdh, yhtc,xn,nd,xq,mqqgzxqk};
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
			
			if(flag){//保存学生可参加勤工助学时间信息
				saveFreeTime sft = new saveFreeTime(request,xh,8,7);
				new Thread(sft).start();
			}
		}
		
		return flag;
	}
	
	/**
	 * 获取学生申请岗位的详细信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuGwxx(QgzxForm model){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getStuGwxx(model);
	}
	
	/**
	 * 获取学生空闲时间标志
	 * @param xh
	 * @return List
	 * @throws Exception 
	 * */
	public List getFreeTimeList(String xh) throws Exception{
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getFreeTimeList(xh);
	}
	
	/**
	 * 根据主键获取岗位信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxxByPk(String pkValue){
		XsgwglDAO gwglDAO = new XsgwglDAO();
		return gwglDAO.getGwxxByPk(pkValue);
	}
	
	/**
	 * 查询学生成绩信息
	 * @param String xh
	 * @return List<String[]>
	 * */
	public List<String[]> queryXscj(String xh){
		XsgwglDAO dao = new XsgwglDAO();		
		return dao.selectXscj(xh);
	}
	
	/**
	 * 查询学生违纪信息
	 * @param String xh
	 * @return List<String[]>
	 * */
	public List<String[]> queryXswj(String xh){
		XsgwglDAO dao = new XsgwglDAO();		
		return dao.selectWjcf(xh);
	}
	
	/**
	 * 查询广州大学岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGzdxXsgwxxb(String pk, String pkValue){
		XsgwglDAO dao = new XsgwglDAO();
		return dao.selectGzdxXsgwxxb(pk,pkValue);
	}
	
	/**
	 * 查询中国地质大学岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryZgdzdxXsgwxxb(String pk, String pkValue){
		XsgwglDAO dao = new XsgwglDAO();
		return dao.selectZgdzdxXsgwxxb(pk,pkValue);
	}
	
	/**
	 * 获取学生岗位信息查询表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsgwxxbTopTr(){
		XsgwglDAO dao  = new XsgwglDAO();
		String[] colList = dao.getXsgwxxbColList();
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsgwxx");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * 查询学生岗位信息
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwxxb(CommanForm model){
		XsgwglDAO dao  = new XsgwglDAO();
		return dao.selectXsgwxxb(model);
	}
	
	/**
	 * 在岗学生岗位信息导出查询
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryZgxsxxForExport(CommanForm model,String[] colList){
		XsgwglDAO dao  = new XsgwglDAO();
		return dao.selectZgxsxxForExport(model,colList);
	}
	
	/**
	 * 获取学校的学生岗位审核类型
	 * @param xxdm
	 * @return String
	 * */
	public String getXsgwshShlx(String xxdm){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(Globals.XXDM_XNMZ, "yrdw-xy-xx");
		map.put(Globals.XXDM_XNMZ, "xy-yrdw-xx");
		map.put(Globals.XXDM_ZGMSXY, "xy-yrdw-xx");
		map.put(Globals.XXDM_NBTYZYJSXY, "xy-xx");
		map.put(Globals.XXDM_NBLGXY, "qtyh-xx");
		map.put(Globals.XXDM_HBJTZYJSXY, "fdy-yrdw-xx");
		map.put(Globals.XXDM_ZJJTZYJSXY, "fdy-yrdw-xx");
		map.put(Globals.XXDM_SZWBZYJSXY,"xy-xx");
		map.put(Globals.XXDM_WHSYFWXY, "fdy-yrdw-xx");
		map.put(Globals.XXDM_ZJJSZYJSXY, "qgzxgly"); //浙江建设勤工助学管理员审核（2011.12.5 qlj）
		String shlx = map.get(xxdm);
		shlx = StringUtils.isNull(shlx) ? "yrdw-xx" : shlx;
		return shlx;
	}
	
	/**
	 * 获取学生岗位信息审核查询表头
	 * @param shlx
	 * @return String[]
	 * */
	public String[] getXsgwshCol(String shlx,User user){
		QgzxDao dao = new QgzxDao();
		String[] colList  = null;
		if(StringUtils.isNotNull(shlx)){
			
			String[] jtlx = shlx.split("-");
			if(jtlx.length == 3){
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", 
						"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm", "fdyyj", "xyyj", "xxyj", "sqsj" };
			}else if(jtlx.length==2 && "fdy".equalsIgnoreCase(jtlx[0])){
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", 
						"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm","fdyyj", "xxyj", "sqsj" };
			}else{
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", 
						"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm","xyyj", "xxyj", "sqsj" };
			}
		}
		//浙江交通职业技术学院
		if(Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
			colList = new String[] { "bgcolor","标记", "主键", "行号", "xn", "nd", 
					"xqmc", "xh", "xm", "bjmc", "sfpks", "gwdm", "fdyyj", "xyyj", "xxyj", "sqsj" };
		}
		if(Globals.XXDM_YNYS.equalsIgnoreCase(Base.xxdm)){
			colList = StringUtils.joinStrArr(colList, new String[] { "bh","gh" });
		}
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			if(dao.isYrdwUser(user.getUserName())){
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "xyyj", "xxyj", "已录用岗位", "sqsj" };
			}
		}
		if(Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)){
			if(dao.isYrdwUser(user.getUserName())){
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj" };
			}else if("xx".equalsIgnoreCase(user.getUserType())
					|| "admin".equalsIgnoreCase(user.getUserType())){
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj",  "xxyj" };
			}
		}
		if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(Base.xxdm)){
			
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd","xqmc",
						 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj",  "xxyj" };
			
		}
		return colList;
	}
	
	/**
	 * 学生岗位信息审核查询
	 * @param model
	 * @param user
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwsh(CommanForm model,User user){
		QgzxService service = new QgzxService();
		XsgwglDAO dao = new XsgwglDAO();
		List<String[]> rs = new ArrayList<String[]>();
		//是否是用人单位
		boolean isYrdw = service.isYrdwUser(model.getUserName());
		//是否是辅导员
		boolean isFdy = "true".equalsIgnoreCase(user.getFdyQx()) ? true : false;
		//是否是班主任
		boolean isBzr = "true".equalsIgnoreCase(user.getBzrQx()) ? true : false;
		//审核类型
		String shlx = getXsgwshShlx(Base.xxdm);
		
		//获取表头
		String[] colList = getXsgwshCol(shlx,user);
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)){
			//中国地质大学
			rs = dao.queryXsgwshForZgdzdx(model, user, colList);			
		}else if(Globals.XXDM_HNGYDX.equalsIgnoreCase(Base.xxdm)){
			//湖南工业大学
			rs = dao.queryXsgwshForHngydx(model, user, colList);
		}else if(Globals.XXDM_YNYS.equalsIgnoreCase(Base.xxdm)){
			//云南艺术学院
			rs = dao.queryXsgwshForYnysxy(model, user, colList);
		}else if(Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(Base.xxdm)){
			//浙江机电职业计算学院
			rs = dao.queryXsgwshForZjjdzyjsxy(model, user, colList);
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
			//北京联合大学
			rs = dao.queryXsgwshForBjlhdx(model, user, colList);
		}else if(Globals.XXDM_HYGXY.equalsIgnoreCase(Base.xxdm)){
			//淮阴工学院
			rs = dao.queryXsgwshForHygxy(model, user, colList);
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//浙江科技
			rs = dao.queryXsgwshForZjkjxy(model, user, colList);
		}else if(Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)){
			//成都体育
			rs = dao.queryXsgwshForCdtyxy(model, user, colList);
		}else if(Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
			//浙江交通
			if("fdy-yrdw-xx".equalsIgnoreCase(shlx)){
				//辅导员-用人单位审核-学校
				if(isFdy){
					//辅导员
					rs = dao.queryXsgwshForFirstZjjtzyjsxy(model, user, colList);
				}else if(isYrdw || isBzr ){
					//用人单位
					rs = dao.queryXsgwshForSecondZjjtzyjsxy(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForXxThirdZjjtzyjsxy(model, user, colList);
				}
			}
		}else{
			if("yrdw-xx".equalsIgnoreCase(shlx)){
				//用人单位-学校
				if(isYrdw){
					//用人单位
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}
			}
			if("fdy-xx".equalsIgnoreCase(shlx)){
				//辅导员-学校
				if(isFdy){
					//辅导员
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}
			}
			if("xy-xx".equalsIgnoreCase(shlx)){
				//学院-学校
				if("xy".equalsIgnoreCase(user.getUserType())){
					//学院
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}
			}
			if("qtyh-xx".equalsIgnoreCase(shlx)){
				//其它用户-学校
				if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForTowStepXxSecond(model, user, colList);
				}else{
					//其它用户
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}
			}
			if("yrdw-xy-xx".equalsIgnoreCase(shlx)){
				//用人单位审核-学院-学校
				if(isYrdw){
					//用人单位
					rs = dao.queryXsgwshForFirst(model, user, colList);
				}else if("xy".equalsIgnoreCase(model.getUserType())){
					//学院
					rs = dao.queryXsgwshForSecond(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForXxThird(model, user, colList);
				}
			}
			if("xy-yrdw-xx".equalsIgnoreCase(shlx)){
				//学院-用人单位-学校
				if("xy".equalsIgnoreCase(model.getUserType())){
					//学院
					rs = dao.queryXsgwshForFirst(model, user, colList);
				}else if(isYrdw){
					//用人单位
					rs = dao.queryXsgwshForSecond(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForXxThird(model, user, colList);
				}
			}
			
			if("fdy-yrdw-xx".equalsIgnoreCase(shlx)){
				//辅导员-用人单位-学校
				if(isFdy){
					//学院
					rs = dao.queryXsgwshForTowStepFirst(model, user, colList);
				}else if(isYrdw){
					//用人单位
					rs = dao.queryXsgwshForSecond(model, user, colList);
				}else if("xx".equalsIgnoreCase(model.getUserType()) || "admin".equalsIgnoreCase(model.getUserType())){
					//学校
					rs = dao.queryXsgwshForXxThird(model, user, colList);
				}
			}
			if("qgzxgly".equalsIgnoreCase(shlx)){
				//勤工助学管理员审核，有访问权限者便可审核（2011.12.5 qlj）
				rs = dao.queryXsgwshForQgglyThird(model, user, colList);
				
			}
		}
		//是否困难生字段信息取资助
		rs = service.changeRsValue(rs);
		return rs;
	}
	
	/**
	 * 获取学生岗位审核查询表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsgwxxShTopTr(User user){
		XsgwglDAO dao  = new XsgwglDAO();
		//审核类型
		String shlx = getXsgwshShlx(Base.xxdm);
		
		//获取表头
		String[] colList = getXsgwshCol(shlx,user);
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsgwxx");
		return dao.arrayToList(colList, colListCN);
	}
}
