package xgxt.mdqr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;

import com.zfsoft.basic.BasicService;

public class MdqrService {

	/**
	 * method getXmlbList 获取项目类别列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmlbList(String gnmk) {
		MdqrDAO dao = new MdqrDAO();
		return dao.getXmlbList(gnmk);
	}

	/**
	 * method getXmList 获取项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmList(String xmlbdm) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("dm", "");
		hashMap.put("mc", "----请选择----");
		list.add(hashMap);
		list.addAll(dao.getXmList(xmlbdm));
		return list;
	}
	
	/**
	 * 根据设置名单权限获取列表
	 * method getXmList 获取项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmListBySzqx(String xmlbdm,String yhqx) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("dm", "");
		hashMap.put("mc", "----请选择----");
		list.add(hashMap);
		list.addAll(dao.getXmList(xmlbdm,yhqx));
		return list;
	}
	
	/**
	 * 根据设置名单权限获取列表
	 * method getXmList 获取项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmListByShqx(String xmlbdm,String yhqx) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String shjb="";
		if("xx".equalsIgnoreCase(yhqx)){
			shjb=" and (shjb='xxsh' or shjb='xyxsh') ";
		}else if("xy".equalsIgnoreCase(yhqx)){
			shjb=" and (shjb='xysh' or shjb='xyxsh') ";
		}
		
		hashMap.put("dm", "");
		hashMap.put("mc", "----请选择----");
		list.add(hashMap);
		list.addAll(dao.getXmListByShqx(xmlbdm,shjb));
		return list;
	}
	
	/**
	 * method getXmList 获取项目列表 return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmShList(String xmdm,String mdqr) {
		MdqrDAO dao = new MdqrDAO();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.addAll(dao.getXmShList(xmdm,mdqr));
		return list;
	}

	/**
	 * 保存名单设置结果
	 * 
	 * @throws Exception
	 *             名单设置批量保存 method mdsz return void
	 */
	public void mdsz(HttpServletRequest request, MdqrForm myForm)
			throws Exception {
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();

		// 进行数据操作 的表名
		String realTable = "mdqr_xmnrb";

		// 当前页的学号数组(先删后增将该页信息删除时)
		String[] xh = myForm.getSave_xh();
		// 被选中信息(值是学号)
		String[] cbv = myForm.getPrimarykey_cbv();
		// 项目

		String xm = myForm.getSave_xmdm();
		// 项目类别
		String xmlbdm = myForm.getSave_xmlbdm();
		
		String sbsj= myForm.getSave_sbsj();

		String xn = myForm.getXn();

		String xq = myForm.getXq();

		String nd = myForm.getNd();
		
		String []isDis=myForm.getIsDisable();

		String[] pkValue = new String[xh.length];
		// 判断操作是否成功
		boolean reslut = false;
		
		//设置周期
		String pk ="";
		if("xn".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn";
		}else if("xq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn||xq";
		}else if("nd".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||nd";
		}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||sbsj";
		}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh";
		}
		
		
		String[] arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq","nd" };
		for (int i = 0; i < xh.length; i++) {
			//判断数据是否为只读,只删除非只读数据
			if(!"disabled".equalsIgnoreCase(isDis[i])){
				if("xn".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + xn;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn"};
				}else if("xq".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + xn + xq;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq"};
				}else if("nd".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + nd;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "nd" };
				}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i] + sbsj;
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh" };
				}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
					pkValue[i] = xm + xmlbdm + xh[i];
					arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh"};
				}
			}
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		MdqrModel model = new MdqrModel();
		if (cbv != null) {
			String[] xnArr = new String[cbv.length];
			String[] xqArr = new String[cbv.length];
			String[] ndArr = new String[cbv.length];
			String[] xmArr = new String[cbv.length];
			String[] xmlbArr = new String[cbv.length];
			String[] sbsjArr = new String[cbv.length];
			for (int i = 0; i < cbv.length; i++) {
				
					xmArr[i] = xm;
					xmlbArr[i] = xmlbdm;
					sbsjArr[i] =sbsj;
					if("xn".equalsIgnoreCase(getSzzq(xm))){
						xnArr[i] = xn;
					}else if("xq".equalsIgnoreCase(getSzzq(xm))){
						xnArr[i] = xn;
						xqArr[i] = xq;
					}else if("nd".equalsIgnoreCase(getSzzq(xm))){
						ndArr[i] = nd;
					}
				
			}
			
			model.setXh(cbv);
			model.setSbsj(sbsjArr);
			model.setXmdm(xmArr);
			model.setXmlbdm(xmlbArr);
			if("xn".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
			}else if("xq".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
				model.setXq(xqArr);
			}else if("nd".equalsIgnoreCase(getSzzq(xm))){
				model.setNd(ndArr);
			}
		}
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
	}

	/**
	 * 保存名单确认结果
	 * 
	 * @throws Exception
	 * 名单确认批量保存 method mdsz return void
	 */
	public void mdqr(HttpServletRequest request, MdqrForm myForm)
			throws Exception {
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();

		// 进行数据操作 的表名
		String realTable = "mdqr_xmnrb";

		// 当前页的学号数组(先删后增将该页信息删除时)
		String[] xh = myForm.getSave_xh();
		// 被选中信息(值是学号)
		String[] cbv = myForm.getPrimarykey_cbv();
		// 项目
		String[] xysh =myForm.getXysh();
		
		String[] xxsh =myForm.getXxsh();
		
		String[] sbsj=myForm.getSbsjArr();
		
		String xm = myForm.getQueryequals_xmdm();
		// 项目类别
		String xmlbdm = myForm.getQueryequals_xmlbdm();

		String xn = myForm.getXn();

		String xq = myForm.getXq();

		String nd = myForm.getNd();

		String[] pkValue = new String[xh.length];
		// 判断操作是否成功
		boolean reslut = false;

//		设置周期
		String pk ="";
		if("xn".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn";
		}else if("xq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||xn||xq";
		}else if("nd".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||nd";
		}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh||sbsj";
		}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
			pk = "xmdm||xmlbdm||xh";
		}
		
		
		String[] arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq","nd" };
		for (int i = 0; i < xh.length; i++) {
			if("xn".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + xn;
			}else if("xq".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + xn + xq;
			}else if("nd".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + nd;
			}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i] + sbsj[i];
			}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
				pkValue[i] = xm + xmlbdm + xh[i];
			}
		}
		if("xn".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn","mdqr","xysh","xxsh"};
		}else if("xq".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "xn", "xq","mdqr","xysh","xxsh"};
		}else if("nd".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh", "nd","mdqr","xysh","xxsh" };
		}else if("wzq".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh","mdqr","xysh","xxsh" };
		}else if("jyc".equalsIgnoreCase(getSzzq(xm))){
			arrzd = new String[] { "xmdm", "xmlbdm","sbsj","xh","mdqr","xysh","xxsh"};
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		MdqrModel model = new MdqrModel();
		if (cbv != null) {
			String[] xnArr = new String[xh.length];
			String[] xqArr = new String[xh.length];
			String[] ndArr = new String[xh.length];
			String[] xmArr = new String[xh.length];
			String[] xmlbArr = new String[xh.length];
			String[] xyshArr =new String[xh.length];
			String[] xxshArr =new String[xh.length];
			String[] sbsjArr =new String[xh.length];
			String[] mdqrArr = new String[xh.length];
			for(int j=0;j<xh.length;j++){
				if("xn".equalsIgnoreCase(getSzzq(xm))){
					xnArr[j] = xn;
				}else if("xq".equalsIgnoreCase(getSzzq(xm))){
					xnArr[j] = xn;
					xqArr[j] = xq;
				}else if("nd".equalsIgnoreCase(getSzzq(xm))){
					ndArr[j] = nd;
				}
				xmArr[j] = xm;
				xmlbArr[j] = xmlbdm;
				xyshArr[j] = xysh[j];
				xxshArr[j] = xxsh[j];
				sbsjArr[j] = sbsj[j];
				for (int i = 0; i < cbv.length; i++) {
					if(xh[j].equalsIgnoreCase(cbv[i])){
						
						mdqrArr[j] = "checked";
						break;
					}
				}
			}
			model.setXh(xh);
			model.setXmdm(xmArr);
			model.setXmlbdm(xmlbArr);
			model.setSbsj(sbsjArr);
			model.setXysh(xyshArr);
			model.setXxsh(xxshArr);
			model.setMdqr(mdqrArr);
			if("xn".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
			}else if("xq".equalsIgnoreCase(getSzzq(xm))){
				model.setXn(xnArr);
				model.setXq(xqArr);
			}else if("nd".equalsIgnoreCase(getSzzq(xm))){
				model.setNd(ndArr);
			}
		}
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
	}
	
	public void getXmxx(HttpServletRequest request, MdqrForm form)
			throws Exception {
		MdqrDAO dao = new MdqrDAO();
		BasicService basicService = new BasicService();
		// 查询结果集
		request.setAttribute("rs", dao.getXmxx(form));
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb", new String[] { "编号", 
				"学号", "姓名", "年级", Base.YXPZXY_KEY,"专业","班级","是否设置名单" }));
	}
	
	public void getMdqrXx(HttpServletRequest request, MdqrForm form)
		throws Exception {
		MdqrDAO dao = new MdqrDAO();
		BasicService basicService = new BasicService();
		
		String [] topTr=new String[] {"编号","学号", "姓名", "年级","名单设置时间",  Base.YXPZXY_KEY, "班级","是否确认"};
		request.setAttribute("rsNum", dao.getMdqrXx(form).size());
		request.setAttribute("colsStr", topTr.length);
		request.setAttribute("rs", dao.getMdqrXx(form));
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmmdqrb", topTr));
	}
	
	public void getXx(HttpServletRequest request){
		
		BasicService basicService = new BasicService();
		
		String [] topTr=new String[] { "编号","学号", "姓名", "年级","名单设置时间",  Base.YXPZXY_KEY, "班级","是否确认"};
		List<String[]>list=new ArrayList<String[]>();

		request.setAttribute("rs", list);
		request.setAttribute("rsNum",0);
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmmdqrb", topTr));
		request.setAttribute("colsStr", topTr.length);
	}
	
	//返回查询结果集字段
	public String[] getOutputArr(MdqrForm form){
		//审核级别
		String shjb=getXmShjb(form);
		String []outputColumn={"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","sbsj","xysh","xxsh"};
		//判断审核级别   学院
		if("xysh".equalsIgnoreCase(shjb)){
			outputColumn=new String[]{"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","sbsj","xysh"};
		}else if("xxsh".equalsIgnoreCase(shjb)){
			outputColumn=new String[]{"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","sbsj","xxsh"};
		}
		return outputColumn;
	}
	
	/**
	 * disabled属性判断
	 * @param form
	 * @return
	 */
	public void setDisabled(HttpServletRequest request,MdqrForm form){
		//审核级别
		String shjb=getXmShjb(form);
		if("xyxsh".equalsIgnoreCase(shjb)
			&& "xy".equalsIgnoreCase(form.getUserType())){
				request.setAttribute("clientColumns", "(case when xxsh='通过' then 'disabled' else '' end)disabled, ");
		}else{
			request.setAttribute("clientColumns", "'' disabled, ");
		}
		
	}
	
	//获取项目审核级别
	public String  getXmShjb(MdqrForm form){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.getXmShjb(form);
		return hashMap.get("shjb");
	}
	
	/**
	 * 审核时保存的值
	 */
	public HashMap<String, String> getShValMap(MdqrForm form) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		// 审核级别
		String shjb = getXmShjb(form);
		// 学院审核流程
		if ("xysh".equalsIgnoreCase(shjb)) {
			hashMap.put("xysh", form.getShjg());
			// 学校审核流程
		} else if ("xxsh".equalsIgnoreCase(shjb)) {
			hashMap.put("xxsh", form.getShjg());
			// 学院审核 - 学校审核流程
		} else if ("xyxsh".equalsIgnoreCase(shjb)) {
			// 学院用户
			if ("xy".equalsIgnoreCase(form.getUserType())) {
				hashMap.put("xysh", form.getShjg());
				// 学校用户
			} else if ("xx".equalsIgnoreCase(form.getUserType())
					|| "admin".equalsIgnoreCase(form.getUserType())) {
				hashMap.put("xxsh", form.getShjg());
			}
		}
		return hashMap;
	}
	
	/**
	 * 审核时保存的值
	 */
	public HashMap<String, String> getQrValMap(String mdqr) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		// 审核级别
		hashMap.put("mdqr",mdqr );
		return hashMap;
	}

	/**
	 * 根据选择的项目代码判断
	 * @param xmdm
	 * @return
	 */
	public String checkMdSz(String xmdm){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.checkMdSz(xmdm);
		return hashMap.get("mdsz");
	}
	
	/**
	 * 根据选择的项目代码判断
	 * @param xmdm
	 * @return
	 */
	public String checkMdQr(String xmdm){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.checkMdQr(xmdm);
		return hashMap.get("mdqr");
	}
	
	/**
	 * 根据模块获取path路径
	 * @param path(path路径),gnmk(功能模块)
	 */
	public String getPath(String path,String gnmk){
		path+="&gnmk="+gnmk;
		return path;
	}
	
	/**
	 * 获取用户权限
	 * 
	 * @param
	 */
	public String getYhqx(MdqrForm form) {
		String yhqx = "";
		if ("true".equalsIgnoreCase(form.getIsFdy())) {
			yhqx = "fdy";
		} else if ("xy".equalsIgnoreCase(form.getUserType())) {
			yhqx = "xy";
		} else if ("xx".equalsIgnoreCase(form.getUserType())
				|| "admin".equalsIgnoreCase(form.getUserType())) {
			yhqx = "xx";
		}
		return yhqx;
	}
	
	/**
	 * 根据项目代码获取周期
	 * 
	 */
	public String getSzzq(String xmdm){
		MdqrDAO dao =new MdqrDAO();
		HashMap<String,String>hashMap=dao.getSzzq(xmdm);
		String szzq=hashMap.get("szzq");
		return szzq;
	}
	
	/**
	 * 获取审核级别
	 */
	public String getShjb(MdqrForm form){
		MdqrDAO dao=new MdqrDAO();
		HashMap<String,String>hashMap=dao.getShjb(form);
		String shjb=hashMap.get("shjb");
		return shjb;
	}
	
	/**
	 * 判断是否为学院,学校二级审核
	 */
	public void checkShjb(HttpServletRequest request,MdqrForm form){
		
		//判断是否辅导员
		if("ture".equalsIgnoreCase(form.getIsFdy())){
			request.setAttribute("annexTerm", " and 1=2 ");
		}
		if("xx".equalsIgnoreCase(form.getUserType())
				|| "admin".equalsIgnoreCase(form.getUserType())){
			if("xyxsh".equalsIgnoreCase(getShjb(form))){
				request.setAttribute("annexTerm", " and xysh='通过' ");
			}
		}
	}
	
	/**
	 * 初始化列表
	 */
	public void initXmList(HttpServletRequest request){
		//申报周期
		List<HashMap<String,String>>sbzqList=arrToList(new String[]{"学年","学期","年度","无周期","仅一次"});
		//申报周期
		List<HashMap<String,String>>mdszList=arrToList(new String[]{"辅导员", Base.YXPZXY_KEY,"学校","无限制"});
		//申报周期
		List<HashMap<String,String>>mdqrList=arrToList(new String[]{"辅导员", Base.YXPZXY_KEY,"学校","无限制"});
		//申报周期
		List<HashMap<String,String>>shjbList=arrToList(new String[]{ Base.YXPZXY_KEY+"审核","学校审核", Base.YXPZXY_KEY+"审核-学校审核","无审核"});
		//申报周期
		List<HashMap<String,String>>kfsbList=arrToList(new String[]{"开放","关闭"});
		//申报周期
		List<HashMap<String,String>>kfshList=arrToList(new String[]{"开放","关闭"});
		//申报周期
		List<HashMap<String,String>>kfqrList=arrToList(new String[]{"开放","关闭"});
		request.setAttribute("sbzqList", sbzqList);
		request.setAttribute("mdszList", mdszList);
		request.setAttribute("mdqrList", mdqrList);
		request.setAttribute("shjbList", shjbList);
		request.setAttribute("kfsbList", kfsbList);
		request.setAttribute("kfshList", kfshList);
		request.setAttribute("kfqrList", kfqrList);
	}
	
	/**
	 * 将数组转化为hashMap
	 */
	public List<HashMap<String,String>>arrToList(String[]strArr){
		List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<strArr.length;i++){
			HashMap<String,String>hashMap=new HashMap<String,String>();
			hashMap.put("dm", strArr[i]);
			hashMap.put("mc", strArr[i]);
			list.add(hashMap);
		}
		return list;
	}
	
	public boolean deleteXmxx(MdqrForm form) throws Exception{
		MdqrDAO dao=new MdqrDAO();
		return dao.deleteXmxx(form);
	}
	
	public MdqrForm setFormXx(MdqrForm form){
		form.setBjdm(form.getQueryequals_bjdm());
		form.setNj(form.getQueryequals_nj());
		form.setXh(form.getQuerylike_xh());
		form.setXm(form.getQuerylike_xm());
		form.setXydm(form.getQueryequals_xydm());
		form.setZydm(form.getQueryequals_zydm());
		form.setXmlbdm(form.getQueryequals_xmlbdm());
		form.setXmdm(form.getQueryequals_xmdm());
		form.setXn(form.getQueryequals_xn());
		form.setXq(form.getQueryequals_xq());
		form.setNd(form.getQueryequals_nd());
		return form;
	}
	
	/**
	 * 获取学期名称
	 * @param xqdm
	 * @return
	 */
	public String getXqmc(String xqdm){
		MdqrDAO dao=new MdqrDAO();
		String xqmc=dao.getXqmc(xqdm).get("xqmc");
		return xqmc;
	}
	
	//输出字段
	public String[]getOutPut(MdqrForm myForm){
		//项目周期
		String zq=getSzzq(myForm.getQueryequals_xmdm());
		String[] outputColumn = { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "xmmc", "xmlbmc" };
		//根据周期返回结果集列表
		if("xn".equalsIgnoreCase(zq)){
			outputColumn=new String[]{"pkValue", "xh", "xm","xn", "nj", "xymc",
					"zymc", "bjmc", "xmmc", "xmlbmc"};
		}else if("xq".equalsIgnoreCase(zq)){
			outputColumn=new String[]{"pkValue", "xh", "xm","xn","xqmc", "nj", "xymc",
					"zymc", "bjmc", "xmmc", "xmlbmc"};
		}else if("nd".equalsIgnoreCase(zq)){
			outputColumn=new String[]{"pkValue", "xh", "xm","nd", "nj", "xymc",
					"zymc", "bjmc", "xmmc", "xmlbmc"};
		}
		return outputColumn;
	}
	
	public void getShTj(HttpServletRequest request,MdqrForm myForm){
		StringBuilder query=new StringBuilder();
		if(!"".equalsIgnoreCase(myForm.getKssj())
				&& myForm.getKssj()!=null){
			 query.append(" and sbsj>='"+myForm.getKssj()+"'");
		}
		
		if(!"".equalsIgnoreCase(myForm.getJssj())
				&& myForm.getJssj()!=null){
			 query.append(" and sbsj<='"+myForm.getJssj()+"'");
		}
		query.append("and exists (select xmlbdm from mdqr_xmlbb b where a.xmlbdm=b.xmlbdm and gnmk='"+myForm.getGnmk()+"') ");
		request.setAttribute("annexTerm", query.toString());
	}
	
	public MdqrForm initXmdm(MdqrForm myForm){
		ArrayList<HashMap<String,String>> list=(ArrayList<HashMap<String,String>>)getXmlbList(myForm.getGnmk());
		String xmlbdm=list.get(0).get("dm");
		ArrayList<HashMap<String,String>> xmlist=(ArrayList<HashMap<String,String>>)getXmShList("%",getYhqx(myForm));
		myForm.setXmdm(xmlist.get(0).get("dm"));
		myForm.setQueryequals_xmlbdm(xmlbdm);
		return myForm;
	}
}
