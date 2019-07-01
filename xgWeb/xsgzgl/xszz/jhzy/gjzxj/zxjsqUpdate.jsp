<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 

			var gjzxjsqzt = jQuery("#gjzxjsqzt").val();
			
			if(gjzxjsqzt != ""){
				alertError(gjzxjsqzt);
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}	
		}
		
		//保存国家助学金
		function saveGjzxjSq(){
			var xh = jQuery("#xh").val();
			var sqly = jQuery("#sqly").val();
			
			if(xh == ""){
				alertError("学号不能为空，请您确认^_^||");
				return false;
			}
			
			if(sqly == ""){
				alertError("申请理由不能为空，请您确认^_^||");
				return false;
			}
		
			var url = "jhzy_gjzxj.do?method=saveGjzxjSq";
			
			//参数
		 	var parameter = {
		 		"str_xh":escape(xh),
		 		"str_sqly":escape(sqly)
			};
	
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery.ajaxSetup({async:false});
			
			jQuery.post(url,
				parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
					closeWindown();		
				}
			);
	
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="gjzxjsqzt" name="gjzxjsqzt" value="${gjzxjsqzt }"/>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<!-- 学生基本信息 begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th align="right" width="25%">
								<font color="red">*</font>学号
							</th>
							<td align="left" width="25%">
								<input type="hidden" id="xh" value="${rs.xh }"/>
								${rs.xh }
							</td>
							<th align="right" width="25%">
								姓名
							</th>
							<td align="left" width="25%">
								${rs.xm }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								性别
							</th>
							<td align="left" width="">
								${rs.xb }
							</td>
							<th align="right" width="">
								学制
							</th>
							<td align="left" width="">
								${rs.xz }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								年级
							</th>
							<td align="left" width="">
								${rs.nj }
							</td>
							<th align="right" width="">
								<bean:message key="lable.xb" />
							</th>
							<td align="left" width="">
								${rs.xymc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								专业
							</th>
							<td align="left" width="">
								${rs.zymc }
							</td>
							<th align="right" width="">
								班级
							</th>
							<td align="left" width="">
								${rs.bjmc}
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								民族
							</th>
							<td align="left" width="">
								${rs.mzmc }
							</td>
							<th align="right" width="">
								政治面貌
							</th>
							<td align="left" width="">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								身份证号
							</th>
							<td align="left" width="">
								${rs.sfzh }
							</td>
							<th align="right" width="">
								出生年月
							</th>
							<td align="left" width="">
								${rs.csrq }
							</td>
						</tr>
					</tbody>
					<!-- 学生基本信息end -->
					
					<!-- 国家助学金 begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="25%">
								困难生认定级别
							</th>
							<td align="left" width="" colspan="3">
								${rs.knstjdc }
							</td>
						</tr>
						<tr>
							<th align="right" width="25%">
								<font color="red">*</font>申请理由
								<br/><font color="red">(限制字数200)</font>
							</th>
							<td align="left" width="" colspan="3">
								<textarea rows="5" id="sqly" cols="" 
									onblur="chLeng(this,200);"
									style="word-break:break-all;width:99%" >${rs.sqly }</textarea>
							</td>
						</tr>
					</tbody>
					<!-- 国家助学金 end-->			
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" name="保存" onclick="saveGjzxjSq()" id="buttonSave">
										保 存
									</button>
									<button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>