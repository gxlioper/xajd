<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
			function checkMkList(text){
				var liArr = $('xmxslb').getElementsByTagName('li');
				
				for (var i = 0 ; i < liArr.length ; i++){
					if (liArr[i].id.indexOf(text)>=0){
						liArr[i].style.display="";
					} else {
						liArr[i].style.display="none";
					}
				}
			
			}
			
			
			//����ģ�����
			function setMkdm(mkdm,mkmc){
				$("gnmk").value=mkdm;
				$("mkmc").value=mkmc;
			}
			
			function checkGnmk(){
					var gnmk=$("gnmk").value;
					var mkmc=$("mkmc").value;
					allNotEmpThenGo("/xgxt/newsInfo.do?gnmk="+gnmk+"&mkmc="+mkmc+"&doType=query");
			}
			
			function setXzmk(){
				var mkmc=$("mkmc").value;
				if($(mkmc)){ 
					$(mkmc).style.background="#dce8f8";
				}
			}
			
		function getXm(){
			var text="";
			var num=document.getElementsByName("mkdm").length;
			var dm=document.getElementsByName("mkdm");
			var mc=document.getElementsByName("mkmcArr");
			for(i=0;i<num;i++){	
				text+="<li id=\""+mc[i].value+"\">&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" style='color:#0A63BF'  onclick='setMkdm(\""+dm[i].value+"\",\""+mc[i].value+"\");allNotEmpThenGo(\"/xgxt/newsInfo.do?gnmk="+dm[i].value+"&mkmc="+mc[i].value+"&doType=query\");return false;'>"+mc[i].value+"</a></li>";
			}
			document.getElementById('xmxslb').innerHTML = text;
				
		}
		</script>
	</head>
	<body>
		<%
		String userType=session.getAttribute("userType").toString();
		String width="";
		if("stu".equalsIgnoreCase(userType)){
			width="width:84%";
		}else {
			width="width:81.5%";
		}
		 %>
		<html:form action="/newsInfo" method="post">
			<input type="hidden" name="gnmk" id="gnmk" value="${gnmk}"/>
			<input type="hidden" name="mkmc" id="mkmc" value="${mkmc}"/>
			<input type="hidden" name="userType" id="userType" value="${userType}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
							<li>
								<a href="#"
									onclick="returnHomPage('');return false;"
									class="btn_fh">����</a>
							</li>
					</ul>
				</div>
			</div>
			<div class="leftframe04">
				<div class="menulist">
				   
					<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:450px;padding:0; overflow:auto;overflow-x:hidden;">
<%--							<p class="choose_select">--%>
<%--								<label>--%>
<%--									����--%>
<%--								</label>--%>
<%--								<input type="text" class="text_nor" style="width:80px" onkeyup="checkMkList(this.value)"/>--%>
<%--							</p>--%>
							<h3 style="margin-bottom:10px">
								<span class="title">����ģ���б�</span>
							</h3>
							<ul id="tsbjList">
									<logic:equal name="gnmk" value="">
										<li class="Current" id="" >
									</logic:equal>
									<logic:notEqual name="gnmk" value="">
										<li  id="" >
									</logic:notEqual>
									<a href="javascript:allNotEmpThenGo('/xgxt/newsInfo.do?doType=query')" class="Child">
										ȫ��
									</a>
								</li>
								<logic:iterate name="mkList" id="mk">
										<logic:equal name="gnmk" value="${mk.dm}">
											<li class="Current" id="${mk.dm}" >
										</logic:equal>
										<logic:notEqual name="gnmk" value="${mk.dm}">
											<li  id="${mk.dm}" >
										</logic:notEqual>
										<a href="javascript:allNotEmpThenGoForNews('/xgxt/newsInfo.do?gnmk=${mk.dm}&mkmc=${mk.mc}&doType=query')" 
										   style="float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;"
										   class="Child" >
											${mk.mc}
										</a>
									</li>
								</logic:iterate>
							</ul>
						</div>
				</div>
			</div>

		
			
				<div class="rightframe04" style="<%=width%>">
				<div class="searchtab" style="width:100%">
								<table width="100%" border="0">
									<tfoot>
										<tr>
											<td colspan="6">
												<div class="btn">
													<button class="btn_cx" id="search_go" onclick="checkGnmk()">
														�� ѯ
													</button>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
														�� ��
													</button>
												</div>
											</td>
										</tr>
									</tfoot>

									<tbody>
										<tr>
											<th>
												���ű���
											</th>
											<td>
												<html:text property="newsTitle" />
											</td>
										
											<th>
												������
											</th>
											<td>
												<html:text property="puber" />
											</td>
											<td colspan="2">
										</tr>
										<tr>	
											<th>
												����ʱ��
											</th>
											<td colspan="2">
												<html:text property="kssj"  styleId="kssj" 
													onclick="return showCalendar('kssj','y-mm-dd');" 
													 />--
												<html:text property="jssj"  styleId="jssj" 
													onclick="return showCalendar('jssj','y-mm-dd');" 
													 />
											</td>
											<td colspan="3">
										</tr>
									</tbody>
								</table>
							</div>
	
				
								<h3 class="datetitle_01">
									<span> ��ѯ���&nbsp;&nbsp;  </span>
								</h3>

							<div class="con_overlfow" style="width:100%">
								<table summary="" class="dateline" width="100%">
						<thead>
							<tr>
								<td  onclick="tableSort(this)">
									ģ������
								</td>
								<td  onclick="tableSort(this)">
									���ű���
								</td>
								<td  onclick="tableSort(this)">
									������
								</td>
								<td  onclick="tableSort(this)">
									����ʱ��
								</td>
							</tr>
						</thead>
						<tbody>
								<logic:notEmpty name="news">
									<logic:iterate name="news" id="n">
										<tr>
											<td>
												 ${n.mkmc }
											</td>
											<td>
												<a
													href="newsContent.do?newsId=${n.newsid }"
													target="_blank">
												${n.newstitle }
												</a>
											</td>
											<td>${n.fbr }</td>
											<td>${n.pubtime }</td>
										</tr>
									</logic:iterate>
									
									<%
											for (int i = 0; i < (Integer) request
												.getAttribute("maxNum")
												- ((List) request.getAttribute("news")).size(); i++) {
									%>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<%
									}
									%>
								</logic:notEmpty>
								<logic:empty name="news">
								<%
										for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>
							</logic:empty>
								
						</tbody>
					</table>
									<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
								</div>
								
								<script type="text/javascript">
									$('choose').className="hide";
									</script>
						<logic:present name="result">
							<logic:equal value="true" name="result">
								<script>
						alert('�����ɹ���');
					</script>
							</logic:equal>
							<logic:notEqual value="true" name="result">
								<script>
						alert('����ʧ�ܣ�');
					</script>
							</logic:notEqual>
						</logic:present>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
