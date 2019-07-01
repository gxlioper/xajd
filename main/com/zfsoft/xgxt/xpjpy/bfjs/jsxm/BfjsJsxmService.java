/**
 * @部门:学工产品事业部
 * @日期：2013-7-22 下午02:45:52 
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
	
	public static final String XMLX_PLUS = "1";//加分
	public static final String XMLX_MINUS = "2";//减分
	public static final String XMLX_DEFAULT = "3";//默认分
	private static final String DEFAULT_MAX = "100";//默认最大分
	private static final String DEFAULT_MIN = "0";//默认最小分
	
	public static final String XMJB_QT = "0";//全体
	public static final String XMJB_NJ = "1";//年级
	public static final String XMJB_YX = "2";//院系
	
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
			//删除父级项目的综测分数
			delJsfs(t.getFjdm());
			
			//项目类型为默认分
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
			
			//项目类型为默认分
			if (XMLX_DEFAULT.equals(t.getXmlx())){
				BfjsFswhDao fswhDao = new BfjsFswhDao();
				result =fswhDao.updateDefaultJsxmf(t);
			}
		}
		return result;
	
	}
	/**
	 * 
	 * @描述:查询竞赛项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:31:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * Map<String,List<HashMap<String,String>>> 返回类型 
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
	 * @描述:初始化竞赛项目结构
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:31:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pdzq
	 * @throws Exception
	 * void 返回类型 
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
		
		//判断当前周期是否有竞赛项目，如果有 return;
		boolean isHave = !dao.getBfjsJsxmList(xn, xq).isEmpty();
		
		if (isHave){
			return ;
		}
		
		//判断系统中是否有初始过竞赛项目
		boolean isHaveBfjsJsxm = dao.getBfjsJsxmCount() > 0;
		//如果有，拷贝上周期或最近
		if (isHaveBfjsJsxm){
			//复制最近周期的竞赛项目结构
			copyBfjsJsxm(xn, xq);
		} else {
			//如果没有，内置系统结构
			initBfjsJsxm(xn, xq);
		}
		
		//非学年竞赛项目，重置学年竞赛项目结构
		String maxXq = dao.getMaxJsxq(xn);
		
//		if(BfjsCsszService.getJszq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(xq))){
//			xnBfjsJsxm(xn);
//		}
		
	}
	
	

	//复制竞赛项目项目
	private void copyBfjsJsxm(String xn, String xq) throws Exception{
		
		//最近周期的竞赛项目项目结构
		List<HashMap<String,String>> zjzqBfjsJsxm = null;
		
		//学期竞赛项目
		if (BfjsCsszService.getDfzq()){
			zjzqBfjsJsxm = dao.getZjzqBfjsJsxm(true);
		} else {
			zjzqBfjsJsxm = dao.getZjzqBfjsJsxm(false);
		}
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//将最近周期的项目结构 封装成 key:父级项目代码 value：子项目集
		for (HashMap<String,String> map : zjzqBfjsJsxm){
			String key = map.get("fjdm");
			
			List<HashMap<String,String>> list = resultMap.get(key);
			
			if (list == null){
				list = new ArrayList<HashMap<String,String>>();
			}
			
			list.add(map);
			
			resultMap.put(key, list);
		}
		
		//构建新的项目结构
		UniqID uID = UniqID.getInstance();
		String jszfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] jszf = new String[]{jszfXmdm,xn,xq,yjxm.get("xmmc"),"N",yjxm.get("xmlx"),yjxm.get("qzbl"),yjxm.get("mrfs"),DEFAULT_MAX,DEFAULT_MIN,""};
		params.add(jszf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//二级项目
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
			//三级项目
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
	
	//复制学年竞赛项目项目
	private void xnBfjsJsxm(String xn) throws Exception{
		
		//最近周期的竞赛项目项目结构
		List<HashMap<String,String>> zjzqBfjsJsxm = dao.getXnBfjsJsxmForXn(xn);
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//将最近周期的项目结构 封装成 key:父级项目代码 value：子项目集
		for (HashMap<String,String> map : zjzqBfjsJsxm){
			String key = map.get("fjdm");
			
			List<HashMap<String,String>> list = resultMap.get(key);
			
			if (list == null){
				list = new ArrayList<HashMap<String,String>>();
			}
			
			list.add(map);
			
			resultMap.put(key, list);
		}
		
		//构建新的项目结构
		UniqID uID = UniqID.getInstance();
		String jszfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] jszf = new String[]{jszfXmdm,xn,BfjsCsszService.XQKG,yjxm.get("xmmc"),"N","",yjxm.get("qzbl"),yjxm.get("mrfs"),"",DEFAULT_MAX,DEFAULT_MIN};
		params.add(jszf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//二级项目
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
			//三级项目
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
	
	
	//系统第一次初始化竞赛项目项目结构
	private void initBfjsJsxm(String xn, String xq) throws Exception {
		//第一级项目
		UniqID uID = UniqID.getInstance();
		String jszfXmdm = uID.getUniqIDHash();
		
		String[] jszf = new String[]{jszfXmdm,xn,xq,"竞赛项目总分","N","","100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		String[] dyf = new String[]{uID.getUniqIDHash(),xn,xq,"德育分",jszfXmdm,XMLX_PLUS,"100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		String[] zyf = new String[]{uID.getUniqIDHash(),xn,xq,"智育分",jszfXmdm,XMLX_PLUS,"100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		String[] tyf = new String[]{uID.getUniqIDHash(),xn,xq,"体育分",jszfXmdm,XMLX_PLUS,"100","0",DEFAULT_MAX,DEFAULT_MIN,""};
		
		List<String[]> params = new ArrayList<String[]>();
		
		params.add(jszf);
		params.add(dyf);
		params.add(zyf);
		params.add(tyf);
		
		//初始化竞赛项目项目结构
		dao.initBfjsJsxmList(params);
		
		
		
	}
	
	
	/**
	 * 
	 * @描述:删除竞赛项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:33:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delBfjsJsxm(String xmdm){
		
		try {
			
			Map<String, String> xmInfo = dao.getBfjsJsxm(xmdm);
			boolean result = dao.delBfjsJsxm(xmdm) > 0;
			
			if (result){
				//删除 项目对应的竞赛项目分数
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
	 * @描述:无子级项目的竞赛项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:33:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getNoChildJsfxm(){
		return dao.getNoChildJsfxm();
	}
	
	
	/**
	 * 
	 * @描述:按班级查询可录入竞赛项目分数的项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:34:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getEditBfjsJsxmByBjdm() throws Exception{
		
		BfjsCsszDao BfjsCsszDao = new BfjsCsszDao();
		BfjsCsszModel csszModel = BfjsCsszDao.getModel();
		
	
		
		return dao.getNoChildJsfxm();
	}
	
	
	
	
	
	/**
	 * 
	 * @描述: 查询前两级竞赛项目项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:34:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
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
	 * @描述:查询当前周期的竞赛项目项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:35:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentZfxm(){
		
		return dao.getCurrentZfxm();
	}
	


	/**
	 * 
	 * @描述:当前周期的综则项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-1 上午10:35:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCurrentBfjsJsxmList() throws Exception{
		BfjsCsszDao BfjsCsszDao = new BfjsCsszDao();
		BfjsCsszModel csszModel = BfjsCsszDao.getModel();
		
		return dao.getBfjsJsxmList(csszModel.getXn(), csszModel.getXq());
	}
	

	
	/**
	 * 
	 * @描述: 查询可调整详细比例的竞赛项目项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-27 下午03:34:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,Object>> getBfjsJsxmListByXxbl(){
		//查询可设详细比例的竞赛项目项目
		List<HashMap<String,String>> BfjsJsxmList = dao.getBfjsJsxmListByXxbl();
		
		List<HashMap<String,Object>>  resultList = new ArrayList<HashMap<String,Object>>();
		
		/*
		 * 处理可设详细比例项目结构
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
	 * @描述:判断项目名称是否存在
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-22 下午01:51:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
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
