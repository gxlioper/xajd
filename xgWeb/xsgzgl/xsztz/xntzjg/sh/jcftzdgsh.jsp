<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("请将必填项填写完整！");
				return false;
			}
			var url = "jcftz_sh.do?method=sbDgsh&type=save";
			ajaxSubFormWithFun("jcftzshForm",url,function(data){
				 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		}
				});
		}
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.rdsqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.rdsplc}&shid=${rs.shid}");
			});
		</script>
	</head>
	<body>
		<html:form action="/jcftz_sh" method="post" styleId="jcftzshForm">
		<html:hidden name="rs" property="rdsqid" styleId="rdsqid"/>
		<html:hidden name="rs" property="xmdm" styleId="xmdm"/>
		<html:hidden name="rs" property="rdsplc" styleId="rdsplc"/>
		<html:hidden name="rs" property="shid" styleId="shid"/>
		<html:hidden name="rs" property="csms" styleId="csms"/>
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:equal name="xmlx" value="1" >
						   <tr>
							<th>项目名称</th>
							<td>
								${hdmap.xmmc}
							</td>
							<th>项目级别</th>
							<td id="xmjbmc" >
								${hdmap.xmjbmc}
                           </td>
						</tr>
						<tr>
							<th>学年</th>
							<td id="xn" >
								${hdmap.xn}
							</td>
							<th>学期</th>
							<td id="xq" >
								${hdmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>申报部门</th>
							<td id="sbbmmc" >
								${hdmap.sbbmmc}
							</td>
							<th>联系方式</th>
							<td id="lxdh" name="lxdh">
								${hdmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>所属科目</th>
							<td id="sskmmc" name="sskmmc">
								${hdmap.sskmmc}
							</td>
							<th>基础学分</th>
							<td id="jcxf" name="jcxf">
								${hdmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>可参与人数</th>
							<td id="kcyrs" name="kcyrs">
								${hdmap.kcyrs}
							</td>
							<th>已申请人数</th>
							<td id="sqrs" name="sqrs">
								${hdmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>已通过人数</th>
							<td id="tgrs" name="tgrs">
								${hdmap.tgrs}
							</td>
							<th>项目开始时间</th>
							<td id="xmkssj" name="xmkssj">
								${hdmap.xmkssj}
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xmlx" value="2" >
							          <tr>
							<th width="20%">项目名称</th>
							<td width="30%">
								${xmxxmap.xmmc}
							</td>
							<th width="20%">项目级别</th>
							<td width="30%" id="xmjbmc" >
							  	${xmxxmap.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>学年</th>
							<td id="xn" >
								${xmxxmap.xn}
							</td>
							<th>学期</th>
							<td id="xq" >
							    ${xmxxmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>申报部门</th>
							<td id="sbbmmc" >
								${xmxxmap.bmmc}
							</td>
							<th>联系方式</th>
							<td id="lxdh" name="lxdh">
								${xmxxmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>所属科目</th>
							<td id="sskmmc" name="sskmmc">
								${xmxxmap.sskmmc}
							</td>
							<th>基础学分</th>
							<td id="jcxf" name="jcxf">
								${xmxxmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>可参与团队数</th>
							<td id="kcyrs" name="kcyrs">
								${xmxxmap.kcyrs}
							</td>
							<th>已申请团队数</th>
							<td id="sqrs" name="sqrs">
								${xmxxmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>已通过团队数</th>
							<td id="tgrs" name="tgrs">
								${xmxxmap.tgrs}
							</td>
							<th>活动开始时间</th>
							<td id="xmkssj" name="xmkssj">
								${xmxxmap.xmkssj}
							</td>
	                   </tr>
	          
					</logic:equal>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审批信息</span>
							</th>
						</tr>
					</thead>					
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">
								
								</td>
							</tr>				
				       </tbody>
				       <thead>
							<tr>
								<th colspan="4">
									<span>审核信息</span>
								</th>
							</tr>
						</thead>
					<tbody>
						<tr>
							<tr>
								<th >
									审核结果
								</th>
								<td id="shjgSpan">
									
								</td>
							</tr>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=jcftzsh&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>
