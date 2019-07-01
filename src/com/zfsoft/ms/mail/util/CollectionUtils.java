package com.zfsoft.ms.mail.util;

import java.util.*;

public abstract class CollectionUtils extends org.apache.commons.collections.CollectionUtils {
    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
