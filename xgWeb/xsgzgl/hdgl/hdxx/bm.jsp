<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/hdgl/js/hdxx.js"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/hdgl_hdxx" method="post" styleId="demoForm" onsubmit="return false;">
    <html:hidden property="hdid" styleId="hdid" value="${hdid}"/>
	<logic:equal value="createGroup" name="lx">
	    <html:hidden property="xhs" styleId="xhs" value="${xhs}"/>
	</logic:equal>
    <logic:equal value="jrdw" name="lx">
        <html:hidden property="dwid" styleId="dwid" value="${dwid}"/>
    </logic:equal>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
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
                    <span>简历信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th><span class="red">*</span>个人照片</th>
                <td colspan="3">
                    <div id="stuImg" class="open_img" style="margin-left:0px;margin-right:5px;margin-top: 0px;height: 130px">
                        <img style="width:100px;height:130px" id="avatarPreview" src="xsxx_xsgl.do?method=showPhoto&xh=${xh}" border="0">
                    </div>
                    <%--<div align="left" style="margin-left: 10px;">--%>
                        <%--<button type="button" onclick="scZp();return false;">--%>
                            <%--上传照片--%>
                        <%--</button>--%>
                        <%--<input id="imgUpload" type="file" name="stuPic" style="display:none;" onchange="upload();">--%>
                    <%--</div>--%>
                </td>
            </tr>
            <logic:iterate id="i" name="jldxList">
                <tr>
                    <th>
                        <logic:equal value="1" name="i" property="sfbt">
                            <span class="red">*</span>
                        </logic:equal>
                        ${i.mc}
                    </th>
                    <td colspan="3">
						<logic:equal value="bmfj" name="i" property="zd">
							<input type="hidden" id="bmfj" name="bmfj" value="${data.bmfj}" />
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
												elementid : 'bmfj'
												});
										});
									</script>
						</logic:equal>
						<logic:equal value="gzjl" name="i" property="zd">
							<textarea name="gzjl" id="gzjl" style="width:98%;" rows="5">${data.gzjl}</textarea>
						</logic:equal>
                        <logic:equal value="zyzjl" name="i" property="zd">
                            <textarea name="zyzjl" id="zyzjl" style="width:98%;" rows="5">${data.zyzjl}</textarea>
                        </logic:equal>
                        <logic:equal value="yynl" name="i" property="zd">
                            <textarea name="yynl" id="yynl" style="width:98%;" rows="5">${data.yynl}</textarea>
                        </logic:equal>
                        <logic:equal value="zwms" name="i" property="zd">
                            <textarea name="zwms" id="zwms" style="width:98%;" rows="5">${data.zwms}</textarea>
                        </logic:equal>
                        <logic:equal value="zyjn" name="i" property="zd">
                            <textarea name="zyjn" id="zyjn" style="width:98%;" rows="5">${data.zyjn}</textarea>
                        </logic:equal>
					</td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                        "<span class="red">*</span>"为必填项
                    </div>
                    <div class="btn">
                        <logic:equal value="grbm" name="lx">
                            <button type="button" onclick="saveBm();">
                                提交
                            </button>
                        </logic:equal>
                        <logic:equal value="createGroup" name="lx">
                            <button type="button" onclick="createGroup();">
                                提交
                            </button>
                        </logic:equal>
                        <logic:equal value="jrdw" name="lx">
                            <button type="button" onclick="jrdwSave();">
                                提交
                            </button>
                        </logic:equal>
                        <button type="button" onclick="iFClose();">
                            关闭
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    <!--提示框弹框-->
    <div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <a type="button" class="close" data-dismiss="modal"
                       aria-hidden="true">×
                    </a>
                    <h4 class="modal-title" id="msgTitle">
                        提示
                    </h4>
                </div>
                <div class="modal-body" id="msgContent">
                    按下 ESC 按钮退出。
                </div>
                <div class="modal-footer">
                    <a type="button" class="btn btn-default"
                       data-dismiss="modal">关闭
                    </a>
                </div>
            </div>
        </div>
    </div>
</html:form>
</body>
</html>

