<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<jsp:directive.page import="java.util.ArrayList" />
<%@page import="java.util.HashMap"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		//��ʾ���ҳ��
		function showShJsp(doType){
			
			if(doType == "sh" && curr_row == null){		
				alert('��ѡ��Ҫ��˵����ݣ�');
				return false;
			}
			
			var sffj = $("sffj").value;
			var xh = curr_row.cells[1].innerText;
			var sqsj = "";
			if(sffj == "�ּ�"){
				sqsj = curr_row.cells[9].innerText;
			}else{
				sqsj = curr_row.cells[8].innerText;
			}
			
			var iskns = $('iskns').value;
			var url = "/xgxt/commXszz.do?method=xmshUpdate&iskns="+iskns;
				url+= "&xmdm="+$('xmdm').value;
				url+= "&doType="+doType;
				url+= "&xh="+xh;
				url+= "&sqsj="+sqsj;
			
			showOpenWindow(url,'800','600');
		}
		
		//�������
		function plshxm(){
		
			var checkBoxArr = document.getElementsByName("checkVal");
			var shpk = "";
			var flag = false;
			
			var blog=true;
			var jb="";
			jQuery("[name=primarykey_checkVal]:checked").each(function(i){
				
				if(i==0){
					jb=jQuery(this).parents("tr").children().eq(8).html();
				}
				
				if(jb!=jQuery(this).parents("tr").children().eq(8).html()){
					blog=false;
				}
			});
			
			
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){	
					shpk +=	checkBoxArr[i].value+"!!@@!!";	
					flag = true;
				}
			}
			
			
			
			var url = "/xgxt/commXszz.do?method=xmshPl";
			
			if(flag){
		
				var url = "/xgxt/commXszz.do?method=xmshUpdate";
					url+= "&xmdm="+$('xmdm').value;
					url+= "&doType=plsh";
//					url+= "&shpk="+shpk;
					
				if(blog){
					url+= "&jb="+jb;
				}
					
				showTopWin(url,'800','400');	
				
			}else{
				alert("�빴ѡҪ���������");
				return false;
			}
		}
		
		function setMklx(url){
			if($("mklx")){
				if($("mklx").value!=""){
					url+="&mklx="+$("mklx").value;
				}
			}
			allNotEmpThenGo(url);
		}
		
		function getXm(){
			var text="<span>";
			var xmdm=$("xmdm").value;
			var num=document.getElementsByName("shxmdm").length;
			if($("xmlb")){
				for(i=0;i<num;i++){	
					var dm=document.getElementsByName("shxmdm")[i].value;
					var mc=document.getElementsByName("shxmmc")[i].value;
					var lx=document.getElementsByName("shxmlb")[i].value;
					if($("xmlb").value==lx || $("xmlb").value==""){
						if(xmdm==dm){
							text+="<li id='"+lx+"' style='background:#dce8f8'>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  style='color:#0A63BF'  onclick='setMklx(\"/xgxt/commXszz.do?method=xmshManage&xmdm="+dm+"&widthType=${widthType}\");return false;'><span class='ico'></span>"+mc+"</a></li>";
						}else{
							text+="<li id='"+lx+"'>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"   style='color:#0A63BF' onclick='setMklx(\"/xgxt/commXszz.do?method=xmshManage&xmdm="+dm+"&widthType=${widthType}\");return false;'><span class='ico'></span>"+mc+"</a></li>";
						}
					}
				}
				text+="</span>"
				document.getElementById('xmxslb').innerHTML = text;
				
			}
		}
		
		function loadXmList(obj){
			var xmlb = jQuery(obj).val();
			if (xmlb != ''){
				jQuery('li[name='+xmlb+']',jQuery('#xmxslb')).attr('style','display:');
				jQuery('li[name!='+xmlb+']',jQuery('#xmxslb')).attr('style','display:none');
			} else {
				jQuery('li',jQuery('#xmxslb')).attr('style','display:');
			}
		}

		//jQuery(function(){
		//	jQuery("#CNLTreeMenu1").css("height",jQuery("#rightframe04").css("height"));
		//});
		jQuery(function(){
			changeWin();
		});
		
		function changeWin() {
			var left=parent.document.getElementById("left");
			var right=parent.document.getElementById("right");
			
			if (left && left.className == "hide") {
<%--				left.className = "hide";--%>
<%--				right.style.width = "100%";--%>
<%--				if (jQuery('div.rightframe04')){--%>
<%--					jQuery('div.rightframe04').attr('class','rightframe04_hidden');--%>
<%--				}--%>
<%--			} --%>
<%--			else {--%>
				
				right.style.width = "100%";
				
				if (jQuery(document).find('div.rightframe04')){
					jQuery(document).find('div.rightframe04').attr('class','rightframe04_hidden');
				}
			}
		}
		
		</script>
		<style type="css">
		.text-overflow {
			display: block; /*�����������*/
			width: 16em;
			word-break: keep-all; /* ������ */
			white-space: nowrap; /* ������ */
			overflow: hidden; /* ���ݳ������ʱ���س������ֵ����� */
			text-overflow: ellipsis;
			/* ���������ı����ʱ��ʾʡ�Ա��(...) ������overflow:hidden;һ��ʹ�á�*/
		}	
		</style>

	</head>

	<body onload="changeWin();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<%
					String widthType = request.getAttribute("widthType").toString();
					String width = "width:80%";
					if ("dbsx".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					} else if ("kjfs".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					}
			%>
			<!-- ������ -->
			<html:hidden property="iskns" styleId="iskns" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xmdm" styleId="xmdm" />
			<input type="hidden" id="sffj" name="sffj" value="${sffj }" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum}" />
			<input type="hidden" id="mklx" name="mklx" value="${mklx}" />
			<input type="hidden" id="widthType" name="widthType"
				value="${widthType}" />
			<!-- ������ end-->

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showShJsp('sh');return false;"
									class="btn_sh">�������</a>
							</li>
							<li>
								<a href="#" onclick="plshxm();return false;" class="btn_ck">�������</a>
							</li>
							<logic:equal name="widthType" value="dbsx">
								<li>
									<a href="#" onclick="returnHomPage('');return false;" class="btn_fh">����</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
			</div>
			<!-- �๦�ܲ����� -->
			<div class="leftframe04"  id="a11">
				<div class="menulist">
					<!--��start-->
					<div class="menutitle">
						<h3>
							<span class="title"> <logic:empty name="mklx">
					      	������Ŀ
						  </logic:empty> <logic:notEmpty name="mklx">
									<logic:equal name="mklx" value="zz">
						      	������Ŀ
						      </logic:equal>
									<logic:equal name="mklx" value="pj">
						      	������Ŀ
						      </logic:equal>
								</logic:notEmpty> </span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:420px; overflow-x:hidden;overflow-y:auto;">
							<p class="choose_select">
								<label>
									��Ŀ����
								</label>
								<html:select property="xmlb" style="width:80px" styleId="xmlb"
									onchange="loadXmList(this)">
									<html:option value=""></html:option>
									<html:options collection="xmlbList" property="en"
										labelProperty="cn" />
								</html:select>
							</p>
							<ul id="xmxslb">
								<logic:iterate id="xmsh" name="xmshList">
									<li name="${xmsh.xmlb}"
										class='<logic:equal value="${xmdm}" name="xmsh" property="xmdm">Current</logic:equal>'>
										<a href="#" class='Child' title="${xmsh.xmmc}"
											onclick="setMklx('/xgxt/commXszz.do?method=xmshManage&xmdm=${xmsh.xmdm}&widthType=${widthType}');return false"
											style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>${xmsh.xmmc}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>

					</div>
					<!--��end-->
				</div>
			</div>

			<div class="rightframe04" style="<%=width%>" id="rightframe04">
				<!-- ���� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<font color="blue">&nbsp;ѧ�꣺${xn
											}&nbsp;&nbsp;&nbsp;ѧ�ڣ�${xq
											}&nbsp;&nbsp;&nbsp;��ȣ�${nd}&nbsp;&nbsp;&nbsp;���ڣ�${sqsjCn}</font>
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('commXszz.do?method=xmshManage&widthType=${widthType}');return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('nj-xy-zy-bj-xh-xm');return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" style="width:150px" onmouseover=""
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									Ժϵ
								</th>
								<td>
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="xydm" />
										<html:select property="xydm" style="width:150px"
											onmouseover="" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- ��ѧԺ�û� end -->
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="false">
										<html:select property="xydm" style="width:150px"
											onmouseover="" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width:150px" onmouseover=""
										styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:150px" onmouseover=""
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="" maxlength="20" />
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20" />
								</td>
							</tr>
							<logic:equal name="sffj" value="�ּ�">
								<tr>
									<th>
										��Ŀ����
									</th>
									<td>
										<html:select property="xmzzjb" styleId="xmzzjb"
											style="width:150px" onmousemove="">
											<html:option value="">----��ѡ��----</html:option>
											<html:options collection="fjList" property="fjmc"
												labelProperty="fjmc" />
										</html:select>
									</td>
									<td colspan="4"></td>
								</tr>
							</logic:equal>
						</tbody>
					</table>
					<!-- �������� end-->
					<!-- ��ѯ���-->
				</div>


				<!-- �������� end-->
				<!-- ��ѯ���-->

				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" width="100%"
						id="rsTable">
						<!-- ��ͷ -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<!-- �ٵ�CHECKBOX ��ֹȫѡ������-->
									<input type="checkbox" name="jiade" value="all" onclick="" />
									<input type="checkbox" id="selall" name="selall"
										onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" length="7">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:equal name="sffj" value="�ּ�">
									<td onclick="tableSort(this)" nowrap>
										��Ŀ����
									</td>
								</logic:equal>
								<logic:iterate id="tit" name="topTr" offset="7" length="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->
						<!--���� -->
						<tbody>
							<logic:notEmpty name="rsList">
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="showShJsp('view')">
										<td align="center">
											<input type="checkbox" id="checkVal"
												name="primarykey_checkVal" value="${rs.pk }" />
										</td>
										<td align="center">
											${rs.xh }
											<input type="hidden" id="xh${index }" value="${rs.xh }" />
										</td>
										<td align="center" title="${rs.xm }">
											${rs.showxm}
										</td>
										<td align="center">
											${rs.xb }
										</td>
										<td align="center">
											${rs.nj }
										</td>
										<td align="center" title="${rs.xymc }">
											${rs.showxy}
										</td>
										<td align="center" title="${rs.zymc }">
											${rs.showzy}
										</td>
										<td align="center" title="${rs.bjmc }">
											${rs.showbj}
										</td>
										<logic:equal name="sffj" value="�ּ�">
											<td align="center">
												${rs.xmzzjb }
											</td>
										</logic:equal>
										<td align="center">
											${rs.sqsj }
											<input type="hidden" id="sqsj${index }" value="${rs.sqsj }" />
										</td>
										<td align="center">
											${rs.shzt1 }${rs.shzt2 }${rs.shzt3 }
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!--���� end-->
							<%
									ArrayList<HashMap<String,String>> list = (ArrayList<HashMap<String,String>>)(request.getAttribute("rsList"));
									int rsNum = 0;
									if (list != null) {
										rsNum = list.size();
									}
									int pageSize = (Integer) request.getAttribute("pageSize");
									for (int i = 0; i < (pageSize - rsNum); i++) {
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0" length="8">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
								<logic:equal name="sffj" value="�ּ�">
									<td>
										&nbsp;
									</td>
								</logic:equal>
								<logic:iterate id="tit" name="topTr" offset="7" length="2">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<%
							}
							%>

						</tbody>
						<!--���� end -->
					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
				<!--��ҳend-->
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
			</div>
		</html:form>
		<div id="tmpdiv1"></div>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>
