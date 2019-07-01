package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ�ϱ�_Init��
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

public class PjpyXmsbInit {

	/**
	 * ������Ŀ���_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getPjxmsbRForm(RequestForm rForm, PjpyXmsbForm model,
			HttpServletRequest request) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		PjpyXmsbService service = new PjpyXmsbService();

		// ����ģ��
		String gnmk = "pjpy";
		// ϵͳ�ֶ�����
		String menu = "pjxmsb";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_pjlc_xmsb.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// �������
		String pjnd = jbszModel.getPjnd();
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? model.getXmdm() : xmdm;
		// ��Ŀ����
		String bjdm = request.getParameter("bjdm");
		// ��Ŀ����model��ʼ��
		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pk);
		model.setXmszModel(xmszModel);
		// ��������
		String szrs = service.getXmszrs(model);
		xmszModel.setSzrs(szrs);
		// ��Ŀ����
		String xmmc = xmszModel.getXmmc();
		// ���Ʒ�Χ
		String kzfw = xmszModel.getKzfw();

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc",
				"xmdm", "bjdm", "szrs", "xmmc", "kzfw" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, xmdm, bjdm,
				szrs, xmmc, kzfw };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

	}
	
	private List<HashMap<String, String>> getDefaultValue(PjpyXmsbForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "ѧ��");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "����");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xb");
		map.put("cn", "�Ա�");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "�༶����");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "zhf");
		map.put("cn", "�۲��");
		topTr.add(map);
		
		// �۲�����
		String zcpm = model.getZcpm();
		
		if("3".equalsIgnoreCase(zcpm)){
			map = new HashMap<String, String>();
			map.put("en", "zcfbjpm");
			map.put("cn", "�༶����");
			topTr.add(map);
		}else if("2".equalsIgnoreCase(zcpm)){
			map = new HashMap<String, String>();
			map.put("en", "zcfnjzypm");
			map.put("cn", "�꼶רҵ����");
			topTr.add(map);
		}
		
		map = new HashMap<String, String>();
		map.put("en", "zyf");
		map.put("cn", "������");
		topTr.add(map);
		String zypm=model.getZypm();
		if("3".equalsIgnoreCase(zypm)){
			map = new HashMap<String, String>();
			map.put("en", "zyfbjpm");
			map.put("cn", "�����ְ༶����");
			topTr.add(map);
		}else if("2".equalsIgnoreCase(zypm)){
			map = new HashMap<String, String>();
			map.put("en", "zcfnjzypm");
			map.put("cn", "�������꼶רҵ����");
			topTr.add(map);
		}
		
		
		
		map = new HashMap<String, String>();
		map.put("en", "cz");
		map.put("cn", "����");
		topTr.add(map);
		
		return topTr;
	}
}
