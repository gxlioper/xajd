package xsgzgl.gygl.ldgl;

import org.apache.struts.util.MessageResources;
import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LdglServices extends GyglNewService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	private LdglDao dao=new LdglDao();
	/**
	 * 楼栋层数下拉列表
	 * @return
	 */
	public List<HashMap<String,String>> getLdcsList(){
		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		for(int i=1;i<=30;i++){//当前设置成20层，若不足再修改
			HashMap<String,String> map=new HashMap<String, String>();
			map.put("dm", i+"");
			map.put("mc", i+"");
			list.add(map);
		}
		return list;
	}
	/**
	 * 获取楼栋列表表头
	 * @param model
	 * @return
	 */
	public String[] getTopTr(LdglModel model){
		Base.GYPZYQ_KEY = message.getMessage("lable.yuanqu");
		String[] outPutString=null;
		if(Base.xxdm.equals("10351")){ // 温州大学
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","校区名称",Base.GYPZYQ_KEY+"代码","管理员","备注"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","校区名称","管理员","备注"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号",Base.GYPZYQ_KEY+"名称","管理员","备注"};
			}else{
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","管理员","备注"};
			}
		}else if(Base.xxdm.equals("12861")){ // 浙江机电职业技术学院
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","校区名称",Base.GYPZYQ_KEY+"代码","管理员","辅导员","备注"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","校区名称","管理员","辅导员","备注"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号",Base.GYPZYQ_KEY+"名称","管理员","辅导员","备注"};
			}else{
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","管理员","辅导员","备注"};
			}
		}else{
			if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","是否含0层","校区名称",Base.GYPZYQ_KEY+"代码","管理员","备注"};
			}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","是否含0层","校区名称","管理员","备注"};
			}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","是否含0层",Base.GYPZYQ_KEY+"名称","管理员","备注"};
			}else{
				outPutString =new String[]{"楼栋代码","楼栋名称","楼栋性别","楼栋层数","起始层号","是否含0层","管理员","备注"};
			}
		}
		return outPutString;
	}
	
	public List<String[]> getLdglInfoList(LdglModel model) throws Exception{
		
		return dao.getLdglInfoList(model);
	}
	/**
	 * 保存楼栋信息
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean saveLdxx(HttpServletRequest request,LdglModel model){
		String qsh_ws=Base.chgNull(request.getParameter("qsh_ws"), "3", 0);//寝室号位数
		String qsh_sfhszm=Base.chgNull(request.getParameter("qsh_sfhszm"), "否", 0);//寝室号是否含首字母
		String qsh_szm=Base.chgNull(request.getParameter("qsh_szm"), "", 0);//寝室号首字母
		String qsh_ws_zero="";//层号位数补零，由寝室号位数决定
		
		
		String[] ch=request.getParameterValues("ch");//层号
		String[] scqss=request.getParameterValues("scqss");//生成寝室数
		String[] cws=request.getParameterValues("cws");//床位数
		String[] sfbz=request.getParameterValues("sfbz");//收费标准
//		String[] qsxb=request.getParameterValues("qsxb");//寝室性别
		ArrayList<String> sqls=new ArrayList<String>();
		//楼栋基本信息
		sqls.add("insert into xg_gygl_new_ldxxb values('"+
				model.getLddm()+"','"+model.getLdmc()+"','"+model.getLdxb()+"','"+
				model.getLdcs()+"','"+model.getQsch()+"','"+model.getSfhlc()+"','"+
				model.getXqdm()+"','"+model.getYqdm()+"','"+model.getBz()+"')");
		
		String qsh=null;//寝室号
		String qsxbOne=null;//寝室性别
		int ch_num=0;//层号
		int t=ch.length;
		for(int i=0;i<ch.length;i++){
			t=t-1;
			//处理寝室号位数补零
			//生成4位超过10层报错问题 by yyp
			if(/*Integer.parseInt(ch[i])<9&&*/"4".equals(qsh_ws)){
				qsh_ws_zero="0";
			}else{
				qsh_ws_zero="";
			}
			for(int j=0;j<Integer.parseInt(scqss[i]);j++){
				//处理寝室号
				ch_num=Integer.parseInt(ch[i]);
				if(ch_num<0){
					if(j<9){
						qsh="B"+(-ch_num)+qsh_ws_zero+"0"+(j+1);
					}else{
						qsh="B"+(-ch_num)+qsh_ws_zero+""+(j+1);
					}
				}else{
					if("12301".equals(Base.xxdm)){
						if (j <= 9) {
							qsh = (ch_num) + qsh_ws_zero + "0" + (j);
						} else {
							qsh = (ch_num) + qsh_ws_zero + "" + (j);
						}
					}else{
						if (j < 9) {
							qsh = (ch_num) + qsh_ws_zero + "0" + (j + 1);
						} else {
							qsh = (ch_num) + qsh_ws_zero + "" + (j + 1);
						}
					}
				}
				if("是".equals(qsh_sfhszm)&&qsh_szm!=null&&qsh_szm.trim().length()>0){
					qsh=qsh_szm.trim()+qsh;
				}
				//处理寝室性别
				if("混住".equals(model.getLdxb())){
					qsxbOne=request.getParameter("qsxb"+t);//qsxb[i];
				}else{
					qsxbOne=model.getLdxb();
				}
				//插入寝室信息
				sqls.add("insert into xg_gygl_new_qsxxb(lddm,qsh,ch,qsxb,cws,sfbz) values('"+
						model.getLddm()+"','"+qsh+"','"+ch[i]+"','"+qsxbOne+"','"+cws[i]+"','"+sfbz[i]+"')");
				
				//插入床位信息
				for(int k=1;k<=Integer.parseInt(cws[i]);k++){
					sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
							+"','"+qsh+"','"+k+"')");
				}
				
			}
		}
		return dao.saveLdxx(request, model,sqls.toArray(new String[]{}));
	}
	/**
	 * 获取单个楼栋信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getLdxxOne(LdglModel model){
		return dao.getLdxxOne(model);
	}
	/**
	 * 获取楼栋统计信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getLdtjxx(LdglModel model){
		return dao.getLdtjxx(model);
	}
	/**
	 * 修改楼栋信息
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean updateLdxx(HttpServletRequest request,LdglModel model){
		String newldmark=request.getParameter("newldmark");
		String[] ch=request.getParameterValues("ch");//层号
		List<String> chList = new ArrayList<String>();
		ArrayList<String> sqls=new ArrayList<String>();
		//修改楼栋基本信息
		
		String qsxbOne=null;//寝室性别
		if("yes".equals(newldmark)){//重新生成楼栋寝室床位信息

			String qsh_ws=Base.chgNull(request.getParameter("qsh_ws"), "3", 0);//寝室号位数
			String qsh_sfhszm=Base.chgNull(request.getParameter("qsh_sfhszm"), "否", 0);//寝室号是否含首字母
			String qsh_szm=Base.chgNull(request.getParameter("qsh_szm"), "", 0);//寝室号首字母
			String qsh_ws_zero="";//层号位数补零，由寝室号位数决定
			
			sqls.add("update xg_gygl_new_ldxxb set ldmc='"+model.getLdmc()+"',ldxb='"+model.getLdxb()+"'," +
					"bz='"+model.getBz()+"',ldcs='"+model.getLdcs()+"',qsch='"+model.getQsch()+"'," +
					"sfhlc='"+model.getSfhlc()+"' where lddm='"+model.getLddm()+"'");

			List<String> paramList = new ArrayList<String>();
			paramList.add(model.getLddm());
			String countSql = "select count(1) num from view_xg_gygl_new_cwxx where lddm=? and (qsxydm is not null or " +
					" qsnj is not null or xydm is not null or nj is not null or xh is not null)";

			//可增加层数功能
			if("1".equals(model.getSfkzjlc())){
				countSql += " and to_number(ch) > ?";
				paramList.add(model.getOriginalLdcs());

				for(String c:ch){
					if(Integer.parseInt(c)>Integer.parseInt(model.getOriginalLdcs())){
						chList.add(c);
					}
				}
				ch = chList.toArray(new String[]{});
			}

			String num=dao.getOneRs(countSql, paramList.toArray(new String[]{}), "num");

			if(!"0".equals(num)){//数据出异常情况不予删除修改
				return false;
			}

			//可增加楼层时不做删除
			if(!"1".equals(model.getSfkzjlc())){
				String delCwSql = "delete from xg_gygl_new_cwxxb where lddm='"+model.getLddm()+"'";
				String delQsSql = "delete from xg_gygl_new_qsxxb where lddm='"+model.getLddm()+"'";

				sqls.add(delCwSql);
				sqls.add(delQsSql);
			}

			String[] scqss=request.getParameterValues("scqss");//生成寝室数
			String[] cws=request.getParameterValues("cws");//床位数
			String[] sfbz=request.getParameterValues("sfbz");//收费标准
			
			String qsh=null;//寝室号
			int ch_num=0;//层号
			for(int i=0;i<ch.length;i++){
				//处理寝室号位数补零
				if("4".equals(qsh_ws)){
					qsh_ws_zero="0";
				}else{
					qsh_ws_zero="";
				}				
				for(int j=0;j<Integer.parseInt(scqss[i]);j++){
					//处理寝室号
					ch_num=Integer.parseInt(ch[i]);
					if(ch_num<0){
						if(j<9){
							qsh="B"+(-ch_num)+qsh_ws_zero+"0"+(j+1);
						}else{
							qsh="B"+(-ch_num)+qsh_ws_zero+""+(j+1);
						}
					}else{
						if("12301".equals(Base.xxdm)){
							if (j <= 9) {
								qsh = (ch_num) + qsh_ws_zero + "0" + (j);
							} else {
								qsh = (ch_num) + qsh_ws_zero + "" + (j);
							}
						}else{
							if (j < 9) {

								qsh = (ch_num) + qsh_ws_zero + "0" + (j + 1);
							} else {
								qsh = (ch_num) + qsh_ws_zero + "" + (j + 1);
							}
						}
						
					}
					if("是".equals(qsh_sfhszm)&&qsh_szm!=null&&qsh_szm.trim().length()>0){
						qsh=qsh_szm.trim()+qsh;
					}
					//处理寝室性别
					if("混住".equals(model.getLdxb())){
						qsxbOne=request.getParameter("qsxb"+i);//qsxb[i];
					}else{
						qsxbOne=model.getLdxb();
					}

					//插入寝室信息
					sqls.add("insert into xg_gygl_new_qsxxb(lddm,qsh,ch,qsxb,cws,sfbz) values('"+
							model.getLddm()+"','"+qsh+"','"+ch[i]+"','"+qsxbOne+"','"+cws[i]+"','"+sfbz[i]+"')");

					//插入床位信息
					for(int k=1;k<=Integer.parseInt(cws[i]);k++){
						sqls.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+model.getLddm()
								+"','"+qsh+"','"+k+"')");
					}

				}
			}
		}else{
			sqls.add("update xg_gygl_new_ldxxb set ldmc='"+model.getLdmc()+"',ldxb='"+model.getLdxb()+"'," +
					"bz='"+model.getBz()+"' where lddm='"+model.getLddm()+"'");
			if(ch!=null&&ch.length>0){//若层号不为空，则说明楼栋的寝室和床位需重新生成
				for(int i=0;ch!=null&&i<ch.length;i++){
					//处理寝室性别
					if("混住".equals(model.getLdxb())){
						qsxbOne=request.getParameter("qsxb_"+ch[i]+"_男");
						if(!Base.isNull(qsxbOne)){
							sqls.add("update xg_gygl_new_qsxxb set qsxb='"+qsxbOne+"' where qsxb='男' and ch='"+ch[i]+"' and lddm='"+model.getLddm()+"'");
						}
						
						qsxbOne=request.getParameter("qsxb_"+ch[i]+"_女");
						if(!Base.isNull(qsxbOne)){
							sqls.add("update xg_gygl_new_qsxxb set qsxb='"+qsxbOne+"' where qsxb='女' and ch='"+ch[i]+"' and lddm='"+model.getLddm()+"'");
						}
					}else{
						qsxbOne=model.getLdxb();
						sqls.add("update xg_gygl_new_qsxxb set qsxb='"+qsxbOne+"' where lddm='"+model.getLddm()+"'");
						break;
					}
				}
			}
		}
		return dao.updateLdxx(request, model,sqls.toArray(new String[]{}));
	}
	/**
	 * 删除楼栋信息
	 * @param request
	 * @param model
	 * @return
	 */
	public boolean deleteLdxx(HttpServletRequest request,LdglModel model){
		return dao.deleteLdxx(request, model);
	}
	
	/**
	 * 查询用户
	 * @param form
	 * @return
	 */
	public List<String[]> queryYh(LdglForm form){
		return dao.queryYh(form);
	}
	
	public boolean saveFdyFp(String[] yhs,String[] lds){
		List<String[]> params = new ArrayList<String[]>(); 
		List<String[]> delParams = new ArrayList<String[]>();
		
		for(int i=0; i<lds.length; i++){
			delParams.add(new String[]{lds[i]});
			for(int j=0; j<yhs.length; j++){
				params.add(new String[]{yhs[j],lds[i]});
			}
		}
		
		return dao.saveFdyFp(params, delParams);
	}
	public boolean saveFdyFp_12861(String[] yhs,String[] lds){
		List<String[]> params = new ArrayList<String[]>(); 
		List<String[]> delParams = new ArrayList<String[]>();
		
		for(int i=0; i<lds.length; i++){
			delParams.add(new String[]{lds[i]});
			for(int j=0; j<yhs.length; j++){
				params.add(new String[]{yhs[j],lds[i]});
			}
		}
		
		return dao.saveFdyFp_12861(params, delParams);
	}
	
	/**
	 * 获取楼栋寝室列表 该处的信息列表是经过处理的
	 * @param form
	 * @return
	 */
	public List<List<HashMap<String,String>>> getLdqsxxList(LdglForm form){
		List<List<HashMap<String,String>>> ld_list=new ArrayList<List<HashMap<String,String>>>();
		List<HashMap<String,String>> qsxxList=dao.getLdqsxxList(form);
		if(qsxxList==null||qsxxList.size()==0){
			return ld_list;
		}
		String curr_ch=qsxxList.get(0).get("ch");//当前层号
		List<HashMap<String,String>> ch_list=new ArrayList<HashMap<String,String>>();//层号集合
		for(int i=0;i<qsxxList.size();i++){
			if(!curr_ch.equals(qsxxList.get(i).get("ch"))){//判断当前层号与迭代层号
				ld_list.add(ch_list);
				ch_list=new ArrayList<HashMap<String,String>>();
				curr_ch=qsxxList.get(i).get("ch");
			}
			ch_list.add(qsxxList.get(i));
			if(i==qsxxList.size()-1){//如果是最后一个自己添加进去
				ld_list.add(ch_list);
			}
		}
		
		return ld_list;
	}
	
	/**
	 * 获取楼栋中层中最大的寝室数
	 * @param form
	 * @return
	 */
	public String getChMaxQss(LdglForm form){
		return dao.getChMaxQss(form);
	}
	
	/**
	 * 
	 * @param request
	 * @param form
	 * @return
	 */
	public String saveLdQsxx(HttpServletRequest request,LdglModel form){
		//首先验证该楼栋是否可修改
		String lddm=form.getLddm();//楼栋代码
		String[] qsh_old_s=request.getParameterValues("qsh_old_s");//旧寝室号
		String[] qsh_new_s=request.getParameterValues("qsh_new_s");//新寝室号
		String[] cws_s=request.getParameterValues("cws_s");//床位数
		String[] xb_s=request.getParameterValues("xb_s");//性别
		
		if(qsh_old_s==null||qsh_new_s==null||cws_s==null||qsh_old_s.length==0||
				qsh_old_s.length!=qsh_new_s.length||qsh_old_s.length!=cws_s.length){
			return "楼栋寝室相关数据异常！";
		}
		String ldxb=getLdxxOne(form).get("ldxb");
		List<String> sql_s=new ArrayList<String>();
		for(int i=0;i<qsh_old_s.length;i++){
			//删除旧的床位
			sql_s.add("delete from xg_gygl_new_cwxxb where lddm='"+lddm+"' and qsh='"+qsh_old_s[i]+"'");
			//插入新的床位
			for(int j=0;j<Integer.parseInt(cws_s[i]);j++){
				sql_s.add("insert into xg_gygl_new_cwxxb(lddm,qsh,cwh) values('"+lddm+"','"+qsh_new_s[i]+"','"+(j+1)+"')");
			}
			//更改寝室信息
			if("混住".equals(ldxb)){
				sql_s.add("update xg_gygl_new_qsxxb set qsh='"+qsh_new_s[i]+"',cws='"+cws_s[i]+"',qsxb='"+xb_s[i]+"' where lddm='"+lddm+"' and qsh='"+qsh_old_s[i]+"'");
			}else{
				sql_s.add("update xg_gygl_new_qsxxb set qsh='"+qsh_new_s[i]+"',cws='"+cws_s[i]+"' where lddm='"+lddm+"' and qsh='"+qsh_old_s[i]+"'");
			}
		}
		boolean b=dao.saveLdQsxx(sql_s.toArray(new String[]{}));
		return "";
	}
	
	/**
	 * 检验楼栋寝室信息是否可以修改
	 * @param form
	 * @return
	 */
	public boolean checkLdQsxxSfkxg(LdglForm form){
		boolean b=dao.checkLdQsxxSfkxg(form);
		return b;
	}
	
	/**
	 * 楼栋信息管理自定义导出
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdglInfoExportList(LdglModel model) throws Exception{
		
		return dao.getLdglInfoExportList(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-24 上午09:54:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFpryList(User user,LdglForm ldglform) throws Exception{
		LdglDaoFp daoFp = new LdglDaoFp();
		return daoFp.getFpryList(user, ldglform);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-25 下午02:48:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveLdfp(LdglForm ldglform) throws Exception{
		String[] lddmArray = ldglform.getCheckVal();
		String[] yhms = ldglform.getYhms();
		if(lddmArray == null || yhms == null ){
			return false;
		}
		return dao.checkBatch(dao.saveLdfp(lddmArray, yhms));
	}
	
	/**
	 * 
	 * @描述: 取消分配
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-25 下午04:27:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ldglform
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelLdfp(LdglForm ldglform) throws Exception{
		String lddm = ldglform.getLddm();
		String[] yhms = ldglform.getYhms();
		if(lddm == null || yhms == null ){
			return false;
		}
		return dao.cancelFp(lddm, yhms);
	}
}
