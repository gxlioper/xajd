<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src='/xgxt/js/check.js'></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/xlzx/xlwjga/zdgzxs/js/zdgzxs.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<html:form method="post" styleId="form" action="/xlzx_zdgzxs">
    <div style='width: 100%; height: 450px; overflow-x: hidden; overflow-y: auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>咨询情况</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th align="right">
                    咨询师
                </th>
                <td >
                    ${zxsMap.xm}
                    <html:hidden property="zxs" styleId="zxs" value="${zxsMap.zgh}"/>
                </td>
                <th align="right">
                    联系电话
                </th>
                <td>
                        ${zxsMap.lxdh}
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>目前咨询次数
                </th>
                <td >
                    <html:text property="zxcs" styleId="zxcs"/>
                </td>
                <th align="right">
                    <font color="red">*</font>是否征得学生同意
                </th>
                <td >
                    <label><html:radio property="sfxsty" value="1">是</html:radio></label>
                    <label><html:radio property="sfxsty" value="0">否</html:radio></label>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>问题类别
                </th>
                <td colspan="3">
                    <html:select property="wtlb" styleId="wtlb" style="width:400px">
                        <html:option value=""/>
                        <html:option value="A">A 类：有即刻的自杀（伤害）风险，需24小时监护</html:option>
                        <html:option value="B">B 类 ：有自杀（伤害）倾向，需经常了解评估</html:option>
                        <html:option value="C">C 类 ：有精神疾病倾向，请辅导员协助医疗</html:option>
                        <html:option value="D">D 类 ：无自杀（伤害）及精神病倾向，请辅导员协助解决问题</html:option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>问题描述
                    <br/>
                    <font color="red"><B>(限1000字)</B>
                    </font>
                </th>
                <td align="left" colspan="3">
                    <html:textarea property='wtms' styleId="wtms" style="width:95%" rows='5'
                                   onblur="checkLen(this,1000)"/>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <font color="red">*</font>处理建议
                    <br/>
                    <font color="red"><B>(限1000字)</B>
                    </font>
                </th>
                <td align="left" colspan="3">
                    <html:textarea property='cljy' styleId="cljy" style="width:95%" rows='5'
                                   onblur="checkLen(this,1000)"/>
                </td>
            </tr>
                <%--<tr>
                    <th>
                        <font color="red">*</font>附件
                    </th>
                    <td colspan="3">
                        <html:hidden property="filepath2" styleId="filepath2"/>
                        <input type="file" id="filepath_f2" name="filepath2" />
                        <script type="text/javascript">
                            //调用附件
                            jQuery(function(){
                                jQuery('#filepath_f2').multiUploader({
                                    maxcount : 1,
                                    //后缀
                                    accept : 'png|gif|jpg|zip|rar|doc|docx',
                                    //最大文件大小 单位M
                                    maxsize: 10,
                                    //存放附件的隐藏域的id
                                    elementid : 'filepath2',
                                    eid : 'filepath_f2'
                                });
                            });
                        </script>
                    </td>
                </tr>--%>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td>
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button id="buttonSave" type="button" onclick="save('add');return false;">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();return false;">
                            关 闭
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