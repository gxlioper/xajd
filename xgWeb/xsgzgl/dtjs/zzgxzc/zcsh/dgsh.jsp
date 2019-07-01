<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/qmlxdj/lxsh/js/lxsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="
							+ new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${shid}",function(){
					jQuery("#shjg").change(function(){
						var tempvalue = this.value;
						if(tempvalue == "2" || tempvalue == "3"){
							jQuery("#jsxbhtr").hide();
						}else{
							jQuery("#jsxbhtr").show();
						}
					})
					});
				
			});
			function saveSh(){
		      var shzt = jQuery("#shjg").val();
		      var ids = "xh"+"-"+"szdzb"+"-"+"sfsn"+"-"+"jsdzz"+"-"+"sqdw"+"-"+"dfjzrq"+"-"+"shjg"+"-"+"shyj";
	          if(checkNotNull(ids) == false){
		        showAlert("请将带<font color='red'>*</font>的项目填写完整");
		        return false;
	          }
		      
		      if(${isJsxbhShow}&&shzt == '1'){
		      	if(jQuery("#jsxbh").val()== ""){
		      		 showAlert("请填写介绍信编号！");
			         return false;
		      	}
		      }
		      var url = "dzzgxsh.do?method=Dgsh&type=save";
		      ajaxSubFormWithFun("ZcshForm",url,function(data){
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
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/dzzgxsh" method="post" styleId="ZcshForm">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="xh" styleId="xh"/>
		<html:hidden  property="splcid" styleId="splcid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>登记信息</span>&nbsp;
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>所在党支部</th>
							<td>
								<html:select property="szdzb" styleId="szdzb" style="width:90%">
									<html:options collection="dzbList" property="dzbdm" labelProperty="dzbmc"/>
								</html:select>							
							</td>
							<th><font color="red">*</font>是否省内</th>
							<td>
								 <html:select property="sfsn" styleId="sfsn" style="width:90%">
									<html:option value="省内">省内</html:option>
									<html:option value="省外">省外</html:option>
								 </html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>接收本人组织关系的党组织</th>
							<td colspan="3">
								<html:text property="jsdzz" styleId="jsdzz" style="width:90%" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>本人组织关系所去单位</th>
							<td colspan="3">
								<html:text property="sqdw" styleId="sqdw"  maxlength="50" style="width:90%"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>党费交至日期</th>
							<td colspan="3">
								<html:text property="dfjzrq" styleId="dfjzrq" onclick="return showCalendar('dfjzrq','y-mm-dd',true);" style="width:180px"  />
							</td>
							<%-- <th><font color="red">*</font>是否需要开具婚姻证明</th>
							<td >
								<html:radio property="sfkjhyzm" value="是" styleId="yes"/><label for="yes">是</label>
								<html:radio property="sfkjhyzm" value="否" styleId="no"/><label for="no">否</label>
							</td> --%>
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
					<tr>
						<th >
							审核结果
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
			</tr>
			<logic:equal value="true" name="isJsxbhShow">
				<tr id="jsxbhtr">
					        <th>
								<font color="red">*</font>介绍信编号
							</th>
							<td  colspan="3">
								<html:text styleId="jsxbh" property="jsxbh" />
							</td>
				</tr>
			</logic:equal>
			<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zzgxzc&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
				</table>
				
				</div>	
				<div style="height: 35px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存"  onclick="saveSh();return false;">
										保 存
								    </button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
	
</html>