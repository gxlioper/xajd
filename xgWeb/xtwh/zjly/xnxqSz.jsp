<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/station/station.js"></script>
		<script type="text/javascript" src="js/station/city_name.js"></script>
		<script type="text/javascript">
			function bc() {
				var url = "xtwh_bsdtxnxqwh.do?method=bcXnxq";
				ajaxSubFormWithFun("xnxqwhForm", url, function(data) {
					showAlertDivLayer(data["message"]);
				});
			}
	</script>
	</head>
	<body >
		<html:form action="/xtwh_bsdtxnxqwh" method="post" styleId="xnxqwhForm" >
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>学年学期设置</span></th>
					</tr>
				</thead>
					<tbody>
						<tr>
							<th>
								当前年度
							</th>
							<td>
								<html:select property="dqnd" styleId="dqnd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								当前学年
							</th>
							<td>
								<html:select property="dqxn" styleId="dqxn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								当前学期
							</th>
							<td>
								<html:select property="dqxq" styleId="dqxq">
									<html:options collection="xqList" property="xqmc"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								评奖年度
							</th>
							<td>
								<html:select property="pjnd" styleId="pjnd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								评奖学年
							</th>
							<td>
								<html:select property="pjxn" styleId="pjxn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								评奖学期
							</th>
							<td>
								<html:select property="pjxq" styleId="pjxq">
									<html:options collection="xqList" property="xqmc"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
					<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">	
			          <logic:equal name="writeAble" value="yes">		            
						<button type="button" class="button2" onclick="bc();return false;" id="btn_save">
							保 存
						</button>
						</logic:equal>
						<button type="button" class="button2" onclick="window.close();return false;" style="width:80px;display:none" 
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
