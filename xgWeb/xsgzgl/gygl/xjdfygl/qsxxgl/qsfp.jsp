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
                if(data["message"]=="保存成功！"){
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
            //初始化加载好下拉框选项
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
                    <span>分配书院/学院</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    提示
                </th>
                <td>
                    当前已选择<span class="red">${xzgs}</span>个宿舍
                </td>
            </tr>
            <tr>
                <th>
                    分配方式
                </th>
                <td >
                    <input type="radio" name="fpfs" value="sy" checked>书院（本科生）
                    <input type="radio" name="fpfs" value="xy">学院（研究生）
                </td>
            </tr>
            <tr>
                <th width="20%">书院/学院</th>
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
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
                            关 闭
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