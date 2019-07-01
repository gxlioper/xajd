package xgxt.pjpy.comm.zhcp.sjdr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_���ݵ���_Init��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */
public class ZhcpSjdrInit {

	/**
	 * Ʒ�»�����_��ʼ������(ѧ��)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getSjdrRForm(RequestForm rForm, ZhcpSjdrForm model,
			HttpServletRequest request) {

		// ������Ŀ
		String czxm = model.getCzxm();
		czxm = Base.isNull(czxm) ? "zd5" : czxm;
		model.setCzxm(czxm);
		
		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "jcsjdr";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_general_zhcp_maintain.do";
		// ���ֵ
		String[] colList = new String[] { "xh", "xm", "nj", "bjmc" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model);
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
		String showNum = String.valueOf(4);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "czxm" };
		String[] qtzdz = new String[] { czxm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	private List<HashMap<String, String>> getDefaultValue(ZhcpSjdrForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		// ������Ŀ
		String czxm = model.getCzxm();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("en", "pjsj");
		map.put("cn", "����ʱ��");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "ѧ��");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "����");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "nj");
		map.put("cn", "�꼶");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "�༶����");
		topTr.add(map);

		DAO dao = DAO.getInstance();
		String xmmc = dao.getOneValue("xg_pjpy_zcxmb", "xmmc", "xmdm", czxm);
		map = new HashMap<String, String>();
		map.put("en", czxm);
		map.put("cn", xmmc);
		topTr.add(map);
		
		model.setXmmc(xmmc);
		
		return topTr;
	}
}
