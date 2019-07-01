package xgxt.pjpy.mjxy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.mjxy.jxj.MjxyJxjService;

public class PjpyMjxyService {
	/**
	 * ��ȡ��ѧ�����������б�
	 * 
	 */
	public HashMap<String,String> getJxjTjList(String xh,String jxjlb){
		PjpyMjxyDao pjpyMjxyDao=new PjpyMjxyDao();
		PjpyMjxyService service=new PjpyMjxyService();
		List<HashMap<String,String>>list= pjpyMjxyDao.getJxjTjList();
		HashMap<String,String>tjMap=new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			HashMap<String,String>hashMap=list.get(i);
				//���������
			if("bjgkms".equals(hashMap.get("tjzd"))){
				tjMap.put("bjgkms",pjpyMjxyDao.checkBjgkms(xh,jxjlb));
				//���ִ���
			}else if("cfcs".equals(hashMap.get("tjzd"))){
				tjMap.put("cfcs",pjpyMjxyDao.checkCfcs(xh,jxjlb));
				//�۲�����
			}else if("pmbl".equals(hashMap.get("tjzd"))){
				tjMap.put("pmbl",service.getZhfpm(xh,jxjlb));
				//���������
			}else if("tydb".equals(hashMap.get("tjzd"))){
				tjMap.put("tydb",pjpyMjxyDao.checkTycj(xh,jxjlb));
			}
		}
		return tjMap;
	}
	
	
	public String getZhfpm(String xh,String jxjlb){
		MjxyJxjService service=new MjxyJxjService();
		PjpyMjxyDao pjpyMjxyDao=new PjpyMjxyDao();
		//רҵ��������
		float zyrs=Float.parseFloat(service.getZyrs(xh));
		if("false".equals(pjpyMjxyDao.getZcfpm(xh))){
			return "û���ҵ���ѧ�����۲�������Ϣ��";
		}else{
			float zcpm=Float.parseFloat(pjpyMjxyDao.getZcfpm(xh));
			float pmbl=zcpm/zyrs*100;
			return pjpyMjxyDao.checkZcbl(pmbl,jxjlb);
		}
	}
	
	//����
	public String runPro(){
		DAO dao=DAO.getInstance();
		String sql="{call pro_mjxy_zyxxcj()}";
		boolean blog=false;
		try {
			blog= dao.runProcuder(sql, new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(blog){
				return "����ɹ�";
			}else{
				return "����ʧ��";
			}
		}
	}
	
	
	/**
	 * �۲����������
	 * 
	 */
	public HashMap<String,String> getZcCjPm(String xh,String xn){
		PjpyMjxyDao pjpyMjxyDao=new PjpyMjxyDao();
		List<HashMap<String,String>>list=pjpyMjxyDao.getZcCjPm(xh, xn);
		HashMap<String,String>hashMap=new HashMap<String,String>();
		if(list.size()>0){
			hashMap=list.get(0);
			return hashMap;
		}else{
			hashMap.put("fs", "0");
			hashMap.put("pm", null);
			return hashMap;
		}
	}

}


