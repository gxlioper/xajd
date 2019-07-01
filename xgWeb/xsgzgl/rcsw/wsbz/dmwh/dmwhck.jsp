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
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/dmwh/js/dmwh.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/wsbz_dmwh" method="post"
			styleId="WsbzDmwhForm" onsubmit="return false;">
			<html:hidden property="fddm" styleId="fddm"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th width="40%">
								小分队名称
							</th>
							<td width="60%">
								${jg.fdmc}
							</td>
						</tr>
						<tr>
							<th width="40%">
								活动频率
							</th>
							<td width="60%">
								${jg.hdpl}
							</td>
						</tr>
						<tr>
							<th width="40%">
								监督时间
							</th>
							<td width="60%">
								${jg.sj}
							</td>
						</tr>
						<tr>
							<th width="40%">
								监督地点
							</th>
							<td width="60%">
								${jg.dd}
							</td>
						</tr>
						<tr>
							<th width="40%">
								人数
							</th>
							<td width="60%">
								${jg.rs}
							</td>
						</tr>
						<tr>
							<th width="40%">
								工作职责
							</th>
							<td width="60%">
								${jg.gzzz}
							</td>
						</tr>
						<tr>
							<th width="40%">
								服务要求
							</th>
							<td width="60%">
								${jg.fwyq}
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <div style="height:30px"></div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
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

