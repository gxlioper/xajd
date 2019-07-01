package xgxt.qgzx.zgdzdx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.CommanForm;
import xgxt.qgzx.service.XsgwglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;

public class QgzxGwsqAction extends DispatchAction {

	String writeAble = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String myAction = mapping.getParameter();
		ActionForward myActFwd = null;
		try {
			// 判断用户读写权
			writeAble = Base.getWriteAble(request);
			String dxq = request.getParameter("writeAble");
			if (!"".equalsIgnoreCase(dxq) && dxq != null) {
				writeAble = dxq;
			}

			if ("zgdzdx_gwsq_SaveOne".equalsIgnoreCase(myAction)) {//岗位申请
				myActFwd = zgdzdx_gwsq_SaveOne(mapping, form, request, response);
			}
			
			
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward zgdzdx_gwsq_SaveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxxglService xsxxglService = new XsxxglService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		QgzxZgdzdxService zgdzService = new QgzxZgdzdxService();
		QgzxGwsqService Gwsqservice = new QgzxGwsqService();
		XsgwglService xsgwglService = new XsgwglService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String xq = "";
		
		
		String userType = session.getAttribute("userOnLine").toString();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> rs = new HashMap<String, String>();
		
//		 保存数据
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		//判断学生是否在黑名单中
		if(zgdzService.isHmdMember(xh)){
			request.setAttribute("hmdMember", "true");
			request.setAttribute("result", false);
		}else{			
			// 岗位申请时间
			String[] tmp = Hgsqservice.getStuTimeService();
			String xn = tmp[0];
			String nd = tmp[1];
			xq = tmp[2];
			
			//工作时间保存
			Hgsqservice.updateQgzxTime(xh, request);
			//其他基本信息保存
			
			String gwdmgwsbsj1 = DealString.toGBK(request.getParameter("gwdmgwsbsj1"));
			String gwdmgwsbsj2 = DealString.toGBK(request.getParameter("gwdmgwsbsj2"));
			String gwdmgwsbsj3 = DealString.toGBK(request.getParameter("gwdmgwsbsj3"));
			String []strGw = {gwdmgwsbsj1,gwdmgwsbsj2,gwdmgwsbsj3};
			String lxdh = qgzxhgsqForm.getLxdh();
			String sqly = DealString.toGBK(request.getParameter("xssq"));
			String yhtc = DealString.toGBK(qgzxhgsqForm.getYhtc());
			String bz = DealString.toGBK(qgzxhgsqForm.getBz());
			String zzmmdm = qgzxhgsqForm.getZzmmdm();
			
			for(int i=0;i<strGw.length;i++){
				if(!"".equalsIgnoreCase(strGw[i]) && strGw[i] != null){
					String gw=strGw[i].split("-")[0];
					String sj=strGw[i].split("-")[1];
					
					HashMap<String, String> tmp1 = new HashMap<String, String>();
					tmp1.put("xh", xh);
					tmp1.put("gwdm", gw);
					tmp1.put("gwsbsj", sj);
					tmp1.put("lxdh", lxdh);
					tmp1.put("xn", xn);
					tmp1.put("nd", nd);
					tmp1.put("xq", xq);
					
					tmp1.put("sqly", sqly);
					tmp1.put("xssq", sqly);
					tmp1.put("yhtc", yhtc);
					tmp1.put("bz", bz);
					tmp1.put("zzmmdm", zzmmdm);
					
					boolean res = Gwsqservice.gwxx_saveService(tmp1);
					if(res){
						request.setAttribute("inserted", "ok");
					}else{
						i++;
						request.setAttribute("inserted", "no");
						request.setAttribute("reason", "拟申请岗位"+i+"重复！");
						break;
					}
					rs.putAll(tmp1);
				}
			}
			
			rs.put("gwdmgwsbsj1", gwdmgwsbsj1);
			rs.put("gwdmgwsbsj2", gwdmgwsbsj2);
			rs.put("gwdmgwsbsj3", gwdmgwsbsj3);			
			rs.put("lxdh", lxdh);
		}
		rs.putAll(xsxxglService.selectStuinfo(xh));//查询学生信息
		rs.putAll(xsxxglService.getStuJtxx(xh));//查询学生家庭信息
		rs.put("sfpks", Gwsqservice.checkIsKns(xh) ? "是" : "否");
		rs.put("xqmc", xsxxglService.getXqmc(xq));
		
		request.setAttribute("rs", rs);
		request.setAttribute("gwList", Gwsqservice.getKsqgwList());
		request.setAttribute("zzmmList", Gwsqservice.getZzmmList());
		request.setAttribute("path", "post_stu_apply.do");
		FormModleCommon.commonRequestSet(request);
		xsgwglService.freeTimeTableZgdzdx(xh, request);
		return mapping.findForward("success");
	}
}
