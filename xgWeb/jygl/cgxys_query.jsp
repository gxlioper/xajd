<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
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
	<body onload="">
		<script language="javascript">

		function viewMoreadd(){
			var url = "/xgxt/cgjyxys.do?act=add";
			var info = "Status:YES;dialogWidth:800px;dialogHeight:450px;help:no;";
			showModalDialog(url, window, info);
		}
		function xz_viewMore()
			{
					var pk=curr_row.cells[0].getElementsByTagName('input')[0].value;
				    var url='/xgxt/cgjyxys.do?act=view&pk='+pk;
				    showTopWin(url,'750','350');
			}
		function viewMoredel()
			{		
					var pk=curr_row.cells[0].getElementsByTagName('input')[0].value;
				    var url='/xgxt/cgjyxys.do?act=del&pk='+pk;
				    document.forms[0].action = url;
					document.forms[0].submit();
			}
		function viewMoreaupdate(){
					var pk=curr_row.cells[0].getElementsByTagName('input')[0].value;
				    var url='/xgxt/cgjyxys.do?act=update&pk='+pk;
				    showTopWin(url,'750','350');
		}
		function querygo(){
		 	document.forms[0].action = "/xgxt/cgjyxys.do?act=go&doType=query";
		 	document.forms[0].submit();
    	}
		</script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/chgRychlist.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>

		<html:form action="/cgjyxys.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ��:��ҵ���� - ��ҵЭ�鷽�� - �ع�Э����
				</div>
			</div>
				<div class="rightcontent">

					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<input type="hidden" id="tableName" name="tableName"
							value="jygl_zxjsxxb" />
						<input type="hidden" id="realTable" name="realTable"
							value="jygl_zxjsxxb" />
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										
										&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" styleId="xh" style="width:85px"/>
									&nbsp;&nbsp;������
										<html:text property="xm" style="width:85px"></html:text>
									&nbsp;&nbsp;�ع�Э����ԭ��
							<html:select name="rsq" property="zgxysyy" style="width:85px">
								<html:option value=""></html:option>
								<html:option value="Э����">Э����</html:option>
								<html:option value="Э����ʧ">Э����ʧ</html:option>
								<html:option value="��Ϣ����">��Ϣ����</html:option>
							</html:select>
								Э����ԭ��
							<html:select name="rsq" property="xyjcyy" style="width:100px" >
								<html:option value=""></html:option>
								<html:option value="�������ϲ���">�������ϲ���</html:option>
								<html:option value="�������ϴ�">�������ϴ�</html:option>
								<html:option value="������Ϣ����">������Ϣ����</html:option>
								<html:option value="���˵�λ��Ϣ����">���˵�λ��Ϣ����</html:option>
								<html:option value="���˵�λ���ܲ�����Ϣ����">���˵�λ���ܲ�����Ϣ����</html:option>
							</html:select>
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<button class="button2" style="height: 40px" id="search_go"
											onclick="querygo();">
											��ѯ
										</button>
									</td>

								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��ʾ��¼����
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"><logic:present name="qssj">(<bean:write
											name="qssj" />--</logic:present> <logic:present name="zzsj">
										<bean:write name="zzsj" /> Υ������)</logic:present>
									��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;��סCtrl���Զ�ѡ</font>
							</legend>

							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor: hand">
										<logic:notPresent name="xsjxjb">
											<logic:iterate id="tit" name="topTr" offset="1" length="5">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
											<logic:iterate id="tit" name="topTr" offset="7" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
												<logic:iterate id="tit" name="topTr" offset="9" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:notPresent>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
												<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand"
													ondblclick="xz_viewMore(this)">
													<td>
														<logic:iterate id="v" name="s" offset="0" length="1">
															<input type="hidden" id="jzid" name="jzid" value="<bean:write name="v"/>" />
														</logic:iterate>
														<logic:iterate id="v" name="s" offset="1" length="1">
															<bean:write name="v" />
														</logic:iterate>
													</td>
													<logic:iterate id="v" name="s" offset="2" length="4">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
														<logic:iterate id="v" name="s" offset="7" length="1">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
														<logic:iterate id="v" name="s" offset="9" length="1">
														<td nowrap>
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</tr>
									<!-- ��ѧ�� -->
								</logic:iterate>
							</table>
							<logic:notEqual value="12872" name="xxdm">
								<TABLE width="99%" id="rsTable1" class="tbstyle">
									<TR>
										<TD height=3></TD>
									</TR>
									<TR>
										<TD>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=zjgxJyxxForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height=3></TD>
									</TR>
								</TABLE>
								<br />
								<br />
							</logic:notEqual>

						</fieldset>
					</logic:notEmpty>
					<br />
					<br />
					<div id="toolTipLayer"
						style="position: absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px" width="100%">

								&nbsp;
								
									
											<button class="button2" onclick="viewMoreadd()"
												style="width: 80px">
												�� ��
											</button>
									
											<button class="button2"
												onclick="viewMoreaupdate();"
												style="width: 80px">
												�� ��
											</button>
									&nbsp;
								
										<button class="button2" onclick="if(confirm('ȷ��Ҫɾ��������¼��')){viewMoredel(this);return true;}else{return false;}"
											style="width: 80px">
											ɾ ��
										</button>
									

								<logic:equal value="no" name="xydel">
									<button class="button2" onclick="Alldel()" style="width: 80px">
										ȫ��ɾ��
									</button>

											<button class="button2" onclick="impAndChkData()"
												style="width: 80px">
												��������
											</button>
									<button class="button2" onclick="dataExport()"
										style="width: 80px">
										��������
									</button>
								</logic:equal>

						</div>
					</center>
				</div>

			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("�����ɹ���");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
		</logic:equal>
		
		<logic:equal name="delete" value="ok">
			<script language="javascript">
      				alert("ɾ���ɹ���");
      				document.getElementById("search_go").click();
	  		</script>
		</logic:equal>
		<logic:equal name="delete" value="no">
			<script language="javascript">
	  				alert("ɾ��ʧ��! ");
	  				document.getElementById("search_go").click();
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
					
					function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('��ѡ��Ҫ��ӡ�������У�');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
					}
					function hzyprint() {
				     	if (curr_row==null || curr_row=='') 
				     	{
				     		alert('��ѡ��Ҫ��ӡ�������ݣ�����һ�м���!');
				     		return;
				     	}
				     	 else {
				     	 	var url = 'dxjxjsp.do?method=dxjxjsp&pk=';
				     	 	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				     	 	window.open(url);
				     	 }
				     }
				     function hzyjxjmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyjxjmodi.do?pkValue='+pkValue,'670','550');
				     	}
				     } 
				     function hzzyrychmodi(){
				     	if (curr_row==null || curr_row=='') {
				     		alert('��ѡ��Ҫ������������.');
				     		return;
				     	} else {
				     		var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				     		
				     		showTopWin('hzzyrychmodi.do?pkValue='+pkValue,'610','510');
				     	}
				     }
				     function wjsjzy(url) {
				     	var RowsStr="!!SplitOneSplit!!";   
				     	if (Rows.length==0) {
				     		alert('��ѡ��Ҫ������������,��סCtrl�����Զ�ѡ!');
				     		return;
				     	}
				     	if (confirm('ȷ��Ҫ��ѡ�������ת����ʷ��Ϣ����?')) {
				     		for (i=0; i<Rows.length; i++){ 										//�����ַ���
    							RowsStr+=Rows[i].getElementsByTagName("input")[0].value+"!!SplitOneSplit!!";
							}
							showTips('���������У���ȴ�......');
							refreshForm(url+"?pkValue="+RowsStr);
				     	}
				     	return;
				     }
		</script>
	</body>
</html>
