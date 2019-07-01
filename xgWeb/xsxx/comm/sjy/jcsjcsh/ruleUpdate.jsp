<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">		
		//返回规则选择
		function showRuleManage(){
			var url = "/xgxt/sjyJcsjcsh.do?method=ruleManage";			
			refreshForm(url);
		}
		
		//保存规则制定
		function saveRule(){
		
			var num =  document.getElementsByName("zdh").length;
			var flag = true;
		
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("zdh")[i];
				if(obj.value ==  ""){
					flag = false;
				}
			}
			
			var url = "sjyJcsjcsh.do?method=ruleUpdate&doType=save";
			
			if(flag){
				if (confirm("将要执行保存操作，请确认所制定的各项规则！")) {
					if($("rule").value == "xsxxb_xzqkm"){
						setZdhValue();
					}
					saveUpdate(url,"");
				}
			}else{
				if (confirm("尚有部分规则未制定，确认先执行保存操作吗？")) {
					if($("rule").value == "xsxxb_xzqkm"){
						setZdhValue();
					}
					saveUpdate(url,"");
				}
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
				如果数据已经是符合规则的数据，则无需处理，如果是不符合规则的数据，需要进行相应的转换，才可以
				使其进入到学工正式库中。
				</br>
				<font color="blue">注：如不确认，请不要进行贸然修改，并联系相关人员进行数据处理。</font>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<html:form action="/sjyJcsjcsh">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" value="${czxm }"/>
			<input type="hidden" name="rule" value="${rule }"/>
			<input type="hidden" name="zd" value="${zd }"/>
			<input type="hidden" name="lyb" value="${lyb }"/>
			<!-- 隐藏域 end-->
			
			<div class="tab">	
				<!-- 规则制定 -->	
						
					<!-- 部门代码表_部门类别 -->
					<logic:equal name="rule" value="bmdmb_bmlb">
						<%@ include file="rule/bmdmb_bmlb.jsp"%>
					</logic:equal>
					<!-- 专业代码表_上级部门 -->
					<logic:equal name="rule" value="zydmb_bmdm">
						<%@ include file="rule/zydmb_bmdm.jsp"%>
					</logic:equal>
					<!-- 班级代码表_所属专业 -->
					<logic:equal name="rule" value="bjdmb_zydm">
						<%@ include file="rule/bjdmb_zydm.jsp"%>
					</logic:equal>
					<!-- 学生信息表_所属班级 -->
					<logic:equal name="rule" value="xsxxb_bjdm">
						<%@ include file="rule/xsxxb_bjdm.jsp"%>
					</logic:equal>
					<!-- 学生信息表_性别 -->
					<logic:equal name="rule" value="xsxxb_xb">
						<%@ include file="rule/xsxxb_xb.jsp"%>
					</logic:equal>
					<!-- 学生信息表_学籍状态 -->
					<logic:equal name="rule" value="xsxxb_xjztm">
						<%@ include file="rule/xsxxb_xjztm.jsp"%>
					</logic:equal>
					<!-- 学生信息表_行政区块 -->
					<logic:equal name="rule" value="xsxxb_xzqkm">
						<%@ include file="rule/xsxxb_xzqkm.jsp"%>
					</logic:equal>
					
				<!-- 规则制定 end-->
					
				<!-- 操作按钮 -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									
									<logic:notEqual name="color" value="green">
										<button type="button" onclick="saveRule()" id="buttonSave">
											保 存
										</button>
									</logic:notEqual>
										
									<button type="button" onclick="showRuleManage()" id="buttonClose">
										返 回
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<!-- 操作按钮 end-->
			</div>
			<!-- 提示信息 -->
			<logic:present name="message">
				<script defer="defer">
					if($("message") && $("message").value != ""){
					
						alert($("message").value);
						
						$("message").value = "";
						$("doType").value = "";
					}
				</script>
			</logic:present>
			<!-- 提示信息 end-->
		</html:form>
	</body>
</html>