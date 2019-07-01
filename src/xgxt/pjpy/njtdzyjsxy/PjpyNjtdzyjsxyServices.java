package xgxt.pjpy.njtdzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.action.Base;

/**
 * Title:南京铁道职业技术学院评奖评优业务逻辑类
 * Copyright:Copright(c)2008
 * Company:杭州正方电子工程有限公司
 * @Author:Lp
 * @version 1.0
 * 生成日期：2008-05-26
 */
public class PjpyNjtdzyjsxyServices {
     private PjpyNjtdzyjsxyDao dao = null;
     /**
      * @return 返回综合素质成绩表头信息
      */
     public ArrayList<HashMap<String, String>>  getzhszcjSearchTitle(){
    	 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
    	 dao = new PjpyNjtdzyjsxyDao();
    	 result = dao.getNjtdZhszcjSearchTitle();//表头信息 
    	 return result;
     }
     /**
      * @return 返回查询结果 
      */
     public ArrayList<String[]> getzhszcjResult(PjpyNjtdzyjsxyZhszcjModel pnzModel){
    	 ArrayList<String[]> result = new ArrayList<String[]>();
    	 result = dao.getNjtdZhszcjResult(pnzModel);//查询结果
    	 return result;
     }
     /**
      * 南京铁道综合素质评价成绩计算方法（每学期评一次，按学年汇总）:用Z表示，满分为100分，合格为60分。
      * 思想道德素质、科学文化素质、职业技能素质、身心素质和拓展素质五个一级指标成绩分别用Z1、Z2、Z3、Z4、Z5表示；
      * 思想道德素质所包含的政治表现、社会公德、遵章守纪、集体观念、艰苦奋斗、宿舍生活表现
      * 六个二级指标成绩分别用Z11、Z12、Z13、Z14、Z15、Z16表示；
      * 科学文化素质所包含的学习态度、学习成绩两个二级指标分别用Z21、Z22表示；职业技能素质所包含的计算机应用水平、英语水平、专业操作水平
      * 三个二级指标成绩分别用Z31、Z32、Z33表示；身心素质所包含的体育课成绩、体质健康成绩、体育锻炼和心理健康四个二级指标成绩分别用Z41、Z42、Z43、Z44表示；
      * 拓展素质所包含的创新创造能力、职业技术能力、社会实践能力、组织管理能力、文体特长、特殊表彰六个二级指标成绩分别用Z51、Z52、Z53、Z54、Z55、Z56表示。
      * 各一级指标和二级指标在单独评价时均以100分为满分。综合素质评价成绩的计算公式如下：
      *(一) 综合素质评价总分 Z＝Z1×40%＋Z2×20%＋Z3×10%＋Z4×15%＋Z5×15%
      *(二) 思想道德素质评价得分Z1＝Z11×20%＋Z12×20%＋Z13×20%＋Z14×15%＋Z15×15%＋Z16×10%
      *(三) 科学文化素质得分Z2＝Z21×50%＋Z22×50%
      *(四) 职业技能素质得分
      * Z3（第一学年）＝Z31  Z3（第二学年）＝Z32  Z3（毕业学年）＝Z33
      *(五) 身心素质评价得分Z4＝Z41×10%＋Z42×20%＋Z43×30%＋Z44×40%
      *(六) 拓展素质评价得分Z5＝Z51＋Z52＋Z53＋Z54＋Z55＋Z56
      * @param xh
      * @param xn
      * @param xq
      * @return 按照南京铁道综合素质计算方法
      * 返回综合素质成绩计算结果
      */
     public ArrayList<HashMap<String, String>> getZhszcjJsJg(String xh,String xn,String xq)throws Exception{
    	 dao = new PjpyNjtdzyjsxyDao();
    	 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
    	 ArrayList<HashMap<String, String>> JsX = dao.getNjtdZhszcjJsX(xh, xn, xq);	     	 
     	 for(int i=0;i<JsX.size();i++){
     		HashMap<String,String> map = new HashMap<String,String>();
    		 float Z = 0;
    		 float Z1 = 0;     		
    		 float Z11 = getJSX(JsX.get(i).get("zzbx"));
    		 float Z12 = getJSX(JsX.get(i).get("shgd")); 
    		 float Z13 = getJSX(JsX.get(i).get("zzsj"));
    		 float Z14 = getJSX(JsX.get(i).get("jtgn"));
    		 float Z15 = getJSX(JsX.get(i).get("jkfd"));
    		 float Z16 = getJSX(JsX.get(i).get("ssshbx"));
    		 Z1 = (Z11*20+Z12*20+Z13*20+Z14*15+Z15*15+Z16*10)/100;
       		 float Z2 = 0;
       		 float Z21 = getJSX(JsX.get(i).get("xxtd"));
       		 float Z22 = getJSX(JsX.get(i).get("xxcj"));
       		 Z2 = (Z21*50+Z22*50)/100;
       		 float Z3 = 0;
       		 float Z31 = getJSX(JsX.get(i).get("jsjsp"));
       		 float Z32 = getJSX(JsX.get(i).get("yysp"));
       		 float Z33 = getJSX(JsX.get(i).get("zyczjn"));
       		 Z3 = Z31+Z32+Z33;//职业技能素质二级指标中三个参数中，每学年只存在一个参数值
       		 float Z4 = 0;
       		 float Z41 = getJSX(JsX.get(i).get("tykcj"));
       		 float Z42 = getJSX(JsX.get(i).get("tzjk"));
       		 float Z43 = getJSX(JsX.get(i).get("tydl"));
       		 float Z44 = getJSX(JsX.get(i).get("xlsz"));
       		 Z4 = (Z41*10+Z42*20+Z43*30+Z44*40)/100;
       		 float Z5 = 0;
       		 float Z51 = getJSX(JsX.get(i).get("cxcznl"));
       		 float Z52 = getJSX(JsX.get(i).get("zyjsnl"));
       		 float Z53 = getJSX(JsX.get(i).get("shsjnl"));
       		 float Z54 = getJSX(JsX.get(i).get("zzglnl"));
       		 float Z55 = getJSX(JsX.get(i).get("wttc"));
       		 float Z56 = getJSX(JsX.get(i).get("tsbz"));
       		 Z5 = Z51+Z52+Z53+Z54+Z55+Z56;
       		 Z = (Z1*40+Z2*20+Z3*10+Z4*15+Z5*15)/100;
       		 map.put("ssddsz",new java.text.DecimalFormat("#,##0.00").format(Z1));
       		 map.put("kxwhsz",new java.text.DecimalFormat("#,##0.00").format(Z2));
       		 map.put("zyjnsz",new java.text.DecimalFormat("#,##0.00").format(Z3));
       		 map.put("sxsz",new java.text.DecimalFormat("#,##0.00").format(Z4));
       		 map.put("tzsz",new java.text.DecimalFormat("#,##0.00").format(Z5));
       		 map.put("zhszzf",new java.text.DecimalFormat("#,##0.00").format(Z));
       		 map.put("xh",xh);
       		 map.put("xn",xn);
       		 map.put("xq",xq);
       		 result.add(map);
    	 }
    	 return result;
     }
     /**
      * 对象为空或为空String时返回“0”否则返回相关float值
      * @param ob  ArrayList<HashMap<String,String>>对象
      * @return float
      */
     public float getJSX(Object ob){
    	 float f = 0;
    	 if(ob == null){
    		 f = 0;
    	 }else if(Base.isNull(ob.toString())){
    		 f = 0;	
    	 }else if(ob.toString().equals(" ")||ob.toString()==" "){
    	     f = 0;
    	 }else{
    		 f = Float.parseFloat(ob.toString().trim());
    	 }
    	 return f;
     }     
}
