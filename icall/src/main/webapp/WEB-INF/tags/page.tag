<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="page" type="com.github.pagehelper.PageInfo" required="true" description="分页" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="false" description="每页大小" %>
<%@ attribute name="pageNum" type="java.lang.Integer" required="false" description="请求的页码" %>
<%@ attribute name="orderBy" type="java.lang.String" required="false" description="排序字段" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <table class="gridtable" style="width:100%;text-align: center;">
     <tr>
         <c:if test="${page.hasPreviousPage}">
             <td><a href="${pageContext.request.contextPath}/country?pageNum=${page.prePage}&pageSize=${page.pageSize}">前一页</a></td>
         </c:if>
         <c:forEach items="${page.navigatepageNums}" var="nav">
             <c:if test="${nav == page.pageNum}">
                 <td style="font-weight: bold;">${nav}</td>
             </c:if>
             <c:if test="${nav != page.pageNum}">
                 <td><a href="${pageContext.request.contextPath}/country?pageNum=${nav}&pageSize=${page.pageSize}">${nav}</a></td>
             </c:if>
         </c:forEach>
         <c:if test="${page.hasNextPage}">
             <td><a href="${pageContext.request.contextPath}/country?pageNum=${page.nextPage}&pageSize=${page.pageSize}">下一页</a></td>
         </c:if>
     </tr>
 </table>