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
				<td align="center">
					<h2>
						${xxmc }学生学费减免申请表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
					  <tr>
					  	<td width="6%"></td>
					  	<td width="10%"></td>
					  	<td width="1%"></td>
					  	<td width="7%"></td>
					  	<td width="5%"></td>
					  	<td width="5%"></td>
					  	<td width="11%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="10%"></td>
					  </tr>
					  <tr height="50px">
					    <td align="center" colspan="2">姓 名 </td>
					    <td align="center" colspan="2">${rs.xm } </td>
					    <td align="center" colspan="2">性别 </td>
					    <td align="center">${rs.xb } </td>
					    <td align="center" colspan="2">学 院 </td>
					    <td align="center" colspan="2">${rs.xymc } </td>
					    <td align="center" colspan="2">专业年级 </td>
					    <td align="center">${rs.zymc }&nbsp;&nbsp;${rs.nj } </td>
					  </tr>
					  <tr height="50px">
					    <td align="center" colspan="2">学 号 </td>
					    <td align="center" colspan="2">${rs.xh } </td>
					    <td align="center" colspan="2">学制 </td>
					    <td align="center">${rs.xz } </td>
					    <td align="center" colspan="2">毕业时间 </td>
					    <td align="center" colspan="2">${rs.bysj } </td>
					    <td align="center" colspan="2">联系电话 </td>
					    <td align="center">${rs.lxdh } </td>
					  </tr>
					  <tr height="60px">
					    <td colspan="5">
					    	学习情况总体评价： <br/>
					    	<logic:equal value="优" property="xxqkpj" name="rs">
					    		优√ 良 □ <br/>
					        	一般 □ 差 □ 
					    	</logic:equal>
					    	<logic:equal value="良" property="xxqkpj" name="rs">
					    		优□ 良 √ <br/>
					        	一般 □ 差 □ 
					    	</logic:equal>
					    	<logic:equal value="一般" property="xxqkpj" name="rs">
					    		优□ 良 □ <br/>
					        	一般 √ 差 □ 
					    	</logic:equal>
					    	<logic:equal value="差" property="xxqkpj" name="rs">
					    		优□ 良 □ <br/>
					        	一般 □ 差 √ 
					    	</logic:equal>
					    	<logic:equal value="" property="xxqkpj" name="rs">
					    		优□ 良 □ <br/>
					        	一般 □ 差 □ 
					    	</logic:equal>
					        
					    </td>
					    <td colspan="4" align="center">
					    	就读期间累计不及格<br/>必修课门数 
					    </td>
					    <td colspan="5">${rs.bjgkm } </td>
					  </tr>
					  <tr height="50px">
					    <td width="104" colspan="3" align="center">思想品德现实表现 </td>
					    <td width="95" colspan="2" align="center">${rs.sxpdbx } </td>
					    <td width="120" colspan="3" align="center">
					    	奖惩<br/> 
					        情况 
					     </td>
					    <td colspan="6" align="center">
					    	&nbsp;&nbsp;&nbsp;&nbsp;${rs.jcqk }
					    </td>
					  </tr>
					  <tr height="50px">
					    <td colspan="8" align="center">
					    	在校期间受过何种经济补助（包括临时困难<br/>
					    	补助、政府奖学金、社会资助等） 
					    </td>
					    <td width="300" colspan="6" align="center">
					    	&nbsp;&nbsp;&nbsp;&nbsp;${rs.sghzbz }
					    </td>
					  </tr>
					  <tr height="50px">
					    <td align="center" colspan="2">
					    	本学年缴费情况 
					    </td>
					    <td width="120" colspan="4" align="center">
					    	<logic:equal value="是" property="sfjf" name="rs">
					    		学费已缴√ <br/>
					        	学费未缴□ 
					    	</logic:equal>
					    	<logic:equal value="否" property="sfjf" name="rs">
					    		学费已缴□ <br/>
					        	学费未缴√ 
					    	</logic:equal>
					    	<logic:equal value="" property="sfjf" name="rs">
					    		学费已缴□ <br/>
					        	学费未缴□ 
					    	</logic:equal>
					    	
					    </td>
					    <td colspan="2"align="center">
					    	本学年是否申请贷款
					    </td>
					    <td align="center" colspan="2">
					    	<logic:equal value="是" property="sfdk" name="rs">
					    		是 √ <br/>
					        	否 □
					    	</logic:equal>
					    	<logic:equal value="否" property="sfdk" name="rs">
					    		是 □ <br/>
					        	否 √
					    	</logic:equal>
					    	<logic:equal value="" property="sfdk" name="rs">
					    		是 □ <br/>
					        	否 □
					    	</logic:equal>
					    	 
					    </td>
					    <td colspan="2" align="center">
					    	申请学费减免类别
					    </td>
					    <td colspan="2" align="center">
					    	<logic:equal value="全免" property="sqlb" name="rs">
					    		全免 √ <br/>
					        	部分免 □
					    	</logic:equal>
					    	<logic:equal value="部分免" property="sqlb" name="rs">
					    		全免 □ <br/>
					        	部分免 √
					    	</logic:equal>
					    	<logic:equal value="" property="sqlb" name="rs">
					    		全免 □ <br/>
					        	部分免 □
					    	</logic:equal>
					    </td>
					  </tr>
					  <tr>
					    <td align="center">&nbsp; 
					     	申<br/><br/>请<br/><br/>理<br/><br/>由
					     </td>
					    <td colspan="13" valign="top">
					    	家庭经济困难简要情况 &nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					     </td>
					  </tr>
					  <tr>
					    <td colspan="7" valign="top">班级意见 
					        <p style="height:120px">
					        	&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjshyj }
					        </p>
					        <div align="right">
					        班主任签名 
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        <br/>
					        年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					        </div>
					    </td>
					    <td colspan="7" valign="top"><bean:message key="lable.xb" />意见 
					    	 <p style="height:120px">
					    	 	&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					    	 </p>
					   		<div align="right">
					        盖章
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        <br/>
					        年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        月 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					    </td>
					  </tr>
					</table>
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
