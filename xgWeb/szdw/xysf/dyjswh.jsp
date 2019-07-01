<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url,h,w){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
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
					modi('gdby_tygl.do?method=tyViewAndModi&doType=modi');
				}
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
			}
		}
		
		function choiceDisabled(ele) {
			var tmp = ele.split("-");
			for (i = 0; i < tmp.length; i++) {
				if (document.getElementById(tmp[i])) {
					document.getElementById(tmp[i]).disabled = true;
				}
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/xysf_dyjsgl.do?method=dyjswh" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<input type="hidden" name="isComm" value="true"/>
					
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_ck" onclick="modi('xysf_dyjsgl.do?method=dyjskcap',800,600);return false;">�γ̰���</a></li>
					<li><a href="#" class="btn_zj" onclick="showTopWin('xysf_dyjsgl.do?method=addDyjs',700,500);return false;">����</a></li>
					<li><a href="#" class="btn_xg" onclick="modi('xysf_dyjsgl.do?method=dyjsViewAndModi&doType=modi',700,500);return false;">�޸�</a></li>
					<li><a href="#" class="btn_sc" onclick="dataBatch('xysf_dyjsgl.do?method=dyjswh&doType=del&go=go');return false;">ɾ ��</a></li>
					<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">����</a></li>
					<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">����</a></li>
					<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a></li>							
				</ul>
			</div>
			</div>
			</logic:equal>
			
			<div class="searchtab">
				<table width="100%" class="">
					<tbody>
						<tr>
							<th>ְ����</th>
							<td><html:text property="querylike_zgh" styleId="zgh"></html:text></td>
							<th>����</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							<th>������</th>
							<td><html:select property="queryequals_bmdm" style="width:180px"
									styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						
						<logic:equal value="xy" name="userType">
							<input type="hidden" name="queryequals_bmdm" value="${userDep }"/>
							<script type="text/javascript">
								choiceDisabled('bmdm');
							</script>
						</logic:equal>
					</tbody>
					
					<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/xysf_dyjsgl.do?method=dyjswh&go=go')">
		              		�� ѯ
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			�� ��
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     		</tfoot>
				</table>
				</div>
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
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('xysf_dyjsgl.do?method=dyjsViewAndModi&doType=view',700,500);"
								align="center"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</logic:iterate>
								</td>
							
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
								</logic:iterate>
							
							</tr>
						</logic:iterate>
					</table>
					<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dyjstForm"></jsp:include>
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
