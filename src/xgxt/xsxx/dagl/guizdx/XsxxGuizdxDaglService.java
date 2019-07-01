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
	
	// --------------------------�������� begin---------------------------------
	
	/**
	 * ѧ��������Ϣ�����
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
	 * ��ȡ�����б���Ϣ
	 * @return 
	 */
	public List<HashMap<String,String>>getLxList(){
		
		DAO dao=DAO.getInstance();
		String []en={"001","002","003"};
		String []cn={"��������","��У����","��ҵת��"};
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ���ӵ���������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean addDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.addDadmb(model);
		
	}
	
	/**
	 * ���ӵ���������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean updateDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.updateDadmb(model);
		
	}
	
	/**
	 * ��ѯ���������������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String,String> getOnDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.getOnDadmb(model);
		
	}
	
	
	/**
	 * ��ѯ���������������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean delDadmb(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.delDadmb(model);
	}
	
	//-------------------------�������� end---------------------------
	
	
	// --------------------����������Ϣ begin---------------------
	/**
	 * ��ȡ������ѧ������Ϣ
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
	 * ������������������Ϣ
	 * @throws Exception 
	 *
	 */
	public boolean saveXsdaByCheck(HttpServletRequest request,XsxxGuizdxDaglForm form) throws Exception{
		
		XsxxGuizdxDaglModel xsxxGuizdxDaglModel=new XsxxGuizdxDaglModel();
		User user=form.getUser();
		
		String userName=user.getUserName();
	
		//�������ݲ��� �ı���
		String realTable ="xg_gzdx_xsxx_xsrxdab";
		
		//�����������
		String[] dm=form.getXsdaInfo();
		//ѧ��
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
	 * ���ݵ�����Ϣby��������
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getDaxxByLx(XsxxGuizdxDaglForm model){
		
		return dao.getDaxxByLx(model);
	}
	
	/**
	 * ����������������
	 * @return
	 * @throws Exception 
	 */
	public boolean saveXsdaByOne(XsxxGuizdxDaglForm model) throws Exception{
	
		return dao.saveXsdaByOne(model);
	}
	
	/**
	 * ��ȡ���������������
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getXsdaByOne(XsxxGuizdxDaglForm model){
		
		return dao.getXsdaByOne(model);
	
	}
	// --------------------����������Ϣ end---------------------

	/**
	 * ��ȡ��У��������Ϣ
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
	 * ������������������Ϣ
	 * @throws Exception 
	 *
	 */
	public boolean saveZxdaByCheck(HttpServletRequest request,XsxxGuizdxDaglForm form) throws Exception{
		
		XsxxGuizdxDaglModel xsxxGuizdxDaglModel=new XsxxGuizdxDaglModel();
		User user=form.getUser();
		
		String userName=user.getUserName();
	
		//�������ݲ��� �ı���
		String realTable ="xg_gzdx_xsxx_xszxqjdab";
		
		//�����������
		String[] dm=form.getXsdaInfo();
		//ѧ��
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
	 * ����������У������
	 * @return
	 * @throws Exception 
	 */
	public boolean saveZxdaByOne(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.saveZxdaByOne(model);
	}

	/**
	 * ��ȡ������У�������
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getZxdaByOne(XsxxGuizdxDaglForm model){

		return dao.getZxdaByOne(model);
	}
//	 ------------------��ҵ������ begin------------------
	/**
	 * ��ȡ��ҵ��������Ϣ
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
	 * ���ӵ���������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean addBysda(HttpServletRequest request,XsxxGuizdxDaglForm model) throws Exception{
		
		boolean blog=false;
		String[]pkValue=new String[]{model.getXh()};
		//ɾ����������
		blog=dao.delBysda(model);
		//������������
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
	 * ���ӵ���������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean updateBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.updateBysda(model);
	}
	
	/**
	 * ��ѯ���������������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String,String> getOneBysda(XsxxGuizdxDaglForm model) throws Exception{
		
		return dao.getOneBysda(model);
		
	}
	
	/**
	 * ��ѯ���������������Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean delByda(XsxxGuizdxDaglForm model) throws Exception{
		
		
		return dao.delByda(model);
		
	}
	// ---------------------��У������ end-------------------
	
	/**
	 * ��ȡ��ҵ��������Ϣ
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
	 * ������������������Ϣ
	 * @throws Exception 
	 *
	 */
	public boolean saveByzdByCheck(HttpServletRequest request,XsxxGuizdxDaglForm form) throws Exception{
		
		XsxxGuizdxDaglModel xsxxGuizdxDaglModel=new XsxxGuizdxDaglModel();
		User user=form.getUser();
		
		String userName=user.getUserName();
	
		//�������ݲ��� �ı���
		String realTable ="xg_gzdx_xsxx_xsbyzdb";
		
		//�����������
		String[] dm=form.getXsdaInfo();
		//ѧ��
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
	 * ��ȡ������У�������
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getBydaByOne(XsxxGuizdxDaglForm model){
		return dao.getBydaByOne(model);
	}
}


