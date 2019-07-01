<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/head.ini"%>
<title>成都体育学院新生基本情况登记表</title>
<style>
.font_style td{font-size:14px;font-family:隶书; }
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>

<div align=center>
<html:form action="/xszz_sqsh" method="post" styleId="xszzSqshForm">
<table width="90%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
				<br/><br/>
					<b>
					<span style='font-size:16.0pt;font-family:隶书;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						成都体育学院新生基本情况登记表
					</span>
					<br/>
					</b>
					<br/><br/>
					<div align="center">
						<span style='font-size:12.0pt;font-family:华文新魏;mso-ascii-font-family:"Times New Roman";'>
						系(部):${jbxx.xymc }&nbsp;&nbsp;&nbsp;
						专业:${jbxx.zymc }&nbsp;&nbsp;&nbsp;
						班级:${jbxx.bjmc }&nbsp;&nbsp;&nbsp;
						学号:${jbxx.xh }&nbsp;&nbsp;&nbsp;
						生源地:${jbxx.sydqmc }
						</span>
					</div>
					<br/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="font_style" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" style='border-collapse:collapse;border:none;'>
						<tr>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="3%"></td>
							<td width="4%"></td>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="6%"></td>
							<td width="15%"></td>
						</tr>
						<tr height="28px">
					        <td align="center">姓名</td>
					        <td align="center">${jbxx.xm }</td>
					        <td align="center">性别</td>
					        <td align="center">${jbxx.xb }</td>
					        <td align="center">籍<br/>贯</td>
					        <td align="center" colspan="2">${jbxx.jgmc }</td>
					        <td align="center">民族</td>
					        <td align="center">${jbxx.mzmc }</td>
					        <td align="center">出生<br/>年月</td>
					        <td align="center" colspan="3">${jbxx.csrq }</td>
					        <td align="center" rowspan="4">
								<img style="width:100px;height:130px;" src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh}" border="0"/>
							</td>
					      </tr>
						<tr height="28px">
					        <td align="center" rowspan="2">入学前学习<br/>(工作)单位</td>
					        <td align="center" colspan="2" rowspan="2">${jbxx.rxqxxdw }</td>
					        <td align="center">身高</td>
					        <td align="center" colspan="2">${jbxx.sg }&nbsp;CM</td>
					        <td align="center">应届</td>
					        <td align="center">${jbxx.yj }</td>
					        <td align="center">城镇</td>
					        <td align="center">${jbxx.cz }</td>
					        <td align="center" >是否为低保家庭</td>
					        <td align="center" colspan="2"></td>
					      </tr>
					      <tr height="28px">
					     	<td align="center">体重</td>
					        <td align="center" colspan="2">${jbxx.tz }&nbsp;KG</td>
					        <td align="center">往届</td>
					        <td align="center">${jbxx.wj }</td>
					        <td align="center">农村</td>
					        <td align="center">${jbxx.nc }</td>
					        <td align="center" >是否为低保家庭</td>
					        <td align="center" colspan="2"></td>
					      </tr>
					      <tr height="39px">
					        <td align="center">入学前<br/>户口所在地</td>
					        <td align="center" colspan="2">${jbxx.rxqhkszd }</td>
					        <td align="center">家庭详细<br/>通讯地址</td>
					        <td align="center" colspan="6">${jbxx.jtszd }</td>
					        <td align="center">邮政<br/>编码</td>
					        <td align="center" colspan="2">${jbxx.yb }</td>
					      </tr>
					      <tr height="39px">
					        <td align="center" rowspan="2">本人<br/>联系方式</td>
					        <td align="left" colspan="3">
					        	固定电话：${jbxx.jtdh }</td>
					        <td align="left" colspan="6">移动电话：${jbxx.sjhm }</td>
					        <td align="center" colspan="3" rowspan="2">入党(团)<br/>时间</td>
					        <td align="center" rowspan="2">
					        	<logic:notEmpty name="jbxx" property="rdsj"><bean:write name="jbxx" property="rdsj"/><br/>入党</logic:notEmpty>
					        	<logic:empty name="jbxx" property="rdsj"><bean:write name="jbxx" property="rtsj"/><br/>入团</logic:empty>
					        	</td>
					      </tr>
					      <tr height="39px">
					        <td align="left" colspan="3">QQ号：${jbxx.qqhm }</td>
					        <td align="left" colspan="6">电子邮箱：${jbxx.dzyx }</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">特长</td>
					        <td align="center" colspan="5">${jbxx.tc }</td>
					        <td align="center" colspan="2">学校寝室号码</td>
					        <td align="center" colspan="2">${jbxx.ssbh }</td>
					        <td align="center" colspan="2">身份证号码</td>
					        <td align="center" colspan="2">${jbxx.sfzh }</td>
					      </tr>
					      <tr height="40px">
					        <td align="center" colspan="3">入学前所取得的运动成绩、等级<br/>称号以及奖惩情况</td>
					        <td align="center" colspan="11">${jbxx.qk }</td>
					      </tr>
					      <tr height="28px">
					        <td align="center" rowspan="3">个人简历</td>
					        <td align="center" rowspan="3" colspan="4">${jbxx.grjl }</td>
					        <td align="center" rowspan="3">高考<br/>成绩</td>
					        <td align="center" rowspan="2">文考</td>
					        <td align="center">总分</td>
					        <td align="center">政治</td>
					        <td align="center">语文</td>
					        <td align="center">数学</td>
					        <td align="center">外语</td>
					        <td align="center" colspan="2">综合</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center" colspan="2">&nbsp;</td>
					      </tr>
					      <tr height="39px">
					        <td align="center">体考</td>
					        <td align="center">总分</td>
					        <td align="center"></td>
					        <td align="center">专<br/>项</td>
					        <td align="center" colspan="4"></td>
					      </tr>
					      <tr height="39px">
					        <td align="center" rowspan="5">家庭主要成<br/>员及社会关<br/>系</td>
					        <td align="center">姓名</td>
					        <td align="center">年龄</td>
					        <td align="center">与本人<br/>关系</td>
					        <td align="center" colspan="2">月收入<br/>(元)</td>
					        <td align="center">政治<br/>面貌</td>
					        <td align="center" colspan="5">现在何地和单位任何职务</td>
					        <td align="center" colspan="2">联系电话</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">${jbxx.jtcy1_xm }</td>
					        <td align="center">${jbxx.jtcy1_nlxx }</td>
					        <td align="center">${jbxx.jtcy1_gx}</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">${jbxx.jtcy1_zzmmmc}</td>
					        <td align="center" colspan="5">${jbxx.jtcy1_gzdz}${jbxx.jtcy1_zw}</td>
					        <td align="center" colspan="2">${jbxx.jtcy1_lxdh1}</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">${jbxx.jtcy2_xm }</td>
					        <td align="center">${jbxx.jtcy2_nlxx }</td>
					        <td align="center">${jbxx.jtcy2_gx}</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">${jbxx.jtcy2_zzmmmc}</td>
					        <td align="center" colspan="5">${jbxx.jtcy2_gzdz}${jbxx.jtcy2_zw}</td>
					        <td align="center" colspan="2">${jbxx.jtcy2_lxdh1}</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">${jbxx.jtcy3_xm }</td>
					        <td align="center">${jbxx.jtcy3_nlxx }</td>
					        <td align="center">${jbxx.jtcy3_gx}</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">${jbxx.jtcy3_zzmmmc}</td>
					        <td align="center" colspan="5">${jbxx.jtcy3_gzdz}${jbxx.jtcy3_zw}</td>
					        <td align="center" colspan="2">${jbxx.jtcy3_lxdh1}</td>
					      </tr>
					      <tr height="28px">
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center" colspan="2">&nbsp;</td>
					        <td align="center">&nbsp;</td>
					        <td align="center" colspan="5">&nbsp;</td>
					        <td align="center" colspan="2">&nbsp;</td>
					      </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="left">
				<br/>
					<span style='font-size:12.0pt;font-family:华文新魏;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
					注：1、本表由学生本人用黑色钢笔如实填写；且可在校园网学生处网页上下载。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				填表日期：&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日<br/>
	  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、本表一式两份，系学生管理办公室、学校学生处各存一份。
					</span>
				</td>
			</tr>
		</table>
	</html:form>
	</div>
</body>
</html>