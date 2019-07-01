/**
 * @部门:学工产品事业部
 * @日期：2015-1-6 下午02:43:00 
 */
package xsgzgl.qgzx.mrgzkh.khsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglDAO;
import xsgzgl.qgzx.cssz.QgzxCsszDAO;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 每日工作考核管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-6 下午02:43:00
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class GzkhKhsqService extends SuperServiceImpl<GzkhKhsqForm, GzkhKhsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	GzkhKhsqDao dao = new GzkhKhsqDao();

	/**
	 * 
	 * @描述:加载学生岗位信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-12 上午10:28:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param model
	 *            void 返回类型
	 * @throws
	 */
	public void setXsGwxx(HttpServletRequest request, GzkhKhsqForm model) {
		List<HashMap<String, String>> yrdwList = getYrbm(model);
		if (yrdwList.size() > 0 && "" != yrdwList.get(0).get("bmdm")) {
			if ("".equals(model.getYrdw()) || null == model.getYrdw()) {
				model.setYrdw(yrdwList.get(0).get("bmdm"));
			}
		}
		List<HashMap<String, String>> gwList = getGwxx(model);
		request.setAttribute("gwList", gwList);
		request.setAttribute("yrdwList", yrdwList);
	}

	public List<HashMap<String, String>> getXsGwxx(GzkhKhsqForm model) {
		return dao.getGwxx(model);
	}

	/**
	 * 
	 * @描述:获取学生勤工岗位
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午09:02:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxx(GzkhKhsqForm model) {
		return dao.getGwxx(model);
	}

	/**
	 * 
	 * @描述:获取学生勤工岗位所属部门
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午09:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getYrbm(GzkhKhsqForm model) {
		return dao.getYrbm(model);
	}

	/**
	 * 
	 * @描述:获取在岗学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 上午10:14:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGzkhStuList(GzkhKhsqForm model, User user) throws Exception {
		return dao.getGzkhStuList(model, user);
	}

	/**
	 * 
	 * @描述:考核申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午02:26:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveKhsq(GzkhKhsqForm model, User user) throws Exception {
		QgzxCsszDAO csszDao = new QgzxCsszDAO();
//		HashMap<String, String> csszMap = csszDao.getCssz();
		GzkhJcszDao jcszDao = new GzkhJcszDao();
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = dao.getShlcID();
		model.setGs(gsFormat(model.getGs()));
//		model.setCjbz(csszMap.get("cjbz"));
		model.setQxfw(jcszDao.getModel().getQxfw());
		model.setSqid(sqid);
		model.setSplc(splc);
		model.setCzyh(user.getUserName());
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(model);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(), "qgzx_mrgzkh_khsh.do", "qgzx_mrgzkh_khsq.do");
			}
		}

		if(!"stu".equals(user.getUserType())){//老师维护学生工作时长，直接进去勤工办审核流
			GzkhKhshService gzkhKhshService = new GzkhKhshService();
			GzkhKhshForm form = new GzkhKhshForm();
			BeanUtils.copyProperties(form,model);
			form.setShjg("1");
			form.setShyj("同意");
			String gwid = dao.getGwid(splc);//获取第一级的审批岗位
			form.setGwid(gwid);
			form.setThgw("-1");
			result = gzkhKhshService.saveSh(form,user);
		}
		return result;
	}

	/**
	 * 
	 * @描述:考核申请修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午02:26:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveEditKhsq(HttpServletRequest request, GzkhKhsqForm model) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(model.getType()) || "tj".equalsIgnoreCase(model.getType())) {
			if ("tj".equalsIgnoreCase(model.getType())) {
				String values = request.getParameter("values");
				model.setSqid(values);
			} else {
				model.setGs(gsFormat(model.getGs()));
			}
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "qgzx_mrgzkh_khsh.do", "qgzx_mrgzkh_khsq.do");
			}
		} else {
			model.setGs(gsFormat(model.getGs()));
			result = runUpdate(model);
		}
		return result;
	}

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-7 下午03:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSqJl(GzkhKhsqForm model, String czlx) throws Exception {
		String[] gwxx = model.getGwdm().split(",");
		model.setGwdm(gwxx[0]);
		model.setXn(gwxx[1]);
		return dao.isHaveSqJl(model, czlx);
	}

	/**
	 * 
	 * @描述:验证学生填写月份金额或工时是否超过设定上限
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-15 下午05:34:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public String checkZjeAndGs(String xh, String xn, String gs, String gzrq, String cjbz, String sqid, String gwdm) throws Exception {

		QgzxCsszDAO csszDao = new QgzxCsszDAO();
		HashMap<String, String> gwxxMap = dao.getGwxx(gwdm);
		HashMap<String, String> csszMap = csszDao.getCssz();
		if (null == cjbz) {
			cjbz = csszMap.get("cjbz");
		}
		HashMap<String, String> xsCjxx = dao.getCjffXxOfStu(xh, xn, gzrq);
		String cjgs = xsCjxx.get("gs") == null ? "0" : xsCjxx.get("gs");
		String je = xsCjxx.get("je") == null ? "0" : xsCjxx.get("je");
		HashMap<String, String> sqjlMap = dao.getsqjl(xh, xn, gzrq, sqid);
		String gwZje = dao.getKhsqOfYf(xh, xn, gwdm, gzrq, sqid);
		String ffje = dao.getCjByGwdm(xh, xn, gwdm, gzrq);
		if ("gs".equals(csszMap.get("sxzd")) && (Double.parseDouble(cjgs) + Double.parseDouble(sqjlMap.get("zgs")) + Double.parseDouble(gs) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		if (StringUtils.isNotNull(gwZje) && "yes".equals(csszMap.get("sfsdgwcjsx"))&&StringUtils.isNotNull(gwxxMap.get("gwcjsx"))
				&& (Double.parseDouble(gwZje) + Double.parseDouble(ffje) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(gwxxMap.get("gwcjsx")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GWJESXOUT, new String[] { gzrq.substring(0, 7), gwxxMap.get("gwmc"), gwxxMap.get("gwcjsx") });
		}
		if ("je".equals(csszMap.get("sxzd"))
				&& (Double.parseDouble(je) + Double.parseDouble(sqjlMap.get("zje")) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_JEOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		return "true";
	}
	public String checkGs(String xh, String xn, String gs, String gzrq, String cjbz, String sqid, String gwdm) throws Exception {

		HashMap<String, String> sqjlMap = dao.getsqjl(xh, xn, gzrq, sqid);

		if (Double.parseDouble(sqjlMap.get("zgs")) + Double.parseDouble(gs) > 40) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), "40" });
		}

		return "true";
	}

	public String gsFormat(String gs) {
		int lastStr = gs.indexOf(".");
		if (gs.length() - 1 == lastStr) {
			String[] gsArr = gs.split("\\.");
			gs = gsArr[0];
		}
		return gs;
	}

}
