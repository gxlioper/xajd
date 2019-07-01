<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
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
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center" align="center">
					<h2>
						宁波城市职业技术学院国家助学贷款申请表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center" align="center">
					<table  width="100%" class="tbstyle">
					  <tr>
					  	<td align="center" width="5%"></td>
					  	<td align="center" width="10%"></td>
					  	<td align="center" width="6%"></td>
					  	<td align="center" width="12%"></td>
					  	<td align="center" width="6%"></td>
					  	<td align="center" width="7%"></td>
					  	<td align="center" width="12%"></td>
					  	<td align="center" width="6%"></td>
					  	<td align="center" width="7%"></td>
					  	<td align="center" width="12%"></td>
					  	<td align="center" width="5%"></td>
					  	<td align="center" width="12%"></td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="3">姓 名 </td>
					    <td align="center">${rs.xm }</td>
					    <td align="center">性别 </td>
					    <td align="center">${rs.xb } </td>
					    <td align="center">学 院 </td>
					    <td align="center" colspan="2">${rs.xymc } </td>
					    <td align="center">专业年级 </td>
					    <td align="center" colspan="2"> ${rs.zymc }&nbsp;&nbsp;&nbsp;${rs.nj }</td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="3">学 号 </td>
					    <td align="center">${rs.xh }</td>
					    <td align="center">学制 </td>
					    <td align="center">${rs.xz } </td>
					    <td align="center">毕业时间 </td>
					    <td align="center" colspan="2">${rs.bysj }</td>
					    <td align="center">寝室电话 </td>
					    <td align="center" colspan="2">${rs.qsdh } </td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="3">
					    	家庭详细 <br/>
					        地址及邮编  
					    </td>
					    <td align="center" colspan="6">${rs.jtdz }&nbsp;&nbsp;${rs.jtyb }  </td>
					    <td align="center">
					    		家庭<br/>电话  
					     </td>
					    <td align="center" colspan="2">  
					    	${rs.jtdh }
					    </td>
					  </tr>
					  <tr height="40px">
					    <td colspan="6"> 
					    	就读期间学习情况总体评价：<br/>
					         <logic:equal value="优" property="xxqkpj" name="rs">
					    		优√ &nbsp;&nbsp;&nbsp;&nbsp;良&nbsp;&nbsp;&nbsp;&nbsp; 一般 &nbsp;&nbsp;&nbsp;&nbsp; 差 &nbsp;&nbsp;&nbsp;&nbsp; 
					    	</logic:equal>
					    	<logic:equal value="良" property="xxqkpj" name="rs">
					    		优&nbsp;&nbsp;&nbsp;&nbsp;良√ &nbsp;&nbsp; 一般 &nbsp;&nbsp;&nbsp;&nbsp; 差 &nbsp;&nbsp;&nbsp;&nbsp;
					    	</logic:equal>
					    	<logic:equal value="一般" property="xxqkpj" name="rs">
					    		优&nbsp;&nbsp;&nbsp;&nbsp;良 &nbsp;&nbsp;&nbsp;&nbsp; 一般 √&nbsp;&nbsp; 差 &nbsp;&nbsp;&nbsp;&nbsp;
					    	</logic:equal>
					    	<logic:equal value="差" property="xxqkpj" name="rs">
					    		优&nbsp;&nbsp;&nbsp;&nbsp;良 &nbsp;&nbsp;&nbsp;&nbsp; 一般 &nbsp;&nbsp;&nbsp;&nbsp; 差 √&nbsp;&nbsp;
					    	</logic:equal>
					    	<logic:equal value="" property="xxqkpj" name="rs">
					    		优&nbsp;&nbsp;&nbsp;&nbsp;良 &nbsp;&nbsp;&nbsp;&nbsp; 一般 &nbsp;&nbsp;&nbsp;&nbsp; 差 &nbsp;&nbsp;&nbsp;&nbsp;
					    	</logic:equal>
					        
					    </td>
					    <td align="center">就读期间累计不及格必修课目数 </td>
					    <td align="center" colspan="2">${rs.bjgkm }</td>
					    <td align="center" colspan="2">有无违纪处分 </td>
					    <td align="center">${rs.sfwj } </td>
					  </tr>
					  
					  <tr height="40px">
					    <td colspan="6">上学年德育考核成绩：${rs.dycj } </td>
					    <td colspan="2">有无不良信用记录 </td>
					    <td align="center" colspan="4">
					    	<logic:equal value="有" property="sfblxy" name="rs">
					    	有√  无□ 
					    	</logic:equal>
					    	<logic:equal value="无" property="sfblxy" name="rs">
					    	有□ 无√  
					    	</logic:equal>
					    	<logic:equal value="" property="sfblxy" name="rs">
					    	有□ 无□ 
					    	</logic:equal>
					    </td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="2">在校期间预计申请贷款总额 </td>
					    <td align="center" colspan="3">${rs.zje } </td>
					    <td align="center" colspan="2">贷款人身份证号码 </td>
					    <td align="center" colspan="5">${rs.sfzh } </td>
					  </tr>
					  <tr>
					    <td align="center">
					    	申<br/><br/>请<br/><br/>理<br/><br/>由
					    </td>
					    <td colspan="11" >
					    	<p style="height:120px">
					    		&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					    	</p>
					    </td>
					  </tr>
					  <tr>
					    <td colspan="5">
					    	<p style="height:140px">
					    		<bean:message key="lable.xb" />审核意见：${rs.xyshyj }
					    	</p>
					    	<div align="center">
					    		盖章
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
					    		 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	</div>
					    </td>
					    <td colspan="3" >
					    	<p style="height:140px">
					    		学生处意见：${rs.xxshyj }
					    	</p>
					    	<div align="center">
					    		盖章
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
					    		 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	</div>
					    </td>
					    <td colspan="4" >
					    	<p style="height:140px">
					    		银行审批意见：
					    	</p>
					    	<div align="center">
					    		盖章
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
					    		 年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	</div>
					    </td>
					  </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>申请材料：</strong> 1)历年学习成绩总表（<bean:message key="lable.xb" />盖章）或新生录取通知单复印件； 2)《宁<br/>
					波城市学院贫困生家庭经济状况调查表》（乡镇街道以上盖章证明（复印件））；3)家长签<br/>
					名同意贷款并按期归还本息的告家长书回执单； 4)家庭或保证可联系到的固定电话近<br/>
					期话费单原件；5)家长身份证复印件及本人身份证、学生证复印件 (<strong>每位学生的申请</strong><br/>
					<strong>材料务必按所标顺序用A4纸装订</strong>。)
				</td>
			</tr>
		</table>
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
