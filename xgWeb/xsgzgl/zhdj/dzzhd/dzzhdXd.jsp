<!--党组织活动心得-->
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
    <script language="javascript" src="comm/editor/kindeditor.js"></script>
    <script language="javascript" src="comm/editor/zh_CN.js"></script>
    <script language="javascript" src="comm/editor/editor.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/dzzhd/js/dzzhdXd.js"></script>
</head>
<body style="width:100%">
<html:form action="/zhdj_dzzhd" method="post" styleId="DzzhdForm">
    <table width="100%" border="0" class="formlist">
        <input type="hidden" value="${data.id}" id="id">
        <input type="hidden" value="${data.xh}" id="xh">
        <input type="hidden" value="${data.hdid}" id="hdid">
        <input type="hidden" value="${status}" id="status">
        <tbody id="tbody_jbxx">
        <tr>
            <th width="20%">
                学号
            </th>
            <td width="30%" >
                ${data.xh}
            </td>
            <th width="20%">
                姓名
            </th>
            <td width="30%" >
               ${data.xm}
            </td>
        </tr>
        <tr>
            <th width="20%">
                <span style="color:red">*</span>活动心得
            </th>
            <td width="80%" colspan="3">
                <logic:notEqual value="view" name="status">
                    <textarea rows="8" name="editorid" id="editorid" style="height:100%;">${data.hdxd}</textarea>
                </logic:notEqual>
                <logic:equal value="view" name="status">
                    <p>${data.hdxd}</p>
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
                                onclick="hdxdSave();">
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

