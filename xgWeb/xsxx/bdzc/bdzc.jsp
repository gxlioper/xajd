<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript">
		function change(obj) {
			if (null != obj.value && '' !=obj.value) {
				obj.parentNode.getElementsByTagName('input')[0].value=obj.value;
			}
		}
		
		function bdzc(code,obj) {
			var xn = $('xn').value;
			var xq = $('xq').value;
			var pkValue = obj.value+xn+xq;
			var url = '/xgxt/bdzcgl.do?method=bdzcOne&doType=update';
			url+='&pk='+pkValue;
			if (code == 13) {
				showTopWin(url,'550','380');
			}
		}
		//打开新窗口
		function showInfo(url,doType,w,h){
			
			if(curr_row == null){
				alert('请选择一行！');
				return false;
			}
			
			var dis = curr_row.getElementsByTagName('input')[0].disabled;
			
			if(doType != "view" && dis){
				alert('只有办理过学生缴费的学生才能进行报到注册！');
				return false;
			}
			
			var pk = curr_row.getElementsByTagName('input')[0].value;
			url+="&doType="+doType;
			url+="&pk="+pk;
			showTopWin(url,w,h);
		}
	</script>
</head>
	<body onload="document.getElementById('xh').focus(); xyDisabled('xy');">		
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
						<li> <a href="#" onclick="showInfo('/xgxt/bdzcgl.do?method=bdzcOne','update','700','400');" class="btn_csh">注册</a> </li>
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
								onclick="allNotEmpThenGo('/xgxt/bdzcgl.do?method=bdzc&doType=query')">
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
						<html:hidden property="queryequals_xn" styleId="xn" />
						<html:select property="queryequals_xn" disabled="true" style="width:180px">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
					<th>学期</th>
					<td>
						<html:hidden property="queryequals_xq" styleId="xq"/>
						<html:select property="queryequals_xq" disabled="true" style="width:180px">
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
						<logic:equal value="stu" name="userType" scope="session">
							<html:text property="querylike_xh" maxlength="20" style="width:180px" styleId="xh" value="${userName }" readonly="true"></html:text>
						</logic:equal>
						<logic:notEqual value="stu" name="userType" scope="session">
							<html:text property="querylike_xh" maxlength="20" 
									style="width:180px" styleId="xh" 
									onkeypress="bdzc(event.keyCode,this)"
									onblur="chkIsStu(this,'view_xsjbxx','xh')"
									></html:text>
						</logic:notEqual>
					</td>						
					<th>姓名</th>
					<td>
						<html:text property="querylike_xm" maxlength="20" style="width:180px"></html:text>
					</td>				
				</tr>													
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="queryequals_xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>专业</th>
					<td>
						<html:select property="queryequals_zydm" onchange="initBjList()"  styleId="zy" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="queryequals_bjdm"  styleId="bj" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
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
					<th>注册状态</th>
					<td>
						<html:select property="queryequals_zczt" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zcztList" property="en" labelProperty="cn"/>
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
				<font color="blue">(只有办理过学生缴费的学生才能进行报到注册；)</font>
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
						<logic:iterate id="tit" name="topTr" offset="4" scope="request">
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
							ondblclick="showInfo('/xgxt/bdzcgl.do?method=bdzcOne','view','700','400');"
							style="cursor:hand;">
							<td align=center>
								<input type="checkbox" id="cbv" name="primarykey_cbv" 	value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" 
								<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>/>
						    </td>
							<logic:iterate id="v" name="s" offset="4" >
								<td align=center>
									<bean:write name="v"/>
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
	</body>
</html>
