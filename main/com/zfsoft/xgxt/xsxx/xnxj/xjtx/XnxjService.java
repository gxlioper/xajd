/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 下午01:50:46 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjtx;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xnxj.jcsz.JcszForm;
import com.zfsoft.xgxt.xsxx.xnxj.jcsz.JcszService;
import com.zfsoft.xgxt.xsxx.xnxj.xjjg.XnxjJgDao;
import com.zfsoft.xgxt.xsxx.xnxj.xjjg.XnxjJgForm;
import com.zfsoft.xgxt.xsxx.xnxj.xjjg.XnxjJgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学年小结模块
 * @类功能描述: 学年小结service 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-19 下午01:50:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnxjService extends SuperServiceImpl<XnxjForm, XnxjDao> {

	private XnxjDao dao = new XnxjDao();
	
	private XnxjJgDao xnxjJgDao = new XnxjJgDao();
	
	private ShlcInterface shlc = new CommShlcImpl(); //审核流操作接口

	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public XnxjService() {
		super.setDao(dao);	
	}
	
	/**
	 * 
	 * @描述:根据学年 学号获取学年小结
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-20 上午09:32:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * XnxjForm 返回类型 
	 * @throws
	 */
	public XnxjForm getModel(XnxjForm t) throws Exception{
		return dao.getModel(t, new String[]{t.getXh() , t.getXn()});
	}
	
	/**
	 * 
	 * @描述:更新学年小结
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-31 下午05:28:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXnxj(XnxjForm model) throws Exception{
		if(model == null) return false;
		
		if(model.getId()== null) return false;
		
		String xnxj = model.getXjnr();
		
		XnxjForm t = dao.getModel(model.getId());
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		if(t == null){ return false; }
		
		t.setXjnr(xnxj);
		
		return dao.runUpdate(t);
		
	}
	
	/**
	 * 
	 * @描述:保存学年小结
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-20 上午09:34:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXnxj(XnxjForm model) throws Exception{
		
		XnxjForm t = dao.getModel(model , new String[]{model.getXh() , model.getXn()}); //根据学号 学年查询是否存在申请数据
		
		JcszService jcszService = new JcszService();
		
		JcszForm jcszModel = jcszService.getModel();
		
		boolean isSuccess = false;
		
		if(t == null){
			if(jcszModel != null && StringUtils.isNotNull(jcszModel.getSpl())){ 
				model.setSplid(jcszModel.getSpl()); 							//设置审批流到申请表
			}
			model.setShjg(Constants.YW_WTJ);   									//设置未提交状态
			model.setTxsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss")); 	//申请时间设置
			isSuccess = dao.runInsert(model); 									//插入申请表
			if(isSuccess){
				if(StringUtils.isEqual(model.getType(), "submit")){  			//判断是否是提交操作
					String spl = jcszModel.getSpl();
																				//获取插入后的学年小结数据
					XnxjForm xnxjModel = dao.getModel(new XnxjForm(), new String[]{model.getXh() , model.getXn()}); 
					if(StringUtils.isEqual("wxsh", spl)){						//如果审核流设置的无需审核并且是提交动作，则直接插入结果表
						XnxjJgService xnxjjgService = new XnxjJgService();
						XnxjJgForm xnxjJgModel = new XnxjJgForm();
						xnxjJgModel.setSjly(Constants.SJLY_SPL);
						xnxjJgModel.setSqid(model.getId());
						xnxjJgModel.setTxsj(model.getTxsj());
						xnxjJgModel.setXh(model.getXh());
						xnxjJgModel.setXjnr(model.getXjnr());
						xnxjJgModel.setXn(model.getXn());
						isSuccess = xnxjjgService.runInsert(xnxjJgModel);
						if(isSuccess){
							xnxjModel.setShjg(Constants.YW_TG);
							dao.runUpdate(xnxjModel); 						//保存结果成功后，修改申请表的状态为通过
						}
					}else{													//插入审核表
						isSuccess = shlc.runSubmit(xnxjModel.getId(), jcszModel.getSpl(), model.getXh(), "xsxx_xnxj_xjsh.do", "xsxx_xnxj_xjtx.do");
						if(isSuccess){
							xnxjModel.setShjg(Constants.YW_SHZ);
							dao.runUpdate(xnxjModel); 						//修改为审核中状态
						}
					}
				}
			}
			return isSuccess;
		}else{
			
			if(StringUtils.isEqual("wxsh", t.getSplid())){						//如果审核流设置的无需审核并且是提交动作，则直接插入结果表
				XnxjJgService xnxjjgService = new XnxjJgService();
				XnxjJgForm xnxjJgModel = new XnxjJgForm();
				xnxjJgModel.setSjly(Constants.SJLY_SPL);
				xnxjJgModel.setSqid(model.getId());
				xnxjJgModel.setTxsj(model.getTxsj());
				xnxjJgModel.setXh(model.getXh());
				xnxjJgModel.setXjnr(model.getXjnr());
				xnxjJgModel.setXn(model.getXn());
				isSuccess = xnxjjgService.runInsert(xnxjJgModel);
				if(isSuccess){
					t.setShjg(Constants.YW_TG);
					isSuccess = dao.runUpdate(t); 								//保存结果成功后，修改申请表的状态为通过
				}
			}else{																//插入审核表
				isSuccess = shlc.runSubmit(t.getId(), t.getSplid(), t.getXh(), "xsxx_xnxj_xjsh.do", "xsxx_xnxj_xjtx.do");
				if(isSuccess){
					t.setShjg(Constants.YW_SHZ);
					isSuccess = dao.runUpdate(t); 								//修改为审核中状态
				}
			}
			return isSuccess;
		}
		
	}
	
	/**
	 * 
	 * @描述:删除学年小结
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-20 下午01:41:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqidList
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delXnxjsq(String[] sqidList) throws Exception{
		if(sqidList == null){
			return 0;
		}
		
		return dao.runDelete(sqidList);
		
	}
	
	/**
	 * 
	 * @描述:批量提交学年小结申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-20 下午02:33:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int submitXnxjs(String[] sqids) throws Exception{
		
		if(sqids == null || sqids.length == 0) return 0;
		
		int i=0;
		for (String string : sqids) {
			
			XnxjForm model = dao.getModel(string);
			
			String ywid = model.getId();
			String sqr = model.getXh();
			if(!Constants.YW_YTH.equals(model.getShjg())){ 
				
				JcszService jcszService = new JcszService();
				JcszForm jcszModel = jcszService.getModel();
				
				model.setSplid(jcszModel.getSpl());
			}
			String splid = model.getSplid();
			boolean isSuccess = shlc.runSubmit(ywid, splid, sqr, "xsxx_xnxj_xjsh.do", "xsxx_xnxj_xjtx.do");
			
			if(isSuccess){
				model.setShjg(Constants.YW_SHZ);
				isSuccess = dao.runUpdate(model);
			}
			i++;
		}
		
		return sqids.length;
		
	}
	
	/**
	 * 
	 * @描述:批量撤销
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-21 下午02:25:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] cancelXnxjs(String[] sqids) throws Exception{
		
		int success = 0;
		
		int fail = 0;
		
		if(sqids == null || sqids.length == 0) return new int[]{success , fail};

		for (String string : sqids) {
			
			XnxjForm model = dao.getModel(string);
			
			String ywid = model.getId();
			String sqr = model.getXh();
			String splid = model.getSplid();
			
			boolean isSuccess;
			
			try {
				isSuccess = shlc.firstStepCancle(ywid, splid);
				
				if(isSuccess){
//					审批流当中如存在退回记录，则状态为退回
					if(Integer.valueOf(dao.getShzt(ywid))>0){
						model.setShjg(Constants.YW_YTH);
					}else{
						model.setShjg(Constants.YW_WTJ);
					}
					isSuccess = dao.runUpdate(model);
					if(isSuccess){
						success++;
					}else
						fail ++;
				}else
					fail ++;
				
			} catch (SystemException e) {
				fail ++;
			}
		}
		
		return new int[]{success , fail};
		
	}
	
	/**
	 * 
	 * @描述:小结审核查询
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-21 下午02:25:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String ,String>> getXnxjShPageList(XnxjForm t , User user) throws Exception{
		return dao.getXnxjShPageList(t, user);
	}
	
	/**
	 * 
	 * @描述:保存学年小结审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-23 上午11:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXnxjSh(XnxjForm  t, User user) throws Exception{
		
		String ywid = t.getId(); //业务ID
		String gwid = t.getXtgwid();//岗位id
		String shjg = t.getShjg();//审核结果
		String shyj = t.getShyj();//审核意见
		String splid = t.getSplid();//审批流id
		String sqr = t.getXh();//申请人
		String shr = user.getUserName();//审核人
		String thgw = t.getThgw(); //退回岗位id
		String xjnr = t.getXjnr(); //小结内容
		String txsj = t.getTxsj(); //填写时间
		String xh = t.getXh();
		String xn = t.getXn();
		
		
		ShxxModel model = new ShxxModel();
		model.setYwid(ywid);
		model.setShlc(splid);
		model.setShr(shr);
		model.setShyj(shyj);
		model.setShzt(shjg);
		model.setThgw(thgw);
		model.setGwid(gwid);
		model.setSqrid(sqr);
		model.setTzlj("xsxx_xnxj_xjsh.do");
		model.setTzljsq("xsxx_xnxj_xjtx.do");
		
		boolean isSuccess = false;
		
		try {
			String zhzt  = shlc.runAuditing(model);
			
			XnxjForm updateForm = dao.getModel(t.getId());
			
			updateForm.setShjg(zhzt);
			
			isSuccess = dao.runUpdate(updateForm); //更新申请表
			
			if(isSuccess && Constants.SH_TG.equals(zhzt)){
				
				XnxjJgForm xnxjJgModel = new XnxjJgForm();
				xnxjJgModel.setSjly(Constants.SJLY_SPL);
				xnxjJgModel.setSqid(ywid);
				xnxjJgModel.setXjnr(xjnr);
				xnxjJgModel.setTxsj(txsj);
				xnxjJgModel.setXh(xh);
				xnxjJgModel.setXn(xn);
				isSuccess = xnxjJgDao.runInsert(xnxjJgModel); //插入结果表
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		return isSuccess;
	}
	
	/**
	 * 
	 * @描述:撤销学年小结审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-23 下午04:11:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelXnxjSh(String ywid) throws Exception{
		if(StringUtils.isNull(ywid)) return false;
		
		XnxjForm model = dao.getModel(ywid);
		
		boolean isSuccess = false;
		
		if(model != null){
			model.setShjg(Constants.YW_SHZ);
			isSuccess = dao.runUpdate(model);
		}
		int rownum = 0;
		if(isSuccess){
			rownum = xnxjJgDao.delXnxjg(ywid);
		}
		
		return true;
	}
	
	public HashMap<String , String> getXnxjInfo(String xh , String xn){
		return dao.getXnxjInfo(xh , xn);
	}
	/**
	 * 查询审核意见，根据审核时间升序
	 */
	public List<HashMap<String , String>> getXnxjShyjList(String id){
		return dao.getXnxjShyjList(id);
	}
	
}
