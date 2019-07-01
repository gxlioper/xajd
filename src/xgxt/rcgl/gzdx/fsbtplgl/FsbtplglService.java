package xgxt.rcgl.gzdx.fsbtplgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.form.User;
/**
 * ���ݴ�ѧ��ʳ������������
 * @author yeyipin
 */
public class FsbtplglService extends CommService{
	private FsbtplglDAO fsbtplglDao = new FsbtplglDAO();
	
	/**
	 * ��ȡ��ʳ��������ά��
	 * @return
	 */
	public List<HashMap<String, String>> getFsbtList(){
		return fsbtplglDao.getFsbtList();
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
	 * ��ȡtopMap
	 * @param mk
	 * @return
	 */
	public Map<String, Object> getTopMap(String mk){
		String[] outputs = null;
		String[] topTr = null;
		
		if("cx".equalsIgnoreCase(mk)){
			outputs = new String[]{"pkValue","xh","xm","bjmc","btny","btmc","btje","jsr","ffsj"};
			topTr = new String[]{"����","ѧ��","����","�༶����","��������","��������","�������","������","����ʱ��"};
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
	 * @throws Exception
	 */
	public List<String[]> getCxList(FsbtplglForm model, HttpServletRequest request,User user, String[] outPutList) throws Exception {
		String condition = " where 1=1 ";
		
		if(!"".equalsIgnoreCase(model.getXydm())&&model.getXydm()!=null){
			condition +="and xydm='"+model.getXydm()+"' ";
		}
		if(!"".equalsIgnoreCase(model.getZydm())&&model.getZydm()!=null){
			condition +="and zydm='"+model.getZydm()+"' ";
		}
		if(!"".equalsIgnoreCase(model.getBjdm())&&model.getBjdm()!=null){
			condition +="and bjdm='"+model.getBjdm()+"' ";
		}
		if(!"".equalsIgnoreCase(model.getXueh())&&model.getXueh()!=null){
			condition +="and xh like '%"+model.getXueh()+"%' ";
		}
		if(!"".equalsIgnoreCase(model.getXm())&&model.getXm()!=null){
			condition +="and xm like '%"+model.getXm()+"%' ";
		}
		condition += user.getQueryCondition();
		model.setCondition(condition);
		//����ǰ̨��ѯ
		request.setAttribute("searchTjstr", condition);
		return fsbtplglDao.getCxList(model,request, user, outPutList);
	}
	
	/**
	 * ���淢�Ų���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveFsbt(FsbtplglForm model,HttpServletRequest request) throws Exception{
		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhstr");
		String[] pk_xh = null;
		if(xh !=null && !"".equalsIgnoreCase(xh)){//����ѧ��
			pk_xh = new String[]{xh};
		}else if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){//���ѧ��
			pk_xh = xhstr.split("!!splitOne!!");
		}else {//���ݼ�
			pk_xh = fsbtplglDao.getXhs(searchTjstr);
		}
		model.setPk_xh(pk_xh);
		return fsbtplglDao.saveFsbt(model, user);
	}
	
	/**
	 * ����ɾ��
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
		return fsbtplglDao.batchDel(pkValues);
	}
	
	/**
	 * ��ȡ��ʳ������Ϣ
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getFsbtInfo(String pkValue){
		return fsbtplglDao.getFsbtInfo(pkValue);
	}
	/**
	 * ���Ĭ������
	 * @param string
	 * @return
	 */
	public String getMrxx(String key) {
		if("nd".equalsIgnoreCase(key)){
			return Base.currNd;
		}else if("yf".equalsIgnoreCase(key)){
			return fsbtplglDao.getMrYf();
		}else if("btdm".equalsIgnoreCase(key)){
			return fsbtplglDao.getMrbtdm();
		}
		return null;
	}
	
	/**
	 * ���ѧ����Ϣ
	 * @param xh
	 * @param request
	 * @return
	 */
	public Map<String, String> getXsxx(String xh, HttpServletRequest request) {
		return fsbtplglDao.getXsxx(xh,request);
	}
	/**
	 * ��ò�����Ϣ
	 * @param btdm
	 * @return
	 */
	public Map<String,String> getBtxx(String btdm) {
		return fsbtplglDao.getBtxx(btdm);
	}
}
