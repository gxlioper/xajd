<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjssh/js/grxfjsshEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){

            var sqid = jQuery("#sqid").val();
            var splc = jQuery("#splc").val();
            var shid = jQuery("#shid").val();
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
        });
    </script>
</head>
<body>
<html:form action="/sxzzjy_grxfjssh" method="post" styleId="GrxfjsshForm">
    <div id="filepathHiddenDiv" style="display: none;">
        <div id="commonfileupload-list-0" style="padding: 5px;"></div>
    </div>
    <html:hidden property="sqid" styleId="sqid"/>
    <html:hidden property="splc" styleId="splc"/>
    <html:hidden property="shid" styleId="shid"/>
    <div style="height:460px;overflow-x:hidden;overflow-y:auto;">
        <div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >

        </div>
        <table width="100%" border="0" class="formlist">
            <thead>
            <thead>
            <tr>
                <th colspan="4">
                    <span>基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>
                        <logic:equal name="sqlx" value="sq">个人学风建设</logic:equal>
                        <logic:equal name="sqlx" value="nzhb">中期汇报</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">年终总结</logic:equal>
                    </span>
                </th>
            </tr>
            </thead>
            <tbody>
        <logic:equal name="sqlx" value="sq">
            <tr style="">
                <th width="14%">
                    名 称
                </th>
                <td colspan="3">
                        ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    申报类型
                </th>
                <td width="36%">
                        ${map.sblxmc}
                </td>
                <th width="14%">
                    学年学期
                </th>
                <td width="36%">
                        ${map.xn}${map.xqmc}
                </td>
            </tr>
            <tr style="">
                <th>
                    本学年目标
                </th>
                <td   colspan="3">
                    <table id="bxnmbTable">
                        <tr>
                            <th width="27%">平均学分积</th>
                            <td width="23%">${map.pjxfj}分</td>
                            <th width="27%">平均学分积小班排名</th>
                            <td width="23%">${map.pjxfjpm}名</td>
                        </tr>
                        <tr>
                            <th width="27%">综合测评成绩</th>
                            <td width="23%">${map.zhcpcj}分</td>
                            <th width="27%">综合测评成绩排名</th>
                            <td width="23%">${map.zhcpcjpm}名</td>
                        </tr>
                        <tr>
                            <th width="27%">综合测评成绩年级（专业）百分比</th>
                            <td >${map.njbfb}%</td>
                            <th width="27%">所在宿舍获奖次数</th>
                            <td >${map.sshjcs}次</td>
                        </tr>
                        <tr>
                            <th width="27%">
                                其他<br>
                                <span style="color: red">(限100字)</span>
                            </th>
                            <td colspan="3">
                                    ${map.qt}
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr style="">
                <th>
                    建设思路和计划<br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">${map.jssl}
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" value="${map.fjid}"/>
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            var gid = jQuery('#fjid').val();
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </td>
            </tr>
        </logic:equal>
            </tbody>
        </table>
        <logic:notEqual name="sqlx" value="sq">
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th colspan="4"  style="text-align: left">
                        年度总结
                    </th>
                </tr>
                <tr>
                    <th width="27%">平均学分积</th>
                    <td width="23%">${map.pjxfj}分</td>
                    <th width="27%">平均学分积小班排名</th>
                    <td width="23%">${map.pjxfjpm}名</td>
                </tr>
                <tr>
                    <th width="27%">综合测评成绩</th>
                    <td width="23%">${map.zhcpcj}分</td>
                    <th width="27%">综合测评成绩排名</th>
                    <td width="23%">${map.zhcpcjpm}名</td>
                </tr>
                <tr>
                    <th width="27%">综合测评成绩年级（专业）百分比</th>
                    <td colspan="3">${map.njbfb}%</td>

                </tr>
                <tr>
                    <th width="27%">
                        其他<br>
                        <span style="color: red">(限100字)</span>
                    </th>
                    <td colspan="3">
                            ${map.qt}
                    </td>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th width="14%">
                        <logic:equal name="sqlx" value="nzhb">第一学期课程成绩</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">本学年课程成绩</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd1}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <logic:equal name="sqlx" value="nzhb">中期总结</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">年度个人建设总结</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd2}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <logic:equal name="sqlx" value="nzhb">个人建设存在问题分析</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">个人建设过程典型事迹</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd3}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        <logic:equal name="sqlx" value="nzhb">个人建设整改方案</logic:equal>
                        <logic:equal name="sqlx" value="nzzj">个人获奖明细</logic:equal>
                    </th>
                    <td   colspan="3">
                            ${map.zd4}
                    </td>
                </tr>
                </tbody>
            </table>
        </logic:notEqual>

        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>审核历史</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="4" id="shlccx">

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
            <tbody>
            <tr>
                <th>
                    <font color="red">*</font>审核结果
                </th>
                <td colspan="3" id="shjgSpan">

                </td>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*&nbsp;</font> 审核意见
                    <br />
                    <font color="red">(限200字)</font>
                </th>
                <td colspan="3">
                    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ylbx&id=shyj" />
                    <textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
                    <button type="button" name="保存"  onclick="saveShzt();return false;">
                        保 存
                    </button>
                    <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
    <!-- 提示信息 -->
    <%@ include file="/comm/other/tsxxNew.jsp"%>
</html:form>
</body>
</html>
