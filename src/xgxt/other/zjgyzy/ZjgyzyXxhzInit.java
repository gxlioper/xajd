package xgxt.other.zjgyzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;

public class ZjgyzyXxhzInit {

	ZjgyzyXxhzService service =new ZjgyzyXxhzService();
	
	/**
	 * ѧ��������Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initXsrsManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
	
		// ����ģ��
		String gnmk = "xsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("xsrsPrint.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
	
	}
	
	/**
	 * ѧ��ס����Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initXszsManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		
		// ����ģ��
		String gnmk = "gygl";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("xszsPrint.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs","rznsrs","rznvsrs","wrznsrs","wrznvsrs"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
	
	
	}
	

	/**
	 * ѧ��������Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initXsdaManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {

		// ����ģ��
		String gnmk = "xsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("xsdaPrint.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList ={"r","xymc","bjmc","zrs","nsrs","nvsrs"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
	}
	
	/**
	 * ����Ա��Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initFdyManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		

		// ����ģ��
		String gnmk = "xsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("fdyPrint.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList ={"r","xm","xb","jg","zzmm","dwlbdm","csrq","xl","xw","sxzy",
				"byyx","zc","zwmc","cjgzrq","szgzsj","bmmc","kzzd4","fblw"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
	
	}
	
	/**
	 * ����Ա������Ƹ��һ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initPyqkManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		

		// ����ģ��
		String gnmk = "xsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("prqkPrint.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList ={"r","nd","xn","xqmc","xymc","bjmc","fdy","bzr"}; 
		
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
	}
	
	/**
	 * Υ�ʹ�����Ϣһ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void initWjcfManage(RequestForm rForm, BasicModel model,
			HttpServletRequest request,User user)
			throws Exception {
		

		// ����ģ��
		String gnmk = "xsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		model.setPath("wjcfPrint.do");
		String path = model.getPath();
		
		// ����ֶ�
		String[] colList ={"r","nd","xn","xqmc","bjmc","xh","xm","cflbmc","cfsj","cfyymc","cfwh"}; 
		model.setColList(colList);
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,user,path);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
	}
	
	/**
	 * ����ģ��·����ȡ��ͷ��Ϣ
	 * @param colList
	 * @param path
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String[] colList,User user, String path) {

		DAO dao=DAO.getInstance();
		
		String[] en = colList;
		
		String[] cn = null;
		
		String userType=user.getUserType();
		
		// --------------------��ѯʦ���� --------------------------
		if ("xsrsPrint.do".equalsIgnoreCase(path)) {
			
			cn= new String[] {"���","��Ժ","�༶","����","����","Ů��"}; 
		
		}else if("xszsPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"���","��Ժ","�༶","����","����","Ů��","��ס����","��סŮ��","δ��ס����","δ��סŮ��"}; 
		}else if("xsdaPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"���","��Ժ","�༶","����","����","Ů��"}; 
		}else if("fdyPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"���","����","�Ա�","����","������ò","����״��","��������","ѧ��","ѧλ","��ѧרҵ",
					"��ҵԺУ","ְ��","ְ��","�μӹ���ʱ��","���¸���Ա����ʱ��","���ڷ�Ժ","������","�������"}; 
		}else if("prqkPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"���","���","ѧ��","ѧ��","���ڷ�Ժ","�༶","����Ա","������"};

		}else if("wjcfPrint.do".equalsIgnoreCase(path)){
			
			cn= new String[] {"���","���","ѧ��","ѧ��","�༶","ѧ��","����","��������","��������","����ԭ��","�����ĺ�"}; 
		}
		
		return dao.arrayToList(en, cn);
	}
}
