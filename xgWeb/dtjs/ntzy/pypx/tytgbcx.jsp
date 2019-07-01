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
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value
				+'&xh='+curr_row.getElementsByTagName('input')[1].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function print(url){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value
				+'&xh='+curr_row.getElementsByTagName('input')[1].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ��ӡ�������У�');
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
			var xysh = curr_row.getElementsByTagName('input')[2].value;
			var xxsh = curr_row.getElementsByTagName('input')[3].value;
			var userType = $('userType').value;
			
			if((userType == "fdy" && xysh == "ͨ��") || (userType == "xy" && xxsh == "ͨ��")){
				alert("��ѡ���ѧ���ѱ��ϼ����ͨ��������Ȩ�޸�");
			}else{
				modi('ntzy_pypx.do?method=pypxview&doType=modi&mk='+$('mk').value);
			}
		}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
		}
	}
	</script>
	</head>
	<body onload="choiceDisabled('xy','xy');choiceDisabled('stu','xh-xm-xy-zy-bj');">
		<html:form action="ntzy_pypx" method="post">
			<input type="hidden" name="mk" value="${mk }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>��ǰ����λ�ã�</em><a>
					<logic:equal value="yxty" name="mk">���㹲����Ա��ѯ</logic:equal>
					<logic:equal value="yxtgb" name="mk">���㹲���Ÿɲ���ѯ</logic:equal>
					<logic:equal value="tzzgb" name="mk">��������֧�ɲ���ѯ</logic:equal>
					<logic:equal value="xshgb" name="mk">����ѧ����ɲ���ѯ</logic:equal>
					</a>
				</p>
			</div>
			
			<fieldset>
				
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_xg" onclick="isSh();">�޸�</a></li>
							<li><a href="#" class="btn_sc" onclick="dataBatch('ntzy_pypx?method=tytgbcx&doType=del&go=go')">ɾ��</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
							<li><a href="#" class="btn_dc" onclick="wjcfDataExport('ntzy_pypx.do?method=pypxExp&mk='+$('mk').value,600,400)">����</a></li>
							<li><a href="#" class="btn_dy" onclick="print('ntzy_pypx.do?method=pypxview&doType=print&mk='+$('mk').value);">��ӡ����</a></li>
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
	              		onclick="allNotEmpThenGo('/xgxt/ntzy_pypx.do?method=tytgbcx&go=go')">
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
								<input type="hidden" name="queryequals_mk" value="${mk }"/>
								<logic:equal value="stu" name="userType">
									<input type="hidden" name="queryequals_xh" value="${userName }"/>
								</logic:equal>
								<logic:equal value="xy" name="userType">
									<input type="hidden" name="queryequals_xydm" value="${userDep}"/>
								</logic:equal>
								ѧ�꣺</th>
								<td><html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select></td>
								<th>ѧ�ţ�</th>
								<td><html:text property="querylike_xh" styleId="xh"></html:text></td>
								<th>������</th>
								<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
						</tr>
				
						<tr>
							<th>
								<bean:message key="lable.xb" />��</th>
								<td><html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
								<th>רҵ��</th>
								<td><html:select property="queryequals_zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>�༶��</th>
								<td><html:select property="queryequals_bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select></td>
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
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzy_pypx.do?method=pypxview&doType=view&mk='+$('mk').value)"
								align="center"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="${v }"/>
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="5">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="8" indexId="index">
									<logic:equal name="index" value="8">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
									</logic:equal>
									<logic:equal name="index" value="9">
										<td>
											<input type="hidden" value="${v }" />
											${v }
										</td>
									</logic:equal>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					
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
