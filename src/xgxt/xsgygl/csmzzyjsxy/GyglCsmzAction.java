package xgxt.xsgygl.csmzzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.FormModleCommon;
import xgxt.wjcf.csmz.xsDormDSCHModel;
import xgxt.xsgygl.dao.gyglDao;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政职业技术学院公寓管理Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-11</p>
 */
 
public class GyglCsmzAction extends DispatchAction {
	/**
	 * 辅导员下寝情况上报页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respose
	 * @return
	 */
   public ActionForward fdy_wstb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HttpSession session = request.getSession();	  
	   HashMap<String,String> map = new HashMap<String,String>();
	   String userName     = session.getAttribute("userName").toString();//职工号
	   String userNameReal = session.getAttribute("userNameReal").toString();//用户姓名
	   String isFdy        = session.getAttribute("isFdy").toString();//是否是辅导员标志	  
	   if(isFdy.equalsIgnoreCase("true")){//如果是辅导员用户默认用户登录名和用户姓名
		   map.put("zgh",userName);
		   map.put("xm",userNameReal);
	   }
	   map.put("isFdy",isFdy);	  
	   map.put("xn",Base.currXn);
	   map.put("xq",Base.currXq);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("rs",map);
	   request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
	   return mapping.findForward("gygl_fdywstb");
	   
   }
 
   /**
    * 辅导员下寝填报信息保存
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    * @throws Exception 
    */
   public ActionForward savefdy_tbxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//保存参数MODEL
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   boolean saveDone           = gcServices.saveFdytbxx(gcModel);	   
	   request.setAttribute("done",saveDone);	   
	   return mapping.findForward("savefdy_tbxx");
   }
   /**辅导员下寝信息添加*/
   public ActionForward fdy_xqxx_add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HashMap<String,String> map = new HashMap<String,String>();
	   map.put("xn",Base.currXn);
	   map.put("xq",Base.currXq);
	   request.setAttribute("rs",map);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
	   return mapping.findForward("fdy_xqxx_add");
   }
   /**辅导员下寝信息添加保存
 * @throws Exception */
   public ActionForward fdy_xqxx_addToSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose) throws Exception{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//保存参数MODEL
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   boolean saveDone           = gcServices.saveFdytbxx(gcModel);	   
	   request.setAttribute("done",saveDone);	   
	   return new ActionForward("/csmz_gygl.do?method=fdy_xqxx_add",false);
   }
   /**辅导员下寝信息修改*/
   public ActionForward fdy_xqxx_modi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HashMap<String,String> map = new HashMap<String,String>();
	   String pkValue = request.getParameter("pkValue");
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   map = gcServices.getFdyXqXxMap(pkValue);
	   request.setAttribute("rs",map);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
	   return mapping.findForward("fdy_xqxx_modi");
   }
   /**辅导员下寝信息修改保存
 * @throws InvocationTargetException 
 * @throws Exception */
   public ActionForward fdy_xqxx_modiToSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose) throws Exception, InvocationTargetException{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//保存参数MODEL
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   boolean saveDone           = gcServices.saveFdytbxx(gcModel);	   
	   request.setAttribute("done",saveDone);	
	   return new ActionForward("/csmz_gygl.do?method=fdy_xqxx_modi");
   }
   /**辅导员下寝信息查看*/
   public ActionForward fdy_xqxx_view(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HashMap<String,String> map = new HashMap<String,String>();
	   String pkValue = request.getParameter("pkValue");
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   map = gcServices.getFdyXqXxMap(pkValue);
	   request.setAttribute("rs",map);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
	   return mapping.findForward("fdy_xqxx_view");
   }
   /**辅导员下寝信息删除
  *@throws Exception */
   public ActionForward fdy_xqxx_del(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose) throws Exception{	  
	   String pkValue = request.getParameter("pkValue");
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   boolean del = gcServices.delFdyXqXx(pkValue);
	   request.setAttribute("sfDone",del);
	   return new ActionForward("/csmz_gygl.do?method=xqcx_Default&go=go");
   }
   /**
    * 学生正对辅导员下寝情况进行反馈
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */
   public ActionForward stu_fkxxcx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   HttpSession session  = request.getSession();
	   String userType      = session.getAttribute("userOnLine").toString();//用户类型 学生或老师
	   String userName      = session.getAttribute("userName").toString();//用户登陆名	  
	   GyglCsmzServices gcServices          = new GyglCsmzServices();
	   List<HashMap<String,String>> qurList = gcServices.fkxxcx(userType, userName);//返回查询结果
	   
	   request.setAttribute("userType",userType);
	   request.setAttribute("rs",qurList);	   
	   request.setAttribute("rsNum",qurList!=null?qurList.size():"0");//记录数
	   return mapping.findForward("stu_fkxx");
   }
   /**反馈信息默认查询页面信息*/
   public ActionForward stu_tofkxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   String pkValue              = DealString.toGBK(request.getParameter("pkValue"));
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   request.setAttribute("rs",gcServices.stu_fdyXqXxView(pkValue));//页面显示信息	   
	   request.setAttribute("pkValue",pkValue);
	   return mapping.findForward("stu_fkxxView");
   }
   /** 反馈信息保存*/
   public ActionForward saveStu_Fkxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   String pkValue              = DealString.toGBK(request.getParameter("pkValue"));
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//保存参数MODEL	 
	   BeanUtils.copyProperties(gcModel, gcForm);
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   boolean saveDone           = gcServices.saveStu_fkxx(pkValue, gcModel);
	   request.setAttribute("done",saveDone);
	   request.setAttribute("pkValue",pkValue);
	   return new ActionForward("/csmz_gygl.do?method=stu_tofkxx");
   }
   /**辅导员下寝信息查询
   * @throws InvocationTargetException 
   * @throws IllegalAccessException */
   public ActionForward xqcx_Default(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{	  
	   HttpSession session = request.getSession();
	   //判断用户读写权开始
	   String userType     = session.getAttribute("userType").toString();   //得到用户类型
	   if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
	    }
//	   boolean isStu      = (userType.equalsIgnoreCase("stu"));				 //判断是否是学生用户登录
	   String userName       = session.getAttribute("userName").toString();	//得到登录用户名
//	   String writeAble    = (Base.chkUPower(sUName, "fdy_xqtjcx.do", isStu) == 1) ? "yes" : "no";
	   String userOnLine       = session.getAttribute("userOnLine").toString();	//得到登录用户名
	   //判断用户读写权结束
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   ArrayList<HashMap<String, String>> topTr = null;
	   ArrayList<String[]>  rs = null;
	   String xn = gcForm.getXn();
	   String xq = gcForm.getXq();
	   if(xn==null||xn.equalsIgnoreCase("")){
		   gcForm.setXn(Base.currXn);
	   }
	   if(xq==null||xq.equalsIgnoreCase("")){
		   gcForm.setXq(Base.currXq);
	   }
	   GyglCsmzFdyXqCxModel gcModel = new GyglCsmzFdyXqCxModel();//查询参数MODEL	 
	   GyglCsmzServices gcServices  = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   if(request.getParameter("go")!=null&&
			   request.getParameter("go").equalsIgnoreCase("go")){
		   topTr = gcServices.getFdyXqSearchTitle();//查询结果表头
		   rs    = gcServices.getFdyXqSearchResult(gcModel);//查询结果
	   } 
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "fdy_xqtjcx.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
	
	   request.setAttribute("xnList", Base.getXnndList());//学年列表
	   request.setAttribute("xqList", Base.getXqList());//学期列表
	   request.setAttribute("xyList",Base.getXyList());//学院列表
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("fdyList",gyDao.getFdyList());//辅导员列表
	   request.setAttribute("rs", rs);
	   request.setAttribute("topTr",topTr);
	   request.setAttribute("rsNum",rs!=null?rs.size():"");
	   request.setAttribute("realTable","gygl_fdyxqxxb");
	   request.setAttribute("tableName","view_gygl_fdyxqxx");
	   return mapping.findForward("fdyxq_cxDef");
   }
   /**
    * 长沙民政新生（在没分班编学号之前）宿舍
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
   @SuppressWarnings("static-access")
public ActionForward xsDorm_Distribute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{	 
	   GyglCsmzActionForm gcForm    = (GyglCsmzActionForm)form;
	   GyglCsmzServices gcServices  = new GyglCsmzServices();
	   gyglDao gyDao                = new gyglDao();
	   GetGyglData getG             = new GetGyglData();
	   String xydm                  = gcForm.getXydm();
	   //String zydm                  = gcForm.getZydm();
	   String xqdm                  = gcForm.getXqdm();	  
	   String lddm                  = gcForm.getLddm();
	   String currNj                = Base.currNd;//当前年级
	   String doType                = request.getParameter("doType");
	   String xb                    = gcForm.getXb();
	   String newMappingItems       = DealString.toGBK(gcForm.getConditionSqlValue());
	   String oldMappingItems       = DealString.toGBK(gcForm.getOldCondiSqlValue());
	   if(doType!=null&&doType.equalsIgnoreCase("save")){
		   boolean doFlag          = false;		  
		   doFlag                   = gcServices.xsDorm_DistributeSave(newMappingItems,oldMappingItems);
		   if(doFlag){//保存成功后新值即为旧值
			   oldMappingItems      = newMappingItems;
		   }
		   request.setAttribute("doFlag", doFlag);		   
	   }	  
	   gcForm.setNj(currNj);	   
	   
	   String[] wfpZRS = getG.getXsWFPrs(currNj,xydm,"","");//未总人数
	   if(wfpZRS!=null){
		   request.setAttribute("allbody", wfpZRS[0]);
		   request.setAttribute("allboy", wfpZRS[1]);
		   request.setAttribute("allgirl", wfpZRS[2]);
	   }else{
		   request.setAttribute("allbody", "0");
		   request.setAttribute("allboy", "0");
		   request.setAttribute("allgirl", "0");
	   }
	   String[] yfpZWS = getG.getXSYFPcws(currNj,xydm);//已经总床(位)数
	   if(yfpZWS!=null){
		   request.setAttribute("totalBed", yfpZWS[0]);
		   request.setAttribute("boyBed", yfpZWS[1]);
		   request.setAttribute("girlBed", yfpZWS[2]);
		   request.setAttribute("bgBed", yfpZWS[3]);
	   }else{
		   request.setAttribute("totalBed", "0");
		   request.setAttribute("boyBed", "0");
		   request.setAttribute("girlBed", "0");
		   request.setAttribute("bgBed","0");
	   }
	   
	   request.setAttribute("oldCondiSqlValue",oldMappingItems);
	   request.setAttribute("xbxdList",getG.getLdXbXdList(xqdm));//楼栋性别限定列表
	   request.setAttribute("ldList",getG.getSsFpLdList(xqdm, xb));//楼栋列表
	   request.setAttribute("ssxxList", getG.getSsFpSsXxList(xqdm, xb, lddm));//未宿舍列表
	   request.setAttribute("ssfpList",getG.getSsFpFpSsXxList(lddm, xqdm, xydm, currNj));//已宿舍列表
	   request.setAttribute("nj",currNj);
	   request.setAttribute("njList", Base.getXnndList());
	   request.setAttribute("xyList",gyDao.getXsYxList(currNj));
	   request.setAttribute("xiaoqquList", gyDao.getXiaoQuList());
	   return mapping.findForward("xsDormDistribute");
   }
   @SuppressWarnings("static-access")
public ActionForward xsBed_Distribute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   DAO dao = DAO.getInstance();
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   String currNj               = Base.currNd;//当前年级
	   String doType               = request.getParameter("doType");
	   gyglDao gyDao               = new gyglDao();
	   GetGyglData     getG        = new GetGyglData();
	   String xydm                 = gcForm.getXydm();
	   String zydm                 = gcForm.getZydm();
	   String xqdm                 = gcForm.getXqdm();
	   String bjdm                 = gcForm.getBjdm();
	   String xb                   = gcForm.getXb();
	   String lddm                 = gcForm.getLddm();
	   String ksh                  = DealString.toGBK(gcForm.getKsh()).trim();
	   gcForm.setKsh(ksh);
	   String newMappingItems      = DealString.toGBK(gcForm.getConditionSqlValue());
	   String oldMappingItems      = DealString.toGBK(gcForm.getOldCondiSqlValue());
	   String userDep              = request.getSession().getAttribute("userDep").toString();
	   //String userType = request.getSession().getAttribute("userType").toString();
	   String userType             = DAO.getInstance().getUserType(userDep);
	   gcForm.setNj(currNj);
	   if("xy".equalsIgnoreCase(userType)){
		   xydm  = userDep;
		   gcForm.setXydm(xydm);
		}
	   if(doType!=null&&doType.equalsIgnoreCase("save")){
		  boolean doFlag          = false;		 
		  doFlag                   = gcServices.xsBed_DistributeSave(newMappingItems,oldMappingItems);
		  if(doFlag){//保存成功后新值即为旧值
			  oldMappingItems      = newMappingItems;
		  }
		  request.setAttribute("doFlag", doFlag);
	   }
	   String[] wfpZRS = getG.getXsWFPrs(currNj,xydm,zydm,bjdm);//未总人数
	   if(wfpZRS!=null){
		   request.setAttribute("allbody", wfpZRS[0]);
		   request.setAttribute("allboy", wfpZRS[1]);
		   request.setAttribute("allgirl", wfpZRS[2]);
	   }else{
		   request.setAttribute("allbody", "0");
		   request.setAttribute("allboy", "0");
		   request.setAttribute("allgirl", "0");
	   }
	   String[] yfpZRS = getG.getXsYFPrs(currNj, xydm, zydm,bjdm);//已总人数
	   if(yfpZRS!=null){
		   request.setAttribute("totalBed", yfpZRS[0]);
		   request.setAttribute("boyBed", yfpZRS[1]);
		   request.setAttribute("girlBed", yfpZRS[2]);
	   }else{
		   request.setAttribute("totalBed", "0");
		   request.setAttribute("boyBed", "0");
		   request.setAttribute("girlBed", "0");
	   }
	   request.setAttribute("oldCondiSqlValue",oldMappingItems);
	   
	  
	   request.setAttribute("xbxdList",getG.getLdXbXdList(xqdm));//性别列表
	   request.setAttribute("ldList",getG.getXsCwFpLdList(xydm, currNj, xqdm, xb));//楼栋列表
	   request.setAttribute("ssxxList",getG.getXsCwFpSsList(lddm,currNj, xydm));//宿舍信息列表	  
	   request.setAttribute("nj",currNj);
	   request.setAttribute("njList", Base.getXnndList());
	   request.setAttribute("xyList",gyDao.getXsYxList(currNj));
	   request.setAttribute("userType",userType);
	   request.setAttribute("xxdm",Base.xxdm);
	   if(!Base.isNull(ksh)){//单个学号查询
			String sql = "select ksh,xydm,zydm,bjdm,xb from view_newstuinfo where  ksh=?";
			HashMap<String,String>map=dao.getMap(sql,new String[]{ksh},new String[]{"ksh","zydm","xydm","bjdm","xb"});
			xydm = map.get("xydm");
			zydm = map.get("zydm");
			bjdm = map.get("bjdm");
			xb = map.get("xb");
			request.setAttribute("zyList",getG.getSsFpZyList(xydm, currNj));//专业列表
			request.setAttribute("bjList",getG.getSsFpBjList(zydm, currNj));//班级列表
			request.setAttribute("xiaoqquList",getG.getXiaoQuList(xydm,currNj));//校区列表
			request.setAttribute("xsList", getG.getWfpXsList(xydm, zydm,bjdm, currNj, xb,ksh));//未分配学生列表
			request.setAttribute("fpcwList",getG.getXsYFpCwList(zydm, currNj,xb,bjdm,xydm,ksh));//已分配床位列表
			gcForm.setXydm(xydm);
			gcForm.setZydm(zydm);
			gcForm.setBjdm(bjdm);
			gcForm.setXb(xb);
		}else{
			request.setAttribute("zyList",getG.getSsFpZyList(xydm, currNj));//专业列表
			request.setAttribute("bjList",getG.getSsFpBjList(zydm, currNj));//班级列表
			request.setAttribute("xiaoqquList",getG.getXiaoQuList(xydm,currNj));//校区列表
			request.setAttribute("xsList", getG.getWfpXsList(xydm, zydm,bjdm, currNj, xb,""));//未分配学生列表
			request.setAttribute("fpcwList",getG.getXsYFpCwList(zydm, currNj,xb,bjdm,xydm,""));//已分配床位列表
		}
	   return mapping.findForward("xsBedDistribute");
   }
   public ActionForward cwsFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   String sycws = request.getParameter("qssycws");
	   String nj = request.getParameter("nj");
	   String doType = request.getParameter("doType");
	   List<HashMap<String, String>> cwsList = new ArrayList<HashMap<String,String>>();
	   if(sycws!=null&&!sycws.equalsIgnoreCase("")){
		   for (int i = 1; i <= Integer.parseInt(sycws); i++) {
			   HashMap<String, String> resTemp = new HashMap<String, String>();
			   resTemp.put("en", String.valueOf(i));
			   resTemp.put("cn", String.valueOf(i));
			   cwsList.add(resTemp);
		   }//end for
	   }
	   request.setAttribute("nj",nj);
	   request.setAttribute("cwsList",cwsList);
	   request.setAttribute("doType",doType);
	   return mapping.findForward("cwsFp");
   }   
   public ActionForward xsDormDataSearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   HttpSession session         = request.getSession();
	   String currNj               = Base.currNd;//当前年级
	   gyglDao gyDao               = new gyglDao();
	   GetGyglData getG            = new GetGyglData();
	   String userDep              = session.getAttribute("userDep").toString();//用户所在部门
	   //String userName           = session.getAttribute("userName").toString();
	   String userType        	   = session.getAttribute("userType").toString();//用户类型
	   String xydm                 = gcForm.getXydm(); 	   
	   if("xy".equalsIgnoreCase(userType)){
		   xydm  = userDep;
		   gcForm.setXydm(xydm);
	   }	   
	   gcForm.setNj(currNj);
	   request.setAttribute("njList", Base.getXnndList());
	   request.setAttribute("xyList",gyDao.getXsYxList(currNj));
	   request.setAttribute("zyList",getG.getSsFpZyList(xydm, currNj));//专业列表
	   request.setAttribute("tableName", "view_newstuzsxx");
	   request.setAttribute("realTable", "xszsxxb");
	   request.setAttribute("userType",userType);
	   return mapping.findForward("xsDDSCH");
   }
   public ActionForward xsDormDSExecute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   HttpSession session         = request.getSession();
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   xsDormDSCHModel  xsDDSModel = new xsDormDSCHModel();
	   gcForm.setNj(Base.currNd);
	   GyglCsmzServices gcServices = new GyglCsmzServices();
//	   String bmdm            	   = session.getAttribute("userDep").toString();//用户所在部门
	   String userName             = session.getAttribute("userName").toString();
	   String userType        	   = session.getAttribute("userType").toString();//用户类型
	   BeanUtils.copyProperties(xsDDSModel, gcForm);
	   
//	   if("xy".equalsIgnoreCase(userType)){
//			xydm = bmdm;
//		}
	   
	   List<HashMap<String, String>> topList = gcServices.getXsDormSearchTitle();
	   List<String[]>                resList = gcServices.getXsDormSearchResult(userName, userType, xsDDSModel);
	   request.setAttribute("topTr", topList);
	   //==============2010/8/4 edit by luojw =================
	   request.setAttribute("rs", gcServices.getResultList(resList, gcForm));
	   //==============2010/8/4 end =================
	   request.setAttribute("rsNum", resList != null ? resList.size() : 0);
	   request.setAttribute("writeAble","yes");
	   return new ActionForward("/csmz_gygl.do?method=xsDormDataSearch",false);
   }
   public ActionForward xsZsxxAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   HashMap<String,String> map  = new HashMap<String,String>();
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   String ksh                  = request.getParameter("xh");
	   GetGyglData getG            = new GetGyglData();
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   String lddm                 = gcForm.getLddm();
//	   String qsh                  = gcForm.getQsh();
	   String ssbh                 = "";
	   String xydm                 = "";
	   String nj                   = "";
	   String xb                   = "";
//	   String[] ssInfo             = new String[3];
	   if(ksh!=null&&!ksh.equalsIgnoreCase("")){
		   map    = gcServices.getxsXX(ksh);
//		   ssInfo = gcServices.serv_getXsSsInfo(ksh);// 
//	       if(ssInfo!=null){
//	    	   lddm = ssInfo[0];
//	    	   ssbh = ssInfo[1];
////	    	   qsh  = ssInfo[2];
//	    	   map.put("lddm",lddm);
//	    	   map.put("qsh",ssbh);	    	   
//	       }
	       xydm = map.get("xydm");
	       nj   = map.get("nj");
	       xb   = map.get("xb");
	   }	   
	   map.put("bz","");
	   request.setAttribute("nj",nj);
	   request.setAttribute("xydm",xydm);
	   request.setAttribute("ldList",getG.getGyLdList(xydm,nj,xb));//楼栋列表
	   request.setAttribute("ssxxList",getG.getQshList(lddm,xydm,nj));//宿舍信息列表
	   request.setAttribute("cwxxList",getG.getCwhList(ssbh));//床位信息列表
	   request.setAttribute("rs",map);
	   return mapping.findForward("xsZSXX");
   }
   public ActionForward xsZsxxAddSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   String ksh  = gcForm.getXh();
	   String ssbh = gcForm.getQsh();
	   String cwh  = gcForm.getCwh();
	   String rzrq = gcForm.getZsrq();
	   boolean done = gcServices.xsZSADSave(ksh, ssbh, cwh,rzrq);
	   request.setAttribute("done", done);
	   return new ActionForward("/csmz_gygl.do?method=xsZsxxAdd",false);
   }
   /**学生住宿信息批量删除
	 * @throws Exception */
	public ActionForward xsZsxxPlDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglCsmzServices gcServices = new GyglCsmzServices();
		boolean  flag  = gcServices.service_xsZsxxPlDel(request);
		request.setAttribute("flag",flag);
		return new ActionForward("/csmz_gygl.do?method=xsDormDataSearch", false);
	}
	/**将新生住宿信息中考生号转换学号
	 * @throws Exception */
	public ActionForward xhKshSynchro(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglCsmzServices gcServices = new GyglCsmzServices();
		String pkValue = request.getParameter("delPk");
		String  result  = gcServices.service_xhKshSynchro(pkValue);
		request.setAttribute("synchroResult",result);
		return new ActionForward("/csmz_gygl.do?method=xsDormDataSearch", false);
	}
   //宿舍分配情况统计
	public ActionForward hFDeTailView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglCsmzServices gcServices = new GyglCsmzServices();
		List list = gcServices.serviec_hFDeTailView();
		List<HashMap<String, String>> topList = gcServices.getTableTitle();//查询表头
		request.setAttribute("list", list);
		request.setAttribute("topTr", topList);
		return mapping.findForward("ssHfDetail");
	}
	
	public ActionForward ssrzxxView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglCsmzServices gcServices = new GyglCsmzServices();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));//获取楼栋代码
		String ldmc = DealString.toGBK(request.getParameter("ldmc"));//楼栋名称
		List<String[]> resList = gcServices.getSxRzqk(pkValue);
		List<HashMap<String, String>> topList = gcServices.getSxRxqkTitle();
		request.setAttribute("list", resList);
		request.setAttribute("ldmc", ldmc);
		request.setAttribute("topTr", topList);
		return mapping.findForward("xsrzxxview");
	}
	//床位分配情况统计
	public ActionForward FpDeTailView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglCsmzServices gcServices = new GyglCsmzServices();
		GyglCsmzActionForm csmz_gyglForm = (GyglCsmzActionForm)form;
		HttpSession session = request.getSession();	
		String xydm = csmz_gyglForm.getXydm();
		String zydm = csmz_gyglForm.getNj();
		String nj = csmz_gyglForm.getNj();
		String bjdm = csmz_gyglForm.getBjdm();
		String userDep = session.getAttribute("userDep").toString();
		List list = gcServices.service_FpDeTailView(userDep,nj,xydm,zydm,bjdm);
		request.setAttribute("rs", list);		
		return mapping.findForward("cwFpDetail");
	}
	public ActionForward xsrzqk_Tj(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String xxdm = Base.xxdm;
		
		if (Base.xxdm.equals(Globals.XXDM_ZJCMXY)) {
			return rzqktj(mapping, form, request, response);
		}
		
		GyglCsmzActionForm csmz_gyglForm = (GyglCsmzActionForm)form;
		GyglCsmzServices gcServices = new GyglCsmzServices();
		String xydm = DealString.toString(csmz_gyglForm.getXydm());
		String zydm = DealString.toString(csmz_gyglForm.getZydm());
		String nj   = DealString.toString(csmz_gyglForm.getNj());
		String bjdm = DealString.toString(csmz_gyglForm.getBjdm());
		String userDep = request.getSession().getAttribute("userDep").toString();
		List list      = null;
		if(request.getParameter("go")!=null
				&&request.getParameter("go").equalsIgnoreCase("go")){
			 list = gcServices.service_FpDeTailView(userDep,nj,xydm,zydm,bjdm);
		}
		request.setAttribute("rs", list);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// 发送班级列表
		//request.setAttribute("xxdm",xxdm);
		return mapping.findForward("xsrzqk_Tj");
	}
	public ActionForward wxsyzy_xsrzqk_Tj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		GyglCsmzActionForm csmz_gyglForm = (GyglCsmzActionForm)form;
		GyglCsmzModel model = new GyglCsmzModel();
		GyglCsmzServices service = new GyglCsmzServices();
		BeanUtils.copyProperties(model,csmz_gyglForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		try {
			service.serv_wxsyzy_xsrzqk_Tj(wwb, model);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return mapping.findForward("");
	}
	
	
	
	public ActionForward rzqktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		GyglCsmzActionForm model = (GyglCsmzActionForm)form;
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("xy".equals(userType)) {
			model.setXydm(userDep);
		}	
		
		request.setAttribute("path", "stuHouseInfo.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("rzqktj");
	}
	
	
	/**
	 * 入住情况统计报表输出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printRzqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglCsmzServices service = new GyglCsmzServices();
		GyglCsmzActionForm model = (GyglCsmzActionForm)form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		service.printRzqk(model, response.getOutputStream());
		
		return mapping.findForward("");
	}
}
