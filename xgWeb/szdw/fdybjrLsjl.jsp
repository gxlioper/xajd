<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/szdwfzjy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="userName" id="userName" value="${userName }"/>
			<input type="hidden" name="userType" id="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable }"/>
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
			<div class="toolbox">
			 <!-- ��ť -->
			  <logic:equal value="yes" name="writeAble">
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="wjcfDataExport('/xgxt/szdwfzjy.do?method=szdwExp')" class="btn_dc"> �������� </a> </li>
				    </ul>
				 </div>
				</logic:equal>			 
			 <div class="searchtab">			
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="" />
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/szdwfzjy.do?method=szdwBbLy');">
			              	�� ѯ
			              </button>
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:select property="queryequals_xn" style="" onchange="">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<html:radio property="tableName" value="view_fdybbjls">����Ա��ʷ��Ϣ</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;
      						</th>
      						<td>
      							<html:radio property="tableName" value="view_bzrbbjls">��������ʷ��Ϣ</html:radio>
      						</td>
							</tr>
							<tr>
								<th>
										<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
										<html:select property="queryequals_xydm" style="width:150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
										<html:select property="queryequals_zydm" style="width:150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
										<html:select property="queryequals_bjdm" style="width:150px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										ְ����
									</th>
									<td>
										<logic:equal name= "fdyQx" value="true" scope="session" >
										<html:text property="querylike_zgh" styleId="zgh" style="" value="${userName}" maxlength="20" readonly="true"/>
										</logic:equal>
										<logic:notEqual name="fdyQx" value="true" scope="session" >
										<html:text property="querylike_zgh" styleId="zgh" style="" maxlength="20"/>
										</logic:notEqual>
									</td>
									<th>
										����Ա����
									</th>
									<td>
										<html:text property="querylike_xm" styleId="xm" style="" maxlength="20"/>
									</td>
									<td colspan="2">
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
					 		 	��¼����
								<bean:write name="rsNum" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��������ͷ��������</font>
					 		 </logic:notEmpty>
					    </span>
					    </h3>				
						<logic:notEmpty name="rs">
							<div class="con_overlfow">
							 <table summary="" class="dateline tablenowrap" width="100%">
								<thead>	
									<tr style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>

								<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" >
										<logic:iterate id="v" name="s" offset="0">
										<td>
											<bean:write name="v" />	
										</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							</div>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=szdwForm"></jsp:include>
							 <script type="text/javascript">
								$('choose').className="hide";
							</script>							
					</logic:notEmpty>
				</div>
			</logic:empty>
		</html:form>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
					}
				</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
