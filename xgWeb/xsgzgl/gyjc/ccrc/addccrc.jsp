<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccrc/js/ccrc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gyjc_ccrcsz" method="post" styleId="CcrcForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom:0px;" >
			<div class="toolbox">
				<!-- ��ť
				<div class="buttonbox">
					<ul>
						<li id="li_fh">
							<a href="javascript:void(0);" class="btn_fh" onclick="fhjcsd();return false;"  >����</a>
						</li>	
						<li id="li_fp">
							<a href="javascript:void(0);" class="btn_sz" onclick="saveFyfp();return false;"  >����</a>
						</li>	
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelFp();return false;" 
							   class="btn_qxsh">ȡ������</a>
						</li>	
						
					</ul>
				</div>
				 -->
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ճ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>${xn}<input type="hidden" name="xn" id="xn" value="${xn}"/> </td>
							<th>ѧ��</th>
							<td>${xqmc}<input type="hidden" name="xqdm" id="xqdm" value="${xq}"/> </td>
						</tr>
						<tr>
							<th><font color="red">*</font>�������</th>
							<td>
								<html:text property="jcrq" value="${minDate}" styleId="jcrq" onclick="return showCalendar('jcrq','y-mm-dd');"  maxlength="10" ></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
						
					</tbody>
				</table>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveCcrc();">
										��    ��
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