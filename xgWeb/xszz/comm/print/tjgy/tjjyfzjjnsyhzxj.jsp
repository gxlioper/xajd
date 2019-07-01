<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
	<br/>
		<center>
			<span style="font-size:28px;font-family:黑体">天津市教育发展基金 天津农商银行助学金申请表</span>
		</center>
		<br/>
		<table width="800px" class="printtab" style="font-size:18px;font-weight:bold">
			<tr >
				<td width="100px">
				<p align="center">
					学校<br/>名称
				</p>	
				</td>
				<td colspan="6"><p align="center">
				${xxmc }
				</p> 
			    </td>
			</tr>
			<tr height="62px">
				<td width="100px" >
					<p align="center">
						姓 名
					</p>
				</td>
				<td >
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td >
					<p align="center">
						性 别
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td align="center">
						民 族
				</td>
				<td >
					<p align="center" >
						${rs.mzmc }
					</p>
				</td>
			</tr>
			<tr>
				<td width="100px" >
					<p align="center">
						政治<br/>面貌
					</p>
				</td>
				<td >
					<p align="center">
						${rs.zzmmmc }
					</p>
				</td>
				<td >
					<p align="center">
						出生<br/>年月
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						${rs.csrq }
					</p>
				</td>
				<td rowspan="4" colspan="2"  align="center">
					<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:118px;height:130px" />
				</td>
				
			</tr>
			<tr height="62px">
			<td width="100px">
					<p align="center">
						层次
					</p>
				</td>
				<td >
					<p align="center">
						${rs.pycc }
					</p>
				</td>
				<td >
					<p align="center">
						专业
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.zymc }
					</p>
				</td>
			</tr>
			<tr>
				<td width="100px">
					<p align="center">
						年级
					</p>
				</td>
				<td >
					<p align="center">
						${rs.nj }
					</p>
				</td>
				
				<td >
					<p align="center">
						联系<br/>方式
					</p>
				</td>
				<td  colspan="2">
					<p align="center">
						${rs.lxdh }
					</p>
				</td>
			</tr>
			<tr>
			<td width="100px">
			<p align="center">身份<br/>证号</p>
			</td>
			<td colspan="4">
			<p align="center" >
			${rs.sfzh }
			</p>
			</td>
			</tr>
			<tr  height="40px">
							<td rowspan="2" align="center">
								家庭
								<br />
								经济
								<br />
								情况
								<br />
							</td>
							<td align="center" >
								家庭人口总数
							</td>
							<td align="center">
								${rs.jtrs }
							</td>
							<td align="center">
								年人均收入
							</td>
							<td align="center">
								
							</td>
							<td align="center" >
								邮政编码
							</td>
							<td align="center">
								${rs.jtyb }
							</td>
			</tr>
			<tr>
			<td align="center">
								家庭住址
							</td>
							<td align="center" colspan="5">
								${rs.jtdz }
							</td>
			</tr>
			<tr  height="180">
				<td  align="center">
					学习<br/>
					成绩<br/>
					及获<br/>
					奖情<br/>
					况（<br/>
					新生<br/>
					注明<br/>
					高考<br/>
					成绩）<br/>
				</td>
				<td width="800" colspan="7" valign="top" style="word-break:break-all">
					&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxcj }
				</td>
			</tr>
			<tr  height="140">
				<td  align="center">
					申请<br/>理由
				</td>
				<td width="800" colspan="7" valign="top" style="word-break:break-all">
				<p style="height:130px">
				&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
				</p>
						<div align="right">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
						</div>
				</td>
			</tr>
			<tr  height="190">
				<td  align="center">
					学<br/>
					校<br/>
					审<br/>
					核<br/>
					意<br/>
					见<br/>
				</td>
				<td>
					<p style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公章
					</p>
					<p align="right">
					年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          日
					</p>
				</td>
				<td>
					<p align="center">
						天<br/>
						津<br/>
						农<br/>
						商<br/>
						银<br/>
						行<br/>
					</p>
				</td>
				<td>
					<p align="center" style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公章
					</p>
					<p align="right">
					年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          日
					</p>
				</td>
				<td>
					<p align="center">
						天<br/>
						津<br/>
						市<br/>
						教<br/>
						育<br/>
						发<br/>
						展<br/>
						基<br/>
						金<br/>
						会<br/>
					</p>
				</td>
				<td colspan="2">
					<p align="center" style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公章
					</p>
					<p align="right">
					年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          日
					</p>
				</td>
			</tr>
		</table>
			<table width="800px" border="0" align="center">
			<tr>
				<td>
					<p style="font-size:15px">备注：此表一式三份（分别由学校、天津农商银行、天津市教育发展基金会存档）</p>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>



		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
