<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="java.util.Map.Entry" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head_dollar.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/pjplsq.js"></script>
		<script type="text/javascript" src="<%=stylePath %>js/lockTableTitle.js"></script>
		<script type="text/javascript"> 
		var interVal;
		
		jQuery(document).ready(function () {
			//检测是否有未执行完的检测
			var bjdms = jQuery("#bjdms").val();
			jQuery.getJSON("bzjl_sqsh.do?method=getCheckProgress",{bjdms:bjdms},function(data){
				if (data["success"]=="false"){
					interVal = setInterval(getCheckProgress, 1000);
				}
			});
			//第一个参数：table的ID，第二个参数：要锁定的列数目，第三个参数：显示的宽度，第四个参数：显示的高度。注意table里面都必须为td，th的话会出现问题
			FixTable("MyTable", 4, 940,450);
			jQuery(".xl_select").hover(function(){
				jQuery(this).children(".jt").addClass("cur");
				jQuery(this).children("ul").show();
			},function(){
				jQuery(this).children(".jt").removeClass("cur");
				jQuery(this).children("ul").hide();
			})
			
			
			if(jQuery("#xxdm").val() == '10279'){
				var checkbox = jQuery("input[type='checkbox']:disabled");
				var aa = jQuery(checkbox).attr("index");				
				jQuery("td[name='fs"+aa+"']").show();
				jQuery("td[name='pm"+aa+"']").show();
			}
			
		});

		function reflushForm(){
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var bjdms = jQuery("#bjdms").val();
			refreshForm("bzjl_sqsh.do?method=toCheckPjsq&bjdms="+bjdms);
		}

		function getAllZcpmInfo(obj){
			var xh = jQuery(obj).text();
			var url = "bzjl_sqsh.do?method=viewAllZcpm&xh="+xh;
			var title = "综测排名汇总";
			showDialog(title,800,400,url)
		}
		function saveDgxmSq(obj){
			var url = "bzjl_sqsh.do?method=saveJxsq&type=submit&flag=dgtj";
			var xmdm = jQuery(obj).parent().find("[name='xmdm']").val();
			var xh = jQuery(obj).parent().find("[name='xh']").val();
			var para = {xmdms:xmdm,xh:xh};
			showConfirmDivLayer("您确定要提交该申请吗？",{"okFun":function(){
				jQuery.ajax({
					url : url,
					type : "post",
					dataType : "json",
					data:para,
					success : function(data) {
						if (data["message"] == "提交成功！") {
							showAlertDivLayer(data["message"], {}, {
								"clkFun" : function() {
									if (parent.window) {
										var tips = loading();	
										tips.show(true);
										reflushForm();
									}
								}
							});
						} else {
							showAlertDivLayer(data["message"]);
						}
					},
					contentType : "application/x-www-form-urlencoded;charset=utf-8"
					});
			}});
		}
		

		function refreshself(){
			var tips = loading();
			tips.show(true);
			jQuery('#search_go').click();
		}
		
		
		function checkConditions(){
			var bjdms = jQuery("#bjdms").val();
			jQuery.post("bzjl_sqsh.do?method=checkConditions",{bjdms:bjdms},function(data){
				loading("0%");
				interVal = setInterval(getCheckProgress, 1000);
			});
		}
		
		function getCheckProgress(){
			var bjdms = jQuery("#bjdms").val();
			jQuery.getJSON("bzjl_sqsh.do?method=getCheckProgress",{bjdms:bjdms},function(data){
				loading(data["message"]);
				if (data["success"]=="true"){
					window.clearInterval(interVal);
					jQuery('#search_go').click();
				}
			});
		}
		
		</script>
		
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优-我的评奖-奖项上报</a>
			</p>
		</div>
	
		<html:form action="/bzjl_sqsh" method="post" styleId="zcxmForm">
		
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="bjdms" styleId="bjdms"/>
			<input type="hidden" id="dgbkjd" value="${dgbkjd}"/>
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li id="li_qx">
							<a href="javascript:void(0);" onclick="javascript:refreshForm('pj_jxsq_new.do');return false;" class="btn_fh">返回</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="javascript:checkConditions();return false;" class="btn_sx">检验评奖条件</a>
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
		
		综测项目：
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList" indexId="i">
				<logic:equal value="N" name="z" property="fjdm">
					<logic:equal value="10279" name="xxdm">
						<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" onclick="choosezcxm(this,${i});" checked="checked"/> ${z.xmmc }
					</logic:equal>
					<logic:notEqual value="10279" name="xxdm">
						<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" checked="checked" disabled="disabled" name="zcxm"/> ${z.xmmc }
					</logic:notEqual>					
				</logic:equal>
				<logic:notEqual value="N" name="z" property="fjdm">
					<logic:equal value="10279" name="xxdm">
						<logic:equal value="平均绩点" property="xmmc" name="z">
							<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" index="${i }" checked="checked" disabled="disabled" /> ${z.xmmc }
						</logic:equal>
						<logic:notEqual value="平均绩点" property="xmmc" name="z">
							<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" index="${i }" onclick="choosezcxm(this,${i});" /> ${z.xmmc }
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual value="10279" name="xxdm">
						<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" index="${i }" onclick="choosezcxm(this,${i});" /> ${z.xmmc }
					</logic:notEqual>	
				</logic:notEqual>
			</logic:iterate>
		</logic:present> 
		<%--改，加入评奖项目一行，和综测项目一样--%>
		<br />
		评奖项目：
		<logic:present name="pjxmList">
			<logic:iterate id="z" name="pjxmList" indexId="i">
				<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" checked="checked"  name="pjxm" index="${i }" onclick="choosepjxm(this,${i});"/> ${z.xmmc }
			</logic:iterate>
		</logic:present>

		<table cellpadding="0"  id="MyTable" cellspacing="0" class="dateline nowrap">
			<thead>
				<tr>
					<% // 奖项上报是否显示符合
					boolean dgbkjd = "true".equals((String)request.getAttribute("dgbkjd"));
					List<HashMap<String,String>> engScoreList = (List<HashMap<String,String>>)request.getAttribute("engScoreList");%>
					<td width="39px">
						操作
					</td>
					<td width="83px">
						学号
					</td>
					<td width="49px">
						姓名
					</td>
					<%
					if(dgbkjd){%>
					<td width="49px">
						必修单科最低
					</td>
					<%}else{%>
						
					<%}
					if(null != engScoreList){
					%>
					<td width="25px">英语一</td>
					<td width="25px">英语二</td>
					<td width="25px">CET4</td>
					<td width="25px">CET6</td>
					<%
					}
					List<HashMap<String,String>> resultList = (List<HashMap<String,String>>)request.getAttribute("resultList");
					List<HashMap<String,String>> zcxmList = (List<HashMap<String,String>>)request.getAttribute("zcxmList");
					List<HashMap<String,String>> pjxmList = (List<HashMap<String,String>>)request.getAttribute("pjxmList");
					List<HashMap<String,String>> sqLists = (List<HashMap<String,String>>)request.getAttribute("sqLists");
					List<HashMap<String,String>> checkResultList = (List<HashMap<String,String>>)request.getAttribute("checkResultList");
					//ArrayList<HashMap<String, Object>> checklist = new ArrayList<HashMap<String, Object>>();
					
					List<HashMap<String,String>> bxkcjList = (List<HashMap<String,String>>)request.getAttribute("bxkcjList");
					
// 					if(dgbkjd){
// 						 checklist = (ArrayList<HashMap<String, Object>>)request.getAttribute("checklist");

// 					}
					%>
					
					<%
					for(int x = 0;x<zcxmList.size();x++){
						HashMap<String,String> e = (HashMap<String,String>)zcxmList.get(x);
						if(x==0){
							%>
							<td width = "65px" name='fs<%=x%>'>
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
						<td name="pjxmtd${j}">
							${y.xmmc}
						</td>
					</logic:iterate>
				</tr>
			</thead>
			<tbody>
				<%
					HashMap<String,String> xmrs=new HashMap<String,String>();
					int rs;
					for(int x = 0;x<resultList.size();x++){
						Map<String,String> e = (Map<String,String>)resultList.get(x);
// 						Map<String, Object>  check = new HashMap<String, Object>();
// 						if(dgbkjd){
// 							 check = checklist.get(x);
// 						}
						Map<String,String> checkInfo = new HashMap<String,String>();
						for (Map<String,String> map : checkResultList){
							if (e.get("xh").equals(map.get("xh"))){
								checkInfo.put(map.get("xmdm"),map.get("jcjg"));
							}
						}

						%>
					<tr>
						<td>
							<a onclick="showDialog('选择申请奖项',500,350,'bzjl_sqsh.do?method=plSelectPjxm&xh=<%=e.get("xh") %>');;"
									   href="javascript:void(0);">
									   <font color="blue"><u>上报</u></font>	
							</a>
						</td>
						<td>
						   <logic:equal name="xxdm"value="12865">
							  <a href="javascript:void(0);" onclick="getAllZcpmInfo(this)"><font color="blue"><%=e.get("xh") %></font></a>
						   </logic:equal>
						   <logic:notEqual name="xxdm"  value="12865">
						      <%=e.get("xh") %>
						   </logic:notEqual>
						</td>
						<td>
							<%=e.get("xm") %>
						</td>
							<%
					if(dgbkjd){
					String zdf = "";
					if(null != bxkcjList){
						for(int inx = 0;inx<bxkcjList.size();inx++){
							if(e.get("xh").equals(bxkcjList.get(inx).get("xh"))){
								zdf = bxkcjList.get(inx).get("zddkcj");
								break;
							}
						}
					}
					%>
					<td >
						<%=zdf %>
					</td>
					<%}else{%>
						
					<%}
						String eng1="";
						String eng2="";
						String cet4="";
						String cet6="";
						if(null != engScoreList){
							for(int iny = 0;iny<engScoreList.size();iny++){
								if(e.get("xh").equals(engScoreList.get(iny).get("xh"))){
									eng1 = engScoreList.get(iny).get("eng1");
									eng2 = engScoreList.get(iny).get("eng2");
									cet4 = engScoreList.get(iny).get("cet4");
									cet6 = engScoreList.get(iny).get("cet6");
									break;
								}
							}
						
					%>
					<td ><%=eng1%></td>
					<td ><%=eng2%></td>
					<td ><%=cet4%></td>
					<td ><%=cet6%></td>
						<%
						}
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
						
						
						<td style="padding:0px 20px;" name="pjxmtd<%=j%>">
							<% boolean checkflagg = true;
							for(int k = 0;k<sqLists.size();k++){
								HashMap<String,String> mp = (HashMap<String,String>)sqLists.get(k);
								if(e.get("xh").equals(mp.get("xh"))&&ep.get("xmdm").equals(mp.get("dqxmdm"))){
									checkflagg = false;
									if(mp.get("ff")!=null){
										if(mp.get("ff").equals("yes")){
										%>
										<div style="position:relative;z-index:1;height:20px;line-height:20px;">
											<a href="javascript:;" style="color:blue;float:left" title='<%=mp.get("gwmc") %>' onclick="qxsq('<%=mp.get("sqid") %>');"><%=ep.get("xmmc") %></a>
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
										
											<a href="javascript:;" style="color:#999999" title='<%=mp.get("gwmc") %>'><%=ep.get("xmmc") %><font color="red"><%=mp.get("sftz") %></font></a>
											
									<%}}else{%>

									<%	
									}
									
								}%>

							<%}%>
							<%if(checkflagg){
								
// 								  List<HashMap<String, String>> lsslist = (List<HashMap<String, String>>)check.get(ep.get("xmdm"));
								  
								  boolean checkflag = true;
								  
								  if (checkInfo.containsKey(ep.get("xmdm"))){
									  checkflag = Boolean.valueOf(checkInfo.get(ep.get("xmdm")));
								  }
								  
// 								  if(lsslist != null){
// 									  for(int ss=0;ss<lsslist.size();ss++){
									  
// 									     if(("false").equals(lsslist.get(ss).get("result"))){
// 										     checkflag = false;
// 										     break;
// 								         }
// 									  }
// 							      }
								  if(!checkflag){%>
									  <img src='images/ico_39.gif' name='faidImg' title='不符合条件'/>
								<%  }else{
									//符合条件人数统计
										if(xmrs.get(String.valueOf(j))==null){
											rs=0;
										}else{
											rs=Integer.parseInt(xmrs.get(String.valueOf(j)));
										}
										xmrs.put(String.valueOf(j),String.valueOf(++rs));
										%>
									   <a href="javascript:;" onclick="saveDgxmSq(this)" ><img src='images/ico_38.gif' title='符合条件' /></a>
									   <input type="hidden" name="xmdm" value="<%=ep.get("xmdm")%>" />
									   <input type="hidden" name="xh" value="<%=e.get("xh")%>" />
									   
								<%} 
							}
							%>
						</td>
						
						
						<%}%>
					</tr>	
				<%}%>	
			</tbody>
		</table>
		<%
			for(Entry<String,String> entry:xmrs.entrySet()){
				//符合条件人数统计
		%>		
			<input  type="hidden"  name ="xmrs" id="xmrs<%=entry.getKey()%>" value="<%=entry.getValue()%>" />
		<%		
			}
		%>
	</body>
</html>
