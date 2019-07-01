<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" href="xsgzgl/dtjs/dtxxglnew/color/dtxxglnew.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoChange();
				var zpcj=jQuery("#zpcj").val();
				if(zpcj==null||zpcj==''){
					var kqcjbfb=jQuery('#kqcjbfb').val()/100;
					var sjcjbfb=jQuery('#sjcjbfb').val()/100;
					var bjcjbfb=jQuery('#bjcjbfb').val()/100;
					var kscjbfb=jQuery('#kscjbfb').val()/100;
					var kqcj=jQuery('#kqcj').val()==''?0:jQuery('#kqcj').val();
					var sjcj=jQuery('#sjcj').val()==''?0:jQuery('#sjcj').val();
					var bjcj=jQuery('#bjcj').val()==''?0:jQuery('#bjcj').val();
					var kscj=jQuery('#kscj').val()==''?0:jQuery('#kscj').val();
					jQuery('#xszpcj').html(kqcj*kqcjbfb+sjcj*sjcjbfb+bjcj*bjcjbfb+kscj*kscjbfb);
				}
			});
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="dxbmgl_dxpxjg.do">
		<html:hidden property="xh" styleId="xh"/>
		 <!-- 提示信息 end-->
			<div class="prompt" id="div_help">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						<img src="xsgzgl/dtjs/dtxxglnew/color/sys.png" alt="深蓝色" style="height: 100%;"/> &nbsp;深颜色为已有党团阶段，
						<img src="xsgzgl/dtjs/dtxxglnew/color/qys.png" alt="浅蓝色" /> &nbsp;浅颜色为尚未申请的党团阶段。
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
		 <!-- 提示信息 end-->		
		<div style="tab;width:100%;height:375px;overflow-x:hidden;overflow-y:auto;top: 22px;">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>学号</th>
						<td>${dxpxjgForm.xh }</td>
						<th>姓名</th>
						<td>${dxpxjgForm.xm }</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>${dxpxjgForm.xb }</td>
						<th>身份证号</th>
						<td>${dxpxjgForm.sfzh }</td>
					</tr>
					<tr>
						<th>年级</th>
						<td>${dxpxjgForm.nj }</td>
						<th>学院</th>
						<td>${dxpxjgForm.xymc }</td>
					</tr>
					<tr>
						<th>专业</th>
						<td>${dxpxjgForm.zymc }</td>
						<th>班级</th>
						<td>${dxpxjgForm.bjmc }</td>
					</tr>
				</tbody>
			</table>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>培训信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>培训期次</th>
							<td>${dxpxjgForm.pxqc }</td>
							<th>培训时间</th>
							<td>${dxpxjgForm.pxsj }</td>
						</tr>
						<tr>
							<th>考勤成绩<input type="hidden" id="kqcjbfb" value="${dxpxjgForm.kqcjbfb }"/>
							<input type="kqcj" id="kqcj" value="${dxpxjgForm.kqcj }"/></th>
							<td>${dxpxjgForm.kqcj }</td>
							<th>实践成绩<input type="hidden" id="sjcjbfb" value="${dxpxjgForm.sjcjbfb }"/>
							<input type="sjcj" id="sjcj" value="${dxpxjgForm.sjcj }"/></th>
							<td>${dxpxjgForm.sjcj }</td>
						</tr>
						<tr>
							<th>笔记成绩<input type="hidden" id="bjcjbfb" value="${dxpxjgForm.bjcjbfb }"/>
							<input type="bjcj" id="bjcj" value="${dxpxjgForm.bjcj }"/></th>
							<td>${dxpxjgForm.bjcj }</td>
						</tr>
						<tr>
							<th>考试成绩<input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb }"/>
							<input type="kscj" id="kscj" value="${dxpxjgForm.kscj }"/></th>
							<td>${dxpxjgForm.kscj }</td>
							<th>总评成绩<input type="zpcj" id="zpcj" value="${dxpxjgForm.zpcj }"/></th>
							<td id="xszpcj">${dxpxjgForm.zpcj }</td>
						</tr>
						<tr>
							<th>评价结果</th>
							<td>${dxpxjgForm.pjjg }</td>
						</tr>
					</tbody>
				</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								&nbsp;&nbsp;&nbsp;&nbsp;
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
