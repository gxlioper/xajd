<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbgl/js/zwwh.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">

        jQuery(function ($) {
            var zwss = $('#selzwss').val();
            //������ò
            jQuery("#zwss option").each(function () {
                if (zwss == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });
            //����״̬
            var zwlx = $('#selzwlx').val();
            jQuery("#zwlx option").each(function () {
                if (zwlx == ($(this).val())) {
                    $(this).attr('selected', true);
                }
            });
        });

    </script>
    <style type="text/css">
    </style>
</head>
<body>
<html:form action="/dzdy_zwwh" method="post" styleId="DzbzwwhForm">

    <div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�޸�ְ����Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <th><font color="red">*</font>����</th>
            <td>
 				<html:hidden property="dm" styleId="dm" value="${dm}"/>${dm}
            </td>
            </tr>
            <th><font color="red">*</font>����</th>
            <td>
                <html:text property="mc" styleId="mc" style="width:90%" value ="${mc}"/>
            </td>
            </tr>
            <tr>
                <th><font color="red">*</font>ְ������</th>
                <td>
                    <input type="hidden" id="selzwss" value="${zwss}"/>
                    <html:select property="zwss" style="width:152px" styleId="zwss">
                        <option value='�̹���֧��'>�̹���֧��</option>
                        <option value='ѧ����֧��'>ѧ����֧��</option>
                        <option value='����֧'>����֧</option>
                    </html:select>
                </td>
            </tr>
             <tr>
                <th><font color="red">*</font>ְ������</th>
                <td>
                    <input type="hidden" id="selzwlx" value="${zwlx}"/>
                    <html:select property="zwlx" style="width:152px" styleId="zwlx">
                          <option value='��ʦ'>��ʦ</option>
                    	  <option value='ѧ��'>ѧ��</option>
                    </html:select>
                </td>
            </tr>
            </tbody>

        </table>
    </div>
    <div style="height:30px;"></div>
    <%--;height:520px --%>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="updateSaveDm();">
                            �� ��
                        </button>
                        <button type="button" onclick="iFClose();">
                            �ر�
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