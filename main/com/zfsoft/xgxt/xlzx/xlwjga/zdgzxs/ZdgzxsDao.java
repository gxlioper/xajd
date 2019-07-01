package com.zfsoft.xgxt.xlzx.xlwjga.zdgzxs;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-04-10 14:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZdgzxsDao extends SuperDAOImpl<ZdgzxsForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ZdgzxsForm.class);
        super.setKey("id");
        super.setTableName("xg_xljl_zdgzxs");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZdgzxsForm zdgzxsForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZdgzxsForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.*,t2.lxdh zxslxdh,t2.xm zxsxm,t1.fdyxm fydmc,");
        sql.append(" case when c.cwh is not null then c.ldmc||'，'||c.qsh||'，'||c.cwh||'号床' else '' end zsqsmc, ");
        sql.append(" decode(a.wtlb,'A','A 类：有即刻的自杀（伤害）风险，需24小时监护','B','B 类 ：有自杀（伤害）倾向，需经常了解评估','C'," );
        sql.append("     'C 类 ：有精神疾病倾向，请辅导员协助医疗','D','D 类 ：无自杀（伤害）及精神病倾向，请辅导员协助解决问题',a.wtlb) wtlbmc ,");
        sql.append(" decode(a.sfxsty,'1','是','0','否',a.sfxsty) sfxstymc ," );
        sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,");
        sql.append(" b.lxdh,b.zybj,b.zybjmc,b.sydm1 sydm,b.symc1 symc,b.zzmmmc,b.mz ");
        sql.append(" from xg_xljl_zdgzxs a ");
        sql.append(" left join view_xg_gygl_new_cwxx c on a.xh = c.xh ");
        sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
        sql.append(" left join ");
        sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
        sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ");
        sql.append(" on b.bjdm = t1.bjdm ");
        sql.append(" left join xg_view_xlzx_zxsxx t2 on a.zxs=t2.zgh ");
        sql.append(" ) t1 where 1=1 ");
//		sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> getInfoById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.*,t2.lxdh zxslxdh,t2.xm zxsxm,t1.fdyxm,case when c.cwh is not null then c.ldmc||'，'||c.qsh||'，'||c.cwh||'号床' else '' end zsqsmc, ");
        sql.append(" decode(a.wtlb,'A','A 类：有即刻的自杀（伤害）风险，需24小时监护','B','B 类 ：有自杀（伤害）倾向，需经常了解评估','C'," );
        sql.append("     'C 类 ：有精神疾病倾向，请辅导员协助医疗','D','D 类 ：无自杀（伤害）及精神病倾向，请辅导员协助解决问题',a.wtlb) wtlbmc ,");
        sql.append(" decode(a.sfxsty,'1','是','0','否',a.sfxsty) sfxstymc ," );
        sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,");
        sql.append(" b.lxdh,b.zybj,b.zybjmc,b.sydm1 sydm,b.symc1 symc,b.zzmmmc,b.mz ");
        sql.append(" from xg_xljl_zdgzxs a ");
        sql.append(" left join view_xg_gygl_new_cwxx c on a.xh = c.xh ");
        sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
        sql.append(" left join ");
        sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
        sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ");
        sql.append(" on b.bjdm = t1.bjdm ");
        sql.append(" left join xg_view_xlzx_zxsxx t2 on a.zxs=t2.zgh ");
        sql.append(" ) t1 where id=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
