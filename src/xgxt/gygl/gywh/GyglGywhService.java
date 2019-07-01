package xgxt.gygl.gywh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.init.InitGygl;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;
import xgxt.gygl.cwgl.GyglCwglDAO;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_公寓维护_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class GyglGywhService extends GyglCommService {

	GyglGywhDAO dao = new GyglGywhDAO();
	
	public List<HashMap<String,String>>qsxxList;
	
	/**
	 * 获取基础数据信息
	 * 
	 * @return List<HashMap<String,String>>
	 * @throws Exception
	 */
	public List<String[]> getJcsjList(GyglGywhForm myForm, User user,
			HttpServletRequest request) throws Exception {

		List<String[]> jcsjList = new ArrayList<String[]>();

		String mklx = myForm.getMklx();

		getJcColList(myForm);

		// 校区
		if ("xq".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=xq";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getXqsjList(myForm, request);
			// 园区
		} else if ("yq".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=yq";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getYqsjList(myForm, request);
			// 楼栋
		} else if ("ld".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=ld";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getLdsjList(myForm, user, request);
			// 寝室
		} else if ("qs".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=qs";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getQssjList(myForm, user, request);
			// 床位
		} else if ("cw".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=cw";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getCwsjList(myForm, request);
		} else if ("ldwh".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_ldwh.do";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getLdsjList(myForm, user, request);
		} else if ("qswh".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_qswh.do";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getQssjList(myForm, user, request);
		}
		return jcsjList;
	}
	
	/**
	 * 获取业务数据信息
	 * @return List<HashMap<String,String>>
	 * @throws Exception 
	 */
	public List<String[]>getYwsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{
		
		List<String[]>ywsjList=new ArrayList<String[]>();
		
		String mklx=myForm.getMklx();
		
		getYwColList(myForm);
		//寝室分配
		if("qsfp".equalsIgnoreCase(mklx)){
			String path = "gygl_gywh_ywsj.do&searchType=qsfp";
			myForm.getSearchModel().setPath(path);
			ywsjList=dao.getQsfpsjList(myForm,request);
		//床位分配
		}else if("cwfp".equalsIgnoreCase(mklx)){
			String path = "gygl_gywh_ywsj.do&searchType=cwfp";
			myForm.getSearchModel().setPath(path);
			ywsjList=dao.getCwfpsjList(myForm,request);
		//历史信息	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			String path = "gygl_gywh_ywsj.do&searchType=lsxx";
			myForm.getSearchModel().setPath(path);
			ywsjList=dao.getLsxxsjList(myForm,request);
		//寝室	
		}
		return ywsjList;
	}
	
	/**
	 * 获取基础数据输出字段
	 * @return String[]
	 * @throws Exception 
	 */
	public void getJcColList(GyglGywhForm myForm){
		
		String[] colList=null;
		
		String mklx=myForm.getMklx();
		String czxq=myForm.getCzxq();
		String czyq=myForm.getCzyq();
		//校区
		if("xq".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","dm","xqmc","sjly"};
		//园区
		}else if("yq".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","yqdm","yqmc","xqmc","sjly"};
		//楼栋	
		}else if("ld".equalsIgnoreCase(mklx) || "ldwh".equalsIgnoreCase(mklx) ){
			
			//同时存在校区和园区
			if("是".equalsIgnoreCase(czxq) && "是".equalsIgnoreCase(czyq)){
				colList=new String[]{"pkValue","disabled","xqmc","yqmc","lddm","ldmc","xbxd","cs","zrs"};
			//存在校区,不存在园区
			}else if("是".equalsIgnoreCase(czxq) && "否".equalsIgnoreCase(czyq)){
				colList=new String[]{"pkValue","disabled","xqmc","lddm","ldmc","xbxd","cs","zrs"};
			//存在园区,不存在校区
			}else if("否".equalsIgnoreCase(czxq) && "是".equalsIgnoreCase(czyq)){
				colList=new String[]{"pkValue","disabled","yqmc","lddm","ldmc","xbxd","cs","zrs"};
			//都不存在
			}else{
				colList=new String[]{"pkValue","disabled","lddm","ldmc","xbxd","cs","zrs"};
			}
			
		//寝室	
		}else if("qs".equalsIgnoreCase(mklx)  || "qswh".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","ldmc","cs","qsh","cws","kcws","rzrs","xb","kfhz","sfbz","fpbj","rzqk","xbxd"};
		//床位
		}else if("cw".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","lddm","ldmc","cs","qsh","cwh","cwbj","sjly"};
		}
		myForm.setColList(colList);
	}
	
	/**
	 * 获取业务数据输出字段
	 * @return String[]
	 * @throws Exception 
	 */
	public void getYwColList(GyglGywhForm myForm){
		
		String[] colList=null;
		
		String mklx=myForm.getMklx();
		
		String fpdx=myForm.getFpdx();
		//校区
		if("qsfp".equalsIgnoreCase(mklx)){
			
			if("xy".equalsIgnoreCase(fpdx) || "bj".equalsIgnoreCase(fpdx)){
				colList=new String[]{"pkValue","ldmc","cs","qsh","fpdx","bmmc","sjly"};
			}else if("njxy".equalsIgnoreCase(fpdx) || "njzy".equalsIgnoreCase(fpdx)){
				colList=new String[]{"pkValue","ldmc","cs","qsh","fpdx","nj","bmmc","sjly"};
			}
			
		//床位分配
		}else if("cwfp".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","xh","xm","bjmc","ldmc","qsh","cs","cwh","sjly"};
		//学生住宿历史信息	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","xh","xm","bjmc","ldmc","qsh","cwh","sjly"};
		}
		myForm.setColList(colList);
	}
	
	/**
	 * 获取基础数据表头信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getJcsjTopTr(GyglGywhForm myForm,
			MessageResources message) {

		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;
		String czxq = myForm.getCzxq();
		String czyq = myForm.getCzyq();
		String mklx = myForm.getMklx();

		String xqdm = message.getMessage("lable.xiaoqu")+"代码";
		String xqmc = message.getMessage("lable.xiaoqu")+"名称";
		String yqdm = message.getMessage("lable.yuanqu")+"代码";
		String yqmc = message.getMessage("lable.yuanqu")+"名称";
		String lddm = message.getMessage("lable.ld")+"代码";
		String ldmc = message.getMessage("lable.ld")+"名称";
		String cs = message.getMessage("lable.cs");
		String qs = message.getMessage("lable.qs")+"号";
	
		// 校区
		if ("xq".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { "主键", xqdm, xqmc, "数据来源" };
			colListEN = myForm.getColList();
			// 园区
		} else if ("yq".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { "主键", yqdm, yqmc, xqdm, "数据来源" };
			colListEN = myForm.getColList();
			// 楼栋
		} else if ("ld".equalsIgnoreCase(mklx) || "ldwh".equalsIgnoreCase(mklx)) {
			colListEN = myForm.getColList();
			
//			同时存在校区和园区
			if("是".equalsIgnoreCase(czxq) && "是".equalsIgnoreCase(czyq)){
				colListCN = new String[] { "主键", xqmc,yqmc,lddm, ldmc,"性别", cs,"寝室数" };
			//存在校区,不存在园区
			}else if("是".equalsIgnoreCase(czxq) && "否".equalsIgnoreCase(czyq)){
				colListCN = new String[] { "主键", xqmc,lddm, ldmc,"性别", cs,"寝室数"  };
			//存在园区,不存在校区
			}else if("否".equalsIgnoreCase(czxq) && "是".equalsIgnoreCase(czyq)){
				colListCN = new String[] { "主键",yqmc,lddm, ldmc,"性别", cs,"寝室数"  };
			//都不存在
			}else{
				colListCN = new String[] { "主键",lddm, ldmc,"性别", cs, "寝室数"  };
			}
			
			// 寝室
		} else if ("qs".equalsIgnoreCase(mklx) || "qswh".equalsIgnoreCase(mklx)) {
			colListEN = myForm.getColList();
			colListCN = new String[] { "主键", ldmc, cs, qs, "床位数","空床位数","已住人数","性别",
					"可否混住", "收费标准","分配标记", "寝室入住情况","楼栋性别限制"};
		} else if ("cw".equalsIgnoreCase(mklx)) {
			colListEN = myForm.getColList();
			colListCN = new String[] { "主键", lddm, ldmc, cs, qs, "床位号", "床位标记",
					"数据来源" };
		} else if ("zsxx".equalsIgnoreCase(mklx)) {
			colListEN = new String[] { "入住时间","异动时间","异动前床位","异动后床位"};
			colListCN = new String[] { "入住时间","异动时间","异动前床位","异动后床位"};
		}

		return dao.arrayToList(colListEN, colListCN);

	}
	
	/**
	 * 获取业务数据表头信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>> getYwsjTopTr(GyglGywhForm myForm){

		DAO dao = DAO.getInstance();
	
		String[] colListCN = null;
		String[] colListEN = null;
		
		String mklx=myForm.getMklx();
		
		String fpdx=myForm.getFpdx();
		
		//寝室分配
		if("qsfp".equalsIgnoreCase(mklx)){
			
			if("xy".equalsIgnoreCase(fpdx) || "bj".equalsIgnoreCase(fpdx)){
				colListCN=new String[]{"主键","楼栋名称","楼层","寝室号","分配对象","部门名称","数据来源"};
				colListEN=myForm.getColList();
			}else if("njxy".equalsIgnoreCase(fpdx) || "njzy".equalsIgnoreCase(fpdx)){
				colListCN=new String[]{"主键","楼栋名称","楼层","寝室号","分配对象","年级","部门名称","数据来源"};
				colListEN=myForm.getColList();
			}
			
		//床位分配
		}else if("cwfp".equalsIgnoreCase(mklx)){
			colListCN=new String[]{"主键","学号","姓名","班级","楼栋名称","寝室号","层数","床位号","数据来源"};
			colListEN=myForm.getColList();
		//学生住宿历史信息	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			colListCN=new String[]{"主键","学号","姓名","班级","楼栋名称","寝室号","床位号","数据来源"};
			colListEN=myForm.getColList();
		}
		return dao.arrayToList(colListEN, colListCN);
		
	}
	
	/**
	 * 删除基础数据
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delJcsj(GyglGywhForm myForm,
			HttpServletRequest request) throws Exception{
		String mklx=myForm.getMklx();
		
		DelDataDetect delData=new DelDataDetect();
		DelDetectModel model=new DelDetectModel();
		
		if("xq".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=xq");
			myForm.setTableName("dm_zju_xq");
			myForm.setQuery(" where dm= ");
		//园区
		}else if("yq".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=yq");
			myForm.setTableName("yqdmb");
			myForm.setQuery(" where yqdm= ");
		//楼栋	
		}else if("ld".equalsIgnoreCase(mklx) || "ldwh".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=ld");
			myForm.setTableName("sslddmb");
			myForm.setQuery(" where lddm= ");
		//寝室	
		}else if("qs".equalsIgnoreCase(mklx) || "qswh".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=qs");
			myForm.setTableName("ssxxb");
			myForm.setQuery(" where lddm||'!!@!!'||cs||'!!@!!'||qsh= ");
		}else if("cw".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=cw");
			myForm.setTableName("xg_gygl_qtcwxxb");
			myForm.setQuery(" where lddm||cs||qsh||cwh= ");
		}
		model.setPkValue(myForm.getCheckVal());
		delData.dataDetect(model,request);
		if(model.isBool()){
			
			dao.delJcsj(myForm);
		}else{
			
			request.setAttribute("delMessage", model.getMessage());
		}
		return model.isBool();
	}
	
	/**
	 * 删除业务数据
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delYwsj(GyglGywhForm myForm, HttpServletRequest request)
			throws Exception {
		String mklx = myForm.getMklx();

		DelDataDetect delData = new DelDataDetect();
		DelDetectModel model = new DelDetectModel();
		// 寝室分配
		if ("qsfp".equalsIgnoreCase(mklx)) {
			model.setPath("gygl_gywh_ywsj.do&searchType=qsfp");
			myForm.setTableName("xg_gygl_qsfpb");
			myForm.setQuery(" where lddm||cs||qsh= ");
			// 床位分配
		} else if ("cwfp".equalsIgnoreCase(mklx)) {
			model.setPath("gygl_gywh_jcsj.do&searchType=cw");
			myForm.setTableName("xszsxxb");
			myForm.setQuery(" where xh= ");
			// 历史信息
		} else if ("lsxx".equalsIgnoreCase(mklx)) {
			myForm.setTableName("xszslsxxb");
			myForm.setQuery(" where guid= ");
		}
		model.setPkValue(myForm.getCheckVal());
		delData.dataDetect(model, request);
		if (model.isBool()) {

			dao.delJcsj(myForm);
			
		} else {

			request.setAttribute("delMessage", model.getMessage());
		}
		return model.isBool();
	}
	
	/**
	 * 获取基础数据页签信息
	 * @param jbszModel
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getJcsjCard(GyglJbszForm jbszModel){
		List<HashMap<String,String>>cardList=new ArrayList<HashMap<String,String>>();
		String czxq=jbszModel.getCzxq();
		String czyq=jbszModel.getCzyq();
		HashMap<String,String>hashMap=new HashMap<String,String>();
		if("是".equalsIgnoreCase(czxq)){
			hashMap.put("dm", "xq");
			hashMap.put("mcxs", "lable");
			hashMap.put("mc", "lable.xiaoqu");
			cardList.add(hashMap);
		}
		
		if("是".equalsIgnoreCase(czyq)){
			hashMap=new HashMap<String,String>();
			hashMap.put("dm", "yq");
			hashMap.put("mcxs", "lable");
			hashMap.put("mc", "lable.yuanqu");
			cardList.add(hashMap);
		}
		
//		hashMap=new HashMap<String,String>();
//		hashMap.put("dm", "ld");
//		hashMap.put("mcxs", "lable");
//		hashMap.put("mc", "lable.ld");
//		cardList.add(hashMap);
//		
//		hashMap=new HashMap<String,String>();
//		hashMap.put("dm", "qs");
//		hashMap.put("mcxs", "lable");
//		hashMap.put("mc", "lable.qs");
//		cardList.add(hashMap);
//		
//		hashMap=new HashMap<String,String>();
//		hashMap.put("dm", "cw");
//		hashMap.put("mcxs", "xsmc");
//		hashMap.put("mc", "其他床位信息（非住人床位）");
//		cardList.add(hashMap);
		return cardList;
	}
	
	
	/**
	 * 获取业务数据页签信息
	 * @param jbszModel
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getYwsjCard(){
		List<HashMap<String,String>>cardList=new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String>hashMap=new HashMap<String,String>();
		//寝室分配
		hashMap.put("dm", "qsfp");
		hashMap.put("mcxs", "xsmc");
		hashMap.put("mc", "寝室分配");
		cardList.add(hashMap);
		
		//床位分配
		hashMap=new HashMap<String,String>();
		hashMap.put("dm", "cwfp");
		hashMap.put("mcxs", "xsmc");
		hashMap.put("mc", "床位分配");
		cardList.add(hashMap);
		
		//历史信息
		hashMap=new HashMap<String,String>();
		hashMap.put("dm", "lsxx");
		hashMap.put("mcxs", "xsmc");
		hashMap.put("mc", "历史信息");
		cardList.add(hashMap);
		return cardList;
	}
	
	/**
	 * 基础数据模块信息初始化
	 * @param myForm
	 */
	public void JcmkxxInit(GyglGywhForm myForm){
		String mklx=myForm.getMklx();
		if("xq".equalsIgnoreCase(mklx)){
			myForm.setTableName("dm_zju_xq");
			myForm.setRealTable("dm_zju_xq");
		//园区
		}else if("yq".equalsIgnoreCase(mklx)){
			myForm.setTableName("yqdmb");
			myForm.setRealTable("yqdmb");
		//楼栋	
		}else if("ld".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_sslddm");
			myForm.setRealTable("sslddmb");
		//寝室	
		}else if("qs".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_ssxx");
			myForm.setRealTable("ssxxb");
		//床位
		}else if("cw".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_gygl_qtcwxxb");
			myForm.setRealTable("xg_gygl_qtcwxxb");
		}else if("qswh".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_ssxx");
			myForm.setRealTable("ssxxb");
		}else if("ldwh".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_sslddm");
			myForm.setRealTable("sslddmb");
		}
	}
	
	/**
	 * 务数据模块信息初始化
	 * @param myForm
	 */
	public void YwmkxxInit(GyglGywhForm myForm){
		String mklx=myForm.getMklx();
		//寝室分配
		if("qsfp".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_gygl_qsfpb");
			myForm.setRealTable("xg_gygl_qsfpb");
		//床位分配
		}else if("cwfp".equalsIgnoreCase(mklx)){
			myForm.setTableName("xszsxxb");
			myForm.setRealTable("xszsxxb");
		//历史信息	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			myForm.setTableName("xszslsxxb");
			myForm.setRealTable("xszslsxxb");
		}
	}
	
	/**
	 * 获取单条校区信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getXqxx(GyglGywhForm myForm){
		return dao.getXqxx(myForm);
	}
	
	/**
	 * 获取单条园区信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getYqxx(GyglGywhForm myForm){
		return dao.getYqxx(myForm);
	}	

	/**
	 * 获取楼栋信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getLdxx(GyglGywhForm myForm){
		return dao.getLdxx(myForm);
	}
	
	/**
	 * 获取寝室详细信息
	 * @param myForm
	 * @return  HashMap<String,String>
	 * @author qlj
	 */
	public HashMap<String,String>getQsxx(GyglGywhForm myForm){
		//寝室详细信息
		HashMap<String,String>qsxxMap= dao.getQsxx(myForm);
		//已经入住学生数量
		qsxxMap.putAll(dao.getYzrCws(myForm));
		return qsxxMap;
	}
	
	/**
	 * 获取寝室详细信息
	 * @param myForm
	 * @return  HashMap<String,String>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getCwxx(GyglGywhForm myForm){
		return dao.getCwxx(myForm);
	}
	
	/**
	 * 获取楼栋信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getLdList(GyglGywhForm myForm){
		return dao.getLdList(myForm);
	}
	
	/**
	 * 获取园区信息(DWR 楼栋维护)
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getYqxxList(){
		return dao.getYqList();
	}
	
	
	/**
	 * 获取园区信息(DWR 楼栋维护)
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getYqList(String xqdm){
		return dao.getYqxxList(xqdm);
	}
	
	public boolean  zdscQs(GyglGywhForm myForm,User user) throws Exception{
		
		List<HashMap<String,String>>ssxxList=new ArrayList<HashMap<String,String>>();
		//寝室号位数
		String bls=myForm.getBls();
		
		String wsxz=myForm.getWsxz();
		//寝室数量
		String[]qsslArr=myForm.getQsslArr();
		//床位数
		String[]cwsArr=myForm.getCwsArr();
		//收费标准
		String[]sfbzArr=myForm.getSfbzArr();
		//分配标记
		String[]fpbjArr=myForm.getFpbjArr();
		//层数
		String[]csArr=myForm.getCsArr();  
		//性别
		String[]xbArr=myForm.getXbArr();
		//可否混住
		String[]kfhzArr=myForm.getKfhzArr();
		//编号规则
		String bhgz=myForm.getBhgz();
		
		//楼栋信息
		qsxxList=dao.getQsList(myForm);
		for(int i=0;i<qsslArr.length;i++){
			HashMap<String,String>ssxxMap=new HashMap<String,String>();
			if(!Base.isNull(qsslArr[i]) 
					&& !"".equalsIgnoreCase(qsslArr[i])){
				ssxxMap.put("bls", bls);
				ssxxMap.put("qssl", qsslArr[i]);
				ssxxMap.put("cws", cwsArr[i]);
				ssxxMap.put("sfbz", sfbzArr[i]);
				ssxxMap.put("fpbj", fpbjArr[i]);
				ssxxMap.put("cs", csArr[i]);
				ssxxMap.put("xb", xbArr[i]);
				ssxxMap.put("kfhz", kfhzArr[i]);
				ssxxMap.put("bhgz", bhgz);
				ssxxMap.put("wsxz", wsxz);
				ssxxList.add(ssxxMap);
			}
			
		}
		
		//自动生成完的寝室信息
		boolean blog= saveZdscqs(zdsc(ssxxList,myForm),myForm);
		GyglGywhModel model=new GyglGywhModel();
		model.setSfplcz(true);
		creatGyglCwxx(model,user);
		
		return blog;
	}
	
	/**
	 * 创建床位信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void creatGyglCwxx(GyglGywhModel model, User user) throws Exception {

		// 宿舍信息表
		String tableName = "ssxxb";
		String[] colList = new String[] { "lddm", "cs", "qsh", "cws" };
		
		StringBuilder query =new StringBuilder();
		
		ArrayList<String>inputVal=new ArrayList<String>();
		
		if(!model.isSfplcz()){
			query.append(" where lddm=? ");
			query.append(" and cs=? ");
			query.append(" and qsh=? ");
			inputVal.add(model.getLddmQuery());
			inputVal.add(model.getCsQuery());
			inputVal.add(model.getQshQuery());
		}
		
		query.append(" order by lddm,cs,qsh ");
		
		List<HashMap<String, String>> qsList = getRsList(tableName, query.toString(),
				inputVal.toArray(new String[]{}), colList, "");

		// 批量字段
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "cwh" };

		// 楼栋
		ArrayList<String> ldList = new ArrayList<String>();
		// 层数
		ArrayList<String> csList = new ArrayList<String>();
		// 寝室号
		ArrayList<String> qshList = new ArrayList<String>();
		// 床位号
		ArrayList<String> cwhList = new ArrayList<String>();

		if (qsList != null && qsList.size() > 0) {

			for (int i = 0; i < qsList.size(); i++) {

				HashMap<String, String> map = qsList.get(i);
				// 楼栋代码
				String lddm = map.get("lddm");
				// 楼栋代码
				String cs = map.get("cs");
				// 楼栋代码
				String qsh = map.get("qsh");
				// 可住人床位数
				String cws = map.get("cws");

				if (!Base.isNull(cws)) {
					for (int j = 0; j < Integer.parseInt(cws); j++) {
						ldList.add(lddm);
						csList.add(cs);
						qshList.add(qsh);
						cwhList.add(String.valueOf(j+1));
					}
				}
			}

			model.setLddm(ldList.toArray(new String[] {}));
			model.setCs(csList.toArray(new String[] {}));
			model.setQsh(qshList.toArray(new String[] {}));
			model.setCwh(cwhList.toArray(new String[] {}));

			tableName = "xg_gygl_cwxxb";
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			if(!model.isSfplcz()){
				saveForm.setPk(" lddm||cs||qsh ");
				HashMap<String,String>qsMap=qsList.get(0);
				saveForm.setPkValue(new String[] {qsMap.get("lddm")+qsMap.get("cs")+
						qsMap.get("qsh")});
			}else{
				saveForm.setPk("1");
				saveForm.setPkValue(new String[] { "1" });
			}
			saveForm.setArrzd(arrzd);

			saveInfoToDb(saveForm, model, user);
		}
	}
	
	/**
	 * 保存行李床位信息
	 * @param myForm
	 * @param user
	 * @return boolean
	 * @throws Exception
	 * @author qlj
	 */
	public boolean creatGyglQtcwxx(GyglGywhForm myForm, User user) throws Exception{
		
		//其它宿舍信息表
		String tableName = "xg_gygl_qtcwxxb";

		GyglGywhModel model = new GyglGywhModel();
		// 床位号数组(前台获取)
		String[] cwh = myForm.getCwhArr();
		if (cwh != null && cwh.length > 0) {
			// 床位标记数组(前台获取)
			String[] cwlxArr = myForm.getCwbjArr();
			// 数组长度
			int len = cwh.length;
			// 主键
			String[] pkValue = new String[len];
			// 楼栋代码
			String lddm = myForm.getLddm();
			// 层数
			String cs = myForm.getCs();
			// 寝室号
			String qsh = myForm.getQsh();

			int n = 0;
			for (int i = 0; i < len; i++) {
				// 拼主键
				pkValue[i] = lddm + cs + qsh + cwh[i];
				if (!"未入住".equalsIgnoreCase(cwlxArr[i])) {
					n++;
				}
			}

			// 需要批量保存的字段
			String[] arrzd = { "lddm", "cs", "qsh", "cwh", "cwbj", "sjly" };
			String[] lddmArr = new String[n];
			String[] qshArr = new String[n];
			String[] csArr = new String[n];
			String[] cwbjArr = new String[n];
			String[] sjlyArr = new String[n];
			String[] cwhArr = new String[n];

			int m = 0;
			for (int i = 0; i < len; i++) {
				if (!"未入住".equalsIgnoreCase(cwlxArr[i])) {
					lddmArr[m] = lddm;
					csArr[m] = cs;
					qshArr[m] = qsh;
					sjlyArr[m] = "手动";
					cwbjArr[m] = cwlxArr[i];
					cwhArr[m] = cwh[i];
					m++;
				}
			}

			SaveForm saveForm = new SaveForm();
			model.setLddm(lddmArr);
			model.setCs(csArr);
			model.setQsh(qshArr);
			model.setCwh(cwhArr);
			model.setSjly(sjlyArr);
			model.setCwbj(cwbjArr);

			saveForm.setTableName(tableName);
			saveForm.setPk(" lddm||cs||qsh||cwh ");
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);
			//通用保存方法
			return saveInfoToDb(saveForm, model, user);
			
		} else {

			return true;
		}
	}
	
	/**
	 * 保存自动生成的寝室
	 * @param scqsxxList
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 * @author qlj
	 */
	public boolean saveZdscqs(List<HashMap<String, String>>scqsxxList
			,GyglGywhForm myForm) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
		GyglGywhModel model=new GyglGywhModel();
		// 进行数据操作 的表名
		String realTable = "ssxxb";
		
		String[] arrzd = new String[] { "lddm", "qsh","cs","cws", "fpbj", "sfbz","xb","kfhz","sjly"};
		
		// 当前页的学号数组(先删后增将该页信息删除时)
		int len=scqsxxList.size();
		
		String[] pkValue=new String[len];
		String[] lddm = new String[len];
		String[] qsh = new String[len];
		String[] cs = new String[len];
		String[] cws = new String[len];
		String[] fpbj = new String[len];
		String[] sfbz = new String[len];
		String[] xb = new String[len];
		String[] kfhz = new String[len];
		String[] sjly = new String[len];
		for (int i = 0; i < len; i++) {
			HashMap<String,String>scqsxxMap=scqsxxList.get(i);
			pkValue[i] = "2";
			sjly[i] = "自动";
			lddm[i]=myForm.getLddm();
			qsh[i]=scqsxxMap.get("qsh");
			cs[i]=scqsxxMap.get("cs");
			cws[i]=scqsxxMap.get("cws");
			fpbj[i]=scqsxxMap.get("fpbj");
			sfbz[i]=scqsxxMap.get("sfbz");
			xb[i]=scqsxxMap.get("xb");
			kfhz[i]=scqsxxMap.get("kfhz");
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk("1");
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
		model.setSjly(sjly);
		model.setLddm(lddm);
		model.setQsh(qsh);
		model.setCs(cs);
		model.setCws(cws);
		model.setFpbj(fpbj);
		model.setSfbz(sfbz);
		model.setXb(xb);
		model.setKfhz(kfhz);
			
		myForm.setQss(String.valueOf(len));
		return  ghxyNjzrwhService.saveTyxx(saveForm, model);
	}
	
	
	/**
	 * 获取楼层信息列表
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>> getLcxxList(GyglGywhForm myForm) {
		// 获取层数
		String cs = myForm.getCs();
		List<HashMap<String, String>> lcxxList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < qsxxList.size(); i++) {
			HashMap<String, String> qsxxMap = qsxxList.get(i);

			// 获取该楼层的详细信息
			if (cs.equalsIgnoreCase(qsxxMap.get("cs"))) {
				HashMap<String, String> lcxxMap = new HashMap<String, String>();
				lcxxMap.put("lddm", qsxxMap.get("lddm"));
				lcxxMap.put("cs", qsxxMap.get("cs"));
				lcxxMap.put("qsh", qsxxMap.get("qsh"));
				lcxxList.add(lcxxMap);
			}

		}
		
		return lcxxList;
	}
	
	/**
	 * 自动生成寝室
	 * @param ssxxList
	 * @param myForm
	 * @return List<HashMap<String, String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>> zdsc(List<HashMap<String, String>> ssxxList,
			GyglGywhForm myForm) {
		// 自动生成
		List<HashMap<String, String>> zdscqsList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < ssxxList.size(); i++) {

			HashMap<String, String> ssxxMap = ssxxList.get(i);
			//寝室数量
			int qssl = Integer.parseInt(ssxxMap.get("qssl"));
			int m = 1;
			//记数(寝室数量)
			int n = 1;
			while(qssl>=n){
				HashMap<String,String>zdscqsMap=new HashMap<String,String>();
				ssxxMap.put("szbh", String.valueOf((m)));
				
				if("blxs".equalsIgnoreCase(ssxxMap.get("bhgz"))){
					ssxxMap = scSsbh(ssxxMap);
				}else if("gdws".equalsIgnoreCase(ssxxMap.get("bhgz"))){
					ssxxMap =scKdwQsh(ssxxMap);
				}
				myForm.setCs(ssxxMap.get("cs"));
				List<HashMap<String, String>> lcxxList = getLcxxList(myForm);
				boolean blog = true;
				for (int j = 0; j < lcxxList.size(); j++) {
					HashMap<String, String> lcxxMap = lcxxList.get(j);
					if (ssxxMap.get("qsh").equalsIgnoreCase(lcxxMap.get("qsh"))) {
						m++;
						blog = false;
						break;
					}
				}
				if (blog) {
					m++;
					n++;
					zdscqsMap.put("qsh", ssxxMap.get("qsh"));
					zdscqsMap.put("lddm", myForm.getLddm());
					zdscqsMap.put("cws", ssxxMap.get("cws"));
					zdscqsMap.put("fpbj", ssxxMap.get("fpbj"));
					zdscqsMap.put("sfbz", ssxxMap.get("sfbz"));
					zdscqsMap.put("cs", myForm.getCs());
					zdscqsMap.put("kfhz", ssxxMap.get("kfhz"));
					zdscqsMap.put("xb",ssxxMap.get("xb"));
					zdscqsList.add(zdscqsMap);
				}
				
			}
		}

		return zdscqsList;
	}
	
	/**
	 * 补0型寝室号生成
	 * @param ssxxMap
	 * @return
	 */
	public HashMap<String,String> scSsbh(HashMap<String,String>ssxxMap){
		//补零数
		String bls=ssxxMap.get("bls");
		//层数
		String cs=ssxxMap.get("cs");
		String szbh=ssxxMap.get("szbh");
		String ling="0";
		if("2".equalsIgnoreCase(bls)){
			ling="00";
		}else if("3".equalsIgnoreCase(bls)){
			ling="000";
		}
		ssxxMap.put("qsh", cs+ling+szbh);
		return ssxxMap;
	}
	
	/**
	 * 固定位数型室号生成
	 * @param ssxxMap
	 * @return
	 */
	public HashMap<String,String> scKdwQsh(HashMap<String,String>ssxxMap){
		//显示位数
		String wsxz=ssxxMap.get("wsxz");
		//层数
		String cs=ssxxMap.get("cs");
		String szbh=ssxxMap.get("szbh");
		//编号长度
		int len=szbh.length();
		//层数只被看成一位
		int xsws=Integer.parseInt(wsxz)-1;
		xsws=xsws-len;
		String qsh="";
		qsh+=cs;
		for(int i=0;i<xsws;i++){
			qsh+="0";
		}
		qsh+=szbh;
		
		
		ssxxMap.put("qsh", qsh);
		return ssxxMap;
	}
	
	/**
	 * 增加校区
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean addXqxx(GyglGywhForm myForm) throws Exception{

		return dao.addXqxx(myForm);
	}
	
	/**
	 * 修改校区
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean modiXqxx(GyglGywhForm myForm) throws Exception{

		return dao.modiXqxx(myForm);
	}
	
	/**
	 * 增加园区信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean addYqxx(GyglGywhForm myForm) throws Exception{

		return dao.addYqxx(myForm);
	}
	
	/**
	 * 增加楼栋信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean addLdxx(GyglGywhForm myForm) throws Exception{

		return dao.addLdxx(myForm);
	}
	
	/**
	 * 增加寝室信息(床位信息、其它床位信息)
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean addQsxx(GyglGywhForm myForm,User user) throws Exception{
		
		boolean blog=dao.addQsxx(myForm);	
		//创建寝室成功后执行
		if(blog){
			GyglGywhModel model=new GyglGywhModel();
			//设置床位生成时是否为批量操作
			model.setSfplcz(false);
			model.setLddmQuery(myForm.getLddm());
			model.setCsQuery(myForm.getCs());
			model.setQshQuery(myForm.getQsh());
			//创建床位信息
			creatGyglCwxx(model,user);
			//创建其他床位信息
			creatGyglQtcwxx(myForm,user);
		}
		return blog;
	}
	
	/**
	 * 修改园区信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean modiYqxx(GyglGywhForm myForm) throws Exception{

		return dao.modiYqxx(myForm);
	}
	
	/**
	 * 修改楼栋信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean modiLdxx(GyglGywhForm myForm) throws Exception{
		if(!"混合".equalsIgnoreCase(myForm.getXbxd())){
			updateQsxb(myForm);
		}
		return dao.modiLdxx(myForm);
	}
	
	public boolean modiQsxx(GyglGywhForm myForm,User user) throws Exception{

		boolean blog= dao.modiQsxx(myForm);
		//创建寝室成功后执行
		if(blog){
			GyglGywhModel model=new GyglGywhModel();
			//设置床位生成时是否为批量操作
			model.setSfplcz(false);
			model.setLddmQuery(myForm.getLddm());
			model.setCsQuery(myForm.getCs());
			model.setQshQuery(myForm.getQsh());
			//创建床位信息
			creatGyglCwxx(model,user);
			//创建其他床位信息
			creatGyglQtcwxx(myForm,user);
		}
		return blog;
	}
	
	public List<HashMap<String,String>>getXqList(){
		return dao.getXqList();
	}
	
	public List<HashMap<String,String>>getQsfpList(GyglGywhForm myForm){

		return dao.getLdrzqk(myForm);
	}
	
	/**
	 * 获取楼栋楼层统计信息
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdcsList(GyglGywhForm myForm){
		
		HashMap<String,String>ldxx=dao.getLdxx(myForm);
		//入住情况
		List<HashMap<String,String>>lcrzqk=dao.getLcrzqk(myForm);
		//分配情况
		List<HashMap<String,String>>lcfpqk=dao.getLcfpqk(myForm);
		//寝室数量
		List<HashMap<String,String>>lcqss=dao.getLcqss(myForm);
		int cs=Integer.parseInt(ldxx.get("cs"));
		List<HashMap<String,String>>lcxxList=new ArrayList<HashMap<String,String>>();
		for(int i=1;i<=cs;i++){
			HashMap<String,String>lcxxMap=new HashMap<String,String>();
			String lc=String.valueOf(i);
			lcxxMap.put("cs", lc);
			//入住情况
			lcxxMap.put("rzrs", "0");
			for(int j=0;j<lcrzqk.size();j++){
				HashMap<String,String>ldrzqkMap=lcrzqk.get(j);
				if(lc.equalsIgnoreCase(ldrzqkMap.get("cs"))){
					lcxxMap.put("rzrs", ldrzqkMap.get("rzrs"));
					break;
				}
				
			}
			//分配情况
			lcxxMap.put("yfps","0");
			for(int k=0;k<lcfpqk.size();k++){
				HashMap<String,String>lcfpqkMap=lcfpqk.get(k);
				if(lc.equalsIgnoreCase(lcfpqkMap.get("cs"))){
					//已经分配寝室数量
					lcxxMap.put("yfps", lcfpqkMap.get("yfps"));
					break;
				}
				
			}
			//寝室数量
			lcxxMap.put("qss", "0");
			for(int l=0;l<lcqss.size();l++){
				HashMap<String,String>lcqssMap=lcqss.get(l);
				if(lc.equalsIgnoreCase(lcqssMap.get("cs"))){
					//已经分配寝室数量
					lcxxMap.put("qss", lcqssMap.get("qss"));
					break;
				}
			}
			lcxxList.add(lcxxMap);
		}
		return lcxxList;
	}
	
	/**
	 * 获取空闲宿舍统计情况
	 * @return
	 */
	public List<HashMap<String,String>>getKxssTjList(GyglGywhForm myForm){
		GyglCwglDAO cwglDao=new GyglCwglDAO();
		//HashMap<String,String>ldxx=dao.getLdxx(myForm);
		//分配情况
		List<HashMap<String,String>>wfpcws=dao.getWfpcws(myForm);
		//寝室数量
		List<HashMap<String,String>>kxqss=dao.getKxqss(myForm);
		
		List<HashMap<String,String>>lcxxList=new ArrayList<HashMap<String,String>>();
		
		GyglCwglForm cwglForm=new GyglCwglForm();
		cwglForm.setLddm(myForm.getLddm());
		List<HashMap<String,String>>csList=cwglDao.getLdlcxx(cwglForm);
		for(int i=0;i<csList.size();i++){
			HashMap<String,String>lcxxMap=new HashMap<String,String>();
			HashMap<String,String>ldlcxxMap=csList.get(i);
			
			lcxxMap.put("cs", ldlcxxMap.get("cs"));
			//分配情况
			lcxxMap.put("wrzcw","0");
			for(int k=0;k<wfpcws.size();k++){
				HashMap<String,String>wfpcwsMap=wfpcws.get(k);
				if(ldlcxxMap.get("cs").equalsIgnoreCase(wfpcwsMap.get("cs"))){
					//已经分配寝室数量
					lcxxMap.put("wrzcw", wfpcwsMap.get("wrzcw"));
					break;
				}
				
			}
			//寝室数量
			lcxxMap.put("qss", "0");
			for(int l=0;l<kxqss.size();l++){
				HashMap<String,String>kxqssMap=kxqss.get(l);
				if(ldlcxxMap.get("cs").equalsIgnoreCase(kxqssMap.get("cs"))){
					//已经分配寝室数量
					lcxxMap.put("qss", kxqssMap.get("kxqss"));
					break;
				}
			}
			lcxxList.add(lcxxMap);
		}
		return lcxxList;
	}
	
	/**
	 * 获取楼层列表
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getCwsList(GyglGywhForm myForm){
		
		int cws=Integer.parseInt(myForm.getCws());
		
		List<HashMap<String,String>>cwsList=new ArrayList<HashMap<String,String>>();
		
		int cwgs=1;
		
		if(!Base.isNull(myForm.getCwgs())){
			 cwgs = Integer.parseInt(myForm.getCwgs());
		}
		
		for(int i=cwgs;i<=cws;i++){
			HashMap<String,String>cwsMap=new HashMap<String,String>();
			cwsMap.put("dm", String.valueOf(i));
			cwsMap.put("mc", String.valueOf(i));
			cwsList.add(cwsMap);
		}
		
		myForm.setCws(null);
		
		return cwsList;
	}
	
	public List<String[]>getXszsxxInfo(GyglGywhForm myForm) throws Exception{
		return dao.getXszsxxInfo(myForm);
	}
	
	/**
	 * 根据楼栋性别修改情况修改寝室性别
	 * @param myForm
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean updateQsxb(GyglGywhForm myForm) throws Exception{
		
		return dao.updateQsxb(myForm);
	}
	
	/**
	 * 修改寝室信息
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateQsxx(GyglGywhForm myForm,User user) throws Exception{
		return dao.updateQsxx(myForm,user);
	}
	
	public void setQsBhgz(GyglGywhForm myForm){
		String bhgzxz=InitGygl.initGygl.getBhgz();
		String[]bhgzxzArr=bhgzxz.split("!!@@!!");
		if(bhgzxzArr!=null && bhgzxzArr.length>0){
			String bhgz=bhgzxzArr[0];
			if("blxs".equalsIgnoreCase(bhgz)){
				myForm.setBhgz(bhgz);
			}else if("gdws".equalsIgnoreCase(bhgz)){
				myForm.setBhgz(bhgz);
			}
		}
	}
}