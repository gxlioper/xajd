<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#yxzcwxx").hide();
				jQuery("#yxzrzcwxx").hide();
				var zsfxs = jQuery("#zsfxs").val();
				if("0" == zsfxs) {
					jQuery("#zsfM").hide();
					jQuery("#zsf").hide();
					jQuery("#sfcwcshs").attr("colspan","3");
				}
				setTstz();
			});

			//选择床位
			function selectCw(){
				var xh = jQuery("#xh").val();
				if(xh===''){
					showAlert('请先选择一个学生！');
				}else{
					showDialog('请选择一个床位',800,500,'ydsq.do?method=selectCwxx&goto=${path}&xh='+xh);
				}
			}
			function setSfcwcsh(cellValue,rowObject){
				if(cellValue!=null&&cellValue!=""){
					jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
					jQuery("input:radio[name='sfcwcsh']").attr("disabled","disabled");
				}else{
					jQuery("input:radio[name='sfcwcsh']:eq(0)").attr("checked","checked");
					jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");
				}
				return cellValue;
			}
			function showCwxx(cwxx){
				var gridSetting = {
						caption:"已选择床位信息",
						multiselect:false,
						rowNum:1,
						url:"ydsq.do?method=selectCwxx&type=query&cwxx="+cwxx,
						colList:[
						   {label:'床位信息id',name:'cwxx', index: 'cwxx',key:true,hidden:true},
						   {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
						   {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
						   {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
						   {label:'床位性别',name:'qsxb', index: 'qsxb'},
						   {label:'所属年级',name:'nj', index: 'nj'},
						   {label:'所属<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'学号',name:'xh', index: 'xh',formatter:setSfcwcsh},
						   {label:'姓名',name:'xm', index: 'xm'}
						],
						sortname: "sfrz",
					 	sortorder: "desc"
					}
				jQuery("#cwxxTable").initGrid(gridSetting);
				jQuery("#yxzcwxx").show();
				jQuery("#cwxx").val(cwxx);
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/ydjg">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
		<html:hidden property="zsfxs" styleId="zsfxs" value="${zsfxs}"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/gygl/comm/ssyd/selectStudentSsyd.jsp" %>
				<thead>
					<tr>
						<th colspan="4">
							<span>床位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							楼栋名称
						</th>
						<td align="left">
							${cwxxData.ldmc}
						</td>
						<th align="right">
							寝室号
						</th>
						<td align="left">
							${cwxxData.qsh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							床位号
						</th>
						<td align="left" id="td_cwh">
							${cwxxData.cwh}
						</td>
						<th align="right">
							寝室电话
						</th>
						<td align="left">
							${cwxxData.qsdh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							收费标准
						</th>
						<td align="left">
							${cwxxData.sfbz}
						</td>
						<th align="right">
							所属年级
						</th>
						<td align="left">
							${cwxxData.nj}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							所属<bean:message key="lable.xb" />
						</th>
						<td align="left">
							${cwxxData.xymc}
						</td>
						<th align="right">
							所属班级
						</th>
						<td align="left">
							${cwxxData.bjmc}
						</td>
					</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>宿舍异动申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>宿舍异动类型
						</th>
						<td align="left">
							<html:select property="ssydlx" styleId="ssydlx" disabled="false" >
									<html:option value="00">退宿</html:option>
									<html:option value="01">宿舍调整</html:option>
								<logic:equal name="xxdm" value="12686">
									<html:option value="02">实习留宿</html:option>
								</logic:equal>
									<html:option value="03">入住</html:option>
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>学年/学期
						</th>
						<td align="left">
							<logic:equal value="12303" name = "xxdm">
									${dqxn} ${dqxq}
								<html:hidden property="xn" styleId="xn" />	  
								<html:hidden property="xq" styleId="xq"  style="width:30px;"/>
							</logic:equal>
							<logic:notEqual value="12303" name = "xxdm">
								<html:select property="xn" styleId="xn" disabled="false" >
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								<html:select property="xq" styleId="xq" disabled="false" >
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
				<tbody id="rz">
					<tr >
						<th align="right" width="10%">
							<span class="red">*</span>入住原因
						</th>
						<td align="left">
							<html:select property="rzssyy" styleId="rzssyy" disabled="false" >
									<html:options collection="rzyyList" property="rzyydm"
										labelProperty="rzyymc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>入住时间
						</th>
						<td align="left">
							<logic:equal value="12303" name = "xxdm">
								<html:text property="rzsssj" styleId="rzsssj" value ="${currDate}" disabled = "flase" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:equal>
							<logic:notEqual value="12303" name = "xxdm">
								<html:text property="rzsssj" styleId="rzsssj"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>选择床位</th>
						<td align="left" width="10%" colspan="3">
							<button class="btn_01" type="button"  onclick="selectRzcw();return false;" <logic:equal name="userType" value="stu">disabled="disabled"</logic:equal>>选择床位</button>
						</td>
					</tr>
					<tr id="yxzrzcwxx">
						<th>
							已选择床位信息
						</th>
						<td colspan="3">
							<table id="rzcwxxTable"></table>
							<input type="hidden" name="rzcwxx" id="rzcwxx" />
						</td>
					</tr>
				</tbody>
				<tbody id="ts">
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>退宿原因
						</th>
						<td align="left">
							<html:select property="tstzyy" styleId="tstzyy" disabled="false" >
									<html:options collection="tstzyyList" property="tsyydm"
										labelProperty="tsyymc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>退宿时间
						</th>
						<td align="left">
							<logic:equal value="12303" name = "xxdm">
								<html:text property="tstzsj" styleId="tstzsj" value ="${currDate}" disabled = "flase" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:equal>
							<logic:notEqual value="12303" name = "xxdm">
								<html:text property="tstzsj" styleId="tstzsj"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:notEqual>
						</td>
					</tr>
				</tbody>
				<tbody id="tz">
					<tr >
						<th align="right" width="10%">
							<span class="red">*</span>宿舍调整原因
						</th>
						<td align="left">
							<html:select property="tzssyy" styleId="tzssyy" disabled="false" >
									<html:options collection="tzyyList" property="tzyydm"
										labelProperty="tzyymc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>宿舍调整时间
						</th>
						<td align="left">
							<logic:equal value="12303" name = "xxdm">
									<html:text property="tzsssj" styleId="tzsssj" value ="${currDate}" disabled = "flase" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
								</logic:equal>
								<logic:notEqual value="12303" name = "xxdm">
									<html:text property="tzsssj" styleId="tzsssj"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:notEqual>
							
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>选择床位</th>
						<td align="left" width="10%" colspan="3">
							<button class="btn_01" type="button"  onclick="selectCw();return false;">选择床位</button>
						</td>
					</tr>
					<tr id="yxzcwxx">
						<th>
							已选择床位信息
						</th>
						<td colspan="3">
							<table id="cwxxTable"></table>
							<input type="hidden" name="cwxx" id="cwxx" />
						</td>
					</tr>
				</tbody>
					<tr id="showSfcwcsh">
						<th >
							<font color="red">*</font>原床位是否初始化
						</th>
						<td id="sfcwcshs">
							<input type="radio" name="sfcwcsh" value="0">是
					    	<input type="radio" name="sfcwcsh" value="1" checked>否
						</td>
						
						<th align="right" width="10%" id="zsf">
							住宿费
						</th>
						<td align="left" id="zsfM">
							<html:select property="jflx" styleId="jflx" >
								<html:option value=""></html:option>
								<html:option value="01">补缴</html:option>
								<html:option value="02">退还</html:option>
							</html:select>
							<html:text size="15" property="zsfje" styleId="zsfje" maxlength="6" onkeyup="checkInputData(this);"></html:text> 元
						</td>
					</tr>
						<tr>
							<th align="right" style="width: 10%">
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="fjxx" styleId="fjxx"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//后缀
											accept : 'doc|docx|xls|xlsx|jpg|png|rar|zip|pdf|ppt',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'fjxx'
											});
									});
								</script>  
							</td>
						</tr>
					<tr>
						<logic:notEqual value="12303" name = "xxdm">
							<th align="right" width="10%">
								备注
							</th>
						</logic:notEqual>
						<logic:equal value="12303" name = "xxdm">
							<th align="right" width="10%">
								<span class="red">*</span>申请理由
							</th>
						</logic:equal>
						<td colspan="3">
							<html:textarea rows="4" property="bz" styleId="bz" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" id="tssave"  onclick="save('ydjg.do?method=add&type=save','xh-xn-xq-tstzyy-tstzsj');return false;" id="buttonSave">
									保 存
								</button>
								<button type="button" id="tzsave" onclick="save('ydjg.do?method=add&type=save','xh-xn-xq-tzssyy-tzsssj-cwxx');return false;" id="buttonSave">
									保 存
								</button>
								<button type="button" id="rzsave" onclick="save('ydjg.do?method=add&type=save','xh-xn-xq-rzssyy-rzsssj');return false;" id="buttonSave">
									保 存
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
</html>
