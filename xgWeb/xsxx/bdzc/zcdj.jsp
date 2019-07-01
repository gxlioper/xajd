<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/xsxx/bdzc.js"></script>
	<script type="text/javascript">
		function change(obj) {
			if (null != obj.value && '' !=obj.value) {
				obj.parentNode.getElementsByTagName('input')[0].value=obj.value;
			}
		}
		
		function tbZczt(){
			 refreshForm('/xgxt/bdzcgl.do?method=zcdj&doType=zczttb');
		}
	</script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/bdzcgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<logic:equal value="xy" name="userType">
				<logic:equal value="false" name="isFdy">
					<html:hidden property="queryequals_xydm" value="${userDep }"/>
				</logic:equal>
			</logic:equal>
			
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
						<li> <a href="#" onclick="if (isChecked('primarykey_cbv')) refreshForm('/xgxt/bdzcgl.do?method=zcdj&doType=save&zczt=注册');" class="btn_csh">批量注册</a> </li>
						<li> <a href="#" onclick="if (isChecked('primarykey_cbv')) refreshForm('/xgxt/bdzcgl.do?method=zcdj&doType=save&zczt=未注册');" class="btn_csh">撤消注册</a> </li>
						<li> <a href="#" onclick="tbZczt();" class="btn_sx">同步注册信息</a> </li>
						<li> <a href="#" onclick="expData('/xgxt/bdzcgl.do?method=zcdj&doType=expPageData');" class="btn_dc">导出数据</a> </li>
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
								onclick="allNotEmpThenGo('/xgxt/bdzcgl.do?method=zcdj&doType=query')">
							查询
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
				<tr>
					<th>学年</th>
					<td>
						<html:select property="queryequals_xn" style="width:180px">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>	
					</td>
					<th>学期</th>
					<td>
						<html:select property="queryequals_xq" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="xq" property="xqdm" labelProperty="xqmc"/>
						</html:select>
					</td>
					<th></th>
					<td>
						
					</td>
				</tr>
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
					<th>是否缓注册</th>
					<td>
						<html:select property="queryequals_ishzc" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="hzcList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<th>是否绿色通道</th>
					<td>
						<html:select property="queryequals_isLstd" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="hzcList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<th>是否欠费</th>
					<td>
						<html:select property="queryequals_sfqf" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="hzcList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>注册状态</th>
					<td>
						<html:select property="queryequals_zczt" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zcztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<th></th>
					<td>
						
					</td>
					<th></th>
					<td>
						
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
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
					    <td>
							<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>
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
							style="cursor:hand;">
							<td align=center>
								<input type="hidden" name="xn" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="hidden" name="xq" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="cbv" name="primarykey_cbv" 	value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
						    </td>
							<logic:iterate id="v" name="s" offset="3" >
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>							
						</tr>
					</logic:iterate>
				</tbody>			
			   </table>
			   </div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bdzcForm"></jsp:include>
			    <!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
		 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("成功更改"+${count}+"条记录的注册状态");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("操作失败 ！");
	         	</script>
			</logic:equal>
		</logic:present>
		<logic:present name="message" >
				<script>
	       	 	 alert("${message}!");
	         	</script>
		</logic:present>
	</body>
</html>
