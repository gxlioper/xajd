<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.gygl.cssz.CsszForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function disp(val) {
				if (val=='开' || val=='1') {
					document.getElementById("tmp1").style.display='';
					document.getElementById("tmp2").style.display='';
					document.getElementById("s_ks").style.display = '';
					document.getElementById("s_js").style.display = '';
				} else {
					document.getElementById("tmp1").style.display='none';
					document.getElementById("tmp2").style.display='none';
					//document.getElementById("hjkssj").value = '';
					//document.getElementById("hjjssj").value = '';
					document.getElementById("s_ks").style.display = 'none';
					document.getElementById("s_js").style.display = 'none';
				}
			}
			//提交数据
			function subm() {
				var array = document.getElementsByName("kg");
				var ks = document.getElementById("hjkssj").value;
				var js = document.getElementById("hjjssj").value;
				var flag = 0;
				for (var i=0;i<array.length;i++) {
					if (array[i].value=='1' && array[i].checked==true) {
						if (ks ==null || ks=='' ||js==null||js=='') {
							alert("开始时间，结束时间为必填！");
							return false;
						}
						ks = ks.replace("-","").replace("-","");
						ks = ks.replace(":","").replace(":","");
						ks = ks.replace(" ","");
						
						js = js.replace("-","").replace("-","");
						js = js.replace(":","").replace(":","");
						js = js.replace(" ","");

				if (parseFloat(js) <= parseFloat(ks) ) {
					alert("开始时间必须晚于结束时间！");
					return false;
				}
					}
				}
				
						
				refreshForm('rcsw_nthy_xfhjsjkz.do?type=save');
			}
		</script>
	</head>
	<body onload="disp('${rs.kg }')">
		<html:form action="/rcsw_nthy_xfhjgl" method="post">

			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					1.申请开关打开后，才能对时间进行设置。<br/>
					2.当前时间位于申请开始时间至申请结束时间段时，学生才可以进行学费缓交申请！
				</p>
				<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
			</div>

				<div class="open_win01">
					<table align="center" class="formlist">
						<tbody>
						<tr>
								<th width="50%">
									<span class="red">*</span>申请开关
								</th>
								<td  width="50%">
									<html:radio name="rs" property="kg" value="1" onclick="disp('开')"/>开
									<html:radio name="rs" property="kg" value="0" onclick="disp('关')"/>关
								</td>
							</tr>
							<tr id="tmp1">
								<th>
									<span class="red" id="s_ks">*</span>申请开始时间
								</th>
								<td>
									<input type="text" id="kssj" name="hjkssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" value="${rs.hjkssj  }"/>
								</td>
							</tr>
							<tr id="tmp2">
								<th>
									<span class="red" id="s_js">*</span>申请开始时间
								</th>
								<td>
									<input type="text" id="jssj" name="hjjssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" value="${rs.hjjssj }"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="subm()">
											保 存
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alert($('message').value);
			</script>
		</logic:present>
	</body>
</html>
