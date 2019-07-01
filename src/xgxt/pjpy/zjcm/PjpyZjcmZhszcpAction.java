package xgxt.pjpy.zjcm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyZjcmZhszcpAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	
	private ZhszcpService service = ZhszcpService.getInstance();
	
	/**
	 * 综合素质测评维护首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? "" : session
				.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy") == null ? "" : session
				.getAttribute("isFdy").toString(); 
		String userName = session.getAttribute("userName") == null ? ""
				: session.getAttribute("userName").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zjcmForm.setXydm(xydm);
		} else {
			xydm = zjcmForm.getXydm();
		}
		zydm = zjcmForm.getZydm();
		nj = zjcmForm.getNj();
		String acts = StringUtils.isNull(request.getParameter("act")) ? ""
				: request.getParameter("act");
		String szlx = zjcmForm.getSzlx();// 用于刷新页面的类型
		String realTable = "zjcm_dycjb";
		String titName = "德育成绩维护";
		if (!StringUtils.isNull(szlx)) {
			realTable = service.getTableName(szlx);
			titName = service.getTitNameBytable(realTable);
		} else {
			szlx = "0";
		}
		String act = request.getParameter("go");// 操作标志
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String, String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		if (!StringUtils.isNull(act)) {
			if ("a".equalsIgnoreCase(act)) {// 查询数据
				ZhszcpModel model = new ZhszcpModel();
				BeanUtils.copyProperties(model, zjcmForm);
				titList = service.getTitleName(szlx);
				int count = service.getResultNum(model, "view_" + realTable,
						isFdy, userName);// 获取最大记录数
				rsList = service
						.getResult(model, "view_" + realTable, zjcmForm, isFdy, userName);
				zjcmForm.getPages().setMaxRecord(
						Integer.parseInt(String.valueOf(count)));// 设置最大的记录数
			}
			if ("del".equalsIgnoreCase(acts)) {// 删除数据
				String sFlag = service.delete(zjcmForm.getCbv(), request
						.getParameter("realTable"), request);
				if (StringUtils.isNull(sFlag)) {
					request.setAttribute("deleted", "yes");
				} else {
					request.setAttribute("failinfo", "操作完成,其中第" + sFlag
							+ "条数据操作失败!");
					request.setAttribute("deleted", "no");
				}
			}
		}
		if ("4".equalsIgnoreCase(szlx)) {// 综合素质测评表
			request.setAttribute("zhsz", "yes");
		}
		if ("autocount".equalsIgnoreCase(acts)) {// 自动计算综合测评成绩
				boolean bFlag = service.zhszAutoCount(zjcmForm.getXn(), zjcmForm.getXq(), xydm);
				if (bFlag) {
					request.setAttribute("deleted", "yes");
				} else {
					request.setAttribute("deleted", "no");
				}
		}
		if (StringUtils.isNull(zjcmForm.getXn())) {// 默认为奖学金申请学年
			zjcmForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(zjcmForm.getXq())) {// 默认为奖学金申请学期
			zjcmForm.setXq(Base.getJxjsqxq());
		}
		request.setAttribute("tmp", szlx);
		zjcmForm.setXm(DealString.toGBK(zjcmForm.getXm()));
		appendProperties(request, xydm, zydm, nj);
		appendFdybjList(request);
		appendTableXx(request, "view_" + realTable, realTable);
		appendQryResult(request, titList, rsList);
		request.setAttribute("titname", titName);
		zjcmForm.setSzlx(szlx);
		return mapping.findForward("zhszcppage");
	}

	/**
	 * 素质分增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		//String szlx = request.getParameter("szlx");// 操作表类型
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = getStuInfo(xh);
		}
		String act = request.getParameter("act");
		String tableName = request.getParameter("tableName");
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {// 保存数据
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, zjcmForm);
			boolean bFlag = service.save(model, tableName, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("titname", service.getTitNameBytable(tableName));
		request.setAttribute("tableName", tableName);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		return mapping.findForward("szfaddpage");
	}
	
	/**
	 * 素质分修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String tableName = request.getParameter("tableName");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.view(pkValue, "view_" + tableName);//显示信息
			if (rs != null) {
				zjcmForm.setXn(rs.get("xn"));
				zjcmForm.setXq(rs.get("xq"));
				zjcmForm.setCj(rs.get("cj"));
			}
		}
		String act = request.getParameter("act");
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {//修改信息
			ZhszcpModel model = new ZhszcpModel();
			model.setXn(request.getParameter("xn"));
			model.setXq(request.getParameter("xq"));
			model.setCj(request.getParameter("cj"));
			boolean bFlag = service.update(model, tableName, request, pkValue);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("flag", act);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("titname", service.getTitNameBytable(tableName));//表头信息
		request.setAttribute("tableName", tableName);
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("szfmodipage");
	}
	
	/**
	 * 综合素质比例设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpfBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		HashMap<String, String> rs = service.getZhszcpBlxx();
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {//保存数据
			String pkValue = request.getParameter("pkValue");
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, zjcmForm);
			boolean bFlag = service.saveZhcpbl(pkValue, model, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		if (rs != null) {
			zjcmForm.setDybl(rs.get("dybl"));
			zjcmForm.setZybl(rs.get("zybl"));
			zjcmForm.setTybl(rs.get("tybl"));
			zjcmForm.setSjcxbl(rs.get("sjcxbl"));
			zjcmForm.setCxbl(rs.get("cxbl"));
		}
		request.setAttribute("rs", rs);	
		return mapping.findForward("zhszblszpage");
	}
	
	/**
	 * 奖学金报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = request.getParameter("xh");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rsMap = service.getJxjsqxnxq();
		HashMap<String, String> rs = service.getXy(xh, pkValue);
		if (rs != null) {
			String csrq = rs.get("csrq");//出生日期
			String rxrq = rs.get("rxrq");//入学日期
			String bysj = rs.get("bysj");//毕业时间
			rs.put("csrq", service.returnNy(csrq));
			rs.put("rxrq", service.returnNy(rxrq));
			rs.put("bysj", service.returnNy(bysj));
		}
		
		request.setAttribute("nd", rsMap.get("jxjsqnd"));
		request.setAttribute("rs", rs);
		return mapping.findForward("jxjprint");
	}
}
