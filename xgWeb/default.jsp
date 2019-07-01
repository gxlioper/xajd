<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.comm.xtwh.CommXtwhService" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<jsp:directive.page import="xgxt.action.Base" />
		<jsp:directive.page import="xgxt.DAO.DAO" />
		<jsp:directive.page import="xgxt.action.CommanAction" />
		<jsp:directive.page import="xgxt.xtwh.xtwhOther.XtwhOtherService" />
		<script language="javascript" src="js/message.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language=javascript src="/xgxt/dwr/interface/getBaseData.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/getLxckDcls.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script language=javascript
			src="/xgxt/js/webServiceFunction/wsFunction.js"></script>
		<script language="javascript" src="js/main.js"></script>
		<script type="text/javascript">
			function logout(){
				if(confirm('ȷ��Ҫ�˳���')){
					//wjcfDataExport("/xgxt/chkLogout.do");
					window.location='/xgxt/chkLogout.do';
					return true;
				}
			return false;
		}
		</script>
	</head>
	<body onload="singleLogin()" id="mainbody" onselectstart="return false"
		ondragstart="return false" onbeforecopy="return false"
		oncopy="document.selection.empty()"
		onselect="document.selection.empty()">
		<html:form action="fdyUserChange" method="post">
		<input type="hidden" name="openType" id="openType" value="${openType}" />
		<input type="hidden" name="userName" id="userName" value="${userName}" />
		<input type="hidden" name="userDep" id="userDep" value="${userDep}" />
		<input type="hidden" name="fdyQx" id="fdyQx" value="${fdyQx}" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="jsName" id="jsName" value="${userOnLine}" />
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="urls" id="urls" value="/default.jsp" />
		<input type="hidden" name="showShgcKnsTs" id="showShgcKnsTs"
			value="${showShgcKnsTs }" />
		<%--����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
		<logic:equal value="128720" name="xxdm">
			<input type="hidden" name="fbsj" id="fbsj"
				value="<bean:write name="message" property="fbsj"/>" />
			<input type="hidden" name="fbnr" id="fbnr"
				value="<bean:write name="message" property="fbnr"/>" />
		</logic:equal>

		<div class="mainbody type_mainbody">
			<div class="topframe">
				<div class="head">
					<div class="logo">
						<h2 class="float">
							<img src="<%=stylePath%>logo/logo_school.png" />
							<img src="<%=stylePath%>logo/logo_xg.png" />
						</h2>
					</div>
					  <!--�汾ѡ��-->
					 <logic:equal value="true" name="isFdyUser">
               		 <div class="versions"  onmouseover="javascript:document.getElementById('versions').style.display='block'" onmouseout="javascript:document.getElementById('versions').style.display='none'">
		                <a class="versions_btn" href="#"  >
		                	<logic:equal name="fdyQx" value="true">
		                		����Ա
		                	</logic:equal>
		                	<logic:equal name="fdyQx" value="false">
		                		�Ǹ���Ա
		                	</logic:equal>
		                </a>
		                <div class="versionslist" id="versions" style="display:none;">
										<%--��ǰ�Ǹ���Ա�û�--%>
										<logic:equal value="true" name="fdyQx">
											<a href="#" title="" class="passw" 
												onclick="fdyUserChange()">�Ǹ���Ա</a>
										</logic:equal>
										<%--end��ǰ�Ǹ���Ա�û�--%>
										<%--��ǰ���Ǹ���Ա�û�--%>
										<logic:equal value="false" name="fdyQx"  >
											<a href="#" title="" class="passw"
												onclick="fdyUserChange()">����Ա</a>
										</logic:equal>
										<%--��ǰ���Ǹ���Ա�û�--%>
							
		                </div>
		            </div>
		           </logic:equal>
		            
					<div class="info">
						<div class="welcome">
							<div class="tool">
							<!-- ����¼�	onmouseover="javascript:document.getElementById('downmenu').style.display='block'"
								onmouseout="javascript:document.getElementById('downmenu').style.display='none'"--> 
								
								
								<!-- ����¼� onclick="showhid('downmenu');"-->
								<a class="tool_btn" href="#" >${userNameReal  
									}</a>
								<!-- ��ҵ <div class="downmenu" id="downmenu" style="display:none;">
									<a href="#" class="passw">�޸�����</a><a href="#">��������</a>
								</div> --> 
							</div>

							<span> <script language="javaScript">
<!--
now = new Date(),hour = now.getHours()
if(hour < 6){document.write("�賿�ã�")}
else if (hour < 9){document.write("���Ϻã�")}
else if (hour < 12){document.write("����ã�")}
else if (hour < 14){document.write("����ã�")}
else if (hour < 17){document.write("����ã�")}
else if (hour < 19){document.write("����ã�")}
else if (hour < 22){document.write("���Ϻã�")}
else {document.write("���Ϻã�")}
// -->
</script>
							</span><!-- <a href="#" class="news">[����Ϣ(0)]</a> ��ҵ��-->
							<a href="#" title="ע��" class="logout"
								onclick="logout();"></a>
						</div>
					</div>
				</div>
				<div class="menu" id="nav">
					<div class="nav">
						<ul>
							<%
										String xstsType = null;
										try {
										String userName = (String)session.getAttribute("userName");
										String userType = (String)session.getAttribute("userOnLine");
										String xxdm = (String)session.getAttribute("xxdm");
										String yhm = userName;
										String sql = "";
										DAO dao = DAO.getInstance();
										if (userType.equalsIgnoreCase("teacher")) {
											System.out.println("enter");
											sql = "select gnmkdm,gnmkmc from view_yhqx where yhm=? and length(gnmkdm)=3 order by to_number(xssx)";
											CommanAction ca = new CommanAction();
											String[] returnRs = ca.getUsrSQL(sql, userName, xxdm, true,
											Boolean.parseBoolean(session.getAttribute("isFdy")
											.toString()));
											ca = null;
											sql = returnRs[0];
											userName = returnRs[1];
										} else if (userType.equalsIgnoreCase("student")) {
											XtwhOtherService xtwhService = new XtwhOtherService();
											String [] returnMes = xtwhService.getTopMenuForStu(Base.xxdm, userName);
											sql = returnMes[0];
											xstsType = returnMes[1];
											userName = "6727";
										}

										String[] inputValue = { userName };
										String[] outputValue = { "gnmkdm", "gnmkmc" };
										String menuListTop = dao.getStringToSplit(sql, inputValue,
										outputValue);
										String[] printOut = menuListTop.split("!!SplitSignOne!!");
										
											out.print(" <li id=\"li-" + 0
											+ "\"><a href=\"default.jsp\"'>");
											out.print("��ҳ");
											out.println("</a></li>");
										for (int i = 1; i < printOut.length; i++) {
											String[] gnmkcf = printOut[i].split("!!SplitSignTwo!!");
											out.print(" <li id=\"li-" + i
											+ "\"><a href=\"main1.jsp?act=left&menuTop=");
											out.print(gnmkcf[1]);
											out.print("\"  title='");
											out.print(gnmkcf[2]);
											out.print("' id='");
											out.print(gnmkcf[1]);
											out.print("' onclick='menuUrl(\"" + gnmkcf[1] + "\");setTsxx(this.id);'>");
											out.print(gnmkcf[2]);
											out.println("</a></li>");
										}
										
										//=============2010.11.9 edit by luojw======================
										//��ʾ��䣨С���ȣ�
										String tsyj =dao.getOneValue("xtwh_syszb", "tsyj", "1", "1");
										if (!Base.isNull(tsyj)) {
											int size = tsyj.length();
											int max = 150;
											if (size < max) {
												for (int i = 0; i < max; i++) {
													tsyj += "&nbsp;";
												}
											}
										}
										session.setAttribute("tsyj", tsyj);
										// ��ݷ�ʽ
										CommXtwhService xtwhService = new CommXtwhService();
										List<HashMap<String, String>> kjfsList = xtwhService
												.getKjfsMenuList(yhm);
										session.setAttribute("kjfsList", kjfsList);
		
									} catch (Exception e) {
										e.printStackTrace();
									}
								%>

						</ul>
					</div>
					<div class="morediv"
						onmousemove="javascript:document.getElementById('morelist').style.display='block'"
						onmouseout="javascript:document.getElementById('morelist').style.display='none'">
						<p class="more"></p>
						<div class="morelist" id="morelist" style="display:none;">
							<ul id="dropDownUl">
							</ul>
						</div>
					</div>
				</div>
				<input type="hidden" name="xstsType" id="xstsType"
					value="<%=xstsType%>" />
				<div class="mainframe">
					<div class="leftframe">
						<div class="piclink_01">
							<h3>
								<span class="title">���ù���</span>
								<img src="<%=stylePath%>images/blue/ico_help.gif" width="14"
									height="14" class="help"
									onmouseover="helpcon.style.display='block'"
									onmouseout="helpcon.style.display='none'" />
								<p class="titlecon" id="helpcon" style="display:none;">
									�ṩ���ٽ���ҳ��Ŀ�ݷ�ʽ���㡰
									<img src="<%=stylePath%>images/blue/ico_linkmore.gif" />
									�����Զ��峣�ù��ܣ��㡰
									<img src="<%=stylePath%>images/blue/ico_linkup.gif" />
									<img src="<%=stylePath%>images/blue/ico_linkdown.gif" />
									���ɽ������ơ����ơ�
								</p>
							</h3>

							<ul>
								<logic:iterate name="kjfsList" id="kjfs">
									<li>
										<a href="commXtwh.do?method=yzyhqx&path='${kjfs.gnmk }'" target="framecon"> <img
												src="<%=stylePath%>${kjfs.picpath }" /> <span>${kjfs.mkms
												}</span> </a>
									</li>
								</logic:iterate>
							</ul>
							<%--      			<ul>--%>
							<%--        			<li><a href="stu_info_query.do?method=stuInfo" target="framecon"><img src="<%=stylePath%>images/blue/54/Function37.png"/><span>ѧ����Ϣ��ѯ</span></a></li>--%>
							<%--        			<li><a href="data_search.do?act=discipInfo" target="framecon"><img src="<%=stylePath%>images/blue/54/Function43.png"/><span>Υ�ʹ��ֲ�ѯ</span></a></li>--%>
							<%--        			<li><a href="prise_result.do" target="framecon"><img src="<%=stylePath%>images/blue/54/Function48.png"/><span>�������Ų�ѯ</span></a></li>--%>
							<%--        			<li><a href="main1.jsp?act=left&menuTop=N31"><img src="<%=stylePath%>images/blue/54/Function79.png"/><span>ѧ����</span></a></li>--%>
							<%--        			<li><a href="jygl_jyxycx.do" target="framecon"><img src="<%=stylePath%>images/blue/54/Function54.png"/><span>��ҵЭ���ѯ</span></a></li>--%>
							<%--        			<li><a href="dtjs_zsdy.do" target="framecon"><img src="<%=stylePath%>images/blue/54/Function74.png"/><span>��Ա��Ϣ��ѯ</span></a></li>--%>
							<%--      			</ul>--%>
							<div class="functionbut">
								<a class="up"></a>
								<a class="down"></a>
								<a class="add" title="��ӳ��ù���"
									onclick="showTopWin('commXtwh.do?method=kjfsUpdate','600','480')"></a>
								<%--					<a class="add" title="��ӳ��ù���" href="demo_index_05.html"></a>--%>
							</div>
						</div>

					</div>
					<div class="rightframe" name="framecon">
						<iframe name="framecon" class="framecon" allowTransparency="true"
							src="mainBody.jsp" width="100%" frameborder="0" marginwidth="0"
							marginheight="0"
							onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>500?fdh:500)"
							scrolling="no">
						</iframe>

					</div>
					<div class="botframe">
						<div class="copy">
							<span>&copy;1999-2010 <a href="http://www.zfsoft.com"
								target="_blank">������������ɷ����޹�˾</a> <span>��Ȩ����</span>&nbsp;&nbsp;��ϵ�绰��0571-89902828</span>
						</div>
					</div>
				</div>
			</div>

			<script type="text/javascript">
		
			window.onunload = function () {
			var xxdm = document.getElementById("xxdm").value;
			var userType = document.getElementById("userType").value;
			if (xxdm == "12865") {
				refreshForm("chkLogout.do");
				return;
			}
		};
		var showShgcKnsTs = document.getElementById("showShgcKnsTs").value;
		if (showShgcKnsTs == "show") {
			window.onload = shgcKnsShow();
		}

		var xstsTs = document.getElementById('xstsType').value;
		if (xstsTs=="1"){
			window.onload = XstsShow();
		}

		function changeLogo(str) {
			var xxdm = document.getElementById("xxdm").value;
			if (xxdm == "11417") {
				if (str == "N16") {
					document.getElementById("xsgz").src = "images/system_gygl.gif";
				} else {
					document.getElementById("xsgz").src = "images/system_xsgz.gif";
				}
			}
		}
			
			//�㽭��ý<bean:message key="lable.xsgzyxpzxy" />�û�Υ�ʹ�������
		if (document.getElementById("xxdm").value == "11647" && (document.getElementById("userType").value == "xy" || document.getElementById("fdyQx").value == "true")) {
			zjcmwjcfMsg();
		}
			
			//�й����ʴ�ѧ��ѧ����
		if (document.getElementById("xxdm").value == "10491" && document.getElementById("userType").value != "stu") {
			zgdzdxFxMsg();
		}
		if (document.getElementById("xxdm").value == "1049701" && document.getElementById("userType").value.trim() == "stu") {
			getXjydInfo.isWjcfJcsj($("userName").value, function (data) {
				if (null != data && "" != data) {
					var msg1 = new class_message("", 200, 120, "\u6d88\u606f\u63d0\u793a\uff1a", "\u60a8\u7684\u7559\u6821\u67e5\u770b\u5373\u5c06\u5230\u671f,\u8bf7\u7533\u8bf7\u89e3\u9664!", "\u67e5\u770b\u8be6\u7ec6", "true", "/xgxt/wjcfLxck.do");
					msg1.rect(null, null, null, screen.height - 50);
					msg1.speed = 60;
					msg1.step = 10;
					msg1.show();
				}
			});
		}
		</script>


			<script language="javascript" src="js/prototype-1.6.0.3.js"></script>
			<script type="text/javascript">
		var sumWidth = 0;
		var splitPositionNo = 0;
		var liDropDownArr = new Array();
		  for(var j=1;;j++){
		     if($('li-'+j)){
		         sumWidth += $('li-'+j).getWidth();
		       
		         if(sumWidth > $('nav').getWidth()-180){//ȷ���ֽ�li���
		         $("morelist").style.display = '';
		         splitPositionNo = j-1;
		            for(var k=j-1;;k++){
		               if($('li-'+k)){//ȷ��
		                  liDropDownArr.push($('li-'+k));//����li����
		                  $('li-'+k).remove();//ɾ��ԭ����li����
		               } else {
		                 break;
		               }
		            }
		         }
		     } else{
		        break;
		     }
		  }
		  
		  function fdyUserChange(){
				var url='/xgxt/fdyUserChange.do';
				document.forms[0].action = url;
				document.forms[0].submit();
		  }
		  
		  if(liDropDownArr.length>0){
		  	 for(var p=0;p<liDropDownArr.length;p++){
		      $('dropDownUl').insert(liDropDownArr[p]);
		     }
		     $('morelist').style.display="none";
		  } 
			
		  function menuUrl(url){
				$("urls").value="/xgxt/initMenu.do?act=left&menuTop="+url;
			}
		</script>
	</body>
	</html:form>
</html>
