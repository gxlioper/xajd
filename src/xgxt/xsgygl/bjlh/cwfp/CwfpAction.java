package xgxt.xsgygl.bjlh.cwfp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.GetDataInfo;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.xsgygl.dao.GyglShareDAO;

public class CwfpAction extends DispatchAction {
	
	/**
	 * 床位分配
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cwfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		CwfpService service = new CwfpService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;

		if (userType.equalsIgnoreCase("xy")) {
			myForm.setXydm(userDep);
		}
		String fpbj = "qrz";
		if (!StringUtils.isNull(myForm.getFpbj())) {
			fpbj = myForm.getFpbj();
		} else {
			fpbj = userDep;
		}

		if (fpbj.equalsIgnoreCase(BjlhGyglDAO.CJDM)) {
			myForm.setFplx("成教生");
			myForm.setLx("成教生");
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.KYDM)) {
			myForm.setFplx("研究生");
			myForm.setLx("研究生");
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TWDM)) {
			myForm.setFplx("团委生");
			myForm.setLx("团委生");
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TYDM)) {
			myForm.setFplx("体优生");
			myForm.setLx("体优生");
		} else {
			myForm.setFplx("全日制");
		}
		myForm.setFpbj(fpbj);
		request.setAttribute("fplx", myForm.getFplx());

		String[] tmp = service.getCwFpZsData(myForm.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(), fpbj);// 未划分总数(人)默认为全日制学生
		if (tmp != null) {
			request.setAttribute("total", tmp[0]);
			request.setAttribute("boy", tmp[1]);
			request.setAttribute("girl", tmp[2]);
		} else {
			request.setAttribute("total", "0");
			request.setAttribute("boy", "0");
			request.setAttribute("girl", "0");
		}
		String[] tmpT = service.getCwFpYhfcws(myForm.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(), myForm.getLddm(), myForm.getCs(), fpbj);// 已划分总(床)位数(人):
		if (tmpT != null) {
			request.setAttribute("totalBed", tmpT[0]);
			request.setAttribute("boyBed", tmpT[1]);
			request.setAttribute("girlBed", tmpT[2]);
			request.setAttribute("xlBed", tmpT[3]);
		} else {
			request.setAttribute("totalBed", "0");
			request.setAttribute("boyBed", "0");
			request.setAttribute("girlBed", "0");
			request.setAttribute("xlBed", "0");
		}
		tmp = service.getCwFpWhfcws(myForm.getXydm(), myForm.getLddm(), myForm.getCs(), fpbj);// 未划分总(床)位数(人):
		if (tmp != null) {
			request.setAttribute("totalBedF", tmp[0]);
			request.setAttribute("boyBedF", tmp[1]);
			request.setAttribute("girlBedF", tmp[2]);
			request.setAttribute("bgBedF", tmp[3]);
		} else {
			request.setAttribute("totalBedF", "0");
			request.setAttribute("boyBedF", "0");
			request.setAttribute("girlBedF", "0");
			request.setAttribute("bgBedF", "0");
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_cwfp.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		service.setList(myForm, request);
		// 校区列表
		List xiaoqquList = GyglShareDAO.getXiaoQuList(userName);
		request.setAttribute("xiaoqquList", xiaoqquList);
		GetDataInfo getDataIF = new GetDataInfo();
		request.setAttribute("ldList", getDataIF.getSsFpLdList(
				myForm.getXqdm(), myForm.getXb()));// 楼栋列表
		request.setAttribute("lcList", getDataIF
				.getSsFpCsList(myForm.getLddm()));// 楼层列表
		request.setAttribute("fpbjList", service.getFpbjList());// 分配标记列表
		request.setAttribute("commanForm", myForm);
		request.setAttribute("cwxxList", service.getCwFpCwXxList(myForm
				.getXqdm(), myForm.getXb(), myForm.getLddm(), myForm.getCs(),
				fpbj));// 未分配床位信息列表
		request.setAttribute("xsxxList", service.getCwFpXsXxList(myForm
				.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(),
				myForm.getXb(), fpbj));//未分配学生信息列表
		request.setAttribute("yfpxsxxList", service.getCwFpYfpXsXxList(myForm
				.getXydm(), myForm.getZydm(), myForm.getBjdm(), myForm.getNj(),
				myForm.getXb(), myForm.getLddm(), myForm.getCs(), fpbj));// 已分配学生信息列表
		
		return mapping.findForward("cwfpManage");
	}
	
	/**
	 * 保存宿舍分配信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCwfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		
		String fpbj = myForm.getFpbj();
		String fplx = "";

		if (fpbj.equalsIgnoreCase(BjlhGyglDAO.CJDM)) {
			fplx = "成教生";
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.KYDM)) {
			fplx = "研究生";
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TWDM)) {
			fplx = "团委生";
		} else if (fpbj.equalsIgnoreCase(BjlhGyglDAO.TYDM)) {
			fplx = "体优生";
		} else {
			fplx = "全日制";
		}
		
		//要删除的已划分宿舍SQL字符串
		String oldMappingItems = myForm.getOldCondiSqlValue();
		//已划分宿舍SQL字符串
		String newMappingItems = myForm.getConditionSqlValue();
		CwfpService service = new CwfpService();
		boolean result = service.saveCwfpxx(oldMappingItems, newMappingItems, fplx);
		if (result) {
			oldMappingItems=newMappingItems;//更改旧值
			request.setAttribute("doFlag", "ok");
		} else {
			request.setAttribute("doFlag", "no");
		}		
		request.setAttribute("oldMappingItems", oldMappingItems);
		return cwfpManage(mapping, form, request, response);
	}
	
	/**
	 * 保存行李宿舍分配信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXlCwfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		
		String cwList = myForm.getConditionXlValue();
		CwfpService service = new CwfpService();
		boolean result = service.saveXlCwfpxx(cwList);
		if (result) {
			request.setAttribute("doFlag", "ok"); 
		} else {
			request.setAttribute("doFlag", "no");
		}
		return cwfpManage(mapping, form, request, response);
	}
}
