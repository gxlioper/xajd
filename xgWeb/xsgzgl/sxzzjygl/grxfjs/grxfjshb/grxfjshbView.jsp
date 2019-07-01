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
<html:form action="/sxzzjy_grxfjshb" method="post"  styleId="form">
    <input type="hidden" id="nzhbsqid" name="nzhbsqid" value="${nzhbMap.sqid}"/>
    <input type="hidden" id="nzzjsqid" name="nzzjsqid" value="${nzzjMap.sqid}"/>
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
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
                    <th width="27%">平均学分积</th>
                    <td width="23%">${nzhbMap.pjxfj}分</td>
                    <th width="27%">平均学分积小班排名</th>
                    <td width="23%">${nzhbMap.pjxfjpm}名</td>
                </tr>
                <tr>
                    <th width="27%">综合测评成绩</th>
                    <td width="23%">${nzhbMap.zhcpcj}分</td>
                    <th width="27%">综合测评成绩排名</th>
                    <td width="23%">${nzhbMap.zhcpcjpm}名</td>
                </tr>
                <tr>
                    <th width="27%">综合测评成绩年级（专业）百分比</th>
                    <td colspan="3">${nzhbMap.njbfb}%</td>
                </tr>
                <tr>
                    <th width="27%">
                        其他<br>
                        <span style="color: red">(限100字)</span>
                    </th>
                    <td colspan="3">
                            ${nzhbMap.qt}
                    </td>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th width="14%">
                        第一学期课程成绩
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd1}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        中期总结
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd2}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        个人建设存在问题分析
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd3}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        个人建设整改方案
                    </th>
                    <td   colspan="3">
                            ${nzhbMap.zd4}
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
                    <th width="27%">平均学分积</th>
                    <td width="23%">${nzzjMap.pjxfj}分</td>
                    <th width="27%">平均学分积小班排名</th>
                    <td width="23%">${nzzjMap.pjxfjpm}名</td>
                </tr>
                <tr>
                    <th width="27%">综合测评成绩</th>
                    <td width="23%">${nzzjMap.zhcpcj}分</td>
                    <th width="27%">综合测评成绩排名</th>
                    <td width="23%">${nzzjMap.zhcpcjpm}名</td>
                </tr>
                <tr>
                    <th width="27%">综合测评成绩年级（专业）百分比</th>
                    <td colspan="3">${nzzjMap.njbfb}%</td>
                </tr>
                <tr>
                    <th width="27%">
                        其他<br>
                        <span style="color: red">(限100字)</span>
                    </th>
                    <td colspan="3">
                            ${nzzjMap.qt}
                    </td>
                </tr>
                </tbody>
            </table>
            <table width="100%" border="0" class="formlist">
                <tbody>
                <tr style="">
                    <th width="14%">
                        本学年课程成绩
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd1}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        年度个人建设总结
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd2}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        个人建设过程典型事迹
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd3}
                    </td>
                </tr>
                <tr style="">
                    <th>
                        个人获奖明细
                    </th>
                    <td   colspan="3">
                            ${nzzjMap.zd4}
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

