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
		
		<script type="text/javascript" src="xsgzgl/xszz/xgzdsz/js/xgzdsz.js"></script>
		
		<style>.bold{font-weight: bold!important;}</style>
		<script type="text/javascript">
		jQuery(function(){
			initData();
		})
		</script>
		
	</head>
	<body >
		<html:form action="/jtqkdc_xgzdsz" method="post" styleId="XgzdForm">
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
						必填：选中"必填"，则该字段在修改家庭情况调查信息保存时会验证必填，否则无法保存！
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<table width="100%" border="0" class="formlist" id="content">
				<thead id="jtcyxx_thead">
					<th colspan="6">
							<span>家庭成员信息</span>
							<label style="margin-left:98px;">
								<input type="checkbox" value="yes" name="jtcyxx" onclick = "changePlbt('jtcyxx','yes')" id ="check2"/>
								<label for="check2">全部必填</label>
								<input type="checkbox" value="no" name="jtcyxx" onclick = "changePlbt('jtcyxx','no')" id ="check1"  />
								<label for="check1">全部非必填 </label>
							</label>
					</th>
				</thead>
				<tbody id="jtcyxx">
					
				</tbody>
				<thead id="jtqkxx_thead">
					<th colspan="6">
							<span>家庭情况</span>
							<label style="margin-left:122px;">
								<input type="checkbox" value="yes" name="jtqkxx" onclick = "changePlbt('jtqkxx','yes')" id="check4" />
								<label for="check4">全部必填</label>
								<input type="checkbox" value="no" name="jtqkxx" onclick = "changePlbt('jtqkxx','no')" id="check3" />
								<label for="check3">全部非必填</label>
							</label>  
					</th>
				</thead>
				<tbody id="jtqkxx">
					
				</tbody>
				
				<thead id="fjxx_thead">
					<th colspan="6">
							<span>附件信息</span>
					</th>
				</thead>
				<tbody id="fjxx">
					
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="saveData();return false;">
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
 