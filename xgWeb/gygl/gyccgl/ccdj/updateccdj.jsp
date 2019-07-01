<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="gygl/gyccgl/ccdj/js/ccdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				lddmChange();
				chChange();
				changesShcd();
			})
		</script>
	</head>
	<body>
		<html:form action="/gyccgl_ccdj" method="post" styleId="CcdjForm" onsubmit="return false;">
			<html:select property="shcdmark" styleId="shcd" style="display:none" >
				<html:options collection="shcdList" property="shcddm" labelProperty="je"/>
		    </html:select>
		    <html:hidden property="id" styleId="id"  name="rs"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>寝室信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">学年</th>
							<td width="30%">
								${rs.xn}
								<html:hidden property="xn" name="rs" styleId="xn" />				
							</td>
							<th width="20%">学期</th>
							<td width="30%">
								${rs.xqmc}
								<html:hidden property="xq" name="rs" styleId="xq" />				
							</td>
						</tr>
						<tr>
							<th>楼栋</th>
							<td>
								${rs.ldmc}
								<html:hidden property="lddm" name="rs" styleId="lddm" />		
							</td>
							<th>层号</th>
							<td>
								${rs.ch}
								<html:hidden property="ch" name="rs" styleId="ch" />
							</td>
						</tr>
						<tr>
							<th>寝室号</th>
							<td>
								${rs.qsh}
								<html:hidden property="qsh" name="rs" styleId="qsh" />
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>登记信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%">
									<thead>
										<th width="30%" style="text-align:left">物品名称</th>
										<th width="40%" style="text-align:left">损害程度</th>
										<th width="30%" style="text-align:left">估算费用(元)</th>
								    </thead>
								    <tbody id="iterate">
								    	<logic:iterate name="wpList" id="i">
								    		<tr>
								    			<td>${i.wpmc} <input name="wpdms" value="${i.wpdm}" type="hidden" /></td>
								    			<td>
								    				<html:select property="shcds" style="width:98%" value="${i.shcd}">
								    					<html:option value=""></html:option>
								    					<html:options collection="shcdList" property="shcddm" labelProperty="shcdmc"/>
								    				</html:select>
								    			</td>
								    			<td name="je" >${i.je}</td>
								    		</tr>
								    	</logic:iterate>
								    </tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>费用总计(元)</th>
							<td colspan="3">
								<html:text property="zje" maxlength="8" name="rs" style="width:65%"  styleId="zje" onkeyup="checkMoneyBykeyUpByone(this)"/>
							</td>
						</tr>
						<tr>
							<th>备注(限1000字)</th>
							<td colspan="3">
								<html:textarea  rows="5" property="bz" name="rs" styleId="bz" style="width:98%" onblur="checkLen(this,1000)" />
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保    存
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
		</html:form>
	</body>
	
</html>