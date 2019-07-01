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
		allNotEmpThenGo('gyglZjjs.do?method=xszdsh');
	}
	
	//显示奖学金审核详细
	function showXszdDetail(){
		var num = jQuery("input[name=primarykey_checkVal]:checked").length;
		
		if(num == 0){
			alertError("请勾选一条您希望审核的记录");
			return false;
		}if(num > 1){
			alertError("只能勾选一条记录，请确认");
			return false;
		}
		
		var pk = jQuery("input[name=primarykey_checkVal]:checked")[0].value;
		var url = "gyglZjjs.do?method=xszdUpdate";
			url+= "&pk="+pk;

		showTopWin(url,800,600);
	}
	
	//批量保存
	function saveShzt(shzt){
		var num = jQuery("input[name=primarykey_checkVal]:checked").length;
		
		if(num == 0){
			alertError("请勾选您希望审核的记录");
			return false;
		}
		
		$("shzt").value = shzt;
		confirmInfo("您是否要审核"+shzt+"该申请记录? 请确认",submitShzt);
	}
	
	function submitShzt(tag){
		if(tag == "ok"){
			var shzt = $("shzt").value;
			
			showTips('处理中，请稍后......');
			var url = "gyglZjjs.do?method=xszdsh&doType=save";
				url+= "&shzt="+shzt;
				
			refreshForm(url);
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
				1.如果您是辅导员的话，结果集中仅列出<bean:message key="lable.xb" />用户未审核过的申请记录，如果您想查看的话，请前往结果查询模块。</br>
				2.如果您是<bean:message key="lable.xb" />用户的话，结果集中仅列出辅导员用户审核通过的申请记录，如果您想查看的话，请前往结果查询模块。
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
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" id="btn_sh"
									onclick="showXszdDetail();return false;"
									class="btn_sh">
									单条审核
								</a>
							</li>
							<li>
								<a href="#" id="btn_shtg"
									onclick="saveShzt('通过');return false;"
									class="btn_shtg">
									批量审核通过
								</a>
							</li>
							<li>
								<a href="#" id="btn_shbtg"
									onclick="saveShzt('不通过');return false;"
									class="btn_shbtg">
									批量审核不通过
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
						<tr onclick="rowOnClick(this);" style="cursor:hand">
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
