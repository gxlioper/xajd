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
				/* width:1200px�� */
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
					<h2>�㽭ͬ�ÿƼ�ְҵѧԺѧ���ɼ����ܱ�</h2>
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
					</select> /<%= xn==null?"":xn %>ѧ��<%--  / <%= xqmc==null?"":xqmc %>  --%></h3>
					<font color="red">ע��1.ƽ��ѧ�ּ���=��ѧ��ȫ��ѧ�ּ���֮��/��ѧ������ѧ��֮��&nbsp;&nbsp;2.����Ϊƽ��ѧ�ּ�������</font>
				</div>
				
				<div class="e_body">
					<!-- ѭ���༶  -->
					<% 
						for(ClassSummary cs:classCollection){
							Map<String,XscjModel> stuMap = cs.getStuMap();	//�༶ѧ������
							Map<String,XskcModel> kcMap = cs.getKcMap();	//�༶�γ̼��ϣ�2��ѧ�ڣ�
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
									<th rowspan="2">ѧ��</th>
									<th rowspan="2">����</th>
									<%if(xqmc1!=null){ %>
										<th colspan="<%=size1 %>" ><%=xqmc1 %></th>
									<%} %>
									<%if(xqmc2!=null){ %>
										<th colspan="<%=size2 %>" ><%=xqmc2 %></th>
									<%} %>
									<th rowspan="2">ƽ����</th>
									<th rowspan="2">ƽ��ѧ�ּ���</th>
									<th rowspan="2">����</th>
								</tr>
								<tr>
									<!-- ѭ����Ŀ��ʽһ -->
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
								<!-- ѭ��ѧ�� -->
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
									<!-- ѭ����Ŀ�ɼ� -->
									<% 
										for(String xqKey:cjMap.keySet()){
											//��ȡ��Ӧѧ�ڵĳɼ����ϣ����ֻ��һ��ѧ�ڳɼ�map����һ����û�е�����
											//��ֱ̨�Ӵ���2��ѧ�ڵ�map
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
										���ι���ѯ�༶<font color="#227447"><%=classMap.size() %></font>����
										��ǰΪ��<font color="#227447"><%=cs.getBjmc()==null?"":cs.getBjmc() %></font>��
										�ð༶���β�ѯ��<font color="#227447"><%=stuMap.size() %></font>����¼��
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
				<h1>�������ݣ�</h1>
			<%	
				}
			%>
			
			
			
		</div>
	</body>
</html>
