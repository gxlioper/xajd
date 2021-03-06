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
			});
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="/dtxxjg">
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
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>学生党团发展进程</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
				      	<td colspan="4">
				      		<logic:notEmpty name="jdlist">
					        	<div class="Join_party">
										<ul>
											<logic:iterate name="jdlist" id="jdxx" indexId="i">
												<li>
													<input type="hidden" name="grxj" value="${jdxx.grxj }" />
													<input type="hidden" name="zd5" value="${jdxx.zd5 }" />
													<input type="hidden" name="jddm" value="${jdxx.jddm }" />
													<input type="hidden" name="jdmc" value="${jdxx.jdmc }"/>
													<input type="hidden" name="dtxxjgid" value="${jdxx.dtxxjgid }" />
													<input type="hidden" name="ksqkssj" value="${jdxx.ksqkssj}" />
													<input type="hidden" name="ksqjssj" value="${jdxx.ksqjssj}" />
													<input type="hidden" name="zd1" value="${jdxx.zd1}" />
													<input type="hidden" name="zd2" value="${jdxx.zd2}" />
													<input type="hidden" name="zd3" value="${jdxx.zd3}" />
													<input type="hidden" name="sjly" value="0"/><!-- 增加默认为结果数据 -->
													<div class="lc_bg">
													<div class="text">
													<p class="Process"><span>${jdxx.jdmc}</span>
													    <a href="#"	onclick="show(this,'${jdxx.jdmc}','${jdxx.jddm}')">
													    <i class="i1"></i>
													    </a>
													   </br><span name="sj">${jdxx.kssj}</span>
													</p>
													</div>
													</div>
													<logic:notEqual name="i" value="${size }">
							                   	 		<span class="Arrow"></span>
							                        </logic:notEqual>
													
												</li>
											</logic:iterate>
										</ul>
									</div>
				            </logic:notEmpty>
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
