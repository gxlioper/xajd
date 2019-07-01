<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">		
		//保存字段设置
		function showRuleUpdate(rule,color){
			var url = "/xgxt/sjyJcsjcsh.do?method=ruleUpdate";
				url+="&rule="+rule;
				url+="&color="+color;
				
			showTips('处理数据中，请等待......');
			
			refreshForm(url);
		}
		
		//执行规则
		function doRule(){
			if (confirm("将要根据所制定的规则，更新各临时表，请确认操作\n注：未制定规则的字段将不会被更新")) {
				saveUpdate('/xgxt/sjyJcsjcsh.do?method=ruleManage&doType=tb','');
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>数据源 - 规则制定</a>
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
				若字段规则是<font color="green">绿色</font>，则表示该字段值都符合规则，
				<font color="red">红色</font>则表示存在不合法记录，需要进行规则添加。
				完成规则制定后，请点击“执行”按钮，执行制定好的规则
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<html:form action="/sjyJcsjsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" value="${czxm }"/>
			<!-- 隐藏域 end-->
			
			<div class="tab">		
				<!-- 页面基本情况 -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<td colspan="4"></td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate name="ruleList" id="trRule">
							<tr style="">	
								<logic:iterate name="trRule" property="tdList" id="tdRule">
								<td style="width: 25%" bgcolor="${tdRule.color }">
									<a href="#" onclick="showRuleUpdate('${tdRule.dm }','${tdRule.color }')" >${tdRule.mc }</a>
								</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									
									<button type="button" onclick="doRule()" id="buttonSave">
										执 行
									</button>
										
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- 提示信息 end-->
		</html:form>
	</body>
</html>