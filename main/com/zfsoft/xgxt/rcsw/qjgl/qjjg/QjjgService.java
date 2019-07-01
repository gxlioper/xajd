/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjjg;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjgz.QjgzService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: 请假申请service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjjgService extends SuperServiceImpl<QjjgForm, QjjgDao> {

	private static String YJXJ = "1";// 已经销假
	private static String SWXJ = "0";// 尚未销假
	// 请假信息老师操作
	public static String _QJXX_LSCZ = "1";
	/**
	 * 请假状态 草稿状态（结果库中代表正常申请信息）
	 */
	public static String _SQZT_CGZT = "0";

	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID = "-1";
	QjjgDao dao = new QjjgDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public QjjgService() {
		this.setDao(dao);
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:保存
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean save(QjjgForm model) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setShzt("");
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setQjjgid(UniqID.getInstance().getUniqIDHash());
		// 保存到申请
		// 为空则为老师结果库操作增加，同步到学生申请
		QjsqService qjsqService = new QjsqService();
		QjsqForm qf = new QjsqForm();
		model.setQjbh(qjsqService.getQjbh(model.getSqsj()));
		BeanUtils.copyProperties(qf, model);
		if (StringUtils.isNull(qf.getQjsqid())) {
			qf.setAddtype(_QJXX_LSCZ);// 老师增加
			qf.setSplcid("无需审核");// 无需审核
			qf.setShzt("");
			qjsqService.save(qf);
		}
		model.setQjbh(qf.getQjbh());
		model.setQjsqid(qf.getQjsqid());
		model.setSplcid("无需审核");
		//无需审核重复提交
		if(dao.isYtj(qf.getQjsqid())){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		boolean insertResult = super.runInsert(model);
		boolean result = insertResult;
/*		if (insertResult) {
			// 保存审核流程
			result = shlc.runSubmit(model.getQjsqid(), model.getSplcid());
		}*/
		return result;
	}
	@Override
	public QjjgForm getModel(QjjgForm t) throws Exception {
		t = dao.getModel(t);
		if(t!=null){
			// 把学期代码转换为学期名称
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		if(Base.xxdm.equals("10695")){//西藏民族大学专用
			QjsqService sqService = new QjsqService();
			t.setJtgjmc(sqService.getJtgjmc(t.getJtgj()));
		}
		return t;
	}

	/**
	 * 
	 * @描述:获得请假历史记录
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-16 下午03:22:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHistory(QjjgForm model)
			throws Exception {
		// 获取此学生所有请假记录
		List<HashMap<String, String>> history = dao.getListForXh(model.getXh());
		for (HashMap<String, String> hm : history) {
			if (hm.get("qjsqid")==null||model.getQjsqid().equals(hm.get("qjsqid"))) {
				history.remove(hm);
				break;
			}
		}
		return history;
	}
	/**
	 * 
	 */
	public HashMap<String, String> getQjjgForPrint(String qjjgid)
	throws Exception {
	return dao.getQjjgForPrint(qjjgid);
	}
	
	public boolean haveNewSqjl(QjjgForm model)
	throws Exception {
		return dao.haveNewSqjl(model);
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:31:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] 返回类型 String数组 0为成功删除条数为不能删除的
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// 可删除的id集合
		List<String> delSqId = new ArrayList<String>();// 对应可删除的申请数据id

		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
			if (dao.isCanDel(str)) {
				delId.add(str);// 记录删除id
				delSqId.add(getModel(str).getQjsqid());
				// del.append(str);
				// del.append(",");
			} else {
				HashMap<String, String> hm = dao.getQjjg(str);
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xn"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xqmc"));
				noDel.append(",</br>");
				isHaveNoId = true;
			}
		}
		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		if (i > 0) {
			// 删除对应审批流程信息
			for (String id : ids) {
				shlc.deleteShjl(id);
			}
			// 删除对应申请表信息
			deleteQjsq(delSqId.toArray(new String[] {}));
		}
		String str = noDel.toString();
		// 去除最后多余逗号
		str = isHaveNoId ? str : _BCZSCID;
		return new String[] { String.valueOf(i), str };
	}

	/**
	 * 
	 * @描述:删除申请数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-14 下午07:11:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @throws Exception void 返回类型
	 * @throws
	 */
	private void deleteQjsq(String[] values) throws Exception {
		QjsqService qjsq = new QjsqService();
		qjsq.delete(values, true);
	}

	/**
	 * 
	 * @描述:从申请复制到结果集
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-12 下午04:33:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean copyForSq(QjjgForm qf) throws Exception {
		qf.setQjzt(_SQZT_CGZT);// 正常审核的数据
		return super.runInsert(qf);
	}

	/**
	 * 
	 * @描述:请假申请修改
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午04:53:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean update(QjjgForm model) throws Exception {
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		// 如果没有对应申请id则为结果库添加 不需要设置申请流程
		if (StringUtil.isNull(model.getQjsqid())) {
			model.setShzt(Constants.YW_WTJ);
			return super.runUpdate(model);
		}
		/*
		 * if(!"无需审核".equals(hm.get("splcid"))){ model.setShzt(Constants.WSH);
		 * }else{
		 */
		model.setShzt("");
		// }

		// 为空则为老师结果库操作增加，同步到学生申请
		QjsqService qjsqService = new QjsqService();
		QjsqForm qf = new QjsqForm();
		BeanUtils.copyProperties(qf, model);
		qf.setAddtype(_QJXX_LSCZ);// 老师操作
		
		qjsqService.update(qf);
		boolean insertResult = super.runUpdate(model);
		boolean result = insertResult;
/*		if (insertResult) {
			shlc.deleteShjl(model.getQjsqid());
			result = shlc.runSubmit(model.getQjsqid(), model.getSplcid());
		}*/
		return result;
	}

	/**
	 * 
	 * @描述:销假处理
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-12 上午10:20:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean xjcl(QjjgForm model,User user) throws Exception {
		// QjjgForm qf=getModel(model);
		// BeanUtils.copyProperties(model,qf);
		model.setXjzt(YJXJ);
		model.setXjr(user.getUserName());
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		model.setXjsj(time);
		boolean insertResult = super.runUpdate(model);
		return insertResult;
	}

	/**
	 * 
	 * @描述:获取对应的请假规则
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午04:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param isShow 是否是显示
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 * @throws
	 */
	public HashMap<String, String> getSplc(QjjgForm model, boolean isShow) throws Exception {
		HashMap<String,String> data=null;
		//如果是显示则不去验证天数（即显示允许对应规则已经修改）
		if(isShow){
			data= new HashMap<String, String>();
			data.put("splcid", "无需审核");
			// 获取具体对应请假规则
			if(StringUtils.isNotNull(model.getSplcid())){//已经存在对应流程id，直接返回
				data.put("splcid",model.getSplcid());
				return data ;
			}
		}
		QjgzService qjgz = new QjgzService();
		List<HashMap<String, String>> list = qjgz.getAllList(new QjgzForm());
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			//Integer qjtsInt = Integer.parseInt(model.getQjts());
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj)) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)) {
				return hm;
			}
		}
		return data;
	}
	public HashMap<String, String> getSplc(QjjgForm model) throws Exception {
		//默认不是显示模式
		return getSplc(model,false);
	}
	/**
	 * 
	 * @描述:
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午03:49:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getShxxAndXjxx(
			List<HashMap<String, String>> list) {
		return list;
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjjgForm t)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t));
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjjgForm t, User user)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t, user));
	}

	/**
	 * 查询学生个人信息
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}

	public List<HashMap<String, String>> getQjlx() {
		return dao.getQjlx();
	}

	/**
	 * 
	 * @描述:获得销假信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-12 上午11:06:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             QjjgForm 返回类型
	 * @throws
	 */
	public QjjgForm getXjxx(String qjsqid) throws IllegalAccessException,
			InvocationTargetException {
		HashMap<String, String> hm = dao.getXjxx(qjsqid);
		QjjgForm qf = new QjjgForm();
		if (null == hm || hm.size() <= 0) {
			qf.setXjzt(SWXJ);
		} else {
			qf.setXjzt(YJXJ);
			BeanUtils.copyProperties(qf, hm);
		}
		return qf;
	}

	/**
	 * 
	 * @描述:是否存在对应天数的审核流程
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午06:58:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean isHaveSplcForTs(QjjgForm model) throws Exception {
		HashMap<String, String> splc = getSplc(model);
		if (null == splc) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @描述:以word方式打印
	 * @作者：王洪锦[工号：1004]
	 * @日期：2013-12-17 上午10:13:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsxx 学生信息
	 * @param jtqkmodel  家庭信息
	 * @param xxmc  学校名称
	 * @param knsdc	困难生档次
	 * @param rddc rddc
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File printForWord(HashMap<String, String> xsjbxx,HashMap<String, String> qjjgMap) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String qjsj="自  "+DateTranCnDate.fomartDateToCn(qjjgMap.get("kssj"),FomartDateType.day);
		qjsj+="  至  "+DateTranCnDate.fomartDateToCn(qjjgMap.get("jssj"),FomartDateType.day);
		qjsj+=" 共  "+qjjgMap.get("qjts")+" 天";
		if("10351".equals(Base.xxdm)){
			qjsj+=" 共  "+qjjgMap.get("qjjs")+" 节";
		}
		qjjgMap.put("qjsj",qjsj);
		
		qjjgMap.put("qjsy", HtmlUtil.xmlZy(qjjgMap.get("qjsy")));
		qjjgMap.remove("xh");
		qjjgMap.put("sqsj",DateTranCnDate.fomartDateToCn(qjjgMap.get("sqsj"),FomartDateType.day));
		qjjgMap.put("shsj1",DateTranCnDate.fomartDateToCn(qjjgMap.get("shsj1"),FomartDateType.day));
		qjjgMap.put("shsj2",DateTranCnDate.fomartDateToCn(qjjgMap.get("shsj2"),FomartDateType.day));
		qjjgMap.put("shsj3",DateTranCnDate.fomartDateToCn(qjjgMap.get("shsj3"),FomartDateType.day));
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.putAll(xsjbxx);
		data.putAll(qjjgMap);
		return getWord(data);
	}
	/**
	 * @throws FileNotFoundException 
	 * 
	 * @描述:获取文档
	 * @作者：王洪锦
	 * @日期：2013-12-17 上午10:13:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return File 返回类型
	 * @throws
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"rcsw/qjsq_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	
	/**
	 * 
	 * @描述:华师大请假申请打印辅导员留存报表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-11-22 下午03:26:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File 返回类型 
	 * @throws
	 */
	public File getWordOfFdy(Map<String, Object> data) throws FileNotFoundException {

		String templatePath = Constants.TEP_DIR+"rcsw/qjsq_fdy_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq_fdy_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "rcsw", "qjsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	
	
	/**查询请假项目列表*/
	public List<HashMap<String,String>> getQjxmList(){
		return dao.getQjxmList();
	}
	
	public HashMap<String,String> getCssz(){
		return dao.getCssz();
	}
	//请假天数格式化
	private String qjtsFormat(String qjts) {
		int lastStr = qjts.indexOf(".");
		if (qjts.length() - 1 == lastStr) {
			String[] gsArr = qjts.split("\\.");
			qjts = gsArr[0];
		}
		return qjts;
	}
	
	/**
	 * 
	 * @描述:取倒数第二审核流程信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-8 上午09:44:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> xjrpdTenD(String sqid) {
		return dao.xjrpdTenD(sqid);
	}
	
	/**
	 * 
	 * @描述:取最后一级审核流程信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-8 上午09:44:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> xjrpdOneToN(String sqid) {
		return dao.xjrpdOneToN(sqid);
	}
	
	/**
	 * @描述: 根据qjjgid查询学生请假结果信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-13 下午02:21:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQjjgxxForQjsqid(String qjjgid){
		return dao.getQjjg(qjjgid);
	}
	
	/**
	 * 移动端销假
	 */
	public List<HashMap<String, String>> getPageListXj(QjjgForm t, User user)
	throws Exception {
		return dao.getPageListXj(t, user);
	}
}
