package xsgzgl.gygl.cwdh;

import java.util.ArrayList;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
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
