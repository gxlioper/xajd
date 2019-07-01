<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhZjXg.js"></script>
	</head>
	<body>
		<html:form action="/xpj_xmwh"  styleId="form1"  method="post">
			<div class="prompt" style="display:none;">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<font color="red">
						当前项目已有学生申请，部分项不允许修改
					</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<html:hidden property="xmdm" styleId="xmdm" />
			<html:hidden property="jdkzjb" styleId="jdkzjb" />
			<html:hidden property="rskzjb" styleId="rskzjb" />
			<html:hidden property="rsfpfs" styleId="rsfpfs" />
			<input type="hidden" name="rsfpfsOld" id="rsfpfsOld" value="${xmwhModel.rsfpfs}" />

			<input type="hidden" name="spzt" id="spzt" value="${spzt }" />
			<input type="hidden" name="xmxz" id="xmxz" value="${xmxz}" />
			<input type="hidden" name="shlcOld" id="shlcOld"  value="${xmwhModel.shlc}" />

			<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>评奖项目设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								<font color="red">*</font>项目名称
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" maxlength="20"
									styleClass="text_nor" />
							</td>
							<th>英文名称</th>
							<td>
								<html:text property="xmywmc" styleId="xmywmc" maxlength="100"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>项目类型</th>
							<td>
								<html:select property="lxdm" styleId="lxdm" style="width:155px">
								<html:options collection="xmlxList" property="dm"
									labelProperty="mc" />
								</html:select>
							</td>
							<th>项目性质</th>
							<td>
								<span id="xz"></span>
							</td>
						</tr>
						<tr>
						    <th>项目金额</th>
							<td>
								<html:text property="xmje" styleId="xmje" maxlength="5"
									styleClass="text_nor" />
							</td>
							<th>
								<font color="red">*</font>显示顺序
							</th>
							<td>
								<html:text property="xsxh" styleId="xsxh" maxlength="3" onkeyup="checkInputData(this);" />
							</td>
						</tr>
					</tbody>
				</table>

				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2" >
								<span>审核流程</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  width="20%">
								<font color="red">*</font>审核流程
							</th>
							<td  width="80%">
								<html:select property="shlc" styleId="shlc" >
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc"
									labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>人数控制级别
							</th>
							<td width="80%" id="rskzjbTd" >
							</td>				
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>人数分配方式</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<td colspan="2">
							<logic:notEqual value="10335" name="xxdm"> 
								<label>
									<input type="radio" name="rsfpfsView" value="xx">学校
								</label>
							</logic:notEqual>
								&nbsp;
								<label>
									<input type="radio" name="rsfpfsView" value="xy"><bean:message key="lable.xb" />
								</label>
								&nbsp;								<label>
									<input type="radio" name="rsfpfsView" value="njxy">年级+<bean:message key="lable.xb" />
								</label>
								&nbsp;	
								<logic:notEqual value="10335" name="xxdm"> 							
								<label>
									<input type="radio" name="rsfpfsView" value="njzy">年级+专业
								</label>
								&nbsp;								<label>
									<input type="radio" name="rsfpfsView" value="bj">班级
								</label>
								&nbsp;
								<label>
									<input type="radio" name="rsfpfsView" value="cpz">参评组
								</label>	
								</logic:notEqual>							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>学生培养类型
							</th>
							<td colspan="3">
								<html:select property="pycc" styleId="pycc">
									<html:option value="all">全部</html:option>>
									<html:options collection="pyccList" property="pyccdm"
												  labelProperty="pyccmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">项目说明<br/>
								<font color="red">(限制录入500字)</font></th>
							<td width="80%">
								<html:textarea property="xmsm" styleId="xmsm"  style="width:98%"  rows="5"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table border="0" class="formlist">
				
				<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
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
	</body>
</html>

