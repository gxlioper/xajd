<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>	
</head>
	<body>
		<html:form action="/xsxx_zgkd.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>		
			<div class="tab">	
			<table class="formlist" id="rsTable">
			<thead>			
			<tr>
				<th colspan="6">
					<span>个人信息修改(原始值  - 新值)</span>
				</th>
			</tr>
			<tr>
				<td>
					字段名
				</td>
				<td>
					新值
				</td>
				<td>
					原始值
				</td>
				<td>
					字段名
				</td>
				<td>
					新值
				</td>
				<td>
					原始值
				</td>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>学号</th>
				<td>
					<bean:write name="newValue" property="xh"/>		 
					<html:hidden property="xh" name="oldValue"/>							
				</td>
				<td>
					<bean:write name="oldValue" property="xh"/>
				</td>
				<th>姓名</th>
				<td>
					<bean:write name="newValue" property="xm"/>
				</td>
				<td>
					<bean:write name="oldValue" property="xm"/>
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
				<bean:write name="newValue" property="xymc"/>
	<%--				<html:select property="xydm" name="newValue" disabled="true">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>--%>
	<%--				</html:select>--%>
				</td>
				<td>
				<bean:write name="oldValue" property="xymc"/>
	<%--				<html:select property="xydm" name="oldValue" disabled="true">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>--%>
	<%--				</html:select>--%>
				</td>
				<th>专业</th>
				<td>
				<bean:write name="newValue" property="zymc"/>
	<%--				<html:select property="zydm" name="newValue" disabled="true" styleId="zydm">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="zyList" property="zydm" labelProperty="zymc"/>--%>
	<%--				</html:select>--%>
				</td>
				<td>
				<bean:write name="oldValue" property="zymc"/>
	<%--				<html:select property="zydm" name="oldValue" disabled="true" styleId="zydm">--%>
	<%--					<html:option value=""></html:option>--%>
	<%--					<html:options collection="zyList" property="zydm" labelProperty="zymc"/>--%>
	<%--				</html:select>--%>
				</td>
			</tr>
			
			<tr>
			<th>年级</th>
			<td>
				<bean:write name="newValue" property="nj"/>
<%--				<html:select property="nj" name="newValue" disabled="true" styleId="nj">--%>
<%--					<html:option value=""></html:option>--%>
<%--					<html:options collection="njList" property="nj" labelProperty="nj"/>--%>
<%--				</html:select>--%>
			</td>			
			<td>
				<bean:write name="oldValue" property="nj"/>
			
<%--				<html:select property="nj" name="oldValue" disabled="true" styleId="nj">--%>
<%--					<html:option value=""></html:option>--%>
<%--					<html:options collection="njList" property="nj" labelProperty="nj"/>--%>
<%--				</html:select>--%>
			</td>
			<th>班级</th>
			<td>
			<bean:write name="newValue" property="bjmc"/>
<%--				<html:select property="bjdm" name="newValue" disabled="true" styleId="bjdm">--%>
<%--					<html:option value=""></html:option>--%>
<%--					<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>--%>
<%--				</html:select>--%>
			</td>
			<td>
				<bean:write name="oldValue" property="bjmc"/>
			</td>
			</tr>
			
			<tr>
			<th>性别</th>
			<td>
				<bean:write name="newValue" property="xb"/>
			</td>
			<td>
				<bean:write name="oldValue" property="xb"/>
			</td>
			<th>学制</th>
			<td>
				<bean:write name="newValue" property="xz"/>
			</td>
			<td>
				<bean:write name="oldValue" property="xz"/>
			</td>
			</tr>
			
			<tr>
			<th>民族</th>
			<td>
				<html:select property="mz" name="newValue" disabled="true" styleId="mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="mz" name="oldValue" disabled="true" styleId="mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>政治面貌</th>
			<td>
				<html:select property="zzmm" name="newValue" disabled="true" styleId="zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="zzmm" name="oldValue" disabled="true" styleId="zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>学籍状态</th>
			<td>
				<bean:write name="newValue" property="xjztm"/>
			</td> 
			<td>
				<bean:write name="oldValue" property="xjztm"/>
			</td>
			<th>出生日期</th>
			<td>
				<bean:write name="newValue" property="csrq"/>
			</td>
			<td>
				<bean:write name="oldValue" property="csrq"/>
			</td>
			</tr>
			
			<tr>
			<th>姓名拼音</th>
			<td>
				<bean:write name="newValue" property="xmpy"/>				
			</td>			
			<td>
				<bean:write name="oldValue" property="xmpy"/>
			</td>
			<th>曾用名</th>
			<td>
				<bean:write name="newValue" property="cym"/>
			</td>
			<td>
				<bean:write name="oldValue" property="cym"/>
			</td>
			</tr>
			
			<tr>
			<th>身高</th>
			<td>
				<bean:write name="newValue" property="sg"/>
			</td>
			<td>
				<bean:write name="oldValue" property="sg"/>
			</td>
			<th>体重</th>
			<td>
				<bean:write name="newValue" property="tz"/>
			</td>
			<td>
				<bean:write name="oldValue" property="tz"/>
			</td>
			</tr>
			
			<tr>
			<th>身份证号</th>
			<td>
				<bean:write name="newValue" property="sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="sfzh"/>
			</td>
			<th>特长</th>
			<td>
				<bean:write name="newValue" property="tc"/>
			</td>
			<td>
				<bean:write name="oldValue" property="tc"/>
			</td>
			</tr>
			
			<tr>
			<th>
				培养方式
			</th>
			<td>
				<bean:write name="newValue" property="pyfs"/>
			</td>
			<td>
				<bean:write name="oldValue" property="pyfs"/>
			</td>
			<th>
				培养层次
			</th>
			<td>
				<bean:write name="newValue" property="pycc"/>
			</td>
			<td>
				<bean:write name="oldValue" property="pycc"/>
			</td>
			</tr>
			
			<tr>
			<th>
				入学方式
			</th>
			<td>
				<bean:write name="newValue" property="rxfs"/>
			</td>
			<td>
				<bean:write name="oldValue" property="rxfs"/>
			</td>
			<th>
				考生类别
			</th>
			<td>
				<bean:write name="newValue" property="kslb"/>
			</td>
			<td>
				<bean:write name="oldValue" property="kslb"/>
			</td>
			</tr>
			
			<tr>
			<th>
				来源地区
			</th>
			<td>
				<bean:write name="newValue" property="syd"/>
			</td>
			<td>
				<bean:write name="oldValue" property="syd"/>
			</td>
			<th>
				籍贯
			</th>			
			<td>
				<bean:write name="newValue" property="jg"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jg"/>
			</td>
			</tr>
			<tr>
			<th>
				宿舍编号
			</th>
			<td>
				<bean:write name="newValue" property="ssbh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="ssbh"/>
			</td>
			<th>		
			    寝室电话	
			</th>
			<td>	
				<bean:write name="newValue" property="qsdh"/>			
			</td>
			<td>	
				<bean:write name="oldValue" property="qsdh"/>			
			</td>
			</tr>
			<tr>
			<th>
				电子邮箱
			</th>
			<td>
				<bean:write name="newValue" property="dzyx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="dzyx"/>
			</td>
			<th>
				联系电话
			</th>
			<td>
				<bean:write name="newValue" property="lxdh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="lxdh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				手机号码
			</th>			
			<td>
				<bean:write name="newValue" property="sjhm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="sjhm"/>
			</td>
			<th>
			</th>
			<td>
			</td>
			<td>
			</td>
			</tr>
			<%@ include file="/xsxx/bjqnzzxy/xsxx_audit_bjqnzzxy.jsp"%>			
			<tr>
			<th>
				家庭邮编
			</th>
			<td>
				<bean:write name="newValue" property="yb"/>
			</td>
			<td>
				<bean:write name="oldValue" property="yb"/>
			</td>
			<th>
				家庭经济情况
			</th>
			<td>
				<bean:write name="newValue" property="jjzk"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jjzk"/>
			</td>
			</tr>
			<tr>
				<th>
				家庭地址
				</th>
				<td>
					<bean:write name="newValue" property="jtszd"/>
				</td>
				<td>
					<bean:write name="oldValue" property="jtszd"/>
				</td>
				<th>
				</th>
				<td>
				</td>
				<td>
				</td>
			</tr>
			</tbody>
			<thead>
				<tr title="点击展开或收缩">
					<td colspan="6" style="cursor:hand" align="center"
						onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
							<span>学生家庭成员信息</span>
					</td>
				</tr>				
			</thead>
			<tbody>
			<tr id="jt1" style="display: none">
			<td colspan="6">					
			<table width="100%" class="tbstyle">
			<thead>
			<tr>
			<td>
			字段名
			</td>
			<td>
			新值
			</td>
			<td>
			原始值
			</td>
			<td>
			字段名
			</td>
			<td>
			新值
			</td>
			<td>
			原始值
			</td>
			</tr>
			</thead>
			<tr>
			<th>
				家庭成员1姓名
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_xm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_xm"/>
			</td>
			<th>
				本人与家庭成员1关系
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_gx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_gx"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员1出生日期
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_nl"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_nl"/>
			</td>
			<th>
				家庭成员1身份证号
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_sfzh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员1民族
			</th>
			<td>
				<html:select property="jtcy1_mz" name="newValue" disabled="true" styleId="jtcy1_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy1_mz" name="oldValue" disabled="true" styleId="jtcy1_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>
				家庭成员1政治面貌
			</th>
			<td>
				<html:select property="jtcy1_zzmm" name="newValue" disabled="true" styleId="jtcy1_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy1_zzmm" name="oldValue" disabled="true" styleId="jtcy1_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员1职业
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_zy"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_zy"/>
			</td>
			<th>
				家庭成员1职务
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_zw"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_zw"/>
			</td>
			</tr>
				
			<tr>
			<th>
				家庭成员1工作单位电话
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_lxdh1"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_lxdh1"/>
			</td>
			<th>
				家庭成员1个人电话
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_lxdh2"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy1_lxdh2"/>
			</td>			
			</tr>
			
			<tr>
			<th>
				家庭成员1工作地址
			</th>
			<td>
				<bean:write name="newValue" property="jtcy1_gzdz"/>
			</td>	
			<td>
				<bean:write name="oldValue" property="jtcy1_gzdz"/>
			</td>	
			<td>		
			</td>	
			<td>		
			</td>	
			<td>		
			</td>
			</tr>	
					
			<tr>
			<th>
				家庭成员2姓名
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_xm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_xm"/>
			</td>
			<th>
				本人与家庭成员2关系
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_gx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_gx"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员2出生日期
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_nl"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_nl"/>
			</td>
			<th>
				家庭成员2身份证号
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_sfzh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员2民族
			</th>
			<td>
				<html:select property="jtcy2_mz" name="newValue" disabled="true" styleId="jtcy2_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy2_mz" name="oldValue" disabled="true" styleId="jtcy2_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>
				家庭成员2政治面貌
			</th>
			<td>
				<html:select property="jtcy2_zzmm" name="newValue" disabled="true" styleId="jtcy2_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy2_zzmm" name="oldValue" disabled="true" styleId="jtcy2_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员2职业
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_zy"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_zy"/>
			</td>
			<th>
				家庭成员2职务
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_zw"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_zw"/>
			</td>
			</tr>
				
			<tr>
			<th>
				家庭成员2工作单位电话
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_lxdh1"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_lxdh1"/>
			</td>
			<th>
				家庭成员2个人电话
			</th>
			<td>
				<bean:write name="newValue" property="jtcy2_lxdh2"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy2_lxdh2"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员2工作地址
			</th>	
			<td>
				<bean:write name="newValue" property="jtcy2_gzdz"/>
			</td>		
			<td>
				<bean:write name="oldValue" property="jtcy2_gzdz"/>
			</td>	
			<td>		
			</td>
			<td>
			</td>
			<td>
			</td>
			</tr>
										
			<tr>
			<th>
				家庭成员3姓名
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_xm"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_xm"/>
			</td>
			<th>
				本人与家庭成员3关系
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_gx"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_gx"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员3出生日期
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_nl"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_nl"/>
			</td>
			<th>
				家庭成员3身份证号
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_sfzh"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_sfzh"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员3民族
			</th>
			<td>
				<html:select property="jtcy3_mz" name="newValue" disabled="true" styleId="jtcy3_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy3_mz" name="oldValue" disabled="true" styleId="jtcy3_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<th>
				家庭成员3政治面貌
			</th>
			<td>
				<html:select property="jtcy3_zzmm" name="newValue" disabled="true" styleId="jtcy3_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			<td>
				<html:select property="jtcy3_zzmm" name="oldValue" disabled="true" styleId="jtcy3_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员3职业
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_zy"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_zy"/>
			</td>
			<th>
				家庭成员3职务
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_zw"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_zw"/>
			</td>			
			</tr>
				
			<tr>
			<th>
			    家庭成员3工作单位电话
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_lxdh1"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_lxdh1"/>
			</td>
			<th>
				家庭成员3个人电话
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_lxdh2"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_lxdh2"/>
			</td>
			</tr>
			
			<tr>
			<th>
				家庭成员3工作地址
			</th>
			<td>
				<bean:write name="newValue" property="jtcy3_gzdz"/>
			</td>
			<td>
				<bean:write name="oldValue" property="jtcy3_gzdz"/>
			</td>	
			<td>		
			</td>
			<td>
			</td>
			<td>
			</td>
			</tr>	
			</table>
			</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="6">
		          <div class="btn">
		            <logic:equal value="yes" name="writeAble">
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=通过')">
						通 过 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="refreshForm('xsxx_zgkd.do?method=saveAutidting&yesNo=不通过')">
						不通过 
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button"						
						onclick="Close();return false;">
						关 闭 
					</button>
					</logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
			</table>
		</div>	
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");		
					Close();		
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
