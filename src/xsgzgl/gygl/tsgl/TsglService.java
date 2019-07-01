package xsgzgl.gygl.tsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xsgzgl.gygl.comm.GyglNewService;

public class TsglService extends GyglNewService{
	
	TsglDao dao = new TsglDao();
	/**
	 * 获取退宿信息数据集
	 * @author zhanghui
	 */
	public ArrayList<String[]> getTsglInfoList(TsglForm myForm, HttpServletRequest request) 
		throws Exception{		
		return dao.getTsglInfoList(myForm,request);
	}

	/**
	 * 查看退宿信息
	 * @author zhanghui
	 */
	public HashMap<String,String> viewTsxx(String pk){
		return dao.viewTsxx(pk);
	}
	
	/**
	 * 删除退宿信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteTsglInfo(TsglForm model) throws Exception{
		
		return dao.deleteTsglInfo(model);
	}
	
	/**
	 * 退宿信息查询 自定义导出
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTsglInfoExportList(TsglForm myForm, HttpServletRequest request) 
		throws Exception{		
		return dao.getTsglInfoExportList(myForm,request);
	}
}
