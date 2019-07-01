package xgxt.xsxx.dagl.guizdx;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.form.User;

public class XsxxGuizdxDaglService extends CommService{
	
	XsxxGuizdxDaglDAO dao=new XsxxGuizdxDaglDAO();
	
	// --------------------------档案类型 begin---------------------------------
	
	/**
	 * 学生档案信息代码表
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getDadmbInfo(XsxxGuizdxDaglForm model,String[]colList) throws
		IllegalArgumentException,SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		return dao.getDadmbInfo(model,colList);
	}
	
	/**
	 * 获取类型列表信息
	 * @return 
	 */
	public List<HashMap<String,String>>getLxList(){
		
		DAO dao=DAO.getInstance();
		String []en={"001","002","003"};
		String []cn={"新生档案","在校档案","毕业转档"};
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean addDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.addDadmb(model);
		
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean updateDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.updateDadmb(model);
		
	}
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String,String> getOnDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.getOnDadmb(model);
		
	}
	
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public boolean delDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.delDadmb(model);
	}
	
	//-------------------------档案类型 end---------------------------
	
	
	// --------------------新生档案信息 begin---------------------
	/**
	 * 获取新生入学档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getXsrxdaInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		return dao.getXsrxdaInfo(model, colList);
	}
	
	/**
	 * 批量保存新生档案信息
	 * @throws Exception 
	 *
	 */
	public boolean saveXsdaByCheck(HttpServletRequest request,XsxxGuizdxDaglForm form) throws Exception{
		
		XsxxGuizdxDaglModel xsxxGuizdxDaglModel=new XsxxGuizdxDaglModel();
		User user=form.getUser();
		
		String userName=user.getUserName();
	
		//进行数据操作 的表名
		String realTable ="xg_gzdx_xsxx_xsrxdab";
		
		//新生档案类别
		String[] dm=form.getXsdaInfo();
		//学号
		String[] xh=form.getPkValue();
		
		String[] checkVal = new String[dm.length*xh.length];
		String[] dmArr = new String[dm.length*xh.length];
		int n=0;
		
		for(int j=0;j<xh.length;j++){
			for(int i=0;i<dm.length;i++){
				
				checkVal[n]=xh[j];
				dmArr[n]=dm[i];
				n++;
			}
		}
		String pk="xh";
		String arrzd[]={"xh","dm"};
		String onezd[]={"jlsj","jlr"};
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(xh);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
	
		xsxxGuizdxDaglModel.setXh(checkVal);
		xsxxGuizdxDaglModel.setDm(dmArr);
		xsxxGuizdxDaglModel.setJlr(userName);
		
		
		return saveInfoToDb(saveForm, xsxxGuizdxDaglModel, user);
		
	}
	
	
	
	/**
	 * 根据档案信息by档案类型
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getDaxxByLx(XsxxGuizdxDaglForm model){
		
		return dao.getDaxxByLx(model);
	}
	
	/**
	 * 单个保存新生档案
	 * @return
	 * @throws Exception 
	 */
	public boolean saveXsdaByOne(XsxxGuizdxDaglForm model) throws Exception{
	
		return dao.saveXsdaByOne(model);
	}
	
	/**
	 * 获取单个新生档案情况
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getXsdaByOne(XsxxGuizdxDaglForm model){
		
		return dao.getXsdaByOne(model);
	
	}
	// --------------------新生档案信息 end---------------------

	/**
	 * 获取在校生档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getZxdaInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		return dao.getZxdaInfo(model, colList);
	}
	
	/**
	 * 批量保存新生档案信息
	 * @throws Exception 
	 *
	 */
	public boolean saveZxdaByCheck(HttpServletRequest request,XsxxGuizdxDaglForm form) throws Exception{
		
		XsxxGuizdxDaglModel xsxxGuizdxDaglModel=new XsxxGuizdxDaglModel();
		User user=form.getUser();
		
		String userName=user.getUserName();
	
		//进行数据操作 的表名
		String realTable ="xg_gzdx_xsxx_xszxqjdab";
		
		//新生档案类别
		String[] dm=form.getXsdaInfo();
		//学号
		String[] xh=form.getPkValue();
		
		String[] checkVal = new String[dm.length*xh.length];
		String[] dmArr = new String[dm.length*xh.length];
		int n=0;
		
		for(int j=0;j<xh.length;j++){
			for(int i=0;i<dm.length;i++){
				
				checkVal[n]=xh[j];
				dmArr[n]=dm[i];
				n++;
			}
		}
		String pk="xh";
		String arrzd[]={"xh","dm"};
		String onezd[]={"jlsj","jlr"};
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(xh);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
	
		xsxxGuizdxDaglModel.setXh(checkVal);
		xsxxGuizdxDaglModel.setDm(dmArr);
		xsxxGuizdxDaglModel.setJlr(userName);
		
		
		return saveInfoToDb(saveForm, xsxxGuizdxDaglModel, user);
		
	}
	
	/**
	 * 单个保存在校生档案
	 * @return
	 * @throws Exception 
	 */
	public boolean saveZxdaByOne(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.saveZxdaByOne(model);
	}

	/**
	 * 获取单个在校档案情况
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getZxdaByOne(XsxxGuizdxDaglForm model){

		return dao.getZxdaByOne(model);
	}
//	 ------------------毕业生档案 begin------------------
	/**
	 * 获取毕业生档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getBysdaInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		return dao.getBysdaInfo(model, colList);
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean addBysda(HttpServletRequest request,XsxxGuizdxDaglForm model) throws Exception{
		
		boolean blog=false;
		String[]pkValue=new String[]{model.getXh()};
		//删除主表数据
		blog=dao.delBysda(model);
		//插入主表数据
		if(blog){
			blog=dao.addBysda(model);
		}
		
		if(blog){
			model.setPkValue(pkValue);
			blog=saveByzdByCheck(request, model);
		}
		return blog;
		
	}
	
	/**
	 * 增加档案代码信息
	 * @return
	 * @throws Exception 
	 */
	public boolean updateBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.updateBysda(model);
	}
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String,String> getOneBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.getOneBysda(model);
		
	}
	
	/**
	 * 查询单条档案代码表信息
	 * @return
	 * @throws Exception 
	 */
	public boolean delByda(XsxxGuizdxDaglForm model) throws Exception{
		
		
		return dao.delByda(model);
		
	}
	// ---------------------在校生档案 end-------------------
	
	/**
	 * 获取毕业生档案信息
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]>getDacxInfo(XsxxGuizdxDaglForm model,String[]colList) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		return dao.getDacxInfo(model, colList);
	}
	
	/**
	 * 批量保存新生档案信息
	 * @throws Exception 
	 *
	 */
	public boolean saveByzdByCheck(HttpServletRequest request,XsxxGuizdxDaglForm form) throws Exception{
		
		XsxxGuizdxDaglModel xsxxGuizdxDaglModel=new XsxxGuizdxDaglModel();
		User user=form.getUser();
		
		String userName=user.getUserName();
	
		//进行数据操作 的表名
		String realTable ="xg_gzdx_xsxx_xsbyzdb";
		
		//新生档案类别
		String[] dm=form.getXsdaInfo();
		//学号
		String[] xh=form.getPkValue();
		
		String[] checkVal = new String[dm.length*xh.length];
		String[] dmArr = new String[dm.length*xh.length];
		int n=0;
		
		for(int j=0;j<xh.length;j++){
			for(int i=0;i<dm.length;i++){
				
				checkVal[n]=xh[j];
				dmArr[n]=dm[i];
				n++;
			}
		}
		String pk="xh";
		String arrzd[]={"xh","dm"};
		String onezd[]={"jlsj","jlr"};
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(xh);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
	
		xsxxGuizdxDaglModel.setXh(checkVal);
		xsxxGuizdxDaglModel.setDm(dmArr);
		xsxxGuizdxDaglModel.setJlr(userName);
		
		
		return saveInfoToDb(saveForm, xsxxGuizdxDaglModel, user);
		
	}
	
	/**
	 * 获取单个在校档案情况
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getBydaByOne(XsxxGuizdxDaglForm model){
		return dao.getBydaByOne(model);
	}
}


