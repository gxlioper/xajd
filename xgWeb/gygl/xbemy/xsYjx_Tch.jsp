<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>学生意见箱</a>
			</p>
		</div>
		<!-- 标题 end-->
		<FORM action="/XsgyglDispatch.do?method=xsYjx" method="post">
			<button class="button2" style="display: none;height:0px"
				id="search_go"
				onclick="refreshForm('/xgxt/XsgyglDispatch.do?method=xsYjx')"></button>
			<!-- 页签 end-->
			<div class="toolbox">
			<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 意见列表&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr>
								<td onclick="tableSort(this)" align="center">
									标题
								</td>
								<td onclick="tableSort(this)" align="center">
									发表人
								</td>
								<td onclick="tableSort(this)" align="center">
									发表时间
								</td>
								<td onclick="tableSort(this)" align="center">
									有无回复
								</td>
								<td align="center">
									操作
								</td>
							</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate id="list" name="rs">
							<tr onmouseover="rowOnClick(this)">
								<td>
									<a
										href="/xgxt/XsgyglDispatch.do?method=viewYjXx&id=<bean:write name="list" property="id"/>"
										target=_black><bean:write name="list" property="title" />
									</a>
								</td>
								<td>
									<bean:write name="list" property="xh" />
								</td>
								<td>
									<bean:write name="list" property="pubdate" />
								</td>
								<td>
									<bean:write name="list" property="ywhf" />
								</td>
								<logic:equal value="no" name="writeAble">
								<td></td>
								</logic:equal>
								<logic:equal value="yes" name="writeAble">
									<td>
								<logic:equal value="无" name="list" property="ywhf">
									<a href="#"onclick="showOpenWindow('/xgxt/XsgyglDispatch.do?method=yjHf&act=add&id=<bean:write name="list" property="id"/>',800,500)">
												回复</a>/
       							</logic:equal>
								<logic:equal value="有" name="list" property="ywhf">
									<a href="#"onclick="showOpenWindow('/xgxt/XsgyglDispatch.do?method=yjHf&act=modi&id=<bean:write name="list" property="id"/>',800,500)">
												修改回复</a>/
       							</logic:equal>
									<a href="#" onclick="if(confirm('确实要删除该条意见吗？')){location='/xgxt/XsgyglDispatch.do?method=xsYjx&doType=del&id=<bean:write name="list" property="id"/>';}">删除</a>
									</td>
								</logic:equal>
							</tr>
						</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
		</FORM>
	</body>
</html>
<logic:equal value="no" name="writeAble">
<script language="javascript" >
alert("你只有读权限！");
</script>
</logic:equal>
