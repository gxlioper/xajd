/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-10 ����05:01:04 
 */  
package xgxt.base;

import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������������
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-4-10 ����05:01:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ServerWhiteListUtil {
	 private static List<String> whiteList = null;;

	    static {
	        try {
	            // ��ȡ�������б�
	            whiteList = new Gson().fromJson(
	                    new InputStreamReader(ServerWhiteListUtil.class.getResourceAsStream("serverWhiteList.json")),
	                    new TypeToken<List<String>>() {
	                    }.getType());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * �жϵ�ǰhost�Ƿ��ڰ�������
	     * @param host ����host
	     * @return boolean �Ƿ��ڰ�������
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
