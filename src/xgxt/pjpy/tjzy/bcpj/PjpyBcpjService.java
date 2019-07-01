package xgxt.pjpy.tjzy.bcpj;

import java.util.List;

import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class PjpyBcpjService extends BasicService{
	
	
	PjpyBcpjDAO dao=new PjpyBcpjDAO();
	
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
	 * 批量删除功能
	 * @param model
	 * @return
	 */
	public boolean batchDelete(BasicModel model){
		
		return dao.batchDelete(model);
	}
	
	/**
	 * 删除评奖项目审核表中的无效数据
	 * @return boolean
	 * @throws Exception 
	 * 
	 */
	public boolean deletePjxmInfo() throws Exception{
		
		
		return dao.deletePjxmInfo();
	}
}
