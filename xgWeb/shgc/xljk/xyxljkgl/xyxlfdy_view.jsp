<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script type="text/javascript" >
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
				<em>您的当前位置:</em><a>${title } - 查看</a>
			</p>
		</div>
		<html:form action="/xljk_xljkfdy" method="post">
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th width="16%">
						<font color="red">*</font>
						<logic:equal value="10827" name="xxdm">
								心理专干编号
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
								辅导员编号
						</logic:notEqual>
					</th>
					<td align="left" width="34%">
						<html:text property="fdybh" styleId="fdybh" readonly="true" />
					</td>
					<th>
						<font color="red">*</font>姓 名
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr>
				<logic:equal value="10827" name="xxdm">
					<th>
						<font color="red">*</font>毕 业 院 校
					</th>
					<td align="left">
						<html:text property="byyx" styleId="byyx" readonly="true" />
					</td>
				</logic:equal>
				<logic:notEqual value="10827" name="xxdm">
					<th>
						<font color="red">*</font>性 别
					</th>
					<td>
						<html:text property="xb"  styleId="xb"
							readonly="true" />
					</td>
				</logic:notEqual>
					<th>
						出 生 年 月
					</th>
					<td align="left">
						<html:text property="csny" styleId="csrq" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>学 院
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<logic:notEqual value="10827" name="xxdm">
					<th>
						<font color="red">*</font>毕 业 院 校
					</th>
					<td align="left">
						<html:text property="byyx" styleId="byyx" readonly="true" />
					</td>
					</logic:notEqual>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>任 职 时 间
					</th>
					<td align="left">
						<html:text property="rzsj" styleId="rzsj" readonly="true" />
					</td>
					<th>
						职 务
					</th>
					<td align="left">
						<html:text property="zw" styleId="zw" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						职 称
					</th>
					<td align="left">
						<html:text property="zc" styleId="zc" readonly="true" />
					</td>
					<th>
						<font color="red">*</font>学 历
					</th>
					<td align="left">
						<html:text property="xl" styleId="xl" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>专 业
					</th>
					<td align="left">
						<html:text property="zy" readonly="true" />
					</td>
					<th>
						<font color="red">*</font>手 机 号 码
					</th>
					<td align="left">
						<html:text property="sjhm" styleId="sjhm" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>联 系 电 话
					</th>
					<td align="left">
						<html:text property="lxdh" readonly="true" />
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>从事心理健康教育
						<br/>
						工作经历
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="7" style="width:98%" property="gzjl"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>参加心理健康教育
						<br/>
						培训情况
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="7" style="width:98%" property="pxqk"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>心理健康教育
						<br/>
						方面的论文及
						<br/>
						科研成果
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="7" style="width:98%" property="lwcg"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<th>
						备 注
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="5" style="width:98%" property="bz"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
