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
	
		}
		
		//获得其他信息
		function setOtherXsxx(xh){
			
		}

		//验证保存
		function checkSaveSxjy(){

			var flag = true;
			var xh = jQuery("#input_xh").val();
			
			if(xh == ""){
				alertError("请您选择学生");
				flag = false;
			}
			
			if(flag){
				confirmInfo("请您确认是否执行保存操作？",saveSxjy);
			}
		}

		//执行保存
		function saveSxjy(tag){
				
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
				
				var url = "general_jygl_sxjy_ajax.do?method=saveSxjy";

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
		
		<html:form action="/general_jygl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
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
									<button type="button" class="btn_01" onclick="showChooseDiv();">选择</button>
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
						
						<!-- 修改 or查看 begin -->
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
						<!-- 修改 or查看 end -->
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
								<span>实习就业信息</span>
							</th>
						</tr>
					</thead>
					<tbody>	
						<tr>
							<th width="">
								就业单位
							</th>
							<td width="" colspan="3">
								<input type="text" name="str_jydw" maxlength="30" 
									id="jydw" value="${rs.jydw }"/>
							</td>
						</tr>
						<tr>
							<th width="">
								实习情况<br />
								<font color="blue">(限100字)</font>
							</th>
							<td width="" colspan="3">
								<textarea rows="5" name="str_sxqk" cols="" onblur="chLeng(this,100)"
									id="bz" style="width:99%">${rs.sxqk }</textarea>
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
									<!-- 修改 or增加 begin -->
									<logic:notEqual name="doType" value="view">
										<button type="button" name="保存" onclick="checkSaveSxjy();">保 存</button>
									</logic:notEqual>
									<!-- 修改 or增加 end -->
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