/**
 * @部门:学工产品事业部
 * @日期：2014-8-19 下午02:23:39 
 */  
package com.zfsoft.xgxt.jjgl.jjxq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jjgl.cssz.CsszService;
import com.zfsoft.xgxt.jjgl.xqwh.XqwhForm;
import com.zfsoft.xgxt.jjgl.xqwh.XqwhService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import java.util.*;

/** 
 * @类功能描述: 家教需求
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-19 下午02:23:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjxqService extends SuperServiceImpl<JjxqForm, JjxqDao> {

	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @描述:获取modelMap
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午10:22:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String id) throws Exception{
		return dao.getModelMap(id);
	}
	
	/**
	 * 
	 * @描述: 家教需求列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-27 上午10:43:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJjxqList(JjxqForm t,User user) throws Exception{
		return dao.getJjxqList(t,user);
	}
	
	
	/**
	 * 
	 * @描述: 保存申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-2 下午04:31:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXssq(JjxqForm t, User user) throws Exception{

		String sqid = UniqID.getInstance().getUniqIDHash();
		t.setSqid(sqid);
		//获取基础设置中设置的审核流程
		Map<String,String> configMap = new CsszService().getConfigMap();
		String splc = configMap.get("jjtdsplc");
		t.setSplc(splc);
		t.setShzt(Constants.YW_SHZ);// 审核中

		// 保存申请信息
		boolean result = dao.saveXssq(t, user);
		// 保存审核信息
		if (result) {
			result = shlc.runSubmit(sqid, splc, user.getUserName(),
					"jjgl_jjczsh.do", "xsjj_jjsc.do");
		}

		return result;
	}
	
	
	/**
	 * 
	 * @描述: 结束家教
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-2 下午04:31:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJsjj(JjxqForm t,User user) throws Exception{

	    boolean result = false;
	    String splc = null;
	    //根据pjjsqid jjcz shzt=3查一下是否有退回记录 有的话直接提交
        //没有则新建一条
        Map<String,String> tjjSqxxMap = dao.getTjjSqxx(t);

        if(tjjSqxxMap != null&&tjjSqxxMap.size()!=0){
            t.setSqid(tjjSqxxMap.get("sqid"));
            t.setShzt(Constants.YW_SHZ);// 审核中
            //修改申请状态
            result = dao.updateTjjShztBySqid(t);
        }else{
            String sqid = UniqID.getInstance().getUniqIDHash();
            t.setSqid(sqid);
            //获取基础设置中设置的审核流程
            Map<String,String> configMap = new CsszService().getConfigMap();
            splc = configMap.get("jjtdsplc");
            t.setSplc(splc);
            t.setShzt(Constants.YW_SHZ);// 审核中

            // 保存申请信息
            result = dao.saveJsjj(t, user);
        }


		// 保存审核信息
		if (result) {
			result = shlc.runSubmit(t.getSqid(), splc, user.getUserName(),
					"jjgl_jjczsh.do", "xsjj_wdjj.do");
		}

		return result;
	}
	
	/**
	 * 
	 * @描述: 查询学生申请信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-2 下午04:31:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXssqInfo(String sqid){
		return dao.getXssqInfo(sqid);
	}

	/**
	 * 获取家教操作审核列表数据
	 * @param jjxqForm
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> getJjczshList(JjxqForm jjxqForm, User user) throws Exception {

		return dao.getJjczshList(jjxqForm,user);
	}

	/**
	 * 家教操作审核的保存
	 * @param jjxqForm
	 * @param user
	 * @return
	 */
	public boolean jjczshSave(JjxqForm jjxqForm, User user) {

		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(jjxqForm.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(jjxqForm.getShyj());
		// 审核状态
		model.setShzt(jjxqForm.getShjg());
		//岗位退回
		model.setThgw(jjxqForm.getThgw());
		// 审核岗位ID
		model.setGwid(jjxqForm.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(jjxqForm.getSqid());
		//申请人id
		model.setSqrid(jjxqForm.getXh());

		model.setTzlj("jjgl_jjczsh.do");
		if("1".equals(jjxqForm.getJjcz())){
            model.setTzljsq( "xsjj_jjsc.do");
        }else {
			model.setTzljsq("xsjj_wdjj.do");
        }

		boolean result = false;
		try {
			String shzt = shlc.runAuditing(model);
			jjxqForm.setShzt(shzt);

			// 最后一步审核通过后修改家教状态（根据家教操作判断） 家长家教需求表
			// 还要更新派出时间 学生申请表
			if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
				//更新审核状态和派出时间
                //派家教
                if("1".equals(jjxqForm.getJjcz())){
                    jjxqForm.setPcsj(DateUtils.getCurrTime());
                    result = dao.updateShztBySqid(jjxqForm);
                //退家教
                }else if("0".equals(jjxqForm.getJjcz())){
					result = dao.updateTjjShztBySqid(jjxqForm);
                //关闭家教
                }else if("4".equals(jjxqForm.getJjcz())){
                    jjxqForm.setGbsj(DateUtils.getCurrTime());
                    //　修改派家教申请表的关闭时间　又要修改家教操作（退关）申请表的审核状态
					result = dao.updateGbsjBySqid(jjxqForm);
					if(result){
						result = dao.updateTjjShztBySqid(jjxqForm);
					}
                }

				if(result){
					// 派家教 退家教 关闭家教
					// 还要保存之前的家教状态（用于最后一级撤销）
					XqwhForm xqwhForm = new XqwhForm();
					xqwhForm.setXqid(jjxqForm.getXqid());
					xqwhForm.setJjzt(jjxqForm.getJjcz());
					result = new XqwhService().runUpdateJjzt(xqwhForm);
				}
			}else {
				//更新审核状态
				result = dao.updateShztBySqid(jjxqForm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获得派家教申请信息map
	 * @param sqid
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getJjxqMap(String sqid) throws Exception {
		return dao.getJjxqMap(sqid);
	}

	/**
	 * 获得退家教、关闭家教申请信息map
	 * @param sqid
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getTjjxqMap(String sqid) {
		return dao.getTjjxqMap(sqid);
	}

	/**
	 * 根据pjjsqid和jjcz判断当前家教操作是否已经存在数据
	 * @param jjxqForm
	 * @return
	 */
	public boolean isJjczExist(JjxqForm jjxqForm) {

		String count = dao.isJjczExist(jjxqForm);
		return Integer.parseInt(count) > 0;
	}

	/**
	 * 家教操作审核最后一级撤销
	 * @param jjxqForm
	 * @return
	 */
	public boolean cancelShLast(JjxqForm jjxqForm) throws Exception {

		jjxqForm.setShzt(Constants.YW_SHZ);
		boolean result = false;
		//派家教
		if("1".equals(jjxqForm.getJjcz())){
			result = dao.updateShztBySqid(jjxqForm);
		//退家教
		}else if("0".equals(jjxqForm.getJjcz())){
			result = dao.updateTjjShztBySqid(jjxqForm);
		//关闭家教
		}else if("4".equals(jjxqForm.getJjcz())){
			result = dao.updateTjjShztBySqid(jjxqForm);
		}
		//还原到之前的家教状态
		if (result) {
			result = new XqwhService().updateJjztForCancel(jjxqForm.getXqid());
		}
		return result;
	}

	public HashMap<String,String> getPjxxMap(String xqid) {

		return dao.getPjxxMap(xqid);
	}

	public Boolean jjpjSave(String pjid,String xqid, String pjzs, String py) throws Exception {

		if(StringUtils.isNull(pjid)){
			return dao.jjpjInsert(xqid,pjzs,py);
		}else {
			return dao.jjpjUpdate(pjid,xqid,pjzs,py);
		}
	}

	public List<HashMap<String,String>> getJjgsxxList(String xqid) throws Exception {

		return dao.getJjgsxxList(xqid);
	}

	public boolean jjgsSave(String jjbh, String jjny, String jjgs) throws Exception {

		return dao.jjgsSave(jjbh,jjny,jjgs);
	}

	public boolean isJjgsExist(String jjbh, String jjny) {

		String count = dao.getJjgsjlCount(jjbh,jjny);
		return Integer.parseInt(count) > 0;
	}
}
