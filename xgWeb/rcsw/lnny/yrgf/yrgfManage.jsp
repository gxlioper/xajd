<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='js/moveDiv.js'></script>
		<script type='text/javascript' src='js/comm/searchTj.js'></script>
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
			
		function loadMkxx(){
			if($("xmlxgl").value!=""){
				checkMkList($("xmlxgl").value);
			}
		}
			
		function getXm(){
			var text="";
			var num=document.getElementsByName("lxdm").length;
			var dm=document.getElementsByName("lxdm");
			var mc=document.getElementsByName("lxmc");
			for(i=0;i<num;i++){	
				if($("xmlxdm").value==dm[i].value){
					text+="<li id=\""+mc[i].value+"\"  style='background:#dce8f8'>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" style='color:#0A63BF'  onclick='setXmlx(\""+dm[i].value+"\",\""+mc[i].value+"\");allNotEmpThenGo(\"/xgxt/rcswLnny.do?method=yrgfManage&doType=query\");return false;'>"+mc[i].value+"</a></li>";
				}else{
					text+="<li id=\""+mc[i].value+"\">&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" style='color:#0A63BF'  onclick='setXmlx(\""+dm[i].value+"\",\""+mc[i].value+"\");allNotEmpThenGo(\"/xgxt/rcswLnny.do?method=yrgfManage&doType=query\");return false;'>"+mc[i].value+"</a></li>";
				}
			}
			document.getElementById('xmxslb').innerHTML = text;
		}
		
		//设置模块代码
		function setXmlx(dm,mc){
			$("xmlxdm").value=dm;
			$("xmlxmc").value=mc;
		}
			
		
		function saveFz(){
			if($("dfsj")){
				if(""==$("dfsj").value){
					alert("请填写打分时间!");
					return false;
				}
			}
		
			var xhArr=document.getElementsByName('xhArr');
			//判断页面是否有记录需要保存
			if(xhArr.length>0){
				refreshForm('/xgxt/rcswLnny.do?method=yrgfManage&doType=save');
			}else{
				alert("没有需要保存的记录!");
				return false;
			}
		}
		
		function showDiv(div_id,len){
			hiddenSelect();
			createOtherDiv("一日规范情况",$(div_id).outerHTML,div_id,300,200);
		}
		
		function plxssz(div_id,len){
			//判断批量设置时,是否选择学生
			var pkV=document.getElementsByName("checkVal");
			var blog=false;
			
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked){
					blog=true;
					break;
				}
			}
			if(!blog){
				alert("请先选择需要批量设置的学生！");
				return false;
			}else{
				showDiv(div_id,len);
			}
		}
		
		function changeDiv(){
			
			var xhArr=document.getElementsByName('xhArr');
			for(j=0;j<xhArr.length;j++){
				var div_id="divs_"+j;
				createDivs("dgsz",div_id,j);
			}
			createDivs("plsz","divs_pl","0");
		}
		
		//类型:批量、单个
		function createDivs(czlx,div_id,id){
				var text="";
				text+="<table class='formlist'  style=\"width:100%;height:260;overflow-x:hidden;overflow-y:auto;\">";
				if("dgsz"==czlx){
				var hidArr=document.getElementsByName('hidArr_'+id);
				var lens=hidArr.length/4;
				var m=0;
				for(i=0;i<lens;i++){
					if(i==0){
						text+="<tr>"
					}else if(i%3==0){
						text+="</tr><tr>";
					}
					if(i==lens-1){
						
						text+="<td width='150px'><input type='checkbox' name='fzArr_"+i+"_"+id+"' id='fzArr_"+i+"_"+id+"'";
						if(eval(hidArr[m].value)==0){
							text+=">"
						}else{
							text+="checked='checked'>"
						}
						m++;
						var xmmcLen=(hidArr[m].value).length;
							if(xmmcLen>7){
								text+=hidArr[m].value.substring(0,7)+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+hidArr[m].value.substring(7,xmmcLen);
							}else{
								text+=hidArr[m].value;
							}
						m++;
						if("减分"==hidArr[m].value){
							m++;
						   text+="(减"+hidArr[m].value+"分)";
						}else{
							m++;
							text+="(加"+hidArr[m].value+"分)";
						}
						text+="</td>";
						if(lens%3!=0){
							for(col=0;col<(3-lens%3);col++){
								text+="<td>&nbsp;</td>";
							}
						}
						m++;
					}else {
						text+="<td width='150px'><input type='checkbox' name='fzArr_"+i+"_"+id+"' id='fzArr_"+i+"_"+id+"'";
						if(eval(hidArr[m].value)==0){
							text+=">"
						}else{
							text+="checked='checked'>"
						}
						m++;
						var xmmcLen=(hidArr[m].value).length;
							if(xmmcLen>7){
								text+=hidArr[m].value.substring(0,7)+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+hidArr[m].value.substring(7,xmmcLen);
							}else{
								text+=hidArr[m].value;
							}
						m++;
						if("减分"==hidArr[m].value){
							m++;
						   text+="(减"+hidArr[m].value+"分)";
						}else{
							m++;
							text+="(加"+hidArr[m].value+"分)";
						}
						text+="</td>";
						m++;
					}
				}
				
				
				}else{
					var hidArr=document.getElementsByName('hidArr_0');
					var lens=hidArr.length/4;
					var m=0;
					for(i=0;i<lens;i++){
						if(i==0){
							text+="<tr>"
						}else if(i%3==0){
							text+="</tr><tr>";
						}
						if(i==lens-1){
							text+="<td width='150px'><input type='checkbox' name='fzArr_"+i+"_pl' id='fzArr_"+i+"_pl'>";
							m+=1;
						 	var xmmcLen=(hidArr[m].value).length;
							if(xmmcLen>7){
								text+=hidArr[m].value.substring(0,7)+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+hidArr[m].value.substring(7,xmmcLen);
							}else{
								text+=hidArr[m].value;
							}
							m++;
							if("减分"==hidArr[m].value){
								m++;
							    text+="(减"+hidArr[m].value+"分)";
							}else{
								m++;
								text+="(加"+hidArr[m].value+"分)";
							}
							text+="</td>";
							if(lens%3!=0){
								for(col=0;col<(3-lens%3);col++){
									text+="<td>&nbsp;</td>";
								}
							}
							m++;
						}else {
							text+="<td width='150px'><input type='checkbox' name='fzArr_"+i+"_pl' id='fzArr_"+i+"_pl'>";
							m+=1;
							var xmmcLen=(hidArr[m].value).length;
							if(xmmcLen>7){
								text+=hidArr[m].value.substring(0,7)+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+hidArr[m].value.substring(7,xmmcLen);
							}else{
								text+=hidArr[m].value;
							}
							m++;
							if("减分"==hidArr[m].value){
								m++;
							    text+="(减"+hidArr[m].value+"分)";
							}else{
								m++;
								text+="(加"+hidArr[m].value+"分)";
							}
							text+="</td>";
							m++;
							}
					}
					
					
				}
				if(lens/3<4){
					var n=12-lens;
					if(lens%3!=0 && lens/3>0){
						n=3-lens/3;
					}else if(lens%3==0){
						n=4-lens/3;
					}else{
						n=3-lens/3;
					}
					
					for(m=0;m<n;m++){
						text+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
					}
				}
				text+="</table>";
				if("dgsz"==czlx){
						text+="</td></tr><tr><td colspan=\"4\" align='right'><button type="button"  onclick=\"showSelect('"+div_id+"');\">确 定</button><button type="button" onclick=\"showSelect('"+div_id+"');\">关 闭</button></tr></td>"
					}else{
						text+="</td></tr><tr><td colspan=\"4\" align='right'><button type="button" onclick=\"plsz();showSelect('"+div_id+"');\"'>确 定</button><button type="button" onclick=\"showSelect('"+div_id+"');\">关 闭</button></td></tr>";
					}
				createDiv("一日规范情况",text,div_id,500,200);
		}
		
		function plsz(){
			var pkV=document.getElementsByName("checkVal");
			var xlh=document.getElementsByName("xlh");
			// 判断是否选择要批量设置的学生
			var blog=false;
			var xhV=new Array();
			var idV=new Array();
			//记数
			var m=0;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked){
					xhV[m]=pkV[i].value;
					idV[m]=xlh[i].value;
					blog=true;
					m++;
				}
			}
			if(!blog){
				alert("请先选择需要批量设置的学生！");
				return false;
			}
			// ------------end---------------
			var hidArr=document.getElementsByName('hidArr_'+0);
			var lens=hidArr.length/4;
			for(j=0;j<lens;j++){
				for(i=0;i<xhV.length;i++){
					if($('fzArr_'+j+'_pl').checked){
						$('fzArr_'+j+'_'+xlh[i].value).checked=true;
					}else{
						$('fzArr_'+j+'_'+xlh[i].value).checked=false;
					}
				}
			}
		}
		
		
		function showSelect(id){
			hiddenZyDiv(id)	
			i = document.getElementsByTagName("select").length;
			for (j = 0; j < i; j++) {
				var obj = document.getElementsByTagName("select")[j];
				var id = obj.id;
				var arr;
				var splitName;
				
				if ($(id)){
					arr = id.split('_');
				}
				
				if (null != arr && arr.length>0){
					splitName=arr[0];
				}
				
				if (splitName!="select"){
					obj.style.display = "";
				}
		
			}
		}
		
		function hiddenSelect(){
		
			i = document.getElementsByTagName("select").length;
			for (j = 0; j < i; j++) {
				var obj = document.getElementsByTagName("select")[j];
				var id = obj.id;
				var arr;
				var splitName;
				
				if ($(id)){
					arr = id.split('_');
				}
				
				if (null != arr && arr.length>0){
					splitName=arr[0];
				}
				
				if (splitName!="select"){
					obj.style.display = "none";
				}
		
			}
		}
		
		
		
		function createDiv(title,divHtml,divid,width,height){
			var floatDiv = document.createElement('div');
			floatDiv.id = divid;
			floatDiv.style.position = "absolute";
			floatDiv.style.width = width;
			floatDiv.style.height = height;
			floatDiv.style.backgroundColor = "#FFFFFF";
			
			var d_width = document.body.clientWidth;
			var d_height = document.body.clientHeight;
			var d_left_top = (d_width - width) / 2 +"px";
			var d_top_top =  "100" + "px";
			
			floatDiv.style.top = d_top_top;
			floatDiv.style.left = d_left_top;
			floatDiv.style.zIndex  = 1001;
			
		
			//temp_divHtml=divHtml;
			
			var dd_html ="";
			dd_html += "<div id=\"windown-title\" align='left'><h2>"+title+"</h2>";
			dd_html += "<span id=\"windown-close\" onclick=\"hiddenZyDiv('"+divid+"')\">确定</span>";
			dd_html += "</div>";
			dd_html+="<table style=\"width:100%;height:260;\" border='0' class='formlist open01'>";
			dd_html+="<tbody><tr><td colspan='4'>";
			dd_html+="</td></tr>";
			
			
			
			dd_html+="<tr style=\"width:100%;height:40;\"><td colspan='4'>";
			dd_html+="<div style=\"width:100%;height:260;overflow-x:hidden;overflow-y:auto;\"><table style=\"width:100%\">";
			dd_html += "</div>";
			dd_html += "<div id=\"open_win\" class=\"open_win01\" style=\"padding-top:5px\">";
			floatDiv.innerHTML = dd_html+divHtml+"</div>";
			
			//document.getElementById(divId).innerHTML = "";
			//document.body.removeAttribute($(divId))
			document.forms[0].appendChild(floatDiv);
			$(divid).style.display="none";
		}
		
		
		</script>
	</head>

	<body onload="getXm();changeDiv();loadMkxx()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/rcswLnny">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xmlxdm" styleId="xmlxdm" />
			<html:hidden property="xmlxmc" styleId="xmlxmc" />
			<!-- 隐藏域 end-->

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="saveFz()" class="btn_ccg">保存</a>
							</li>
							<li>
								<a href="#" onclick="plxssz('divs_pl','0');return false"
									class="btn_shtg">批量操作</a>
							</li>
							<li>
								<a href="#" onclick="allNotEmpThenGo('/xgxt/rcswLnny.do?method=yrgfTjcx&doType=query');return false"
									class="btn_fh">返回</a>
							</li>
							 
								<font color="blue">注：项目类型需要先维护子项才能在该模块显示</font>
							 
						</ul>
					</div>
				</logic:equal>
			</div>
			<div class="leftframe04">
				<div class="menulist">
					<!--层start-->
					<div class="menutitle">
						<h3>
							<span class="title">一日规范</span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:400px; overflow:auto;">

							<p class="choose_select">
								<label>
									类型
								</label>
								<input type="text" name="xmlxgl" id="xmlxgl" value="${xmlxgl}" onkeyup="checkMkList(this.value)"
									style="width:90px" />
							</p>
							<ul id="xmxslb">

							</ul>
							<logic:iterate name="xmlxList" id="xmlx">
								<input type="hidden" id="lxdm" name="lxdm"
									value="${xmlx.dm}" />
								<input type="hidden" id="lxmc" name="lxmc"
									xmlxmc""
									value="${xmlx.mc}" />
							</logic:iterate>
						</div>
						<script type="text/javascript">		
						<!--
						var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu1","li");
						MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif");
						MyCNLTreeMenu1.SetNodes(0);
						//Sw('AllClose_1');
						-->
						</script>
					</div>
					<!--层end-->
				</div>
			</div>

			<!-- 搜索 -->
			<div class="rightframe04" style="width:80%">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<font color="blue">&nbsp;学年：${xn
											}&nbsp;&nbsp;&nbsp;学期：${xq
											}&nbsp;&nbsp;&nbsp;年度：${nd}&nbsp;&nbsp;&nbsp;日期：${djsj}</font>
									</div>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/rcswLnny.do?method=yrgfManage&doType=query')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('nj-xy-zy-bj-xh-xm');">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="" maxlength="20" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20" />
								</td>

							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									院系
								</th>
								<td>
								<logic:equal name="userType" value="xy">
									<html:select property="queryequals_xydm" style="width:150px" styleId="xydm"
										onchange="initZyList();initBjList()" value="${userDep }"
										disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" styleId="xy" value="${userDep}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<html:select property="xydm" style="width:150px" styleId="xy" 
										onchange="initZyList();initBjList()" >
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:150px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>

							</tr>
							<tr>
								<th>
									<font color="red">*</font>打分时间
								</th>
								<td>
									<input type="text" name="dfsj" id="dfsj" value="${dfsj}"
										onclick="return showCalendar('dfsj','y-mm-dd');"
										onblur="dateFormatChg(this)" readonly="true" />
								</td>
								<td colspan="4">

								</td>
							</tr>
						</tbody>
					</table>
					<!-- 过滤条件 end-->
					<!-- 查询结果-->
				</div>
				<logic:empty name="rs">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <font color="red">未找到任何记录！</font> </span>
					</h3>
				</logic:empty>

				<div class="con_overlfow">
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" name="jd" id="jd" />
									<input type="checkbox" id="selall" name="selall"
									onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:notEmpty name="rs">
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<td>
											<input type="hidden" name="xhArr" id="xhArr"
												value="<logic:iterate id="v" name="s" offset="0" length='1'><bean:write name="v" /></logic:iterate>" />
											<input type="hidden" name="xlh" id="xlh"
												value="${index}" />
											<input type="checkbox" name="checkVal" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length='1'><bean:write name="v" /></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="0" length='3'>
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
											<a href="#" onclick="showDiv('divs_${index}',${index});return false"><u><font color="blue">操作</font></u></a>
										</td>
										<logic:iterate id="v" name="s" offset="4">
											<td style="display:none">
												<input type="hidden" name="hidArr_${index}"
													id="hidArr_${index}" value="${v}" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length='1'>
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</logic:notEmpty>
						<!--内容 end-->
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
							<logic:iterate id="tit" name="topTr">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
							<td>
									&nbsp;
							</td>
						</tr>
						<%
						}
						%>

					</table>
				</div>
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=rcswLnnyForm"></jsp:include>
				</div>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页显示-->
				<logic:present name="result">
					<logic:equal name="result" value="true">
					<script> 
						alert("操作成功!");
					</script>
					</logic:equal>
					<logic:equal name="result" value="false">
					<script> 
						alert("操作失败!");
					</script>
					</logic:equal>
				</logic:present>
		</html:form>
		
	</body>
</html>
