<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script>
	function checkText(flag){
		//alert(flag);
		if(flag==1){	
			var dd_dm=document.all["dd_dm"].value;
			if ( dd_dm==""){
				alert ("请选择咨询地点");
				return false;
			}
			var sjd_dm=document.all["sjd_dm"].value;
			if ( sjd_dm==""){
				alert ("请选择时间段");
				return false;
			}
		}
		if(flag==1||flag==2){
			var rq=document.all["rq"].value;
			if ( rq==""){
				alert ("请选择日期");
				document.all["rq"].focus();
				return false;
			}
		}
		if(flag==3){
			var formrq=document.all["fromrq"].value;
			if ( formrq==""){
				alert ("请选择从某天开始时间");
				document.all["fromrq"].focus();
				return false;
			}
			var torq=document.all["torq"].value;
			if ( torq==""){
				alert ("请选择到某天结束时间");
				document.all["torq"].focus();
				return false;
			}
		}
		underDealWith();
		if(flag==1){
			refreshForm('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl&doType=Add_xx');//生成资源
		}
		if(flag==2){
			refreshForm('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl&doType=Add_mt'); //生成某日的资源
		}
		if(flag==3){
			refreshForm('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl&doType=Add_pl'); //批量生成
		}
	}
	</script>
</head>
<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 增加预约资源</a>
			</p>
		</div>
	<div id="tmpdiv"></div>

	<html:form action="/xljk_zxszy_add">
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>相关选项</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="checkText('1')">
										生成资源
									</button>
									<button onclick="checkText('2')" >
										生成某天资源
									</button>
									<button  onclick="checkText('3')">
										批量生成
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				<tbody>
				<tr>
					<th width="40%">
						地点
					</th>
					<td>
						<html:select property="dd_dm" style="width:100px" styleId="dd_dm"
							onchange="">
							<html:option value=""></html:option>
							<html:options collection="ddList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						时间段
					</th>
					<td>
						<html:select property="sjd_dm" style="width:100px"
							styleId="sjd_dm" onchange="">
							<html:option value=""></html:option>
							<html:options collection="sjdList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						日期
					</th>
					<td>
						<html:text style="cursor:hand; width:75px;" styleId="dateF"
							property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>

				<tr>
					<th>
						（批量生成条件）从某天
					</th>
					<td>
						<html:text style="cursor:hand; width:75px;" styleId="dateFr"
							property="fromrq"
							onclick="return showCalendar('dateFr','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>
						（批量生成条件）到某天
					</th>
					<td>
						<html:text style="cursor:hand; width:75px;" styleId="dateTo"
							property="torq"
							onclick="return showCalendar('dateTo','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>
						（批量生成条件）星期
					</th>
					<td>
						<html:select property="monthday">
							<html:option value=""></html:option>
							<html:option value="1">周日</html:option>
							<html:option value="2">周一</html:option>
							<html:option value="3">周二</html:option>
							<html:option value="4">周三</html:option>
							<html:option value="5">周四</html:option>
							<html:option value="6">周五</html:option>
							<html:option value="7">周六</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<td colspan="2">
							<span>相关说明</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							<font color="blue">一： 生成资源：</font>对于生成资源只关注：
							<font color="red">地点：</font><font color="red">时间段：</font>和
							<font color="red">日期：</font>,最后生成一条记录
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<font color="blue">二： 生成当天资源：</font>对于生成当天资源只关注：
							<font color="red">日期:</font>，
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最后生成
							<font color="red">地点的数量 * 时间段的数量</font>条记录
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<font color="blue">三： 批量生成：</font>对于批量生成只关注
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">(批量生成条件)从某天:</font>和
							<font color="red">(批量生成条件)到某天：</font>，其他条件不与考虑，最后生成
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">(到某天 - 从某天) * 地点的数量 * 时间段的数量</font>条资源记录
							.如果选择了星期，则生成从从某天到某天所选星期的资源
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<logic:notEmpty name="message">
			<logic:equal name="message" value="insert_success">
				<script>
							alert("保存成功!");
							Close();
							dialogArgumentsQueryChick();
						</script>
			</logic:equal>
			<logic:equal name="message" value="insert_fail">
				<script>alert("保存失败!")</script>
			</logic:equal>
			<logic:equal name="message" value="date error">
				<script>alert("批量日期有误!")</script>
			</logic:equal>
			<logic:equal name="message" value="date exits">
				<script>alert("该时间段已经存在!")</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
