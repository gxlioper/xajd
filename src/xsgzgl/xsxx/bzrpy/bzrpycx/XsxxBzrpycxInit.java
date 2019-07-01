package xsgzgl.xsxx.bzrpy.bzrpycx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.xsxx.dagl.guizdx.XsxxGuizdxDaglForm;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.xsxx.bzrpy.BasicModel;

public class XsxxBzrpycxInit {
	
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initBzrpy(RequestForm rForm, BasicModel model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = model.getPath();
		// ����ֶ�
		String[] colList = model.getColList(); 
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
		
		if ("xsxx_bzrpycx.do?method=bzrpycxManage".equalsIgnoreCase(path)) {
			cn= new String[] {"ѧ��","ѧ��", "����", "�꼶",  "�༶","����", "������","����ʱ��"}; 
		}
		
		return dao.arrayToList(en, cn);
	}

}
