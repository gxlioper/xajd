<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/hdgl/js/hdxq.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body style="width: 100%">
<html:form action="/hdgl_hdxq" method="post" styleId="demoForm" onsubmit="return false;">
    <html:hidden property="ids" styleId="ids" value="${data.sqid}"/>
    <html:hidden property="sqid" styleId="sqid" value="${data.sqid}"/>
    <html:hidden property="bmlx" styleId="bmlx" value="${bmlx}"/>
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <%@ include file="/xsgzgl/hdgl/hdxq/viewStudent.jsp" %>
            <thead>
            <tr>
                <th colspan="4">
                    <span>������Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>������Ƭ</th>
                <td colspan="3">
                    <div id="stuImg" class="open_img" style="margin-left:0px;margin-right:5px;margin-top: 0px;height: 130px">
                        <img style="width:100px;height:130px" id="avatarPreview" src="xsxx_xsgl.do?method=showPhoto&xh=${xh}" border="0">
                    </div>
                </td>
            </tr>
            <tr>
                <th>���˽���</th>
                <td colspan="3">
                    ${data.gzjl}
                </td>
            </tr>
            <tr>
                <th>���˹�������</th>
                <td colspan="3">
                        ${data.zyzjl}
                </td>
            </tr>
            <tr>
                <th>��У�ڼ���������</th>
                <td colspan="3">
                        ${data.yynl}
                </td>
            </tr>
            <tr>
                <th>����ԭ��</th>
                <td colspan="3">
                        ${data.zwms}
                </td>
            </tr>
            <tr>
                <th>ְҵ����</th>
                <td colspan="3">
                        ${data.zyjn}
                </td>
            </tr>
			<tr>
			    <th>��������</th>
			    <td colspan="3">
			            <input type="hidden" id="bmfj" name="bmfj" value="${data.bmfj}" />
			             <div id="bmfjDiv"></div>
			            <script type="text/javascript">
			            	//���ø���
			            	jQuery(function() {
			            		jQuery('#bmfjDiv').multiUploader_q({
			            			gid: jQuery('#bmfj').val(),
			            			uid: 'fjuid${n}'
			            		});
			            	});
			            </script>	 
			    </td>
			</tr>
            <logic:equal value="sh" name="cz">
                <tr>
                    <th width="16%">
                        ������
                        <font color="red">��200��</font>
                    </th>
                    <td  colspan="3">

                        <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgw&id=shyj" />
                        <textarea rows="10" style="width: 90%;margin-top: 5px" id="shyj" name="shyj"></textarea>
                    </td>
                </tr>
            </logic:equal>

            </tbody>
        </table>
    </div>
    <div style="height:25px"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
						<logic:equal value="sh" name="cz">
							<button type="button" onclick="bcsh('1');return false;">
							    ͨ��
							</button>
							<button type="button" onclick="bcsh('2');return false;">
							    ��ͨ��
							</button>
						</logic:equal>
                        <button type="button" onclick="iFClose();">
                            �ر�
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

