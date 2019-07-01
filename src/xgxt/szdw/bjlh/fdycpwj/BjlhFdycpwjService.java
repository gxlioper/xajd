package xgxt.szdw.bjlh.fdycpwj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhForm;
import xsgzgl.comm.BasicService;

public class BjlhFdycpwjService extends BasicService{

	BjlhFdycpwjDAO dao=new BjlhFdycpwjDAO();
	
	/**
	 * 获取表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] topTr=new String[]{};
		if("cpwjgl".equals(type)){//测评问卷管理
			topTr=new String[]{"学年","问卷ID","问卷名称","是否启用","发布日期","发布人"};
		}else if("cpwjst".equals(type)){//测评问卷试题
			topTr=new String[]{"试题","类型"};
		}else if("cpwjtj".equals(type)){//测评问卷统计
			topTr=new String[]{"考核学年","职工号","姓名","所在部门","学生总数","已答卷人数"};
		}
		return topTr;
	}
	
	/**
	 * 获取测评问卷列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getCpwjList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		return dao.getCpwjList(model,request);
	}
	
	/**
	 * 获取测评问卷单条记录
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjOne(BjlhFdycpwjForm model){
		return dao.getCpwjOne(model);
	}
		
	/**
	 * 保存测评问卷信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCpwjInfo(BjlhFdycpwjForm model,String type) throws Exception{
		return dao.saveCpwjInfo(model,type);
	}
	
	/**
	 * 删除测评问卷信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		return dao.deleteCpwjInfo(model);
	}
	
	/**
	 * 验证测评问卷权限
	 * @param model
	 * @return
	 */
	public String checkCpwjQx(BjlhFdycpwjForm model){
		return dao.checkCpwjQx(model);
	}
	
	/**
	 * 复制测评问卷信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean copyCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		return dao.copyCpwjInfo(model);
	}
	
	/**
	 * 是否启用测评问卷
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String sfqyCpwj(BjlhFdycpwjForm model) throws Exception{
		return dao.sfqyCpwj(model);
	}
	
	/**
	 * 获取测评问卷试题列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getCpwjStList(BjlhFdycpwjForm model){
		return dao.getCpwjStList(model);
	}
	
	/**
	 * 保存测评文件试题信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveCpwjStxx(BjlhFdycpwjForm model,String type) throws Exception{
		return dao.saveCpwjStxx(model,type);
	}
	
	/**
	 * 获取测评问卷试题信息列表
	 * @param model
	 * @return
	 */
//	public List<HashMap<String,String>> getCpwjStxxList(BjlhFdycpwjForm model){
//		return dao.getCpwjStxxList(model);
//	}
	
	/**
	 * 获取选项列表
	 * @param model
	 * @param type
	 * @return
	 */
	public List<HashMap<String,String>> getXxlist(BjlhFdycpwjForm model,String type){
		return dao.getXxlist(model,type);
	}
	
	/**
	 * 获取一个问卷选项信息分试题的Map 以方便页面数据的展现--为了简化页面，还是采取在后台直接处理成html的字符串，
	 * @param model
	 * @return
	 */
	public HashMap<String,List<HashMap<String,String>>> getXxMapListMap(BjlhFdycpwjForm model){
		HashMap<String,List<HashMap<String,String>>> map=new HashMap<String, List<HashMap<String,String>>>();
		List<HashMap<String,String>> stList=getCpwjStList(model);
		List<HashMap<String,String>> xxList=getXxlist(model,"wj");
		if(stList==null||stList.size()==0||xxList==null||xxList.size()==0){
			return map;
		}
		int xxindex=0;
		for(int i=0;i<stList.size();i++){
			if("2".equals(stList.get(i).get("stlx"))){//如果是简答题直接跳过
				continue;
			}
			List<HashMap<String,String>> stxx=new ArrayList<HashMap<String,String>>();
			for(int j=xxindex;j<xxList.size();j++){
				if(!stList.get(i).get("stid").equals(xxList.get(j).get("stid"))){//如果试题id不一致时，直接进行下一个试题
					break;
				}
				stxx.add(xxList.get(j));
				xxindex++;
			}
			map.put(stList.get(i).get("stid"), stxx);
		}
		return map;
	}
	
	public List<HashMap<String,String>> getStxxXxHtml(BjlhFdycpwjForm model,List<HashMap<String,String>> stList){
		List<HashMap<String,String>> xxList=getXxlist(model,"wj");
		HashMap<String,String> daMap=getWjStDaMap(model);//答案map
		String da;//答案
		if(stList==null||stList.size()==0||xxList==null||xxList.size()==0){
			return stList;
		}
		int xxindex=0;//选项循环索引
		int dhxxgs=0;//单行选项个数
		int yxhxxgs=0;//已循环选项个数
		String stlx;//试题类型
		HashMap<String,String> st;//试题
		HashMap<String,String> xx;//选项
		
		for(int i=0;i<stList.size();i++){
			st=stList.get(i);
			stlx=st.get("stlx");
			if("1".equals(stlx)){//单选题
				dhxxgs=Integer.parseInt(st.get("dhxxgs"));
				yxhxxgs=1;
				StringBuffer xxHtml=new StringBuffer();
				for(int j=xxindex;j<xxList.size();j++){
					xx=xxList.get(j);
					if(!st.get("stid").equals(xx.get("stid"))){//如果试题id不一致时，直接进行下一个试题
						break;
					}
					da=Base.chgNull(daMap.get(st.get("stid")+"-"+xx.get("xxid")), "", 0);
					xxHtml.append(xx.get("zfbm")+"、");//字符编码
					xxHtml.append("<input type='radio' name='st_"+st.get("stid")+"' value='"+xx.get("xxid")+"' "+da+"/>"+xx.get("xxnr"));
					xxHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					if(yxhxxgs%dhxxgs==0){//用于控制换行
						xxHtml.append("<br/>");
					}
					yxhxxgs++;
					xxindex++;
				}
				st.put("xxHtml", xxHtml.toString());
			}else if("2".equals(stlx)){//简答题
				da=Base.chgNull(daMap.get(st.get("stid")+"-"), "", 0).replace("checked=\"checked\"", "");
				st.put("xxHtml", "<input type='text' name='st_"+st.get("stid")+"' value='"+da+"' maxlength='500' style='width:600px;'/>");
			}
		}
		return stList;
	}
	
	public HashMap<String,String> getWjStDaMap(BjlhFdycpwjForm model){
		List<HashMap<String,String>> daList=dao.getWjStDaList(model);
		HashMap<String,String> daMap=new HashMap<String, String>();
		if(daList==null||daList.size()==0){
			return daMap;
		}
		String stlx;
		for(int i=0;i<daList.size();i++){
			stlx=daList.get(i).get("stlx");
			if("1".equals(stlx)){
				daMap.put(daList.get(i).get("stxx"),"checked=\"checked\"");
			}else if("2".equals(stlx)){
				daMap.put(daList.get(i).get("stxx"), daList.get(i).get("wbda"));
			}
		}
		return daMap;
	}
	
	/**
	 * 获取学生是否已作答问卷
	 * @param model
	 * @return
	 */
	public boolean getXsWjstSfzd(BjlhFdycpwjForm model){
		return dao.getXsWjstSfzd(model);
	}
	
	/**
	 * 获取测评问卷试题单条记录
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjStxxOne(BjlhFdycpwjForm model){
		return dao.getCpwjStxxOne(model);
	}
	
	/**
	 * 保存测评问卷答案
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCpwjDa(HttpServletRequest request,BjlhFdycpwjForm model) throws Exception{
		boolean b=false;
		List<HashMap<String,String>> stList=getCpwjStList(model);
		if(stList==null||stList.size()==0){
			return b;
		}
		String xh=model.getXh();//学号
		String wjid=model.getWjid();//问卷id
		
		//String fdy=dao.getFdyInfo(model.getXh()).get("zgh");//辅导员id
		String fdy = model.getFdyid();
		HashMap<String,String> st;//试题
		String stlx;//试题类型
		String stid;//试题id
		String da;//答案
		
		List<String> sqls=new ArrayList<String>();
		sqls.add("delete from xg_szdw_bjlh_fdycpwjdab where xh='"+xh+"' and wjid='"+wjid+"' and fdyid='" + fdy + "'");
		for(int i=0;i<stList.size();i++){
			st=stList.get(i);
			stlx=st.get("stlx");
			stid=st.get("stid");
			da=request.getParameter("st_"+stid);
			if("1".equals(stlx)){//单选题
				sqls.add("insert into xg_szdw_bjlh_fdycpwjdab(xh,wjid,stid,xxid,fdyid) values('"+xh+"','"+wjid+"','"+stid+"','"+da+"','"+fdy+"')");
			}else if("2".equals(stlx)){//简答题
				sqls.add("insert into xg_szdw_bjlh_fdycpwjdab(xh,wjid,stid,wbda,fdyid) values('"+xh+"','"+wjid+"','"+stid+"','"+da+"','"+fdy+"')");
			}
		}
		
		return new CommDAO().saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * 获取试题选项统计信息HTML
	 * @param model
	 * @param stList
	 * @return
	 */
	public List<HashMap<String,String>> getStxxTjxxHtml(BjlhFdycpwjForm model,List<HashMap<String,String>> stList){
		List<HashMap<String,String>> xxList=dao.getWjstTjInfoList(model);//getXxlist(model,"wj")
		if(stList==null||stList.size()==0||xxList==null||xxList.size()==0){
			return stList;
		}
		int xxindex=0;//选项循环索引
		String stlx;//试题类型
		HashMap<String,String> st;//试题
		HashMap<String,String> xx;//选项
		
		for(int i=0;i<stList.size();i++){
			st=stList.get(i);
			stlx=st.get("stlx");
			if("1".equals(stlx)){//单选题
				StringBuffer xxHtml=new StringBuffer();
				for(int j=xxindex;j<xxList.size();j++){
					xx=xxList.get(j);
					if(!st.get("stid").equals(xx.get("stid"))){//如果试题id不一致时，直接进行下一个试题
						break;
					}
					xxHtml.append(xx.get("zfbm")+"、");//字符编码
					xxHtml.append(xx.get("xxnr"));
					xxHtml.append("(人数："+xx.get("xxrs")+"/"+xx.get("xxrsbfb")+")");
					xxHtml.append("<br/>");
					xxindex++;
					st.put("dtrs", xx.get("dtrs"));
				}
				st.put("xxHtml", xxHtml.toString());
			}else{
				//如果是非简答题，直接移除
				stList.remove(i);
				i--;
			}
		}
		return stList;
	}
	
	public List<HashMap<String,String>> getAddXnList(){
		return dao.getAddXnList();
	}
	
	/**
	 * 获取测评问卷统计查询列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getCpwjTjQueryList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		return dao.getCpwjTjQueryList(model,request);
	}
	
	/**
	 * 获取学生的辅导员信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(BjlhFdycpwjForm model){
		return dao.getFdyInfo(model.getXh());
	}
	
	/**
	 * 获得参数设置表中的默认设置
	 * @return
	 */
	public HashMap<String, String> getMrsz() {
		return dao.getMrsz();
	}

	public String getDate(){
		return dao.getNowTime("YYYYMMDD");
	}

	/** 
	 * @描述:获取学生需要测评的教师信息
	 * @作者：cmj
	 * @日期：2013-12-13 下午04:29:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCpFdyList(BjlhFdycpwjForm myForm) {
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象辅导员
			lx="辅导员";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象班主任
			lx="班主任";
		}
		
		return dao.getCpFdyList(myForm,lx);
	}
	
	/**
	 * 获取测评问卷是否已作答问卷
	 * @param model
	 * @return
	 */
	public boolean getCpwjSfzd(BjlhFdycpwjForm myForm){
		return dao.getCpwjSfzd(myForm);
	}
	
}
