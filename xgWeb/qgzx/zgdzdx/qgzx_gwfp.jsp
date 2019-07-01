<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input id="pk" name="pk" value="<bean:write name="pk"/>" type="hidden"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 直接分配岗位 - 岗位分配</a>
				</p>
			</div>

			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>岗位分配</span>
						</th>
					</tr>
				</thead>	
				<tbody>			
				<tr>
					<th>学年</th>
					<td >
						<bean:write name="xn"/>
					</td>
				</tr>
				<tr>
					<th>年度</th>
					<td >
						<bean:write name="nd"/>
					</td>
				</tr>
				<tr>
					<th>学期</th>
					<td >
						<bean:write name="xq"/>
					</td>
				</tr>
				<tr>
					<th>岗位</th>
					<td>
						<html:select property="gwdm">
						<html:options collection="gwList" labelProperty="gwdm" property="gwdmgwsbsj"/>
						</html:select>
					</td>
				</tr>	
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" class="button2" onclick="refreshForm('qgzxZgdzdx.do?method=saveGwfp')">
							提 交
						</button>
						<button type="button" class="button2" onclick="Close();return false;">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>	
			</table>
			</div>

			<logic:present name="result">
				<logic:equal value="true" name="result">
					<logic:empty name="msg">
					<script>
						alert('操作成功!');
					</script>
				</logic:empty>
				<logic:notEmpty name="msg">
				<input id="msg" name="msg" value="<bean:write name="msg"/>" type="hidden"/>
				<script>
					alert(document.getElementById('msg').value);
				</script>
				</logic:notEmpty>
				<script>
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<logic:empty name="msg">
					<script>
						alert('操作失败');
					</script>
				</logic:empty>
				<logic:notEmpty name="msg">
				<input id="msg" name="msg" value="<bean:write name="msg"/>" type="hidden"/>
				<script>
					alert(document.getElementById('msg').value);
				</script>
				</logic:notEmpty>
				<script>
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
