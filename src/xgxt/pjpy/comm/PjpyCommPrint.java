package xgxt.pjpy.comm;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.dtjs.ntzy.pypx.NtzyPypxService;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.pjlc.jgcx.PjpyJgcxForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;

import common.Globals;
import common.PjpyXmdm;
import common.XszzXmdm;

public class PjpyCommPrint extends DispatchAction {

	//б��
	public static String slashes;
	
	static {
		if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
			slashes = "/";
		} else {
			slashes = "\\";
		}
	}
	
	/**
	 * ѧ������_��ӡ
	 * 
	 * @author lyl
	 * @return ActionForward
	 */
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		PjpyCommService service = new PjpyCommService();
		PjpyJgcxForm myForm = (PjpyJgcxForm) form;
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		
		// ================= ����ֵ ==================
		// ѧУ����
		String xxmc = Base.xxmc;
		// ѧУ����
		String xxdm = Base.xxdm;

		// ��Ŀ����
		String xmmc = request.getParameter("jxjName");
		myForm.setXmmc(xmmc);
		request.setAttribute("xmmc", xmmc);

		// ѧ��
		String xh = request.getParameter("xh");
		if (!Base.isNull(xh)) {
			myForm.setXh(xh);
		}
		// ѧ��
		String xn=request.getParameter("xn");
		
		if(!Base.isNull(xn)){
			xn = request.getParameter("xn").split(" ")[0];
		}else{
			xn = jbszModel.getPjxn();
		}
		
		// ��ת
		String forward = "";
		// ��ӡ������Ϣ
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		// =================end==================

		// ================= ѧ��������Ϣ ==================
		getStuInfo(myForm, map);
		// =================end==================

		// ================= ��Ŀ������Ϣ ==================
		getXmjbxx(myForm, map);
		// =================end==================
		
		//----------ѧ��ְ�񡢰༶�������Ƿ�Υ�͡��۲���Ϣ��������Ŀ�� 2012.3.6 qph-------------------------
		NtzyPypxService ntzyService = new NtzyPypxService();
		map.put("xszw", ntzyService.getZw(xh));
		map.put("bjrs", service.getBjrs(myForm, map));
		map.put("sfwj", service.getSfwj(myForm, map));
		map.putAll(service.getZczfBjpm(myForm, map));
		map.put("bkkms", service.getBkks(myForm, map));
		map.put("wdxfkms", service.getWdxfkms(xh));
		//---------------2012.3.6 qph end-------------------------------
		
		// ================= ��Ŀ������Ϣ ==================
		List<HashMap<String, String>> qtxxList = service.getOtherInfo(xmmc, xh);
		for (int i = 0; i < qtxxList.size(); i++) {
			HashMap<String, String> qtxx = qtxxList.get(i);
			map.put(qtxx.get("zdmc"), qtxx.get("zdz"));
		}
		// =================end==================
		
		// =================��Ŀ������Ϣ ==================
		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjxqmc = jbszModel.getPjxqmc();
		String pjnd = jbszModel.getPjnd();
		
		//map.put("pjxn", pjxn);
		map.put("pjxn", xn);
		map.put("pjnd", pjnd);
		map.put("pjxqmc", pjxqmc);
		// =================end==================
		
		// ================= ��ȡ�۲⼰������Ϣ begin==================
		getZhxxInfo(myForm, map);
		
		// ================= ��ȡ�������� begin==================
		if(Globals.XXDM_HZSFXY.equals(xxdm) || Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			xmmc = myForm.getXmmc();
			map.put("sqly", service.getHzsfSqly(jbszModel,xmmc,xh));
		}
		//================= ��ȡ�۲⼰������Ϣ end==================
		

		// ================= �жϴ�ӡ���ֱ��� ==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();
		rForm.setRs(map);
		request.setAttribute("xscj", service.getXscjList(myForm, map));
		request.setAttribute("pjinfo", service.getPjInfo(myForm, map));
		service.setRequestValue(rForm, request);
		// =================��ʼ��request��ֵ end====================
		
		//���ҵexcel���
		if (Globals.XXDM_TJGYDX.equals(xxdm)){
			return printExcel(myForm, map, response);
		}
	
		// =================��ô�ӡ����·�� ====================
		forward = getPrintJspForward(myForm);
		// ===================end ====================
		
		// =================���ⱨ��·������ ====================
		forward = getSpecialForward(myForm, map, forward);
		// ===================end ====================

		if (Base.isNull(forward)) {
			forward = "/pjpy/comm/print/comm/jxjsqb.jsp";
		} 
		
		return new ActionForward(forward, false);
	}

	/**
	 * ���ѧ����Ϣ
	 * 
	 * @param myForm
	 * @param xmb
	 * @param xh
	 * @param map
	 */
	private void getStuInfo(PjpyJgcxForm myForm, HashMap<String, String> map) {

		XsxxglService stuService = new XsxxglService();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ѧ��
		String xh = myForm.getXh();

		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);

		// ����ѧԺ
		if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			// ������ѧ����
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
	 * �����Ŀ������Ϣ
	 * 
	 * @param myForm
	 * @param xmb
	 * @param xh
	 * @param map
	 */
	private void getXmjbxx(PjpyJgcxForm model, HashMap<String, String> map) {

		PjpyCommService service = new PjpyCommService();
	
		// ��Ŀ������Ϣ
		HashMap<String, String> xmjbInfo = service.getXmjbInfo(model);
		String xmje = xmjbInfo.get("xmje");//��Ŀ���
		xmje = Base.isNull(xmje) ? "" : xmje;
		xmjbInfo.put("xmje", xmje);
		// ��Ŀ�����Ϣ
		HashMap<String, String> xmshInfo = service.getXmshInfo(model);

		map.putAll(xmjbInfo);
		map.putAll(xmshInfo);

	}
	
	/**
	 * ��ȡ�۲⼰������Ϣ
	 * @param model
	 * @param map
	 */
	public void getZhxxInfo(PjpyJgcxForm model, HashMap<String, String> map) {
		PjpyCommService service = new PjpyCommService();
		HashMap<String, String> pjfMap = service.getPjfInfo(model, map);
		//������Ŀ��
		pjfMap.put("bkks", service.getBkks(model, map));
		//�Ƿ�Υ����Ϣ
		//pjfMap.put("sfwj", service.getSfwj(model, map));
		//�����༶����
		pjfMap.put("pjbjrs", service.getBjrsByPjry(model, map));
		//�ܲ����Ϣ
		pjfMap.putAll(service.getZczfBjpm(model, map));
		//�༶�ɲ���Ϣ
		pjfMap.put("bjgb",service.getBjgbInfo(model, map));
		//�����������
		pjfMap.put("sqly", service.getSqly(model,map));
		//�������������Ϣ
		pjfMap.putAll(service.getPjpyShyj(model, map));

		if(Globals.XXDM_GUIZHDX.equalsIgnoreCase(Base.xxdm)){
			//pjfMap.putAll(service.getShxx(model, map));
			//��Ȩƽ����/��Ȩƽ���ɼ�
			pjfMap.putAll(service.getPjcj(model, map));
		}
		
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
			pjfMap.put("tjdj", model.getXmmc().substring(0,2));
		}
		
		map.putAll(pjfMap);
	}
	
	/**
	 * ��ô�ӡ�����·��
	 * 
	 * @param myForm
	 * @param map
	 * @param forward
	 * @throws Exception
	 */
	private String getPrintJspForward(PjpyJgcxForm myForm) throws Exception {
		
		String forward = "";
		
		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmmc = myForm.getXmmc();
		
		if(Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)){//���ݴ�ѧ
			
			if ("ʡ�����ҵ��".equals(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/syxbys.jsp";
			} else if ("У�����ҵ��".equals(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/xyxbys.jsp";
			} else {
				forward = "/pjpy/comm/print/" + xxdm + "/pjpySqb.jsp";
			}
			
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			
			System.out.println("��Ŀ����------" + xmmc + "------------------");
			
			String tjdj=xmmc.length()>2?  xmmc.substring(0,2):xmmc;
			if ("����ѧ��".equalsIgnoreCase(xmmc) 
					|| "����ѧ���ɲ�".equalsIgnoreCase(xmmc)
					|| "����ѧ����".equalsIgnoreCase(xmmc)) {// ��Ŀ����
				
				forward = "/pjpy/comm/print/" + xxdm + "/shxs.jsp";
			} else if ("һ��".equalsIgnoreCase(tjdj) || "����".equalsIgnoreCase(tjdj) || "����".equalsIgnoreCase(tjdj)
					|| "�ص�".equalsIgnoreCase(tjdj)){
				
				forward = "/pjpy/comm/print/" + xxdm + "/yxxsjxj.jsp";
			} else if ("�����ҵ��".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/yxbysb.jsp";
			} 
			
		}else if(Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)){
		
			if ("����ѧ��".equalsIgnoreCase(xmmc)) {// ��Ŀ����
				forward = "/pjpy/comm/print/" + xxdm + "/shxs.jsp";
			} else if ("У�ڽ�ѧ��".equalsIgnoreCase(xmmc) || "ѧ����ѧ��".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/xnjxj.jsp";
			} else if ("����ѧ���ɲ�".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/yxxsgb.jsp";
			} 
			
		}else if(Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)){
		
			if ("����ѧ���ɲ�".equalsIgnoreCase(xmmc)){
				forward = "/pjpy/comm/print/" + xxdm + "/yxxsgb.jsp";
			} 
			
		}
		
		return forward;
	}
	
	/**
	 * �������������Ŀ��·��
	 * 
	 * @param myForm
	 * @param map
	 * @param forward
	 * @throws Exception
	 */
	private String getSpecialForward(PjpyJgcxForm myForm,
			HashMap<String, String> map, String forward) throws Exception {

		// ѧУ����
		String xxdm = Base.xxdm;
		// ��Ŀ����
		String xmmc = myForm.getXmmc();

		// ���ҵ��ѧ��άѧ��
		if (Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {
			if (PjpyXmdm.PJPY_ALJXJSQB.equalsIgnoreCase(xmmc)) {// ������ѧ��
				forward = "/pjpy/comm/print/tjgy/aljxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_DMZZJXJSQB.equalsIgnoreCase(xmmc)) { // ������֯��ѧ��
				forward = "/pjpy/comm/print/tjgy/dmzzjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_FZZGJXJSQB.equalsIgnoreCase(xmmc)) { // ��֮֯�⽱ѧ��
				forward = "/pjpy/comm/print/tjgy/fzzgjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_FTSYJXJSQB.equalsIgnoreCase(xmmc)) { // ����ʵҵ��ѧ��
				forward = "/pjpy/comm/print/tjgy/ftsyjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_JBHJXJSQB.equalsIgnoreCase(xmmc)) { // ��ٺϽ�ѧ��
				forward = "/pjpy/comm/print/tjgy/jbhjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_SMJXJSQB.equalsIgnoreCase(xmmc)) { // ɣ�齱ѧ��
				forward = "/pjpy/comm/print/tjgy/smjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_SWJXJSQB.equalsIgnoreCase(xmmc)) { // ��γ��ѧ��
				forward = "/pjpy/comm/print/tjgy/swjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_TRJXJSQB.equalsIgnoreCase(xmmc)) { // ��Ȼ��ѧ��
				forward = "/pjpy/comm/print/tjgy/trjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_WKCJXJSQB.equalsIgnoreCase(xmmc)) { // ���˲���ѧ��
				forward = "/pjpy/comm/print/tjgy/wkcjxjsqb.jsp";
			} else if (PjpyXmdm.PJPY_WSTJXJSQB.equalsIgnoreCase(xmmc)) { // ��˹�ؽ�ѧ��
				forward = "/pjpy/comm/print/tjgy/wstjxjsqb.jsp";
			}
		}else if(Globals.XXDM_HZSFXY.equals(xxdm)) {
			//����ʦ��ѧԺ��ӡ�ǼǱ�
			if("����ѧ���ɲ�".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/yxxsgb.jsp";
			}else if("����ѧ��".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/yxxs.jsp";
			}else if("��������ѧ���ɲ�".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/qzyxxsgb.jsp";
			}else if("��������ѧ��".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/qzyxxs.jsp";
			}else if("ѧϰ�ر�".equals(xmmc) || "���ܽ�".equals(xmmc) || "�Ƽ���".equals(xmmc) || "���½�".equals(xmmc) || "������".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/dxjxj.jsp";
			}else if("����ѧϰ�ر�".equals(xmmc) || "���漼�ܽ�".equals(xmmc) || "����Ƽ���".equals(xmmc) || "���洴�½�".equals(xmmc) || "����������".equals(xmmc)){
				forward = "/pjpy/comm/print/10347/qzdxjxj.jsp";
			}else {
				if("����".equals(xmmc.substring(0, 2))){
					forward = "/pjpy/comm/print/10347/qzzyjxj.jsp";
				}else{
					forward = "/pjpy/comm/print/10347/zyjxj.jsp";
				}
			}
		}

		return forward;
	}

	
	
	/**
	 * ���ҵ����excel���
	 * @param myForm
	 * @param map
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printExcel(PjpyJgcxForm myForm, Map<String,String> map, HttpServletResponse response)
			throws Exception {
		String xmmc = myForm.getXmmc();
		String nd = Base.currNd;
		map.put("nd",nd);
		
		PjpyPrintService service = new PjpyPrintService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		if ("У�����ҵ��".equals(xmmc)){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/xyxbys.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printYxbys(wwb, map);
		} else if ("������ѧ��".equals(xmmc)){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/aljxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printAljxj(wwb, map);
		} else if ("ɣ�齱ѧ��".equals(xmmc)){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/smjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printSmjxj(wwb, map);
		} else if ("���˲���ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/wkcjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printWkqjxj(wwb, map);
		} else if ("��֮֯�⽱ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/fzzgjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printFzzgjxj(wwb, map);
		}else if ("������֯��ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/dmzzjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printDmzzjxj(wwb, map);
		} else if ("��ٺϽ�ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/jbhjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printJbhjxj(wwb, map);
		} else if ("��γ��ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/swjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printSwjxj(wwb, map);
		} else if ("��Ȼ��ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/trjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printTrjxj(wwb, map);
		} else if ("����ʵҵ��ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/ftsyjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printFtsyjxj(wwb, map);
		} else if (myForm.getXmmc().indexOf("�㴨������ѧ��")!=-1){
			//��� ���㴨������ѧ��(һ��)���еġ�һ��
			map.put("sbdj", myForm.getXmmc().substring("�㴨������ѧ��".length()+1, myForm.getXmmc().length()-2));
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/hcjsjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printHcjsjxj(wwb, map);
		} else if (myForm.getXmmc().indexOf("��˹�ؽ�ѧ��")!=-1){
			//��� ����˹�ؽ�ѧ��(һ��)���еġ�һ����
			map.put("sbdj", myForm.getXmmc().substring("��˹�ؽ�ѧ��".length()+1, myForm.getXmmc().length()-1));
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/wstjxj.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printWstjxj(wwb, map);
		} else if ("У������ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/xjyxxs.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printXjyxxs(wwb, map);
		} else if ("����ѧ��".equals(myForm.getXmmc())){
			String modelPath = servlet.getServletContext().getRealPath("/print/tjgy/yxxs.xls");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.printYxxs(wwb, map);
		} 
		
		return null;
	}

	
	
	
	
}
