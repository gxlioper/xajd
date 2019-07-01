package xgxt.pjpy.tjzy.zjff;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xsgzgl.comm.BasicModel;

public class PjpyZjffInit {
	/**
	 * �������ͳ�ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZjffResult(RequestForm rForm, BasicModel model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "pjpy";
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
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = "7";
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
		
		if ("pjpy_tjzy_zjff.do?method=zjffManage".equalsIgnoreCase(path)) {
			cn= new String[] {"","ѧ��","����", "�꼶",  "�༶","��Ŀ����", "��Ŀ���","����ɹ�"}; 
		}
		
		return dao.arrayToList(en, cn);
	}
}
