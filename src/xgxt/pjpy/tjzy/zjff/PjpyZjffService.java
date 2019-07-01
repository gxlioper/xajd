package xgxt.pjpy.tjzy.zjff;

import java.util.List;

import xgxt.form.SaveForm;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.BasicModel;

public class PjpyZjffService extends BasicService{
	
	
	PjpyZjffDAO dao=new PjpyZjffDAO();
	
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
		
		String[]pk=model.getPk();
		
		String[] arrzd = model.getArrayZd();
		
		String[] onezd = model.getOneZd();
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(getString(pk));
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, saveModel, model.getUser());
	
	}
	
	public String getString(String[]array){
		
		StringBuilder str=new StringBuilder();
		
		for(int i=0;i<array.length;i++){
			if(i!=0){
				
				str.append("||'!!@@!!'||");
			}
			str.append(array[i]);
		}
		
		return str.toString();
	}
	
	/**
	 * �����޸Ĺ���
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean batchUpdate(BasicModel model){
			
		return dao.batchUpdate(model);
	} 
	
	/**
	 * �޸���˽��
	 * @return
	 * @throws Exception 
	 */
	public boolean overUpdate() throws Exception{
		
		boolean blog=dao.wshUpdate();
		
		if(blog){
			blog=dao.shtgUpdate();
		}
		if(blog){
			blog=dao.shbtgUpdate();
		}
		if(blog){
			blog=dao.shzUpdate();
		}
		
		return blog;
	}
}
