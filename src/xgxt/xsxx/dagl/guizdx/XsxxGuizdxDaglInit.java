package xgxt.xsxx.dagl.guizdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.bjlhly.zhcp.BjlhlyZhcpForm;

public class XsxxGuizdxDaglInit {
	
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initDalxwhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "zhcp";
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "guizdx_dagl_dalxwh.do";
		// ����ֶ�
		String[] colList = new String[] {"disabled","dm","mc","lx"}; 
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,path);
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
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "2";
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
	 * ��������ά����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initXsdawhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "zhcp";
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "guizdx_dagl_xsda.do";
		// ����ֶ�
		String[] colList = new String[] {"xh", "xm", "nj","bjmc","mc" }; 
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,path);
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
		String showNum = "5";
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
	 * ��У����ά����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZxdawhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

//		 ����ģ��
		String gnmk = "zhcp";
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "guizdx_dagl_zxda.do";
		// ����ֶ�
		String[] colList = new String[] {"xh", "xm", "nj","bjmc","mc" }; 
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,path);
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
		String showNum = "5";
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
	 * ��ҵת��ά����ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initByzdwhInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

//		 ����ģ��
		String gnmk = "zhcp";
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "guizdx_dagl_byzd.do";
		// ����ֶ�
		String[] colList = new String[] {"xh", "xm", "nj", 
				"bjmc","mc","sfbrtj","datddz","jsr" }; 
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,path);
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
		String showNum = "13";
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
	 * ������Ϣ��Ѱ��ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initDacxInfo(RequestForm rForm, XsxxGuizdxDaglForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xsda";
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "guizdx_dagl_dacx.do";
		// ����ֶ�
		String[] colList = new String[] {"xh","xm","nj","bjmc",
				"zxda","xsda","byda","sfbrtj","datddz","jsr"}; 
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList,path);
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
		String showNum = "10";
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
	public List<HashMap<String, String>> getTopTr(String[] colList, String path) {

		DAO dao=DAO.getInstance();
		
		String[] en = colList;
		
		String[] cn = null;
		
		if ("guizdx_dagl_dalxwh.do".equalsIgnoreCase(path)) {
			cn= new String[] {"disabled","����", "����", "����" }; 
		}else if("guizdx_dagl_xsda.do".equalsIgnoreCase(path)){
			cn=new String[]{"ѧ��", "����", "�꼶", "�༶","������Ϣ"};
			
		}else if("guizdx_dagl_zxda.do".equalsIgnoreCase(path)){
			
			cn=new String[]{"ѧ��", "����", "�꼶", "�༶","������Ϣ"};
				
		}else if("guizdx_dagl_byzd.do".equalsIgnoreCase(path)){
			
			cn=new String[]{"ѧ��", "����", "�꼶", 
					"�༶","��ҵת��","�Ƿ����ᵵ","�����ᵵ��ַ","������"};
		}else if("guizdx_dagl_dacx.do".equalsIgnoreCase(path)){
			
			cn=new String[]{"ѧ��","����","�꼶","�༶",
					"��У����","��������","��ҵ����","�Ƿ����ᵵ","����Ͷ�ݵ�ַ","������"};
		}
		
		return dao.arrayToList(en, cn);
	}

}
