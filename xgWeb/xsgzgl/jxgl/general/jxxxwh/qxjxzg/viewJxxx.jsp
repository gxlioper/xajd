<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">

				
		</script>
	</head>
	<body>
		<html:form method="post" styleId="qxjxzgForm" action="/qxjxzg">
		 <html:hidden property="jxid" styleId="jxid"/>
		 <html:hidden property="xh" styleId="xh"/>
		<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>��ѵ��Ϣ
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr >
				<td colspan="4">
				<table width="100%" class="formlist" >
	            <tr >
				<th style="text-align:center;">��ѵ����</th>
				<th style="text-align:center;">��ѵ��ʼʱ��</th>
				<th style="text-align:center;">��ѵ����ʱ��</th>
				</tr>
				<tr>
				<td align="center">${jxxxMap.jxmc}</td>
				<td align="center">${jxxxMap.kssj}</td>
				<td align="center">${jxxxMap.jssj}</td>
				</tr>
				</table>
				</td>
				</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>��ϸ��Ϣ
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							ȡ������	
						</th>
						<td colspan="3">
							${data.ly}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							������Ϣ
						</th>
						<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = "${data.fj}";
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
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
							<div class="btn">
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
