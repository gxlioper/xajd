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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjssh/js/bjxfjsshEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${bjmap.dyrs}";
            var bjzrs = "${bjmap.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);

            var sqid = jQuery("#sqid").val();
            var splc = jQuery("#splc").val();
            var shid = jQuery("#shid").val();
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
        });
    </script>
</head>
<body>
<html:form action="/sxzzjy_bjxfjssh" method="post" styleId="BjxfjsshForm">
    <div id="filepathHiddenDiv" style="display: none;">
        <div id="commonfileupload-list-0" style="padding: 5px;"></div>
    </div>
    <html:hidden property="sqid" styleId="sqid"/>
    <html:hidden property="splc" styleId="splc"/>
    <html:hidden property="shid" styleId="shid"/>
    <html:hidden property="sqlx" styleId="sqlx"/>
    <div style="height:460px;overflow-x:hidden;overflow-y:auto;">
        <div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" >

        </div>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>班级信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr style="">
                <th width="14%">
                     班级
                </th>
                <td colspan="3" >
                        ${bjmap.bjmc}
                    <input type="hidden" id="bjmc" name="bjmc" value="${bjmap.bjmc}"/>
                    <html:hidden property="bjdm" styleId="bjdm" />
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    学院
                </th>
                <td width="36%" id="xyTd">
                        ${bjmap.xymc}
                </td>
                <th width="14%">
                    班级人数
                </th>
                <td width="36%" id="bjrsTd">
                    共${bjmap.bjzrs}人（男${bjmap.nansrs}人，女${bjmap.nvsrs}人）

                </td>
            </tr>
            <tr>
                <th >
                    党员数
                </th>
                <td  id="dysTD">
                        ${bjmap.dyrs}
                </td>
                <th >
                    党员比例
                </th>
                <td  id="dyblTD">

                </td>

            </tr>
            <tr>
                <th >
                    辅导员
                </th>
                <td  id="fdyTD">
                        ${bjmap.fdyxm}
                </td>
                <th >
                    班主任
                </th>
                <td  id="bzrTD">
                        ${bjmap.bzrxm}
                </td>

            </tr>
            <tr>
                <th >
                    班长
                </th>
                <td  id="bzTD">
                        ${bjmap.bzxm}
                </td>
                <th >
                    团支书
                </th>
                <td  id="tzsTD">
                        ${bjmap.tzsxm}
                </td>

            </tr>
            </tbody>
        </table>
    <logic:equal name="sqlx" value="sq">
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>班级学风建设</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr width="14%">
                <th >
                    名 称
                </th>
                <td colspan="3">
                        ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span>申报类型
                </th>
                <td width="36%">
                        ${map.sblxmc}
                            <html:hidden property="sblx" styleId="sblx" value="${map.sblx}"/>
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
                            <th>班级平均学分积</th>
                            <td width="20%">${map.pjxfj}</td>
                            <td colspan="2">班级学分积在年级（专业）
                                    ${map.njzy}的
                                    ${map.zyxbgs}个小班中排名第
                                    ${map.pjxfjpm}。
                            </td>
                        </tr>
                        <tr>
                            <th>班级英语四级通过率</th>
                            <td>${map.sjtgl}</td>
                            <td colspan="2">班级英语四级通过率在年级
                                    ${map.njxbgs}个小班中排名第
                                    ${map.sjtglpm}。
                            </td>
                        </tr>
                        <tr>
                            <th>不及格门次</th>
                            <td>${map.bjgmc}门</td>
                            <th>不及格人数</th>
                            <td>${map.bjgrs}人</td>
                        </tr>
                        <tr>
                            <th>不及格人次</th>
                            <td>${map.bjgrc}人次</td>
                            <th>班干部学习成绩前五名</th>
                            <td>${map.bgbqwrs}人</td>

                        </tr>
                        <tr>
                            <th>班干部学习成绩前十名</th>
                            <td>${map.bgbqsrs}人</td>
                            <th>获奖学生</th>
                            <td>${map.hjxsrs}人</td>
                        </tr>
                        <tr>
                            <th>获集体奖</th>
                            <td>${map.hjtjgs}个</td>
                            <th>社会实践获奖</th>
                            <td>${map.shsjhjrc}人次</td>
                        </tr>
                        <tr>
                            <th>宿舍获奖</th>
                            <td>${map.sshjcs}次</td>
                            <th>组织全班集体活动</th>
                            <td>${map.qbjthdcs}次</td>
                        </tr>
                        <tr>
                            <th>科技学术获奖</th>
                            <td>${map.kjxshjrc}人次</td>
                            <th>组织班级同学参加校院活动</th>
                            <td>${map.cjxyhdcs}次</td>
                        </tr>
                        <tr>
                            <th>降级同学</th>
                            <td>${map.jjtxrs}名</td>
                            <th>试读学生</th>
                            <td>${map.sdxsrs}名</td>
                        </tr>
                        <tr>
                            <th>退学</th>
                            <td colspan="3">${map.txrs}人</td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr style="">
                <th>
                    建设思路和计划

                </th>
                <td   colspan="3">
                        ${map.jssl}
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
            </tbody>
        </table>
    </logic:equal>
    <logic:notEqual name="sqlx" value="sq">
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    <logic:equal name="sqlx" value="nzhb">中期汇报</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">年度总结</logic:equal>

                </th>
            </tr>
            <tr>
                <th width="21%">班级平均学分积</th>
                <td width="5%">${map.pjxfj}</td>
                <td colspan="2">班级学分积在年级（专业）
                        ${map.njzy}的
                        ${map.zyxbgs}个小班中排名第
                        ${map.pjxfjpm}。
                </td>
            </tr>
            <tr>
                <th width="21%">班级英语四级通过率</th>
                <td width="5%">${map.sjtgl}</td>
                <td colspan="2" >班级英语四级通过率在年级
                        ${map.njxbgs}个小班中排名第
                        ${map.sjtglpm}。
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
            <tr>
                <th width="27%">不及格门次</th>
                <td width="23%">${map.bjgmc}门</td>
                <th width="27%">不及格人数</th>
                <td width="23%">${map.bjgrs}人</td>
            </tr>
            <tr>
                <th>不及格人次</th>
                <td>${map.bjgrc}人次</td>
                <th>班干部学习成绩前五名</th>
                <td>${map.bgbqwrs}人</td>

            </tr>
            <tr>
                <th>班干部学习成绩前十名</th>
                <td>${map.bgbqsrs}人</td>
                <th>获奖学生</th>
                <td>${map.hjxsrs}人</td>
            </tr>
            <tr>
                <th>获集体奖</th>
                <td>${map.hjtjgs}个</td>
                <th>社会实践获奖</th>
                <td>${map.shsjhjrc}人次</td>
            </tr>
            <tr>
                <th>宿舍获奖</th>
                <td>${map.sshjcs}次</td>
                <th>组织全班集体活动</th>
                <td>${map.qbjthdcs}次</td>
            </tr>
            <tr>
                <th>科技学术获奖</th>
                <td>${map.kjxshjrc}人次</td>
                <th>组织班级同学参加校院活动</th>
                <td>${map.cjxyhdcs}次</td>
            </tr>
            <tr>
                <th>降级同学</th>
                <td>${map.jjtxrs}名</td>
                <th>试读学生</th>
                <td>${map.sdxsrs}名</td>
            </tr>
            <tr>
                <th>退学</th>
                <td colspan="3">${map.txrs}人</td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    <logic:equal name="sqlx" value="nzhb">中期总结</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">年度班级建设总结</logic:equal>

                </th>
                <td   colspan="3">
                        ${map.zd1}
                </td>
            </tr>
            <tr style="">
                <th>
                    <logic:equal name="sqlx" value="nzhb">班级建设存在问题分析</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">班级建设过程典型事迹</logic:equal>

                </th>
                <td   colspan="3">
                        ${map.zd2}
                </td>
            </tr>
            <tr style="">
                <th>
                    <logic:equal name="sqlx" value="nzhb">班级建设整改方案</logic:equal>
                    <logic:equal name="sqlx" value="nzzj">班级集体和个人获奖明细</logic:equal>

                </th>
                <td   colspan="3">
                        ${map.zd3}
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
