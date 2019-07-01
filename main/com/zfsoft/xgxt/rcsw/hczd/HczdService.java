/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.hczd;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	： HczdService
 * @description	：火车站点service(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2017-11-22 下午04:17:47
 * @version 	V1.0 
 */

public class HczdService extends SuperServiceImpl<HczdForm, HczdDao>{
	
	/**
	 * @description	： 增加
	 * @author 		：柳俊（1282）
	 * @date 		：2017-11-22 下午04:26:23
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public boolean runInsert(HczdForm t) throws Exception {
		String zdmc = t.getZdmc();
		String zdpy = transferPinYin(zdmc);
		String zdjp = transferJp(zdmc);
		t.setZdpy(zdpy);
		t.setZdjp(zdjp);
		return dao.runInsert(t);
	}
	
	public boolean runUpdate(HczdForm t) throws Exception {
		String zdmc = t.getZdmc();
		String zdpy = transferPinYin(zdmc);
		String zdjp = transferJp(zdmc);
		t.setZdpy(zdpy);
		t.setZdjp(zdjp);
		return dao.runUpdate(t);
	}
	
	/**
	 * @description	： 转换成全拼
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-22 下午04:30:09
	 * @param src
	 * @return
	 */
	public String transferPinYin(String src){
		 	char[] t1 = null;
	        t1 = src.toCharArray();
	        String[] t2 = new String[t1.length];
	        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
	        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
	        String t4 = "";
	        int t0 = t1.length;
	        try {
	            for (int i = 0; i < t0; i++) {
	                // 判断是否为汉字字符
	                if (java.lang.Character.toString(t1[i]).matches(
	                        "[\\u4E00-\\u9FA5]+")) {
	                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
	                    t4 += t2[0];
	                } else
	                    t4 += java.lang.Character.toString(t1[i]);
	            }
	            // System.out.println(t4);
	            return t4;
	        } catch (BadHanyuPinyinOutputFormatCombination e1) {
	            e1.printStackTrace();
	        }
	        return t4;
	}
	
	/**
	 * @description	： 转换成简拼
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-22 下午04:38:19
	 * @param src
	 * @return
	 */
	public String transferJp(String str){
		String temp = "";
        String demo = "";
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        for(int i=0;i<convert.length();i++){//convert目前为小写首字母,下面是将小写首字母转化为大写
            if(convert.charAt(i)>='a' && convert.charAt(i)<='z'){
                temp=convert.substring(i,i+1).toUpperCase();
                demo += temp;
            }
        }
        return demo;
	}
	
	/**
	 * @description	： 是否存在
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-23 下午01:48:00
	 * @param t
	 * @return
	 */
	public boolean isExist(HczdForm t){
		if(null == t.getOriZdmc()){
			return dao.countSl(t) > 0;
		}else{
			if(t.getZdmc().equals(t.getOriZdmc())){
				return false;
			}else{
				return dao.countSl(t) > 0;
			}
		}
		
	}
}
