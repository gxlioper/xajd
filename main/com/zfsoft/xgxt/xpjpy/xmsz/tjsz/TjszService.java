/**
 * @部门:学工产品事业部
 * @日期：2013-7-31 下午05:00:59 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tjsz;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.tsxs.TsxsService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xtwh.bjdl.BjdlService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 条件设置
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-31 下午05:00:59
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TjszService extends SuperServiceImpl<TjszModel, TjszDao> {
	private static String FLAG_INPUT_NUM = "1";
	private static String FLAG_SELECT = "2";
	private static String FLAG_CHECKBOX = "3";
	private static String FLAG_CHECKBOX_DIV = "5";

	private static String FLAG_GXZGS_GDZ = "1";// 关系值格式为固定值时
	private static String FLAG_GXZGS_SJKQZ = "2";// 关系值格式为数据库取值,“表名:代码,名称”
	private static String FLAG_GXZGS_FF = "3";// 关系值格式为类名全称#方法名,若仅方法名，则取当前操作类中方法

	private static String CHAR_DH = ",";
	private static String CHAR_SX = "|";
	private static String CHAR_SX_REG = "[|]";
	private static String CHAR_FH = ";";
	private static String CHAR_MH = ":";
	private static String CHAR_JH = "#";

	private TjszDao dao = new TjszDao();

	public TjszService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:查询所有条件
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public TjszViewForm getAll(String xmdm) throws Exception {
		List<HashMap<String, String>> tjList = dao.getAllTj();
		List<HashMap<String, String>> gxList = dao.getAllGx();
		List<HashMap<String, String>> tjgxList = dao.getAllTjGx();
		List<HashMap<String, String>> tjszList = dao.getTjsz(xmdm);

		List<HashMap<String, String>> xnList = Base.getXnndList();
		List<HashMap<String, String>> xqList = Base.getXqList();
		List<HashMap<String, String>> yyfwList = new TsxsService().getTslxList("2");// 应用对象
		
		List<HashMap<String, String>> bjdlList = new BjdlService().getBjdlList();//班级大类
		for (HashMap<String, String> map : bjdlList) {
			map.put("lxdm",map.get("dm"));
			map.put("lxmc",map.get("mc"));
			yyfwList.add(map);
		}
		
		List<HashMap<String, String>> sfqyList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// 是否启用

		for (HashMap<String, String> map : tjgxList) {// 条件关系值处理
			String gxlx = null;
			String gxz = null;
			String gxzgs = null;
			gxlx = map.get("gxlx");
			gxz = map.get("gxzly");
			map.put("gxz", gxz);
			gxzgs = map.get("gxzgs");
			if (gxlx != null
					&& (gxlx.equals(FLAG_SELECT) || gxlx.equals(FLAG_CHECKBOX) || gxlx.equals(FLAG_CHECKBOX_DIV))) {// checkbox，通过方法名查询具体值
				gxz = getGxzGsh(gxzgs, gxz);
				map.put("gxz", gxz);
			}
		}

		TjszViewForm viewForm = new TjszViewForm();
		viewForm.setTjList(tjList);
		viewForm.setGxList(gxList);
		viewForm.setTjgxList(tjgxList);
		viewForm.setTjszList(tjszList);

		viewForm.setXnList(xnList);
		viewForm.setXqList(xqList);
		viewForm.setYyfwList(yyfwList);
		viewForm.setSfqyList(sfqyList);
		
		List<HashMap<String, String>> zhcpTjxmList = new ZcxmDao().getZhcpTjxm();//综测条件项目
		viewForm.setZhcpTjxmList(zhcpTjxmList);
		
		return viewForm;
	}

	/**
	 * 
	 * @描述:查询学年学期
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public TjszViewForm getXn(String xmdm) throws Exception {
		List<HashMap<String, String>> xnList = Base.getXnndList();
		List<HashMap<String, String>> xqList = Base.getXqList();
		String cuurXn = Base.currXn;
		if (cuurXn != null) {
			String cuurXnStart = cuurXn.substring(0, 4);
			String cuurXnEnd = cuurXn.substring(5, 9);

			HashMap<String, String> map = null;
			String xn = null;
			for (int i = xnList.size() - 1; i >= 0; i--) {
				map = xnList.get(i);
				xn = map.get("xn");
				if (xn != null && xn.length() >= 9) {
					String xnStart = xn.substring(0, 4);
					String xnEnd = xn.substring(5, 9);
					try {
						if (Integer.parseInt(cuurXnStart) < Integer
								.parseInt(xnStart)
								&& Integer.parseInt(cuurXnEnd) < Integer
										.parseInt(xnEnd)) {
							xnList.remove(i);
						}
					} catch (Exception e) {
						e.printStackTrace();// /////////////////
					}
				}
			}
		}
		TjszViewForm viewForm = new TjszViewForm();
		viewForm.setXnList(xnList);
		viewForm.setXqList(xqList);
		List<HashMap<String, String>> zhcpTjxmList = new ZcxmDao().getZhcpTjxm();//综测条件项目
		viewForm.setZhcpTjxmList(zhcpTjxmList);
		return viewForm;
	}

	/**
	 * 
	 * @描述:获取某个项目的所有条件设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm) throws Exception {
		return dao.getTjsz(xmdm);
	}

	/*
	 * 根据关系值格式，格式化关系值，转化为竖线分割格式
	 */
	private String getGxzGsh(String gxzgs, String gxz) throws Exception {
		String result = gxz;
		if (gxzgs != null && gxz != null) {
			if (gxzgs.equals(FLAG_GXZGS_SJKQZ)) {// FLAG_GXZGS_SJKQZ =
				// "2";//关系值格式为数据库取值,“表名:代码,名称”
				result = getSjkqz(gxz);
			} else if (gxzgs.equals(FLAG_GXZGS_FF)) {// FLAG_GXZGS_FF =
				// "3";//关系值格式为类名全称#方法名,
				result = getFf(gxz);
			}
		}
		return result;
	}

	/*
	 * 数据库取值,“表名:代码,名称”，返回格式用"|"连接，key1|value1|key2|value2 若配置不合法，异常捕获
	 */
	private String getSjkqz(String gxz) throws Exception {
		String result = "";
		String bm = null;// 表名
		String dm = null;// 代码
		String mc = null;// 名称
		String key = null;
		String value = null;//
		List<HashMap<String, String>> dmMcList = null;
		if (gxz != null) {
			try {
				String[] gxzs = gxz.split(CHAR_MH);
				if (gxzs != null && gxzs.length > 1) {
					bm = gxzs[0];
					dm = gxzs[1].split(CHAR_DH)[0];
					mc = gxzs[1].split(CHAR_DH)[1];
					dmMcList = dao.getDmMc(bm, dm, mc);
					boolean flag = false;
					for (HashMap<String, String> map : dmMcList) {
						key = map.get(dm);
						value = map.get(mc);
						if (value != null) {
							if (flag) {
								result += CHAR_SX;
							} else {
								flag = true;
							}
							result += key + CHAR_SX + value;
						}
					}
				}
			} catch (Exception e) {
				logger.error("条件配置不合法：", e);
			}
		}
		return result;
	}

	/*
	 * FLAG_GXZGS_FF = "3";//关系值格式为类名全称#方法名,若后加:”，
	 * 返回格式用"|"连接，key1|value1|key2|value2 若配置不合法，异常捕获
	 */
	private String getFf(String gxz) {
		String result = "";
		String lm = null;// 类名
		String ffms = null;// 方法名|参数
		String ffm = null;// 方法名
		String param = null;// 参数
		if (gxz != null) {
			try {
				String[] gxzs = gxz.split(CHAR_MH);
				if (gxzs != null) {
					lm = gxzs[0].split(CHAR_JH)[0];// 类名
					ffms = gxzs[0].split(CHAR_JH)[1];// 方法名|参数
					ffm = ffms.split(CHAR_SX_REG)[0];
					if (ffms.split(CHAR_SX_REG).length > 1) {
						param = ffms.split(CHAR_SX_REG)[1];
					}
					Class t = Class.forName(lm);
					Object o = t.newInstance();
					Method method = null;
					if (param == null) {
						method = t.getMethod(ffm);
					} else {
						method = t.getMethod(ffm, String.class);
					}
					if (gxzs.length == 1) {// 仅包含 类名全称#方法名，方法无参，返回为竖线分割的字符串
						result = (String) method.invoke(o);
					} else if (gxzs.length > 1) {// 类名全称#方法名:代码,名称，方法无参，返回为List<hashMap<String,String>>格式
						List<HashMap<String, String>> list = null;
						String dm = gxzs[1].split(CHAR_DH)[0];
						String mc = gxzs[1].split(CHAR_DH)[1];
						if (param == null) {
							list = (List) method.invoke(o);
						} else {
							list = (List) method.invoke(o, param);
						}

						boolean flag = false;
						for (HashMap<String, String> map : list) {
							String key = map.get(dm);
							String value = map.get(mc);
							if (value != null) {
								if (flag) {
									result += CHAR_SX;
								} else {
									flag = true;
								}
								result += key + CHAR_SX + value;
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("条件配置不合法：", e);
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述:获取某个项目的所有条件设置，包含条件关联，-----------供接口调用
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm,String xh)
			throws Exception {
		String sqts = null;
		String xn = null;
		String xnView = null;// 学年显示内容
		String tjz = null;
		String tjzView = null;
		String gxmc = null;
		String gxlx = null;
		String gxz = null;
		String gxzgs = null;// 关系值格式
		String zqlx = null;

		List<HashMap<String, String>> xqList = Base.getXqList();// 学期
		List<HashMap<String, String>> tjszGlList = dao.getTjszGl(xmdm,xh);
		for (HashMap<String, String> map : tjszGlList) {

			sqts = map.get("yzts");
			xn = map.get("ylzq");
			tjz = map.get("tjz");
			gxmc = map.get("gxmc");
			gxlx = map.get("gxlx");
			gxz = map.get("gxzly");
			map.put("gxz", gxz);
			gxzgs = map.get("gxzgs");
			zqlx = map.get("zqlx");
			if (sqts != null && !sqts.trim().equals("")) {
				xnView = xn;
				if(zqlx != null && zqlx.equals("3")){
					xnView = getZhcpTjxmMc(xn);
				}else{
					if (xnView != null && !xnView.trim().equals("")) {// 学年
						for (HashMap<String, String> xq : xqList) {
							xnView = xnView.replaceAll(CHAR_FH + xq.get("xqdm"),
									"学年" + xq.get("xqmc"));// 学期用名称显示
							xnView = xnView.replaceAll(CHAR_DH, "、");
						}
					} else {
						xnView = "";
					}				
				}

				sqts = sqts.replaceAll("\\$\\{xn\\}", xnView);
				if (gxmc == null) {
					gxmc = "";
				}
				sqts = sqts.replaceAll("\\$\\{gxmc\\}", gxmc);
				tjzView = tjz;
				if (tjzView == null) {
					tjzView = "";
				} else {
					if (gxlx == null) {
						continue;
					}
					if (gxlx.equals(FLAG_INPUT_NUM)) {// 数字文本框
						// 不做处理
					} else if (gxlx.equals(FLAG_SELECT) && gxz != null) {// 下拉框
						gxz = getGxzGsh(gxzgs, gxz);// 根据关系值格式，格式化关系值，转化为竖线分割格式
						String[] gxzs = gxz.split(CHAR_SX_REG);
						if (gxzs != null) {
							for (int i = 0; i < gxzs.length; i = i + 2) {
								String name = gxzs[i];
								String value = null;
								if (gxzs[i + 1] != null) {
									value = gxzs[i + 1];
								}
								if (tjzView.equals(name)) {
									tjzView = value;
									break;
								}
							}
						}
					} else if ((gxlx.equals(FLAG_CHECKBOX ) || gxlx.equals(FLAG_CHECKBOX_DIV)  )&& gxz != null) {// checkbox
						gxz = getGxzGsh(gxzgs, gxz);// 根据关系值格式，格式化关系值，转化为竖线分割格式
						map.put("gxz", gxz);
						String[] gxzs = gxz.split(CHAR_SX_REG);
						if (gxzs != null) {
							for (int i = 0; i < gxzs.length; i = i + 2) {
								String name = gxzs[i];
								String value = name;
								if (gxzs[i + 1] != null) {
									value = gxzs[i + 1];
								}
								tjzView = tjzView.replaceAll(name, value);// 条件
								tjzView = tjzView.replaceAll(CHAR_DH, "、");// 条件
							}
						}
					}
				}

				sqts = sqts.replaceAll("\\$\\{tjz\\}", tjzView);
				map.put("sqts", sqts);
			}
		}

		for (HashMap<String, String> map : tjszGlList) {
			map.put("xn", map.get("ylzq"));
			map.put("ffm", map.get("yzjk"));
			map.put("tjgx", map.get("gxdm"));
		}
		return tjszGlList;
	}
	

	/**
	 * 
	 * @描述:获取某个项目的所有条件设置，包含条件关联，-----------供接口调用
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm)
			throws Exception {
		return getTjszGl(xmdm,null);
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @param keys
	 * @param sfqy
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */

	public boolean delDeal(String xmdm, String tjdm) throws Exception {
		return dao.delDeal(xmdm, tjdm);
	}

	/**
	 * 
	 * @描述:更新保存
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录:
	 * @param xmdm
	 * @param keys
	 * @param sfqy
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveOrUpdate(String xmdm, List<TjszModel> list)
			throws Exception {
		return dao.runDeal(xmdm, list);
	}
	
	/**
	 * 
	 * @描述:测评条件，通过代码获取名称
	 * @作者：ligl
	 * @日期：2013-8-21 下午03:43:17
	 * @修改记录: 
	 * @param dm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZhcpTjxmMc(String dm){
		String mc = "";
		if(dm == null){
			return mc;
		}
		List<HashMap<String, String>> zhcpTjxmList = new ZcxmDao().getZhcpTjxm();//综测条件项目
		if(zhcpTjxmList != null && zhcpTjxmList.size() > 0){
			for (HashMap<String, String> map : zhcpTjxmList) {
				if(dm.equals(map.get("dm"))){
					mc = map.get("mc");
				}
			}
		}
		return mc;
	}
	
	/**
	 * 
	 * @描述:查询所有条件
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:07:42
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public TjszViewForm getJtpj(String xmdm,String lbdm) throws Exception {
		List<HashMap<String, String>> tjList = dao.getAllTj(lbdm);
		List<HashMap<String, String>> gxList = dao.getAllGx();
		List<HashMap<String, String>> tjgxList = dao.getAllTjGx(lbdm);
		List<HashMap<String, String>> tjszList = dao.getTjszJtpj(xmdm);

		List<HashMap<String, String>> xnList = Base.getXnndList();
		List<HashMap<String, String>> xqList = Base.getXqList();
		List<HashMap<String, String>> yyfwList = new TsxsService().getTslxList("2");// 应用对象
		
		List<HashMap<String, String>> bjdlList = new BjdlService().getBjdlList();//班级大类
		for (HashMap<String, String> map : bjdlList) {
			map.put("lxdm",map.get("dm"));
			map.put("lxmc",map.get("mc"));
			yyfwList.add(map);
		}
		
		List<HashMap<String, String>> sfqyList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// 是否启用

		for (HashMap<String, String> map : tjgxList) {// 条件关系值处理
			String gxlx = null;
			String gxz = null;
			String gxzgs = null;
			gxlx = map.get("gxlx");
			gxz = map.get("gxzly");
			map.put("gxz", gxz);
			gxzgs = map.get("gxzgs");
			if (gxlx != null
					&& (gxlx.equals(FLAG_SELECT) || gxlx.equals(FLAG_CHECKBOX) || gxlx.equals(FLAG_CHECKBOX_DIV))) {// checkbox，通过方法名查询具体值
				gxz = getGxzGsh(gxzgs, gxz);
				map.put("gxz", gxz);
			}
		}

		TjszViewForm viewForm = new TjszViewForm();
		viewForm.setTjList(tjList);
		viewForm.setGxList(gxList);
		viewForm.setTjgxList(tjgxList);
		viewForm.setTjszList(tjszList);

		viewForm.setXnList(xnList);
		viewForm.setXqList(xqList);
		viewForm.setYyfwList(yyfwList);
		viewForm.setSfqyList(sfqyList);
		
		List<HashMap<String, String>> zhcpTjxmList = new ZcxmDao().getZhcpTjxm();//综测条件项目
		viewForm.setZhcpTjxmList(zhcpTjxmList);
		
		return viewForm;
	}
	
	/**
	 * @描述：修改项目条件后审核中的不符合新条件的学生列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws Exception 
	 */
	public List<String> getbfhxhList(String xmdm) throws Exception{
		SqshDao sqshDao=new SqshDao();
		List<HashMap<String,String>> wshlist=sqshDao.getwshList(xmdm);
		List<String> resultList=new ArrayList<String>();
		if(wshlist.isEmpty()){
			return resultList;
		}
		CheckCondition check = new CheckStudentCondition();
		//对该项目每个审核中的学生进行条件判断
		for(HashMap<String,String>wshmap:wshlist){
			String xh=wshmap.get("xh");
			List<HashMap<String, String>> conditions = getTjszGl(xmdm, xh);
			// 校验结果
			List<HashMap<String, String>> results = check.checkCondition(xh,conditions);
			boolean checkResult = true;
//			StringBuffer checkInfo = new StringBuffer();
			if (results != null && !results.isEmpty()){
				for (Map<String,String> info : results){
					if (!Boolean.valueOf(info.get("result"))){
						checkResult = false;
						break;
					}
//					checkInfo.append(String.format("%s=%s;", info.get("result"),info.get("sqts")));
				}
			}
			//不符合评奖条件的学生加入结果集
			if(!checkResult){
//				wshmap.put("checkInfo", checkInfo.toString());
				resultList.add(xh);
			}
			
		}
		return resultList;
	}
	
	public List<HashMap<String,String>> getbfhList(TjszModel model,String xmdm,List<String> xhList) throws Exception{
		return dao.getbfhList(model,xmdm,xhList);
	}

}
