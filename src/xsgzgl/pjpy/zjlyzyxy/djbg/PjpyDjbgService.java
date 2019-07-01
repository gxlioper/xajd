package xsgzgl.pjpy.zjlyzyxy.djbg;

import java.util.HashMap;
import java.util.List;

import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;


/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author CMJ
 * @version 1.0
 */

public class PjpyDjbgService extends xsgzgl.pjpy.general.djbg.PjpyDjbgService {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();

	/**
	 * ��õǼǱ��
	 * 
	 * @author ΰ�����
	 */
	public String getDjbg(PjpyDjbgModel model) {

		// �ǼǱ��
		String djbg = "djbg";
		
		// ��Ŀ����
		String xmmc = model.getXmmc();
		
		if ("���ҽ�ѧ��".equalsIgnoreCase(xmmc)) {
			djbg = "gjjxj";
		}

		return djbg;
	}

	/**
	 * �ǼǱ������
	 * 
	 * @author CMJ
	 */
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {

		// ѧ��
		String xh = model.getXh();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xmmc", xmmc);
		String lx = model.getLx();

		// ����ѧ����Ϣ
		setXsxxInfo(map);
		//����ѧ���ɼ�
		setXscjInfo(map);
		//����ѧ��ͳ�Ƴɼ�
		setXstjcjInfo(map);
		//����ѧ�������
		setXshjqkInfo(map);
		
		
		HashMap<String, String> bcpjyj=new HashMap<String, String>();
		HashMap<String, String> xsxx=new HashMap<String, String>();
		if ("bcpj".equalsIgnoreCase(lx)) {
			List<HashMap<String, String>> bcshList = dao.getBcsh(xh, xmmc);
			xsxx = dao.setXshjqkInfo(xh, xn);
			if (bcshList != null && bcshList.size() > 0) {
				int maxLv = bcshList.size();
				for (int j = 0; j < bcshList.size(); j++) {
					if (String.valueOf(maxLv).equalsIgnoreCase(
							bcshList.get(j).get("shjb"))) {
						bcpjyj.put("xxyj",
								bcshList.get(j).get("shyj"));
						bcpjyj.put("sqly",
								bcshList.get(j).get("sqly"));
					} else if (String.valueOf(maxLv - 1)
							.equalsIgnoreCase(
									bcshList.get(j).get("shjb"))) {
						bcpjyj.put("xyyj",
								bcshList.get(j).get("shyj"));
						bcpjyj.put("sqly",
								bcshList.get(j).get("sqly"));
					}
				}
			}
		}else if("lspj".equalsIgnoreCase(lx)){
			List<HashMap<String, String>> bcshList = dao.getLssh(xh, xmmc,xn);
			xsxx = dao.setXslshjqkInfo(xh, xn);
			if (bcshList != null && bcshList.size() > 0) {
				int maxLv = bcshList.size();
				for (int j = 0; j < bcshList.size(); j++) {
					if (String.valueOf(maxLv).equalsIgnoreCase(
							bcshList.get(j).get("shjb"))) {
						bcpjyj.put("xxyj",
								bcshList.get(j).get("shyj"));
						bcpjyj.put("sqly",
								bcshList.get(j).get("sqly"));
					} else if (String.valueOf(maxLv - 1)
							.equalsIgnoreCase(
									bcshList.get(j).get("shjb"))) {
						bcpjyj.put("xyyj",
								bcshList.get(j).get("shyj"));
						bcpjyj.put("sqly",
								bcshList.get(j).get("sqly"));
					}
				}
			}
		}
		map.putAll(bcpjyj);
		map.putAll(xsxx);

		String nowTime = dao.getNowTime("YYYY��MM��DD��");
		map.put("nowTime", nowTime);
		
		return map;
	}

	private void setXshjqkInfo(HashMap<String, Object> map) {
		// TODO �Զ����ɷ������
		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");
		
		HashMap<String, String> xsxx=new HashMap<String, String>();
		
		xsxx = dao.setXshjqkInfo(xh, xn);

		map.putAll(xsxx);
	}

	/**
	 * �ǼǱ������
	 * 
	 * @date 2013-01-31
	 * @author CMJ
	 */
	public List<HashMap<String, String>> getDjbgInfoList(PjpyDjbgModel model) {
		// TODO �Զ����ɷ������
		return null;
	}
	
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ����ѧ����Ϣ��������Ա�⣩
	 * 
	 * @author CMJ
	 */
	private void setXsxxInfo(HashMap<String, Object> map) {

		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");

		HashMap<String, String> xsxx = dao.setXsxxInfo(xh, xn);
		String sfzh=xsxx.get("sfzh");
		if(sfzh!=null){
			for(int i=0;i<sfzh.length();i++){
				String s=new String(new char[]{sfzh.charAt(i)});
				String name="sfzh"+i;
				map.put(name, s);
			}
		}
		map.putAll(xsxx);
	}
	/**
	 * ����ѧ���ɼ���Ϣ
	 * 
	 * @author CMJ
	 */
	private void setXscjInfo(HashMap<String, Object> map) {

		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");

		List<HashMap<String, String>> list = dao.getXscjList(xh, xn);

		if (list != null && list.size() > 0) {

			// �ɼ���
			int num = list.size();

			for (int i = 0; i < (num < 21 ? num : 21); i++) {
				String kcmc = list.get(i).get("kcmc");
				String cj = list.get(i).get("cj");

				map.put("kcmc" + i, kcmc);
				map.put("cj" + i, cj);
			}
		}
	}
	/**
	 * ����ѧ��ͳ�Ƴɼ���ƽ������͡���������
	 */
	private void setXstjcjInfo(HashMap<String, Object> map){
		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");
		
		HashMap<String, String> xscj=dao.getXstjcjInfo(xh,xn);
		map.putAll(xscj);
	}
}
