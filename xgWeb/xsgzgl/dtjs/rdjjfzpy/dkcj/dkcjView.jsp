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
    <script type="text/javascript" src="xsgzgl/dtjs/rdjjfzpy/dkcj/js/dkcj.js"></script>
    <script type="text/javascript">
        jQuery(function(){

        });
    </script>
</head>
<body style="width: 100%">
<html:form action="/dtjs_dkcj" method="post" styleId="form" onsubmit="return false;">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;height: 350px' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ��������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>���γɼ���Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="15%">
                    ���γɼ�
                </th>
                <td width="35%">
                        ${map.dkcj}
                </td>
                <th>
                    ����ʱ��
                </th>
                <td>
                    ${map.kssj}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" onclick="iFClose();">
                            �ر�
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

