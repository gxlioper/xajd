/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:40:07 
 */
package  xsgzgl.qgzx.kycxgl.kyxmgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-11-30 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KyxmglService extends SuperServiceImpl<KyxmglForm, KyxmglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String KYCX_XMLB_KYGl = "kygl";
	private KyxmglDao dao = new KyxmglDao();
	
	public void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new ArrayList<HashMap<String, String>>();
			bmList=dao.getBmList();
		request.setAttribute("bmList", bmList);
		request.setAttribute("xmsxList", dao.getXmsxList());
		
	}
	public List<HashMap<String, String>> getPageListOfSh(KyxmglForm t, User user) throws Exception{
		return dao.getPageListOfSh(t,user);
	}
	
	public List<HashMap<String, String>> getKycxList(String xh) throws Exception{
		return dao.getKycxList(xh);
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:科研项目结果增加保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-30 上午09:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean editKyxmgl(KyxmglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xmid = UniqID.getInstance().getUniqIDHash();
			model.setXmid(xmid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	public boolean isHaveKyxm(KyxmglForm model) {
		return dao.isHaveKyxm(model);
	}
	


	/**
	 * @throws Exception
	 * 
	 * @描述:科研项目结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-30 下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKyxmgl(KyxmglForm t) throws Exception {
		return dao.getKyxmgl(t);
	}

	
	/**
	 * 获取项目成员列表
	 */
	public List<HashMap<String,String>> getCyList(String xmdm) throws Exception {
		return dao.getCyList(xmdm);
	}
	/**
	 * 
	 * @描述:指导老师列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-1 下午05:59:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTeaList(String xmdm) throws Exception {
		return dao.getTeaList(xmdm);
	}
	
	public List<HashMap<String,String>> getYsxxList(String xmdm) throws Exception {
		return dao.getYsxxList(xmdm);
	}
	/**
	 * 
	 * @描述:运行状态变更记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-4 上午10:15:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBgztList(String xmdm) throws Exception {
		return dao.getBgztList(xmdm);
	}
	/**
	 * 
	 * @描述:运行状态列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-4 上午10:35:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYxztList() throws Exception {
		return dao.getYxztList();
	}
	
	public List<HashMap<String,String>> getXmfyList(String xmdm) throws Exception {
		return dao.getXmfyList(xmdm);
	}
	
	
	
	public List<HashMap<String, String>> showStudents(KyxmglForm model, User user) throws Exception {
		return dao.showStudents(model, user);

	}
	public List<HashMap<String, String>> getXsxxList(KyxmglForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)||null==xhArr){
			model.setXhs(new String[]{});
		}
		else{
			model.setXhs(xhArr.split(","));
		}
		return dao.getXsxxList(model, user);

	}
	public List<HashMap<String, String>> getTeaList(KyxmglForm model,String zghArr) throws Exception {
		if("".equals(zghArr)||null==zghArr){
			model.setZghs(new String[]{});
		}
		else{
			model.setZghs(zghArr.split(","));
		}
		return dao.getTeaList(model);

	}
	
	public boolean editXmwh(KyxmglForm model,User user,List<KyxmglXsxxForm> xsxxList, List<KyxmglJsxxForm> jsxxList,List<KyxmglJfysForm> jfysList) throws Exception {
		boolean result = true;
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID(KYCX_XMLB_KYGl);
			model.setSplcid(splc);
			result = runUpdate(model);
			
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmid(), splc, user.getUserName(), "qgzx_kycx_kygl_kyxmsh.do", "qgzx_kycx_kygl_kyxmsq.do");
			}
		}else{
			result=dao.runUpdate(model);
			}
		 //先删后插
		 dao.delJs(model.getXmid());// 删除指导老师
		 dao.delXs(model.getXmid());// 删除成员
		 dao.delYs(model.getXmid());// 删除预算
		List<String[]> xsList = new ArrayList<String[]>();
		List<String[]> jsList = new ArrayList<String[]>();
		List<String[]> jfList = new ArrayList<String[]>();
		String[] xsxx = null;
		String[] jsxx = null;
		String[] jfxx = null;
		for (KyxmglXsxxForm xsForm : xsxxList) {
			xsxx = new String[4];
			xsxx[0] = model.getXmid();
			xsxx[1] = xsForm.getXh();
			xsxx[2] = xsForm.getXmfg();
			xsxx[3] = xsForm.getSfsfs();
			xsList.add(xsxx);
		}
		for (KyxmglJsxxForm jsForm : jsxxList) {
			jsxx = new String[4];
			jsxx[0] = model.getXmid();
			jsxx[1] = jsForm.getZgh();
			jsxx[2] = jsForm.getZc();
			jsxx[3] = jsForm.getYjfx();
			jsList.add(jsxx);
		}
		for (KyxmglJfysForm jsForm : jfysList) {
			jfxx = new String[4];
			jfxx[0] = model.getXmid();
			jfxx[1] = jsForm.getZclb();
			jfxx[2] = jsForm.getYsje();
			jfxx[3] = jsForm.getZyyt();
			jfList.add(jfxx);
		}
		if (result) {
			//批量保存
			dao.xsPlbc(xsList);
			dao.jsPlbc(jsList);
			dao.ysPlbc(jfList);
		}
		return result;

	}
	public boolean editCywh(KyxmglForm model,List<KyxmglXsxxForm> xsxxList, List<KyxmglJsxxForm> jsxxList) throws Exception {
		boolean result = true;
		
		 //先删后插
		 dao.delJs(model.getXmid());// 删除指导老师
		 dao.delXs(model.getXmid());// 删除成员
		 
		List<String[]> xsList = new ArrayList<String[]>();
		List<String[]> jsList = new ArrayList<String[]>();
		
		String[] xsxx = null;
		String[] jsxx = null;
		
		for (KyxmglXsxxForm xsForm : xsxxList) {
			xsxx = new String[4];
			xsxx[0] = model.getXmid();
			xsxx[1] = xsForm.getXh();
			xsxx[2] = xsForm.getXmfg();
			xsxx[3] = xsForm.getSfsfs();
			xsList.add(xsxx);
		}
		for (KyxmglJsxxForm jsForm : jsxxList) {
			jsxx = new String[4];
			jsxx[0] = model.getXmid();
			jsxx[1] = jsForm.getZgh();
			jsxx[2] = jsForm.getZc();
			jsxx[3] = jsForm.getYjfx();
			jsList.add(jsxx);
		}
		
		if (result) {
			//批量保存
			dao.xsPlbc(xsList);
			dao.jsPlbc(jsList);
		}
		return result;

	}
	public boolean submitXmwh(KyxmglForm model,User user) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID(KYCX_XMLB_KYGl);
			model.setSplcid(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getXmid(), splc,user.getUserName() , "qgzx_kycx_kygl_kyxmsh.do", "qgzx_kycx_kygl_kyxmsq.do");
			}
			return result;
	}
	
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public boolean editFywh(KyxmglForm model, List<KyxmglXmfyForm> fywhList) throws Exception {
		boolean result = true;
		dao.runUpdate(model);
		 //先删后插
		 dao.delXmfy(model.getXmid());// 删除项目费用维护
		List<String[]> fyList = new ArrayList<String[]>();
		
		String[] fyxx = null;
		
		for (KyxmglXmfyForm fyForm : fywhList) {
			fyxx = new String[6];
			fyxx[0] = model.getXmid();
			fyxx[1] = fyForm.getFylb();
			fyxx[2] = fyForm.getFymc();
			fyxx[3] = fyForm.getFyje();
			fyxx[4] = fyForm.getBxsj();
			fyxx[5] = fyForm.getBz();
			fyList.add(fyxx);
		}
		if (result) {
			//批量保存
			dao.fywhPlbc(fyList);
		}
		return result;

	}
	/**
	 * 
	 * @描述:状态设置保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-4 上午10:56:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean ztsz(KyxmglForm model) throws Exception {
		boolean result = true;
		result=dao.runUpdate(model);
		result=dao.insertZtBg(model);
		return result;

	}
	
	public boolean saveSh(KyxmglForm form, User user) {
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		model.setZd1("立项等级");
		model.setZd2(form.getLxdj());
		model.setZd3(form.getLxdj()=="1"?"重点项目":"一般项目");
		model.setZd4("批准经费");
		model.setZd6(form.getPzjf());
		
		// 业务ID(多为申请ID)
		model.setYwid(form.getXmid());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_kycx_kygl_kyxmsh.do");
		model.setTzljsq("qgzx_kycx_kygl_kyxmsq.do");
		boolean reuslt = true;
		try {
			String zhzt = shlc.runAuditing(model);
			KyxmglForm xmForm = new KyxmglForm();
			xmForm.setXmid(form.getXmid());
				xmForm.setShzt(zhzt);
				xmForm.setLxdj(form.getLxdj());
				xmForm.setPzjf(form.getPzjf());
				reuslt = dao.runUpdate(xmForm);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:批量审核保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-8 下午03:22:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return String 返回类型
	 * @throws
	 */
	public String savePlsh(KyxmglForm t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			t.setXmid(ids[i]);
			Map<String, String> result = dao.getKyxmgl(t);
			KyxmglForm model = new KyxmglForm();
			model.setSplcid(result.get("splcid"));
			model.setLxdj(t.getLxdj());
			model.setPzjf(t.getPzjf());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setXmid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	public boolean cancel(KyxmglForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm);
		return result;
	}
	
	public String cxshnew(String ywid, KyxmglForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();

		// 判断流程序号
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(ywid, user.getUserName(), model.getShlc(), "cx");

		// 审核前一步骤立项等级和批准经费
		String lxdj = shxx.get("zd2") == null ? "" : shxx.get("zd2");
		String pzjf = shxx.get("zd6") == null ? "" : shxx.get("zd6");
		String shzt = Constants.YW_SHZ;
		model.setShzt(shzt);
		model.setXmid(ywid);
		model.setLxdj(lxdj);
		model.setPzjf(pzjf);
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		// 回滚科研项目的立项等级
		dao.runUpdate(model);
		return cancelFlag;

	}
}
