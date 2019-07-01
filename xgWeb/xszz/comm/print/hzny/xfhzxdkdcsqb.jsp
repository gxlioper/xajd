<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
<!-- 头文件 -->
<html:html>
<body>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<p align=center style="line-height:10px">
			<span style='font-size:18.0pt;color:black;'>学费和国家助学贷款代偿申请表</span>
		</p>
		<p align="left" style="line-height:0px">
			<span
				style='font-size:12.0pt;font-family:宋体;color:black;text-indent:300px '>填报日期：&nbsp;&nbsp;
				&nbsp;&nbsp;年&nbsp;&nbsp;月 &nbsp;&nbsp;日</span>
		</p>
		<table width="100%" class="printstyle" align="center">
			<tr style="height:45px">
				<td align="center">
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>姓<span
							lang=EN-US>&nbsp;&nbsp;&nbsp; </span>名</span>
					</p>
				</td>
				<td align="center" colspan=2>
					${rs.xm }
				</td>
				<td align="center">
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>性<br />别</span>
					</p>
				</td>
				<td align="center" colspan=3>
					${rs.xb }
				</td>
				<td align="center" colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>政治面貌</span>
					</p>
				</td>
				<td align="center" colspan=4>
					${rs.zzmmmc }
				</td>
				<td align="center" colspan=3>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>出生<br />年月</span>
					</p>
				</td>
				<td align="center">
					${rs.csrq }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>毕业学校</span>
					</p>
				</td>
				<td align="center" colspan=6>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>华中农业大学</span>
					</p>
				</td>
				<td align="center" colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>所学专业</span>
					</p>
				</td>
				<td align="center" colspan=8>
					${rs.zymc }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>毕业时间</span>
					</p>
				</td>
				<td align="center" colspan=2>
					${rs.byrq }
				</td>
				<td align="center" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>身份证号</span>
					</p>
				</td>
				<td align="center" colspan=7>
					${rs.sfzh }
				</td>
				<td align="center" colspan=4>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>已签定<br />的服务<br />年限</span>
					</p>
				</td>
				<td align="center" colspan=2>
					${rs.kzzd1 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>本人联系<br />电话</span>
					</p>
				</td>
				<td align="center" colspan=4>
					${rs.lxdh }
				</td>
				<td align="center" colspan=4>
					<p align=left style='text-align:left'>
						<span style='font-size:
  12.0pt;font-family:宋体;color:black'>电子邮件地址</span>
					</p>
				</td>
				<td align="center" colspan=10>
					${rs.dzyx }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="center">
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>代偿账户<br />信息</span>
					</p>
				</td>
				<td align="" colspan=4 valign="top">
					<span style='font-size:12.0pt;font-family:宋体;color:black;text-indent: 10px;'>开户名：${rs.kzzd2 }</span>
				</td>
				<td colspan=4>
					<span
						style='font-size:12.0pt;font-family:宋体;color:black;text-align: left'>开户银行：</span>
					<br />
					<b><span
						style='font-size:12.0pt;font-family:宋体;
  						color:black;text-align:center;text-indent:50px'>中国银行武汉<br />&nbsp;&nbsp;华农支行</span>
					</b>
				</td>
				<td align="left" colspan=10 valign="top">
					<span style='font-size:12.0pt;font-family:宋体;color:black;text-indent: 10px;'>开户账号：${rs.kzzd3 }</span>
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>家庭地址及邮编</span>
					</p>
				</td>
				<td align="center" colspan=16>
					&nbsp;${rs.jtyb }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>就业单位名称</span>
					</p>
				</td>
				<td align="center" colspan=16>
					&nbsp;${rs.kzzd4 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>就业单位详细地址</span>
					</p>
				</td>
				<td align="center" colspan=16>
					&nbsp;${rs.kzzd5 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" style="text-indent: 10px" colspan=3>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>就业单位联系电话</span>
					</p>
				</td>
				<td align="center" colspan=7>
					&nbsp;${rs.kzzd6 }
				</td>
				<td align="center"  colspan=4>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>单&nbsp;&nbsp;位<br />邮&nbsp;&nbsp;编</span>
					</p>
				</td>
				<td align="center" colspan=5>
					&nbsp;${rs.kzzd7 }
				</td>
			</tr>
			<tr style="height:45px">
				<td align="left" colspan=2 valign=top>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black;'>&nbsp;实际交学费<br />&nbsp;金额（含已放<br />&nbsp;款总额）<span
							lang=EN-US>*</span>
						</span>
					</p>
				</td>
				<td align="center" colspan=2>
					&nbsp;${rs.kzzd8 }
				</td>
				<td align="center" colspan=4>
					<p align=center style='text-align:center'>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>贷款本金<br />总额</span>
					</p>
				</td>
				<td align="center" colspan=4>
					&nbsp;${rs.kzzd9 }
				</td>
				<td align="center" colspan=4>
					<p>
						<span style='font-size:12.0pt;font-family:宋体;color:black'>申请代偿<br />金额</span>
					</p>
				</td>
				<td align="center" colspan=3>
					&nbsp;${rs.kzzd10 }
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:宋体;color:black;height: 50px'><bean:message key="lable.xb" />审查意见：</span>
					
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  							font-family:宋体;color:black'>单位公章：<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;
						</span>日</span>
					</p>
				</td>
			</tr>
			<tr style="height:75px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:宋体;color:black;height: 30px'>毕业学校财务部门对实际交纳学费及获得国家助学贷款的审查意见：</span>
					<p align="left" style="text-indent: 230px" >
						<span style='font-size:12.0pt;
  font-family:宋体;color:black'>单位公章：<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;
						</span>日</span>
					</p>
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:宋体;color:black;height: 50px'>毕业学校学生资助管理中心审查意见：</span>
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  font-family:宋体;color:black'>单位公章：<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;
						</span>日</span>
					</p>
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:宋体;color:black;height: 50px'>毕业学校审查意见：</span>
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  font-family:宋体;color:black'>单位公章：<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;
						</span>日</span>
					</p>
				</td>
			</tr>
			<tr style="height:90px">
				<td align="left" colspan=19>
					<span style='font-size:12.0pt;font-family:宋体;color:black;height: 20px'>全国学生资助管理中心审核意见：</span>
					<p align="left" style="text-indent: 130px">
						<span style='font-size:12.0pt;
  font-family:宋体;color:black'>经审核，同意办理代偿手续，最终核定代偿金额为人民币<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>元。<br/></span>
					</p>
					<p align="left" style="text-indent: 230px">
						<span style='font-size:12.0pt;
  font-family:宋体;color:black'>单位公章：<span
							lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>年<span lang=EN-US>&nbsp;&nbsp; </span>月<span lang=EN-US>&nbsp;&nbsp;
						</span>日</span>
					</p>
				</td>
			</tr>
			<tr height=0>
				<td width=84></td>
				<td width=24></td>
				<td width=60></td>
				<td width=37></td>
				<td width=23></td>
				<td width=24></td>
				<td width=36></td>
				<td width=9></td>
				<td width=51></td>
				<td width=12></td>
				<td width=12></td>
				<td width=16></td>
				<td width=32></td>
				<td width=12></td>
				<td width=12></td>
				<td width=24></td>
				<td width=24></td>
				<td width=12></td>
				<td width=66></td>
			</tr>
		</table>
		<p>
			<span style='font-family:宋体;color:black'>注：<b>＊</b>此处金额为申请人最后学历相应学制规定年限内的学费金额和贷款金额。</span>
		</p>
	</html:form>
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
</body>
</html:html>
