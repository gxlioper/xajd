package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

public class PjpyXmshInit {

	/**
	 * ������Ŀ���_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getPjxmshRForm(RequestForm rForm, PjpyXmshForm model,
			HttpServletRequest request) {
		
		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "pjxmsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// ����·��
		String path = "pjpy_pjlc_xmsh.do";
		// ����ֶ�
		String[] colList = new String[] { "pk", "xh", "xm", "nj", "showxy",
				"showzy", "showbj", "shzt", "sjzt","xymc","zymc","bjmc" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
				"xg_view_pjpy_xmsh", colList, null);
		// �Ƿ��ѯ����
		String search = !("go".equalsIgnoreCase(request.getParameter("go"))) ? "false"
				: "true";

		// ��ҳ
		Pages pages = model.getPages();
		
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
		String showNum = "7";
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============
		String shxm = model.getShxm();
		String spgw = model.getSpgw();
		String shjb = model.getShjb();
		String pjxn = model.getJbszModel().getPjxn();
		String pjxq = model.getJbszModel().getPjxq();
		String pjnd = model.getJbszModel().getPjnd();
		String xqmc = model.getJbszModel().getPjxqmc();
		
		String[] qtzd = new String[] { "shxm", "pjxn", "pjxq", "pjnd", "pjxqmc","spgw","shjb","pk" };
		String[] qtzdz = new String[] { shxm, pjxn, pjxq, pjnd, xqmc,spgw,shjb,pkValue };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setPages(pages);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPkValue(pkValue);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);
		
	}
}
