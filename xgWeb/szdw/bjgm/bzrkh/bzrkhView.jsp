<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<style>
			table tbody th{
				text-align: center;
			}
		</style>
		<script	type="text/javascript">
		</script>
</head>
<body>
	<html:form action="/bjgm_fdykh" method="post">
		<input type="hidden" name="xyV" id="xyV" value="" />
		<input type="hidden" id="url" name="url"
			value="bjgm_fdykh_bzrkhAdd.do?method=bzrkhAdd" />
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�����ο�����Ϣ�鿴</a>
			</p>
		</div>	
		<div class="prompt" id="span_cwh" style="display: none">
	       <h3><span>��ʾ��</span></h3>
	       <p><br/></p>
	   	</div>
		
		<div class="tab">
		<table style="width: 100%;">
		<tr><td>
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>�����ο�����Ϣά��</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th style="text-align: left;font-weight: bold;" colspan="4">
					��������Ϣ
				</th>
			</tr>
			<tr>
				<th><font color="red">*</font>ѧ��</th>
				<td>
					<logic:present name="bzrkhList">
						${bzrkhList[0].xn}
					</logic:present>
				</td>
				<th><font color="red">*</font>ѧ��</th>
				<td>
					<logic:present name="bzrkhList">
						<logic:iterate id="bl" name="bzrkhList" length="1">
						<logic:equal name="bl" property="xq" value="01">
							��
						</logic:equal>
						<logic:equal name="bl" property="xq" value="02">
							��
						</logic:equal>
						</logic:iterate>
					</logic:present>
				</td>
			</tr>
			<tr>
				<th width="16%">
					<font color="red">*</font>ְ����				
				</th>
				<td width="34%">
					${rs.zgh}
				</td>
				<th width="16%">
					����
				</th>
				<td width="34%">
					${rs.xm}
				</td>
			</tr>
			<tr>
				<th>�Ա�</th>
				<td>
					${rs.xb}
				</td>
				<th>��������</th>
				<td>
					${rs.bmmc}
				</td>
			</tr>		
			<tr>
				<th style="text-align: left; font-weight: bold;" colspan="4">
					�����ο�������
				</th>
			</tr>
			<tr>
				<th width="16%">
					����
				</th>
				<td width="34%">
					<logic:present name="bzrkhList">
						${bzrkhList[0].cq}
					</logic:present>
					<logic:notPresent name="bzrkhList">
						0
					</logic:notPresent>
				</td>
				<th>
					����
				</th>
				<td>
					<logic:present name="bzrkhList">
						${bzrkhList[0].cl}
					</logic:present>
					<logic:notPresent name="bzrkhList">
						0
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<th>�꼶</th>
				<td>
					<logic:present name="bzrkhList">
						${bzrkhList[0].nj}
					</logic:present>
					<logic:notPresent name="bzrkhList">
						0
					</logic:notPresent>
				</td>
				<th>ѧ����</th>
				<td>
					<logic:present name="bzrkhList">
						${bzrkhList[0].xsk}
					</logic:present>
					<logic:notPresent name="bzrkhList">
						0
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<th>�����ε÷�
				</th>
				<td id="grdfTd">
					<logic:present name="bzrkhList">
					${bzrkhList[0].grdf}
					</logic:present>
				</td>
				<th></th>
				<td>
					 
				</td>
			</tr>
			</tbody>
		</table>
		</td></tr>
		<tr><td>
		<table class="formlist" width="95%">
			<tr style="height:22px">
				<th colspan="9" style="text-align: left; font-weight: bold;border-top: 0px;">
					<span>�༶��������</span>
				</th>
			</tr>
			<tr>
				<th width="16%" >
					�༶
				</th>
				<th width="10%" >
					������ȫ
				</th>
				<th width="10%" >
					�屨
				</th>
				<th width="10%" >
					����
				</th>
				<th width="10%" >
					����
				</th>
				<th width="10%" >
					����
				</th>
				<th width="10%" >
					����
				</th>
				<th width="10%" >
					�μ��
				</th>
				<th width="14%" >
					�༶�÷�
				</th>
			</tr>
			<logic:present name="bzrkhList">
			<logic:iterate id="bj" name="bzrkhList" indexId="idx">
			<tr>
				<th width="10px" >
					${bj.bjmc}
				</th>
				<td>
					${bj.wsaq}
				</td>
				<td>
					${bj.bb}
				</td>
				<td>
					${bj.sq}
				</td>
				<td>
					${bj.jl}
				</td>
				<td>
					${bj.rs}
				</td>
				<td>
					${bj.jli}
				</td>
				<td>
					${bj.kjc}
				</td>
				<td id="bjdfTd${idx}">
					${bj.bjdf}
				</td>
			</tr>
			</logic:iterate>
			</logic:present>
			<tr style="height:22px">
				<th colspan="9" style="text-align: left; font-weight: bold;border-top: 0px;">
					<span>�����ܷ�</span>
				</th>
			</tr>
			<tr>
				<th width="16%" >
					�ܷ�
				</th>
				<td width="74%" colspan="8" id="grzfTd">
					<logic:present name="bzrkhGrzf">
						${bzrkhGrzf}
					</logic:present>
					<logic
				</td>
			</tr>
			<tr>
				<th colspan="9">
					<button type="button" id="buttonClose" onclick="window.close();return false;">�ر�</button>
				</th>
			</tr>
		</table>
		</td></tr>
		</table>
		</div>
	</html:form>
</body>
</html>