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
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_首页_通用_Service类
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
	 * 初始化评奖流程信息(本级别)
	 * 
	 * @author qlj
	 */
	public void initThisPjlcInfo(String lcdj, User user) {

		// 获得本评奖等级列表
		List<HashMap<String, String>> pjdjList = dao.getPjlcList(lcdj, user);

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		//是否需要参评组
		String cpz = jbszModel.getCpz();
		
		if (pjdjList != null && pjdjList.size() > 0) {
			for (int i = 0; i < pjdjList.size(); i++) {
				HashMap<String, String> map = pjdjList.get(i);
				String lcdm = map.get("lcdm");

				try {
					if ("103".equalsIgnoreCase(lcdm)) {// 参评小组设置
						if("yes".equalsIgnoreCase(cpz)){
							dao.initCpxzRy(user);
						}
					} else if ("104".equalsIgnoreCase(lcdm)) {// 综测项目维护

						//PjpyZhcpDAO zhcpDAO = new PjpyZhcpDAO();

						// 评奖学年
						//String pjxn = jbszModel.getPjxn();
						// 评奖学期
						//String pjxq = jbszModel.getPjxq();
						// 评奖年度
						//String pjnd = jbszModel.getPjnd();

						// 本周期的综测项目
						//List<HashMap<String, String>> zcxmList = zhcpDAO
								//.getZcxmList(pjxn, pjxq, pjnd);
						
						// 综测扩展字段
						//List<HashMap<String, String>> kzzdList = zhcpDAO
						//.getKzzdList(user);

						//dao.initComments(zcxmList, user);
						//dao.initDrb(zcxmList,kzzdList, user);
						//dao.initDcb(zcxmList,kzzdList, user);
					} else if ("116".equalsIgnoreCase(lcdm)) {// 综测分结果
						// 综合分计算
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
