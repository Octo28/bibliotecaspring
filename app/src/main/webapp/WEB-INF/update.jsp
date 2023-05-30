<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8" />
        <title>Atualizar Livro</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Atualizar Livro</h1>
            <a href="/livro/list" class="btn btn-primary">Voltar</a>
            <form action="/livro/update" method="post">
                <input type="hidden" name="id" value="${livro.id}" />
                <div class="form-group">
                    <label for="titulo">TíTulo</label>
                    <input type="text" name="titulo" class="form-control" value="${livro.titulo}" />
                </div>
                <div class="form-group">
                    <label for="isbn">ISBN</label>
                    <input type="text" name="isbn" class="form-control" value="${livro.isbn}" />
                </div>
                <div class="form-group">
                    <label for="genero">Gênero</label>
                    <select class="form-control" name="genero">
                        <c:forEach var="item" items="${generos}">
                            <option ${livro.genero.id == item.id ? "selected" : ""} value="${item.id}">${item.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <br />
                <button type="submit" class="btn btn-success">Salvar</button>
            </form>
        </div>
    </body>
</html>

