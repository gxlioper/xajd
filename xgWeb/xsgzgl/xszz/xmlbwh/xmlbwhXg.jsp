<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmlbwh/js/xmlbwhXg.js"></script>
	</head>
	<body >	
		<html:form action="/xszz_xmlbwh" method="post" styleId="form1" onsubmit="return false;">
			<html:hidden property="lbdm" styleId="lbdm" />			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��Ŀ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="30%">
								<font color="red">*</font>����
							</th>
							<td width="70%" >
								<html:text property="lbmc" styleId="lbmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ˵��<br/>
								<font color="red">(������200����)</font>
							</th>
							<td style="word-break:break-all;">
								<html:textarea  property="lbsm" styleId="lbsm" style="width:98%"  rows="5"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button"  onclick="saveForm();">
										�� ��
									</button>
									<button type="button"  onclick="iFClose();">
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

