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
        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        .formlist input{
            width: 43px;
        }
    </style>
</head>
<body>
<html:form action="/sxzzjy_bjxfjshb" method="post"  styleId="form">
    <html:hidden property="hblx" styleId="hblx"/>
    <html:hidden property="jgid" styleId="jgid" />
    <html:hidden property="sqid" styleId="sqid" />
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
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    <logic:equal name="hblx" value="nzhb">中期汇报</logic:equal>
                    <logic:equal name="hblx" value="nzzj">年度总结</logic:equal>
                </th>
            </tr>
            <tr>
                <th width="21%">班级平均学分积</th>
                <td width="5%"><html:text property="pjxfj" styleId="pjxfj" onblur="checkLen(this,10);"/></td>
                <td colspan="2">班级学分积在年级（专业）
                    <html:text property="njzy" styleId="njzy" onblur="checkLen(this,50);"/>的
                    <html:text property="zyxbgs" styleId="zyxbgs" onblur="checkLen(this,10);"/>个小班中排名第
                    <html:text property="pjxfjpm" styleId="pjxfjpm" onblur="checkLen(this,10);"/>。
                </td>
            </tr>
            <tr>
                <th width="21%">班级英语四级通过率</th>
                <td width="5%"><html:text property="sjtgl" styleId="sjtgl" onblur="checkLen(this,10);"/></td>
                <td colspan="2" >班级英语四级通过率在年级
                    <html:text property="njxbgs" styleId="njxbgs" onblur="checkLen(this,10);"/>个小班中排名第
                    <html:text property="sjtglpm" styleId="sjtglpm" onblur="checkLen(this,10);"/>。
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist bxnmb" >
            <tbody>
            <tr>
                <th width="27%">不及格门次</th>
                <td width="23%"><html:text property="bjgmc" styleId="bjgmc" onblur="checkLen(this,10);"/>门</td>
                <th width="27%">不及格人数</th>
                <td width="23%"><html:text property="bjgrs" styleId="bjgrs" onblur="checkLen(this,10);"/>人</td>
            </tr>
            <tr>
                <th>不及格人次</th>
                <td><html:text property="bjgrc" styleId="bjgrc" onblur="checkLen(this,10);"/>人次</td>
                <th>班干部学习成绩前五名</th>
                <td><html:text property="bgbqwrs" styleId="bgbqwrs" onblur="checkLen(this,10);"/>人</td>

            </tr>
            <tr>
                <th>班干部学习成绩前十名</th>
                <td><html:text property="bgbqsrs" styleId="bgbqsrs" onblur="checkLen(this,10);"/>人</td>
                <th>获奖学生</th>
                <td><html:text property="hjxsrs" styleId="hjxsrs" onblur="checkLen(this,10);"/>人</td>
            </tr>
            <tr>
                <th>获集体奖</th>
                <td><html:text property="hjtjgs" styleId="hjtjgs" onblur="checkLen(this,10);"/>个</td>
                <th>社会实践获奖</th>
                <td><html:text property="shsjhjrc" styleId="shsjhjrc" onblur="checkLen(this,10);"/>人次</td>
            </tr>
            <tr>
                <th>宿舍获奖</th>
                <td><html:text property="sshjcs" styleId="sshjcs" onblur="checkLen(this,10);"/>次</td>
                <th>组织全班集体活动</th>
                <td><html:text property="qbjthdcs" styleId="qbjthdcs" onblur="checkLen(this,10);"/>次</td>
            </tr>
            <tr>
                <th>科技学术获奖</th>
                <td><html:text property="kjxshjrc" styleId="kjxshjrc" onblur="checkLen(this,10);"/>人次</td>
                <th>组织班级同学参加校院活动</th>
                <td><html:text property="cjxyhdcs" styleId="cjxyhdcs" onblur="checkLen(this,10);"/>次</td>
            </tr>
            <tr>
                <th>降级同学</th>
                <td><html:text property="jjtxrs" styleId="jjtxrs" onblur="checkLen(this,10);"/>名</td>
                <th>试读学生</th>
                <td><html:text property="sdxsrs" styleId="sdxsrs" onblur="checkLen(this,10);"/>名</td>
            </tr>
            <tr>
                <th>退学</th>
                <td colspan="3"><html:text property="txrs" styleId="txrs"  onblur="checkLen(this,10);"/>人</td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">中期总结</logic:equal>
                    <logic:equal name="hblx" value="nzzj">年度班级建设总结</logic:equal>
                    <br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd1" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd1"
                    ></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">班级建设存在问题分析</logic:equal>
                    <logic:equal name="hblx" value="nzzj">班级建设过程典型事迹</logic:equal>
                    <br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd2" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd2"
                    ></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">班级建设整改方案</logic:equal>
                    <logic:equal name="hblx" value="nzzj">班级集体和个人获奖明细</logic:equal>
                    <br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd3" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd3"
                    ></html:textarea>
                </td>
            </tr>

            </tbody>


        </table>
    </div>
</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="bz">
                "<span class="red">*</span>"为必填项
            </div>
            <div class="btn">
                <button type="button" type="button" onclick="saveForm_update('save');">
                    保存草稿
                </button>

                <button type="button" type="button" onclick="saveForm_update('submit');">
                    提交申请
                </button>

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

