package xgxt.audit.splc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.qsgl.GyglQsglForm;

public class SplcInit {
	
	public void initForSplc(RequestForm rForm, GyglQsglForm model,
			HttpServletRequest request) {
		
		
		// ����ģ��
		String gnmk = "audit";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwhSplc.do";
		// ����ֶ�
		String[] colList = new String[] { "id", "djlx", "mc", "ms", "sfmr" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList);
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
		String showNum = "4";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	
	private List<HashMap<String, String>> getTopTr(String[] colList) {

		String[] colListCN = null;

		colListCN = new String[] { "ID", "��������", "����", "����", "�Ƿ�Ĭ��" };

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

}
