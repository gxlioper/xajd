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
            var url = "gygl_fygl_qsxxgl10698.do?method=qsfp&type=save";
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
        function createStr(data){
            var str = "";
            for(var i=0;i<data.length;i++){
                if(typeof data[i]["mc"] != "undefined" && data[i]["mc"].trim() != ""){
                    str += "<option value='"+data[i]["dm"]+"'>"+data[i]["mc"]+"</option>";
                }
            }
            return str;
        }
        var syStr;
        var xyStr;
        jQuery(function(){
            //��ʼ�����غ�������ѡ��
            syStr = createStr(${syList});
            xyStr = createStr(${xyList});

            jQuery("input[name='fpfs']").change(function(){
                var fpfs = jQuery("input[name='fpfs']:checked").val();
                jQuery("#xydm").empty();
                if("xy" == fpfs){
                    jQuery("#xydm").append(xyStr);
                } else {
                    jQuery("#xydm").append(syStr);
                }
            });

            jQuery("input[name='fpfs']").trigger('change');
        })
    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_qsxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <input type="hidden" value="${pks}" name="pks"/>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ժ/ѧԺ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    ��ʾ
                </th>
                <td>
                    ��ǰ��ѡ��<span class="red">${xzgs}</span>������
                </td>
            </tr>
            <tr>
                <th>
                    ���䷽ʽ
                </th>
                <td >
                    <input type="radio" name="fpfs" value="sy" checked>��Ժ����������
                    <input type="radio" name="fpfs" value="xy">ѧԺ���о�����
                </td>
            </tr>
            <tr>
                <th width="20%">��Ժ/ѧԺ</th>
                <td width="70%">
                    <select name="xydmId" id="xydm" style="width: 80%">
                    </select>
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