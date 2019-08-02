package com.zfsoft.xgxt.zhdj.dzzhd;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-党组织生活管理-党组织活动管理
 * @类功能描述: 党团建设活动管理
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/19 16:02
 */
public class DzzhdDao extends SuperDAOImpl<DzzhdForm> {

    public DzzhdDao() {
    }

    public static volatile DzzhdDao dzzhdDao = null;
    public String url = "";

    public static DzzhdDao getDzzhdDao() {
        if (dzzhdDao == null) {
            synchronized (DzzhdDao.class) {
                if (dzzhdDao == null) {
                    dzzhdDao = new DzzhdDao();
                }
            }
        }
        return dzzhdDao;
    }

    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(DzzhdForm dzzhdForm) throws Exception {
        return null;
    }

    /**
     * @param model
     * @param user
     * @return java.util.List<java.util.HashMap   <   java.lang.String   ,   java.lang.String>>
     * @描述: 获取我发布的
     * @作者: 何爽[工号:1730]
     * @日期: 2019/7/19 17:19
     * @修改记录: 修改人-修改时间-修改描述
     **/
    public List<HashMap<String, String>> getFbPageList(DzzhdForm model, User user) {
        try {
            String searchTj = SearchService.getSearchTj(model.getSearchModel());
            String[] inputV = SearchService.getTjInput(model.getSearchModel());
            StringBuilder sql = new StringBuilder();
            sql.append("select id,hdmc,kssj,jssj,hdlxdm,hdnr,fj,cjr,cjrxm,cjsj,mxdx,");
            if("stu".equals(user.getUserType())){
                sql.append("(select count(xh) from xg_zhdj_dzzhdryb where hdid=a.id and tjsj is not null and xh = '"+user.getUserName()+"') xdtj ");
            }else{
                sql.append("(select count(xh) from xg_zhdj_dzzhdryb where hdid=a.id and tjsj is not null) ytj,");
                sql.append("(select count(xh) from xg_zhdj_dzzhdryb where hdid=a.id and tjsj is null) wtj ");
            }
            sql.append(" from xg_zhdj_dzzhdb a");
            sql.append(" where 1=1 ");
            if ("stu".equals(user.getUserType())) {
                sql.append(" and id in(select hdid from xg_zhdj_dzzhdryb where xh = '" + user.getUserName() + "')");
            } else {
                sql.append(" and cjr = '" + user.getUserName() + "'");
            }
            sql.append(searchTj);
            return getPageList(model, sql.toString(), inputV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DzzhdForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select id,hdmc,kssj,jssj,hdlxdm,hdnr,fj,cjr,cjrxm,cjsj,mxdx");
        sql.append(" from xg_zhdj_dzzhdb");
        sql.append(" where 1 = 1");
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    //添加
    public boolean insert(DzzhdForm model) throws Exception {
        boolean result = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("insert into xg_zhdj_dzzhdb(hdmc,kssj,jssj,hdlxdm,hdnr,fj,cjr,cjrxm,cjsj,mxdx)");
            sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
            String[] inputValue = {
                    model.getHdmc(), model.getKssj(), model.getJssj(), model.getHdlxdm(), model.getHdnr(),
                    model.getFj(), model.getCjr(), model.getCjrxm(), model.getCjsj(), model.getMxdx()
            };
            result = dao.insert(sql.toString(), inputValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //根据id获取
    public HashMap<String, String> getById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select id,hdmc,kssj,jssj,hdlxdm,hdnr,fj,cjr,cjrxm,cjsj,mxdx");
        sql.append(" from xg_zhdj_dzzhdb");
        sql.append(" where 1 = 1 and id = ? ");
        HashMap<String, String> data = dao.getMapNotOut(sql.toString(), new String[]{id});
        return data;
    }

    //获取政治面貌信息
    public List<HashMap<String, String>> getZzmmList() {
        String sql = "select zzmmdm,zzmmmc from zzmmdmb order by zzmmdm";
        return dao.getListNotOut(sql, new String[]{});
    }

    //根据id移除活动信息
    public boolean removeById(String hdid) {
        String sql = "delete from xg_zhdj_dzzhdb where id=?";
        try {
            return dao.runDelete(sql, new String[]{hdid}) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("党组织活动信息移除有误:" + e.getMessage());
        }
        return false;
    }

    //根据活动id获取活动人员名单数量
    public Integer getHdryCount(String hdid) {
        String sql = "select count(hdid) nums from xg_zhdj_dzzhdryb where hdid=?";
        String nums = dao.getOneRs(sql, new String[]{hdid}, "nums");
        if (StringUtils.isNotNull(nums)) {
            return Integer.valueOf(nums);
        }
        return 0;
    }

    /**
     * @param model
     * @return boolean
     * @描述: 活动修改
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 10:41
     * @修改记录: 修改人-修改时间-修改描述
     **/
    public boolean update(DzzhdForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_zhdj_dzzhdb set");
        sql.append(" hdmc=?,kssj=?,jssj=?,hdlxdm=?,hdnr=?,fj=?,mxdx=?");
        sql.append(" where id = ?");
        String input[] = {
                model.getHdmc(), model.getKssj(), model.getJssj(), model.getHdlxdm(),
                model.getHdnr(), model.getFj(), model.getMxdx(), model.getId()
        };
        try {
            return dao.runUpdate(sql.toString(), input);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("党组织活动管理-活动修改有误:" + e.getMessage());
        }
        return false;
    }

    /**
     * @param model
     * @return boolean
     * @描述: 活动保存
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 10:42
     * @修改记录: 修改人-修改时间-修改描述
     **/
    public boolean add(DzzhdForm model) {
        boolean flag = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("insert into xg_zhdj_dzzhdb(id,hdmc,kssj,jssj,hdlxdm,hdnr,fj,cjr,cjrxm,cjsj,mxdx)");
            sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
            String[] inputValue = {
                    model.getId(), model.getHdmc(), model.getKssj(), model.getJssj(),
                    model.getHdlxdm(), model.getHdnr(), model.getFj(), model.getCjr(),
                    model.getCjrxm(), model.getCjsj(), model.getMxdx()
            };
            flag = dao.insert(sql.toString(), inputValue);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("党组织活动管理-活动添加有误:" + e.getMessage());
        }
        return flag;
    }

    /**
     * @param model
     * @param user
     * @return java.util.List<java.util.HashMap   <   java.lang.String   ,   java.lang.String>>
     * @描述: 获取可添加活动人员列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 16:56
     * @修改记录: 修改人-修改时间-修改描述
     **/
    public List<HashMap<String, String>> getStuxxList(DzzhdForm model, User user) {
        List<HashMap<String, String>> list = null;
        try {
            String searchTj = SearchService.getSearchTj(model.getSearchModel());
            String[] inputV = SearchService.getTjInput(model.getSearchModel());
            String mxdx = model.getMxdx();
            StringBuilder zzmmSql = new StringBuilder();
            if(StringUtils.isNotNull(mxdx)){
                zzmmSql.append("(");
                String[] zzmm = mxdx.split(",");
                for (int i = 0; i < zzmm.length; i++) {
                    zzmmSql.append("'" + zzmm[i] + "'");
                    if (zzmm.length != 1 && i != zzmm.length - 1) {
                        zzmmSql.append(",");
                    }
                }
                zzmmSql.append(")");
            }
            // 权限过滤
            String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
            StringBuilder sql = new StringBuilder();
            sql.append(" select a.* from(");
            sql.append(" select a.xh,a.xy,a.xydm,a.bjdm,a.xm,a.nj,a.sfzx,a.xb,a.zydm,a.zzmm,");
            sql.append("(case a.xb when '1' then '男' when '2' then '女' else  a.xb end) xbmc,");
            sql.append("(select zymc from bks_zydm where zydm=a.zydm) zymc,");
            sql.append("(select bjmc from bks_bjdm where bjdm=a.bjdm) bjmc,");
            sql.append("(select bmmc from zxbz_xxbmdm where bmdm = a.xydm) xymc");
            sql.append(" from xsxxb a where (a.sfzx='在校' or a.sfzx is null))a where 1=1");
            //过滤已加入的学生
            sql.append(" and not exists(select xh from xg_zhdj_dzzhdryb where hdid='" + model.getId() + "' and xh=a.xh )");
            if(zzmmSql.length() > 0){
                sql.append(" and a.zzmm in "+zzmmSql);
            }
            sql.append(searchTjByUser);
            sql.append(searchTj);
            list = getPageList(model, sql.toString(), inputV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param model
     * @param user
     * @return java.util.List<java.util.HashMap   <   java.lang.String   ,   java.lang.String>>
     * @描述: 获取已添加活动人员列表
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/1 17:26
     * @修改记录: 修改人-修改时间-修改描述
     **/
    public List<HashMap<String, String>> getDzzRyList(DzzhdForm model, User user) {
        List<HashMap<String, String>> list = null;
        StringBuilder sql = new StringBuilder();
        try {
            String searchTj = SearchService.getSearchTj(model.getSearchModel());
            String[] inputV = SearchService.getTjInput(model.getSearchModel());
            sql.append(" select * from (select a.xh,a.hdxd,a.hdid,a.fj,a.tjsj,b.xm,b.nj,b.xydm,b.bjdm,b.xb,b.zydm,");
            sql.append("(case b.xb when '1' then '男' when '2' then '女' else  b.xb end) xbmc,");
            sql.append("(case when a.tjsj is not null  then '已提交' else  '未提交' end) hdxdtx,");
            sql.append("(select zymc from bks_zydm where zydm=b.zydm) zymc,");
            sql.append("(select bjmc from bks_bjdm where bjdm=b.bjdm) bjmc,");
            sql.append("(select bmmc from zxbz_xxbmdm where bmdm = b.xydm) xymc");
            sql.append(" from(select xh,hdxd,hdid,fj,tjsj from xg_zhdj_dzzhdryb where hdid='" + model.getId() + "') a");
            sql.append(" left join xsxxb b on a.xh=b.xh");
            sql.append(") where 1=1 ");
            sql.append(searchTj);
            list = getPageList(model, sql.toString(), inputV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //活动人员添加
    public boolean batchAdd(String hdid, String xhs) {
        String sql = "insert into xg_zhdj_dzzhdryb(hdid,xh) values (?,?)";
        List<String[]> params = new ArrayList<String[]>();
        String xh[] = xhs.split(",");
        for (String item : xh) {
            params.add(new String[]{hdid, item});
        }
        int[] result = new int[0];
        try {
            result = dao.runBatch(sql, params);
            if (result.length == params.size() && result[0] != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //活动人员删除
    public boolean batchRemove(String hdid, String xhs) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from xg_zhdj_dzzhdryb where hdid=? and xh in(");
        String[] xh = xhs.split(",");
        for (int i = 0; i < xh.length; i++) {
            sql.append("'" + xh[i] + "'");
            if (xh.length != 1 && i != xh.length - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        try {
            return dao.runDelete(sql.toString(), new String[]{hdid}) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @描述: 根据活动id 学号获取活动心得信息
     * @作者: 何爽[工号:1730]
     * @日期: 2019/8/2 14:01
     * @修改记录: 修改人-修改时间-修改描述
     * @param hdid 活动id
	* @param xh 学号
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getHdxdInfo(String hdid,String xh){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.id,a.hdid,a.xh,a.hdxd,a.fj,a.tjsj,b.xm");
        sql.append(" from xg_zhdj_dzzhdryb a");
        sql.append(" left join xsxxb b on a.xh=b.xh");
        sql.append(" where a.hdid=? and a.xh=?");
        return dao.getMapNotOut(sql.toString(),new String[]{hdid,xh});
    }

    public boolean saveHdxd(HashMap<String,String> data){
        String sql = "update xg_zhdj_dzzhdryb set hdxd=?,fj=?,tjsj=? where id=?";
        String[] inputV = {
                data.get("hdxd"),data.get("fj"),data.get("tjsj"),
                data.get("id")
        };
        try {
            return dao.runUpdate(sql,inputV);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("党组织活动-心得体会提交失败:"+e.getMessage());
        }
        return false;
    }
}
