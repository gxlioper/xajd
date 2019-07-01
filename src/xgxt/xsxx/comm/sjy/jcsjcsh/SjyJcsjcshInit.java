package xgxt.xsxx.comm.sjy.jcsjcsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class SjyJcsjcshInit {

	/**
	 * ����Դ_�������ݳ�ʼ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJcsjcshRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
		
		//������Ŀ
		String czxm = model.getCzxm();
		
		if ("xy".equalsIgnoreCase(czxm)) {
			getJcsjcshByXyRForm(rForm, model, request);
		} else if ("zy".equalsIgnoreCase(czxm)) {
			getJcsjcshByZyRForm(rForm, model, request);
		} else if ("bj".equalsIgnoreCase(czxm)) {
			getJcsjcshByBjRForm(rForm, model, request);
		} else if ("xsjbxx".equalsIgnoreCase(czxm)) {
			getJcsjcshByStuRForm(rForm, model, request);
		}

	}
	
	/**
	 * ����Դ_�������ݳ�ʼ��(ѧԺ)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJcsjcshByXyRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "xsxx";
		// ϵͳ�ֶ�����
		String menu = "jcsjcsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_sjy_jcsjcsh.do";
		// ��ͼ
		String tableName = "xg_view_jcsj_bmxx";
		// ��
		String realTable = "xg_jcsj_bmdmb_temp";
		// ����ֶ�
		String[] colList = new String[] { "bmdm", "bmmc", "fbmmc", "lbmc"};
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//������Ŀ
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// ����
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// ��Դ��
		String lyb = request.getParameter("lyb");
		// ��ɫ
		String color = request.getParameter("color");
		// �ֶ�
		String zd = rule;
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

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color" };
		String[] qtzdz = new String[] { czxm, rule, "xg_jcsj_bmdmb", zd, color };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
	 * ����Դ_�������ݳ�ʼ��(רҵ)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJcsjcshByZyRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
		
		// ����ģ��
		String gnmk = "xsxx";
		// ϵͳ�ֶ�����
		String menu = "jcsjcsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_sjy_jcsjcsh.do";
		// ��ͼ
		String tableName = "xg_view_jcsj_zyxx";
		// ��
		String realTable = "xg_jcsj_zydmb_temp";
		// ����ֶ�
		String[] colList = new String[] { "zydm", "zymc", "bmmc"};
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//������Ŀ
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// ����
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// ��Դ��
		String lyb = request.getParameter("lyb");
		// ��ɫ
		String color = request.getParameter("color");
		// �ֶ�
		String zd = rule;
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

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color","bm" };
		String[] qtzdz = new String[] { czxm, rule, "xg_jcsj_zydmb", zd, color,model.getBmdm() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
	 * ����Դ_�������ݳ�ʼ��(�༶)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJcsjcshByBjRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
	
		// ����ģ��
		String gnmk = "xsxx";
		// ϵͳ�ֶ�����
		String menu = "jcsjcsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_sjy_jcsjcsh.do";
		// ��ͼ
		String tableName = "view_njxyzybj_all";
		// ��
		String realTable = "xg_jcsj_bjdmb_temp";
		// ����ֶ�
		String[] colList = new String[] { "bjdm", "bjmc", "nj", "zymc" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//������Ŀ
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// ����
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// ��Դ��
		String lyb = request.getParameter("lyb");
		// ��ɫ
		String color = request.getParameter("color");
		// �ֶ�
		String zd = rule;
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

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color","nj","zy" };
		String[] qtzdz = new String[] { czxm, rule, "xg_jcsj_bjdmb", zd, color,model.getNj(),model.getZydm() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
	 * ����Դ_�������ݳ�ʼ��(ѧ��������Ϣ)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJcsjcshByStuRForm(RequestForm rForm, SjyJcsjcshForm model,
			HttpServletRequest request) {
	
		// ����ģ��
		String gnmk = "xsxx";
		// ϵͳ�ֶ�����
		String menu = "jcsjcsh";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xsxx_sjy_jcsjcsh.do";
		// ��ͼ
		String tableName = "xg_view_xsjbxx_temp";
		// ��
		String realTable = "xg_jcsj_xsxxb_temp";
		// ����ֶ�
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc" };
		// ��ͷ
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		// �Ƿ��ѯ����
		String search = "go".equalsIgnoreCase(request.getParameter("go")) ? "true"
				: "false";
		//������Ŀ
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		model.setCzxm(czxm);
		// ����
		String rule = request.getParameter("rule");
		model.setRule(rule);
		// ��Դ��
		String lyb = request.getParameter("lyb");
		// ��ɫ
		String color = request.getParameter("color");
		// �ֶ�
		String zd = rule;
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

		String[] qtzd = new String[] { "czxm", "rule", "lyb", "zd", "color",
				"nj", "xy", "zy", "bj" };
		String[] qtzdz = new String[] { czxm, rule, "xsxxb", zd, color,
				model.getNj(), model.getXydm(), model.getZydm(),
				model.getBjdm() };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
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
