<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
		function batch(yesNo){
			var RowsStr="!!";	
			var realTable = document.getElementById('realTable').value;	
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	}
			}
						
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ�����ļ�¼��");
				return false;
			}
			if (confirm("ȷ��Ҫ����������")) {
			    refreshForm('qgzxLogic.do?method=postBatchAuditing&type=' + yesNo);
			}
		}
		
		function del(url){
			var pkString = "";
			if(Rows[0]==null){
				alert('��ѡ��Ҫɾ���ļ�¼��');
				return false;
			}

			for (i=0; i<Rows.length; i++){ //�����ַ���
    			pkString +=Rows[i].getElementsByTagName("input")[0].value+"!!";
			}
			if (confirm("��λɾ��������ø�λ��ѧ����Ϣ��¼Ҳ��ɾ����ȷ��Ҫ����ɾ����λ��")) {
			    refreshForm('qgzxLogic.do?method=delPost&pkString=' + pkString);
			}
		}
		
		function audit(){
			var xxdm = val('xxdm');
			var url = '/xgxt/postChkOne.do?act=view&pkVal=';
			if(xxdm == '10395'){//����ѧԺ
				url = "gwfb.do?method=gwfbsh&pkValue=";
			}
			chkPriseOne(url,700,620)
		}
	</script>
</head>
<body>
	<html:form action="/post_check" method="post">
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
		<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
		<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
		<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
		<input type="hidden" id="bmlb" name="bmlb" value="xy" />
		<input type="hidden" id="userName" name="userName" value="${userName}" />
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
					<%--�������ϴ�ѧ--%>
		          	 <logic:equal value="11417" name="xxdm">
					 	<li> <a href="#" onclick="del();" class="btn_sc">ɾ ��</a> </li>
		           	</logic:equal>
					<li> <a href="#" onclick="batch('pass');" class="btn_shtg">���ͨ��</a> </li>
					<li> <a href="#" onclick="batch('nopass');" class="btn_shbtg">��˲�ͨ��</a> </li>
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
						  <button type="button" class="btn_cx" id="search_go"
								onclick="refreshForm('/xgxt/post_check.do?go=go')">
								��ѯ
						  </button>
						  <button type="button" id="cz"
							onclick="czSearchCond('nd-xn-xq-yrdwdm-gwxzdm-shjg-xmdm-xqdm-gwflag');return false;">
							����					    	
                          </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
				  <tbody>
			      	<tr>
			      		<th>ѧ��</th>
						<td>
							<html:select property="xn" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>���</th>
						<td>
							<html:select property="nd" styleId="nd" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" styleId="xq" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
			      		<th>���˵�λ</th>
						<td>
							<html:select property="yrdwdm" styleId="yrdwdm" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm" labelProperty="yrdwmc"/>
							</html:select>
						</td>
						<logic:notEqual value="11078" name="xxdm">
						<th>��λ����</th>
						<td>
							<html:select property="gwxz" style="width:180px" styleId="gwxzdm" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<th>��λ����</th>
						<td>
							<html:text property="xmdm" style="width:180px" styleId="xmdm"></html:text>
						</td>
						</logic:notEqual>
						<logic:equal value="11078" name="xxdm">
						<th>��λ����</th>
						<td>
							<html:select property="xmdm" style="width:180px" styleId="xmdm">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdm"
									labelProperty="gwdm" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
						</logic:equal>						
					</tr>
					<logic:notEqual value="11078" name="xxdm">
					<!--������ѧԺ-->
					<logic:notEqual value="10395" name="xxdm">
					<tr>
						<th>У��</th>
						<td>
							<html:select property="xqdm" style="width:180px" styleId="xqdm">
								<html:option value=""></html:option>
								<html:options collection="xiaoquList" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<th>��˽��</th>
						<td>
							<html:select property="shjg" style="width:180px" styleId="shjg">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:notEqual>
					<!--����ѧԺ-->
					<logic:equal value="10395" name="xxdm">
					<tr>
			      		<th>��λ���</th>
						<td>
							<html:select property="gwflag" style="width:180px" styleId="gwflag">
								<html:option value=""></html:option>
								<html:option value="xngw">У�ڸ�λ</html:option>
								<html:option value="xwgw">У���λ</html:option>
							</html:select>
						</td>
						<th>��˽��</th>
						<td>
							<html:select property="shjg" style="width:180px" styleId="shjg">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:equal>
					</logic:notEqual>
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
					<td>
						<input type="checkbox" name="all" value="all" onclick="chec()"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="3">
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
							style="cursor:hand;background-color:
							   <logic:iterate id="v" name="s" offset="0" length="1">
							   <bean:write name="v"/>
							   </logic:iterate>
							   "
							ondblclick="audit()">
							<td>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="checkbox" name="pkV" value="<bean:write name="v"/>"/>
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
			</div>
			<!--��ҳ��ʾ-->
			   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			<!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
           	
			<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert('�����ɹ���');				
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<logic:notEmpty name="mes">
			<input type="hidden" id="mes" name="mes" value="${mes}"/>
			<script>
				alert(document.getElementById("mes").value);
			</script>
			</logic:notEmpty>
			<logic:empty name="mes">
			<script>
				alert("����ʧ�ܣ�");
			</script>
			</logic:empty>
			</logic:equal>
			</logic:present>
	</html:form>
</body>
</html>
