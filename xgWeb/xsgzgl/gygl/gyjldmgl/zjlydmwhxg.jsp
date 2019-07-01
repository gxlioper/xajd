<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyjldmgl/js/zjlydmwh.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_gyjldmgl" method="post"
			styleId="GyjldmglForm" onsubmit="return false;">

			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>奖惩类别代码
							</th>
							<td>
								${dm}
								<html:hidden property="gyjllbdldm" styleId="gyjllbdldm" />
							</td>
						</tr>
						<tr>
							<th>
								奖惩类别名称
							</th>
							<td>
								<html:text property="gyjllbdlmc" styleId="gyjllbdlmc" maxlength="100"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								类别
							</th>
							<td>
								${jkf}
								<html:hidden property="lb" styleId="lb"  />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>分值
							</th>
							<td>
								<html:text property="fz" styleId="fz" onkeyup="checkInputNum(this)" maxlength="3"
									styleClass="text_nor" />
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveFormjldm('update');return false;">
										保 存
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
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

