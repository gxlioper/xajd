<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self"/>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		function choiceDisabled(usertype,ele) {
   			if($("userType")){
				if ($("userType").value == usertype) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
		
	function isSh(){
		if(curr_row != null){
			var xysh = curr_row.getElementsByTagName('input')[1].value;
			var xxsh = curr_row.getElementsByTagName('input')[2].value;
			var userType = $('userType').value;
			
			if((userType == "fdy" && xysh == "ͨ��") || (userType == "xy" && xxsh == "ͨ��")){
				alert("��ѡ���ѧ���ѱ��ϼ����ͨ��������Ȩ�޸�");
			}else{
				modi('ntzy_pypx.do?method=yxtzbView&doType=modi');
			}
		}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
		}
	}
		
		function print(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ��ӡ�������У�');
				return false;
			}
		}
	</script>
	</head>
	
	<body onload="choiceDisabled('xy','bmdm');choiceDisabled('stu','tzbmc-sqr-bmdm');">
		<html:form action="ntzy_pypx.do" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			
			<logic:equal value="xy" name="userType">
				<input type="hidden" name="queryequals_ssbm" value="${userDep }"/>
			</logic:equal>
			
			<logic:equal value="stu" name="userType">
				<input type="hidden" name="queryequals_sqr" value="${userName }"/>
			</logic:equal>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>������֧����ѯ</a>
				</p>
			</div>
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_xg" onclick="isSh();">�޸�</a></li>
							<li><a href="#" class="btn_sc" onclick="dataBatch('ntzy_pypx.do?method=yxtzbcx&doType=del&go=go')">ɾ��</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
							<li><a href="#" class="btn_dc" onclick="wjcfDataExport('ntzy_pypx.do?method=yxtzbExp',600,400)">����</a></li>
							<li><a href="#" class="btn_dy" onclick="print('ntzy_pypx.do?method=yxtzbView&doType=print');">��ӡ����</a></li>
						</ul>
					</div>
					</div>
				</logic:equal>
				
				<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
	        		<tr>
	          			<td colspan="6">
	            		<div class="btn">
	              		<input type="hidden" name="go" value="a" />
	              		<button type="button" class="btn_cx" id="search_go" 
	              		onclick="allNotEmpThenGo('/xgxt/ntzy_pypx.do?method=yxtzbcx&go=go')">
	              		�� ѯ
	              		</button>
	             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
	              			�� ��
	             		 </button>
	             		  <button type="button" class="btn_cz" id="btn_cz" onclick="location='pypxcx.do'">
	              			����
	             		 </button>
	            		</div>
	          		</td>
	       			</tr>
	     			</tfoot>
					<tbody>
						<tr>
							<th>
								ѧ�꣺</th>
								<td><html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select></td>
								<th>��֧�����ƣ�</th>
								<td><html:text property="querylike_tzbmc" styleId="tzbmc"></html:text></td>
								<th>�����ˣ�</th>
								<td><html:text property="querylike_sqr" styleId="sqr"></html:text></td>
						</tr>
						<tr>
							<th>
							���ڲ��ţ�</th>
								<td><html:select property="queryequals_ssbm" style="width:180px"
									styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</fieldset>
			
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
				<fieldset>
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzy_pypx.do?method=yxtzbView&doType=view',700,500);"
								align="center"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" 
										id="pkV" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									
									<logic:iterate id="v" name="s" offset="6" length="1">
										<input type="hidden" value="${v}"/>
									</logic:iterate>
									
									<logic:iterate id="v" name="s" offset="7" length="1">
										<input type="hidden" value="${v}"/>
									</logic:iterate>
								</td>
							
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
								</logic:iterate>
							
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</fieldset>
				<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pypxForm"></jsp:include>
			  <!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>
