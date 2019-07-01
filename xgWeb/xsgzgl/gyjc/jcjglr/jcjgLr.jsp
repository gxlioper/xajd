<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcjglr/js/jcjglr.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" >
		</script>
	</head>
	<body>
		<html:form action="/gyjc_jcjglr" method="post" styleId="JcjglrForm" onsubmit="return false;">
		<html:hidden property="lddm" styleId="lddm"/>
		<html:hidden property="xydm" styleId="xydm"/>
		<html:hidden property="qsh" styleId="qsh"/>
		<html:hidden property="js" styleId="js"/>
		<input type="hidden" id="sfcc" value ="1" />
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>寝室信息
				</span>
				</th>
			</tr>
			</thead>
			<tbody>
			    <tr>
					<th>楼栋</th>
					<td id="ldmc" width="30%">${qsxx.ldmc}</td>
					<th width="20%">层号</th>
					<td id="ch">${qsxx.ch}</td>
				</tr>
				<tr>
					<th width="20%">寝室</th>
					<td id="qshTd" width="30%">${qsxx.qsh}</td>
					<th width="20%">学院</th>
					<td id="xymc">${qsxx.xymc}</td>
				</tr>
			</tbody>
			<logic:equal value="1" name="jcmxMap" property="wsjc">
			<thead>
				<tr>
					<th colspan="4">
					<span>卫生检查&nbsp;&nbsp;
					<input type="hidden" id="wsjc" value="${jcmxMap.wsmxid}" name="mxidflag"/>
					<logic:equal name="jcmxMap" value="0" property="wstjzt">
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('1','wsjc');">增加检查项</a>
					</logic:equal>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="wsjcTbody">
			  <tr>
			  <th><div align="center">卫生区块</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  <th ><div align="center">操作</div></th>
			  </tr>
			  <logic:iterate id="i" name="wsjcPfList">
			  		<tr class='jcxmTr'>
			  			<td>
			  				<logic:equal value="1" name="jcmxMap" property="wstjzt">
			  					${i.fjmc}
			  				</logic:equal>
			  				<logic:equal value="0" name="jcmxMap" property="wstjzt">
			  					<html:select  style="width:80%" styleClass="jclxSel" property="fjidmark" value="${i.fjid}" onchange="changeJclx(this,'wsjc');">
			  						<html:options collection="wsjcFjSeList" property="guid" labelProperty="wsqkyq"/>
			  					</html:select>
			  				</logic:equal>
			  				<input type="hidden" name="mxid" value="${i.mxid}" />
			  			</td>
			  			<td colspan="2">
			  				<logic:equal value="1" name="jcmxMap" property="wstjzt">
			  					${i.wsqkyq}<input type="hidden" name="pfid" value="${i.pfid}" />
			  				</logic:equal>
			  				<logic:equal value="0" name="jcmxMap" property="wstjzt">
			  					<html:select  style="width:80%" styleClass="pfidSel" property="pfid" value="${i.pfid}" >
			  						<html:options collection="wsjcZjSeList" property="guid" labelProperty="wsqkyq"/>
			  					</html:select>
			  				</logic:equal>
			  			</td>
			  			<td align="center">
			  				<logic:equal value="0" name="jcmxMap" property="wstjzt">
			  					<a class='name' href='javascript:;' onclick='deleteTr(this)'>删除</a>
			  				</logic:equal>
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <logic:equal value="2" name="jcmxMap" property="aqjc">
			  <thead>
				<tr>
					<th colspan="4">
					<span>安全检查&nbsp;&nbsp;
					<input type="hidden" id="aqjc" value="${jcmxMap.aqmxid}" name="mxidflag"/>
					<logic:equal name="jcmxMap" value="0" property="aqtjzt">
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('2','aqjc');">增加检查项</a>
					</logic:equal>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="aqjcTbody">
			  <tr>
			  <th><div align="center">项目</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  <th ><div align="center">操作</div></th>
			  </tr>
			    <logic:iterate id="i" name="aqjcPfList">
			  		<tr class='jcxmTr'>
			  			<td>
			  				<logic:equal value="1" name="jcmxMap" property="aqtjzt">
			  					${i.fjmc}
			  				</logic:equal>
			  				<logic:equal value="0" name="jcmxMap" property="aqtjzt">
			  					<html:select style="width:80%" styleClass="jclxSel" property="fjidmark" value="${i.fjid}" onchange="changeJclx(this,'aqjc');">
			  						<html:options collection="aqjcFjSeList" property="guid" labelProperty="wsqkyq"/>
			  					</html:select>
			  				</logic:equal>
			  				<input type="hidden" name="mxid" value="${i.mxid}" />
			  			</td>
			  			<td colspan="2">
			  				<logic:equal value="1" name="jcmxMap" property="aqtjzt">
			  					${i.wsqkyq}<input type="hidden" name="pfid" value="${i.pfid}" />
			  				</logic:equal>
			  				<logic:equal value="0" name="jcmxMap" property="aqtjzt">
			  					<html:select style="width:80%" styleClass="pfidSel" property="pfid" value="${i.pfid}" >
			  						<html:options collection="aqjcZjSeList" property="guid" labelProperty="wsqkyq"/>
			  					</html:select>
			  				</logic:equal>
			  			</td>
			  			<td align="center">
			  				<logic:equal value="0" name="jcmxMap" property="aqtjzt">
			  					<a class='name' href='javascript:void(0);' onclick='deleteTr(this)'>删除</a>
			  				</logic:equal>
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <logic:equal value="3" name="jcmxMap" property="jljc">
			 <thead>
				<tr>
					<th colspan="4">
					<span>纪律检查&nbsp;&nbsp;
					<input type="hidden" id="jljc" value="${jcmxMap.jlmxid}" name="mxidflag"/>
					<logic:equal name="jcmxMap" value="0" property="jltjzt">
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('3','jljc');">增加检查项</a>
					</logic:equal>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="jljcTbody">
			  <tr>
			  <th><div align="center">项目</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  <th ><div align="center">操作</div></th>
			  </tr>
			   <logic:iterate id="i" name="jljcPfList">
			  		<tr class='jcxmTr'>
			  			<td>
			  				<logic:equal value="1" name="jcmxMap" property="jltjzt">
			  					${i.fjmc}
			  				</logic:equal>
			  				<logic:equal value="0" name="jcmxMap" property="jltjzt">
			  					<html:select style="width:80%" styleClass="jclxSel" property="fjidmark" value="${i.fjid}" onchange="changeJclx(this,'jljc');">
			  						<html:options collection="jljcFjSeList" property="guid" labelProperty="wsqkyq"/>
			  					</html:select>
			  				</logic:equal>
			  				<input type="hidden" name="mxid" value="${i.mxid}" />
			  			</td>
			  			<td colspan="2">
			  				<logic:equal value="1" name="jcmxMap" property="jltjzt">
			  					${i.wsqkyq}<input type="hidden" name="pfid" value="${i.pfid}" />
			  				</logic:equal>
			  				<logic:equal value="0" name="jcmxMap" property="jltjzt">
			  					<html:select style="width:80%" styleClass="pfidSel" property="pfid" value="${i.pfid}" >
			  						<html:options collection="jljcZjSeList" property="guid" labelProperty="wsqkyq"/>
			  					</html:select>
			  				</logic:equal>
			  			</td>
			  			<td align="center">
			  				<logic:equal value="0" name="jcmxMap" property="jltjzt">
			  					<a class='name' href='javascript:void(0);' onclick='deleteTr(this)'>删除</a>
			  				</logic:equal>
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <thead>
				<tr>
					<th colspan="4">
					<span>附件信息<html:hidden property="fjid" styleId="fjid" value="${jcmxMap.fjid}" />
					</span>
							<button type="button" onclick="addFj();return false;" class="btn_01">增加</button>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr>
				<td colspan="4">
					<table width="100%">
						<thead>
							<tr>
								<th width="50%" colspan="2">
									文件
								</th>
								<th width="50%"  colspan="2">
									操作
								</th>
							</tr>
						</thead>
						<tbody id="tbody_fj">
							<logic:iterate id="i" name="yscwjList">
								<tr>
									<td colspan="2">${i.originalname}</td>
									<td colspan="2"><a  href="javascript:void(0)" onclick="downloadFile(this)">下载</a>
										<a hhref="javascript:void(0)" onclick="delFile(this)">删除</a>
										<input type="hidden" value="${i.fid}"  name="fid"/>
									</td>
								</tr>
							</logic:iterate>
							
						</tbody>
					</table>
				</td>			  
			  </tr>
			  <!--  
			  <tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="fjid" styleId="fjid" value="${jcmxMap.fjid}" />
								<%--@ include file="/xsgzgl/comm/fileUpload/f.jsp"--%>
								<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 50,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'fjid'
													});
											});
										</script>
									
							</td>
						</tr>
						-->
			  </tbody>
			  </table>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
							<!-- 
								<button type="button" onclick="saveLrjg('0')">
									保 存
								</button>
								 -->
								<button type="button" onclick="saveLrjg('1')">
									提交
								</button>
								
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>