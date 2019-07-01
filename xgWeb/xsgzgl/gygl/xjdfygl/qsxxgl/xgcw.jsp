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
            var checkIds = "xqdm-lddm-ch-qsh-cws-sfbz";
            if(!checkNotNull(checkIds)){
                showAlert("�뽫��������д������");
                return false;
            }
            var url = "gygl_fygl_qsxxgl10698.do?method=add&type=save";
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
        function refreshTable(data){
            jQuery("#cwTd").empty();
            if(data != null){
                var html = "";
                for(var i=0;i<data.length;i++){
                    html += "<tr>";
                    html += "<td>"+data[i]["cwh"]+"</td>";
                    html += "<td>"+nullToBlank(data[i]["xm"])+"</td>";
                    html += "<td>"+nullToBlank(data[i]["rzsj"])+"</td>";
                    if(data[i]["xm"] == "" || data[i]["xm"] == null){
                        html += "<td><a href=\"javascript:void(0);\" onclick=\"deleteRow(this)\">ɾ����λ</a></td>";
                    } else {
                        html += "<td></td>";
                    }
                    html += "</tr>";
                }

                jQuery("#cwTd").append(html);
            }
        }
        /**
         * ɾ����λ
         * @param target
         */
        function deleteRow(target){
            var cwh = target.parentElement.parentElement.firstElementChild.innerHTML;
            jQuery.post("gygl_fygl_qsxxgl10698.do?method=xgcw&type=del",{cwh:cwh.trim(),pk:jQuery("#pk").val()},function(data){
                if(data.status == "success"){
                    jQuery(target.parentElement.parentElement).remove();
                } else {
                    jQuery.error("��λɾ��ʧ�ܣ�")
                }
            },'json');
        }
        function nullToBlank(val){
            return val == null ? "":val;
        }
        function toUpperCase(target){
            if(/[a-z]/.test(jQuery(target).val())){
                jQuery(target).val(jQuery(target).val().toUpperCase());
            }
        }
        function addcw() {
            var cwh = jQuery("#cwh").val();
            if(cwh == ""){
                showAlert("�����봲λ��!");
                return false;
            }
            jQuery.post("gygl_fygl_qsxxgl10698.do?method=xgcw&type=save",{pk:jQuery("#pk").val(),cwh:cwh.trim()},function(data){
                if(data.status == "success"){
                    jQuery("#cwh").val("");
                    query();
                } else {
                    showAlert(data["message"]);
                }
            },'json')
        }
        function query(){
            jQuery.post("gygl_fygl_qsxxgl10698.do?method=xgcw&type=query",{pk:jQuery("#pk").val()},function(data){
                refreshTable(data);
            },'json')
        }
        jQuery(function(){
            query();
        })

    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_qsxxgl10698">
    <input value="${pk}" type="hidden" id="pk"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>ѧ����λ��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="4">
                    <div class="con_overlfow">
                        <style>
                            #shlccx_table th{text-align: center;}
                            #shlccx_table tr{text-align: center;}
                        </style>
                        <h4 style="padding-left: 40%;">${data.ldmc}${data.qsh}���ᴲλ��Ϣ</h4>
                        <table id="shlccx_table" width="100%" class="formlist" >
                            <tr>
                                <th>��λ���</th>
                                <th>ѧ������</th>
                                <th>��סʱ��</th>
                                <th>����</th>
                            </tr>
                            <tbody id="cwTd">

                            </tbody>
                        </table>
                        <div>
                            ��Ӵ�λ����λ���<input name="cwh" id="cwh" maxlength="10" oninput="toUpperCase(this)"><button type="button" id="add" onclick="addcw();return false;">���</button>
                        </div>
                    </div>
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