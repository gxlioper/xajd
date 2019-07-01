<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">	
	
	function updateHdfb(type){
		if(type == "add"){
			showTopWin('/xgxt/zgddShgzHdfb.do?method=hdfbUpdate&doType='+type,500,430)
		}else if(type == "edit" || type == "view"){
			if(curr_row == null){
				alert('��ѡ��Ҫ�޸ĵ���Ϣ!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/zgddShgzHdfb.do?method=hdfbUpdate&doType='+type+'&pk='+pk,500,430)
			}
		}else if(type == "del"){
			if(curr_row == null){
				alert('��ѡ��Ҫɾ������Ϣ!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				if (confirm("ȷ��Ҫɾ���ôλ������\n���\"ȷ��\"��ɾ���ôη��������\"ȡ��\"��������ɾ����")) {
					showTips('���������У���ȴ�......');
					refreshForm('/xgxt/zgddShgzHdfb.do?method=hdfbManage&doType='+type+'&pk='+pk)
				}
			}
		}
	}
	
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getZbrDAO.js'></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zgddShgzHdfb" method="post">
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
	
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="updateHdfb('add')"
									class="btn_zj">�� ��</a>
							</li>
							<li>
								<a href="#"
									onclick="updateHdfb('edit')"
									class="btn_xg">�� ��</a>
							</li>
							<li>
								<a href="#"
									onclick="updateHdfb('del')"
									class="btn_sc">ɾ ��</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData();" class="btn_dr">��������</a>
							</li>
							<li>
								<a href="#" onclick="dataExport()" class="btn_dc">��������</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
									<button class=""  id="search_go"
										onclick="allNotEmpThenGo('/xgxt/zgddShgzHdfb.do?method=hdfbManage')">
										��ѯ
									</button>
										<input type="hidden" name="go" value="a" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									�����
								</th>
								<td>
									<html:select property="hddm"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="hdList" property="hddm"
											labelProperty="hdmc" />
									</html:select>	
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" >
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>	
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��ʼʱ��
								</th>
								<td>
									<html:text  property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');"/>
								</td>
								<th>
									����ʱ�䣺	
								</th>
								<td>
									
									<html:text  property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jssj','y-mm-dd');"/>	
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
	<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
			 		��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="center"
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="updateHdfb('view')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=zgddShgzForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				//alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				//alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript">
			if ($('choose')) {
				$('choose').className="hide";
			}
					</script>
	</body>
</html>
