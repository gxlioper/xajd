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
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript">
        function save(type){
            var hblx = "";
            var checkbox = jQuery("input[name='hblx']:checked");
            if(checkbox.length == 0){
                showAlertDivLayer("请选择类型！");
                return false;
            }
            for(var i=0;i<checkbox.length;i++){
                hblx += jQuery(checkbox[i]).val();
                if(i<checkbox.length-1)
                    hblx += ",";
            }
            var api = frameElement.api;
            W = api.opener;
            if("del" == type){
                W.del(hblx);
            }else if("submit" == type){
                W.submit(hblx);
            }else{
                W.cancel(hblx);
            }
            closeDialog();
        }
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjshb" method="post"  styleId="form">
    <%--<input type="hidden" id="jgids" name="jgids" value="${jgids}"/>--%>
    <div style='width:100%; height:100px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr>
                <th rowspan="2" style="text-align: left">
                    选择类型
                </th>
                <td><label><input type="checkbox" name = "hblx" value="nzhb"/>中期汇报</label></td>
            </tr>
            <tr style="">
                <td><label><input type="checkbox" name = "hblx" value="nzzj"/>年终总结</label></td>
            </tr>
            </tbody>
        </table>
    </div>
</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="bz">
                "<span class="red">*</span>"为必填项
            </div>
            <div class="btn">
                <logic:equal value="del" name="type">
                    <button type="button" type="button" onclick="save('del');">
                        删 除
                    </button>
                </logic:equal>
                <logic:equal value="submit" name="type">
                    <button type="button" type="button" onclick="save('submit');">
                        提 交
                    </button>
                </logic:equal>
                <logic:equal value="cancel" name="type">
                    <button type="button" type="button" onclick="save('add');">
                        撤 销
                    </button>
                </logic:equal>
                <button type="button" type="button" onclick="iFClose();">
                    关 闭
                </button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>

