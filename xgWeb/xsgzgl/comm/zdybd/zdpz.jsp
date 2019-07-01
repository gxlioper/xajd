<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdpz.js"></script>
		
		<style>.bold{font-weight: bold!important;}</style>

		
	</head>
	<body >
		<html:form action="/zdybd_zddy" method="post" styleId="form1">
		<input type="hidden" name="zdzds" id="zdzds" value="${zdzds}">
		<input type="hidden" name="btzds" id="btzds" value="${btzds}">
		<input type="hidden" name="lb" id="lb" value="">
		<input type="hidden" name="gndm" id="gndm" value="">
		<input type="hidden" name="dataJson" id="dataJson" value="">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						只读：选中“只读”，则该字段不允许学生进行修改，只能查看<br/>
						必填：选中“必填”，则该字段在学生修改信息保存时会验证必填，否则无法保存
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>

			<div class="compTab">
				<div class="comp_title">
			      <ul>
			         <li id="li_xs" class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'xs');"><span>学生</span></a></li>
			         <li id="li_js"><a href="javascript:void(0);" onclick="selectTab(this,'js');"><span>教师</span></a></li>
			      </ul>
			    </div>
			</div>
			<table width="100%" border="0" class="formlist" id="content">
			</table>
			<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>
