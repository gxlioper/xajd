<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/fyff/ffjg/js/fyffjg.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function($){
			
			if('����' == $("#fffs").val()){
				jQuery("#bfyf").show();
			} else {
				jQuery("#bfyf").hide();
			}
			
		});

		
		</script>
		
	</head>
		
	<body>
		
		<html:form action="/rcsw_fyff_ffjg" method="post" styleId="FyffjgForm" onsubmit="return false;">
			<input type="hidden" id = "fffs" name = "fffs" value = "${rs.fffs}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>ѧ����Ϣ</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/fyff/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>���÷�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>������Ŀ</th>
							<td>
								${rs.ffxmmc }
							</td>
							<th>���Ž��</th>
							<td>
								${rs.sfje }
							</td>
					    </tr>
					    <tr>
							<th>����ʱ��</th>
							<td>
								${rs.ffsj }
							</td>
							<th>����;��</th>
							<td>
								${rs.fftjmc }&nbsp;&nbsp;
								
								${rs.ffzh }

							</td>
						</tr>
						<tr id="bfyf">
							<th>�����·���</th>
							<td>
								${rs.bfyfs }
							</td>
					    
							<th>�������</th>
							<td>
								${rs.bfje }
							</td>
						</tr>
					    <tr>
							<th>
								��ע
								<br />
							</th>
							<td colspan="3">
								${rs.bz }
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>

