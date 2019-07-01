package xsgzgl.xsxx.zjjtzyjsxy.xxxg.zxxs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;
import xsgzgl.xsxx.grxx.XsxxGrxxDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_在校学生_浙江交通技师_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxZxxsService extends CommService implements XsxxZxxsInterface {

	XsxxZxxsDAO dao = new XsxxZxxsDAO();

	/**
	 * 获得表头文件(在校学生)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsxxZxxsTop(XsxxZxxsModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"xjztm" };
		String[] cn = new String[] { "", "学号", "姓名", "性别", "年级", "班级", "学籍状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集(在校学生)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		return dao.getXsxxZxxsList(myForm, user);
	}

	/**
	 * 构建结果集(在校学生)
	 * 
	 * @author 伟大的骆
	 */
	public String createXsxxZxxsHTML(SearchRsModel rsModel,
			XsxxZxxsModel model, ArrayList<String[]> rsArrList, User user) {


		BasicService basicService=new BasicService();
		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ");
				html.append("style=\"cursor:hand\" ");
				html.append("ondblclick=\"showXsxxDetail('"+pk+"')\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					html.append(basicService.replaceHtml(rs[j]));
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}
		
		return html.toString();
	
	}
	
	/**
	 * 获得学生基本信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getZxxsInfo(XsxxZxxsModel model, User user) {

		// 学号
		String xh = model.getXh();

		HashMap<String, String> map = dao.getZxxsInfo(xh, user);

		return map;
	}

	/**
	 * 保存学生信息
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveXsxx(XsxxZxxsModel model, User user) {

		// 是否在学生信息表中存在
		boolean isXsxxExists = isExists("xsxxb", "xh", model.getXh());
		// 是否在学生辅助信息表中存在
		boolean isFzxxExists = isExists("xsfzxxb", "xh", model.getXh());

		boolean flag = true;

		XsxxGrxxDAO grxxDAO = new XsxxGrxxDAO();

		if (!isXsxxExists) {
			try {
				flag = grxxDAO.copyToXsxxb(model.getXh());
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		if (!isFzxxExists) {
			try {
				flag = grxxDAO.copyToFzxxb(model.getXh());
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		// 更新学生信息
		if (flag) {
			flag = updateXsxx(model, user);
		}

		// 更新辅助信息
		if (flag) {
			flag = updateFzxx(model, user);
		}

		return flag;
	}
	
	/**
	 * 更新学生信息
	 * 
	 * @author 伟大的骆
	 */
	public boolean updateXsxx(XsxxZxxsModel model, User user) {

		String xh = model.getXh();// 学号
		String xm = model.getXm();// 姓名
		String xb = model.getXb();// 性别
		String csrq = model.getCsrq();// 出生日期
		String mz = model.getMz();// 民族
		String zzmm = model.getZzmm();// 政治面貌
		String sfzh = model.getSfzh();// 身份证号
		String pycc = model.getPycc();// 培养层次
		String hkszd = model.getHkszd();// 户口所在地
		String jg = model.getJg();// 籍贯
		String syd = model.getSyd();// 生源地
		String bjdm = model.getBjdm();// 所在部门
		String xjztm = model.getXjztm();// 学籍状态
		String xz = model.getXz();// 学制
		String sfzc = model.getSfzc();// 是否注册
		String sfzd = model.getSfzd();// 是否走读
		String rxrq = model.getRxrq();// 入学日期
		String bysj = model.getBysj();// 毕业时间
		String sfbys = model.getSfbys();// 是否毕业生
		String sfyby = model.getSfyby();// 是否已毕业
		String sfzx = model.getSfzx();// 是否在校
		String lxdh = model.getLxdh();// 联系电话
		String sjhm = model.getSjhm();// 手机号码
		String qqhm = model.getQqhm();// QQ号码
		String dzyx = model.getDzyx();// 电子邮箱
		String yhdm = model.getYhdm();// 银行
		String yhkh = model.getYhkh();// 银行卡号
		String fdyxm = model.getFdyxm();// 辅导员姓名
		String kh = model.getKh();// 一卡通号
		String sg = model.getSg();// 身高
		String tz = model.getTz();// 体重
		String xmpy = model.getXmpy();// 姓名拼音
		String cym = model.getCym();// 曾用名
		String tc = model.getTc();// 特长
		String kslb = model.getKslb();// 考生类别
		String rxfs = model.getRxfs();// 入学方式
		String pyfs = model.getPyfs();// 培养方式
		String jkzk = model.getJkzk();// 健康状况

		boolean flag = true;

		// 更新学生信息

		try {
			flag = dao.updateXsxxb(xh, xm, xb, csrq, mz, zzmm, sfzh, pycc,
					hkszd, jg, syd, bjdm, xjztm, xz, sfzc, sfzd, rxrq, bysj,
					sfbys, sfyby, sfzx, lxdh, sjhm, qqhm, dzyx, yhdm, yhkh,
					fdyxm, kh, sg, tz, xmpy, cym, tc, kslb, rxfs, pyfs, jkzk,
					user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 更新辅助信息
	 * 
	 * @author 伟大的骆
	 */
	public boolean updateFzxx(XsxxZxxsModel model, User user) {

		String xh = model.getXh();// 学号
		String lxdh1 = model.getLxdh1();// 家庭电话
		String jtszd = model.getJtszd();// 家庭地址
		String yb = model.getYb();// 邮政编码
		String jjzk = model.getJjzk();// 家庭经济状况
		String jtcy1_xm = model.getJtcy1_xm();// 家庭成员1_姓名
		String jtcy1_gx = model.getJtcy1_gx();// 家庭成员1_关系
		String jtcy1_nl = model.getJtcy1_nl();// 家庭成员1_生日
		String jtcy1_sfzh = model.getJtcy1_sfzh();// 家庭成员1_身份证号
		String jtcy1_zy = model.getJtcy1_zy();// 家庭成员1_职业
		String jtcy1_zw = model.getJtcy1_zw();// 家庭成员1_职务
		String jtcy1_lxdh1 = model.getJtcy1_lxdh1();// 家庭成员1_工作电话
		String jtcy1_lxdh2 = model.getJtcy1_lxdh2();// 家庭成员1_个人电话
		String jtcy1_gzdz = model.getJtcy1_gzdz();// 家庭成员1_工作地址
		String jtcy1_mz = model.getJtcy1_mz();// 家庭成员1_民族
		String jtcy1_zzmm = model.getJtcy1_zzmm();// 家庭成员1_政治面貌
		String jtcy2_xm = model.getJtcy2_xm();// 家庭成员2_姓名
		String jtcy2_gx = model.getJtcy2_gx();// 家庭成员2_关系
		String jtcy2_nl = model.getJtcy2_nl();// 家庭成员2_生日
		String jtcy2_sfzh = model.getJtcy2_sfzh();// 家庭成员2_身份证号
		String jtcy2_zy = model.getJtcy2_zy();// 家庭成员2_职业
		String jtcy2_zw = model.getJtcy2_zw();// 家庭成员2_职务
		String jtcy2_lxdh1 = model.getJtcy2_lxdh1();// 家庭成员2_工作电话
		String jtcy2_lxdh2 = model.getJtcy2_lxdh2();// 家庭成员2_个人电话
		String jtcy2_gzdz = model.getJtcy2_gzdz();// 家庭成员2_工作地址
		String jtcy2_mz = model.getJtcy2_mz();// 家庭成员2_民族
		String jtcy2_zzmm = model.getJtcy2_zzmm();// 家庭成员2_政治面貌
		String jtcy3_xm = model.getJtcy3_xm();// 家庭成员3_姓名
		String jtcy3_gx = model.getJtcy3_gx();// 家庭成员3_关系
		String jtcy3_nl = model.getJtcy3_nl();// 家庭成员3_生日
		String jtcy3_sfzh = model.getJtcy3_sfzh();// 家庭成员3_身份证号
		String jtcy3_zy = model.getJtcy3_zy();// 家庭成员3_职业
		String jtcy3_zw = model.getJtcy3_zw();// 家庭成员3_职务
		String jtcy3_lxdh1 = model.getJtcy3_lxdh1();// 家庭成员3_工作电话
		String jtcy3_lxdh2 = model.getJtcy3_lxdh2();// 家庭成员3_个人电话
		String jtcy3_gzdz = model.getJtcy3_gzdz();// 家庭成员3_工作地址
		String jtcy3_mz = model.getJtcy3_mz();// 家庭成员3_民族
		String jtcy3_zzmm = model.getJtcy3_zzmm();// 家庭成员3_政治面貌

		boolean flag = true;

		// 更新辅助信息
		try {
			flag = dao.updateFzxxb(xh, lxdh1, jtszd, yb, jjzk, jtcy1_xm,
					jtcy1_gx, jtcy1_nl, jtcy1_sfzh, jtcy1_zy, jtcy1_zw,
					jtcy1_lxdh1, jtcy1_lxdh2, jtcy1_gzdz, jtcy1_mz, jtcy1_zzmm,
					jtcy2_xm, jtcy2_gx, jtcy2_nl, jtcy2_sfzh, jtcy2_zy,
					jtcy2_zw, jtcy2_lxdh1, jtcy2_lxdh2, jtcy2_gzdz, jtcy2_mz,
					jtcy2_zzmm, jtcy3_xm, jtcy3_gx, jtcy3_nl, jtcy3_sfzh,
					jtcy3_zy, jtcy3_zw, jtcy3_lxdh1, jtcy3_lxdh2, jtcy3_gzdz,
					jtcy3_mz, jtcy3_zzmm, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存毕业处理
	 * 
	 * @author 牛B的裘
	 */
	public boolean saveBycl(XsxxZxxsModel model, User user) throws Exception {

		//初始化学生信息表不存在数据
		boolean flag=xsxxInit(model, user);
		
		if(flag){
			
			flag=dao.saveBycl(model, user);
		}
		
		return flag;
	}
	
	/**
	 * 学生信息初始化
	 * 
	 * @author 牛B的裘
	 */
	public boolean xsxxInit(XsxxZxxsModel model, User user) throws Exception{
		
		return dao.xsxxInit(model, user);
	}

	/**
	 * 获取学生信息(历史学生) 2012.4.12 qlj
	 */
	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXsxxLsxsList(myForm, user);
	}
	
}