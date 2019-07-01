<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			function selectSbjx(){
				var xmdm = jQuery("#xmdm").val();
				
				if (xmdm == "")
					return false;
				
				var api = frameElement.api,W = api.opener;
				W.initGrid(xmdm);
				jQuery("#jxmc",W.document).html(jQuery("#xmdm option:selected").html());
				iFClose();
			}
		</script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh" method="post" styleId="zcxmForm">
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="selectSbjx();">
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
								<span class="red">*</span>上报奖项
							</th>
							<td width="70%">
								<html:select property="xmdm" styleId="xmdm" style="width:95% ">
									<html:option value=""></html:option>
									<logic:present name="pjxmList">
										<html:options collection="pjxmList" property="xmdm" labelProperty="xmmc"/>
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

