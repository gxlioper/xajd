package xsgzgl.gygl.cwgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

public class CwglService extends GyglNewService{
	private CwglDAO cwglDao = new CwglDAO();
	
	/**
	 * 保存新增床位
	 * @param model
	 * @return
	 */
	public String saveCwwh(CwglModel model){
		
		return cwglDao.saveCwwh(model) ? "保存成功！" : "保存失败！";
	}
	
	public boolean updateCwqsh(String qsh1 , String sqh2 , String lddm) throws Exception{
		return cwglDao.updateCwqsh(qsh1, sqh2 , lddm);
	}
	
	/**
	 * 修改床位
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String updateCwwh(String pkValue, CwglModel model) throws Exception{
		boolean flag=false;
		flag=cwglDao.updateCwwh(pkValue, model);
		if(flag){
			//删除不住校备注
			cwglDao.deleteBzxbz(model.getXh());
		}
		return flag ? "保存成功！" : "保存失败！";
	}
	
	/**
	 * @描述：入住
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年2月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String ruzhu(String pkValue, CwglModel model) throws Exception{
		boolean flag=false;
		flag=cwglDao.updateCwwh(pkValue, model);
		cwglDao.ruzhu(pkValue, model);
		if(flag){
			//删除不住校备注
			cwglDao.deleteBzxbz(model.getXh());
		}
		return flag ? "保存成功！" : "保存失败！";
	}
	
	/**
	 * 查询床位
	 * @param model
	 * @return
	 */
	public List<String[]> queryCw(CwglModel model,HttpServletRequest request) throws Exception{
		
		return cwglDao.queryCw(model,request);
	}
	
	/**
	 * 批量删除床位信息
	 * @param pk
	 * @return
	 */
	public boolean delCw(String[] pk){
		List<String[]> params = new ArrayList<String[]>();
		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return cwglDao.delCw(params);
	}
	
	/**
	 * 获取单个床位信息
	 * @param pk
	 * @return
	 */
	public Map<String, String> getCwForPk(String pk){
		return cwglDao.getCwForPk(pk);
	}
	
	/**
	 * 获取最大床位号
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public Map<String, String> getCwhForQs(String lddm, String qsh){
		return cwglDao.getCwhForQs(lddm, qsh);
	}
	
	/**
	 * 批量修改是否保留
	 * @param myForm
	 * @return
	 */
	public String updateCwbl(CwglForm myForm,User user) throws Exception{
		return cwglDao.updateCwbl(myForm,user);
	}
	/**
	 * 预留类别列表
	 */
	public List<HashMap<String, String>> getYllbList() {
		return cwglDao.getYllbList();
	}

	/**
	 * 床位对调
	 * @param pk
	 * @param model
	 * @return
	 */
	public boolean cwdd(String[] pk,CwglModel model){
		return cwglDao.cwdd(pk,model);
	}
	
	
	/**
	 * 获取当前查询数据集已入住的床位总数
	 * @return
	 */
	public String getYrzrs(CwglModel model,User user) throws Exception{
		return cwglDao.getYrzrs(model,user);
	}
	
	/**
	 * 获取当前查询数据集未入住的床位总数
	 * @return
	 */
	public String getWrzrs(CwglModel model) throws Exception{
		return cwglDao.getWrzrs(model);
	}
	
	public String getSearchTjstr(CwglModel model) throws Exception{
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
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
	 * 保存退宿信息
	 * @author zhanghui
	 */
	public String saveTsxx(CwglForm myForm,HttpServletRequest request)throws Exception{

		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhstr");
		String[] pk_xh = null;
		if(xh !=null && !"".equalsIgnoreCase(xh)){//单个学生
			pk_xh = new String[]{xh};
		}else if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){//多个学生
			pk_xh = xhstr.split("!!splitOne!!");
		}else {//数据集
			pk_xh = cwglDao.getXhs(searchTjstr,user);
		}
		myForm.setPk_xh(pk_xh);
		return cwglDao.saveTsxx(myForm,user);
	}
	
	/**
	 * @描述：毕业处理-退宿 获取学生数量(武昌首义学院个性化)
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月22日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param searchModel
	 * @param user
	 * @return
	 * @throws Exception
	 * int 返回类型
	 */
	public int getCounts(CwglForm myForm,SearchModel searchModel, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		return cwglDao.getCounts(myForm,searchModel,user);
		
	}
	
	/**
	 * @描述：毕业处理-退宿(武昌首义学院个性化)
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月22日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param searchModel
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String saveTsxx2(CwglForm myForm,SearchModel searchModel,User user,HttpServletRequest request)throws Exception{
		
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =getSeachTj(myForm,searchModel,user);
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhstr");
		String[] pk_xh = null;
		if(xh !=null && !"".equalsIgnoreCase(xh)){//单个学生
			pk_xh = new String[]{xh};
		}else if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){//多个学生
			pk_xh = xhstr.split(",");
		}else {//数据集
			pk_xh = cwglDao.getXhs(searchTjstr,user);
		}
		myForm.setPk_xh(pk_xh);
		return cwglDao.saveTsxx(myForm,user);
	}
	
	/**
	 * @描述：毕业处理-退宿条件拼接(武昌首义学院个性化)
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月22日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param searchModel
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String getSeachTj(CwglForm myForm,SearchModel searchModel,User user)throws Exception{
		
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");	
		sql.append(" and xh in (");
		sql.append(" select xh ");
		sql.append(" from VIEW_XSBFXX  where 1=1 ");
		sql.append("and (sfyby ='是' )  ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);			
		sql.append(") "); 
		String sqlstr=sql.toString();
		// 高级查询条件
		String searchTjstr = "";
		if(sqlstr!=null && !"".equalsIgnoreCase(sqlstr)){
			String[] tj = sqlstr.replace("?", "split").split("split");
			for(int i=0;i<inputV.length;i++){
				searchTjstr += tj[i]+"'"+inputV[i]+"'";
			}
			searchTjstr+=tj[inputV.length];
		}
		
		return searchTjstr;
		
	}
	
	
	/**
	 * 初始化床位所属
	 * @author zhanghui
	 */
	public String initCwss(CwglForm myForm,HttpServletRequest request)throws Exception{

		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String cwStr = request.getParameter("cwStr");
		String[] primarykey_checkVal = null;
		if(cwStr !=null && !"".equalsIgnoreCase(cwStr)){//多张床位
			primarykey_checkVal = cwStr.split("!!splitOne!!");
		}else {//数据集
			primarykey_checkVal = cwglDao.getCwhs(searchTjstr);
		}
		myForm.setPrimarykey_checkVal(primarykey_checkVal);
		myForm.setBz(cwStr);
		return cwglDao.initCwss(myForm,user);
	}
	
	/**
	 * 获取学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsxx(String xh, HttpServletRequest request){
		return cwglDao.getXsxx(xh, request);
	}
	
	/**
	 * 查询学生
	 * @param model
	 * @return
	 */
	public List<String[]> queryXs(CwglForm model, HttpServletRequest request){
		return cwglDao.queryXs(model,request);
	}

	public List<HashMap<String,String>> getFdyList(String pkValue) {
		// TODO 自动生成方法存根
		
		return cwglDao.getFdyBzrList(pkValue);
	}
	
	/**
	 * 通过学号获得学生住宿信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getCwForXh(String pk){
		return cwglDao.getCwForXh(pk);
	}
	
	/**
	 * 
	 * @描述:根据学号获取同寝室其他床位信息
	 * 专业 学号 姓名 床位号
	 * @作者：zhangxiaobin [工号：1036]
	 * @日期：2015-2-11 上午09:54:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTqCwForXh(String xh){
		return cwglDao.getTqCw(xh);
	}
	
	/**
	 * 床位信息管理自定义导出
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> queryExportCw(CwglModel model,HttpServletRequest request) throws Exception{
		return cwglDao.queryExportCw(model,request);
	}
}
