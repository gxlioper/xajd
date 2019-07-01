<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/sjwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//getCjsx();
				setSfkxg();
				//为button注册事件
				jQuery("#but_save").click(function(){
					saveForm('xgCjff');
				});
				jQuery("#but_close").click(btn_close);
				jQuery("#yrbm").change(function(){
					getGwmc();
				});
				jQuery("#xn").change(function(){
					getGwmc();
				});

				//初始化用人部门
				initYrbm();
			});

			//初始化联动数据
			function initYrbm(){
				jQuery("#xn").change();
			}
		</script>
	</head>
	<body>
		<html:form action="/qgzx_jfcjgl_cjff.do" method="post" styleId="demoForm">
			<input type="hidden" name="cjbz" id="cjbz" value="${qgzxCsszRs.cjbz}" />
			<input type="hidden" name="oldje" id="oldje" value="${model.je}" />
			<input type="hidden" name="sjbs" id="sjly" value="${model.sjbs}" />
			<input type="hidden" name="oldyrbm" id="oldyrbm" value="${model.yrbm}"/>
			<input type="hidden" name="oldgwdm" id="oldgwdm" value="${model.gwdm}"/>
			<div class="tab" style="overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
								<html:hidden value="${jbxx.xm}" name="model" property="xm" styleId="xm"/>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>修改酬金发放信息</span>
								<html:hidden name="model" property="wbh" styleId="wbh" />
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								学年<logic:equal value="xq" name="cs" property="qgzq">学期
								</logic:equal>
							</th>
							<td width="34%">
								<html:select name="model" property="xn" styleId="xn" disabled="true" style="width:100px">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
								<logic:equal name="cs" property="qgzq" value="xq">
									<html:select  property="xq" styleId="xq" disabled="true" >
										<html:options collection="xqList" labelProperty="xqmc" property="xqdm" />
								    </html:select>
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xn">
									<input type="hidden" name="xq" id="xq" value =""/>
								</logic:equal>
							</td>
							<th width="16%">
								用人部门
							</th>
							<td width="34%" >
								<logic:empty name="rs" property="disQg">
									<html:select name="model" property="yrbm" styleId="yrbm" disabled="true" style="width:150px">
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
									<logic:equal value="true" name="rs" property="dis">
										<input type="hidden" value="${rs.yrbm}" id="yrbm" name="yrbm"/>
									</logic:equal>
								</logic:empty>
								<logic:notEmpty name="rs" property="disQg">
									<html:select name="model" property="yrbm" styleId="yrbm" disabled="true" style="width:150px">
										<html:options collection="bmList" name="model" property="bmdm" labelProperty="bmmc" />
									</html:select>
									<input type="hidden" value="${rs.yrbm}" id="yrbm" name="yrbm"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							
							<th width="16%">
								岗位名称
							</th>
							<td width="34%">
								<html:select name="model" property="gwdm" styleId="gwdm" disabled="true" style="width:150px;" onchange="getCjsx();">
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<th width="16%">
								发放时间
							</th>
							<td width="34%" colspan="3">
								<html:text  name="model" property="ffsj" disabled="true" styleId="ffsj" onclick="return showCalendar('ffsj','yyyy-MM');" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>工时
							</th>
							<td width="34%">
								<html:text name="model" property="gs" styleId="gs" maxlength="20" styleClass="text_nor" onkeyup="checkInputData(this);" />
							</td>
							<th width="16%">
								<font color="red">*</font>金额
							</th>
							<td width="34%">
								<html:text name="model" property="je" styleId="je" maxlength="20" styleClass="text_nor" onkeyup="checkInputData(this);"/>
							</td>
						</tr>
						<tr id="cjsxTr">
							<th width="16%">
								岗位酬金上限
							</th>
							<td colspan="3">
								<label id="cjsx"></label>
							</td>
						</tr>
						<tr>
							<th width="16%">
								备注
								<br/>
								<font color="red">限1000字</font>
							</th>
							<td colspan="3">
								<html:textarea name="model" property="bz" styleId="bz" cols="50" rows="3" styleClass="text_nor" style="height:100px;width:98%;"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<div style="height: 50px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" id="but_save">
										保 存
									</button>
									<button type="button" type="button" id="but_close" >
										关 闭
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

