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
		    	<span> �������䷽ʽ��${rsfpfs.rsfpfsmc }&nbsp;&nbsp;&nbsp;<font color="blue"></font>
		    		<font color="blue" class="underline pointer"
													 onclick="showDialog('������������',650,450,'xpj_xmwh_rssz.do?method=xmwhRsszCk&rsfpfs=${rsfpfs.rsfpfs }&xmdm=${xpjSqshModel.xmdm }');"
												>����</font>&nbsp;&nbsp;<font color="color">���������顱��ť���Բ鿴������������</font>
<%--		    		  ��������&nbsp;<font color="#0457A7" >${shqkInfo.zrs }</font>&nbsp;��--%>
<%--		    		  ����ͨ������&nbsp;<font color="#0457A7" >${shqkInfo.zztgrs }</font>&nbsp;�ˣ�--%>
<%--		    		  ͨ����Ϊ<font color="#0457A7">&nbsp;${shqkInfo.zztgl }&nbsp;</font>��--%>
		    	</span>
		    </h3>
			<table class="dateline" width="100%">
				<thead>
					<tr>
						<th rowspan="2" style="text-align: center;">��˸�λ</th>
						<th colspan="3" style="text-align: center;">���ͳ��</th>
					</tr>
					<tr>
<%--						<th style="text-align: center;">ͨ��</th>--%>
<%--						<th style="text-align: center;">��ͨ��</th>--%>
						<th style="text-align: center;">���������</th>
						<th style="text-align: center;">��ͨ������</th>
					</tr>
				</thead>
				<tbody>
					<logic:present name="shqkInfo">
					<logic:notEmpty name="shqkInfo" property="shqkList">
						<logic:iterate id="shqk" name="shqkInfo" property="shqkList">
							<tr>
								<td>${shqk.gwmc }</td>
<%--								<td>������&nbsp;<font color="#0457A7" class="underline pointer" --%>
<%--													 onclick="showDialog('',700,500,'xpj_sqsh.do?method=getStudentsByShqk&shzt=1&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"--%>
<%--												>${shqk.tg }</font>&nbsp;��<br/>--%>
<%--									������<font color="#0457A7">&nbsp;${shqk.tgl }&nbsp;</font></td>--%>
<%--								<td>������&nbsp;<font color="#0457A7" class="underline pointer"--%>
<%--													  onclick="showDialog('',700,500,'xpj_sqsh.do?method=getStudentsByShqk&shzt=2&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"--%>
<%--												>${shqk.btg }</font>&nbsp;��<br/>--%>
<%--									������<font color="#0457A7">&nbsp;${shqk.btgl }&nbsp;</font></td>--%>
<%--								<td>������&nbsp;<font color="#0457A7" class="underline pointer"--%>
<%--													 onclick="showDialog('',700,500,'xpj_sqsh.do?method=getStudentsByShqk&shzt=0&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"--%>
<%--												>${shqk.dsh }</font>&nbsp;��<br/>--%>
<%--									������<font color="#0457A7">&nbsp;${shqk.dshl }&nbsp;</font></td>--%>
								<td>������&nbsp;<font color="#0457A7" class="underline pointer"
													 onclick="showDialog('',700,500,'xpj_sqsh.do?method=getStudentsByShqk&shzt=0&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"
												>${shqk.dcl }</font>&nbsp;��<br/>
									������&nbsp;${shqk.dcll }&nbsp;</td>
								<td>������&nbsp;<font color="#0457A7" class="underline pointer"
													 onclick="showDialog('',700,500,'xpj_sqsh.do?method=getStudentsByShqk&shzt=1&shid=${shqk.shid }&xmdm=${xpjSqshModel.xmdm }');"
												>${shqk.ycl }</font>&nbsp;��<br/>
									������&nbsp;${shqk.ycll }&nbsp;</td>	
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					</logic:present>
				</tbody>
			</table>
		</div>
	</body>
</html>
