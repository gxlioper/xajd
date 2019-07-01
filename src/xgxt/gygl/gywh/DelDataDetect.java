package xgxt.gygl.gywh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;

import com.zfsoft.basic.BasicService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 关联性数据删除检测
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */
public class DelDataDetect {
	
	/**
	 * 关联数据删除检测
	 * return String
	 */
	public void dataDetect(DelDetectModel model,HttpServletRequest request){
		
		DAO dao=DAO.getInstance();
		
		//获取删除关联数据设置表信息
		List<HashMap<String,String>>delSz=getDelsz(model);
		
		//需要删除的记录
		String[]pkValue=model.getPkValue();
		
		
		//删除失败消息提示
		ArrayList<Object> messages=new ArrayList<Object>();
		ArrayList<Object> allMes=new ArrayList<Object>();
		HashMap<String,String>mesMap=new HashMap<String,String>();
		boolean blog=true;
		String mes="";
		boolean m=true;
		for(int i=0;i<delSz.size();i++){
			//主键
			List<String>pkList=new ArrayList<String>();
			StringBuilder sql=new StringBuilder();
			//获取数据关联表配置信息
			HashMap<String,Object>delMap=new HashMap<String,Object>();
			delMap.putAll(delSz.get(i));
			List<String>input=new ArrayList<String>();
			//需要删除数据的表
			String scb=String.valueOf(delMap.get("scb"));
			//需要删除数据的表主键
			String scbzj=String.valueOf(delMap.get("scbzj"));
			//关联数据来源表
			String glb=String.valueOf(delMap.get("glb"));
			//提示信息
			String message=String.valueOf(delMap.get("message"));
			//主键拼接规则
			String pjgz =String.valueOf(delMap.get("pjgz"));
			//关联表字段
			String glbzd=String.valueOf(delMap.get("glbzd"));
			
			String tzpath=String.valueOf(delMap.get("tzpath"));
			HashMap<String,String>messageMap=new HashMap<String,String>();
			messageMap.put("message", message);
			messageMap.put("path",tzpath );
			
			//删除表与关联表关联字段
			String scbglzd=String.valueOf(delMap.get("scbglzd"));
			
			// ==============================拼接检测 sql================================
			String pks=scbzj.replace(",", "||a.");
			sql.append(" select a."+pks+" pkValue,count(1)num from "+scb+" a where exists");
			//输出字段
			List<String> outputValue=new ArrayList<String>();
			//关联表字段,关联数据数量
			outputValue.add("num");
			outputValue.add("pkValue");
			String[]scbzjArr=scbzj.split(",");
			String[]scbglzdArr=scbglzd.split(",");
			String[]glbzdArr=glbzd.split(",");
			sql.append(" (select 1 from "+glb+" b where 1=1  ");
			for(int j=0;j<scbglzdArr.length;j++){
				sql.append(" and a."+scbglzdArr[j]+"=b."+glbzdArr[j]);
				
			}
			
			sql.append(" )  ");
			for(int j=0;j<pkValue.length;j++){
				
				if(!Base.isNull(pjgz)){
					scbglzd=pjgz.replace("'||", "'||a.");
				}else{
					scbglzd=scbzj.replace(",", " ||a.");
				}
				if(j==0){
					sql.append(" and ( a."+scbglzd+"=? ");
				}else{ 
					sql.append(" or  a."+scbglzd+"= ? ");
				}
				input.add(pkValue[j]);
			}
			
			
			sql.append(")group by "+scbzj );
			
			// ==============================拼接检测sql end================================
			
			HashMap<String,String>errMap=dao.getMap(sql.toString(), input.toArray(new String[]{}), outputValue.toArray(new String[]{}));
			
			//判断是否存在不可删除的数据
			if(errMap.size()>0){
				//如果存在继续检测下一类型的数据关联
				// ==============拼接下一类型需要检测的数据主键===================
				messages.add(messageMap);
				
				pkList.add(errMap.get("pkValue"));
				
				pkValue=pkList.toArray(new String[]{});
				delMap.put("pkValue", input.toArray(new String[]{}));
				blog=false;
				delMap.putAll(getScbzjMc(delMap));
				mes=getZjzdmc(delMap);
				if(m){
					for(int j=0;j<pkValue.length;j++){
						request.setAttribute(scbzjArr[j], pkValue[j]);
					}
					m=!m;
				}
				//==============end===================
			}
		}
		
		if(mesMap.size()>0){
			messages.add(mesMap);
		}
		
		if(!blog){
			allMes.add(mes);
			for(int i=0;i<messages.size();i++){
				HashMap<String,String>firstMap=(HashMap<String,String>)messages.get(i);
				for(int j=i+1;j<messages.size();j++){
					HashMap<String,String>cecoundMap=(HashMap<String,String>)messages.get(j);
					if(firstMap.get("path").equalsIgnoreCase(cecoundMap.get("path"))){
						firstMap.put("path", "no");
					}
				}
			}
			List<HashMap<String,String>>delList=new ArrayList<HashMap<String,String>>();
			for(int i=0;i<messages.size();i++){
				HashMap<String,String>firstMap=(HashMap<String,String>)messages.get(i);
				if(!"no".equalsIgnoreCase(firstMap.get("path"))){
					delList.add(firstMap);
				}
			}
			
			allMes.addAll(delList);
		}
		
		model.setBool(blog);
		model.setMessage(allMes);
	}
	
	/**
	 * 获取删除关联数据设置
	 * @return
	 */
	public List<HashMap<String,String>>getDelsz(DelDetectModel model){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select scb,scbzj,glb,scbglzd,path,message,tzpath,scbzjm,mclyb,pjgz,glbzd from xg_gygl_delszb where 1=1 ");
		
		List<String>input=new ArrayList<String>();
		
		String []outPut={"scb","scbzj","glb","path","scbglzd","message","tzpath","scbzjm","mclyb","pjgz","glbzd"};
		
		//根据PATH路径抽取数据
		if(!Base.isNull(model.getPath())){
			sql.append(" and path=? ");
			input.add(model.getPath());
		}
		
		return dao.getList(sql.toString(), input.toArray(new String[]{}), outPut);
	}
	
	/**
	 * 获取删除表主键名称
	 * @return
	 */
	public HashMap<String,String>getScbzjMc(HashMap<String,Object>scbMap){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		String scbzj=String.valueOf(scbMap.get("scbzj"));
		
		String pjgz = String.valueOf(scbMap.get("pjgz"));
		
		String scbzjm=String.valueOf(scbMap.get("scbzjm"));
		
		String mclyb=String.valueOf(scbMap.get("mclyb"));
		
		String[] pkValue=(String[])scbMap.get("pkValue");
		
		sql.append(" select "+scbzjm+" from "+mclyb+" a  where 1=1 ");
		
		List<String>input=new ArrayList<String>();
		
		String []outPut=scbzjm.split(",");
		
		String scbglzd="";
		
		if(!Base.isNull(pjgz)){
			scbglzd=pjgz.replace("'||", "'||a.");
		}else{
			scbglzd=scbzj.replace(",", " ||a.");
		}
	
		//根据PATH路径抽取数据
		sql.append(" and  a."+scbglzd+"= ? ");
			
		input.add(pkValue[0]);
		
		
		return dao.getMap(sql.toString(),input.toArray(new String[]{}), outPut);
	}
	
	public String getZjzdmc(HashMap<String,Object>scbMap){
		BasicService service = new BasicService();
		
		String mclyb=String.valueOf(scbMap.get("mclyb"));
		
		String scbzjm=String.valueOf(scbMap.get("scbzjm"));
		
		String scjlxx="";
		
		String []scbzjmArr=scbzjm.split(",");
		String []outputColumn=scbzjm.split(",");
		ArrayList<HashMap<String, String>> top = (ArrayList<HashMap<String, String>>) service
				.getTopTr(mclyb, outputColumn);
		for(int i=0;i<outputColumn.length;i++){
			HashMap<String,String>topMap=top.get(i);
			topMap.get("en");
			scjlxx+=topMap.get("cn")+":"+scbMap.get(scbzjmArr[i])+"  ";
		}
		return scjlxx;
	}
}
