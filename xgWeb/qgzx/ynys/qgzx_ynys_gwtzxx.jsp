<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>	
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>	
	<script language="javascript" src="js/qgzxFunction.js"></script>	
	<script language="javascript">
		function exportData(){
			url = "qgzxGwgl.do?method=exportGwtzxx";
			var eleArr = ['xn','xq','nj','gwdm','gwxz'];
			
			url += "&xydm=" + val('xy');
			for(var i=0; i<eleArr.length; i++){
				url += "&" + eleArr[i] + "=" + val(eleArr[i]);
			}
			window.open(url);
		}
	</script>
	<body onload="xyDisabled('xy')">		
		<html:form action="/qgzx_work_adjust" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
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
					<!--��дȨ-->
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="viewInfo('add','qgzx_work_adjustInfo.do')" class="btn_zj">�� ��</a> </li>
						<li> <a href="#" onclick="viewInfo('del','qgzx_work_adjustInfo.do')" class="btn_sc">ɾ ��</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					</logic:equal>
					<!--end��дȨ-->
					<li> <a href="#" onclick="dataExport()" class="btn_dc">��������</a> </li>
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
								onclick="allNotEmpThenGo('/xgxt/qgzx_work_adjust.do')">
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
						<html:select property="xn" style="width:180px" onchange="loadGwmcxx('gwdm','xn','nd','xq')" 
							styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
					<th>ѧ��</th>
					<td>
						<html:select property="xq" style="width:180px" onchange="loadGwmcxx('gwdm','xn','nd','xq')"
							styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>						
					<th>�꼶</th>
					<td>
						<html:select property="nj" style="width:180px;" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>				
				</tr>						
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>��λ����</th>
					<td>
						<html:select property="gwxz" styleId="gwxz" style="width:180px" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm"
								labelProperty="gwxzmc" />
						</html:select>
					</td>
					<th>��λ����</th>
					<td>
						<html:select property="gwdm" styleId="gwdm" style="width:180px">
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
					<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������Ե�����λ��������ͷ��������</font>
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
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;"
							ondblclick="viewInfo('modi','qgzx_work_adjustInfo.do')">
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
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxForm"></jsp:include>
			    <!--��ҳ��ʾ-->
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
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
                                                                                                   
