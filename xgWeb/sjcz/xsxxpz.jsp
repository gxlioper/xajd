<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
</head>
<body>
	<html:form action="xsxxgl.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>显示模块选择</span>
					</th>
				</tr>
			</thead>
		
			<tbody>
				<logic:iterate id="rs" name="pages" indexId="index">
					<%if(index%2 == 0 ){
					%>
						<tr>
					<%} %>
					<td>
						<input type="hidden" name="ename" value="${rs.ename }"/>
						<input type="hidden" name="cname" value="${rs.cname }"/>
						<input type="hidden" name="xssx" value="${rs.xssx }"/>
						<b>${rs.cname }</b> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						显示<input type="radio" name="${rs.ename }" value="1" 
							<logic:equal name="rs" value="1" property="sfxs">checked="checked"</logic:equal> 
						/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						不显示<input type="radio" name="${rs.ename }" value="0"
							<logic:equal name="rs" value="0" property="sfxs">checked="checked"</logic:equal>
						/>
					</td>
					
					<%if(index%2 == 1){
					%>
						</tr>
					<%} %>
				</logic:iterate>
				<%if(((List)request.getAttribute("pages")).size()%2 != 0 && ((List)request.getAttribute("pages")).size() > 0){%>
						<td></td>
						</tr>
					<%} %>
			</tbody>
			
	

			<tfoot>
		      <tr>
		        <td colspan="6">
		          <div class="btn">
		          		<button type="button" name="提交" onclick="refreshForm('xsxxgl.do?method=xsxxpz&doType=save');">提 交</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html>
