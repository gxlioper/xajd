/**
 * @部门:学工产品事业部
 * @日期：2013-6-6 下午05:03:33 
 */
package com.zfsoft.xgxt.sztz.zyszpj;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xgxt.action.Base;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 职业素质评价
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路 [工号：982]
 * @时间： 2013-6-6 下午05:03:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZyszpjService extends SuperServiceImpl<ZyszpjForm, ZyszpjDao> {
	ZyszpjDao dao = new ZyszpjDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	public static String _TX_QUERY = "txcx";// 同学查询
	public static String _BR_QUERY = "brcx";// 本人查询.
	public static String _LS_QUERY = "lscx";// 老师查询
	public static String _TXPJ = "tx";// 同学评价
	public static String _LSPJ = "ls";// 老师评价
	public static String _BRPJ = "br";// 本人评价
	public static String _PRINT_CLASSPATH="classpath://templates//sztz//zyszpj";
	public static String _PRINT_WORD_PATH="zyszpj.xml";
	public ZyszpjService() {
		super.setDao(dao);
	}

	public String getQueryStr(String query, ZyszpjForm myForm) {
		String queryStr = _LS_QUERY;
		if (query.equals(_TX_QUERY)) {
			// myForm.setXh("");
			queryStr = _TX_QUERY;
		} else if (query.equals(_BR_QUERY)) {
			queryStr = _BR_QUERY;
		} else {
			// myForm.setXh("");
		}
		return queryStr;
	}
	/**
	 * 
	 * @描述:根据查询方式获取查询的path
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-25 上午09:54:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param path
	 * @param query
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPath(String path,String query){
		return path+"?method=list&query="+query;
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:获取打印的子项目信息
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-13 下午06:23:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return HashMap<String,HashMap<String,String>> 返回类型 <子项目名称,<子项目,对应详细信息>>
	 * @throws
	 */
	public List<Map<String, String>> getPrinForZxm(ZyszpjForm myForm)
			throws Exception {
		List<Map<String, String>> hs = new LinkedList<Map<String, String>>();
		List<HashMap<String, String>> list = dao.getXmlbForZysz(myForm
				.getZyszid());
		int xsxh = 0;// 显示序号
		for (HashMap<String, String> has : list) {
			xsxh++;
			int i = 1;
			List<HashMap<String, String>> h = dao.getZxmDetailForXmlbAndZyszid(
					myForm.getZyszid(), has.get("xmlbid"));
			if (h.size() == 1) {
				HashMap<String, String> zxm = h.get(0);
				zxm.put("xsbz", "1");// 只有一条
				zxm.put("xsxh", String.valueOf(xsxh));// 序号
				hs.add(zxm);
				continue;
			}
			for (HashMap<String, String> hm : h) {
				hm.put("xsbz", "2");// 多条数据的第一条数据
				hm.put("xsxh", String.valueOf(xsxh));// 序号
				if (i != 1) {// 同一子项目不是第一条数据
					hm.put("xsbz", "3");// 打印个标志通一子项目的其他数据 freemarker里面进行判断
				}
				hs.add(hm);
				i++;
			}
		}
		return hs;
	}

	/**
	 * @描述:批量打印
	 * @作者：张昌路
	 * @日期：2013-6-9 下午04:51:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return
	 * @throws Exception
	 *             File 返回类型
	 * @throws
	 */
	public File getRyzsWord(List<ZyszpjForm> mydata) throws Exception {
		List<File> files = new ArrayList<File>();
		for (ZyszpjForm zf : mydata) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("xh", zf.getXh());
			data.put("xm", zf.getXsxx().get("xm"));
			data.put("bj", zf.getXsxx().get("bj"));
			data.put("zpxx", zf.getZpxx());
			data.put("zpr", zf.getXsxx().get("xm"));
			data.put("hpxx", zf.getHpxx());
			data.put("hpr", zf.getHpr());
			data.put("spxx", zf.getSpxx());
			data.put("hpr", zf.getHpr());
			data.put("zxm", zf.getZxmMap());
			data.put("spr", zf.getSpr());
			data.put("dj", zf.getPjdj());
			files.add(getWord(data));
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		return zipFile;
	}

	/**
	 * 
	 * @描述:获取文档
	 * @作者：张昌路
	 * @日期：2013-6-17 下午04:50:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return File 返回类型
	 * @throws
	 */
	private File getWord(Map<String, Object> data) {
		File wordFile = FreeMarkerUtil.getWordFile(data,
				_PRINT_CLASSPATH, _PRINT_WORD_PATH);
		return wordFile;
	}
	/**
	 * 
	 * @描述:打印下载文档
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午04:56:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File printWord(ZyszpjForm zf) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("xh", zf.getXh());
		data.put("xm", zf.getXsxx().get("xm"));
		data.put("bjmc", zf.getXsxx().get("bjmc"));
		data.put("zpxx", HtmlUtil.xmlZy(zf.getZpxx()));
		data.put("zpr", zf.getXsxx().get("xm"));
		data.put("hpxx", HtmlUtil.xmlZy(zf.getHpxx()));
		data.put("hpr", zf.getHpr());
		data.put("spxx", HtmlUtil.xmlZy(zf.getSpxx()));
		data.put("hpr", zf.getHpr());
		data.put("zxm", zf.getZxmMap());
		data.put("spr", zf.getSpr());
		data.put("dj", zf.getPjdj());
		return getWord(data);
	}

	/**
	 * 
	 * @描述:整理子项目格式用于打印
	 * @作者：张昌路
	 * @日期：2013-6-17 上午09:02:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sjmap
	 * @return List<LinkedHashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<LinkedHashMap<String, String>> zlZxmForPrint(
			HashMap<String, HashMap<String, String>> sjmap) {
		LinkedHashMap<String, String> printMc = new LinkedHashMap<String, String>();
		Iterator<Entry<String, HashMap<String, String>>> it = sjmap.entrySet()
				.iterator();
		int i = 1;
		while (it.hasNext()) {
			Entry<String, HashMap<String, String>> entry = it.next();
			printMc.put("xsxh", String.valueOf(i));// 显示序号
			Iterator<Entry<String, String>> colum = entry.getValue().entrySet()
					.iterator();
			while (colum.hasNext()) {
				Entry<String, String> value = colum.next();
				if (i == 1) {// 第一次存放此项目名称
					printMc.put(entry.getKey(), value.getValue());
				} else {
					printMc.put(entry.getKey(), "");
				}
			}
			i++;
		}

		return null;
	}

	public List<String> aaa() {

		return null;
	}

	/**
	 * 查询学生个人信息
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetailGy(xh);
	}

	public List<HashMap<String, String>> getXmlb() throws Exception {
		return dao.getXmlbList();
	}

	/**
	 * 
	 * @描述:TODO(获得评价等级)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-13 上午10:43:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPjdj() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("pjdj", "优秀");
		hm.put("pjdjmc", "优秀");
		list.add(hm);
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put("pjdj", "及格");
		hm1.put("pjdjmc", "及格");
		list.add(hm1);
		HashMap<String, String> hm2 = new HashMap<String, String>();
		hm2.put("pjdj", "不及格");
		hm2.put("pjdjmc", "不及格");
		list.add(hm2);
		return list;
	}

	/**
	 * 
	 * @描述:TODO(获取是否可以操作互评信息)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-8 下午07:00:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @param czlx
	 *            操作类型 是互评还是师评
	 * @return String 返回类型 1可以操作 -1不可操作
	 * @throws
	 */
	public String isCanUpdate(ZyszpjForm zf, String czlx, String czr) {
		if (czlx.equalsIgnoreCase(ZyszpjService._TXPJ)) {// 如果是互评
			if(czr.equals(zf.getXh())){//本人不允许互评自己
				return "0";
			}else if (null != zf.getHprid() && !zf.getHprid().equals(czr)
					&& !StringUtil.isNull(zf.getHpxx())) {// 如果已经被互评且不是上次互评的同学不予操作
				return "-3";
			}
		}
		// 不再需要进行判断
		// else {
		/*
		 * String nj = dao.getBj(czr); String xxnj = dao.getBj(zf.getXh()); if
		 * (!nj.equals(xxnj)) {// 如果操作人不是同班同学则只能查看 return "-2"; }
		 */
		// }
		if (czlx.equalsIgnoreCase(ZyszpjService._LSPJ)) {// 如果是师评操作
			return "-1";
		}
		if (!StringUtil.isNull(zf.getSprid())) {
			return "-1";// 不可以修改互评信息
		}
		return "1";// 可以修改互评信息
	}

	/**
	 * 
	 * @描述:整理师评人和互评人名称
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-14 下午06:57:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @param iszl
	 *            是否整理互评师评
	 * @return ZyszpjForm 返回类型
	 * @throws
	 */
	public ZyszpjForm zlHprAndSpr(ZyszpjForm zf, boolean zlhp, boolean zlsp) {
		zf.setHpr(dao.getName(zf.getHprid()));
		zf.setSpr(dao.getNameForTeac(zf.getSprid()));
		// 整理页面显示信息
		if (StringUtil.isNull(zf.getHprid()) && zlhp) {
			zf.setHpxx("尚未被互评");
		}
		if (StringUtil.isNull(zf.getSprid()) && zlsp) {
			zf.setSpxx("尚未被师评");
		}
		return zf;
	}

	/**
	 * 
	 * @描述:根据评价类型整理互评师评默认值
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 上午10:21:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @param xglx
	 *            是否可以修改返回值,配合isCanUpdate使用
	 * @return ZyszpjForm 返回类型
	 * @throws
	 */
	public ZyszpjForm zlHpSp(ZyszpjForm zf, String xglx) {
		if (xglx.equals("-1")) {// 师评
			return zlHprAndSpr(zf, true, false);
		} else if (xglx.equals("1")) {// 互评
			return zlHprAndSpr(zf, false, true);
		}
		return this.zlHprAndSpr(zf);
	}

	/**
	 * 
	 * @描述:整理师评人和互评人名称
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-14 下午06:57:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param iszl
	 *            是否整理互评师评
	 * @return ZyszpjForm 返回类型
	 * @throws
	 */
	public ZyszpjForm zlHprAndSpr(ZyszpjForm zf) {
		zf.setHpr(dao.getName(zf.getHprid()));
		zf.setSpr(dao.getNameForTeac(zf.getSprid()));
		// 整理页面显示信息
		if (StringUtil.isNull(zf.getHprid())) {
			zf.setHpxx("尚未被互评");
		}
		if (StringUtil.isNull(zf.getSprid())) {
			zf.setSpxx("尚未被师评");
		}
		return zf;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:TODO(删除 所有职业素质相关信息)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午07:04:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean deleteZyszxxAll(String zyszId) throws Exception {
		return dao.deleteZysz(zyszId) && dao.deleteZxm(zyszId);
	}

	/**
	 * @throws Exception
	 * @throws Exception
	 * 
	 * @描述:TODO(批量删除 所有职业素质相关信息)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午07:04:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return int 返回类型 成功删除行数
	 * @throws
	 */
	public int runDeleteZysz(String[] ids) throws Exception {
		int i = 0;
		for (String str : ids) {
			boolean a = deleteZyszxxAll(str);
			System.out.println(a);
			i = a ? i + 1 : i;
		}
		return i;
	}
	/**
	 * 
	 * @描述:整理职业素质评价学期（原版本是直接保存的名称现在更改为保存对应代码）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-24 下午02:19:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public ZyszpjForm formatZyszpjForXq(ZyszpjForm zf){
		zf.setXqmc(dao.getXqmc(zf.getXq()));
		return zf;
	}
	/**
	 * 
	 * @描述:TODO(更新职业素质评价所有信息)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-8 下午03:29:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateZyszxxAll(ZyszpjForm form) throws Exception {
		form.setTxsj(GetTime.getNowTime());
		boolean flag = true;
		// 更新职业素质
		flag = flag && dao.updateZysz(form);
		// 删除对应子项目信息
		flag = flag && dao.deleteZxm(form.getZyszid());
		// 增加新的子项目信息
		List<ZxmForm> list = form.getZxm();
		flag = flag && dao.addZxm(list, form.getZyszid());
		return flag;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:TODO(更新职业素质评价中的评价信息)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-8 下午03:29:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateZyszPjxx(ZyszpjForm form) throws Exception {
		// 更新职业素质
		return dao.updateZysz(form);
	}
	/**
	 * 
	 * @描述:TODO(保存专业素质)
	 * @作者： 张昌路 [工号：982]
	 * @日期：2013-6-7 下午02:06:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws SQLException boolean 返回类型
	 * @throws
	 */
	public boolean saveZysz(ZyszpjForm form) throws SQLException {
		form.setTxsj(GetTime.getNowTime());
		form.setXn(Base.currXn);
		form.setXq(Base.currXq);
		boolean backe = false;
		List<ZxmForm> list = form.getZxm();
		if (dao.saveZysz(form)) {
			backe = dao.addZxm(list, form.getZyszid());
		}
		return backe;
	}

	public String getXmlbMc(String xmid) {
		return dao.getXmlbMc(xmid);
	}

	public boolean isCanAdd(ZyszpjForm form) throws Exception {
		List<HashMap<String, String>> list = dao.isCanAddZysz(form);
		if (null != list && list.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(整理所属的项目信息)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午05:33:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 *            void 返回类型
	 * @throws
	 */
	public void zlXm(ZyszpjForm form) throws Exception {
		List<ZxmForm> zxm = new ArrayList<ZxmForm>();
		List<Map<String, String>> list=this.getPrinForZxm(form);
		for (Map<String, String> hm : list) {
			ZxmForm zf = new ZxmForm();
			zf.setZyszId(hm.get("zyszid"));
			zf.setZxmId(hm.get("zxmid"));
			zf.setXmlbId(hm.get("xmlbid"));
			zf.setSj(hm.get("sj"));
			zf.setHdnr(hm.get("hdnr"));
			zf.setDd(hm.get("dd"));
			zf.setCyjhjqk(hm.get("cyjhjqk"));
			zf.setXmlbmc(this.getXmlbMc(hm.get("xmlbid")));
			zxm.add(zf);
		}
		form.setZxm(zxm);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:TODO(整理form数据 把添加的活动过程等信息增加进来)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午02:18:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zf
	 * @param xmlbid
	 * @param sj
	 * @param dd
	 * @param hdnr
	 * @param cyjhjqk
	 * @return ZyszpjForm 返回类型
	 * @throws
	 */
	public void zlForm(ZyszpjForm zf, String[] xmlbid, String[] sj,
			String[] dd, String[] hdnr, String[] cyjhjqk) throws Exception {
		if (xmlbid.length != dd.length || xmlbid.length != hdnr.length
				|| xmlbid.length != cyjhjqk.length) {
			throw new Exception("页面数据传递错误，请检查！");
		}

		List<ZxmForm> xmList = new ArrayList<ZxmForm>();
		for (int i = 0; i < xmlbid.length; i++) {
			ZxmForm xm = new ZxmForm();
			xm.setXmlbId(xmlbid[i]);
			xm.setSj(sj[i]);
			xm.setDd(dd[i]);
			xm.setHdnr(hdnr[i]);
			xm.setCyjhjqk(cyjhjqk[i]);
			xmList.add(xm);
			xm.setZyszId(zf.getZyszid());
		}
		zf.setZxm(xmList);
	}
}
