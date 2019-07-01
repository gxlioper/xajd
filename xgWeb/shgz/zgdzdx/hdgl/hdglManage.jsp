<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<link id="csss"/>

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">	
	
	function updateHdsh(type){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
			
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		
		if(type == "tg" || type =="wtg" || type == "del"){
			if(flag){
				var msg;
				if(type=="tg"){
					msg="��ͨ��";
				}else{
					msg="δͨ��";
				}
				if(type != "del"){
					if (confirm("������ѡ��������״̬��Ϊ"+msg+"��\n���\"ȷ��\"�������ɡ�\n���\"ȡ��\"����������ˣ�")) {
						refreshForm('/xgxt/zgddShgzHdgl.do?method=hdglManage&doType='+type)
					}
				}else{
					if (confirm("ȷ��Ҫɾ����ѡ������\n���\"ȷ��\"��ɾ�����롣\n���\"ȡ��\"��������ɾ����")) {
						refreshForm('/xgxt/zgddShgzHdgl.do?method=hdglManage&doType='+type)
					}
				}
			}else{
				alert("�빴ѡ��������Ϣ");
				return false;
			}
		}else if(type == "sh" || type == "view" ){
			if(curr_row == null){
				alert('��ѡ��Ҫ�������Ϣ!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/zgddShgzHdgl.do?method=hdglSh&pk='+pk+"&doType="+type,600,480)
			}
		}
	}
	
	function disabled() {
        if($("userType")){
            var ele="";
	        if ($("userType").value == "xy") {
	             ele ="xy";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }		      
	        }else if($("userType").value == "stu"){
	             ele ="xy-zy-bj-xh-xm";
	             var tmp = ele.split("-");
		         for (i = 0; i < tmp.length; i++) {
		 	       document.getElementById(tmp[i]).disabled = true;
		         }	        
	        }

        }
    }
	</script>
	<body onload="disabled()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getZbrDAO.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zgddShgzHdgl" method="post">
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
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
							<logic:equal name="doType" value="jg">
								<li>
									<a href="#" class="btn_ck" onclick="updateHdsh('view')"> �鿴
										</button>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="impAndChkData()"> �������� </a>
								</li>
								<li>
									<a href="#" class="btn_dc" onclick="dataExport()"> �������� </a>
								</li>
							</logic:equal>
							<logic:notEqual name="doType" value="jg">
								<logic:equal name="writeAble" value="yes">
									<logic:notEqual name="userType" value="xy">
										<li>
											<a href="#" class="btn_sh" onclick="updateHdsh('tg')"> ���ͨ�� </a>
										</li>
										<li>
											<a href="#" class="btn_sh" onclick="updateHdsh('wtg')"> ��˲�ͨ�� </a>
										</li>
										<li>
											<a href="#" class="btn_sc" onclick="updateHdsh('del')"> ɾ�� </a>
										</li>
									</logic:notEqual>
									<logic:equal name="userType" value="xy">
										<logic:equal name="needXy" value="yes">
											<li>
												<a href="#" class="btn_sh" onclick="updateHdsh('tg')"> ���ͨ�� </a>
											</li>
											<li>
												<a href="#" class="btn_sh" onclick="updateHdsh('wtg')"> ��˲�ͨ�� </a>
											</li>
										</logic:equal>
									</logic:equal>
								</logic:equal>
							</logic:notEqual>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<logic:equal name="doType" value="jg">
											<button class="" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zgddShgzHdgl.do?method=hdglHdjg')">
												��ѯ
											</button>
										</logic:equal>
										<logic:notEqual name="doType" value="jg">
											<button class="" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zgddShgzHdgl.do?method=hdglManage')">
												��ѯ
											</button>
										</logic:notEqual>
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
									<html:select property="hddm" onchange="">
										<html:option value=""></html:option>
										<html:options collection="hdList" property="hddm"
											labelProperty="hdmc" />
									</html:select>
								</td>
								<logic:notEqual name="doType" value="jg">
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xn" style="" disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="" disabled="true">
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
								</logic:notEqual>
								<logic:equal name="doType" value="jg">
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xn" style="">
											<html:option value=""></html:option>
											<html:options collection="xnVList" property="xn"
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
								</logic:equal>
							</tr>
							<tr>
							<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" style="width:85px"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" style="width:85px"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:160px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width:160px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:160px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
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
							<logic:equal name="doType" value="jg">
								<font color="blue">��ʾ��������ͷ��������,˫���鿴��ϸ</font>
							</logic:equal>
							<logic:notEqual name="doType" value="jg">
								<font color="blue">��ʾ��������ͷ��������,˫���鿴��ϸ���������</font>
							</logic:notEqual>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align="center"
							width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:equal name="doType" value="jg">
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
									</logic:equal>
									<logic:notEqual name="doType" value="jg">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" >
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" length="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<logic:equal name="userType" value="xy">
											<logic:iterate id="tit" name="topTr" offset="2" length="9">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:equal>
										<logic:notEqual name="userType" value="xy">
											<logic:iterate id="tit" name="topTr" offset="2" length="10">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</logic:notEqual>
									</logic:notEqual>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<logic:equal name="doType" value="jg">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="updateHdsh('view')">
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
													<bean:write name="v" filter="false" />
												</td>
											</logic:iterate>
										</tr>
									</logic:equal>
									<logic:notEqual name="doType" value="jg">
										<tr onclick="rowOnClick(this);" style="cursor:hand"
											ondblclick="updateHdsh('sh')">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
											</td>
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v" />" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="2" length="8">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="10" length="1">
												<td align="left">
													<bean:write name="v" filter="false" />
												</td>
											</logic:iterate>
											<logic:notEqual name="userType" value="xy">
												<logic:iterate id="v" name="s" offset="11" length="1">
													<td align="left">
														<bean:write name="v" filter="false" />
													</td>
												</logic:iterate>
											</logic:notEqual>
										</tr>
									</logic:notEqual>
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
	</body>
</html>
