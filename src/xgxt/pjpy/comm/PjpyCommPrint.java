package xgxt.pjpy.comm;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.dtjs.ntzy.pypx.NtzyPypxService;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.pjlc.jgcx.PjpyJgcxForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;

import common.Globals;
import common.PjpyXmdm;
import common.XszzXmdm;

public class PjpyCommPrint extends DispatchAction {

	//斜杠
	public static String slashes;
	
	static {
		if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
			slashes = "/";
		} else {
			slashes = "\\";
		}
	}
	
	/**
	 * 学生评奖_打印
	 * 
	 * @author lyl
	 * @return ActionForward
	 */
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		PjpyCommService service = new PjpyCommService();
		PjpyJgcxForm myForm = (PjpyJgcxForm) form;
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		
		// ================= 赋初值 ==================
		// 学校名称
		String xxmc = Base.xxmc;
		// 学校代码
		String xxdm = Base.xxdm;

		// 项目名称
		String xmmc = request.getParameter("jxjName");
		myForm.setXmmc(xmmc);
		request.setAttribute("xmmc", xmmc);

		// 学号
		String xh = request.getParameter("xh");
		if (!Base.isNull(xh)) {
			myForm.setXh(xh);
		}
		// 学年
		String xn=request.getParameter("xn");
		
		if(!Base.isNull(xn)){
			xn = request.getParameter("xn").split(" ")[0];
		}else{
			xn = jbszModel.getPjxn();
		}
		
		// 跳转
		String forward = "";
		// 打印报表信息
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		// =================end==================

		// ================= 学生基本信息 ==================
		getStuInfo(myForm, map);
		// =================end==================

		// ================= 项目基本信息 ==================
		getXmjbxx(myForm, map);
		// =================end==================
		
		//----------学生职务、班级人数、是否违纪、综测信息、补考科目数 2012.3.6 qph-------------------------
		NtzyPypxService ntzyService = new NtzyPypxService();
		map.put("xszw", ntzyService.getZw(xh));
		map.put("bjrs", service.getBjrs(myForm, map));
		map.put("sfwj", service.getSfwj(myForm, map));
		map.putAll(service.getZczfBjpm(myForm, map));
		map.put("bkkms", service.getBkks(myForm, map));
		map.put("wdxfkms", service.getWdxfkms(xh));
		//---------------2012.3.6 qph end-------------------------------
		
		// ================= 项目基本信息 ==================
		List<HashMap<String, String>> qtxxList = service.getOtherInfo(xmmc, xh);
		for (int i = 0; i < qtxxList.size(); i++) {
			HashMap<String, String> qtxx = qtxxList.get(i);
			map.put(qtxx.get("zdmc"), qtxx.get("zdz"));
		}
		// =================end==================
		
		// =================项目基本信息 ==================
		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjxqmc = jbszModel.getPjxqmc();
		String pjnd = jbszModel.getPjnd();
		
		//map.put("pjxn", pjxn);
		map.put("pjxn", xn);
		map.put("pjnd", pjnd);
		map.put("pjxqmc", pjxqmc);
		// =================end==================
		
		// ================= 获取综测及其他信息 begin==================
		getZhxxInfo(myForm, map);
		
		// ================= 获取申请理由 begin==================
		if(Globals.XXDM_HZSFXY.equals(xxdm) || Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			xmmc = myForm.getXmmc();
			map.put("sqly", service.getHzsfSqly(jbszModel,xmmc,xh));
		}
		//================= 获取综测及其他信息 end==================
		

		// ================= 判断打印何种报表 ==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();
		rForm.setRs(map);
		request.setAttribute("xscj", service.getXscjList(myForm, map));
		request.setAttribute("pjinfo", service.getPjInfo(myForm, map));
		service.setRequestValue(rForm, request);
		// =================初始化request的值 end====================
		
		//天津工业excel输出
		if (Globals.XXDM_TJGYDX.equals(xxdm)){
			return printExcel(myForm, map, response);
		}
	
		// =================获得打印报表路径 ====================
		forward = getPrintJspForward(myForm);
		// ===================end ====================
		
		// =================特殊报表路径处理 ====================
		forward = getSpecialForward(myForm, map, forward);
		// ===================end ====================

		if (Base.isNull(forward)) {
			forward = "/pjpy/comm/print/comm/jxjsqb.jsp";
		} 
		
		return new ActionForward(forward, false);
	}

	/**
	 * 获得学生信息
	 * 
	 * @param myForm
	 * @param xmb
	 * @param xh
	 * @param map
	 */
	private void getStuInfo(PjpyJgcxForm myForm, HashMap<String, String> map) {

		XsxxglService stuService = new XsxxglService();

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 学号
		String xh = myForm.getXh();

		// 学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);

		// 闽江学院
		if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			// 国家助学贷款
			if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {
				String rxrq = stuInfo.get("rxrq");
				if (!Base.isNull(rxrq) && rxrq.length() > 4) {
					rxrq = rxrq.substring(0, 4);
					stuInfo.put("rxrq", rxrq);
				}
			}
		}

		map.putAll(stuInfo);
	}

	/**
	 * 获得项目基本信息
	 * 
	 * @param myForm
	 * @param xmb
	 * @param xh
	 * @param map
	 */
	private void getXmjbxx(PjpyJgcxForm model, HashMap<String, String> map) {

		PjpyCommService service = new PjpyCommService();
	
		// 项目基本信息
		HashMap<String, String> xmjbInfo = service.getXmjbInfo(model);
		String xmje = xmjbInfo.get("xmje");//项目金额
		xmje = Base.isNull(xmje) ? "" : xmje;
		xmjbInfo.put("xmje", xmje);
		// 项目审核信息
		HashMap<String, String> xmshInfo = service.getXmshInfo(model);

		map.putAll(xmjbInfo);
		map.putAll(xmshInfo);

	}
	
	/**
	 * 获取综测及其它信息
	 * @param model
	 * @param map
	 */
	public void getZhxxInfo(PjpyJgcxForm model, HashMap<String, String> map) {
		PjpyCommService service = new PjpyCommService();
		HashMap<String, String> pjfMap = service.getPjfInfo(model, map);
		//补考科目数
		pjfMap.put("bkks", service.getBkks(model, map));
		//是否违纪信息
		//pjfMap.put("sfwj", service.getSfwj(model, map));
		//评奖班级人数
		pjfMap.put("pjbjrs", service.getBjrsByPjry(model, map));
		//总测分信息
		pjfMap.putAll(service.getZczfBjpm(model, map));
		//班级干部信息
		pjfMap.put("bjgb",service.getBjgbInfo(model, map));
		//获得申请理由
		pjfMap.put("sqly", service.getSqly(model,map));
		//评奖评优审核信息
		pjfMap.putAll(service.getPjpyShyj(model, map));

		if(Globals.XXDM_GUIZHDX.equalsIgnoreCase(Base.xxdm)){
			//pjfMap.putAll(service.getShxx(model, map));
			//加权平均分/加权平均成绩
			pjfMap.putAll(service.getPjcj(model, map));
		}
		
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
			pjfMap.put("tjdj", model.getXmmc().substring(0,2));
		}
		
		map.putAll(pjfMap);
	}
	
	/**
	 * 获得打印报表的路径
	 * 
	 * @param myForm
	 * @param map
	 * @param forward
	 * @throws Exception
	 */
	private String getPrintJspForward(PjpyJgcxForm myForm) throws Exception {
		
		String forward = "";
		
		// 学校代码
		String xxdm = Base.xxdm;
		// 项目名称
		String xmmc = myForm.getXmmc();
		
		if(Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)){//贵州大学
			
			if ("省优秀毕业生".equals(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/syxbys.jsp";
			} else if ("校优秀毕业生".equals(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/xyxbys.jsp";
			} else {
				forward = "/pjpy/comm/print/" + xxdm + "/pjpySqb.jsp";
			}
			
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			
			System.out.println("项目名称------" + xmmc + "------------------");
			
			String tjdj=xmmc.length()>2?  xmmc.substring(0,2):xmmc;
			if ("三好学生".equalsIgnoreCase(xmmc) 
					|| "优秀学生干部".equalsIgnoreCase(xmmc)
					|| "优良学风标兵".equalsIgnoreCase(xmmc)) {// 项目名称
				
				forward = "/pjpy/comm/print/" + xxdm + "/shxs.jsp";
			} else if ("一等".equalsIgnoreCase(tjdj) || "二等".equalsIgnoreCase(tjdj) || "三等".equalsIgnoreCase(tjdj)
					|| "特等".equalsIgnoreCase(tjdj)){
				
				forward = "/pjpy/comm/print/" + xxdm + "/yxxsjxj.jsp";
			} else if ("优秀毕业生".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/yxbysb.jsp";
			} 
			
		}else if(Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)){
		
			if ("三好学生".equalsIgnoreCase(xmmc)) {// 项目名称
				forward = "/pjpy/comm/print/" + xxdm + "/shxs.jsp";
			} else if ("校内奖学金".equalsIgnoreCase(xmmc) || "学生奖学金".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/xnjxj.jsp";
			} else if ("优秀学生干部".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/yxxsgb.jsp";
			} 
			
		}else if(Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)){
		
			if ("优秀学生干部".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/yxxsgb.jsp";
			} 
			
		}
		
		return forward;
	}
	
	/**
	 * 获得特殊资助项目的路径
	 * 
	 * @param myForm
	 * @param map
	 * @param forward
	 * @throws Exception
	 */
	private String getSpecialForward(PjpyJgcxForm myForm,
			HashMap<String, String> map, String forward) throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmmc = myForm.getXmmc();

		// 天津工业大学社会奖学金
		if (Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {
			if (PjpyXmdm.PJPY_ALJXJSQB.equalsIgnoreCase(xmmc)) {// 傲龙奖学金
				forward = "/pjpy/comm/print/tjgy/aljxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_DMZZJXJSQB.equalsIgnoreCase(xmmc)) { // 帝棉针织奖学金
				forward = "/pjpy/comm/print/tjgy/dmzzjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_FZZGJXJSQB.equalsIgnoreCase(xmmc)) { // 纺织之光奖学金
				forward = "/pjpy/comm/print/tjgy/fzzgjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_FTSYJXJSQB.equalsIgnoreCase(xmmc)) { // 福田实业奖学金
				forward = "/pjpy/comm/print/tjgy/ftsyjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_JBHJXJSQB.equalsIgnoreCase(xmmc)) { // 金百合奖学金
				forward = "/pjpy/comm/print/tjgy/jbhjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_SMJXJSQB.equalsIgnoreCase(xmmc)) { // 桑麻奖学金
				forward = "/pjpy/comm/print/tjgy/smjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_SWJXJSQB.equalsIgnoreCase(xmmc)) { // 上纬奖学金
				forward = "/pjpy/comm/print/tjgy/swjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_TRJXJSQB.equalsIgnoreCase(xmmc)) { // 天然奖学金
				forward = "/pjpy/comm/print/tjgy/trjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_WKCJXJSQB.equalsIgnoreCase(xmmc)) { // 王克昌奖学金
				forward = "/pjpy/comm/print/tjgy/wkcjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_WSTJXJSQB.equalsIgnoreCase(xmmc)) { // 乌斯特奖学金
				forward = "/pjpy/comm/print/tjgy/wstjxjsqb.jsp";
			}
		}else if(Globals.XXDM_HZSFXY.equals(xxdm)) {
			//湖州师范学院打印登记表
			if("优秀学生干部".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/yxxsgb.jsp";
			}else if("优秀学生".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/yxxs.jsp";
			}else if("求真优秀学生干部".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/qzyxxsgb.jsp";
			}else if("求真优秀学生".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/qzyxxs.jsp";
			}else if("学习特别奖".equals(xmmc) || "技能奖".equals(xmmc) || "科技奖".equals(xmmc) || "创新奖".equals(xmmc) || "文体活动奖".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/dxjxj.jsp";
			}else if("求真学习特别奖".equals(xmmc) || "求真技能奖".equals(xmmc) || "求真科技奖".equals(xmmc) || "求真创新奖".equals(xmmc) || "求真文体活动奖".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/qzdxjxj.jsp";
			}else {
				if("求真".equals(xmmc.substring(0, 2))){
					forward = "/pjpy/comm/print/10347/qzzyjxj.jsp";
				}else{
					forward = "/pjpy/comm/print/10347/zyjxj.jsp";
				}
			}
		}

		return forward;
	}

	
	
	/**
	 * 天津工业报表excel输出
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printExcel(PjpyJgcxForm myForm, Map<String,String> map, HttpServletResponse response)
			throws Exception {
		String xmmc = myForm.getXmmc();
		String nd = Base.currNd;
		map.put("nd",nd);
		
		PjpyPrintService service = new PjpyPrintService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		if ("校优秀毕业生".equals(xmmc)){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/xyxbys.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printYxbys(wwb, map);
		} else if ("傲龙奖学金".equals(xmmc)){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/aljxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printAljxj(wwb, map);
		} else if ("桑麻奖学金".equals(xmmc)){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/smjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printSmjxj(wwb, map);
		} else if ("王克昌奖学金".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/wkcjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printWkqjxj(wwb, map);
		} else if ("纺织之光奖学金".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/fzzgjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printFzzgjxj(wwb, map);
		}else if ("帝棉针织奖学金".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/dmzzjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printDmzzjxj(wwb, map);
		} else if ("金百合奖学金".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/jbhjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printJbhjxj(wwb, map);
		} else if ("上纬奖学金".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/swjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printSwjxj(wwb, map);
		} else if ("天然奖学金".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/trjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printTrjxj(wwb, map);
		} else if ("福田实业奖学金".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/ftsyjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printFtsyjxj(wwb, map);
		} else if (myForm.getXmmc().indexOf("汇川技术奖学金")!=-1){
			//获得 “汇川技术奖学金(一级)”中的“一”
			map.put("sbdj", myForm.getXmmc().substring("汇川技术奖学金".length()+1, myForm.getXmmc().length()-2));
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/hcjsjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printHcjsjxj(wwb, map);
		} else if (myForm.getXmmc().indexOf("乌斯特奖学金")!=-1){
			//获得 “乌斯特奖学金(一级)”中的“一级”
			map.put("sbdj", myForm.getXmmc().substring("乌斯特奖学金".length()+1, myForm.getXmmc().length()-1));
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/wstjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printWstjxj(wwb, map);
		} else if ("校级优秀学生".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/xjyxxs.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printXjyxxs(wwb, map);
		} else if ("优秀学生".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/yxxs.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printYxxs(wwb, map);
		} 
		
		return null;
	}

	
	
	
	
}
