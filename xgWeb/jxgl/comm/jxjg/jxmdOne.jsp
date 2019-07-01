<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/comm/swbl/swblSq.js"></script>
		<script type="text/javascript">
		function sqSave(){
			if($("xh") && ""==$("xh").value){
				alertError("学号不能为空!");
				return false;
			}
			saveRcsw("/xgxt/rcswSwbl.do?method=swblSq&doType=save");
		}
		
		function sqUpdate(){
			refreshForm("/xgxt/rcswSwbl.do?method=swblXg&doType=save");
		}
		
		function dgsh(shzt){
			refreshForm("/xgxt/rcswSwbl.do?method=swblDgsh&doType=save&shzt="+shzt);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/jxglJxjg" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
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
							<th style="width:16%">
								学号
							</th>
							<td style="width:34%">
								${stuInfo.xh }
							</td>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${stuInfo.xm }
							</td>
						</tr>
							
							
							<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${stuInfo.xb }
							</td>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${stuInfo.nj }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${stuInfo.xymc }
							</td>
							<th style="width:16%">
								专业
							</th>
							<td style="width:34%">
								${stuInfo.zymc }
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${stuInfo.bjmc }
							</td>
							<th style="width:16%">
								
							</th>
							<td style="width:34%">
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>军训编制信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:16%">
								学年
							</th>
							<td style="width:34%">
								${xsjxbz.xn}
							</td>
							<th style="width:16%">
								${xsjxbz.jzjb1}
							</th>
							<td style="width:34%">
								${xsjxbz.jzmc1}
							</td>
							
						</tr>
						<tr>
							<th style="width:16%">
								${xsjxbz.jzjb2}
							</th>
							<td style="width:34%">
								${xsjxbz.jzmc2}
							</td>
							
							<th style="width:16%">
								${xsjxbz.jzjb3}
							</th>
							<td style="width:34%">
								${xsjxbz.jzmc3}
							</td>
							
						</tr>
						<tr>
							<th style="width:16%">
								教师
							</th>
							<td style="width:34%">
								${xsjxbz.jsdm}
							</td>
							
							<th style="width:16%">
								教官
							</th>
							<td style="width:34%">
								${xsjxbz.jgbh}
							</td>
							
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="Close();return false;">
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
