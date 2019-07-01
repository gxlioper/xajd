<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	</head>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/pjpyFunction.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/bbld.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/chgRychlist.js'></script>
	
	
		<script type="text/javascript">
			function chec(){
			
			var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('rychxx');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
			
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}	
		  
  			}
  			function chgJxjlists(xydms) {
		var xydm = document.getElementById(xydms).value;
		chgRychlist.xyRychList(xydm,function(data) {
					DWRUtil.removeAllOptions('jxjdm');			
					var o = [{id:'',labelText:''}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					for(var i=0;i<data.length;i++){
						o = [{id:data[i].rychdm,labelText:data[i].rychmc}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					}
		});
	}  		
	</script>

		<html:form action="/prise_conf_rs" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<logic:equal value="12872" name="xxdm">
				<input type="hidden" id="tableName" name="tableName"
				value="xsrychxxb" />
				<input type="hidden" id="realTable" name="realTable" value="xsrychxxb" />
			</logic:equal>
			<logic:notEqual value="12872" name="xxdm">
				<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable" value="xsrychb" />
			</logic:notEqual>
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
			<input type="hidden" name="zyV" id="zyV"/>
			<input type="hidden" name="bjV" id="bjV"/>
			<input type="hidden" name="userName" id="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
				<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">
					<ul>
						<logic:equal value="12872" name="xxdm">
						<logic:notEqual value="true" name="isFdy">
						<li><a href="#" class="btn_shtg" onclick="checkAll('pass')">审核通过</a></li>
						<li><a href="#" class="btn_shbtg" onclick="checkAll('nopass')">审核不通过</a></li>
						</logic:notEqual>
						</logic:equal>
						
						<logic:notEqual value="12872" name="xxdm">
						<li><a href="#" class="btn_shtg" onclick="checkAll('pass')">审核通过</a></li>
						<li><a href="#" class="btn_shbtg" onclick="checkAll('nopass')">审核不通过</a></li>
						</logic:notEqual>
						
						<logic:notPresent name="cqksisFdy">
<%--						<logic:equal name="userType" value="xy" scope="session">--%>
<%--							<button type="button" class="button2" onclick="showTopWin('xySetBysStuNum.do',450,405);">--%>
<%--								核对毕业生人数--%>
<%--							</button>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--						</logic:equal>--%>
						<logic:present name="showjsxx">
							<li><a href="#" class="btn_sh" onclick="creditAutoChk('/xgxt/creditAutoCheck.do');">自动审核</a></li>
						</logic:present>
						
						<%--武汉理工大学--%>
						<logic:equal value="10497" name="xxdm">
						<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入审核结果</a></li>
						<li><a href="#" class="btn_dr" onclick="if(document.getElementById('jxjdm').value==null || document.getElementById('jxjdm').value==''){alert('请选择荣誉称号!')}else{document.forms[0].target = '_blank';refreshForm('pjpy_whlgdx.do?method=rychExport');document.forms[0].target = '_self';}">导出样式表</a></li>
						</logic:equal>
						<%--武汉理工大学end--%>						
						
						<%--非武汉理工大学--%>
						<logic:notEqual value="10497" name="xxdm">
						<%--<button type="button" class="button2" onclick="dataExport()">
							输出汇总
						</button>
						<button type="button" class="button2" onclick="if(confirm('名单将以pdf格式显示，\n打开前请确认您的计算机上安装了可以查看pdf文件的相关软件。\n确定要打开吗？')){chgRight('/xgxt/nameList.do?typ=cri','_blank');document.forms[0].target = '_self';}return false;">
							名单生成
						</button>
						<button type="button" class="button2" onclick="showTopWin('showYxBysFpb.do',650,405);">
							优秀毕业生分配表
						</button>--%>
						
						<logic:equal value="yes" name="showahjg">
							<li><a href="#" class="btn_dr" onclick="showTopWin('xjgrfbb.do','720','580')">评比分配表</a></li>
						</logic:equal>
						</logic:notEqual>
						</logic:notPresent>
						<logic:present name="showxcxy">
						<li><a href="#" class="btn_sh" onclick="djspbPrint('yxbysspb')">优秀毕业生审批表</a></li>
						<li><a href="#" class="btn_zj" onclick="djspbPrint('yxbysdjb')">优秀毕业生登记表</a></li>
						</logic:present>
						<logic:equal value="10355" name="xxdm">
						<li><a href="#" class="btn_dy" onclick="rysprint()">报表打印</a></li>
						</logic:equal>	
						<logic:equal value="12872" name="xxdm">
						<li><a href="#" class="btn_xg" onclick="rychplqm('xsrychb')" id="buttonQuery">批量签名</a></li>			
						<li><a href="#" class="btn_dy" onclick="hzyrychprint();" id="btn_print">报表打印</a></li>
						<li><a href="#" class="btn_dy" onclick="zsldprint('hzyrychbbld.do?pkValue=','cbv')" id="btn_prints">报表连打</a></li>	
						</logic:equal>
					</ul>
					</div>
					</div>
				</logic:equal>
				
				<div class="searchtab">
					<table width="100%" border="0">
					
					<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		            		<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go"
								onclick="refreshForm('/xgxt/credit_check.do?stab=stab')">
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
							<th>学年</th>
							<td><html:select property="xn" style="width:90px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
								<logic:notEqual value="11078" name="xxdm">
								<th>年度</th>
								<td><html:select property="nd" style="width:80px" disabled="true"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select></td><%--
								&nbsp;&nbsp;学期:
								<html:select property="xq" styleId="xq" disabled="true">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>--%>
								</logic:notEqual>
								<th>年级</th>
								<td><html:select property="nj" style="width:70px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
								</tr>
								<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" /></th>
								<logic:equal value="10827" name="xxdm">
									<td><html:select property="xydm" style="width:165px"
								onchange="initZyList();initBjList();chgJxjlists('xy');" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<td><html:select property="xydm" style="width:165px"
								onchange="initZyList();initBjList()" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
								</logic:notEqual>
								
								<logic:equal value="fdy" name="iscsmz">
								<th>专业</th>
								<td><html:select property="zydm" onchange="initBjList()" style="width:150px" 
								styleClass="select" styleId="zy">
									<html:option value="">全部</html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>班级</th>
								<td><html:select property="bjdm" style="width:150px" 
								styleClass="select" styleId="bj">
									<html:option value="">全部</html:option>
									<html:options collection="bjList" property="en"
										labelProperty="cn" />
								</html:select></td>
								</logic:equal>
								<logic:notEqual value="fdy" name="iscsmz">
								<th>专业</th>
								<td><html:select property="zydm"  onchange="initBjList()" style="width:150px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>班级</th>
								<td><html:select property="bjdm" style="width:150px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select></td>
								</logic:notEqual>
							</tr>
							<tr>
								<logic:equal value="10827" name="xxdm">
									<logic:equal value="xy" name="userType">
										<th>获奖类别</th>
										<td><html:select property="jxjlb" styleId="jxjlb" onchange="refreshForm('/xgxt/credit_check.do?stab=stab')">
											<html:option value="1">院级</html:option>
											<html:option value="2">系级</html:option>
										</html:select></td>
									</logic:equal>
								</logic:equal>
								<th>荣誉称号</th>
								<td colspan=""><html:select property="xmdm" style="width:145px" styleId="jxjdm">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select></td>
								<logic:notEqual value="12872" name="xxdm">
									<th>状态</th>
								<td colspan="5"><html:select property="zt" styleId="zt" >
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="不通过">不通过</html:option>
									<html:option value="通过">通过</html:option>
								</html:select></td>
								</logic:notEqual>
								<input id="chgMode" type="checkbox" style="border:0px;display:none" />
								<logic:present name="showhzy">
										<th>审核否</th>
										<td><html:select property="yesNo">
											<html:option value="">    全部    </html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select></td>
								</logic:present>
						</tr>
					</tbody>
				</table>
				</div>
			
				<div id="result" class="formbox">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs">
				 		 <font color="blue">提示：双击可以查看详细信息；单击表头可以排序！</font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
		
					<logic:notEmpty name="rs">
							<logic:equal value="10827" name="xxdm">
								<logic:equal value="xy" name="userType">
									<table width="99%" id="rsTable" class="dateline">
								<thead>
									<tr align=""left"" style="cursor:hand">
										<td>
											<input type="checkbox" name="rychxx" value="all" onclick="chec()"/>
										</td>
										<logic:iterate id="tit" name="topTr" offset="3">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>										
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align="left"
										style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/>
    </logic:iterate>" ondblclick="chkPriseOne('/xgxt/creditChkOne.do?act=view&pkVal=')">
										<td>
										<input type="checkbox" name="cbv" id="cbv"
										<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />											
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />												
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="6" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="3" length="1">
												<bean:write name="v" />
											</logic:iterate>
											<logic:equal value="yes" name="shownblg">
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="13" length="1"><bean:write name="v" /></logic:iterate>"/>
											</logic:equal>
										</td>
										<logic:iterate id="v" name="s" offset="4">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<table width="99%" id="rsTable" class="dateline">
								<thead>
									<tr align=""left"" style="cursor:hand">
										<td >
											<input type="checkbox" name="rychxx" value="all" onclick="chec()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td  id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>										
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align=""left""
										style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
    "
										ondblclick="chkPriseOne('/xgxt/creditChkOne.do?act=view&pkVal=')">
										<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="cbv" id="cbv" value="<bean:write name="v"/>" />											
											</logic:iterate>											
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />												
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="5" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<bean:write name="v" />
											</logic:iterate>
											<logic:equal value="yes" name="shownblg">
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="12" length="1"><bean:write name="v" /></logic:iterate>"/>
											</logic:equal>
										</td>
										<logic:iterate id="v" name="s" offset="3">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								<table width="99%" id="rsTable" class="dateline">
								<thead>
									<tr align=""left"" style="cursor:hand">
										<td>
											<input type="checkbox" name="rychxx" value="all" onclick="chec()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>										
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align=""left""
										style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
    "
										ondblclick="chkPriseOne('/xgxt/creditChkOne.do?act=view&pkVal=','710','620')">
										<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="cbv" id="cbv" value="<bean:write name="v"/>" />											
											</logic:iterate>											
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />												
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="5" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<bean:write name="v" />
											</logic:iterate>
											<logic:equal value="yes" name="shownblg">
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="12" length="1"><bean:write name="v" /></logic:iterate>"/>
											</logic:equal>
											
										</td>
<%--										<td><logic:iterate id="v" name="s" offset="3" length="2"><bean:write name="v" /></logic:iterate></td>--%>
<%--										<td>--%>
<%--											<logic:iterate id="v" name="s" offset="5" length="1"><bean:write name="v"/></logic:iterate>--%>
<%--										</td>--%>
										<logic:iterate id="v" name="s" offset="3">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
							</table>
							</logic:notEqual>
					</logic:notEmpty>
								
					<div id="tmpdiv"></div>
					<logic:present name="result">
					<logic:equal value="true" name="result">
						<script>
						alert("操作成功!");
						document.getElementById("search_go").click();
						</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<script>
						 alert("操作失败!");
						</script>
					</logic:equal>
					</logic:present>
				</div>
		</html:form>
  <script language="javascript">
     function djspbPrint(tab){
        if(curr_row == null){
            alert('请选择要打印的数据！\n（单击相应的行）');
            return false;
        }else{
            var pkV =  curr_row.getElementsByTagName("input")[0].value;
            var xh = curr_row.cells[4].innerText;
            var rychdm = pkV.substring(pkV.indexOf(xh)+xh.length,pkV.length);
            window.open('rychqsb.do?xh='+xh+'&rychdm='+rychdm+'&tabTyp='+tab + '&pkValue='+pkV);
        }
     } 
     	function hzyrychprint(){
						if (curr_row==null || curr_row=='') {
							alert('请选择要打印的数据行！');
							return;
						} else 
							window.open('hzyrychprint.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value);
					}
			

var b = false;
var xxdm = document.getElementById('xxdm').value;
if(document.getElementById('rsTable') && xxdm != '11078'){
	for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
  			b = true;
  			break;
  		}
  	}
  	if (b) {
  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
  		    var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
  			xhTd.innerHTML = html_content;
  		}
  	}
}
function queryOne(xh) {
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showTopWin(url, 800, 600);
}
function rysprint() {
if (curr_row==null) {
	window.open('pjpyzgmswh.do?method=ryprint&pkValue=');
	} else {
	window.open('pjpyzgmswh.do?method=ryprint&pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value+'&p=k&rychmc='+curr_row.cells[7].innerText);
	}
}
 </script>
</body>
</html>
