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
	 * ������Ŀ�����ȡ
	 * ��Ŀ���ñ���ϸ��Ϣ
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author ������
	 */
	public HashMap<String,String>getXmszb(RcswSwblForm model){
		
		return dao.getXmszb(model);
	}
	
	/**
	 * ������Ŀ�����ȡ
	 * ����Ŀ����ʾ�ֶ�
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author ������
	 */
	public List<HashMap<String,String>>getXszdList(RcswSwblForm model){
		
		return dao.getXszdList(model);
	}
	
	/**
	 * ������Ŀ�����ȡ
	 * ����Ŀ����ʾ�ķּ��ֶ�(����Ŀ�ֶ���ʾ)
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author ������
	 */
	public List<HashMap<String,String>>getFjzdxs(RcswSwblForm model){
		//����Ŀ����ʾ�ֶ�
		List<HashMap<String,String>>xszdList=getXszdList(model);
		
		//�ּ��ֶ��б�
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
	 * ������Ŀ�����ȡ
	 * ����Ŀ����ʾ�ķּ��ֶ�(����Ŀ�ֶ���ʾ)
	 * method getXmszb
	 * @param model
	 * @return HashMap<String,String>
	 * @author ������
	 */
	public List<HashMap<String,String>>getSwsqzd(RcswSwblForm model){
		//����Ŀ����ʾ�ֶ�
		List<HashMap<String,String>>xszdList=getXszdList(model);
		
		//�ּ��ֶ��б�
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
		//����Ŀ����ʾ�ֶ�
		List<HashMap<String,String>>xszdList=getXszdList(model);
		
		//�ּ��ֶ��б�
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
	 * ��ȡ�����б����ݣ����䣩
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
					// TODO �Զ����� catch ��
					e.printStackTrace();
					return false;
				} 
			}
		}
		
		return true;
		
	}
	
	/**
	 * ����������������������Ϣ���棩
	 * @param model
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSwblSq(RcswSwblForm model) throws Exception{
		List<HashMap<String,String>>swsqzdList=getSwsqzd(model);
		return dao.saveSwblSq(swsqzdList, model);
	}
	
	
	/**
	 * ��������(����Ŀ)
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
	 * ��ȡ�����ѯ��Ϣ
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
		String[]cn={"ѧ��","ѧ��","���","ѧ��","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","����ʱ��"};
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	public List<HashMap<String,String>>getShTopTr(RequestForm rForm){
		
		DAO dao=DAO.getInstance();
		String[]en={"xn","xqmc","xh","xm","xb","nj","xymc","zymc","bjmc","sqsj","shzt"};
		String[]cn={"ѧ��","ѧ��","ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","����ʱ��","���״̬"};
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ɾ�����������Ϣ
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
	 * �޸�������Ϣ
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
     * ��˽�����Ϣ
     * @param model
     * @return
     * @throws Exception
     */
    public List<String[]> getSpxxInfo(RcswSwblForm model) throws Exception{
    	
    	return dao.getSpxxInfo(model);
    }
    
    /**
     * �����û�����ȡ��ǰģ��
     * ���漰�����û�����������λ
     * @param model
     * @return List<HashMap<String,String>>
     */
    public List<HashMap<String,String>>getUserSpgw(RcswSwblForm model){

    	return dao.getUserSpgw(model);
    }
    
    /**
     * ��˸�λ����
     * @param request
     * @param model
     * @param spgwList
     */
    public void shgwKz(HttpServletRequest request, RcswSwblForm model,
			List<HashMap<String, String>> spgwList) {
		
    	// �жϵ�½�û��ڼ�����˸�λ��
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
			//����˸�λ
			request.setAttribute("dspgw", "dspgw");
		}
	}
    
    /**
	 * �޸����״̬
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
     * ��ȡ��Ŀ���ý����(��ҳ)
     * @param model
     * @return
     * @throws Exception
     */
    public List<String[]>getXmszList(RcswSwblForm model) throws Exception{
    	
    	return dao.getXmszList(model);
    }
    
    /**
     * ��ȡ��Ŀ���ñ�ͷ
     * @param model
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>>getXmszTopTr(RcswSwblForm model) throws Exception{
    	
    	DAO dao=DAO.getInstance();
    	String[]en={"xmdm","xmmc","sqzq","swjssj","shjssj","sfqy","lcmc"};
    	String[]cn={"��Ŀ����","��Ŀ����","��������","�������ʱ��","��˽���ʱ��","�Ƿ�����","��������"};
    	
    	return dao.arrayToList(en, cn);
    }
}
