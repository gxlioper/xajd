package xgxt.pjpy.tjzy.zjff;

import java.util.List;

import xgxt.form.SaveForm;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.BasicModel;

public class PjpyZjffService extends BasicService{
	
	
	PjpyZjffDAO dao=new PjpyZjffDAO();
	
	/**
	 * 通用查询方法
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getBasicList(BasicModel model) throws Exception{

		return dao.getBasicList(model);
	}
	
	/**
	 * 根据传入得 键、值形式表字段信息进行保存
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean saveInfo(BasicModel model){
		
		return dao.saveInfo(model);
	}
	

	/**
	 * 根据传入得 键、值形式表字段信息进行保存
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(BasicModel model){
		
		return dao.updateInfo(model);
	}
	

	/**
	 * 根据传入得 键、值形式表字段信息进行保存
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public boolean saveBatch(BasicModel model,Object saveModel) throws Exception{
		
		// 进行数据操作 的表名
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
	 * 批量修改功能
	 * @param valueMap
	 * @return
	 * @throws Exception 
	 */
	public boolean batchUpdate(BasicModel model){
			
		return dao.batchUpdate(model);
	} 
	
	/**
	 * 修改审核结果
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
