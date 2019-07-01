package xgxt.rcgl.gzdx.fsbtgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;
import xgxt.rcgl.gzdx.lxgl.LxglForm;

public class FsbtglService {
	private FsbtglDAO fsbtglDao = new FsbtglDAO();
	
	/**
	 * ��ȡ��ʳ��������ά��
	 * @return
	 */
	public List<HashMap<String, String>> getFsbtList(){
		return fsbtglDao.getFsbtList();
	}
	
	/**
	 * ��ȡ�·�List
	 * @return
	 */
	public List<HashMap<String, String>> getYfList(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		for(int i=1;i<13; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", i+"");
			map.put("mc", i + "�·�");
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		return fsbtglDao.getStuInfo(xh);
	}
	
	/**
	 * ��ȡtopMap
	 * @param mk
	 * @return
	 */
	public Map<String, Object> getTopMap(String mk){
		String[] outputs = null;
		String[] topTr = null;
		
		if("cx".equalsIgnoreCase(mk)){
			outputs = new String[]{"pkValue", "nd", "yf", "mc","btje", "xh", "bjmc", "jsr", "ffsj"};
			topTr = new String[]{"����", "���", "�·�", "������Ŀ","�������", "ѧ��", "�༶", "������", "����ʱ��"};
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("outputs", outputs);
		map.put("topTr", topTr);
		
		return map;
	}
	
	/**
	 * ��ȡ��ѯ��Ϣ
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getCxList(FsbtglForm model, User user, String[] outPutList) throws Exception {
		return fsbtglDao.getCxList(model, user, outPutList);
	}
	
	/**
	 * ���淢�Ų���
	 * @param model
	 * @return
	 */
	public boolean saveFsbt(FsbtglForm model){
		return fsbtglDao.saveFsbt(model);
	}
	
	/**
	 * ����ɾ��
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
		return fsbtglDao.batchDel(pkValues);
	}
	
	/**
	 * ��ȡ��ʳ������Ϣ
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getFsbtInfo(String pkValue){
		return fsbtglDao.getFsbtInfo(pkValue);
	}
	
	
}
