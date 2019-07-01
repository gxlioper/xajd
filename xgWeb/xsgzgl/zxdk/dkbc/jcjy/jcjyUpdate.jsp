<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/dkbc/jcjy/js/jcjy.js"></script>
		<script type="text/javascript" >
			jQuery(function(){
				jQuery("#dclb").val('${dclb}');
				jQuery("#clsfqq").val('${clsfqq}');
				jQuery("#sfwxzfsfz").val('${sfwxzfsfz}');
				jQuery("#sfzzzg").val('${sfzzzg}');
				jQuery("#lzsfzc").val('${lzsfzc}');
				jQuery("#dksfwqch").val('${dksfwqch}');
			})
		</script>
	</head>
	<body>
		<html:form action="/jcjy_bcdc" method="post" styleId="jcjyModel">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="juid" styleId="juid"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>代偿补偿</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
				    		<th>代偿类别</th>
				    		<td>
								<html:select property="dclb" styleId="dclb" style="width:150px">
									<option value="">--请选择--</option>
									<option value="学费代偿">学费代偿</option>
									<option value="贷款代偿">贷款代偿</option>
								</html:select>
							</td>
				    		<th>材料是否齐全</th>
							<td>
				    			<html:select property="clsfqq" styleId="clsfqq" style="width:150px">
				    				<option value="">--请选择--</option>
				    				<option value="是">是</option>
				    				<option value="否">否</option>
				    			</html:select>
				    		</td>
					    </tr>
					    <tr>
				    		<th>毕业时间</th>
				    		<td>
								<html:text property="bysj" styleId="bysj" onfocus="return showCalendar(this.id,'yyyy-MM');" maxlength="7" style="width:150px"></html:text>
							</td>
							
							<th>家人联系方式</th>
							<td>
								 <html:text property="jrlxfs"  styleId="jrlxfs"maxlength="12" style="width:150px"></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>就业单位名称</th>
							<td>
								 <html:text property="jydwmc"  styleId="jydwmc"maxlength="12" style="width:150px"></html:text>
							</td>
							<th>就业单位详细地址</th>
							<td>
								 <html:text property="jydwdz"  styleId="jydwdz"maxlength="12" style="width:150px"></html:text>
							</td>			 
					    </tr>
					    <tr>
					    	<th>是否为县政府<br/>所在地</th>
				    		<td>
				    			<html:select property="sfwxzfsfz" styleId="sfwxzfsfz" style="width:150px">
				    				<option value="">--请选择--</option>
				    				<option value="是">是</option>
				    				<option value="否">否</option>
				    			</html:select>
				    		</td>
							<th>行业类别 </th>
							<td>
							   	<html:select property="hylb" styleId="hylb" style="width:150px">
									<html:option value="">请选择</html:option>
									<html:options collection="hylbList" property="hylbdm" labelProperty="hylbmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
					    	<th>就业单位人事<br />部门联系电话</th>
				    		<td>
								 <html:text property="jydwlxdh" styleId="jydwlxdh" maxlength="12" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>已签订服务年限 </th>
							<td>
								 <html:text property="qdfwnx" styleId="qdfwnx" maxlength="2" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    </tr>
					    
					    <tr>
					    	<th>应缴纳学费<br/>金额（元）</th>
				    		<td>
								 <html:text property="yjnxf" styleId="yjnxf" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>实际缴纳学费<br/>金额（元）</th>
							<td>
								 <html:text property="sjjnxf" styleId="sjjnxf" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>贷款本金金额（元）</th>
				    		<td>
								 <html:text property="dkje" styleId="dkje" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>经办银行名称</th>
							<td>
								 <html:text property="yh" styleId="yh" style="width:150px" ></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>贷款合同号</th>
							<td>
								<html:textarea property='hth' style="width:98%" styleId="hth" rows='3' onblur="checkLen(this,500);" style="width:150px"/>
							</td>
							<th>贷款起止时间</th>
							<td>
								<html:textarea property='dkkssj' style="width:98%" styleId="dkkssj" rows='3' onblur="checkLen(this,500);" style="width:150px"/>
							</td>
					    </tr>
					    <tr>
					    	<th>申请补偿金额（元）</th>
				    		<td>
								 <html:text property="sqbcje" styleId="sqbcje" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>批准补偿代偿<br/>金额（元）</th>
					    	<td>
								 <html:text property="pzbcdcje" styleId="pzbcdcje" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    </tr>
					    <tr>
					    	<th>第一次代偿金额</th>
					    	<td>
								 <html:text property="dicdc" styleId="dicdc" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>第一次代偿时间</th>
				    		<td>
								<html:text property="dicdcsj" styleId="dicdcsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
					    	<th>第二次代偿金额</th>
					    	<td>
								 <html:text property="decdc" styleId="decdc" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>第二次代偿时间</th>
				    		<td>
								<html:text property="decdcsj" styleId="decdcsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
					    	<th>第三次代偿金额</th>
					    	<td>
								 <html:text property="dscdc" styleId="dscdc" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>第三次代偿时间</th>
				    		<td>
								<html:text property="dscdcsj" styleId="dscdcsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>是否在职在岗</th>
							<td>
				    			<html:select property="sfzzzg" styleId="sfzzzg" style="width:150px">
				    				<option value="">--请选择--</option>
				    				<option value="是">是</option>
				    				<option value="否">否</option>
				    			</html:select>
				    		</td>
				    		<th>离职离岗是否为正常<br/>调动、提拔、工作<br/>需要换岗</th>
							<td>
				    			<html:select property="lzsfzc" styleId="lzsfzc" style="width:150px">
				    				<option value="">--请选择--</option>
				    				<option value="是">是</option>
				    				<option value="否">否</option>
				    			</html:select>
				    		</td>
					    </tr>
					    <tr>
					    	<th>申请利息回补金额</th>
					    	<td>
								 <html:text property="ylzd1" styleId="ylzd1" maxlength="6" style="width:150px" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>发放利息回补时间</th>
				    		<td>
								<html:text property="ylzd2" styleId="ylzd2" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="7" style="width:150px"></html:text>
							</td>
						</tr>
					    <tr>
					    	<th>贷款是否已完全偿还</th>
							<td>
				    			<html:select property="dksfwqch" styleId="dksfwqch" style="width:150px">
				    				<option value="">--请选择--</option>
				    				<option value="是">是</option>
				    				<option value="否">否</option>
				    			</html:select>
				    		</td>
					    </tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveJcjy('update');">
										保存
									</button>
									<button type="button" type="button" onclick="iFClose();">
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