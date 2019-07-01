package xsgzgl.gygl.cwdh;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class FilterUtils {
    public static <T> List<T> filter(List<T> list, Test<T> tObject){
        ArrayList<T> r = new ArrayList<T>();
        for(T t : list){
            if(tObject.test(t)){
                r.add(t);
            }
        }
        r.trimToSize();
        return r;
    }
}
