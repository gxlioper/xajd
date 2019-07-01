package xsgzgl.xsxx.general.xgzdpz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;

public class XgzdpzService extends CommService{
	private XgzdpzDao dao = new XgzdpzDao();
	
	/**
	 * ����ֶη���
	 * @param list
	 * @return
	 */
	public List<HashMap<String, String>> getXgzdflList() {
		return dao.getXgzdfl();
	}
	
	/**
	 * ����ֶ�
	 * @param list
	 * @return
	 */
	public List<HashMap<String, String>> getXgzdList(XgzdpzForm model) {
		return dao.getXgzd(model);
	}
	
	/**
	 * �޸�ֻ��״̬�ֶ�
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
	 * ��ȡ����״̬�ֶ�
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
	 * �޸�ֻ������״̬
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcXgzdsz(XgzdpzForm model) throws Exception{
		boolean flag = true;
		//�ȸ�������Ϊ��
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
