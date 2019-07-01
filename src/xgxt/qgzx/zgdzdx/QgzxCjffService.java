package xgxt.qgzx.zgdzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学勤工助学酬金发放Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-02-10</p>
 */
public class QgzxCjffService {
	QgzxCjffDAO  dao = new QgzxCjffDAO();
	
	/**
	 * 查询岗位下的勤工助学学生列表
	 * @param model
	 * @return List
	 * */
	public List<String[]> getQgzxStuList(QgzxCjffForm model){
		return dao.getGdgwStuList(model);
	}
	
	/**
	 * 保存月工资发放信息
	 * @param model
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveYgzffInfo(QgzxCjffForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String count = model.getCount();
		HashMap<String, String> gwMap = dao.getGwinfo(model.getPk(),model.getPkValue());
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		String nd = model.getNd();
		String yf = model.getYf();		
		String xn = model.getXn();
		String xq = model.getXq();
		String userName = model.getUserName();
		String ffsj = gwMap.get("nowtime");
		String[] columns = {"xh", "gwdm", "cjje", "bz", "sqsj", "gzsj", "xn", "nd", "xq", "yf"};
		
		for(int i=0; i<Integer.parseInt(count); i++){
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String yrdwdm = gwMap.get("yrdwdm");
			
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if((cjje!=null && !"".equalsIgnoreCase(cjje)) || (gzsj !=null && !"".equalsIgnoreCase(gzsj))){
				if(dao.checkExists("xscjffb", "gwdm||sqsj||xh||nd||yf||fflx", model.getPkValue()+xh+nd+yf+"")){//记录已经存在
					flag = StandardOperation.update("xscjffb", new String[]{"gzsj", "cjje", "bz", "ffsj"}, new String[]{gzsj,cjje,bz,ffsj},"gwdm||sqsj||xh||nd||yf||fflx", model.getPkValue()+xh+nd+yf+"", request);
				}else{//发放
					flag = StandardOperation.insert("xscjffb", columns, new String[]{xh,gwdm,cjje,bz,gwsbsj,gzsj,xn,nd,xq,yf}, request);
					if(flag){
						if(dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)){//保存用人单位发放酬金的时间
							StandardOperation.update("cjffsjb", new String[]{"ffsj", "ffr"}, new String[]{ffsj, userName}, "yrdwdm", yrdwdm, request);
						}else{
							StandardOperation.insert("cjffsjb", new String[]{"yrdwdm","ffsj", "ffr"}, new String[]{yrdwdm, ffsj, userName}, request);
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 根据页面主键查询岗位信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwInfo(String pk, String pkValue){
		return dao.getGwinfo(pk, pkValue);
	}
	
	/**
	 * 获取酬金总金额
	 * @param model
	 * @return String
	 * */
	public String getCjzje(QgzxCjffForm model){
		String zje = dao.getZcjje(model);
		zje = zje == null ? "0" : zje;
		return zje;
	}
	
	/**
	 * 获取限制的最高酬金金额
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxConfig(){
		return dao.getQgzxConfig();		
	}
	
	/**
	 * 工资补发信息保存
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveGzbfInfo(QgzxCjffForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String count = model.getCount();
		HashMap<String, String> gwMap = dao.getGwinfo(model.getPk(),model.getPkValue());
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		
		String tableName = "xscjffb";
		String fflx = "补发";
		String yrdwdm = gwMap.get("yrdwdm");
		String ffsj = gwMap.get("nowtime");
		String ffr = model.getUserName();
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String yf = model.getYf();
		
		yf = yf == null || "".equalsIgnoreCase(yf) ? "0" : yf;
		yf = String.valueOf(Integer.parseInt(yf));
		
		String[] columns = new String[]{"xh", "gwdm", "cjje", "bz", "sqsj", "gzsj", "xn","nd", "xq", "yf", "fflx"};
		String[] values = {};
		
		for(int i=0; i<Integer.parseInt(count); i++){
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if((cjje!=null && !"".equalsIgnoreCase(cjje)) || (gzsj !=null && !"".equalsIgnoreCase(gzsj))){
				values = new String[]{xh, gwdm, cjje, bz, gwsbsj,  gzsj, xn, nd, xq, yf, fflx};
				if(dao.checkExists("xscjffb", "xh||gwdm||sqsj||xn||nd||xq||yf||fflx", xh+gwdm+gwsbsj+xn+nd+xq+yf+fflx)){
					//本月工资已经补发
					String xxdm = StandardOperation.getXxdm();
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
						// ============begin 骆嘉伟 2009/4/20 =========
						columns = new String[] { "cjje", "bz", "gzsj", "ffsj"};
						values = new String[] { cjje, bz, gzsj, ffsj };
						flag = StandardOperation.update(tableName, columns,
								values, "xh||gwdm||sqsj||xn||nd||xq||yf||fflx",
								xh + gwdm + gwsbsj + xn + nd + xq + yf + fflx,
								request);
						request.setAttribute("msg", "已覆盖前次补发工资");
						// ============end 骆嘉伟 2009/4/20 =========
					} else {
						request.setAttribute("msg", "本月工资已经补发,不能再进行补发操作！");
					}
				}else{
					flag = StandardOperation.insert(tableName, columns, values, request);
					if(flag){
						if(flag){
							if(dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)){//保存用人单位发放酬金的时间
								StandardOperation.update("cjffsjb", new String[]{"ffsj", "ffr"}, new String[]{ffsj, ffr}, "yrdwdm", yrdwdm, request);
							}else{
								StandardOperation.insert("cjffsjb", new String[]{"yrdwdm","ffsj", "ffr"}, new String[]{yrdwdm, ffsj, ffr}, request);
							}
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 查询参数设置信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getConfigParamter(){
		return dao.selectGwsbsjb();
	}
	
	/**
	 * 查询发放酬金的学生列表
	 * @param pkValue
	 * @return List
	 * */
	public List<HashMap<String, String>> getCjffInfoOfStu(String pkValue){
		return dao.selectCjffInfoOfStu(pkValue);
	}
	
	/**
	 * 删除酬金发放信息
	 * @param pk
	 * @param pkValue
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean deleteCjffInfo(QgzxCjffForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String fflx = model.getFflx();
		fflx = fflx == null ? "" : fflx.trim();
		String pk = model.getPk();
		String[] pkV = model.getPkV();
		String pkValue = "";
		
		if(fflx != null && "临时".equalsIgnoreCase(fflx)){
			for(int i=0; i<pkV.length; i++){
				pkValue = pkV[i];
				flag = StandardOperation.delete("xslsgcjffb", "xh||gwdm||gwsbsj||nd||yf", pkValue, request);
			}
		}else{
			for(int i=0; i<pkV.length; i++){
				pkValue = pkV[i];
				flag = StandardOperation.delete("xscjffb", pk, pkValue, request);
			}
		}
		return flag;
	}

	/**
	 * 获取用人单位列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(){
		return dao.getYrdwList();
	}
		
	/**
	 * 获取月份列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getYfList(){
		return dao.getYfList();		
	}
	
	/**
	 * 获取月份列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwList(){
		return dao.getGwList();		
	}
	
	/**
	 * 临时岗酬金发放信息
	 * @param pk
	 * @param pkValue
	 * @return List
	 * */
	public List<String[]> getLsgcjffInfo(QgzxCjffForm model,String page){
		return dao.getLsgcjffInfo(model,page);
	}
	
	/**
	 * 获取临时岗酬金总额
	 * @param model
	 * @return String
	 * */
	public String getLsgcjze(QgzxCjffForm model){
		return dao.getLsgcjze(model);
	}
	
	/**
	 * 临时工资补发信息保存
	 * 
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * @author luo
	 */
	public boolean saveGzLsInfo(QgzxCjffForm model, String addRowNum,
			HttpServletRequest request) throws Exception {
		boolean flag = false;
		String count = model.getCount();
		HashMap<String, String> gwMap = dao.getGwinfo(model.getPk(), model
				.getPkValue());
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		String tableName = "xslsgcjffb";
//		String fflx = "临时";
		String yrdwdm = gwMap.get("yrdwdm");
		String ffsj = gwMap.get("nowtime");
		String ffr = model.getUserName();
		String nd = model.getNd();
		String yf = model.getYf();
		addRowNum = addRowNum == null || "".equalsIgnoreCase(addRowNum) ? "0" : addRowNum;

		yf = yf == null || "".equalsIgnoreCase(yf) ? "0" : yf;
		yf = String.valueOf(Integer.parseInt(yf));

		String[] columns = new String[] { "xh", "gwdm", "cjje", "bz", "gwsbsj",
				"gzsj", "nd", "yf" , "ffsj"};
		String[] values = {};

		for (int i = 0; i < Integer.parseInt(count); i++) {
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if ((cjje != null && !"".equalsIgnoreCase(cjje))
					|| (gzsj != null && !"".equalsIgnoreCase(gzsj))) {
				values = new String[] { xh, gwdm, cjje, bz, gwsbsj, gzsj, nd,
						yf, ffsj };
				flag = StandardOperation.delete(tableName, "xh||gwdm", xh
						+ gwdm, request);
				if (flag) {
					flag = StandardOperation.insert(tableName, columns, values,
							request);
				}
				if (flag) {
					if (flag) {
						if (dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)) {// 保存用人单位发放酬金的时间
							StandardOperation.update("cjffsjb", new String[] {
									"ffsj", "ffr" },
									new String[] { ffsj, ffr }, "yrdwdm",
									yrdwdm, request);
						} else {
							StandardOperation.insert("cjffsjb", new String[] {
									"yrdwdm", "ffsj", "ffr" }, new String[] {
									yrdwdm, ffsj, ffr }, request);
						}
					}
				}
			}
		}

		for (int i = Integer.parseInt(count); i <= Integer.parseInt(count)
				+ Integer.parseInt(addRowNum); i++) {
			String xh = request.getParameter("xh" + i);
			String gzsj = DealString.toGBK(request.getParameter("gzsj" + i));
			String cjje = DealString.toGBK(request.getParameter("cjje" + i));
			String gwdm = gwMap.get("gwdm");
			String gwsbsj = gwMap.get("gwsbsj");
			String bz = DealString.toGBK(request.getParameter("bz" + i));
			if ((cjje != null && !"".equalsIgnoreCase(cjje))
					|| (gzsj != null && !"".equalsIgnoreCase(gzsj))) {
				values = new String[] { xh, gwdm, cjje, bz, gwsbsj, gzsj, nd,
						yf,ffsj };
				flag = StandardOperation.insert(tableName, columns, values,
						request);
				if (flag) {
					if (flag) {						
						if (dao.checkExists("cjffsjb", "yrdwdm", yrdwdm)) {// 保存用人单位发放酬金的时间
							StandardOperation.update("cjffsjb", new String[] {
									"ffsj", "ffr"},
									new String[] { ffsj, ffr }, "yrdwdm",
									yrdwdm, request);
						} else {
							StandardOperation.insert("cjffsjb", new String[] {
									"yrdwdm", "ffsj", "ffr" }, new String[] {
									yrdwdm, ffsj, ffr }, request);
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 获取学生酬金发放查询表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXscjffTorTr(CommanForm model){
		String tabName = "view_xscjff";
		String[] colList = dao.getXscjffbCol(model);
		String[] colListCN  = dao.getColumnNameCN(colList, tabName);
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * 学生酬金发放信息查询
	 * @param CommanForm model
	 * @return List<String>
	 * */
	public List<String[]> queryXscjffb(CommanForm model){
		String xxdm = StandardOperation.getXxdm();
		List<String[]> rs = new ArrayList<String[]>();
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//中国地质大学
			rs = dao.selectXscjffbByZgdzdx(model);
		}else if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){
			//浙江传媒学院
			rs = dao.selectXscjffbByZjcmxy(model);
		}else{
			rs = dao.selectXscjffb(model);
		}
		return rs;
	}
	
	/**
	 * 临时岗位信息
	 * @param pk
	 * @param pkValue
	 * @return List
	 * @author luo
	 */
	public HashMap<String, String> getGwinfoOne(QgzxCjffForm model) {
		return dao.getGwinfoOne(model);
	}
	
	/**
	 * 获取当前时间,格式:****年**月**日
	 * @return String 
	 * */
	public String getNowTime() {
		return dao.getNowTime();
	}
}
