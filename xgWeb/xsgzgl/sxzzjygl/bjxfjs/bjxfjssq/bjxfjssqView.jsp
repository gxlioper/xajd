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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjssq/js/bjxfjssqEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${bjmap.dyrs}";
            var bjzrs = "${bjmap.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+ new Date().getTime());

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjssq" method="post"  styleId="form">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <html:hidden property="sqid" styleId="sqid" />
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr>
                <th colspan="4" style="text-align: left">
                    班级信息
                </th>

            </tr>

            <tr style="">
                <th width="14%">
                    班级
                </th>
                <td colspan="3">
                        ${bjmap.bjmc}
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
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    班级学风建设
                </th>
            </tr>
            <tr style="">
                <th >
                    名 称
                </th>
                <td colspan="3">
                    ${map.xfjsmc}
                </td>
            </tr>
            <tr style="">
                <th >
                    申报类型
                </th>
                <td >
                     ${map.sblxmc}
                </td>
                <th >
                    学年学期
                </th>
                <td>
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
                    <html:hidden property="fjid" styleId="fjid" />
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
        <logic:notEqual value="无需审核" name="shztmc">
            <table width="100%" border="0" class="formlist">
                <thead>
                <tr>
                    <th colspan="4">
                        <span>审核信息</span>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="4" id="shlccx">

                    </td>
                </tr>

                </tbody>

            </table>
        </logic:notEqual>
    </div>
</html:form>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
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

