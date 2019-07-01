<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
</head>
	<body>
			<html:form action="/stu_status_different" method="post">
				<input type="hidden" id="userType" name="userType" value="${userType}" />
				<input type="hidden" id="tableName" name="tableName" value="${tableName}" />			
				<input type="hidden" id="realTable" name="realTable" value="${realTable}" />	
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
				  <logic:empty name="sh">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
					   <ul>
							<logic:equal name="xxdm" value="13022">
								<li> <a href="#" onclick="viewAdd('/xgxt/attend_school_prove.do?doType=add&sh=no','add');" class="btn_zj">�� ��</a> </li>
							</logic:equal>

							<logic:notEqual name="xxdm" value="13022">
								<li> <a href="#" onclick="viewAdd('/xgxt/attend_school_prove.do?doType=add','add');" class="btn_zj">�� ��</a> </li>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13022">
								<li> <a href="#" onclick="if(curr_row != null){viewAdd('/xgxt/attend_school_prove.do&needSh=no','modi');return true;}else{alert('��ѡ��Ҫ�޸ĵ��У�');return false;};" class="btn_xg">�� ��</a> </li>
							</logic:equal>
							<logic:notEqual name="xxdm" value="13022">
								<li> <a href="#" onclick="if(curr_row != null){viewAdd('/xgxt/attend_school_prove.do','modi');return true;}else{alert('��ѡ��Ҫ�޸ĵ��У�');return false;};" class="btn_xg">�� ��</a> </li>
							</logic:notEqual>
								<li> <a href="#" onclick="if(curr_row != null){if(confirm('��ȷ��ɾ��������Ϣ��'))refreshForm('/xgxt/prove_query.do?doType=del&xh='+curr_row.cells[0].innerText+'&nd='+curr_row.cells[1].innerText+'&zmlx='+curr_row.cells[8].innerText);return true;}else{alert('��ѡ��Ҫɾ�����У�');return false;};" class="btn_sc">ɾ ��</a> </li>
					   </ul>
				  </div>
				  </logic:empty>
			      </logic:equal>

				  <logic:notEqual value="student" name="user">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<logic:notEmpty name="sh">
									<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/prove_query.do?doType=auditing')">
									��ѯ
								</button>
								</logic:notEmpty>
								<logic:empty name="sh">
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/prove_query.do')">
									��ѯ
								</button>
								</logic:empty>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>���</th>
						<td>
							<logic:empty name="sh">
								<html:select property="nd" style="width:180px"
									disabled="false" styleId="nd">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</logic:empty>
							<logic:notEmpty name="sh">
								<html:select property="nd" style="width:180px" disabled="true"
									styleId="nd">
									<html:option value="">
										<bean:write name="nd" />
									</html:option>
								<input type="hidden" name="nd" id="nd" value="<bean:write name="nd" />" />
								</html:select>
							</logic:notEmpty>
						</td>
						<th>֤������</th>
						<td colspan="3">
							<html:select property="zmlx" style="width:180px" styleId="zmlx">
								<html:option value=""></html:option>
								<html:options collection="typeList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					  </tr>

					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" style="width:180px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>						
						<th>ѧ��</th>
						<td>
							<logic:equal value="stu" name="userType" scope="session">
								<html:text property="xh" style="width:180px" value="${userName}" readonly="true"/>				
							</logic:equal>
						<logic:notEqual value="stu" name="userType" scope="session">
							<html:text property="xh" style="width:180px"/>				
						</logic:notEqual>
									
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" style="width:180px"/>
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:180px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
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
							<html:select property="bjdm" style="width:180px" styleId="bj">
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
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:empty name="sh">
						<logic:iterate id="tit" name="topTr" offset="1">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
						</logic:empty>
						<logic:notEmpty name="sh">
						<logic:iterate id="tit" name="topTr" offset="1" >
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
						</logic:notEmpty>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
					<logic:notEmpty name="sh">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;background-color:
						    <logic:iterate id="v" name="s" offset="0" length="1">
						    <bean:write name="v"/>
						    </logic:iterate>
						     "
							ondblclick="viewAdd('','modi')">
							<logic:iterate id="v" name="s" offset="1">
								<td align="left" style="cursor:hand">
									<input type="hidden" value="<bean:write name="v"/>" />
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:notEmpty>
					<logic:empty name="sh">
						<tr onclick="rowOnClick(this)" style="cursor:hand"
							ondblclick="viewAdd('','modi')">
							<logic:iterate id="v" name="s" offset="1">
								<td align="left" style="cursor:hand">
									<input type="hidden" value="<bean:write name="v"/>" />
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:empty>
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
			</html:form>
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ���");					
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ�ܣ�");
				</script>
			</logic:equal>
	</body>
</html>
