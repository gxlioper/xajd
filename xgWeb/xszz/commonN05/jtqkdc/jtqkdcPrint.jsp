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
			<html:form action="/jtqkdc" method="post">
				<p align="left" style=""><strong>附件1： </strong></p>
				<p align="center"><h3>高等学校学生及家庭情况调查表</h3></p>
				<p align="center"><h5>&nbsp;学校：<u>${xxmc }</u>&nbsp;&nbsp;院（系）：<u>${rs.xymc}</u>&nbsp;&nbsp;专业： <u>${rs.zymc}</u>&nbsp;&nbsp;年级：<u>${rs.nj}</u></h5></p>
				<table cellspacing="0" cellpadding="0" class="tbstyle" width="90%">
				  <tr>
				    <td width="8%" rowspan="4"><p align="center"><strong>学生本人基本情况 </strong></p></td>
				    <td colspan="2" width="10%"><p align="center">姓 名 </p></td>
				    <td colspan="5" width="12%"><p align="center">${rs.xm}</p></td>
				    <td width="10%"><p align="center">性 别 </p></td>
				    <td colspan="2" width="10%"><p align="center">${rs.xb} </p></td>
				    <td colspan="3" width="12%"><p align="center">出生年月 </p></td>
				    <td colspan="3" width="18%"><p align="center">${rs.csrq} </p></td>
				    <td colspan="2" width="10%"><p align="center">民 族 </p></td>
				    <td width="10%"><p align="center">${rs.mzmc} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">身份证号码 </p></td>
				    <td colspan="6"><p align="center">${rs.sfzh}</p></td>
				    <td colspan="2"><p align="center">政治面貌 </p></td>
				    <td colspan="3"><p align="center">${rs.zzmmmc}</p></td>
				    <td colspan="3"><p align="center">入学前户口 </p></td>
				    <td colspan="3"><p align="center">
				    <logic:equal value="城镇" name="rs" property="rxqhkxz">
					<input type="checkbox" value="城镇" checked="checked">城镇
					<input type="checkbox" value="农村">农村
				    </logic:equal>
				    <logic:equal value="农村" name="rs" property="rxqhkxz">
					<input type="checkbox" value="城镇">城镇
					<input type="checkbox" value="农村" checked="checked">农村
				    </logic:equal>
					<logic:empty name="rs" property="rxqhkxz">
				            □城镇&nbsp;&nbsp;□农村 
                   </logic:empty>
					</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">家庭人口数 </p></td>
				    <td colspan="6"><p align="center">${rs.jtrks} </p></td>
				    <td colspan="2"><p align="center">毕业学校 </p></td>
				    <td colspan="3"><p align="center">${rs.byxx} </p></td>
				    <td colspan="3"><p align="center">个人特长 </p></td>
				    <td colspan="3"><p align="center">${rs.tc}</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">孤 残 </p></td>
				    <td colspan="3" nowrap="nowrap"><p align="center">
				    <logic:equal value="是" name="rs" property="sfgc">
					<input type="checkbox" value="是" checked="checked">是
					<input type="checkbox" value="否">否
				    </logic:equal>
				    <logic:equal value="否" name="rs" property="sfgc">
					<input type="checkbox" value="是">是
					<input type="checkbox" value="否" checked="checked">否
				    </logic:equal>
					<logic:empty name="rs" property="sfgc">
				            □是&nbsp;&nbsp; □否 
                   </logic:empty>
					</p></td>
				    <td colspan="3"><p align="center">单 亲 </p></td>
				    <td colspan="5" nowrap="nowrap"><p align="center"> 
				    <logic:equal value="是" name="rs" property="sfdq">
					<input type="checkbox" value="是" checked="checked">是
					<input type="checkbox" value="否">否
				    </logic:equal>
				    <logic:equal value="否" name="rs" property="sfdq">
					<input type="checkbox" value="是">是
					<input type="checkbox" value="否" checked="checked">否
				    </logic:equal>
					<logic:empty name="rs" property="sfdq">
				             □是&nbsp;&nbsp; □否 
                   </logic:empty>
					</p></td>
				    <td colspan="3"><p align="center">烈士子女 </p></td>
				    <td colspan="3"><p align="center">
				    <logic:equal value="是" name="rs" property="sflszn">
					<input type="checkbox" value="是" checked="checked">是
					<input type="checkbox" value="否">否
				    </logic:equal>
				    <logic:equal value="否" name="rs" property="sflszn">
					<input type="checkbox" value="是">是
					<input type="checkbox" value="否" checked="checked">否
				    </logic:equal>
					<logic:empty name="rs" property="sflszn">
				             □是&nbsp;&nbsp; □否 
                   </logic:empty>
					</p></td>
				  </tr>
				  <tr>
				    <td width="43" rowspan="2"><p align="center"><strong>家庭通讯信息 </strong></p></td>
				    <td colspan="4"><p align="center">详细通讯地址 </p></td>
				    <td colspan="15"><p align="center">${rs.jtdz} </p></td>
				  </tr>
				  <tr>
				    <td colspan="4"><p align="center">邮政编码 </p></td>
				    <td colspan="4"><p align="center">${rs.jtyb} </p></td>
				    <td colspan="3"><p align="center">联系电话 </p></td>
				    <td colspan="8"><p align="center">${rs.lxdh1qh}（区号）－${rs.lxdh1} </p></td>
				  </tr>
				  <tr>
				    <td width="43" rowspan="7"><p align="center"><strong>家庭成员情况 </strong></p></td>
				    <td colspan="2"><p align="center">姓名 </p></td>
				    <td colspan="2"><p align="center">年龄</p></td>
				    <td colspan="3"><p align="center">与学生 </p>
				        <p align="center">关系 </p></td>
				    <td colspan="7"><p align="center">工作（学习）单位 </p></td>
				    <td width="98"><p align="center">职业 </p></td>
				    <td colspan="2"><p align="center">年收入（元） </p></td>
				    <td colspan="2"><p align="center">健康状况 </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">${rs.jtcy1_xm}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy1_csrq}&nbsp;&nbsp;</p></td>
				    <td colspan="3"><p align="center">${rs.jtcy1_gx}&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">${rs.jtcy1_gzdwmc}&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">${rs.jtcy1_zy}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy1_nsr}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy1_jkzk}&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">${rs.jtcy2_xm}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy2_csrq}&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">${rs.jtcy2_gx}&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">${rs.jtcy2_gzdwmc}&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">${rs.jtcy2_zy}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy2_nsr}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy2_jkzk}&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">${rs.jtcy3_xm}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy3_csrq}&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">${rs.jtcy3_gx}&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">${rs.jtcy3_gzdwmc}&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">${rs.jtcy3_zy}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy3_nsr}&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">${rs.jtcy3_jkzk}&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp; </p></td>
				    <td colspan="3"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="7"><p align="center">&nbsp;&nbsp;</p></td>
				    <td width="98"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				    <td colspan="2"><p align="center">&nbsp;&nbsp;</p></td>
				  </tr>				  
				  <tr>
				    <td width="43"><p align="center"><strong>影响家庭经济 </strong></p>
				        <p align="center"><strong>状况有关信息 </strong></p></td>
				    <td colspan="19" valign="top">
						<p style="width:100%">家庭人均年收入 <u>&nbsp;&nbsp;${rs.jtrjnsr}&nbsp;&nbsp;&nbsp;&nbsp;</u>（元）。学生本学年已获资助情况<u>&nbsp;&nbsp;${rs.yhzzqk}
						<logic:empty name="rs" property="yhzzqk">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty></u> 。 </p>
				        <p>家庭遭受自然灾害情况： <u>&nbsp;&nbsp;${rs.zszrzhqk}
						<logic:empty name="rs" property="zszrzhqk">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u>。家庭遭受突发意外事件： <u>&nbsp;&nbsp;${rs.zstfywsj}
						<logic:empty name="rs" property="zstfywsj">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> 。 </p>
				        <p>家庭成员因残疾、年迈而劳动能力弱情况：<u>&nbsp;&nbsp;${rs.ldnlrqk}
						<logic:empty name="rs" property="ldnlrqk">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> 。 </p>
				        <p>家庭成员失业情况：<u>&nbsp;&nbsp;${rs.jtcysyqk}
							<logic:empty name="rs" property="jtcysyqk">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:empty>
						</u> 。家庭欠债情况：<u>&nbsp;&nbsp;${rs.jtqzqk}
						<logic:empty name="rs" property="jtqzqk">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> 。 </p>
				        <p>其他情况：<u>&nbsp;&nbsp;${rs.jtqtqk}
						<logic:empty name="rs" property="jtqtqk">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						</u> 。 </p></td>
				  </tr>
				  <tr>
				    <td width="43" ><p align="center"><strong>签章 </strong></p></td>
				    <td><p align="center">学<br/>生<br/>本<br/>人 </p></td>
				    <td colspan="6"><p align="center">&nbsp; </p></td>
				    <td colspan="2"><p align="center">学生<br/>家长<br/>或监<br/>护人 </p></td>
				    <td colspan="5"><p align="center">&nbsp; </p></td>
				    <td colspan="2"><p align="center">学生家庭<br/>所在地乡<br/>镇或街道<br/>民政部门 </p></td>
				    <td colspan="3" valign="top" >
						<br/>
				        <p>经办人签字： </p>
				        <br/>
				        <p>单位名称： </p>
				        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（加盖公章） </p>
				        <p align="right"><u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年<u>&nbsp;&nbsp;&nbsp;&nbsp;</u> 月 <u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日&nbsp;&nbsp; </p>
					</td>
				  </tr>
				  <tr>
				    <td width="43" rowspan="2"><p align="center"><strong>民政部门信息 </strong></p></td>
				    <td colspan="3"><p align="center">详细通讯地址 </p></td>
				    <td colspan="16"><p align="center">${rs.mzbtxdz} </p></td>
				  </tr>
				  <tr>
				    <td colspan="3"><p align="center">邮政编码 </p></td>
				    <td colspan="6"><p align="center">${rs.mzbyb} </p></td>
				    <td colspan="3"><p align="center">联系电话 </p></td>
				    <td colspan="7"><p align="center">${rs.mzblxdhqh}（区号）－${rs.mzblxdh}</p></td>
				  </tr>
				</table>
				<p align="left" style="width:85%"><strong>注：本表仅供参考。 </strong></p>
			<div class="noprint" align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
	
			</html:form>
        </center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
