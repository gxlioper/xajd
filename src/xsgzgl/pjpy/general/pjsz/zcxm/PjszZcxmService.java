package xsgzgl.pjpy.general.pjsz.zcxm;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_�۲���Ŀ_Service��
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

public class PjszZcxmService extends CommService implements PjszZcxmInterface {

	PjszZcxmDAO dao = new PjszZcxmDAO();

	/**
	 * �����۲���Ŀ
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveZcxm(PjszZcxmModel model, String lx, User user) {

		boolean flag = false;
		
		if ("next".equalsIgnoreCase(lx)) {//�����¼��۲���Ŀ
			flag = saveNextZcxm(model, user);
		} else {
			flag = saveZcxm(model, user);
		}
		
		return flag;
	}

	/**
	 * �����۲���Ŀ
	 * 
	 * @author ΰ�����
	 */
	private Boolean saveZcxm(PjszZcxmModel model, User user) {
		boolean flag = false;

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ��Ŀ����
		String xmjb = model.getXmjb();
		// �ϼ�����
		String sjdm = model.getSjdm();
		// �Ӽ���
		String jjf = model.getJjf();
		// ¼������
		String lrly = model.getLrly();

		try {
			// ִ�б����۲���Ŀ
			flag = dao.deleteZcxmb(xmdm, user);
			if (flag) {
				flag = dao.insertZcxmb(xmdm, xmmc, xmjb, sjdm, jjf, lrly, user);
			}

			// ִ�б����۲����
			if (flag) {
				flag = saveZcbl(model, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * �����۲���Ŀ(��һ����)
	 * 
	 * @author ΰ�����
	 */
	private Boolean saveNextZcxm(PjszZcxmModel model, User user) {

		// ��Ŀ����
		String xmjb = String.valueOf(dao.getXmjb(model.getSjdm()) + 1);
		model.setXmjb(xmjb);
		// ��Ŀ����
		String xmdm = getNextXmdm();
		model.setXmdm(xmdm);

		boolean flag = saveZcxm(model, user);

		return flag;
	}
	
	/**
	 * �����Ŀ����(��һ����)
	 * 
	 * @author ΰ�����
	 */
	public String getNextXmdm() {
		
		// ������Ŀ
		String zcxm = "";
		// ��Ŀ�ֶ�
		String[] xmzd = { "zd1", "zd2", "zd3", "zd4", "zd5", "zd6", "zd7",
				"zd8", "zd9", "zd10", "zd11", "zd12", "zd13", "zd14", "zd15",
				"zd16", "zd17", "zd18", "zd19", "zd20", "zd21", "zd22", "zd23",
				"zd24", "zd25", "zd26", "zd27", "zd28", "zd29", "zd30", };
		
		List<HashMap<String, String>> zcxmList = dao.getZcxmList();

		if (zcxmList != null && zcxmList.size() == 30) {
			zcxm = "no";
		} else if (zcxmList != null && zcxmList.size() > 0) {

			// ������Ŀ
			String[] szxm = new String[zcxmList.size()];

			for (int i = 0; i < zcxmList.size(); i++) {
				HashMap<String, String> map = zcxmList.get(i);
				String xmdm = map.get("xmdm");
				// String mrxm = map.get("mrxm");
				szxm[i] = xmdm;
			}
			
			// ��ʹ����Ŀ
			String[] ksyxm = getNoRepeatArrValue(xmzd, szxm);
			if (ksyxm != null && ksyxm.length > 0) {
				zcxm = ksyxm[0];
			}else{
				zcxm = "no";
			}
		}

		return zcxm;
	}

	/**
	 * ɾ���۲���Ŀ
	 * 
	 * @author ΰ�����
	 */
	public Boolean deleteZcxm(PjszZcxmModel model, User user) {

		boolean flag = false;

		// ��Ŀ����
		String xmdm = model.getXmdm();

		try {
			// ִ��ɾ���۲���Ŀ����
			flag = dao.deleteZcxmb(xmdm, xmdm, user);
			// ִ��ɾ���۲��������
			if (flag) {
				flag = dao.deleteZcblb(xmdm, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * �޸��۲����
	 * 
	 * @author ΰ�����
	 */
	public Boolean updateZcxm(PjszZcxmModel model, User user) {

		boolean flag = false;

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// �Ӽ���
		String jjf = model.getJjf();
		// ¼������
		String lrly = model.getLrly();
		
		try {
			//ִ�и��²���
			flag = dao.updateZcxmb(xmdm, xmmc, jjf, lrly, user);
			if(flag){
				flag = saveZcbl(model, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * �����۲����
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveZcbl(PjszZcxmModel model, User user) {

		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��������
		String[] bldm = model.getBldm();
		// ����
		String[] bl = model.getBl();

		boolean flag = false;
		
		try {
			// ִ�б����۲����
			flag = dao.deleteZcblb(xmdm, bldm, user);
			if (flag) {
				flag = dao.insertZcblb(xmdm, bldm, bl, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}

}