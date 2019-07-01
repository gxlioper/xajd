<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/sbwh/xmsb/js/xmsb.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/cxcy_xmsb" method="post" styleId="form" onsubmit="return false;">
    <html:hidden property="xn" styleId="xn" />
    <html:hidden property="xq" styleId="xq" />
    <html:hidden property="id" styleId="id"/>
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 300px' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>项目上报信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">
                    学年学期
                </th>
                <td width="35%">
                        ${map.xn}${map.xqmc}
                </td>
                <th width="15%">大队名称</th>
                <td width="35%">
                        ${map.xymc}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span>填报人</span>
                </th>
                <td >
                        ${map.tbrmc}
                </td>
                <th>
                    <span>填报时间</span>
                </th>
                <td>
                        ${map.tbsj}
                </td>
            </tr>
            <tr>
                <th width="15%">
                    <span><font color="red">*</font>项目名称</span>
                </th>
                <td >
                    <html:text property="xmmc" styleId="xmmc" maxlength="50" style="width:80%;"/>
                </td>
                <th>
                    <span><font color="red">*</font>报告人</span>
                </th>
                <td>
                    <html:text property="bgr" styleId="bgr" maxlength="20"  />
                </td>
            </tr>
            <tr>
                <th>
                    <font color="red">*</font>项目简介
                    <br /><font color="red">&lt;限500字&gt;</font>
                </th>
                <td colspan="3">
                    <html:textarea property='xmjj' style="width:98%" styleId="xmjj" rows='6' onblur="checkLen(this,500);"/>
                </td>
            </tr>
                <%--<tr>
                    <th>
                        <span>附件</span>
                    </th>
                    <td colspan="3">
                        <html:hidden property="fj" styleId="fj"/>
                        <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                        <script type="text/javascript">
                            //调用附件
                            jQuery(function(){
                                jQuery.MultiUploader({
                                    maxcount : 3,
                                    //后缀
                                    accept : 'png|gif|jpg|zip|rar|doc|docx',
                                    //最大文件大小 单位M
                                    maxsize: 10,
                                    //存放附件的隐藏域的id
                                    elementid : 'fj'
                                });
                            });
                        </script>
                    </td>
                </tr>--%>

            </tbody>
        </table>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();">
                            保存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</html:form>
</body>
</html>

