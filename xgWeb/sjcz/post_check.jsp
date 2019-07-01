<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
		function batch(yesNo){
			var RowsStr="!!";	
			var realTable = document.getElementById('realTable').value;	
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	}
			}
						
			if (RowsStr=="!!"){
				alert("请选择要操作的记录！");
				return false;
			}
			if (confirm("确定要批量操作吗？")) {
			    refreshForm('qgzxLogic.do?method=postBatchAuditing&type=' + yesNo);
			}
		}
		
		function del(url){
			var pkString = "";
			if(Rows[0]==null){
				alert('请选择要删除的记录！');
				return false;
			}

			for (i=0; i<Rows.length; i++){ //连接字符串
    			pkString +=Rows[i].getElementsByTagName("input")[0].value+"!!";
			}
			if (confirm("岗位删除后申请该岗位的学生信息记录也将删除，确定要批量删除岗位吗？")) {
			    refreshForm('qgzxLogic.do?method=delPost&pkString=' + pkString);
			}
		}
		
		function audit(){
			var xxdm = val('xxdm');
			var url = '/xgxt/postChkOne.do?act=view&pkVal=';
			if(xxdm == '10395'){//闽江学院
				url = "gwfb.do?method=gwfbsh&pkValue=";
			}
			chkPriseOne(url,700,620)
		}
	</script>
</head>
<body>
	<html:form action="/post_check" method="post">
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
		<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
		<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
		<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
		<input type="hidden" id="bmlb" name="bmlb" value="xy" />
		<input type="hidden" id="userName" name="userName" value="${userName}" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">
					<%--北京联合大学--%>
		          	 <logic:equal value="11417" name="xxdm">
					 	<li> <a href="#" onclick="del();" class="btn_sc">删 除</a> </li>
		           	</logic:equal>
					<li> <a href="#" onclick="batch('pass');" class="btn_shtg">审核通过</a> </li>
					<li> <a href="#" onclick="batch('nopass');" class="btn_shbtg">审核不通过</a> </li>
					</logic:equal>
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
						  <button type="button" class="btn_cx" id="search_go"
								onclick="refreshForm('/xgxt/post_check.do?go=go')">
								查询
						  </button>
						  <button type="button" id="cz"
							onclick="czSearchCond('nd-xn-xq-yrdwdm-gwxzdm-shjg-xmdm-xqdm-gwflag');return false;">
							重置					    	
                          </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
				  <tbody>
			      	<tr>
			      		<th>学年</th>
						<td>
							<html:select property="xn" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>年度</th>
						<td>
							<html:select property="nd" styleId="nd" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" styleId="xq" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
			      		<th>用人单位</th>
						<td>
							<html:select property="yrdwdm" styleId="yrdwdm" style="width:180px" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm" labelProperty="yrdwmc"/>
							</html:select>
						</td>
						<logic:notEqual value="11078" name="xxdm">
						<th>岗位性质</th>
						<td>
							<html:select property="gwxz" style="width:180px" styleId="gwxzdm" onchange="loadGwmcxx('xmdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<th>岗位名称</th>
						<td>
							<html:text property="xmdm" style="width:180px" styleId="xmdm"></html:text>
						</td>
						</logic:notEqual>
						<logic:equal value="11078" name="xxdm">
						<th>岗位名称</th>
						<td>
							<html:select property="xmdm" style="width:180px" styleId="xmdm">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdm"
									labelProperty="gwdm" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
						</logic:equal>						
					</tr>
					<logic:notEqual value="11078" name="xxdm">
					<!--非闽江学院-->
					<logic:notEqual value="10395" name="xxdm">
					<tr>
						<th>校区</th>
						<td>
							<html:select property="xqdm" style="width:180px" styleId="xqdm">
								<html:option value=""></html:option>
								<html:options collection="xiaoquList" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<th>审核结果</th>
						<td>
							<html:select property="shjg" style="width:180px" styleId="shjg">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:notEqual>
					<!--闽江学院-->
					<logic:equal value="10395" name="xxdm">
					<tr>
			      		<th>岗位类别</th>
						<td>
							<html:select property="gwflag" style="width:180px" styleId="gwflag">
								<html:option value=""></html:option>
								<html:option value="xngw">校内岗位</html:option>
								<html:option value="xwgw">校外岗位</html:option>
							</html:select>
						</td>
						<th>审核结果</th>
						<td>
							<html:select property="shjg" style="width:180px" styleId="shjg">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:equal>
					</logic:notEqual>
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
							<font color="blue">提示：双击可以查看详细信息并审核，单击表头可以排序！</font> 
				 		 </logic:notEmpty>
			    </span>
			    </h3>
				   
			  <logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
					<td>
						<input type="checkbox" name="all" value="all" onclick="chec()"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="3">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowMoreClick(this,'',event);"
							style="cursor:hand;background-color:
							   <logic:iterate id="v" name="s" offset="0" length="1">
							   <bean:write name="v"/>
							   </logic:iterate>
							   "
							ondblclick="audit()">
							<td>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="checkbox" name="pkV" value="<bean:write name="v"/>"/>
								</logic:iterate>
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
			   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			<!--分页显示-->
			</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
           	
			<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert('操作成功！');				
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<logic:notEmpty name="mes">
			<input type="hidden" id="mes" name="mes" value="${mes}"/>
			<script>
				alert(document.getElementById("mes").value);
			</script>
			</logic:notEmpty>
			<logic:empty name="mes">
			<script>
				alert("操作失败！");
			</script>
			</logic:empty>
			</logic:equal>
			</logic:present>
	</html:form>
</body>
</html>
