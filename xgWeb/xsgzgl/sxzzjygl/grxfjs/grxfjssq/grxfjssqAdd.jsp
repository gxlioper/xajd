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
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjssq/js/grxfjssqEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
        #bxnmbTable input{width: 50px;}
    </style>
</head>
<body>
<html:form action="/sxzzjy_grxfjssq" method="post"  styleId="form">
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <tbody id="">
            <tr style="">
                <th colspan="4"  style="text-align: left">
                    个人学风建设
                </th>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>名 称
                </th>
                <td colspan="3">
                    <html:text property="xfjsmc" styleId="xfjsmc" style="width:60%;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>申报类型
                </th>
                <td >
                    <html:select property="sblx" styleId="sblx" style="width:150px" styleClass="select">
                        <html:option value=""></html:option>
                        <html:options collection="sblxList" property="sblxdm"
                                      labelProperty="sblxmc" />
                    </html:select>
                </td>
                <th >
                    学年学期
                </th>
                <td>
                        ${xnxq}
                    <html:hidden property="xn" styleId="xn"/>
                    <html:hidden property="xq" styleId="xq"/>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>本学年目标
                </th>
                <td   colspan="3">
                    <table id="bxnmbTable">
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
                            <td ><html:text property="njbfb" styleId="njbfb" onblur="checkLen(this,10);"/>%</td>
                            <th width="27%"><span style="color: red">*</span>所在宿舍获奖次数</th>
                            <td ><html:text property="sshjcs" styleId="sshjcs" onblur="checkLen(this,10);"/>次</td>
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
                    </table>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>建设思路和计划<br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td   colspan="3">
                    <html:textarea property="jssl" style="width:98%;margin-top:5px" rows="5"
                                   onblur="checkLen(this,500);" styleId="jssl"
                    ></html:textarea>
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
                    <input type="file" id="filepath_f" name="filepath" />
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery('#filepath_f').multiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'fjid',

                                eid : 'filepath_f'
                            });
                        });
                    </script>
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
                    <button type="button" type="button" onclick="saveForm_add('save');">
                        保存草稿
                    </button>

                    <button type="button" type="button" onclick="saveForm_add('submit');">
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

