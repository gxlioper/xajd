<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
		//批量审核
		function shformdata(url){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var fdyshArr = document.getElementsByName("fdyshArr");
			var userType = document.getElementById('userType').value; 
			var isFdy = document.getElementById('isFdy').value;
			var flag = false;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			if (flag){
				for (var i = 0 ; i < checkBoxArr.length ; i++){
					if(checkBoxArr[i].checked==true){
					 	if (jQuery(checkBoxArr[i]).attr('alt')=="disabled"){
							alertInfo("您勾选的记录中有正在审核或已审核<br/>的数据，请确认");
							return false;
						} 
					 	if(isFdy!='true' && userType=='xy'){
							if(fdyshArr[i].value!='通过'){
								alertInfo("您勾选的记录中有上级审核未通过<br/>的数据，请确认");
								return false;
							}
						}
					}
				}
				if (confirm('确定要审核所选择的数据吗？')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('正在操作，请等待...');
					}
				}
			}else{
				alert("没有选择相应记录，请选择之后再进行操作！！");
			}
		}

		//单个审核
		function shOne(type){
			var pk = '';
			var userType = document.getElementById('userType').value; 
			var isFdy = document.getElementById('isFdy').value;
			if(curr_row){
				pk = curr_row.getElementsByTagName('input')[0].value;
				//if(userType=='xx' || userType=='admin'){
					//alertInfo("您无审核权限！");
					//return false;
				//}
			}
			if('gx'==type){
				var checkBoxArr = document.getElementsByName("primarykey_cbv");
				var fdyshArr = document.getElementsByName("fdyshArr");
				var c = 0;
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						if(checkBoxArr[i].alt=='disabled'){
							alertInfo("您勾选的记录中有正在审核或已审核<br/>的数据，请确认");
							return false;
						}
						if(isFdy!='true' && userType=='xy'){
							if(fdyshArr[i].value!='通过'){
								alertInfo("您勾选的记录中有上级审核未通过<br/>的数据，请确认");
								return false;
							}
						}
						pk = checkBoxArr[i].value;
						c++;
					}
				}
				if(c==0){
					alert('请选择审核的数据');
					return false;
				}
				if(c>1){
					alert('只能审核单条记录！');
					return false;
				}
			}else{
				if(curr_row == null){
					alert('请选择审核的数据！');
					return false;
				}
				if(isFdy=='true'){
					if(curr_row.getElementsByTagName('input')[2].value!='未审核'){
						alertInfo("您点击的记录正在审核或已审核，<br/>请确认");
						return false;
					}
				}else if(userType=='xy'){
					if(curr_row.getElementsByTagName('input')[1].value!='通过'){
						alertInfo("您点击的记录上级审核未通过，<br/>请确认");
						return false;
					}
				}
			}
			var url = '/xgxt/jqlxgl.do?method=jqlxshDetial';
			url+="&pk="+pk;
			showTopWin(url,'800','600');
		}

		//删除
		function del(checkboxName,url){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('请选择您要删除的数据');
				return false;
			} 
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var fdyshArr = document.getElementsByName("fdyshArr");
			var xyshArr = document.getElementsByName("xyshArr");
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					if(checkBoxArr[i].alt=='disabled' || fdyshArr[i].value!='未审核' || xyshArr[i].value!='未审核'){
						alertInfo("您勾选的记录中有正在审核或已审核<br/>的数据，请确认");
						return false;
					}
				}
			}
			//提示确认信息
			confirmInfo('确定要删除所选择的数据吗？', function(tag){
				if(tag == 'ok'){
					document.forms[0].action = url;
					document.forms[0].submit();
				}
			});
			if ($("pt")){
				BatAlert.showTips('正在操作，请等待...');
			}
		}

		//修改
		function showViewWindow(checkboxName,url,width,height,currPage,message){
			var pkValues = document.getElementsByName(checkboxName);
			var fdyshArr = document.getElementsByName("fdyshArr");
			var xyshArr = document.getElementsByName("xyshArr");
			var tempArr=new Array(),n=0 ;
			
			for (var i = 0 ; i < pkValues.length ; i++){
				
				if (pkValues[i].checked){
					tempArr[n] = pkValues[i];
					if(pkValues[i].alt=='disabled' || fdyshArr[i].value!='未审核' || xyshArr[i].value!='未审核'){
						alertInfo("您勾选的记录中有正在审核或已审核<br/>的数据，请确认");
						return false;
					}
					n++;
				}
			}
			
			if (tempArr.length != 1){
				if (null == message){
					alertInfo("请勾选一条您要修改的数据");
				} else {
					alertInfo(message);
				}
			
				return false;
			} else if (currPage){
				url+="&pkValue="+tempArr[0].value;
				refreshForm(url);
			} else {
				url+="&pkValue="+tempArr[0].value;
				showTopWin(url,width,height);
			}
		}

	</script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/jqlxgl" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">	
			<!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<!--读写权-->
					<logic:equal value="yes" name="writeAble">
						<logic:notEqual value="xy" name="userType">
						<li> <a href="#" onclick="showViewWindow('primarykey_cbv','jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok','770','550');" class="btn_xg">修改</a> </li>
						<li> <a href="#" onclick="del('primarykey_cbv','jqlxgl.do?method=jqlxsh&doType=del');" class="btn_sc">删除</a> </li>
						</logic:notEqual>
						<logic:equal value="xy" name="userType">
							<logic:equal value="true" name="isFdy" scope="session">
								<li> <a href="#" onclick="showViewWindow('primarykey_cbv','jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok','770','550');" class="btn_xg">修改</a> </li>
								<li> <a href="#" onclick="del('primarykey_cbv','jqlxgl.do?method=jqlxsh&doType=del');" class="btn_sc">删除</a> </li>
							</logic:equal>
						</logic:equal>
						<logic:equal value="true" name="isFdy" scope="session">
							<li> <a href="#" onclick="shOne('gx');" class="btn_sh">单个审核</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=通过&doType=sh');" class="btn_shtg">审核通过</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=不通过&doType=sh');" class="btn_shbtg">审核不通过</a> </li>
						</logic:equal>
						<logic:notEqual value="true" name="isFdy" scope="session">
						<logic:equal value="xy" name="userType">
							<li> <a href="#" onclick="shOne('gx');" class="btn_sh">单个审核</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=通过&doType=sh');" class="btn_shtg">审核通过</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/jqlxgl.do?method=jqlxsh&shjg=不通过&doType=sh');" class="btn_shbtg">审核不通过</a> </li>
						</logic:equal>
						</logic:notEqual>
					</logic:equal>
					<!-- 
					<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">导出</a> </li>
					<li><a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a></li>
					 -->
					 <logic:equal name="writeAble" value="yes" >
					<li><a href="#" class="btn_dc" onclick="expData('/xgxt/jqlxgl.do?method=jqlxsh&doType=expData');">导出</a></li>
					</logic:equal>
					<!--end读写权-->
			    </ul>
			  </div>			 
			 <!--查询条件-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('/xgxt/jqlxgl.do?method=jqlxsh')">
							查询
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>	
		      		<th>年级</th>
					<td>
						<html:select property="queryequals_nj" onchange="initZyList();initBjList()" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>				
					<th>学号</th>
					<td>
						<html:text property="querylike_xh" maxlength="20" style="width:180px"></html:text>
					</td>	
					<th>姓名</th>
					<td>
						<html:text property="querylike_xm" maxlength="20" style="width:180px"></html:text>
					</td>	
				</tr>	
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="queryequals_xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>专业</th>
					<td>
						<html:select property="queryequals_zydm" onchange="initBjList()" style="width:180px" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="queryequals_bjdm" style="width:180px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:select property="queryequals_xn" style="width:180px">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
					<logic:equal value="true" name="isFdy" scope="session">
						<th>审核结果</th>
						<td>
							<html:select property="queryequals_fdysh" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						<th></th>
						<td></td>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy" scope="session">
						<logic:equal value="xy" name="userType">
							<th>审核结果</th>
							<td>
								<html:select property="queryequals_xysh" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th></th>
							<td></td>
						</logic:equal>	
						<logic:notEqual value="xy" name="userType">
							<th></th>
							<td></td>
							<th></th>
							<td></td>
						</logic:notEqual>
					</logic:notEqual>
				</tr>
			  </tbody>
			</table>				
		</div>
		
		
		<div class="formbox" id="result">
			<h3 class="datetitle_01">
		    <span>
		    	查询结果&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">未找到任何记录！</font> 
		 		 </logic:empty>
		 		 <logic:notEmpty name="rs">
					<font color="blue">提示：双击可以查看详细信息并审核，单击表头可以排序！</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
			      		<td><input type="checkbox"/>
			      		</td>
						<logic:iterate id="tit" name="topTr" offset="3" scope="request">
							<td id="${tit.en}"
								onclick="tableSort(this)">
								${tit.cn}
							</td>
						</logic:iterate>
					</tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="shOne('');"
							style="cursor:hand;background: <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>">
							<td>
								<input type="checkbox" id="cbv" name="primarykey_cbv" alt="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
								<input type="hidden" name="fdyshArr" value="<logic:iterate id="v" name="s" offset="12" length="1">${v}</logic:iterate>"/>
								<input type="hidden" name="xyshArr" value="<logic:iterate id="v" name="s" offset="13" length="1">${v}</logic:iterate>"/>	
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
			   </div>
			   <!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
			    <!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>

		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("" + $('message').value);
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
