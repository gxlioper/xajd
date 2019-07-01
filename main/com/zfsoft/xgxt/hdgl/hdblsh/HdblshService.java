/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsh;

import java.util.HashMap;

import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgDao;
import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgForm;
import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgService;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshDao;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshForm;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;

/**
 * @className	： HdblshService
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-18 下午04:48:19
 * @version 	V1.0 
 */

public class HdblshService extends SuperServiceImpl<HdblsqshForm, HdblshDao> {
	//审核流程
	private ShlcInterface shlc = new CommShlcImpl();
	
	public boolean saveSh(HdblsqshForm form, User user) {
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplc());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSqid());
		model.setSqrid(user.getUserName());
		model.setTzlj("hdgl_hdbl_hdblsh.do");
		model.setTzljsq("hdgl_hdbl_hdblsq.do");
		model.setZd1("活动类型");
		model.setZd4("活动标签");
		model.setZd7("获得学分");
		String hdlx = form.getHdlx();
		model.setZd2(hdlx);
		String hdlxmc = new HdblsqshDao().getHdmc(hdlx);
		model.setZd3(hdlxmc);
		if(null != form.getHdbqs() && form.getHdbqs().length > 0){			
			String[] hdbqArr = form.getHdbqs();
			StringBuilder hdbq = new StringBuilder();
			for(int i = 0; i < hdbqArr.length; i++){
				hdbq.append(hdbqArr[i]);
				if(i != hdbqArr.length - 1){
					hdbq.append(",");
				}
			}
			model.setZd6(hdbq.toString());
		}
		model.setZd9(form.getHdxf());
		
		/*能力标签*/
		if(null != form.getNlbqs() && form.getNlbqs().length > 0){
			String[] nlbqArr = form.getHdbqs();
			StringBuilder nlbq = new StringBuilder();
			for(int i = 0; i < nlbqArr.length; i++){
				nlbq.append(nlbqArr[i]);
				if(i != nlbqArr.length - 1){
					nlbq.append(",");
				}
			}
			model.setZd10(nlbq.toString());
		}
		
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			HdblsqshForm shForm = new HdblsqshForm();
			BeanUtils.copyProperties(shForm,form);
			shForm.setSqid(form.getSqid());
			shForm.setShzt(zhzt);
			result = dao.runUpdate(shForm);
			String[] hdbqs = shForm.getHdbqs();/*获取活动标签*/
			String[] nlbqs = shForm.getNlbqs();/*获取能力标签*/
			HdbljgDao jgDao = new HdbljgDao();
			if(result){
				jgDao.deleteHdbq(new String[]{form.getSqid()});
				jgDao.deleteNlbq(new String[]{form.getSqid()});
				/*插入活动标签*/
				if(null != hdbqs && hdbqs.length > 0){
					result = jgDao.BatchInsertHdbqx(form.getSqid(), hdbqs);
				}
				/*插入能力标签*/
				if(null != nlbqs && nlbqs.length > 0){
					result = jgDao.BatchInsertNlbqx(form.getSqid(), nlbqs);
				}
			}
			// 保存到结果表
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				HdbljgForm hdbljgForm = new HdbljgForm();
				HdbljgService HdbljgService = new HdbljgService();
				HdbljgService Transervice = TransactionControlProxy.newProxy(HdbljgService);
				HdblsqshService hdblsqshService = new HdblsqshService();
				HdblsqshForm insert_model = hdblsqshService.getModel(form.getSqid());

				BeanUtils.copyProperties(hdbljgForm, StringUtils.formatData(insert_model));
				hdbljgForm.setSjly("1");
//				if(zzdjgService.isHaveRecordForjg(zzdjgForm)){
//					//如果结果表中存在数据，先删除再插入
//					new ZzdjgDao().deleteForSq(zzdjgForm);
//					zzdjgService.runInsert(zzdjgForm);
//				}else{
//					zzdjgService.runInsert(zzdjgForm);
//				}
				String jgid = UniqID.getInstance().getUniqIDHash();
				hdbljgForm.setJgid(jgid);
				result = Transervice.runInsert(hdbljgForm);
				if(result){
					/*插入活动标签*/
					if(null != hdbqs && hdbqs.length > 0){
						result = jgDao.BatchInsertHdbqx(jgid, hdbqs);
					}
					/*插入能力标签*/
					if(null != nlbqs && nlbqs.length > 0){
						result = jgDao.BatchInsertNlbqx(jgid, nlbqs);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @description	： 获取最后一级审核信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 上午11:49:38
	 * @param form
	 * @return
	 */
	public HdblsqshForm getModelForSh(HdblsqshForm form){
		HashMap<String,String> zdMap = dao.getShxxLatest(form);
		if(StringUtils.isNotNull(zdMap.get("zd2")) && StringUtils.isNotNull(zdMap.get("zd6")) && StringUtils.isNotNull(zdMap.get("zd9"))){
			String hdlx = zdMap.get("zd2");
			String hdbq = zdMap.get("zd6");
			String hdxf = zdMap.get("zd9");
			form.setHdlx(hdlx);
			form.setHdbqs(hdbq.split(","));
			form.setHdxf(hdxf);
		}
		return form;
	}
	
	/**
	 * @description	： 撤销
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 下午04:57:16
	 * @param ywid
	 * @param shForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String cxshnew(String ywid, HdblsqshForm shForm, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		shForm.setShzt(shzt);
		String cancelFlag = service.runCancelNew(user.getUserName(), shForm.getShid(), shForm.getSplc());
		shForm.setSqid(ywid);
		dao.runUpdate(shForm, shForm.getSqid());
		return cancelFlag;
	}
	
	/**
	 * @description	： 最后一级撤销
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 下午05:16:25
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean cancel(HdblsqshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			HdbljgService jgService = new HdbljgService();
			HdbljgService newProxy = TransactionControlProxy.newProxy(jgService);
			String jgid = jgService.getJgidBySqid(myForm.getSqid());
			
			if(StringUtils.isNotNull(jgid)){				
				// 删除结果表中的申请结果
				newProxy.runDeleteJg(new String[]{jgid});
			}		
		}
		return result;
	} 
}
