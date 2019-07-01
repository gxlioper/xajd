package xgxt.xszz.commonN05.jtqkdc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.xszz.commonN05.jtqkdc.JtqkdcForm;
import xgxt.studentInfo.service.XsxxglService;
import com.zfsoft.basic.BasicAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: N05学生资助Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-24</p>
 */
public class JtqkdcAction extends BasicAction {
    /**
    * Method jtqkdc
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jtqkdc(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        JtqkdcForm model = (JtqkdcForm)form;
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "xszz_N05_xsjtqkdcb";
        String viewName = "view_xszz_N05_xsjtqkdcb";

        if ("xy".equalsIgnoreCase(session.getAttribute("userType").toString())
            && !"true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
            model.setQueryequals_xydm(session.getAttribute("userDep").toString());
        }

        if("student".equalsIgnoreCase(userOnLine)){//学生登陆
            model.setQuerylike_xh(userName);
        }

        if("go".equalsIgnoreCase(go)){//查询数据
            String[] outputColumn = {"pkValue", "xh", "xm", "xb", "xymc", "zymc", "bjmc", "sfzh", "byxx"};
            selectPageDataByPagination(request, model, tableName, viewName, outputColumn);
        }

        if("del".equalsIgnoreCase(type)){//信息删除
            deleteOperation(request, tableName);
        }

        FormModleCommon.commonRequestSet(request);//将读写权跟title信息放到request中
        //加载学院专业班级列表信息
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        request.setAttribute("realTable", tableName);
        return mapping.findForward("jtqkdc");
    }

    /**
    * Method jtqkdcAdd
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jtqkdcAdd(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();	
        XsxxglService xsxxglService = new XsxxglService();

        String type = request.getParameter("type");
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        HashMap<String, String> rs = new HashMap<String, String>();
        String xh = request.getParameter("xh");

        if("student".equalsIgnoreCase(userOnLine)){//学生登陆
            xh = userName;
        }

        if("save".equalsIgnoreCase(type)){//信息保存
            xh = request.getParameter("save_xh");
            String pkValue = xh;
            insertOperation(request, "xszz_N05_xsjtqkdcb");//插入数据
            selectPageDataByOne(request, "xszz_N05_xsjtqkdcb", "view_xszz_N05_xsjtqkdcb", pkValue);
            rs = (HashMap<String, String>) request.getAttribute("rs");
        }

        rs.putAll(xsxxglService.selectStuinfo(xh));//查询学生基本信息
        rs.putAll(xsxxglService.getStuJtxx(xh));
        rs.put("save_xh", rs.get("xh"));
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//读写权信息
        return mapping.findForward("jtqkdcAdd");
    }

    /**
    * Method jtqkdcModi
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jtqkdcModi(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");

        if("save".equalsIgnoreCase(type)){//信息修改
                updateOperation(request, "xszz_N05_xsjtqkdcb");
        }

        String pkValue = request.getParameter("pkValue");
        selectPageDataByOne(request, "xszz_N05_xsjtqkdcb", "view_xszz_N05_xsjtqkdcb", pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//查询学生基本信息
        rs.putAll(xsxxglService.getStuJtxx(rs.get("xh")));//学生家庭信息
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//读写权信息
        request.setAttribute("type", type);
        return mapping.findForward("jtqkdcModi");
    }

    /**
    * Method jtqkdcExp
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * @throws Exception
    * */
    public ActionForward jtqkdcExp(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String[] outputColumn = { "xh", "xm", "xb", "nj", "xz", "xydm", "zydm", "bjdm", "xymc", "zymc", "bjmc", "sfzh", "rxqhkxz", "jtrks", "byxx", "sfgc", "sfdq", "sflszn", "jtcy1_gzdwmc", "jtcy2_gzdwmc", "jtcy2_nsr", "jtcy2_jkzk", "jtcy3_gzdwmc", "jtcy3_nsr", "jtcy3_jkzk", "jtcy1_nsr", "jtcy1_jkzk", "jtrjnsr", "yhzzqk", "zszrzhqk", "zstfywsj", "ldnlrqk", "jtcysyqk", "jtqzqk", "jtqtqk", "mzbtxdz", "mzbyb", "mzblxdh"};
        expPageData(request, response, "xszz_N05_xsjtqkdcb", "view_xszz_N05_xsjtqkdcb", outputColumn);
        return mapping.findForward("");
    }

    /**
     * Method jtqkdcPrint
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * */
     public ActionForward jtqkdcPrint(ActionMapping mapping, ActionForm form,
         HttpServletRequest request, HttpServletResponse response){
    	 HashMap<String, String> rs = new HashMap<String, String>();
    	 XsxxglService xsxxglService = new XsxxglService();
    	 String pkValue = request.getParameter("pkValue");
    	 String xh = "";
    	 
         selectPageDataByOne(request, "xszz_N05_xsjtqkdcb", "view_xszz_N05_xsjtqkdcb", pkValue);
         rs = (HashMap<String, String>)request.getAttribute("rs");//学生家庭调查情况
         
         xh = rs.get("xh");
         rs.putAll(xsxxglService.selectStuinfo(xh));
         rs.putAll(xsxxglService.getStuJtxx(xh));
         
         //将联系电话分为区号和号码
         String lxdh1 = rs.get("lxdh1");
         String mzblxdh = rs.get("mzblxdh");
         if(StringUtils.isNotNull(lxdh1)){
        	 lxdh1 = lxdh1.replaceAll("-", "-");
        	 String[] tmp = lxdh1.split("-");
        	 if(tmp.length>1){
        		 rs.put("lxdh1qh", tmp[0]);
        		 rs.put("lxdh1", tmp[1]);
        	 }else{
        		 rs.put("lxdh1", tmp[0]);
        	 }
         }
         if(StringUtils.isNotNull(mzblxdh)){
        	 mzblxdh = mzblxdh.replaceAll("-", "-");
        	 String[] tmp = mzblxdh.split("-");
        	 if(tmp.length>1){
        		 rs.put("mzblxdhqh", tmp[0]);
        		 rs.put("mzblxdh", tmp[1]);
        	 }else{
        		 rs.put("mzblxdh", tmp[0]);
        	 }
         }       
         //将家庭成员出生日期转换成年龄
         rs.put("jtcy1_csrq", xsxxglService.changeNl(rs.get("jtcy1_csrq")));
         rs.put("jtcy2_csrq", xsxxglService.changeNl(rs.get("jtcy2_csrq")));
         rs.put("jtcy3_csrq", xsxxglService.changeNl(rs.get("jtcy3_csrq")));
         request.setAttribute("rs", rs);
         return mapping.findForward("jtqkdcPrint");
     }
    
     
	
}
