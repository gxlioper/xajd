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
			    		��ǰ������ĿΪ:<font color="blue">&nbsp;${xmmc}</font>&nbsp;&nbsp;&nbsp;&nbsp;
			    		���˶���:<font color="blue">&nbsp;${ryInfo.xm}</font>&nbsp;&nbsp;&nbsp;&nbsp;
		    		</span>
		    </h3>
			<table class="dateline" width="100%">
				<thead>
					<tr>
						<th rowspan="2" style="text-align: center;">���ֶ���</th>
						<th colspan="3" style="text-align: center;">ͳ��</th>
					</tr>
					<tr>
						<th style="text-align: center;">���������</th>
						<th style="text-align: center;">�Ѵ������</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="dftj" name="dftjList" >
						<tr>
							<td style="text-align: center;"> ${dftj.pfzmc }
								<logic:empty name='${dftj.sjfw }'>
									<font style="font-family:����; font-size:10px"></br>���ݷ�Χ:${dftj.sjfw }</font>
								</logic:empty>
							</td>
							<td>������&nbsp;<font color="#0457A7" class="underline pointer"
												  onclick="showDialog('',750,500,'khgljgcx.do?method=dfrList&pfzt=0&pflx=${dftj.pflx}&xmid=${dftj.xmid}&khdxr=${dftj.khdxr}&bmdm=${dftj.bmdm}&khlx=${dftj.khlx}&xmszid=${dftj.xmszid}');"
											>${dftj.wdf }</font>&nbsp;��<br/>
								������&nbsp;${dftj.wdfb }%&nbsp;</td>
							<td>������&nbsp;<font color="" class="underline pointer"
												  onclick="showDialog('',750,500,'khgljgcx.do?method=dfrList&pfzt=1&pflx=${dftj.pflx}&xmid=${dftj.xmid}&khdxr=${dftj.khdxr}&bmdm=${dftj.bmdm}&khlx=${dftj.khlx}&xmszid=${dftj.xmszid}');"
											>${dftj.ydf }</font>&nbsp;��<br/>
								������&nbsp;${dftj.ydfb }%&nbsp;</td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
		</div>
	</body>
</html>
