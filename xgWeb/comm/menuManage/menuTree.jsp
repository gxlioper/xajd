<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/menuManage.js'></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function getGnmkInfo(gnmkdm){
				dwr.engine.setAsync(false);
					menuManage.getGnmkInfo(gnmkdm,function(data){
						$('cdjb').value = data.cdjb;
						$('gnmkdm').value = data.gnmkdm;
						$('gnmkmc').value = data.gnmkmc;
						$('xssx').value = null == data.xssx ? "" : data.xssx;
						$('picpath').src = stylePath + "/images/blue/54/" + data.picpath;
						$('gnbz').value = null == data.gnbz ? "" : data.gnbz;
						$('dyym').value = null == data.dyym ? "" : data.dyym;
						$('path').value = null == data.picpath ? "" : data.picpath;
					});
				dwr.engine.setAsync(true);
			}
			
			function getChildNode(gnmkdm,gnmkmc){
				var html = "";
				if (0 == $(gnmkdm).getElementsByTagName('ul').length){
					if (gnmkdm.length == 5){
						$(gnmkdm).onclick = null;
					}
					html+=$(gnmkdm).innerHTML;
					html+="<ul>";
					dwr.engine.setAsync(false);
					menuManage.getChildNode(gnmkdm,"是",function(data){
						if (null != data){
							for (var i = 0 ; i < data.length ; i++){
								if (gnmkdm.length==5){
									html+="<li class=\"Child\" id=\"";
									html+=data[i].gnmkdm;
									html+="\">";
									html+="<img src=\"<%=stylePath%>standard/s.gif\" class=\"s\"/>";
								} else {
									html+="<li class=\"Closed\"  id=\"";
									html+=data[i].gnmkdm;
									html+="\"";
									///if (data[i].gnmkdm.length==5){
										html+=" onclick=\"getChildNode('";
										html+=data[i].gnmkdm;
										html+="','";
										html+=data[i].gnmkmc;
										html+="')\"";
									///}
									html+=">";
									html+="<img src=\"<%=stylePath%>standard/s.gif\" class=\"s\" onclick=\"ExCls(this,'Opened','Closed',1);\" />"
								}
								
								html+="<a href=\"#\" onclick=\"getGnmkInfo('";
								html+=data[i].gnmkdm;
								html+="')\">"
								html+=data[i].gnmkmc;
								html+="</a>";
								html+="</li>";
							}
						}
					});
					dwr.engine.setAsync(true);
					html+="</ul>";
					$(gnmkdm).innerHTML=html;
				}
			}
			
			
			
			function checkCdjb(){
				if (3 == $('cdjb').value){
					showTopWin('/xgxt/menuManage.do?method=setShortcutPic',600,400)
				} else {
					alert("非3级菜单不能维护快捷方式！");
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a onmouseover="">${title }</a>
			</p>
		</div>
		<html:form action="/menuManage" method="post">
			<table width="100%">
				<tr>
					<td windth="18%">
						<div class="menulist">
							<!--层start-->
							<div class="menutitle">
								<h3>
									<span class="title">功能菜单</span>
										<%--<a id="AllOpen_1" href="#"
										onclick="MyCNLTreeMenu1.SetNodes(0);Hd(this);Sw('AllClose_1');">展开<span
										class="arrow_up"></span> </a><a id="AllClose_1" href="#"
										onclick="MyCNLTreeMenu1.SetNodes(1);Hd(this);Sw('AllOpen_1');"
										style="display:none;">收起<span class="arrow_down"></span> 
										</a>
								--%></h3>
								<!--CNLTreeMenu Start:-->
								<div class="CNLTreeMenu1" id="CNLTreeMenu1">
									<ul>
										<logic:iterate id="y" name="yjGnmkList">
											<!--一级子类-->
											<li class="Closed" id="${y.gnmkdm }" onclick="getChildNode('${y.gnmkdm}','${y.gnmkmc }',true)">
												<a href="#" onclick="getGnmkInfo('${y.gnmkdm }','${y.gnmkmc }');">${y.gnmkmc }</a>
											</li>
										</logic:iterate>
									</ul>
								</div>
							</div>
							<!--层end-->
						</div>
					</td>
					<td valign="top" width="82%">
						<div class="tab">
							<table width="100%" border="0" class="formlist">
								<tfoot>
									<tr>
										<td colspan="2">
											<div class="bz">
												"
												<span class="red">*</span>"为必填项
											</div>
											<div class="btn">
												<logic:notEqual name="doType" value="view">
													<button type="button" name="提交"
														onclick="saveUpdate('/xgxt/menuManage.do?method=menuTree&doType=save','save_gnmkmc')">
														保存
													</button>
												</logic:notEqual>
											</div>
										</td>
									</tr>
								</tfoot>
								<tbody>
									<tr>
										<th>
											菜单级别
										</th>
										<td>
											<html:text property="cdjb" styleId="cdjb" readonly="true"></html:text>
											
										</td>
									</tr>
									<tr>
										<th>
											<font color="red">*</font>菜单名称
										</th>
										<td>
											<html:text property="save_gnmkmc" maxlength="40" styleId="gnmkmc"></html:text>
											<html:hidden property="save_gnmkdm" styleId="gnmkdm"/>
											<input type="hidden" id="dyym" name="dyym"/>
											
										</td>
									</tr>
									<tr>
										<th>
											显示顺序
										</th>
										<td>
											<html:text property="save_xssx" styleId="xssx"
												maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
										</td>
									</tr>
									<tr>	
										<th>
											快捷方式图片
										</th>
										<td>
											<html:hidden property="path" styleId="path"/>
											<img src="<%=stylePath %>/images/blue/54/${picpath }" style="width:54px;height:54px" id="picpath"/>
											<br/><br/>
											<button type="button" class="btn_01" onclick="checkCdjb();">
												浏览
											</button>
										</td>
									</tr>
									<tr>
										<th>
											功能描述
										</th>
										<td style="word-break:break-all;">
											<html:textarea property="save_gnbz" style="width:98%" rows="5" styleId="gnbz" readonly="true"></html:textarea>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
			</script>
		</logic:present>
		<script type="text/javascript" defer>
			<!--
			
			var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");
			MyCNLTreeMenu1.InitCss("Opened","Closed","Child","<%=stylePath%>standard/s.gif");
			
			setTimeout(function(){
				var gnmkdm = $('gnmkdm').value;
				if (null != gnmkdm){
					var parent = gnmkdm.substr(0,gnmkdm.length-2);
					var superParent = parent.substr(0,parent.length-2);
					
					if ($(superParent)){
						$(superParent).click();
						$(superParent).className = "Opened";
					}
					if ($(parent)){
						$(parent).click();
						$(parent).className = "Opened";
					}
				}
			},100);
			-->
		</script>
	</body>
</html>
