<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<div class="formbox">
		<h3 class="datetitle_01">
	    	<span>
	    		  申请人数&nbsp;<font color="#0457A7" >${shqkInfo.zrs }</font>&nbsp;人，
	    		  最终通过人数&nbsp;<font color="#0457A7" >${shqkInfo.zztgrs }</font>&nbsp;人，
	    		  通过率为<font color="#0457A7">&nbsp;${shqkInfo.zztgl }&nbsp;</font>。
	    	</span>
	    </h3>
		<table class="dateline" width="100%">
			<thead>
				<tr>
					<th rowspan="2" style="text-align: center;">审核岗位</th>
					<th colspan="3" style="text-align: center;">审核状态</th>
				</tr>
				<tr>
					<th style="text-align: center;">通过</th>
					<th style="text-align: center;">不通过</th>
					<th style="text-align: center;">待审核</th>
				</tr>
			</thead>
			<tbody>
				<logic:present name="shqkInfo">
					<logic:iterate id="shqk" name="shqkInfo" property="shqkList">
						<tr>
							<td>${shqk.gwmc }</td>
							<td>人数：&nbsp;<font color="#0457A7" class="underline pointer" 
												 onclick="showDialog('',700,500,'xszz_knsrd.do?method=getStudentsByShqk&shzt=1&xtgwid=${shqk.xtgwid }');"
											>${shqk.tg }</font>&nbsp;人<br/>
								比例：<font color="#0457A7">&nbsp;${shqk.tgl }&nbsp;</font></td>
							<td>人数：&nbsp;<font color="#0457A7" class="underline pointer"
												  onclick="showDialog('',700,500,'xszz_knsrd.do?method=getStudentsByShqk&shzt=2&xtgwid=${shqk.xtgwid }',700,500);"
											>${shqk.btg }</font>&nbsp;人<br/>
								比例：<font color="#0457A7">&nbsp;${shqk.btgl }&nbsp;</font></td>
							<td>人数：&nbsp;<font color="#0457A7" class="underline pointer"
												 onclick="showDialog('',700,500,'xszz_knsrd.do?method=getStudentsByShqk&shzt=0&xtgwid=${shqk.xtgwid }',700,500);"
											>${shqk.dsh }</font>&nbsp;人<br/>
								比例：<font color="#0457A7">&nbsp;${shqk.dshl }&nbsp;</font></td>
						</tr>
					</logic:iterate>
				</logic:present>
			</tbody>
		</table>
		</div>
	</body>
</html>
