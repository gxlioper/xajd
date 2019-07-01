package xgxt.xtwh.xtwhCriterion.yhgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.base.Encrypt;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.xtwhCriterion.CriterionService;
import xgxt.xtwh.xtwhCriterion.jsgl.JsglDAO;

public class YhglService extends CriterionService{
	private YhglDAO yhglDao = new YhglDAO();
	
	/**
	 * �����û�
	 * @return
	 */
	public boolean saveYh(YhglForm model){
		return yhglDao.saveYh(model);
	}
	
	/**
	 * �޸��û�
	 */
	public boolean modiYh(YhglForm model){
		return yhglDao.modiYh(model);
	}
	
	/**
	 * ��ʼ������
	 * @return
	 */
	public boolean initMm(String[] pkValues){
		// ��ʼ������
		String mm = new Encrypt().encrypt("888888");
		boolean flag = false;
		
		if(pkValues!=null && pkValues.length>0){ // ��û��ѡ���û�����ʼ�������û� 
			flag = yhglDao.initMm(mm, pkValues);
		}else{	// ѡ�����û�����ʼ��ָ���û�
			flag = yhglDao.initMm(mm);
		}
		
		return flag;
	}
	
	/**
	 * ��ʼ��ѧ���û�
	 * @param jsdm
	 * @return
	 */
	public Map<String, String> initXs(String jsdm){
		return null;
	}
	
	/**
	 * ����û���ѯ
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> queryYhList(YhglForm model){
		String[] colList = new String[]{"szbm"};
		String[] colLikeList = new String[]{"yhm", "xm"};
		
		String jsdm = model.getJsdm();
		String sfksq = model.getSfksq();
		
		// ���Ӳ�ѯ����
		StringBuilder otherQuery = new StringBuilder();
		
		// ��Ӳ�ѯ������ɫ����
		if(StringUtils.isNotNull(jsdm)){
			otherQuery.append("and exists(select 1 from xg_xtwh_yhjsb b where b.yhm=a.yhm and b.jsdm='")
						.append(jsdm)
						.append("') ");
		}
		
		// ��Ӳ�ѯ�����Ƿ����Ȩ
		if(StringUtils.isNotNull(sfksq) && "��".equalsIgnoreCase(sfksq)){
			otherQuery.append("and exists(select 1 from xg_xtwh_yhjsb b where b.yhm=a.yhm and b.jsqx='��')");
		}else if(StringUtils.isNotNull(sfksq) && "��".equalsIgnoreCase(sfksq)){
			otherQuery.append("and not exists(select 1 from xg_xtwh_yhjsb b where b.yhm=a.yhm and b.jsqx='��')");
		}
		
		return yhglDao.queryYhList(model, colList, colLikeList, otherQuery.toString());
	}
	
	/**
	 * ��ȡ��ͷ
	 * @return
	 */
	public String[] getTopTr(String lx){
		String[] topTr = null;
		
		if("yhgl".equalsIgnoreCase(lx)){
			topTr = new String[]{"�û���", "����", "���ڲ���", "�Ƿ�����", "�ѷ����ɫ��"};
		}else if("yhqxfp".equalsIgnoreCase(lx)){
			topTr = new String[]{"�û���", "����", "���ڲ���"};
		}else if("js".equalsIgnoreCase(lx)){
			topTr = new String[]{"��ɫ��", "��ɫ����", "��ɫ������Χ", "�Ƿ�����"};
		}else if("yhjsfp".equalsIgnoreCase(lx)){
			topTr = new String[]{"�û���", "����", "���ڲ���", "�Ƿ�����", "�ѷ����ɫ��", "����Ȩ��"};
		}else if("xsBatch".equalsIgnoreCase(lx)){
			topTr = new String[]{"ѧ������", "ѧ�����", "�ѷ����ɫ��"};
		}else if("yhjs".equalsIgnoreCase(lx)){
			topTr = new String[]{"��ɫ��", "��ɫ����", "��ɫ������Χ", "�û���", "�û�����"};
		}else if("jsqx".equalsIgnoreCase(lx)){
			topTr = new String[]{"��ɫ��", "��ɫ����", "��ɫ������Χ", "����ģ����", "ӵ�в����˵�"};
		}else if("yhqx".equalsIgnoreCase(lx)){
			topTr = new String[]{"�û���", "��������", "����ģ����", "ӵ�в����˵�"};
		}
		
		return topTr;
	}
	
	/**
	 * ��ȡ�������list
	 * @param YhglForm
	 * @param user
	 * @return
	 */
	public List<HashMap<String, Object>> getFpdxList(YhglForm model,String userName) {
		List<HashMap<String, String>> gnmkList = yhglDao.getGnmkList(userName, 1);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// ����ģ��
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Lv", "1");
		map.put("aName", "Open");
		map.put("gnmkList", gnmkList);
		list.add(map);

		return list;
	}
	
	/**
	 * ��õ�������ģ��˵�
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getOneGnmkList(String userName, int lv){
		return yhglDao.getGnmkList(userName, lv);
	}
	
	/**
	 * ��ò����б�
	 * @param lv
	 * @param dm
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String lv,String dm, String userName, String yhm) {
		return yhglDao.getGnmkList(lv, dm, userName, yhm);
	}
	
	/**
	 * ��ȡ�û�
	 * @param yhm
	 * @return
	 */
	public Map<String, String> getYh(String yhm){
		return yhglDao.getYh(yhm);
	}
	
	/**
	 * ��ȡ��ɫ������ΧList
	 * @return
	 */
	public List<HashMap<String, String>> getJsczfwList() {
		return new JsglDAO().getJsczfwList();
	}
	
	/**
	 * ��ȡ��ɫ����List
	 * @return
	 */
	public List<HashMap<String, String>> getJslxList(){
		return new JsglDAO().getJslxList();
	}
	
	/**
	 * �����û�Ȩ��
	 * @return
	 */
	public boolean saveYhqx(String sqyh,String yhm, String[] btns){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<btns.length; i++){	
			String[] btn = btns[i].split("_");
			list.add(new String[]{yhm,btn[0],btn[1],sqyh}); 
		}
		
		boolean flag = yhglDao.delYhqx(yhm);
		
		if(flag){
			flag = yhglDao.saveYhqx(list);
		}
		
		return flag;
	}
	
	/**
	 * ����ɾ���û�
	 * @param pkValues
	 * @return
	 */
	public boolean delYh(String[] pkValues){
		return yhglDao.delYh(pkValues);
	}
	
	/**
	 * ��ѯ��½�û�ӵ�й����ܽ�ɫlist
	 * @param model
	 * @return
	 */
	public List<String[]> queryJsList(YhglForm model, String userName){
		String otherQuery = "and exists(select 1 from xg_xtwh_yhjsb b where b.yhm='" + userName + "' and a.jsdm=b.jsdm) ";
		
		return yhglDao.queryJsList(model, otherQuery);
	}
	
	/**
	 * ��ɫ������Ȩ�û�
	 * @param yhs
	 * @param jsIds
	 * @return
	 */
	public boolean saveJsForYh(String[] yhs,String[] jsIds){
		String[][] params = new String[yhs.length * jsIds.length][];
		String[] pkValues = new String[yhs.length * jsIds.length];
		
		int count = 0;
		
		for(int i=0; i<yhs.length; i++){
			String[] strs = yhs[i].split("_");
			
			String yhm = strs[0];
			String jsqx = "yy".equalsIgnoreCase(strs[1]) ? "��" : "��";
			
			for(int j=0; j<jsIds.length; j++){
				pkValues[count] = jsIds[j] + yhm;
				params[count] = new String[]{jsIds[j], yhm, jsqx};
				count++;
			}
		}
		
		return yhglDao.batchSq(pkValues, params);
	}
	
	/**
	 * ��ѯѧ������List
	 * @param model
	 * @return
	 */
	public List<String[]> queryXslxList(YhglForm model){
		String[] colList = new String[]{"xslxlbdm"};
		String[] colLikeList = new String[]{"xslxmc"};
		
		return yhglDao.queryXslxList(model, colList, colLikeList);
	}
	
	/**
	 * ��ɫ������Ȩ��ѧ��
	 * @param yhs
	 * @param jsIds
	 * @return
	 */
	public boolean saveJsForXs(String[] xss,String[] jsIds, String kssj, String jssj){
		String[][] params = new String[xss.length * jsIds.length][];
		String[] pkValues = new String[xss.length * jsIds.length];
		
		int count = 0;
		
		for(int i=0; i<xss.length; i++){
			String xslxdm = xss[i];
			for(int j=0; j<jsIds.length; j++){
				pkValues[count] = xslxdm + jsIds[j];
				params[count] = new String[]{xslxdm, jsIds[j], kssj, jssj};
				count++;
			}
		}
		
		return yhglDao.batchSqForXs(pkValues, params);
	}
	
	/**
	 * ����ѧ��Ȩ��
	 * @return
	 */
	public boolean saveXsqx(String sqyh,String xh, String[] btns){
		List<String[]> list = new ArrayList<String[]>();
		
		String[] pkValues = new String[btns.length];
		String[][] params = new String[btns.length][];
		
		for(int i=0; i<btns.length; i++){	
			String[] btn = btns[i].split("_");
			params[i] = new String[]{xh,btn[0],btn[1],sqyh}; 

			pkValues[i] = xh+btn[0]+btn[1];
		}
		
		// �Ƿ����
		boolean[] isExists = DAO.getInstance().checkExists("xg_xtwh_xscdqxb", "yhm||gnmkdm||cdid", pkValues);
		
		for(int i=0; i<isExists.length; i++){
			if(!isExists[i]){
				list.add(params[i]);
			}
		}
		
		return yhglDao.saveXsqx(list);
	}
	
	/**
	 * ��ȡ���еĹ���ģ��
	 * @param userName
	 * @return
	 */
	public List<GnmkModel> getAllGnmkList(String userName, String yhm){
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// һ���˵�
		List<HashMap<String, String>> gnmkListOne = yhglDao.getGnmkList(userName, 1);
		// �����˵�
		List<HashMap<String, String>> gnmkListTwo = yhglDao.getGnmkList(userName, 2);
		// �����˵�
		List<HashMap<String, String>> gnmkListThr = yhglDao.getGnmkList(userName, 3);
		// �ļ���ť
		List<HashMap<String, String>> gnmkListFou = yhglDao.getBtnList(yhm);
		
		// ��ȡ���еĹ���
		for(HashMap<String,String> mapOne : gnmkListOne){
			GnmkModel gnmkModelOne = new GnmkModel();
			String dmOne = mapOne.get("dm");
			gnmkModelOne.setGnmkdm(dmOne);
			gnmkModelOne.setGnmkmc(mapOne.get("mc"));
			
			List<GnmkModel> childListOne = setChildList(gnmkModelOne, gnmkListTwo);
			
			for(GnmkModel gnmkModelTwo : childListOne){
				// �����˵�
				List<GnmkModel> childListTwo = setChildList(gnmkModelTwo, gnmkListThr);
				
				for(GnmkModel gnmkModelThr : childListTwo){
					String dmThr = gnmkModelThr.getGnmkdm();
					// �����˵��µİ�ť
					List<HashMap<String, String>> btnList = new ArrayList<HashMap<String,String>>();
					
					for(HashMap<String, String> btn : gnmkListFou){
						if(btn.get("gnmkdm").equalsIgnoreCase(dmThr)){
							// �ж��ǽ�ɫӵ�л����û�ӵ��
							if("yes".equalsIgnoreCase(btn.get("jsyy"))){
								btn.put("disabled", "yes");
								btn.put("checked", "yes");
							}else if("yes".equalsIgnoreCase(btn.get("yhyy"))){
								btn.put("checked", "yes");
							}
							btnList.add(btn);
						}
					}
					
					gnmkModelThr.setBtnList(btnList);
					// ɾ����֪��ť����Сѭ������
					gnmkListFou.removeAll(btnList);
				}
			}

			list.add(gnmkModelOne);
		}
		
		return list;
	}
	
	/**
	 * �����ӽڵ�
	 * @param model
	 * @param list
	 * @return
	 */
	private List<GnmkModel> setChildList(GnmkModel model, List<HashMap<String, String>> list){
		List<GnmkModel> childList = new ArrayList<GnmkModel>();
		
		// ��ȡ��gnmk�µ��ӹ���ģ��
		for (HashMap<String, String> map : list){
			String dm = map.get("dm");
			if(dm.substring(0, dm.length()-2).equalsIgnoreCase(model.getGnmkdm())){
				GnmkModel gnmkModel = new GnmkModel();
				
				gnmkModel.setGnmkdm(dm);
				gnmkModel.setGnmkmc(map.get("mc"));
	
				childList.add(gnmkModel);
			}
		}
		
		list.removeAll(childList);
		
		model.setChildList(childList);
		return childList;
	}
	
	/**
	 * ��ȡ��½�û��Ĺ����ɫ
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getJsListForUserName(String userName){
		return yhglDao.getJsListForUserName(userName);
	}
	
	
	/**
	 * ��ȡָ��һ���û��Ľ�ɫ
	 * @param yhms
	 * @return
	 */
	public Map<String, List<HashMap<String, String>>> getYhJsList(String[] yhms){
		Map<String, List<HashMap<String, String>>> map = new HashMap<String, List<HashMap<String,String>>>();
		
		List<HashMap<String, String>> list = yhglDao.getYhjsList(yhms);
		
		for(HashMap<String, String> yhjsMap : list){
			String yhm = yhjsMap.get("yhm");
			
			if(map.containsKey(yhm)){
				map.get(yhm).add(yhjsMap);
			}else{
				List<HashMap<String, String>> jsList = new ArrayList<HashMap<String,String>>();
				jsList.add(yhjsMap);
				map.put(yhm, jsList);
			}
		}
		return map;
	}
}
