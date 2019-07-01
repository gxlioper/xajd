<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script type="text/javascript">
		function sendXh(){
			var vel = window.dialogArguments.document.forms[0].xh;
			vel.value = curr_row.cells[0].innerText;
			
			window.dialogArguments.autoFillStuInfo(13, vel);
			window.close();
		}
	</script>
</head>
	<body onload="xyDisabled('xy');">			
			<html:form action="/stu_qgzx_info" method="post">
			<input type="hidden" id="modiTag" name="modiTag" value="${modiTag }"/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />	
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" name="zzmm" id="zzmm" value="${zzmm}" />
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 信息查询 - 个人信息</a>
				</p>
			</div>
			
			<div class="toolbox">				 
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
								onclick="document.forms[0].go.value='go';refreshForm('/xgxt/stu_qgzx_info.do');this.disabled=true;">
							查询
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
		      		<th>年度</th>
					<td>
						<input type="hidden" name="nd" value="${qgzxInfo.nd}" />
						<html:select property="nd" value="${qgzxInfo.nd}" styleId="nd" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="ndList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>
		      		<th>学年</th>
					<td>
						<input type="hidden" name="xn" value="${qgzxInfo.xn}" />
						<html:select property="xn" value="${qgzxInfo.xn}" styleId="xn" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
					<th>学期</th>
					<td>
						<input type="hidden" name="xq" value="${qgzxInfo.xq}" />
						<html:select property="xq" styleId="xq" value="${qgzxInfo.xq}" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
		      		<th>年级</th>
					<td>
						<html:select property="nj" 
							onchange="initZyList();initBjList();" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<th>学号</th>
					<td>
						<html:text property="xh" style="width:100" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');}"/>
					</td>
					<th>姓名</th>
					<td>
						<html:text property="xm" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');}"/>
					</td>
				</tr>
				<tr>
		      		<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" 
							onchange="initZyList();initBjList();" styleId="xy" style="width:200px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>专业</th>
					<td>
						<html:select property="zydm"  styleId="zy" style="width:200px"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>班级</th>
					<td>
						<html:select property="bjdm"  styleId="bj" style="width:200px">
							<logic:notEqual value="yes" name="isBzr">
							<html:option value=""></html:option>
							</logic:notEqual>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
			  </tbody>
			</table>				
		</div>
		<div class="formbox">
			<h3 class="datetitle_01">
		    <span>
		    	查询结果&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">未找到任何记录！</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
				<logic:empty name="zzmm">
					<font color="blue">双击一行可以选定；单击表头可以排序</font>
				</logic:empty>
				<logic:notEmpty name="zzmm">
					<logic:equal name="zzmm" value="ybdy">
					<font color="blue">提示：双击一行可以选定；单击表头可以排序。注:查询结果为非党员</font>
					</logic:equal>
					<logic:equal name="zzmm" value="rdjjfz">
					<font color="blue">提示：双击一行可以选定；单击表头可以排序。注:查询结果为非党员及预备党员</font>
					</logic:equal>
				</logic:notEmpty>
				</logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			 <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr" offset="0">
						<td>${tit }</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;"
							ondblclick="sendXh()">
							<logic:iterate id="v" name="s" offset="0" length="0">
								<td align="center">
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
			      <script type="text/javascript">
					$('choose').className="hide";
				</script>
			    <!--分页显示-->
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
			</logic:notEmpty>
		</div>
	</html:form>
</body>
</html>
