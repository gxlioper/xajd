package xsgzgl.gygl.tsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xsgzgl.gygl.comm.GyglNewService;

public class TsglService extends GyglNewService{
	
	TsglDao dao = new TsglDao();
	/**
	 * ��ȡ������Ϣ���ݼ�
	 * @author zhanghui
	 */
	public ArrayList<String[]> getTsglInfoList(TsglForm myForm, HttpServletRequest request) 
		throws Exception{		
		return dao.getTsglInfoList(myForm,request);
	}

	/**
	 * �鿴������Ϣ
	 * @author zhanghui
	 */
	public HashMap<String,String> viewTsxx(String pk){
		return dao.viewTsxx(pk);
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteTsglInfo(TsglForm model) throws Exception{
		
		return dao.deleteTsglInfo(model);
	}
	
	/**
	 * ������Ϣ��ѯ �Զ��嵼��
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getTsglInfoExportList(TsglForm myForm, HttpServletRequest request) 
		throws Exception{		
		return dao.getTsglInfoExportList(myForm,request);
	}
}
