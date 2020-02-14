<%@ page language="java"
contentType="text/html; charset=windows-1256"
pageEncoding="windows-1256"
import=" entities.Author,entities.Book,java.util.List"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>
<jsp:include page="/bookServlet" />
<jsp:include page="/authorServlet" />

<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=windows-1256">
    <title>  First Page   </title>
</head>

<body>

<h1>
    Books:
</h1>
<table border="1">
    <thead>
    <tr>
        <th>Book Id</th>
        <th>Book Name </th>
        <th>Date Published</th>
        <th>Chapters</th>
    </tr>
    </thead>

   <tbody>
    <% List<Book> bookList= (List<Book>) session.getAttribute("bookList"); %>

        <%for(int i=0;i<bookList.size();i++) { %>
        <tr>
            <td> <%= bookList.get(i).getBookId()%> </td>
            <td> <%= bookList.get(i).getBookName()%> </td>
            <td> <%= bookList.get(i).getDatePublished()%> </td>
            <td> <%= bookList.get(i).getNoChapters()%> </td>
        </tr>

        <% } %>
    </tbody>
</table>

<h1>
    Authors:
</h1>
<table border="1">
    <thead>
    <tr>
        <th>Author Id</th>
        <th>Author Name </th>
        <th>Birthdate</th>
    </tr>
    </thead>

   <tbody>
    <% List<Author> authorList= (List<Author>) session.getAttribute("authorList"); %>

        <%for(int i=0;i<authorList.size();i++) { %>
        <tr>
            <td> <%= authorList.get(i).getAuthorId()%> </td>
            <td> <%= authorList.get(i).getAuthorName()%> </td>
            <td> <%= authorList.get(i).getBirthDate()%> </td>
        </tr>

        <% } %>
    </tbody>
</table>

<h1>
          Add book
         </h1>

         	<form action="bookServlet" method="POST">

         			Name:
         			<input type="text" name="nm"/><br>

         			Date published:
         			<input type="text" name="dp"/><br>

                    Chapters:
                    <input type="number" name="ch"/><br>

                    Author:
                     <input type="text" name="ah"/><br>

         			<input type="submit" value="add">
         		</form>

<h1>
          Delete book
         </h1>

         	<form action="bookServlet" method="GET">

         			Name:
         			<input type="text" name="bn"/><br>

         			<input type="submit" value="delete">
         		</form>



<h1>
          See details for an author
         </h1>

         	<form action="authorDetailsServlet" method="GET">

         			Name:
         			<input type="text" name="an"/><br>

         			<input type="submit" value="details">
         		</form>

</body>



</html>