/**
 * Ws_xgxtSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zjcm;

public interface Ws_xgxtSoap_PortType extends java.rmi.Remote {
    public java.lang.String[][] wsqj_qjlx_get() throws java.rmi.RemoteException;
    public java.lang.String wsqj_qjlx_create(java.lang.String lxmc, java.lang.String beizhu) throws java.rmi.RemoteException;
    public java.lang.String wsqj_qjlx_update(java.lang.String lxid, java.lang.String lxmc, java.lang.String beizhu) throws java.rmi.RemoteException;
    public java.lang.String wsqj_qjlx_delete(java.lang.String lxid) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_self_get(java.lang.String dqym, java.lang.String ymdx, java.lang.String xuehao) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_banji_get(java.lang.String dqym, java.lang.String ymdx, java.lang.String bjid, java.lang.String ksrq, java.lang.String jsrq, java.lang.String xuehao, java.lang.String shzt) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_xueyuan_get(java.lang.String dqym, java.lang.String ymdx, java.lang.String xyid, java.lang.String ksrq, java.lang.String jsrq, java.lang.String xuehao, java.lang.String shzt) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_all_get(java.lang.String dqym, java.lang.String ymdx, java.lang.String ksrq, java.lang.String jsrq, java.lang.String xuehao, java.lang.String shzt) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_single_get(java.lang.String qjid) throws java.rmi.RemoteException;
    public java.lang.String wsqj_qingjia_add(java.lang.String xuenian, java.lang.String xueqi, java.lang.String xyid, java.lang.String xymc, java.lang.String bjid, java.lang.String bjmc, java.lang.String xingming, java.lang.String xuehao, java.lang.String qjlx, java.lang.String qsrq, java.lang.String qjts, java.lang.String qjyy) throws java.rmi.RemoteException;
    public java.lang.String wsqj_qingjia_reply(java.lang.String qjid, java.lang.String shzt, java.lang.String shyj, java.lang.String clr) throws java.rmi.RemoteException;
    public java.lang.String wsqj_qingjia_delete(java.lang.String qjid) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_stat_banji_get(java.lang.String bjid) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_stat_xueyuan_get(java.lang.String xyid) throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_qingjia_stat_all_get() throws java.rmi.RemoteException;
    public java.lang.String[][] wsqj_biaotou_get() throws java.rmi.RemoteException;
    public java.lang.String[][] liuyan_lylx_get() throws java.rmi.RemoteException;
    public java.lang.String[][] liuyan_ly_all_get(java.lang.String dqym, java.lang.String ymdx, java.lang.String lxid, java.lang.String ksrq, java.lang.String jsrq, java.lang.String xuehao, java.lang.String sfdf) throws java.rmi.RemoteException;
    public java.lang.String[][] liuyan_ly_banji_get(java.lang.String dqym, java.lang.String ymdx, java.lang.String bjid, java.lang.String ksrq, java.lang.String jsrq, java.lang.String xuehao, java.lang.String sfdf) throws java.rmi.RemoteException;
    public java.lang.String[][] liuyan_ly_single_get(java.lang.String lyid) throws java.rmi.RemoteException;
    public java.lang.String[][] liuyan_ly_topn_get(java.lang.String lxid, java.lang.String tiaoshu) throws java.rmi.RemoteException;
    public java.lang.String liuyan_ly_add(java.lang.String xuenian, java.lang.String xueqi, java.lang.String xyid, java.lang.String xymc, java.lang.String bjid, java.lang.String bjmc, java.lang.String xingming, java.lang.String xuehao, java.lang.String lylx, java.lang.String biaoti, java.lang.String neirong) throws java.rmi.RemoteException;
    public java.lang.String liuyan_ly_reply(java.lang.String lyid, java.lang.String dfr, java.lang.String dfbm, java.lang.String dfnr) throws java.rmi.RemoteException;
    public java.lang.String liuyan_ly_delete(java.lang.String lyid) throws java.rmi.RemoteException;
    public java.lang.String[][] liuyan_ly_stat_get(java.lang.String lylx) throws java.rmi.RemoteException;
    public java.lang.String[][] liuyan_biaotou_get() throws java.rmi.RemoteException;
    public java.lang.String zhiban_base_add(java.lang.String zbrq, java.lang.String bmid, java.lang.String bmmc, java.lang.String zbry) throws java.rmi.RemoteException;
    public java.lang.String[][] zhiban_base_get(java.lang.String bmid) throws java.rmi.RemoteException;
    public java.lang.String[][] zhiban_base_zizhu_get() throws java.rmi.RemoteException;
}
