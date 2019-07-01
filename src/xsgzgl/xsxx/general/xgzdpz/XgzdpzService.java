package xsgzgl.xsxx.general.xgzdpz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;

public class XgzdpzService extends CommService{
	private XgzdpzDao dao = new XgzdpzDao();
	
	/**
	 * 输出字段分类
	 * @param list
	 * @return
	 */
	public List<HashMap<String, String>> getXgzdflList() {
		return dao.getXgzdfl();
	}
	
	/**
	 * 输出字段
	 * @param list
	 * @return
	 */
	public List<HashMap<String, String>> getXgzdList(XgzdpzForm model) {
		return dao.getXgzd(model);
	}
	
	/**
	 * 修改只读状态字段
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String getZdzd(XgzdpzForm model){
		String zdzds = "";
		List<HashMap<String, String>> map = dao.getZdzd(model);
		if(null!=map&&map.size()>0){
			for(int i=0;i<map.size();i++){
				String zd = map.get(i).get("zd");
				if(i!=map.size()-1){
					zdzds+=zd+",";
				}else{
					zdzds+=zd;
				}
			}
		}
		return zdzds;
	}
	
	/**
	 * 获取必填状态字段
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String getBtzd(XgzdpzForm model){
		String btzds = "";
		List<HashMap<String, String>> map = dao.getBtzd(model);
		if(null!=map&&map.size()>0){
			for(int i=0;i<map.size();i++){
				String zd = map.get(i).get("zd");
				if(i!=map.size()-1){
					btzds+=zd+",";
				}else{
					btzds+=zd;
				}
				
			}
		}
		return btzds;
	}
	
	/**
	 * 修改只读必填状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcXgzdsz(XgzdpzForm model) throws Exception{
		boolean flag = true;
		//先更新所有为空
		flag = dao.xgSfzdSfbt(model);
		if(!"".equals(model.getZdZd())){
			String[] zdzd = model.getZdZd();
			flag = dao.xgSfzd(model, zdzd);
		}
		if(!"".equals(model.getBtZd())){
			String[] btzd = model.getBtZd();
			flag = dao.xgSfbt(model, btzd);
		}
		return flag;
	}
	
}
