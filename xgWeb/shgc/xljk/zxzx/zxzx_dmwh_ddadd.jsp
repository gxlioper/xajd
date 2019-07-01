<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/xljk_zxzx_dwr.js'></script>
		<script type="text/javascript" src="js/lrh_new_js.js"></script>
		<script type='text/javascript'>
		function check_dmh(){
			var dmh=document.all['dd_dm'].value;
			if(""!=dmh){
				xljk_zxzx_dwr.xljk_dmwh_chcekdmh(dmh,chcek_result);
			}
		}
		
		function chcek_result(data){
			if("dmh_ok"==data){
				return true;
			}else if("dmh_exits"==data){
				alert("代码号已经存在！");
				document.all['dd_dm'].value="";
				return false;
			}
		}
		
		function dd_zj(){
			var dmh=document.all['dd_dm'].value;
			var dd2=document.all['dd2'].value;
			if(""==dd2){
				alert("请填写好所有信息！");
				return false;
			}else{
				document.all["add_flag"].value="yes";
				underDealWith();
				refreshForm("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=dd_add");
			}
		}
	</script>
	</head>

	<body>

		<html:form action="/xljk_zxzx_dmwh">
			<input type="hidden" id="add_flag" name="add_flag" value="no" />



			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>地点维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="dd_zj()"  id="buttonSave">
										增加
									</button>
									<button onclick="Close();return false;" >
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

					<tbody>
						<tr>
							<th>
								代码编号
							</th>
							<td>
								<html:text property="dd_dm" styleId="dd_dm" onblur="check_dmh()" maxlength="20" />
							</td>
						</tr>
						<tr>
							<th>
								咨询地点
							</th>
							<td>
								<html:text property="dd2" styleId="dd2" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="insert_success">
					<script>
							alert("操作成功");
							window.close();
							window.dialogArguments.document.getElementById("search_go").click();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	</html>