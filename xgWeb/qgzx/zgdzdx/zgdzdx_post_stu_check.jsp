<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>			
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>	
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function batch(yesNo){
			var pkString = "";
			var xhString = "";
			var userName = document.getElementById("userName").value;
			if(Rows[0]==null){
				alert('��ѡ��Ҫ�����ļ�¼��');
				return false;
			}
			for (i=0; i<Rows.length; i++){ //�����ַ���
    			pkString +=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
    			xhString +=Rows[i].cells[5].innerText+"!!SplitOneSplit!!";
			}
			if (confirm("ȷ��Ҫ����������")) {
				if(yesNo!=null && yesNo=="pass"){
				cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
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
						    if($("chkpass")){$("chkpass").disabled=true;}
						}						
					}
				});
				}else{
					refreshForm('qgzxLogic.do?method=postStuBatchAuditing&pkString=' + pkString + '&type=' + yesNo + '&xhString=' + xhString);
                       if($("chknopass")){$("chknopass").disabled=true;}
				}
			}
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
	</script>
</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/post_stu_check" method="post">
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="${pk}" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
				
			<logic:equal value="false" name="flag">
				<br/><br/>
				<center>���ڲ��ڿ����ʱ�䷶Χ�ڣ����ɽ�����˲�����</center>
			</logic:equal>	
				
			<logic:equal value="true" name="flag">	
			<logic:notEmpty name="errMsg">
				<br/><br/><div align="center"><font color="red"><b>${errMsg}</b></font></div>
			</logic:notEmpty>
			<logic:empty name="errMsg">	
			
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					<!-- �������ϴ�ѧ -->
					<logic:present name="showbjlh">
						<li> <a href="#" onclick="refreshForm('/xgxt/postStuChkBatch.do');" class="btn_sh">¼ ��</a> </li>
					</logic:present>
				    <logic:notPresent name="showbjlh">
						<li> <a href="#" onclick="batch('pass');" class="btn_shtg">���ͨ��</a> </li>
						<li> <a href="#" onclick="batch('nopass');" class="btn_shbtg">��˲�ͨ��</a> </li>
					</logic:notPresent>
					<!-- ����ɽ��ѧ -->
					<logic:equal value="10419" name="xxdm">
					<%-- �Ӳ���zdsh ���� �ڲ�ѯʱ�����Զ���˵����� --%>
						<li> <a href="#" onclick="auto_check_stu();" class="btn_sh">�Զ����</a> </li>
					</logic:equal>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="refreshForm('/xgxt/post_stu_check.do?go=go')">
									��ѯ
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>ѧ��</th>
						<td>
							<html:select property="xn" style="width:120px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>								
						</td>
						<th>���</th>
						<td>
							<html:select property="nd" style="width:90px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>		
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" style="width:90px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>			
						</td>
					  </tr>
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" style="width:90px;padding-left:80px" >
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>	
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xh"/>		
						</td>
						<th>����</th>
						<td>
							<html:text property="xm"/>		
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:230px" styleId="xy" value="${userDep}">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>���˵�λ</th>
						<td>
							<html:select property="yrdwdm" 
								onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>		
						</td>
						<th>��λ����</th>
						<td>
							<html:text property="xmdm" style="width:180px"></html:text>
						</td>
					  </tr>					  
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
							<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ����ˣ�������ͷ��������</font> 
				 		 </logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr" offset="2">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
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
								<logic:iterate id="v" name="s" offset="1" length="1">
									<logic:present name="showbjlh">
										<input type="checkbox" name="gwxx" value="<bean:write name="v"/>"/>
									</logic:present>
									<logic:notPresent name="showbjlh">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:notPresent>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>					
			    </tbody>
				</table>
				<logic:present name="showbjlh">
					<table class="dateline">
					<tr>
						<td>
						<font color="red">ͨ����˵�¼�ø�λ��
						<logic:iterate id="tsgw" name="tsgwList">
						<bean:write name="tsgw" property="gwdm"/>
						<bean:write name="tsgw" property="rs"/>��
						</logic:iterate>
						</font>
						</td>
					</tr>
					</table>
				</logic:present>
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
<%--						document.getElementById("search_go").click();--%>
					</script>
					</logic:notEmpty>
					<script>
<%--						document.getElementById("search_go").click();--%>
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<logic:notEmpty name="mes">
				<input id="mes" name="mes" value="${mes}" type="hidden"/>
				<script>
					alert(document.getElementById('mes').value);
<%--					document.getElementById("search_go").click();--%>
				</script>
				</logic:notEmpty>
				<logic:empty name="mes">
				<script>
					alert('����ʧ��!');
<%--					document.getElementById("search_go").click();--%>
				</script>
				</logic:empty>
			</logic:equal>
			</logic:present>
			</logic:notPresent>
			
			<div id="tmpdiv"></div>
			</logic:empty>	
			</logic:equal>
		
		</html:form>		
	</body>
</html>
                                                                                                   
