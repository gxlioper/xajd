<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<input type="hidden" name="czlx" value="回复" />
			<input type="hidden" name="xh" value="${rs.xh }" />
			<input type="hidden" name="dwmc" value="${jyweb_realName }" />
			<input type="hidden" name="gwmc" value="${rs.gwmc }" />
			<input type="hidden" name="gwfbsj" value="${rs.gwfbsj }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>消息回复</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button name="提交"
										onclick="saveUpdate('jywebUseCheckSession.do?method=revertResume&doType=save','hfxx');">
										保存
									</button>
									<button onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="20%">
								应聘职位
							</th>
							<td>
								${rs.gwmc }
							</td>
						</tr>
						<tr>
							<th width="20%">
								简历投递时间
							</th>
							<td>
								${rs.tdrq }
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>内容
							</th>
							<td style="word-break:break-all;">
								<html:textarea property="hfxx" style="width:95%" rows="12" onblur="checkLen(this,800)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<script>
 			alert("${message}");
 			if(window.dialogArguments){
 				window.close();
 				dialogArgumentsQueryChick();
 			}
 			
 		</script>
		</logic:present>

	</body>
</html>
