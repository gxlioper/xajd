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
    <script type="text/javascript" src="xsgzgl/zhdj/djgzjl/js/djgzjlEdit.js"></script>

    <script type="text/javascript">
    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/zhdj_djgzjl"  method="post"  styleId="form">
    <html:hidden property="xydm" styleId="xydm"  value="${xydm}"/>
    <html:hidden property="xn" styleId="xm"  value="${xn}"/>
    <html:hidden property="xqdm" styleId="xqdm"  value="${xqdm}"/>
    <div style='width:100%; height:270px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="20%">
                    ѧ��
                </th>
                <td colspan="3">
                        ${xn}
                </td>
            </tr>
            <tr style="">
                <th width="20%">
                    ѧ��
                </th>
                <td colspan="3">
                        ${xqmc}
                </td>
            </tr>
            <tr style="">

                <th >
                    ����ѧԺ
                </th>
                <td colspan="3">
                        ${xymc}
                </td>
            </tr>
            <tr style="">
                <th width="20%">
                    <span style="color: red">*</span> ��֧��������
                </th>
                <td width="30%">
                    <html:text property="yhjs" styleId="yhjs" style="width:100px;" onblur="checkInt(this);"/>
                </td>
                <th width="20%">
                    <span style="color: red">*</span> ��֧��ʵ�ʻ�����
                </th>
                <td width="30%">
                    <html:text property="sjhjs" styleId="sjhjs" style="width:100px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> ���ȱ�������Ա��չ����
                </th>
                <td >
                    <html:text property="jdbksdyfzrs" styleId="jdbksdyfzrs" style="width:100px;" onblur="checkInt(this);"/>
                </td>
                <th >
                    <span style="color: red">*</span> �����о�����Ա��չ����
                </th>
                <td >
                    <html:text property="jdyjsdyfzrs" styleId="jdyjsdyfzrs" style="width:100px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> ��֧���Ƿ�ʱ���ɵ���
                </th>
                <td colspan="3">
                    <html:select  property="sfasjndf" styleId="sfasjndf" style="width:100px" >
                        <html:option value=""></html:option>
                        <html:option value="1">��</html:option>
                        <html:option value="0">��</html:option>
                    </html:select>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"Ϊ������
                </div>
                <div class="btn">
                    <button id="buttonSave" onclick="save();return false;">
                        �� ��
                    </button>
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

