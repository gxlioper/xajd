package com.zfsoft.xgxt.dycjgl.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class DydmwhService extends SuperServiceImpl<DydmwhForm,DydmwhDao> {
    public boolean isExistByXmmc(DydmwhForm model) {
        boolean flag = false;

        String numbyMc = dao.xmmcCheckExist(model);
        if(!"0".equalsIgnoreCase(numbyMc)){
            flag = true;
        }

        String numbyDm = dao.xmdmCheckExist(model);
        if(!"0".equalsIgnoreCase(numbyDm)){
            flag = true;
        }

        return flag;
    }

    public String checkfsb(String values) throws Exception {
        String resultxmdm = "";
        String[] xmdm = dao.xmdmCheckExistForfsb(values);
        if(xmdm!=null) {
            for (int i = 0; i < xmdm.length; i++) {
                if (i == xmdm.length - 1) {
                    resultxmdm += xmdm[i];
                } else {
                    resultxmdm += xmdm[i];
                }
            }
        }
        return resultxmdm;
    }

    public String checkbkcj(String values) throws Exception {
        String resultxmdm = "";
        String[] xmdm = dao.xmdmCheckExistForbkcj(values);
        if(xmdm!=null) {
            for (int i = 0; i < xmdm.length; i++) {
                if (i == xmdm.length - 1) {
                    resultxmdm += xmdm[i];
                } else {
                    resultxmdm += xmdm[i];
                }
            }
        }
        return resultxmdm;
    }

    public boolean isExist(DydmwhForm model) {
        boolean flag = false;

        String num = dao.CheckExistFsb(model);
        if(!"0".equalsIgnoreCase(num)){
            flag = true;
        }
        String numBk = dao.CheckExistBkb(model);
        if(!"0".equalsIgnoreCase(numBk)){
            flag = true;
        }

        return flag;
    }
}
