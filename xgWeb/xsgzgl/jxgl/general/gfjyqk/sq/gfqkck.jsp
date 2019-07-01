<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/gfjyqk/sq/js/gfqksq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
	</head>
	<body>
		<html:form method="post" styleId="gfjysqForm" action="/gfjyqk_sq"
			enctype="multipart/form-data">
			<html:hidden property="sqid"  styleId="sqid"/>
			<html:hidden property="shzt" styleId="shzt" />
			<html:hidden property="splc"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								ѧ��
							</th>
							<td align="left">
								${rs.xn}
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
									${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								�����������
							</th>
							<td align="left" colspan="3">
								${rs.gfqkflmc}
							</td>
						</tr>
						<tr id="bydj" <logic:notEqual value="1" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								���۵Ǽ�ʱ��
							</th>
							    <td align="left">
									${rs.bydjsj}
								</td>
							
							<th align="right">
								���۵Ǽǵص�
							</th>
								<td align="left">
									${rs.bydjdd}
								</td>
						</tr>
						
						<tr id="cjrw" <logic:notEqual value="2" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								�ξ�����ʱ��
							</th>
							    <td align="left">
										${rs.cjrwsj}
								</td>
							
							<th align="right">
								������׼��λ
							</th>
								<td align="left">
									${rs.rwpzdw}
								</td>
						</tr>
						<tr id="twfx"  <logic:notEqual value="3" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								���鸴ѧʱ��
							</th>
							    <td align="left">
							    ${rs.twfxsj}
								</td>
							
							<th align="right">
								������׼��λ
							</th>
								<td align="left">
								    ${rs.twpzdw}
								</td>
						</tr>
						<tr id="pjpy"  <logic:notEqual value="4" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								��������ʱ��
							</th>
							    <td align="left">
							       ${rs.pjpysj}
								</td>
							
							<th align="right">
								�������ŵ�λ
							</th>
								<td align="left">
								 ${rs.pjpydw}
								</td>
						</tr>
						
						<tr id="jc"  <logic:notEqual value="5" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								����ʱ��
							</th>
							    <td align="left">
							     ${rs.jcsj}
								</td>
							
							<th align="right">
								���͵�λ
							</th>
								<td align="left">
								 ${rs.jcdw}
								</td>
						</tr>
						<tr id="cjhd"  <logic:notEqual value="6" name="gfqkfl">style="display:none"</logic:notEqual>>
							<th align="right" width="10%">
								�μӻʱ��
							</th>
							    <td align="left">
							     ${rs.cjhdsj}
								</td>
							
							<th align="right">
								�μӻ�ص�
							</th>
								<td align="left">
								 ${rs.cjhddd}
								</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��ע&nbsp;
								<br />
							</th>
							<td colspan="3">
							 ${rs.bz}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<input type="hidden" id="fjid" value="${filepath}"/>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
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
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
