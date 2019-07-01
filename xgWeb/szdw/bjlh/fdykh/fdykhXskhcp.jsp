<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script>
		function checkValue(obj,ind){
			checkInputNum(obj);
			var fzqj = document.getElementById("fzqj_"+ind).value;
			if(fzqj!=null){
				var fzDown = fzqj.split("-")[0];
				var fzUp = fzqj.split("-")[1];
				
				if(obj.value !="0" &&( eval(obj.value)<0 || eval(obj.value)>eval(fzUp) || eval(obj.value)<eval(fzDown))){
					obj.value="";
					alert("输入的数值必须在分值区间内！");
					obj.focus();
				}
			}
		}
		function saveXskhcp(obj){
			var df = document.getElementsByName("df");
			for(var i=0;i< df.length;i++){
				if(df[i].value==""){
					alertInfo("得分项不可为空！");
					return false;
				}
			}
			confirmInfo("你确定要保存吗？保存后不可再次修改！",function(data){
				if("ok"==data){
					saveUpdate(	'/xgxt/bjlh_fdykh.do?method=fdykhXskhcp&doType=save','');
				}
			});
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdykh" method="post">
		<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="95%">		
			<thead>
				<tr>
					<th colspan="5"><span>辅导员测评表：${rs.khbmc}</span></th>
				</tr>
			</thead>
			<tbody>	
			<tr>
				<td colspan="5">				
					 学年：${rs.xn }
					&nbsp;&nbsp;
					 所在部门：${rs1.bmmc }
					&nbsp;&nbsp;
					 辅导员姓名：${rs1.xm }
					<html:hidden property="khbid" name="rs" styleId="khbid"/>
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th>一级指标</th>
				<th>二级指标</th>
				<th>考核内容</th>
				<th>分值区间</th>
				<th>得分</th>
			</tr>
			</thead>
			<tbody>	
			<logic:present name="xmList">
			<logic:iterate id="xm" name="xmList" indexId="ind"> 
				<tr>
					<logic:notEmpty name="xm" property="yjzbRowNum">
						<td width="150px" style="word-break:break-all"  rowspan="${xm.yjzbRowNum}">${xm.yjzb }</td>
					</logic:notEmpty>
					
					<td width="150px" style="word-break:break-all" >${xm.ejzb }</td>
					<td width="300px" style="word-break:break-all" >${xm.khnr }</td>
					<td width="25px"><font id="fzqj_${ind}" value="${xm.fzqj }">${xm.fzqj }</font></td>
					<td width="35px">
						<input type="hidden" name="xmid" value="${xm.xmid }"/>
						<input type="text" id="df" name="df" maxlength="5" style="width: 40px"
							onblur="checkValue(this,${ind})" value="${xm.df}"/>
						<font class="red">*</font>
					</td>
				</tr>
			</logic:iterate>
			</logic:present>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="5">
			        	<div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          	<logic:equal value="false" name="khbsfzd">
				            	<button type="button" name="保存" id="buttonSave" onclick="saveXskhcp(this)">保存</button>
				            </logic:equal>
								<button type="button" name="重置" type="reset">重 置</button>	
				          </div>
				    </td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<script type="text/javascript">
			alertInfo("${message}");
		</script>
	</logic:present>
</body>
</html>
