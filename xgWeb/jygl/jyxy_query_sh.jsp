<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/JyxyDataSearch.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
    
    function jygljyxyDataExport() {
	       document.forms[0].action = "/xgxt/jygljyxyDataExport.do?tableName=jygl_jyxy";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		
		function viewMoreinfo1(doType){
		var url ="/xgxt/JyglJyxyViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showOpenWindow(url, 800, 600);
		 }
		}
		
		
		function jyxyupdate(doType){
		var url ="/xgxt/turnjyxyupdate.do?whichArea=shenhe&doType=first&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 900, 600);
		         //showOpenWindow(url, 900, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function JyglJyxyDel(doType2){
		var url = "/xgxt/JyglJyxyDel.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType2 == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		function jyxyfdysh(doType){
			var url ="/xgxt/turnjyxyfdysh.do?doType=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 800, 600);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
		
		function jyxyxxsh(doType){
			var url ="/xgxt/turnjyxyxxsh.do?doType=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showOpenWindow(url, 800, 600);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
		
		
		</script>
	</head>

	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>就业管理 - 就业协议方案 - 协议审核查询</a>
				</p>
			</div>
			<div class="toolbox">
				<div class="buttonbox">
				    <ul>
					<logic:notEqual value="12061" name="xxdm" scope="session">
						<logic:notEqual value="11122" name="xxdm" scope="session">
							<logic:equal name="who" value="fudaoyuan">
								<li>
									<a href="#" onclick="jyxyfdysh('shenhe')" class="btn_sh">
										辅导员审核 </a>
								</li>
							</logic:equal>

							<logic:equal name="who" value="teacher">
								<li>
									<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_shtg">
										学校审核 </a>
								</li>
							</logic:equal>
						</logic:notEqual>
						<logic:equal value="11122" name="xxdm" scope="session">
							<logic:equal name="whos" value="xy">
								<li>
									<a href="#" onclick="jyxyfdysh('shenhe')" class="btn_sh"> <bean:message
											key="lable.xsgzyxpzxy" />审核 </a>
								</li>
							</logic:equal>
							<logic:equal name="whos" value="xx">
								<li>
									<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_shtg">
										学校审核 </a>
								</li>
							</logic:equal>
						</logic:equal>
					</logic:notEqual>
					<logic:equal value="12061" name="xxdm" scope="session">
						<logic:equal name="whos" value="xy">
							<li>
								<a href="#" onclick="jyxyfdysh('shenhe')" class="btn_sh"> <bean:message
										key="lable.xsgzyxpzxy" />审核 </a>
							</li>
						</logic:equal>
						<logic:equal name="whos" value="xx">
							<li>
								<a href="#" onclick="jyxyxxsh('shenhe')" class="btn_shtg">
									学校审核 </a>
							</li>
						</logic:equal>
					</logic:equal>
					<li>
						<a href="#" onclick="jyxyupdate('update')" class="btn_xg"> 修改
						</a>
					</li>
					<li>
						<a href="#" onclick="JyglJyxyDel('del')" class="btn_sc"> 删除 </a>
					</li>
					<logic:equal name="who" value="teacher">
						<li>
							<a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a>
						</li>
						<li>
							<a href="#" onclick="jygljyxyDataExport()" class="btn_dc"> 导出
							</a>
						</li>
					</logic:equal>
					</ul></div>
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
							<tr>
								<td colspan="8">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" onclick="querygo('/xgxt/JyxyDataSearch.do')" id="query_go">
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
								<tr style="cursor:hand">
										<%--								<logic:equal value="12061" name="xxdm" scope="session">--%>
										<logic:equal name="whos" value="xx">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											<html:select name="rs1" property="xymc" style="width:150px">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xymc"
													labelProperty="xymc" />
											</html:select>
										</td>
										</logic:equal>
										<logic:equal name="whos" value="xy">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
						       				 <html:text name="rs1" property="xymc" style="width:150px"
												readonly="true" />
										</td>
											<%--								</logic:equal>--%>
										</logic:equal>
										<%--								<logic:notEqual value="12061" name="xxdm" scope="session">--%>
										<%--								<logic:equal name="who" value="teacher">--%>
										<%--								<bean:message key="lable.xsgzyxpzxy" />：--%>
										<%--								<html:select name="rs1" property="xymc" style="width:150px">--%>
										<%--										<html:option value=""></html:option>--%>
										<%--										<html:options collection="xyList" property="xymc"--%>
										<%--											labelProperty="xymc" />--%>
										<%--									</html:select>--%>
										<%--								</logic:equal>--%>
										<%--								<logic:equal name="who" value="fudaoyuan">--%>
										<%--							    <bean:message key="lable.xsgzyxpzxy" />：--%>
										<%--						        <html:text name="rs1" property="xymc" style="width:150px"--%>
										<%--										readonly="true" />--%>
										<%--								</logic:equal>--%>
										<%--								</logic:notEqual>--%>
										<th>
											入学年度
										</th>
										<td>
										<html:select name="rs1" property="nd" style="width:60px">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nj"
												labelProperty="nj" />
										</html:select>
										</td>
										<th>
											毕业年度
										</th>
										<td>
										<html:select name="rs1" property="bynd" style="width:60px">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nj"
												labelProperty="nj" />
										</html:select>

										<logic:equal value="11122" name="xxdm" scope="session">
										<th>
											<bean:message key="lable.xsgzyxpzxy" />审核
										</th>
										<td>
											<html:select name="rs1" property="fdysh" style="width:65px">
													<html:option value=""></html:option>
													<html:option value="未审核">未审核</html:option>
													<html:option value="已通过√">已通过√</html:option>
													<html:option value="未通过X">未通过X</html:option>
												</html:select>
										</td>
										</logic:equal>
										<%--<logic:notEqual value="11122" name="xxdm" scope="session">
												辅导员审核：
												</logic:notEqual>
												<html:select name="rs1" property="fdysh" style="width:65px">
													<html:option value=""></html:option>
													<html:option value="未审核">未审核</html:option>
													<html:option value="已通过√">已通过√</html:option>
													<html:option value="未通过X">未通过X</html:option>
												</html:select>
												
												--%>
										<logic:equal value="xx" name="userlx">
										<th>
											学校审核
										</th>
										<td>
											<html:select name="rs1" property="xxsh" style="width:65px">
												<html:option value=""></html:option>
												<html:option value="未审核">未审核</html:option>
												<html:option value="已通过√">已通过√</html:option>
												<html:option value="未通过X">未通过X</html:option>
											</html:select>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<th>
										学号
									</th>
									<td>
										<input type="text" name="xsxh"
											value="<bean:write name="rs1" property="xsxh"/>" />
									</td>
									<th>
										姓名
									</th>
									<td>
										<input type="text" name="name"
											value="<bean:write name="rs1" property="name"/>"
											style="width:95px" />
									</td>
									<th>
										性别
									</th>
									<td>
										<html:select name="rs1" property="xbdm" style="width:60px">
											<html:option value=""></html:option>
											<html:option value="1">
												男
											</html:option>
													<html:option value="2">
												女
											</html:option>
										</html:select>
									</td>
									<th>
										学生类别
									</th>
									<td>
										<html:select name="rs1" property="xslb" style="width:65px">
											<html:option value=""></html:option>
											<html:option value="专科生">
												专科生
											</html:option>
													<html:option value="本科生">
												本科生
											</html:option>
													<html:option value="研究生">
												研究生
											</html:option>
										</html:select>
									</td>
									
								</tr>
								<tr>
									<th>
										就业标志
									</th>
									<td>
										<html:select name="rs1" property="wjybz" style="width:65px">
											<html:option value=""></html:option>
											<html:option value="0">已就业</html:option>
											<html:option value="1">未就业</html:option>
										</html:select>
									</td>
									
									<logic:equal value="11122" name="xxdm" scope="session">
									<th>
										协议书编号
									</th>
									<td>
									<html:text property="xysbh"></html:text>
										
									</td>
									<td colspan="4"></td>
									</logic:equal>
									<logic:notEqual value="11122" name="xxdm" scope="session">
										<td colspan="6"></td>
									</logic:notEqual>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp;
								<logic:empty name="rs">
									<font color="red">未找到任何记录！</font>
								</logic:empty> 
								<logic:notEmpty name="rs">
									<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
								</logic:notEmpty> </span>
						</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" id="rsTable" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="2" length="9">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<logic:equal value="12061" name="xxdm" scope="session">
											<logic:iterate id="tit" name="topTr" offset="10" length="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:message key="lable.xsgzyxpzxy" />
													审核
												</td>
											</logic:iterate>
											<logic:equal value="xx" name="userlx">
												<logic:iterate id="tit" name="topTr" offset="11" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</logic:equal>
										</logic:equal>

										<logic:notEqual value="12061" name="xxdm" scope="session">
											<logic:equal value="11122" name="xxdm" scope="session">
												<logic:iterate id="tit" name="topTr" offset="10" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)" nowrap>
														<bean:message key="lable.xsgzyxpzxy" />
														审核
													</td>
												</logic:iterate>
												<logic:equal value="xx" name="userlx">
													<logic:iterate id="tit" name="topTr" offset="11" length="1">
														<td id="<bean:write name="tit" property="en"/>"
															onclick="tableSort(this)" nowrap>
															<bean:write name="tit" property="cn" />
														</td>
													</logic:iterate>
												</logic:equal>
											</logic:equal>
											<logic:notEqual value="11122" name="xxdm" scope="session">
												<logic:iterate id="tit" name="topTr" offset="11" length="1">
													<td id="<bean:write name="tit" property="en"/>"
														onclick="tableSort(this)" nowrap>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
											</logic:notEqual>
										</logic:notEqual>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="viewMoreinfo1('view')">
										<td style="display:none">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2" length="9">
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:equal value="12061" name="xxdm" scope="session">
											<logic:iterate id="v" name="s" offset="10" length="1">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<logic:equal value="xx" name="userlx">
												<logic:iterate id="v" name="s" offset="11" length="1">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</logic:equal>
										</logic:equal>

										<logic:notEqual value="12061" name="xxdm" scope="session">
											<logic:equal value="11122" name="xxdm" scope="session">
												<logic:iterate id="v" name="s" offset="10" length="1">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:equal value="xx" name="userlx">
													<logic:iterate id="v" name="s" offset="11" length="1">
														<td align="center">
															<bean:write name="v" />
														</td>
													</logic:iterate>
												</logic:equal>
											</logic:equal>
											<logic:notEqual value="11122" name="xxdm" scope="session">
												<logic:iterate id="v" name="s" offset="11" length="1">
													<td align="center">
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</logic:notEqual>
										</logic:notEqual>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							<script type="text/javascript">
									$('choose').className="hide";
							</script>
					</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("删除成功!");
                </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("删除失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

