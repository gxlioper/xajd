<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/newsInfoDWR.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
			function save(){
				
			
				var xssxArr=new Array();
				var checkTimes=new Array();
				var checkXssx=new Array();
				var checkPicId=new Array();
				
				if(document.getElementsByName("xssxArr").length<1){
					alert("没有需要保存的图片显示信息!");
					return false;
				}
				
				for(j=1;j<=5;j++){
					checkTimes[j]=0;
				}
				for(i=0;i<document.getElementsByName("xssxArr").length;i++){
					xssxArr[i]=document.getElementsByName("xssxArr")[i].value;
					if(xssxArr[i]!="none"){
					
						checkTimes[eval(xssxArr[i])]++;
					 	if(checkTimes[xssxArr[i]]>1){
							alert("不能有两个第"+xssxArr[i]+"张");
							return false;
						}
					}
					
				}
				
				var m=0;
				
				for(i=0;i<document.getElementsByName("xssxArr").length;i++){
					if(document.getElementsByName("xssxArr")[i].value!="none"){
						checkXssx[m]=document.getElementsByName("xssxArr")[i].value;
						checkPicId[m]=document.getElementsByName("picArr")[i].value;
						m++;
					}
				}
				dwr.engine.setAsync(false);
				newsInfoDWR.checkTpxssx(checkPicId,checkXssx,function(data){
					if(data){
						refreshForm("/xgxt/newsPigManage.do?method=newsPigManage&doType=save");
					}else{
						if(confirm("显示顺序重复是否要覆盖？")){
							refreshForm("/xgxt/newsPigManage.do?method=newsPigManage&doType=save");
						}else{
							return false;
						}
					}
				});
				dwr.engine.setAsync(true);
				
			}
			
			//删除新闻图片
			function del(){
			
				var primaryKey=document.getElementsByName("primary_key");
				var bool=false;
				for(i=0;i<primaryKey.length;i++){
					if(primaryKey[i].checked){
						bool=true;
					}
				}
				if(!bool){
					alert("请先勾选需要删除的新闻图片！");
					return false;
				}
				
				refreshForm("/xgxt/newsPigManage.do?method=newsPigManage&doType=del");
				
			}
			
		</script>
	</head>
	<body>

		<html:form action="/newsPigManage" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
						<p>
							图片新闻仅支持JPG格式。
						</p>
						<a class="close" title="隐藏"
						   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="save();return false;" class="btn_csh"> 保存 </a>
						</li>
						<li>
							<a href="#" onclick="del();return false;" class="btn_sc"> 删除 </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/newsPigManage.do?method=newsPigManage&doType=query')">
											查询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									发布时间
								</th>
								<td>
									<html:text property="kssj"  styleId="kssj" 
										onclick="return showCalendar('kssj','y-mm-dd');" 
										/>--
									<html:text property="jssj"  styleId="jssj" 
										onclick="return showCalendar('jssj','y-mm-dd');" 
										 />
								</td>
								<th>
									标题
								</th>
								<td>
									<html:text property="newsTitle" styleId="xm" />
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				
					
						<table summary="" id="rsTable" class="dateline" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td><input type="checkbox"/></td>
									<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
										
										<td id="<bean:write name="tit" property="en"/>" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:notEmpty name="rs">
								<%
										ArrayList<String[]> xwrs = (ArrayList<String[]>) request
										.getAttribute("xwrs");
										List<HashMap<String, String>> rs = (List<HashMap<String, String>>) request
										.getAttribute("rs");
										for (int i = 0; i < xwrs.size(); i++) {
										String[] xwrsArr = xwrs.get(i);
										int m = 0;
										for (int j = 0; j < rs.size(); j++) {
											HashMap<String, String> rsMap = rs.get(j);
											if (xwrsArr[0]
											.equalsIgnoreCase(rsMap.get("newsid"))) {
												if (xwrsArr[4].equalsIgnoreCase("1")) {
											out.println("<tr>");
											//checkBox(用于删除图片)
											out.println("<td>");
											out.println("<input type=\"checkbox\" name=\"primary_key\" value=\'"+rsMap.get("newsid")+"\'>");
											out.println("</td>");
											for (int n = 1; n < 4; n++) {
												out.println("<td>");
												out.println(xwrsArr[n]);
												out.println("</td>");
											}
											out.println("<td>");
											out.println("<img src=\"/xgxt/"+ rsMap.get("newscont")+ "\" height=\"100\" width=\"150\" >");
											out.println("</td>");
											out.println("<td>");
											%>
											<!-- 批量顺序保存时 图片ID -->
											<input type="hidden" name="picArr" id="picArr"
												value='<%=rsMap.get("picid")%>' />
												<html:select property="xssxArr" styleId="xssxArr"
													style="width:80px" value='<%=rsMap.get("xssx")%>'>
													<html:options collection="xssxList" property="en"
														labelProperty="cn" />
												</html:select>
												<%
											out.println("</td>");
											out.println("</tr>");
											} 
											else if (m == 1) {
											out.println("<tr>");
											out.println("<td>");
											out.println("<img src=\"/xgxt/"+ rsMap.get("newscont")+ "\" height=\"100\" width=\"150\" >");
											out.println("</td>");
											out.println("<td>");
											%>
											<!-- 批量顺序保存时 图片ID -->
											<input type="hidden" name="picArr" id="picArr"
												value='<%=rsMap.get("picid")%>' />
											<html:select property="xssxArr" styleId="xmlbdm"
												style="width:80px" value='<%=rsMap.get("xssx")%>'>
												<html:options collection="xssxList" property="en"
													labelProperty="cn" />
											</html:select>
											<%
											out.println("</td>");
											out.println("</tr>");
												} else {
											out.println("<tr>");
											out.println("<td rowspan='"+ xwrsArr[4] + "'>");
											out.println("<input type=\"checkbox\" name=\"primary_key\" value=\'"+rsMap.get("newsid")+"\'>");
											out.println("</td>");
											for (int n = 1; n < 4; n++) {
												out.println("<td rowspan='"
												+ xwrsArr[4] + "'>");
												out.println(xwrsArr[n]);
												out.println("</td>");
											}
											out.println("<td>");
											out.println("<img src=\"/xgxt/"+ rsMap.get("newscont")+ "\" height=\"100\" width=\"150\" >");
											out.println("</td>");
											out.println("<td>");
											%>
											<!-- 批量顺序保存时 图片ID -->
											<input type="hidden" name="picArr" id="picArr"
												value='<%=rsMap.get("picid")%>' />
											<html:select property="xssxArr" styleId="xssxArr"
												style="width:80px" value='<%=rsMap.get("xssx")%>'>
												<html:options collection="xssxList" property="en"
													labelProperty="cn" />
											</html:select>
											<%
											out.println("</td>");
											out.println("</tr>");
											m = 1;
												}
											}
										}
									}%>
									</logic:notEmpty>
									<%
									ArrayList list = ((ArrayList) request.getAttribute("rs"));
									int rsNum = 0;
									if (list != null) {
										rsNum = list.size();
									}
									int pageSize = (Integer) request.getAttribute("pageSize");
									for (int i = 0; i < (pageSize - rsNum); i++) {
									%>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled"/>&nbsp;
										</td>
										<logic:iterate id="tit" name="topTr">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
									</tr>
									<%
									}
									%>
							</tbody>
						</table>
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
<%--						<script type="text/javascript">--%>
<%--							$('choose').className="hide";--%>
<%--					</script>--%>
					</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:present name="result">
			<logic:equal name="result" value="true">
				<script>
								alert('操作成功!');
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
								</script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
								alert('操作失败!');
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
