<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script language="javascript">	
		function sendXx(){	
				if(window.opener == undefined){					 				
					var url = window.dialogArguments.document.forms[0].url.value;
					url+="&xh="+curr_row.getElementsByTagName('input')[0].value;
					window.dialogArguments.document.forms[0].action = url;
					window.dialogArguments.document.forms[0].submit();
				}else{
					var url = window.opener.document.forms[0].url.value;
					url+="&xh="+curr_row.getElementsByTagName('input')[0].value;
					window.opener.document.forms[0].action = url;
					window.opener.document.forms[0].submit();
				}
			window.close();
		}			
			jQuery(function(){
				xyDisabled('xy');
			})
		</script>
	</head>
	<body >	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ϣά�� - ${lx}��Ϣ</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/czxxDtjsDyxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="lx" name="lx" value="${lx }" />
			<input type="hidden" id="zd" name="zd" value="${zd }" />
			<input type="hidden" id="va" name="va" value="${va }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
				<div class="toolbox">
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/czxxDtjsDyxx.do?method=xsxxManage')">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" style="" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��	
								</th>
								<td>
									<html:text property="xh" style="width:85px" maxlength="20"/>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" style="width:85px" maxlength="20"/>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									Ժϵ
								</th>
								<td>
									<html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ	
								</th>
								<td>
									<html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width: 150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->	
								<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="sendXx()">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v" />" />
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
							<!--���� end-->
						</table>
					<!--��ҳ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=czxxDtjsForm"></jsp:include>
					<!--��ҳend-->
					</logic:notEmpty>
				</div>							
			</div>
		</html:form>
	</body>
</html>
