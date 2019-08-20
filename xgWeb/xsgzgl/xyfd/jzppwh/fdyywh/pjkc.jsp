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
    <script type="text/javascript">
        function saveKcpj() {
            var checkId = 'pf-xxpj';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            var len = jQuery("#xxpj").val().length;
            if(len<20){
                showAlertDivLayer("请至少输入20个字符！");
                return false;
            }
            if(len>500){
                showAlertDivLayer("最多输入500个字符！");
                return false;
            }
            var url = "xyfd_fdyy.do?method=pjkc&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
    </script>
</head>

<body style="width:100%">
<html:form action="/xyfd_fqyy" method="post" styleId="demoForm">
    <input type="hidden" name="pjid" id="pjid" value="${kcpjxx.pjid}"/>
    <input type="hidden" name="yyh" id="yyh" value="${fdyyxx.yyh}"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4"><span>评价课程</span></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">辅导人员姓名</th>
                <td width="30%">
                    ${fdyyxx.fdjsxm}
                </td>
                <th width="20%">辅导人员类型</th>
                <td width="30%">
                    ${fdyyxx.fdjslb}
                </td>
            </tr>
            <tr>
                <th width="20%"><span class="red">*</span>评分</th>
                <td width="30%" colspan="3">
                    <div style="text-align: center">
                        <div id="star"></div>
                        <div><input type="hidden" id="pf" name="pf" value="${kcpjxx.pf}"/></div>
                    </div>
                </td>
            </tr>
            <tr>
                <th width="20%"><span class="red">*</span>是否解决问题：</th>
                <td width="30%" colspan="3" >
                    <logic:notEqual name="kcpjxx" value="否" property="sfjj" >
                        <input type="radio" name="sfjj" value="是" checked="checked"/>是
                        <input type="radio" name="sfjj" value="否"/>否
                    </logic:notEqual>
                    <logic:equal name="kcpjxx" value="否" property="sfjj" >
                        <input type="radio" name="sfjj" value="是"/>是
                        <input type="radio" name="sfjj" value="否" checked="checked"/>否
                    </logic:equal>
                </td>
            </tr>
            </tbody>
            <thead>
                <tr>
                    <th colspan="4"><span class="red">*详细评价</span></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4">
                        <html:textarea name="kcpjxx" property="xxpj" styleId="xxpj" style="width: 100%;height: 250px;resize:none;"></html:textarea>
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
                        <button type="button" type="button" onclick="saveKcpj();return false;" >
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
<script type="text/javascript">

    jQuery(function() {
        var pf = jQuery('#pf').val();
        jQuery.fn.raty.defaults.path = 'xsgzgl/xyfd/img';
        jQuery('#star').raty({
            score: pf,
            half: true,
            click: function(score){
                jQuery('#pf').val(score);
            }
        });
    });
</script>