package xsgzgl.gygl.qsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.cwgl.CwglForm;
import xsgzgl.gygl.cwgl.CwglModel;

public class QsglService extends GyglNewService{
	
	private QsglDAO qsglDao = new QsglDAO();
	
	/**
	 * 保存新增寝室
	 * @param model
	 * @return
	 */
	public String saveQswh(QsglModel model){
		boolean flag1 = false;
		boolean flag2 = false;
		String message = "";
		
		flag1 =  qsglDao.saveQswh(model);
		
		if(flag1){
			List<String[]> params = new ArrayList<String[]>();
			String lddm = model.getLddm();
			String qsh = model.getQsh();
			String cws = model.getCws();
			if ("10698".equals(Base.xxdm)) {
				String[] ch2 = {"A","B","C","D","E","F","G","H","I","J","H","I","J","K","L","M"};
				for (int i=0; i<=Integer.parseInt(cws); i++){
					params.add(new String[]{lddm, qsh, ch2[i]});
				}
			}else {
				for (int i=1; i<=Integer.parseInt(cws); i++){
					params.add(new String[]{lddm, qsh, i+""});
				}
			}
			
			
			flag2 = qsglDao.saveCws(params);
		}
		
		if(flag1 && flag2){
			message = "保存成功！";
		}else if(flag1 && !flag2){
			message = "床位自动生成失败！";
		}else{
			message = "保存失败！";
		}
		
		return message;
	}
	/**
	 * 修改寝室
	 * @param model
	 * @return
	 * @throws  
	 */
	public String updateQswh(String pkValue, QsglModel model) throws Exception{
		//温州大学个性化 可以修改寝室号 但必须检查时候有冲突 [1036]
		if(StringUtils.equals(Globals.XXDM_WZDX, Base.xxdm)){ 
			String yqsh = model.getYqsh(); //原寝室号
			String qsh = model.getQsh(); //修改后寝室号
			//只有在修改寝室号的情况下做数据冲突判断
			if(!StringUtils.isEqual(yqsh, qsh)){
				Map<String , String> queryQs = qsglDao.getQsForPk(model.getLddm() + model.getQsh());
				if(queryQs != null && queryQs.size() >= 1){
					return "该楼栋该寝室号已存在！";
				}
			}
		}
		
		//温州大学个性化 可以修改寝室号 但必须检查时候有冲突 [1036]
		return qsglDao.updateQswh(pkValue, model) ? "保存成功！" : "保存失败！";
	}
	
	/**
	 * 查询寝室
	 * @param model
	 * @return
	 */
	public List<String[]> queryQs(QsglModel model) throws Exception{
		return qsglDao.queryQs(model);
	}
	
	/**
	 * 批量删除寝室信息
	 * @param pk
	 * @return
	 */
	public boolean delQs(String[] pk){
		boolean flag = false;
		List<String[]> params = new ArrayList<String[]>();
		
		for(String str : pk){
			params.add(new String[]{str});
		}
		
		flag = qsglDao.delQs(params);
		
		// 删除关联床位
		if(flag){
			flag = qsglDao.delCws(params);
		}
		
		return flag;
	}
	
	/**
	 * 获取指定寝室信息
	 * @param pk
	 * @return
	 */
	public Map<String, String> getQsForPk(String pk){
		return qsglDao.getQsForPk(pk);
	}
	
	/**
	 * 获取某楼栋下所有的寝室
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String, String>> getQshForLd(String lddm, String ch){
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("lddm", lddm);
		
		if(StringUtils.isNotNull(ch) && !"null".equalsIgnoreCase(ch)){
			queryMap.put("ch", ch);
		}
		queryMap.put("orderBy", " order by qsh ");
		return qsglDao.getRsList("xg_gygl_new_qsxxb", queryMap, new String[]{"qsh"});
	}
	
	/**
	 * 获得指定楼栋指定层数下最大的寝室号和最大的床位数
	 * @param lddm
	 * @param ch
	 * @return
	 */
	public Map<String, String> getMaxQsInfo(String lddm, String ch){
		return qsglDao.getMaxQsInfo(lddm, ch);
	}
	
	/**
	 * 获取寝室的床位信息
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<String[]> getCwForQs(String pkValue){
		String[] inputValue = new String[]{pkValue};
		String[] outputValue = new String[]{"cwh",  "xh", "xm", "xsnj","xsxymc","xszymc","xsbjmc", "nj", "xymc", "sfbl","qsz"};
		
		return qsglDao.getCwForQs(inputValue, outputValue);
	}
	
	/**
	 * 导入数据
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return qsglDao.importData(request,response);
	}
	
	/**
	 * 获取寝室分配入住信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getQsfprzInfo(String pkValue ){
		return qsglDao.getQsfprzInfo(pkValue);
	}
	
	/**
	 * 初始化床位所属
	 * @author zhanghui
	 */
	public String initQsss(QsglForm myForm,HttpServletRequest request)throws Exception{

		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String cwStr = request.getParameter("qsStr");
		String[] primarykey_checkVal = null;
		if(cwStr !=null && !"".equalsIgnoreCase(cwStr)){//多个寝室
			primarykey_checkVal = cwStr.split("!!splitOne!!");
		}else {//数据集
			primarykey_checkVal = qsglDao.getQshs(searchTjstr);
		};
		myForm.setPrimarykey_checkVal(primarykey_checkVal);
		return qsglDao.initQsss(myForm,user);
	}
	
	/**
	 * 获取可初始化寝室数
	 * @param model
	 * @return
	 */
	public String getKcshqss(QsglModel model) throws Exception{
		return qsglDao.getKcshqss(model);
	}
	
	/**
	 * 获取查询条件
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String getSearchTjstr(QsglModel model) throws Exception{
		String[] sfhkcw=model.getSearchModel().getSearch_tj_sf();//临时移除掉是否含空床位
		model.getSearchModel().setSearch_tj_sf(null);
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		model.getSearchModel().setSearch_tj_sf(sfhkcw);
		
		String searchTjstr = "";
		if(searchTj!=null && !"".equalsIgnoreCase(searchTj)){
			String[] tj = searchTj.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+"'"+inputV[i]+"'";
			}
			searchTjstr+=tj[inputV.length];
		}
		return searchTjstr;
	}
	
	/**
	 * 寝室信息管理 自定义 导出 
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> qsxxglExportdata(QsglModel model) throws Exception{
		return qsglDao.qsxxglExportdata(model);
	}

	/** 
	 * @描述:(获取楼层性别)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-20 下午03:33:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param ch
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getLcXb(String lddm, String ch) {
		// TODO 自动生成方法存根
		return qsglDao.getLcXb(lddm, ch);
	}
	
	/**
	 * 
	 * @描述:保存寝室规格批量维护
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-27 上午10:11:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveplwhTypeOfQs(QsglForm para)
	throws Exception {
		boolean rs = qsglDao.saveplwhTypeOfQs(para, para.getPrimarykey_checkVal());
		if(rs){
			rs = qsglDao.delQsxx(para.getPrimarykey_checkVal());
		}
        int cws = Integer.parseInt(para.getCws());
        List<HashMap<String, String>> qsxxList = qsglDao.getQsxxList(para.getPrimarykey_checkVal());
        List<String[]> params = new ArrayList<String[]>();
        if(rs){
			if ("10698".equals(Base.xxdm)) {
				String[] ch2 = {"A","B","C","D","E","F","G","H","I","J","H","I","J","K","L","M"};
				for (int i = 0; i < qsxxList.size(); i++) {
					for (int j = 0; j < cws; j++) {
						String[] tempArr = new String[]{qsxxList.get(i).get("lddm"),qsxxList.get(i).get("qsh"),ch2[j]+""};
						params.add(tempArr);
					}
				}

			}else{
				for (int i = 0; i < qsxxList.size(); i++) {
					for (int j = 0; j < cws; j++) {
						String[] tempArr = new String[]{qsxxList.get(i).get("lddm"),qsxxList.get(i).get("qsh"),(j+1)+""};
						params.add(tempArr);
					}
				}
			}
        	 rs = qsglDao.saveCwxx(params);
        }
       
        return rs;
	}
}
