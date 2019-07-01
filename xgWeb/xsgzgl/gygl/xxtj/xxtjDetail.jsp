<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xsgzgl.gygl.xxtj.LdModel"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="xsgzgl.gygl.xxtj.QsModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="xgxt.utils.String.StringUtils"%>
<%@page import="xsgzgl.comm.BasicService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>

		<script type="text/javascript">
			var x;
			var y;
		
			 function showInfo(data){
				var xxdm = jQuery("#xxdm").val();
				if($("infoConv")){
					delDiv();
				}
				if(arguments.length == 0){
					return false;
				}
				var infoConv = document.createElement("DIV");
				infoConv.id = "infoConv";
				var dd_html = "<table border='1' width='800px'>";
				if("13798" == xxdm) {
					dd_html += "<tr><th>床位号</th><th>是否保留</th><th>是否入住</th><th>年级</th><th>学院</th><th>专业</th><th>班级</th><th>学号</th><th>姓名</th><th>民族</th><th>手机号码</th></tr>"	
				}else {
					dd_html += "<tr><th>床位号</th><th>是否保留</th><th>是否入住</th><th>学院</th><th>专业</th><th>班级</th><th>学号</th><th>姓名</th><th>民族</th><th>手机号码</th></tr>"
				}
				
			
				for(var i=0; i<data.length; i++){
					var cwh = data[i].cwh == null ? "" : data[i].cwh;
					var xh = data[i].xh == null ? "" : data[i].xh;
					var xm = data[i].xm == null ? "" : data[i].xm;
					var sfbl = data[i].sfbl == null ? "" : data[i].sfbl;
					var mzmc = data[i].mzmc == null ? "" : data[i].mzmc;
					var sjhm = data[i].sjhm == null ? "" : data[i].sjhm;
					var xymc = data[i].xymc == null ? "" : data[i].xymc;
					var zymc = data[i].zymc == null ? "" : data[i].zymc;
					var bjmc = data[i].bjmc == null ? "" : data[i].bjmc;
					var nj = data[i].nj == null ? "" : data[i].nj;
					
					
					var sfrz = xh == "" ? "未入住" : "已入住";
					
					dd_html += "<tr align='center'><td>" + cwh + "</td>"
					dd_html += "<td>" + sfbl + "</td>"
					dd_html += "<td>" + sfrz + "</td>"
					if("13798" == xxdm) {
						dd_html += "<td>" + nj + "</td>"
					}
					dd_html += "<td>" + xymc + "</td>"
					dd_html += "<td>" + zymc + "</td>"
					dd_html += "<td>" + bjmc + "</td>"
					dd_html += "<td>" + xh + "</td>"
					dd_html += "<td>" + xm + "</td>"
					dd_html += "<td>" + mzmc + "</td>"
					dd_html += "<td>" + sjhm + "</td></tr>"
				}
				
				dd_html += "</table>";
				
				infoConv.innerHTML = dd_html;
				infoConv.style.cssText = "border:1px solid #DDD891;background-color:#FEF5DA;position:absolute;z-index:100;filter:alpha(opacity=80);";
				infoConv.style.left = (x + 10) + "px";
				infoConv.style.top = (y + 10) + "px";
				document.body.appendChild(infoConv);
			}
			
			 function showHzInfo(data){
			 	if($("infoConv")){
					delDiv();
				}
				if(arguments.length == 0){
					return false;
				}
				var infoConv = document.createElement("DIV");
				infoConv.id = "infoConv";
				var dd_html = "<table border='0' width='300px'>";
				dd_html += "<tr><th>床位号</th><th>是否保留</th><th>是否分配</th><th>年级</th><th><bean:message key="lable.xsgzyxpzxy" /></th></tr>"
				for(var i=0; i<data.length; i++){
					var cwh = data[i].cwh == null ? "" : data[i].cwh;
					var nj = data[i].nj == null ? "" : data[i].nj;
					var xymc = data[i].xymc == null ? "" : data[i].xymc;
					var sfbl = data[i].sfbl == null ? "" : data[i].sfbl;
					
					var sffp = xymc == "" ? "未分配" : "已分配";
					
					dd_html += "<tr align='center'><td>" + cwh + "</td>"
					dd_html += "<td>" + sfbl + "</td>"
					dd_html += "<td>" + sffp + "</td>"
					dd_html += "<td>" + nj + "</td>"
					dd_html += "<td>" + xymc + "</td></tr>"
				}
				
				dd_html += "</table>";
				
				infoConv.innerHTML = dd_html;
				infoConv.style.cssText = "border:1px solid #DDD891;background-color:#FEF5DA;position:absolute;z-index:100;filter:alpha(opacity=80);";
				infoConv.style.left = (x + 10) + "px";
				infoConv.style.top = (y + 10) + "px";
				document.body.appendChild(infoConv);
			 }
			 
			
			function loadCw(qsh,type,evt,id){
				evt = evt?evt:(window.event?window.event:null);
				var obj = {};
				obj.lddm = jQuery('#lddm').val();
				obj.qsh = qsh;
				var lastTd = jQuery("#"+id).parent().parent("tr").find("td").eq(-1).attr("id");
				var lastTr = "tr_"+id.split("_")[1];
				if("cwxx"==type){
			    	x = (evt.x?evt.x:evt.pageX) + getScroll().l-100;
				    }
			    else{
			    	x = (evt.x?evt.x:evt.pageX) + getScroll().l-160; 
			    }
				
			    y = (evt.y?evt.y:evt.pageY) + getScroll().t;
			    if(lastTd==jQuery("#"+id).parents("td").attr("id")){
				    if("cwxx"==type){
				    	x = (evt.x?evt.x:evt.pageX) + getScroll().l-500;
					    }
				    else{
			    	x = (evt.x?evt.x:evt.pageX) + getScroll().l-300;  
				    }
			    }
			    if(lastTr==jQuery("#qsssxx").children().children(".chTr").eq(-1).attr("id")){
			    	y = (evt.y?evt.y:evt.pageY) + getScroll().t-100;  
			    }
			    if(lastTr==jQuery("#qsssxx").children().children(".chTr").eq(-2).attr("id")){
			    	y = (evt.y?evt.y:evt.pageY) + getScroll().t-100;  
			    }
			    jQuery.ajaxSetup({
					 contentType:"application/x-www-form-urlencoded;charset=utf-8"
				});
				jQuery.post('gyglnew_xxtj.do?method=xxtjForQs',obj,function(data){
					if(data["message"]=="false"){
						showAlertDivLayer("请选择所带学生寝室！");
						return false;
						}
					if(type == "cwxx"){
						showInfo(data);
					}else if(type == "hzxx"){
						showHzInfo(data);
					}
				},"json");
			}
			
			function delDiv(){
				if($("infoConv")){
					document.body.removeChild($("infoConv"));
				}
			}
			
			function hidden(){
				jQuery('')
			}
		</script>
	</head>
	<body>
		<div class="prompt">
	       <h3><span>提示：</span></h3>
	       <p>点击相应的寝室号可以查看详细的床位入住信息或者床位分配信息，寝室分配详细信息表中，若寝室号后跟随（混合）说明该寝室的床位有分配给其他<bean:message key="lable.xsgzyxpzxy" />。<br/></p>
	   	</div>
		<!-- From内容 start-->
		<form action="gyglnew_xxtj.do?method=xxtjDetail" method="post">
		<input type="hidden" id="xxdm" value="${xxdm }" />
		<input type="hidden" id="lddm" value="${ldModel.lddm }"/>
		<!---------------------表格----无数据显示----------------->
		<div class="formbox">
			<%
				LdModel model = (LdModel)request.getAttribute("ldModel");
				Map<Integer, List<QsModel>> csMap = model.getCsMap();
				BasicService service = new BasicService();
			%>
			<table class="table_01" width="98%">
				<tr>
					<td align="center" colspan="2">
						<font size="5" color="blue">${ldModel.ldmc }</font>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">					
						<h3 class="datetitle_01">
							<span>寝室住宿状态信息：
								<font style="background:#FFAEB9">&nbsp;满员&nbsp;</font>
								<font style="background:#C0FF3E">&nbsp;缺额&nbsp;</font>
								<font style="background:#FFFFFF">&nbsp;全空&nbsp;</font> 
							</span>
						</h3>
					</td>
				</tr>
				<%
		//			String[] colors = new String[]{"green","cyan","gray","yellow","yellow","pink","orange","blue"};
					String[] colors = new String[]{"#B4EEB4","#EEE685","#C6E2FF","#9ACD32","#FFB5C5","#1E90FF",
							"#FF7F50","#32CD32","#DDA0DD","#FFC125","#63B8FF","#CD3700","#C1CDC1","#6DB16A","#B2ECF9","#0E8DD6",
							"#8BD348","#F6E78F","#CBD9E2","#BA5C95","#FFD929","#ADADAD"};
					int count = 0;
					Map<String, String> colorMap = new HashMap<String, String>(); 
					
					for(Map.Entry<Integer,List<QsModel>> entry : csMap.entrySet()){
						//Integer cs = (Integer)entry.getKey();
						//String chmc = String.valueOf(cs);
						List<QsModel> qsList = entry.getValue();
						
						String chmc = String.valueOf(qsList.get(0).getCh());
						char szf = (chmc.charAt(0) == '-') ? 'B' : chmc.charAt(0);
						
						chmc = szf + chmc.substring(1);
				%>
				<tr>
					<th width="10%">
						第<%=chmc %>层
					</th>
					<td>
						<table>
						<%
							int index = 0;
							for(int i=0; i<qsList.size(); i++){ 
								QsModel qsModel = qsList.get(i);
								
								if(!colorMap.containsKey(qsModel.getXymc()) && !StringUtils.isNull(qsModel.getXymc())){
									colorMap.put(qsModel.getXymc(), colors[count++]);
								}
						%>
							<% 
								if(i%10 == 0){
							%>
							<tr id="tr_<%=chmc %>_<%=i %>" align="left" valign="top">
							<%
								}
							%>
								<td id="td_<%=chmc %>_<%=i %>" style="width: 118px;height: 20px;background-color:<%=qsModel.getColor() %>">
									<a id="a_<%=chmc %>_<%=i %>" href="#" onmouseover="loadCw('<%=service.replaceHtml(qsModel.getQsh()) %>','cwxx',event,'a_<%=chmc %>_<%=i %>');return false;" onmouseout="delDiv()">
										<font color="<%=qsModel.getQsColor() %>">
											<%=service.replaceHtml(qsModel.getQsh()) %>
											(<%=service.replaceHtml(qsModel.getQsxb()) %><%=service.replaceHtml(qsModel.getCws()) %>)
										</font>
									</a>
								</td>
								
								<%if(i==qsList.size()-1){
									while(i<9){
										i++;
								%>
									<td style="width: 118px;height: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<%}} %>
								
							<%
								if(i%10 == 9){
							%>	
							</tr>
							<%
								}
							%>

						<%
								index++;
							} 
						%>
						</table>
					</td>
				</tr>
				<%} %>
			</table>
		</div>
		
		<div class="formbox">
			<!--标题start-->
			<!--标题end-->
			<table id="qsssxx" width="98%" class="table_01">
				<tr>
					<td align="center" colspan="2">					
						<h3 class="datetitle_01">
							<span>寝室所属详细信息 ：
								<%for(Map.Entry<String,String> colorEntry : colorMap.entrySet()){  %>
								<font style="background:<%=colorEntry.getValue() %>">&nbsp;<%=colorEntry.getKey() %>&nbsp;</font><%} %>	
								<font style="background:#FFFFFF">&nbsp;未分配&nbsp;</font>
								<font style="color:red">&nbsp;含保留床位的寝室&nbsp;</font>
							</span>
								<div class="buttonbox">
									<a href="#" class="btn_dqwz" ><font style="color: black"><bean:message key="lable.xsgzyxpzxy" />混合住宿寝室</font></a>
								</div>
						</h3>
					</td>
				</tr>
				<%
					for(Map.Entry<Integer,List<QsModel>> entry : csMap.entrySet()){
						//Integer cs = (Integer)entry.getKey();
						//String chmc = String.valueOf(cs);
						List<QsModel> qsList = entry.getValue();
						
						String chmc = String.valueOf(qsList.get(0).getCh());
						char szf = (chmc.charAt(0) == '-') ? 'B' : chmc.charAt(0);
						
						chmc = szf + chmc.substring(1);
				%>
				<tr class="chTr" id="tr_<%=chmc %>">
					<th width="10%">
						第<%=chmc %>层
					</th>
					<td>
						<table>
						<%
							int index = 0;
							for(int i=0; i<qsList.size(); i++){ 
								QsModel qsModel = qsList.get(i);
						%>
							<% 
								if(i%10 == 0){
							%>
							<tr id="tr_<%=chmc%>_<%=i%>" align="left" valign="top">
							<%
								}
							%>
								<td id="td_<%=chmc%>_<%=i%>" style="width: 118px;height: 20px;background-color:<%=colorMap.get(qsModel.getXymc()) %> 
<%--									<%--%>
<%--										if("是".equalsIgnoreCase(qsModel.getSfbl())){--%>
<%--									%>--%>
<%--										red--%>
<%--									<%--%>
<%--										}else{--%>
<%--									%>--%>
<%--										<%=colorMap.get(qsModel.getXymc()) %>--%>
<%--									<%--%>
<%--										}--%>
<%--									%>	--%>
									">
									
									<div class="buttonbox">
									<%
										if("是".equalsIgnoreCase(qsModel.getSfhz())){
									%>
										<a id="a_<%=chmc%>_<%=i%>" href="#" class="btn_dqwz"
									<%
										}else{
									%>
										<a href="#"
									<%
										}
									%>	
										onmouseover="loadCw('<%=service.replaceHtml(qsModel.getQsh()) %>','hzxx',event,'a_<%=chmc %>_<%=i %>');return false;" onmouseout="delDiv()">
									<%
										if("是".equalsIgnoreCase(qsModel.getSfbl())){
									%>
										<font color="red">
									<%
										}else{
									%>
										<font color="black">
									<%
										}
									%>
											<%=service.replaceHtml(qsModel.getQsh()) %>																						
										</font>
									</a>
									</div>
								</td>
								<%if(i==qsList.size()-1){
									while(i<9){
										i++;
								%>
									<td style="width: 118px;height: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<%}} %>
							<%
								if(i%10 == 9){
							%>	
							</tr>
							<%
								}
							%>

						<%
								index++;
							} 
						%>
						</table>
					</td>
				</tr>
				<%} %>
			</table>
		</div>
		</form>
	</body>
</html>
