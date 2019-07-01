<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkcgbb/js/cgbb.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/rcsw_xsgzqkbb_cgbbgl" method="post" styleId="xsgzqkCgbbForm" onsubmit="return false;">
    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生工作情况常规报表信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="20%">
                    报送人
                </th>
                <td width="30%">
                    <html:hidden property="bsr"></html:hidden>
                    ${xsgzqkCgbbForm.bsrmc}
                </td>
                <th>
                    <logic:equal value="xx" name="userStatus">
                        <span class="red">*</span>
                    </logic:equal>
                    报送单位
                </th>
                <td>
                    <logic:equal value="xx" name="userStatus">
                        <html:select property="bsdw"  styleId="bsdw" style="width:220px">
                            <html:options collection="xyList" property="xydm" labelProperty="pyszm" />
                        </html:select>
                    </logic:equal>
                    <logic:notEqual value="xx" name="userStatus">
                        <html:hidden property="bsdw"></html:hidden>
                        ${xsgzqkCgbbForm.bsdwmc}
                    </logic:notEqual>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <span class="red">*</span>学年
                </th>
                <td width="30%">
                    <html:select  property="xn" styleId="xn" style="width:100px" >
                        <html:options collection="xnList" labelProperty="xn" property="xn"/>
                    </html:select>
                </td>
                <th><span class="red">*</span>学期</th>
                <td>
                    <html:select  property="xq" styleId="xq" >
                        <html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th width="20%">
                    <font color="red">*</font>报送主题
                </th>
                <td width="80%" colspan="3">
                    <html:text property="bszt" styleId="bszt" style="width:99%;" maxlength="100"></html:text>
                </td>
            </tr>

            <tr>
                <th width="20%"><font color="red">*</font>报送内容
                </br><font color="red">(限2000字)</font></th>
                <td colspan="3">
                    <html:textarea property="bsnr" styleId="bsnr"
                                   onkeypress="checkLen(this,2000);"
                                   style="width:99%;" rows="4"></html:textarea>
                </td>
            </tr>

            <tr>
                <th width="20%">附件</th>
                <td colspan="3">
                    <html:hidden property="fjid" styleId="fjid" />
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
                                elementid : 'fjid'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="height:35px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <button type="button" onclick="saveForAdd();">
                            保存
                        </button>
                        <button type="button" onclick="iFClose();">
                            关闭
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

