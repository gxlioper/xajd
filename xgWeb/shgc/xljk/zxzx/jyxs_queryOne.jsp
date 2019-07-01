<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/xljk_zxzx_dwr.js'></script>
		<script type="text/javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script type='text/javascript'>
		function check_dmh(){
			var dmh=document.getElementById("dmh").value;
			if("" != dmh){
				xljk_zxzx_dwr.xljk_dmwh_chcekdmh(dmh,chcek_result);
			}
		}
		
		function chcek_result(data){
			if("dmh_ok"==data){
				return true;
			}else if("dmh_exits"==data){
				alert("代码号已经存在！");
				document.getElementById("dmh").value = "";
				document.getElementById("dmh").focus();
				return false;
			}
		}
		
		function dd_zj(str){
			var dmh=document.getElementById("dmh").value;
			var ddmc=document.getElementById("dmmc").value;
			if("" == ddmc){
				alert("请填写好教育形式名称！");
				return false;
			}else{
				underDealWith();
				refreshForm("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=" + str);
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/xljk_zxzx_dmwh">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>教育形式维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEmpty name="rs" property="doType">
										<logic:equal value="jyxs_view" name="rs" property="doType">
											<button onclick="dd_zj('jyxs_modi')" id="buttonSave">
												保存
											</button>
										</logic:equal>
										<logic:notEqual value="jyxs_view" name="rs" property="doType">
											<button onclick="dd_zj('jyxs_add')" 
												id="buttonSave">
												增加
											</button>
										</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="doType">
										<button onclick="dd_zj('jyxs_add')" 
											id="buttonSave">
											增加
										</button>
									</logic:empty>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button onclick="window.close();return false;" id="buttonClose">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>代码编号
							</th>
							<td>
								<logic:notEmpty name="rs" property="doType">
									<logic:equal value="jyxs_view" name="rs" property="doType">
										<input type="text"
											value="<bean:write name="rs" property="dmh"/>" id="dmh"
											readonly="readonly" name="dmh"
											onblur="check_dmh()"
											onkeyup="value=value.replace(/[^\d]/g,'') "
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
									</logic:equal>
									<logic:notEqual value="jyxs_view" name="rs" property="doType">
										<input type="text"
											value="<bean:write name="rs" property="dmh"/>" id="dmh"
											name="dmh" onblur="check_dmh()"
											onkeyup="value=value.replace(/[^\d]/g,'') "
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
									</logic:notEqual>
								</logic:notEmpty>
								<logic:empty name="rs" property="doType">
									<input type="text"
										value="<bean:write name="rs" property="dmh"/>" id="dmh"
										oname="dmh" onblur="check_dmh()"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</logic:empty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>教育形式
							</th>
							<td>
								<html:text property="dmmc" styleId="dmmc" name="rs" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="ok">
				<logic:equal name="ok" value="ok">
					<script>
							alert("操作成功");
							window.close();
							window.dialogArguments.document.getElementById("search_go").click();
				</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>
					alert("操作失败!");
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
