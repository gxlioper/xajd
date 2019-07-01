<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/sthdgl/sthdjg/js/sthdjg.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
		jQuery(function(){
			proviceCiyyLocalMain({type:"edit",id:"fwddssx",flag:"yxxdz"});
		})
		</script>
	</head>
	<body>
		<html:form action="/sthdglSthdjg" method="post" styleId="SthdjgForm">
			<html:hidden property="hdid"/>
			<html:hidden property="xh" styleId="xh"/>
			<input type="hidden" name="stid" id="stid" value="0001"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font></span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%">
							<span class="red">*</span>�����
						</th>
						<td width="30%">
							<html:text property="hdmc" styleId="hdmc" style="width:95%" onblur="checkLen(this,50);"/>
						</td>
						<th  width="20%"><span class="red">*</span>ʱ��</th>
						<td width="30%">
							<input type="hidden" id="nowDate" name="nowDate" value="${nowDate}">
							<html:text property="fwsj"  styleId="fwsj"
									   onclick="return showCalendar('fwsj','yyyy-MM-dd HH:mm',true,'nowDate');" />
						</td>
					</tr>
					<tr>
						<th width="20%">
							<font color="red">*</font>���쵥λ
						</th>
						<td width="30%">
							<html:text property="zbdw" styleId="zbdw" style="width:95%" onblur="checkLen(this,50);"/>
						</td>
						<th><span class="red">*</span>����ʱ��</th>
						<td>
							<html:text property="fwsc" styleId="fwsc" onblur="checkLen(this,5);"/>
						</td>

					</tr>
					<tr>
						<th width="20%">
							<font color="red">*</font>����ص�
						</th>
						<td width="30%" colspan="3">
							<html:hidden  property="fwddssx" styleId="fwddssx"/>
							<html:text property="fwdd" styleId="fwdd"  onblur="checkLen(this,50);"/>
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td colspan="3">
							<html:hidden property="fjid" styleId="fjid" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    jQuery.MultiUploader({
                                        maxcount : 3,
                                        //��׺
                                        accept : 'png|gif|jpg|zip|rar|doc|docx',
                                        //����ļ���С ��λM
                                        maxsize: 10,
                                        //��Ÿ������������id
                                        elementid : 'fjid'
                                    });
                                });
							</script>
						</td>
					</tr>
					</tbody>
				 </table>
				</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveSthdjg('update');">
										��    ��
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