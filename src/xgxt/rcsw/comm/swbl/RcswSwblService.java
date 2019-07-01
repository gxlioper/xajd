package xgxt.rcsw.comm.swbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;

public class RcswSwblService extends CommService{

	RcswSwblDAO dao=new RcswSwblDAO();
	/**
	 * 根据项目代码获取
	 * 项目设置表详细信息
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author 裘力俊
	 */
	public HashMap<String,String>getXmszb(RcswSwblForm model){
		
		return dao.getXmszb(model);
	}
	
	/**
	 * 根据项目代码获取
	 * 该项目需显示字段
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author 裘力俊
	 */
	public List<HashMap<String,String>>getXszdList(RcswSwblForm model){
		
		return dao.getXszdList(model);
	}
	
	/**
	 * 根据项目代码获取
	 * 该项目需显示的分级字段(子项目字段显示)
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author 裘力俊
	 */
	public List<HashMap<String,String>>getFjzdxs(RcswSwblForm model){
		//该项目需显示字段
		List<HashMap<String,String>>xszdList=getXszdList(model);
		
		//分级字段列表
		List<HashMap<String,String>>fjzdList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<xszdList.size();i++){
			HashMap<String,String>xszdMap=xszdList.get(i);
			if("xg_rcsw_swsqzxb".equalsIgnoreCase(xszdMap.get("lyb"))){
				fjzdList.add(xszdMap);
			}
		}
		
		return fjzdList;
	}
	
	/**
	 * 根据项目代码获取
	 * 该项目需显示的分级字段(子项目字段显示)
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author 裘力俊
	 */
	public List<HashMap<String,String>>getSwsqzd(RcswSwblForm model){
		//该项目需显示字段
		List<HashMap<String,String>>xszdList=getXszdList(model);
		
		//分级字段列表
		List<HashMap<String,String>>swsqzdList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<xszdList.size();i++){
			HashMap<String,String>xszdMap=xszdList.get(i);
			if("xg_rcsw_swsqb".equalsIgnoreCase(xszdMap.get("lyb"))){
				swsqzdList.add(xszdMap);
			}
		}
		
		return swsqzdList;
	}
	
	public List<HashMap<String,String>>getXsxxzd(RcswSwblForm model){
		//该项目需显示字段
		List<HashMap<String,String>>xszdList=getXszdList(model);
		
		//分级字段列表
		List<HashMap<String,String>>xsxxzdList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<xszdList.size();i++){
			HashMap<String,String>xsxxzdMap=xszdList.get(i);
			if("view_xsjbxx".equalsIgnoreCase(xsxxzdMap.get("lyb"))){
				xsxxzdList.add(xsxxzdMap);
			}
		}
		
		return xsxxzdList;
	}
	
	
	/**
	 * 获取下拉列表数据（反射）
	 * @param model
	 * @param request
	 * @return
	 */
	public boolean resultXlkList(RcswSwblForm model,HttpServletRequest request){
		
		List<HashMap<String,String>>xszdList=getXszdList(model);
		for(int i=0;i<xszdList.size();i++){
			HashMap<String,String>xszdMap=xszdList.get(i);
			String selmethod=xszdMap.get("method");
			if(!Base.isNull(selmethod)){
				try {
					ArrayList<HashMap<String, String>> opList = (ArrayList<HashMap<String, String>>)GetMethod.class.getMethod(selmethod,
							(Class[]) null).invoke(null,(Object[]) null);
					request.setAttribute(xszdMap.get("zd")+"List", opList);
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
					return false;
				} 
			}
		}
		
		return true;
		
	}
	
	/**
	 * 保存事务办理申请表（主表信息保存）
	 * @param model
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSwblSq(RcswSwblForm model) throws Exception{
		List<HashMap<String,String>>swsqzdList=getSwsqzd(model);
		return dao.saveSwblSq(swsqzdList, model);
	}
	
	
	/**
	 * 保存事务(子项目)
	 * @param form
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSwblZx(RcswSwblForm form,User user) throws Exception{
		String xmdm=form.getXmdm();
		String xh=form.getXh();
		String sqsj=GetTime.getNowTime2();
		String[]xn=form.getXnArr();
		String[]xq=form.getXqArr();
		String[]nd=form.getNdArr();
		String[]zd1=form.getZd1Arr();
		String[]zd2=form.getZd2Arr();
		String[]zd3=form.getZd3Arr();
		String[]zd4=form.getZd4Arr();
		String[]zd5=form.getZd5Arr();
		String[]zd6=form.getZd6Arr();
		String[]zd7=form.getZd7Arr();
		String[]zd8=form.getZd8Arr();
		String[]zd9=form.getZd9Arr();
		String[]zd10=form.getZd10Arr();
		
		String pk="xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj";
		String[] pkValue = new String[1];
		pkValue[0]=xmdm+"!!@@!!"+xh+"!!@@!!"+sqsj;
			
		List<String>arrzd=new ArrayList<String>();
		String[]onezd={"xmdm","xh","sqsj"};
		List<HashMap<String,String>>fjzdList=getFjzdxs(form);
		for(int i=0;i<fjzdList.size();i++){
			HashMap<String,String>fjzdMap=fjzdList.get(i);
			arrzd.add(fjzdMap.get("zd"));
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName("xg_rcsw_swsqzxb");
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd.toArray(new String[]{}));
		saveForm.setOnezd(onezd);
		
		RcswSwblModel model=new RcswSwblModel();
		model.setXmdm(xmdm);
		model.setXh(xh);
		model.setSqsj(sqsj);
		model.setXn(xn);
		model.setXq(xq);
		model.setNd(nd);
		model.setZd1(zd1);
		model.setZd2(zd2);
		model.setZd3(zd3);
		model.setZd4(zd4);
		model.setZd5(zd5);
		model.setZd6(zd6);
		model.setZd7(zd7);
		model.setZd8(zd8);
		model.setZd9(zd9);
		model.setZd10(zd10);
		
		return saveInfoToDb(saveForm, model, user);
		
	}
	
	
	/**
	 * 获取结果查询信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getXsSwblList(RcswSwblForm model)throws Exception{
		
		return dao.getXsSwblList(model);
	}
	
	public List<HashMap<String,String>>getCxTopTr(RequestForm rForm){
		
		DAO dao=DAO.getInstance();
		String[]en={"xn","xqmc","nd","xh","nj","xymc","zymc","bjmc","sqsj"};
		String[]cn={"学年","学期","年度","学号","年级",Base.YXPZXY_KEY,"专业","班级","申请时间"};
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	public List<HashMap<String,String>>getShTopTr(RequestForm rForm){
		
		DAO dao=DAO.getInstance();
		String[]en={"xn","xqmc","xh","xm","xb","nj","xymc","zymc","bjmc","sqsj","shzt"};
		String[]cn={"学年","学期","学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","申请时间","审核状态"};
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 删除事务办理信息
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean delSwblxx(RcswSwblForm model) throws Exception{
		
		return dao.delSwblxx(model);
	}
	
	public HashMap<String,String>getSwblMap(RcswSwblForm model){
		
		return dao.getSwblMap(model);
	}
	
	/**
	 * 修改事务信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateSwbl(RcswSwblForm model) throws Exception{
		List<HashMap<String,String>>sqzdList=getSwsqzd(model);
		return dao.updateSwbl(sqzdList, model);
		
	}
	
	public boolean saveXmsh(RcswSwblForm model) throws Exception {

		
		return dao.saveXmsh(model);
	}
	 
	 /**
     * 审核界面信息
     * @param model
     * @return
     * @throws Exception
     */
    public List<String[]> getSpxxInfo(RcswSwblForm model) throws Exception{
    	
    	return dao.getSpxxInfo(model);
    }
    
    /**
     * 根据用户名获取当前模块
     * 所涉及到的用户所在审批岗位
     * @param model
     * @return List<HashMap<String,String>>
     */
    public List<HashMap<String,String>>getUserSpgw(RcswSwblForm model){

    	return dao.getUserSpgw(model);
    }
    
    /**
     * 审核岗位控制
     * @param request
     * @param model
     * @param spgwList
     */
    public void shgwKz(HttpServletRequest request, RcswSwblForm model,
			List<HashMap<String, String>> spgwList) {
		
    	// 判断登陆用户在几个审核岗位中
		if (spgwList.size() > 1 && Base.isNull(model.getShgw())) {

			request.setAttribute("spgwList", spgwList);
			

		} else if (spgwList.size() == 1 && !Base.isNull(model.getShgw())) {
			
			for (int i = 0; i < spgwList.size(); i++) {
				HashMap<String, String> spgwMap = spgwList.get(i);
				if (model.getShgw().equalsIgnoreCase(spgwMap.get("id"))) {
					model.setShjb(spgwMap.get("xh"));
					request.setAttribute("spgw", spgwMap.get("id"));
					break;
				}
			}
			
		} else {
			
			for (int i = 0; i < spgwList.size(); i++) {
				HashMap<String, String> spgwMap = spgwList.get(i);
				if (model.getShgw().equalsIgnoreCase(spgwMap.get("id"))) {
					model.setShjb(spgwMap.get("xh"));
					request.setAttribute("spgw", spgwMap.get("id"));
					break;
				}
			}
			
		}
		
		if(spgwList.size() > 1){
			//多审核岗位
			request.setAttribute("dspgw", "dspgw");
		}
	}
    
    /**
	 * 修改审核状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateShzt(RcswSwblForm model) throws Exception {
		return dao.updateShzt(model);
	}
	
	public List<HashMap<String,String>>getShxxInfo(RcswSwblForm model){
    	return dao.getShxxInfo(model);
    }
	
	public List<HashMap<String,Object>>getXsxxXs(RcswSwblForm model){
		List<HashMap<String,String>>xsxxzdList=getXsxxzd(model);
		List<HashMap<String,Object>>xszdList=new ArrayList<HashMap<String,Object>>();
	
		HashMap<String,String>leftMap=new HashMap<String,String>();
		HashMap<String,String>rightMap=new HashMap<String,String>();
		HashMap<String,Object>xszdMap=new HashMap<String,Object>();
		for(int i=0;i<xsxxzdList.size();i++){
			HashMap<String,String>xsxxzdMap=xsxxzdList.get(i);
			boolean blog=true;
			if("long".equalsIgnoreCase(xsxxzdMap.get("zdlx"))
					|| "textArea".equalsIgnoreCase(xsxxzdMap.get("zdlx"))){
				leftMap.put("zd", xsxxzdMap.get("zd"));
				leftMap.put("zdm", xsxxzdMap.get("zdm"));
				rightMap.put("sfxs", "no");
				leftMap.put("sfxs", "yes");
				xszdMap.put("left", leftMap);
				xszdMap.put("right", rightMap);
				xszdList.add(xszdMap);
				leftMap=new HashMap<String,String>();
				rightMap=new HashMap<String,String>();
			}else{
				if(Base.isNull(leftMap.get("sfxs"))){
					leftMap.put("zd", xsxxzdMap.get("zd"));
					leftMap.put("zdm", xsxxzdMap.get("zdm"));
					leftMap.put("sfxs", "yes");
					blog=false;
				}
				if(!Base.isNull(leftMap.get("sfxs")) 
						&& blog){
					rightMap.put("zd", xsxxzdMap.get("zd"));
					rightMap.put("zdm", xsxxzdMap.get("zdm"));
					rightMap.put("sfxs", "yes");
					xszdMap.put("left", leftMap);
					xszdMap.put("right", rightMap);
					xszdList.add(xszdMap);
					
					leftMap=new HashMap<String,String>();
					rightMap=new HashMap<String,String>();
				}
				
			}
			
		}
		return xszdList;
	}
	
	 /**
     * 获取项目设置结果集(分页)
     * @param model
     * @return
     * @throws Exception
     */
    public List<String[]>getXmszList(RcswSwblForm model) throws Exception{
    	
    	return dao.getXmszList(model);
    }
    
    /**
     * 获取项目设置表头
     * @param model
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>>getXmszTopTr(RcswSwblForm model) throws Exception{
    	
    	DAO dao=DAO.getInstance();
    	String[]en={"xmdm","xmmc","sqzq","swjssj","shjssj","sfqy","lcmc"};
    	String[]cn={"项目代码","项目名称","申请周期","事务结束时间","审核结束时间","是否启用","流程名称"};
    	
    	return dao.arrayToList(en, cn);
    }
}
