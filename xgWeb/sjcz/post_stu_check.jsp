<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/jsFunction.js"></script>
    <script language="javascript" src="js/xgutil.js"></script>
    <script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
	function batch(yesNo){
			var pkString = "";
			var xhString = "";
			var xnString = "";
			var xqString = "";
			var zjkjPk = "";
			var userName = $('userName').value;
			var userType = $('userType').value;
			var xxdm = document.getElementById('xxdm').value;
			var number = 0;
			
			var mes = "ȷ��Ҫ����������";
			if(xxdm == "13022"){//�㽭��ѧ������ѧԺ
				mes = "�ϼ����ͨ���ļ�¼�����ı���˽����ȷ��Ҫ����������";
			}	
			for (i=0; i<document.getElementsByName("pkV").length; i++){
				var obj = document.getElementsByName("pkV")[i];
		    	if(document.getElementsByName("pkV")[i].checked){
		    		number += 1;
		    		pkString += document.getElementsByName("pkV")[i].value+"!!SplitOneSplit!!";
		    		xhString += document.getElementsByName("xhV")[i].value+"!!SplitOneSplit!!";
		    		zjkjPk += document.getElementsByName("xhV")[i].value + trim(obj.parentNode.parentNode.getElementsByTagName("td")[1].innerText) + trim(obj.parentNode.parentNode.getElementsByTagName("td")[2].innerText)+"!!SplitOneSplit!!";
		    	}
		    	
			}			
			if (pkString==""){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			
			if(number <2){
				mes = "ȷ��Ҫ����ѡ��ļ�¼��";
				if(xxdm == "13022"){
					mes = "�ϼ����ͨ���ļ�¼�����ı���˽����ȷ��Ҫ����ѡ��ļ�¼��";
				}
			}
			dwr.engine.setAsync(false);
			if(xxdm == "11057" && yesNo=="pass"){//�㽭�Ƽ�
				//����Ѿ��и�λ���ͨ������ʾ��ʾ��Ϣ
				if(checkArrayEleRepeat(zjkjPk,"!!SplitOneSplit!!")){
					mes = "����ѧ��ͨ���ĸ�λ������1����ȷ��Ҫ����������";
				}else{
					cqkjFunc.checkZjkjXsgw(pkString,zjkjPk,function(data){
						if(data != null && data != ''){
							mes = data + "ȷ������������";
						}
					});
				}
				
			}
			if (confirm(mes)) {
				if(yesNo!=null && yesNo=="pass"){
					if(xxdm == "13742"){//������һְҵ����ѧԺ
						cqkjFunc.checkFprs(pkString,userType,function(data){
							if(data!=null && data.length>0){
								var message = "";
								for (i=0; i<data.length; i++){
									if(data[i]!=null && data[i]!=""){
										message = message+"\n" + data[i];
									}
								}
								if(message!=""){
									alert("�޷����ͨ����"+message);
									return false;
								}else{
									refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
								}						
							}
						});
					}else if(xxdm == "11057"){//�㽭�Ƽ�ѧԺ
						refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
					}else{
						refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
						
					}
				}else{
					refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
				}
			}
			dwr.engine.setAsync(true);
		}
		
		function checkStuOkNum(){
			var pk = document.getElementById("xmdm").value;
			cqkjFunc.isStuOkNumBeyondStandard(pk,function(data){
				
			});
			return data;
		}
		
		function auto_check_stu(){
			var pk = document.getElementById("xmdm").value;
			if($('xmdm').value == ''){ 
				alert('��λ���Ʋ���Ϊ�գ��޷��Զ���ˣ�');
				return;
			}else {
			if(confirm("��ȷ������λҪ��������Զ������")){
				cqkjFunc.isStuOkNumBeyondStandard(pk,function(data){
					if(data == true){
						if(confirm('�ϸ��ѧ�������Ѿ�������λ����������!\n�Ƿ�����Զ���ˣ�\n' +
								'���[ȷ��]��ϵͳ���Զ�ѡ����������ϸ��ѧ��\n' +
								 '���[ȡ��]��ϵͳ���г����кϸ��ѧ�����ֶ����')){		
							refreshForm('/xgxt/post_stu_check.do?go=go&zdsh=3');
						}else{
							refreshForm('/xgxt/post_stu_check.do?go=go&zdsh=2');
						}
					}else{
						refreshForm('/xgxt/post_stu_check.do?go=go&zdsh=1');
					}				
				});
			 }
			}
		}		
	
	function chkPriseOne(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("��ѡ��Ҫ�������У�");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		if(xxdm == "11551"){
			url += "&xh=";
			url += curr_row.cells[5].innerText;
		}		
		showTopWin(url, w, h);
}
}
	//��ת·����
	function queryOne(xh) {
		var url = "/xgxt/stu_info_details.do?xh="+xh;
		showTopWin(url, 800, 600);
	}
	
	//��ӡ���ͨ��ѧ������
	function printStuinfo(){
		var url = "qgzxXcxy.do?method=printPassStu";
		var xn = document.getElementById('xn').value;
		var nd = document.getElementById('nd').value;
		var nj = document.getElementById('nj').value;
		var yrdwdm = document.getElementById('yrdwdm').value;
		var xydm = document.getElementById('xydm').value;
		var xmdm = document.getElementById('xmdm').value;
		
		url += "&xn=" + xn;
		url += "&nd=" + nd;
		url += "&nj=" + nj;
		url += "&yrdwdm=" + yrdwdm;
		url += "&xydm=" + xydm;
		url += "&xmdm=" + xmdm;
		
		window.open(url);
	}
	</script>
</head>
	<body <logic:equal value="11654" name="xxdm">onload="xyDisabled('xy');"</logic:equal> >
		<html:form action="/post_stu_check" method="post">
		
		<logic:equal value="11654" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
		</logic:equal>
		<logic:notEqual value="11654" name="xxdm" scope="session">
			<!-- �㽭��ְͨҵ����ѧԺ -->
			<logic:equal value="12036" name="xxdm" scope="session">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="isFdy" name="isFdy" value="${FdyQx}" />
			<input type="hidden" id="isBzr" name="isBzr" value="${BzrQx}" />
			</logic:equal>
			<!-- �㽭��ְͨҵ����ѧԺend -->
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>"/>
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
		</logic:notEqual>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>	
		
		<div class="toolbox">	
			<!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
			    <logic:equal value="yes" name="writeAble">
			   
					<!-- �������ϴ�ѧ -->
					<logic:present name="showbjlh">
						<li> <a href="#" onclick="refreshForm('/xgxt/postStuChkBatch.do')" class="btn_zj">¼ ��</a> </li>
					</logic:present>		
					<logic:notPresent name="showbjlh">
						<li> <a href="#" onclick="batch('pass')" class="btn_shtg">���ͨ��</a> </li>
						<li> <a href="#" onclick="batch('nopass')" class="btn_shbtg">��˲�ͨ��</a> </li>
					</logic:notPresent>
					<!-- ����ɽ��ѧ -->
					<logic:equal value="10419" name="xxdm">
						<%-- �Ӳ���zdsh ���� �ڲ�ѯʱ�����Զ���˵����� --%>
						<li> <a href="#" onclick="auto_check_stu()" class="btn_sh">�Զ����</a> </li>
					</logic:equal>
					<!-- end����ɽ��ѧ -->
					<!--����ѧԺ-->
					<logic:equal value="10628#" name="xxdm">
						<li> <a href="#" onclick="printStuinfo()" class="btn_ck">ͨ��ѧ��</a> </li>
					</logic:equal>
					<!--end����ѧԺ-->
					 </logic:equal>
			    </ul>
			  </div>			 
			 <!--��ѯ����-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="refreshForm('/xgxt/post_stu_check.do?go=go')">
							��ѯ
						</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										����
							 		</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
					<th>�꼶</th>
					<td>
						<html:select property="nj" style="width:180px" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
		      		<th>ѧ��</th>
					<td>
						<html:select property="xn" style="width:180px;" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
							styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
					<th>���</th>
					<td>
						<html:select property="nd" style="width:180px;" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
							styleId="nd">
							<html:options collection="xnList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>					
				</tr>
				<tr>
					
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<!-- ѧԺ�û��б���ʾ����ѧԺ�û��б���ʾ -->
						<logic:equal value="xy" name="userType">
							<logic:equal value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="true" name="isFdy">
								<logic:equal value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr">
									<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()" disabled="true">
								<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xydm" id="xydm" value="<bean:write name="userDep"/>"/>
								</logic:notEqual>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
					</td>
					<th>רҵ</th>
					<td>
						<html:select property="zydm" style="width:180px" styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>�༶</th>
					<td>
						<html:select property="bjdm" styleId="bj" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>���˵�λ</th>
					<td>
						<html:select property="yrdwdm" onchange="loadGwmcxx('xmdm','xn','nd','xq')" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm"
								labelProperty="yrdwmc" />
						</html:select>
					</td>
		      		<th>��λ����</th>
					<td>
						<html:select property="gwxz" styleId="gwxzdm" onchange="loadGwmcxx('xmdm','xn','nd','xq')" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm"
								labelProperty="gwxzmc" />
						</html:select>
					</td>
					<th>��λ����</th>
					<td>
							<html:text property="xmdm" style="width:180px"></html:text>
					</td>					
				</tr>
				<!--�㽭�Ƽ�ѧԺ-->
				<logic:equal value="11057" name="xxdm">
				<tr>
					<th>���˵�λ���</th>
					<td>
						<html:select property="xyyj" styleId="xyyj" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
		      		<th>ѧУ���</th>
					<td>
						<html:select property="xxyj" styleId="xxyj" style="width:180px;">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<th></th>
					<td>
						
					</td>					
				</tr>
				</logic:equal>
				<!--end�㽭�Ƽ�ѧԺ-->
			  </tbody>
			</table>				
		</div>		
		</div>
		<div class="formbox">
			<h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
					 <font color="blue">��¼����${rsNum} &nbsp;&nbsp;��ʾ��˫��һ�пɲ鿴��ϸ��Ϣ�����ɸı����״̬����ɫ��ʾͨ������ɫ��ʾ��ͨ��������Ϊδ��ˣ�</font>
				</logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
			       <td>
						<input type="checkbox" name="all" value="all" onclick="chec()"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="3">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap="nowrap">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
			    		<logic:iterate name="rs" id="s">
						<tr onclick="rowMoreClick(this)"
							style="cursor:hand;background-color:
						    <logic:iterate id="v" name="s" offset="0" length="1">
						    <bean:write name="v"/>
						    </logic:iterate> 
						     "
							ondblclick="chkPriseOne('/xgxt/postStuChkOne.do?act=view&pkVal=')">
							<td>
								<!-- �����ж����ڿ���ѧУ�û���ʼ��˺�,���˵�λ�����ٽ������(�㹤��֮��ѧԺ) -->
								<logic:equal value="13275" name="xxdm">
								<logic:equal value="xy" name="userType" scope="request">
									<logic:iterate id="v" name="s" offset="12" length="1">
										<logic:equal value="ͨ��" name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" disabled="disabled" />
											</logic:iterate>
										</logic:equal>
										<logic:equal value="��ͨ��" name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" disabled="disabled"/>
											</logic:iterate>
										</logic:equal>
										<logic:equal value="δ���" name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
											</logic:iterate>
										</logic:equal>
										<logic:empty name="v">
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
											</logic:iterate>
										</logic:empty>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="request">
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
									</logic:iterate>
								</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="13275" name="xxdm">
									<logic:equal value="12036" name="xxdm">
										<input type="checkbox" <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> name="pkV" value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
									</logic:equal>
									<logic:notEqual value="12036" name="xxdm">
										<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="checkbox"  name="pkV" value="<bean:write name="v"/>" />
										</logic:iterate>
									</logic:notEqual>
								</logic:notEqual>
								
								<logic:iterate id="v" name="s" offset="6" length="1">
									<input type="hidden" name="xhV" value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="3" length="4">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="7">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>				
				<logic:present name="showbjlh">
					<tfoot>
					<font color="red">ͨ����˵�¼�ø�λ��
					<logic:iterate id="tsgw" name="tsgwList">
					<bean:write name="tsgw" property="gwdm"/>
					<bean:write name="tsgw" property="rs"/>��
					</logic:iterate>
					</font>
					</tfoot>
				</logic:present>				
			   </table>
			   </div>
				<!--��ҳ��ʾ-->
				   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</logic:notEmpty>
		</div>	
		<logic:present name="showbjlh">
		<logic:equal value="true" name="result">
			<script>
				alert("�ɹ�¼�ã�");					
			</script>
			<logic:notEmpty name="mes">
			<input id="mes" name="mes" value="${mes}" type="hidden"/>
			<script>
				alert(document.getElementById('mes').value);
				document.getElementById("search_go").click();
			</script>
			</logic:notEmpty>
			<script>
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
		<logic:notEmpty name="mes">
		<input id="mes" name="mes" value="${mes}" type="hidden"/>
		<script>
			alert(document.getElementById('mes').value);
			document.getElementById("search_go").click();
		</script>
		</logic:notEmpty>
		<logic:empty name="mes">
		<script>
			alert('����ʧ��!');
			document.getElementById("search_go").click();
		</script>
		</logic:empty>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("��ѡ��Ҫ¼�õ�ѧ����");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		</logic:present>
		
		<logic:notPresent name="showBjlh">
		<logic:present name="result">
			<logic:equal value="true" name="result">			
				<logic:notEmpty name="mes">
				<input id="mes" name="mes" value="${mes}" type="hidden"/>
				<script>
					alert(document.getElementById('mes').value);
				</script>
				</logic:notEmpty>
				<script>
					alert("�����ɹ���");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<logic:notEmpty name="mes">
			<input id="mes" name="mes" value="${mes}" type="hidden"/>
			<script>
				alert(document.getElementById('mes').value);
			</script>
			</logic:notEmpty>
			<logic:empty name="mes">
			<script>
				alert('�����ɹ�!');
			</script>
			</logic:empty>
		</logic:equal>
		</logic:present>
		</logic:notPresent>
		
		<div id="tmpdiv"></div>			
		</html:form>
		<!--ҳ�������棺-->
		<script language="javascript" defer="defer">
		var b = false;;
		if(document.getElementById('rsTable')){
			for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
		  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
		  			b = true;
		  			break;
		  		}
		  	}
		  	if (b) {
		  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
		  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
		  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
		  		    var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
		  			xhTd.innerHTML = html_content;
		  		}
		  	}
		}
		</script>
</body>
</html>
                                                                                                   
