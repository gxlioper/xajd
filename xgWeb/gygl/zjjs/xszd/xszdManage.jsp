<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript">
	//查询结果集
	function searchRs(){
		allNotEmpThenGo('gyglZjjs.do?method=xszdManage');
	}
	
		//显示奖学金审核详细
	function showXszdDetail(){
		
		var pk = curr_row.getElementsByTagName('input')[0].value;
		var url = "gyglZjjs.do?method=xszdUpdate&mklx=view";
			url+= "&pk="+pk;

		showTopWin(url,800,600);
	}
	
	function expXszdToExcle(){
			
		var url = "gyglZjjs.do?method=expXszdToExcle";

		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}

	function printSqb(){
		var checkbox = jQuery('input[name=primarykey_checkVal]:checked');
		if (checkbox.length != 1){
			alertInfo('请选择一条您要打印的记录');
			return false;
		}else {
			var pkValue = jQuery(checkbox).val();
			window.open('gyglZjjs.do?method=printSqb&pk='+pkValue);
		}
	}
	</script>
	</head>
	
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.过滤条件中的审核状态过滤是针对最终审核结果的，也就是<font color="blue"><bean:message key="lable.xb" />审核</font>。</br>
				2.勾选一条记录后，执行<font color="blue">打印申请表</font>操作，可以输出该学生的申请表，之后可执行打印操作。</br>
				3.执行<font color="blue">导出报表</font>操作，可以输出Excel格式的统计报表，统计范围是根据您所选择的过滤条件来定的。</br>
				4.对一条记录进行<font color="blue">双击</font>操作，可以查看详细情况。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/gyglZjjs" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="shzt" styleId="shzt" value="" />
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_dy"
								onclick="printSqb();return false;return false;"
								class="btn_dc">
								打印申请表
							</a>
						</li>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" id="btn_dc"
									onclick="expXszdToExcle();return false;return false;"
									class="btn_dc">
									导出报表
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
					</span>
				</h3>

				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					
					<!--内容 -->
					<logic:iterate name="rs" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="showXszdDetail();">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="primarykey_checkVal"  
										value="${v }"		
										<logic:empty name="v">disabled="disabled"</logic:empty>
									/>
								</td>
								
							</logic:iterate>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>						
						</tr>
					</logic:iterate>
					<!--内容 end-->
				</table>

				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZjjsXszdForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
		</html:form>
	</body>
</html>
