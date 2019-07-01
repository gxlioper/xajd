package xgxt.pjpy.nbty.wmqs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.rcsw.kqgl.xskq.RcswKqglXskqService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.xsgygl.dao.gyglDao;

import com.zfsoft.basic.BasicService;

public class NbtyWqmsService {
	
	
	/**
	 * method setZsInfo
	 * 根据学号判断学生是否已分配寝室，并获取住宿信息
	 * @param xh
	 * @return
	 */
	public boolean setZsInfo(HttpServletRequest request,String xh)
	{
		
		NbtyWmqsDao nbtyWmqsDao =new NbtyWmqsDao();
		HashMap<String, String>map=new HashMap<String, String>();
		boolean blog=true;
		//没有寝室的学生
		if("".equals(nbtyWmqsDao.getZsInfo(xh).get("xh"))
				|| null==nbtyWmqsDao.getZsInfo(xh).get("xh")){
			blog=false;
		}
		HashMap<String, String>firstMap=new HashMap<String, String>();
		HashMap<String, String>secundMap=new HashMap<String, String>();
		//根据学号，获取某寝室的具体信息
		List<HashMap<String, String>>list=nbtyWmqsDao.getXszsInfo(xh);
		String xydm="";
		String xymc="";
		String zydm="";
		String zymc="";
		String bjdm="";
		String bjmc="";
		boolean xyBlog=true;
		boolean zyBlog=true;
		boolean bjBlog=true;
		for(int i=0;i<list.size();i++){
			firstMap=list.get(i);
			xyBlog=true;
			zyBlog=true;
			bjBlog=true;
			for(int j=i+1;j<list.size();j++){
				secundMap=list.get(j);
				if(firstMap.get("xydm").equals(secundMap.get("xydm"))){
					xyBlog=false;
				}
				if(firstMap.get("zydm").equals(secundMap.get("zydm"))){
					zyBlog=false;
				}
				if(firstMap.get("bjdm").equals(secundMap.get("bjdm"))){
					bjBlog=false;
				}
			}
			if(xyBlog){
				if("".equals(xydm)){
				xydm+=firstMap.get("xydm");
				xymc+=firstMap.get("xymc");
				}else{
					xydm+="&cat"+firstMap.get("xydm");
					xymc+=","+firstMap.get("xymc");
				}
			}
			if(zyBlog){
				if("".equals(zydm)){
					zydm+=firstMap.get("zydm");
					zymc+=firstMap.get("zymc");
				}else{
					zydm+="&cat"+firstMap.get("zydm");
					zymc+=","+firstMap.get("zymc");
				}
			}
			if(bjBlog){
				if("".equals(bjdm)){
					bjdm+=firstMap.get("bjdm");
					bjmc+=firstMap.get("bjmc");
				}else{
					bjdm+="&cat"+firstMap.get("bjdm");
					bjmc+=","+firstMap.get("bjmc");
				}
			}
		}
		HashMap<String, String>hashMap=new HashMap<String, String>();
		hashMap.put("xydm", xydm);
		hashMap.put("xymc", xymc);
		hashMap.put("zydm", zydm);
		hashMap.put("zymc", zymc);
		hashMap.put("bjdm", bjdm);
		hashMap.put("bjmc", bjmc);
		hashMap.putAll(nbtyWmqsDao.getZsInfo(xh));
		request.setAttribute("map", hashMap);
		return blog;
	}
	
	//获取登陆辅导员管辖班级
	public static boolean getBjByFdy(String userName,String bjdm){
		boolean blog=false;
		List<Map<String,String>>list=NbtyWmqsDao.getBjByFdy(userName);
		for(int i=0;i<list.size();i++){
			Map<String,String>map=list.get(i);
			if(bjdm.indexOf(map.get("bjdm"))>=0){
				blog=true;
				break;
			}
		}
		return blog;
	}
	
	//获取登陆班主任管辖的班级
	public static boolean getBjByBzr(String userName,String bjdm){
		boolean blog=false;
		List<HashMap<String, String>>list=NbtyWmqsDao.getBjByBzr(userName);
		for(int i=0;i<list.size();i++){
			Map<String,String>map=list.get(i);
			if(bjdm.indexOf(map.get("bjdm"))>=0 ){
				blog=true;
				break;
			}
		}
		return blog;
	}
	
	
	//判断是否是登陆用户所管辖的班级
	public boolean getQsByJs(HttpServletRequest request,String userName,String userType,
			String userDep,String isFdy,String isBzr){
		boolean blog=false;
		String bjdm=request.getParameter("save_bjdm");
		String xydm=request.getParameter("save_xydm");
		if("true".equalsIgnoreCase(isFdy)){
			return NbtyWqmsService.getBjByFdy(userName, bjdm);
		}else if("true".equalsIgnoreCase(isBzr)){
			return NbtyWqmsService.getBjByBzr(userName, bjdm);
		}else if("xy".equalsIgnoreCase(userType)){
			if(xydm.indexOf(userDep)>=0 || xydm.equals(userDep)){
				blog=true;
			}
		}else if("xx".equalsIgnoreCase(userType)){
			blog=true;
		}else if("admin".equalsIgnoreCase(userType)){
			blog=true;
		}
		
		return blog;
	}
	
	public boolean getZsInfo(String lddm,String qsh)
	{
		NbtyWmqsDao nbtyWmqsDao =new NbtyWmqsDao();
		HashMap<String, String>map=new HashMap<String, String>();
		boolean blog=true;
		//没有寝室的学生
		if(!nbtyWmqsDao.getZsInfo(lddm,qsh)){
			blog=false;
		}
		return blog;
	}
	
	//根据学号获取住宿信息
	public Map<String, String> getZsInfo(String xh)
	{
		NbtyWmqsDao nbtyWmqsDao =new NbtyWmqsDao();
		return nbtyWmqsDao.getZsInfo(xh);
	}
	
	
	//获取楼栋列表
	public static void setLdlbInfo(HttpServletRequest request) {
		NbtyWmqsDao nbtyWmqsDao =new NbtyWmqsDao();
		request.setAttribute("ldlbList", nbtyWmqsDao.getLdlbInfo());
	}
	
	
	//获取寝室列表
	public static void setQslbInfo(HttpServletRequest request) {
		NbtyWmqsDao nbtyWmqsDao =new NbtyWmqsDao();
		request.setAttribute("qslbList", nbtyWmqsDao.getQslbInfo());
	}
	
	
	//DWR获取学院，专业，班级名称和代码
	public static  HashMap<String, String> getZsxxList(String lddm,String qsh)
	{
		NbtyWmqsDao nbtyWmqsDao =new NbtyWmqsDao();
		HashMap<String, String>firstMap=new HashMap<String, String>();
		HashMap<String, String>secundMap=new HashMap<String, String>();
		List<HashMap<String, String>>list=nbtyWmqsDao.getXszsInfo(lddm,qsh);
		String xydm="";
		String xymc="";
		String zydm="";
		String zymc="";
		String bjdm="";
		String bjmc="";
		boolean xyBlog=true;
		boolean zyBlog=true;
		boolean bjBlog=true;
		for(int i=0;i<list.size();i++){
			firstMap=list.get(i);
			xyBlog=true;
			zyBlog=true;
			bjBlog=true;
			for(int j=i+1;j<list.size();j++){
				secundMap=list.get(j);
				if(firstMap.get("xydm").equals(secundMap.get("xydm"))){
					xyBlog=false;
				}
				if(firstMap.get("zydm").equals(secundMap.get("zydm"))){
					zyBlog=false;
				}
				if(firstMap.get("bjdm").equals(secundMap.get("bjdm"))){
					bjBlog=false;
				}
			}
			if(xyBlog){
				if("".equals(xydm)){
				xydm+=firstMap.get("xydm");
				xymc+=firstMap.get("xymc");
				}else{
					xydm+="&cat"+firstMap.get("xydm");
					xymc+=","+firstMap.get("xymc");
				}
			}
			if(zyBlog){
				if("".equals(zydm)){
					zydm+=firstMap.get("zydm");
					zymc+=firstMap.get("zymc");
				}else{
					zydm+="&cat"+firstMap.get("zydm");
					zymc+=","+firstMap.get("zymc");
				}
			}
			if(bjBlog){
				if("".equals(bjdm)){
					bjdm+=firstMap.get("bjdm");
					bjmc+=firstMap.get("bjmc");
				}else{
					bjdm+="&cat"+firstMap.get("bjdm");
					bjmc+=","+firstMap.get("bjmc");
				}
			}
		}
		
		HashMap<String, String>hashMap=new HashMap<String, String>();
		hashMap.put("xydm", xydm);
		hashMap.put("xymc", xymc);
		hashMap.put("zydm", zydm);
		hashMap.put("zymc", zymc);
		hashMap.put("bjdm", bjdm);
		hashMap.put("bjmc", bjmc);
		return hashMap;
	}
	
	
	//获取批量审核时要修改的字段
	public Map<String,String>getValueMap(String isBzr,String isFdy,
			String userType,String shjg){
		
		String shzd="";
		String shsj="";
		HashMap<String, String> valueMap = new HashMap<String,String>();
		if("true".equalsIgnoreCase(isBzr)){
			shzd="bzrsh";
			shsj="bzrshsj";
		}else if("true".equalsIgnoreCase(isFdy)){
			shzd="fdysh";
			shsj="fdyshsj";
		}else if("xy".equalsIgnoreCase(userType)){
			shzd="xysh";
			shsj="xyshsj";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shzd="xxsh";
			shsj="xxshsj";
		}
		valueMap.put(shzd,shjg );
		valueMap.put(shsj, GetTime.getNowTime2());
		return valueMap;
	}
	
	
	//审核时的数据显示和复选框的disabled属性
	public void disabled(HttpServletRequest request,String userType,
			String isFdy,String isBzr){
		if("true".equalsIgnoreCase(isBzr)){
			request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
		}else if("true".equalsIgnoreCase(isFdy)){	
			request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
		}else if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
			request.setAttribute("annexTerm", " and fdysh='通过' and bzrsh='通过' ");
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("annexTerm", " and xysh='通过' ");
			request.setAttribute("clientColumns", "(case xxsh when '通过' then '' else '' end)disabled,");
		}
	}
	
	
	//设置审核时复选框disabled属性
	public void disabled(String userOnLine,String userType,HttpServletRequest request,
			String isBzr,String isFdy){
		if("true".equalsIgnoreCase(isBzr)){						
			request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
		}else if("true".equalsIgnoreCase(isFdy)){	
			request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
		}else if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then '' else '' end)disabled,");
	
		}
	}
	
	
	//根据不同登陆权限，设置审核结果
	public void setShZd(NbtyWmqsForm nbtyWmqsForm,String isBzr,
			String isFdy,String userType,HashMap<String,String> rs){
		if("true".equalsIgnoreCase(isBzr)){
			nbtyWmqsForm.setSave_bzrsh(rs.get("bzrsh"));
		}else if("true".equalsIgnoreCase(isFdy)){								//加载单个审核时的审核结果
			nbtyWmqsForm.setSave_fdysh(rs.get("fdysh"));	
		}else if("xy".equalsIgnoreCase(userType)){
			nbtyWmqsForm.setSave_xysh(rs.get("xysh"));	
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			nbtyWmqsForm.setSave_xxsh(rs.get("xxsh"));	
		}
	}
	
	
	public String canWrite(String write,HashMap<String,String>rs,
			String isFdy,String isBzr,String userType){
		if("true".equalsIgnoreCase(isBzr)){						//班主任
			if("通过".equals(rs.get("xysh"))){		//如果登陆的是班主任，并且学院审核是通过，班主任只能查看
				write="no";
			}
		}else if("true".equalsIgnoreCase(isFdy)){			//辅导员
			if("通过".equals(rs.get("xysh"))){		//如果登陆的是辅导员，并且学院审核是通过
				write="no";
			}
		}else if("xy".equalsIgnoreCase(userType)){			//学院登陆)
			if("通过".equals(rs.get("xxsh"))){		//如果登陆的是学院，并且学校审核是通过
				write="no";
			}
		}
		return write;
	}
	
	public void studentQs(){
		
	}
	
	public List<Map<String,String>> getStZsInfo(HttpServletRequest request,String xh){
		NbtyWmqsDao dao=new NbtyWmqsDao();
		List list=dao.getStZsInfo(xh);
		String[]topTr={"xn","ldmc","cs","qsh","bzrsh","fdysh","xysh","xxsh"};
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", list.size());
		return list;
	}
	
	//学生用户的查询条件
	public void studentQuery(NbtyWmqsForm nbtyWmqsForm,Map<String,String> map,
			HttpServletRequest request){
		
		nbtyWmqsForm.setCs(map.get("cs"));
		nbtyWmqsForm.setLddm(map.get("lddm"));
		nbtyWmqsForm.setQsh(map.get("qsh"));
		nbtyWmqsForm.setBjdm(map.get("bjdm"));
		nbtyWmqsForm.setXydm(map.get("xydm"));
		nbtyWmqsForm.setZydm(map.get("zydm"));
		request.setAttribute("map", map);
	}
	
	public void getLdLcQshList(HttpServletRequest request){
		gyglDao.getLdLcQshList(request);
	}
	
	
	
	public void getStuByTch(HttpServletRequest request,NbtyWmqsForm nbtyWmqsForm,String userName) throws Exception{
		BasicService basicService = new BasicService();
	
		StringBuilder sql=new StringBuilder();
		NbtyWmqsModel model=new NbtyWmqsModel();
		BeanUtils.copyProperties(model,nbtyWmqsForm);
		CommonQueryDAO dao=new CommonQueryDAO();
		MakeQuery makeQuery=new MakeQuery();
		String []colList={"xn","lddm","cs","qsh","bjdm","bzrsh","fdysh","xysh","xxsh" };
		String []colLikeList={"xydm","zydm","bjdm"};
		makeQuery.makeQuery(colList, colLikeList, model);
		makeQuery.getInputList();
		makeQuery.getQueryString();
		int length=makeQuery.getInputList().length;
		String query="";
		sql.append("select a.*,");
		sql.append(request.getAttribute("clientColumns").toString());
		sql.append(" Rownum r  from view_nbty_wmqsdjb a ");
		sql.append(makeQuery.getQueryString());
		if(null!=request.getAttribute("annexTerm")){
			sql.append(request.getAttribute("annexTerm").toString());
		}
		String shiFdy=request.getParameter("isFdy");
		List<String> al=Arrays.asList(makeQuery.getInputList());
		ArrayList<String> temp =  new ArrayList<String>(); 
		temp.addAll(al); 	
		if("true".equalsIgnoreCase(shiFdy)){
				length+=1;
				sql.append(" and exists(select 1 from fdybjb b where a.bjdm like '%' || b.bjdm || '%' and b.zgh=?) ");
				temp.add(userName);
		}
		String []inputValue=new String[length];
		inputValue=temp.toArray(inputValue);
		
		
		List<String[]>allList=new ArrayList<String[]>();
		String[]outputValue=new String[]{"pk","disabled","xn","ldmc","cs","qsh","bzrsh","fdysh","xysh","xxsh"};
		allList.addAll(dao.commonQuery(sql.toString(),query ,inputValue,outputValue,model));
	
		request.setAttribute("topTr", basicService.getTopTr("view_rcsw_kqgl_xskq",new String[]{"编号","disabled","学年","楼栋名称","楼层","寝室号","班主任审核","辅导员审核","学院审核","学校审核"}));
		request.setAttribute("rs",allList);
		request.setAttribute("rsNum",allList.size());
		
	}
}
