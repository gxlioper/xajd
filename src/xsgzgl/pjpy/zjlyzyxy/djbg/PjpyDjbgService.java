package xsgzgl.pjpy.zjlyzyxy.djbg;

import java.util.HashMap;
import java.util.List;

import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_登记表格_通用_Service类
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
	 * 获得登记表格
	 * 
	 * @author 伟大的骆
	 */
	public String getDjbg(PjpyDjbgModel model) {

		// 登记表格
		String djbg = "djbg";
		
		// 项目名称
		String xmmc = model.getXmmc();
		
		if ("国家奖学金".equalsIgnoreCase(xmmc)) {
			djbg = "gjjxj";
		}

		return djbg;
	}

	/**
	 * 登记表格内容
	 * 
	 * @author CMJ
	 */
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {

		// 学号
		String xh = model.getXh();
		// 项目名称
		String xmmc = model.getXmmc();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xmmc", xmmc);
		String lx = model.getLx();

		// 设置学生信息
		setXsxxInfo(map);
		//设置学生成绩
		setXscjInfo(map);
		//设置学生统计成绩
		setXstjcjInfo(map);
		//设置学生获奖情况
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

		String nowTime = dao.getNowTime("YYYY年MM月DD日");
		map.put("nowTime", nowTime);
		
		return map;
	}

	private void setXshjqkInfo(HashMap<String, Object> map) {
		// TODO 自动生成方法存根
		// 学号
		String xh = (String) map.get("xh");
		// 学年
		String xn = (String) map.get("xn");
		
		HashMap<String, String> xsxx=new HashMap<String, String>();
		
		xsxx = dao.setXshjqkInfo(xh, xn);

		map.putAll(xsxx);
	}

	/**
	 * 登记表格内容
	 * 
	 * @date 2013-01-31
	 * @author CMJ
	 */
	public List<HashMap<String, String>> getDjbgInfoList(PjpyDjbgModel model) {
		// TODO 自动生成方法存根
		return null;
	}
	
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 设置学生信息（评奖人员库）
	 * 
	 * @author CMJ
	 */
	private void setXsxxInfo(HashMap<String, Object> map) {

		// 学号
		String xh = (String) map.get("xh");
		// 学年
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
	 * 设置学生成绩信息
	 * 
	 * @author CMJ
	 */
	private void setXscjInfo(HashMap<String, Object> map) {

		// 学号
		String xh = (String) map.get("xh");
		// 学年
		String xn = (String) map.get("xn");

		List<HashMap<String, String>> list = dao.getXscjList(xh, xn);

		if (list != null && list.size() > 0) {

			// 成绩数
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
	 * 设置学生统计成绩：平均、最低、不及格数
	 */
	private void setXstjcjInfo(HashMap<String, Object> map){
		// 学号
		String xh = (String) map.get("xh");
		// 学年
		String xn = (String) map.get("xn");
		
		HashMap<String, String> xscj=dao.getXstjcjInfo(xh,xn);
		map.putAll(xscj);
	}
}
