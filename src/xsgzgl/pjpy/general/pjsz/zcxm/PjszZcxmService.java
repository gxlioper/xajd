package xsgzgl.pjpy.general.pjsz.zcxm;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_综测项目_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjszZcxmService extends CommService implements PjszZcxmInterface {

	PjszZcxmDAO dao = new PjszZcxmDAO();

	/**
	 * 保存综测项目
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveZcxm(PjszZcxmModel model, String lx, User user) {

		boolean flag = false;
		
		if ("next".equalsIgnoreCase(lx)) {//保存下级综测项目
			flag = saveNextZcxm(model, user);
		} else {
			flag = saveZcxm(model, user);
		}
		
		return flag;
	}

	/**
	 * 保存综测项目
	 * 
	 * @author 伟大的骆
	 */
	private Boolean saveZcxm(PjszZcxmModel model, User user) {
		boolean flag = false;

		// 项目代码
		String xmdm = model.getXmdm();
		// 项目名称
		String xmmc = model.getXmmc();
		// 项目级别
		String xmjb = model.getXmjb();
		// 上级代码
		String sjdm = model.getSjdm();
		// 加减分
		String jjf = model.getJjf();
		// 录入理由
		String lrly = model.getLrly();

		try {
			// 执行保存综测项目
			flag = dao.deleteZcxmb(xmdm, user);
			if (flag) {
				flag = dao.insertZcxmb(xmdm, xmmc, xmjb, sjdm, jjf, lrly, user);
			}

			// 执行保存综测比例
			if (flag) {
				flag = saveZcbl(model, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 保存综测项目(下一级别)
	 * 
	 * @author 伟大的骆
	 */
	private Boolean saveNextZcxm(PjszZcxmModel model, User user) {

		// 项目级别
		String xmjb = String.valueOf(dao.getXmjb(model.getSjdm()) + 1);
		model.setXmjb(xmjb);
		// 项目代码
		String xmdm = getNextXmdm();
		model.setXmdm(xmdm);

		boolean flag = saveZcxm(model, user);

		return flag;
	}
	
	/**
	 * 获得项目代码(下一级别)
	 * 
	 * @author 伟大的骆
	 */
	public String getNextXmdm() {
		
		// 操作项目
		String zcxm = "";
		// 项目字段
		String[] xmzd = { "zd1", "zd2", "zd3", "zd4", "zd5", "zd6", "zd7",
				"zd8", "zd9", "zd10", "zd11", "zd12", "zd13", "zd14", "zd15",
				"zd16", "zd17", "zd18", "zd19", "zd20", "zd21", "zd22", "zd23",
				"zd24", "zd25", "zd26", "zd27", "zd28", "zd29", "zd30", };
		
		List<HashMap<String, String>> zcxmList = dao.getZcxmList();

		if (zcxmList != null && zcxmList.size() == 30) {
			zcxm = "no";
		} else if (zcxmList != null && zcxmList.size() > 0) {

			// 设置项目
			String[] szxm = new String[zcxmList.size()];

			for (int i = 0; i < zcxmList.size(); i++) {
				HashMap<String, String> map = zcxmList.get(i);
				String xmdm = map.get("xmdm");
				// String mrxm = map.get("mrxm");
				szxm[i] = xmdm;
			}
			
			// 可使用项目
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
	 * 删除综测项目
	 * 
	 * @author 伟大的骆
	 */
	public Boolean deleteZcxm(PjszZcxmModel model, User user) {

		boolean flag = false;

		// 项目代码
		String xmdm = model.getXmdm();

		try {
			// 执行删除综测项目操作
			flag = dao.deleteZcxmb(xmdm, xmdm, user);
			// 执行删除综测比例操作
			if (flag) {
				flag = dao.deleteZcblb(xmdm, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 修改综测比例
	 * 
	 * @author 伟大的骆
	 */
	public Boolean updateZcxm(PjszZcxmModel model, User user) {

		boolean flag = false;

		// 项目代码
		String xmdm = model.getXmdm();
		// 项目名称
		String xmmc = model.getXmmc();
		// 加减分
		String jjf = model.getJjf();
		// 录入理由
		String lrly = model.getLrly();
		
		try {
			//执行更新操作
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
	 * 保存综测比例
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveZcbl(PjszZcxmModel model, User user) {

		// 项目代码
		String xmdm = model.getXmdm();
		// 比例代码
		String[] bldm = model.getBldm();
		// 比例
		String[] bl = model.getBl();

		boolean flag = false;
		
		try {
			// 执行保存综测比例
			flag = dao.deleteZcblb(xmdm, bldm, user);
			if (flag) {
				flag = dao.insertZcblb(xmdm, bldm, bl, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}

}