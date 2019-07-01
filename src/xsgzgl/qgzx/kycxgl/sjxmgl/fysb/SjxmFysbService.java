/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:40:07 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl.fysb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
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

public class SjxmFysbService extends SuperServiceImpl<SjxmFysbForm, SjxmFysbDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String KYCX_XMLB_SJGl = "sjgl";
	private SjxmFysbDao dao = new SjxmFysbDao();
	
	public void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new ArrayList<HashMap<String, String>>();
			bmList=dao.getBmList();
		request.setAttribute("bmList", bmList);
		request.setAttribute("xmsxList", dao.getXmsxList());
		request.setAttribute("gwlbList", dao.getGwlbList());
		
	}
	public List<HashMap<String, String>> getPageListOfFyff(SjxmFysbForm t, User user) throws Exception{
		return dao.getPageListOfFyff(t,user);
	}
	
	public List<HashMap<String, String>> ffcx(SjxmFysbForm model,User user) throws Exception {
		return dao.ffcx(model,user);
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:实践项目结果增加保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-30 上午09:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean editFysb(SjxmFysbForm model,List<SjxmglXsxxForm> sbxxList) throws Exception {
		boolean result = true;
		String sbid=UniqID.getInstance().getUniqIDHash();
		model.setSbid(sbid);
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_TG);// 审核中
		}else{
			model.setShzt(Constants.YW_WTJ);
		}
		dao.runInsert(model);
		 //删除费用发放信息
		 dao.delFyff(model);// 上报信息
		List<String[]> ffList = new ArrayList<String[]>();
		String[] ffxx = null;
		for (SjxmglXsxxForm sbForm : sbxxList) {
			ffxx = new String[6];
			ffxx[0] = model.getSbid();
			ffxx[1] = model.getXmid();
			ffxx[2] = sbForm.getXh();
			ffxx[3] = model.getSbyf();
			ffxx[4] = sbForm.getGs();
			ffxx[5] = sbForm.getCjje();
			ffList.add(ffxx);
		}
		if (result) {
			//批量保存
			dao.ffPlbc(ffList);
		}
		return result;
	}
	
	public boolean saveEditFysb(SjxmFysbForm model,List<SjxmglXsxxForm> sbxxList) throws Exception {
		boolean result = true;
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_TG);// 审核中
		}
		dao.runUpdate(model);
		 //删除费用发放信息
		 dao.delFyff(model);// 上报信息
		List<String[]> ffList = new ArrayList<String[]>();
		String[] ffxx = null;
		for (SjxmglXsxxForm sbForm : sbxxList) {
			ffxx = new String[6];
			ffxx[0] = model.getSbid();
			ffxx[1] = model.getXmid();
			ffxx[2] = sbForm.getXh();
			ffxx[3] = model.getSbyf();
			ffxx[4] = sbForm.getGs();
			ffxx[5] = sbForm.getCjje();
			ffList.add(ffxx);
		}
		if (result) {
			//批量保存
			dao.ffPlbc(ffList);
		}
		return result;
	}



	
	/**
	 * 获取项目成员列表
	 */
	public List<HashMap<String,String>> getCyList(String xmdm,String ffny) throws Exception {
		return dao.getCyList(xmdm,ffny);
	}
	public boolean submitFysb(SjxmFysbForm model,User user) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_TG);// 审核中
			result = runUpdate(model);
			return result;
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
	
	public List<HashMap<String,String>> getSbxmList(User user) throws Exception {
		return dao.getSbxmList(user);
	}
	
	public List<HashMap<String, String>> getFfyfList(String xmid) throws Exception {
		return dao.getFfyfList(xmid);
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
	/**
	 * 
	 * @描述:删除项目费用申报
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-12 上午11:36:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delFysb(SjxmFysbForm myForm, String values) throws Exception {
		 dao.runDelete(values.split(","));
		return dao.delFyff(values.split(","));
	}

	
	public boolean cancel(SjxmFysbForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_WTJ);
		boolean result = dao.runUpdate(myForm);
		return result;
	}
	
	
	public List<HashMap<String,String>> getGwlbList()throws Exception{
	return dao.getGwlbList();
	}
}
