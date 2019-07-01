<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	<body>	
			<html:form action="/n05_xszz.do" method="post">
				 <div align="center" style="font-size:20px;font:'黑体' "><b>${xxmc}学生学费缓交申请表</b></div>
				<p align="right"><strong>学号: </strong><strong>${rs.xh}
				<logic:empty name="rs" property="xh">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:empty></strong></p>
				<div align="center">
				  <table cellspacing="0" cellpadding="0" class="tbstyle" width="95%">
				    <tr>
				      <td colspan="4" width="10%"><p align="center"><strong>姓 名 </strong></p></td>
				      <td colspan="4" width="13%"><p align="center">${rs.xm}&nbsp;</p></td>
				      <td colspan="4" width="10%"><p align="center"><strong>性别 </strong></p></td>
				      <td colspan="2" width="6%"><p align="center">&nbsp;${rs.xb}</p></td>
				      <td colspan="6" width="20%"><p align="center"><strong>年级专业班级 </strong></p></td>
				      <td colspan="4" width="20%"><p>${rs.nj}${rs.zymc}${rs.bjmc}</p></td>
				      <td colspan="2" width="7%"><p><strong>身份证号 </strong></p></td>
				      <td colspan="4" width="25%"><p>&nbsp; ${rs.sfzh}</p></td>
				    </tr>
				    <tr>
				      <td colspan="4"><p align="center"><strong>政治面貌 </strong></p></td>
				      <td colspan="4"><p align="center">${rs.zzmmmc}&nbsp;</p></td>
				      <td colspan="6"><p align="center"><strong>户口所在地 </strong></p></td>
				      <td colspan="10"><p align="center">${rs.hkszd}</p></td>
				      <td colspan="2"><p><strong>本人电话 </strong></p></td>
				      <td colspan="4" valign="top"><p>&nbsp; ${rs.sjhm}</p></td>
				    </tr>
				    <tr>
				      <td colspan="4"><p align="center"><strong>德育等第 </strong></p></td>
				      <td colspan="4"><p align="center">${rs.dydd}&nbsp;</p></td>
				      <td colspan="13"><p align="center"><strong>家庭住址、邮政编码 </strong></p></td>
				      <td colspan="9"><p>&nbsp; 地址：${rs.jtdz}邮编：${rs.jtyb}</p></td>
				    </tr>
				    <tr>
				      <td height="16" colspan="6"><p align="center"><strong>曾获何种奖励 </strong></p></td>
				      <td colspan="15"><p>&nbsp; ${rs.hjjl}</p></td>
				      <td colspan="5"><p align="center"><strong>可联系家长电话 </strong></p></td>
				      <td colspan="4"><p>&nbsp; ${rs.lxdh1}</p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center"><strong>有无违纪处分 </strong></p></td>
				      <td colspan="7"><p align="center">&nbsp; ${rs.sfywjxx}</p></td>
				      <td colspan="15"><p align="center"><strong>就读期间累计不及格必修课门数 </strong></p></td>
				      <td colspan="2"><p>&nbsp; ${rs.ljbjgbxkms}</p></td>
				    </tr>
				    <tr>
				      <td colspan="2" rowspan="2"><p align="center"><strong>家庭收 </strong></p>
				          <p align="center"><strong>入来源 </strong></p></td>
				      <td colspan="11" rowspan="2"><p align="center">&nbsp; ${rs.jtsrly}</p></td>
				      <td colspan="9" rowspan="2"><p align="center"><strong>全年家庭经 </strong></p>
				          <p align="center"><strong>济总收入 </strong></p></td>
				      <td width="95" rowspan="2"><p align="center">&nbsp; ${rs.jtnsr} </p></td>
				      <td colspan="5"><p align="center"><strong>家庭人口数 </strong></p></td>
				      <td colspan="2"><p>&nbsp; ${rs.jtrks}</p></td>
				    </tr>
				    <tr>
				      <td colspan="5"><p align="center"><strong>人均月收入 </strong></p></td>
				      <td colspan="2"><p>&nbsp; ${rs.jtrjysr}</p></td>
				    </tr>
				    <tr>
				      <td colspan="13"><p><strong>家 庭 类 型 </strong></p></td>
				      <td colspan="17">
						<p align="center">
						<strong>
						双亲（ ）
						<logic:equal value="是" name="sfdq">
						 单亲（ √ ）  
						</logic:equal>
						<logic:notEqual value="是" name="sfdq">
						 单亲（ ）  
						</logic:notEqual>
						离异（ ）
						<logic:equal value="是" name="sflszn">
						  烈属（√ ）  
						</logic:equal>
						<logic:notEqual value="是" name="sflszn">
						  烈属（ ）  
						</logic:notEqual>
						<logic:equal value="是" name="sfgc">
						   孤儿（√ ）
						</logic:equal>
						<logic:notEqual value="是" name="sfgc">
						  孤儿（ ） 
						</logic:notEqual>
						 </strong>  
					
					  </p></td>
				    </tr>
				    <tr>
				      <td width="56" rowspan="6"><p align="center"><strong>家</strong><strong>庭</strong><strong>主要成员 </strong></p>
				          </td>
				      <td colspan="6"><p align="center"><strong>姓名 </strong></p></td>
				      <td colspan="6"><p align="center"><strong>称谓 </strong></p></td>
				      <td colspan="4"><p align="center"><strong>月收入 </strong></p></td>
				      <td colspan="13"><p align="center"><strong>工作或学习单位 </strong></p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center">${rs.jtcy1_xm}&nbsp;</p></td>
				      <td colspan="6"><p align="center">${rs.jtcy1_gx}&nbsp;</p></td>
				      <td colspan="4"><p align="center">${rs.jtcy1_ysr}&nbsp;</p></td>
				      <td colspan="13"><p align="center">${rs.jtcy1_gzdwmc}&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center">${rs.jtcy2_xm}</p></td>
				      <td colspan="6"><p align="center">${rs.jtcy2_gx}&nbsp;</p></td>
				      <td colspan="4"><p align="center">${rs.jtcy2_ysr}&nbsp;</p></td>
				      <td colspan="13"><p align="center">${rs.jtcy2_gzdwmc}&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td colspan="6"><p align="center">${rs.jtcy3_xm}&nbsp;</p></td>
				      <td colspan="6"><p align="center">${rs.jtcy3_gx}&nbsp;</p></td>
				      <td colspan="4"><p align="center">${rs.jtcy3_ysr}&nbsp;</p></td>
				      <td colspan="13"><p align="center">${rs.jtcy3_gzdwmc}&nbsp;</p></td>
				    </tr>
					<tr>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="4"><p align="center">&nbsp;</p></td>
				      <td colspan="13"><p align="center">&nbsp;</p></td>
				    </tr>
					<tr>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="6"><p align="center">&nbsp;</p></td>
				      <td colspan="4"><p align="center">&nbsp;</p></td>
				      <td colspan="13"><p align="center">&nbsp;</p></td>
				    </tr>
				    <tr>
				      <td width="56" rowspan="9"><p align="center"><strong>受 </strong></p>
				          <p align="center"><strong>资 </strong></p>
				          <p align="center"><strong>助 </strong></p>
				          <p align="center"><strong>情 </strong></p>
				          <p align="center"><strong>况 </strong></p>
				          <p align="center"><strong>&nbsp; </strong></p></td>
				      <td colspan="12"><p align="center"><strong>资助项目 </strong></p></td>
				      <td colspan="9"><p align="center"><strong>第一学年金额 </strong></p></td>
				      <td colspan="3"><p align="center"><strong>第二学年金额 </strong></p></td>
				      <td colspan="4" nowrap="nowrap"><p align="center"><strong>第三学年金额 </strong></p></td>
				      <td width="267"><p align="center"><strong>总资助金额 </strong></p></td>
				    </tr>
				    <tr>
				      <td height="25" colspan="12"><p align="center">国家/生源地助学贷款</p></td>
				      <td colspan="9"><p align="center">${rs.zxdkxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.zxdkxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.zxdkxn3}</p></td>
				      <td width="267"><p align="center">${rs.zxdkzje}</p></td>
				    </tr>
				    <tr>
				      <td height="25" colspan="12"><p align="center">助学金</p></td>
				      <td colspan="9"><p align="center">${rs.zxjxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.zxjxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.zxjxn3}</p></td>
				      <td width="267"><p align="center">${rs.zxjzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">奖学金</p></td>
				      <td colspan="9"><p align="center">${rs.jxjxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.jxjxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.jxjxn3}</p></td>
				      <td width="267"><p align="center">${rs.jxjzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">勤工助学</p></td>
				      <td colspan="9"><p align="center">${rs.qgzxxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.qgzxxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.qgzxxn3}</p></td>
				      <td width="267"><p align="center">${rs.qgzxzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">学费减免</p></td>
				      <td colspan="9"><p align="center">${rs.xfjmxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.xfjmxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.xfjmxn3}</p></td>
				      <td width="267"><p align="center">${rs.xfjmzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">临时困难补助 </p></td>
				      <td colspan="9"><p align="center">${rs.lsknbzxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.lsknbzxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.lsknbzxn3}</p></td>
				      <td width="267"><p align="center">${rs.lsknbzzje}</p></td>
				    </tr>
					<tr>
				      <td height="25" colspan="12"><p align="center">慈善助学金</p></td>
				      <td colspan="9"><p align="center">${rs.cszxjxn1}</p></td>
				      <td colspan="3"><p align="center">${rs.cszxjxn2}</p></td>
				      <td colspan="4"><p align="center">${rs.cszxjxn3}</p></td>
				      <td width="267"><p align="center">${rs.cszxjzje}</p></td>
				    </tr>
				    <tr>
				      <td height="25" colspan="29"><p><strong>1. </strong><b>国家/生源地助学贷款 2.助学金 3.奖学金 4.勤工助学 5.学费减免 6.临时困难补助 7.慈善助学金 </b></td>
				    </tr>
				    <tr>
				      <td colspan="5" height="20"><b>申请学费缓交主要原因 </b></td>
				      <td colspan="25"><p>&nbsp; ${rs.sqyy}<strong>&nbsp; </strong></p>
				          <p><strong>&nbsp; </strong></p></td>
				    </tr>
				    <tr>
				      <td colspan="5" valign="top"><p align="center"><strong>缓交金额 </strong></p></td>
				      <td colspan="5"><p>&nbsp; ${rs.hjje}</p></td>
				      <td colspan="12"><p align="center"><b>预计交费时间、金额 </b></p></td>
				      <logic:empty name="rs" property="yjdycjfsj">
				      <td colspan="14"><p><b>第一次（&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;） 第二次（&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;）</b></p></td>
				      </logic:empty>
				      <logic:notEmpty name="rs" property="yjdycjfsj">
				      <td colspan="14"><p><b>第一次（</b>时间：${rs.yjdycjfsj}金额：${rs.yjdycjfje} <b>） 第二次（</b>时间：${rs.yjdecjfsj}金额：${rs.yjdecjfje} <b>）</b></p></td>
				      </logic:notEmpty>
				    </tr>
				    <tr>
				      <td colspan="15"><b>家庭是否持有相关困难证件</b>(请根据实际情况,在右边的四个选项中选择)</td>
				      <td colspan="15" rowspan="2">
							<b>
							<logic:equal value="特困证" name="rs" property="tkz">
							1.《特困证》（√ ）
							</logic:equal>
							<logic:notEqual value="特困证" name="rs" property="tkz">
							1.《特困证》（  ）
							</logic:notEqual>

							<logic:equal value="最低生活保障证" name="rs" property="zdshbzz">
							 2.《最低生活保障证》（ √）
							</logic:equal>
							<logic:notEqual value="最低生活保障证" name="rs" property="zdshbzz">
							 2.《最低生活保障证》（  ）
							</logic:notEqual>
							<logic:equal value="社会扶助证" name="rs" property="shfzz">
							3.《社会扶助证》（√ 
							</logic:equal>
							<logic:notEqual value="社会扶助证" name="rs" property="shfzz">
							3.《社会扶助证》（）
							</logic:notEqual>
							4.（<logic:equal value="县（市、区）民政局证明" name="rs" property="zxzm">
										C
							</logic:equal>
							<logic:equal value="县街道证明" name="rs" property="zxzm">
										B
							</logic:equal>
							<logic:equal value=" 镇（乡）证明" name="rs" property="zxzm">
										A
							</logic:equal> ）证明：A. 镇（乡） B. 街道  C. 县（市、区）民政局
							

				      		</br>
							</b>
				       		 持有证件或证明请附相关复印件
					  </td>
				    </tr>
				    <tr>
				      <td colspan="11"><p><strong>家庭所在地最低生活保障线 </strong></p></td>
				      <td colspan="4"><p>&nbsp;${rs.jtszdzdshbzx}</p></td>
				    </tr>
				    <tr>
				      <td colspan="15">
						<p align="center"><b>在当地有无享受过任何经济困难补</b><br>
				          		<b>助？具体说明补助性质、金额等 </b></p>
					  </td>
				      <td colspan="15">
						  <p>&nbsp; ${rs.xsjjknbzqk} </p>
                      </td>
				    </tr>
				    <tr>
				      <td colspan="6">
						 <p><strong>父母是否知晓学费缓交？ </strong><br></p>
				          <p><strong>&nbsp; </strong></p>
				          <p>&nbsp; ${rs.fmsfzxxfhj}</p>
				          <p><strong>&nbsp; </strong></p>
					  </td>
				      <td colspan="10">
						  <p><strong>班主任初审意见 : </strong></p>
				          <p>${rs.fdyshyj}</p>
						  <p><strong>&nbsp; </strong></p>				    
				          <p><strong>班主任签名： </strong></p>
				          <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </strong></p>        
					  </td>
				      <td colspan="9">
						<p><strong>分院 / 系审查意见 : </strong><br></p>
				        <p>${rs.xyshyj}</p>
				        <p><strong>&nbsp; </strong></p>
				        <p><strong>分院 / 系盖章 : </strong></p>
				        <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></p>        
					  </td>
				      <td colspan="5">
						<p><strong>学生处审核意见 ：</strong></p>
				        <p>${rs.xxshyj}</p>
				        <p><strong>&nbsp; </strong></p>
				        <p ><strong>学生处盖章： </strong></p>
				        <p align="center"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></p>
					</td>
				    </tr>
				    <tr>
				      <td colspan="3"><p align="center"><strong>备注 </strong></p></td>
				      <td colspan="27"><p><strong>学费必须在学生毕业离校前交清。 </strong></p></td>
				    </tr>
				  </table>
				</div>
		</html:form>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
