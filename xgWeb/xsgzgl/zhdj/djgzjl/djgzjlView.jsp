<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>

    <script type="text/javascript">
    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/zhdj_djgzjl"  method="post"  styleId="form">
    <div style='width:100%; height:270px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="20%">
                    学年
                </th>
                <td colspan="3">
                        ${map.xn}
                </td>
            </tr>
            <tr style="">
                <th width="20%">
                    学期
                </th>
                <td colspan="3">
                        ${map.xqmc}
                </td>
            </tr>
            <tr style="">

                <th >
                    所属学院
                </th>
                <td colspan="3">
                        ${map.xymc}
                </td>
            </tr>
            <tr style="">
                <th width="20%">
                    党支部换届数
                </th>
                <td width="30%">
                        ${map.yhjs}
                </td>
                <th width="20%">
                    党支部实际换届数
                </th>
                <td width="30%">
                        ${map.sjhjs}
                </td>
            </tr>
            <tr style="">
                <th >
                    季度本科生党员发展人数
                </th>
                <td >
                        ${map.jdbksdyfzrs}
                </td>
                <th >
                    季度研究生党员发展人数
                </th>
                <td >
                        ${map.jdyjsdyfzrs}
                </td>
            </tr>
            <tr style="">
                <th >
                     党支部是否按时交纳党费
                </th>
                <td colspan="3">
                        ${map.sfasjndfmc}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <%--<div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>--%>
                <div class="btn">
                    <%--<button id="buttonSave" onclick="save();return false;">
                        保 存
                    </button>--%>
                    <button onclick="Close();return false;">
                        关 闭
                    </button>

                </div>
            </td>
        </tr>
        </tfoot>
    </table>

</html:form>
</body>
</html>

