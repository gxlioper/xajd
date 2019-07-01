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
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdsb/js/hdsbEdit.js"></script>

    <script type="text/javascript">

    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdsb"  method="post"  styleId="form">
    <html:hidden property="dzbid" styleId="dzbid"  value="${map.dzbid}"/>
    <html:hidden property="type" styleId="type"  value="${type}"/>
    <html:hidden property="hdid" styleId="hdid"  value="${model.hdid}"/>
    <div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="16%">
                    学年
                </th>
                <td width="34%">
                        ${xn}
                </td>
                <th width="16%">
                    支部
                </th>
                <td width="34%">
                      ${map.dzbmc}
                </td>
            </tr>
            <tr style="">
                <th >
                    学期
                </th>
                <td >
                    ${xqmc}
                </td>
                <th >
                    所属学院
                </th>
                <td >
                    ${map.xymc}
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> 时间
                </th>
                <td >
                    <html:text property="hdsj" styleId="hdsj" style="width:150px;" onfocus="showCalendar('hdsj','yyyy-MM-dd HH:mm:ss');" />
                </td>
                <th >
                    <span style="color: red">*</span> 地点
                </th>
                <td >
                    <html:text property="hddd" styleId="hddd" style="width:150px;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> 应到人数
                </th>
                <td >
                    <html:text property="ydrs" styleId="ydrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
                <th >
                    <span style="color: red">*</span> 实到人数
                </th>
                <td >
                    <html:text property="sdrs" styleId="sdrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> 请假人数
                </th>
                <td >
                    <html:text property="qjrs" styleId="qjrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
                <th >
                    <span style="color: red">*</span> 无故不到人数
                </th>
                <td >
                    <html:text property="wgbdrs" styleId="wgbdrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> 三会一课/党日活动
                </th>
                <td >
                    <html:select  property="shykdrhd" style="width:150px" styleId="shykdrhd">
                        <html:option value=""></html:option>
                        <html:options collection="drhdList" property="dm"
                                      labelProperty="mc" />
                    </html:select>
                </td>
                <th >
                    <span style="color: red">*</span> 活动类型
                </th>
                <td >
                    <html:select property="hdlx" style="width:150px" styleId="hdlx">
                        <html:option value=""></html:option>
                        <html:options collection="hdlxList" property="dm"
                                      labelProperty="mc" />
                    </html:select>
                </td>
            </tr>

            <tr style="">
                <logic:equal value="xssb" name="type">
                    <th >
                        活动主题
                    </th>
                    <td >
                        ${map.hdzt}
                        <html:hidden property="hdzt" styleId="hdzt" style="width:150px;"/>
                    </td>
                </logic:equal>
                <logic:notEqual value="xssb" name="type">
                    <th >
                        <span style="color: red">*</span> 活动主题
                    </th>
                    <td >
                        <html:text property="hdzt" styleId="hdzt" style="width:150px;"/>
                    </td>
                </logic:notEqual>
                <th >
                    <span style="color: red">*</span> 时长
                </th>
                <td >
                    <html:text property="hdsc" styleId="hdsc" style="width:150px;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> 提交思想报告人员数
                </th>
                <td   colspan="3">
                    <html:text property="tjsxbgrs" styleId="tjsxbgrs" style="width:150px;" onblur="checkInt(this);"/>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>会议记录<br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td    colspan="3">
                    <textarea rows="3" cols="3" id="hyjl" name="hyjl" onblur="checkLen(this,500);" maxlength="500"></textarea>
                </td>
            </tr>

            <tr>
                <th>
                    附件
                </th>
                <td  colspan="3">
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
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button id="buttonSave" onclick="save('add');return false;">
                        保 存
                    </button>
                    <button onclick="Close();return false;">
                        关 闭
                    </button>

                </div>
            </td>
        </tr>
        </tfoot>
    </table>

</html:form>
</body>
</html>

