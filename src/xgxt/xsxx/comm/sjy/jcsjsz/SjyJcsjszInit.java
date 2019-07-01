package xgxt.xsxx.comm.sjy.jcsjsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class SjyJcsjszInit {

	/**
	 * ����Դ_������������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJcsjszRForm(RequestForm rForm, SjyJcsjszForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xsxx";
		// ϵͳ�ֶ�����
		String menu = "jcsjsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_sjy_jcsjsz.do";
		// ��
		String tableName = "xg_view_xsxx_zdszb";
		// ����ֶ�
		String[] colList = new String[] { "search_zd", "search_zdm",
				"search_ymxs", "search_xgwz", "search_lrxz", "search_wkxz",
				"search_lrxs", "search_lybm", "search_sfqy" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		// ��ҳ
		// Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//��ʾ����
		String showNum = "8";
		commSetting.setShowNum(showNum);
		
		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ����Դ_������������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJcsjszGuideRForm(RequestForm rForm, SjyJcsjszForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xsxx";
		// ϵͳ�ֶ�����
		String menu = "jcsjsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_sjy_jcsjsz.do";
		// ��������
		String step = request.getParameter("step");
		// ���ղ�
		String step_last = "step" + model.getStepNum();

		String[] qtzd = new String[] { "step", "step_last" };
		String[] qtzdz = new String[] { step, step_last };
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);

	}
}
