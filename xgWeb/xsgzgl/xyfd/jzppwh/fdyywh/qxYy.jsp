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
    <script type="text/javascript">
        function qxYy() {
            var checkId = 'qxyy';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var url = "xyfd_fqyy.do?method=qxYy&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }

        function changeyy() {
            var qxyy = jQuery("#qxyy").val();
            if(qxyy=='5'){
                jQuery("#qtqk_tj").show();
            }else {
                jQuery("#qtqk_tj").hide();
            }
        }
    </script>
</head>

<body style="width:100%">
<html:form action="/xyfd_fqyy" method="post" styleId="demoForm">
    <input type="hidden" name="yyid" id="yyid" value="${yyid}"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
                <tr>
                    <th colspan="2"><span>取消原因</span></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th colspan="1" style="width: 20%;text-align: center;" ><span class="red">*</span>取消原因</th>
                    <td colspan="1">
                        <html:select property="qxyy" styleId="qxyy" onchange="changeyy()">
                            <html:options collection="qxyyList" property="qxyydm"
                                          labelProperty="qxyymc" />
                        </html:select>
                    </td>
                </tr>
                <tr id="qtqk_tj" style="display: none">
                    <td colspan="2">
                        <textarea name='qtqk' id='qtqk' style='width: 100%;height: 200px;resize:none;'></textarea>
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
                    <div class="bz">"<span class="red">*</span>"为必填项</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="qxYy();return false;" >
                            确定
                        </button>
                        <button type="button" name="关 闭" onclick="iFClose();">
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
