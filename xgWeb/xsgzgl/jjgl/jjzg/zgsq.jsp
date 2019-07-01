<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function saveZgsq(url){
			ajaxSubFormWithFun("zgsqForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					document.location.href=document.location;
				}});
			});
		}
	</script>
  </head>
  
  <body>
  		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/jjgl_jjzg" method="post" styleId="zgsqForm">
			<input type="hidden" value="${jjzgForm.sqid }" name="id"/>
			<html:hidden property="sqid" />
			<html:hidden property="xh" value="${userName }"/>
			<html:hidden property="shzt"/>
			<html:hidden property="splcid" value="${cssz.xszgsqsplcid }" />
		
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="16%">学号</th>
							<td width="34%">${jbxx.xh }</td>
							<th width="16%">姓名</th>
							<td width="34%">${jbxx.xm }</td>
					    </tr>
					    <tr>
					    	<th>性别</th>
					    	<td>${jbxx.xb }</td>
					    	<th>年级</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr>
					    	<th>学院</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>班级</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>家教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
					    	<th><font color="red">*</font>擅长科目</th>
					    	<td colspan="3">
					    		<html:select property="jjkma" >
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxkdm" labelProperty="jjxkmc"/>
					    		</html:select>
					    		<html:select property="jjkmb">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxkdm" labelProperty="jjxkmc"/>
					    		</html:select>
					    		<html:select property="jjkmc">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxkdm" labelProperty="jjxkmc"/>
					    		</html:select>
					    		（请按优先级选择）
					    	</td>
					    </tr>
						<tr>
					    	<th><font color="red">*</font>针对年级</th>
					    	<td>
					    		<html:select property="jjnjdm">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnjdm" labelProperty="jjnjmc"/>
					    		</html:select>
					    	</td>
					    	<th><font color="red">*</font>联系电话</th>
					    	<td>
					    		<html:text property="lxdh" maxlength="20"></html:text>
					    	</td>
					    </tr>
					    <tr>
					    	<th>备注</th>
					    	<td colspan="3">
					    		<html:textarea property="bz" style="width:85%;" rows="5"></html:textarea>
					    	</td>
					    </tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:empty name="jjzgForm" property="sqid">
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=save');">
											保    存
										</button>
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=saveAndSubmit');">
											提交申请
										</button>
									</logic:empty>
									<logic:notEmpty name="jjzgForm" property="sqid">
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=update');">
											保    存
										</button>
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=submit');">
											提交申请
										</button>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
  </body>
</html>
