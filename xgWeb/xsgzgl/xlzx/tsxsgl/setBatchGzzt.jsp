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
								<span>设置关注状态</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gzztxx">
						<logic:notEqual name="xxdm" value="11527">
							<tr>
								<th width="16%">
									<span class="red">*</span>关注状态
								</th>
								<td width="34%" colspan="3" >
									<html:select property="gzzt" styleId="gzzt" value="${gzzt}"  style="width:100px">
										<html:option value="1">关注</html:option>
										<html:option value="2">取消关注</html:option>
									</html:select>
								</td>
							</tr>
						
						</logic:notEqual>
							
							<%--湖南城市学院--%>
						<logic:equal name="xxdm" value="11527">
							<tr>
								<th width="16%">
									<span class="red">*</span>关注状态
								</th>
								<td >
									<html:select property="gzzt" styleId="gzzt" value="${gzzt}" onchange="gz();" style="width:100px">
										<html:option value="1">学校重点关注</html:option>
										<html:option value="2">学院重点关注</html:option>
										<html:option value="3">学院预警对象</html:option>
										<html:option value="0">取消关注</html:option>
									</html:select>
									</td>
								<th width="16%" id="yymsTh">
									<span class="red">*</span>原因描述
								</th>
								<td id="yymsTd">
									<html:select property="yyms" styleId="yyms" value="${yyms}"  style="width:100px">
										<html:option value="毕业">毕业</html:option>
										<html:option value="休学">休学</html:option>
										<html:option value="退学">退学</html:option>
										<html:option value="康复">康复</html:option>
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

