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
<html:form action="/xyfd_mydpj" method="post" styleId="demoForm">
    <input type="hidden" name="jlbh" id="jlbh" value="${jlxx.jlbh}"/>
    <input type="hidden" name="lx" id="lx" value="${jlxx.lx}"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4"><span>���ۿγ�</span></th>
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
                <th width="20%"><span class="red">*</span>����</th>
                <td width="30%" colspan="3">
                    <div style="text-align: center">
                        <div id="star"></div>
                        <div><input type="hidden" id="pf" name="pf" value="${pjxx.pf}"/></div>
                    </div>
                </td>
            </tr>
            <tr>
                <th width="20%"><span class="red">*</span>�Ƿ������⣺</th>
                <td width="30%" colspan="3" >
                    ${pjxx.sfjj}
                </td>
            </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4"><span class="red">*��ϸ����</span></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4">
                        <html:textarea name="pjxx" property="xxpj" styleId="xxpj"
                                       style="width: 100%;height: 250px;resize:none;" readonly="true"></html:textarea>
                    </td>
                </tr>
            </tbody>
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