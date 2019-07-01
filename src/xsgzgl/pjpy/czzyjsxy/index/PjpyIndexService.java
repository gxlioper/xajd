package xsgzgl.pjpy.czzyjsxy.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import common.Globals;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.index.PjpyIndexDAO;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��ҳ_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class PjpyIndexService extends xsgzgl.pjpy.general.index.PjpyIndexService {

	
	PjpyIndexDAO dao = new PjpyIndexDAO();
	
	/**
	 * ��ʼ������������Ϣ(������)
	 * 
	 * @author qlj
	 */
	public void initThisPjlcInfo(String lcdj, User user) {

		// ��ñ������ȼ��б�
		List<HashMap<String, String>> pjdjList = dao.getPjlcList(lcdj, user);

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		//�Ƿ���Ҫ������
		String cpz = jbszModel.getCpz();
		
		if (pjdjList != null && pjdjList.size() > 0) {
			for (int i = 0; i < pjdjList.size(); i++) {
				HashMap<String, String> map = pjdjList.get(i);
				String lcdm = map.get("lcdm");

				try {
					if ("103".equalsIgnoreCase(lcdm)) {// ����С������
						if("yes".equalsIgnoreCase(cpz)){
							dao.initCpxzRy(user);
						}
					} else if ("104".equalsIgnoreCase(lcdm)) {// �۲���Ŀά��

						//PjpyZhcpDAO zhcpDAO = new PjpyZhcpDAO();

						// ����ѧ��
						//String pjxn = jbszModel.getPjxn();
						// ����ѧ��
						//String pjxq = jbszModel.getPjxq();
						// �������
						//String pjnd = jbszModel.getPjnd();

						// �����ڵ��۲���Ŀ
						//List<HashMap<String, String>> zcxmList = zhcpDAO
								//.getZcxmList(pjxn, pjxq, pjnd);
						
						// �۲���չ�ֶ�
						//List<HashMap<String, String>> kzzdList = zhcpDAO
						//.getKzzdList(user);

						//dao.initComments(zcxmList, user);
						//dao.initDrb(zcxmList,kzzdList, user);
						//dao.initDcb(zcxmList,kzzdList, user);
					} else if ("116".equalsIgnoreCase(lcdm)) {// �۲�ֽ��
						// �ۺϷּ���
						PjpyZhcpService zhcpService = new PjpyZhcpService();
						PjpyGeneralForm myForm = new PjpyGeneralForm();
						zhcpService.account(myForm, user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
