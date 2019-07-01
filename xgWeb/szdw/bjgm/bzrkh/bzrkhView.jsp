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
				<em>您的当前位置:</em><a>班主任考核信息查看</a>
			</p>
		</div>	
		<div class="prompt" id="span_cwh" style="display: none">
	       <h3><span>提示：</span></h3>
	       <p><br/></p>
	   	</div>
		
		<div class="tab">
		<table style="width: 100%;">
		<tr><td>
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>班主任考核信息维护</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th style="text-align: left;font-weight: bold;" colspan="4">
					班主任信息
				</th>
			</tr>
			<tr>
				<th><font color="red">*</font>学年</th>
				<td>
					<logic:present name="bzrkhList">
						${bzrkhList[0].xn}
					</logic:present>
				</td>
				<th><font color="red">*</font>学期</th>
				<td>
					<logic:present name="bzrkhList">
						<logic:iterate id="bl" name="bzrkhList" length="1">
						<logic:equal name="bl" property="xq" value="01">
							春
						</logic:equal>
						<logic:equal name="bl" property="xq" value="02">
							秋
						</logic:equal>
						</logic:iterate>
					</logic:present>
				</td>
			</tr>
			<tr>
				<th width="16%">
					<font color="red">*</font>职工号				
				</th>
				<td width="34%">
					${rs.zgh}
				</td>
				<th width="16%">
					姓名
				</th>
				<td width="34%">
					${rs.xm}
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>
					${rs.xb}
				</td>
				<th>所属部门</th>
				<td>
					${rs.bmmc}
				</td>
			</tr>		
			<tr>
				<th style="text-align: left; font-weight: bold;" colspan="4">
					班主任考核评分
				</th>
			</tr>
			<tr>
				<th width="16%">
					出勤
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
					材料
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
				<th>年级</th>
				<td>
					<logic:present name="bzrkhList">
						${bzrkhList[0].nj}
					</logic:present>
					<logic:notPresent name="bzrkhList">
						0
					</logic:notPresent>
				</td>
				<th>学生课</th>
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
				<th>班主任得分
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
					<span>班级考核评分</span>
				</th>
			</tr>
			<tr>
				<th width="16%" >
					班级
				</th>
				<th width="10%" >
					卫生安全
				</th>
				<th width="10%" >
					板报
				</th>
				<th width="10%" >
					升旗
				</th>
				<th width="10%" >
					纪律
				</th>
				<th width="10%" >
					人数
				</th>
				<th width="10%" >
					奖励
				</th>
				<th width="10%" >
					课间操
				</th>
				<th width="14%" >
					班级得分
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
					<span>考核总分</span>
				</th>
			</tr>
			<tr>
				<th width="16%" >
					总分
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
					<button type="button" id="buttonClose" onclick="window.close();return false;">关闭</button>
				</th>
			</tr>
		</table>
		</td></tr>
		</table>
		</div>
	</html:form>
</body>
</html>