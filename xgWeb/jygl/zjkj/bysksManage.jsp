<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
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
			var xxsh = curr_row.getElementsByTagName('input')[1].value;
			var userType = $('userType').value;
			
			if(userType == "xy" && xxsh == "ͨ��"){
				alert("��ѡ���ѧ���ѱ��ϼ����ͨ��������Ȩ�޸�");
			}else{
				modi('jygl.do?method=bysksmodi&doType=modi');
			}
		}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
		}
	}
	</script>
	</head>
	<body onload="choiceDisabled('xy','xy');">
		<html:form action="jygl.do" method="post">
			<input type="hidden" name="mk" value="${mk }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<input type="hidden" name="doType" value="${doType }" />
			<logic:equal value="xy" name="userType">
				<input type="hidden" name="queryequals_xydm" value="${userDep}"/>	
			</logic:equal>
			
			<div class="tab_cur">
				<p class="location">
					<em>��ǰ����λ�ã�</em><a>${title }</a>
				</p>
			</div>
				
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
					<div class="buttonbox">
						<ul>
						<logic:equal value="cx" name="doType">
							<li><a href="#" class="btn_zj" onclick="location='jygl_kykgsq.do'">����</a></li>
							<li><a href="#" class="btn_xg" onclick="isSh();">�޸�</a></li>
							<li><a href="#" class="btn_sc" onclick="dataBatch('jygl.do?method=bysksdel&go=go')">ɾ��</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
							<li><a href="#" class="btn_dc" onclick="expData('jygl.do?method=bysksexp&go=go')">����</a></li>
						</logic:equal>

						<logic:equal value="sh" name="doType">
							<li><a href="#" class="btn_sh" onclick="modi('jygl.do?method=bysksmodi&doType=shone');">�������</a></li>
							<li><a href="#" class="btn_shtg" onclick="batchData('primarykey_cbv','jygl.do?method=byskssh&shjg=tg&go=go','sh')">���ͨ��</a></li>
							<li><a href="#" class="btn_shbtg" onclick="batchData('primarykey_cbv','jygl.do?method=byskssh&shjg=btg&go=go','sh')">��˲�ͨ��</a></li>
						</logic:equal>
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
	              		<button class="btn_cx" id="search_go" 
	              		onclick="allNotEmpThenGo('/xgxt/jygl.do?method=bysksManage&go=go')">
	              		�� ѯ
	              		</button>
	             		 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
	              			�� ��
	             		 </button>
	            		</div>
	          		</td>
	       		</tr>
	     		</tfoot>
					
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td><html:text property="querylike_xh" styleId="xh"></html:text></td>
							<th>����</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>��Ŀ</th>
							<td><html:select property="queryequals_mk">
								<html:option value=""></html:option>
								<html:option value="ky">�����о���</html:option>
								<html:option value="kg">��������Ա</html:option>
							</html:select></td>
							<th>���</th>
							<td>
								<html:select property="queryequals_xxsh">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xb" /></th>
								<td><html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
								<th>רҵ</th>
								<td><html:select property="queryequals_zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>�༶</th>
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
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('jygl.do?method=bysksmodi&doType=view');" class="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
								align="left" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="checkbox" name="primarykey_cbv" id="pkV" ${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="9">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="12">
									<td>
										<input type="hidden" value="${v }" />
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					
				<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
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
