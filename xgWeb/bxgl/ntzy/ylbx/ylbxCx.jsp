<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function checkTimes(){
			var blog=checkSearchTj('kssj','jssj');
			if(blog){
				allNotEmpThenGo('ntzyYlbx.do?method=ylbxCx&doType=qry')
			}
		}
	</script>
</head>
	<body>
		<html:form action="ntzyYlbx" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${BzrQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" id="realTable" name="realTable" value="bxxx_nnzy_ylbxb" />
			<logic:notEqual name="userType" value="xx">
				<logic:notEqual name="userType" value="admin">
					<input type="hidden" name="querylike_jbbm" id="querylike_jbbm" value="${jbbm}"/>
				</logic:notEqual>
			</logic:notEqual>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 
			 <div class="buttonbox">
			    <ul>
			    <logic:equal name="writeAble" value="yes">
				<li> <a href="#" onclick="showTopWin('ntzyYlbx.do?method=ylbxSq',700,500)" class="btn_zj"> 增加 </a> </li>
			    <li> <a href="#" onclick="modi('ntzyYlbx.do?method=ylbxOne&doType=view&act=modi')" class="btn_xg"> 修改 </a> </li>
				<li> <a href="#" onclick="dataBatch('ntzyYlbx.do?method=ylbxCx&doType=del')" class="btn_sc"> 删除 </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a> </li>
				</logic:equal>
				<li> <a href="#" onclick="wjcfDataExport('ntzyYlbx.do?method=expDate')" class="btn_dc"> 导出 </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <button class="btn_cx" id="search_go" 
		              	onclick="checkTimes()">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
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
								<logic:equal name="userType" value="stu">
									<html:text property="xh" styleId="xh" readonly="true" style="width:90px" value="${userName}"/>
									<html:hidden property="querylike_xh" styleId="xh" value="${userName}"/>
								</logic:equal>
								<logic:notEqual name="userType" value="stu" >
									<html:text property="querylike_xh" styleId="xh" style="width:90px" />
								</logic:notEqual>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xm"  style="width:90px"/>
							</td>
							<th>
								性别
							</th>
							<td>
								<html:select property="queryequals_xb" styleId="xb">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
						    	年级
						    </th>
						    <td>
							      <html:select property="queryequals_nj" styleId="nj">
							        	<html:option value=""></html:option>
							        	<html:options collection="njList" property="nj" labelProperty="nj" />
							        </html:select>	
							</td>
							<th>
								保险时间
							</th>
							<td>
								 <html:text property="kssj" styleId="kssj"  style="width:90px" 
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
								--	
								<html:text property="jssj" styleId="jssj"   style="width:90px"
									onclick="return showCalendar('jssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
							<td colspan="2">
						</tr>
						<tr>
							
						    	<logic:equal name="userType" value="xy">
						    		  <th>
						    		  <bean:message key="lable.xb" />
						    		  </th>
						    		  <td>
								        <html:select property="xydm" disabled="true"  style="width:150px"
								        	>
								        	<html:option value=""></html:option>
								        	<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								        </html:select>	
							        	<html:hidden property="queryequals_xydm" value="${userDep}" styleId="xy"/>
							        </td>
						    	</logic:equal>
						    	<logic:notEqual name="userType" value="xy">
						    		 <th>
						    		  <bean:message key="lable.xb" />
						    		  </th>
						    		  <td>
							        <html:select property="queryequals_xydm"  style="width:150px" onchange="initZyList();initBjList();" 
							        	styleId="xy">
							        	<html:option value=""></html:option>
							        	<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							        </html:select>	
							        </td>
						    	</logic:notEqual>
						 		<th>
						        专业
						        </th>
						        <td>
						        <html:select property="queryequals_zydm" style="width:150px" onchange="initBjList();" 
						        	styleId="zy">
						        	<html:option value=""></html:option>
						        	<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						        </html:select>	
						        </td>
						        <th>
						        班级
						        </th>
						        <td>
						        <html:select property="queryequals_bjdm" style="width:150px"
						        	 styleId="bj">
						        	<html:option value=""></html:option>
						        	<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						        </html:select>	
							</td>
							
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
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('ntzyYlbx.do?method=ylbxOne&doType=view&act=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>/>
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=ntzyYlbxForm"></jsp:include>
				</logic:notEmpty>
			</div>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
