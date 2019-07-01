<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jg.bjid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jg.splc}&shid=${jg.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}

		var url = "cxdd_pysh.do?method=Dgsh&type=save";
		ajaxSubFormWithFun("CxddShForm",url,function(data){
			 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
	}
	</script>
	<style type="text/css">
	 .center{text-align:center}
	</style>
</head>
<body>
	<html:form action="/cxdd_pysh" method="post" styleId="CxddShForm">
		
		<html:hidden  property="bjid" styleId="bjid"/>
		<html:hidden  property="bjdm" styleId="bjdm"/>		
		<html:hidden name="jg" property="splc" styleId="splc"/>
			
		<html:hidden name="jg" property="shid" styleId="shid"/>
	     <%-- 
		<html:hidden  property="cjbz" styleId="cjbz"/>
	        --%>
		<html:hidden name="jg"  property="xn" styleId="xn"/>
  		<html:hidden name="jg"  property="xq" styleId="xq"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
		   <table width="100%" border="0" class="formlist">			
			       <thead>
						<tr>
							<th colspan="4">
								<span><font color="blue">${xn}${xqmc} ${bjmc}</font> 操行等第信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr width="100%">
							<td width="100%" colspan="5">
							   <table width="100%">
							   		<tr width="100%">
							   			<th style='text-align:center'>学号</th>
							   			<th style='text-align:center'>姓名</th>
							   			<th style='text-align:center'>班级</th>
							   			<th style='text-align:center'>评价</th>
							   			<th style='text-align:center'>评语</th>
							   		</tr>
							   		<logic:iterate id="i" name="rslist">
							   		<tr width="100%">
							   			<td class='center' width='15%'>${i.xh}</td>
							   			<td class='center' width='15%'>${i.xm}</td>
							   			<td class='center' width='15%'>${i.bjmc}</td>
							   			<td class='center' width='15%'>${i.cxdjmc}</td>
							   			<td class='center' width='40%'>${i.py}</td>
							   		</tr>
							   		</logic:iterate>
							   </table>
							</td>
						</tr>
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审批信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审核信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th >
						审核结果
					</th>
					<td id="shjgSpan" colspan="3">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cxddxsb&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
		  </table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
