<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function cpwjDaSave(){
			var stid=document.getElementsByName("hidden_stid");//试题id
			var stmc=document.getElementsByName("hidden_stmc");//试题名称
			var stlx=document.getElementsByName("hidden_stlx");//试题类型

			var temp;//临时用户获取
			if(stid&&stid.length>0){
				for(var i=0;i<stid.length;i++){
					temp=document.getElementsByName("st_"+stid[i].value);
					
					if(temp&&temp.length>0){
						if(stlx[i].value=="1"){//单选题
							var b=false;
							for(var j=0;j<temp.length;j++){
								if(temp[j].checked){
									b=true;
								}
							}
							if(!b){
								alertInfo("请选择"+stmc[i].value);
								return false;
							}
						}else if(stlx[i].value=="2"){//简答题
							if(temp[0].value.trim()==""){
								alertInfo("请填写"+stmc[i].value);
								return false;
							}
						}
					}else{
						//如果为空数据异常
					}
				}
			}

			//////////////////////考核表验证start
			var df = document.getElementsByName("df");
			for(var i=0;i< df.length;i++){
				if(df[i].value==""){
					alertInfo("得分项不可为空！");
					return false;
				}
			}
			/////////////////////考核表验证end
			confirmInfo("确定提交吗？",function(data){
				if("ok"==data){
					var fdyid=document.getElementById("fdyid").value;
					saveData('bjlh_fdycpwj.do?method=cpwjglWjlrSingle&fdyid='+fdyid+'&doType=save','');
				}
			});
		}

		function checkValue(obj,ind){
			checkInputNum(obj);
			var fzqjGet = jQuery("#fzqj_"+ind);
			if(fzqjGet.length>0){
				var fzqj = fzqjGet.val();
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
		function fh(){
			var url='bjlh_fdycpwj.do?method=cpwjglWjlr';
			refreshForm(url);
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdycpwj" method="post">
		<html:hidden property="wjid" name="rs" styleId="wjid"/>		
		<input type="hidden" name="fdyid" id="fdyid" value="${fdyid }"/>
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
						<th><span>辅导员测评问卷222：${rs.wjmc}</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							学年：${xn}
							&nbsp;&nbsp;
						          所在部门:${fdyInfo.bmmc }
						    &nbsp;&nbsp;
						          辅导员姓名:${fdyInfo.xm }
						    &nbsp;&nbsp;
						</td>
					</tr>
				<logic:present name="stList">
					<logic:iterate id="st" name="stList">
					<tr>
						<td>
							<input type="hidden" name="hidden_stid" value="${st.stid}" />
							<input type="hidden" name="hidden_stmc" value="${st.stmc}" />
							<input type="hidden" name="hidden_stlx" value="${st.stlx}" />
							${st.r}、<bean:write name="st" property="stmc" format="true"/>
						<br/><br/>
							${st.xxHtml }
						</td>
					</tr>
					</logic:iterate>
				</logic:present>
				</tbody>
<%--				<tfoot>--%>
<%--					<tr>--%>
<%--					  	<td>--%>
<%--					  		<div class="bz">"<span class="red">*</span>"为必填项</div>--%>
<%--						    <div class="btn">--%>
<%--								<logic:equal value="false" name="xswjstsfzd">--%>
<%--				            		<button type="button" name="保存" id="buttonSave" onclick="cpwjDaSave();">保存</button>--%>
<%--			          			</logic:equal>--%>
<%--								<button type="button" name="重置" type="reset">重 置</button>	--%>
<%--						    </div>--%>
<%--					    </td>--%>
<%--					</tr>--%>
<%--			    </tfoot>--%>
			</table>
		</div>
		<logic:equal value="11417" name="xxdm">
		<div class="tab">
		<table class="formlist" width="95%">		
			<thead>
				<tr>
					<th colspan="5"><span>辅导员测评表：${rsKhb.khbmc}</span></th>
				</tr>
			</thead>
			<tbody>	
			<tr>
				<td colspan="5">				
					 学年：${rsKhb.xn }
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
				<th>分值</th>
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
					<td width="25px"><font value="${xm.fzqj}">${xm.fzqj}</font>
						<input type="hidden" id="fzqj_${ind}" value="${xm.fzqj }"/></td>
					<td width="50px">
						<input type="hidden" name="xmid" value="${xm.xmid }"/>
						<input type="text" id="df" name="df" maxlength="5" style="width: 15px"
							onblur="checkValue(this,${ind})" value="${xm.df}"/>
						<font class="red">*</font>
					</td>
				</tr>
			</logic:iterate>
			</logic:present>
			</tbody>
			<%--<tfoot>
			      <tr>
			        <td colspan="5">
			        	<div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          	<logic:equal value="false" name="khbsfzd">
				            	<button type="button" name="保存" id="buttonSave" onclick="cpwjDaSave();return false;">保存</button>
								<button type="button" name="重置" type="reset">重 置</button>
							</logic:equal>	
				          </div>
				    </td>
			      </tr>
			    </tfoot>--%>
		</table>
		</div>
		</logic:equal>
		<div class="tab">
			<table  class="formlist" width="95%">
				<tfoot>
			      <tr>
			        <td colspan="5">
			        	<div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          	<logic:equal value="false" name="khbsfzd">
				            	<button type="button" name="保存" id="buttonSave" onclick="cpwjDaSave();return false;">保存</button>
							</logic:equal>	
				          </div>
				    </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		
	</html:form>
	<logic:present name="back">
		<script type="text/javascript">
			alertInfo("${back}");
		</script>
	</logic:present>
</body>
</html>
