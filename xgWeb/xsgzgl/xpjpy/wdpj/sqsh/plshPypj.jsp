<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head_dollar.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/pjplsh.js"></script>
		<script type="text/javascript" src="<%=stylePath %>js/lockTableTitle.js"></script>
		<script type="text/javascript"> 
		jQuery(document).ready(function () { 
			//第一个参数：table的ID，第二个参数：要锁定的列数目，第三个参数：显示的宽度，第四个参数：显示的高度。注意table里面都必须为td，th的话会出现问题
			FixTable("MyTable", 4, 940,450);
			jQuery(".xl_select").hover(function(){
				jQuery(this).children(".jt").addClass("cur");
				jQuery(this).children("ul").show();
			},function(){
				jQuery(this).children(".jt").removeClass("cur");
				jQuery(this).children("ul").hide();
			})
				
		});

		function reflushForm(){
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var bjdms = jQuery("#bjdms").val();
			refreshForm("xpj_sqsh.do?method=toCheckPjpy&xn="+xn+"&xq="+xq+"&bjdms="+bjdms);
		}
		</script>
		
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优-我的评奖-奖项批量审核</a>
			</p>
		</div>
	
		<html:form action="/xpj_sqsh" method="post" styleId="zcxmForm">
		
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="bjdms" styleId="bjdms"/>
			
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="plshtg();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">批量审核通过</a>
						</li>
						<li id="li_qx">
							<a href="javascript:void(0);" onclick="javascript:refreshForm('pj_jxplsh.do');return false;" class="btn_fh">返回</a>
						</li>
					</ul>
				</div>
<%--				<div class="searchtab">--%>
<%--					<table width="100%" border="0">--%>
<%--						<tbody>--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									学号--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:text property="xh" styleId="xh"/>--%>
<%--								</td>--%>
<%--								<td colspan="2">--%>
									<div style="display:none">
										<button type="button" class="btn_cx" id="search_go" onclick="reflushForm();">
											查 询
										</button>
<%--										&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">--%>
<%--											重 置--%>
<%--										</button>--%>
									</div>
<%--								</TD>--%>
<%--							</TR>--%>
<%--						</TBODY>--%>
<%--					</TABLE>--%>
<%--				</DIV>--%>
<%--			</DIV>--%>
		</html:form>
		
		
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList" indexId="i">
				<logic:equal value="N" name="z" property="fjdm">
					<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" checked="checked" disabled="disabled" name="zcxm"/> ${z.xmmc }
				</logic:equal>
				<logic:notEqual value="N" name="z" property="fjdm">
					<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" index="${i }" onclick="choosezcxm(this,${i});" /> ${z.xmmc }
				</logic:notEqual>
			</logic:iterate>
		</logic:present>

		<table cellpadding="0"  id="MyTable" cellspacing="0" class="dateline nowrap">
			<thead>
				<tr>
					<td>
						学号
					</td>
					<td>
						姓名
					</td>
					<td>
						班级
					</td>
					<%
					List<HashMap<String,String>> resultList = (List<HashMap<String,String>>)request.getAttribute("resultList");
					List<HashMap<String,String>> zcxmList = (List<HashMap<String,String>>)request.getAttribute("zcxmList");
					List<HashMap<String,String>> pjxmList = (List<HashMap<String,String>>)request.getAttribute("pjxmList");
					List<HashMap<String,String>> shLists = (List<HashMap<String,String>>)request.getAttribute("shLists");
					%>
					
					
					<%
					for(int x = 0;x<zcxmList.size();x++){
						HashMap<String,String> e = (HashMap<String,String>)zcxmList.get(x);
						if(x==0){
							%>
							<td name='fs<%=x%>'>
								<%=e.get("xmmc") %>
							</td>
							<td name='pm<%=x%>'>
								排名
							</td>
							
						<%}else{%>
						
							<td name='fs<%=x%>' style="display:none">
								<%=e.get("xmmc") %>
							</td>
							<td name='pm<%=x%>' style="display:none">
								排名
							</td>
							
						<%}%>
					<%}%>
					
					<logic:iterate id="y" name="pjxmList" indexId="j">
						<td>
							${y.xmmc}
							<br>
							${y.rsksfsmc}[${y.zzme}]
						</td>
					</logic:iterate>
				</tr>
			</thead>
			<tbody>
				<%
					
					for(int x = 0;x<resultList.size();x++){
						HashMap<String,String> e = (HashMap<String,String>)resultList.get(x);
						
						%>
					<tr>
						<td>
							<%=e.get("xh") %>
						</td>
						<td>
							<%=e.get("xm") %>
						</td>
						<td>
							<%=e.get("bjmc") %>
						</td>
						
						<%
							for(int i = 0;i<zcxmList.size();i++){
								HashMap<String,String> em = (HashMap<String,String>)zcxmList.get(i);
								if(i==0){
									%>
									<td name='fs<%=i%>'>
										<%=e.get("fs"+i) %>
									</td>
									<td name='pm<%=i%>'>
										<%=e.get("pm"+i) %>
									</td>
									
									
								<%}else{%>
									
									<td name='fs<%=i%>' style="display:none">
										<%=e.get("fs"+i) %>
									</td>
									<td name='pm<%=i%>' style="display:none">
										<%=e.get("pm"+i) %>
									</td>
								<%}%>
												
						
						<%}%>
						
						
						<%
							for(int j = 0;j<pjxmList.size();j++){
								HashMap<String,String> ep = (HashMap<String,String>)pjxmList.get(j);
						%>
						
						
						<td style="padding:0px 20px;">
							<%
							for(int k = 0;k<shLists.size();k++){
								HashMap<String,String> mp = (HashMap<String,String>)shLists.get(k);
								if(e.get("xh").equals(mp.get("xh"))&&ep.get("xmdm").equals(mp.get("xmdm"))){
									if(mp.get("ff")!=null){
										if(mp.get("ff").equals("yes")){
										%>
										<div style="position:relative;z-index:1;height:20px;line-height:20px;">
											<a href="javascript:;" style="color:blue;float:left" title='<%=mp.get("gwmc") %>' onclick="tocheck('<%=mp.get("sqid") %>','<%=mp.get("shid") %>','<%=mp.get("gwid") %>');"><%=ep.get("xmmc") %></a>
<%--											<div style="position:absolute;z-index:2;right:-20px;top:11px;">--%>
<%--												<div class="xl_select">--%>
<%--											    	<a href="javascript:;" class="jt"></a>--%>
<%--											        <ul style="background:#fff;padding:0px;">--%>
<%--											        	<li><a href="#" onclick="">审核</a></li>--%>
<%--											        </ul>--%>
<%--											    </div>--%>
<%--											</div>--%>
										</div>
										<%}else if(mp.get("ff").equals("no")){%>
										
											<a href="javascript:;" style="color:#999999" title='<%=mp.get("gwmc") %>'><%=ep.get("xmmc") %></a>
											
											
									<%}}else{%>
										
									<%	
									}
									
								}
								
							%>
						
						
							<%}%>
						</td>
						
						
						<%}%>
					</tr>	
				<%}%>
				
				
			</tbody>
		</table>
		
	</body>
</html>
