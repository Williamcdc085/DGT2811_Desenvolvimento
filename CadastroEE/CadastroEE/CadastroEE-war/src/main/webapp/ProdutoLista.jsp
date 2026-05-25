<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cadastroee.model.Produto, java.util.List" %>
<%--
  ProdutoLista.jsp
  Procedimento 2 — Passo 2: página de consulta/listagem de produtos.
  Procedimento 3 — Passo 2: classes Bootstrap aplicadas conforme roteiro.

  URL de acesso:
    http://localhost:8080/CadastroEE-war/ServletProdutoFC?acao=listar
--%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Produtos — CadastroEE</title>

    <%-- Procedimento 3 — Passo 1c/1d: Bootstrap via CDN --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc4s9bIOgUxi8T/jzmvO6VXZSJNdEnWvqSE7B9+B6nxh"
            crossorigin="anonymous"></script>
</head>

<%-- Procedimento 3 — Passo 2a: classe container no body --%>
<body class="container py-4">

    <h2 class="mb-3">Cadastro de Produtos</h2>

    <%-- Feedback de operação (mensagem enviada pelo Servlet) --%>
    <%
        String msg = (String) request.getAttribute("mensagem");
        if (msg != null) {
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= msg %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
    <% } %>

    <%-- Procedimento 2 — Passo 2a: link para formulário de inclusão
         Procedimento 3 — Passo 2b: classes btn btn-primary m-2 --%>
    <a href="ServletProdutoFC?acao=formIncluir" class="btn btn-primary m-2">
        + Novo Produto
    </a>

    <%-- Procedimento 3 — Passo 2c/2d: classes table table-striped + thead table-dark --%>
    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Preço de Venda</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%-- Procedimento 2 — Passo 2d/2e: iteração dinâmica sobre a lista --%>
            <%
                List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                if (produtos != null && !produtos.isEmpty()) {
                    for (Produto p : produtos) {
            %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getNome() %></td>
                <td><%= p.getQuantidade() %></td>
                <td>R$ <%= String.format("%.2f", p.getPrecoVenda()) %></td>
                <td>
                    <%-- Procedimento 3 — Passo 2e: btn btn-primary btn-sm --%>
                    <a href="ServletProdutoFC?acao=formAlterar&id=<%= p.getId() %>"
                       class="btn btn-primary btn-sm">Alterar</a>

                    <%-- Procedimento 3 — Passo 2f: btn btn-danger btn-sm --%>
                    <a href="ServletProdutoFC?acao=excluir&id=<%= p.getId() %>"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Confirmar exclusão de <%= p.getNome() %>?')">
                        Excluir
                    </a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5" class="text-center text-muted">Nenhum produto cadastrado.</td>
            </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
