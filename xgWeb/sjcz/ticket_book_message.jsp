<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
			function saveMes(){
				var txss = document.getElementById('tsxx').value;
				refreshForm('ticket_book_appsucess.do?method=SaveSucessMessage&doType=save&val='+txss);
			}
		</script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>日常事务 - 票务管理 - 维护提示信息</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>填写提示信息表</span>
								</th>
							</tr>
						</thead>


						<tbody>
							<tr>
								<td>
									<html:textarea property="tsxx" name="rs" styleId="txss"
										 style="word-break:break-all;width:99%" rows="2">
									</html:textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" class="button2" onclick="saveMes();">
											提 交
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="button2" onclick="window.close();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
					
			</logic:notEmpty>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
    alert("操作成功！");
    window.close();
    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
    alert("操作失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>

