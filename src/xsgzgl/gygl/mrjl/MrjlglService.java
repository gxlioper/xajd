package xsgzgl.gygl.mrjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xsgzgl.gygl.comm.GyglNewService;

public class MrjlglService extends GyglNewService{
	MrjlglDAO mrjlglDao = new MrjlglDAO();
	
	public List<HashMap<String, String>> getZbryList(){
		return mrjlglDao.getZbryList();
	}
	
	/**
	 * 每日记录查询
	 * @param model
	 * @return
	 */
	public List<String[]> mrjlQuery(MrjlglForm model){
		return mrjlglDao.mrjlQuery(model);
	}
	
	public boolean saveMrjl(MrjlglForm model){
		return mrjlglDao.saveMrjl(model);
	}
	
	public boolean updateMrjl(MrjlglForm model){
		return mrjlglDao.updateMrjl(model);
	}
	
	public boolean delMrjl(String[] pks){
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pk : pks){
			params.add(new String[]{pk});
		}
		
		return mrjlglDao.delMrjl(params);
	}
	
	public Map<String, String> getMrjl(String pk){
		return mrjlglDao.getMrjl(pk);
	}
}
