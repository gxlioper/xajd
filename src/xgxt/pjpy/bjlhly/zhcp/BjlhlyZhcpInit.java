package xgxt.pjpy.bjlhly.zhcp;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.cwgl.GyglCwglForm;

public class BjlhlyZhcpInit {
	/**
	 * ��ʼ���۲�����ѯ���������Ϣ
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initZcjgInfo(RequestForm rForm, BjlhlyZhcpForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "zhcp";
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "zhcp_bjlhly_zcjg.do";
		// ����ֶ�
		String[] colList = new String[] {"xh", "xm", "njmc", "bjmc","dycj",
				"decj","dsscj","zpjf","bjpm","zypm" }; 
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
		String showNum = "9";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
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
		
		if ("zhcp_bjlhly_zcjg.do".equalsIgnoreCase(path)) {
			cn= new String[] {"ѧ��", "����", "�꼶", "�༶","��һ�۲�ɼ�",
					"����۲�ɼ�","�����ϳɼ�","�ܳɼ�","�༶����","רҵ����" }; 
		}
		
		return dao.arrayToList(en, cn);
	}

}
