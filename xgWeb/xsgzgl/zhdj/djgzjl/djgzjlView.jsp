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
                    ѧ��
                </th>
                <td colspan="3">
                        ${map.xn}
                </td>
            </tr>
            <tr style="">
                <th width="20%">
                    ѧ��
                </th>
                <td colspan="3">
                        ${map.xqmc}
                </td>
            </tr>
            <tr style="">

                <th >
                    ����ѧԺ
                </th>
                <td colspan="3">
                        ${map.xymc}
                </td>
            </tr>
            <tr style="">
                <th width="20%">
                    ��֧��������
                </th>
                <td width="30%">
                        ${map.yhjs}
                </td>
                <th width="20%">
                    ��֧��ʵ�ʻ�����
                </th>
                <td width="30%">
                        ${map.sjhjs}
                </td>
            </tr>
            <tr style="">
                <th >
                    ���ȱ�������Ա��չ����
                </th>
                <td >
                        ${map.jdbksdyfzrs}
                </td>
                <th >
                    �����о�����Ա��չ����
                </th>
                <td >
                        ${map.jdyjsdyfzrs}
                </td>
            </tr>
            <tr style="">
                <th >
                     ��֧���Ƿ�ʱ���ɵ���
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
                    "<span class="red">*</span>"Ϊ������
                </div>--%>
                <div class="btn">
                    <%--<button id="buttonSave" onclick="save();return false;">
                        �� ��
                    </button>--%>
                    <button onclick="Close();return false;">
                        �� ��
                    </button>

                </div>
            </td>
        </tr>
        </tfoot>
    </table>

</html:form>
</body>
</html>

