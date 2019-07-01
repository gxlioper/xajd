<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//页面初始化
		function onShow(){
			//评奖学年
			var pjxn = jQuery("#hidden_xn").val();
			if(pjxn != ""){
				jQuery("#pjxn").val(pjxn);
			}

			//评奖学期
			var pjxq = jQuery("#hidden_xq").val();
			if(pjxq != ""){
				jQuery("#pjxq").val(pjxq);
			}

			//项目类型
			var xmlx = jQuery("#xmlx_check").val();
			if(xmlx != ""){
				jQuery("input[type=radio][name=xmlx][value="+xmlx+"]").attr("checked",true);
			}
		}
		
		//获得其他信息
		function setOtherXsxx(xh){
			
		}

		//验证保存历史评奖
		function checkSaveLspj(){

			var flag = true;
			var xh = jQuery("#input_xh").val();
			var pjxn = jQuery("#pjxn").val();
			var pjxq = jQuery("#pjxq").val();
			var xmmc = jQuery("#xmmc").val();
			
			if(xh == ""){
				alertError("请您选择学生");
				flag = false;
			}else if(pjxn == ""){
				alertError("评奖学年不能为空，请您确认");
				flag = false;
			}else if(xmmc == ""){
				alertError("项目名称不能为空，请您确认");
				flag = false;
			}

			if(pjxq == ""){
				jQuery("#hidden_xq").val("no");
			}
			
			if(flag){
				confirmInfo("请您确认是否执行保存操作？",saveLspj);
			}
		}

		//保存历史评奖
		function saveLspj(tag){
				
			if(tag=="ok"){
				// 得到JSON对象
		        var parameter ={};
		      	//指定获取的控件类型，进行循环
				jQuery("input,textarea").each(function(){
					//获取表单控件name
					var name=jQuery(this).attr("name");
					//构建json对象
					parameter[name]=escape(jQuery(this).val());
				});
				
				var url = "general_wdpj_jgcx_ajax.do?method=savePjlsxx";

				jQuery.ajaxSetup({async:false});
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);

				jQuery.ajaxSetup({async:true});
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:380px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- 增加 begin -->
						<logic:equal name="doType" value="add">
							<tr>
								<th width="20%">
									<font color="red">*</font>学号
								</th>
								<td width="30%">
									<input type="text" name="str_xh" 
										readonly="readonly" id="input_xh" 
										style="width:100px" value="${rs.xh }"/>
									<button type="button" class="btn_01" onclick="showChooseDiv()">选择</button>
								</td>
								<th width="20%">
									姓名		
								</th>
								<td width="">
									<span id="span_xm">${rs.xm }</span>
								</td>
							</tr>
						</logic:equal>
						<!-- 增加 end -->
						
						<!-- 修改or查看 begin -->
						<logic:notEqual name="doType" value="add">
							<tr>
								<th width="20%">
									学号
								</th>
								<td width="30%">
									<input type="hidden" name="str_xh" id="input_xh" value="${rs.xh }"/>
									${rs.xh }
								</td>
								<th width="20%">
									姓名		
								</th>
								<td width="">
									<span id="span_xm">${rs.xm }</span>
								</td>
							</tr>
						</logic:notEqual>
						<!-- 修改or查看 end -->
						<tr>
							<th width="">
								年级
							</th>
							<td width="">
								<span id="span_nj">${rs.nj }</span>
							</td>
							<th width="">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td width="">
								<span id="span_xymc">${rs.xymc }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								专业
							</th>
							<td width="">
								<span id="span_zymc">${rs.zymc }</span>
							</td>
							<th width="">
								班级
							</th>
							<td width="">
								<span id="span_bjmc">${rs.bjmc }</span>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>项目申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="">
								<font color="red">*</font>评奖学年
							</th>
							<td width="">
								<!-- 增加 begin -->
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="pjxn" styleId="pjxn" 
										onchange="$('hidden_xn').value=this.value">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</logic:equal>
								<!-- 增加 end -->
						
								<!-- 修改or查看 begin -->
								<logic:notEqual name="doType" value="add">
									<html:select name="rs" property="pjxn" styleId="pjxn" disabled="true"
										onchange="$('hidden_xn').value=this.value">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</logic:notEqual>
								<!-- 修改or查看 end -->
						
								<input type="hidden" name="str_xn" id="hidden_xn" value="${rs.xn }"/>
							</td>
							<th width="">
								评奖学期
							</th>
							<td width="">
								<!-- 增加 begin -->
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="pjxq" styleId="pjxq" 
										onchange="$('hidden_xq').value=this.value">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</logic:equal>
								<!-- 增加 end -->
						
								<!-- 修改or查看 begin -->
								<logic:notEqual name="doType" value="add">
									<html:select name="rs" property="pjxq" styleId="pjxq" disabled="true"
										onchange="$('hidden_xq').value=this.value">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</logic:notEqual>
								<!-- 修改or查看 end -->
								
								<input type="hidden" name="str_xq" id="hidden_xq" value="${rs.xq }"/>
							</td>
						</tr>	
						<tr>
							<th width="">
								<font color="red">*</font>项目名称	
							</th>
							<td width="">
								<!-- 增加 begin -->
								<logic:equal name="doType" value="add">
									<input type="text" name="str_xmmc" id="xmmc" value="${rs.xmmc }" maxlength="20"/>
								</logic:equal>
								<!-- 增加 end -->
						
								<!-- 修改or查看 begin -->
								<logic:notEqual name="doType" value="add">
									<input type="text" name="str_xmmc" id="xmmc" value="${rs.xmmc }" readonly="readonly"/>
								</logic:notEqual>
								<!-- 修改or查看 end -->
							</td>
							<th width="">
								项目类型
							</th>
							<td width="">
								<!-- 增加 begin -->
								<logic:equal name="doType" value="add">
									<input type="radio" name="xmlx" id="xmlx_01"
										value="01" onclick="setCheckedValue(this)" 
										checked="checked"/>奖学金
									<input type="radio" name="xmlx" id="xmlx_02"
										value="02" onclick="setCheckedValue(this)" 
										/>荣誉称号
									</logic:equal>
								<!-- 增加 end -->
						
								<!-- 修改or查看 begin -->
								<logic:notEqual name="doType" value="add">
									<input type="radio" name="xmlx" id="xmlx_01"
										value="01" onclick="setCheckedValue(this)" 
										disabled="disabled"/>奖学金
									<input type="radio" name="xmlx" id="xmlx_02"
										value="02" onclick="setCheckedValue(this)" 
										disabled="disabled"/>荣誉称号
								</logic:notEqual>
								<!-- 修改or查看 end -->
								<input type="hidden" name="str_xmlx" id="xmlx_check" value="${rs.xmlx }"/>
							</td>
						</tr>
						<tr>
							<th width="">
								金额
							</th>
							<td width="">
								<input type="text" name="str_xmje" 
									onkeyup="checkInputNum(this)"
									onblur="checkInputNum(this)"
									maxlength="5"
									id="xmje" value="${rs.xmje }"/>
							</td>
							<th width="">
								获得时间
							</th>
							<td width="">
								<input type="text" id="hdsj" readonly="readonly"
									name="str_hdsj" value="${rs.hdsj }"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar(this.id,'yyyyMMdd');"/>
							</td>
						</tr>	
						<tr>
							<th width="">
								备注
								<br />
								<font color="blue">(限500字)</font>
							</th>
							<td width="" colspan="3">
								<textarea rows="5" name="str_bz" cols="" 
									onblur="chLeng(this,500)"
									id="bz" style="width:99%">${rs.bz }</textarea>
							</td>
						</tr>
					</tbody>
			    </table>
		    </div>
		    
		    <div>
		    	<table width="100%" border="0" class="formlist">	
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<!-- 修改or增加 begin -->
									<logic:notEqual name="doType" value="view">
										<button type="button" name="保存" onclick="checkSaveLspj();">保 存</button>
									</logic:notEqual>
									<!-- 修改or增加 end -->
									<button type="button" name="关闭" onclick="Close();return false;">关 闭</button>
								</div>
							</td>
						</tr>
				    </tfoot>
			    </table>
		    </div>
		    <!-- 学生选择 -->
			<%@ include file="/comm/other/choiceXh.jsp"%>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>