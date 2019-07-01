/**
 * @部门:学工产品事业部
 * @日期：2014-5-5 上午10:34:33 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjjg;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgDao;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-5 上午10:34:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjJgService extends
		SuperServiceImplExtend<JtpjJgForm, JtpjJgDao> {
	private JtpjJgDao jjd = new JtpjJgDao();
	/**
	 * 结果库
	 */
	private final String SJLY_JGK = "0";
	/**
	 * 流程数据
	 */
	private final String SJLY_LC = "1";

	public JtpjJgService() {
		super.setDao(jjd);
	}

	/**
	 * 
	 * @描述: 保存
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 上午09:42:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean save(JtpjJgForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		myForm.setSjly(SJLY_JGK);
		return runInsert(myForm);
	}

	/**
	 * @描述: 审核通过保存
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-5 下午02:34:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean shtgSave(JtpjJgForm myForm) throws Exception {
		//myForm.setSqxn(Base.currNd);
		//myForm.setSqxq(Base.currXq);
		myForm.setSjly(SJLY_LC);
		return runInsert(myForm);
	}

	/**
	 * 
	 * @描述: 修改
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 上午09:42:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean update(JtpjJgForm myForm) throws Exception {
		myForm.setSqsj(GetTime.getNowTime4());
		myForm.setSjly(SJLY_JGK);
		return runUpdate(myForm);
	}
	/**
	 * 
	 * @描述: 撤销删除结果数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-5 下午03:16:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean deleteForCx(String sqid) throws Exception {
		return dao.deleteJgForSqid(sqid);
	}
	
	/**
	 * @描述: 打印Word登记表（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 */
	public HashMap<String, String> getDjbInfo(String jgid) {
		return dao.getDjbInfo(jgid);
	}
	
	public HashMap<String, String> getDjb(String jgid) {
		return dao.getDjb(jgid);
	}
	
	//山东畜牧兽医职业学院个性化打印(先进班集体推荐表)
	public File getXmGxhDy_12947_xjbjt(String[] parameters)
		throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> Bzrxxlist = this.getBzrxxlist_12947(parameters);
		data.put("bzrxxlist",Bzrxxlist);
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//jtpj", "sdxmsy_12947_xjbjttjb.xml", "先进班集体推荐表" );
		return wordFile;

   }
	/**
	 * 
	 * @描述:山东畜牧职业个性化需求（先进班集体推荐表）
	 * @作者：张昌路[工号：1206]
	 * @日期：2015-6-5 下午02:17:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	private List<HashMap<String, String>> getBzrxxlist_12947(String[] values){
		return dao.getBzrxxlist(values);
	}
	
	//山东畜牧兽医先进班集体申请表获得入学日期
	public HashMap<String, String> getRxrq(String jgid){
		StringBuilder sql = new StringBuilder();
		return dao.getRxrq(jgid);
	}
	
	/**
	 * @描述：登记表自动填充所需数据
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月7日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param djbMap
	 * void 返回类型
	 */
	public HashMap<String, String> fillData(HashMap<String, String> djbMap){
		djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
		PjjgDao pjjgDao=new PjjgDao();
		JtpjJgDao jtpjJgDao = new JtpjJgDao();
		HashMap<String, String> splcmap=pjjgDao.getSpxxInfo2ji(djbMap.get("sqid"));
		djbMap.putAll(splcmap);
		if("bj".equals(djbMap.get("jtpjdw"))){
			djbMap.putAll(dao.getBjxx(djbMap.get("pjjtid")));
			djbMap.putAll(dao.getfdyxx(djbMap.get("pjjtid")));
		}
		djbMap.put("currYe", DateUtils.getYear());
		djbMap.put("currMo", DateUtils.getMonth());
		djbMap.put("currDa", DateUtils.getDayOfMonth());
		djbMap.put("sysDate", DateUtils.getLyr()); //xxxx年xx月xx日
		
		return djbMap;
	}
	
	/** 
	 * @描述:是否为班级奖项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-20 下午03:09:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isBjjx(String jxid){
		return dao.isBjjx(jxid);
	}

	public HashMap<String,String> gethbgydxDjb(String jgid) {
		return dao.gethbgydxDjb(jgid);
	}
}
