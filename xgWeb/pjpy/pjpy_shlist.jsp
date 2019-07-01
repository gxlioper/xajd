<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script language="javascript" src="js/pjpy/pjpy_hzy.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/webPrint.js"></script>
<%--	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>--%>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="js/bbld.js"></script>
	
	<script type="text/javascript">
		function xjbjsh(){
			if (curr_row==null || curr_row=='') {
				alert('请选择要操作的数据行！');
				return;
			} else {
				var url = 'dispatch.do?method=xjbjandwmbjShone&pkValue=';
				url += curr_row.cells[0].getElementsByTagName("input")[1].value;
				showTopWin(url,'650','500');
			}
		}

	</script>
  </head>
  
  	<body onload="xyDisabled('xy');">
		<html:form action="/dispatch" method="post">
		<input type="hidden" id="userType" name="userType" value="${userType }"/>
		
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 审核 - 集体荣誉审核</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<li><a href="#" class="btn_shtg" onclick="shtg()">审核通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="shbtg()">审核不通过</a></li>
							<logic:equal value="12872" name="xxdm">
								<li><a href="#" class="btn_sc" onclick="xjbjdel()">删除</a></li>
								<li><a href="#" class="btn_dy" onclick="xjbjprint()">打印报表</a></li>
							</logic:equal>
						</ul>
					</div>
					</div>
				</logic:equal>
				<input type="hidden" id="zyV" name="zyV" value=""/>
				<input type="hidden" id="failinfo" name="failinfo" value="${failinfo }"/>
				
				<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th align="left">学年</th>
							<td><html:select property="xn" style="width:120px" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<logic:present name="ndList">
							<th>年度</th>
							<td><html:select property="nd" style="width:90px" styleId="nd">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
								</logic:present>
							<logic:present name="xqList">
							<th>学期</th>
							<td><html:select property="xq" style="width:90px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select></td>
							</logic:present>
							<th>年级</th>
							<td><html:select property="nj" style="width:90px;padding-left:80px"
								onchange="initZyList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
								<th></th>
								<td></td>
						</tr>
							<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>

							<th align="left">专业</th>
							<td><html:select property="zydm" style="width:150px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
							</html:select></td>
							<th>审核项目</th>
							<td><html:select property="shxm" style="width:150px" styleId="shxm">
								<html:options collection="shList" property="shxm"
									labelProperty="shxmmc" />
							</html:select></td>
							</tr>
							<tr>
							<th>审核状态</th>
							<td colspan="5">
							<input id="chgMode" type="checkbox" style="border:0px;display:none" />
							<html:select property="pass" styleId="pass" >
								<html:option value=""></html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
							</td>
						</tr>
					</tbody>
					
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button id="search_go" 
							onclick="if(document.getElementById('shxm').value==''){alert('请选择相应的审核项目！！');return false;}refreshForm('/xgxt/dispatch.do?method=search')">
							查 询
							</button>
							 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								重 置
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
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
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
							<table width="99%" id="rsTable" class="dateline">
								<thead>
									<tr align="left" style="cursor:hand">
									<td><input type="checkbox" onclick="selectAllCheckBox()" /></td>
										<logic:iterate id="tit" name="topTr" >
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;background-color:
									    <logic:iterate id="v" name="s" offset="0" length="1">
									    <bean:write name="v"/>
									    </logic:iterate>
									    "
										ondblclick="xjbjsh()">
										<td align=left>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="checkval" id="checkval" value="<bean:write name="v"/>" />
											</logic:iterate>
											<input type="hidden" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
											
										
										</td>
											<logic:iterate id="v" name="s" offset="2" >
											<td align=left>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
					</logic:notEmpty>
					
					<div id="tmpdiv"></div>
				</div>
		</html:form>
 <script type="text/javascript" src="js/bottomButton.js"></script> 
   <logic:present name="result" >
    <logic:equal value="true" name="result">
       <script type="text/javascript">
    alert("保存成功！");
  </script>
    </logic:equal>
    <logic:equal value="false" name="result">
    <script type="text/javascript">
    alert("保存失败！");
  </script>
    </logic:equal>
  </logic:present>
  <script type="text/javascript">
  	function xjbjprint() {
  		var url = '/xgxt/pjpy_hzy_xjbjandwmbj_print.do?method=xjbjAndWmbjSqPrint&bjdm=';
  		if (curr_row == null || curr_row == '') {
  			url += '';
  		} else {
  			var tmp = curr_row.cells[0].getElementsByTagName("input")[0].value;
  			
  			if (tmp != '') {
  				tmp = tmp.substr(16,tmp.length-16);
  				
  			}
  			url += tmp;
  		}
  		url += '&titName=';
  		var tp = document.getElementById('shxm').value;
  		if (tp != '') {
  			if (tp == 'view_pjpy_xjbjsqb') {
  				url += 'xjbj';
  			} else {
  				url += 'wmbj';
  		}
  		
  		window.open(url);
  
  	}
  	}
  	function xjbjdel(){
  		var checkBoxArr = document.getElementsByName("checkval");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		var url="dispatch.do?method=xjbjDel";
		if (confirm('确定要删除所选择的数据吗？')) {
			refreshForm(url);
			return;
		} else {
			return;
		}
	}else{
		alert("没有选择相应记录，请选择之后再操作！！");
	}
  	}
  	
  </script>
  <logic:present name="deleted">
  	<logic:equal value="yes" name="deleted">
  		<script>
  			alert('操作成功！');
  			document.getElementById('search_go').click();
  		</script>
  	</logic:equal>
  	<logic:equal value="no" name="deleted">
  		<script>
  			alert('操作失败!'+ document.getElementById('failinfo').value);
  			document.getElementById('search_go').click();
  		</script>
  	</logic:equal>
  </logic:present>
	</body>
</html>
