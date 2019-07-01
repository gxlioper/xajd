<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/gygl/xjdgybz/ktsq/js/ktsq.js"></script>
    <script type="text/javascript">
        function save(type){
            var url = "gygl_gybz_wh.do?method=add&type="+type;
            ajaxSubFormWithFun("demoForm", url, function(data) {
                if(data["message"]=="����ɹ���"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            });
        }
        jQuery(function(){
            xqChange();
        })
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_gybz_wh">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ѧ��
                </th>
                <td>
                    <html:select property="xn" styleId="xn">
                        <html:options collection="xnList" property="xn" labelProperty="xn"></html:options>
                    </html:select>
                </td>
                <th>
                    У��
                </th>
                <td>
                    <html:select property="xqdm" styleId="xqdm" onchange="xqChange()">
                        <html:options collection="xqList" property="dm" labelProperty="xqmc"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    ����¥����
                </th>
                <td>
                    <html:select property="lddm" styleId="lddm" onchange="ldChange();">
                    </html:select>
                </td>
                <th>�����</th>
                <td>
                    <html:select property="qsh" styleId="qsh" onchange="qshChange();">
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>��������</th>
                <td>
                    <html:text styleId="rs" property="rs"/>
                </td>
                <th>���᳤</th>
                <td>
                    <html:text styleId="sszmc" property="sszmc"/>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>���ҳ�Ա</span>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4">
                        <table id="shlccx_table" width="100%">
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>����</th>
                    <td>
                        <html:radio property="gl" value="1.0P">1.0P</html:radio>
                        <html:radio property="gl" value="1.2P">1.2P</html:radio>
                    </td>
                    <th>ʹ�÷�ʽ</th>
                    <td>
                        <html:radio property="syfs" value="����">����</html:radio>
                        <html:radio property="syfs" value="�Թ�">�Թ�</html:radio>
                    </td>
                </tr>
                <tr>
                    <th>ʹ������</th>
                    <td>
                        <html:text property="synx" styleId="synx" maxlength="2"/>
                    </td>
                    <th>��װ����</th>
                    <td>
                        <html:text property="azrq" styleId="azrq" readonly="true" onclick="return showCalendar('azrq','yyyy-MM-dd');"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save('save');" id="buttonSave">
                            ����ݸ�
                        </button>
                        <button type="button" onclick="save('submit');" id="buttonSave">
                            �ύ����
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
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