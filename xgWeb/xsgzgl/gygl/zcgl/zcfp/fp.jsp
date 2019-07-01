<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/gygl/zcgl/zcfp/js/fp.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            onShow("add");
        });
    </script>
</head>
<body style="width: 100%">
<html:form action="/gygl_zcgl_zcfpgl" method="post" styleId="demoForm" onsubmit="return false;">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    校区
                </th>
                <td>
                    <html:select property="xqdm" styleId="xqdm" onchange="xqChange()">
                        <html:options collection="xqList" property="dm" labelProperty="xqmc"></html:options>
                    </html:select>
                </td>
                <th>
                    宿舍楼栋号
                </th>
                <td>
                    <html:select property="lddm" styleId="lddm" onchange="ldChange();">
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    层号
                </th>
                <td>
                    <html:select property="ch" styleId="ch" onchange="lcChange();">
                    </html:select>
                </td>
                <th>寝室号</th>
                <td>
                    <html:select property="qsh" styleId="qsh">
                    </html:select>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室资产信息&nbsp;&nbsp;<a href="#" onclick="addTr();" class="name">增加一行</a></span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="4">
                    <style type="text/css">
                        #shlccx_table th{text-align: center;}
                        #shlccx_table tr{text-align: center;}
                    </style>
                    <table id="shlccx_table" style="width: 98%;">
                        <tr>
                            <th><span class="red">*</span>资产类型</th>
                            <th><span class="red">*</span>资产名称</th>
                            <th><span class="red">*</span>数量</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('fp');">
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
    <%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>

