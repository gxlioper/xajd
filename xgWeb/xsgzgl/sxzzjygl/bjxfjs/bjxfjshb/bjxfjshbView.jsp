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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjshb/js/bjxfjshbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var dybl = 0;
            var dyrs = "${bjmap.dyrs}";
            var bjzrs = "${bjmap.bjzrs}";
            if(dyrs > 0 && bjzrs>0)
                dybl = (dyrs*100/bjzrs).toFixed(2)+"%";
            jQuery("#dyblTD").html(dybl);
            var sfNzhb="${sfNzhb}";
            var sfNzzj="${sfNzzj}";
            if("1" == sfNzhb){
                jQuery("#shlccx_nzhb").load("comm_spl.do?method=lccx&sqid="+jQuery("#nzhbsqid").val()+"&tt="+ new Date().getTime());
            }
            if("1" == sfNzzj){
                jQuery("#shlccx_nzzj").load("comm_spl.do?method=lccx&sqid="+jQuery("#nzzjsqid").val()+"&tt="+ new Date().getTime());
            }

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjshb" method="post"  styleId="form">
    <input type="hidden" id="nzhbsqid" name="nzhbsqid" value="${nzhbMap.sqid}"/>
    <input type="hidden" id="nzzjsqid" name="nzzjsqid" value="${nzzjMap.sqid}"/>
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
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
            </tbody>
        </table>
    <logic:equal value="1" name="sfNzhb">
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    中期汇报
                </th>
            </tr>
            <tr>
                <th width="21%">班级平均学分积</th>
                <td width="5%">${nzhbMap.pjxfj}</td>
                <td colspan="2">班级学分积在年级（专业）
                        ${nzhbMap.njzy}的
                        ${nzhbMap.zyxbgs}个小班中排名第
                        ${nzhbMap.pjxfjpm}。
                </td>
            </tr>
            <tr>
                <th width="21%">班级英语四级通过率</th>
                <td width="5%">${nzhbMap.sjtgl}</td>
                <td colspan="2" >班级英语四级通过率在年级
                        ${nzhbMap.njxbgs}个小班中排名第
                        ${nzhbMap.sjtglpm}。
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
            <tr>
                <th width="27%">不及格门次</th>
                <td width="23%">${nzhbMap.bjgmc}门</td>
                <th width="27%">不及格人数</th>
                <td width="23%">${nzhbMap.bjgrs}人</td>
            </tr>
            <tr>
                <th>不及格人次</th>
                <td>${nzhbMap.bjgrc}人次</td>
                <th>班干部学习成绩前五名</th>
                <td>${nzhbMap.bgbqwrs}人</td>

            </tr>
            <tr>
                <th>班干部学习成绩前十名</th>
                <td>${nzhbMap.bgbqsrs}人</td>
                <th>获奖学生</th>
                <td>${nzhbMap.hjxsrs}人</td>
            </tr>
            <tr>
                <th>获集体奖</th>
                <td>${nzhbMap.hjtjgs}个</td>
                <th>社会实践获奖</th>
                <td>${nzhbMap.shsjhjrc}人次</td>
            </tr>
            <tr>
                <th>宿舍获奖</th>
                <td>${nzhbMap.sshjcs}次</td>
                <th>组织全班集体活动</th>
                <td>${nzhbMap.qbjthdcs}次</td>
            </tr>
            <tr>
                <th>科技学术获奖</th>
                <td>${nzhbMap.kjxshjrc}人次</td>
                <th>组织班级同学参加校院活动</th>
                <td>${nzhbMap.cjxyhdcs}次</td>
            </tr>
            <tr>
                <th>降级同学</th>
                <td>${nzhbMap.jjtxrs}名</td>
                <th>试读学生</th>
                <td>${nzhbMap.sdxsrs}名</td>
            </tr>
            <tr>
                <th>退学</th>
                <td colspan="3">${nzhbMap.txrs}人</td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    中期总结
                </th>
                <td   colspan="3">
                        ${nzhbMap.zd1}
                </td>
            </tr>
            <tr style="">
                <th>
                    班级建设存在问题分析
                </th>
                <td   colspan="3">
                        ${nzhbMap.zd2}
                </td>
            </tr>
            <tr style="">
                <th>
                    班级建设整改方案
                </th>
                <td   colspan="3">
                        ${nzhbMap.zd3}
                </td>
            </tr>
            </tbody>
        </table>
        <logic:notEqual value="无需审核" name="nzhbMap" property="shztmc" >
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
                    <td colspan="4" id="shlccx_nzhb">
                    </td>
                </tr>
                </tbody>
            </table>
        </logic:notEqual>
    </logic:equal>
    <logic:equal value="1" name="sfNzzj">
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                     年度总结
                </th>
            </tr>
            <tr>
                <th width="21%">班级平均学分积</th>
                <td width="5%">${nzzjMap.pjxfj}</td>
                <td colspan="2">班级学分积在年级（专业）
                        ${nzzjMap.njzy}的
                        ${nzzjMap.zyxbgs}个小班中排名第
                        ${nzzjMap.pjxfjpm}。
                </td>
            </tr>
            <tr>
                <th width="21%">班级英语四级通过率</th>
                <td width="5%">${nzzjMap.sjtgl}</td>
                <td colspan="2" >班级英语四级通过率在年级
                        ${nzzjMap.njxbgs}个小班中排名第
                        ${nzzjMap.sjtglpm}。
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
            <tr>
                <th width="27%">不及格门次</th>
                <td width="23%">${nzzjMap.bjgmc}门</td>
                <th width="27%">不及格人数</th>
                <td width="23%">${nzzjMap.bjgrs}人</td>
            </tr>
            <tr>
                <th>不及格人次</th>
                <td>${nzzjMap.bjgrc}人次</td>
                <th>班干部学习成绩前五名</th>
                <td>${nzzjMap.bgbqwrs}人</td>

            </tr>
            <tr>
                <th>班干部学习成绩前十名</th>
                <td>${nzzjMap.bgbqsrs}人</td>
                <th>获奖学生</th>
                <td>${nzzjMap.hjxsrs}人</td>
            </tr>
            <tr>
                <th>获集体奖</th>
                <td>${nzzjMap.hjtjgs}个</td>
                <th>社会实践获奖</th>
                <td>${nzzjMap.shsjhjrc}人次</td>
            </tr>
            <tr>
                <th>宿舍获奖</th>
                <td>${nzzjMap.sshjcs}次</td>
                <th>组织全班集体活动</th>
                <td>${nzzjMap.qbjthdcs}次</td>
            </tr>
            <tr>
                <th>科技学术获奖</th>
                <td>${nzzjMap.kjxshjrc}人次</td>
                <th>组织班级同学参加校院活动</th>
                <td>${nzzjMap.cjxyhdcs}次</td>
            </tr>
            <tr>
                <th>降级同学</th>
                <td>${nzzjMap.jjtxrs}名</td>
                <th>试读学生</th>
                <td>${nzzjMap.sdxsrs}名</td>
            </tr>
            <tr>
                <th>退学</th>
                <td colspan="3">${nzzjMap.txrs}人</td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    年度班级建设总结
                </th>
                <td   colspan="3">
                        ${nzzjMap.zd1}
                </td>
            </tr>
            <tr style="">
                <th>
                    班级建设过程典型事迹
                </th>
                <td   colspan="3">
                        ${nzzjMap.zd2}
                </td>
            </tr>
            <tr style="">
                <th>
                    班级集体和个人获奖明细
                </th>
                <td   colspan="3">
                        ${nzzjMap.zd3}
                </td>
            </tr>
            </tbody>
        </table>
        <logic:notEqual value="无需审核" name="nzhbMap" property="shztmc" >
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
                    <td colspan="4" id="shlccx_nzzj">
                    </td>
                </tr>
                </tbody>
            </table>
        </logic:notEqual>
    </logic:equal>
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

