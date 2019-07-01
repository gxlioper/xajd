<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<style>
		.hh{
			word-wrap:break-word;
		    word-break:break-all;
		    -moz-binding: url('./wordwrap.xml#wordwrap');
		    overflow: hidden;
     	}
		</style>
		<script type="text/javascript">
		function downSsfj(downType){
			var url="";
			if(downType=="ssfj"){
				url="wjcfCfssgl_cfsswh.do?method=cfssfjXz";
			}else if(downType=="cffj"){

			}else{
				return false;
			}
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
				
			
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfCfssgl_cfsswh.do?method=cfsswhCk" method="post">
			<input type="hidden" name="cfid" value="${pkValue }"/>
			<logic:present name="rs">
			<input type="hidden" name="ssfjmc" value="${rs.ssfjmc }"/>
			</logic:present>
			<div class="tab_cur" >
					<p class="location">
						<em>您的当前位置:</em><a>${title } - 增加</a>
					</p>
			</div>
			<%--<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>系统提示：</span></h3>
		       <p>1<br/></p>
		   	</div>
			--%><div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								学号
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								姓名
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xm"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								性别
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xb"/>
								</logic:present>
							</td>
							<th style="width:20%">
								年级
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="nj"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xymc"/>
								</logic:present>
							</td>
							<th style="width:20%">
								专业
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="zymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								班级
							</th>
							<td style="width:80%" colspan="3" class="hh">
								<logic:present name="rs">
									<bean:write name="rs" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>处分信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								处分学年
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xn"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处分学期
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="xq"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								处分类别
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cflbmc"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处分原因
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfyymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								处分文号
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfwh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处分时间
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="cfsj"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								违纪时间
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="wjsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								处理决定书面材料或附件
							</th>
							<td style="width:30%">
								<logic:notEmpty name="rs" property="cffjmc">
									<logic:notEqual name="rs" property="cffjmc" value="">
									<a href="#" onclick="downSsfj('ssfj');return false;">下载申诉附件</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="cffjmc">
									<logic:equal name="rs" property="cffjmc" value="">
									无上传申诉附件
									</logic:equal>
									</logic:empty>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								违纪事实经过
							</th>
							<td style="width:80%" colspan="3"  class="hh">
								<logic:present name="rs">
									<bean:write name="rs" property="wjssjg"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								备注
							</th>
							<td style="width:80%" colspan="3"  class="hh">
								<logic:present name="rs">
									<bean:write name="rs" property="bz"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>申诉信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%">
								申诉时间
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sqsj"/>
								</logic:present>
							</td>
							<th style="width:20%">
								证明材料或附件
							</th>
							<td style="width:30%">
								<logic:present name="rs">
								${ssfjmc }
									<logic:notEmpty name="rs" property="ssfjmc">
									<logic:notEqual name="rs" property="ssfjmc" value="">
									<a href="#" onclick="downSsfj('ssfj');return false;">下载申诉附件</a>
									</logic:notEqual>
									</logic:notEmpty>
									<logic:empty name="rs" property="ssfjmc">
									<logic:equal name="rs" property="ssfjmc" value="">
									无上传申诉附件
									</logic:equal>
									</logic:empty>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								申诉理由
							</th>
							<td style="width:30%" colspan="3"  class="hh">
								<logic:present name="rs">
									<bean:write name="rs" property="sqly"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>申诉审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="cfshxxList">
						<logic:iterate id="cf" name="cfshxxList" indexId="ind">
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="mc"/>审核结果
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shjg"/>
							</td>
							<th style="width:20%">
								<bean:write name="cf" property="mc"/>审核人
							</th>
							<td style="width:30%">
								<bean:write name="cf" property="shr"/>
							</td>
						</tr>
						<tr>
							<th style="width:20%">
								<bean:write name="cf" property="mc"/>审核意见
							</th>
							<td style="width:30%" colspan="3"  class="hh">
								<bean:write name="cf" property="shyj"/>
							</td>
						</tr>
						</logic:iterate>
						</logic:present>
						<tr>
							<th style="width:20%">
								申诉文号
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sswh"/>
								</logic:present>
							</td>
							<th style="width:20%">
								申诉时间
							</th>
							<td style="width:30%">
								<logic:present name="rs">
									<bean:write name="rs" property="sssj"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button"  class="button2"  onclick="Close();return false;">
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
