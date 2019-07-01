/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-22 ����02:45:52 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcxm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲���Ŀ 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-22 ����02:45:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcxmService extends SuperServiceImpl<ZcxmModel, ZcxmDao> {
	
	public static final String XMLX_PUSH = "1";//�ӷ�
	public static final String XMLX_REMOVE = "2";//����
	public static final String XMLX_DEFAULT = "3";//Ĭ�Ϸ�
	private static final String DEFAULT_MAX = "100";//Ĭ������
	private static final String DEFAULT_MIN = "0";//Ĭ����С��
	
	public static final String XMJB_QT = "0";//ȫ��
	public static final String XMJB_NJ = "1";//�꼶
	public static final String XMJB_YX = "2";//Ժϵ
	
	private static final String MAX_XMDM = "N";
	
	private ZcxmDao dao = new ZcxmDao();
	
	public ZcxmService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�۲���Ŀ�ṹ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����06:48:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * Map<String,List<HashMap<String,String>>> ��������
	 */
	public Map<String,List<HashMap<String,String>>> getZcxm() throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		List<HashMap<String,String>> zcxmList = dao.getZcxmList(xn, xq);
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		for (HashMap<String,String> map : zcxmList){
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
	 * @����: ��ʼ���۲���Ŀ�ṹ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-22 ����05:12:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public void initZcxmList(String pjzq) throws Exception{
		
		String xn = null;
		String xq = null;
		
		if (!StringUtil.isNull(pjzq)){
			String[] zqArray = pjzq.split("#");
			xn = zqArray[0];
			xq = zqArray[1];
		}
		
		//�жϵ�ǰ�����Ƿ����۲�ṹ������� return;
		boolean isHave = !dao.getZcxmList(xn, xq).isEmpty();
		
		if (isHave){
			return ;
		}
		
		//�ж�ϵͳ���Ƿ��г�ʼ���۲���Ŀ
		boolean isHaveZcxm = dao.getZcxmCount() > 0;
		//����У����������ڻ����
		if (isHaveZcxm){
			//����������ڵ��۲�ṹ
			copyZcxm(xn, xq);
		} else {
			//���û�У�����ϵͳ�ṹ
			initZcxm(xn, xq);
		}
		
		//��ѧ���۲⣬����ѧ���۲�ṹ
		String maxXq = dao.getMaxZcxq(xn);
		
		if(CsszService.getZczq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(xq))){
			xnZcxm(xn);
		}
		
	}
	
	

	//�����۲���Ŀ
	private void copyZcxm(String xn, String xq) throws Exception{
		
		//������ڵ��۲���Ŀ�ṹ
		List<HashMap<String,String>> zjzqZcxm = null;
		
		//ѧ���۲�
		if (CsszService.getZczq()){
			zjzqZcxm = dao.getZjzqZcxm(true);
		} else {
			zjzqZcxm = dao.getZjzqZcxm(false);
		}
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//��������ڵ���Ŀ�ṹ ��װ�� key:������Ŀ���� value������Ŀ��
		for (HashMap<String,String> map : zjzqZcxm){
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
		String zczfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] zczf = new String[]{zczfXmdm,xn,xq,yjxm.get("xmmc"),"N","",yjxm.get("qzbl"),yjxm.get("mrfs"),"",DEFAULT_MAX,DEFAULT_MIN};
		params.add(zczf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//������Ŀ
			String[] ejxm = new String[]{ejxmdm,
										 xn,xq,
										 ejxmMap.get("xmmc"),
										 zczfXmdm,
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
												 xn,xq,
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
		dao.initZcxmList(params);
		
		//��ʼ�����꼶��Ժϵ��Ӧ����ϸ����
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		if (XMJB_NJ.equals(csszModel.getZcxmjb())){
			dao.initXxblByNj();
		}
		
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			dao.initXxblByXy();
		}
	}
	
	//����ѧ���۲���Ŀ
	private void xnZcxm(String xn) throws Exception{
		
		//������ڵ��۲���Ŀ�ṹ
		List<HashMap<String,String>> zjzqZcxm = dao.getXnzcxmForXn(xn);
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//��������ڵ���Ŀ�ṹ ��װ�� key:������Ŀ���� value������Ŀ��
		for (HashMap<String,String> map : zjzqZcxm){
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
		String zczfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] zczf = new String[]{zczfXmdm,xn,CsszService.XQKG,yjxm.get("xmmc"),"N","",yjxm.get("qzbl"),yjxm.get("mrfs"),"",DEFAULT_MAX,DEFAULT_MIN};
		params.add(zczf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//������Ŀ
			String[] ejxm = new String[]{ejxmdm,
										 xn,CsszService.XQKG,
										 ejxmMap.get("xmmc"),
										 zczfXmdm,
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
												 xn,CsszService.XQKG,
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
		if(dao.delXnZcxm(xn)){
			dao.initZcxmList(params);
		}
		
	}
	
	
	//ϵͳ��һ�γ�ʼ���۲���Ŀ�ṹ
	private void initZcxm(String xn, String xq) throws Exception {
		//��һ����Ŀ
		UniqID uID = UniqID.getInstance();
		String zczfXmdm = uID.getUniqIDHash();
		
		String[] zczf = new String[]{zczfXmdm,xn,xq,"�۲��ܷ�","N","","100","0","",DEFAULT_MAX,DEFAULT_MIN};
		String[] dyf = new String[]{uID.getUniqIDHash(),xn,xq,"������",zczfXmdm,XMLX_PUSH,"100","0","",DEFAULT_MAX,DEFAULT_MIN};
		String[] zyf = new String[]{uID.getUniqIDHash(),xn,xq,"������",zczfXmdm,XMLX_PUSH,"100","0","",DEFAULT_MAX,DEFAULT_MIN};
		String[] tyf = new String[]{uID.getUniqIDHash(),xn,xq,"������",zczfXmdm,XMLX_PUSH,"100","0","",DEFAULT_MAX,DEFAULT_MIN};
		
		List<String[]> params = new ArrayList<String[]>();
		
		params.add(zczf);
		params.add(dyf);
		params.add(zyf);
		params.add(tyf);
		
		//��ʼ���۲���Ŀ�ṹ
		dao.initZcxmList(params);
		//��ʼ�����꼶��Ժϵ��Ӧ����ϸ����
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		if (XMJB_NJ.equals(csszModel.getZcxmjb())){
			dao.initXxblByNj();
		}
		
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			dao.initXxblByXy();
		}
	}
	
	
	/**
	 * 
	 * @����: ɾ�� �۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����10:41:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * boolean ��������
	 */
	public boolean delZcxm(String xmdm){
		
		try {
			
			Map<String, String> xmInfo = dao.getZcxm(xmdm);
			boolean result = dao.delZcxm(xmdm) > 0;
			
			if (result){
				//ɾ�� ��Ŀ��Ӧ���۲����
				delZcfs(xmdm);
				//ɾ�� ��Ŀ��Ӧ�ĸ��꼶��Ժϵ��ϸ����
				dao.delXxbl(xmdm);
			}
			
			//��ѧ���۲⣬����ѧ���۲�ṹ
			
			String maxXq = dao.getMaxZcxq(xmInfo.get("xn"));
			
			if(CsszService.getZczq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(xmInfo.get("xq")))){
				xnZcxm(xmInfo.get("xn"));
			} 
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	/*
	 * 
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	public boolean runInsert(ZcxmModel t) throws Exception {

		String uuid = UniqID.getInstance().getUniqIDHash();
		
		t.setXmdm(uuid);
		boolean result = super.runInsert(t);
		
		if (result){
			//ɾ��������Ŀ���۲����
			delZcfs(t.getFjdm());
			
			//��Ŀ����ΪĬ�Ϸ�
			if (XMLX_DEFAULT.equals(t.getXmlx())){
				return result;
			}
			
			//----���ݲ������� ������ϸ����----
			CsszDao csszDao = new CsszDao();
			CsszModel csszModel = csszDao.getModel();
			
			//�۲���Ŀ����Ϊ�꼶
			if (XMJB_NJ.equals(csszModel.getZcxmjb())){
				dao.insertZcblByNj(t);
			}
			
			//�۲���Ŀ����ΪԺϵ
			if (XMJB_YX.equals(csszModel.getZcxmjb())){
				dao.insertZcblByXy(t);
			}
		}
		
		//��ѧ���۲⣬����ѧ���۲�ṹ
		String maxXq = dao.getMaxZcxq(t.getXn());
		
		if(CsszService.getZczq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(t.getXq()))){
			xnZcxm(t.getXn());
		}
		
		return result;
	}


	//�۲���Ŀ����������ɾ���۲��
	private boolean delZcfs(String xmdm) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		ZcfsDao zcfsDao = new ZcfsDao();
		
		return zcfsDao.delZcfs(csszModel.getXn(),csszModel.getXq(),xmdm);
	}
	
	
	/**
	 * 
	 * @����: ����¼���۲�ֵ��۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����01:52:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		return dao.getAllowEditZcfxm();
	}
	
	
	/**
	 * 
	 * @����: ���Ӽ���Ŀ���۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����01:52:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getNoChildZcfxm(){
		return dao.getNoChildZcfxm();
	}
	
	
	/**
	 * @����: ���༶��ѯ��¼���۲��������Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-4-1 ����10:18:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getEditZcxmByBjdm(String bjdm) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		if (XMJB_NJ.equals(csszModel.getZcxmjb())){
			//�۲���Ŀ����Ϊ �꼶
			return dao.getEditZcxmByNj(bjdm);
		}
		
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			//�۲���Ŀ������Ϊ Ժϵ
			return dao.getEditZcxmByXy(bjdm);
		}
		
		return dao.getNoChildZcfxm();
	}
	
	
	/**
	 * @����: ���༶��ѯ��¼���۲��������ĿS
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-4-1 ����10:18:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getEditZcxmByBjdms(List<HashMap<String, String>> bjList, ZcfsModel model, User user) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		/*����ũҵ��ѧ����������Ȩ�ر�Ϊ0ʱ��ʾ��Ŀ*/
		if(!"10364".equals(Base.xxdm)){
			if (XMJB_NJ.equals(csszModel.getZcxmjb())){
				//�۲���Ŀ����Ϊ �꼶
				return dao.getEditZcxmByNj(bjList,model, user);
			}
		}
		
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			//�۲���Ŀ������Ϊ Ժϵ
			return dao.getEditZcxmByXy(bjList,model, user);
		}
		
		return dao.getNoChildZcfxm();
	}
	
	
	
	
	/**
	 * 
	 * @����: ��ѯǰ�����۲���Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����02:50:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getFirstAndSecondZcxm(ZcfsModel t){
		return dao.getFirstAndSecondZcxm(t);
	}
	
	
	
	public List<HashMap<String,String>> getFirstAndSecondZcxm(String xn, String xq){
		return dao.getFirstAndSecondZcxm(xn,xq);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ��ǰ���ڵ��۲���Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-27 ����11:56:24
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
	 * @����: ��ǰ���ڵ�������Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-10-28 ����10:08:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentZcxmList() throws Exception{
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		return dao.getZcxmList(csszModel.getXn(), csszModel.getXq());
	}
	
	public List<HashMap<String,String>> getCurrentZcxmList2() throws Exception{
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		if(Base.xxdm.equals("10589")){
			return dao.getZcxmList(csszModel.getXn(), csszModel.getXq());
		}else{
			return dao.getZcxmList2(csszModel.getXn(), csszModel.getXq());
		}
		
	}



	/**
	 * @throws Exception 
	 * @����: �����ϸ�����Ƿ��޸Ĺ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����10:07:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * boolean �������� 
	 */
	public boolean jcBlxg(String xmdm) throws Exception{
		
		String count = dao.getConutByUpdate(xmdm);
		
		return Integer.valueOf(count) > 0;
	}


	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(ZcxmModel t) throws Exception {
		boolean result = super.runUpdate(t);
		
		//ͬ�����±��������꼶��Ժϵ
		if (result && "yes".equals(t.getTbbl())){
			dao.updateXxbl(t);
		}
		
		if (XMLX_DEFAULT.equals(t.getXmlx())){
			//���ǵ����ӡ����ָ���ΪĬ�Ϸ֣�ִ��ɾ������
			dao.delXxbl(t.getXmdm());
		} else {
			//��֮��Ĭ�Ϸָ���Ϊ�ӡ����֣���Ӧ��ϸ��������
			CsszDao csszDao = new CsszDao();
			CsszModel csszModel = csszDao.getModel();
			
			if (XMJB_YX.equals(csszModel.getZcxmjb())){
				dao.initXxblByXy();
			}
			
			if (XMJB_NJ.equals(csszModel.getZcxmjb())){
				dao.initXxblByNj();
			}
		}
		
		//��ѧ���۲⣬����ѧ���۲�ṹ
		String maxXq = dao.getMaxZcxq(t.getXn());
		
		if(CsszService.getZczq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(t.getXq()))){
			xnZcxm(t.getXn());
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @����: ����۲�����Ƿ�������һ��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����02:11:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void checkZcbl() throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		ZcfsDao zcfsDao = new ZcfsDao();
		//�Ѿ��ύ�۲�ֵİ༶����
		String tjgs = zcfsDao.getYtjZcfNum(csszModel.getXn(), csszModel.getXq());
		//�Ѿ��а༶�ύ���۲�־Ͳ����������
		if (Integer.valueOf(tjgs) > 0){
			logger.debug("�Ѿ��а༶�ύ���۲�֣��������ϸ�����Ƿ������ò���һ�£�");
			return ;
		}
		
		//���꼶���
		if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
			String count = dao.getCountXxblByNj(csszModel.getXn(), csszModel.getXq());
			
			if (Integer.valueOf(count) == 0){
				dao.delXxbl(csszModel.getXn(), csszModel.getXq());
				dao.initXxblByNj();
			}
		}
		
		//��Ժϵ���
		if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())){
			String count = dao.getCountXxblByXy(csszModel.getXn(), csszModel.getXq());
			
			if (Integer.valueOf(count) == 0){
				dao.delXxbl(csszModel.getXn(), csszModel.getXq());
				dao.initXxblByXy();
			}
		}
		
	}



	
	/**
	 * 
	 * @����: ��ѯ�ɵ�����ϸ�������۲���Ŀ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-27 ����03:34:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,Object>> getZcxmListByXxbl(){
		//��ѯ������ϸ�������۲���Ŀ
		List<HashMap<String,String>> zcxmList = dao.getZcxmListByXxbl();
		
		List<HashMap<String,Object>>  resultList = new ArrayList<HashMap<String,Object>>();
		
		/*
		 * ���������ϸ������Ŀ�ṹ
		 */
		for (HashMap<String,String> map : zcxmList){
			HashMap<String,Object> resultMap = new HashMap<String, Object>();
			
			if (MAX_XMDM.equals(map.get("ffjdm"))){
				
				String xmdm = map.get("xmdm");
				
				resultMap.put("xmdm", xmdm);
				resultMap.put("xmmc", map.get("xmmc"));
				resultMap.put("name", map.get("name"));
				resultMap.put("qzbl", map.get("qzbl"));
				List<HashMap<String,String>> sjxmList = new ArrayList<HashMap<String,String>>();
				
				for (HashMap<String,String> minMap : zcxmList){
					
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
	
	public List<HashMap<String,String>> getSzyfList(){
		
		return dao.getSzyfList();
		
	}
	
	
	
	/**
	 * 
	 * @����: ��ѯ��ϸ�۲����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-31 ����11:21:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXxblList(ZcxmModel t) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		//�ɵ����������۲���Ŀ
		List<HashMap<String,Object>> zcxmList = getZcxmListByXxbl();
		
		//�꼶��ϸ����
		if (XMJB_NJ.equals(csszModel.getZcxmjb())){
			return dao.getXxblListByNj(t, zcxmList);
		} 
		
		//Ժϵ��ϸ����
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			return dao.getXxblListByXy(t, zcxmList);
		}
		
		return null;
	}



	/**
	 * 
	 * @����: �����꼶��ѧԺ��ϸ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-3-31 ����04:58:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXxblByBmdm(ZcxmModel t) throws Exception{
		
		return dao.updateXxblByBmdm(t);
	}
	
	
	/**
	 * 
	 * @����:�ж���Ŀ�����Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-8 ����01:51:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean xmmcExist(ZcxmModel model) {
		
		boolean flag = false;
		
		String count = dao.xmmcExist(model);
		
		if(!"0".equalsIgnoreCase(count)){
			flag = true;
		}
		
		return flag;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�۲��·ݱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-10-26 ����10:09:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveYf(String[] zcyf,String xn,String xq) throws Exception{
		boolean flag=true;
		List<String[]> zcyfList = new ArrayList<String[]>();
		String[] zcyfArr = null;
		for (int i = 0; i < zcyf.length; i++) {
			zcyfArr= new String[3];
			zcyfArr[0]=xn;
			zcyfArr[1]=xq;
			zcyfArr[2]=zcyf[i];
			zcyfList.add(zcyfArr);
		}
		flag=dao.delZcyf(xn, xq);
		flag=dao.insertZcyf(zcyfList);
		return flag;
	}
	
	public List<String> getYszYf(String xn,String xq) throws Exception{
		return dao.getYszYf(xn,xq);
	}
	public List<HashMap<String,String>> getZcYf(String xn,String xq) throws Exception{
		return dao.getZcYf(xn,xq);
	}
}
