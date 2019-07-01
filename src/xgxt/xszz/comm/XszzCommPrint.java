package xgxt.xszz.comm;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.comm.CommService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.xszz.XszzTyForm;

import common.Globals;
import common.XszzXmdm;

public class XszzCommPrint extends DispatchAction {

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
	 * 学生资助_打印
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		// 学校代码
		String xxdm = StandardOperation.getXxdm();
		// 项目代码
		String xmdm = myForm.getSave_xmdm();
		myForm.setXmdm(xmdm);
		// 项目名称
		String xmmc = service.getOneValue("xszz_zzxmb", "xmmc", "xmdm", xmdm);
		// 申请时间
		String sqsj = myForm.getSave_sqsj();
		myForm.setSqsj(sqsj);
		// 项目表
		String xmb = myForm.getXmb();
		// 学号
		String xh = myForm.getXh();
		if (Base.isNull(xh)) {
			xh = request.getParameter("save_xh");
			myForm.setXh(xh);
		}
		// 学校名称
		String xxmc = Base.xxmc;
		// 项目类别
		String xmlb = service.getOneValue("xszz_zzxmb", "xmlb", "xmdm", xmdm);
		// 跳转
		String forward = "/xszz/comm/print/";
		// 打印报表信息
		HashMap<String, String> map = new HashMap<String, String>();
		// =================end==================

		// ================= 项目基本信息 ==================
		String[] colList = service.getTableZd(xmb);
		HashMap<String, String> xmInfo = service.getXszzInfo(xmb,
				"xh||sqsj||xmdm", xh + sqsj + xmdm, colList);

		// 学年
		String xn = xmInfo.get("xn");
		myForm.setXn(xn);
		// 学期
		String xq = xmInfo.get("xq");
		myForm.setXq(xq);
		// 年度
		String nd = xmInfo.get("nd");
		myForm.setNd(nd);

		map.put("xh", xh);
		map.put("xmlb", xmlb);
		map.put("xxmc", xxmc);
		map.put("xmmc", xmmc);

		// =================end==================

		map.putAll(xmInfo);

		// ================= 学生基本信息 ==================
		getStuInfo(myForm, map);
		// =================end==================

		// ================= 家庭基本情况 ==================
		getJtqkInfo(myForm, map, request);
		// =================end==================

		// ================= 项目相关情况 ==================
		getXmxgInfo(myForm, map);
		// =================end==================

		// ================= 项目级别列表==================
		getXmfjqk(myForm, map, request);
		// =================end==================

		// ================= 其他信息==================
		getQtInfo(myForm, map);
		// =================end==================

		// ================= 综合素质测评 ==================
		getStuZcInfo(myForm, map);
		// =================end==================

		// ================= 获取该生的困难生等级 ==================
		getKnsInfo(myForm, map);
		// =================end==================

		// ================= 获取学生成绩相关 ==================
		getCjInfo(myForm, map, request);
		// =================end==================
		
		// ================= 学生受资助项目信息==================
		getXszzxmInfo(myForm, map, request);
		// =================end==================
		
		// ================= 判断打印何种报表 ==================
		map.put("xh", xh);
		map.put("xn", Base.currXn);
		map.put("xq", xq);
		map.put("nd", Base.currNd);
		

		
		// 获取对应学校jsp的文件夹
		HashMap<String, String> folderMap = Globals.getXszzFolderMap();
		String folder = folderMap.get(xxdm);
		folder = StringUtils.isNull(folder) ? "comm" : folder;
		forward += folder;

		// 非外设
		forward += "/" + xmb + ".jsp";

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();
		// ================= 湖州师范学院个性化 ==================
		if(Globals.XXDM_HZSFXY.equals(xxdm)){
			//将出生日期yyyy-MM-dd更改为yyyy年MM月dd日格式
			//“陆侯燕英”帮困奖学金和“迎南树人”奖学金
			if(XszzXmdm.XSZZ_HZSF_LHYY.equals(xmdm) || XszzXmdm.XSZZ_HZSF_YNSR.equals(xmdm)){
				//SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdm = new SimpleDateFormat("yyyyMMdd");
				String xscsrq = map.get("csrq");
				if(xscsrq!=null && !"".equals(xscsrq)){
					Date date=sdm.parse(xscsrq);
					SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
					String newcsrq = sd.format(date);
					map.put("csrq", newcsrq);
				}else{
					map.put("csrq", "    年    月    日");
				}
			}
			//将身份证号分解
			if(XszzXmdm.XSZZ_HZSF_GJZXJ.equals(xmdm) || XszzXmdm.XSZZ_HZSF_GJLZJXJ.equals(xmdm) || XszzXmdm.XSZZ_HZSF_GJJXJ.equals(xmdm)){
				String xssfzh = map.get("sfzh")==null || "".equals("sfzh")?"":map.get("sfzh");
				int sylen= 18-xssfzh.length();
				for (int i=0;i<xssfzh.length();i++){
					map.put("sfzh"+i, xssfzh.charAt(i)+"");
				}
				for (int i=0;i<sylen;i++){
					map.put("sfzh"+(xssfzh.length()+i), "");
				}
			}
			//计算及格科目数
			int jgkms = Integer.parseInt(map.get("yxkms")!=null?map.get("yxkms"):"0")+Integer.parseInt(map.get("lhkms")!=null?map.get("lhkms"):"0");
			map.put("jgkms", jgkms==0?null:jgkms+"");
			//永兴特钢助学金和社会爱心助学，判断是初次申请还是再次申请
			if(XszzXmdm.XSZZ_HZSF_YXTGZXJ.equals(xmdm) || XszzXmdm.XSZZ_HZSF_SHAXZX.equals(xmdm)){
				List<HashMap<String,String>> sqList =service.getSfccsq(xh,xmdm);
				String ysq = request.getParameter("ysq");//是否修改
				if("yes".equals(ysq)){//如果是修改，判断不包含本次申请
					if(null!=sqList && sqList.size()>1){
						map.put("sfzcsq", "再次申请");
					}else{
						map.put("sfzcsq", "初次申请");
					}
				}else{//如果不是修改，判断包含本次申请
					if(null!=sqList && sqList.size()>0){
						map.put("sfzcsq", "再次申请");
					}else{
						map.put("sfzcsq", "初次申请");
					}
				}
			}
		}
		// =================湖州师范学院个性化end==================
		rForm.setRs(map);

		service.setRequestValue(rForm, request);

		// 获取特殊值
		setSpecialValue(myForm, request);
		// ===================end ====================

		// =================特殊报表路径处理 ====================
		forward = getSpecialForward(myForm, map, forward);
		// ===================end ====================
		
		

		// ======================2010.9.19 李容=================
		// 工程路径
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		File file = new File(pro_path + forward);
		if (!file.exists()) {
			// 文件不存在 跳转到默认文件夹下的对应文件
			forward = "/xszz/comm/print/comm/" + xmb + ".jsp";
		}
		// ======================end 2010.9.19 李容=================
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
	public  void getStuInfo(XszzTyForm myForm, HashMap<String, String> map) {

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
	 * 获得家庭情况
	 * 
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	private void getJtqkInfo(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {

		XszzCommService service = new XszzCommService();

		// 学校代码
		String xxdm = StandardOperation.getXxdm();
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 项目名称
		String xmmc = map.get("xmmc");

		// 家庭情况信息
		HashMap<String, String> jtInfo = service.getJtqkInfo(myForm);
		map.putAll(jtInfo);

		List<HashMap<String, String>> cyList = service.getJtgxList(myForm);
		//暂存
		List<HashMap<String, String>> xscyList = new ArrayList<HashMap<String,String>>();

		// 成员数目
		int cyNum = (cyList != null && cyList.size() > 0) ? cyList.size() : 0;

		// 闽江学院
		if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			// 家庭情况调查
			if (XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)) {

				// 补空数目
				int bkNum = cyNum > 6 ? 0 : 6 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					cyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
		}else  if (Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)) {//成都体育学院
			
			if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {
			
				int zhs=5;
				cyList=getSpaceList(cyList, zhs);
				request.setAttribute("cyNum", String.valueOf(cyNum));
			}else if(XszzXmdm.XSZZ_TCZXJ.equalsIgnoreCase(xmmc)){
				int zhs=3;
				cyList=getSpaceList(cyList, zhs);
				cyNum=cyList.size();
				request.setAttribute("cyNum", String.valueOf(cyNum));
			}
				
		}
		
		// 湖州师范学院
		else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {
			//  国家助学金-家庭情况调查
			if (XszzXmdm.XSZZ_HZSF_GJZXJ.equalsIgnoreCase(xmdm)) {
				//只取5条家庭成员信息
				if(cyNum > 5){
					for (int i=0;i<5;i++){
						xscyList.add(cyList.get(i));
					}
					cyNum=5;
				}else{
					xscyList.addAll(cyList);
				}
				// 补空数目
				int bkNum = cyNum > 5 ? 0 : 5 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					xscyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
			// 家庭情况调查
			if (XszzXmdm.XSZZ_HZSF_JTQKDC.equalsIgnoreCase(xmdm)) {
				//只取6条家庭成员信息
				if(cyNum > 6){
					for (int i=0;i<5;i++){
						xscyList.add(cyList.get(i));
					}
					cyNum=6;
				}else{
					xscyList.addAll(cyList);
				}
				// 补空数目
				int bkNum = cyNum > 6 ? 0 : 6 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					xscyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
			// 中国工商银行湖州市分行助学贷款申请审批表
			if (XszzXmdm.XSZZ_HZSF_ZGGSZXDK.equalsIgnoreCase(xmdm)) {
				//只取4条家庭成员信息
				if(cyNum > 4){
					for (int i=0;i<5;i++){
						xscyList.add(cyList.get(i));
					}
					cyNum=4;
				}else{
					xscyList.addAll(cyList);
				}
				// 补空数目
				int bkNum = cyNum > 4 ? 0 : 4 - cyNum;
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					xscyList.add(space);
				}
				cyNum += bkNum;
				request.setAttribute("cyNum", String.valueOf(cyNum));
				request.setAttribute("bkNum", String.valueOf(bkNum));
			}
		}
		
		request.setAttribute("cyNum", String.valueOf(cyNum));
		// 湖州师范学院
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)&& XszzXmdm.XSZZ_HZSF_GJZXJ.equalsIgnoreCase(xmdm)){
			request.setAttribute("cyList", xscyList);
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)&& XszzXmdm.XSZZ_HZSF_JTQKDC.equalsIgnoreCase(xmdm)){
			request.setAttribute("cyList", xscyList);
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)&& XszzXmdm.XSZZ_HZSF_ZGGSZXDK.equalsIgnoreCase(xmdm)){
			request.setAttribute("cyList", xscyList);
		}else{
			request.setAttribute("cyList", cyList);
		}
		
	}
	
	/**
	 * 
	 * @param cyList
	 * @param zhs
	 * @return
	 */
	public List<HashMap<String,String>>getSpaceList(List<HashMap<String,String>>cyList,int zhs){
		
		// 家庭成员数
		int cyNum=cyList.size();
		// 补空行数
		int bkNum = cyNum > zhs ? 0 :zhs - cyNum;
		for (int i = 0; i < bkNum; i++) {
			HashMap<String, String> space = new HashMap<String, String>();
			cyList.add(space);
		}
		
		return cyList;
	}
	

	/**
	 * 获得项目相关信息
	 * 
	 * @param myForm
	 * @param map
	 * @throws Exception
	 */
	private void getXmxgInfo(XszzTyForm myForm, HashMap<String, String> map)
			throws Exception {

		XszzCommService service = new XszzCommService();

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = myForm.getXmdm();

		// 非家庭情况调查和困难生
		if (!XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)
				&& !XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {

			HashMap<String, String> xmxgInfo = service.getXmInfo(myForm);

			// 闽江学院
			if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
				// 国家助学贷款
				if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {

					// 金额大小写转换
					String zje = xmxgInfo.get("xmzzje");
					String xnje = xmxgInfo.get("xnje");

					zje = MoneyFormat.format(zje);
					xnje = MoneyFormat.format(xnje);

					xmxgInfo.put("zje", zje);
					xmxgInfo.put("xnje", xnje);

				}
			}

			map.putAll(xmxgInfo);
		}
	}

	/**
	 * 获得项目分级情况
	 * 
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	private void getXmfjqk(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {

		XszzCommService service = new XszzCommService();

		// 项目分级情况列表
		List<HashMap<String, String>> xmfjqkList = service
				.getXmfjqkList(myForm);

		int fjNum = (xmfjqkList != null && xmfjqkList.size() > 0) ? xmfjqkList
				.size() : 0;

		request.setAttribute("xmfjqkList", xmfjqkList);
		request.setAttribute("fjNum", String.valueOf(fjNum));
	}

	/**
	 * 获得学生综测成绩
	 * 
	 * @param myForm
	 * @param map
	 */
	private void getStuZcInfo(XszzTyForm myForm, HashMap<String, String> map) {

		XszzCommService service = new XszzCommService();

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 学号
		String xh = myForm.getXh();
		// 学年
		String xn = map.get("xn");

		// 闽江学院
		if (Globals.XXDM_MJXY.equals(xxdm)) {
			// 国家励志奖学金
			if (XszzXmdm.XSZZ_GJLZJXJ.equalsIgnoreCase(xmdm)
					&& !Base.isNull(xh) && !Base.isNull(xn)) {
				HashMap<String, String> zcfMap = service.getMjxyZcf(xh, map
						.get("xn"));
				map.putAll(zcfMap);
			}
		}
		//湖州师范学院
		if (Globals.XXDM_HZSFXY.equals(xxdm)) {
			// 国家励志奖学金
			if (XszzXmdm.XSZZ_HZSF_GJLZJXJ.equalsIgnoreCase(xmdm)
					&& !Base.isNull(xh) && !Base.isNull(xn)) {
				//成绩信息待处理
				HashMap<String, String> zcfMap = service.getMjxyZcf(xh, map
						.get("xn"));
				map.putAll(zcfMap);
			//国家奖学金
			}else if (XszzXmdm.XSZZ_HZSF_GJJXJ.equalsIgnoreCase(xmdm)
					&& !Base.isNull(xh) && !Base.isNull(xn)) {
				//成绩信息待处理
				HashMap<String, String> zcfMap = service.getMjxyZcf(xh, map
						.get("xn"));
				map.putAll(zcfMap);
			}
		}
	}

	/**
	 * 获得困难生信息
	 * 
	 * @param myForm
	 * @param map
	 */
	private void getKnsInfo(XszzTyForm myForm, HashMap<String, String> map) {

		XszzCommService service = new XszzCommService();

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 学号
		String xh = myForm.getXh();
		// 学年
		String xn = map.get("xn");
		if("".equals(xn)){
			xn=Base.currXn;
		}
		// 学期
		String xq = map.get("xq");

		// 非困难生项目
		if (!Base.isNull(xh) && !Base.isNull(xn)) {

			myForm.setPkValue(XszzXmdm.XSZZ_KNS);

			HashMap<String, String> rsKns = service.getXmxgInfo(myForm);

			String shjb = rsKns.get("shjb");
			String sqzq = rsKns.get("sqzq");

			String knjb = service.getKnjbForXh(shjb, sqzq, xn, xh, xq);

			map.put("knjb", knjb);
		}
	}

	/**
	 * 获得其他信息
	 * 
	 * @param myForm
	 * @param map
	 * @throws Exception
	 */
	private void getQtInfo(XszzTyForm myForm, HashMap<String, String> map)
			throws Exception {

		XszzCommService service = new XszzCommService();

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 学号
		String xh = myForm.getXh();
		// 学年
		String xn = map.get("xn");
		// 学期
		String xq = map.get("xq");

		// 中国地大
		//if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)||Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {

			HashMap<String, String> qtInfo = service.getQtInfo(myForm);

			// 优秀门数
			String yxms = qtInfo.get("yxms");
			yxms = Base.isNull(yxms) ? "0" : yxms;
			// 优秀门数
			String lhms = qtInfo.get("lhms");
			lhms = Base.isNull(lhms) ? "0" : lhms;
			// 及格以上门数
			String jgysms = String.valueOf(Integer.parseInt(yxms)
					+ Integer.parseInt(lhms));
			// 班级人数
			String bjrs = service.getBjrs(map);

			map.put("bjrs", bjrs);
			map.put("jgysms", jgysms);
			qtInfo.put("xh", xh);
			qtInfo.put("xn", xn);
			map.putAll(qtInfo);
		//}

	}
	
	/**
	 * 获得成绩相关信息
	 * 
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	private void getCjInfo(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {

		XszzCommService service = new XszzCommService();

		// 学校代码
		String xxdm = StandardOperation.getXxdm();
		// 学号
		String xh = myForm.getXh();
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 项目表
		String xmb = myForm.getXmb();

		// 宁波城市
		if (Globals.XXDM_NBCSZYJSXY.equals(xxdm)) {
			// 国家助学贷款,学费减免
			if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)
					|| XszzXmdm.XSZZ_XFJM.equalsIgnoreCase(xmdm)) {
				// 不及格科目总数
				request.setAttribute("bjgkm", service.getBjgs(xh));
				// 有无违纪处分
				request.setAttribute("sfwj", service.isWj(myForm));
				// 上学年德充成绩
				request.setAttribute("dycj", service.sxnDyf(myForm));
			}
		}

		// 广州番禺困难生认定（上一学年不及格成绩）
		if (Globals.XXDM_GZFYZYJSXY.equals(xxdm)
				&& XszzXmdm.XSZZ_KNS.equals(xmdm)) {
			List<HashMap<String, String>> bjgcjList = service.getBjgcjByXn(
					Base.beforXn, xh);
			request.setAttribute("bjgcjList", bjgcjList);
		}

		// ====================以下 made by 李容JJ========================
		// 学生成绩信息
		List<HashMap<String, String>> cjList = service.getXscjList(xxdm, xmb,
				myForm);

		int cjNum = 0;
		if (cjList != null && cjList.size() > 0) {
			cjNum = 0 == cjList.size() % 3 ? cjList.size() / 3
					: cjList.size() / 3 + 1;
		}
		request.setAttribute("cjList", cjList);
		request.setAttribute("cjNum", cjNum);

		// 学习成绩和综测信息
		map = service.getXxcjZcxx(xxdm, xmb, map);

		// ====================endJ========================

	}

	/**
	 * 获得特殊资助项目的路径
	 * 
	 * @param myForm
	 * @param map
	 * @param forward
	 * @throws Exception
	 */
	private String getSpecialForward(XszzTyForm myForm,
			HashMap<String, String> map, String forward) throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 项目类别
		String xmlb = map.get("xmlb");
		// 项目名称
		String xmmc = map.get("xmmc");
		
		// 海南大学资助项目
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			if (XszzXmdm.XSZZ_HKJXJ.equalsIgnoreCase(xmdm)) {// 何康奖学金
				forward = "/xszz/comm/print/hndx/hkjxj.jsp";
			} else if (XszzXmdm.XSZZ_JXFZJJ.equalsIgnoreCase(xmdm)) {// 教育发展基金（金历助学金)
				forward = "/xszz/comm/print/hndx/jyfzjj.jsp";
			} else if (XszzXmdm.XSZZ_ZHYD.equalsIgnoreCase(xmdm)) {// 中海油田
				forward = "/xszz/comm/print/hndx/zhydzxj.jsp";
			}
		}

		// 浙江科技学院
		if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)) {
			if (XszzXmdm.XSZZ_ZWSZXJ.equalsIgnoreCase(xmdm)) { // 真维斯助学金
				forward = "/xszz/comm/print/zjkj/zwszxj.jsp";
			}
		}

		// 中国地大
		else if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			if (XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)) {// 家庭情况调查
				forward = "/xszz/comm/print/comm/jtqkdcb.jsp";
			} else if (XszzXmdm.XSZZ_GJJXJ.equalsIgnoreCase(xmdm)
					|| XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)
					|| XszzXmdm.XSZZ_GJLZJXJ.equalsIgnoreCase(xmdm)) {// 国家奖学金,国家助学金，国家励志奖学金
				forward = "/xszz/comm/print/zgdd/gj.jsp";
			} else if ("奖学金".equalsIgnoreCase(xmlb)) {// 项目类别为奖学金的项目
				forward = "/xszz/comm/print/zgdd/jxj.jsp";
			} else if ("助学金".equalsIgnoreCase(xmlb)) {// 项目类别为助学金的项目
				forward = "/xszz/comm/print/zgdd/zxj.jsp";
			} else {// 其他
				forward = "/xszz/comm/print/zgdd/zxj.jsp";
			}
		} else if (Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {// 天津工业大学
			if (XszzXmdm.XSZZ_ZWSZXJ.equalsIgnoreCase(xmdm)) { // 真维斯助学金
				forward = "/xszz/comm/print/tjgy/zwszxj.jsp";
			} else if (XszzXmdm.XSZZ_TJYDZXJ.equalsIgnoreCase(xmdm)) {// 天津移动大学生助学基金
				forward = "/xszz/comm/print/tjgy/tjydzxj.jsp";
			} else if (XszzXmdm.XSZZ_TJRMZFJXJ.equalsIgnoreCase(xmdm)) {// 天津市人民政府奖学金
				forward = "/xszz/comm/print/tjgy/tjrmzfjxj.jsp";
			} else if (XszzXmdm.XSZZ_TJHSZHBAZXJ.equalsIgnoreCase(xmdm)) {// 天津市红十字会博爱助学金
				forward = "/xszz/comm/print/tjgy/tjhszhbazxj.jsp";
			} else if (XszzXmdm.XSZZ_LDYZZJTJJKNXS.equalsIgnoreCase(xmdm)) {// 老党员资助家庭经济困难学生
				forward = "/xszz/comm/print/tjgy/ldyzzjtjjknxs.jsp";
			} else if (XszzXmdm.XSZZ_TJJYFZJJNSYHZXJ.equalsIgnoreCase(xmdm)) {// 天津市教育发展基金 天津农商银行助学金
				forward = "/xszz/comm/print/tjgy/tjjyfzjjnsyhzxj.jsp";
			}
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){//北京联合大学
			if (XszzXmdm.XSZZ_JTJJKNSRDB.equalsIgnoreCase(xmdm)) { // 家庭经济困难学生认定申请表
				forward = "/xszz/comm/print/bjlh/jtjjknxsrd.jsp";
			}
		} else if (Globals.XXDM_ZJJSZYJSXY.equals(xxdm)){//浙江建设职业技术学院
			if (XszzXmdm.XSZZ_TSKNBZ.equals(xmdm)){//特殊困难补助
				forward = "/xszz/comm/print/zjjs/tsknbz.jsp";
			}else if(XszzXmdm.XSZZ_ZTJTAXGYJ.equals(xmdm)){//中天集团爱心公益金
				forward = "/xszz/comm/print/zjjs/ztjtaxgyjj.jsp";
			}
		} else if (Globals.XXDM_NTHYZYJSXY.equals(xxdm)){
			if ("7001".equals(xmdm)){//特殊困难补助
				forward = "/xszz/comm/print/nthy/lsknbz.jsp";
			}else if(XszzXmdm.XSZZ_XFJM.equals(xmdm)){//学费减免
				forward = "/xszz/comm/print/nthy/xszz_xfjmb.jsp";
			}
		} else if(Globals.XXDM_GXLSXY.equalsIgnoreCase(xxdm)){//广西麓山学院
			
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/xszz_gjjxjb.jsp";//国家奖学金
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/gjlzjxj.jsp";//国家励志奖学金
			}else if(XszzXmdm.XSZZ_GJZXJ.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/gjzxj.jsp";//国家助学金表
			}else if(XszzXmdm.XSZZ_TJBB_GXLSXY_ZZQRMZFJXJB.equals(xmdm)){
				forward = "/xszz/comm/print/gxlsxy/zzqrmzfjxjb.jsp";//自治区人民政府奖学金申请表
			}
			
		}else if(Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm)){//北京联合大学
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/xszz_gjjxjb.jsp";//国家奖学金
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/gjlzjxj.jsp";//国家励志奖学金
			}else if(XszzXmdm.XSZZ_HZNY_XFHZXDKDC.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/xfhzxdkdcsqb.jsp";//学费和助学贷款贷出申请表
			}else if(XszzXmdm.XSZZ_JTQKDC.equals(xmdm)){
				forward = "/xszz/comm/print/hzny/jtqkdcb.jsp";//家庭情况调查表
			}else if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzny/xszz_knsb.jsp";// 家庭经济困难学生认定申请表
			}
			
		} else if(Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)){//湖州师范学院
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/xszz_gjjxjb.jsp";//国家奖学金
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/gjlzjxj.jsp";//国家励志奖学金
			}else if(XszzXmdm.XSZZ_GJZXJ.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/gjzxj.jsp";//国家助学金
			}else if(XszzXmdm.XSZZ_JTQKDC.equals(xmdm)){
				forward = "/xszz/comm/print/hzsfxy/jtqkdcb.jsp";//家庭情况调查表
			}else if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_knsb.jsp";// 家庭经济困难学生认定申请表
			}else if (XszzXmdm.XSZZ_HZSF_LHYY.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_lhyy.jsp";//“陆侯燕英”帮困奖学金
			}else if (XszzXmdm.XSZZ_HZSF_YNSR.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_ynsr.jsp";//“迎南树人”奖学金
			}else if (XszzXmdm.XSZZ_HZSF_KNSMZPY.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_knsmzpyb.jsp";//家庭经济困难学生民主评议表
			}else if (XszzXmdm.XSZZ_HZSF_JJJZJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_jjjzj.jsp";//紧急救助金
			}else if (XszzXmdm.XSZZ_HZSF_YXTGZXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_szxssqb.jsp";//永兴特钢助学金
			}else if (XszzXmdm.XSZZ_HZSF_SHAXZX.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_szxssqb.jsp";//社会爱心助学
			}else if (XszzXmdm.XSZZ_HZSF_KNS.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_knsb.jsp";//家庭经济困难学生认定申请表
			}else if (XszzXmdm.XSZZ_HZSF_BYSZXDK.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_byszxdkxxb.jsp";//毕业生助学贷款
			}
			
			
			else if (XszzXmdm.XSZZ_HZSF_ZGGSZXDK.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_zggszxdk.jsp";//中国工商银行湖州市分行助学贷款申请审批表
			}else if (XszzXmdm.XSZZ_HZSF_GJZXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/gjzxj.jsp";//国家助学金申请审批表
			}else if (XszzXmdm.XSZZ_HZSF_GJLZJXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/gjlzjxj.jsp";//国家励志奖学金申请审批表
			}else if (XszzXmdm.XSZZ_HZSF_GJJXJ.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/xszz_gjjxjb.jsp";//国家奖学金申请审批表
			}else if (XszzXmdm.XSZZ_HZSF_JTQKDC.equalsIgnoreCase(xmdm)) { 
				forward = "/xszz/comm/print/hzsfxy/jtqkdcb.jsp";//学生及家庭情况调查表
			}
			
			
			
		} else if(Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)){//成都体育学院
			//将身份证号分解
			String xssfzh = map.get("sfzh");
			if(!Base.isNull(xssfzh)){
				int sylen= 18-xssfzh.length();
				for (int i=0;i<xssfzh.length();i++){
					map.put("sfzh"+i, xssfzh.charAt(i)+"");
				}
				for (int i=0;i<sylen;i++){
					map.put("sfzh"+(xssfzh.length()+i), "");
				}
			}
			//计算及格科目数
			int jgkms = Integer.parseInt(map.get("yxkms")!=null?map.get("yxkms"):"0")+Integer.parseInt(map.get("lhkms")!=null?map.get("lhkms"):"0");
			map.put("jgkms", jgkms==0?null:jgkms+"");
			
			if (XszzXmdm.XSZZ_GJJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/cdty/xszz_gjjxjb.jsp";//国家奖学金
			}else if(XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)){
				forward = "/xszz/comm/print/cdty/gjlzjxj.jsp";//国家励志奖学金
			}else if(XszzXmdm.XSZZ_GJZXJ.equals(xmdm)){
				forward = "/xszz/comm/print/cdty/gjzxj.jsp";//国家助学金
			}else if (XszzXmdm.XSZZ_TCZXJ.equalsIgnoreCase(xmmc)) { 
				forward = "/xszz/comm/print/cdty/tczxj.jsp";// 体彩助学金
			}
		}
		
		return forward;
	}

	/**
	 * 某些学校的项目存放一些个性化值
	 * 
	 * @sjf
	 * @param myForm
	 * @param request
	 */
	private void setSpecialValue(XszzTyForm myForm, HttpServletRequest request) {
		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = myForm.getXmdm();

		if (XszzXmdm.XSZZ_JXFZJJ.equalsIgnoreCase(xmdm)) { // 教育发展基金
			if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 海南大学
				String[] ids = new String[] { "szsheng", "szshi", "szxian",
						"xxsheng", "xxshi", "xxxian" };
				changeQx(request, ids);
			}
		} else if (XszzXmdm.XSZZ_ZHYD.equalsIgnoreCase(xmdm)) {// 中和油田
			if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 海南大学
				String[] ids = new String[] { "szsheng", "szshi", "szxian" };
				changeQx(request, ids);
			}
		} else if (XszzXmdm.XSZZ_XFHJ.equalsIgnoreCase(xmdm)) {// 学费缓交
			if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
				String[] ids = new String[] { "szsheng", "szshi", "szxian" };
				changeQx(request, ids);
			}
		}
	}

	/**
	 * 设置省市县
	 * 
	 * @sjf
	 * @param request
	 * @param ids
	 */
	private void changeQx(HttpServletRequest request, String[] ids) {
		XszzCommService service = new XszzCommService();
		Map<String, String> rs = (Map<String, String>) request
				.getAttribute("rs");
		String[] dm = new String[ids.length];

		for (int i = 0; i < ids.length; i++) {
			dm[i] = rs.get(ids[i]);
		}

		List<HashMap<String, String>> list = service.getQxmc(dm, ids);

		for (Map<String, String> map : list) {
			request.setAttribute(map.get("id"), map.get("qxmc"));
		}
	}

	/**
	 * 广州番禺，家庭经济困难生学生认定表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printKnsrd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzTyForm myForm = (XszzTyForm) form;
		XszzCommService service = new XszzCommService();

		request.setAttribute("knsList", service.getKnsInfo(myForm.getXh()));
		return mapping.findForward("printKnsrd");
	}
	
	/**
	 * 学生受资助项目情况
	 * （受资助项目含国家助学贷款、国家励志奖学金、学校奖学金等）
	 * @param myForm
	 * @param map
	 * @param request
	 * @throws Exception
	 * @author honglin
	 * @2013-01-17
	 */
	private void getXszzxmInfo(XszzTyForm myForm, HashMap<String, String> map,
			HttpServletRequest request) throws Exception {
		CommService service = new CommService();
		List<HashMap<String, String>> xszzList = service.getXszzList(myForm.getXh());
		List<HashMap<String, String>> zzList = new ArrayList<HashMap<String,String>>(); 
		// 学校代码
		String xxdm = StandardOperation.getXxdm();
		
		int xszzNum = (xszzList != null && xszzList.size() > 0) ? xszzList.size() : 0;
		// 湖州师范学院
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {
				// 补空数目
				int bkNum = xszzNum > 6 ? 0 : 6 - xszzNum;
				//只取6条受助项目信息
				if(xszzNum > 6){
					for (int i = 0; i < 6; i++) {
						zzList.add(xszzList.get(i));
					}
					xszzNum=6;
				}else{
					zzList.addAll(xszzList);
				}
				for (int i = 0; i < bkNum; i++) {
					HashMap<String, String> space = new HashMap<String, String>();
					zzList.add(space);
				}
				xszzNum += bkNum;
				request.setAttribute("xszzNum", String.valueOf(xszzNum));
				request.setAttribute("xszzbkNum", String.valueOf(bkNum));
				
		}
		request.setAttribute("xszzList", zzList);
	}
}
