package xgxt.jxgl.comm.jxbz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ����_Init��
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
public class JxglJxbzInit {

	/**
	 * Ʒ�»�����_��ʼ������(ѧ��)
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void getJxbzRForm(RequestForm rForm, JxglJxbzForm model,
			HttpServletRequest request) {

		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;

		// ����ģ��
		String gnmk = "jxgl";
		// ϵͳ�ֶ�����
		String menu = "jxbz";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "jxgl_jxbz.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";
		// ��С����
		String minBz = jxszModel.getMinBz();
		// ������
		String maxBz = jxszModel.getMaxBz();
		// �˵�ID
		String menuId = request.getParameter("menuId");
		model.setMenuId(menuId);
		// ѧ��
		String xn = request.getParameter("xn");
		if (Base.isNull(xn)) {
			xn = Base.currXn;
		}
		model.setXn(xn);
		// ��������
		String czlx = request.getParameter("czlx");
		model.setCzlx(czlx);
		// ���ƴ���
		String bzdm = request.getParameter("bzdm");
		model.setBzdm(bzdm);
		// ��������
		String bzmc = request.getParameter("bzmc");
		model.setBzmc(bzmc);
		// ���Ƶȼ�
		String bzdj = request.getParameter("bzdj");
		model.setBzdj(bzdj);
		// �ϼ�����
		String sjdm = request.getParameter("sjdm");
		model.setSjdm(sjdm);
		// ��ʦ����
		String jsdm = request.getParameter("jsdm");
		model.setJsdm(jsdm);
		// �̹ٱ��
		String jgbh = request.getParameter("jgbh");
		model.setJgbh(jgbh);
		
		String[] qtzd = new String[] { "xn", "minBz", "maxBz", "czlx" };
		String[] qtzdz = new String[] { xn, minBz, maxBz, czlx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);

	}

	private List<HashMap<String, String>> getDefaultValue(JxglJxbzForm model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		
		map = new HashMap<String, String>();
		map.put("en", "");
		map.put("cn", "");
		topTr.add(map);
		
		map = new HashMap<String, String>();
		map.put("en", "nj");
		map.put("cn", "�꼶");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xymc");
		map.put("cn", "Ժϵ");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "zymc");
		map.put("cn", "רҵ");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "�༶");
		topTr.add(map);

		return topTr;
	}
}
