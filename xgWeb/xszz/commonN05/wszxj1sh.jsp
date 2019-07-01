<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxgzkh.js'></script>
	<script language="javascript" src="/xgxt/js/xgutil.js"></script>
	<script language="javascript">
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
		function rowMoreClick(objTr) {
		
		if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
		}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
		
	iRow=window.event.srcElement;
	do{
		iRow=iRow.parentElement;
	}while(iRow.tagName!='TR')

	//Ctrl多选
	if(event.ctrlKey){
		var j=-1;
		for(i=0;i<Rows.length;i++){
			if(iRow==Rows[i]){
				j=i;
				break;
			}
		}
		if(j!=-1){
			for(i=j;i<Rows.length-1;i++){
				Rows[i]=Rows[i+1];
			}
			Rows.length=Rows.length-1;
			iRow.style.backgroundColor = "#FFFFFF";
		}else{
			Rows[Rows.length]=iRow;
		}
//		ShiftStartRow=iRow;
	}
	else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
		
//		ShiftStartRow=iRow;
	}
	changeColor(iRow);
}

//选中行变色
function changeColor(E){
	
/*	for(var i=1;i<E.parentElement.rows.length;i++){
		E.parentElement.rows(i).style.backgroundColor=cur_bgc;
	}
*/
	for(i=0;i<Rows.length;i++){
		Rows[i].style.backgroundColor=cur_bgc;	
	}
}

function selectAll(){
	var checkBoxArr = document.getElementsByName("pk");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (!checkBoxArr[i].disabled) {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function del(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("请选择要批量删除的记录！");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function tg(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("请选择要批量修改为通过的记录！");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function btg(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("请选择要批量修改为不通过的记录！");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		url += "&xmdm="+document.getElementById('xmdm').value;
		showTopWin(url, 750, 550);
		return true;
	}
}

function add(url){
	url += "&xmdm=";
	url += $('xmdm').value;
	return showTopWin(url,750,550);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("请选择要修改的记录！");
		return false;
	}
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	url += "&xmdm="
	url += $('xmdm').value;
	return showTopWin(url,750,550);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj1Exp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		function tjjg(){
			var dd_html = "<div>";
			dd_html += "<center><br><table width='350' class='tbstyle'>";
			dd_html += "<thead>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "请选择您要提交审核结果的班级";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "学年：";		
			dd_html += "</td><td>";
			dd_html += val('xn');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "班级：";		
			dd_html += "</td><td>";
			dd_html += "<select id='tjbjdm' name='tjbjdm'/>";
			dd_html += "</td></tr>";
			dd_html += "<tr height='30' bgcolor='EEF4F9'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "<button class='button2' onclick='commitSh()'>确定</button>&nbsp;&nbsp;";//szGroup()
			dd_html += "<button class='button2' onclick='closeAdd()'>取消</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table></center>";
			dd_html += "</div>";
			showDiv(dd_html, 400, 170);
			copySelect('bj','tjbjdm');
			setVal('tjbjdm',val('bj'));
		}
		
		function commitSh(){
			//判断选择的班级是否已经提交过
			var xn = val('xn');
			var zxjdm = val('xmdm');
			var bjdm = val('tjbjdm');
			if(bjdm == null || bjdm == "" || bjdm == undefined){
				alert('请选择您要提交审核结果的班级！');
				return false;
			}
			qgzxgzkh.checkExists('xszz_com_bmshtjb','zjz||dm||bm||tjxm||tjzt',xn+zxjdm+bjdm+'bjwszxj1'+'1',function(data){
				if(data != null && data == true){
					alert('该班级已经提交！');
					return false;
				}else{//提交
					qgzxgzkh.checkExists('view_xszz_com_wszxj1','xn||zxjdm||bjdm||fdysh',xn+zxjdm+bjdm+'未审核',function(data){
						if(data){
							alert('还有数据未审核,暂时不能提交结果！');
							return false;
						}else{
							refreshForm("n05_xszz.do?method=wszxj1sh&go=tjsh&xn=" + xn + "&xmdm=" + zxjdm + "&tjbjdm=" + bjdm);
						}
					});
					
				}
			});
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/n05_xszz.do?method=wszxj1sh" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>
					<logic:equal name="isQuery" value="is">
					学生资助 - 申请结果查询 - <bean:write name="xmmc"/>
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					学生资助 - 审核 - <bean:write name="xmmc"/>
					</logic:notEqual>
					</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="xmdm" name="xmdm"
				value="<bean:write name="xmdm" />">
			<div class="toolbox">
			<logic:equal value="yes" name="writeAble" scope="request">
					 <div class="buttonbox">
						<ul>
						<logic:equal name="isQuery" value="is">
						<li> <a href="#" onclick="add('/xgxt/n05_xszz.do?method=wszxj1sq');" class="btn_zj"> 增加 </a> </li>
					    <li> <a href="#" onclick="modi('/xgxt/n05_xszz.do?method=wszxj1sq&type=modi');" class="btn_xg"> 修改 </a> </li>
						<li> <a href="#" onclick="del('/xgxt/n05_xszz.do?method=wszxj1sh&go=del');" class="btn_sc"> 删除 </a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a> </li>
						<li> <a href="#" onclick="dataExport2()"  class="btn_dc"> 导出 </a> </li>
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<li> <a href="#" onclick="tg('/xgxt/n05_xszz.do?method=wszxj1sh&go=tg');" class="btn_shtg"> 通 过 </a> </li>
							<li> <a href="#"  onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=btg');" class="btn_shbtg"> 不通过 </a> </li>
							<logic:present name="fdyshFlag">
								<li> <a href="#"  onclick="tjjg()" class="btn_tj"> 提交结果 </a> </li>
							</logic:present>
						</logic:notEqual>
					    </ul>
					 </div>
			</logic:equal>
			<div class="searchtab">
			<table width="100%" border="0">
				<tfoot>
				 	<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
								<input type="hidden" name="go" value="a" />
								<div class="bz">
									<!--辅导员提交信息-->
									<logic:present name="tjxx">
									<tr>
										<td colspan="2">
											<font color="red"><b><bean:write name="tjxx" property="bjmc"/>提交结果：<bean:write name="tjxx" property="tjzt"/></b></font>
										</td>
									</tr>
									</logic:present>
									<!--end辅导员提交信息-->
								</div>
								<button class="btn_cx"   id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=wszxj1sh&go=go')">
									查 询
								</button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 重 置
		                         </button>
		                    </div>
						</td>
					</tr>
				</tfoot>
				<tbody>
						<tr>
							<th>	
								学号
							</th>
							<td>	
								<html:text property="xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>	
								姓名
							</th>
							<td>	
								<html:text property="xm" styleId="xm" style="width:50px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>	
								性别
							</th>
							<td>	
								<html:select property="xb">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
							</td>
							
						</tr>
						<tr>
							<th>
								学年
							</th>
							<td>	
								<logic:equal name="isQuery" value="is">
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</logic:equal>
								<logic:notEqual name="isQuery" value="is">
								<html:select property="xn" 
									styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</logic:notEqual>
							</td>
							<th>	
								年级
							</th>	
							<td>
								<html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>	
								身份证号
							</th>
							<td>	
								<html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
						</tr>
						
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>	
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>	
								专业
							</th>
							<td>	
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>	
								班级
							</th>
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
			</div>
			<div class="formbox" id="result">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
						<font color="blue"> 
							提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			    <logic:notEmpty name="rs">
						<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<logic:equal name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1sq&type=modi')"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="1" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
										<td align=center>
											<input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:equal>
									<logic:notEqual name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1shOne')"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="1" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									     ">
										<td align=center><input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> 
										value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:notEqual>
								</logic:iterate>
								</tbody>
							</table>
							<!--分页显示-->
						    	 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzCommonN05ActionForm"></jsp:include>
						 	 <!--分页显示-->
							</div>
					</logic:notEmpty>
					<div id="tmpdiv"></div>
					</div>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:notEmpty name="result">
		 	<logic:equal value="yes" name="result">
		 		<script>
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 </logic:notEmpty>
	</body>
</html>
