<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>	
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript">
	function batch(yesNo){
		var pkString = "";
		if(Rows[0]==null){
			alert('��ѡ��Ҫ�����ļ�¼��');
			return false;
		}
		for (i=0; i<Rows.length; i++){ //�����ַ���
   			pkString +=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
		}
		if (confirm("ȷ��Ҫ����������")) {
		    refreshForm('hgsqsh.do?pkString=' + pkString + '&type=' + yesNo + '&go=go');
		}
	}
	</script>

	<body onload="xyDisabled('xy')">
		<html:form action="/qgzxHgsqsh" method="post">
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips}</a>
				</p>
			</div>

			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
						<li> <a href="#" onclick="batch('pass');" class="btn_shtg">���ͨ��</a> </li>
						<li> <a href="#" onclick="batch('nopass');" class="btn_shbtg">��˲�ͨ��</a> </li>
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
									onclick="allNotEmpThenGo('qgzxHgsqsh.do')">
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
							<html:select property="xn" style="width:120px" onchange="loadGwmcxx('gwdm','xn','nd','xq')"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" style="width:90px" onchange="loadGwmcxx('gwdm','xn','nd','xq')"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:230px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>	
						</td>
						<th>��λ����</th>
						<td>
							<html:select property="gwxz" styleId="gwxz" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<th>��λ����</th>
						<td>
							<html:select property="gwdm" styleId="gwdm">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdm"
									labelProperty="gwdm" />
							</html:select>
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
							<font color="blue">��ʾ��˫����¼�����޸ģ�������ͷ��������</font> 
				 		 </logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowMoreClick(this,'',event)"
							style="cursor:hand;"
							ondblclick="viewInfo('modi','qgzxhgsqModi.do')">
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="2">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				</logic:notEmpty>
				</div>
			<div id="tmpdiv"></div>
		</html:form>

		<logic:equal value="ok" name="result" scope="request">
				<script>
					alert("�����ɹ���");					
					document.getElementById('search_go').click();
				</script>
		</logic:equal>
		<logic:equal value="no" name="result" scope="request">
				<script>
					alert("����ʧ�ܣ�");
					document.getElementById('search_go').click();
				</script>
		</logic:equal>		
	</body>
</html>
                                                                                                   
