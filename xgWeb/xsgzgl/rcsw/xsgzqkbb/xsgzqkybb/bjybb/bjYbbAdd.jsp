<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <%@ include file="/syscommon/autocomplete.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkybb/bjybb/js/bjYbb.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/rcsw_xsgzqkbb_bjybbgl" method="post" styleId="xsgzqkBjYbbForm" onsubmit="return false;">
    <html:hidden property="xyybbid" styleId="xyybbid"></html:hidden>

    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生工作情况班级月报表信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%"><span class="red">*</span>班级</th>
                <td width="30%" colspan="3">
                    <input type="text" id="bjmc" value="" style="width:200px;" readonly="readonly" title=""/>
                    <input type="hidden" name="bjdm" id="bjdm" value=""/>
                    <button class="btn_01" type="button" onclick="showDialog('请选择一个班级',800,500,'rcsw_xsgzqkbb_bjybbgl.do?method=bjManage&xyybbid=${xsgzqkBjYbbForm.xyybbid}');return false;">选择</button>
                    <span id="bj_span"></span>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>学生人数（男）
                </th>
                <td width="30%">
                    <html:text property="mxss" styleId="mxss" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
                <th width="20%">
                    <span class="red">*</span>学生人数（女）
                </th>
                <td width="30%">
                    <html:text property="wxss" styleId="wxss" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>召开班会次数
                </th>
                <td width="30%">
                    <html:text property="zkbhcs" styleId="zkbhcs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
                <th><span class="red">*</span>班级活动开展次数</th>
                <td>
                    <html:text property="bjhdkzcs" styleId="bjhdkzcs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>深入宿舍次数
                </th>
                <td width="30%">
                    <html:text property="srsscs" styleId="srsscs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
                <th><span class="red">*</span>师生谈话次数</th>
                <td>
                    <html:text property="ssthcs" styleId="ssthcs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>跟班听课次数
                </th>
                <td width="30%">
                    <html:text property="gbtkcs" styleId="gbtkcs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
                <th><span class="red">*</span>与家长联系情况</th>
                <td>
                    <html:text property="yjzlxqk" styleId="yjzlxqk" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>学生违纪及突发事件处理情况
                </th>
                <td width="30%">
                    <html:text property="tfsjclqk" styleId="tfsjclqk" maxlength="128"></html:text>
                </td>
                <th width="20%"><span class="red">*</span>休学人数</th>
                <td width="30%">
                    <html:text property="xxrs" styleId="xxrs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>复学人数
                </th>
                <td width="30%">
                    <html:text property="fxrs" styleId="fxrs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td width="20%">
                <th><span class="red">*</span>退学人数</th>
                <td width="30%">
                    <html:text property="txrs" styleId="txrs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>其他人数
                </th>
                <td width="30%">
                    <html:text property="qtrs" styleId="qtrs" maxlength="4" onkeyup="checkInputData(this)" onblur="checkInputData(this)"></html:text>
                </td>
                <th width="20%">备注</th>
                <td width="30%">
                    <html:text property="bz" styleId="bz" maxlength="64"></html:text>
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveForAdd();">
                            保存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关闭
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

