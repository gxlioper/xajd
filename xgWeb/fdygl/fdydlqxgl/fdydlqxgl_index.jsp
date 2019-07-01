<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/lrh_new_js.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="JavaScript">
		
		function rcgl_sfbd_view()
		{
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_view&xn_id="+xn_id,525,300);
		}
		
		function rcgl_sfbd_add()
		{
			showTopWin("/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_add",550,425);
		}
		
		function rcgl_sfbd_del()
		{
			var len = document.getElementsByName("xz").length;
			var str = "";
			var s1="!!SplitSignOne!!";
			var strxx = new Array();
           	for(i=0;i<len;i++){
              if(document.getElementsByName("xz")[i].checked){
                 	str=str+document.getElementsByName("xz")[i].value+s1;
              	}
  	 		}
  	 		str = str.substring(0,str.length-16);
  	 		strxx = str.split(s1);
  	 		if(1!=strxx.length)
  	 		{
  	 			alert("请谨慎删除，只能单行删除！");
  	 			return false;
  	 		}
  	 		else
  	 		{
  	 			refreshForm("/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_del&xzstr="+str);
  	 		}
		}
		
		 function select_all(){
  			 for(i=0;i<document.getElementsByName("xz").length;i++)
   			 document.getElementsByName("xz")[i].checked=document.getElementsByName("qbxz")[0].checked;
		}
		
		function sfbd_qrbd()
		{
			 var len = document.getElementsByName("xz").length;
			 var str = "";
			 var s1="!!SplitSignOne!!";
           		for(i=0;i<len;i++){
              		if(document.getElementsByName("xz")[i].checked){
                 		str += document.getElementsByName("xz")[i].value;
                 		str += s1;
              			}
          			 }
           		if(str == 0){
              			alert("至少选择一条记录！");
              			return false;
           			}
           		else{
              			refreshForm('/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_qrbd&xzstr='+str);
          			 }
				}
				
		function sfbd_qxbd()
		{
			 var len = document.getElementsByName("xz").length;
			 var str = "";
			 var s1="!!SplitSignOne!!";
           		for(i=0;i<len;i++){
              		if(document.getElementsByName("xz")[i].checked){
                 		str += document.getElementsByName("xz")[i].value;
                 		str += s1;
              			}
          			 }
           		if(str == 0){
              			alert("至少选择一条记录！");
              			return false;
           			}
           		else{
              			refreshForm('/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_qxbd&xzstr='+str);
          			 }
				}
				
				
		function fdydlqxgl_fpqx()
		{
			 alert("注意！分配权限的用户初始密码为'888888'！");
			 var len = document.getElementsByName("xz").length;
			 var str = "";
			 var s1="!!SplitSignOne!!";
           		for(i=0;i<len;i++){
              		if(document.getElementsByName("xz")[i].checked){
                 		str += document.getElementsByName("xz")[i].value;
                 		str += s1;
              			}
          			 }
           		if(str == 0){
              			alert("至少选择一条记录！");
              			return false;
           			}
           		else{
              			document.all['strtmp'].value=str;
              			refreshForm('/xgxt/fdydlqxgl.do?doType=fpqx_fdyqlqxxxb');
          			 }
		}
		function fdydlqxgl_shqx()
		{
			 var len = document.getElementsByName("xz").length;
			 var str = "";
			 var s1="!!SplitSignOne!!";
           		for(i=0;i<len;i++){
              		if(document.getElementsByName("xz")[i].checked){
                 		str += document.getElementsByName("xz")[i].value;
                 		str += s1;
              			}
          			 }
          		if(str == 0){
              			alert("至少选择一条记录！");
              			return false;
           			}
			if (confirm("确定要收回辅导员登陆权限吗？")) {
					document.all['strtmp'].value=str;
					refreshForm("/xgxt/fdydlqxgl.do?doType=shqx_fdyqlqxxxb");
					return true;
					} 
					else {
						return false;
					}
		}
				
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/fdydlqxgl" method="post">
		<input type="hidden" name="strtmp" value="" />
		<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="session"/>" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>思政队伍 - 信息维护 - 辅导员登入权限管理</a>
			</p>
		</div>
		<div class="toolbox">
		 <!-- 按钮 -->
		 <logic:equal value="yes" name="writeAble" scope="request">
		 <div class="buttonbox">
		    <ul>
			<li> <a href="#" onclick="fdydlqxgl_fpqx()" class="btn_shtg"> 分配权限 </a> </li>
		    <li> <a href="#" onclick="fdydlqxgl_fpqx()" class="btn_shbtg"> 收回权限 </a> </li>
			<li> <a href="#" onclick="refreshForm('/xgxt/fdydlqxgl.do?doType=update_fdydlqxxxb');" class="btn_sx"> 辅导员权限数据同步 </a> </li>
		    </ul>
		 </div>
		 </logic:equal>
		<div class="searchtab">
		<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <button class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('/xgxt/fdydlqxgl.do?doType=search')">
		              	查 询
		              </button>
		              <button  style="display:none;" id="search_go1" 
									onclick="refreshForm('/xgxt/fdydlqxgl.do?doType=search')">
								</button>
		              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
		
				<tbody>
						<tr>
							<th align="left">
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								辅导员编号：
							</th>
							<td>
								<html:text property="zgh" styleId="zgh"/>
							</td>
							<th>
								姓名：
							</th>
							<td>
								<html:text property="xm"  styleId="xm"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="6">
								是否有登陆权限:
								<html:select property="sfyqx" style="width:100px" styleId="sfyqx"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="sfyqxList" property="DMH"
										labelProperty="DMMC" />
								</html:select>
							</td>
						</tr>
					</thead>
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
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			<logic:notEmpty name="rs">
					  <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="center" width="3%">
									<input type="checkbox" name="qbxz" value="all" onclick="select_all()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;background-color:">	
									<td align="center">
										<input type="checkbox" name="xz" value="<bean:write name="s" property="zgh"/>">
									</td>
									<td>
										<input type="hidden" id="zgh" name="zgh"
										value="<bean:write name="s" property="zgh"/>" />
										<bean:write name="s" property="zgh"/>
									</td>
									<td>
										<bean:write name="s" property="xm"/>
									</td>
									<td>
										<bean:write name="s" property="xb"/>
									</td>
									<td>
										<bean:write name="s" property="bmmc"/>
									</td>
									<td>
										<bean:write name="s" property="sfyqx"/>
									</td>
							</tr>
					   </logic:iterate>
					   </tbody>
					</table>	
			</logic:notEmpty>
			</div>
			<logic:notEmpty name="message">
					<logic:equal name="message" value="del_success">
						<script>
							alert("删除成功!");
							document.getElementById("search_go1").click();
						</script>
					</logic:equal>
					<logic:equal name="message" value="update_success">
						<script>
							alert("更新成功!");
							document.getElementById("search_go1").click();
						</script>
					</logic:equal>
					<logic:equal name="message" value="no">
						<script>alert("保存失败!咨询师编号已经存在！")</script>
					</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
