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
		<center>
			<html:form action="/xsxxdj" method="post">
				<p align="center">&nbsp;&nbsp;</p>
				<p align="center"><h3 >${xxmc}学生信息登记表 </h3></p>
				<table width="95%" height="524" cellpadding="0" cellspacing="0" class="tbstyle">
					<tr>
						<td width="5%"></td><td width="5%"></td><td width="6%"></td><td  width="5%"></td><td  width="12%"></td><td  width="6%"></td><td   width="6%"></td><td  width="15%"></td><td width="4%"></td><td width="4%"></td><td width="4%"></td><td width="3%"></td><td width="3%"></td><td width="3%"></td><td width="3%"></td><td width="12%"></td><td  width="5%"></td><td width="4%"></td>
					</tr>
				  <tr>
				    <td colspan="2"><p align="center">班级名称 </p></td>
				    <td colspan="3"><p align="center">${stuRs.bjmc} </p></td>
				    <td colspan="2"><p align="center">学号 </p></td>
				    <td ><p align="center">${stuRs.xh} </p></td>
				    <td colspan="3"><p align="center">姓名 </p></td>
				    <td colspan="5"><p align="center">${stuRs.xm} </p></td>
				    <td colspan="2" rowspan="7">
					<p align="center"><img border="0" style="height:133px;width:100px;"
													src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"> </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">性别 </p></td>
				    <td colspan="3"><p align="center">${stuRs.xb} </p></td>
				    <td colspan="2"><p align="center">民族 </p></td>
				    <td ><p align="center">${stuRs.mzmc}</p></td>
				    <td colspan="3"><p align="center">身份证号码 </p></td>
				    <td colspan="5"><p align="center">${stuRs.sfzh}</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">出生年月 </p></td>
				    <td colspan="3"><p align="center">${stuRs.csrq} </p></td>
				    <td colspan="2"><p align="center">曾用名 </p></td>
				    <td ><p align="center">${stuRs.cym} </p></td>
				    <td colspan="3"><p align="center">籍贯 </p></td>
				    <td colspan="5"><p align="center">${stuRs.jg} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">出生地 </p></td>
				    <td colspan="3"><p align="center">${rs.csd} </p></td>
				    <td colspan="2"><p align="center">血型 </p></td>
				    <td><p align="center">${rs.xuex} </p></td>
				    <td colspan="3"><p align="center">身高 </p></td>
				    <td colspan="5"><p align="center">${stuRs.sg} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2" rowspan="2"><p align="center">政治面貌 </p></td>
				    <td>团员</td>
				    <td colspan="2">
					<p>
					<logic:equal value="是" name="rs" property="sfsty">
					是<input type="checkbox" value="是" checked="checked">
					否<input type="checkbox" value="否">
				    </logic:equal>
				    <logic:equal value="否" name="rs" property="sfsty">
					是<input type="checkbox" value="是">
					否<input type="checkbox" value="否" checked="checked">
				    </logic:equal>
					<logic:empty name="rs" property="sfsty">
				            是 □ 否□ 
                   </logic:empty> </p></td>
				    <td colspan="2"><p align="center">加入时间 </p></td>
				    <td><p align="center">${rs.jrgqtsj} </p></td>
				    <td colspan="3" rowspan="2"><p align="center">健康状况 </p></td>
				    <td colspan="4"><p>入学前 </p></td>
				    <td><p align="center">${rs.rxqjkzk} </p></td>
				  </tr>
				  <tr>
				    <td colspan="5"><p align="center">推优及转预时间 </p></td>
				    <td ><p align="center">${rs.tytysj} ${rs.zybdysj} </p></td>
				    <td colspan="4"><p>入学后 </p></td>
				    <td><p align="center">${rs.rxhjkzk } </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">手机号码 </p></td>
				    <td colspan="3"><p align="center">${stuRs.sjhm}</p></td>
				    <td colspan="2"><p align="center">以往病史 </p></td>
				    <td colspan="9"><p align="center">${rs.ywbs} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">手机短号 </p></td>
				    <td colspan="3"><p align="center">${rs.sjdh} </p></td>
				    <td colspan="2"><p align="center">QQ 号码 </p></td>
				    <td ><p align="center">${rs.qqhm} </p></td>
				    <td colspan="4"><p align="center">宗教信仰 </p></td>
				    <td colspan="4"><p align="center">${rs.zjxy}</p></td>
				    <td><p>e-mail </p></td>
				    <td><p>${stuRs.dzyx} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">家庭地址 </p></td>
				    <td colspan="6"><p align="center">${jtxxRs.jtdz} </p></td>
				    <td colspan="4"><p align="center">学生个人建行卡号 </p></td>
				    <td colspan="6"><p align="center">${stuRs.yhkh}</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">通讯地址 </p></td>
				    <td colspan="6"><p align="center">${rs.txdz} </p></td>
				    <td colspan="4"><p align="center">家庭电话及邮政编码 </p></td>
				    <td colspan="6"><p align="center">${jtxxRs.lxdh1};${jtxxRs.jtyb} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">寝室号 </p></td>
				    <td colspan="6"><p align="center">${stuRs.ssbh} </p></td>
				    <td colspan="4"><p align="center">寝室电话 </p></td>
				    <td colspan="6"><p align="center">${stuRs.qsdh} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">实习医院 </p></td>
				    <td colspan="6"><p align="center">${rs.sxyy} </p></td>
				    <td colspan="4"><p align="center">实习住宿地址 </p></td>
				    <td colspan="6"><p align="center">${rs.sxzsdz} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">特长 </p></td>
				    <td colspan="16"><p align="center">${stuRs.tc} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">专业技能证书 </p></td>
				    <td colspan="7"><p align="center">${rs.zyjnzs}</p></td>
				    <td colspan="2"><p align="center">户口迁入 </p></td>
				    <td colspan="7">
					<p>
					<logic:equal value="是" name="rs" property="hksfqr">
					是<input type="checkbox" value="是" checked="checked">
					否<input type="checkbox" value="否">
				    </logic:equal>
				    <logic:equal value="否" name="rs" property="hksfqr">
					是<input type="checkbox" value="是">
					否<input type="checkbox" value="否" checked="checked">
				    </logic:equal>
					<logic:empty name="rs" property="hksfqr">
				            是 □ 否□ 
                   </logic:empty>
                   </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">奖惩情况 </p></td>
				    <td colspan="16"><p align="center">${rs.jcqk} </p></td>
				  </tr>
				  <tr>
				    <td rowspan="6"><p align="center">家<br>庭<br>主<br>要<br>成<br>员 </p></td>
				    <td colspan="3"><p align="center">姓名 </p></td>
				    <td colspan="2"><p align="center">称呼 </p></td>
				    <td colspan="8"><p align="center">工作单位 </p></td>
				    <td colspan="4"><p align="center">联系电话 </p></td>
				  </tr>
				  <tr height="30px">
				    <td colspan="3"><p align="center">${jtxxRs.jtcy1_xm}</p></td>
				    <td colspan="2"><p align="center">${jtxxRs.jtcy1_gx}</p></td>
				    <td colspan="8"><p align="center">${jtxxRs.jtcy1_gzdz} </p></td>
				    <td colspan="4"><p align="center">${jtxxRs.jtcy1_lxdh1}</p></td>
				  </tr>
				  <tr height="30px">
				    <td colspan="3"><p align="center">${jtxxRs.jtcy2_xm}</p></td>
				    <td colspan="2"><p align="center">${jtxxRs.jtcy2_gx}</p></td>
				    <td colspan="8"><p align="center">${jtxxRs.jtcy2_gzdz}</p></td>
				    <td colspan="4"><p align="center">${jtxxRs.jtcy2_lxdh1}</p></td>
				  </tr>
				  <tr height="30px">
				    <td colspan="3"><p align="center">${jtxxRs.jtcy3_xm}</p></td>
				    <td colspan="2"><p align="center">${jtxxRs.jtcy3_gx}</p></td>
				    <td colspan="8"><p align="center">${jtxxRs.jtcy3_gzdz}</p></td>
				    <td colspan="4"><p align="center">${jtxxRs.jtcy3_lxdh1}</p></td>
				  </tr>	
				  <tr height="30px">
				    <td colspan="3"><p align="center"></p></td>
				    <td colspan="2"><p align="center"></p></td>
				    <td colspan="8"><p align="center"></p></td>
				    <td colspan="4"><p align="center"></p></td>
				  </tr>	
				   <tr height="30px">
				    <td colspan="3"><p align="center"></p></td>
				    <td colspan="2"><p align="center"></p></td>
				    <td colspan="8"><p align="center"></p></td>
				    <td colspan="4"><p align="center"></p></td>
				  </tr>				  
				  <tr>
				    <td rowspan="${xxjlSize+1}"><p align="center">主<br>要<br>学<br>习<br>经<br>历</p></td>
				    <td colspan="3"><p align="center">开始时间 </p></td>
				    <td colspan="2"><p align="center">结束时间 </p></td>
				    <td colspan="3"><p align="center">所在学校 </p></td>
				    <td colspan="5"><p align="center">担任职务 </p></td>
				    <td colspan="4"><p align="center">证明人 </p></td>
				  </tr>
				<logic:iterate id="v" name="xxjl">
				  <tr>
				    <td colspan="3"><p align="center">${v.kssj} </p></td>
				    <td colspan="2"><p align="center">${v.jssj} </p></td>
				    <td colspan="3"><p align="center">${v.szdw} </p></td>
				    <td colspan="5"><p align="center">${v.drzw}</p></td>
				    <td colspan="4"><p align="center">${v.zmr}</p></td>
				  </tr>
				</logic:iterate>
				</table>
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自我介绍 </p>
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.zwjs} </p>
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申明：以上所有信息真实有效，特此申明！ </p>
				<p align="right">本人签名：<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></p>
				<p align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
							</html:form>
				</center>
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
