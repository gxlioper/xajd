<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
        <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function saveZw() {
                var guid = jQuery("#guid").val();
                var xh = jQuery("#xh").val();
                var jgid = jQuery("#jgid").val();
                var tnzw = jQuery("#tnzw").val();
                showConfirmDivLayer("ȷ���Ըó�Աְ����б����",{"okFun":function(){
                        jQuery.post("ttgl_stcygl.do?method=zwChange&type=save",{guid:guid,xh:xh,jgid:jgid,tnzw:tnzw},function(data){
                            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                                    var api = frameElement.api;
                                    var url = "ttgl_stcygl.do?method=zwManage&jgid="+jgid;
                                    if (api){
                                        if (api.get('childDialog')){
                                            api.reload(api.get('parentDialog') ,url);
                                        } else {
                                            var W = api.opener;
                                            W.location=url;
                                        }
                                    } else if (parent.window){
                                        parent.window.document.location=url;
                                    }
                                    iFClose();
                                }});
                        },'json');
                    }
                });
			}
		</script>
	</head>

	<body>
		<html:form action="/ttgl_stcygl">
			<input type="hidden" id="guid" value="${guid}"/>
			<input type="hidden" id="xh" value="${xh}"/>
			<input type="hidden" id="jgid" value="${jgid}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
            <div class="main_box">
                <h3 class=datetitle_01>
                    <span>ѡ��ְ��&nbsp;&nbsp; </span>
                </h3>
                <div class="con_overlfow" style="overflow-y: auto;">
                    <table width="100%" border="0" class="formlist">
                        <thead>
                        <tr>
                            <th colspan="4">
                                <span>ѧ����Ϣ</span>
                            </th>
                        </tr>
                        </thead>
                        <%@ include file="/xsgzgl/hdgl/hdqd/selectStudentforqd.jsp" %>
                    </table>
                    <table width="100%" border="0" class="formlist">
                        <thead>
                        <tr>
                            <th colspan="4">
                                <span>ְ����Ϣ</span>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th width="20%">��ǰְ��</th>
                            <td colspan="3">${data.tnzw}</td>
                        </tr>
                        <tr>
                            <th>Ŀ��ְ��</th>
                            <td colspan="3">
                                <select id="tnzw" name="tnzw" style="width: 50%">
                                    <option value="��һ������">��һ������</option>
                                    <option value="�ڶ�������">�ڶ�������</option>
                                    <option value="����������">����������</option>
                                    <option value="���ĸ�����">���ĸ�����</option>
                                    <option value="���帺����">���帺����</option>
                                    <option value="��Ա">��Ա</option>
                                    <option value="��֧��">��֧��</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div style="height:30px;"></div>
            <div>
                <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
                    <tfoot>
                    <tr>
                        <td>
                            <div class="btn">
                                <button type="button" onclick="saveZw()">
                                    ȷ��
                                </button>
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
