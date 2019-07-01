<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		
		function modi(url){
			if($("jczc")){
				url+= "&jczc="+$("jczc").value;
			}
			if($("jcsj")){
				url+= "&jcsj="+$("jcsj").value;
			}
			
			if(curr_row != null){
				showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		</script>
	</head>
	<body onload="showWsdj()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
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
			
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_xg" onclick="modi('/xgxt/commWsjc.do?method=xsfslrUpdate&doType=modi');">¼��</a></li>
				</ul>
			</div>
			</div>
		</logic:equal>
			<div class="toolbox">
				
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>ѧ��</th>
								<td><html:text property="xh"></html:text></td>
								<th>����</th>
								<td><html:text property="xm"></html:text></td>
								<th>
									У��
								</th>
								<td>
									<html:select property="xqdm" style="width: 150px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>													
								</td>
							</tr>							
							<tr>
								<th>
									¥��
								</th>
								<td>
									<html:select property="lddm" style="width: 150px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="cs" style="width: 150px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="qsh" style="width: 150px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>			
								</td>
								<th></th>
								<td></td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
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
										<html:select property="xydm" onchange="" styleId="xy" style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
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
										<html:select property="jczc" style="width: 150px" styleId="jczc" onchange="$('search_go').click()">
											<html:options collection="zcList" property="dm" labelProperty="mc" />
										</html:select>	
									</logic:equal>
									<logic:equal name="jczq" value="��">
										<html:text property="jcsj" styleId="jcsj" onchange="$('search_go').click()"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('jcsj','y-mm-dd');"
										/>
									</logic:equal>
								</td>
								<th></th><td></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="if($('jcsj')&&$('jcsj').value==''){alert('���ʱ�䲻��Ϊ�գ���ȷ�ϣ�')}else{allNotEmpThenGo('/xgxt/commWsjc.do?method=xsfslrManage')};">
											��ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
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
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rsArrList" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('/xgxt/commWsjc.do?method=xsfslrUpdate&doType=view');"
								align="left"
								style="cursor:hand">
					
								<logic:iterate id="v" name="s" offset="0">
									<td>
										<input type="hidden" value="${v }"/>
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--��ҳend-->
						
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>