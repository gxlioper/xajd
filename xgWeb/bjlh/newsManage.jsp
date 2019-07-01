<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
	href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function ljsdaUpdate(url,w,h){	
	var pk="";	
	if(curr_row == null ){
			alert("��ѡ��һ�м�¼��\n����һ�м���!");
			return false;
		} 		
	pk= curr_row.cells[0].getElementsByTagName("input")[0].value;			
	url+=pk;
	
	if(w==""||w==null){
		w = 800;
}
	if(h==""||h==null){
		h = 700;
	}
	showTopWin(url,w,h);		
}

	function allowOper(){
		var xxdm = document.getElementById("xxdm").value;
		var userName = document.getElementById("userName").value;
		var userType = document.getElementById("userType").value;
		if(xxdm!=null && xxdm == "10827"){//��ɳ����
			var puber = curr_row.cells[2].innerText.trim();
			if(puber==userName){
				document.getElementById("btn").style.display='';
			}else{
				document.getElementById("btn").style.display='none';
			}
			if(userType=="admin"){
				document.getElementById("btn").style.display='';
			}
		}
	}
</script>
</head>
	<body>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ���Ź��� - ����ά��</a>
				</p>
			</div>
		
	
			<html:form action="/newsManage" method="post">
			<input type="hidden" id="parameter" name="parameter" value="newsManage" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="if(curr_row == null){alert('��ѡ��Ҫ�޸ĵ���!');return false;}else{showTopWin('/xgxt/newsManageUpdate.do?&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,800,640)}" class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#"
									onclick="if(curr_row == null){alert('��ѡ��Ҫɾ������!');return false;}else{if(confirm('ȷʵҪɾ����ǰ������')){refreshForm('/xgxt/newsManage.do?del=true&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value)}};"
									class="btn_sc"> ɾ�� </a>
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
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/newsManage.do')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									����ģ��
								</th>
								<td>
									<html:select name="commanForm" property="xmdm" styleId="mxdx">
									<html:option value=""></html:option>
									<html:options collection="modList" property="gnmkdm"
										labelProperty="gnmkmc" />
									</html:select>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" />
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									<html:text property="pubTime" styleId="pubtime" style="cursor:hand;"
									onclick="return showCalendar('pubtime','y-mm-dd');" />
								</td>
							</tr>
							</tbody>
						</table>
						</div>
						
						
						
						<div class="formbox">
								<h3 class="datetitle_01">
									<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
											<font color="red">δ�ҵ��κμ�¼��</font>
										</logic:empty> 
										<logic:notEmpty name="rs">
											<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
										</logic:notEmpty>
									</span>
								</h3>

								<logic:notEmpty name="rs">
								<div class="con_overlfow">
									<table summary="" class="dateline tablenowrap" align="" width="100%">
										<thead>
											<tr>
												<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this);" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
											</tr>
										</thead>
										<tbody>
											<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);allowOper()" style="cursor:hand" ondblclick="showTopWin('/xgxt/newsContent.do?newsId='+curr_row.cells[0].getElementsByTagName('input')[0].value,800,640)" >	
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
								</logic:notEmpty>
							</div>
			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("ɾ���ɹ�!");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("ɾ��ʧ��");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
