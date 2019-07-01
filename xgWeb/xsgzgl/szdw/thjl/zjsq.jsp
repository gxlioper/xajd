<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script language="javascript" src="xsgzgl/szdw/thjl/js/thjlDetail.js"> </script>
    <script type="text/javascript">
        var editor;
        jQuery(function(){
            //简单视图
            var simpleOption = {
                name:'simple',
                resizeType : 1,
                themeType : 'simple',//样式风格
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons','link'
                ],
                newlineTag:'br',
                afterBlur:function(){this.sync();}
            };
            editor = KindEditor.create('textarea[name="thnr"]',simpleOption);
        })
        function changeThlx(value){
            jQuery.post("szdw_thlx.do?method=getKhwtListByThlx",{ssthlx:value},function(data){
                if(data["wttg"] != null){
                    editor.html(data["wttg"]);
                } else {
                    editor.html("");
                }
            },'json');
        }
        function submit1() {
            if(jQuery("#thsj").val()=="" || jQuery("#kssj").val()=="" || jQuery("#jssj").val()=="" || jQuery("#thlx").val()==""){
                return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
            }
            var url = "szdw_thjl.do?method=zjsqBc&doType="+jQuery("#doType").val();
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body onload="init();">
<input type="hidden" name="khhwt_inp" id="khhwt_inp" value="${thjlInfo.khhwt}" />
<input type="hidden" name="wtms_inp" id="wtms_inp" value="${thjlInfo.wtms}" />
<html:form action="/szdw_thjl" method="post" styleId="demoForm">
    <input type="hidden" name="zgh" id="zgh" value="${zgh}" />
    <input type="hidden" name="sqid" id="sqid" value="${thjlInfo.id}" />
    <input type="hidden" name="knlxdm" id="knlxdm" value="${thjlInfo.knlxdm}" />
    <input type="hidden" name="doType" id="doType" value="${doType}" />
    <input type="hidden" name="path" id="path" value="${path}" />
    <input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
    <input type="hidden" id = "sfzdgzmc" value = "${thjlInfo.sfzdgzmc }"/>
    <input type="hidden" id = "sfsdktmc" value = "${thjlInfo.sfsdktmc }"/>
    <logic:equal name="doType" value="view">
        <input type="hidden" value="${xh}" id="xh">
    </logic:equal>
    <logic:equal name="doType" value="update">
        <input type="hidden" value="${xh}" id="xh">
    </logic:equal>
    <div style="tab;overflow-x:hidden;overflow-y:auto;height:450px;margin-bottom: 0px;" >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
            <logic:equal name="doType" value="view">
                <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
            </logic:equal>
            <logic:notEqual name="doType" value="view">
                <logic:equal name="doType" value="update">
                    <%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
                </logic:equal>
                <logic:notEqual name="doType" value="update">
                    <%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
                </logic:notEqual>
            </logic:notEqual>
            <thead>
                <tr>
                    <th colspan="4">
                        <span>不及格成绩</span>
                    </th>
                </tr>
            </thead>
            <tbody id="bjgcj">
            </tbody>
            <thead>
            <tr >
                <th colspan="4">
                    <span>学籍异动信息</span>
                </th>
            </tr>
            </thead>
            <tbody id="xjydInfo">
            </tbody>
            <thead>
            <tr >
                <th colspan="4">
                    <span>谈话教师信息</span>
                </th>
            </tr>
            </thead>
            <tbody id="thjlInfo">
            <tr style="height:10px">
                <th  width="16%">
                    谈话教师
                </th>
                <td  width="34%">
                        ${thjlInfo.jsxm}
                </td>
                <th width="16%">
                    职工号
                </th>
                <td width="34%">
                        ${thjlInfo.zgh}
                </td>
            </tr>
            <tr>
                <th width="16%">
                    性别
                </th>
                <td width="34%">
                        ${thjlInfo.jsxb}
                </td>
                <th width="16%">
                    部门
                </th>
                <td width="34%">
                        ${thjlInfo.jsbmmc}
                </td>
            </tr>
            <thead>
            <tr >
                <th colspan="4">
                    <span>谈话记录信息</span>
                </th>
            </tr>
            </thead>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>谈话日期
                </th>
                <td width="34%" colspan="3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.thsj }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:text property="thsj" styleId="thsj"  value="${thjlInfo.thsj }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>谈话时段
                </th>
                <td width="34%">
                    <logic:equal name="doType" value="view">
                        <logic:notEqual  name="thjlInfo" property="kssj" value="">
                            ${thjlInfo.kssj }&nbsp;至&nbsp;${thjlInfo.jssj }
                        </logic:notEqual>
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:text property="kssj" styleId="kssj" style="width:21%" value="${thjlInfo.kssj }" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'jssj\\')}'})" readonly="true" onchange="changeThsc();"/>&nbsp;至
                        <html:text property="jssj" styleId="jssj" style="width:21%" value="${thjlInfo.jssj }" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'kssj\\')}'})" readonly="true" onchange="changeThsc();"/>
                    </logic:notEqual>
                </td>
                <th width="16%">
                    谈话时长
                    <input type="hidden" name="thsc" id="thsc" value="${thjlInfo.thsc}">
                </th>
                <td width="34%" id="thscTd">
                    <logic:equal name="doType" value="view">
                        <logic:notEqual  name="thjlInfo" property="thsc" value="">
                            ${thjlInfo.thsc }分
                        </logic:notEqual>
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view">
                        <span class="red">*</span>
                    </logic:notEqual>谈话类型
                </th>
                <td width="34%" colspan="3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.thlxmc }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:select property="thlx" styleId="thlx"  style="width: 200px" value="${thjlInfo.thlx}" onchange="changeThlx(this.value);">
                            <html:option value=""></html:option>
                            <html:options collection="thlxList" property="lxdm"
                                          labelProperty="lxmc" />
                        </html:select>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    谈话记录<br>
                    <logic:notEqual name="doType" value="view"><font color="red"><B>(限2000字)</B></font></logic:notEqual>
                </th>
                <td width="34%" colspan="3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.thnr }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:textarea property="thnr" styleId="thnr"  value="${thjlInfo.thnr }" style="word-break:break-all;width:99%" onblur="chLeng(this,1000);" rows='6'/>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">上传附件</th>
                <td width="34%" colspan = "3">
                    <logic:notEqual value="view" name="doType">
                        <html:hidden property="filepath" styleId="filepath" value="${thjlInfo.filepath}"/>
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
                                    elementid : 'filepath',
                                    label : '谈话模板电子版或照片',
                                    eid : 'filepath_f'
                                });
                            });
                        </script>
                    </logic:notEqual>
                    <logic:equal value="view" name="doType">
                        <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                        <html:hidden property="filepath" styleId="filepath" value="${thjlInfo.filepath}"/>
                        <script type="text/javascript">
                            //调用附件
                            jQuery(function(){
                                var gid = jQuery('#filepath').val();
                                jQuery.MultiUploader_q({
                                    gid : gid
                                });
                            });
                        </script>
                    </logic:equal>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>是否重点关注
                </th>
                <td width="34%" colspan = "3">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.sfzdgzmc }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <logic:present name="isnotList">
                            <logic:iterate id="o" name="isnotList">
                                <label>
                                    <html:radio property="sfzdgz" name="thjlInfo" value="${o.dm}">${o.mc}</html:radio>
                                </label>
                            </logic:iterate>
                        </logic:present>
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="16%">
                    <logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>是否需要深度恳谈
                </th>
                <td width="34%" onclick="initsdktsj();">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.sfsdktmc }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <logic:iterate id="o" name="isnotList">
                            <label>
                                <html:radio property="sfsdkt" name="thjlInfo" value="${o.dm}">${o.mc}</html:radio>
                            </label>
                        </logic:iterate>
                    </logic:notEqual>
                </td>
                <th width="16%" id="sdktsjTh" style="display: none">
                    何时组织深度恳谈
                </th>
                <td width="34%" id="sdktsjTd" style="display: none">
                    <logic:equal name="doType" value="view">
                        ${thjlInfo.sdktsj }
                    </logic:equal>
                    <logic:notEqual name="doType" value="view">
                        <html:text property="sdktsj" styleId="sdktsj"  value="${thjlInfo.sdktsj }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
                    </logic:notEqual>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div>
        <table class="formlist">
            <tfoot>
            <tr>
                <td colspan="4">
                    <logic:notEqual name="doType" value="view"><div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div></logic:notEqual>
                    <div class="btn">
                        <button type="button" onclick="submit1();return false;">
                            提交
                        </button>
                        <button onclick="Close();return false;">
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

