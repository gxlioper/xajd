<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function delData(){
				var pkValue=document.getElementsByName("primarykey_pkValue");
				var blog=false;
				for(i=0;i<pkValue.length;i++){
					if(pkValue[i].checked){
						blog=true;
						break;
					}
				}
				if(!blog){
					alert("请选择需要删除的数据!");
					return false;
				}
				
				if(confirm("确定要删除已勾选的异常数据吗？")){
					refreshForm("/xgxt/dataDetect.do?method=disposeManage&doType=del");
				}
			}
			
			function goDataDetect(){
				refreshForm("/xgxt/dataDetect.do?method=detectManage&mklx="+$("mklx").value);
			}
		</script>
	</head>

	<body onload="">
		<html:form action="/dataDetect" >
			<button id="search_go"
				onclick="allNotEmpThenGo('gyglYcjc.do?method=queryYcsj')"
				style="display:none"></button>
			<html:hidden property="tableName" styleId="tableName" />
			<html:hidden property="yclxdm" styleId="yclxdm" />
			<html:hidden property="mklx" styleId="mklx" />
			<html:hidden property="pk" styleId="pk" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					在本模块查询出的所有数据,皆为"异常数据"。勾选需要删除的"异常数据"后，点击"删除"按钮可以将其删除。
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 标题 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="delData();return false;" class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" onclick="goDataDetect();return false;"
									class="btn_fh">返回</a>
							</li>
						</logic:equal>
					</ul>
				</div>
			</div>
			<!-- 查询结果-->
			<div class="formbox">
				<h3 class="datetitle_01">
					<logic:empty name="rs">
						<font color="red">无异常数据</font>
					</logic:empty>
					<logic:notEmpty name="rs">
					数据异常 &nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">${yclxmc}</font>
					</logic:notEmpty>
				</h3>
				
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center">
									<td>
										<input type="checkbox" style="display:none" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
										<td nowrap="nowrap"
											id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:notEmpty name="rs">
							<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" ondblclick="">
									<!-- checkbox -->
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td nowrap="nowrap">
											<input type="checkbox" id="checkVal"
												name="primarykey_pkValue" value="${v }" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
							</logic:notEmpty>
							<%@ include file="/comm/noRows.jsp"%>
							<!--内容 end-->
						</table>
					</div>
					<!--分页-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=dataDetectForm"></jsp:include>
					<!--分页end-->
				
			</div>
			<!-- 查询结果 end-->

		</html:form>

	</body>
</html>
