<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<style type="text/css">
			.aaa{height: 30px;line-height: 30px;background: #fff;}
		</style>
	</head>
	<body>
		<div class="formbox">
			<h3 class="aaa">
		    	<span> 人数分配方式：${rsfpfs.rsfpfsmc }&nbsp;&nbsp;&nbsp;<font color="blue"></font>
		    		<font color="blue" class="underline pointer"
													 onclick="showDialog('人数分配详情',650,450,'xpj_xmwh_rssz.do?method=xmwhRsszCk&rsfpfs=${rsfpfs.rsfpfs }&xmdm=${xpjSqshModel.xmdm }');"
												>详情</font>&nbsp;&nbsp;<font color="color">单击“详情”按钮可以查看人数分配详情</font>
<%--		    		  申请人数&nbsp;<font color="#0457A7" >${shqkInfo.zrs }</font>&nbsp;人--%>
<%--		    		  最终通过人数&nbsp;<font color="#0457A7" >${shqkInfo.zztgrs }</font>&nbsp;人，--%>
<%--		    		  通过率为<font color="#0457A7">&nbsp;${shqkInfo.zztgl }&nbsp;</font>。--%>
		    	</span>
		    </h3>
			<table class="dateline" width="100%">
				<thead>
					<tr>
						<th rowspan="2" style="text-align: center;">审核岗位</th>
						<th colspan="3" style="text-align: center;">审核统计</th>
					</tr>
					<tr>
<%--						<th style="text-align: center;">通过</th>--%>
<%--						<th style="text-align: center;">不通过</th>--%>
						<th style="text-align: center;">待审核人数</th>
						<th style="text-align: center;">已通过人数</th>
					</tr>
				</thead>
				<tbody>
					<logic:present name="shqkInfo">
					<logic:notEmpty name="shqkInfo" property="shqkList">
						<logic:iterate id="shqk" name="shqkInfo" property="shqkList">
							<tr>
								<td>${shqk.gwmc }</td>
<%--								<td>人数：&nbsp;<font color="#0457A7" class="underline pointer" --%>
<%--													 onclick="showDialog('',700,500,'bzjl_sqsh.do?method=getStudentsByShqk&shzt=1&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"--%>
<%--												>${shqk.tg }</font>&nbsp;人<br/>--%>
<%--									比例：<font color="#0457A7">&nbsp;${shqk.tgl }&nbsp;</font></td>--%>
<%--								<td>人数：&nbsp;<font color="#0457A7" class="underline pointer"--%>
<%--													  onclick="showDialog('',700,500,'bzjl_sqsh.do?method=getStudentsByShqk&shzt=2&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"--%>
<%--												>${shqk.btg }</font>&nbsp;人<br/>--%>
<%--									比例：<font color="#0457A7">&nbsp;${shqk.btgl }&nbsp;</font></td>--%>
<%--								<td>人数：&nbsp;<font color="#0457A7" class="underline pointer"--%>
<%--													 onclick="showDialog('',700,500,'bzjl_sqsh.do?method=getStudentsByShqk&shzt=0&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"--%>
<%--												>${shqk.dsh }</font>&nbsp;人<br/>--%>
<%--									比例：<font color="#0457A7">&nbsp;${shqk.dshl }&nbsp;</font></td>--%>
								<td>人数：&nbsp;<font color="#0457A7" class="underline pointer"
													 onclick="showDialog('',700,500,'bzjl_sqsh.do?method=getStudentsByShqk&shzt=0&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"
												>${shqk.dcl }</font>&nbsp;人<br/>
									比例：&nbsp;${shqk.dcll }&nbsp;</td>
								<td>人数：&nbsp;<font color="#0457A7" class="underline pointer"
													 onclick="showDialog('',700,500,'bzjl_sqsh.do?method=getStudentsByShqk&shzt=1&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"
												>${shqk.ycl }</font>&nbsp;人<br/>
									比例：&nbsp;${shqk.ycll }&nbsp;</td>	
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					</logic:present>
				</tbody>
			</table>
		</div>
	</body>
</html>
