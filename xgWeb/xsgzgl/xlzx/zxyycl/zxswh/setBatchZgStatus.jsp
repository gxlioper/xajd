<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xlzx/zxswh/js/setBatchZgStatus.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
	</head>
	<body >
		<html:form action="/xlzx_zxs" method="post"  onsubmit="return false;" styleId="form">
		<input type="hidden" id="dealZgh" name="dealZgh" value="${dealZgh }"  />
			<div style='width:100%;height:80px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="1" class="formlist">
					<thead>
						<tr width="100%">
							<th colspan="4">
								<span>在岗状态设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<span class="red">*</span>在岗状态
							</th>
							<td width="34%" colspan="3" >
								<html:select property="status" styleId="status" value="${status}"  style="width:100px">
									<html:option value="">请选择</html:option>
									<html:option value="1">在岗</html:option>
									<html:option value="2">不在岗</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table border="1" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>

