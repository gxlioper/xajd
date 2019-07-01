<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			 refreshForm('/xgxt/zgdzdx_xszz.do?method=jtqkdcshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>
				困难生 - 审核 - 家庭情况调查审核 - 单个审核
				</a>
				</p>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="12" align="center">
							家庭情况调查
						</td>
					</tr>
				</thead>

				<tr>
					<th  colspan="3">
						学号
					</th>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<th width="16%">
							姓名
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<th colspan="3">
							性别
					</th>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<th>
							学年
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xn"/>
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
				</tr>
				<tr>
					<th colspan="3">
							民族
					</th>
					<td colspan="2">
						<bean:write name="rs" property="mzmc"/>
					</td>
					<th>
							政治面貌
					</th>
					<td colspan="3">
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							年级
					</th>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							专业名称
					</th>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
							班级名称
					</th>
					<td colspan="3">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							身份证号
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfzh"/>
					</td>
					<th>
							入学前户口
					</th>
					<td colspan="3">
						<bean:write name="rs" property="rxqhk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							生源
					</th>
					<td colspan="6">
						<bean:write name="rs" property="sy"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							家庭人口数
					</th>
					<td colspan="2">
						<bean:write name="rs" property="jtrks"/>
					</td>
					<th>
							是否孤残
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfgc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							是否单亲
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfdq"/>
					</td>
					<th>
							是否烈士子女
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sflszn"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							家庭邮政编码
					</th>
					<td colspan="2">
						<bean:write name="rs" property="jt_yzbm"/>
					</td>
					<th>
							家庭联系电话
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jt_lxdh"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							家庭详细通讯地址
					</th>
					<td colspan="6">
						<bean:write name="rs" property="jt_xxtxdz"/>
					</td>
				</tr>
				<tr>
					<th width="4%" rowspan="6">
							家
							<br>
							庭
							<br>
							成
							<br>
							员
							<br>
							情
							<br>
							况
					</th>
					<th width="8%">
							姓名
					</td>
					<th width="8%">
							年龄
					</th>
					<th width="9%">
							与学生关系
					</th>
					<th colspan="2">
							工作(学习)单位
					</th>
					<th width="12%">
							职业
					</th>
					<th width="11%">
							年收入(元)
					</th>
					<th width="11%">
							健康状况
					</th>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy1_xm"/>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl"/>
						</div>
					</td>
					<td>
							<bean:write name="rs" property="jtcy1_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy1_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy1_zy"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="jtcy1_sr"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="jtcy1_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy2_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy2_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy2_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_zy"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy3_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy3_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy3_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_zy"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy4_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy4_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy4_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy4_gzdw"/>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_zy"/>
						</div>
					</td>
					<td>
							<bean:write name="rs" property="jtcy4_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy4_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy5_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy5_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy5_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_zy"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_jkzk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							父亲身份证号
					</th>
					<td colspan="2">
						<bean:write name="rs" property="fqsfzh"/>
					</td>
					<th>
							母亲身份证号
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mqsfzh"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							家庭全年收入
						</div>
					</th>
					<td colspan="2">
						<bean:write name="rs" property="jtqnsr"/>
					</td>
					<th>
						<div align="center">
							人均年收入
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="rjnsr"/>
					</td>
				</tr>
				<tr>
					<th colspan="9">
							家庭贫困原因
					</th>
				</tr>
				<tr>
					<th colspan="3">
							农村贫困地区
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfncpkdq"/>
					</td>
					<th>
							城镇下岗家庭
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfczxgjt"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							父母新残疾
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sffmxcj"/>
					</td>
					<th>
							患重大疾病
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfhzdjb"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							单亲家庭
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfdqjt"/>
					</td>
					<th>
							孤儿
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfgr"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							自然灾害
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfzrzh"/>
					</td>
					<th>
							家庭人口多
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfjtrkd"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							其它
					</th>
					<td colspan="6">
						<bean:write name="rs" property="qtnr"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							贫困原因详细说明
					</th>
					<td colspan="6">
						<bean:write name="rs" property="pkyyxxsm"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							家中欠债情况
					</th>
					<td colspan="6">
						<bean:write name="rs" property="jzqzqk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							学生入学前已获资助情况(含资助金额)
					</th>
					<td colspan="6">
						<bean:write name="rs" property="xsrxqyhzzqk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							其他情况
					</th>
					<td colspan="6">
						<bean:write name="rs" property="qtqk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							民政部门邮政编码
					</th>
					<td colspan="2">
						<bean:write name="rs" property="mzbm_yzbm"/>
					</td>
					<th>
							民政部门联系电话
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_lxdh"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							民政部门详细通讯地址
					</th>
					<td colspan="6">
						<bean:write name="rs" property="mzbm_xxtxdz"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							申请时间
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<th>
							审核结果
					</th>
					<td colspan="3">
						<html:select name="rs" property="sh">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				</tbody>
				 <tfoot>
				      <tr>
				        <td colspan="12"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
							<button type="button"  onclick="yz()"  
								id="buttonSave">
								保 存
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button"   onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"  
								id="buttonClose">
								关 闭
							</button>	           
				          </div>
				          </td>
				      </tr>
				    </tfoot>
			</table>
		</html:form>
	</body>
</html>
