<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
			<script language="javascript" src="xsgzgl/xlzx/yysqnew/js/addYyzxInfo.js"></script>
			<script type="text/javascript">
				jQuery(function(){
                    jQuery("#jzxx_tb").hide();
					if('${pbfs}' == '2'){
						jQuery(":input").not("#xh").not("button").not("#yyfs").not("#yyfs").not("#jzxx_tb input").not("#jzxx_tb textarea").click(function(){
							if(jQuery("#xh").val() == ""){
								showAlert("请先选择学生！");
								return false;
							}
						})
					}
				});
				function changeYyfs() {
					var yyfs = jQuery("#yyfs").val();
					if(yyfs == "jz"){
						jQuery("#jzxx_tb").show();
					}else{
                        jQuery("#jzxx_tb").hide();
					}
                }
			</script>
		<style type="text/css">
			#jzxx_tb input{
				width: 100px;
			}
		</style>
	</head>
	<body>
		<input type="hidden" name="path" id="path" value="${path}" />
		<input type="hidden" name="sjhm" id="sjhm" value="${xsInfo.sjhm}" />
		<input type="hidden" name="pbfs" id="pbfs" value="${pbfs}"/>
		<html:form action="/xlzx_zxyyclnew" method="post" styleId="form">
			<div style='width:100%; height:450px; overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="2">
							<span>预约方式信息</span>
						</th>
					</tr>
					</thead>
					<th width="16%">
						<span class="red">*</span>预约方式
					</th>
					<td >
						<html:select property="yyfs" styleId="yyfs" style="width:100px;" onchange="changeYyfs();">
							<html:option value="xc" >现场预约</html:option>
							<html:option value="dh">电话预约</html:option>
							<html:option value="jz">家长预约</html:option>
						</html:select>
					</td>
				</table>
				<table width="100%" border="0" class="formlist" id="jzxx_tb">
					<thead>
					<tr>
						<th colspan="4">
							<span>家长填写信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>家长姓名</span>
						</th>
						<td width="34">
							<input type="text" name="jzxm" id="jzxm" onblur="checkLen(this,10)"/>
						</td>
						<th width="16%">
							<span class="red">*</span><span>性别</span>
						</th>
						<td width="34">
							<label><input type="radio" name="jzxb" value="1" style="width: 15px;"/>男</label>
							<label><input type="radio" name="jzxb" value="2" style="width: 15px;"/>女</label>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>联系电话</span>
						</th>
						<td width="34">
							<input type="text" name="jzlxdh" id="jzlxdh" maxlength="12"  onblur="checkLxdh();" />
						</td>
						<th width="16%">
							<span class="red">*</span><span>与学生关系</span>
						</th>
						<td width="34">
							<input type="text" name="gx" id="gx" onblur="checkLen(this,10)"/>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>电子邮箱</span>
						</th>
						<td width="34">
							<input type="text" name="jzdzyx" id="jzdzyx"
								   maxlength="30"  onblur="checkEmail(this)" />
						</td>
						<th width="16%">
							<span>家庭健全</span>
						</th>
						<td width="34">
							<input type="text" name="jtjq" id="jtjq" onblur="checkLen(this,10)"/>
						</td>

					</tr>
					<tr>
						<th width="16%">
							<span>父亲职业</span>
						</th>
						<td width="34">
							<input type="text" name="fqzy" id="fqzy" onblur="checkLen(this,10)"/>
						</td>
						<th width="16%">
							<span>父学历</span>
						</th>
						<td width="34">
							<input type="text" name="fxl" id="fxl" onblur="checkLen(this,10)"/>
						</td>

					</tr>
					<tr>
						<th width="16%">
							<span>母亲职业</span>
						</th>
						<td width="34">
							<input type="text" name="mqzy" id="mqzy" onblur="checkLen(this,10)"/>
						</td>
						<th width="16%">
							<span>母学历</span>
						</th>
						<td width="34">
							<input type="text" name="mxl" id="mxl" onblur="checkLen(this,10)"/>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>家庭住址</span>
						</th>
						<td  colspan="3">
							<input type="text" name="jtdz" id="jtdz" style="width: 95%" onblur="checkLen(this,50)"/>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>学生是否知晓</span>
						</th>
						<td width="34">
							<label><input type="radio" name="xssfzx" value="1" style="width: 15px;"/>是</label>
							<label><input type="radio" name="xssfzx" value="0" style="width: 15px;"/>否</label>
						</td>
						<th width="16%">
							<span class="red">*</span><span>辅导员是否知晓</span>
						</th>
						<td width="34">
							<label><input type="radio" name="fdysfzx" value="1" style="width: 15px;"/>是</label>
							<label><input type="radio" name="fdysfzx" value="0" style="width: 15px;"/>否</label>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>来访目的</span>
							<br/><font color="red">(限500字)</font>
						</th>
						<td  colspan="3">
							<textarea rows="4" cols="" style="width: 98%"
									  id="lfmd" name="lfmd" onblur="checkLen(this,500)"></textarea>
						</td>
					</tr>
					</tbody>

				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudentAjaxAll.jsp" %>

					<thead>
						<tr >
							<th colspan="4">
								<span>咨询师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfo">
						<tr style="height:10px">
							<th width="16%">
								<logic:equal value="true" name="isZxsGly">
									<span class="red">*</span>
								</logic:equal>
								职工号
							</th>
							<td width="34%">
								<logic:equal value="false" name="isZxsGly">
									${zxsInfo.zgh}
									<input type="hidden" id="zgh" class="zgh" name="zgh" style="width:120px;" value="${zxsInfo.zgh}"/>
								</logic:equal>
								<logic:equal value="true" name="isZxsGly">
									<input type="text" id="zgh" class="zgh" name="zgh" style="width:120px;" value="${zxsInfo.zgh}"/>
									<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个咨询师',800,500,'xlzx_yysqnew.do?method=showZxs');">选择
									</button>
								</logic:equal>
							</td>
							
							<th  width="16%">
								姓名
							</th>
							<td  width="34%" class="xm">
								${zxsInfo.xm}
							</td>
						</tr>
						<tr style="height:10px">
							<th  width="16%">
								性别
							</th>
							<td  width="34%" class="xb">
								${zxsInfo.xb }
							</td>
							<th width="16%">
								年龄
							</th>
							<td  width="34%" class="nl">
								${zxsInfo.age}
							</td>
						</tr>
						<tr style="height:10px">
							<th width="16%">
								联系电话
							</th>
							<td  width="34%" class="lxdh">
								${zxsInfo.lxdh }
								
							</td>
							<th width="16%">
								所在部门
							</th>
							<td  width="34%" class="bmmc">
								${zxsInfo.bmmc }
								
							</td>
						</tr>						
					</tbody>			
							
					
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询安排信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="dealInfo">
					<tr style="height:10px" >
							<th>
								<span class="red">*</span>咨询安排日期
							</th>
							<td >
									<html:text property="zxrq" styleId="zxrq"   style="width:100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${today}'})"  onchange="delValidate();"/>
							</td>
							<th  width="16%">
								<logic:equal name="pbfs" value="2"><span class="red">*</span></logic:equal>咨询时段
							</th>
							<td  width="34%" >
								<logic:notEqual name="pbfs" value="2">
									<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'zxjssj\\')}'})" readonly="true"/>&nbsp;至&nbsp;
								    <html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'zxqssj\\')}'})" readonly="true"/>
								</logic:notEqual>
								<logic:equal name="pbfs" value="2">
									<select name="sjddm" id="sjddm" style="width:60%">
										<option></option>
									</select>
								</logic:equal>
							</td>
						</tr>
						<tr>

							<th>
								咨询联系电话
							</th>
							<td colspan="3">
								<html:text property="zxtell" styleId="zxtell"  value="${zxsInfo.lxdh}"  maxlength="11" onblur="checkInputData(this);"/>
							</td>

						</tr>
						<tr>
							<th>
								咨询地址
							</th>
							<td colspan="3">
								<html:text property="zxdz" styleId="zxdz" style="width:90%"  maxlength="50"  value="${zxsInfo.address }"  />
							</td>
						</tr>
						<tr style="height:10px" name="yyfkId">
							<th  width="16%">
								备注<br/>
								<font color="red"><B>(限500字)</B></font>
							</th>
							<td  width="34%" colspan="3">
								<html:textarea  property='bz' styleId="bz" value="" style="word-break:break-all;width:99%" onblur="chLengs(this,500);" rows='4' />
							</td>
						</tr>
					</tbody>
				</table>

			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="saveYysqInfo();return false;">
									保 存
								</button>
								<button onclick="Close();return false;">
									关 闭
								</button>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>

		</html:form>
	</body>
</html>

