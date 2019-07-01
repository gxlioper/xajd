<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
		</script>
	</head>
	<body onload="jd()">
	
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>您的当前位置:</em><a>${title }-查看教育活动</a>
			</p>
		</div>
	
		<html:form action="/xljk_xyxljkjyhd" method="post">
		
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button onclick="window.close();return false;" >
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>主 题
					</th>
					<td colspan="6" align="left">
						<html:text style="width:98%" property="zt" styleId="zt"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left" colspan="2">
						<html:text property="xymc" style="width:180px" styleId="xymc"
							readonly="true" />
					</td>
				</tr>

				<tr>
					<th colspan="2">
						<font color="red">*</font>教 育 形 式
					</th>
					<td align="left" colspan="2">
						<html:text property="hdxs"styleId="hdxs"
							readonly="true" />
					</td>
					<th colspan="2">
						其 他 形 式
					</th>
					<td align="left" colspan="2">
						<html:text property="qthdxs" styleId="qthdxs" readonly="true"
							readonly="true" />
					</td>
				</tr>
				<tr >
					<th colspan="2">
						<font color="red">*</font>地 点
					</th>
					<td align="left" colspan="2">
						<html:text property="dd" styleId="dd" readonly="true" />
					</td>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>活 动 日 期
					</th>
					<td colspan="2">
						<html:text styleId="dateF"
							property="rq" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						开 始 时 间
					</th>
					<td align="left" colspan="2">
						<html:text property="kssj" styleId="kssj" readonly="true" />
					</td>
					<th colspan="2">
						结 束 时 间
					</th>
					<td align="left" colspan="2">
						<html:text property="jssj" styleId="jssj" readonly="true" />
					</td>
				</tr>
				<tr >
					<th colspan="2">
						<font color="red">*</font>主 持 人
					</th>
					<td align="left" colspan="2">
						<html:text property="zcr" styleId="zcr" readonly="true" />
					</td>
					<th colspan="2">
						<font color="red">*</font>学 生
					</th>
					<td align="left" colspan="2">
						<html:text property="xs" readonly="true" />
					</td>
				</tr>
				<tr >
					<th colspan="2">
						<font color="red">*</font>参 与 学 生
					</th>
					<td colspan="6" align="left">
						<html:text style="width:98%" property="cyxs" styleId="cyxs"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>参 与 学 生 人 数
					</th>
					<td align="left" colspan="6">
						<html:text property="rs" styleId="rs" readonly="true" />
					</td>
				</tr>
				<!-- 闽江学院文件下载 -->
						<logic:equal name="xxdm" value="10395">
						<tr>
							<th colspan="2" nowrap="nowrap">
								文件下载
							</th>
							<td align="left" title="处分文件下载" colspan="6">
								<a href="downloadfilewj.do?len=&wjsclj=${wjdz }" target="_blank"><font color="red">下载</font></a>
							</td>
						</tr>
						</logic:equal>
				<tr>
					<th colspan="2">
						<font color="red">*</font> 教 育 活 动 记 录
					</th>
					<td colspan="6" align="left">
						<html:textarea rows="5" style="word-break:break-all;" cols="76"  property="hdjl"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>教 育 活 动 效 果
					</th>
					<td colspan="6" align="left">
						<html:textarea rows="5" style="word-break:break-all;" cols="76" property="hdxg"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
