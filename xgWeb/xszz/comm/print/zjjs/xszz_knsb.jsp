<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<html:html>
<head>
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
<body>
	<br/><br /><br />
	<p align=center>
		<b><span
			style='font-size:18.0pt;letter-spacing:1.0pt'>高等学校家庭经济困难学生认定申请表</span>
		</b>
	</p>
	<br />
	<br />
	<p style='font-size:16px'><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学校：<U>&nbsp;&nbsp;${xxmc}&nbsp;&nbsp;</U></B></p>
	<table class="printtab"
		style="font-family:仿宋_GB2312;font-size:15px;width:100%" >
		<!-- 学生本人基本情况 -->
		<!-- 第一行 -->
		<tr style="height:45px">
			<td width="7%" rowspan="4" align="center">
					<B>
					学<br>
					生<br>
					本<br>
					人<br>
					基<br>
					本<br>
					情<br>
					况
					</B>
			</td>
			<td colspan="2" width="14%"  align="center" style="vertical-align: center">
				姓 名
			</td>
			<td colspan="2" width="14%"  align="center" style="vertical-align: center">
				${rs.xm }
			</td>
			<td colspan="2" width="11%"  align="center" style="vertical-align: center">
				性别
			</td>
			<td  width="8%"  align="center" style="vertical-align: center">
				${rs.xb }
			</td>
			<td colspan="2" width="16%"  align="center" style="vertical-align: center">
				出生<br/>年月
			</td>
			<td colspan="2" width="12%"  align="center" style="vertical-align: center">
				${rs.csrq }
			</td>
			<td width="7%"  align="center" style="vertical-align: center">
				民族
			</td>
			<td width="7%"  align="center" style="vertical-align: center">
				${rs.mzmc }
			</td>
		</tr>
		<!-- 第二行 -->
		<tr style="height:45px">
			<td width="" colspan="2"  align="center" style="vertical-align: center">
				身份证号码
			</td>
			<td width="" colspan="4"  align="center" style="vertical-align: center">
				${rs.sfzh }
			</td>
			<td  align="center" style="vertical-align: center">
				政治<br/>面貌
			</td>
			<td colspan="2"  align="center" style="vertical-align: center">
				${rs.zzmmmc }
			</td>
			<td colspan="2" align="center" style="vertical-align: center">
				家庭人均<br/>年收入
			</td>
			<td colspan="2" align="center" style="vertical-align: center">
				${rs.jtrjsr }元
			</td>
		</tr>
		<!-- 第三行 -->
		<tr style="height:45px">
			<td colspan="2" align="center" style="vertical-align: center">
				<bean:message key="lable.xb" />
			</td>
			<td colspan="3" align="center" style="vertical-align: center">
				${xxmc }
			</td>

			<td width="" align="center" style="vertical-align: center">
				系
			</td>
			<td colspan="3" align="center" style="vertical-align: center">
				${rs.xymc }
			</td>

			<td width="9%" align="center" style="vertical-align: center">
				专业
			</td>
			<td width="21%" colspan="3" align="center" style="vertical-align: center">
				${rs.zymc }
			</td>
		</tr>
		<!-- 第四行 -->
		<tr>
			<td width="" colspan="2" align="center" style="vertical-align: center">
				年级
			</td>
			<td width=""  align="center" style="vertical-align: center">
				${rs.nj}
			</td>
			<td width="" align="center" style="vertical-align: center">
				班
			</td>
			<td width="" colspan="2" align="center" style="vertical-align: center">
				${rs.bjmc}
			</td>
			<td width="18%" colspan="2" align="center" style="vertical-align: center">
				在校联<br/>系电话
			</td>
			<td width="" colspan="5" align="center" style="vertical-align: center">
			</td>
		</tr>
		<!-- 学生陈述申请认定理由 -->
		<tr style="height:150px">
			<td width="5%" align="center" style=";vertical-align: center">
				<B>
				学<br>
				生<br>
				陈<br>
				述<br>
				申<br>
				请<br>
				认<br>
				定<br>
				理<br>
				由
				</B>
			</td>
			<td colspan="13" align="left">
				<p style="height: 120px">
					<br>
					${rs.rdly }
				</p>
				<div align="right">
					学生签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<u>&nbsp;&nbsp;&nbsp;</u> 年
					<u>&nbsp;&nbsp;&nbsp;</u> 月
					<u>&nbsp;&nbsp;&nbsp;</u> 日
				</div>
				<div align="left">
					<font style="font-size:15px">
					(注：可另附详细情况说明。)
					</font>
				</div>
			</td>
		</tr>
		<!-- 学生陈述申请认定理由 -->
		<tr height="150px">
			<td width="5%" rowspan="${fjNum }">
				<div align="center">
					<B>
					民<br>
					主<br>
					评<br>
					议
					</B>
				</div>
			</td>
			<td width="" rowspan="${fjNum }">
				<div align="center">
					推<br>
					荐<br>
					档<br>
					次
				</div>
			</td>
			<td width="10%" height="50px" colspan="4">
				<div align="center">
					<logic:iterate name="xmfjqkList" id="fj" indexId="num">
						<logic:equal name="num" value="0">
								${fj.fjmc }□
							</logic:equal>
					</logic:iterate>
				</div>
			</td>
			<td width="10%" rowspan="${fjNum }">
				<div align="center">
					陈
					<br>
					述
					<br>
					理
					<br>
					由
				</div>
			</td>
			<td width="" colspan="7" rowspan="${fjNum }">
				<p  style="height: 120px"></p>
				<div align="right">
					评议小组组长签字：&nbsp;&nbsp;
					<u>&nbsp;&nbsp;&nbsp;</u> 年
					<u>&nbsp;&nbsp;&nbsp;</u> 月
					<u>&nbsp;&nbsp;&nbsp;</u> 日
				</div>
			</td>
		</tr>
		<logic:iterate name="xmfjqkList" id="fj" indexId="num">
			<logic:notEqual name="num" value="0">
				<tr>
					<td width="10%" colspan="4"  height="50px" >
						<div align="center">
							${fj.fjmc }
							<logic:notEqual name="fj" property="fjmc" value="${rs.xmzzjb }">
									□
								</logic:notEqual>
							<logic:equal name="fj" property="fjmc" value="${rs.xmzzjb }">
								<img src="/xgxt/pictures/xszz/gou.jpg"></img>
							</logic:equal>
						</div>
					</td>
				</tr>
			</logic:notEqual>
		</logic:iterate>
		<!-- 认定决定 -->
		<tr height="150px" >
			<td width="5%" align="center" valign="middle">
					<B>
					认<br>
					定<br>
					决<br>
					定
					</B>
			</td>
			<td width="11%" align="center" valign="middle" >
					院(系)
					<br>
					意见
			</td>
			<td width="" colspan="5">
				<p style="height: 140px;" align="left">
					经评议小组推荐、本院<br/>
					(系)认真审核后，
					<br/>
					□ 同意评议小组意<br/>见。
					<br/>
					□ 不同意评议小组<br/>
					意见。调整为<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
				</p>
				<div align="left">
					工作组组长签字：&nbsp;&nbsp;&nbsp;&nbsp;
				</div><br/>
				<div align="left">
					<u>&nbsp;&nbsp;&nbsp;&nbsp;</u> 年
					<u>&nbsp;&nbsp;&nbsp;</u> 月
					<u>&nbsp;&nbsp;&nbsp;</u> 日
				</div>
				<br/><br/>
			</td>
			<td width="8%">
				<div align="center">
					学<br>
					校<br>
					学<br>
					生<br>
					资<br>
					助<br>
					管<br>
					理<br>
					机<br>
					构<br>
					意<br>
					见
				</div>
			</td>
			<td width="" colspan="6">
				<p style="height: 120px;" align="left">
					经学生所在院(系)提请，本机构认真核实，<br>
					□ 同意工作组和评议小组意见。
					<br>
					□ 不同意工作组和评议小组意见。调整为<br/>
					<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>。
				</p>
				<br/>
				<div align="left">
					负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<br/>
				<div align="left">
					<u>&nbsp;&nbsp;&nbsp;&nbsp;</u> 年
					<u>&nbsp;&nbsp;&nbsp;</u> 月
					<u>&nbsp;&nbsp;&nbsp;</u> 日
				</div><br/>
				<div align="left">
					（加盖部门公章）
				</div>
				<br/>
				<br/>
			</td>
		</tr>
	</table>
	<!-- 贫困生登记 -->
	<br /><br />
	<p align=center>
		<b><span
			style='font-size:14.0pt;letter-spacing:1.0pt'>浙江建设职业技术学院贫困生登记表</span>
		</b>
	</p>
	<br />
	<table class="printtab"
		style="font-family:仿宋_GB2312;font-size:15px;width:100%" >
		<!-- 学生本人基本情况 -->
		<!-- 第一行 -->
		<tr  style="height: 22px"> 
        <td width="5%" rowspan=6 align="center" valign="middle">
        	本<br/>
        	人<br/>
        	情<br/>
        	况</td> 
        <td width="20%" align="center" valign="middle">姓名</td> 
        <td width="15%" align="center" valign="middle">${rs.xm } </td> 
        <td width="15%" align="center" valign="middle">性别</td> 
        <td width="10%" align="center" valign="middle">${rs.xb }</td> 
        <td width="15%" align="center" valign="middle">出生年月</td> 
        <td width="15%" align="center" valign="middle">${rs.csrq }</td> 
        <td width="15%" rowspan=4 ><img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96px" height="128px"/></td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width="20%" align="center" valign="middle">民族</td> 
        <td width="15%" align="center" valign="middle">${rs.mzmc }</td> 
        <td width="10%"  align="center" valign="middle">政治面貌</td> 
        <td width="10%"  align="center" valign="middle"> ${rs.zzmmmc }</td> 
        <td width="15%" align="center" valign="middle">入学时间</td> 
        <td width="15%"  align="center" valign="middle">${rs.rxrq }</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width="20%" align="center" valign="middle"> 身份证号码</td> 
        <td width="75%" colspan=5 align="center" valign="middle" >&nbsp; ${rs.sfzh }</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width="20%" align="center" valign="middle">已获资助情况</td> 
        <td width="20%" colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width="35%" colspan=3 >
        <logic:empty name="rs" property="xymc">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </logic:empty>
        <logic:notEmpty name="rs" property="xymc">
        &nbsp;${rs.xymc}
        </logic:notEmpty>
        系
        <logic:empty name="rs" property="bjmc">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </logic:empty>
        <logic:notEmpty name="rs" property="bjmc">
        ${rs.bjmc}
        </logic:notEmpty>
        班级</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=20% align="center" valign="middle">寝室电话</td> 
        <td width=20% colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=15% align="center" valign="middle" >手机</td> 
        <td width=45% colspan=3 >&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=20%  align="center" valign="middle">任何学生干部</td> 
        <td width=20% colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=15% align="center" valign="middle">获奖情况</td> 
        <td width=45% colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr > 
        <td width=5% rowspan=4  align="center" valign="middle" style="">
        	家<br/>
        	庭<br/>
        	经<br/>
        	济<br/>
        	情<br/>
        	况</td> 
        <td width=20%  align="center" valign="middle" style="height: 22px">
       		 家庭户口
       	</td> 
        <td width=50% colspan=4  align="center" valign="middle" style="height: 22px">
        	1、城镇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2、农村
        </td> 
        <td width=15%  align="center" valign="middle" style="height: 22px">家庭人数</td> 
        <td width=15%  style="">&nbsp; </td> 
      </tr> 
      
      <tr style="height: 22px"> 
        <td width=20% style="">家庭月总收入</td> 
        <td width=15% style="">&nbsp; </td> 
        <td width=20% colspan=2 style="">人均月收入</td> 
        <td width=15% style="">&nbsp; </td> 
        <td width=15% style="">收入来源</td> 
        <td width=15% style="">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=20% rowspan=2  align="center" valign="middle" style="">家庭联系方式</td> 
        <td width=15%  align="center" valign="middle" style="">邮 编</td> 
        <td width=35% colspan=3 style=""  align="center" valign="middle">&nbsp; </td> 
        <td width=15% style="" align="center" valign="middle">家庭电话</td> 
        <td width=15% style="" align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td  align="center" valign="middle">地 址</td> 
        <td  colspan=3 align="center" valign="middle">&nbsp; </td> 
        <td align="center" valign="middle">手 机</td> 
        <td  align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td  rowspan=5 align="center" valign="middle">家庭成员</td> 
        <td  align="center" valign="middle">姓 名</td> 
        <td  align="center" valign="middle">年 龄</td> 
        <td  colspan=2 align="center" valign="middle">与本人关系</td> 
        <td  colspan=3 align="center" valign="middle">工作单位、做何工作</td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr style="height: 22px"> 
        <td width=108 align="center" valign="middle">&nbsp; </td> 
        <td width=72 align="center" valign="middle">&nbsp; </td> 
        <td width=144 colspan=2 align="center" valign="middle">&nbsp; </td> 
        <td width=261 colspan=3 align="center" valign="middle">&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=620 colspan=8 height="90px" >
        <p style="height:80px" >经济困难情况(可附页，确保情况属实，一旦查出虚报现象，给予记过处分)：</p>
  <p  align="right">本人签名：&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      </tr> 
      <tr> 
        <td width=620 colspan=8  height="90px" >
        <p style="height:80px">系审核意见：</p>
        <p align="right">负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      </tr> 
      <tr> 
        <td width=620 colspan=8 height="100px" >
        <p style="height:90px">学生资助部门意见：</p>
        <p align="right">
        负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年
        &nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
      </tr> 
	</table>
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
