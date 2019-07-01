<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
		//申诉初始化
		function sscsh(){
			var sfkss=jQuery("#sfkss").val();
			var sfksqjc=jQuery("#sfksqjc").val();
			if(sfkss!=""){
				if(sfkss.indexOf("no") > -1){
					jQuery("#bkss").attr("checked","checked");
				}
				if(sfkss.indexOf("xs") > -1){
					jQuery("#xskss").attr("checked","checked");
				}
				if(sfkss.indexOf("js") > -1){
					jQuery("#jskss").attr("checked","checked");
				}
			}
			if(sfksqjc!=""){
				if(sfksqjc.indexOf("no") > -1){
					jQuery("#bksqjc").attr("checked","checked");
				}
				if(sfksqjc.indexOf("xs") > -1){
					jQuery("#xsksqjc").attr("checked","checked");
				}
				if(sfksqjc.indexOf("js") > -1){
					jQuery("#jsksqjc").attr("checked","checked");
				}
			}
		}

		</script>
	</head>
	<body onload="sscsh();">
		<html:form action="/wjcfJcsz_cflbdm" method="post">
			<div class="tab_cur" >
					<p class="location">
						<em>您的当前位置:</em><a>${title } - 查看</a>
					</p>
			</div>
			<%--<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>系统提示：</span></h3>
		       <p><br/></p>
		   	</div>
			--%>
			<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:245px;margin-bottom: 0px;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>处分代码信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="rs">
						<tr>
							<th style="width:40%">
								处分类别
							</th>
							<td style="width:60%">
								<bean:write name="rs" property="cflbmc"/>
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								审核流
							</th>
							<td  style="width:60%">
							<logic:equal value="yes" name="wxsh">
								<logic:present name="shlcList">
									无审核流
								</logic:present>
								<logic:notPresent name="shlcList">
									当前为配置处分审批流程
								</logic:notPresent>
							</logic:equal>
							<logic:notEqual value="yes" name="wxsh">
								<logic:present name="shlcList">
									<logic:iterate id="shlc" name="shlcList" indexId="index">
										<input disabled="none" type="radio" name="spl" <logic:equal name="rs" property="spl"  value="${shlc.splc}"> checked="checked" </logic:equal> value="<bean:write name="shlc" property="splc"/>"/><bean:write name="shlc" property="lcxx"/>
										<br/>
									</logic:iterate>
								</logic:present>
								<logic:notPresent name="shlcList">
									当前为配置处分审批流程
								</logic:notPresent>
							</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								是否可申诉
							</th>
							<td  style="width:60%">
								<input disabled="none" type="checkbox" id="bkss" value="no"  onclick="sfkyss('sfkss',this);"/>不可申诉
								<input disabled="none" type="checkbox" id="xskss" value="xs" onclick="sfkyss('sfkss',this);"/>学生可申诉
								<input disabled="none" type="checkbox" id="jskss" value="js" onclick="sfkyss('sfkss',this);"/>教师可申诉
								<html:hidden property="sfkss" name="rs" styleId="sfkss" onclick="sfkyss('sfkss',this);"/>
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								申诉受理工作日
							</th>
							<td  style="width:60%">
								<bean:write name="rs" property="ssslgzr"/>(天)
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								是否可申请解除
							</th>
							<td  style="width:60%">
								<input disabled="none" type="checkbox" id="bksqjc" value="no" onclick="sfkyss('sfksqjc',this);"/>不可申诉
								<input disabled="none" type="checkbox" id="xsksqjc" value="xs" onclick="sfkyss('sfksqjc',this);"/>学生可申诉
								<input disabled="none" type="checkbox" id="jsksqjc" value="js" onclick="sfkyss('sfksqjc',this);"/>教师可申诉
								<html:hidden property="sfksqjc" name="rs" styleId="sfksqjc" onclick="sfkyss('sfkss',this);"/>
							</td>
						</tr>
						</logic:present>
					</tbody>
				</table>	
			</div>
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
									</font>
								</div>
								<div class="btn">
									<!-- <button type="button"  class="button2" id="btn_bc"  onclick="modi()">
										保 存
									</button> -->
									<button type="button"  class="button2"  onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>
