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
			var flag = false;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			if (flag){
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

		function shOne(type){
			var pk = '';
			if(curr_row){
				pk = curr_row.getElementsByTagName('input')[0].value;
			}
			if('gx'==type){
				var checkBoxArr = document.getElementsByName("primarykey_cbv");
				var c = 0;
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
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
			}
			var url = '/xgxt/xszcgl.do?method=xszcshDetial';
			url+="&pk="+pk;
			showTopWin(url,'650','500');
		}

		//删除
		function del(checkboxName,url){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('请选择您要删除的数据');
				return false;
			} 
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].alt=='disabled'){
					alertInfo("您勾选的记录中有正在审核或已审核<br/>的数据，请确认");
					return false;
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


	</script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/xszcgl" method="post">
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
						<li> <a href="#" onclick="del('primarykey_cbv','xszcgl.do?method=xszcsh&doType=del');" class="btn_sc">删除</a> </li>
						<logic:equal value="true" name="isFdy" scope="session">
							<li> <a href="#" onclick="shOne('gx');" class="btn_sh">单个审核</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/xszcgl.do?method=xszcsh&shjg=通过&doType=sh');" class="btn_shtg">审核通过</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/xszcgl.do?method=xszcsh&shjg=不通过&doType=sh');" class="btn_shbtg">审核不通过</a> </li>
						</logic:equal>
						<logic:notEqual value="true" name="isFdy" scope="session">
						<logic:equal value="xy" name="userType">
							<li> <a href="#" onclick="shOne('gx');" class="btn_sh">单个审核</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/xszcgl.do?method=xszcsh&shjg=通过&doType=sh');" class="btn_shtg">审核通过</a> </li>
							<li> <a href="#" onclick="shformdata('/xgxt/xszcgl.do?method=xszcsh&shjg=不通过&doType=sh');" class="btn_shbtg">审核不通过</a> </li>
						</logic:equal>
						</logic:notEqual>
					</logic:equal>
					<!-- 
					<li> <a href="#" onclick="configureExportData();return false;" class="btn_dc">导出</a> </li>
					<li><a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a></li>
					 -->
					<li><a href="#" class="btn_dc" onclick="expData('/xgxt/xszcgl.do?method=xszcsh&doType=expData');">导出</a></li>
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
								onclick="allNotEmpThenGo('/xgxt/xszcgl.do?method=xszcsh')">
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
					<th>注册状态</th>
					<td>
						<html:select property="queryequals_zczt" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zcztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<th>审核结果</th>
					<td>
						<html:select property="queryequals_fdysh" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="shztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszcForm"></jsp:include>
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
