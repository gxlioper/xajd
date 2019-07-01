<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript">
        var bztr,bzth,bztd;
        var qjsyHtml,cdsjHtml;
        jQuery(function(){
            var qdzt = jQuery("#qdzt").val();
            bztr = jQuery("#bz_tr");
            bzth = jQuery("#bz_tr").find("th").eq(0);
            bztd = jQuery("#bz_tr").find("td").eq(0);
            qjsyHtml = "<textarea rows=\"4\" cols=\"\" name='qdztbz' id='qdztbz' style='width: 97%;' "  +
                " onblur=\"checkLen(this,200);return false;\" >${qdztbz}</textarea>";
            cdsjHtml = "<input name=\"qdztbz\" id=\"qdztbz\" style=\"width:120px;\" value='${qdztbz}'" +
                " onfocus=\"return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss',true,'nowTime');\" />";
            if("qj" == qdzt){
                bztr.show();
                bzth.append("<span class=\"red\">*</span>请假事由<br /><font color=\"red\">(限制在200字内)</font>");
                bztd.append(qjsyHtml)
            }else if("cd" == qdzt){
                bztr.show();
                bzth.append("<span class=\"red\">*</span>迟到时间");
                bztd.append(cdsjHtml)
            }else{
                bztr.hide();
            }

        });
        function save(){
            var url = "xlzx_yysqnew.do?method=saveQdzt";
            ajaxSubFormWithFun("form", url, function(data) {
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
        function change(){
            var qdzt = jQuery("#qdzt").val();
            bzth.empty();
            bztd.empty();
            if("qj" == qdzt){
                bztr.show();
                bzth.append("<span class=\"red\">*</span>请假事由<br /><font color=\"red\">(限制在200字内)</font>");
                bztd.append(qjsyHtml)
            }else if("cd" == qdzt){
                bztr.show();
                bzth.append("<span class=\"red\">*</span>迟到时间");
                bztd.append(cdsjHtml)
            }else{
                bztr.hide();
            }
            jQuery("#qdztbz").val("");

        }
    </script>
</head>
<body>
<html:form action="/xlzx_yysqnew" method="post" styleId="form">
    <html:hidden property="id" styleId="id"/>
    <input type="hidden" id="nowTime" name="nowTime" value="${nowTime}"/>
    <div style='width:100%;height:250px;overflow-y:auto;overflow-x:hidden'>

        <table width="100%" border="0" class="formlist">
            <tr >
                <th >
                    <span>签到状态</span>
                </th>
                <td>
                    <html:select property="qdzt" styleId="qdzt" style="width:100px;" onchange="change();">
                        <html:option value="yqd">已签到</html:option>
                        <html:option value="wqd">未签到</html:option>
                        <html:option value="qj">请假</html:option>
                        <html:option value="cd">迟到</html:option>
                    </html:select>
                </td>
            </tr>
            <tr id="bz_tr">
                <th>

                </th>
                <td>

                </td>
            </tr>
        </table>
    </div>
    <table  border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz" id="btx">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button id="buttonSave" onclick="save();return false;">
                        保 存
                    </button>
                    <button onclick="Close();return false;">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</html:form>
</body>
</html>

