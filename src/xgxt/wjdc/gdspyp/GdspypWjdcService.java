package xgxt.wjdc.gdspyp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;

public class GdspypWjdcService {
	private GdspypWjdcDAO wjdcDao = new GdspypWjdcDAO();
	
	/**
	 * ��ȡ��ͷ
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		if("jgfx".equalsIgnoreCase(mk)){
			topTr = new String[]{"ѧ��", "����",Base.YXPZXY_KEY, "רҵ", "�༶", "�ܷ�"};
		}
		
		return topTr;
	}
	
	/**
	 * ���������ѯ
	 * @param model
	 * @return
	 */
	public List<String[]> jgfxQuery(GdspypWjdcForm model){
		return wjdcDao.jgfxQuery(model);
	}
	
	public boolean savePynr(GdspypWjdcForm model){
		boolean flag = false;
		flag = wjdcDao.delPynr(model);
		
		if(flag){
			flag = wjdcDao.savePynr(model);
		}
		return flag;
	}
	
	public Map<String, String> getJgfxOne(String pk){
		return wjdcDao.getJgfxOne(pk);
	}
	
	public boolean delJgfx(String[] pks){
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pk : pks){
			params.add(new String[]{pk});
		}
		
		return wjdcDao.delJgfx(params);
	}
	
	/**
	 * ��ȡ�������ά��
	 * @return
	 */
	public List<HashMap<String, String>> getPy(){
		return wjdcDao.getPy();
	}
	
		/**
	 * ��ȡ����������Ŀ��
	 * @return
	 */
	public List<HashMap<String,String>> getMslbtmb(){
		return wjdcDao.getMslbtmb();
	}
	
	public String saveWjhd(HttpServletRequest request) throws Exception{
		String userName=request.getSession().getAttribute("userName").toString();
		String zfh=null;//�����
		String zbf=null;//���
		ArrayList<String> sql=new ArrayList<String>();
		for(int i=1;i<100;i++){
			zfh=request.getParameter("zfh_"+i);
			zbf=request.getParameter("zbf_"+i);
			if(!Base.isNull(zfh)&&!Base.isNull(zbf)){
				sql.add("insert into xg_wjdc_gdspyp_mslbxsdab values('"+userName+"','"+i+"','"+zfh+"','"+zbf+"')");
			}
		}
		if(sql.size()==0){
			return "û�����ݿɱ��棡";
		}
		boolean b=wjdcDao.saveWjhd(sql.toArray(new String[]{}));
		if(b){
			return "����ɹ���";
		}else{
			return "����ʧ�ܣ�";
		}
	}
	
	/**
	 * ��ȡ�ʾ�ش��
	 * @param userName
	 * @return
	 */
	public List<HashMap<String,String>> getWjhdda(String userName){
		return wjdcDao.getWjhdda(userName);
	}
	
	/**
	 * ��ȡѧ���ش������Ϣ
	 * @param userName
	 * @return
	 */
	public List<Map<String, String>> getHdInfoList(String userName){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		//�ʾ�����
		List<HashMap<String, String>> wjstList = getMslbtmb();
		//�ʾ��
		List<HashMap<String, String>> wjdaList = getWjhdda(userName);
		
		for(Map<String, String> wjstMap : wjstList){
			String stId = wjstMap.get("id");
			
			for(Map<String, String> wjdaMap : wjdaList){
				if(stId.equalsIgnoreCase(wjdaMap.get("id"))){
					wjstMap.put("zfh", wjdaMap.get("zfh"));
					wjstMap.put("zbf", wjdaMap.get("zbf"));
					wjstMap.put("fs", wjdaMap.get("fs"));
					
					break;
				}
			}
			
			list.add(wjstMap);
		}
		
		return list;
	}
}
