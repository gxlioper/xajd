/**
 * @部门:学工产品事业部
 * @日期：2013-7-22 下午02:45:52 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测项目 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-22 下午02:45:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcxmService extends SuperServiceImpl<ZcxmModel, ZcxmDao> {
	
	public static final String XMLX_PUSH = "1";//加分
	public static final String XMLX_REMOVE = "2";//减分
	public static final String XMLX_DEFAULT = "3";//默认分
	private static final String DEFAULT_MAX = "100";//默认最大分
	private static final String DEFAULT_MIN = "0";//默认最小分
	
	public static final String XMJB_QT = "0";//全体
	public static final String XMJB_NJ = "1";//年级
	public static final String XMJB_YX = "2";//院系
	
	private static final String MAX_XMDM = "N";
	
	private ZcxmDao dao = new ZcxmDao();
	
	public ZcxmService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述: 查询综测项目结构
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 下午06:48:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * Map<String,List<HashMap<String,String>>> 返回类型
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
	 * @描述: 初始化综测项目结构
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-22 下午05:12:29
	 * @修改记录: 修改者名字-修改日期-修改内容
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
		
		//判断当前周期是否有综测结构，如果有 return;
		boolean isHave = !dao.getZcxmList(xn, xq).isEmpty();
		
		if (isHave){
			return ;
		}
		
		//判断系统中是否有初始过综测项目
		boolean isHaveZcxm = dao.getZcxmCount() > 0;
		//如果有，拷贝上周期或最近
		if (isHaveZcxm){
			//复制最近周期的综测结构
			copyZcxm(xn, xq);
		} else {
			//如果没有，内置系统结构
			initZcxm(xn, xq);
		}
		
		//非学年综测，重置学年综测结构
		String maxXq = dao.getMaxZcxq(xn);
		
		if(CsszService.getZczq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(xq))){
			xnZcxm(xn);
		}
		
	}
	
	

	//复制综测项目
	private void copyZcxm(String xn, String xq) throws Exception{
		
		//最近周期的综测项目结构
		List<HashMap<String,String>> zjzqZcxm = null;
		
		//学期综测
		if (CsszService.getZczq()){
			zjzqZcxm = dao.getZjzqZcxm(true);
		} else {
			zjzqZcxm = dao.getZjzqZcxm(false);
		}
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//将最近周期的项目结构 封装成 key:父级项目代码 value：子项目集
		for (HashMap<String,String> map : zjzqZcxm){
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
		String zczfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] zczf = new String[]{zczfXmdm,xn,xq,yjxm.get("xmmc"),"N","",yjxm.get("qzbl"),yjxm.get("mrfs"),"",DEFAULT_MAX,DEFAULT_MIN};
		params.add(zczf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//二级项目
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
		
		//初始化各年级或院系对应的详细比例
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		if (XMJB_NJ.equals(csszModel.getZcxmjb())){
			dao.initXxblByNj();
		}
		
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			dao.initXxblByXy();
		}
	}
	
	//复制学年综测项目
	private void xnZcxm(String xn) throws Exception{
		
		//最近周期的综测项目结构
		List<HashMap<String,String>> zjzqZcxm = dao.getXnzcxmForXn(xn);
		
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		
		//将最近周期的项目结构 封装成 key:父级项目代码 value：子项目集
		for (HashMap<String,String> map : zjzqZcxm){
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
		String zczfXmdm = uID.getUniqIDHash();
		
		HashMap<String,String> yjxm = resultMap.get("N").get(0);
		
		List<String[]> params = new ArrayList<String[]>();
		String[] zczf = new String[]{zczfXmdm,xn,CsszService.XQKG,yjxm.get("xmmc"),"N","",yjxm.get("qzbl"),yjxm.get("mrfs"),"",DEFAULT_MAX,DEFAULT_MIN};
		params.add(zczf);
		
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		
		for (HashMap<String,String> ejxmMap : ejxmList){
			
			String ejxmdm = uID.getUniqIDHash();
			//二级项目
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
			//三级项目
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
	
	
	//系统第一次初始化综测项目结构
	private void initZcxm(String xn, String xq) throws Exception {
		//第一级项目
		UniqID uID = UniqID.getInstance();
		String zczfXmdm = uID.getUniqIDHash();
		
		String[] zczf = new String[]{zczfXmdm,xn,xq,"综测总分","N","","100","0","",DEFAULT_MAX,DEFAULT_MIN};
		String[] dyf = new String[]{uID.getUniqIDHash(),xn,xq,"德育分",zczfXmdm,XMLX_PUSH,"100","0","",DEFAULT_MAX,DEFAULT_MIN};
		String[] zyf = new String[]{uID.getUniqIDHash(),xn,xq,"智育分",zczfXmdm,XMLX_PUSH,"100","0","",DEFAULT_MAX,DEFAULT_MIN};
		String[] tyf = new String[]{uID.getUniqIDHash(),xn,xq,"体育分",zczfXmdm,XMLX_PUSH,"100","0","",DEFAULT_MAX,DEFAULT_MIN};
		
		List<String[]> params = new ArrayList<String[]>();
		
		params.add(zczf);
		params.add(dyf);
		params.add(zyf);
		params.add(tyf);
		
		//初始化综测项目结构
		dao.initZcxmList(params);
		//初始化各年级或院系对应的详细比例
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
	 * @描述: 删除 综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-23 上午10:41:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型
	 */
	public boolean delZcxm(String xmdm){
		
		try {
			
			Map<String, String> xmInfo = dao.getZcxm(xmdm);
			boolean result = dao.delZcxm(xmdm) > 0;
			
			if (result){
				//删除 项目对应的综测分数
				delZcfs(xmdm);
				//删除 项目对应的各年级或院系详细比例
				dao.delXxbl(xmdm);
			}
			
			//非学年综测，重置学年综测结构
			
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
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	public boolean runInsert(ZcxmModel t) throws Exception {

		String uuid = UniqID.getInstance().getUniqIDHash();
		
		t.setXmdm(uuid);
		boolean result = super.runInsert(t);
		
		if (result){
			//删除父级项目的综测分数
			delZcfs(t.getFjdm());
			
			//项目类型为默认分
			if (XMLX_DEFAULT.equals(t.getXmlx())){
				return result;
			}
			
			//----根据参数设置 保存详细比例----
			CsszDao csszDao = new CsszDao();
			CsszModel csszModel = csszDao.getModel();
			
			//综测项目级别为年级
			if (XMJB_NJ.equals(csszModel.getZcxmjb())){
				dao.insertZcblByNj(t);
			}
			
			//综测项目级别为院系
			if (XMJB_YX.equals(csszModel.getZcxmjb())){
				dao.insertZcblByXy(t);
			}
		}
		
		//非学年综测，重置学年综测结构
		String maxXq = dao.getMaxZcxq(t.getXn());
		
		if(CsszService.getZczq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(t.getXq()))){
			xnZcxm(t.getXn());
		}
		
		return result;
	}


	//综测项目调整，关联删除综测分
	private boolean delZcfs(String xmdm) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		ZcfsDao zcfsDao = new ZcfsDao();
		
		return zcfsDao.delZcfs(csszModel.getXn(),csszModel.getXq(),xmdm);
	}
	
	
	/**
	 * 
	 * @描述: 允许录入综测分的综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午01:52:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		return dao.getAllowEditZcfxm();
	}
	
	
	/**
	 * 
	 * @描述: 无子级项目的综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-24 下午01:52:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getNoChildZcfxm(){
		return dao.getNoChildZcfxm();
	}
	
	
	/**
	 * @描述: 按班级查询可录入综测分数的项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 上午10:18:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getEditZcxmByBjdm(String bjdm) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		if (XMJB_NJ.equals(csszModel.getZcxmjb())){
			//综测项目级别为 年级
			return dao.getEditZcxmByNj(bjdm);
		}
		
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			//综测项目级别是为 院系
			return dao.getEditZcxmByXy(bjdm);
		}
		
		return dao.getNoChildZcfxm();
	}
	
	
	/**
	 * @描述: 按班级查询可录入综测分数的项目S
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-4-1 上午10:18:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getEditZcxmByBjdms(List<HashMap<String, String>> bjList, ZcfsModel model, User user) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		/*安徽农业大学，参数设置权重比为0时显示项目*/
		if(!"10364".equals(Base.xxdm)){
			if (XMJB_NJ.equals(csszModel.getZcxmjb())){
				//综测项目级别为 年级
				return dao.getEditZcxmByNj(bjList,model, user);
			}
		}
		
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			//综测项目级别是为 院系
			return dao.getEditZcxmByXy(bjList,model, user);
		}
		
		return dao.getNoChildZcfxm();
	}
	
	
	
	
	/**
	 * 
	 * @描述: 查询前两级综测项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-7-30 下午02:50:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getFirstAndSecondZcxm(ZcfsModel t){
		return dao.getFirstAndSecondZcxm(t);
	}
	
	
	
	public List<HashMap<String,String>> getFirstAndSecondZcxm(String xn, String xq){
		return dao.getFirstAndSecondZcxm(xn,xq);
	}
	
	
	/**
	 * 
	 * @描述: 查询当前周期的综测项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-9-27 上午11:56:24
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
	 * @描述: 当前周期的综则项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-10-28 上午10:08:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
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
	 * @描述: 检测详细比例是否修改过
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 上午10:07:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型 
	 */
	public boolean jcBlxg(String xmdm) throws Exception{
		
		String count = dao.getConutByUpdate(xmdm);
		
		return Integer.valueOf(count) > 0;
	}


	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(ZcxmModel t) throws Exception {
		boolean result = super.runUpdate(t);
		
		//同步更新比例到各年级或院系
		if (result && "yes".equals(t.getTbbl())){
			dao.updateXxbl(t);
		}
		
		if (XMLX_DEFAULT.equals(t.getXmlx())){
			//考虑到将加、减分更改为默认分，执行删除操作
			dao.delXxbl(t.getXmdm());
		} else {
			//反之，默认分更改为加、减分，对应详细比例增加
			CsszDao csszDao = new CsszDao();
			CsszModel csszModel = csszDao.getModel();
			
			if (XMJB_YX.equals(csszModel.getZcxmjb())){
				dao.initXxblByXy();
			}
			
			if (XMJB_NJ.equals(csszModel.getZcxmjb())){
				dao.initXxblByNj();
			}
		}
		
		//非学年综测，重置学年综测结构
		String maxXq = dao.getMaxZcxq(t.getXn());
		
		if(CsszService.getZczq()&&(StringUtils.isBlank(maxXq)||maxXq.equals(t.getXq()))){
			xnZcxm(t.getXn());
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @描述: 检测综测比例是否与配置一致
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 下午02:11:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void checkZcbl() throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		ZcfsDao zcfsDao = new ZcfsDao();
		//已经提交综测分的班级个数
		String tjgs = zcfsDao.getYtjZcfNum(csszModel.getXn(), csszModel.getXq());
		//已经有班级提交了综测分就不继续检测了
		if (Integer.valueOf(tjgs) > 0){
			logger.debug("已经有班级提交了综测分，不检测详细比例是否与配置参数一致！");
			return ;
		}
		
		//按年级检测
		if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
			String count = dao.getCountXxblByNj(csszModel.getXn(), csszModel.getXq());
			
			if (Integer.valueOf(count) == 0){
				dao.delXxbl(csszModel.getXn(), csszModel.getXq());
				dao.initXxblByNj();
			}
		}
		
		//按院系检测
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
	 * @描述: 查询可调整详细比例的综测项目
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-27 下午03:34:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,Object>> getZcxmListByXxbl(){
		//查询可设详细比例的综测项目
		List<HashMap<String,String>> zcxmList = dao.getZcxmListByXxbl();
		
		List<HashMap<String,Object>>  resultList = new ArrayList<HashMap<String,Object>>();
		
		/*
		 * 处理可设详细比例项目结构
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
	 * @描述: 查询详细综测比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-31 上午11:21:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXxblList(ZcxmModel t) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		//可调整比例的综测项目
		List<HashMap<String,Object>> zcxmList = getZcxmListByXxbl();
		
		//年级详细比例
		if (XMJB_NJ.equals(csszModel.getZcxmjb())){
			return dao.getXxblListByNj(t, zcxmList);
		} 
		
		//院系详细比例
		if (XMJB_YX.equals(csszModel.getZcxmjb())){
			return dao.getXxblListByXy(t, zcxmList);
		}
		
		return null;
	}



	/**
	 * 
	 * @描述: 更新年级或学院详细比例
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-3-31 下午04:58:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXxblByBmdm(ZcxmModel t) throws Exception{
		
		return dao.updateXxblByBmdm(t);
	}
	
	
	/**
	 * 
	 * @描述:判断项目名称是否存在
	 * @作者：cq [工号：785]
	 * @日期：2014-8-8 下午01:51:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
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
	 * @描述:综测月份保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-10-26 上午10:09:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
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
