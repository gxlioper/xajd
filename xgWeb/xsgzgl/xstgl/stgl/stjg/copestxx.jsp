<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stjg/js/stjg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#xn").change(function(){
				if(jQuery("#xn").val()<'${currxn}'){
					showAlert("有效学年不得小于当前学年"+'${currxn}'+"！",{},{"clkFun":function(){
						jQuery("#xn").val('${currxn}');
						return false;
				    }});
				}
			});
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/stglStjg" method="post" styleId="StjgForm" onsubmit="return false;">
			
			<html:hidden property="stid" styleId="stid" value="${stid}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>社团项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<th width="40%">
								<font color="red">*</font>社团项目名称
							</th>
							<td width="60%">
								<html:text property="stxmmc" value="复制${stxmmc}" styleId="stxmmc" maxlength="50" style="width:96%"></html:text>
							
							</td>
						</tr>
						<tr>
							<th width="40%">
							<font color="red">*</font>有效学年
							</th>
							<td width="60%">
								<html:select  property="xn" styleId="xn" value="${currxn}" style="width:98%">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
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
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveCopyStjg();">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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

