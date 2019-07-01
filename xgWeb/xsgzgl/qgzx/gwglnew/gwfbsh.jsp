<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
    <script type='text/javascript' src='/xgxt/dwr/util.js'></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script type='text/javascript' src="js/String.js"></script>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.gwdm}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
        });
        function shSave(){

            if(jQuery("#shyj").val() == ""){
                showAlertDivLayer("请填写审核意见！");
                return false;
            }
            var message;
            if(jQuery("#shjg").val() == "1"){
                message = "通过";
            }
            if(jQuery("#shjg").val() == "2"){
                message = "不通过";
            }
            if(jQuery("#shjg").val() == "3"){
                message = "退回";
            }
            showConfirmDivLayer("您确定" + message + "该申请吗？",{"okFun":function(){
                var url = "qgzx_gwglnew.do?method=gwfbsh&type=save";
                ajaxSubFormWithFun("qgzxGwglNewForm",url,function(data){
                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                });
            }});
        }
    </script>
</head>
<body >

<html:form styleId="qgzxGwglNewForm" action="/qgzx_gwglnew" method="post" onsubmit="return false;">
    <input type="hidden" name="gwdm" value="${model.gwdm}">
    <input type="hidden" name="yrdwid" value="${yrdwid}"/>
    <input type="hidden" name="splc" value="${model.splc}"/>
    <table align="center" class="formlist">
        <thead>
        <tr>
            <th colspan="4" style="width: 25%">
                <span>岗位基本信息</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="7%">
                学年学期
            </th>
            <td>
                    ${model.xn}${model.xqmc}
            </td>
            <th width="7%">
                用人单位
            </th>
            <td>
                ${yrdwmc}
            </td>
        </tr>
        </tbody>
        <tbody>
        <tr>
            <th>
                岗位名称
            </th>
            <td>
                    ${model.gwmc}
            </td>
            <th>工作性质</th>
            <td>
                <logic:equal name="model" property="gwxzdm" value="0"> 临时</logic:equal>
                <logic:equal name="model" property="gwxzdm" value="1">正式</logic:equal>
            </td>
        </tr>
        <tr>
            <th>
                招聘人数
            </th>
            <td>
                    ${model.xqrs}人
            </td>
            <th>
                岗位类型
            </th>
            <td>
                <logic:equal name="model" property="gwlx" value="0">临时</logic:equal>
                <logic:equal name="model" property="gwlx" value="1">长期</logic:equal>
            </td>
        </tr>
        <tr>
            <th>
                岗位类别
            </th>
            <td >
                <select name="gwlb" id="gwlb" disabled>
                    <logic:iterate id="i" name="gwlblist">
                        <option value="${i.gwxzdm}" <logic:equal name="model" property="gwlb" value="${i.gwxzdm}">selected="selected"</logic:equal>>${i.gwxzmc}</option>
                    </logic:iterate>
                </select>
            </td>
            <th>
                工资上限/月
            </th>
            <td>
                    ${gsgz.cjsx}元
            </td>
        </tr>
        <tr>
            <th>
                工时上限/月
            </th>
            <td colspan="3">
                    ${gsgz.gzscsx}小时
                <span id="label"></span>
            </td>
        </tr>
        <tr>
            <th>
                招聘开始时间
            </th>
            <td>
                    ${model.zpkssj}
            </td>
            <th>
                招聘结束时间
            </th>
            <td>
                <logic:equal name="model" property="cq" value="1">长期</logic:equal>
                <logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
            </td>
        </tr>
        <tr>
            <th>
                是否置顶
            </th>
            <td>
                <input type="radio" name="sfzd" value="0" checked="checked">否
                <input type="radio" name="sfzd" value="1">是
            </td>
            <th>
                是否显示工资
            </th>
            <td>
                <input type="radio" name="sfxsgz" value="0" checked="checked">否
                <input type="radio" name="sfxsgz" value="1">是
            </td>
        </tr>
        <tr>
            <th>
                招聘要求
            </th>
            <td colspan="3">
                    ${model.gwryyq}
            </td>
        </tr>
        </tbody>
        <thead>
        <tr>
            <th colspan="4">
                <span>审核信息</span>
            </th>
        </tr>
        </thead>
        <tr>
            <td colspan="4" id="shlccx">

            </td>
        </tr>
        <tbody>
        <tr>
            <th >
                审核结果
            </th>
            <td id="shjgSpan" colspan="3">

            </td>
        </tr>

        <tr>
            <th >
                <font color="red">*&nbsp;</font> 审核意见
                <br />
                <font color="red">(限200字)</font>
            </th>
            <td colspan="3">
                <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
                <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" id="btn_submit" type="button"
                            onclick="shSave();">
                        保存
                    </button>
                    <button type="button" name="关闭" onclick="Close();return false;">
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
