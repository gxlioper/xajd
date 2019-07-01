package xgxt.pjpy.njtdzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.action.Base;

/**
 * Title:�Ͼ�����ְҵ����ѧԺ��������ҵ���߼���
 * Copyright:Copright(c)2008
 * Company:�����������ӹ������޹�˾
 * @Author:Lp
 * @version 1.0
 * �������ڣ�2008-05-26
 */
public class PjpyNjtdzyjsxyServices {
     private PjpyNjtdzyjsxyDao dao = null;
     /**
      * @return �����ۺ����ʳɼ���ͷ��Ϣ
      */
     public ArrayList<HashMap<String, String>>  getzhszcjSearchTitle(){
    	 ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
    	 dao = new PjpyNjtdzyjsxyDao();
    	 result = dao.getNjtdZhszcjSearchTitle();//��ͷ��Ϣ 
    	 return result;
     }
     /**
      * @return ���ز�ѯ��� 
      */
     public ArrayList<String[]> getzhszcjResult(PjpyNjtdzyjsxyZhszcjModel pnzModel){
    	 ArrayList<String[]> result = new ArrayList<String[]>();
    	 result = dao.getNjtdZhszcjResult(pnzModel);//��ѯ���
    	 return result;
     }
     /**
      * �Ͼ������ۺ��������۳ɼ����㷽����ÿѧ����һ�Σ���ѧ����ܣ�:��Z��ʾ������Ϊ100�֣��ϸ�Ϊ60�֡�
      * ˼��������ʡ���ѧ�Ļ����ʡ�ְҵ�������ʡ��������ʺ���չ�������һ��ָ��ɼ��ֱ���Z1��Z2��Z3��Z4��Z5��ʾ��
      * ˼��������������������α��֡���ṫ�¡������ؼ͡����������ܶ��������������
      * ��������ָ��ɼ��ֱ���Z11��Z12��Z13��Z14��Z15��Z16��ʾ��
      * ��ѧ�Ļ�������������ѧϰ̬�ȡ�ѧϰ�ɼ���������ָ��ֱ���Z21��Z22��ʾ��ְҵ���������������ļ����Ӧ��ˮƽ��Ӣ��ˮƽ��רҵ����ˮƽ
      * ��������ָ��ɼ��ֱ���Z31��Z32��Z33��ʾ�����������������������γɼ������ʽ����ɼ��������������������ĸ�����ָ��ɼ��ֱ���Z41��Z42��Z43��Z44��ʾ��
      * ��չ�����������Ĵ��´���������ְҵ�������������ʵ����������֯���������������س������������������ָ��ɼ��ֱ���Z51��Z52��Z53��Z54��Z55��Z56��ʾ��
      * ��һ��ָ��Ͷ���ָ���ڵ�������ʱ����100��Ϊ���֡��ۺ��������۳ɼ��ļ��㹫ʽ���£�
      *(һ) �ۺ����������ܷ� Z��Z1��40%��Z2��20%��Z3��10%��Z4��15%��Z5��15%
      *(��) ˼������������۵÷�Z1��Z11��20%��Z12��20%��Z13��20%��Z14��15%��Z15��15%��Z16��10%
      *(��) ��ѧ�Ļ����ʵ÷�Z2��Z21��50%��Z22��50%
      *(��) ְҵ�������ʵ÷�
      * Z3����һѧ�꣩��Z31  Z3���ڶ�ѧ�꣩��Z32  Z3����ҵѧ�꣩��Z33
      *(��) �����������۵÷�Z4��Z41��10%��Z42��20%��Z43��30%��Z44��40%
      *(��) ��չ�������۵÷�Z5��Z51��Z52��Z53��Z54��Z55��Z56
      * @param xh
      * @param xn
      * @param xq
      * @return �����Ͼ������ۺ����ʼ��㷽��
      * �����ۺ����ʳɼ�������
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
       		 Z3 = Z31+Z32+Z33;//ְҵ�������ʶ���ָ�������������У�ÿѧ��ֻ����һ������ֵ
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
      * ����Ϊ�ջ�Ϊ��Stringʱ���ء�0�����򷵻����floatֵ
      * @param ob  ArrayList<HashMap<String,String>>����
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
