package xsgzgl.xsxx.general.xsxxgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;
import common.GlobalsVariable;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.xsxx.comm.xjyd.XsxxXjydForm;
import xgxt.xsxx.comm.xjyd.XsxxXjydService;
import xsgzgl.gygl.comm.GyglNewInter;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyYwjkInterface;
import xsgzgl.qgzx.xsgw.QgzxXsgwcxService;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhService;
import xsgzgl.xszz.general.XszzGeneralService;
import xsgzgl.xszz.general.inter.XszzYwjkInterface;

import com.zfsoft.xgxt.rcsw.cwsjcx.CwsjService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import common.GlobalsVariable;

/**
 * 学生业务信息处理SERVICE
 */
public class XsywxxService extends CommService {

	/**
	 * 通过学号获取学生成绩列表
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuCjList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "学生成绩");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuCjAllList(xh));

		rs.add(map);
		return rs;
	}

	/**
	 * 学生成绩表头
	 * 
	 * @return
	 */
	public List<String[]> getStuCjAllList(String xh) {
		XsxxtyglDao dao = new XsxxtyglDao();
		String[] title = { "学年", "学期", "课程名称", "课程性质", "成绩", "补考成绩", "绩点", "学分" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.addAll(dao.getStuCjList(xh));
		rs.add(0, title);
		return rs;
	}
	
	/**
	 * 通过学号查询学生成绩及等级考试信息列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuCjxxDjxxList(String xh) {
		XsxxtyglDao dao = new XsxxtyglDao();
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		//成绩 
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String[]> list = new ArrayList<String[]>();
		
		if(Globals.XXDM_GLLGDX.equals(Base.xxdm)){
			list.add(new String[]{"学年", "学期", "课程名称", "课程性质", "成绩", "补考成绩","重修成绩","绩点", "学分"});
			list.addAll(dao.getStugllgCjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "课程考试成绩");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}else if(Globals.XXDM_CZZYJSXY.equals(Base.xxdm)){
			list.add(new String[]{"学年", "学期", "课程名称", "课程性质", "成绩","期末成绩","绩点", "学分"});
			list.addAll(dao.getStuczxyCjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "课程考试成绩");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}else{
			list.add(new String[]{"学年", "学期", "课程名称", "课程性质", "成绩", "补考成绩", "绩点", "学分"});
			list.addAll(dao.getStuCjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "课程考试成绩");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}
		
			//等级考试
			map = new HashMap<String, Object>();
			list = new ArrayList<String[]>();
			list.add(new String[]{"学年", "学期", "等级考试名称", "成绩"});
			list.addAll(dao.getStuDjcjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "等级考试成绩");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		
		return rs;
		
	}

	/**
	 * 获取各业务模块类别HTML
	 * 
	 * @param gnmk
	 * @return
	 */
	public String getYwmklbHtml(String gnmk, String xh) {
		gnmk = unicode2Gbk(gnmk);
		StringBuffer html = new StringBuffer("");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String type = "no";// 用来判断是否有二级功能的标志
		if (GlobalsVariable.XTWH_GNMK_WJCF.equalsIgnoreCase(gnmk)) {// 违纪处分列表模块的处理
			WjcfCfsjwhService wjcfService = new WjcfCfsjwhService();
			list = wjcfService.getStuWjcfList(xh);
		} else if (GlobalsVariable.XTWH_GNMK_GYGL.equalsIgnoreCase(gnmk)) {// 公寓管理列表模块的处理
			GyglNewInter gyglInter = new GyglNewInter();
			list = gyglInter.getStuGyAllList(xh);
			//潍坊学院寝室物品维护、其它学校没有二级功能
			if(list.size()!=1){
				type = "yes";
			}
		} else if (GlobalsVariable.XTWH_GNMK_XSCJ.equalsIgnoreCase(gnmk)) {// 学生成绩列表模块的处理
			//list = getStuCjList(xh);
			list = getStuCjxxDjxxList(xh);
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_QGZX.equalsIgnoreCase(gnmk)) {// 勤工助学列表模块的处理
			QgzxXsgwcxService qgzxService = new QgzxXsgwcxService();
			list = qgzxService.getStuGwxxCjxxList(xh);
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_PJPY.equalsIgnoreCase(gnmk)) {// 评奖评优列表模块的处理
			try {
				//list = getStuPjList(xh);
				PjjgService pjjgService = new PjjgService();
				list = pjjgService.getPjpyInfo(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_XSZZ.equalsIgnoreCase(gnmk)) {// 学生资助列表模块的处理
			try {
			//	list = getStuZzList(xh);
				list = getStuZzListNew(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_RCSW.equalsIgnoreCase(gnmk)) {// 日常事务列表模块的处理
			try {
				
				list = getRcswInfo(xh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				type = "yes";
			} else if (GlobalsVariable.XTWH_GNMK_XJYD.equalsIgnoreCase(gnmk)) {// 学籍异动列表模块的处理，武汉职业技术学院个性化
			try {
					list = getStuXjydList(xh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_CWSJ.equalsIgnoreCase(gnmk)){//财务数据
			try {
				list = getStuCwsjList(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "no";
		} else if (GlobalsVariable.XTWH_GNMK_XJYDXX.equalsIgnoreCase(gnmk)) {// 学籍异动列表模块[通用]
			try {
				list = getXjydList(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "yes";
	}
		getHtmlByList(html, list, type);
		return html.toString();
	}
	


	/**
	 * @throws Exception  
	 * @描述: 学籍异动列表模块[通用]
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-27 下午04:03:51
	 * @param xh
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws 
	 */
	private List<HashMap<String, Object>> getXjydList(String xh) throws Exception {

		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		XjydjgService service = new XjydjgService();
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"学年","学期","异动类别","原年级","原"+Base.YXPZXY_KEY,"原专业","原班级","学籍异动文号","异动时间"});
		list.addAll(service.getXsydListByXh(xh));		
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "学籍异动信息");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}

	/** 
	 * @描述:(获取学生财务数据)
	 * @作者：cmj[工号：913]
	 * @日期：2013-8-28 下午07:19:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws 
	 */
	private List<HashMap<String, Object>> getStuCwsjList(String xh) throws Exception{
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		CwsjService service = new CwsjService();
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"学年","学期","应缴费用","实缴费用","欠缴费用"});
		list.addAll(service.getStuCwsjList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "财务数据信息");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}

	/**
	 * 通过列表拼接HTML字符串
	 * 
	 * @param html
	 * @param cjList
	 */
	private StringBuffer getHtmlByList(StringBuffer htmls,
			List<HashMap<String, Object>> cjList, String type) {
		if (cjList != null && cjList.size() > 0) {
			if ("yes".equalsIgnoreCase(type)) {// 有二级功能的拼接
				for (HashMap<String, Object> map : cjList) {
					String mkmc = (String) map
							.get(GlobalsVariable.XSXX_KTEYS_GNMK);
					List<String[]> rs = map
							.get(GlobalsVariable.XSXX_KTEYS_CXJG) != null ? (List<String[]>) map
							.get(GlobalsVariable.XSXX_KTEYS_CXJG)
							: null;
					int cols = rs != null && rs.get(0) != null ? rs.get(0).length
							: 0;

					// 拼接表头部分
					htmls.append("<table width='100%' id='tab_");
					htmls.append(mkmc);
					htmls
							.append("'><thead><tr onclick='' style='cursor: hand;'>");
					htmls.append("<th colspan='" + cols + "'><span>");
					htmls.append(mkmc);
					htmls.append("</span></th></tr></thead>");

					// 拼接结果集部分
					if (map.get(GlobalsVariable.XSXX_KTEYS_CXJG) != null) {
						List<String[]> list = (List<String[]>) map
								.get(GlobalsVariable.XSXX_KTEYS_CXJG);
						int size = list.size();
						for (int j = 0; j < list.size(); j++) {
							
							if (1 == size) {
								htmls.append("<tr><td colspan='"+cols+"'><div align='center'>暂无数据！</div></td></tr>");
								break;
							}
							
							htmls.append("<tr>");
							String[] array = list.get(j);
							String width = array != null ? ""
									+ (100 / array.length) : "100";
							for (int m = 0; m < array.length; m++) {
								if (j == 0) {
									htmls.append("<th width='" + width
											+ "%'><div align='center'>");
									htmls.append(array[m]);
									htmls.append("</div></th>");
								} else {
									htmls.append("<td  align='center'>");
									htmls.append(array[m]);
									htmls.append("</td>");
								}
							}
							htmls.append("</tr>");
						}
						
					}
					htmls.append("</tbody></table>");
				}
			} else {
				htmls.append("<table width='100%' ><tbody>");
				HashMap<String, Object> map = cjList.get(0);
				if (map.get(GlobalsVariable.XSXX_KTEYS_CXJG) != null) {
					List<String[]> list = (List<String[]>) map
							.get(GlobalsVariable.XSXX_KTEYS_CXJG);
					int size = list.size();
					int cols = list.get(0) != null ? list.get(0).length : 1;
					for (int j = 0; j < list.size(); j++) {
						
						if (1 == size) {
							htmls.append("<tr><td colspan='"+cols+"'><div align='center'>暂无数据！</div></td></tr>");
							break;
						}
						
						htmls.append("<tr>");
						String[] array = list.get(j);
						String width = array != null ? ""
								+ (100 / array.length) : "100";
						for (int m = 0; m < array.length; m++) {
							if (j == 0) {
								htmls.append("<th width='" + width
										+ "%'><div align='center'>");
								htmls.append(array[m]);
								htmls.append("</div></th>");
							} else {
								htmls.append("<td  align='center'>");
								htmls.append(array[m]);
								htmls.append("</td>");
							}
						}
						htmls.append("</tr>");
					}
					
				}
				htmls.append("</tbody></table>");
			}
		}
		return htmls;
	}

	/**
	 * 通过学号获取学生評獎列表
	 * 
	 * @param xh
	 * @author 偉大的駱
	 */
	public List<HashMap<String, Object>> getStuPjList(String xh)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyYwjkInterface service = myService.getPjpyYwjkService();

		List<HashMap<String, Object>> rs = service.getStuPjList(xh);

		return rs;
	}

	/**
	 * 通过学号获取学生资助列表
	 * 
	 * @param xh
	 * @author 偉大的駱
	 */
	public List<HashMap<String, Object>> getStuZzList(String xh)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		XszzGeneralService myService = new XszzGeneralService();
		XszzYwjkInterface service = myService.getXszzYwjkService();

		List<HashMap<String, Object>> rs = service.getStuZzList(xh);

		return rs;
	}
	
	
	public List<HashMap<String, Object>> getStuZzListNew(String xh)
	throws IllegalArgumentException, SecurityException,
		InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();

		//困难生认定信息
		KnsjgDao knsjgDao = new KnsjgDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String[]> list = new ArrayList<String[]>();
		
		list.add(new String[]{"学年", "学期", "认定档次", "认定时间"});
		list.addAll(knsjgDao.getKnsjgList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "困难生认定信息");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		
		//资助项目信息
		ZzxmjgDao zzxmjgDao = new ZzxmjgDao();
		map = new HashMap<String, Object>();
		list = new ArrayList<String[]>();
		list.add(new String[]{"学年", "学期", "项目名称", "金额", "申请时间"});
		list.addAll(zzxmjgDao.getZzxmjgList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "资助项目信息");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}
	
	/** 
	 * @描述: 通过学号获取学生学籍异动列表
	 * @作者：HongLin
	 * @日期：2013-5-20 上午11:51:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, Object>> getStuXjydList(String xh)
	throws Exception {

		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		XsxxXjydService service = new XsxxXjydService();
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"学年","学期","异动类别","转出"+Base.YXPZXY_KEY,"转出班级","转入"+Base.YXPZXY_KEY,"转入班级","异动时间"});
		XsxxXjydForm model = new XsxxXjydForm(); 
		model.setXh(xh);
		list.addAll(service.getStuXjydList(model));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "学籍异动信息");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}
	
	
	/**
	 * 
	 * 日常事务信息
	 * @作者：Dlq[工号：995]
	 * @日期：2013-10-16 上午10:25:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,Object>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, Object>> getRcswInfo(String xh) throws Exception{
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		RcxwjgService  rcxwjgService = new RcxwjgService();
		String[] title = { "行为记录学年", "行为记录学期", "行为记录大类", "行为记录类别", "行为记录时间","行为类别分值" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(rcxwjgService.getStuRcswList(xh));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "日常行为记录");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);
		
		return list;
	}
}
