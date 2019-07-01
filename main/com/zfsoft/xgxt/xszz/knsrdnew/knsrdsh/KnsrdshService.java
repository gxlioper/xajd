/**
 * @部门:学工产品事业部
 * @日期： 2014-1-27 上午10:14:22 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcDao;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcForm;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;
import com.zfsoft.xgxt.xszz.knsrd.KnsrdForm;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq.KnsrdzbsqnrForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 困难生认定管理模块
 * @类功能描述: TODO(困难生认定审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-27 上午10:14:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdshService extends SuperServiceImpl<KnsrdshForm, KnsrdshDao> implements Constants{

	private KnsrdshDao dao = new KnsrdshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private KnsdcDao dcDao = new KnsdcDao();
	private KnsjgDao jgDao = new KnsjgDao();
	
	public KnsrdshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(得到困难生指标属性集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-20 上午09:22:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(KnsrdshForm model)throws Exception {
		return dao.getKnsrdzbsxList(model);
	}
	
	/**
	 * 
	 * @描述:TODO(得到困难生认定指标内容Ids)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-20 上午09:23:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsrdzbsqnrids(KnsrdshForm model)throws Exception {
		return dao.getKnsrdzbsqnrids(model);
	}
	/**
	 * 
	 * @描述:批量审核
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-10-9 上午10:57:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(KnsrdshForm t, User user) throws Exception {
		String[] ids = t.getSqids();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		JSONArray jsonArray = new JSONArray("[]");
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			KnsrdshForm model = new KnsrdshForm();
			model.setSqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setRddc(t.getRddc());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setSplc(t.getSplc());
			model.setXh(xhs[i]);
			
			boolean isSuccess = saveSh(model, user,"",jsonArray);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	
	/**
	 * 
	 * @描述:TODO(保存困难生认定审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-20 上午09:19:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @param nrids
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSh(KnsrdshForm form,User user,String nrids,JSONArray jsonArray)throws Exception {
		
		//如果该条申请记录有指标申请内容则先删除再重新插入
		if(!"".equals(nrids)){
			String[]nrid = nrids.split(",");
			dao.deleteKnsrdzbsqnrb(nrid);
		}
		//重新往指标申请内容表里插入审核后的数据
		for(int i=0;i<jsonArray.length(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i); 
			JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
			//往困难指标申请-指标内容表里面插入数据
			for(int j=0;j<zbnrJsonArray.length(); j++){
				KnsrdzbsqnrForm knsrdzbsqnrForm = new KnsrdzbsqnrForm();
				JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
				String sqnrid = UniqID.getInstance().getUniqIDHash();
				knsrdzbsqnrForm.setId(sqnrid);
				knsrdzbsqnrForm.setSqid(form.getSqid());
				setKnsrdzbsqnr(jsonZbnr,knsrdzbsqnrForm);
				dao.addKnsrdzbsqnr(knsrdzbsqnrForm);
			}
		}
		
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
		model.setSqrid(form.getXh());
		model.setTzlj("xg_xszz_knsrd_knsh.do");
		model.setTzljsq("xg_xszz_knsrd_knsq.do");
		
		if(form.getShjg().equals("1")){
			// 設定业务字段1[业务名称]
			model.setZd1("认定档次");
			// 設定业务字段2[业务ID]
			model.setZd2(form.getRddc());
			// 設定业务字段3
			KnsdcForm dcForm = new KnsdcForm();
			dcForm.setDcdm(form.getRddc());
			dcForm = dcDao.getModel(dcForm);
			model.setZd3(dcForm.getDcmc());
		}
		boolean reuslt = false;
		try {
			String shzt = shlc.runAuditing(model);
			KnsrdshForm upForm = new KnsrdshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(shzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//审核状态为通过的往日常行为结果表中保存该条数据
			if(shzt.equalsIgnoreCase(Constants.YW_TG)){
        		//插入困难生结果表
				KnsjgDao knsjgDao = new KnsjgDao();
				KnsjgForm knsjgModel = new KnsjgForm();
				KnsrdshForm copyModel = getModel(form);
				copyModel.setRddc(form.getRddc());
				BeanUtils.copyProperties(knsjgModel, copyModel);
				// 先按学号、学年、学期判断是否存在
				Map<String, String> map = knsjgDao.getXsknsjg(knsjgModel
						.getXh(), knsjgModel.getXn(), knsjgModel.getXq());
				if (!map.isEmpty()) {
					knsjgDao.delKnsjg(knsjgModel.getXn(), knsjgModel.getXq(), knsjgModel.getXh());
				}

				knsjgModel.setSjly("1");
				knsjgModel.setLylcywid(copyModel.getSqid());
				knsjgDao.runInsert(knsjgModel);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	private KnsrdzbsqnrForm setKnsrdzbsqnr(JSONObject jsonJ,
			KnsrdzbsqnrForm knsrdzbsqnrForm) {
		
		String nrid = jsonJ.getString("nrid");
		String fz =  jsonJ.getString("fz"); 
		String shfz =  jsonJ.getString("shfz"); 
		knsrdzbsqnrForm.setNrid(nrid);
		knsrdzbsqnrForm.setSqfz(fz);
		knsrdzbsqnrForm.setShfz(shfz);
		return knsrdzbsqnrForm;
		
	}
	
	/**
	 * 
	 * @描述:TODO(撤销困难生认定审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-20 上午08:58:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean newCancelSh(KnsrdshForm model){
		boolean resultKnsrdsh = false;
		try {
			//更新困难生认定
			resultKnsrdsh = dao.cancelKnsrdzbsq(model.getSqid(), Constants.YW_SHZ);
			if(resultKnsrdsh){
				//删除困难生认定结果中的记录
				jgDao.delJgb(model.getSqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultKnsrdsh;
	}
	
	/**
	 * 
	 * @描述:TODO(困难生认定申请集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-20 下午03:45:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sxid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbnrsqList(String sxid,String sqid) throws Exception {
		return dao.getKnsrdzbnrsqList(sxid,sqid);
	}

}
