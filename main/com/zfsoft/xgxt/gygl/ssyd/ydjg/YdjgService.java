package com.zfsoft.xgxt.gygl.ssyd.ydjg;

import java.util.HashMap;
import java.util.List;
import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.gygl.ssyd.ydsh.YdshDAO;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述:宿舍异动审核
 * @作者： qilm
 * @时间：2013-9-29
 * @版本： V1.0
 */
public class YdjgService extends SuperServiceImpl<YdjgForm, YdjgDAO> {
	/**
	 * 数据来源：0[结果库追加]
	 */
	private static final String SJLY_JG = "0";

	/**
	 *  退宿
	 */
	public static String YDLX_TS = "00";
	/**
	 *  宿舍调整
	 */
	public static String YDLX_SSTZ = "01";
	/**
	 *  入住
	 */
	public static String YDLX_SSRZ = "03";

	
	/**
	 *  当前学年学期FLG (0:当前学期）
	 */
	public static final String CURR_XNXQ_FLG_Y = "0";

	/**
	 *  当前学年学期FLG (1:不是当前学期）
	 */
	public static final String CURR_XNXQ_FLG_N = "1";
	
	/**
	 *  是否最近记录FLG (0:是）
	 */
	public static final String SFZJJL_FLG_Y = "0";
	/**
	 * 是否床位初始化[0:初始化]
	 */
	private static final String  SFCWCSH_CSH = "0";
	/**
	 * 是否床位初始化[1:不初始化]
	 */
	private static final String  SFCWCSH_BCSH = "1";

	
	private YdjgDAO dao = new YdjgDAO();
	
	public YdjgService() {
		super.setDao(dao);
	}
	

	/**
	 * 
	 * @描述:保存宿舍异动结果
	 * @作者：qilm
	 * @日期：2013-9-9 下午06:46:35
	 * @param qf
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public boolean save(YdjgForm model) throws Exception {

		boolean returnFlg = true;
		YdsqService ydsqService = new YdsqService();
		YdshDAO ydshdao = new YdshDAO();
		String guid = UniqID.getInstance().getUniqIDHash();

		// 是否床位初始化
		String sfcwcsh = model.getSfcwcsh();
		
		//宿舍异动ID
		model.setSsydid(guid);
		// 操作时间
		model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		// 数据来源
		model.setSjly(SJLY_JG);
		
		// 异动前床位信息
		HashMap<String,String> ydqCwxx = ydsqService.getCwxxForXh(model.getXh());
		model.setYdqlddm(ydqCwxx.get("lddm"));
		model.setYdqldmc(ydqCwxx.get("ldmc"));
		model.setYdqqsh(ydqCwxx.get("qsh"));
		model.setYdqcwh(ydqCwxx.get("cwh"));
		model.setYdqqsrzsj(ydqCwxx.get("rzsj"));
		
		// 退宿
		if(YDLX_TS.equals(model.getSsydlx())){

			// 插入 结果表
			returnFlg = super.runInsert(model);
			
			if(returnFlg){
				HashMap<String, String> cwxxMap = new HashMap<String, String>();
	
				cwxxMap.put("xh", "");
				cwxxMap.put("lddm", model.getYdqlddm());
				cwxxMap.put("qsh", model.getYdqqsh());
				cwxxMap.put("cwh", model.getYdqcwh());
				cwxxMap.put("rzsj", "");//入住时间
				
				// 是否初始化
				if(SFCWCSH_CSH.equals(sfcwcsh)){
	
					cwxxMap.put("nj", "");
					cwxxMap.put("bjdm", "");
					cwxxMap.put("zydm", "");
					cwxxMap.put("xydm", "");
				}
				
				// 床位信息更新
				returnFlg =  ydshdao.updateCwxxb(cwxxMap);
			}
			
			// 宿舍调整
		}else if(YDLX_SSTZ.equals(model.getSsydlx())){
			
			//调整后床位信息
			String ydhcwxx = model.getCwxx();
			
			if(ydhcwxx!=null && !"".equals(ydhcwxx)){
				String[] cwxx = ydhcwxx.split("_");
				
				// 查询
				HashMap<String, String> cwxxMapGet = ydsqService.getCwxx(cwxx[0], cwxx[1], cwxx[2]);

				// 异动后床位信息
				String ydhlddm = cwxxMapGet.get("lddm");
				String ydhldmc = cwxxMapGet.get("ldmc");
				String ydhqsh = cwxxMapGet.get("qsh");
				String ydhcwh = cwxxMapGet.get("cwh");
				String ydhrzsj = cwxxMapGet.get("rzsj");
				
				//调整后床位信息
				model.setYdhlddm(ydhlddm);
				model.setYdhldmc(ydhldmc);
				model.setYdhqsh(ydhqsh);
				model.setYdhcwh(ydhcwh);
				
				// 异动后寝室原所属
				model.setYdhnj(cwxxMapGet.get("nj"));
				model.setYdhxydm(cwxxMapGet.get("xydm"));
				model.setYdhzydm(cwxxMapGet.get("zydm"));
				model.setYdhbjdm(cwxxMapGet.get("bjdm"));

				// 設定調整時間/原因
				model.setTstzsj(model.getTzsssj());
				model.setTstzyy(model.getTzssyy());

				// 插入 结果表
				returnFlg = super.runInsert(model);
				
				HashMap<String, String> cwxxMap = new HashMap<String, String>();
				
				// 异动后的床位信息更新
				cwxxMap.put("lddm", ydhlddm);
				cwxxMap.put("qsh", ydhqsh);
				cwxxMap.put("cwh", ydhcwh);
				cwxxMap.put("xh", model.getXh());
				HashMap<String, String> xsxx = dao.getXsInfo(model.getXh());
				cwxxMap.put("nj", xsxx.get("nj"));
				cwxxMap.put("bjdm", xsxx.get("bjdm"));
				cwxxMap.put("zydm", xsxx.get("zydm"));
				cwxxMap.put("xydm", xsxx.get("xydm"));
				cwxxMap.put("rzsj", model.getTstzsj());//入住时间
				
				// 床位信息更新
				returnFlg = ydshdao.updateCwxxb(cwxxMap);

				// 异动关联学生
				String ydglxh = cwxxMapGet.get("xh")==null ?"":cwxxMapGet.get("xh");
				
				// 学生对调（插入对调异动结果）
				if(!"".equals(ydglxh)){

					YdjgForm ydjgFormGl = new YdjgForm();
					String guidGl = UniqID.getInstance().getUniqIDHash();

					ydjgFormGl.setSsydid(guidGl);
					ydjgFormGl.setXh(ydglxh);
					ydjgFormGl.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
					ydjgFormGl.setXn(model.getXn());
					ydjgFormGl.setXq(model.getXq());
					ydjgFormGl.setSsydlx(model.getSsydlx());
					ydjgFormGl.setTstzsj(model.getTstzsj());
					ydjgFormGl.setTstzyy(model.getTstzyy());
					ydjgFormGl.setBz(model.getBz());
					//異動前床位信息
					ydjgFormGl.setYdqlddm(ydhlddm);
					ydjgFormGl.setYdqldmc(ydhldmc);
					ydjgFormGl.setYdqqsh(ydhqsh);
					ydjgFormGl.setYdqcwh(ydhcwh);
					//異動後床位信息
					ydjgFormGl.setYdhlddm(model.getYdqlddm());
					ydjgFormGl.setYdhldmc(model.getYdqldmc());
					ydjgFormGl.setYdhqsh(model.getYdqqsh());
					ydjgFormGl.setYdhcwh(model.getYdqcwh());		
					ydjgFormGl.setSfcwcsh(SFCWCSH_BCSH);//是否床位初始化
					ydjgFormGl.setSjly(SJLY_JG);		//设定数据来源
					ydjgFormGl.setSsydsqid("");
					ydjgFormGl.setYdqqsrzsj(ydhrzsj);//异动前寝室入住时间

					// 插入 结果表
					returnFlg = super.runInsert(ydjgFormGl);

					cwxxMap = new HashMap<String, String>();
					
					// 异动前的床位信息更新
					cwxxMap.put("lddm", model.getYdqlddm());
					cwxxMap.put("qsh", model.getYdqqsh());
					cwxxMap.put("cwh", model.getYdqcwh());
					cwxxMap.put("xh", ydglxh);
					HashMap<String, String> xsxxGl = dao.getXsInfo(ydglxh);
					
					cwxxMap.put("nj", xsxxGl.get("nj"));
					cwxxMap.put("bjdm", xsxxGl.get("bjdm"));
					cwxxMap.put("zydm", xsxxGl.get("zydm"));
					cwxxMap.put("xydm", xsxxGl.get("xydm"));
					cwxxMap.put("rzsj", model.getTstzsj());//入住时间
					returnFlg = ydshdao.updateCwxxb(cwxxMap);
					
					//无关联学生
				}else if( returnFlg){
					
					cwxxMap =  new HashMap<String, String>() ;
					cwxxMap.put("xh", "");
					cwxxMap.put("lddm", model.getYdqlddm());
					cwxxMap.put("qsh", model.getYdqqsh());
					cwxxMap.put("cwh", model.getYdqcwh());
					cwxxMap.put("rzsj", "");//入住时间
					
					// 是否初始化
					if(SFCWCSH_CSH.equals(sfcwcsh)){
		
						cwxxMap.put("nj", "");
						cwxxMap.put("bjdm", "");
						cwxxMap.put("zydm", "");
						cwxxMap.put("xydm", "");
					}
					returnFlg = ydshdao.updateCwxxb(cwxxMap);
				}
			}
		}else if(YDLX_SSRZ.equals(model.getSsydlx())){
			
			//调整后床位信息
			String ydhcwxx = model.getRzcwxx();
			
			// 异动后床位信息
			String ydhlddm = "";
			String ydhldmc = "";
			String ydhqsh = "";
			String ydhcwh = "";
			
			if(ydhcwxx!=null && !"".equals(ydhcwxx)){
				String[] cwxx = ydhcwxx.split("_");
				
				// 查询
				HashMap<String, String> cwxxMapGet = ydsqService.getCwxx(cwxx[0], cwxx[1], cwxx[2]);

				// 异动后床位信息
				ydhlddm = cwxxMapGet.get("lddm");
				ydhldmc = cwxxMapGet.get("ldmc");
				ydhqsh = cwxxMapGet.get("qsh");
				ydhcwh = cwxxMapGet.get("cwh");
//				String ydhrzsj = cwxxMapGet.get("rzsj");
				
				//调整后床位信息
				model.setYdhlddm(ydhlddm);
				model.setYdhldmc(ydhldmc);
				model.setYdhqsh(ydhqsh);
				model.setYdhcwh(ydhcwh);
				
				// 异动后寝室原所属
				model.setYdhnj(cwxxMapGet.get("nj"));
				model.setYdhxydm(cwxxMapGet.get("xydm"));
				model.setYdhzydm(cwxxMapGet.get("zydm"));
				model.setYdhbjdm(cwxxMapGet.get("bjdm"));
			}

			// 設定調整時間/原因
			model.setTstzsj(model.getRzsssj());
			model.setTstzyy(model.getRzssyy());

			// 插入 结果表
			returnFlg = super.runInsert(model);
			
			HashMap<String, String> cwxxMap = new HashMap<String, String>();
			
			// 异动后的床位信息更新
			cwxxMap.put("lddm", ydhlddm);
			cwxxMap.put("qsh", ydhqsh);
			cwxxMap.put("cwh", ydhcwh);
			cwxxMap.put("xh", model.getXh());
			HashMap<String, String> xsxx = dao.getXsInfo(model.getXh());
			cwxxMap.put("nj", xsxx.get("nj"));
			cwxxMap.put("bjdm", xsxx.get("bjdm"));
			cwxxMap.put("zydm", xsxx.get("zydm"));
			cwxxMap.put("xydm", xsxx.get("xydm"));
			cwxxMap.put("rzsj", model.getTstzsj());//入住时间
			cwxxMap.put("rzyydm", model.getTstzyy());
			
			// 床位信息更新
			ydshdao.updateCwxxb(cwxxMap);
		}
		
		return returnFlg;
	}



	/** 
	 * @描述:学生最近的一次宿舍异动信息
	 * @作者：qilm
	 * @日期：2013-10-8 上午10:07:58
	 * @param myForm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXsydInfo(YdjgForm myForm) {

		return dao.getXsydInfo(myForm);
	}

	/**
	 * @throws Exception  
	 * @描述:学生最近的更多宿舍异动信息
	 * @作者：qilm
	 * @日期：2013-10-8 上午10:07:58
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsYdList(YdjgForm myForm) throws Exception {
		
		return dao.getXsYdList(myForm);
	}

	/** 
	 * @描述:按学年学期取出床位异动信息
	 * @作者：qilm
	 * @日期：2013-10-8 下午05:14:15
	 * @param myForm
	 * @param currXnxqFlgY
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getQsYdList(YdjgForm myForm,
			String currXnxqFlg) throws Exception {
		
		return dao.getQsYdList(myForm,currXnxqFlg);
	}
	
	/**
	 * 
	 * @描述:取得宿舍异动结果信息
	 * @作者：qilm
	 * @日期：2013-10-9 上午10:41:59
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYdjg(YdjgForm model) throws Exception {
		return dao.getYdjg(model);
	}


	/**
	 * 
	 * @描述:删除该申请对应的宿舍异动结果库
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param ssydsqid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int runDeleteYdjg(String ssydsqid) throws Exception  {
		return dao.runDeleteYdjg(ssydsqid);
	}
	
	/**
	 * 
	 * @描述:根据申请ID取得异动结果
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param ssydsqid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYdjg(String ssydsqid) throws Exception{
		return dao.getYdjg(ssydsqid);
	}
	/** 
	 * @描述:学生相关信息
	 * @作者：qilm
	 * @日期：2013-10-10 上午11:59:38
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXsInfo(String xh) {
		return dao.getXsInfo(xh);
	}
	
	/**
	 * 
	 * @描述:根据宿舍异动ID获取异动信息
	 * @作者：cq [工号：785]
	 * @日期：2014-6-3 下午05:14:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ssydid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYdxx(String ssydid){
		
		return dao.getYdxx(ssydid);
	}
	
}
