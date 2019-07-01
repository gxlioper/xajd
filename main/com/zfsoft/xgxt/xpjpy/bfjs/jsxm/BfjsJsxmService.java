/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-22 ����02:45:52 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.jsxm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszModel;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszDao;
import com.zfsoft.xgxt.xpjpy.bfjs.fswh.BfjsFswhDao;
import com.zfsoft.xgxt.xpjpy.bfjs.fswh.BfjsFswhModel;




public class BfjsJsxmService extends SuperServiceImpl<BfjsJsxmModel, BfjsJsxmDao> {
	
	public static final String XMLX_PLUS = "1";//�ӷ�
	public static final String XMLX_MINUS = "2";//����
	public static final String XMLX_DEFAULT = "3";//Ĭ�Ϸ�
	private static final String DEFAULT_MAX = "100";//Ĭ������
	private static final String DEFAULT_MIN = "0";//Ĭ����С��
	
	public static final String XMJB_QT = "0";//ȫ��
	public static final String XMJB_NJ = "1";//�꼶
	public static final String XMJB_YX = "2";//Ժϵ
	
	private static final String MAX_XMDM = "N";
	
	private BfjsJsxmDao dao = new BfjsJsxmDao();
	
	public BfjsJsxmService(){
		super.setDao(dao);
	}
	
	public boolean runInsert(BfjsJsxmModel t) throws Exception {

		String uuid = UniqID.getInstance().getUniqIDHash();
		
		t.setXmdm(uuid);
		boolean result = super.runInsert(t);
		
		if (result){
			//ɾ��������Ŀ���۲����
			delJsfs(t.getFjdm());
			
			//��Ŀ����ΪĬ�Ϸ�
			if (XMLX_DEFAULT.equals(t.getXmlx())){
				BfjsFswhDao fswhDao = new BfjsFswhDao();
				result =fswhDao.insertDefaultJsxmf(t);
			}
			
			
		}
		return result;
	
	}
	public boolean runUpdate(BfjsJsxmModel t) throws Exception {

		boolean result = super.runUpdate(t);
		
		if (result){
			
			//��Ŀ����ΪĬ�Ϸ�
			if (XMLX_DEFAULT.equals(t.getXmlx())){
				BfjsFswhDao fswhDao = new BfjsFswhDao();
				result =fswhDao.updateDefaultJsxmf(t);
			}
		}
		return result;
	
	}
	/**
	 * 
	 * @����:��ѯ������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:31:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * Map<String,List<HashMap<String,String>>> �������� 
	 * @throws
	 */
	public Map<String,List<HashMap<String,String>>> getBfjsJsxm() throws Exception{
		BfjsCsszDao csszDao = new BfjsCsszDao();
		BfjsCsszModel  csszModel = csszDao.getModel();
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		List<HashMap<String,String>> BfjsJsxmList = dao.getBfjsJsxmList(xn, xq);
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		for (HashMap<String,String> map : BfjsJsxmList){
			String key = map.get("fjdm");
			
			List<HashMap<String,String>> list = resultMap.get(key);
			
			if (list == null){
				list = new ArrayList<HashMap<String,String>>();
			}
			
			list.add(map);
			
			resultMap.put(key, list);
		}
		
		return resultMap;
	}
	
	
	/**
	 * 
	 * @����:��ʼ��������Ŀ�ṹ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:31:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pdzq
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void initBfjsJsxmList(String pdzq) throws Exception{
		
		String xn = null;
		String xq = null;
		
		if (!StringUtil.isNull(pdzq)){
			String[] zqArray = pdzq.split("#");
			xn = zqArray[0];
			xq = zqArray[1];
		}
		
		//�жϵ�ǰ�����Ƿ��о�����Ŀ������� return;
		boolean isHave = !dao.getBfjsJsxmList(xn, xq).isEmpty();
		
		if (isHave){
			return ;
		}
		
		//�ж�ϵͳ���Ƿ��г�ʼ��������Ŀ
		boolean isHaveBfjsJsxm = dao.getBfjsJsxmCount() > 0;
		//����У����������ڻ����
		if (isHaveBfjsJsxm){
			//����������ڵľ�����Ŀ�ṹ
			copyBfjsJsxm(xn, xq);
		} else {
			//���û�У�����ϵͳ�ṹ
			initBfjsJsxm(xn, xq);
		}
		
		//��ѧ�꾺����Ŀ������ѧ�꾺����Ŀ�ṹ
		String maxXq = dao.getMaxJsxq(xn);
		
//		if(BfjsCsszService.getJszq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(xq))){
//			xnBfjsJsxm(xn);
//		}
		
	}
	
	

	//���ƾ�����Ŀ��Ŀ
	private void copyBfjsJsxm(String xn, String xq) throws Exception{
		
		//������ڵľ�����Ŀ��Ŀ�ṹ
		List<HashMap<String,String>> zjzqBfjsJsxm = null;
		
		//ѧ�ھ�����Ŀ
		if (BfjsCsszService.getDfzq()){
			zjzqBfjsJsxm = dao.getZjzqBfjsJsxm(true);
		} else {
			zjzqBfjsJsxm = dao.getZjzqBfjsJsxm(false);
		}
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//��������ڵ���Ŀ�ṹ ��װ�� key:������Ŀ���� value������Ŀ��
		for (HashMap<String,String> map : zjzqBfjsJsxm){
			String key = map.get("fjdm");
			
			List<HashMap<String,String>> list = resultMap.get(key);
			
			if (list == null){
				list = new ArrayList<HashMap<String,String>>();
			}
			
			list.add(map);
			
			resultMap.put(key, list);
		}
		
		//�����µ���Ŀ�ṹ
		UniqID uID = UniqID.getInstance();
		String jszfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] jszf = new String[]{jszfXmdm,xn,xq,yjxm.get("xmmc"),"N",yjxm.get("xmlx"),yjxm.get("qzbl"),yjxm.get("mrfs"),DEFAULT_MAX,DEFAULT_MIN,""};
		params.add(jszf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//������Ŀ
			String[] ejxm = new String[]{ejxmdm,
										 xn,xq,
										 ejxmMap.get("xmmc"),
										 jszfXmdm,
										 ejxmMap.get("xmlx"),
										 ejxmMap.get("qzbl"),
										 ejxmMap.get("mrfs"),
										 ejxmMap.get("zdfz"),
										 ejxmMap.get("zxfz"),
										 ejxmMap.get("pfsm"),
										 
								};
			
			List<HashMap<String,String>> sjxmList = resultMap.get(ejxmMap.get("xmdm"));
			//������Ŀ
			if (sjxmList != null && !sjxmList.isEmpty()){
				for (HashMap<String,String> sjxmMap : sjxmList){
					String[] sjxm = new String[]{uID.getUniqIDHash(),
												 xn,xq,
												 sjxmMap.get("xmmc"),
												 ejxmdm,
												 sjxmMap.get("xmlx"),
												 sjxmMap.get("qzbl"),
												 sjxmMap.get("mrfs"),
												 sjxmMap.get("zdfz"),
												 sjxmMap.get("zxfz"),
												 sjxmMap.get("pfsm"),
											};
					params.add(sjxm);
				}
			}
			
			params.add(ejxm);
		}
		dao.initBfjsJsxmList(params);
	
	
	}
	
	//����ѧ�꾺����Ŀ��Ŀ
	private void xnBfjsJsxm(String xn) throws Exception{
		
		//������ڵľ�����Ŀ��Ŀ�ṹ
		List<HashMap<String,String>> zjzqBfjsJsxm = dao.getXnBfjsJsxmForXn(xn);
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//��������ڵ���Ŀ�ṹ ��װ�� key:������Ŀ���� value������Ŀ��
		for (HashMap<String,String> map : zjzqBfjsJsxm){
			String key = map.get("fjdm");
			
			List<HashMap<String,String>> list = resultMap.get(key);
			
			if (list == null){
				list = new ArrayList<HashMap<String,String>>();
			}
			
			list.add(map);
			
			resultMap.put(key, list);
		}
		
		//�����µ���Ŀ�ṹ
		UniqID uID = UniqID.getInstance();
		String jszfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] jszf = new String[]{jszfXmdm,xn,BfjsCsszService.XQKG,yjxm.get("xmmc"),"N","",yjxm.get("qzbl"),yjxm.get("mrfs"),"",DEFAULT_MAX,DEFAULT_MIN};
		params.add(jszf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//������Ŀ
			String[] ejxm = new String[]{ejxmdm,
										 xn,BfjsCsszService.XQKG,
										 ejxmMap.get("xmmc"),
										 jszfXmdm,
										 ejxmMap.get("xmlx"),
										 ejxmMap.get("qzbl"),
										 ejxmMap.get("mrfs"),
										 ejxmMap.get("jktb"),
										 ejxmMap.get("zdfs"),
										 ejxmMap.get("zxfs")
								};
			
			List<HashMap<String,String>> sjxmList = resultMap.get(ejxmMap.get("xmdm"));
			//������Ŀ
			if (sjxmList != null && !sjxmList.isEmpty()){
				for (HashMap<String,String> sjxmMap : sjxmList){
					String[] sjxm = new String[]{uID.getUniqIDHash(),
												 xn,BfjsCsszService.XQKG,
												 sjxmMap.get("xmmc"),
												 ejxmdm,
												 sjxmMap.get("xmlx"),
												 sjxmMap.get("qzbl"),
												 sjxmMap.get("mrfs"),
												 sjxmMap.get("jktb"),
												 sjxmMap.get("zdfs"),
												 sjxmMap.get("zxfs")
											};
					params.add(sjxm);
				}
			}
			
			params.add(ejxm);
		}
		if(dao.delXnBfjsJsxm(xn)){
			dao.initBfjsJsxmList(params);
		}
		
	}
	
	
	//ϵͳ��һ�γ�ʼ��������Ŀ��Ŀ�ṹ
	private void initBfjsJsxm(String xn, String xq) throws Exception {
		//��һ����Ŀ
		UniqID uID = UniqID.getInstance();
		String jszfXmdm = uID.getUniqIDHash();
		
		String[] jszf = new String[]{jszfXmdm,xn,xq,"������Ŀ�ܷ�","N","","100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		String[] dyf = new String[]{uID.getUniqIDHash(),xn,xq,"������",jszfXmdm,XMLX_PLUS,"100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		String[] zyf = new String[]{uID.getUniqIDHash(),xn,xq,"������",jszfXmdm,XMLX_PLUS,"100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		String[] tyf = new String[]{uID.getUniqIDHash(),xn,xq,"������",jszfXmdm,XMLX_PLUS,"100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		
		List<String[]> params = new ArrayList<String[]>();
		
		params.add(jszf);
		params.add(dyf);
		params.add(zyf);
		params.add(tyf);
		
		//��ʼ��������Ŀ��Ŀ�ṹ
		dao.initBfjsJsxmList(params);
		
		
		
	}
	
	
	/**
	 * 
	 * @����:ɾ��������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:33:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delBfjsJsxm(String xmdm){
		
		try {
			
			Map<String, String> xmInfo = dao.getBfjsJsxm(xmdm);
			boolean result = dao.delBfjsJsxm(xmdm) > 0;
			
			if (result){
				//ɾ�� ��Ŀ��Ӧ�ľ�����Ŀ����
				delJsfs(xmdm);
				
			}
			

			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

private boolean delJsfs(String xmdm) throws Exception{
		
	BfjsCsszDao csszDao = new BfjsCsszDao();
	BfjsCsszModel csszModel = csszDao.getModel();
		
	BfjsFswhDao fswhDao = new BfjsFswhDao();
		
		return fswhDao.delBfjsFswh(csszModel.getXn(),csszModel.getXq(),xmdm);
	}
	
	public List<HashMap<String,String>> getAllowEditJsfxm(){
		return dao.getAllowEditJsfxm();
	}
	
	
	/**
	 * 
	 * @����:���Ӽ���Ŀ�ľ�����Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:33:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getNoChildJsfxm(){
		return dao.getNoChildJsfxm();
	}
	
	
	/**
	 * 
	 * @����:���༶��ѯ��¼�뾺����Ŀ��������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:34:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditBfjsJsxmByBjdm() throws Exception{
		
		BfjsCsszDao BfjsCsszDao = new BfjsCsszDao();
		BfjsCsszModel csszModel = BfjsCsszDao.getModel();
		
	
		
		return dao.getNoChildJsfxm();
	}
	
	
	
	
	
	/**
	 * 
	 * @����: ��ѯǰ����������Ŀ��Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:34:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getFirstAndSecondBfjsJsxm(BfjsFswhModel t){
		return dao.getFirstAndSecondBfjsJsxm(t);
	}
	
	
	
	public List<HashMap<String,String>> getFirstAndSecondBfjsJsxm(String xn, String xq){
		return dao.getFirstAndSecondBfjsJsxm(xn,xq);
	}
	
	
	/**
	 * 
	 * @����:��ѯ��ǰ���ڵľ�����Ŀ��Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:35:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentZfxm(){
		
		return dao.getCurrentZfxm();
	}
	


	/**
	 * 
	 * @����:��ǰ���ڵ�������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-1 ����10:35:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentBfjsJsxmList() throws Exception{
		BfjsCsszDao BfjsCsszDao = new BfjsCsszDao();
		BfjsCsszModel csszModel = BfjsCsszDao.getModel();
		
		return dao.getBfjsJsxmList(csszModel.getXn(), csszModel.getXq());
	}
	

	
	/**
	 * 
	 * @����: ��ѯ�ɵ�����ϸ�����ľ�����Ŀ��Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-27 ����03:34:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,Object>> getBfjsJsxmListByXxbl(){
		//��ѯ������ϸ�����ľ�����Ŀ��Ŀ
		List<HashMap<String,String>> BfjsJsxmList = dao.getBfjsJsxmListByXxbl();
		
		List<HashMap<String,Object>>  resultList = new ArrayList<HashMap<String,Object>>();
		
		/*
		 * ���������ϸ������Ŀ�ṹ
		 */
		for (HashMap<String,String> map : BfjsJsxmList){
			HashMap<String,Object> resultMap = new HashMap<String, Object>();
			
			if (MAX_XMDM.equals(map.get("ffjdm"))){
				
				String xmdm = map.get("xmdm");
				
				resultMap.put("xmdm", xmdm);
				resultMap.put("xmmc", map.get("xmmc"));
				resultMap.put("name", map.get("name"));
				resultMap.put("qzbl", map.get("qzbl"));
				List<HashMap<String,String>> sjxmList = new ArrayList<HashMap<String,String>>();
				
				for (HashMap<String,String> minMap : BfjsJsxmList){
					
					if (xmdm.equals(minMap.get("fjdm"))){
						sjxmList.add(minMap);
					}
				}
				
				resultMap.put("sjxmList", sjxmList);
				resultList.add(resultMap);
			}
		}
		
		return resultList;
	}


	/**
	 * 
	 * @����:�ж���Ŀ�����Ƿ����
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-22 ����01:51:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean xmmcExist(BfjsJsxmModel model) {
		
		boolean flag = false;
		
		String count = dao.xmmcExist(model);
		
		if(!"0".equalsIgnoreCase(count)){
			flag = true;
		}
		
		return flag;
	}


}
