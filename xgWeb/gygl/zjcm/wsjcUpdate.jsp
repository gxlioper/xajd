<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<!-- ͷ -->
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script language="javascript">	
		function setJcsj(){
			var d_width = document.body.clientWidth;
			var d_height = document.body.clientHeight ;
			var d_left = 0;
			var d_top = 0;
			var d_color = "#EEF4F9";
			var d_width_top = 200;
			var d_height_top = 120;
			var d_left_top = (d_width - d_width_top) / 2;
			var d_top_top = (d_height - d_height_top) / 2;
			var d_color_top = "#EEF4F9";
			dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
			dd_html += "</div>";
			dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
			dd_html += "<table width='300' class='formlist'>";
			dd_html += "<thead>";
			dd_html += "<tr height=''>";
			dd_html += "<th colspan='2'>";
			dd_html += "<span>���ʱ��</span>";
			dd_html += "</th>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<th align='right' width='30%'>";
			dd_html += "ʱ��:";
			dd_html += "</th>";
			dd_html += "<td align='left'>";
			dd_html += "<input type='text' name='jcsj' id='jcsj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tr bgcolor='EEF4F9'>";
			dd_html += "<td colspan='2' align='center'>";
			dd_html += "<button class='button2' onclick='saveWsjc()';>ȷ��</button>";
			dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
			dd_html += "<button class='button2' onclick='closeAdd1()'>�ر�</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table>";
			dd_html += "</div>";
			tmpdiv1.innerHTML = dd_html;
		}
		
		
		function time(id){
			return showCalendar(id,'y-mm-dd');
		}
		
		function saveWsjc(){
			if($("jcsj")){
				if($("jcsj").value == ""){
					alert("��ȷ�����ʱ��");
					return false;
				}
			}
	
			var url = "/xgxt/zjcmGygl.do?method=wsjcUpdate&doType=save";
			showTips('���������У���ȴ�......');
			refreshForm(url);
		}
		
		</script>
	</head>
	<!-- ͷ end -->
	<body onload="xyDisabled('xy');">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="csh" name="csh" value="${csh }" />
			<!-- ������ end-->
			<!-- ҳǩ -->
			<div class="compTab" id="card">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li>
							<a href="#" 
								onclick="$('go').value='';refreshForm('zjcmGygl.do?method=wsjcManage')"
							 	id="${card.whdmb }">
								<span>�鿴</span>
							</a>
						</li>
						<li class="ha">
							<a href="#"
								onclick="$('go').value='';refreshForm('zjcmGygl.do?method=wsjcUpdate')"
							 	id="${card.whdmb }">
								<span>¼��</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- ҳǩ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="viewTempDiv('���ʱ��','tmpdiv1',280,160)"
									class="btn_ccg">����</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zjcmGygl.do?method=wsjcUpdate');">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									У��
								</th>
								<td>
									<html:select property="xqdm" style="width: 100px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="lddm" style="width: 100px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="cs" style="width: 100px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh" style="width: 100px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									
									<logic:equal value="xy" name="userType" scope="session">
										<html:hidden value="${userDep }" property="xydm"/>
									</logic:equal>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td>
										��������
									</td>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showInfo('/xgxt/zjcmGygl.do?method=fyxxView','view','600','480')">
									<td align="center">
										<input type="checkbox" id="checkVal" name="checkVal" 
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" style="display: none"/>
										<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" length="7">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
									<td>
										<input type="hidden" name="jcqs" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
										<input type="text" name="fs" value="" onblur="chMax(this,100)"
											 onkeypress="return onlyNum(this,3)" 
											 maxlength="3" style="width:30%;ime-mode:disabled"/>
									</td>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						 <script type="text/javascript">
					      $('choose').className="hide";
					     </script>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("������¼��һ�����ҵ������֣�");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<div id="tmpdiv1" style="display: none">
			<table class="formlist">
				<tr>
					<th>ʱ��</th>
					<td><input type='text' name='jcsj' id='jcsj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='readonly'/></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><button onclick='saveWsjc()'>ȷ��</button></td>
				</tr>
			</table>	
		</div>
	</body>
</html>
