package xgxt.pjpy.tjzy.bcpj;

import java.util.List;

import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;

public class PjpyBcpjService extends BasicService{
	
	
	PjpyBcpjDAO dao=new PjpyBcpjDAO();
	
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
	 * ����ɾ������
	 * @param model
	 * @return
	 */
	public boolean batchDelete(BasicModel model){
		
		return dao.batchDelete(model);
	}
	
	/**
	 * ɾ��������Ŀ��˱��е���Ч����
	 * @return boolean
	 * @throws Exception 
	 * 
	 */
	public boolean deletePjxmInfo() throws Exception{
		
		
		return dao.deletePjxmInfo();
	}
}
