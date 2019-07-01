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
		//选择老师信息
		function sendXx(){
			var tableName=$("tableName").value;
			var lx="";if($("lx")){lx=$("lx").value};
			var zd="";if($("zd")){zd=$("zd").value};
			var va="";if($("va")){va=$("va").value};
			var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
			url+="&tableName="+tableName;
			if(lx !=""){
				url+="&lx="+lx;
			}
			if(zd !=""){
				url+="&zd="+zd;
			}
			if(va !=""){
				url+="&va="+va;
			}
			showTopWin(url,800,600);
		}
		//重新请求当前页面
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}

		//个人分数计算开始
		function grCount(obj,begin,end){
			checkInputNum(obj);
			if(obj.value==""){
				return ;
			}
			if(!verdictNumMax(obj,begin,end)){
				return ;
			}
			countTeacher(obj);
		}

		//计算个人得分parentNode 
		function countTeacher(obj){
			var cqObj=document.getElementById("cq");
			var clObj=document.getElementById("cl");
			var njObj=document.getElementById("nj");
			var xskObj=document.getElementById("xsk");
			var grdfObj=document.getElementById("grdf");
			var grdfTdObj=document.getElementById("grdfTd");
			var count=intValue(cqObj.value)+intValue(clObj.value)+
					intValue(njObj.value)+intValue(xskObj.value);
			grdfTdObj.innerHTML=count;
			grdfObj.value=count;
		}

		//判断值上线
		function verdictNumMax(obj,begin,end){
			var grdfObj=document.getElementById("grdf");
			var grdfTdObj=document.getElementById("grdfTd");
			if(obj.value =="" || (parseFloat(obj.value)>=begin && parseFloat(obj.value)<=end)){
				return true;
			}
			alertInfo("分数超出范围,分数应在"+begin+"至"+end+"范围内");
			obj.value=end;
			grdfObj.value="";
			countTeacher(obj);
			return false;
		}

		//工具类型
		function intValue(str){
			if(str==""){
				return 0;
			}
			return parseFloat(str);
		}

		//班级得分计算开始
		function bjCountBegin(obj,idx,begin,end){
			checkInputNum(obj);
			if(obj.value==""){
				return ;
			}
			if(begin!=null && end!=null && !verdictNumMax(obj,begin,end)){
				return ;
			}
			bjCountTeacher(idx);
			//计算中分数
			var idxs=parseInt(document.getElementById("bjxxListSize").value);
			khzfCount(idxs,"grdf","bjdf","grzf","grzfTd");
		}

		//班级得分计算
		function bjCountTeacher(idx){
			var bjdmObj=document.getElementById("bjdm"+idx);
			var wsaqObj=document.getElementById("wsaq"+idx);
			var bbObj=document.getElementById("bb"+idx);
			var sqObj=document.getElementById("sq"+idx);
			var jlObj=document.getElementById("jl"+idx);
			var rsObj=document.getElementById("rs"+idx);
			var jliObj=document.getElementById("jli"+idx);
			var kjcObj=document.getElementById("kjc"+idx);
			var bjdfObj=document.getElementById("bjdf"+idx);
			var bjdfTdObj=document.getElementById("bjdfTd"+idx);
			//var grzfObj=document.getElementById("grzf");
			//var grzfTdObj=document.getElementById("grzfTd");
			//var grdfTdObj=document.getElementById("grdfTd");
			var count=intValue(wsaqObj.value)+intValue(bbObj.value)+
				intValue(sqObj.value)+intValue(jlObj.value)+intValue(jliObj.value)
				-intValue(kjcObj.value)+bjrsCount(rsObj.value);
			bjdfTdObj.innerHTML=count;
			bjdfObj.value=count;
			//grzfTdObj=count+intValue(grdfTdObj.innerHTML);
			//grzfObj=count+intValue(grdfTdObj.innerHTML);
		}

		//班级人数得分计算
		function bjrsCount(rs){
			if(rs!=""){
				return parseInt(rs)*2;
			}
			return 0;
		}

		//总分计算
		function khzfCount(idxs,grdfName,bjdfName,zfName,zfTdName){
			var zfs=0;
			if(bjdfName!=null){
				for(var i=0;i<idxs;i++){
					zfs=zfs+intValue(document.getElementById(bjdfName+i).value);
				}
				zfs=zfs/idxs;
			}
			if(grdfName!=null){
				zfs=zfs+intValue(document.getElementById(grdfName).value);
			}
			document.getElementById(zfName).value=zfs;
			document.getElementById(zfTdName).innerHTML=zfs;
		}

		//提交表单
		function submitBzrkh(url,cod){
			if(!bzrkhIsNull()){
				return ;
			}
			$('url').value=url;
			autoFillTeaInfo(cod);
		}

		//数据判断
		function bzrkhIsNull(){
			var bjxxListSize=document.getElementById("bjxxListSize");
			if(bjxxListSize.value=="" || bjxxListSize.value==0){
				alertInfo("当前班主任无班级无法评分s!");
				return false;
			}
			return true;
		}
		</script>
</head>
<body>
	<html:form action="/bjgm_fdykh" method="post">
		<input type="hidden" name="xyV" id="xyV" value="" />
		<input type="hidden" id="url" name="url"
			value="bjgm_fdykh_bzrkhAdd.do?method=bzrkhAdd" />
		<logic:present name="bjxxListSize">
			<input type="hidden" name="bjxxListSize" value="${bjxxListSize}" id="bjxxListSize"/>
		</logic:present>
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>班主任考核信息维护</a>
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
						<input type="hidden" name="xn" id="xn" value="${bzrkhList[0].xn}"/>
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
						<input type="hidden" name="xq" id="xq" value="${bl.xq}"/>
					</logic:present>
				</td>
			</tr>
			<tr>
				<th width="16%">
					<font color="red">*</font>职工号				
				</th>
				<td width="34%">
					${rs.zgh}
					<input type="hidden" name="zgh" id="zgh" value="${rs.zgh}"/>
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
						<html:text property="cq" styleId="cq" onkeyup="grCount(this,0,60);" onchange="grCount(this,0,60);" maxlength="5" value="${bzrkhList[0].cq}"  style="width: 150px">
						</html:text>
					</logic:present>
					<logic:notPresent name="bzrkhList">
						<html:text property="cq" styleId="cq" onkeyup="grCount(this,0,60);" onchange="grCount(this,0,60);" maxlength="5" value=""  style="width: 150px">
						</html:text>
					</logic:notPresent>
				</td>
				<th>
					材料
				</th>
				<td>
					<logic:present name="bzrkhList">
						<html:text property="cl" styleId="cl" maxlength="5" onkeyup="grCount(this,0,30);" onchange="grCount(this,0,30);" value="${bzrkhList[0].cl}" style="width: 150px">
						</html:text>
					</logic:present>
					<logic:notPresent name="bzrkhList">
						<html:text property="cl" styleId="cl" maxlength="5" onkeyup="grCount(this,0,30);" onchange="grCount(this,0,30);" value="" style="width: 150px">
						</html:text>
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<th>年级</th>
				<td>
					<logic:present name="bzrkhList">
						<html:text property="nj" styleId="nj" maxlength="5" onkeyup="grCount(this,0,3);" onchange="grCount(this,0,3);" value="${bzrkhList[0].nj}" style="width: 150px">
						</html:text>
					</logic:present>
					<logic:notPresent name="bzrkhList">
						<html:text property="nj" styleId="nj" maxlength="5" onkeyup="grCount(this,0,3);" onchange="grCount(this,0,3);" value="" style="width: 150px">
						</html:text>
					</logic:notPresent>
				</td>
				<th>学生课</th>
				<td>
					<logic:present name="bzrkhList">
						<html:text property="xsk" styleId="xsk" maxlength="5" value="${bzrkhList[0].xsk}" onkeyup="grCount(this,0,7);" onchange="grCount(this,0,7);" style="width: 150px">
						</html:text>
					</logic:present>
					<logic:notPresent name="bzrkhList">
						<html:text property="xsk" styleId="xsk" maxlength="5" value="" onkeyup="grCount(this,0,7);" onchange="grCount(this,0,7);" style="width: 150px">
						</html:text>
					</logic:notPresent>
				</td>
			</tr>
			<tr>
				<th>班主任得分
					<input type="hidden" name="grdf" id="grdf" <logic:present name="bzrkhList">value="${bzrkhList[0].grdf}"</logic:present> <logic:present name="bjxxListSize"> onchange="khzfCount(${bjxxListSize},'grdf','bjdf')" </logic:present>/> 
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
					<input type="hidden" name="bjdms" id="bjdm${idx}" value="${bj.bjdm}"/>
				</th>
				<td>
					<input type="text" name="wsaq" id="wsaq${idx}" value="${bj.wsaq}" maxlength="5" style="width:50px;" onkeyup="bjCountBegin(this,'${idx}',0,100)" onchange="bjCountBegin(this,'${idx}',0,100)"/>
				</td>
				<td>
					<input type="text" name="bb" id="bb${idx}" value="${bj.bb}" maxlength="5" style="width:50px;" onkeyup="bjCountBegin(this,'${idx}',0,20)" onchange="bjCountBegin(this,'${idx}',0,20)"/>
				</td>
				<td>
					<input type="text" name="sq" id="sq${idx}" value="${bj.sq}" maxlength="5" style="width:50px;" onkeyup="bjCountBegin(this,'${idx}',0,160)" onchange="bjCountBegin(this,'${idx}',0,160)"/>
				</td>
				<td>
					<input type="text" name="jl" id="jl${idx}" value="${bj.jl}" maxlength="5" style="width:50px;" onkeyup="bjCountBegin(this,'${idx}',0,100)" onchange="bjCountBegin(this,'${idx}',0,100)"/>
				</td>
				<td>
					<input type="text" name="rs" id="rs${idx}" value="${bj.rs}" maxlength="5" style="width:50px;" onkeyup="bjCountBegin(this,'${idx}')" onchange="bjCountBegin(this,'${idx}')"/>
				</td>
				<td>
					<input type="text" name="jli" id="jli${idx}" value="${bj.jli}" maxlength="5" style="width:50px;" onkeyup="bjCountBegin(this,'${idx}',5,20)" onchange="bjCountBegin(this,'${idx}',5,20)"/>
				</td>
				<td>
					<input type="text" name="kjc" id="kjc${idx}"  value="${bj.kjc}" maxlength="5" style="width:50px;" onkeyup="bjCountBegin(this,'${idx}')" onchange="bjCountBegin(this,'${idx}')"/>
					<input type="hidden" name="bjdf" id="bjdf${idx}" value="${bj.bjdf}" />
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
					总分<input type="hidden" name="grzf" id="grzf" <logic:present name="bzrkhGrzf"> value="${bzrkhGrzf}" </logic:present>/>
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
					<button type="button" id="buttonSave" onclick="submitBzrkh('bjgm_fdykh_bzrkhUpdate.do',13);return false;">保存</button>
					<button type="button" id="buttonClose" onclick="window.close();return false;">关闭</button>
				</th>
			</tr>
		</table>
		</td></tr>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
				var message=document.getElementById("message");
			if(message.value!=""){
				if(message.value=="保存失败!当前老师已审核!"){
					alertInfo("保存失败!当前老师已审核!");
				}else{
					alertInfo(message.value, function(){
						Close();
						if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
							window.dialogArguments.document.getElementById('search_go').click();
						}	
					});
				}
			}
			
		</script>
	</logic:present>
</body>
</html>