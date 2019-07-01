
package xgxt.pjpy.tyb.zhszcp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.bdsz.BdszService;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.ntzydx.PjpyNtzydxService;
import xgxt.pjpy.tyb.cssz.service.PjpyPjzqszService;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZctjszService;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhszcpService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.utils.img.ImageExportUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import com.zfsoft.basic.BasicService;
import common.GlobalsVariable;


public class PjpyZhszcpwhAction extends CommonAction {
	
	//一级代码默认编号
	private static final String ZHSZCP_YJDM = "999";
	
    /**
    * 综合素质测评四级项目数据维护
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward zhszcpSjwh(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{
       
    	PjpyZhszcpwhActionForm actionForm = (PjpyZhszcpwhActionForm) form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
        PjpyPjzqszService pjzqService = new PjpyPjzqszService(); 
        PjpyZctjszService tjszService = new PjpyZctjszService();
        XtwhOtherService xtwhService = new XtwhOtherService();
        PjpyTyService tyService = new PjpyTyService();
        User user = getUser(request);
        
        if (UserTypePd.isXy(user.getUserType()) && !UserTypePd.isFdy(user.getIsFdy())) {
        	actionForm.setXydm(user.getUserDep());
        }
        
        //项目代码级别,2,3,4级
        String dmlb = request.getParameter("dmlb");
        //项目代码
        String dm = request.getParameter("dm");
        //表名
        String bm = request.getParameter("bm");
        //审核级别
        String shjb = tjszService.queryZhszcpxmShjb(dm, bm);
        //操作类型
        String act = request.getParameter("act");
        //保存自定义字段值表
        String bdszBcnrb = "py_bdsz_bcnr";
        
        List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>(); 
        List<String[]> rs = new ArrayList<String[]>();
        List<HashMap<String, String>> titleList = tjszService.queryZcDmxxList(dm);
        
        //判断是否可操作
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(getUserType(request)) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_ZHSZCP)
					&& tyService.checkKgflag()){
			String msg = "维护功能暂时不开放操作，仅限查询！";
			request.setAttribute("yhInfo", msg);
		}
        
        addpendUserInfo(actionForm, request);
        //查询数据操作
        if (QUERY.equalsIgnoreCase(act)) {
			PjpyZhszcpModel model = new PjpyZhszcpModel();
			BeanUtils.copyProperties(model, actionForm);
			model.setShjb(new String[]{shjb});

			// 自定义字段列表
			HashMap<String, String[]> zdyzdMap = service.getZdyzdMap(bm);
			// 查询显示字段列表
			HashMap<String, String[]> cxzdMap = service.getPjpyCxzdMap(bm,shjb);
			
			//查询主表的字段信息
			BasicService basicService = new BasicService();
			String[] zbzdArray = basicService.getTableColumn(bm);
			
			topTr = service.getZhszcpTitle(cxzdMap);
			rs = service.queryZhszcpResult(model, cxzdMap,zdyzdMap, bm, dm, zbzdArray);
		}
        if(DELETE.equalsIgnoreCase(act)){//删除操作
        	String realTable = bm.substring(5, bm.length());
        	//删除主表数据
        	deleteOperation(request, realTable); 
        	//删除附表数据
        	boolean result = (Boolean)request.getAttribute("result");
        	if(result){//删除主表成功
        		HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
        		String[] primaryKeys = {};
        		int i = 0;
        		for(String key : primaryMap.keySet()){
        			if(i++ == 0 ){
        				primaryKeys = primaryMap.get(key);
        			}
        		}
        		result = service.deleteBdszNrbcb(primaryKeys,bm,bdszBcnrb,user);
        		request.setAttribute("result", result);
        		request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
        	}
        }
        
        // 设置综测周期
        request.setAttribute("pjzq", pjzqService.getZczq());

        //页签列表
        request.setAttribute("pages", titleList);
        bm = StringUtils.isNull(bm) ? (titleList != null && titleList.size() > 0 ? titleList.get(0).get("bm") : bm) : bm;
        request.setAttribute("bm", bm);
        request.setAttribute("dm", dm);
        request.setAttribute("shjb", shjb);
        
        //将读写权跟title信息放到request中
        appendTableXx(request, bm, StringUtils.isNotNull(bm) ? bm.substring(5,bm.length()) : "");
        appendOperQx(request, "pjpy_tyb_zhszcpSjwh.do?dmlb=" + dmlb + "&dm="+ dm);
        appendQryResult(request, topTr, rs);
        FormModleCommon.commonRequestSet(request);
        FormModleCommon.setNjXyZyBjListForFdyBzr(request);
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
        request.setAttribute("chkList", service.getChkList(3));//审核列表
        request.setAttribute("disli", getDisliValue(request.getParameter("disli"), titleList));//是否显示操作按钮标志
        return mapping.findForward("query");
        
    }
    
    private String getDisliValue(String liValue, List<HashMap<String, String>> titleList) {
    	String sfwh = titleList != null && titleList.size() > 0
				&& titleList.get(0) != null ? titleList.get(0).get("sfwh") : "";
    	return StringUtils.isNull(liValue) ? (StringUtils.isNull(sfwh) ? "" : sfwh) : liValue;
    }
    
    /**
     * 综合素质测评四项数据增加操作
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
    	setDefaultValue(zcForm);
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	
    	//表名
    	String bm = request.getParameter("bm");
    	//bm = StringUtil.isNull(bm) ? "" : bm.replace("view_", "");
    	
    	//代码类别
    	String dmlb = request.getParameter("dmlb");
    	//代码
    	String dm = request.getParameter("dm");
    	
    	//存放当前学年，学期，年度
    	HashMap<String, String> rs = new HashMap<String, String>();
    	
    	String xh = request.getParameter("xh");
    	if (!StringUtils.isNull(xh)) {
    		rs.putAll(CommonQueryDAO.commonQueryOne("view_xsjbxx",
					new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
							"bjmc","xz" }, "xh", xh));
    	}
    	
    	//保存数据操作
    	if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
    		//自定义字段列表
			List<HashMap<String, String>> zdyzdMap = service.getZdyZdList(bm);
			//字段列表值
			HashMap<String, String[]> valueMap = getParamterMapArray(request, zdyzdMap);
			
			PjpyZhszcpModel model = new PjpyZhszcpModel();
			BeanUtils.copyProperties(model, zcForm);
			model.setXh(xh);
			
			//对于保存失败的数据进行返回并提示
			boolean result = service.saveZhszcpInfo(model, valueMap, bm,
					zdyzdMap);
			request.setAttribute("result", result);
//			request.setAttribute("errmsg", "操作完成,其中第"
//					+ (StringUtils.isNotNull(result) ? result.substring(1,
//							result.length() - 1) : "")
//					+ "条数据保存失败,可能是数据库已存在该记录.");
    	}
    	
    	
    	
    	rs.put("xn", Base.getJxjsqxn());
    	rs.put("xqmc", Base.getJxjsqxqmc());
    	rs.put("nd", Base.getJxjsqnd());
    	request.setAttribute("bm", bm);
    	request.setAttribute("rs", rs);
    	appendOperQx(request, "pjpy_tyb_zhszcpSjwh.do?dmlb=" + dmlb + "&dm=" + dm);
    	//存储表、视图信息
    	appendTableXx(request, bm, bm.substring(5,bm.length()));
    	request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
		return mapping.findForward("zhszcpAdd");
	}
    
    /**
     * 综合素质测评四项数据修改操作
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	PjpyZhszcpwhActionForm model = (PjpyZhszcpwhActionForm)form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	BdszService bdszService = new BdszService();
    	//操作类型 查看或审核
    	String act = request.getParameter("act");
    	//主键
    	String pkValue = request.getParameter("pkValue");
    	//视图名称
    	String viewName = request.getParameter("bm");
    	//代码类别
    	String dmlb = request.getParameter("dmlb");
    	//代码
    	String dm = request.getParameter("dm");
        //审核级别
        String shjb = request.getParameter("shjb");
    	//表名称 通过视图名称截取
    	String tableName = StringUtils.isNotNull(viewName) 
    	                   ? viewName.replace("view_", "")
    	                   : "";   
    	//初始化model中的部分属性值
    	appendDefault(model, request, shjb);
    	if(SAVE.equalsIgnoreCase(act)){//信息保存
    		HashMap<String, String> tableMap = new HashMap<String, String>();
    		tableMap.put("tableName", tableName);
    		tableMap.put("viewName", viewName);
    		
    		HashMap<String, String> valueMap = new HashMap<String, String>();
    		List<HashMap<String, String>> zdyzd = bdszService.getZdydz(viewName, "pjpy");
    		valueMap = getParamterMap(request, zdyzd);//获取页面字段值
    		valueMap.putAll(getValueMap(request, PRIFIX_SAVE));//获取save前缀字段值
    		valueMap.put("pkValue", pkValue);
    		//保存附表信息
    		boolean result = service.saveZdyzdNrByFlag(viewName, new HashMap[]{valueMap});
        	request.setAttribute("result", result ? "yes" : "no");
    	}
    	//根据主键查询出数据
    	tableName = service.selectTableExists(tableName) ? tableName : viewName;
    	selectPageDataByOne(request,tableName, viewName, pkValue);
    	HashMap<String, String> rs =(HashMap<String, String>)request.getAttribute("rs");
    	rs.putAll(service.queryZdyzdNr(viewName, pkValue));//自定义字段信息
    	rs.putAll(CommonQueryDAO.commonQueryOne("view_xsjbxx",
				new String[] {"xz" }, "xh", rs.get("xh")));
    	
    	//绑定字段信息,用于页面自定义标签显示数据 
    	bdszService.getBdZd(viewName, "pjpy", model);    	
    	request.setAttribute("act", act);
    	appendOperQx(request, "pjpy_tyb_zhszcpSjwh.do?dmlb=" + dmlb + "&dm=" + dm);
    	//存储表、视图信息
    	appendTableXx(request, viewName, viewName);
    	request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
    	FormModleCommon.commonRequestSet(request);
    	request.setAttribute("shjb", shjb);
    	request.setAttribute("bm", viewName);
    	return mapping.findForward("zhszcpModi");
    }
    
    
    /**
     * 综合素质测评审核
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpSjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	PjpyZhszcpwhActionForm actionForm = (PjpyZhszcpwhActionForm) form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
        PjpyPjzqszService pjzqService = new PjpyPjzqszService(); 
        PjpyZctjszService tjszService = new PjpyZctjszService();        
        
        //操作类型
        String act = request.getParameter("act");
        //项目代码级别,2,3,4级
        String dmlb = request.getParameter("dmlb");
        //项目代码
        String dm = request.getParameter("dm");
        //表名
        String bm = request.getParameter("bm");
        //审核级别
        String shjb = tjszService.queryZhszcpxmShjb(dm, bm);
        
        User user = getUser(request);
        
        if (UserTypePd.isXy(user.getUserType()) && !UserTypePd.isFdy(user.getIsFdy())) {
        	actionForm.setXydm(user.getUserDep());
        }
        
        List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>(); 
        List<String[]> rs = new ArrayList<String[]>();
        List<HashMap<String, String>> titleList = tjszService.queryZcDmxxList(dm);
        
        //学院用户默认查询自己学院数据
        appendDefault(actionForm, request, shjb); 
        //审核
        if(SH_TG.equalsIgnoreCase(act) || SH_BTG.equalsIgnoreCase(act)){
        	//根据视图名获取表名称
        	String tableName = bm.substring(5,bm.length());
        	//主键信息
        	HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
        	//获取审核字段和字段值
        	HashMap<String, String> valueMap = new HashMap<String, String>();
        	valueMap = getZhszcpShzdxx(actionForm, shjb, act);
        	//审核操作
        	auditingBatchOperation(request, primaryMap, valueMap, tableName);
        }
        if (QUERY.equalsIgnoreCase(act)) {
        	//查询数据操作
        	PjpyZhszcpModel model = new PjpyZhszcpModel();
    		BeanUtils.copyProperties(model, actionForm);
    		
    		//默认的审核查询条件
    		appendShtj(model,request,shjb);
    		
			// 自定义字段列表
			HashMap<String, String[]> zdyzdMap = service.getZdyzdMap(bm);
			
			//查询主表的字段信息
			BasicService basicService = new BasicService();
			String[] zbzdArray = basicService.getTableColumn(bm);
			
			// 查询显示字段列表
			HashMap<String, String[]> cxzdMap = service.getPjpyCxzdMap(bm,shjb);
			topTr = service.getZhszcpTitle(cxzdMap);
			rs = service.queryZhszcpResult(model, cxzdMap,zdyzdMap, bm, dm, zbzdArray);
		}
        
        // 设置评奖周期
        request.setAttribute("pjzq", pjzqService.getPjzq());

        //页签列表
        request.setAttribute("pages", titleList);
        request.setAttribute("bm", bm);
        request.setAttribute("dm", dm);
        shjb = StringUtils.isNull(shjb) ? (titleList != null && titleList.size() > 0 ? titleList.get(0).get("shjb") : shjb) : shjb;
        request.setAttribute("shjb", shjb);
        
        
        //将读写权跟title信息放到request中
        appendTableXx(request, bm, bm);
        appendOperQx(request, "pjpy_tyb_zhszcpSjsh.do?dmlb=" + dmlb + "&dm=" + dm);// 设置
        appendQryResult(request, topTr, rs);
        FormModleCommon.commonRequestSet(request);
        FormModleCommon.setNjXyZyBjListForFdyBzr(request);
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
        //审核列表
        request.setAttribute("chkList", service.getChkList(3));
    
		return mapping.findForward("zhszcpSh");
	}
    
    /**
     * 综合素质测评单条记录审核
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpShOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	PjpyZhszcpwhActionForm model = (PjpyZhszcpwhActionForm)form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	BdszService bdszService = new BdszService();
    	//操作类型 查看或审核
    	String act = request.getParameter("act");
    	//主键
    	String pkValue = request.getParameter("pkValue");
    	//视图名称
    	String viewName = request.getParameter("bm");
    	//代码类别
    	String dmlb = request.getParameter("dmlb");
    	//代码
    	String dm = request.getParameter("dm");
        //审核级别
        String shjb = request.getParameter("shjb");
    	//表名称 通过视图名称截取
    	String tableName = StringUtils.isNotNull(viewName) 
    	                   ? viewName.substring(5,viewName.length())
    	                   : "";   
    	//初始化model中的部分属性值
    	appendDefault(model, request, shjb);
    	
    	if(SAVE.equalsIgnoreCase(act)){//信息保存
        	//主键信息
        	HashMap<String, String[]> primaryMap = new HashMap<String, String[]>();
        	primaryMap.put("cbv", new String[]{pkValue});
        	//获取审核字段和字段值
        	HashMap<String, String> valueMap = new HashMap<String, String>();
        	valueMap = getValueMap(request, PRIFIX_SAVE);
        	valueMap.put(getShzd(model, shjb)+"sj", GetTime.getNowTime2());//审核时间
        	//审核操作
        	auditingBatchOperation(request, primaryMap, valueMap, tableName);
    	}
    	
    	//根据主键查询出数据
    	selectPageDataByOne(request,tableName, viewName, pkValue);
    	HashMap<String, String> rs =(HashMap<String, String>)request.getAttribute("rs");
    	rs.putAll(service.queryZdyzdNr(viewName, pkValue));//自定义字段信息
    	rs.putAll(CommonQueryDAO.commonQueryOne("view_xsjbxx",
				new String[] {"xz" }, "xh", rs.get("xh")));
    	
    	//绑定字段信息,用于页面自定义标签显示数据 
    	bdszService.getBdZd(viewName, "pjpy", model);
    	
    	request.setAttribute("act", act);
    	//审核类型列表数据
    	request.setAttribute("chkList", service.getChkList(3));
    	appendOperQx(request, "pjpy_tyb_zhszcpSjsh.do?dmlb=" + dmlb + "&dm=" + dm);
    	//存储表、视图信息
    	appendTableXx(request, viewName, viewName);
    	request.setAttribute("shjb", shjb);
    	request.setAttribute("bm", viewName);
    	request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
        appendOperQx(request, "pjpy_tyb_zhszcpSjsh.do?dmlb=" + dmlb + "&dm=" + dm);// 设置
        FormModleCommon.commonRequestSet(request);
    	return mapping.findForward("zhszcpShOne");
    }
    
    
    /**
     * 增加审核条件
     * @param request
     * @param shjb
     * */
    public void appendShtj(PjpyZhszcpModel model,HttpServletRequest request,String shjb){
    	String userType = getSessionAttributeValue(request, "userType");
    	String userDep = getSessionAttributeValue(request, "userDep");
    	if("3".equalsIgnoreCase(shjb)){//三级审核
    		if ("xy".equalsIgnoreCase(userType)
					&& !"true".equalsIgnoreCase(getSessionAttributeValue(
							request, "isFdy"))) {
				model.setFdysh("通过");
				model.setXydm(userDep);
				model.setQueryequals_xydm(userDep);
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				model.setXysh("通过");
			}
    	}
    	if("2".equalsIgnoreCase(shjb)){//二级审核
    		if(!"xy".equalsIgnoreCase(userType)){
    			model.setXysh("通过");
    		}else {
    			model.setXydm(userDep);
    			model.setQueryequals_xydm(userDep);
    		}
    	}
    	model.setShjb(new String[]{shjb});    	
    }
    
    /**
     * 加载用户相关信息
     * @param model
     * @param request
     * */
    public void addpendUserInfo(PjpyZhszcpwhActionForm model,HttpServletRequest request){
    	String userType = getSessionAttributeValue(request, "userType");
    	//用户信息增加到form中
    	model.setUserType(userType);
    	model.setUserName(getSessionAttributeValue(request, "userName"));
    	model.setIsFdy(getSessionAttributeValue(request, "isFdy"));
    }
    
    /**
     * 增加默认条件
     * @param request
     * @param shjb
     * */
    public void appendDefault(PjpyZhszcpwhActionForm model,HttpServletRequest request,String shjb){
    	String userType = getSessionAttributeValue(request, "userType");
    	String userDep = getSessionAttributeValue(request, "userDep");
    	//用户信息增加到form中
    	addpendUserInfo(model, request);    	
    	
    	if("3".equalsIgnoreCase(shjb)){//三级审核
    		if("xy".equalsIgnoreCase(userType) && "false".equalsIgnoreCase(getSessionAttributeValue(request, "isFdy"))){    			
    			model.setXydm(userDep);
    			model.setQueryequals_xydm(userDep);
    		}
    	}
    	if("2".equalsIgnoreCase(shjb)){//二级审核
    		if(!"xy".equalsIgnoreCase(userType)){
    			model.setXysh("通过");
    		}else {
    			model.setXydm(userDep);
    			model.setQueryequals_xydm(userDep);
    		}
    	}
    	
    }
    
     /**
     * 获取审核字段信息
     * @param actionForm
     * @param shjb
     * @param act
     * @return HashMap<String, String>
     * */
   public HashMap<String, String> getZhszcpShzdxx(PjpyZhszcpwhActionForm actionForm, 
		                                          String shjb, 
		                                          String act){
	   
	   HashMap<String, String> valueMap = new HashMap<String, String>();
	   act = SH_TG.equalsIgnoreCase(act) ? "通过" : "不通过";//审核结果
	   String shzd = getShzd(actionForm, shjb);//获取审核字段
	   
	   valueMap.put(shzd, act);//审核结果
	   valueMap.put(shzd+"sj", GetTime.getNowTime2());//审核时间
	   
	   return valueMap;
   }
   
   /**
    * 获取当前用户要操作的审核字段
    * @param actionForm
    * @param shjb
    * @return String
    * */
   public String getShzd(PjpyZhszcpwhActionForm actionForm, 
           				 String shjb){
	   String isFdy = actionForm.getIsFdy();//辅导员
	   String userType = actionForm.getUserType();//用户类型
	   String shzd = "xxsh";//默认为学校
	   
	   
	   if("3".equalsIgnoreCase(shjb)){//三级审核
		   if(UserTypePd.isFdy(isFdy)){//辅导员
			   shzd = "fdysh";
		   }else if(UserTypePd.isXy(userType)){//学院
			   shzd = "xysh";
		   }
	   }
	   if("2".equalsIgnoreCase(shjb)){//二级审核
		   if(UserTypePd.isXy(userType)){//学院
			   shzd = "xysh";
		   }
	   }
	   return shzd;
   }
   
   /**
    * 获取页面自定义字段的值
    * @param request
    * @param zdxxList
    * @return HashMap<String, String>
    * */
   public HashMap<String, String> getParamterMap(HttpServletRequest request,
			List<HashMap<String, String>> zdxxList) {
	   HashMap<String, String> map = new HashMap<String, String>();
	   for(HashMap<String, String> zdxx : zdxxList){
		   map.put(zdxx.get("zdid"), request.getParameter(zdxx.get("zdid")));
	   }
	   return map;
   }
   
   /**
    * 获取页面自定义字段的数组值  
    * @param request
    * @param zdxxList
    * @return HashMap<String, String>
    * */
   public HashMap<String, String[]> getParamterMapArray(
			HttpServletRequest request, List<HashMap<String, String>> zdxxList) {
	   HashMap<String, String[]> map = new HashMap<String, String[]>();
	   for(HashMap<String, String> zdxx : zdxxList){
		   map.put(zdxx.get("zdid"), request.getParameterValues(zdxx.get("zdid")));
	   }
	   return map;
   }
    
    /**
     * 综合素质测评自定义字段展现
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zhszcpZdzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
    	
    	PjpyZhszcpwhActionForm tybForm = (PjpyZhszcpwhActionForm) form;
    	
    	PjpyZctjszService tjszService = new PjpyZctjszService();
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	
    	//表名
    	String bm = request.getParameter("bm");  
    	String dm = request.getParameter("dm");
    	String dmlb = request.getParameter("dmlb");
    	String xh = request.getParameter("xh");
    	
    	//保存数据操作
    	if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
    		//获取主表名称
    		//String realTable = StringUtils.isNotNull(bm) ? bm.substring(5, bm.length()) : "";
    		//自定义字段列表
			List<HashMap<String, String>> zdyzdMap = service.getZdyZdList(bm);
			//字段列表值
			HashMap<String, String[]> valueMap = getParamterMapArray(request, zdyzdMap);
			
			PjpyZhszcpModel model = new PjpyZhszcpModel();
			BeanUtils.copyProperties(model, tybForm);
			
			boolean result = service.saveZhszcpInfo(model, valueMap, bm,
					zdyzdMap);
			//对于保存失败的数据进行返回并提示
			request.setAttribute("result",result);
//			request.setAttribute("errmsg", "操作完成,其中第"
//					+ (StringUtils.isNotNull(result) ? result.substring(0,
//							result.length() - 1) : "")
//					+ "条数据操作失败,可能是数据库已存在该记录.");
    	}
    		
    	
    	//自定义字段名称列表 用于输出表头
    	List<HashMap<String, String>> rs = tjszService.queryZhszcpxm( bm); 
    	request.setAttribute("rs", rs);
    	
    	//将查询出来的自定义字段封装成JSON数据对象
    	request.setAttribute("jsonStr", service.appendJsOjbectByZdyzdxx(rs));
    	
    	request.setAttribute("dm", dm);
    	request.setAttribute("bm", bm);
    	request.setAttribute("dmlb", dmlb);
    	request.setAttribute("xh", xh);
    	return mapping.findForward("zhszcpZdzx");
    }
    
    /**
     * 综测总分统计查询与汇总
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zhszcpHz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
    	PjpyZhszcpwhActionForm tybForm = (PjpyZhszcpwhActionForm)form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	PjpyPjzqszService pjzqService = new PjpyPjzqszService();
//    	PjpyZctjszService tjszService = new PjpyZctjszService();
    	String zczq = pjzqService.getZczq();//获取综测周期
  
    	List<String[]> list = new ArrayList<String[]>();
    	List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
    	
    	 User user = getUser(request);
         
         if (UserTypePd.isXy(user.getUserType())) {
        	 tybForm.setQueryequals_xydm(user.getUserDep());
         } else if (GlobalsVariable.XTDM_STU
				.equalsIgnoreCase(user.getUserType())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(user
						.getUserType())) {
			request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
		}
         
    	
    	if (QUERY.equalsIgnoreCase(request.getParameter("act"))) {
    		HashMap<String, String> map = new HashMap<String, String>();
    		map.put("2", request.getParameter("queryequals_ejdm"));//二级	
    		map.put("3", request.getParameter("queryequals_sjdm"));//三级代码	
    		map.put("4", request.getParameter("queryequals_sidm"));//四级代码
    		String jb = tybForm.getQueryequals_jb();
    		
    		HashMap<String, String[]> colMap = service.getZhszcpzfExpTitle(zczq,
    				jb, map, true, tybForm);//获取导出的字段信息
    		String[] columnCN = colMap.get("cn");//表头

    		//查询导出的数据
    		list = service.queryZhszcpzfForExp(zczq, jb, map, colMap
    				.get("en"), tybForm, true);

    		topTr = service.queryZhszcpTitle(columnCN);
    		//this.selectPageDataByPagination(request, form, "pjpy_zhszcpb", "view_pjpy_zhszcpb", new String[]{"pkValue", "行号", "nd", "xn", "xq", "xh", "xm", "bjmc", "mc", "fs", "pm"});
    	}
    	
    	//查询所有的综测代码信息
    	List<HashMap<String, String>> dmList = service.queryZhszcpdmList();
    	//分类不同级别的代码信息
    	String ejdm = tybForm.getQueryequals_ejdm();
    	String sjdm = tybForm.getQueryequals_sjdm();
    	request.setAttribute("ejdmList", service.queryZhszcpdmList(dmList, "2"));//二级
    	request.setAttribute("sajdmList",
				StringUtils.isNotNull(ejdm) ? service.queryZhszcpdmList("3", ejdm)
						: service.queryZhszcpdmList(dmList, "3"));// 三级
    	request.setAttribute("sijdmList",
				StringUtils.isNotNull(sjdm) ? service.queryZhszcpdmList("4", sjdm)
						: service.queryZhszcpdmList(dmList, "4"));//四级
    	
    	//加载学院，专业，班级，年级，学年，学期，年度列表
    	FormModleCommon.setNjXyZyBjListForFdyBzr(request);
    	FormModleCommon.setNdXnXqList(request);
    	
    	//设置综测周期
        request.setAttribute("pjzq", zczq);
        request.setAttribute("pjpyzq", pjzqService.getPjzq());//设置评奖周期
        appendOperQx(request, "pjpy_tyb_zhszcp.do");
        
        //设置默认的学年，学期，年度选项
        setDefaultValue(tybForm);
        appendTableXx(request, "view_pjpy_zhszcpb", "pjpy_zhszcpb");
        
        appendQryResult(request, topTr, list);
        
    	return mapping.findForward("zhszcpHz");
    }

    //设置默认的学年，学期，年度选项
	private void setDefaultValue(PjpyZhszcpwhActionForm tybForm) {
		if (StringUtils.isNull(tybForm.getQueryequals_xn())) {
        	tybForm.setQueryequals_xn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_nd())) {
        	tybForm.setQueryequals_nd(Base.getJxjsqnd());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_xq())) {
        	tybForm.setQueryequals_xq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getXn())) {
        	tybForm.setXn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getXq())) {
        	tybForm.setXq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getNd())) {
        	tybForm.setNd(Base.getJxjsqnd());
        }
	}
    
	/**
	 * 自动计算综测总分信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward countZccj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyZhszcpwhActionForm tybForm = (PjpyZhszcpwhActionForm) form;
		PjpyZhszcpService service = new PjpyZhszcpService();
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		setDefaultValue(tybForm);
		
		User user = getUser(request);
        if (UserTypePd.isXy(user.getUserType())) {
        	 tybForm.setQueryequals_xydm(user.getUserDep());
        }
		
		BeanUtils.copyProperties(model, tybForm);
		model.setYjdm(ZHSZCP_YJDM);
		model.setPjzq(request.getParameter("pjpyzq"));
		model.setZczq(request.getParameter("pjzq"));
		
		//自动计算
		boolean result = service.countZhszcpzf(model);
		request.setAttribute("result", result);
		
		zhszcpHz(mapping, form, request, response);
		return mapping.findForward("zhszcpHz");
	}
	
	/**
     * 综合素质测评总分导出
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpzfExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyZhszcpwhActionForm model = (PjpyZhszcpwhActionForm) form;
		PjpyPjzqszService pjzqService = new PjpyPjzqszService();
		PjpyZhszcpService service = new PjpyZhszcpService();
		
		User user = getUser(request);
         
         if (UserTypePd.isXy(user.getUserType())) {
        	 model.setQueryequals_xydm(user.getUserDep());
         }
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("2", request.getParameter("queryequals_ejdm"));//二级	
		map.put("3", request.getParameter("queryequals_sjdm"));//三级代码	
		map.put("4", request.getParameter("queryequals_fjdm"));//四级代码
		String jb = model.getQueryequals_jb();

		String pjzq = pjzqService.getPjzq();
		HashMap<String, String[]> colMap = service.getZhszcpzfExpTitle(pjzq,
				jb, map, false, model);//获取导出的字段信息
		String[] columnCN = colMap.get("cn");//表头

		//查询导出的数据
		List<String[]> list = service.queryZhszcpzfForExp(pjzq, jb, map, colMap
				.get("en"), model, false);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list, columnCN, columnCN, response.getOutputStream());
		return mapping.findForward("");
	}
    
   /**
     * 综合测评成绩统计
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zccjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	PjpyTyService service = new PjpyTyService();
    	PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm)form;
    	myForm.setQueryequals_nd(Base.getJxjsqnd());
		myForm.setQueryequals_xn(Base.getJxjsqxn());
		myForm.setQueryequals_xq(Base.getJxjsqxq());
		
    	//学院专业班级年级列表
    	FormModleCommon.setNjXyZyBjListForFdyBzr(request);
    	//学年年度学期列表
		FormModleCommon.setNdXnXqList(request);
    	//评奖周期
    	request.setAttribute("pjzq", service.getZhcpSqzq());
    	request.setAttribute("path", "zccjtj.do");
    	FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zccjtj");
	}    
    
    /**
     * 综合测评成绩统计报表打印
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward printZhcpcjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm)form;
    	String type = request.getParameter("type");//对应的报表
    	PjpyNtzydxService ntzyService = new PjpyNtzydxService();
    	
    	response.reset();
		response.setContentType("application/vnd.ms-excel");
		//打印综合测评统计表报
		ntzyService.printZhcptjbb(myForm,type,response.getOutputStream());
		return mapping.findForward("");
	}
   
    /**
     * 导出学生的相片信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward picexport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
    	
    	ImageExportUtils img = new ImageExportUtils();
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	//String[] xhArray = service.queryStuXh("302063101");
    	String[] xhArray = {"3060823107",
    			"3060601049",
    			"3060831028",
    			"3060902142",
    			"3061401031",
    			"3061511032",
    			"3061511038",
    			"3061904047",
    			"3063027071",
    			"3063027090",
    			"3063027096",
    			"3070902103",
    			"3070911004",
    			"3070921069",
    			"3071001249",
    			"3071401037",
    			"3071511047",
    			"3071511052",
    			"3071511054",
    			"3071511056",
    			"3080102424",
    			"3080102998"
    		};
    	img.stuImageExport(xhArray, response);
    	
    	return mapping.findForward("");
    }
}
