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
    <script type="text/javascript">
        function save(){
            if(jQuery("input[name='fsbj']:checked").val() == "�ѷ�ˢ"){
                if(jQuery("#fsrq").val() == ""){
                    showAlert("����д��ˢ������ڣ�");
                    return false;
                }
            }
            var url = "gygl_fygl_fsbjwh.do?method=add&type=save";
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

        })

    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_fsbjwh">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>��ˢ���</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ��ˢ�������
                </th>
                <td>
                    <logic:notEmpty name="xzgs">
                        <input value="${pks}" name="pks" type="hidden" id="pk"/>
                        ��ǰ��ѡ��<span class="red">${xzgs}</span>�����ҽ���������ˢ���
                    </logic:notEmpty>
                    <logic:notEmpty name="cxgs">
                        ��ǰ��ѯ����й���<span class="red">${cxgs}</span>�����ҿɽ���������ˢ���
                    </logic:notEmpty>
                </td>
            </tr>
            <tr>
                <th>���</th>
                <td>
                    <input type="hidden" name="xn" value="${nd}">
                    ${nd}
                </td>
            </tr>
            <tr>
                <th>��ˢ״̬</th>
                <td>
                    <input type="radio" name="fsbj" checked value="�ѷ�ˢ">&nbsp;�ѷ�ˢ
                    <input type="radio" name="fsbj" value="δ��ˢ">&nbsp;δ��ˢ
                </td>
            </tr>
            <tr>
                <th>��ˢ�������</th>
                <td>
                    <input name="fsrq" id="fsrq" readonly="readonly" onclick="return showCalendar('fsrq','yyyy-MM-dd');"/>
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
                        <button type="button" onclick="save();" id="buttonSave">
                            �� ��
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