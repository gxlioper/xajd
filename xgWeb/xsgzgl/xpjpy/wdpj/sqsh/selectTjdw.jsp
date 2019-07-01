<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			function selectTjdw(){
				var tjdw = jQuery("#tjdw").val();
				
				if (tjdw == "")
					return false;
				
				var api = frameElement.api,W = api.opener;
				W.document.location.href="xpj_sqsh.do?method=viewShqkList&tjdw="+tjdw;
				iFClose();
			}
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh" method="post" styleId="zcxmForm">
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="selectTjdw();">
										确定 
									</button>
									
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="30%">
								<span class="red">*</span>统计方式
							</th>
							<td width="70%">
								<html:select property="tjdw" styleId="tjdw">
									<html:option value=""></html:option>
									<logic:present name="tjdwList">
										<html:options collection="tjdwList" property="dm" labelProperty="mc"/>
									</logic:present>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

