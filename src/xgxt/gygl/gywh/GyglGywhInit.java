package xgxt.gygl.gywh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class GyglGywhInit {

	/**
	 * ��Ԣ������������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getGyjbszRForm(RequestForm rForm, GyglGywhForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xsxx";
		// ϵͳ�ֶ�����
		String menu = "xtzdsz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_jbsz_xtzdsz.do";
		// ��
		String tableName = "xg_view_xsxx_zdszb";
		// ����ֶ�
		String[] colList = new String[] { "search_zd", "search_zdm",
				"search_ymxs", "search_sjy", "search_xgwz", "search_lrxz",
				"search_wkxz", "search_lrxs", "search_lyb", "search_sfqy",
				"lybnum" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// ��ҳ
		// Pages pages = model.getPages();

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
		
		//��ʾ����
		String showNum = "9";
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
}
