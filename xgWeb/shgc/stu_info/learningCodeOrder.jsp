<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getRule.js'></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
			<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript">		
				function initPageData(){
				var totalNum=0;
				getRule.getLearningCodeOrderRule(function(data){		
				for(var i=0;i<data.length;i++){
				if(data[i].zdmc==""){
					
				}else{
					//字段名称
					document.getElementById("zdmc"+parseInt(i+1)).value=data[i].zdmc;			
					document.getElementById("px"+parseInt(i+1)).value=data[i].px;
				}
				}
			});	
		}
	</script>	
	</head>
	<body onload="initPageData();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
			<html:form action="/arrangeClass.do" method="post">
			<input type="hidden" id="num" name="num" value="<bean:write name="zcxNum"/>"/>
				<div class="tab_cur" id="jd">
					<p class="location" id="title_m">
						<em>您的当前位置:</em><a>
						分班编学号 - 基本设置 - 生成学号排序顺序设置
						</a>
					</p>
				</div>
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="6"><span>生成学号排序顺序</span></th>
	        			</tr>
	   				 </thead>		
					<tbody>
					<%
						for(int i=1;i<11;i++){
						
					 %>
					<tr>
					<th align="right">排序信息<%=i %></th>
					<td>
						<select id="zdmc<%=i %>" name="zdmc<%=i %>">
						<option value=""></option>
						<logic:iterate id="v" name="zcxList">
							<option value="${v.zcxdm}">${v.zcxmc}</option>
						</logic:iterate>
						</select>
					</td>
					
					<td align="right">排序方法<%=i %></td>
					<td>
						<select id="px<%=i %>" name = "px<%=i %>">
							<option value=""></option>
							<option value="asc">升序</option>
							<option value="desc">降序</option>
						</select> 
					</td>
					</tr>	
					<% }%>	
					</tbody>	
					<tfoot>
						<tr>
						<td colspan="4">
						<div class="btn">
						<button onclick="refreshForm('arrangeClass.do?method=saveLearningCodeOrderRule');">
								 保 存
						</button>
						<button onclick="Close();return false;">
								关 闭
						</button>
						</div>
						</td>
						</tr>
					</tfoot>									
					</table>
					</div>
					<logic:notEmpty name="result">
						<logic:equal value="true" name="result">
							<script>
								alert("操作成功！");					
							</script>
						</logic:equal>
						<logic:equal name="result" value="false">
							<script>
								alert("操作失败!");
							</script>
						</logic:equal>
					</logic:notEmpty>					
			</html:form>			
	</body>
</html>
