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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjshb/js/grxfjshbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){

        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        .formlist input{
            width: 50px;
        }
        .
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjshb" method="post"  styleId="form">
    <html:hidden property="hblx" styleId="hblx"/>
    <html:hidden property="jgid" styleId="jgid" />
    <html:hidden property="sqid" styleId="sqid" />
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
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    <logic:equal name="hblx" value="nzhb">中期汇报</logic:equal>
                    <logic:equal name="hblx" value="nzzj">年度总结</logic:equal>
                </th>
            </tr>
            <tr>
                <th width="27%"><span style="color: red">*</span>平均学分积</th>
                <td width="23%"><html:text property="pjxfj" styleId="pjxfj" onblur="checkLen(this,10);"/>分</td>
                <th width="27%"><span style="color: red">*</span>平均学分积小班排名</th>
                <td width="23%"><html:text property="pjxfjpm" styleId="pjxfjpm" onblur="checkLen(this,10);"/>名</td>
            </tr>
            <tr>
                <th width="27%"><span style="color: red">*</span>综合测评成绩</th>
                <td width="23%"><html:text property="zhcpcj" styleId="zhcpcj" onblur="checkLen(this,10);"/>分</td>
                <th width="27%"><span style="color: red">*</span>综合测评成绩排名</th>
                <td width="23%"><html:text property="zhcpcjpm" styleId="zhcpcjpm" onblur="checkLen(this,10);"/>名</td>
            </tr>
            <tr>
                <th width="27%"><span style="color: red">*</span>综合测评成绩年级（专业）百分比</th>
                <td colspan="3"><html:text property="njbfb" styleId="njbfb" onblur="checkLen(this,10);"/>%</td>
            </tr>
            <tr>
                <th width="27%">
                    <span style="color: red">*</span>其他<br>
                    <span style="color: red">(限100字)</span>
                </th>
                <td colspan="3">
                    <html:textarea property="qt" style="width:98%;margin-top:5px" rows="3"
                                   onblur="checkLen(this,100);" styleId="qt"
                    ></html:textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist">
            <tbody>
            <tr style="">
                <th width="14%">
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">第一学期课程成绩</logic:equal>
                    <logic:equal name="hblx" value="nzzj">本学年课程成绩</logic:equal>
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
                    <logic:equal name="hblx" value="nzhb">中期总结</logic:equal>
                    <logic:equal name="hblx" value="nzzj">年度个人建设总结</logic:equal>
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
                    <logic:equal name="hblx" value="nzhb">个人建设存在问题分析</logic:equal>
                    <logic:equal name="hblx" value="nzzj">个人建设过程典型事迹</logic:equal>
                    <br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd3" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd3"
                    ></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>
                    <logic:equal name="hblx" value="nzhb">个人建设整改方案</logic:equal>
                    <logic:equal name="hblx" value="nzzj">个人获奖明细</logic:equal>
                    <br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="zd4" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="zd4"
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

