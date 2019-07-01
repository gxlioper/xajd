package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkzbb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ����ũҵ��ѧ
 * ѧ����������ܱ���dao.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:13
 */
public class XsgzqkZbbDao extends SuperDAOImpl<XsgzqkZbbForm> {
    /**
     * ��������DAO����Ӧ�ı�
     */
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_rcsw_xsgzqk_zbb");
        super.setKey("id");
        super.setClass(XsgzqkZbbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkZbbForm xsgzqkZbbForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkZbbForm xsgzqkZbbForm, User user) throws Exception {

        //���ɸ߼���ѯ�������������ֵ
        SearchModel searchModel = xsgzqkZbbForm.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchModel);
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "bmdm");
        String[] inputV = SearchService.getTjInput(searchModel);

        StringBuilder sql = new StringBuilder("SELECT * FROM (");
        sql.append("SELECT a.id,a.xn,a.xq,b.xqmc,a.xxzt,a.xxnr,a.bsr,d.xm bsrmc,a.bssj,a.lxdh,a.dwfzr,e.xm dwfzrmc,");
        sql.append("a.zc,a.zcksjsrq,a.bmdm,c.bmmc bsdwmc,a.clqk,a.bz FROM xg_rcsw_xsgzqk_zbb a ");
        sql.append("LEFT JOIN xqdzb b ON a.xq = b.xqdm ");
        sql.append("LEFT JOIN zxbz_xxbmdm c ON a.bmdm = c.bmdm ");
        sql.append("LEFT JOIN fdyxxb d ON a.bsr = d.zgh ");
        sql.append("LEFT JOIN fdyxxb e ON a.dwfzr = e.zgh ");
        sql.append(") t WHERE 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);

        return getPageList(xsgzqkZbbForm, sql.toString(), inputV);
    }

    /**
     *  ����id��ѯһ���ܱ�����Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-23 15:44
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getZbbById(String id) {

        StringBuilder sql = new StringBuilder("SELECT a.id,a.xn,a.xq,b.xqmc,a.xxzt,a.xxnr,a.bsr,d.xm bsrmc,a.bssj,a.lxdh,a.dwfzr,e.xm dwfzrmc,");
        sql.append("a.zc,a.zcksjsrq,a.bmdm,c.bmmc bsdwmc,a.clqk,a.bz,a.fjid FROM xg_rcsw_xsgzqk_zbb a ");
        sql.append("LEFT JOIN xqdzb b ON a.xq = b.xqdm ");
        sql.append("LEFT JOIN zxbz_xxbmdm c ON a.bmdm = c.bmdm ");
        sql.append("LEFT JOIN fdyxxb d ON a.bsr = d.zgh ");
        sql.append("LEFT JOIN fdyxxb e ON a.dwfzr = e.zgh ");
        sql.append("WHERE a.id = ?");

        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }

    /**
     *  �ж�ͬѧ��ѧ��ͬѧԺ�����ܴ��Ƿ��Ѵ�������.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-24 15:09
     * @param xsgzqkZbbForm
     * @return boolean
     * @throw
     */
    public boolean isZcRepeat(XsgzqkZbbForm xsgzqkZbbForm) {

        StringBuilder sql = new StringBuilder("SELECT count(1) num FROM xg_rcsw_xsgzqk_zbb ");
        sql.append("WHERE xn = ? AND xq = ? AND bmdm = ? AND zc = ? ");

        List<String> inputList = new ArrayList<String>();
        inputList.add(xsgzqkZbbForm.getXn());
        inputList.add(xsgzqkZbbForm.getXq());
        inputList.add(xsgzqkZbbForm.getBmdm());
        inputList.add(xsgzqkZbbForm.getZc());

        if(!StringUtil.isNull(xsgzqkZbbForm.getId())){
            sql.append("and id <> ? ");
            inputList.add(xsgzqkZbbForm.getId());
        }
        String [] inputValue = inputList.toArray(new String[]{});
        String num = dao.getOneRs(sql.toString(),inputValue,"num");
        return Integer.valueOf(num)>0;
    }
    public List<HashMap<String, String>> getXxbsbList(String id ) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id,a.xn,a.xq,b.xqmc,a.xxzt,a.xxnr,a.bsr,d.xm bsrmc,a.bssj,a.lxdh,a.dwfzr,e.xm dwfzrmc,");
        sql.append("a.zc,a.zcksjsrq,a.bmdm,c.bmmc bsdwmc,a.clqk,a.bz FROM xg_rcsw_xsgzqk_zbb a ");
        sql.append("LEFT JOIN xqdzb b ON a.xq = b.xqdm ");
        sql.append("LEFT JOIN zxbz_xxbmdm c ON a.bmdm = c.bmdm ");
        sql.append("LEFT JOIN fdyxxb d ON a.bsr = d.zgh ");
        sql.append("LEFT JOIN fdyxxb e ON a.dwfzr = e.zgh where id = ?");

        return dao.getListNotOut(sql.toString(), new String[] { id});
    }
}
