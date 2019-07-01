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
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ְҵ����ѧԺ��Ԣ����Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-11</p>
 */
 
public class GyglCsmzAction extends DispatchAction {
	/**
	 * ����Ա��������ϱ�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param respose
	 * @return
	 */
   public ActionForward fdy_wstb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HttpSession session = request.getSession();	  
	   HashMap<String,String> map = new HashMap<String,String>();
	   String userName     = session.getAttribute("userName").toString();//ְ����
	   String userNameReal = session.getAttribute("userNameReal").toString();//�û�����
	   String isFdy        = session.getAttribute("isFdy").toString();//�Ƿ��Ǹ���Ա��־	  
	   if(isFdy.equalsIgnoreCase("true")){//����Ǹ���Ա�û�Ĭ���û���¼�����û�����
		   map.put("zgh",userName);
		   map.put("xm",userNameReal);
	   }
	   map.put("isFdy",isFdy);	  
	   map.put("xn",Base.currXn);
	   map.put("xq",Base.currXq);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("rs",map);
	   request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
	   return mapping.findForward("gygl_fdywstb");
	   
   }
 
   /**
    * ����Ա�������Ϣ����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    * @throws Exception 
    */
   public ActionForward savefdy_tbxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//�������MODEL
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   boolean saveDone           = gcServices.saveFdytbxx(gcModel);	   
	   request.setAttribute("done",saveDone);	   
	   return mapping.findForward("savefdy_tbxx");
   }
   /**����Ա������Ϣ���*/
   public ActionForward fdy_xqxx_add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HashMap<String,String> map = new HashMap<String,String>();
	   map.put("xn",Base.currXn);
	   map.put("xq",Base.currXq);
	   request.setAttribute("rs",map);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
	   return mapping.findForward("fdy_xqxx_add");
   }
   /**����Ա������Ϣ��ӱ���
 * @throws Exception */
   public ActionForward fdy_xqxx_addToSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose) throws Exception{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//�������MODEL
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   boolean saveDone           = gcServices.saveFdytbxx(gcModel);	   
	   request.setAttribute("done",saveDone);	   
	   return new ActionForward("/csmz_gygl.do?method=fdy_xqxx_add",false);
   }
   /**����Ա������Ϣ�޸�*/
   public ActionForward fdy_xqxx_modi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HashMap<String,String> map = new HashMap<String,String>();
	   String pkValue = request.getParameter("pkValue");
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   map = gcServices.getFdyXqXxMap(pkValue);
	   request.setAttribute("rs",map);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
	   return mapping.findForward("fdy_xqxx_modi");
   }
   /**����Ա������Ϣ�޸ı���
 * @throws InvocationTargetException 
 * @throws Exception */
   public ActionForward fdy_xqxx_modiToSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose) throws Exception, InvocationTargetException{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//�������MODEL
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   boolean saveDone           = gcServices.saveFdytbxx(gcModel);	   
	   request.setAttribute("done",saveDone);	
	   return new ActionForward("/csmz_gygl.do?method=fdy_xqxx_modi");
   }
   /**����Ա������Ϣ�鿴*/
   public ActionForward fdy_xqxx_view(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose){
	   HashMap<String,String> map = new HashMap<String,String>();
	   String pkValue = request.getParameter("pkValue");
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   map = gcServices.getFdyXqXxMap(pkValue);
	   request.setAttribute("rs",map);
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
	   return mapping.findForward("fdy_xqxx_view");
   }
   /**����Ա������Ϣɾ��
  *@throws Exception */
   public ActionForward fdy_xqxx_del(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respose) throws Exception{	  
	   String pkValue = request.getParameter("pkValue");
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   boolean del = gcServices.delFdyXqXx(pkValue);
	   request.setAttribute("sfDone",del);
	   return new ActionForward("/csmz_gygl.do?method=xqcx_Default&go=go");
   }
   /**
    * ѧ�����Ը���Ա����������з���
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */
   public ActionForward stu_fkxxcx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   HttpSession session  = request.getSession();
	   String userType      = session.getAttribute("userOnLine").toString();//�û����� ѧ������ʦ
	   String userName      = session.getAttribute("userName").toString();//�û���½��	  
	   GyglCsmzServices gcServices          = new GyglCsmzServices();
	   List<HashMap<String,String>> qurList = gcServices.fkxxcx(userType, userName);//���ز�ѯ���
	   
	   request.setAttribute("userType",userType);
	   request.setAttribute("rs",qurList);	   
	   request.setAttribute("rsNum",qurList!=null?qurList.size():"0");//��¼��
	   return mapping.findForward("stu_fkxx");
   }
   /**������ϢĬ�ϲ�ѯҳ����Ϣ*/
   public ActionForward stu_tofkxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   String pkValue              = DealString.toGBK(request.getParameter("pkValue"));
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   request.setAttribute("rs",gcServices.stu_fdyXqXxView(pkValue));//ҳ����ʾ��Ϣ	   
	   request.setAttribute("pkValue",pkValue);
	   return mapping.findForward("stu_fkxxView");
   }
   /** ������Ϣ����*/
   public ActionForward saveStu_Fkxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	   GyglCsmzActionForm gcForm   = (GyglCsmzActionForm)form;
	   String pkValue              = DealString.toGBK(request.getParameter("pkValue"));
	   GyglCsmzModel gcModel       = new GyglCsmzModel();//�������MODEL	 
	   BeanUtils.copyProperties(gcModel, gcForm);
	   GyglCsmzServices gcServices = new GyglCsmzServices();
	   boolean saveDone           = gcServices.saveStu_fkxx(pkValue, gcModel);
	   request.setAttribute("done",saveDone);
	   request.setAttribute("pkValue",pkValue);
	   return new ActionForward("/csmz_gygl.do?method=stu_tofkxx");
   }
   /**����Ա������Ϣ��ѯ
   * @throws InvocationTargetException 
   * @throws IllegalAccessException */
   public ActionForward xqcx_Default(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{	  
	   HttpSession session = request.getSession();
	   //�ж��û���дȨ��ʼ
	   String userType     = session.getAttribute("userType").toString();   //�õ��û�����
	   if (userType.equalsIgnoreCase("stu")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
	    }
//	   boolean isStu      = (userType.equalsIgnoreCase("stu"));				 //�ж��Ƿ���ѧ���û���¼
	   String userName       = session.getAttribute("userName").toString();	//�õ���¼�û���
//	   String writeAble    = (Base.chkUPower(sUName, "fdy_xqtjcx.do", isStu) == 1) ? "yes" : "no";
	   String userOnLine       = session.getAttribute("userOnLine").toString();	//�õ���¼�û���
	   //�ж��û���дȨ����
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
	   GyglCsmzFdyXqCxModel gcModel = new GyglCsmzFdyXqCxModel();//��ѯ����MODEL	 
	   GyglCsmzServices gcServices  = new GyglCsmzServices();
	   BeanUtils.copyProperties(gcModel, gcForm);
	   if(request.getParameter("go")!=null&&
			   request.getParameter("go").equalsIgnoreCase("go")){
		   topTr = gcServices.getFdyXqSearchTitle();//��ѯ�����ͷ
		   rs    = gcServices.getFdyXqSearchResult(gcModel);//��ѯ���
	   } 
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "fdy_xqtjcx.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
	
	   request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
	   request.setAttribute("xqList", Base.getXqList());//ѧ���б�
	   request.setAttribute("xyList",Base.getXyList());//ѧԺ�б�
	   gyglDao gyDao = new gyglDao();
	   request.setAttribute("fdyList",gyDao.getFdyList());//����Ա�б�
	   request.setAttribute("rs", rs);
	   request.setAttribute("topTr",topTr);
	   request.setAttribute("rsNum",rs!=null?rs.size():"");
	   request.setAttribute("realTable","gygl_fdyxqxxb");
	   request.setAttribute("tableName","view_gygl_fdyxqxx");
	   return mapping.findForward("fdyxq_cxDef");
   }
   /**
    * ��ɳ������������û�ְ��ѧ��֮ǰ������
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
	   String currNj                = Base.currNd;//��ǰ�꼶
	   String doType                = request.getParameter("doType");
	   String xb                    = gcForm.getXb();
	   String newMappingItems       = DealString.toGBK(gcForm.getConditionSqlValue());
	   String oldMappingItems       = DealString.toGBK(gcForm.getOldCondiSqlValue());
	   if(doType!=null&&doType.equalsIgnoreCase("save")){
		   boolean doFlag          = false;		  
		   doFlag                   = gcServices.xsDorm_DistributeSave(newMappingItems,oldMappingItems);
		   if(doFlag){//����ɹ�����ֵ��Ϊ��ֵ
			   oldMappingItems      = newMappingItems;
		   }
		   request.setAttribute("doFlag", doFlag);		   
	   }	  
	   gcForm.setNj(currNj);	   
	   
	   String[] wfpZRS = getG.getXsWFPrs(currNj,xydm,"","");//δ������
	   if(wfpZRS!=null){
		   request.setAttribute("allbody", wfpZRS[0]);
		   request.setAttribute("allboy", wfpZRS[1]);
		   request.setAttribute("allgirl", wfpZRS[2]);
	   }else{
		   request.setAttribute("allbody", "0");
		   request.setAttribute("allboy", "0");
		   request.setAttribute("allgirl", "0");
	   }
	   String[] yfpZWS = getG.getXSYFPcws(currNj,xydm);//�Ѿ��ܴ�(λ)��
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
	   request.setAttribute("xbxdList",getG.getLdXbXdList(xqdm));//¥���Ա��޶��б�
	   request.setAttribute("ldList",getG.getSsFpLdList(xqdm, xb));//¥���б�
	   request.setAttribute("ssxxList", getG.getSsFpSsXxList(xqdm, xb, lddm));//δ�����б�
	   request.setAttribute("ssfpList",getG.getSsFpFpSsXxList(lddm, xqdm, xydm, currNj));//�������б�
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
	   String currNj               = Base.currNd;//��ǰ�꼶
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
		  if(doFlag){//����ɹ�����ֵ��Ϊ��ֵ
			  oldMappingItems      = newMappingItems;
		  }
		  request.setAttribute("doFlag", doFlag);
	   }
	   String[] wfpZRS = getG.getXsWFPrs(currNj,xydm,zydm,bjdm);//δ������
	   if(wfpZRS!=null){
		   request.setAttribute("allbody", wfpZRS[0]);
		   request.setAttribute("allboy", wfpZRS[1]);
		   request.setAttribute("allgirl", wfpZRS[2]);
	   }else{
		   request.setAttribute("allbody", "0");
		   request.setAttribute("allboy", "0");
		   request.setAttribute("allgirl", "0");
	   }
	   String[] yfpZRS = getG.getXsYFPrs(currNj, xydm, zydm,bjdm);//��������
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
	   
	  
	   request.setAttribute("xbxdList",getG.getLdXbXdList(xqdm));//�Ա��б�
	   request.setAttribute("ldList",getG.getXsCwFpLdList(xydm, currNj, xqdm, xb));//¥���б�
	   request.setAttribute("ssxxList",getG.getXsCwFpSsList(lddm,currNj, xydm));//������Ϣ�б�	  
	   request.setAttribute("nj",currNj);
	   request.setAttribute("njList", Base.getXnndList());
	   request.setAttribute("xyList",gyDao.getXsYxList(currNj));
	   request.setAttribute("userType",userType);
	   request.setAttribute("xxdm",Base.xxdm);
	   if(!Base.isNull(ksh)){//����ѧ�Ų�ѯ
			String sql = "select ksh,xydm,zydm,bjdm,xb from view_newstuinfo where  ksh=?";
			HashMap<String,String>map=dao.getMap(sql,new String[]{ksh},new String[]{"ksh","zydm","xydm","bjdm","xb"});
			xydm = map.get("xydm");
			zydm = map.get("zydm");
			bjdm = map.get("bjdm");
			xb = map.get("xb");
			request.setAttribute("zyList",getG.getSsFpZyList(xydm, currNj));//רҵ�б�
			request.setAttribute("bjList",getG.getSsFpBjList(zydm, currNj));//�༶�б�
			request.setAttribute("xiaoqquList",getG.getXiaoQuList(xydm,currNj));//У���б�
			request.setAttribute("xsList", getG.getWfpXsList(xydm, zydm,bjdm, currNj, xb,ksh));//δ����ѧ���б�
			request.setAttribute("fpcwList",getG.getXsYFpCwList(zydm, currNj,xb,bjdm,xydm,ksh));//�ѷ��䴲λ�б�
			gcForm.setXydm(xydm);
			gcForm.setZydm(zydm);
			gcForm.setBjdm(bjdm);
			gcForm.setXb(xb);
		}else{
			request.setAttribute("zyList",getG.getSsFpZyList(xydm, currNj));//רҵ�б�
			request.setAttribute("bjList",getG.getSsFpBjList(zydm, currNj));//�༶�б�
			request.setAttribute("xiaoqquList",getG.getXiaoQuList(xydm,currNj));//У���б�
			request.setAttribute("xsList", getG.getWfpXsList(xydm, zydm,bjdm, currNj, xb,""));//δ����ѧ���б�
			request.setAttribute("fpcwList",getG.getXsYFpCwList(zydm, currNj,xb,bjdm,xydm,""));//�ѷ��䴲λ�б�
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
	   String currNj               = Base.currNd;//��ǰ�꼶
	   gyglDao gyDao               = new gyglDao();
	   GetGyglData getG            = new GetGyglData();
	   String userDep              = session.getAttribute("userDep").toString();//�û����ڲ���
	   //String userName           = session.getAttribute("userName").toString();
	   String userType        	   = session.getAttribute("userType").toString();//�û�����
	   String xydm                 = gcForm.getXydm(); 	   
	   if("xy".equalsIgnoreCase(userType)){
		   xydm  = userDep;
		   gcForm.setXydm(xydm);
	   }	   
	   gcForm.setNj(currNj);
	   request.setAttribute("njList", Base.getXnndList());
	   request.setAttribute("xyList",gyDao.getXsYxList(currNj));
	   request.setAttribute("zyList",getG.getSsFpZyList(xydm, currNj));//רҵ�б�
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
//	   String bmdm            	   = session.getAttribute("userDep").toString();//�û����ڲ���
	   String userName             = session.getAttribute("userName").toString();
	   String userType        	   = session.getAttribute("userType").toString();//�û�����
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
	   request.setAttribute("ldList",getG.getGyLdList(xydm,nj,xb));//¥���б�
	   request.setAttribute("ssxxList",getG.getQshList(lddm,xydm,nj));//������Ϣ�б�
	   request.setAttribute("cwxxList",getG.getCwhList(ssbh));//��λ��Ϣ�б�
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
   /**ѧ��ס����Ϣ����ɾ��
	 * @throws Exception */
	public ActionForward xsZsxxPlDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglCsmzServices gcServices = new GyglCsmzServices();
		boolean  flag  = gcServices.service_xsZsxxPlDel(request);
		request.setAttribute("flag",flag);
		return new ActionForward("/csmz_gygl.do?method=xsDormDataSearch", false);
	}
	/**������ס����Ϣ�п�����ת��ѧ��
	 * @throws Exception */
	public ActionForward xhKshSynchro(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglCsmzServices gcServices = new GyglCsmzServices();
		String pkValue = request.getParameter("delPk");
		String  result  = gcServices.service_xhKshSynchro(pkValue);
		request.setAttribute("synchroResult",result);
		return new ActionForward("/csmz_gygl.do?method=xsDormDataSearch", false);
	}
   //����������ͳ��
	public ActionForward hFDeTailView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglCsmzServices gcServices = new GyglCsmzServices();
		List list = gcServices.serviec_hFDeTailView();
		List<HashMap<String, String>> topList = gcServices.getTableTitle();//��ѯ��ͷ
		request.setAttribute("list", list);
		request.setAttribute("topTr", topList);
		return mapping.findForward("ssHfDetail");
	}
	
	public ActionForward ssrzxxView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglCsmzServices gcServices = new GyglCsmzServices();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));//��ȡ¥������
		String ldmc = DealString.toGBK(request.getParameter("ldmc"));//¥������
		List<String[]> resList = gcServices.getSxRzqk(pkValue);
		List<HashMap<String, String>> topList = gcServices.getSxRxqkTitle();
		request.setAttribute("list", resList);
		request.setAttribute("ldmc", ldmc);
		request.setAttribute("topTr", topList);
		return mapping.findForward("xsrzxxview");
	}
	//��λ�������ͳ��
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
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// ���Ͱ༶�б�
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
			System.out.println("���ݵ���ʧ��!");
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
	 * ��ס���ͳ�Ʊ������
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
