<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/cxcy_xmsb" method="post" styleId="form" onsubmit="return false;">
    <html:hidden property="id" styleId="id" />
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
                    <span>项目名称</span>
                </th>
                <td >
                        ${map.xmmc}
                </td>
                <th>
                    <span>报告人</span>
                </th>
                <td>
                        ${map.bgr}
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
                <th>
                    项目简介
                </th>
                <td colspan="3">
                        ${map.xmjj}
                </td>
            </tr>
                <%--<tr>
                    <th>
                        <span>附件</span>
                    </th>
                    <td colspan="3">
                        <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                        <script type="text/javascript">
                            //调用附件
                            jQuery(function(){
                                var gid = "${map.fj}";
                                jQuery.MultiUploader_q({
                                    gid : gid
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

