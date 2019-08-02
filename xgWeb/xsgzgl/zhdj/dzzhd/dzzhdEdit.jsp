<!--党组织活动发布-->
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="comm/editor/kindeditor.js"></script>
    <script language="javascript" src="comm/editor/zh_CN.js"></script>
    <script language="javascript" src="comm/editor/editor.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/dzzhd/js/dzzhdEdit.js"></script>
</head>
<body style="width:100%">
<html:form action="/zhdj_dzzhd" method="post" styleId="DzzhdForm">
    <table width="100%" border="0" class="formlist">
        <input type="hidden" value="${data.id}" name="id" id="id">
        <input type="hidden" value="${status}" id="status">
        <tbody id="tbody_jbxx">
        <tr>
            <th width="20%">
                <logic:notEqual value="view" name="status">
                    <span style="color:red">*</span>
                </logic:notEqual>
                活动名称
            </th>
            <td width="80%" colspan="3">
                <html:text property="hdmc" styleId="hdmc" style="width: 569px;" onblur="checkLen(this,100);"/>
            </td>
        </tr>
        <tr>
            <th width="20%">
                <logic:notEqual value="view" name="status">
                    <span style="color:red">*</span>
                </logic:notEqual>
                开始时间
            </th>
            <td width="30%"><html:text styleId="kssj" property="kssj"
                  onclick="return showCalendar('kssj','yyyy-MM-dd HH:mm',true,'jssj');"  readonly="true"></html:text>
            </td>
            <th width="20%">
                <logic:notEqual value="view" name="status">
                    <span style="color:red">*</span>
                </logic:notEqual>
                结束时间
            </th>
            <td width="30%"><html:text styleId="jssj" property="jssj"
                 onclick="return showCalendar('jssj','yyyy-MM-dd HH:mm',false,'kssj');"  readonly="true"></html:text>
            </td>
        </tr>
        <tr>
            <th width="20%">
                面向对象
            </th>
            <td width="80%" colspan="3">
                <logic:notEqual value="view" name="status">
                    <logic:iterate id="t" name="mxdxList" indexId="index">
                                <span style="display: inline-table">
                                    <span>
                                        <input type="checkbox" name="mxdxs" value="${t.zzmmdm}"/>${t.zzmmmc}
                                        <%if((index+1)%3==0){%> </br> <%}%>
                                    </span>
                                </span>
                    </logic:iterate>
                </logic:notEqual>
                <logic:equal value="view" name="status">
                    ${data.mxdxmc}
                </logic:equal>
            </td>
        </tr>
        <tr>
            <th width="20%">
                <logic:notEqual value="view" name="status">
                    <span style="color:red">*</span>
                </logic:notEqual>
                活动内容
            </th>
            <td width="80%" colspan="3">
                <logic:notEqual value="view" name="status">
                    <textarea rows="6" name="editorid" id="editorid" style="height:100%;"></textarea>
                </logic:notEqual>
                <logic:equal value="view" name="status">
                    <p>${data.hdnr}</p>
                </logic:equal>
            </td>
        </tr>
        <tr>
            <th width="20%">
                附件信息
            </th>
            <td width="80%" colspan="3">
                <input type="hidden" id="fj" name="fj" value="${data.fj}"/>
                <logic:notEqual value="view" name="status">
                    <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery.MultiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'fj'
                            });
                        });
                    </script>
                </logic:notEqual>
                <logic:equal value="view" name="status">
                    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            var gid = jQuery('#fj').val();
                            jQuery.MultiUploader_q({
                                gid : gid
                            });
                        });
                    </script>
                </logic:equal>
            </td>
        </tr>
        </tbody>
    </table>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <logic:notEqual name="status" value="view">

                        <button type="button" id="tj" type="button"
                                onclick="hdSave();">
                            保 存
                        </button>
                    </logic:notEqual>
                    <button type="button" name="关 闭" onclick="iFClose();">
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

