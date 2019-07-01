package com.zfsoft.xgxt.xlzx.xlwjga.xxlwjgy;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-04-10 14:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XlwjgyDao extends SuperDAOImpl<XlwjgyForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(XlwjgyForm.class);
        super.setKey("id");
        super.setTableName("xg_xljl_xlwjgyb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(XlwjgyForm xlwjgyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XlwjgyForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.*, ");
        sql.append(" decode(a.fxtj,'fdy','辅导员','stu','学生','tel','电话热线','bbs','BBs','zx','咨询','qq','QQ','qt','其他',a.fxtj) fxtjmc ,");
        sql.append(" decode(a.wjcd,'fcjj','非常紧急','jj','紧急','yb','一般','bj','不急',a.wjcd) wjcdmc ,");
        sql.append(" decode(a.wjgyfs,'xcgy','现场干预','zdxy','指导书院/学院','yjzx','建议预约咨询','jyyl','建议医疗',a.wjgyfs) wjgyfsmc ,");
        sql.append(" decode(a.xtbm,'sy','书院/学院','gac','公安处','wlzx','网络中心','xyy','校医院','qt','其他',a.xtbm) xtbmmc ,");
        sql.append(" decode(a.wjgyjg,'sljj','顺利解决','ydgj','有待跟进','jyyl','建议医疗','xx','休学','tx','退学','qt','其他',a.wjgyjg) wjgyjgmc ,");
        sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,");
        sql.append(" b.lxdh,b.zybj,b.zybjmc,b.sydm1 sydm,b.symc1 symc,b.zzmmmc,b.mz ");
        sql.append(" from xg_xljl_xlwjgyb a ");
        sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
        sql.append(" ) t1 where 1=1 ");
//		sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> getInfoById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.*, ");
        sql.append(" decode(a.fxtj,'fdy','辅导员','stu','学生','tel','电话热线','bbs','BBs','zx','咨询','qq','QQ','qt','其他',a.fxtj) fxtjmc ,");
        sql.append(" decode(a.wjcd,'fcjj','非常紧急','jj','紧急','yb','一般','bj','不急',a.wjcd) wjcdmc ,");
        sql.append(" decode(a.wjgyfs,'xcgy','现场干预','zdxy','指导书院/学院','yjzx','建议预约咨询','jyyl','建议医疗',a.wjgyfs) wjgyfsmc ,");
        sql.append(" decode(a.xtbm,'sy','书院/学院','gac','公安处','wlzx','网络中心','xyy','校医院','qt','其他',a.xtbm) xtbmmc ,");
        sql.append(" decode(a.wjgyjg,'sljj','顺利解决','ydgj','有待跟进','jyyl','建议医疗','xx','休学','tx','退学','qt','其他',a.wjgyjg) wjgyjgmc ,");
        sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,");
        sql.append(" b.lxdh,b.zybj,b.zybjmc,b.sydm1 sydm,b.symc1 symc,b.zzmmmc,b.mz ");
        sql.append(" from xg_xljl_xlwjgyb a ");
        sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
        sql.append(" ) t1 where id=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
