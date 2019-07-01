<%@page import="java.util.Iterator"%>
<%@page import="com.zfsoft.xgxt.xpjpy.wdpj.sqsh.XskcModel"%>
<%@page import="java.util.Set"%>
<%@page import="com.zfsoft.xgxt.xpjpy.wdpj.sqsh.XscjModel"%>
<%@page import="java.util.Collection"%>
<%@page import="com.zfsoft.xgxt.xpjpy.wdpj.sqsh.ClassSummary"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- <script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/xscjhzShow.js"></script> -->
		
		<%
			Map<String,ClassSummary> classMap = (Map<String,ClassSummary>)request.getAttribute("classMap");
			String xn = null;
			String xqmc = null;
			Collection<ClassSummary> classCollection = classMap.values();
			
		%>
		
		<style type="text/css">
			body{
				/* overflow-x: hidden;
				overflow-y: auto; */
			}
			div.container{
				text-align: center;
				margin: 0 auto;
				padding:0 10px 10px 10px;
			}
			div.e_head{
				/* width:100%; */
				background-color: #e8f0fb;
				padding:5px;
			}
			div.e_body{
				margin:0;
				min-height: 520px;
				/* overflow-x:auto;
				overflow-y:hidden; */
				/* width:1200px； */
			}
			table{
				/* display: none; */
				margin: 0 auto;
				min-width: 1200px;
			}
			
			th,td{
				border:1px solid black;
				height: 20px;
				/* width: 60px; */
			}
			td{
				width:100px;
			}
			h1{
				margin:20px 0;
				color:black;
				font-size: 20px;
			}
			h2,h3{
				margin:5px 0;
				color:black;
			}
			h2{
				font-size: 20px;
			}
			h3{
				font-size: 15px;
			}
			tfoot tr th{
				text-align: left;
				height: 30px;
				border: 1px solid #e6e6e6;
				background-color: #e6e6e6;
				padding:5px;
			}
			table tbody tr:nth-child(odd) {
				 background-color: #efefef;
			}
			table tbody tr:nth-child(even) { 
				background-color: #ffffff; 
			}
			table tbody tr:hover { 
				background-color: #f8e2c2; 
			}
			
			td .content{
				width:80px;
			}
			
		</style>
		
		<script type="text/javascript">
			var tableChange = function(){
				var selectVal = jQuery("#tableSelect").val();
				jQuery("table").hide();
				jQuery("table."+selectVal).show();
				
			}
		
			jQuery(function(){
				/* jQuery("#tableSelect").change = tableChange; */
				tableChange();
			});
			
		</script>
	</head>
	<body>
		<div class="container">
			<% 
				if(classMap!=null&&classMap.size()!=0){
			%>
				<div class="e_head">
					<h2>浙江同济科技职业学院学生成绩汇总表</h2>
					<h3><select id="tableSelect" onchange="tableChange()">
						<% 
							for(ClassSummary cs:classCollection){
								xn = cs.getXn();
								xqmc = cs.getXqmc();
						%>
							<option value="<%= cs.getBjdm()==null?"":cs.getBjdm() %>"><%= cs.getBjmc()==null?"":cs.getBjmc() %></option>
						<% 
							} 
						%>
					</select> /<%= xn==null?"":xn %>学年<%--  / <%= xqmc==null?"":xqmc %>  --%></h3>
					<font color="red">注：1.平均学分绩点=该学年全部学分绩点之和/该学年所修学分之和&nbsp;&nbsp;2.排名为平均学分绩点排名</font>
				</div>
				
				<div class="e_body">
					<!-- 循环班级  -->
					<% 
						for(ClassSummary cs:classCollection){
							Map<String,XscjModel> stuMap = cs.getStuMap();	//班级学生集合
							Map<String,XskcModel> kcMap = cs.getKcMap();	//班级课程集合（2个学期）
							String bjdm = cs.getBjdm();
							int totalSize = 0;
							int size1 = 0;
							int size2 = 0;
							String xqmc1 = null;
							String xqmc2 = null;
							
							Iterator<XskcModel> iterator = kcMap.values().iterator();
							if(iterator.hasNext()){
								XskcModel xkm = iterator.next();
								size1 = xkm.getKcSet().size();
								xqmc1 = xkm.getXqmc();
							}
							if(iterator.hasNext()){
								XskcModel xkm = iterator.next();
								size2 = xkm.getKcSet().size();
								xqmc2 = xkm.getXqmc();
							}
							
					%>
						<table class="<%=bjdm==null?"":bjdm%>">
							<thead>
								<tr>
									<th rowspan="2">学号</th>
									<th rowspan="2">姓名</th>
									<%if(xqmc1!=null){ %>
										<th colspan="<%=size1 %>" ><%=xqmc1 %></th>
									<%} %>
									<%if(xqmc2!=null){ %>
										<th colspan="<%=size2 %>" ><%=xqmc2 %></th>
									<%} %>
									<th rowspan="2">平均分</th>
									<th rowspan="2">平均学分绩点</th>
									<th rowspan="2">排名</th>
								</tr>
								<tr>
									<!-- 循环科目方式一 -->
									<% 
										for(String kcKey: kcMap.keySet()){
											XskcModel xskcModel = kcMap.get(kcKey);
											for(String kcmc:xskcModel.getKcSet()){
									%>
											<th><%=kcmc==null?"":kcmc %></th>
									<% 
											}
										}
									%>
								</tr>
							</thead>
							<tbody>
								<!-- 循环学生 -->
								<% 
									Collection<XscjModel> xscjCollec = stuMap.values();
									for(XscjModel model:xscjCollec){
										Map<String,Map<String,String[]>> cjMap = model.getCjMap();
										String pjcj = model.getPjcj();
										String pjjd = model.getPjjd();
										String pjcjpm = model.getPjcjpm();
								%>
								<tr>
									<td ><div class="content"><%=model.getXh()==null?"":model.getXh() %></div></td>
									<td ><div class="content"><%=model.getXm()==null?"":model.getXm() %></div></td>
									<%-- <td width="100px"><%=cs.getXn()==null?"":cs.getXn() %></td> --%>
									<!-- 循环科目成绩 -->
									<% 
										for(String xqKey:cjMap.keySet()){
											//获取对应学期的成绩集合，如果只有一个学期成绩map，或一个都没有的问题
											//后台直接创建2个学期的map
											Map<String,String[]> cj = cjMap.get(xqKey);
											//for(XskcModel xskcModel:kcMap.values()){
											XskcModel xskcModel = kcMap.get(xqKey);
											if (xskcModel != null) {
												for(String kcmc:xskcModel.getKcSet()){
													String[] c = cj.get(kcmc);
													String cv = null;
													String jv = null;
													if(c!=null&&c.length!=0){
														cv = c[0];
														jv = c[1];
													}
									%>
												<td ><div class="content"><%=cv==null?"":cv %><%=jv==null?"":"/"+jv %></div></td>
									<% 
											}
										  }
										}
									%>
									<td ><div class="content"><%=pjcj==null?"":pjcj %></div></td>
									<td ><div class="content"><%=pjjd==null?"":pjjd %></div></td>
									<td ><div class="content"><%=pjcjpm==null?"":pjcjpm %></div></td>
								</tr>
								<% 
										}
								%>
							</tbody>
							<tfoot>
								<tr>
									<th colspan="<%=size1+size2+5%>">
										本次共查询班级<font color="#227447"><%=classMap.size() %></font>个，
										当前为：<font color="#227447"><%=cs.getBjmc()==null?"":cs.getBjmc() %></font>，
										该班级本次查询到<font color="#227447"><%=stuMap.size() %></font>条记录。
									</th>
								</tr>
							</tfoot>
						</table>
					<% 
						}
					%>
					
				</div>
			<% 
				}else{
			%>	
				<h1>查无数据！</h1>
			<%	
				}
			%>
			
			
			
		</div>
	</body>
</html>
