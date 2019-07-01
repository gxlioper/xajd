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
		<meta name="Copyright" content="正方软件 zfsoft" />

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
			alert("请选择一行记录！\n单击一行即可!");
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
			alert("此操作需要有选中的行，请点击要添加备注的行！");
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
		alert("请选择要删除的记录！！");
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
						<em>您的当前位置:</em><a>思想教育 - 数据维护 - 团员信息</a>
					</p>
				</div>
					<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<logic:equal name="xxdm" value="10402">
									<li><a href="#" class="btn_zj" onclick="showTopWin('member_modi.do?doType=add',600,400)">增加</a></li>
									<li><a href="#" class="btn_sc" onclick="if(curr_row == null){alert('请选择要删除的学生数据!');return false;}refreshForm('member_Del.do?delPk='+curr_row.cells[0].getElementsByTagName('input')[0].value)">删除</a></li>
								</logic:equal>
								<li><a href="#" class="btn_xg" onclick="ljsdaUpdate('member_modi.do?stu_id=',600,400)">修改</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>
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
								查询
							 </button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			重 置
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     			</tfoot>
						<tbody>
							<tr>
								<th>
									年级：</th>
									<td><html:select property="nj" style="width:90px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select></td>
									<th><bean:message key="lable.xsgzyxpzxy" />：</th>
									<td>
									<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									<html:select property="xydm" style="width:180px" styleId="xy" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select></td>
									
									<th>专业：</th>
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
									班级：</th>
									<td><html:select property="bjdm" style="width:120px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select></td>
									<th>
									<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
									学号：</th>
									<td><html:text property="xh" /></td>
									<th>姓名：</th>
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
					    	查询结果&nbsp;&nbsp;
								<font color="red">未找到任何记录！</font> 
					    </span>
					    </h3>
			 		 </logic:empty>
				<logic:notEmpty name="rs">
						
						    <h3 class="datetitle_01">
					    	<span>
					    		查询结果&nbsp;&nbsp;
									<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
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
						
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					  <!--分页显示-->
						<script type="text/javascript">
								$('choose').className="hide";
						</script>
				</logic:notEmpty>
				</div>
				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						document.getElementById('search_go').click();						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
