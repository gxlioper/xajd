<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	   <%@ include file="/syscommon/pagehead_V4.ini"%>
	   <script type="text/javascript" src="js/check.js"></script>
	   <script type="text/javascript" src="js/xszz/tjgy_xxcj.js"></script>
  </head>
  
  <body>
  		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<logic:present name="stu" property="xh">
			<logic:present name="rs">
				<logic:notEqual value="" name="rs" property="xysh">
					<div class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							当前申请记录的审核状态为<bean:message key="lable.xb" />审核"${rs.xysh }",学校审核"${rs.xxsh }"。
						</p>
						<a class="close" title="隐藏"
						   onclick="this.parentNode.style.display='none'"></a>
						   
						 <logic:notEqual value="未审核" name="rs" property="xysh">
							 <logic:notEqual value="退回" name="rs" property="xysh">
					 			<script defer>
					 				jQuery('#saveButton').attr('class','disabled');
					 				jQuery('#saveButton').attr('disabled',true);
					 			</script>
							 </logic:notEqual>
						 </logic:notEqual>
					</div>
				</logic:notEqual>
			</logic:present>
		</logic:present>
		
  		<html:form action="/xxcj" method="post">
  			<html:hidden property="xn" value="${xn }"/>
  			<html:hidden property="xq" value="${xq }"/>
  			<input type="hidden" id="url" name="url" value="/xxcj.do?method=xstx" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />		
  		
  			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveUpdate('xxcj.do?method=xstx&doType=save','xh')" id="saveButton">
										保 存
									</button>

									<button type="button" type="reset">
										重 置
									</button>
									<logic:equal value="add" name="xxcjForm" property="doType">
										<button type="button" onclick="refreshForm('xxcj.do?method=xxcjcx')">
											返回
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody name="xsxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<logic:equal value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" readonly="true"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType">
									<html:text property="xh" styleId="xh" value="${stu.xh}" 
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsjbxx','xh')"/>	
									<logic:notEqual value="modi" name="oper">
										<button type="button" onclick="showTopWin('stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</logic:notEqual>						
								</logic:notEqual>		
							</td>
							<th width="16%">姓名</th>
							<td width="34%">
								${stu.xm }
							</td>
						</tr>
						<tr>
							<th>身份证号</th>
							<td>
								${stu.sfzh }
							</td>
							<th>是否有学籍</th>
							<td>${stu.xjztm }</td>
						</tr>
						<tr>
							<th>专业号</th>
							<td>${stu.zydm }</td>
							<th>专业班级</th>
							<td>${stu.bjmc }</td>
						</tr>
						<tr>
							<th>入学年级</th>
							<td>${stu.nj }</td>
							<th>性别</th>
							<td>${stu.xb }</td>
						</tr>
						<tr>
							<th>民族</th>
							<td>${stu.mzmc }</td>
							<th>政治面貌</th>
							<td>${stu.zzmmmc }</td>
						</tr>
						<tr>
							<th>学生类别</th>
							<td>
								${stu.pycc }
							</td>
							<th>毕业日期</th>
							<td>${stu.byny }</td>
						</tr>
						<tr>
							<th>学制</th>
							<td>${stu.xz }</td>
							<th>籍贯</th>
							<td>${stu.jg }</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>采集信息</span>
								<p class="floatright normal">
									<a class="linkColor" href="xxcj.do?method=dowDoc&filePath=<%=request.getRealPath("") %>/print/xszz/tjgy_pkx.doc">贫困县名单</a>&nbsp;&nbsp;
									<a class="linkColor" href="xxcj.do?method=dowDoc&filePath=<%=request.getRealPath("") %>/print/xszz/tjgy_dzxb.doc"> 东中西部划分</a>&nbsp;&nbsp;
									<a class="linkColor" href="xxcj.do?method=dowDoc&filePath=<%=request.getRealPath("") %>/print/xszz/tjgy_zdjb.doc">重大疾病</a>
								</p>
							</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody1');return false">第一部分(基本信息)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody1" style="display:none">
						<tr>
							<th>学费</th>
							<td>
								<html:text property="xf" onblur="checkInputNum(this)" maxlength="10" name="rs"></html:text>
							</td>
							<th>烈士子女</th>
							<td>
								<html:radio property="lszn" value="1" name="rs"></html:radio>是
								<html:radio property="lszn" value="0" name="rs"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>孤儿</th>
							<td>
								<html:radio property="ge" value="1" name="rs"></html:radio>是
								<html:radio property="ge" value="0" name="rs"></html:radio>否
							</td>
							<th>低保</th>
							<td>
								<html:radio property="db" value="1" name="rs"></html:radio>是
								<html:radio property="db" value="0" name="rs"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>特困救助</th>
							<td>
								<html:radio property="tkjz" value="1" name="rs"></html:radio>是
								<html:radio property="tkjz" value="0" name="rs"></html:radio>否
							</td>
							<th>优抚</th>
							<td>
								<html:radio property="yf" value="1" name="rs"></html:radio>是
								<html:radio property="yf" value="0" name="rs"></html:radio>否
							</td>
						</tr>
						
						<tr>
							<th>残疾学生</th>
							<td>
								<html:radio property="cjxs" value="1" name="rs"></html:radio>是
								<html:radio property="cjxs" value="0" name="rs"></html:radio>否
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
								   onclick="showTbody(this,'myTbody2');return false">第二部分(东中西部划分)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody2" style="display:none">
						<tr>
							<th>东部市县</th>
							<td>
								<html:radio name="rs" property="dbsx" value="1" onclick="setDq(this,'dbpk','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>是
								<html:radio name="rs" property="dbsx" value="0" onclick="setDq(this,'dbpk','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>否
							</td>
							<th>东部村镇</th>
							<td>
								<html:radio name="rs" property="dbcz" value="1" onclick="setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>是
								<html:radio name="rs" property="dbcz" value="0" onclick="setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>东部国家贫困地区</th>
							<td>
								<html:radio name="rs" property="dbpk" value="1" onclick="setDq(this,'dbsx','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>是
								<html:radio name="rs" property="dbpk" value="0" onclick="setDq(this,'dbsx','dbcz');setDqfb(['dbsx','dbcz','dbpk'],['zbsx','zbcz','zbpk','xbsx','xbcz','xbpk'])"></html:radio>否
							</td>
							<th>中部市县</th>
							<td>
								<html:radio name="rs" property="zbsx" value="1" onclick="setDq(this,'zbpk','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>是
								<html:radio name="rs" property="zbsx" value="0" onclick="setDq(this,'zbpk','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>中部村镇</th>
							<td>
								<html:radio name="rs" property="zbcz" value="1" onclick="setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>是
								<html:radio name="rs" property="zbcz" value="0" onclick="setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>否
							</td>
							<th>中部国家贫困地区</th>
							<td>
								<html:radio name="rs" property="zbpk" value="1" onclick="setDq(this,'zbsx','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>是
								<html:radio name="rs" property="zbpk" value="0" onclick="setDq(this,'zbsx','zbcz');setDqfb(['zbsx','zbcz','zbpk'],['dbsx','dbcz','dbpk','xbsx','xbcz','xbpk'])"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>西部县市</th>
							<td>
								<html:radio name="rs" property="xbsx" value="1" onclick="setDq(this,'xbpk','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>是
								<html:radio name="rs" property="xbsx" value="0" onclick="setDq(this,'xbpk','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>否
							</td>
							<th>西部村镇</th>
							<td>
								<html:radio name="rs" property="xbcz" value="1" onclick="setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>是
								<html:radio name="rs" property="xbcz" value="0" onclick="setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>西部国家贫困地区</th>
							<td>
								<html:radio name="rs" property="xbpk" value="1" onclick="setDq(this,'xbsx','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>是
								<html:radio name="rs" property="xbpk" value="0" onclick="setDq(this,'xbsx','xbcz');setDqfb(['xbcz','xbsx','xbpk'],['dbsx','dbcz','dbpk','zbsx','zbcz','zbpk'])"></html:radio>否
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody3');return false">第三部分(家庭信息-基本情况)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody3" style="display:none">
						<tr>
							<th>双亲家庭</th>
							<td>
								<html:radio name="rs" property="sqjt" value="1" onclick="checkDqxx(this)"></html:radio>是
								<html:radio name="rs" property="sqjt" value="0" onclick="checkDqxx(this)"></html:radio>否
							</td>
							<th>单亲离父养</th>
							<td>
								<html:radio name="rs" property="dqlfy" value="1" onclick="checkDqfyxx(this)"></html:radio>是
								<html:radio name="rs" property="dqlfy" value="0" onclick="checkDqfyxx(this)"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲离母养</th>
							<td>
								<html:radio name="rs" property="dqlmy" value="1" onclick="checkDqfyxx(this)"></html:radio>是
								<html:radio name="rs" property="dqlmy" value="0" onclick="checkDqfyxx(this)"></html:radio>否
							</td>
							<th>单亲离其它</th>
							<td>
								<html:radio name="rs" property="dqlqt" value="1" onclick="checkDqfyxx(this)"></html:radio>是
								<html:radio name="rs" property="dqlqt" value="0" onclick="checkDqfyxx(this)"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲亡父养</th>
							<td>
								<html:radio name="rs" property="dqwfy" value="1" onclick="checkDqfyxx(this)"></html:radio>是
								<html:radio name="rs" property="dqwfy" value="0" onclick="checkDqfyxx(this)"></html:radio>否
							</td>
							<th>单亲亡母养</th>
							<td>
								<html:radio name="rs" property="dqwmy" value="1" onclick="checkDqfyxx(this)"></html:radio>是
								<html:radio name="rs" property="dqwmy" value="0" onclick="checkDqfyxx(this)"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲亡其它</th>
							<td>
								<html:radio name="rs" property="dqwqt" value="1" onclick="checkDqfyxx(this)"></html:radio>是
								<html:radio name="rs" property="dqwqt" value="0" onclick="checkDqfyxx(this)"></html:radio>否
							</td>
							<th>抚养费其他</th>
							<td>
								<html:radio name="rs" property="fyfqt" value="1" onclick="checkFyfqt(this)"></html:radio>是
								<html:radio name="rs" property="fyfqt" value="0" onclick="checkFyfqt(this)"></html:radio>否
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody4');return false">第四部分(家庭信息-父母工作情况)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody4" style="display:none">
						<tr>
							<th>父母都务农</th>
							<td>
								<html:radio name="rs" property="fmwn" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="fmwn" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
							<th>父工母农</th>
							<td>
								<html:radio name="rs" property="fgmn" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="fgmn" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>母工父农</th>
							<td>
								<html:radio name="rs" property="mgfn" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="mgfn" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
							<th>父母都工作</th>
							<td>
								<html:radio name="rs" property="fmgz" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="fmgz" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>父工母下岗</th>
							<td>
								<html:radio name="rs" property="fgmxg" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="fgmxg" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
							<th>母工父下岗</th>
							<td>
								<html:radio name="rs" property="mgfxg" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="mgfxg" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>父母下岗无工作</th>
							<td>
								<html:radio name="rs" property="fmxg" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="fmxg" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
							<th>父母下岗一方就业</th>
							<td>
								<html:radio name="rs" property="fmxgyfjy" value="1" onclick="setFmgz(this);"></html:radio>是
								<html:radio name="rs" property="fmxgyfjy" value="0" onclick="setFmgz(this);"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>家庭经济其它</th>
							<td>
								<html:radio name="rs" property="jtjjqt" value="1"></html:radio>是
								<html:radio name="rs" property="jtjjqt" value="0"></html:radio>否
							</td>
							<th>单亲有无工作情况</th>
							<td>
								<html:radio name="rs" property="ywgzqk" value="1"></html:radio>是
								<html:radio name="rs" property="ywgzqk" value="0"></html:radio>否
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody5');return false">第五部分(家庭信息-父母健康情况)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody5" style="display:none">
						<tr>
							<th>父母身体良好</th>
							<td>
								<html:radio name="rs" property="fmstlh" value="1" onclick="setFmst(this);"></html:radio>是
								<html:radio name="rs" property="fmstlh" value="0" onclick="setFmst(this);"></html:radio>否
							</td>
							<th>父亲身体偏差</th>
							<td>
								<html:radio name="rs" property="fqstcpc" value="1" onclick="setQm(this,'fqstjc','fcj')"></html:radio>是
								<html:radio name="rs" property="fqstcpc" value="0" onclick="setQm(this,'fqstjc','fcj')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲父亲身体偏差</th>
							<td>
								<html:radio name="rs" property="dqfqstpc" value="1" onclick="setQm(this,'dqfqstjc','dqfc')"></html:radio>是
								<html:radio name="rs" property="dqfqstpc" value="0" onclick="setQm(this,'dqfqstjc','dqfc')"></html:radio>否
							</td>
							<th>母亲身体偏差</th>
							<td>
								<html:radio name="rs" property="mqstpc" value="1" onclick="setQm(this,'mqstjc','mcj')"></html:radio>是
								<html:radio name="rs" property="mqstpc" value="0" onclick="setQm(this,'mqstjc','mcj')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲母亲身体偏差</th>
							<td>
								<html:radio name="rs" property="dqmqstpc" value="1" onclick="setQm(this,'dqmqstjc','dqmc')"></html:radio>是
								<html:radio name="rs" property="dqmqstpc" value="0" onclick="setQm(this,'dqmqstjc','dqmc')"></html:radio>否
							</td>
							<th>父亲身体极差</th>
							<td>
								<html:radio name="rs" property="fqstjc" value="1" onclick="setQm(this,'fqstcpc','fcj')"></html:radio>是
								<html:radio name="rs" property="fqstjc" value="0" onclick="setQm(this,'fqstcpc','fcj')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲父亲身体极差</th>
							<td>
								<html:radio name="rs" property="dqfqstjc" value="1" onclick="setQm(this,'dqfqstpc','dqfc')"></html:radio>是
								<html:radio name="rs" property="dqfqstjc" value="0" onclick="setQm(this,'dqfqstpc','dqfc')"></html:radio>否
							</td>
							<th>母亲身体极差</th>
							<td>
								<html:radio name="rs" property="mqstjc" value="1" onclick="setQm(this,'mqstpc','mcj')"></html:radio>是
								<html:radio name="rs" property="mqstjc" value="0" onclick="setQm(this,'mqstpc','mcj')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲母亲身体极差</th>
							<td>
								<html:radio name="rs" property="dqmqstjc" value="1" onclick="setQm(this,'dqmqstpc','dqmc')"></html:radio>是
								<html:radio name="rs" property="dqmqstjc" value="0" onclick="setQm(this,'dqmqstpc','dqmc')"></html:radio>否
							</td>
							<th>父母都是残疾人</th>
							<td>
								<html:radio name="rs" property="fmcj" value="1" onclick="setQm2(this,'fcj','mcj','fqstcpc','mqstpc','fqstjc','mqstjc')"></html:radio>是
								<html:radio name="rs" property="fmcj" value="0" onclick="setQm2(this,'fcj','mcj','fqstcpc','mqstpc','fqstjc','mqstjc')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲父残</th>
							<td>
								<html:radio name="rs" property="dqfc" value="1" onclick="setQm(this,'dqfqstpc','dqfqstjc')"></html:radio>是
								<html:radio name="rs" property="dqfc" value="0" onclick="setQm(this,'dqfqstpc','dqfqstjc')"></html:radio>否
							</td>
							<th>父残</th>
							<td>
								<html:radio name="rs" property="fcj" value="1" onclick="setQm(this,'fqstcpc','fqstjc','fmcj')"></html:radio>是
								<html:radio name="rs" property="fcj" value="0" onclick="setQm(this,'fqstcpc','fqstjc','fmcj')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>单亲母残</th>
							<td>
								<html:radio name="rs" property="dqmc" value="1" onclick="setQm(this,'dqmqstpc','dqmqstjc')"></html:radio>是
								<html:radio name="rs" property="dqmc" value="0" onclick="setQm(this,'dqmqstpc','dqmqstjc')"></html:radio>否
							</td>
							<th>母残</th>
							<td>
								<html:radio name="rs" property="mcj" value="1" onclick="setQm(this,'mqstpc','mqstjc','fmcj')"></html:radio>是
								<html:radio name="rs" property="mcj" value="0" onclick="setQm(this,'mqstpc','mqstjc','fmcj')"></html:radio>否
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody6');return false">第六部分(家庭信息-父母年龄情况)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody6" style="display:none">
						<tr>
							<th>父50以下</th>
							<td>
								<html:radio name="rs" property="fwsx" value="1" onclick="setQm(this,'fwls','flqs','qsys')"></html:radio>是
								<html:radio name="rs" property="fwsx" value="0" onclick="setQm(this,'fwls','flqs','qsys')"></html:radio>否
							</td>
							<th>父51-60</th>
							<td>
								<html:radio name="rs" property="fwls" value="1" onclick="setQm(this,'fwsx','flqs','qsys')"></html:radio>是
								<html:radio name="rs" property="fwls" value="0" onclick="setQm(this,'fwsx','flqs','qsys')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>父61-70</th>
							<td>
								<html:radio name="rs" property="flqs" value="1" onclick="setQm(this,'fwsx','fwls','qsys')"></html:radio>是
								<html:radio name="rs" property="flqs" value="0" onclick="setQm(this,'fwsx','fwls','qsys')"></html:radio>否
							</td>
							<th>父71以上</th>
							<td>
								<html:radio name="rs" property="qsys" value="1" onclick="setQm(this,'fwsx','fwls','flqs')"></html:radio>是
								<html:radio name="rs" property="qsys" value="0" onclick="setQm(this,'fwsx','fwls','flqs')"></html:radio>否
							</td>
						</tr>
						<tr>
							
							<th>母45以下</th>
							<td>
								<html:radio name="rs" property="mswyx" value="1" onclick="setQm(this,'msww','mwlw','mlls')"></html:radio>是
								<html:radio name="rs" property="mswyx" value="0" onclick="setQm(this,'msww','mwlw','mlls')"></html:radio>否
							</td>
							<th>母45-55</th>
							<td>
								<html:radio name="rs" property="msww" value="1" onclick="setQm(this,'mswyx','mwlw','mlls')"></html:radio>是
								<html:radio name="rs" property="msww" value="0" onclick="setQm(this,'mswyx','mwlw','mlls')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>母56-65</th>
							<td>
								<html:radio name="rs" property="mwlw" value="1" onclick="setQm(this,'mswyx','msww','mlls')"></html:radio>是
								<html:radio name="rs" property="mwlw" value="0" onclick="setQm(this,'mswyx','msww','mlls')"></html:radio>否
							</td>
							<th>母66以上</th>
							<td>
								<html:radio name="rs" property="mlls" value="1" onclick="setQm(this,'mswyx','msww','mwlw')"></html:radio>是
								<html:radio name="rs" property="mlls" value="0" onclick="setQm(this,'mswyx','msww','mwlw')"></html:radio>否
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody7');return false">第七部分(家庭信息-赡养老人情况)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody7" style="display:none">
						<tr>
							<th>赡养老人(个)</th>
							<td>
								<html:text name="rs" property="sylrs" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="isZero(this);" maxlength="2"></html:text>
							</td>
							<th>老人身体良好</th>
							<td>
								<html:radio name="rs" property="lrsth" value="1" onclick="setDb(this,'lrstc')"></html:radio>是
								<html:radio name="rs" property="lrsth" value="0" onclick="setDb(this,'lrstc')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>老人身体较差</th>
							<td>
								<html:radio name="rs" property="lrstc" value="1" onclick="setDb(this,'lrsth')"></html:radio>是
								<html:radio name="rs" property="lrstc" value="0" onclick="setDb(this,'lrsth')"></html:radio>否
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody8');return false">第八部分(家庭信息-兄弟姐妹情况)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody8" style="display:none">
<%--						<tr>--%>
<%--							<th>哥姐在学</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjzx" value="1" onclick="setQm(this,'gjwn','gjdg','gjgz')"></html:radio>是--%>
<%--								<html:radio name="rs" property="gjzx" value="0" onclick="setQm(this,'gjwn','gjdg','gjgz')"></html:radio>否--%>
<%--							</td>--%>
<%--							<th>哥姐务农</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjwn" value="1" onclick="setQm(this,'gjzx','gjdg','gjgz')"></html:radio>是--%>
<%--								<html:radio name="rs" property="gjwn" value="0" onclick="setQm(this,'gjzx','gjdg','gjgz')"></html:radio>否--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>哥姐打工</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjdg" value="1" onclick="setQm(this,'gjzx','gjwn','gjgz')"></html:radio>是--%>
<%--								<html:radio name="rs" property="gjdg" value="0" onclick="setQm(this,'gjzx','gjwn','gjgz')"></html:radio>否--%>
<%--							</td>--%>
<%--							<th>哥姐工作</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjgz" value="1" onclick="setQm(this,'gjzx','gjwn','gjdg')"></html:radio>是--%>
<%--								<html:radio name="rs" property="gjgz" value="0" onclick="setQm(this,'gjzx','gjwn','gjdg')"></html:radio>否--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>哥姐身体较差</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjstc" value="1"></html:radio>是--%>
<%--								<html:radio name="rs" property="gjstc" value="0"></html:radio>否--%>
<%--							</td>--%>
<%--							<th>哥姐其它</th>--%>
<%--							<td>--%>
<%--								<html:radio name="rs" property="gjqt" value="1"></html:radio>是--%>
<%--								<html:radio name="rs" property="gjqt" value="0"></html:radio>否--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<th>哥姐在学</th>
							<td>
								<html:radio name="rs" property="gjzx" value="1" ></html:radio>是
								<html:radio name="rs" property="gjzx" value="0" ></html:radio>否
							</td>
							<th>哥姐务农</th>
							<td>
								<html:radio name="rs" property="gjwn" value="1" ></html:radio>是
								<html:radio name="rs" property="gjwn" value="0" ></html:radio>否
							</td>
						</tr>
						<tr>
							<th>哥姐打工</th>
							<td>
								<html:radio name="rs" property="gjdg" value="1" ></html:radio>是
								<html:radio name="rs" property="gjdg" value="0" ></html:radio>否
							</td>
							<th>哥姐工作</th>
							<td>
								<html:radio name="rs" property="gjgz" value="1" ></html:radio>是
								<html:radio name="rs" property="gjgz" value="0" ></html:radio>否
							</td>
						</tr>
						<tr>
							<th>哥姐身体较差</th>
							<td>
								<html:radio name="rs" property="gjstc" value="1"></html:radio>是
								<html:radio name="rs" property="gjstc" value="0"></html:radio>否
							</td>
							<th>哥姐其它</th>
							<td>
								<html:radio name="rs" property="gjqt" value="1"></html:radio>是
								<html:radio name="rs" property="gjqt" value="0"></html:radio>否
							</td>
						</tr>
					<!-- 
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody9');return false">第九部分</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody9" style="display:none">
					 -->	
						<tr>
							<th>弟妹辍学</th>
							<td>
								<html:radio name="rs" property="dmcx" value="1" onclick="setQm(this,'dmzxx','dmgz','dmdx')"></html:radio>是
								<html:radio name="rs" property="dmcx" value="0" onclick="setQm(this,'dmzxx','dmgz','dmdx')"></html:radio>否
							</td>
							<th>弟妹中小学</th>
							<td>
								<html:radio name="rs" property="dmzxx" value="1" onclick="setQm(this,'dmcx','dmgz','dmdx')"></html:radio>是
								<html:radio name="rs" property="dmzxx" value="0" onclick="setQm(this,'dmcx','dmgz','dmdx')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>弟妹读高中</th>
							<td>
								<html:radio name="rs" property="dmgz" value="1" onclick="setQm(this,'dmcx','dmzxx','dmdx')"></html:radio>是
								<html:radio name="rs" property="dmgz" value="0" onclick="setQm(this,'dmcx','dmzxx','dmdx')"></html:radio>否
							</td>
							<th>弟妹读大学</th>
							<td>
								<html:radio name="rs" property="dmdx" value="1" onclick="setQm(this,'dmcx','dmzxx','dmgz')"></html:radio>是
								<html:radio name="rs" property="dmdx" value="0" onclick="setQm(this,'dmcx','dmzxx','dmgz')"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>弟妹身体较差</th>
							<td>
								<html:radio name="rs" property="dmstc" value="1"></html:radio>是
								<html:radio name="rs" property="dmstc" value="0"></html:radio>否
							</td>
							<th>弟妹残疾人</th>
							<td>
								<html:radio name="rs" property="dmcj" value="1"></html:radio>是
								<html:radio name="rs" property="dmcj" value="0"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>弟妹其它情况</th>
							<td>
								<html:radio name="rs" property="dmqt" value="1"></html:radio>是
								<html:radio name="rs" property="dmqt" value="0"></html:radio>否
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<!--   
					<thead>
						<tr>
							<th colspan="4">
								<a href="#" class="down"
									onclick="showTbody(this,'myTbody10');return false">第九部分(家庭信息-其它情况)</a>
							</th>
						</tr>
					</thead>
					<tbody id="myTbody10" style="display:none">
						<tr>
							<th>自然灾害</th>
							<td>
								<html:radio name="rs" property="zrzh" value="1"></html:radio>是
								<html:radio name="rs" property="zrzh" value="0"></html:radio>否
							</td>
							<th>重大疾病</th>
							<td>
								<html:radio name="rs" property="zdjb" value="1"></html:radio>是
								<html:radio name="rs" property="zdjb" value="0"></html:radio>否
							</td>
						</tr>
						<tr>
							<th>突发事件</th>
							<td>
								<html:radio name="rs" property="tfsj" value="1"></html:radio>是
								<html:radio name="rs" property="tfsj" value="0"></html:radio>否
							</td>
							<th>欠债情况</th>
							<td>
								<html:radio name="rs" property="qzqk" value="1"></html:radio>是
								<html:radio name="rs" property="qzqk" value="0"></html:radio>否
							</td>
						</tr>
					</tbody>
					 -->
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
				</script>
			</logic:present>
			
  		</html:form>
  </body>
</html>
