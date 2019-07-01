<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveDksq(url){
				
				if (jQuery.trim(jQuery("#hkzh").val()) == "" || jQuery.trim(jQuery("#hkje").val()) == "" 
						|| jQuery("#hkbj").val() == ""){
					showAlert("请将必填项填写完整。");
					return false;
				}

				//提前还款理由不得超过250字符
				if(jQuery("#hkly").val().length > 250){
					showAlertDivLayer("提前还款理由最大字长度为"+250+",现已经超过，请确认！");
					return false;
				}
				
				//备注不得超过250字符
				if(jQuery("#bz").val().length > 250){
					showAlertDivLayer("备注最大字长度为"+250+",现已经超过，请确认！");
					return false;
				}
				
				ajaxSubFormWithFun("hkjgForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/zxdkHkjg" method="post" styleId="hkjgForm">
			<html:hidden property="id" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<logic:present name="khkList">
						<thead>
							<tr>
								<th colspan="4">
									<span>贷款信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
									<logic:equal value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">学年</th>
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">贷款总金额(元)</th>
										<th style="text-align: center;">累计放款金额(元)</th>
										<th style="text-align: center;">贷款期数</th>
										<th style="text-align: center;">贷款账号</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
											<td>${dkxx.dkzh }</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
							<logic:notEqual value="10511" name="xxdm">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">学年</th>
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">贷款总金额</th>
										<th style="text-align: center;">累计放款金额</th>
										<th style="text-align: center;">贷款期限</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEqual>
								</td>
							</tr>
						</tbody>
					</logic:present>
					<thead>
						<tr>
							<th colspan="4">
								<span>提前还款申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>还款账号</th>
							<td>
								<html:text property="hkzh" styleId="hkzh" maxlength="20"></html:text>
							</td>
							<th><font color="red">*</font>还款金额</th>
							<td>
								<html:text property="hkje" styleId="hkje" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>还款标记</th>
							<td>
								<html:select property="hkbj" styleId="hkbj">
									<html:option value=""></html:option>
									<html:option value="全部还清">全部还清</html:option>
									<html:option value="部分还清">部分还清</html:option>
								</html:select>
							</td>
							<th>还款状态</th>
							<td>
								<html:select property="hkzt" >
									<html:option value=""></html:option>
									<html:options collection="hkztList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>提前还款理由
								<br/><font color="red">(限输入250字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="hkly" styleId="hkly" 
											   onblur="checkLen(this,250);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>备注
								<br/><font color="red">(限输入250字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,250);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
							<logic:equal value="10511" name="xxdm">
						  <tr>
							<th>附件信息
								
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
							</td>
						  </tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdkHkjg.do?method=update');">
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