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
			var fzqj = jQuery("#fzqj_"+ind).val();
			if(fzqj.length>0){
				var fzqjarr = fzqj.split('-');
				var srfz = jQuery(obj).val();
				if(fzqjarr.length == 1){
					if(srfz !="" &&( eval(srfz)<0 || eval(srfz)>eval(fzqjarr[0]))){
						jQuery(obj).val("");
						alertInfo("输入的数值必须在分值区间内！",function(){
							jQuery(obj).focus();
						});
						return false;
					}
				}else if(fzqjarr.length == 2){
					var fzDown = fzqjarr[0];
					var fzUp = fzqjarr[1];
					if(srfz !="" &&( eval(srfz)<0 ||  eval(srfz)>fzUp ||  eval(srfz)<fzDown)){
						jQuery(obj).val("");
						alertInfo("输入的数值必须在分值区间内！",function(){
							jQuery(obj).focus();
						});
						return false;
					}
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
					var khzgh =document.getElementById("khzgh").value;
					saveUpdate(	'bjlh_fdykh.do?method=fdykhXskhpf&khzgh='+khzgh+'&doType=save','');
				}
			});
		}
		function fh(){
			var url='bjlh_fdykh.do?method=fdykhXskhcp';
			refreshForm(url);
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdykh" method="post">
	<input type="hidden" name="khzgh" id="khzgh" value="${khzgh }"/>
		<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
		</div>
		<logic:equal value="fh" name="fh">
		<div class="toolbox">
			<div class="buttonbox">
				<ul>
						<li><a href="javascript:void(0);" onclick="fh();" class="btn_fh">返回</a></li>
				</ul>
			</div>
		</div>
		</logic:equal>
		
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
				<th style="width: 12%">分值</th>
				<th style="width: 13%">得分</th>
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
					<td width="30px">${xm.fzqj }</td>
					<input type="hidden"  id="fzqj_${ind}" value="${xm.fzqj }"/>
					<td width="35px">
						<input type="hidden" name="xmid" value="${xm.xmid }"/>
						
						<logic:equal value="false" name="khbsfzd">
						<input type="text" id="df" name="df" maxlength="5" style="width: 35px"
							onblur="checkValue(this,${ind})" value="${xm.df}" /><span style="color: red">*</span>
						</logic:equal>
						<logic:notEqual value="false" name="khbsfzd">
							<input type="text" id="df" name="df" maxlength="5" style="width: 35px"
							onblur="checkValue(this,${ind})" value="${xm.df}"  readonly="readonly" /><span style="color: red">*</span>
						</logic:notEqual>
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
