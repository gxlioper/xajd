<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>

    <script type="text/javascript" src="xsgzgl/xyfd/js/jquery.raty.js"></script>
</head>

<body style="width:100%">
<html:form action="/xyfd_jljgtj" method="post" styleId="demoForm">
    <div style="width: 100%;height: 100%">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4"><span>��������</span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">������Ա����</th>
                <td width="30%">
                    ${jlxx.fdjsxm}
                </td>
                <th width="20%">������Ա����</th>
                <td width="30%">
                    ${jlxx.fdjslx}
                </td>
            </tr>
            <tr>
                <th width="20%">����ʱ��</th>
                <td width="30%">
                        ${jlxx.fdsj}
                </td>
                <th width="20%">�����ص�</th>
                <td width="30%">
                        ${jlxx.fddd}
                </td>
            </tr>
            <tr>
                <th width="20%">������Ŀ</th>
                <td width="30%" colspan="3">
                        ${jlxx.kcmc}
                </td>
            </tr>
            <tr>
                <th width="20%">ѧ������</th>
                <td width="30%">
                        ${jlxx.xm}
                </td>
                <th width="20%">ѧ���绰</th>
                <td width="30%">
                        ${jlxx.sjhm}
                </td>
            </tr>
            <tr>
                <th width="20%">��������</th>
                <td width="30%" colspan="3">
                    <html:textarea name="jlxx" property="fdjl" styleId="fdjl"
                                   style="width: 100%;height: 150px;resize:none;" readonly="true"></html:textarea>
                </td>
            </tr>
            <tr>
                <th width="20%">�Ƿ���</th>
                <td width="30%">
                    ${jlxx.sfjj}
                </td>
                <th width="20%">����</th>
                <td width="30%">
                    <div style="text-align: center">
                        <div id="star"></div>
                        <div><input type="hidden" id="pf" name="pf" value="${jlxx.pf}"/></div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>ѧ����������</th>
                <td colspan="3">
                    <html:textarea name="jlxx" property="xxpj" styleId="xxpj"
                                   style="width: 100%;height: 150px;resize:none;" readonly="true"></html:textarea>
                </td>
            </tr>
        </table>
    </div>
    <div style="position:fixed;bottom:0;width: 100%">
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="btn">
                        <button type="button" name="�� ��" onclick="iFClose();">
                            �� ��
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    </div>
</html:form>
</body>
</html>
<script type="text/javascript">

    jQuery(function() {
        var pf = jQuery('#pf').val();
        jQuery.fn.raty.defaults.path = 'xsgzgl/xyfd/img';
        jQuery('#star').raty({
            score: pf,
            half: true,
            readOnly: true,
            click: function(score){
                jQuery('#pf').val(score);
            }
        });
    });
</script>