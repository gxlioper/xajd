<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self"/>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
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
	
	function addInfo(){
		if(curr_row == null){
			alert("�˲�����Ҫ��ѡ�е��У�����Ҫ��ӱ�ע���У�");
			return false;
		}
		showTopWin("addStuInfo.do?xh=" + curr_row.cells[1].innerText,400,300,false);
	}
	
	function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
      }
    }
    
    function del(url){
		var RowsStr="";
		if(curr_row == null || typeof(curr_row) == undefined){
		alert("��ѡ��Ҫɾ���ļ�¼����");
		return false;
	}
	    RowsStr=curr_row.cells[0].innerText.replace(/^\s+/g,"").replace(/\s*$/g,"");
	    alert(RowsStr);
		document.forms[0].delPk.value = RowsStr;
		document.forms[0].action=url;
	    document.forms[0].submit();
}
function check_user()
	{
		var user=document.all['userType'].value;
		if("xy"==user)
		{
			document.getElementById('xydm').disabled=true;
		}
		else if("xx"==user)
		{
			document.getElementById('xydm').disabled=false;
		}
	}
	function xy_dataExport1()
	{
		var url='/xgxt/expData.do?tableName=view_xsjbxx';
		var xydm=document.getElementById('xydm').value;
		url=url+'&xydm='+xydm;
		dataExport1(url);
	}
</script>
	</head>
	<body onload="check_user()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>

			<html:form action="/stu_info_query.do?method=stuInfo" method="post">
			<input type="hidden" id="xxdm" name="xxdm" 
				value="<bean:write name="xxdm" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userSpecType" scope="request"/>" />
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>˼����� - ����ά�� - ��Ա��Ϣ</a>
					</p>
				</div>
					<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<logic:equal name="xxdm" value="10402">
									<li><a href="#" class="btn_zj" onclick="showTopWin('member_modi.do?doType=add',600,400)">����</a></li>
									<li><a href="#" class="btn_sc" onclick="if(curr_row == null){alert('��ѡ��Ҫɾ����ѧ������!');return false;}refreshForm('member_Del.do?delPk='+curr_row.cells[0].getElementsByTagName('input')[0].value)">ɾ��</a></li>
								</logic:equal>
								<li><a href="#" class="btn_xg" onclick="ljsdaUpdate('member_modi.do?stu_id=',600,400)">�޸�</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">����</a></li>
							</ul>
						</div>
						</div>
					</logic:equal>
					
						<input type="hidden" id="tableName" name="tableName" value="view_tyxxb" />
						<input type="hidden" id="realTable" name="realTable" value="tyxxb"/>
					<input type="hidden" id="delPk" name="delPk" value="pk" />
						
					<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
							 <button type="button" id="search_go"
								onclick="allNotEmpThenGo('/xgxt/member_List.do');">
								��ѯ
							 </button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			�� ��
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     			</tfoot>
						<tbody>
							<tr>
								<th>
									�꼶��</th>
									<td><html:select property="nj" style="width:90px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select></td>
									<th><bean:message key="lable.xsgzyxpzxy" />��</th>
									<td>
									<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									<html:select property="xydm" style="width:180px" styleId="xy" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select></td>
									
									<th>רҵ��</th>
									<td>
									<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select></td>
							</tr>
							<tr>
								<th>
									�༶��</th>
									<td><html:select property="bjdm" style="width:120px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select></td>
									<th>
									<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
									ѧ�ţ�</th>
									<td><html:text property="xh" /></td>
									<th>������</th>
									<td><html:text property="xm" />
								</td>
							</tr>
						</tbody>
					</table>
					</div>
			
				
				<div class="formbox">
			    	<logic:empty name="rs">
					    <h3 class="datetitle_01">
					    <span>
					    	��ѯ���&nbsp;&nbsp;
								<font color="red">δ�ҵ��κμ�¼��</font> 
					    </span>
					    </h3>
			 		 </logic:empty>
				<logic:notEmpty name="rs">
						
						    <h3 class="datetitle_01">
					    	<span>
					    		��ѯ���&nbsp;&nbsp;
									<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						    </span>
						    </h3>
						<table width="100%" id="rsTable" summary="" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">

									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="ljsdaUpdate('member_modi.do?stu_id=',600,400)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						
						<!--��ҳ��ʾ-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					  <!--��ҳ��ʾ-->
						<script type="text/javascript">
								$('choose').className="hide";
						</script>
				</logic:notEmpty>
				</div>
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("����ʧ��!");
					</script>
				</logic:equal>
			</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
