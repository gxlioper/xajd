<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
		
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script language="javascript">		
		function check_users()
		{
			var userType=document.all['userType'].value;			
			 if("stu"==userType)
			{
				document.getElementById('bjdm').disabled=true;
			}
		}
		
		function check_user_commUpdate()
		{
			var userType=document.all['userType'].value;
			 if("stu"==userType)
			{
				return false;
			}
			else
			{
				commUpdate('address_book.do?doType=view&bjdm=',600,400);
			}
		}
	</script>
</head>
	<body onload="check_users()">
			<html:form action="/address_book" method="post">
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />

				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${tips}</a>
					</p>
				</div>
				<div class="toolbox">
				  <!--��дȨ-->
				  <logic:equal value="yes" name="writeAble">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
					   <ul>
							<li> <a href="#" onclick="showTopWin('address_book.do?doType=view',600,400);" class="btn_zj">�� ��</a> </li>
							<li> <a href="#" onclick="commUpdate('address_book.do?doType=view&bjdm=',600,400);" class="btn_xg">�� ��</a> </li>
							<li> <a href="#" onclick="commDel('address_book.do?doType=del&bjdm=');" class="btn_sc">ɾ ��</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
							<li> <a href="#" onclick="dataExport();" class="btn_dc">��������</a> </li>
							<li> <a href="#" onclick="expTab('rsTable','������ͨѶ¼','');" class="btn_dy">��ӡ�б�</a> </li>
					   </ul>
				  </div>
			      </logic:equal>

				  <logic:notEqual value="student" name="user">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/address_book.do')">
									��ѯ
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
						<th>����</th>
						<td>
							<html:text property="xm" />
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<logic:equal name="userType" value="xy" scope="session">
								<html:select property="xydm" style="width:180px"
									onchange="initZyList();initBjList();"
									disabled="true" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual name="userType" value="xy" scope="session">
								<html:select property="xydm" style="width:180px"
									onchange="initZyList();initBjList();" styleId="xy">
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
							<html:select property="bjdm" styleId="bj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  </tbody>
					</table>
				</div>
				</logic:notEqual>
				</div>

				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
						 <logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫���ɲ鿴��ϸ��Ϣ��������ͷ��������</font>
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
						<tr onclick="rowOnClick(this)" style="cursor:hand"
							ondblclick="check_user_commUpdate()">
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									<input type="hidden" value="<bean:write name="v"/>" />
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				  <script type="text/javascript">
					  $('choose').className="hide";
				  </script>
				</logic:notEmpty>
				</div>

				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
				<SCRIPT>
					alert("�����ɹ�!");
					window.close();				 	
				 	document.getElementById("search_go").click();
				</SCRIPT>
				</logic:equal>
				<logic:equal value="false" name="result">
				<SCRIPT>
				 	alert("����ʧ��!");
				</SCRIPT>
				</logic:equal>
				</logic:notEmpty>
			</html:form>
	</body>
</html>
