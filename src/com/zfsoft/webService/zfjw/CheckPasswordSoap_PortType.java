/**
 * CheckPasswordSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public interface CheckPasswordSoap_PortType extends java.rmi.Remote {

    /**
     * JSCheckPassword(ByVal strJSBH As String, ByVal strPassword
     * As String) As Integer
     */
    public int JSCheckPassword(java.lang.String strJSBH, java.lang.String strPassword) throws java.rmi.RemoteException;

    /**
     * BMCheckPassword(ByVal strYHM As String, ByVal strPassword As
     * String) As Integer
     */
    public int BMCheckPassword(java.lang.String strYHM, java.lang.String strPassword, javax.xml.rpc.holders.StringHolder xh) throws java.rmi.RemoteException;

    /**
     * XSCheckPassword(ByVal strXH As String, ByVal strPassword As
     * String) As Integer
     */
    public int XSCheckPassword(java.lang.String strXH, java.lang.String strPassword) throws java.rmi.RemoteException;

    /**
     * zfcwjk(ByVal strxn As String, ByVal strxq As String, ByVal
     * strxh As String, ByVal strjfyf As String, ByVal strsfycjf As String,
     * ByVal strjfjkmm As String) As Integer
     */
    public int zfcwjk(java.lang.String strxn, java.lang.String strxq, java.lang.String strxh, java.lang.String strjfyf, java.lang.String strsfycjf, java.lang.String strjfjkmm) throws java.rmi.RemoteException;

    /**
     * EditYhb(ByVal str As String(), ByVal TableName As String, ByVal
     * czcs As String, ByVal xgmm As String)As Integer
     */
    public int editYhb(java.lang.String[] str, java.lang.String tableName, java.lang.String czcs, java.lang.String xgmm) throws java.rmi.RemoteException;

    /**
     * zfcwjk1( ByVal strxn As String,ByVal strxh As String, ByVal
     * strjfyf As String, ByVal strsfycjf As String, ByVal strjfjkmm As String)
     * As Integer
     */
    public int zfcwjk1(java.lang.String strxn, java.lang.String strxh, java.lang.String strjfyf, java.lang.String strsfycjf, java.lang.String strjfjkmm) throws java.rmi.RemoteException;

    /**
     * zfxsxxxg(ByVal str_xh As String, ByVal str_xgxx As String,
     * ByVal str_mm As String) As Integer
     */
    public int zfxsxxxg(java.lang.String str_xh, java.lang.String str_xgxx, java.lang.String str_mm) throws java.rmi.RemoteException;

    /**
     * zfxsjd(ByVal str_xh As String, ByVal str_mm As String)
     */
    public java.lang.String[] zfxsjd(java.lang.String str_xh, java.lang.String str_mm) throws java.rmi.RemoteException;

    /**
     * CreateXxmcXml()
     */
    public java.lang.String createXxmcXml(java.lang.String str1, java.lang.String owner, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * GetXxmcXml()
     */
    public java.lang.String getXxmcXml(java.lang.String url, java.lang.String filename, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Insert3()
     */
    public java.lang.String insert3(java.lang.String str, java.lang.String str1) throws java.rmi.RemoteException;

    /**
     * GetStuCheckinInfo()
     */
    public com.zfsoft.webService.zfjw.StudentCheckinInfoDT0 getStuCheckinInfo(java.lang.String xh, java.lang.String xnxq) throws java.rmi.RemoteException;

    /**
     * GetXyxx()
     */
    public com.zfsoft.webService.zfjw.XyInfoDTO getXyxx(java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * GetZyxx()
     */
    public com.zfsoft.webService.zfjw.ZyInfoDTO getZyxx(java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * GetBjxx()
     */
    public com.zfsoft.webService.zfjw.BjInfoDTO getBjxx(java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * GetXjyd()
     */
    public com.zfsoft.webService.zfjw.XjydInfoDTO getXjyd(java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * GetXsxx(ByVal beginCount As String, ByVal endCount As String,
     * ByVal strPass As String)
     */
    public com.zfsoft.webService.zfjw.XsInfoDTO getXsxx(java.lang.String beginCount, java.lang.String endCount, java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * getXsInfoAmount(ByVal strPass As String)
     */
    public int getXsInfoAmount(java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * GetCjxx(ByVal beginCount As String, ByVal endCount As String,
     * ByVal strPass As String)
     */
    public com.zfsoft.webService.zfjw.CjInofDTO getCjxx(java.lang.String beginCount, java.lang.String endCount, java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * getCjInfoAmount(ByVal strPass As String)
     */
    public int getCjInfoAmount(java.lang.String strPass) throws java.rmi.RemoteException;

    /**
     * ChangePwd(ByVal strId As String, ByVal strPwd As String, ByVal
     * strJs As String, ByVal strJkPwd As String)
     */
    public boolean changePwd(java.lang.String strId, java.lang.String strPwd, java.lang.String strJs, java.lang.String strJkPwd) throws java.rmi.RemoteException;
}
