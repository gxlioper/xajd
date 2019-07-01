package xgxt.gygl.zsjg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class GyglZsjgInit {

	/**
	 * 公寓住宿信息_初始化数据
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getLsxxRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_lsxx.do";
		// 表
		String tableName = "xszslsxxb";
		// 输出字段
		String[] colList = { "guid", "xh", "xm", "nj", "bjmc", "zymc", "xymc",
				"xb", "ldmc", "qsh", "cwh" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "lsxx");
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// 分页
		// Pages pages = model.getPages();

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//显示列数
		String showNum = "10";
		commSetting.setShowNum(showNum);
		
		// ===============通用设置 end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	
	/**
	 * 公寓住宿信息_初始化数据
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getZsxxRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_zsxx.do";
		// 表
		String tableName = "xszsxxb";
		// 输出字段
		String[] colList = { "pkValue", "xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "ssbh", "ldmc", "cs","qsh","cwh","rzrq" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "zsxx");
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// 分页
		// Pages pages = model.getPages();

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//显示列数
		int showNum =colList.length-1;
		commSetting.setShowNum(String.valueOf(showNum));
		
		// ===============通用设置 end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 公寓住宿信息统计_初始化数据
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getZsxxTjRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 查询类别
		String searchType=model.getFpdx();
		// 访问路径
		String path = "gygl_zsjg_zsxxtj.do&searchType="+searchType;
		
		// 表
		String tableName = "xszsxxb";
		// 输出字段
		String[] colList = { "pkValue", "xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "ssbh", "ldmc", "cs","qsh","cwh","rzrq" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "zsxx");
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// 分页
		// Pages pages = model.getPages();

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//显示列数
		int showNum =colList.length-1;
		commSetting.setShowNum(String.valueOf(showNum));
		
		// ===============通用设置 end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 公寓住宿信息_初始化数据
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getQsydRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "ydgl";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_ydgl.do";
		// 表
		String tableName = "xszsxxb";
		// 输出字段
		String[] colList = { "pkValue", "xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "ssbh", "ldmc", "cs","qsh","cwh","rzrq" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "ydxx");
		// 是否查询操作
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// 分页
		// Pages pages = model.getPages();

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//显示列数
		int showNum =colList.length-1;
		commSetting.setShowNum(String.valueOf(showNum));
		
		// ===============通用设置 end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		if ("lsxx".equalsIgnoreCase(fpdx)) {// 历史信息

			colListCN = new String[] { "主键", "学号", "姓名", "年级", "班级", "专业",
					Base.YXPZXY_KEY, "性别", "楼栋", "寝室号", "床位号" };
		} else if ("zsxx".equalsIgnoreCase(fpdx)) {// 住宿信息

			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY,
					"专业", "班级", "宿舍编号", "楼栋", "层数", "寝室号", "床位号", "入住日期" };

		} else if ("ydxx".equalsIgnoreCase(fpdx)) {// 异动信息

			colListCN = new String[] { "主键", "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY,
					"专业", "班级", "宿舍编号", "楼栋", "层数", "寝室号", "床位号", "入住日期" };
		} else if ("qsrzqk".equalsIgnoreCase(fpdx)) {// 寝室入住情况

			colListCN = new String[] { "楼栋", "寝室号", "性别限制", "姓名及班级" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 按学生分配
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniZstjRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "cwfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_zstj.do";
		// 查询类型
		String searchType = model.getFpdx();
		// 输出字段
		
		String[] colList =null;
		if("xy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","xymc","xyrs","rzbss","rzqtss"};
		}else if("njxy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","xyrs","rzbss","rzqtss"};
		}else if("njzy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","zymc","xyrs","rzbss","rzqtss"};
		}else if("bj".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","zymc","bjmc","xyrs","rzbss","rzqtss"};
		}
		
		// 表头
		List<HashMap<String, String>> topTr = getZstjTopTr(colList, searchType);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(colList.length);
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 按学生分配
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniSsydTjRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "cwfp";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_ydtj.do";
		// 查询类型
		String searchType = model.getFpdx();
		// 输出字段
		
		String[] colList =null;
		if("xy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","xymc","xyrs","rzbss","rzqtss"};
		}else if("njxy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","xyrs","rzbss","rzqtss"};
		}else if("njzy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","zymc","xyrs","rzbss","rzqtss"};
		}else if("bj".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","zymc","bjmc","xyrs","rzbss","rzqtss"};
		}
		
		// 表头
		List<HashMap<String, String>> topTr = getSsydtjTopTr(model, searchType);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(topTr.size()-1);
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 按学生分配
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniSsydCxRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsjg";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_ydcx.do";
		// 输出字段
		
		String[] colList =null;
		colList = new String[] {"xh","xm","xb","nj","xymc","zymc","bjmc","ydqldmc","ydqcs","ydqqsh","ydhldmc","ydhcs","ydhqsh"};
	
		// 表头
		List<HashMap<String, String>> topTr = getSsydCxTopTr(colList);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getZstjTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键",Base.YXPZXY_KEY,Base.YXPZXY_KEY+"人数","入住本部门人数","入住他部门人数"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键","年级",Base.YXPZXY_KEY,Base.YXPZXY_KEY+"人数","入住本部门人数","入住他部门人数"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键","年级","专业",Base.YXPZXY_KEY+"人数","入住本部门人数","入住他部门人数"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"主键","年级",Base.YXPZXY_KEY,"专业","班级",Base.YXPZXY_KEY+"人数","入住本部门人数","入住他部门人数"};
		}
		
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getSsydtjTopTr(GyglZsjgForm myForm, String fpdx) {

		String[] colListCN = null;
		GyglZsjgService service =new GyglZsjgService();
		List<String>ydxnList=service.getYdxnList(myForm);
		int len=ydxnList.size();
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[2+2*len]; 
			colListCN[0]="主键";
			colListCN[1]=Base.YXPZXY_KEY;
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)]=ydxnList.get(i)+"(人数)";
				colListCN[2*(i+1)+1]=ydxnList.get(i)+"(人次)";
			}
			
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[3+2*len]; 
			colListCN[0]="主键";
			colListCN[1]="年级";
			colListCN[2]=Base.YXPZXY_KEY;
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)+1]=ydxnList.get(i)+"(人数)";
				colListCN[2*(i+1)+2]=ydxnList.get(i)+"(人次)";
			}
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[4+2*len]; 
			colListCN[0]="主键";
			colListCN[1]="年级";
			colListCN[2]=Base.YXPZXY_KEY;
			colListCN[3]="专业";
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)+2]=ydxnList.get(i)+"(人数)";
				colListCN[2*(i+1)+3]=ydxnList.get(i)+"(人次)";
			}
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[5+2*len]; 
			colListCN[0]="主键";
			colListCN[1]="年级";
			colListCN[2]=Base.YXPZXY_KEY;
			colListCN[3]="专业";
			colListCN[4]="班级";
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)+3]=ydxnList.get(i)+"(人数)";
				colListCN[2*(i+1)+4]=ydxnList.get(i)+"(人次)";
			}
		}
		
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colListCN,
				colListCN);

		return topTr;
	}
	
	/**
	 * 获得不同分配对象的表头
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getSsydCxTopTr(String[] colList) {

		String[] colListCN = null;
				
		colListCN = new String[] {"学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级",
				"异动前楼栋","异动前层数","异动前寝室","异动后楼栋","异动后层数","异动后寝室"};

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 空闲宿舍
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniKxssTjForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request,MessageResources message) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsjg";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_kxss.do";
		//存在校区
		String czxq=model.getCzxq();
		//存在园区
		String czyq=model.getCzyq();
		
		// 输出字段
		
		String[] colList =null;
		
		
		colList = new String[] { "lddm", "xqmc", "yqmc","ldmc", "xbxd", "xqs", "kqs" };
		
	
		//表头
		List<HashMap<String, String>> topTr = getKxssTjTopTr(model,colList, message);
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		int n=topTr.size()-1;
		if(!"是".equalsIgnoreCase(model.getCzxq())){
			n--;
		}
		if(!"是".equalsIgnoreCase(model.getCzyq())){
			n--;
		}
		String showNum =String.valueOf(n);
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 获得空闲宿舍统计表头
	 * @author qlj
	 */
	private List<HashMap<String, String>> getKxssTjTopTr(GyglZsjgForm model,
			String[] colList, MessageResources message) {

		String xqmc = message.getMessage("lable.xiaoqu") + "名称";
		String yqmc = message.getMessage("lable.yuanqu") + "名称";
 		String lddm = message.getMessage("lable.ld") + "代码";
		String ldmc = message.getMessage("lable.ld") + "名称";
		String[] colListCN = null;

		colListCN = new String[] { lddm,xqmc, yqmc, ldmc, "性别限定", "闲寝室数", "空寝室数" };
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 空闲宿舍初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniKqsxxForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request,MessageResources message) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsjg";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_kxss.do";
		// 输出字段
		
		String[] colList ={"cs","qsh","cws","xbxd","bmmc"};
	
		//表头
		List<HashMap<String, String>> topTr = getKxqsTopTr(model,colList, message,"kqs");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 获得空闲宿舍统计表头
	 * @author qlj
	 */
	private List<HashMap<String, String>> getKxqsTopTr(GyglZsjgForm model,
			String[] colList, MessageResources message,String mklx) {
		
		String cs = message.getMessage("lable.cs");
		String qs = message.getMessage("lable.qs") + "号";

		String[] colListCN = null;

		if ("kqs".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { cs, qs, "床位数", "寝室性别", "分配部门" };
		} else if ("xqs".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { cs, qs, "床位号", "寝室性别", "收费标准", "分配部门" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * 空闲宿舍初始化
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniXqsxxForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request,MessageResources message) {

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsjg";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_kxss.do";
		//存在校区
		String czxq=model.getCzxq();
		//存在园区
		String czyq=model.getCzyq();
		
		// 输出字段
		
		String[] colList ={"cs","qsh","cwh","xb","sfbz","bmmc"};
	
		//表头
		List<HashMap<String, String>> topTr = getKxqsTopTr(model,colList, message,"xqs");
		// 是否查询操作
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	
	/**
	 * 寝室入住情况_初始化数据
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getQsrzqkRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		GyglZsjgService service = new GyglZsjgService();

		// 功能模块
		String gnmk = "gygl";
		// 系统字段设置
		String menu = "zsxx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "gygl_zsjg_qsrzqk.do";
		// 表
		String tableName = "xszslsxxb";
		// 输出字段
		String[] colList = { "ldmc", "qsh", "xb", "xh" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "qsrzqk");

		// 是否查询操作
		String search = !"go".equalsIgnoreCase((request.getParameter("go"))) ? "false"
				: "true";

		String[] qtzd = new String[] { "maxCws" };
		String[] qtzdz = new String[] { service.getMaxCws() };
		
		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		//显示列数
		String showNum = "3";
		commSetting.setShowNum(showNum);
		
		// ===============通用设置 end ============

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
}
