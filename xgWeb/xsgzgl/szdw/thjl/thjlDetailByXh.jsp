<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head> 
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<style type="text/css">	
			.demo_data2 {
			   /* border: 1px solid #DEDEDE;*/
				display: inline;
			    float: left;
			    height: 15px;
			    margin: 0px 0px 0;
			    padding: 0px;
			    width: 80px;
			}
		</style>
		<script language="javascript" src="xsgzgl/szdw/thjl/js/thjlDetailByXh.js"></script>
		
		<script language="javascript" defer="defer">
		jQuery(function() {
			jQuery("#myTbody").css("display","none");
		});
		function showTbody(obj,objTbody){
			if(obj.className=="up"){
				obj.className="down";
				obj.parentNode.parentNode.className="cur-tr";
				document.getElementById(objTbody).style.display="none";
			}else{
				obj.className="up";
				obj.parentNode.parentNode.className="";
				document.getElementById(objTbody).style.display="";
			}
		}
		
		</script>
	</head>
	<body onload="init();">
	<input type="hidden" name="knlxdm" id="knlxdm" value="${thjlInfo.knlxdm}" />
		<html:form action="/szdw_thjl" method="post">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:471px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr > 
							<th colspan="4">
								<span>学生基本信息</span><div align="right"></div>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
					<tr >
						<th colspan="4">
							<span>学籍异动信息</span>
						</th>
					</tr>
					</thead>
					<tbody id="xjydInfo">
					<tr>
						<td colspan="4">
							<style>
								#shlccx_table th{text-align: center;}
								#shlccx_table tr{text-align: center;}
							</style>
							<div class="con_overlfow">
								<table id="shlccx_table" width="100%" class="formlist" >
									<logic:notEmpty name="xjydList">
										<tr>
											<th>学年</th>
											<th>学期</th>
											<th>异动类别</th>
											<th>原年级</th>
											<th>原专业</th>
											<th>原班级</th>
											<th>学籍异动文号</th>
											<th>异动时间</th>
										</tr>
										<logic:iterate id="i" name="xjydList">
											<tr>
												<td>${i.xn}</td>
												<td>${i.xq}</td>
												<td>${i.ydlbmc}</td>
												<td>${i.ydqnj}</td>
												<td>${i.ydqzymc}</td>
												<td>${i.ydqbjmc}</td>
												<td>${i.xjydwh}</td>
												<td>${i.xjydsj}</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="xjydList">
										<tr>
											<td>暂无学籍异动信息</td>
										</tr>
									</logic:empty>
								</table>
							</div>
						</td>
					</tr>
					</tbody>
					  <thead>
						<tr >
							<th colspan="4">
								<span>谈话记录信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="thjlInfo">
					<tr>
						<th width="16%">
								谈话教师
						</th>
						<td width="34%">
							${thjlInfo.jsxm}
						</td>
						<th width="16%">
								职工号
						</th>
						<td width="34%">
							${thjlInfo.zgh}
							<input type="hidden" id="zgh" name="zgh" value="${thjlInfo.zgh }"  />
						</td>						
					</tr>
					<tr>
						<th width="16%">
									性别
						</th>
						<td width="34%">
							${thjlInfo.jsxb}
						</td>
						<th width="16%">
									部门
						</th>
						<td width="34%">
							${thjlInfo.jsbmmc}
						</td>
					</tr>
					<tr>
						<th width="16%">
								谈话日期
						</th>
						<td width="34%" colspan="3">
							${thjlInfo.thsj }
						</td>
					</tr>
					<logic:notEqual name="xxdm" value="10351">
					<tr>
						<th width="16%">
								谈话时段
						</th>
						<td width="34%" >
							<logic:notEqual  name="thjlInfo" property="kssj" value="">
								${thjlInfo.kssj }&nbsp;至&nbsp;${thjlInfo.jssj }
							</logic:notEqual>
						</td>
						<th width="16%">
								谈话时长
						</th>
						<td width="34%" >
							<logic:notEqual  name="thjlInfo" property="thsc" value="">
								${thjlInfo.thsc }分
							</logic:notEqual>
						</td>
					</tr>
					</logic:notEqual>
					<tr>
						<th width="16%">
								谈话类型
						</th>
						<td width="34%" colspan="3">
							${thjlInfo.thlxmc }
						</td>
					</tr>
					<tr>
					</tr>
					<tr style="height:80px;">
						<th>
							谈话内容
						</th>
						<td colspan="3">
							${thjlInfo.thnr}
						</td>
					</tr>
					<tr>
						<th>附件</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="filepath" value="${thjlInfo.filepath}"/>
							<script type="text/javascript">
                                //调用附件
                                jQuery(function(){
                                    var gid = jQuery('#filepath').val();
                                    jQuery.MultiUploader_q({
                                        gid : gid
                                    });
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th width="16%">
							是否重点关注
						</th>
						<td width="34%" colspan="3">
							${thjlInfo.sfzdgzmc }
						</td>
					</tr>
					<tr>
						<th width="16%">
							是否需要深度恳谈
						</th>
						<td width="34%">
								${thjlInfo.sfsdktmc }
						</td>
						<logic:equal name="thjlInfo" property="sfsdktmc" value="是">
							<th width="16%">
								何时组织深度恳谈
							</th>
							<td width="34%">
									${thjlInfo.sdktsj }
							</td>
						</logic:equal>
					</tr>
					</tbody>
				
				</table>
				<br/>
				<table width="100%"  border="0" class="formlist">
				    <thead>
				      <tr>
				      	<th colspan="2">
				      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">历史谈话记录</a>
				   	    </th>
				      </tr>
				    </thead>
				</table>
				<div class="regform" style="padding-top:0px;">
					<div id="myTbody" style="padding-bottom:10px;">
						<logic:notEmpty name="hisThjlList">
							<table width="100%" border="0" class="formlist">
							  <tbody>
								<logic:iterate name="hisThjlList" id="hisThjl" indexId="index">
									<tr onclick="deploy('${index}');return false" title="[展开/隐藏]"><th width="16%">谈话日期</th><td width="34%">${hisThjl.thsj}</td><th width="16%">谈话教师</th>
										<td><span>${hisThjl.jsxm}</span><span style="float: right;"><a href="#" class="up">展开/隐藏</a></span></td>
									</tr>
									<logic:notEqual value="10351" name="xxdm">
									<tr class="${index}" style="display:none" height="50">
										<th width="16%" align="right" >谈话时段</th>
										<td align="left">
											<logic:notEqual  name="hisThjl" property="kssj" value="">
												${hisThjl.kssj }&nbsp;至&nbsp;${hisThjl.jssj }
											</logic:notEqual>
										</td>
										<th width="16%" align="right" >谈话时长</th>
										<td align="left">
											<logic:notEqual  name="hisThjl" property="thsc" value="">
												${hisThjl.thsc }分
											</logic:notEqual>
										</td>
									</tr>
									</logic:notEqual>
									<tr class="${index}" style="display:none" height="50">
										<th width="16%" align="right" >谈话类型</th>
										<td colspan="3" align="left">${hisThjl.thlxmc }</td>
									</tr>
									<logic:notEqual value="10351" name="xxdm">
									<tr class="${index}" style="display:none" height="50">
										<th width="16%" align="right" >困惑和问题</th>
										<td colspan="3" align="left">${hisThjl.khhwt }</td>
									</tr>
									</logic:notEqual>
									<logic:equal value="10351" name="xxdm">
										<tr class="${index}" style="display:none" >
											<th width="16%" align="right" >困惑和问题</th>
											<td colspan="3" align="left">${hisThjl.khhwtmc }</td>
										</tr>
										<tr class="${index}" style="display:none">
											<th width="16%" align="right" >问题描述</th>
											<td colspan="3" align="left">${hisThjl.wtmsmc }</td>
										</tr>
									</logic:equal>

									<tr class="${index}" style="display:none" height="50">
										<logic:notEqual value="10351" name="xxdm">
										<th width="16%" align="right" >面谈要点</th>
										</logic:notEqual>
										<logic:equal value="10351" name="xxdm">
										<th width="16%" align="right" >面谈地点及方式</th>
										</logic:equal>
										<td colspan="3" align="left">${hisThjl.mtyd }</td>
									</tr>
									<logic:equal value="10351" name="xxdm">
										<tr class="${index}" style="display:none" height="50"><th width="16%" align="right" >与谈话人关系</th>
											<td colspan="3" align="left">${hisThjl.gxmc}</td>
										</tr>
										<tr  class="${index}" style="display:none" >
											<th width="16%" align="right" >提供帮助1</th>
											<td colspan="3" align="left">${hisThjl.tgbz1mc }</td>
										</tr>
										<tr  class="${index}" style="display:none" >
											<th width="16%" align="right" >提供帮助2</th>
											<td colspan="3" align="left">${hisThjl.tgbz2mc }</td>
										</tr>
										<tr class="${index}" style="display:none" >
											<th width="16%" align="right" >提供帮助3</th>
											<td colspan="3" align="left">${hisThjl.tgbz3mc }</td>
										</tr>
										<tr id="${index}" class="${index}" style="display:none" height="50">
											<th width="16%" align="right" >其他谈话录入</th>
											<td colspan="3" align="left">${hisThjl.thnr}</td>
										</tr>
									</logic:equal>
								<logic:notEqual value="10351" name="xxdm">
									<tr id="${index}" class="${index}" style="display:none" height="50"><th width="16%" align="right" >面谈小结及建议</th>
										<td colspan="3" align="left">${hisThjl.thnr}</td>
									</tr>
								</logic:notEqual>
									<logic:equal value="10351" name="xxdm">
										<tr class="${index}" style="display:none">
											<th width="16%" align="right" >帮助结果</th>
											<td colspan="3" align="left">${hisThjl.bzjgmc }</td>
										</tr>
									</logic:equal>
									<tr class="${index}" style="display:none" height="50">
										<th width="16%" align="right" >是否重点关注</th>
										<td align="left">
											${hisThjl.sfzdgzmc }
										</td>
										<logic:equal name="hisThjl" property="sfzdgzmc" value="是">
											<th width="16%" align="right" >关注等级</th>
											<td align="left">
												${hisThjl.gzdj }
											</td>
										</logic:equal>
										<logic:equal name="hisThjl" property="sfzdgzmc" value="否">
											<th width="16%" align="right" ></th>
											<td align="left">
											
											</td>
										</logic:equal>
										<logic:equal value="10351" name="xxdm">
											<logic:equal name="hisThjl" property="sfzdgzmc" value="是">
													<tr>
														<th width="16%">关注期限</th>
														<td width="34%">${hisThjl.gzqx }</td>
														<th width="16%">
														</th>
														<td width="34%">
														</td>
													</tr>
					                            </logic:equal>
					                       </logic:equal>
									</tr>
									<tr style="height:5px"></tr>
								</logic:iterate>
							  </tbody>
							</table>
						</logic:notEmpty>
						<logic:empty name="hisThjlList">
							<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> 没有更多谈话记录</span>			
						</logic:empty>
					</div>
				</div>
				</table>
			</div>
			<div>
				<table border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="Close();return false;">
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

