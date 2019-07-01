<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>	
		 <SCRIPT LANGUAGE="JavaScript"> 
<!-- Hide 
function killErrors() { 
return true; 
} 
window.onerror = killErrors; 
// --> 
</SCRIPT>
		<script type="text/javascript">
			 
			function getFdyInfo(act){
				var xxdm = document.getElementById("xxdm").value;
				var url = "";
				if(xxdm == "10497"){ //武汉理工大学
					url = '/xgxt/fdyxxOne_whlg.do?act=' + act + '&pk=' + curr_row.cells[0].getElementsByTagName('input')[0].value;
				}else if("view" == act){
					url = '/xgxt/fdyxxOne.do?act=' + act + '&operation=view' + '&pk=' + curr_row.cells[0].getElementsByTagName('input')[0].value;
				}else {
					url = '/xgxt/fdyxxOne.do?act=' + act + '&pk=' + curr_row.cells[0].getElementsByTagName('input')[0].value;
				}
				showTopWin(url,800,600); 
			}
			
			function modiFdyInfo(){
				if(curr_row == null){
					alert('请选择要修改的行!');
					return false;
				}
				getFdyInfo('modi');
			}
			
			function addFdyInfo(){
				var xxdm = document.getElementById("xxdm").value;
				var url = "";
				if(xxdm == "10497"){ //武汉理工大学
					url = '/xgxt/fdyxxOne_whlg.do?act=add';
				}else{
					url = '/xgxt/fdyxxOne.do?act=add';
				}
				showTopWin(url,800,600);
			}
			
			function checkXxdm(){ //武汉理工大学
				var xxdm = document.getElementById("xxdm").value;
				if(xxdm == "10497"){
					document.getElementById("tableName").value = "view_whlgdx_fdyxx";
				}
			}
			
			function saveTgZyhxxk() {
					if($('select_zdmList').value==''){
						alert("请选择所在用户组");
						return false;
					}
					if($('select_dwList').value==''){
						alert("请选择用户所在单位");
						return false;
					}
					document.forms[0].go.value='go';
					refreshForm('/xgxt/fdyxx.do?act=tgyhxx');
			}
			
			function bbtj(){
				document.forms[0].action = "/xgxt/wxsz_sjtj.do";
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
			
			function tgZyhxxk(){
				var checkBoxArr = document.getElementsByName("checkVal");
				var yzchk=true;
				for(var i=0;i<checkBoxArr.length;i++){
						if(checkBoxArr[i].checked){
							yzchk =false;
							break;
						}
				}
				if(yzchk==true){
					alert("请勾选需批量转入用户库的教师");
					return;
				}
				viewTempDiv("转换用户组选择","viewYhz",450, 190);
		}
		</script>
	</head>
	<body onload="xyDisabled('xy');checkXxdm();">
		<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
				</p>
			</div>
		<html:form action="/data_search" method="post">
		 <!-- 按钮 -->
		 <!-- 有读写权 -->
		 <div class="toolbox">
		 <logic:equal value="yes" name="writeAble" scope="request">	
		 <div class="buttonbox">
		    <ul>
			<li> <a href="#" onclick="addFdyInfo();return false;" class="btn_zj"> 增加 </a> </li>
		      <li> <a href="#" onclick="modiFdyInfo();return false;" class="btn_xg"> 修改 </a> </li>
			<li> <a href="#" onclick="sumitInfo('/xgxt/fdyxx.do?act=del','del');return false;" class="btn_sc"> 删除 </a> </li>
			<li> <a href="#" onclick="impAndChkData();return false;" class="btn_dr"> 导入 </a> </li>
			<li> <a href="#" onclick="dataExport();return false;" class="btn_dc"> 导出 </a> </li>
		   	<logic:equal value="yes" name="qx" scope="request">
			<li> <a href="#" onclick="tgZyhxxk();return false;" class="btn_sx"> 添加至用户信息库 </a> </li>
			</logic:equal>
			<!-- 无锡职业 -->
			<logic:equal name="xxdm" value="12702">
				<li> <a href="#" onclick="bbtj();return false;" class="btn_tj"> 数据统计 </a> </li>
			</logic:equal>
			<!-- 无锡职业end -->
			<!-- 非无锡职业 -->
			<logic:notEqual name="xxdm" value="12702">
				<%--<li> <a href="#" onclick="expTab('rsTable','思政队伍 - 个人信息列表','');return false;" class="btn_dy"> 打印列表 </a> </li>
			--%></logic:notEqual>
			<!-- 非无锡职业end -->
		    </ul>
		 </div>
		 </logic:equal>
		 <!-- 没有读写权 -->
		<logic:equal value="no" name="writeAble" scope="request">	
			 <div class="buttonbox">
			<ul>
				<li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
				<%--<li> <a href="#" onclick="expTab('rsTable','思政队伍 - 个人信息列表','');return false;" class="btn_dy"> 打印列表 </a> </li>
			--%></ul>
			</div>
		</logic:equal>
			<input type="hidden" id="tableName" name="tableName"
				value="view_fdyxxbAllinfo" />
			<input type="hidden" id="realTable" name="realTable"
				value="fdyxxb" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="session"/>" />	
			
			<div class="searchtab">
			<table width="100%" border="0">
				 <tfoot>
				 	<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
		             			 <button type="button" class="btn_cx" id="search_go" 
									onclick="document.forms[0].go.value='go';refreshForm('/xgxt/fdyxx.do?act=persInfo')">
									查 询
								 </button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 重 置
		                         </button>
		                    </div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th align="left">
							部门
						</th>
						<td>
							<html:select property="xydm" styleId="xy" style="width:150px"
								onmouseover="">
								<html:option value=""></html:option>
								<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc" />
							</html:select>
						</td>
						<th>
							职务
						</th>
						<td>
							<html:select property="zxm" styleId="zxm" style="width:150px"
								onmouseover="">
								<html:option value=""></html:option>
								<html:options collection="zwList" property="zwdm"
									labelProperty="zwmc" />
							</html:select>
							
						</td>
						<th>
							是否系统用户
						</th>
						<td>
							<html:select property="sfyh" styleId="sfyh"  style="width:150px"
								onmouseover="">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
							
						</td>
					</tr>
					<tr>
					<th>
						姓名
					</th>
					<td>
						 <logic:notPresent name = "xm">
					   		<input type="text" name="xm" style="width:150px"/>
					  	 </logic:notPresent>
				    	 <logic:present name = "xm">
				   			<input type="text" name="xm" value ="<bean:write name = "xm"/>" style="width:150px"/>
				   		 </logic:present>
					 </td>
					<logic:equal name="xxdm" value="10290" scope="session">
						<th>
						分管年级
						</th>
						<td>
					    <html:select property="nj" style="width:150px" styleId="nj"
					    		onmouseover=""> 
	          					<html:option value=""></html:option>
	          					<html:option value="no">未分配</html:option>   
	          					<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        			</html:select>
	        			</td>
	        			</logic:equal>
	        		   <th>
					   学生工作研究方向
					   </th>
					   <logic:notPresent name = "xsgzyjfx">
					   <td colspan="3">
					   <input type="text" name="xsgzyjfx" />
					   </td>
					   </logic:notPresent>
					    <logic:present name = "xsgzyjfx">
					    <td>
					   <input type="text" name="xsgzyjfx" value ="<bean:write name = "xsgzyjfx"/>" style="width:150px"/>
					   </td>
					   </logic:present>
					   <logic:equal name="xxdm" value="11062">
					   <th>
					   重大过失时间
					   </th>
					   <td>
					   	<input type="text" name="kssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
							onclick="return showCalendar('kssj','y-mm-dd');"/> - 
						<input type="text" name="jssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px"
							onclick="return showCalendar('jssj','y-mm-dd');"/>
					   </td>
					   </logic:equal>
					   <logic:equal name="xxdm" value="12702">
					   <th>
					    所属组别
					    </th>
					    <td>
					   		<html:select property="fdyz" styleId="fdyz" style="width:150px"
					   			onmouseover="">
					   			<html:option value=""></html:option>
								<html:options collection="fdyzList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					   </logic:equal>
					</tr>
				</tbody>
			</table>
			</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
								<font color="blue"> 
									提示：双击一行可以查看详细信息；单击表头可以排序;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
			    	<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td  id="<bean:write name="tit" property="en"/>" 
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left"
								ondblclick="getFdyInfo('view')">
								<td align="center">
										<input type="checkbox" id="checkVal" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>
								<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
								</td>
								
								<logic:iterate id="v" name="s" offset="2">
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
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			  <!--分页显示-->
			</logic:notEmpty>
			</div>
			<logic:present name="del">
				<logic:equal value="ok" name="del">
					<script>
         				 alert("删除成功！删除教职工信息并不会把相关联的用户信息删除，如有需要，请联系管理员来删除相关用户");
       				 </script>
				</logic:equal>
				<logic:equal value="no" name="del">
					<script>
         				 alert("删除失败！");
        			</script>
				</logic:equal>
			</logic:present>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功.\n添加的用户默认密码为:888888");
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
         				 alert("操作失败！");
        			</script>
				</logic:equal>
			</logic:present>
			<!-- 查询用户密码 -->
			<div style="display:none;" id="viewYhz">

				<table width='80%' class='formlist'>
				<thead>
					<tr>
						<th width='25%' colspan="2">
							------------------------------请选择要转入的用户组-----------------------------
						</th>
					</tr>
				</thead>
				<tbody>
					
					<tr>
						<th>
							用户组
						</th>
						<td>
							<html:select property="zdm" styleId="select_zdmList" style="width:120px;" >
								<html:option value=""></html:option>
								<html:options collection="yhzList" property="zdm"
									labelProperty="zmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							所属单位
						</th>
						<td>
							<html:select property="dwdm" styleId="select_dwList" style="width:120px;" >
								<html:option value=""></html:option>
								<html:options collection="dwList" property="dwdm"
									labelProperty="dwmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
					
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class='btn'>
									<font color="red">注：在用户库已存在该用户的情况下不会覆盖原有用户</font>
									<button type="button" onclick="saveTgZyhxxk()">
											确定
									</button>
									<button type="button" onclick="hiddenMessage(true,true);return false;">
											关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		
	</body>
</html>
