<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/jjgl_jjxq" method="post" styleId="jjxqForm" onsubmit="return false;">
    <input type="hidden" id="jjbh" name="jjbh" value="${jjbh}"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>�ҽ�ʱ��ά��</span>
                </th>
            </tr>
            </thead>
        </table>
        <table width="100%" border="0" class="formlist">
            <tr>
                <th>�ҽ̱��</th>
                <th>�ҽ�����</th>
                <th>�ҽ̹�ʱ</th>
                <th>����</th>
            </tr>
            <tr>
                <td>${jjbh}</td>
                <td><input id="jjny" name="jjny" onclick="return showCalendar('jjny','yyyy��MM��',false);"/></td>
                <td><input id="jjgs" name="jjgs" onclick="checkTimeNumForKeyup(this)" onblur="checkTimeNumForBlur(this)"/></td>
                <td>-</td>
            </tr>
            <logic:notEmpty  name="gsxxList">
                <logic:iterate id="gsxx" name="gsxxList">
                    <tr>
                        <td>${gsxx.jjbh}</td>
                        <td>${gsxx.jjny}</td>
                        <td>${gsxx.jjgs}</td>
                        <td><a href="javascript:void(0)" onclick="delGsxx('${gsxx.jjbh}','${gsxx.jjny}')">ɾ��</a></td>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>

        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"Ϊ������
                    </div>
                    <div class="btn">
                        <button type="button" onclick="jjgsSave();">
                            ����
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

<script type="text/javascript">
    /**
     * �ҽ̹�ʱ�ı���
     */
    function jjgsSave(){
        var jjbh = jQuery("#jjbh").val();
        var jjny = jQuery("#jjny").val();
        var jjgs = jQuery("#jjgs").val();
        if (jjny == ""|| jjgs == ""){
            showAlert("�뽫��������д������");
            return false;
        }

        var url = "jjgl_jjxq.do?method=jjgsSave";

        jQuery.post(url, {
            jjbh:jjbh,
            jjny : jjny,
            jjgs:jjgs
        }, function(data) {
            if(data["message"]=="����ɹ���"){
                showAlert(data["message"],{},{"clkFun":function(){
                    iFClose();
                }});
            }else{
                showAlert(data["message"]);
            }
        }, 'json');
    }
</script>
</body>
</html>

