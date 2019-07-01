<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">

<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
</head>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h3>
							<strong>助学贷款提前还款申请</strong>
						</h3>
					</div>
					<br />
					<br />
					<br />
					<strong>
					&nbsp;&nbsp;&nbsp;&nbsp;中国银行东湖开发区支行：
					</strong>
					<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本人____________，身份证号：_________________，毕业于_______________________（学校名称及专业），截止至______年_____月（毕业时间）累计从中国银行东湖开发区支行获得助学贷款_______________元。借款合同编号为________________。原计划从________年______月（还款协议上约定时间）开始还款，现申请提前一次性结清贷款本息。<br /><br />
					还款帐号：____________________________；<br /><br />
					联系电话：<br /><br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特此申请！<br /><br /><br /><br />
					<div align="right">
						申请人：________________&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<br />
					<div align="right">
						________年_____月_____日&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div class="tab">
		<div  class="btn" align="center">
			<button type="button" value="打印" name="button_print"
				onclick="expTab('theTable','')" >打印</button>
		</div>
	</div>
</body>
</html>
