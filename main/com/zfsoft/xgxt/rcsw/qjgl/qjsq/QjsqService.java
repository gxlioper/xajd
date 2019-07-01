/**
s * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: 请假申请service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjsqService extends SuperServiceImpl<QjsqForm, QjsqDao> {
	// 请假申申请 提交状态
	public static String _SQZT_TJZT = "1";
	public static String _SQZT_CGZT="0";
	//请假信息老师操作
	public static String _QJXX_LSCZ="1";
	//非老师操作请假信息（无权限修改 删除）
	public static String _QJXX_FLSCZ="0";
	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID="-1";
	QjsqDao dao = new QjsqDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public QjsqService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @描述:保存
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public boolean save(QjsqForm model) throws Exception {
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		HashMap<String, String> hm = getSplc(model);
		/**
		 * 存在交互特例
		 */
		if (null==hm) {
			model.setSplcid("无需审核");
		}else{
			model.setSplcid(hm.get("splcid"));
		}
		
		//华师大个性化请假编号
		if("10511".equals(Base.xxdm)){
			String qjbh = dao.getQjbh(model.getSqsj());
			model.setQjbh(qjbh);
		}
		boolean result = true;
		// 需要审核的,且不是老师操作
		if ((StringUtils.isNull(model.getAddtype())||!model.getAddtype().equals(_QJXX_LSCZ))) {
			model.setShzt(Constants.YW_WTJ);
		} else {
			model.setShzt("");
/*			// 1代表结果库老师操作
			if (isAddToQjjg(model)) {
				// 获取数据库申请数据
				QjjgForm qf = new QjjgForm();
				QjjgService qs = new QjjgService();
				// 对应属性复制到结果库
				BeanUtils.copyProperties(qf, StringUtils.formatData(model));
				qf.setQjzt(_QJXX_FLSCZ);
				result = qs.save(qf);
			}*/
		}
		if(StringUtil.isNull(model.getQjsqid())){
			model.setQjsqid(UniqID.getInstance().getUniqIDHash());
			result = super.runInsert(model);
		} else {
			result = super.runUpdate(model);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @描述: 保存请假明细
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-11-25 下午01:52:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param mxrq
	 * @param mxxmList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQjmx(QjsqForm model,String[] mxrq,List<String[]> mxxmList) throws Exception{
		
		List<String[]> params = new ArrayList<String[]>();
		
		for (int i = 0 ; i < mxxmList.size() ; i++){
			if (mxxmList.get(i) != null){
				
				String[] param = new String[]{model.getQjsqid(),mxrq[i],StringUtils.joinArr(mxxmList.get(i))};
				params.add(param);
			}
		}
		
		dao.delQjmx(model.getQjsqid());
		return dao.saveQjmx(params);
	}
	
	
	/**
	 * 
	 * @描述:提交
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-18 下午05:03:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean tj(QjsqForm qjsq) throws Exception{
		
		QjsqForm t = getModel(qjsq);		
		//BeanUtils.copyProperties(t, qjsq);	modified by xiaxia 2014-12-19 getModel()已获取值
		t.setSplcid(qjsq.getSplcid());	
		t.setQjzt(_SQZT_TJZT);
		if ("无需审核".equals(t.getSplcid())) {
			t.setShzt("");
		}
		boolean insertResult= runUpdate(t);
		if(insertResult){
			//如果是无需审核同步到结果库
			if ("无需审核".equals(t.getSplcid())) {
				// 获取数据库申请数据
				QjjgForm qf = new QjjgForm();
				QjjgService qs = new QjjgService();
				// 对应属性复制到结果库
				BeanUtils.copyProperties(qf, StringUtils.formatData(t));
				qf.setQjzt(_QJXX_FLSCZ);
				insertResult= qs.save(qf);
			}else{
				//更改提交时才操作审核流程
				if (insertResult) {
					// 保存审核流程
					insertResult = shlc.runSubmit(t.getQjsqid(), t.getSplcid(),t.getXh(),"qjshbase.do","qjsqbase.do");
					t.setShzt(Constants.YW_SHZ);
					runUpdate(t);
				}
			}
		}
		return insertResult;
	}
	@Override
	public QjsqForm getModel(QjsqForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// 把学期代码转换为学期名称
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		if(Base.xxdm.equals("10695")){//西藏民族大学专用
			t.setJtgjmc(getJtgjmc(t.getJtgj()));
		}
		return t;
	}
	/**
	 * 
	 * @描述:是否需要同步到结果库
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-16 下午04:11:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型 
	 */
	private boolean isAddToQjjg(QjsqForm model){
		if(StringUtils.isNull(model.getQjzt())){
			return false;
		}
		//是草稿状态
		if(model.getQjzt().equals(_SQZT_CGZT)){
			return false;
		}
		//是老师操作
		if(StringUtils.isNotNull(model.getAddtype())&&model.getAddtype().equals(_QJXX_LSCZ)){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-14 下午07:17:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return int 返回类型 
	 * @throws Exception
	 */
	private int qjsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	/**
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:31:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return String[] 返回类型   String数组[0]为成功删除条数 [1]为不能删除提示信息
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception 
	 */
	public String[] delete(String[] ids) throws Exception{
		return delete(ids,false);
	}
	/**
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-16 下午07:40:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids 删除id数组
	 * @param isQjjg 是否是结果库操作
	 * @return String[] 返回类型 
	 * @throws Exception
	 */
	public String[] delete(String[] ids,boolean isQjjg) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		List<String> delSqId=new ArrayList<String>();//对应可删除的申请数据id
		
		StringBuffer noDel=new StringBuffer();
		StringBuffer notFound=new StringBuffer();

		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			QjsqForm qf=getModel(str);
			if(dao.isCanDel(str)||isQjjg){
				delId.add(str);//记录删除id
				if(null!=qf){
				delSqId.add(qf.getQjsqid());
				}
			}else{
				HashMap<String, String> hm=dao.getQjsq(str);
				if(null==hm||hm.size()<=0){
					notFound.append("学生");
					notFound.append("&nbsp;");
					notFound.append(qf.getXh());
					notFound.append(",</br>");
					continue;
				}
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xn"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xqmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		if(notFound.length()>0){
			//去掉结尾逗号
			notFound.delete(notFound.length()-",</br>".length(),notFound.length());
			notFound.append("</br>");
			notFound.append("<font color='black'>已经不存在！</font></br>");
		}
		notFound.append(noDel);//合并消息
		int i=delId.size()>0?qjsqDelete(delId.toArray(new String[]{})):0;
		String str=notFound.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		if(!_BCZSCID.equals(str)){
			str=str.substring(0, str.length()-",</br>".length());
		}
		return new String[]{String.valueOf(i),str};
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
	 */
	public boolean update(QjsqForm model) throws Exception {
		boolean result = true;
		model.setQjts(qjtsFormat(model.getQjts()));
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
//		HashMap<String, String> hm = getSplc(model);
//		if (null==hm) {
//			model.setSplcid("无需审核");
//		}else{
//			model.setSplcid(hm.get("splcid"));
//		}
		// 需要审核的,且不是老师操作
		if (!"无需审核".equals(model.getSplcid())&&(StringUtils.isNull(model.getAddtype())||!model.getAddtype().equals(_QJXX_LSCZ))) {
			//model.setShzt(Constants.YW_WTJ);
		} else {
			model.setShzt("");
			// 1代表结果库老师操作
			if (isAddToQjjg(model)) {
				// 获取数据库申请数据
				QjjgForm qf = new QjjgForm();
				QjjgService qs = new QjjgService();
				// 对应属性复制到结果库
				BeanUtils.copyProperties(qf, StringUtils.formatData(model));
				dao.deletQjjgForQjsqid(model.getQjsqid());
				qf.setQjzt(_QJXX_FLSCZ);
				result = qs.save(qf);
			}
		}
		boolean insertResult = super.runUpdate(model);
		//如果非提交状态则不处理流程信息
		if (insertResult && result&&model.getQjzt().equals(_SQZT_TJZT)) {
			shlc.deleteShjl(model.getQjsqid());
			result = shlc.runSubmit(model.getQjsqid(), model.getSplcid(),model.getXh(),"qjshbase.do","qjsqbase.do");
			model.setShzt(Constants.YW_SHZ);
			runUpdate(model);
		}
		return result;
	}

	/**
	 * 
	 * @描述:获取对应的请假规则
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午04:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param isShow 是否是显示模式
	 * @return  String 返回类型
	 * @throws Exception
	 */
	public HashMap<String, String> getSplc(QjsqForm model,boolean isShow) throws Exception {
		HashMap<String,String> data=null;
		//如果是显示则不去验证天数（即显示允许对应规则已经修改）
		//by yxy 补充原来注释，查看时isShow才为true
		if(isShow){
			data= new HashMap<String, String>();
			data.put("splcid", "无需审核");
			// 获取具体对应请假规则
			if(StringUtils.isNotNull(model.getSplcid())){//已经存在对应流程id，直接返回
				data.put("splcid",model.getSplcid());
				return data ;
			}
		}
//		QjgzService qjgz = new QjgzService();
//		List<HashMap<String, String>> list = qjgz.getAllList(new QjgzForm());
		/**
		 * 分别取出有类型和无类型请假规则List集合
		 */
		List<HashMap<String, String>> list = this.getYlxQjgzList();
		List<HashMap<String, String>> wlxlist = this.getWlxQjgzList();
		
	
		/**
		 * 验证规则：
		 * 第一步，先验证请假类型和天数是否与请假规则中的项目匹配，请假类型和天数匹配做与判断，如果则定位该规则的审批流程
		 * 第二步，在第一步无任何项目匹配的情况下，查找是否存在天数匹配且请假类型为空的匹配规则，如果有则定位该规则的审批流程
		 * 第三步，在第一步和第二步无匹配的情况下，直接定为"无需审核";
		 */
		/*原来的判断*/
		/*for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid()) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid())) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj) && qjlxid.equalsIgnoreCase(model.getQjlxid())) {
				return hm;
			}
		}*/
		//加了所属学院后，在原来的基础上，先判断所属学院的是否符合
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid()) &&
					ssxydm.equalsIgnoreCase(model.getSsxydm())) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& qjlxid.equalsIgnoreCase(model.getQjlxid())
					&& ssxydm.equalsIgnoreCase(model.getSsxydm())) {
				return hm;
			}
		}
		//在判断全校的
		for (HashMap<String, String> hm : list) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String qjlxid = hm.get("qjlxid");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) &&
					qjlxid.equalsIgnoreCase(model.getQjlxid()) &&
					"qx".equalsIgnoreCase(ssxydm)) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& qjlxid.equalsIgnoreCase(model.getQjlxid())
					&& "qx".equalsIgnoreCase(ssxydm)) {
				return hm;
			}
		}
		/*原来的判断*/
		/*for (HashMap<String, String> hm : wlxlist) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj) ) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj) ) {
				return hm;
			}
		}*/
		//加了所属学院后，在原来的基础上，先判断所属学院的是否符合
		for (HashMap<String, String> hm : wlxlist) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj)
					&& ssxydm.equalsIgnoreCase(model.getSsxydm()) ) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& ssxydm.equalsIgnoreCase(model.getSsxydm()) ) {
				return hm;
			}
		}
		//在判断全校的
		for (HashMap<String, String> hm : wlxlist) {
			String kssj = hm.get("kssj");
			String jssj = hm.get("jssj");
			String ssxydm = hm.get("ssxydm");
			Double qjtsDoub = Double.parseDouble(model.getQjts());
			if (Double.parseDouble(kssj) == qjtsDoub
					&& qjtsDoub == Double.parseDouble(jssj)
					&& "qx".equalsIgnoreCase(ssxydm) ) {
				return hm;
			}
			if (Double.parseDouble(kssj) < qjtsDoub
					&& qjtsDoub <= Double.parseDouble(jssj)
					&& "qx".equalsIgnoreCase(ssxydm) ) {
				return hm;
			}
		}
		return data;
	}
	public HashMap<String, String> getSplc(QjsqForm model) throws Exception {
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
	 */
	public List<HashMap<String, String>> getShxxAndXjxx(
			List<HashMap<String, String>> list) {
		return list;
	}
	/**
	 * 
	 * @描述:获取尚未提交的数量
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-18 下午03:54:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型 
	 * @throws Exception 
	 */
	public String getWtjs(QjsqForm t, User user) throws Exception{
		Integer i=dao.getWtjts(t,user);
		return i.toString();
	}
	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t));
	}

	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t, User user)
			throws Exception {
		return getShxxAndXjxx(super.getPageList(t, user));
	}

	/**
	 * 
	 * @描述:是否可以进行添加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午07:19:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 */
	public boolean isCanAdd(QjsqForm qf) {
		return dao.checkQjlx(qf);
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

	/**
	 * 
	 * @描述:获取请假类型
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:44:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getQjlx() {
		return dao.getQjlx();
	}

	/**
	 * 
	 * @描述:是否存在对应天数的审核流程
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-10 下午06:58:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return  boolean 返回类型
	 * @throws Exception
	 */
	public boolean isHaveSplcForTs(QjsqForm model) throws Exception {
		HashMap<String, String> splc = getSplc(model);
		if (null == splc) {
			return false;
		}
		return true;
	}
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
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
	public File printForWord(HashMap<String, String> xsxx) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsxx == null){
			xsxx = new HashMap<String, String>();
		}
		data.putAll(xsxx);
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
	 * @日期：2014-11-22 下午02:26:39
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
	/**
	 * 
	 * @描述:获取学生当前学年学期的课程
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-11-18 下午02:48:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjkcList(QjsqForm model) throws Exception{
		return dao.getQjkcList(model);
	}
	/**
	 * 
	 * @描述:获取请假课程信息
	 * @作者：xiaixa [工号：1104]
	 * @日期：2014-11-19 上午10:08:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKcList(String kcbh) throws Exception{
		if(null==kcbh||""==kcbh){
			return new ArrayList<HashMap<String, String>>();
		}else{
		String[] kcbhs = kcbh.split(";");
		return dao.getkcList(kcbhs);
		}
	}
	
	public List<HashMap<String,String>> getQjmxList(String id){
		
		List<HashMap<String,String>> qjmxList = dao.getQjmxList(id);
		
		return qjmxList;
	}
	
	/**
	 * 
	 * @描述:华师大个性化请假编号
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-11-25 下午06:51:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqsj
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getQjbh(String sqsj){
		return dao.getQjbh(sqsj);
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

	public boolean getDupStatusSerivce(QjsqForm model) {
		return dao.getDupStatusDao(model);
	}
	
	/**
	 * 
	 * @描述:有规则请假规则List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-30 上午11:31:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYlxQjgzList(){
		return dao.getYlxQjgzList();
	}
	
	/**
	 * 
	 * @描述:无规则请假规则List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-30 上午11:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWlxQjgzList(){
		return dao.getWlxQjgzList();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 周末或者节假日选择时间正确性判断
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-18 上午11:42:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjlxid
	 * @param kssj
	 * @param jssj
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String, String> checkJjrOrWeekend(String qjlxid,String kssj,String jssj) throws Exception{
		QjlxForm qjlx = new QjlxService().getModel(qjlxid);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		boolean jjrRs = dao.checkJjr(kssj, jssj);
		
		/**
		 * 优秀判断所选的日期是否为节假日，如果日期期间是节假日，
		 * 而选择类型是非节假日，判断为false,返回错误
		 */
		if(!qjlx.getQjlxmc().equals("周末假") && !qjlx.getQjlxmc().equals("节假日假")){
			return dataMap;
		}else if(qjlx.getQjlxmc().equals("周末假") && jjrRs){
			
				dataMap.put("rs", "false");
				dataMap.put("message", "请假类型请选择节假日假！");
		   
		}else if(qjlx.getQjlxmc().equals("周末假") && !dao.checkWeekDay(kssj, jssj)){
			dataMap.put("rs", "false");
			dataMap.put("message", "您选择的时间非周末时间！");
		}else if(qjlx.getQjlxmc().equals("周末假") && !dao.checkIsOneWeekend(kssj, jssj)){
			dataMap.put("rs", "false");
			dataMap.put("message", "周末假开始时间和结束时间必须在同一周！");
			
		}else if(!jjrRs && qjlx.getQjlxmc().equals("节假日假")){
		
			dataMap.put("rs", "false");
			dataMap.put("message", "您选择的时间非节假日时间！");
			
		}else{
			dataMap.put("rs", "true");
		}
		return dataMap;
		
	}
	
	/** 
	 * @描述:获取交通工具名称(西藏民族大学专用)
	 * @作者：lj[工号：1282]
	 * @日期：2017-7-7 下午01:38:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getJtgjmc(String dm) {
		return dao.getJtgjmc(dm);
	}
	
	/**
	 * @描述: 根据获取的id查询相关信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 下午08:04:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQjsqxxForQjsqid(String qjsqid){
		return dao.getQjsq(qjsqid);
	}
	
	/**
	 * 
	 * @描述: 徐州医药个性化判断
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-12 上午10:36:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkIfOverTime(String startDate,String endDate){
		return dao.checkIfOverTime(startDate, endDate);
	}
}
