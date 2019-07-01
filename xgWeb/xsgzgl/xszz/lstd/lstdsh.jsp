<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
            //加载下拉选项
            loadViewJtcySelectOptions();
        })
    </script>
</head>
<body>
<html:form action="/xszz_lstd" method="post" styleId="lstdForm" onsubmit="return false">
    <html:hidden name="model" property="sqid" styleId="sqid"/>
    <html:hidden name="model" property="xh" styleId="xh"/>
    <html:hidden name="model" property="shlc" styleId="shlc"/>
    <div style='tab;width:100%;height:650px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>接受过何种资助极资助金额</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th width="25%">是否申请生源地助学贷款</th>
                    <td width="10%">
                        <logic:equal value="01" name="data" property="hjfs">
                            是
                        </logic:equal>
                        <logic:notEqual value="01" name="data" property="hjfs">
                            否
                        </logic:notEqual>
                    </td>
                    <th width="20%">申请金额</th>
                    <td width="25%">${data.dkje}（单位：元）</td>
                </tr>
                <tr><%--todo 数据从哪来--%>
                    <th>其他资助</th>
                    <td colspan="3"></td>
                </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>家庭情况</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th>家庭户口</th>
                <td>${jtqkModel.jthk}</td>
                <th>家庭人口总数</th>
                <td>${jtqkModel.jtrs}</td>
            </tr>
            <tr>
                <th>家庭人均月收入</th>
                <td>${jtqkModel.jtrjysr}（单位：元）</td>
                <th>家庭经济来源</th>
                <td>${jtqkModel.jtsrly}</td>
            </tr>
            <tr>
                <th>家庭联系电话</th>
                <td>${jtqkModel.jtdh}</td>
                <th>家庭地址</th>
                <td>${jtqkModel.jtdz}</td>
            </tr>
            <tr>
                <th>邮政编码</th>
                <td colspan="3">${jtqkModel.jtyb}</td>
            </tr>
            <thead>
            <thead>
            <tr>
                <th colspan="4">
                    <span>家庭成员</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/xszz/bdpz/jtcyView.jsp" %>
            </thead>
            <tr>
            </tr>
            <thead>
            <tr>
                <th colspan="4">
                    <span>申请缓交理由</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th>简述申请缓缴理由</th>
                <td colspan="3">
                    ${data.sqly}
                </td>
            </tr>
            <tr>
                <th>详细说明将来采取何种方式将解决欠费问题</th>
                <td colspan="3">
                    <input type="checkbox" disabled <logic:equal value="01" name="data" property="hjfs">checked="checked"</logic:equal>/>生源地国家助学贷款
                    <input type="checkbox" disabled <logic:equal value="02" name="data" property="hjfs">checked="checked"</logic:equal>/>校园地国家助学贷款(贷款金额:8000元)
                    <input type="checkbox" disabled <logic:equal value="03" name="data" property="hjfs">checked="checked"</logic:equal>/>勤工助学
                    <input type="checkbox" disabled <logic:equal value="04" name="data" property="hjfs">checked="checked"</logic:equal>/>申请助学金
                    <input type="checkbox" disabled <logic:equal value="05" name="data" property="hjfs">checked="checked"</logic:equal>/>家庭帮助解决
                    <input type="checkbox" disabled <logic:equal value="06" name="data" property="hjfs">checked="checked"</logic:equal>/>其他__________________
                </td>
            </tr>
            <thead>
            <tr>
                <th colspan="4">
                    <span>申请缓交金额及时间</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th>交通费用金额</th>
                <td>${data.jtfyje}（单位：元）</td>
                <th>缓交金额</th>
                <td>${data.sqhjje}（单位：元）</td>
            </tr>
            <tr>
                <th>缓交截止时间</th>
                <td colspan="3">${jssj}</td>
            </tr>
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
            <tr>
                <th><span class="red">*</span>审核</th>
                <td colspan="3">
                    <select name="shzt" id="shzt">
                        <option value="1">通过</option>
                        <option value="2">不通过</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>审核意见<br/><font color="red">(限200字)</font></th>
                <td colspan="3">
                    <html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
                                   onblur="checkLen(this,200);"  styleId="shyj"
                    ></html:textarea>
                </td>
            </tr>
        </table>
    </div>
    <div style="height:35px;" ></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button type="button" id="btn_submit" type="button"
                            onclick="lstdshSave();">
                        保存
                    </button>
                    <button type="button" name="关 闭" onclick="iFClose();">
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

