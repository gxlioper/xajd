package xgxt.pjpy.zjkjxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.cssz.PjpyZjcmCsszModel;
import xgxt.utils.String.StringUtils;

import common.Globals;
import common.GlobalsVariable;

public class PjpyZjkjxyService extends PjpyTyService{
	
	/**
	 * 修改奖学金或荣誉称号人数
	 * @param model
	 * @return boolean
	 * */
	public boolean updateJxjrs(PjpyZjcmCsszModel model){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		boolean result = true;//操作结果
		
		try{
			String pjzq = getPjpySqzq();
			if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
				pjzq = getZhcpSqzq();
			}
			if("xn".equalsIgnoreCase(pjzq)){
				model.setNd("无");
				model.setXq("无");
			}else if("xq".equalsIgnoreCase(pjzq)){
				model.setNd("无");
			}else if("nd".equalsIgnoreCase(pjzq)){
				model.setXn("无");
				model.setXq("无");
			}
			//修改学院人数
			dao.updateJxjrs(model);
			//修改专业人数
			model.setCpfw("zy");
			dao.updateJxjrs(model);
			//修改班级人数
			model.setCpfw("bj");
			dao.updateJxjrs(model);
		} catch (Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 奖学金人数调整
	 * @param form
	 * @return String
	 * */
	public String jxjrstz(PjpyZjkjxyActionForm form){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		String message = "";//操作提示信息
		//获取人数调整的单位是学院、专业或班级
		String tzbm = form.getFs();//
		String cpfw = dao.getTzrsbm(form);//获取参评范围
		
		form.setXn(StringUtils.isNotNull(form.getXn()) ? form.getXn() : "无");
		form.setXq(StringUtils.isNotNull(form.getXq()) ? form.getXq() : "无");
		form.setNd(StringUtils.isNotNull(form.getNd()) ? form.getNd() : "无");
		if(StringUtils.isNotNull(tzbm) && StringUtils.isNotNull(cpfw)){
			int cxrs = 0;//超限人数
			int sjbmrs = 0;//上级部门人数
			int wtztjbmrs = 0;//没有调整人数的同级部门人数
			int tzrszh = 0;//调整的人数总和
			tzrszh = getTzrszh(form);
			//判断调整人数是否超出限制
			if("bj".equalsIgnoreCase(tzbm)){//班级
				sjbmrs = dao.getBjsszyrs(form);
				wtztjbmrs = dao.getWtjbjrs(form);				
				
			}else if("zy".equalsIgnoreCase(tzbm)){//专业
				sjbmrs = dao.getZyssxyrs(form);
				wtztjbmrs = dao.getWtjzyrs(form);
				//专业下级部门调整了人数后，专业不能再调整人数
				//xjbmrs = dao.getZyxjbmrs(form);
			}else if("xy".equalsIgnoreCase(tzbm)){//学院
				//学院不需要判断人数上限
				//学院下级部门调整了人数后，学院不能再调整人数
				//xjbmrs = dao.getXyxjbmrs(form);
			}
			if(sjbmrs <0){//上级部门未分配人数
				cxrs = sjbmrs;
			}else{
				cxrs = (wtztjbmrs+tzrszh)-sjbmrs;//超限人数
				cxrs = cxrs<0 ? 0 : cxrs;
			}
			//保存调整后的人数
			if(cxrs==0 || "xy".equalsIgnoreCase(tzbm)){
				try{
					message= dao.updateJxjrstz(form,tzbm);
				} catch (Exception e) {
					message = "操作失败！";
					e.printStackTrace();
				}
			}else{
				if(cxrs <0){
					message = "上级部门还未调整人数，暂时不能调整！";
				}else{
					message = "调整后的人数超过上级部门的人数，超过人数为:" + cxrs + "人！";
				}
			}
		}else{
			message = "未设置比例，暂时不能调整人数！";
		}
		
		return message;
	}
	
	/**
	 * 调整总人数
	 * @param form
	 * @return int
	 * */
	public int getTzrszh(PjpyZjkjxyActionForm form){
		String[] tzrszh = form.getJxjtzrs();
		int zrs = 0;
		for(int i=0; i<tzrszh.length; i++){
			if(StringUtils.isNotNull(tzrszh[i])){
				zrs += Integer.parseInt(tzrszh[i]);
			}
		}
		return zrs;
	}
	
	/**
	 * 获取下拉列表值
	 * @param type
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getChkList(int type){
		DAO dao = DAO.getInstance();
		return dao.getChkList(type);
	}
	
	/**
	 * 获取奖学金代码列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getJxjList(){
		DAO dao = DAO.getInstance();
		String sql = "select jxjdm en,jxjmc cn from jxjdmb";
		String[] outputValue = {"en", "cn"};
		return dao.getList(sql, new String[]{}, outputValue);
	}
	
	/**
	 * 获取奖学金类别列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getJxjlbList(boolean flag){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		DAO dao = DAO.getInstance();
		String sql = "select dm ,mc from jxjlbxxdmb order by dm";
		if(flag){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "--请选择--");
			rs.add(map);
		}
		String[] outputValue = {"dm", "mc"};
		rs.addAll(dao.getList(sql, new String[]{}, outputValue));
		return rs;
	}
	
	
	
	/**
	 * 获取荣誉称号代码列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getRychList(){
		DAO dao = DAO.getInstance();
		String sql = "select rychdm en,rychmc cn from rychdmb";
		String[] outputValue = {"en", "cn"};
		return dao.getList(sql, new String[]{}, outputValue);
	}	
	
	/**
	 * 奖学金申请人数设置查询
	 * @param model
	 * @param output
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsqrssz(PjpyZjkjxyActionForm model,String[] output){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		return dao.queryJxjsqrsz(model, output);
	}
	
	/**
	 * 奖学金申请人数设置查询,无分页
	 * @param model
	 * @param output
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjsqrsszNotP(PjpyZjkjxyActionForm model,String[] output){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		return dao.queryJxjsqrsszNotP(model, output);
	}
	
	
	/**
	 * 获取表头信息
	 * @param lx
	 * @param output
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String lx, String[] output){
		DAO dao = DAO.getInstance();
		String[] cnColumn = {};
		if("jxjsqxs".equalsIgnoreCase(lx)){
			cnColumn = dao.getColumnNameCN(output, "view_pjpy_jxjsqxs");			
		}
		
		return dao.arrayToList(output, cnColumn);
	}
	
	/**
	 * 获取表头对应的中文列名
	 * @param list
	 * @return String[]
	 * */
	public String[] getCn(List<HashMap<String, String>> list){
		String[] colCN = new String[list.size()];
		for(int i=0; i<list.size(); i++){
			colCN[i] = list.get(i).get("cn");
		}
		
		return colCN;
	}
	
	
	/**
	 * 保存奖学金申请人设置
	 * @param model
	 * @return String
	 * */
	public String saveJxjsqrssz(PjpyZjkjxyActionForm model){
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		return dao.saveJxjsqxs(model);		
	}
	
	/**
	 * 奖学金审核
	 * @param pkValue
	 * @param shzd
	 * @return String
	 * */
	public String auditingJxj(HashMap<String, String[]> pkValue, 
			                  String shzd,
			                  String tableName){
		DAO dao = DAO.getInstance();
		String resultMsg = "";
		String[] pkV = pkValue.get("cbv");
		String key = "typj_rychsq".equalsIgnoreCase(tableName) 
		                 ? GlobalsVariable.PJPY_RYCH 
		                 : GlobalsVariable.PJPY_JXJ;
		//主键
		String pk = GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key) 
		                 ? "xh||xn||jxjdm||sqsj||xq||nd"
		                 : "xh||xn||rychdm||nd||xq";
		
		for(int i=0; i<pkV.length; i++){
			//判断人数是否超限
			String msg = checkJxjrs(pk,pkV[i], shzd,key);
			if(StringUtils.isNotNull(msg)){
				if(pkV.length<2){
					resultMsg += "操作失败，" + msg + "\n";
				}else{
					resultMsg += "第" + (i+1) + "行操作失败，" + msg + "\n";
				}
				
			}else{
				try{
					dao.runUpdate("update " + tableName + " set " + shzd + "='通过' where " + pk + "=?", 
							      new String[]{pkV[i]});
				} catch (Exception e) {
					if(pkV.length<2){
						resultMsg += "操作失败！\n";
					}else{
						resultMsg += "第" + (i+1) + "行操作失败！\n";
					}                     
				}
			}
		}
		
		return resultMsg;
	}
	
	/**
	 * 判断奖学金人数是否超过限制
	 * @param pkV
	 * @param shzd
	 * @return String
	 * */
	public String checkJxjrs(String pk,String pkV,String shzd,String key){
		String result = "";
		PjpyZjkjxyDAO dao = new PjpyZjkjxyDAO();
		HashMap<String, String> rsfpMap = dao.getRsfpMap(pk,pkV,shzd,key);
			
		//查询奖学金人数的主键值
		String zjz = rsfpMap.get("pkV");
		//已经审核通过的人数（在审核级别范围内审核通过的人数）
		int tgrs = Integer.parseInt(rsfpMap.get("tgrs"));
		
		//奖学金人数（分配人数）
		String jxjrs = dao.getJxjrs(zjz,shzd,key);
		
		if(StringUtils.isNull(jxjrs)){
			result = "人数还未分配，暂时不能审核通过！";
		}else{
			int fprs = Integer.parseInt(jxjrs);
			if(fprs<=tgrs){
				result = "审核通过人数已经达到限制人数！";
			}
		}
		return result;
	}
	
}
