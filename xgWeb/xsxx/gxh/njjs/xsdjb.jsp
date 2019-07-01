<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 头文件 -->
<html>
	<body>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
				.noPrin{display:none;}
			</style>
		<!-- end -->
	</head>
	<center>
		<span style="font-size:22px;font-family:黑体">学 生 登 记 表</span>
	</center>
	<br />
	<table class="printtab" width="100%" style="font-size:14px">
		<tr style="height:35px">
			<td align="center" width="15%">
				姓名
			</td>
			<td colspan="2" align="center" width="14%">
				${rs.xm }
			</td>
			<td colspan="2" align="center" width="14%">
				曾用名
			</td>
			<td align="center" width="14%">
				${rs.cym }
			</td>
			<td colspan="2" align="center" width="14%">
				性 别
			</td>
			<td colspan="2" align="center" width="12%">
				${rs.xb }
			</td>
			<td rowspan="5" width="18%">
				<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				系 别
			</td>
			<td colspan="2" align="center">
				${rs.xymc }
			</td>
			<td colspan="2" align="center">
				班 级
			</td>
			<td align="center">
				${rs.bjmc }	
			</td>
			<td colspan="2" align="center">
				民 族
			</td>
			<td colspan="2" align="center">
				${rs.mzmc }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				学 号
			</td>
			<td colspan="2" align="center">
				${rs.xh }
			</td>
			<td colspan="2" align="center">
				出生年月
			</td>
			<td align="center">
				${rs.csrq }
			</td>
			<td colspan="2" align="center">
				籍 贯
			</td>
			<td colspan="2" align="center">
				${rs.sydmc }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				健康状况
			</td>
			<td colspan="2" align="center">
				${rs.jkzk }
			</td>
			<td colspan="2" align="center">
				毕业学校
			</td>
			<td align="center">
				${rs.xxmc }
			</td>
			<td colspan="2" align="center">
				户口属地
			</td>
			<td colspan="2" align="center">
				${rs.hkszd }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				身份证号码
			</td>
			<td colspan="5" align="center">
				${rs.sfzh }
			</td>
			<td colspan="2" align="center">
				是否团员
			</td>
			<td colspan="2" align="center">
				<logic:present name="rs" property="zzmmmc">
					<logic:match value="团员" name="rs" property="zzmmmc">
						是
					</logic:match>
					<logic:notMatch value="团员" name="rs" property="zzmmmc">
						否
					</logic:notMatch>
				</logic:present>
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center" rowspan="2">
				家庭现
				<br />
				在住址
			</td>
			<td colspan="5" rowspan="2" align="center">
				${jtcy.jtszd }
			</td>
			<td colspan="2" align="center">
				邮 编
			</td>
			<td colspan="3" align="center">
				${jtcy.jtyb }
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="2" align="center">
				电 话
			</td>
			<td colspan="3" align="center">
				${rs.lxdh }
			</td>
		</tr>

		<tr style="height:35px">
			<td rowspan="5" align="center">
				学
				<br />
				历
				<br />
				与
				<br />
				经
				<br />
				历
			</td>
			<td colspan="4" align="center">
				何年何月至何年何月
			</td>
			<td colspan="2" align="center">
				何 处 求 学
			</td>
			<td colspan="4" align="center">
				证明人
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
				&nbsp;
			</td>
			<td colspan="2" align="center">
				&nbsp;
			</td>
			<td colspan="4" align="center">
				&nbsp;
			</td>
		</tr>
		<tr style="height:35px">
			<td rowspan="4" align="center">
				家
				<br />
				庭
				<br />
				人
				<br />
				员
				<br />
				情
				<br />
				况
			</td>
			<td align="center" width="9%">
				称谓
			</td>
			<td colspan="2" align="center" width="10%">
				姓名
			</td>
			<td align="center" width="9%">
				年龄
			</td>
			<td colspan="2" align="center">
				工作学习单位
			</td>
			<td colspan="2" align="center">
				年收入
			</td>
			<td colspan="2" align="center">
				联系电话
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				${jtcy.jtcy1_gx }
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy1_xm }
			</td>
			<td align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy1_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				${jtcy.jtcy2_gx }
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy2_xm }
			</td>
			<td align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy2_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				${jtcy.jtcy3_gx }
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy3_xm }
			</td>
			<td align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="2" align="center">
				${jtcy.jtcy3_lxdh1 }
			</td>
		</tr>
		<tr style="height:35px">
			<td rowspan="5" align="center">
				曾
				<br />
				获
				<br />
				得
				<br />
				的
				<br />
				荣
				<br />
				誉
			</td>
			<td colspan="4" align="center">
				何年何月至何年何月
			</td>
			<td colspan="2" align="center">
				获 得 荣 誉
			</td>
			<td colspan="4" align="center">
				证明人
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td colspan="4" align="center">
			</td>
			<td colspan="2" align="center">
			</td>
			<td colspan="4" align="center">
			</td>
		</tr>
		<tr style="height:35px">
			<td align="center">
				特
				<br />
				长
			</td>
			<td colspan="10" align="center">
				${rs.tc }
			</td>
		</tr>
	</table>
	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
	</body>
</html>
