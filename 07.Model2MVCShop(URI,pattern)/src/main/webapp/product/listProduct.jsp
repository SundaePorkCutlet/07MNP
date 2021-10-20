<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@ page import="com.model2.mvc.common.util.* "%>

<%
List<Product> list= (List<Product>)request.getAttribute("list");
Page resultPage=(Page)request.getAttribute("resultPage");

Search search = (Search)request.getAttribute("search");
//==> null 을 ""(nullString)으로 변경
String searchCondition = CommonUtil.null2str(search.getSearchCondition());
String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%>

--%>




<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
function fncGetUserList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?menu=${menu }" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
					
					<c:if test="${menu=='manage' }">
							상품 관리
					
					</c:if>
					<c:if test="${menu=='search' }">
							상품 목록 조회
					</c:if>
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	
	<td align="right">
	
			<input type="radio" name="orderOption" id = "orderOption0" value="0"  ${ ! empty search.orderOption && search.orderOption==0 ? "checked" : "" }/><label for = "orderOption0">상품명순</label>		 
		<input type="radio" name="orderOption" id = "orderOption1" value="1"  ${ ! empty search.orderOption && search.orderOption==1 ? "checked" : "" }/><label for = "orderOption1">높은가격순</label>
		<input type="radio" name="orderOption" id = "orderOption2" value="2"  ${ ! empty search.orderOption && search.orderOption==2 ? "checked" : "" }/><label for = "orderOption2">낮은가격순</label>														
		<input type="radio" name="orderOption" id = "orderOption3" value="3"  ${ ! empty search.orderOption && search.orderOption==3 ? "checked" : "" }/><label for = "orderOption3">최신순</label>														
		<input type="radio" name="orderOption" id = "orderOption4" value="4"  ${ ! empty search.orderOption && search.orderOption==4 ? "checked" : "" }/><label for = "orderOption4">오래된순</label>														
			
		
<!-- 				<td align="right"> -->
<!-- 			<select name="orderOption" class="ct_input_g" style="width:80px"> -->
<%-- 			<option value="0"  ${ ! empty search.orderOption && search.orderOption==0 ? "selected" : "" }>상품명순</option>			  --%>
<%-- 		<option value="1"  ${ ! empty search.orderOption && search.orderOption==1 ? "selected" : "" }>높은가격순</option>								 --%>
<!-- 			</select> -->
			
			
		
		
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
				<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
				<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
		
		</select>
		<input type="text" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword : ""}"   class="ct_input_g" style="width:200px; height:19px" >
		</td>
		
		
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
							
				
				
				
				
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetUserList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  ${resultPage.totalCount } 건수, 현재 ${resultpage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">수량</td>
		<c:if test="${menu=='manage' }">
		<td class="ct_line02"></td>
		<td class="ct_list_b">상태</td>
		</c:if>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	
	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${i+1}"/>
	
	<tr class="ct_list_pop">
		<td align="center">${i}</td>
		<td></td>
				
				<td align="left">
				<c:if test="${menu=='search' }">
				<a href="/product/getProduct?prodNo=${product.prodNo }&menu=search">${product.prodName }</a>
				</c:if>
				<c:if test="${menu=='manage' }">
				<a href="/product/updateProduct?prodNo=${product.prodNo }">${product.prodName }</a>
				</c:if>
				</td>
				
		
		<td></td>
		<td align="left">${product.price }</td>
		<td></td>
		<td align="left">${product.regDate }</td>
		<td></td>
		<td align="left">${product.amount }</td>
		<td></td>
		<c:if test="${menu=='manage' }">
		<td align="left">
		
		<c:if test = "${product.proTranCode=='000'}">
			
			판매중
			</c:if>
			<c:if test = "${product.proTranCode=='001'}">
			
			구매완료
			
				
			<a href="/purchase/updateTranCodeByProd?ProTranCode=${product.proTranCode}&prodNo=${product.prodNo}&menu=manage">배송하기</a>
		
		
			</c:if>
			
		<c:if test = "${product.proTranCode=='002'}">
			
			배송중
		</c:if>
		<c:if test = "${product.proTranCode=='003'}">
			
			배송완료
		</c:if>
		</td>	
		</c:if>
	</tr>
	
	


	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			 <input type="hidden" id="currentPage" name="currentPage" value=""/>
		
			<jsp:include page="../common/pageNavigator.jsp"/>	
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
