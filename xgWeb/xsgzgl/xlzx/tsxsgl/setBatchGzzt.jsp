<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xlzx/tsxsgl/js/setBatchGzzt.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
	</head>
	<body >
		<html:form action="/xlzx_tsxs" method="post"  onsubmit="return false;" styleId="form">
		<input type="hidden" id="dealTsxs" name="dealTsxs" value="${dealTsxs }"  />
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style='width:100%;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="1" class="formlist">
					<thead>
						<tr width="100%">
							<th colspan="4">
								<span>���ù�ע״̬</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gzztxx">
						<logic:notEqual name="xxdm" value="11527">
							<tr>
								<th width="16%">
									<span class="red">*</span>��ע״̬
								</th>
								<td width="34%" colspan="3" >
									<html:select property="gzzt" styleId="gzzt" value="${gzzt}"  style="width:100px">
										<html:option value="1">��ע</html:option>
										<html:option value="2">ȡ����ע</html:option>
									</html:select>
								</td>
							</tr>
						
						</logic:notEqual>
							
							<%--���ϳ���ѧԺ--%>
						<logic:equal name="xxdm" value="11527">
							<tr>
								<th width="16%">
									<span class="red">*</span>��ע״̬
								</th>
								<td >
									<html:select property="gzzt" styleId="gzzt" value="${gzzt}" onchange="gz();" style="width:100px">
										<html:option value="1">ѧУ�ص��ע</html:option>
										<html:option value="2">ѧԺ�ص��ע</html:option>
										<html:option value="3">ѧԺԤ������</html:option>
										<html:option value="0">ȡ����ע</html:option>
									</html:select>
									</td>
								<th width="16%" id="yymsTh">
									<span class="red">*</span>ԭ������
								</th>
								<td id="yymsTd">
									<html:select property="yyms" styleId="yyms" value="${yyms}"  style="width:100px">
										<html:option value="��ҵ">��ҵ</html:option>
										<html:option value="��ѧ">��ѧ</html:option>
										<html:option value="��ѧ">��ѧ</html:option>
										<html:option value="����">����</html:option>
									</html:select>
								</td>
							</tr>
						</logic:equal>
							
					</tbody>
				</table>
			</div>
			<table border="1" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
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

