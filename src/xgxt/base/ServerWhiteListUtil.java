/**
 * @部门:学工产品事业部
 * @日期：2018-4-10 下午05:01:04 
 */  
package xgxt.base;

import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 服务器白名单
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-4-10 下午05:01:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ServerWhiteListUtil {
	 private static List<String> whiteList = null;;

	    static {
	        try {
	            // 读取白名单列表
	            whiteList = new Gson().fromJson(
	                    new InputStreamReader(ServerWhiteListUtil.class.getResourceAsStream("serverWhiteList.json")),
	                    new TypeToken<List<String>>() {
	                    }.getType());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 判断当前host是否在白名单内
	     * @param host 待查host
	     * @return boolean 是否在白名单内
	     */
	    public static boolean isWhite(String host) {
	        if (whiteList == null || whiteList.size() == 0) {
	            return true;
	        }
	        for (String str : whiteList) {
	            if (str != null && str.equals(host)) {
	                return true;
	            }
	        }
	        return false;
	    }
}
