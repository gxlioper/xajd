package xsgzgl.xsxx.bzrpy.sjsz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xsgzgl.xsxx.bzrpy.BasicModel;

public class XsxxPysjService extends CommService{

	XsxxPysjDAO dao=new XsxxPysjDAO();
	
	/**
	 * ͨ�ò�ѯ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{

		return dao.getBasicList(model);
	}
	
	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model){
		
		return dao.saveInfo(model);
	}
	

	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
		
		return dao.updateInfo(model);
	}
	

	/**
	 * ���ݴ���� ����ֵ��ʽ���ֶ���Ϣ���б���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public boolean saveBatch(BasicModel model,Object saveModel) throws Exception{
		
		// �������ݲ��� �ı���
		String realTable=model.getTableName();
		
		String[] pkValue =model.getPkValue();
		
		String pk=getString(model.getPk());
		
		String[] arrzd = model.getArrayZd();
		
		String[] onezd = model.getOneZd();
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, saveModel, model.getUser());
	
	}
	
	public String getString(String[]array){
		
		StringBuilder str=new StringBuilder();
		
		for(int i=0;i<array.length;i++){
			if(i!=0){
				
				str.append(",");
			}
			str.append(array[i]);
		}
		
		return str.toString();
	}
	
	public HashMap<String,String>getBasicMap(BasicModel model){
		
		HashMap<String,String>map=new HashMap<String,String>();
		
		return dao.getBasicMap(model);
		
	}
	
}
