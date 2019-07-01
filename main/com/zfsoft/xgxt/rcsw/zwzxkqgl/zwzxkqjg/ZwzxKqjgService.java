/**
 * @部门:学工产品事业部
 * @日期：2015-1-20 上午11:40:07 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-20 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZwzxKqjgService extends SuperServiceImpl<ZwzxKqjgForm, ZwzxKqjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ZwzxKqjgDao kqjgDao = new ZwzxKqjgDao();

	/**
	 *查询缺勤类型
	 */
	public List<HashMap<String, String>> getQqlxList() {
		return kqjgDao.getQqlxList();
	}

	public boolean isHaveKgjg(ZwzxKqjgForm model) {
		return kqjgDao.isHaveKgjg(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:考勤结果增加保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-22 上午09:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean editKqjg(ZwzxKqjgForm model,List<ZwzxKqjgForm> qqxxList) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String jgid = UniqID.getInstance().getUniqIDHash();
			model.setJgid(jgid);
			model.setSqid(jgid);
			model.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			//删除缺勤学生信息，再插入
			result = dao.delQqxs(model.getJgid());
		}
		List<String[]> qqxsxxList = new ArrayList<String[]>();
		String[] qqxsxx = null;
		for (ZwzxKqjgForm kqjgForm : qqxxList) {
			
		
			qqxsxx = new String[16];
			qqxsxx[0] = model.getJgid();
			qqxsxx[1] = kqjgForm.getXh();
			qqxsxx[2] = model.getXn();
			qqxsxx[3] = model.getXq();
			qqxsxx[4] = model.getLrsj();
			qqxsxx[5] = model.getCclxdm();
			qqxsxx[6] = kqjgForm.getQqlxdm();
			qqxsxx[7] = kqjgForm.getKkjs();
			qqxsxx[8] = model.getBjdm();
			qqxsxx[9] = model.getCcrq();
			qqxsxx[10] = model.getJlf();
			qqxsxx[11] = model.getJlr();
			qqxsxx[12] = kqjgForm.getYlzd1();
			qqxsxx[13] = "";
			qqxsxx[14] = "";
			qqxsxx[15] = "";
			qqxsxxList.add(qqxsxx);

		}
		if (result) {
			result = dao.qqxsPlbc(qqxsxxList);
		}
		return result;

	}

	/**
	 * @throws Exception
	 * 
	 * @描述:获取考勤结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public ZwzxKqjgForm getKqjg(ZwzxKqjgForm t) throws Exception {
		return dao.getKqjg(t);
	}
	/**
	 * 
	 * @描述:删除考勤结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 上午11:32:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * ZwzxKqjgForm 返回类型 
	 * @throws
	 */
	public boolean delQqxs(String[] ids) throws Exception {
		List<String[]> qqxsList = new ArrayList<String[]>();
		String[] idArr = null;
		for (int i = 0; i < ids.length; i++) {
			idArr = new String[1];
			idArr[0]=ids[i];
			qqxsList.add(idArr);
		}
		return  dao.delPlQqxs(qqxsList);
	}

	/**
	 * 
	 * @描述:获取缺勤学生
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 下午04:50:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(String id) {
		return dao.getQqxsList(id);

	}
	
	

	/**
	 * 
	 * @描述:获取班级列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 上午09:16:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListNew(ZwzxKqjgForm model, User user) throws Exception {
		return dao.getBjListNew(model, user);

	}
	/**
	 * 
	 * @描述:获取学期名称
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-30 上午11:19:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xq
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String xq) throws Exception {
		return dao.getXqmc(xq);

	}
	
	

	/**
	 * 
	 * @描述:获取学生信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 上午11:03:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(ZwzxKqjgForm model, User user,HttpServletRequest request) throws Exception {
		String xhArr = request.getParameter("xhArr");
		if("".equals(xhArr)){
			model.setXhArr(new String[]{});
		}else{
		String[] xhs = xhArr.split(",");
		model.setXhArr(xhs);
		}
		return dao.getXsxxList(model, user);

	}
	/**
	 * 
	 * @描述:查询缺勤学生列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 下午04:05:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQqxsList(ZwzxKqjgForm model, User user) throws Exception {
		return dao.getQqxsList(model, user);

	}
	/**
	 * 
	 * @描述:查询缺勤学生列表(不分页)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-28 上午10:59:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllQqxsList(ZwzxKqjgForm model, User user) throws Exception {
		return dao.getAllQqxsList(model, user);

	}
	/**
	 * 
	 * @描述:学生缺勤信息详情查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 下午05:09:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQqxsxx(ZwzxKqjgForm model) throws Exception {
		return dao.getQqxsxx(model);

	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:更新缺勤学生信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 上午11:00:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public boolean updateQqxs(String jgid,String sjzt) throws Exception{
		return dao.updateQqxs(jgid,sjzt);
		
	}
	/**
	 * 
	 * @描述:缺勤学生数据同步
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-30 下午04:22:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qqxsxxTb() throws Exception{
		DAO dao = DAO.getInstance();
		boolean flag = false;

		try {
			flag = dao.runProcuder("{call pro_rcsw_zwzxkq_kqrs_tb()}",
					new String[] {});
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
		
	}

}
