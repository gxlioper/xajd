<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("请选择一行要修改的记录！");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin(url+pkValue,'800','600');
		}
		
		function batchSh(url){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("请选择需要操作的记录！");
				return false;
			}
			refreshForm(url);
		}
		
		/**全选*/
		function selectAll(id){
			var checkBoxArr = document.getElementsByName("cbv");
			if(id!= null && id != ""){
				checkBoxArr = document.getElementsByName(id);
			}
			var selall = document.getElementById('cb');
			if(selall.checked==true){
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = true;
				}
			} else {
				for(var i=0;i<checkBoxArr.length;i++){
					checkBoxArr[i].checked = false;
				}
			}
		}
	</script>
</head>
	<body onload="xyDisabled('xy');">		
		<html:form action="/qgzxZgdzdx.do?method=qgzxzxsh" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userName" name="userName" value="${userName }"/>
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 -  勤工助学之星 - 勤工助学之星审核</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <logic:equal value="yes" name="writeAble" scope="request">
							<li> <a href="#" onclick="batchSh('qgzxZgdzdx.do?method=batchQgzxzxSh&yesNo=通过');" class="btn_shtg">审核通过 </a> </li>
					        <li> <a href="#" onclick="batchSh('qgzxZgdzdx.do?method=batchQgzxzxSh&yesNo=不通过');" class="btn_shbtg">审核不通过</a> </li>
					  </logic:equal>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=qgzxzxsh')">
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
							<html:select property="xn" styleId="xn" style="width:80px">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>	
						</td>
						<th>年度</th>
						<td>
							<html:select property="nd" styleClass="select" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" styleId="xq" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" 
								labelProperty="xqmc"/>
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:175px" styleClass="select" styleId="xy"
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>	
						</td>
						<th>专业</th>
						<td>
							<html:select property="zydm" style="width:175px" styleClass="select" styleId="zy"
								onchange="initBj();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>
						</td>
						<th>班级</th>
						<td>
							<html:select property="bjdm" style="width:175px" styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>学号</th>
						<td>
							<html:text property="xh"/>
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
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">提示：双击记录可以审核，单击表头可以排序！</font> 
				 		 </logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="2">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;" align="center" 
							bgcolor="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" 
							ondblclick="modidata('qgzxZgdzdx.do?method=showQgzxzxsh&pkValue=')">
							<td>
								<input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
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
				</logic:notEmpty>
			</div>
				
			<div id="tmpdiv"></div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
					alert('操作成功!');
					document.getElementById('search_go').click();
				</script>	
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>
				alert("操作失败！");				
			</script>
		</logic:equal>	
		</logic:present>
		
	</body>
</html>
