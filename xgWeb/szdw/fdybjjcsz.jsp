<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		
	<script language="javascript">
	</script>
</head>
<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>思政队伍 - 信息维护 - 辅导员编班 - 参数设置</a>
		</p>
	</div>
	
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				现在不在申请时间内！--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/szdwfzjy" method="post">
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>参数设置</span></th>
			        </tr>
			    </thead>
			    <tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			         
			          	<button type="button" name="保存" onClick="refreshForm('szdwfzjy.do?method=fdybjjcszSave');">保 存</button>
			        	<logic:equal name="userType" value="admin" scope="session">
						<button type="button"  onClick="if(confirm('该操作会把已经分配的辅导员班级全部清空，你确定要重新进行分配吗?')){refreshForm('szdwfzjy.do?method=fdybjCsh');}">
							清空辅导员已分配班级
						</button>
						<button type="button"  onClick="if(confirm('该操作会把已经分配的班主任班级全部清空，你确定要重新进行分配吗?')){refreshForm('szdwfzjy.do?method=bzrbjCsh');}">
							清空班主任已分配班级
						</button>
						</logic:equal>
			          </div></td>
			      </tr>
			    </tfoot>
			<tbody>
			<tr>
				<th colspan="2">
				<div  align="center">
					<font color="red">温馨提示:   </font>
					<font color="blue">修改参数设置对已经分配好的班级的不起任何作用</font>
				</div>
				</th>
			</tr>
			<tr>
				<th align="center" width="20%" rowspan="4">
						辅导员参数设置
				</th>
				<td width="80%">
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="1">单个辅导员只能分管单个班级</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="2">单个辅导员可以分管多个班级</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="3">多个辅导员可以分管单个班级</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="4">多个辅导员可以分管多个班级</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<th align="center" width="20%" rowspan="4">
						班主任参数设置
				</th>
				<td width="80%">
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="1">单个班主任只能分管单个班级</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="2">单个班主任可以分管多个班级</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="3">多个班主任可以分管单个班级</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="4">多个班主任可以分管多个班级</html:radio>
					</div>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		
		
		</html:form>
			<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('修改成功！');
	         	window.dialogArguments.refreshForm("/xgxt/setFdyBj.do");
	         	Close();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('修改失败');
			  </script>
			</logic:equal>
			<logic:equal value="qk" name="done">
			  <script type="text/javascript">
			    alert('清空成功！');
	         	window.dialogArguments.refreshForm("/xgxt/setFdyBj.do");
	         	Close();
			  </script>
			</logic:equal>	
			<logic:equal value="wqk" name="done">
			  <script type="text/javascript">
			    alert('清空失败');
			  </script>
			</logic:equal>
</body>
</html>
