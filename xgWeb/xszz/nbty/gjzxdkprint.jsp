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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />	
		
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/calendar-zh.js"></script>
		<script language="javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function choicesqzk(name,value){
				var sqzkbox = document.getElementsByName(name);
				var sqzk = document.getElementById(value).value;
				switch(sqzk){
					case '已申请成功':sqzkbox[0].checked='checked';break;
					case '正在申请中':sqzkbox[1].checked='checked';break;
				}
			}
		</script>
	</head>
	
	<body>
		<div align="center">
			<p>
				<b><span style='font-size:18.0pt;line-height:150%;font-family:宋体'>宁波天一职业技术学院</span>
				</b>
			</p>
			<p style='line-height:150%'>
				<b><u><span style='font-size:18.0pt;line-height:150%;font-family:宋体'>${rs.xn}</span> </u> </b><b><span style='font-size:18.0pt;line-height:
150%;font-family:宋体'>学年国家助学贷款审核表</span>
				</b>
			</p>
			<table class="tbstyle" style="width: 90%">
				<tr>
					<td width="10%" align=center>
						姓 名
					</td>
					<td width="15%" align=center>
						${rs.xm }
					</td>
					<td width="8%" colspan=3 align=center>
						性别
					</td>
					<td width="8%" colspan=2 align=center>
						${rs.xb }
					</td>
					<td width="12%" align=center>
						出生日期
					</td>
					<td width="12%" align=center>
						${rs.csrq }
					</td>
					<td width="14%" colspan=3 align=center>
						年级、专业、班级
					</td>
					<td width="19%" align=center>
						${rs.bjmc }
					</td>
				</tr>
				
				<tr>
					<td align=center align=center>
						学号
					</td>
					<td align=center align=center>
						${rs.xh }
					</td>
					<td colspan=3 align=center>
						学制
					</td>
					<td colspan=2 align=center>
						${rs.xz }
					</td>
					<td align=center>
						毕业时间
					</td>
					<td align=center>
						${rs.byny }
					</td>
					<td colspan=3 align=center>
						寝室号、寝室电话
					</td>
					<td align=center>
						${rs.ssbh}&nbsp;&nbsp;${rs.qsdh }
					</td>
				</tr>
				<tr>
					<td align=center>
						家庭地址
					</td>
					<td colspan=8 align=left>
						&nbsp;&nbsp;${rs.jtszd }
					</td>
					<td colspan=3 align=center>
						可联系家庭电话
					</td>
					<td align=center>
						${rs.lxdh1 }
					</td>
				</tr>
				<tr>
					<td align=center>						
						邮&nbsp;&nbsp;&nbsp;编
					</td>
					<td align=center>
						${rs.jtyb }
					</td>
					<td colspan=4 align=center>
						身份证号
					</td>
					<td colspan=3 align=center>
						${rs.sfzh }
					</td>
					<td colspan=3 align=center>
						本人电话
					</td>
					<td align=center>
						${rs.lxdh }
					</td>
				</tr>
				<tr>
					<td align=center>
						父亲姓名
					</td>
					<td align=center>
						${rs.fqxm }
					</td>
					<td colspan=4 align=center>
						单位
					</td>
					<td colspan=5 align=center>
						${rs.fqdw }
					</td>
					<td align=center width="9%">
						父亲手机
					</td>
					<td align=center>
						${rs.fqsj }
					</td>
				</tr>
				<tr>
					<td align=center>
						母亲姓名
					</td>
					<td align=center>
						${rs.mqxm }
					</td>
					<td colspan=4 align=center>
						单位
					</td>
					<td colspan=5 align=center>
						${rs.mqdw }
					</td>
					<td align=center>
						母亲手机
					</td>
					<td align=center>
						${rs.mqsj }
					</td>
				</tr>
				<tr>
					<td align=center>
						见证人
					</td>
					<td align=center>
						${rs.jzr1 }
					</td>
					<td colspan=4 align=center>
						身份证号
					</td>
					<td colspan=5 align="center">
						${rs.sfzh1 }
					</td>
					<td align=center>
						见证人签名
					</td>
					<td align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align=center>
						见证人
					</td>
					<td align=center>
						${rs.jzr2}
					</td>
					<td colspan=4 align=center>
						身份证号
					</td>
					<td colspan=5 align=center>
						${rs.sfzh2 }
					</td>
					<td align=center>
						见证人签名
					</td>
					<td align=center>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan=6 align=left>
						就读期学习情况总体评价：<br/>
						<input type="hidden" id="xxqkztpj" value="${rs.xxqkztpj }"/>
							优：<input type="checkbox" name="ztpj" disabled="disabled" value="优"/>
							良：<input type="checkbox" name="ztpj" disabled="disabled" value="良"/>
							一般：<input type="checkbox" name="ztpj" disabled="disabled" value="一般"/>
							差：<input type="checkbox" name="ztpj" disabled="disabled" value="差"/>
							<script type="text/javascript">
								var pj = document.getElementsByName("ztpj");
								var xxqkztpj = $('xxqkztpj').value;
								switch(xxqkztpj){
									case '优':pj[0].checked='checked';break;
									case '良':pj[1].checked='checked';break;
									case '一般':pj[2].checked='checked';break;
									case '差':pj[3].checked='checked';break;
								}
						</script>				
					</td>
					<td colspan=3 align=center>
						就读期间累计不及格<br/><br/>
						必修课目数
					</td>
					<td width="10%" colspan=2 align=center>
						${rs.bjgbxkms }
					</td>
					<td align=center>
						有无违纪<br/><br/>处分
					</td>
					<td align=center>
						${rs.ywwjcf }
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
							上学年品德等第
					</td>
					<td colspan=4 align=center>
						${rs.sxnpddd }
					</td>
					<td colspan=3 align=center>
						有无不良信用记录
					</td>
					<td colspan=4>
						<p align=center>
							<input type="hidden" id="ywblxyjl" value="${rs.ywblxyjl }"/>
							有：<input type="checkbox" name="bljl" disabled="disabled" value="有"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							无：<input type="checkbox" name="bljl" disabled="disabled" value="无"/>
							<script type="text/javascript">
								var bljl = document.getElementsByName("bljl");
								var ywblxyjl = $('ywblxyjl').value;
								switch(ywblxyjl){
									case '有':bljl[0].checked='checked';break;
									case '无':bljl[1].checked='checked';break;
								}
							</script>			
						</p>
					</td>
				</tr>
				<tr>
					<td rowspan=3 align=center>
							已申请
							<br/><br/>
							贷款
							<br/><br/>
							总额
					</td>
					<td rowspan=3 align=center>
						&nbsp;${mxndkxx.dkze.dkze }
					</td>
					<td colspan=4 rowspan=3 align=center>
						分学年<br/><br/>申请贷<br/><br/>款金额
					</td>
					<td colspan=3 align=center>
						第一学年<u>&nbsp;&nbsp;${mxndkxx.xn1.dkje }&nbsp;&nbsp;</u>元
					</td>
					<td colspan=4 align=center>
								<input type="hidden" id="xn1_sqzk" value="${mxndkxx.xn1.sqzk}"/>
								已申请成功：<input type="checkbox" name="xn1_box" disabled="disabled"/>
								
								正在申请中：<input type="checkbox" name="xn1_box" disabled="disabled"/>
								<script type="text/javascript">
									choicesqzk('xn1_box','xn1_sqzk');
								</script>
					</td>
				</tr>
				<tr>
					<td colspan=3 align=center>
							第二学年<u>&nbsp;&nbsp;${mxndkxx.xn2.dkje }&nbsp;&nbsp;</u>元
					</td>
					<td colspan=4 align=center>
								<input type="hidden" id="xn2_sqzk" value="${mxndkxx.xn2.sqzk}"/>
								已申请成功：<input type="checkbox" name="xn2_box" disabled="disabled"/>
								
								正在申请中：<input type="checkbox" name="xn2_box" disabled="disabled"/>
								<script type="text/javascript">
									choicesqzk('xn2_box','xn2_sqzk');
								</script>
					</td>
				</tr>
				<tr>
					<td colspan=3 align=center>
						第三学年<u>&nbsp;&nbsp;${mxndkxx.xn3.dkje }&nbsp;&nbsp;</u>元
					</td>
					<td colspan=4 align=center>
								<input type="hidden" id="xn3_sqzk" value="${mxndkxx.xn3.sqzk}"/>
								已申请成功：<input type="checkbox" name="xn3_box" disabled="disabled"/>
					
								正在申请中：<input type="checkbox" name="xn3_box" disabled="disabled"/>
								<script type="text/javascript">
									choicesqzk('xn3_box','xn3_sqzk');
								</script>
					</td>
				</tr>
				<tr>
					<td colspan=13>
						<p align=left>
							<input type="hidden" id="hkfs" value="${rs.hkfs }"/>
 							还款方式：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 等额本息还款法<input type="checkbox" name="hkfs_box" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 等额本金还款法<input type="checkbox" name="hkfs_box" disabled="disabled"/>
							 <script type="text/javascript">
								var hkfs_box = document.getElementsByName('hkfs_box');
								var hkfs = $('hkfs').value;
								switch(hkfs){
									case '等额本息还款法':hkfs_box[0].checked='checked';break;
									case '等额本金还款法':hkfs_box[1].checked='checked';break;
								}
							 </script>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=13 valign=top>
						<p>
							班主任评语及审查意见：
						</p>
						<p>&nbsp;&nbsp;${rs.fdypy }</p>
						<p align="right">
							<span>签&nbsp;&nbsp;&nbsp;名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=4 valign=top>
						<p>
							院系审查意见：
						</p>
						<p>&nbsp;&nbsp;${rs.xyshyj }</p>
						<p align="right">
							盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
					<td colspan=6 valign=top>
						<p>
							学生资助管理中心审核意见：
						</p>
							<p>
								&nbsp;&nbsp;${rs.xxshyj }
							</p>
					<p align="right">
							盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
					<td colspan=3 valign=top>
						<p>
							<span><bean:message key="lable.xb" />审批意见:</span>
						</p>
						<p>
							&nbsp;&nbsp;${rs.xxshyj }
						</p>
						<p align="right">
							盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
			</table>
			<table width="90%" border="0">
				<tr>
				<td>
				<span>申请材料</span> 
				<span>：</span>
				<span style='font-family:仿宋_GB2312'></span>
				<span lang=EN-US style='font-family:仿宋_GB2312'>1.</span>
				<span style='font-family:仿宋_GB2312;color:black'>《</span>
				<span style='font-family:仿宋_GB2312'>中国建设银行国家助学贷款申请表
				<span style='color:black'>》</span>。<span lang=EN-US>2.</span>
				<span style='color:black'>《</span>宁波天一职业技术<bean:message key="lable.xsgzyxpzxy" />国家助学贷款审核表
				<span style='color:black'>》</span>。<span lang=EN-US>3.</span>
				<span style='color:black'>《高等学校学生及家庭情况调查表》和《高等学校家庭经济困难学生认定申请表》的复印件。
				<span lang=EN-US>4.</span>《督促还款承诺书》（班主任在核实其真实性后，注明“已核实承诺书确为借款人父母本人所签”或“已电话联系借款人父母，其父母已知晓此事，承诺书确为借款人父母所签”，并签名。）。
				<span lang=EN-US>5.</span>学生证、身份证和家庭户口簿<span lang=EN-US>(</span>或户籍证明<span lang=EN-US>)</span>的复印件。
				<span lang=EN-US>6.</span>父母身份证（父母户口不在同一户口簿时还需提供结婚证）的复印件。
				<span lang=EN-US> 7.</span>两名见证人身份证、工作证的复印件。
				<span lang=EN-US>8.</span>新生的入学通</span>知书的复印
				<span style='color:black'>件，</span>和<span style='color:black'>其他</span>在校生的上一学年学习成绩单（由各<bean:message key="lable.xsgzyxpzxy" />统一提供）。
				<span lang=EN-US>9.</span>借款人扣款帐户复印件。</span><br/>					
				&nbsp;&nbsp;
				<span style='font-family:仿宋_GB2312'>所有材料均统一使用<span lang=EN-US>A4</span>纸，证件应每页都复印，且在复印件上加盖“与原件核对无误”章及核对老师的名章，材料按以上顺序排列、装订。</span>
				</td>
				</tr>
			</table>
		</div>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
	</body>
</html>
