<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> <c:out value="${user}? ${(user.id)}">></c:out></h1>
	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<img src="/image/logo.png" class="center1">
			</div>

			
			
		</div>
		<hr />
		
		
		 
		<div class="row" style="margin-top: 50px">
		<div class="col-xs-2">
			
				
						<c:forEach var="book" items="${bookid}">
					<div class="panel panel-default" style="height:250px">
					<div class="panel-body">
					
						<div class="hover1" style="height: 200px">
							<figure>
								<a href="@{/bookDetail?id=}+${book}"><img
									class="img-responsive shelf-book"
									src="#{adminPath}+@{/image/book/}+${book}+'.png'" /></a>
							</figure>
							</div> <br />
					
					
					
						</div>
					</div>
				</c:forEach>
					
			</div>
			<div class="col-xs-6">
			<c:forEach var="book" items="${book}">
					
					<div class="panel1 panel-default" style="height:250px">
					<div class="panel-body1">
					
					<div>
							<c:out value="${book.title}"></c:out>>
					
						</div> <br />
						</div>
						</div>
					
			</c:forEach>
			</div>
			
		</div>
		<br>
		
	</div>
</body>
</html>