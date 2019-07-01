package xsgzgl.xsxx.gdjgxy.xxxg.xgsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改審核_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgshService extends xsgzgl.xsxx.general.xxxg.xgsh.XgshService 
 {
	XgshDAO dao = new XgshDAO();
	/**
	 * 获得學生修改信息
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getXgshInfo(XgshModel model, User user) {

		// 學生信息表字段
		String[] xsxxb = { "xh", "xm", "xb", "csrq", "nj", "xz", "xymc",
				"zzmmmc", "zymc", "mzmc", "bjmc", "xjztm", "rxrq", "sfzh",
				"jgmc", "hkszdmc", "sydmc", "sjhm", "dzyx", "qqhm", "jtdh",
				"jtyb", "jtszd", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdz",
				"jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx",
				"jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1", "jtcy3_xm",
				"jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2", "jtcy3_lxdh1", "xmpy",
				"cym", "sg", "tz", "tc", "jkzk", "pyccmc", "sfzd", "kslbmc",
				"rxfsmc", "pyfsmc", "bz" ,"zw","zd1","zd2","grjl"};

		// 申請ID
		String sqid = model.getSqid();

		List<HashMap<String, String>> list = dao.getXgxxList(sqid, xsxxb);

		HashMap<String, String> map = new HashMap<String, String>();
		// 學生信息
		HashMap<String, String> xsxx = list.get(0);
		// 修改信息
		HashMap<String, String> xgxx = list.get(1);

		for (int i = 0; i < xsxxb.length; i++) {

			String zd = xsxxb[i];
			String xsxx_zd = xsxx.get(zd);
			String xgxx_zd = xgxx.get(zd);
			
			if(zd.equalsIgnoreCase("jtyb")){
				int a=0;
				a++;
			}
			if (Base.isNull(xgxx_zd) || Base.isNull(xgxx_zd.trim())) {
				map.put(zd, xsxx_zd);
			} else if (!Base.isNull(xgxx_zd)) {
				//把有改成无  格式为 "strLiTT"，以LiTT结尾
				
				if(xgxx_zd.endsWith("LiTT")){
					xgxx_zd = xgxx_zd+"空";
				}
				String[] value = xgxx_zd.split("LiTT");
				if(value.length==1){
					map.put(zd, xgxx_zd);
				} else {		
					if("null".equalsIgnoreCase(value[0])){
						value[0]="空";
					}
					String msg = "修改前信息为：" + value[0];
					StringBuilder html = new StringBuilder();
					html.append("<a ");
					html.append("title=\""+msg+"\"");
					html.append(">");
					html.append("<font color=\"red\">");
					if("null".equalsIgnoreCase(value[1])){
						html.append("空");
					}else{
						html.append(value[1]);
					}
					html.append("</font>");
					html.append("</a>");
					
					map.put(zd, html.toString());
				}
			}
		}

		return map;
	}
	
	/**
	 * 提交學生修改信息
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public void submitXxxg(String[] sqid, User user) {

		// 學生信息表字段
		String[] xsxxb = { "xm", "xb", "csrq",  "xz", "zzmm", 
				 "mz",  "xjztm", "rxrq", "sfzh", "jg", "hkszd",
				"syd", "sjhm", "dzyx", "qqhm", "jtyb", "xmpy", "cym", "sg",
				"tz", "tc", "jkzk", "pycc", "sfzd", "kslb", "rxfs", "bz","zw","grjl","zd1","zd2","pyfs","jtdh" };

		// 學生輔助信息表字段
		String[] xsfzxxb = { "lxdh1", "jtszd", "jtcy1_xm", "jtcy1_gx",
				"jtcy1_gzdz", "jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm",
				"jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1",
				"jtcy3_xm", "jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2",
				"jtcy3_lxdh1" };
			
		try {
			//提交更新學生信息表
			dao.updateXsxxb(sqid, xsxxb);
			//提交更新學生輔助信息表
			dao.updateXsfzxxb(sqid, xsfzxxb);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
 }