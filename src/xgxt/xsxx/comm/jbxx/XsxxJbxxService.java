package xgxt.xsxx.comm.jbxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;
import xgxt.xtwh.xtwhOther.DmwhSelectMethod;

public class XsxxJbxxService extends XsxxJbszService {
	
	/** 
	 * 获得启用显示区列表
	 * @author qlj
	 */
	public  XsxxJbxxDAO dao=new XsxxJbxxDAO();
	
	//省市县简称
	public final HashMap<String,String>SSXJC=new HashMap<String,String>();
	//特殊字段名称
	public final HashMap<String,String>TSZDMC=new HashMap<String,String>();
	
	public List<HashMap<String, String>> getXsqxxList(XsxxJbszForm model) {
		model.setXsqsfqy("是");
		return getXsqList(model);
	}
	
	/**
	 * 获得字段位置
	 * @author qlj
	 */
	public List<HashMap<String, String>> getZdwzList(XsxxJbxxForm myForm) {
		
		String tableName = "xg_xsxx_xsqwzb";
		StringBuilder query=new StringBuilder();
		ArrayList<String> inPutList = new ArrayList<String>();
		
		String[] colList = new String[] { "xsqdm", "zd", "szh", "szl"};
		String sql = "";

		return getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList, sql);
	}
	
	/**
	 * 获得启用字段列表
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getQyzdList(XsxxJbxxForm model) {

		String tableName = "xg_view_xsxx_zdszb";
		String query = " where search_sfqy = ? ";
		String[] inPutList = new String[] { "是" };
		String[] colList = new String[] { "search_zd", "search_ymxs","search_lrxz","search_wkxz",
				"search_lrxs","search_sfqy"};
		String sql = "";
		return getRsList(tableName, query, inPutList, colList, sql);
	}
	
	public List<HashMap<String,Object>>getXsqnrList(XsxxJbxxForm model) throws Exception{
		
		//加载省市县简称信息
		setTszdmc();
		//显示区域内容
		List<HashMap<String, Object>> xsqnrList =new ArrayList<HashMap<String, Object>>();
		//显示区域
		List<HashMap<String, String>> xsqList=getXsqxxList(model);
		//合并行
		List<HashMap<String, String>> hbhList=getXsqhbhList(model);
		//显示区字段详细信息(特殊字段处理)
		List<HashMap<String, String>>xsqzdList=setTszd(getXsqZdwzList(model));
		
		//验证方法
		xsqzdList=fieldValidate(xsqzdList,model);
		
		for(int i=1;i<=xsqList.size();i++){
			//显示区内容
			HashMap<String,Object>xsqnrMap=new HashMap<String, Object>();
			
			HashMap<String,String>xsqMap=xsqList.get(i-1);
			
			//将显示区信息添加到显示区内容中
			xsqnrMap.putAll(xsqMap);
			
			//显示区 显示记录合并信息
			List<HashMap<String, Object>> xshList=new ArrayList<HashMap<String, Object>> ();
			int szhs=Integer.parseInt(xsqMap.get("szhs"));//显示区所占行
			int zpszhs=Integer.parseInt(xsqMap.get("zpszhs"));//照片所占行
			String zpxs=xsqMap.get("zpxs");//照片显示
			for(int j=1;j<=szhs;j++){
				//是否合并
				String sfhb="否";
				HashMap<String, Object> xshMap=new HashMap<String,Object>();
				for(int m=0;m<hbhList.size();m++){
					HashMap<String,String>hbhMap=hbhList.get(m);
					
					
					if(hbhMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
							&& hbhMap.get("hbh").equalsIgnoreCase(""+j)){
						sfhb="是";
						break;
					}else{
						sfhb="否";
					}
					
				}
				
				xshMap.put("dygleft", "");
				xshMap.put("dygright", "");
				HashMap<String,String>leftMap=new HashMap<String,String>();
				HashMap<String,String>rightMap=new HashMap<String,String>();
					//显示区中单行信息中单元格信息
					for(int m=0;m<xsqzdList.size();m++){
						HashMap<String,String>xsqzdMap=xsqzdList.get(m);
						
							if(xsqzdMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
									&& xsqzdMap.get("zdszh").equalsIgnoreCase(""+j)){
								
								if("1".equalsIgnoreCase(xsqzdMap.get("zdszl"))){
									leftMap.put("zd",xsqzdMap.get("zd"));//单元格行;
									leftMap.put("jc",xsqzdMap.get("jc"));//字段简称;
									leftMap.put("dygh",xsqzdMap.get("zdszh"));//单元格行;
									leftMap.put("dygl",xsqzdMap.get("zdszl"));//单元格列;
									leftMap.put("zdm",xsqzdMap.get("zdm"));//字段名;
									leftMap.put("zszh",xsqzdMap.get("zszh"));//单元格行;
									leftMap.put("zszl",xsqzdMap.get("zszl"));//单元格列;
									leftMap.put("lrxs",xsqzdMap.get("lrxs"));//字段名;
									leftMap.put("wkxz",xsqzdMap.get("wkxz"));//为空限制;
									leftMap.put("methods",xsqzdMap.get("methods"));//验证方法;
									if("是".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										leftMap.put("disabled","");
									}else{
										leftMap.put("disabled","true");
									}
								}else{
									rightMap.put("jc",xsqzdMap.get("jc"));//字段简称;
									rightMap.put("zd",xsqzdMap.get("zd"));//单元格行;
									rightMap.put("dygh",xsqzdMap.get("zdszh"));//单元格行;
									rightMap.put("dygl",xsqzdMap.get("zdszl"));//单元格列;
									rightMap.put("zdm",xsqzdMap.get("zdm"));//字段名;
									rightMap.put("zszh",xsqzdMap.get("zszh"));//单元格行;
									rightMap.put("zszl",xsqzdMap.get("zszl"));//单元格列;
									rightMap.put("lrxs",xsqzdMap.get("lrxs"));//字段名;
									rightMap.put("wkxz",xsqzdMap.get("wkxz"));//为空限制;
									rightMap.put("methods",xsqzdMap.get("methods"));//验证方法;
									if("是".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										rightMap.put("disabled","");
									}else{
										rightMap.put("disabled","true");
									}
								}
								
							}
						
							
					}
					
				xshMap.put("leftMap",leftMap);//单元格行;
				xshMap.put("rightMap",rightMap);//单元格列;
				//需要显示照片,并且当所占行大于照片所占行(只存照片行以后的数据)
				if("是".equalsIgnoreCase(zpxs) && zpszhs<j){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
				}else if("否".equalsIgnoreCase(zpxs)){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
					
				}
				xshList.add(xshMap);
			}
			xsqnrMap.put("xshxx", xshList);
			xsqnrList.add(xsqnrMap);
		}
		return xsqnrList;
	}
	
	public void setXlkList(XsxxJbxxForm model, HttpServletRequest request) {

		// 下拉列表
		List<HashMap<String, String>> xllbList = dao.getXllbList(model);

		for (int i = 0; i < xllbList.size(); i++) {
			
			HashMap<String, String> xllbMap = xllbList.get(i);
			List<HashMap<String,String>>whzdList=new ArrayList<HashMap<String,String>>();
			if("yes".equalsIgnoreCase(xllbMap.get("lydmwh"))){
				try {
					whzdList = (ArrayList<HashMap<String, String>>)DmwhSelectMethod.class.getMethod(xllbMap.get("lyb"),
							(Class[]) null).invoke(null,(Object[]) null);
					
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}else {
				model.setTableName(xllbMap.get("lyb"));
				model.setZddm(xllbMap.get("dm"));
				model.setZdmc(xllbMap.get("mc"));
				whzdList=dao.getXlkxx(model);
			}
			request.setAttribute(xllbMap.get("zd") + "List",whzdList);
		}
	}
	
	/**
	 * 省市县联动的特殊处理
	 * @param xsqzdList
	 * @return  List<HashMap<String, String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>>setTszd(List<HashMap<String, String>>xsqzdList){
		List<HashMap<String, String>>xszdList=new ArrayList<HashMap<String,String>>();
		for(int j=0;j<xsqzdList.size();j++){
			HashMap<String,String>xsqzdMap=xsqzdList.get(j);
			//只有在录入形式为特殊格式时才做处理
			if("特殊格式".equalsIgnoreCase(xsqzdMap.get("lrxs"))){
				xsqzdMap.put("jc",SSXJC.get(xsqzdMap.get("zd")));
			}
			xszdList.add(xsqzdMap);
		}
		return xszdList;
	}
	
	/**
	 * 设置省市县连动简称
	 * @author qlj
	 */
	public void setSsxjc(){
		SSXJC.put("syd", "sy");
		SSXJC.put("jg", "jg");
		SSXJC.put("hkszd", "hk");
	}
	
	/**
	 * 特殊字段名称处理
	 * @author qlj
	 */
	public void setTszdmc(){
		TSZDMC.put("syd", "sydmc");
		TSZDMC.put("jg", "jgmc");
		TSZDMC.put("hkszd", "hkszdmc");
		
		TSZDMC.put("xydm", "xymc");
		TSZDMC.put("zydm", "zymc");
		TSZDMC.put("bjdm", "bjmc");
		TSZDMC.put("nj", "nj");
	}
	
	/**
	 * 字段验证
	 * @param request
	 * @param model
	 * @return List<HashMap<String,Object>>
	 * @throws Exception
	 * @author qlj
	 */
	public List<HashMap<String, String>> fieldValidate(List<HashMap<String, String>>xsqzdList,
			XsxxJbxxForm model) throws Exception{
		
		String tableName="xsxxb";
		//获取学生信息表字段信息(字段,字段名,字段长度)
		List<HashMap<String,String>>zdxxList=getTableZdList(tableName);
		//需要显示字段的信息
		List<HashMap<String,String>>xxszdList=new ArrayList<HashMap<String,String>>();
		StringBuilder pk=new StringBuilder();
		int m=0;
			for(int j=0;j<xsqzdList.size();j++){
				HashMap<String,String>xsqzdMap=xsqzdList.get(j);
				for (int i=0;i<zdxxList.size();i++){
					HashMap<String,String>zdxxMap=zdxxList.get(i);
					if(xsqzdMap.get("lrxs").equalsIgnoreCase("输入框")
						&& zdxxMap.get("dm").equalsIgnoreCase(xsqzdMap.get("zd"))){
					
						String lrffxz="";
						if("只限中文".equalsIgnoreCase(xsqzdMap.get("lrxz"))
								|| "无限制".equalsIgnoreCase(xsqzdMap.get("lrxz"))){
							int length=Integer.parseInt(zdxxMap.get("len"));
							lrffxz+="checkLen($('"+xsqzdMap.get("zd")+"'),'"+length/2+"');";
						}else{
							lrffxz+="checkLen($('"+xsqzdMap.get("zd")+"'),'"+zdxxMap.get("len")+"');";
						}
					
					
						if("整数限制".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//整数
							lrffxz+="checkNum($('"+xsqzdMap.get("zd")+"'));";
						}else if("数字限制".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//可带小数点
							lrffxz+="checkNumber($('"+xsqzdMap.get("zd")+"'));";
						}else if("英数字限制".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//英数字
							lrffxz+="checkWordNum($('"+xsqzdMap.get("zd")+"'));";
						}else if("中文限制".equalsIgnoreCase(xsqzdMap.get("lrxz"))){//中文
							lrffxz+="checkInputIsZw($('"+xsqzdMap.get("zd")+"'));";
						}
						xsqzdMap.put("methods", lrffxz);
					
						if("不可为空".equalsIgnoreCase(xsqzdMap.get("wkxz"))){
							if(m==0){
								pk.append(xsqzdMap.get("zd"));
							}else{
								pk.append("-"+xsqzdMap.get("zd"));
							}
							m++;
						}
						
					}else{
						if("不可为空".equalsIgnoreCase(xsqzdMap.get("wkxz"))
								){
							if(m==0){
								pk.append(xsqzdMap.get("zd"));
							}else{
								pk.append("-"+xsqzdMap.get("zd"));
							}
							m++;
						}
					}
					break;
			}
				xxszdList.add(xsqzdMap);
				
		}
		model.setPk(pk.toString());
		return xxszdList;
	}
	
	
	
	
	
	/**
	 * 字段的长度验证
	 * @param request
	 * @param model
	 * @return List<HashMap<String,Object>>
	 * @throws Exception
	 * @author qlj
	 */
	public void checkZdSize(HttpServletRequest request,XsxxJbszForm model) throws Exception{
		String tableName="xsxxb";
		//获取学生信息表字段信息(字段,字段名,字段长度)
		List<HashMap<String,String>>zdxxList=getTableZdList(tableName);
		//获取学生信息字段设置表信息
		List<HashMap<String,String>>szzdList=getXsqZdwzList(model);
		//需要显示字段的信息
		List<HashMap<String,String>>xxszdList=new ArrayList<HashMap<String,String>>();
		StringBuilder pk=new StringBuilder();
		StringBuilder zdmc=new StringBuilder();
		int m=0;
		boolean blog=false;
		for(int i=0;i<szzdList.size();i++){
			HashMap<String,String>szzdMap=szzdList.get(i);
			HashMap<String,String>xxszdMap=new HashMap<String,String>();
			for(int j=0;j<zdxxList.size();j++){
				HashMap<String,String>zdxxMap=zdxxList.get(j);
				
				if(szzdMap.get("lrxs").equalsIgnoreCase("输入框")
					&& szzdMap.get("zd").equalsIgnoreCase(zdxxMap.get("dm"))){
					xxszdMap.put("zd", szzdMap.get("zd"));
					xxszdMap.put("mc", zdxxMap.get("mc"));
					//根据录入限制控制字段输入长度(可输入中文状态,长度为一半)
					if("只限中文".equalsIgnoreCase(szzdMap.get("lrxz"))
							|| "无限制".equalsIgnoreCase(szzdMap.get("lrxz"))){
						int length=Integer.parseInt(zdxxMap.get("len"));
						xxszdMap.put("len", ""+length/2);
					}else{
						xxszdMap.put("len", zdxxMap.get("len"));
					}
					xxszdMap.put("lrxz", szzdMap.get("lrxz"));
					xxszdMap.put("lrxs", szzdMap.get("lrxs"));
					xxszdList.add(xxszdMap);
					if("不可为空".equalsIgnoreCase(szzdMap.get("wkxz"))){
						if(m==0){
							pk.append(szzdMap.get("zd"));
						}else{
							pk.append("-"+szzdMap.get("zd"));
						}
						m++;
					}
					break;
				}else{
					if("不可为空".equalsIgnoreCase(szzdMap.get("wkxz"))
							&& !blog){
						if(m==0){
							pk.append(szzdMap.get("zd"));
						}else{
							pk.append("-"+szzdMap.get("zd"));
						}
						blog=true;
						m++;
					}
				}
				
			}
			
		}
		request.setAttribute("pk", pk);
		request.setAttribute("zdmc", zdmc);
		request.setAttribute("xxszdList", xxszdList);
	}
	
	/**
	 * 获取学生相关信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getXsxgxx(XsxxJbxxForm model){
		return dao.getXsxgxx(model);
	}
	
	
	public List<HashMap<String,Object>>designXsxx(XsxxJbxxForm model){
		//加载省市县简称信息
		setTszdmc();
		//显示区内容
		List<HashMap<String, Object>> xsqnrList =new ArrayList<HashMap<String, Object>>();
		//显示区
		List<HashMap<String, String>> xsqList=getXsqxxList(model);
		//合并行
		List<HashMap<String, String>> hbhList=getXsqhbhList(model);
		//显示区字段详细信息(特殊字段显示处理)
		List<HashMap<String, String>>xsqzdList=getTszdmc(getXxyxx(model));
		
		for(int i=1;i<=xsqList.size();i++){
			//显示区内容
			HashMap<String,Object>xsqnrMap=new HashMap<String, Object>();
			
			HashMap<String,String>xsqMap=xsqList.get(i-1);
			
			//将显示区信息添加到显示区内容中
			xsqnrMap.putAll(xsqMap);
			
			//显示区 显示记录合并信息
			List<HashMap<String, Object>> xshList=new ArrayList<HashMap<String, Object>> ();
			int szhs=Integer.parseInt(xsqMap.get("szhs"));//显示区所占行
			int zpszhs=Integer.parseInt(xsqMap.get("zpszhs"));//照片所占行
			String zpxs=xsqMap.get("zpxs");//照片显示
			for(int j=1;j<=szhs;j++){
				//是否合并
				String sfhb="否";
				HashMap<String, Object> xshMap=new HashMap<String,Object>();
				for(int m=0;m<hbhList.size();m++){
					HashMap<String,String>hbhMap=hbhList.get(m);
					
					
					if(hbhMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
							&& hbhMap.get("hbh").equalsIgnoreCase(""+j)){
						sfhb="是";
						break;
					}else{
						sfhb="否";
					}
					
				}
				
				xshMap.put("dygleft", "");
				xshMap.put("dygright", "");
				HashMap<String,String>leftMap=new HashMap<String,String>();
				HashMap<String,String>rightMap=new HashMap<String,String>();
					//显示区中单行信息中单元格信息
					for(int m=0;m<xsqzdList.size();m++){
						HashMap<String,String>xsqzdMap=xsqzdList.get(m);
						
							if(xsqzdMap.get("xsqdm").equalsIgnoreCase(xsqMap.get("xsqdm"))
									&& xsqzdMap.get("zdszh").equalsIgnoreCase(""+j)){
								
								if("1".equalsIgnoreCase(xsqzdMap.get("zdszl"))){
									
									leftMap.put("zd",xsqzdMap.get("mc"));//单元格行;
									leftMap.put("dygh",xsqzdMap.get("zdszh"));//单元格行;
									leftMap.put("dygl",xsqzdMap.get("zdszl"));//单元格列;
									leftMap.put("zdm",xsqzdMap.get("zdm"));//字段名;
									leftMap.put("zszh",xsqzdMap.get("zszh"));//单元格行;
									leftMap.put("zszl",xsqzdMap.get("zszl"));//单元格列;
									leftMap.put("lrxs",xsqzdMap.get("lrxs"));//字段名;
									leftMap.put("wkxz",xsqzdMap.get("wkxz"));//为空限制;
									if("是".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										leftMap.put("disabled","");
									}else{
										leftMap.put("disabled","true");
									}
									
								}else{
									rightMap.put("zd",xsqzdMap.get("mc"));//单元格行;
									rightMap.put("dygh",xsqzdMap.get("zdszh"));//单元格行;
									rightMap.put("dygl",xsqzdMap.get("zdszl"));//单元格列;
									rightMap.put("zdm",xsqzdMap.get("zdm"));//字段名;
									rightMap.put("zszh",xsqzdMap.get("zszh"));//单元格行;
									rightMap.put("zszl",xsqzdMap.get("zszl"));//单元格列;
									rightMap.put("lrxs",xsqzdMap.get("lrxs"));//字段名;
									rightMap.put("wkxz",xsqzdMap.get("wkxz"));//为空限制;
									if("是".equalsIgnoreCase(xsqzdMap.get("xgwz"))){
										rightMap.put("disabled","");
									}else{
										rightMap.put("disabled","true");
									}
								}
								
							}
						
							
					}
					
				xshMap.put("leftMap",leftMap);//单元格行;
				xshMap.put("rightMap",rightMap);//单元格列;
				//需要显示照片,并且当所占行大于照片所占行(只存照片行以后的数据)
				if("是".equalsIgnoreCase(zpxs) && zpszhs<j){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
				}else if("否".equalsIgnoreCase(zpxs)){
					xshMap.put("hbh", j);
					xshMap.put("sfhb", sfhb);
					
				}
				xshList.add(xshMap);
			}
			xsqnrMap.put("xshxx", xshList);
			xsqnrList.add(xsqnrMap);
		}
		return xsqnrList;
	}
	
	/**
	 * 获取学生详细页信息
	 * @author qlj
	 */
	public List<HashMap<String, String>> getXxyxx(XsxxJbszForm model) {

		return dao.getXxyxx(model);
	}
	
	/**
	 * 省市县联动的特殊处理
	 * @param xsqzdList
	 * @return  List<HashMap<String, String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>>getTszdmc(List<HashMap<String, String>>xsqzdList){

		List<HashMap<String, String>>xszdList=new ArrayList<HashMap<String,String>>();
		for(int j=0;j<xsqzdList.size();j++){
			HashMap<String,String>xsqzdMap=xsqzdList.get(j);
			//只有在录入形式为特殊格式时才做处理
			if("特殊格式".equalsIgnoreCase(xsqzdMap.get("lrxs"))){
				xsqzdMap.put("mc",TSZDMC.get(xsqzdMap.get("zd")));
			}
			xszdList.add(xsqzdMap);
		}
		return xszdList;
	}

}
