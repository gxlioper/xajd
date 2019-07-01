<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>

	<body>
		<html:form action="/zjjsRcswJxqk" method="post">
		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" id="buttonSave"
									onclick="if(checkSearchTj('rdsj','bysj')){saveUpdate('zjjsRcswJxqk.do?method=jxqkAdd&doType=save',
									'bmmc-xm-xb-csrq');}">
									保存
								</button>
								<button type="button" id="buttonSave"
									onclick="window.close();return false;">
									关闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<thead>
					<tr>
						<th colspan="4">
							<span>学生信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							<font color="red">*</font>系部
						</th>
						<td width="34%">
							<html:text property="save_bmmc" maxlength="25" styleId="bmmc"></html:text>
						</td>
						<th width="16%">
							<font color="red">*</font>姓名
						</th>
						<td width="34%">
							<html:text property="save_xm" maxlength="10" styleId="xm"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>性别
						</th>
						<td>
							<html:select property="save_xb" styleId="xb">
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						</td>
						<th>
							<font color="red">*</font>出生日期
						</th>
						<td>
							<html:text property="save_csrq" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="csrq"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							最终学历/学位
						</th>
						<td>
							<html:text property="save_xl" maxlength="15"></html:text>
						</td>
						<th>
							毕业院校
						</th>
						<td>
							<html:text property="save_byyx" maxlength="15"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							所学专业
						</th>
						<td>
							<html:text property="save_zy" maxlength="15"></html:text>
						</td>
						<th>
							专业技术职务
						</th>
						<td>
							<html:text property="save_zyjszw" maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							现从事专业及年限
						</th>
						<td>
							<html:text property="save_xzy" maxlength="15"></html:text>
						</td>
						<th>
							申请进修专业
						</th>
						<td>
							<html:text property="save_sqjxzy" maxlength="15"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							进修院校
						</th>
						<td>
							<html:text property="save_jxyx" maxlength="15"></html:text>
						</td>
						<th>
							入读时间
						</th>
						<td>
							<html:text property="save_rdsj" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="rdsj"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							毕业时间
						</th>
						<td>
							<html:text property="save_bysj" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="bysj"
							></html:text>
						</td>
						<th>
							学费金额
						</th>
						<td>
							<html:text property="save_xfje" maxlength="10" 
									   onkeyup="checkInputNum(this)"
									   onblur="checkInputNum(this)"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							获奖情况
						</th>
						<td colspan="3" style="work-break:break-all;">
							<html:text property="save_hjqk" maxlength="40" style="width:80%"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							编制情况
						</th>
						<td>
							<html:text property="save_bzqk" maxlength="10"></html:text>
						</td>
						<th>
							是否入住公寓（时间）
						</th>
						<td>
							<html:text property="save_rzgysj" readonly="true"
									   onclick="showCalendar(this.id,'y-mm-dd')" 
									   styleId="rzgysj"
							></html:text>
						</td>
					</tr>
					<tr>
						<th>
							备注<font color="red"><限200字></font>
						</th>
						<td colspan="3" style="work-break:break-all;">
							<html:textarea property="save_bz" style="width:80%" rows="5"
										 onblur="checkLen(this,200)"	
							></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(){
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				})
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
