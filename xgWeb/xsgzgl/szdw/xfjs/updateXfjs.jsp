<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/szdw/xfjs/js/xfjs.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
</head>
<body >
	<html:form action="/szdw_xfjsgl" styleId="xfjsForm"  method="post">
	<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:380px;margin-bottom: 0px;">
	<input type="hidden" name="id" id="id" value='${id }'/>
	<input type="hidden" name="lrr" id="lrr" value='${lrr }'/>
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>学风考勤情况</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>学年
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					<html:select property="xn" styleId="xn" >
						<html:options collection="xnList" labelProperty="xn" property="xn"/>
					</html:select>
				</td>
				<th width="16%"><font color="red">*</font>学期</th>
				<td width="38%">
					<html:select property="xq" styleId="xq" >
						<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th width="16%">
					<font color="red">*</font>班级			
				</th>
				<td width="34%">
						<html:text property="bjmc" styleId="bjmc"
						style="width:150px;" readonly="true" styleClass="text_nor" ></html:text>
					<input type="hidden" name="bjdm" id="bjdm" value='${bjdm }'/>
					<button class="btn_01" type="button" onclick="showBj();return false;">选择</button>
				</td>
					<th width="16%">
					录入人			
				</th>
				<td width="34%">
					${lrr}
				</td>
			</tr>
			<tr>
				<th width="16%">
					学院		
				</th>
				<td width="34%">
				<html:text property="xymc" styleId="xymc" readonly="true"
						style="width:100px" styleClass="text_nor"></html:text>
				</td>
				<th width="16%">
					校区		
				</th>
				<td width="34%" >
				<html:text property="yxmc" styleId="yxmc" readonly="true"
						style="width:100px" styleClass="text_nor"></html:text>
				</td>
			</tr>
			<tr>
			</td>
				<th width="16%">
					学生类型	
				</th>
				<td width="34%" >
					<html:text property="pyccmc" styleId="pyccmc" readonly="true" 
						style="width:100px" styleClass="text_nor"></html:text>
				</td>
				<th width="16%">
					辅导员	
				</th>
				<td width="34%">
				<html:text property="fdy" styleId="fdy" readonly="true" 
						style="width:100px" styleClass="text_nor"></html:text>
				</td>
			</tr>
			<tr>
			<th width="16%">
					<font color="red">*</font>应到学生人数		
				</th>
				<td width="34%" >
					<html:text property="ydxsrs" styleId="ydxsrs" onkeyup="checkNumBer(this)"
						style="width:87px" styleClass="text_nor" ></html:text>
				</td>
				<th width="16%">
					<font color="red">*</font>实际出勤人数			
				</th>
				<td width="34%">
				<html:text property="sjcqrs" styleId="sjcqrs" onkeyup="checkNumBer(this)"
						style="width:87px" styleClass="text_nor"></html:text>
				</td>
			
			</tr>
			<tr>
				<th width="16%">
					<font color="red">*</font>检查时间			
				</th>
				<td width="34%">
			
							<html:text property="jcsj" styleId="jcsj"  style="width:50%" onfocus="return showCalendar('jcsj','yyyy-MM-dd');" />
						
					</td>
				<th width="16%">
					<font color="red">*</font>检查节次				
				</th>
					<td width="34%"><html:text property="jcjc" styleId="jcjc" 
						styleClass="text_nor"></html:text>
					</td>
			</tr>
			<tr>
				<th width="16%" rowspan="4">
					备注
					<br><font color="red">（限500字）</font><br/>
				</th>
				<td width="34%" colspan="3" rowspan="4">
					<html:textarea property="bz" onblur="chLengs(this,500);" styleId="bz" rows="4" style="width:90%" >${bz}</html:textarea>
				</td>
			</tr>
			</tbody>
			</table>
				</div>
			<table class="formlist" width="97%">
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="updateSave()">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
	
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		 showAlert("操作成功",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		</script>
	</logic:present>
</body>
</html>
