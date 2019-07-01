<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body onload="checkWinType();">
	<html:form action="/data_search" method="post">		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 归档资料 - 归档资料提交</a>
			</p>
		</div>	

		<div class="tab">
		  <table width="100%" border="0" class="formlist" id="rsT">
			<thead>
				<tr>
					<th colspan="2">						
						<span>学生归档资料提交详细信息</span>						
					</td>
				</tr>
			</thead>
			<tbody>					
			<tr>
				<th>学号</th>
				<td>
					${rs.xh}
					<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
				</td>
				<tr>
					<th>姓名</th>
					<td>
						${rs.xm}
					</td>
				</tr>
				<tr>
				<th>性别</th>
				<td>
					${rs.xb}
				</td>
			 </tr>		
			<tr>
				<th>年级</th>
				<td>
					${rs.nj}							
				</td>
			</tr>
			<tr>
				<th>学制</th>
				<td>
					${rs.xz}
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc}
				</td>
			</tr>			
			<tr>					
				<th>专业名称</th>
				<td>
					${rs.zymc}
				</td>
			</tr>
			<tr>
				<th>班级名称</th>
				<td>
					${rs.bjmc}
				</td>
			</tr>	
			<logic:equal value="11407" name="xxdm">
			<tr>
				<td colspan="2">
				<table  style="width:100%" class="formlist">
					<thead>
						<tr><th colspan="4"><span>入学档案</span></th></tr>
					</thead>
					<tbody>
					<tr>
						<th>生源地</th>
						<td>
							${rs.syd}
						</td>
						<th>民族</th>
						<td>
							${rs.mzmc}
						</td>
					</tr>
					<tr>
						<th>档案情况</th>
						<td colspan="3">
							${rs.daqk}
						</td>
					</tr> 
					<tr>
						<th>备注</th>
						<td colspan="3">
							${rs.bz}
						</td>
					</tr>
				</tbody>
			</table>
			</td>
			</tr>
			<tr>
			<td colspan="2">
				<table  style="width:100%" class="formlist">
					<thead>
						<tr><th colspan="2"><span>在校档案资料</span></th></tr>
					</thead>
					<tbody>
					<logic:notEmpty name="zlList">
					<tr>
						<th bgcolor="#CCCCCC">资料名称</th>
						<th bgcolor="#CCCCCC">资料张数</th>
					</tr>
					<logic:iterate id="zl" name="zlList">
						<tr>
							<logic:iterate id="v" name="zl">
							<td>
								<center><bean:write name="v"/></center>
							</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					</tbody>
				</table>
			</td>
			</tr>

			<tr>
			<td colspan="2">
				<table  style="width:100%" class="formlist">
					<thead>
						<tr><th colspan="2"><span>毕业档案</span></th></tr>
					</thead>
					<tbody>
					<tr>
						<th>派遣单位</th>
						<td>
							${rs.pqdwmc}
						</td>
					</tr>
					<tr>
						<th>发放时间</th>
						<td>
							${rs.ffsj}
						</td>
					</tr>
					<tr>
						<th>派遣证编号</th>
						<td>
							${rs.pqzbh}
						</td>
					</tr> 
					<tr>
						<th>取档人姓名</th>
						<td>
							${rs.qdrxm}
						</td>
					</tr> 
					<tr>
						<th>备注</th>
						<td>
							${rs.bz}
						</td>
					</tr>
				</tbody>
				</table>
			</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="11407" name="xxdm">
			<tr>
				<th>已经提交的资料</th>
				<td>
					<span class="red">${rs.ytjzlmc}</span> 
				</td>
			</tr>
			<tr>
				<th>未提交的资料</th>
				<td>
					<span class="red">${rs.wtjzlmc}</span>	
				</td>
			</tr>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn">
		           <button onclick="Close();return false;"
						style="width:80px" 
						id="buttonSave">
						确 定
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>	
				alert("操作成功！");
				Close();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>	
					alert("操作失败！");
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
	</body>
</html>

