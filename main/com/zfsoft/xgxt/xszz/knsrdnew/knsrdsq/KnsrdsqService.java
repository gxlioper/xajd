/**
 * @部门:学工产品事业部
 * @日期：2014-1-24 上午09:05:37 
 */
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import xgxt.utils.GetTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 困难生认定管理模块
 * @类功能描述: TODO(困难生认定申请) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-24 上午09:05:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdsqService extends
		SuperServiceImpl<KnsrdsqForm, KnsrdsqDao> {

	private KnsrdsqDao dao = new KnsrdsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public KnsrdsqService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(获取启用的指标Id)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-24 上午11:43:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsrdzbid(){
		
		return dao.getKnsrdzbid();
	}


	/**
	 * 
	 * @描述:TODO(保存困难生认定申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-26 上午09:03:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveKnsrdsq(JSONArray jsonArray,KnsrdsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
    	model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		for(int i=0;i<jsonArray.length(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i); 
			KnsrdzbsqsxForm knsrdzbsqsxForm = new KnsrdzbsqsxForm();
			String sqsxid = UniqID.getInstance().getUniqIDHash();
			setKnsrdzbsqsx( jsonJ, knsrdzbsqsxForm);
			knsrdzbsqsxForm.setId(sqsxid);
			knsrdzbsqsxForm.setSqid(sqid);
			//往困难生指标申请-指标属性表里面插入数据
			dao.addKnsrdzbsqsx(knsrdzbsqsxForm);
			JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
			//往困难指标申请-指标内容表里面插入数据
			for(int j=0;j<zbnrJsonArray.length(); j++){
				KnsrdzbsqnrForm knsrdzbsqnrForm = new KnsrdzbsqnrForm();
				JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
				String sqnrid = UniqID.getInstance().getUniqIDHash();
				knsrdzbsqnrForm.setId(sqnrid);
				knsrdzbsqnrForm.setSqid(sqid);
				setKnsrdzbsqnr(jsonZbnr,knsrdzbsqnrForm);
				dao.addKnsrdzbsqnr(knsrdzbsqnrForm);
			}
		}
		// 获取审批流程
		JcszDao jcszDao = new JcszDao();
		JcszForm jcszModel = jcszDao.getModel();
		if (jcszModel != null && !StringUtil.isNull(jcszModel.getSplc())) {
			// 设置审批流程到申请记录上
			model.setSplc(jcszModel.getSplc());
		}
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_xszz_knsrd_knsh.do","xg_xszz_knsrd_knsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(按照学号,学年，学期判断该学生困难生认定申请是否已经存在)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午09:17:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByKnsrdsq(KnsrdsqForm model)
	throws Exception {
		boolean flag = false;
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(更新困难生认定申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午08:54:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jsonArray
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateKnsrdsq(JSONArray jsonArray,KnsrdsqForm model) throws Exception {
		
		boolean insertResult =false;
		String sqid = model.getSqid();
		boolean delKnssqsx = false;
		boolean delKnssqnr = false;
		//按照sqid 删除困难生指标申请-指标属性 详细内容
		delKnssqsx = dao.deleteKnsrdzbsqsx(sqid);
		//按照sqid 删除困难指标申请-指标内容表 详细内容
		delKnssqnr = dao.deleteKnsrdzbsqnr(sqid);
		if(delKnssqsx && delKnssqnr){
				if(model.getType().equals(SUBMIT)){
					model.setShzt(Constants.YW_SHZ);//审核中
				}else{
					model.setShzt(Constants.YW_WTJ);//未提交
				}
				model.setSqid(sqid);
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		    	model.setXn(Base.currXn);
				model.setXq(Base.currXq);
				for(int i=0;i<jsonArray.length(); i++){
					JSONObject jsonJ = jsonArray.getJSONObject(i); 
					KnsrdzbsqsxForm knsrdzbsqsxForm = new KnsrdzbsqsxForm();
					String sqsxid = UniqID.getInstance().getUniqIDHash();
					setKnsrdzbsqsx( jsonJ, knsrdzbsqsxForm);
					knsrdzbsqsxForm.setId(sqsxid);
					knsrdzbsqsxForm.setSqid(sqid);
					//往困难生指标申请-指标属性表里面插入数据
					dao.addKnsrdzbsqsx(knsrdzbsqsxForm);
					JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
					//往困难指标申请-指标内容表里面插入数据
					for(int j=0;j<zbnrJsonArray.length(); j++){
						KnsrdzbsqnrForm knsrdzbsqnrForm = new KnsrdzbsqnrForm();
						JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
						String sqnrid = UniqID.getInstance().getUniqIDHash();
						knsrdzbsqnrForm.setId(sqnrid);
						knsrdzbsqnrForm.setSqid(sqid);
						setKnsrdzbsqnr(jsonZbnr,knsrdzbsqnrForm);
						dao.addKnsrdzbsqnr(knsrdzbsqnrForm);
					}
				}
				// 获取审批流程
				JcszDao jcszDao = new JcszDao();
				JcszForm jcszModel = jcszDao.getModel();
				if (jcszModel != null && !StringUtil.isNull(jcszModel.getSplc())&&StringUtil.isNull(model.getSplc())) {
					// 设置审批流程到申请记录上
					model.setSplc(jcszModel.getSplc());
				}
				insertResult = super.runUpdate(model);
				boolean result = false;
				if (insertResult&& SUBMIT.equalsIgnoreCase(model.getType())) {
//					shlc.deleteShjl(model.getSqid());    注释原因：审核退回到申请人后再重新提交会删除原有审核记录
					result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_xszz_knsrd_knsh.do","xg_xszz_knsrd_knsq.do");
					return result;
				}
		
		}
		return insertResult;
	}
	
	/**
	 * 
	 * @描述:TODO(困难生指标申请-指标属性Form里设置属性值)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-26 上午09:24:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jsonJ
	 * @param knsrdzbsqsxForm
	 * @return
	 * KnsrdzbsqsxForm 返回类型 
	 * @throws
	 */
	private KnsrdzbsqsxForm setKnsrdzbsqsx(JSONObject jsonJ,
			KnsrdzbsqsxForm knsrdzbsqsxForm) {
		
		String sxid = jsonJ.getString("sxid");
		String jtqk =  jsonJ.getString("jtqk"); 
		knsrdzbsqsxForm.setSxid(sxid);
		knsrdzbsqsxForm.setJtqk(jtqk);
		return knsrdzbsqsxForm;
		
	}
	
	/**
	 * 
	 * @描述:TODO(往困难指标申请-指标内容表设置属性值)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午08:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jsonJ
	 * @param knsrdzbsqnrForm
	 * @return
	 * KnsrdzbsqnrForm 返回类型 
	 * @throws
	 */
	private KnsrdzbsqnrForm setKnsrdzbsqnr(JSONObject jsonJ,
			KnsrdzbsqnrForm knsrdzbsqnrForm) {
		String nrid = jsonJ.getString("nrid");
		String fz =  jsonJ.getString("fz"); 
		knsrdzbsqnrForm.setNrid(nrid);
		knsrdzbsqnrForm.setSqfz(fz);
		//knsrdzbsqnrForm.setShfz(fz);
		return knsrdzbsqnrForm;
	}
	/**
	 * 
	 * @描述:TODO(获取困难生指标申请-指标属性集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午08:53:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(KnsrdsqForm model)throws Exception {
		
		return dao.getKnsrdzbsxList(model);
	}
	
	/**
	 * 
	 * @描述:TODO(获取困难指标申请-指标内容表ids)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午08:52:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsrdzbsqnrids(KnsrdsqForm model)throws Exception {
		
		return dao.getKnsrdzbsqnrids(model);
	}
	
	
	/**
	 * 
	 * @描述:TODO(删除困难生指标申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-26 下午05:17:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteKnsrdzbsq(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
				if(delId.size()>0){
					ShlcDao shlcdao = new ShlcDao();
					shlcdao.delelteShjl(str);
				}
			}else{
				HashMap<String, String> hm=dao.getKnsrdzbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?knsrdzbsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除困难生指标申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-26 下午05:25:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int knsrdzbsqDelete(String[] sqids) throws Exception {
		for(int i=0;i<sqids.length;i++){
			//按照sqid 删除困难生指标申请-指标属性 详细内容
			 dao.deleteKnsrdzbsqsx(sqids[i]);
			//按照sqid 删除困难指标申请-指标内容表 详细内容
			 dao.deleteKnsrdzbsqnr(sqids[i]);
		}
		return runDelete(sqids);
	}
	
	/**
	 * 
	 * @描述:TODO(提交困难生认定申请状态)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午08:32:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitKnsrdsq(KnsrdsqForm model) throws Exception {
		
		boolean resultKnsrdsq = dao.updateKnsrdsq(model.getSqid(), Constants.YW_SHZ);
		
		boolean result = false;
		if(resultKnsrdsq){
//		// 获取审批流程         注释理由：要根据申请iD获取审批流程。申请记录当中的审批流程和系统设置的审批流程有可能不一致。
//		JcszDao jcszDao = new JcszDao();
//		JcszForm jcszModel = jcszDao.getModel();
		//保存审核流程
		result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_xszz_knsrd_knsh.do","xg_xszz_knsrd_knsq.do");
		}
		return result;
	}
	

	/**
	 * 
	 * @描述:TODO(更新困难生认定申请状态)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午08:51:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateKnsrdsq(KnsrdsqForm model) throws Exception {
		boolean resultBbsq = dao.updateKnsrdsq(model.getSqid(),model.getShzt());
		return resultBbsq;
	}
	
	
	/**
	 * 
	 * @描述:TODO(只有刚提交并且第一级未审核的前提下，申请人可以撤销)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-27 上午08:52:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}

	
}
