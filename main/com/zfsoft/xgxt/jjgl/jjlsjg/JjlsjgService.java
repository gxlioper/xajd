package com.zfsoft.xgxt.jjgl.jjlsjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 西交大：家教老师结果service.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-11-19 16:43
 */
public class JjlsjgService extends SuperServiceImpl<JjlsjgForm,JjlsjgDao> {

    public boolean isRepeat(JjlsjgForm jjlsjgForm) {

        return dao.isRepeat(jjlsjgForm);
    }
}
