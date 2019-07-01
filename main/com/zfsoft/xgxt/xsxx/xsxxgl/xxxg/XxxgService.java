/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.jcsz.XsxxJcszService;
import xsgzgl.xsxx.general.qzxgmdsz.QzxgmdszService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学生信息修改
 * @作者： ligl
 * @时间： 2013-11-23 上午11:12:32
 * @版本： V1.0
 * @修改记录:
 */
public class XxxgService extends SuperServiceImpl<XsxxglModel, XxxgDao> {

	private XxxgDao dao = new XxxgDao();
	private ShlcInterface shlc = new CommShlcImpl();

	public XxxgService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:获取学生信息审核列表
	 * @作者：ligl
	 * @日期：2013-11-23 上午11:46:35
	 * @修改记录:
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(XsxxglModel model,
			User user) throws Exception {
		return dao.getPageList(model, user);
	}

	/**
	 * @throws Exception 
	 * @throws Exception 
	 * 
	 * @描述:提交修改申请信息
	 * @作者：ligl
	 * @日期：2013-12-6 上午10:34:13
	 * @修改记录:
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean saveXgsq(XgsqModel model) throws Exception{
		boolean result = true;
//		try {
		String shzSqid = getShzDataByXh(model.getXh());
		if(!StringUtil.isNull(shzSqid)){
			model.setShjg(Constants.YW_SHZ);
			model.setSqid(shzSqid);
		}
		//当审核状态不为【退回】时，产生新的sqid
		if(!(Constants.SH_TH.equals(model.getShjg())
				|| Constants.SHZ.equals(model.getShjg()))){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			model.setSqid(guid);
		}
		//删除未提交的流程记录
		dao.deleteShlc(model);
		//删除未提交的申请记录
		dao.deleteXgsq(model);

		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));

		String shlid = null;
		HashMap<String, String> splMap = new XsxxJcszService().getOnesCssz();
		shlid = splMap.get("shlid");
		
		String xgzd = model.getXgzd();
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		// /////删除修改字段////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			if(Constants.SH_TH.equals(model.getShjg())
					|| Constants.SHZ.equals(model.getShjg())){
				dao.delXgzdb(model.getSqid());
			}
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		if (shlid == null || shlid.equals("")  || shlid.equals("wxsh")  ) {// 无需审核
			model.setShjg(Constants.YW_TG);
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> xsxgzdList = null;
			if(xgzd != null && !xgzd.equals("")){
				xsxgzdList = JsonUtil.jsonToList(xgzd, HashMap.class);
			}
			// 修改学生信息
			result = xsxxglService
					.updateRecordForStu(model.getSqid(),model.getXh(), false, false,xsxgzdList);
		} else {
			if (!Constants.SHZ.equals(model.getShjg())) {
				result = shlc.runSubmit(model.getSqid(), shlid, model.getXh(),
						"xsxx_xsxxxg_xgsh.do", "xsxx_xsxxxg_xgsq.do");
			}
		}

		//当审核状态不为【退回】时，才进行插入
		if(!(Constants.SH_TH.equals(model.getShjg())
				|| Constants.SHZ.equals(model.getShjg()))){
			if (shlid == null || shlid.equals("")  || shlid.equals("wxsh")  ) {// 无需审核
				model.setShjg(Constants.YW_TG);
			} else {
				model.setShjg(Constants.YW_SHZ);
			}
//			String shzSqid =getShzDataByXh(model.getXh());
			dao.insertXgsq(model);	//插入申请记录
		} else {
			model.setShjg(Constants.YW_SHZ);
			dao.updateXgsqZt(model);  //更新申请表申请状态
		}
		
		new QzxgmdszService().xgQzxgzt(model.getXh());// 修改强制修改名单
		return result;
//		} catch (Exception e) {
//			logger.debug(e.getMessage());
//			return false;
//		}
	}
	
	
	//保存修改申请信息
	@SuppressWarnings("unchecked")
	public boolean saveXgsqDemo(XgsqModel model) throws Exception {
		boolean result = true;
		//当审核状态不为【退回】时，才进行删除原先审核流程和申请记录，并产生新的sqid,重置审核状态等操作
		if(!Constants.SH_TH.equals(model.getShjg())){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			model.setSqid(guid);
			model.setShjg(Constants.YW_WTJ);
		}
		dao.deleteShlc(model);
		dao.deleteXgsq(model);
		
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		String xgzd = model.getXgzd();
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		// /////删除修改字段////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			if(Constants.SH_TH.equals(model.getShjg())){
				dao.delXgzdb(model.getSqid());
			}
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		//当审核状态不为【退回】时，才进行插入
		if(!Constants.SH_TH.equals(model.getShjg())){
			dao.insertXgsq(model);//插入申请记录
		}
		return result;
	}
	/**
	 * 
	 * @描述:审核记录
	 * @作者：ligl
	 * @日期：2013-12-11 下午01:31:37
	 * @修改记录:
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getWclPageList(XsxxglModel model,
			User user) throws Exception {
		return dao.getWclPageList(model, user);
	}

	/**
	 * 
	 * @描述:修改申请状态
	 * @作者：ligl
	 * @日期：2013-12-9 上午09:42:04
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateXgsqZt(XgsqModel model) throws Exception {
		return dao.updateXgsqZt(model);
	}

	/**
	 * 
	 * @描述:审核退回
	 * @作者：ligl
	 * @日期：2013-12-9 上午09:42:04
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean thXgsqZt(String sqid) throws Exception {
		XgsqModel model = new XgsqModel();
		model.setSqid(sqid);
		XsxxglService service = new XsxxglService();
		boolean result = service.updateRecordForStu(sqid, true);
		if (result) {
			model.setShjg(Constants.YW_SHZ);
			result = dao.updateXgsqZt(model);
		}
		return result;
	}
	
	public String updateSqzt(String sqid,String shid,String splc,User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		XgsqModel model = new XgsqModel();
		model.setSqid(sqid);
		//判断流程序号
		HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(sqid, user.getUserName(), splc, "cx");
		String cancelFlag= service.runCancelNew(user.getUserName(), shid, splc);
		if ("1".equals(shxx.get("xh"))) {
			model.setShjg(Constants.YW_WTJ);
		}
		else{
			model.setShjg(Constants.YW_SHZ);
		}
		dao.updateXgsqZt(model);
		return cancelFlag;
	}

	/**
	 * 
	 * @描述:通过申请id，得到修改字段及修改后值
	 * @作者：ligl
	 * @日期：2013-12-9 下午01:45:09
	 * @修改记录:
	 * @param sqid
	 * @return
	 * @throws
	 */
	public List<HashMap<String, String>> getXgzdList(String sqid)
			throws Exception {
		return dao.getXgzdList(sqid);
	}

	public List<HashMap<String, String>> getXgjgPageList(XsxxglModel model,
			User user) throws Exception {
		return dao.getXgjgPageList(model, user);
	}

	/**
	 * 
	 * @描述:根据学号，获取最新的一条审核中的记录
	 * @作者：ligl
	 * @日期：2013-12-16 下午03:57:35
	 * @修改记录:
	 * @param xh
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public String getShzDataByXh(String xh) throws Exception {
		HashMap<String, String> map = dao.getDataByXh(xh, Constants.YW_SHZ);
		String sqid = "";
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}
	/**
	 * 
	 * @描述:根据学号获取最新的审核信息
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-17 下午01:44:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getShxxByXh(String xh) throws Exception {
		return dao.getShxxByXh(xh);
	}
	public HashMap<String, String> getShztByXh(String xh) throws Exception {
		return dao.getShztByXh(xh);
	}
	/**
	 * 
	 * @描述:根据学号，获取最新的一条待审核的记录
	 * @作者：ligl
	 * @日期：2013-12-16 下午03:57:35
	 * @修改记录:
	 * @param xh
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public String getDshDataByXh(String xh) throws Exception {
		HashMap<String, String> map = dao.getDshByXh(xh);
		String sqid = "";
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}
	
	
	/**
	 * 
	 * @描述:通过申请id 获取学号
	 * @作者：ligl
	 * @日期：2013-12-24 上午09:59:20
	 * @修改记录: 
	 * @param sqid
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXhBySqid(String sqid) throws Exception {
		return dao.getXhBySqid(sqid);
	}
	
	//审核导出不分页调用方法
	public List<HashMap<String,String>> getAllListSh(XsxxglModel t, User user) throws Exception {
			
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getWclPageList(t, user);
	}
	
	/**
	 * @描述：结果导出不分页
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getAllListJg(XsxxglModel t, User user) throws Exception {
			
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getXgjgPageList(t, user);
	}
	
	//当审核状态为退回时，保存草稿或者提交时需要删除xgzdb中的数据
	public boolean delXgzdb(String sqid) throws Exception{
		return dao.delXgzdb(sqid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:北京中医药个人获奖附件上传查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-26 下午02:55:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrfjscCx(XsxxglModel model,
			User user) throws Exception{
		return dao.getGrfjscCx(model, user);
	}
	
	/**
	 * 
	 * @描述: 获取学生获奖信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-26 下午04:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXshjXx(String hjid){
		return dao.getXshjXx(hjid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述：北京中医药上传附件更新组gid
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-26 下午05:58:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hjid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateGrhjxxGid(String hjid,String gid) throws Exception{
		return dao.updateGrhjxxGid(hjid, gid);
	}
}
