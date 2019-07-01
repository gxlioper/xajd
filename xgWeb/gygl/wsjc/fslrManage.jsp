<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//����������ȡ�����ȼ�
		function setWsfdj(num){
			var wsfId = "wsf"+num;
			var djId = "wsfdj"+num;	
			var djNum = $("djNum").value;
			
			var wsfdj = "δά��";
			
			if($(wsfId)&&$(wsfId).value!=""){
				var wsf = $(wsfId).value;
				for(var i=0;i<djNum;i++){
					var xx = $("xx"+i).value;
					var sx = $("sx"+i).value;
					var dj = $("dj"+i).value;
					
					if(parseInt(wsf)>=xx && parseInt(wsf)<=sx){
						wsfdj = dj;
						break;
					}
				}
			}
			
			if($(djId)){
				$(djId).value = wsfdj;
			}
		}
		
		//���������ֵȼ�ȡ��������
		function setWsffs(num){
			var wsfId = "wsf"+num;
			var djId = "djOption"+num;	
			var tempDjId = "tempDj"+num;	
			var djNum = $("djNum").value;
			
			var wsf = "δά��";
			
			if($(djId)&&$(djId).value!=""){
				var djV = $(djId).value;
				for(var i=0;i<djNum;i++){
					var xx = $("xx"+i).value;
					var sx = $("sx"+i).value;
					var dj = $("dj"+i).value;
					
					if(dj == djV){
						wsf = sx;
					}
				}
				
				$(tempDjId).value=djV;
			}
			
			if($(wsfId)){
				$(wsfId).value = wsf;
			}
		}
		
		//��ʾ�����ȼ�
		function showWsdj(){
			var num = $("rsNum").value;
			var lrxs = $("lrxs").value;
			for(var i=0;i<num;i++){
			
				var tempDjId = "tempDj"+i;
				var djId = "djOption"+i;	
				
				if($(tempDjId) && $(tempDjId).value != "" && $(djId)){
					$(djId).value = $(tempDjId).value;
				}
				
				if(lrxs == "����"){
					setWsfdj(i);
				}else{
					setWsffs(i);
				}
					
			}
		}
		
		//��ʾ����DIV
		function showSaveDiv(){
		
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var flag = false;
			
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			if(!flag){
				alert('�빴ѡ��Ҫ��������ݣ�');
			}else{
				viewTempDiv('����������Ϣ','showDiv',280,300)
			}
		}
		
		//��ʾ������Ϣ
		function showQsInfo(){
			var url = "/xgxt/commWsjc.do?method=showQsInfo";
			if($("jczc")){
				url+= "&jczc="+$("jczc").value;
			}
			if($("jcsj")){
				url+= "&jcsj="+$("jcsj").value;
			}
			showInfo(url,'view','800','600');
		}
		
		//����������
		function saveWsf(){
		
			var rsNum = $("rsNum").value;
			var lrxs = $("lrxs").value;
			var flag = true;

			for(var i=0;i<rsNum;i++){
				if(lrxs == "����"){
					var id = "wsf"+i;
					if($(id) && $(id).value != ""){
						flag = false;
						break;
					}
				}else{
					var id = "djOption"+i;
					if($(id) && $(id).value != ""){
						flag = false;
						break;
					}
				}
			}
			
			if(flag){
				alert("����¼��������ٱ��棡");
				return false;
			}else{
				saveUpdate('/xgxt/commWsjc.do?method=fslrManage&doType=save','')
			}
		}
		</script>
	</head>
	<body onload="showWsdj()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commWsjc">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>	
			<input type="hidden" id="djNum" value="${djNum}"/>
			<input type="hidden" id="rsNum" value="${rsNum}"/>
			<input type="hidden" id="lrxs" value="${lrxs}"/>
			<!-- �����ֵȼ��ǿ� -->
			<logic:notEqual name="djNum" value="0">
				<logic:iterate name="wsfdjList" id="djnr" indexId="num">
					<input type="hidden" id="xx${num }" value="${djnr.wsfxx }"/>
					<input type="hidden" id="sx${num }" value="${djnr.wsfsx }"/>
					<input type="hidden" id="dj${num }" value="${djnr.wsfdj }"/>
				</logic:iterate>
			</logic:notEqual>	
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="saveWsf()"
									class="btn_ccg">����</a>
							</li>
							<li>
								<a href="#"
									onclick="showSaveDiv();"
									class="btn_xg">����������Ϣ</a>
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
											onclick="if($('jcsj')&&$('jcsj').value==''){alert('���ʱ�䲻��Ϊ�գ���ȷ�ϣ�')}else{allNotEmpThenGo('/xgxt/commWsjc.do?method=fslrManage')};">
											��ѯ
										</button>
										<button class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('xqdm-lddm-cs-qsh-xy-sfdf');return false;">
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
									У��
								</th>
								<td>
									<html:select property="xqdm" style="width: 150px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
								<th>
									¥��
								</th>
								<td>
									<!-- ��Ԣ�ϰ汾 -->
									<logic:equal name="gyglEdition" value="old">
										<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
									</logic:equal>
									<!-- ��Ԣ�°汾 -->
									<logic:equal name="gyglEdition" value="new">
										<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:option value="">----��ѡ��----</html:option>
											<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
										</html:select>
									</logic:equal>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="cs" style="width: 150px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
								<th>
									����<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<logic:equal value="true" name="isxy">
										<html:hidden property="xydm"/>
										<html:select property="xydm" style="width: 150px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="true" name="isxy">
										<html:select property="xydm" onchange="" styleId="xy" style="width:150px" onmouseover="">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									�Ƿ��Ѵ��
								</th>
								<td>
									<html:select property="sfdf" styleId="sfdf" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									<logic:equal name="jczq" value="��">
										<font color="red">*</font>����ܴ�
									</logic:equal>
									<logic:equal name="jczq" value="��">
										<font color="red">*</font>���ʱ��
									</logic:equal>
								</th>
								<td>
									<logic:equal name="jczq" value="��">
										<html:select property="jczc" style="width: 150px" styleId="jczc" onchange="">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
									</logic:equal>
									<logic:equal name="jczq" value="��">
										<html:text property="jcsj" styleId="jcsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('jcsj','y-mm-dd');"
										/>
									</logic:equal>
								</td>
								<th>
<%--									��鲿��--%>
								</th>
								<td>
<%--									<html:select property="jcbm" styleId="jcbm" style="width:150px">--%>
<%--										<html:options collection="jcbmList" property="dm" labelProperty="mc" />--%>
<%--									</html:select>	--%>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
				<logic:empty name="rsArrList">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rsArrList">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ����������<bean:message key="lable.xb" />����Ϊ�գ�����������Ƿ������������ģ�鱻���䡣</font> 
						</span>
					</h3>
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td width="1%">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
									</td>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<!-- �����Ƿ�����ȼ� -->
									<logic:equal name="lrxs" value="����">
										<logic:equal name="gldj" value="��">
											<td>
												�����ֵȼ�
											</td>
										</logic:equal>
									</logic:equal>
									<!-- �ȼ��Ƿ�������� -->
									<logic:equal name="lrxs" value="�ȼ�">
										<logic:equal name="glfs" value="��">
											<td>
												�����ַ���
											</td>
										</logic:equal>
									</logic:equal>
									<td onclick="tableSort(this)" nowrap>
										��鲿��
									</td>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rsArrList" id="rs" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="showQsInfo()">
									<td align="center">
										<input type="checkbox" id="checkVal" 
											name="primarykey_checkVal"  
											value="<logic:iterate id="v" name="rs" offset="0" length="1">${v }</logic:iterate>"/>
										<input type="hidden" name="jcld" value="<logic:iterate id="v" name="rs" offset="6" length="1">${v }</logic:iterate>"/>	
										<input type="hidden" name="jccs" value="<logic:iterate id="v" name="rs" offset="4" length="1">${v }</logic:iterate>"/>	
										<input type="hidden" name="jcqs" value="<logic:iterate id="v" name="rs" offset="5" length="1">${v }</logic:iterate>"/>					
									</td>
									<logic:iterate id="v" name="rs" offset="1" length="5">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
									<logic:equal name="jczq" value="��">
										<td align="left">
											��${jczc }��
										</td>
									</logic:equal>
									<!-- ¼����ʽΪ���� -->
									<logic:equal name="lrxs" value="����">
										<td align="left">
											<input type="text" name="wsffs" id="wsf${index }" 
												onkeydown="return onlyNum(this,3)"
											    onmousedown="return onlyNum(this,3)"
											    maxlength="3" style="width: 50px;ime-mode:disabled"
											    onblur="setWsfdj(${index });chMax(this,100);"
											    value="<logic:iterate id="v" name="rs" offset="7" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:equal name="gldj" value="��">
											<td width="100px">
												<input type="text" name="wsfdj" id="wsfdj${index }" value="" readonly="readonly"
													style="width:70px;"/>
											</td>
										</logic:equal>
									</logic:equal>
									<!-- ¼����ʽΪ�ȼ� -->
									<logic:equal name="lrxs" value="�ȼ�">
										<td align="left">
											<select id="djOption${index }" style="width: 50px;" onchange="setWsffs(${index });">
												<option value=""></option>
												<logic:iterate name="wsfdjList" id="dj">
													<option value="${dj.wsfdj }">${dj.wsfdj }</option>
												</logic:iterate>
											</select>
											<input type="hidden" name="wsfdj" id="tempDj${index }" 
												value="<logic:iterate id="v" name="rs" offset="7" length="1">${v }</logic:iterate>"/>
										</td>
										<logic:equal name="glfs" value="��">
											<td width="50px">
												<input type="text" name="wsffs" id="wsf${index }" value="" readonly="readonly"
													style="width:50px;"/>
											</td>
										</logic:equal>
									</logic:equal>
									<td>
										<input type="hidden" name="arr_jcbm" value="<logic:iterate id="v" name="rs" offset="9" length="1">${v }</logic:iterate>"/>
										<input type="hidden" name="jcbz" value="<logic:iterate id="v" name="rs" offset="10" length="1">${v }</logic:iterate>"/>
										<logic:iterate id="v" name="rs" offset="8" length="1">${v }</logic:iterate>
									</td>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
			<div id="showDiv" style="display:none;height: 500px" align="center">
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td align='right' width='25%'>
								��鲿��
							</td>
							<td align='left'>
								<html:select property="bmdm" styleId="select_jcbm">
									<html:options collection="jcbmList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align='right' width='' height="100px">
								��ע
								</br>
								<font color="red">(��¼��250��)</font>
							</td>
							<td align='left'>
								<html:textarea property="bz" styleId="bz" style="height: 100px" onblur="chLeng(this,250)"/>
							</td>
						</tr>
						<tr>
							<td align='left' width='' colspan="2">
								<font color="blue">ע��ֻ��ִ�й������ֱ�������ҽ��в�����</font>
							</td>
						</tr>
					</tbody>		
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<button onclick="saveUpdate('/xgxt/commWsjc.do?method=fslrManage&doType=upDate','')">
										�ύ
									</button>
									&nbsp;&nbsp;
									<button onclick='hiddenMessage(true,true)'>
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>