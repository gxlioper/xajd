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
		function sjd_bc()
		{
			var h1=document.all["hour1"].value;
			var m1=document.all["minutes1"].value;
			var h2=document.all["hour2"].value;
			var m2=document.all["minutes2"].value;
			if(h1!=""&&m1!=""&&h2!=""&&m2!="")
			{
				if(h1>h2)
				{
					alert("起始时间有误！");
					return false;
				}
				else if(h1==h2)
				{
					if(m1>m2)
					{
						alert("起始时间有误！");
						return false;
					}
					else
					{
						document.all["modi_flag"].value="yes";
						var xn_id=document.all["ZY_XN_ID"].value;
						refreshForm("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=sjd_modi&xn_id="+xn_id);
					}
				}
				else
				{
					document.all["modi_flag"].value="yes";
					var xn_id=document.all["ZY_XN_ID"].value;
					refreshForm("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=sjd_modi&xn_id="+xn_id);
				}
			}
			else
			{
				alert("检查填写信息！请填写好所有信息");
				return false;
			}
		}
	</script>
	</head>

	<body>

		<html:form action="/xljk_zxzx_dmwh">
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<html:text property="ZY_XN_ID" styleId="ZY_XN_ID"
				style="display:none" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>时间段维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="sjd_bc()" id="zj_but">
										保存
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
								<html:text property="sjd_dm" styleId="sjd_dm" onkeypress=""
									onkeyup="" readonly="true" />
							</td>
						</tr>




						<tr>
							<th>
								开始时间
							</th>
							<td>
								<html:select property="hour1" styleId="hour1" onchange="">
									<html:option value="Hours">Hours</html:option>
									<html:option value="00">00</html:option>
									<html:option value="01">01</html:option>
									<html:option value="02">02</html:option>
									<html:option value="03">03</html:option>
									<html:option value="04">04</html:option>
									<html:option value="05">05</html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
									<html:option value="23">23</html:option>
								</html:select>
								:
								<html:select property="minutes1" styleId="minutes1" onchange="">
									<html:option value="Mins">Min</html:option>
									<html:option value="00"></html:option>
									<html:option value="01"></html:option>
									<html:option value="02"></html:option>
									<html:option value="03"></html:option>
									<html:option value="04"></html:option>
									<html:option value="05"></html:option>
									<html:option value="06"></html:option>
									<html:option value="07"></html:option>
									<html:option value="08"></html:option>
									<html:option value="09"></html:option>
									<html:option value="10"></html:option>
									<html:option value="11"></html:option>
									<html:option value="12"></html:option>
									<html:option value="13"></html:option>
									<html:option value="14"></html:option>
									<html:option value="15"></html:option>
									<html:option value="16"></html:option>
									<html:option value="17"></html:option>
									<html:option value="18"></html:option>
									<html:option value="19"></html:option>
									<html:option value="20"></html:option>
									<html:option value="21"></html:option>
									<html:option value="22"></html:option>
									<html:option value="23"></html:option>
									<html:option value="24"></html:option>
									<html:option value="25"></html:option>
									<html:option value="26"></html:option>
									<html:option value="27"></html:option>
									<html:option value="28"></html:option>
									<html:option value="29"></html:option>
									<html:option value="30"></html:option>
									<html:option value="31"></html:option>
									<html:option value="32"></html:option>
									<html:option value="33"></html:option>
									<html:option value="34"></html:option>
									<html:option value="35"></html:option>
									<html:option value="36"></html:option>
									<html:option value="37"></html:option>
									<html:option value="38"></html:option>
									<html:option value="39"></html:option>
									<html:option value="40"></html:option>
									<html:option value="41"></html:option>
									<html:option value="42"></html:option>
									<html:option value="43"></html:option>
									<html:option value="44"></html:option>
									<html:option value="45"></html:option>
									<html:option value="46"></html:option>
									<html:option value="47"></html:option>
									<html:option value="48"></html:option>
									<html:option value="49"></html:option>
									<html:option value="50"></html:option>
									<html:option value="51"></html:option>
									<html:option value="52"></html:option>
									<html:option value="53"></html:option>
									<html:option value="54"></html:option>
									<html:option value="55"></html:option>
									<html:option value="56"></html:option>
									<html:option value="57"></html:option>
									<html:option value="58"></html:option>
									<html:option value="59"></html:option>
								</html:select>
							</td>
						</tr>

						<tr>
							<th>
								结束时间
							</th>
							<td>
								<html:select property="hour2" styleId="hour2" onchange="">
									<html:option value="Hours">Hours</html:option>
									<html:option value="00">00</html:option>
									<html:option value="01">01</html:option>
									<html:option value="02">02</html:option>
									<html:option value="03">03</html:option>
									<html:option value="04">04</html:option>
									<html:option value="05">05</html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
									<html:option value="23">23</html:option>
								</html:select>
								:
								<html:select property="minutes2" styleId="minutes2" onchange="">
									<html:option value="Mins">Min</html:option>
									<html:option value="00"></html:option>
									<html:option value="01"></html:option>
									<html:option value="02"></html:option>
									<html:option value="03"></html:option>
									<html:option value="04"></html:option>
									<html:option value="05"></html:option>
									<html:option value="06"></html:option>
									<html:option value="07"></html:option>
									<html:option value="08"></html:option>
									<html:option value="09"></html:option>
									<html:option value="10"></html:option>
									<html:option value="11"></html:option>
									<html:option value="12"></html:option>
									<html:option value="13"></html:option>
									<html:option value="14"></html:option>
									<html:option value="15"></html:option>
									<html:option value="16"></html:option>
									<html:option value="17"></html:option>
									<html:option value="18"></html:option>
									<html:option value="19"></html:option>
									<html:option value="20"></html:option>
									<html:option value="21"></html:option>
									<html:option value="22"></html:option>
									<html:option value="23"></html:option>
									<html:option value="24"></html:option>
									<html:option value="25"></html:option>
									<html:option value="26"></html:option>
									<html:option value="27"></html:option>
									<html:option value="28"></html:option>
									<html:option value="29"></html:option>
									<html:option value="30"></html:option>
									<html:option value="31"></html:option>
									<html:option value="32"></html:option>
									<html:option value="33"></html:option>
									<html:option value="34"></html:option>
									<html:option value="35"></html:option>
									<html:option value="36"></html:option>
									<html:option value="37"></html:option>
									<html:option value="38"></html:option>
									<html:option value="39"></html:option>
									<html:option value="40"></html:option>
									<html:option value="41"></html:option>
									<html:option value="42"></html:option>
									<html:option value="43"></html:option>
									<html:option value="44"></html:option>
									<html:option value="45"></html:option>
									<html:option value="46"></html:option>
									<html:option value="47"></html:option>
									<html:option value="48"></html:option>
									<html:option value="49"></html:option>
									<html:option value="50"></html:option>
									<html:option value="51"></html:option>
									<html:option value="52"></html:option>
									<html:option value="53"></html:option>
									<html:option value="54"></html:option>
									<html:option value="55"></html:option>
									<html:option value="56"></html:option>
									<html:option value="57"></html:option>
									<html:option value="58"></html:option>
									<html:option value="59"></html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update_success">
					<script>
							alert("操作成功!");
							window.dialogArguments.document.getElementById("search_go").click();
							Close();
						</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	</html>