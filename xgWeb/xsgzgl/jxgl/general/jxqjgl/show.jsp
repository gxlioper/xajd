<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxqjgl/js/jxqjjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="JxqjjgForm" action="/jxqjjg">
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>�����Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<logic:equal name="xxdm" value="10026">
						<tr>
							<th align="right" width="10%">
								����
							</th>
							<td align="left" colspan="3">
								${jzmc}
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							ѧ��
						</th>
						<td align="left">
							${data.xn}
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							�������
						</th>
						<td align="left">
							${data.qjts}&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right">
							�������
						</th>
						<td align="left">
							${qjlxmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<logic:equal name="xxdm" value="10026">
								��Ӫʱ��
							</logic:equal>
							<logic:notEqual name="xxdm" value="10026">
								��ٿ�ʼʱ��
							</logic:notEqual>
						</th>
						<td align="left">
							${data.qjkssj }
						</td>
						<th align="right">
							<logic:equal name="xxdm" value="10026">
								��Ӫʱ��
							</logic:equal>
							<logic:notEqual name="xxdm" value="10026">
								��ٽ���ʱ��
							</logic:notEqual>
						</th>
						<td align="left">
							${data.qjjssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<logic:equal name="xxdm" value="10026">
								���ԭ��
							</logic:equal>	
							<logic:notEqual name="xxdm" value="10026">
								�������
							</logic:notEqual>
						</th>
						<td colspan="3">
							${data.qjsy}
						</td>
					</tr>
					<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="fjxx" styleId="fjxx"/>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjxx').val();
									jQuery.MultiUploader_q({
										gid : gid
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
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
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
