<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<link rel=Edit-Time-Data href="奖学金.files/editdata.mso">
		<title>国家助学金申请审批表</title>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
		<style>
			.radic {
				position:relative;
			}
			.radic em {
				position:absolute;
				top:3px; 
				left:-4px;
				font-family:Arial;
				font-size:22px;
			}
		</style>
	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<input type="hidden" id="sfzh" value="${rs.sfzh }" />

		<div align="center">
			<p align=center>
				<b><span
					style='font-size:22.0pt;font-family:宋体;letter-spacing:1.0pt'>（${rs.xn}学年）国家助学金申请审批表</span>
				</b>
			</p>
			<br />
			<div align="left" style="width:99%;">
				<b>学校：</b><b>${xxmc }</b>&nbsp;
				<b>院系：</b><b>${rs.xymc }</b>&nbsp;
				<b>专业：</b><b>${rs.zymc }</b>&nbsp;
				<b>班级：</b><b>${rs.bjmc }</b>
			</div>
			<table class="printtab nowrap" border="0" align="center" style="width:99%">
				<%--<tr class="print">
					<td width="7%">
					</td>
					<td width="12%">
					</td>
					
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="2.2%">
					</td>
					<td width="2.3%">
					</td>
					<td width="2%">
					</td>
					
					<td width="2.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>

					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="2.2%">
					</td>
					
					<td width="2.3%">
					</td>
					<td width="4.5%">
					</td>
					<td width="5%">
					</td>
					<td width="2.2%">
					</td>
					<td width="2.3%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
					<td width="4.5%">
					</td>
				</tr>

				--%><tr class="nowrap" height="35px">
					<th rowspan=4 align=center  width="7%">
						<br />
						本
						<br />
						人
						<br />
						情
						<br />
						况
						<br />
					</th>
					<td align=center  width="12%">
						姓名
					</td>
					<td colspan="5" align=center width="15%">
						${rs.xm }
					</td>
					<td colspan="3" align=center width="12%">
						性别
					</td>
					<td colspan="4" align=center width="16.5%">
						${rs.xb }
					</td>
					<td colspan="4" align=center width="13.5%">
						出生年月
					</td>
					<td colspan="6" align=center width="24%">
						${rs.csrq }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						学号
					</td>
					<td colspan="5" align="center">
						${rs.xh }
					</td>
					<td colspan="3" align=center>
						民族
					</td>
					<td colspan="4" align="center">
						${rs.mzmc }
					</td>
					<td colspan="4" align=center>
						入学时间
					</td>
					<td colspan="6" align="center">
						${rs.rxrq }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						政治面貌
					</td>
					<td colspan="6" align="center" width="17%">
						${rs.zzmmmc }
					</td>
					<td colspan="4" align="center" width="20%">
						联系电话
					</td>
					<td colspan="12" align="center">
						${rs.lxdh }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						身份证号
					</td>
					<td id="s0" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s1" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s2" colspan="2" align="center">
						&nbsp;
					</td>
					<td id="s3" colspan="2" align="center">
						&nbsp;
					</td>
					<td id="s4" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s5" align="center" width="4.5%"> 
						&nbsp;
					</td>
					<td id="s6" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s7" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s8" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s9" colspan="2" align="center">
						&nbsp;
					</td>
					<td id="s10" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s11" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s12" align="center" colspan="2" >
						&nbsp;
					</td>
					<td id="s13" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s14" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s15" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s16" align="center" width="4.5%">
						&nbsp;
					</td>
					<td id="s17" align="center" width="4.5%">
						&nbsp;
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<th rowspan=4 align="center">
						<br />
						家
						<br />
						庭
						<br />
						经
						<br />
						济
						<br />
						情
						<br />
						况
						<br />
					</td>
					<td align="center">
						家庭户口
					</td>
					<td colspan="12" align="center">
						<logic:present name="rs" property="jthk">
							<logic:equal value="城镇" property="jthk" name="rs">
								<span class="radic">A<em>√</em></span>、城镇&nbsp;&nbsp;&nbsp;&nbsp;B、农村
							</logic:equal>
							<logic:equal value="农村" property="jthk" name="rs">
								A、城镇&nbsp;&nbsp;&nbsp;&nbsp;<span class="radic">B<em>&radic;</em></span>、农村
							</logic:equal>
							<logic:equal value="" property="jthk" name="rs">
								A、城镇 B、农村
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="jthk">
							A、城镇 B、农村
						</logic:notPresent>
					</td>
					<td colspan="4" align="center">
						收入来源
					</td>
					<td colspan="6" align="center">
						${rs.srly }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center" valign="center" style="padding:0px 0px 0px 0px">
						家庭月总
						<br />
						收入
					</td>
					<td colspan="12" align="center">
						${rs.jtyzsr }
					</td>
					<td colspan="4" align="center" valign="center"  style="padding:0px 0px 0px 0px">
						家庭人口
						<br />
						总数
					</td>
					<td colspan="6" align="center">
						${rs.jtrs }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						家庭住址
					</td>
					<td colspan="12" align="center">
						${rs.jtdz }
					</td>
					<td colspan="4" align="center">
						邮政编码
					</td>
					<td colspan="6" align="center">
						${rs.jtyb }
					</td>
				</tr>
				<tr height="35px" class="nowrap">
					<td align="center">
						认定情况
					</td>
					<td colspan="22" align="center">
						A、家庭经济特别困难
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						B、家庭经济一般困难
					</td>
				</tr>

				<tr style="height:22px" height="35px" class="nowrap">
					<th rowspan="5" align="center">
						<br />
						家
						<br />
						庭
						<br />
						成
						<br>
						员
						<br>
						情
						<br>
						况
						<br />
					</th>
					<td align="center">
						姓名
					</td>
					<td colspan="3" align="center" width="12.5%">
						年龄
					</td>
					<td colspan="5" align="center" width="14.5%">
						与本人关系
					</td>
					<td colspan="14" align="center">
						工作或学习单位
					</td>
				</tr>
				<logic:iterate name="cyList" id="cy" length="4">
					<tr height="35px" class="nowrap">
						<td align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</td>
						<td colspan="3" align="center">
							${cy.cynl }&nbsp;&nbsp;
						</td>
						<td colspan="5" align="center">
							${cy.mc }&nbsp;&nbsp;
						</td>
						<td colspan="14" align="center">
							${cy.cygzdw }&nbsp;&nbsp;
						</td>
					</tr>
				</logic:iterate>
				<%
							int cyNum = Integer.valueOf(request.getAttribute("cyNum")
							.toString());
					for (int i = 0; i < 4 - cyNum; i++) {
				%>
				<tr class="nowrap" style="height:35px">
					<td align="center">
						&nbsp;
					</td>
					<td align="center" colspan="3">
						&nbsp;
					</td>
					<td align="center" colspan="5">
						&nbsp;
					</td>
					<td align="center" colspan="14">
						&nbsp;
					</td>
				</tr>
				<%
				}
				%>

				<tr height="110px" class="nowrap">
					<th align=center>
						<br />
						申请
						<br />
						理由
						<br />
					</th>
					<td colspan="23">
						<p style="height: 50px">
							&nbsp;&nbsp;&nbsp;&nbsp; ${rs.sqly }
						</p>
						<p align=right>
							申请人签名（手签）：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="110px" class="nowrap">
					<th align="center">
						<br />
						院
						<br />
						(系)
						<br />
						意
						<br />
						见
						<br />
					</th>
					<td colspan="23">
						<p style="height: 50px">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align=right>
							（院系公章）
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="110px" class="nowrap">
					<th align=center>
						<br />
						学
						<br />
						校
						<br />
						意
						<br />
						见
						<br />
					</th>
					<td colspan="23">
						<p style="height: 50px">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align=right>
							（学校公章）
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp; &nbsp;月&nbsp;&nbsp;&nbsp; &nbsp;日&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<br />
			<div align="right">
				制表：浙江省学生资助管理中心 2010年版
			</div>
			<div align="center" class='noPrin'>
				<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					页面设置
				</button>
				<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					打印预览
				</button>
				<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					直接打印
				</button>
			</div>
			<script type="text/javascript">
		    	var sfzh = jQuery('#sfzh').val();
		    	for(var i=0;i<sfzh.length;i++){
			    	var id = "#s" + i;
			    	var sfzhs = sfzh.substring(i,i+1);
			    	if(jQuery(id)){
			    		jQuery(id).html(sfzhs); 
			    	}
		    	}
		    </script>
		</div>
		<p>
			&nbsp;
		</p>
	</body>
</html>
