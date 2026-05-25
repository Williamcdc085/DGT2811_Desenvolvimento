<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cadastroee.model.Produto" %>
<%--
  ProdutoDados.jsp
  Procedimento 2 — Passo 3: formulário de inclusão e alteração de produto.
  Procedimento 3 — Passo 3: classes Bootstrap aplicadas conforme roteiro.

  Recebe do Servlet o atributo "produto":
    • nulo  → modo inclusão  (acao = incluir)
    • não nulo → modo alteração (acao = alterar)
--%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dados do Produto — CadastroEE</title>

    <%-- Procedimento 3 — Passo 1c/1d: Bootstrap via CDN --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc4s9bIOgUxi8T/jzmvO6VXZSJNdEnWvqSE7B9+B6nxh"
            crossorigin="anonymous"></script>
</head>

<%-- Procedimento 3 — Passo 3a: classe container no body --%>
<body class="container py-4">

    <%--
      Procedimento 2 — Passo 3d: variável 'acao' definida conforme presença da entidade.
      Entidade nula → incluir; entidade fornecida → alterar.
    --%>
    <%
        Produto produto = (Produto) request.getAttribute("produto");
        String acao     = (produto == null) ? "incluir" : "alterar";
        String titulo   = "incluir".equals(acao) ? "Novo Produto" : "Alterar Produto";
    %>

    <h2 class="mb-4"><%= titulo %></h2>

    <%-- Procedimento 3 — Passo 3c: classe form no formulário --%>
    <%-- Procedimento 2 — Passo 3a: envio para ServletProdutoFC via POST --%>
    <form action="ServletProdutoFC" method="post" class="form" style="max-width: 480px;">

        <%-- Procedimento 2 — Passo 3e: campo hidden para acao --%>
        <input type="hidden" name="acao" value="<%= acao %>">

        <%-- Procedimento 2 — Passo 3f: campo hidden para id (somente em alteração) --%>
        <% if ("alterar".equals(acao)) { %>
        <input type="hidden" name="id" value="<%= produto.getId() %>">
        <% } %>

        <%-- Procedimento 3 — Passo 3b: cada par label/input em div.mb-3 --%>

        <div class="mb-3">
            <%-- Procedimento 3 — Passo 3d: classe form-label --%>
            <label for="nome" class="form-label">Nome</label>
            <%-- Procedimento 3 — Passo 3e: classe form-control --%>
            <input type="text"
                   id="nome"
                   name="nome"
                   class="form-control"
                   required
                   value="<%= (produto != null ? produto.getNome() : "") %>">
        </div>

        <div class="mb-3">
            <label for="quantidade" class="form-label">Quantidade</label>
            <input type="number"
                   id="quantidade"
                   name="quantidade"
                   class="form-control"
                   min="0"
                   required
                   value="<%= (produto != null ? produto.getQuantidade() : "") %>">
        </div>

        <div class="mb-3">
            <label for="precoVenda" class="form-label">Preço de Venda (R$)</label>
            <input type="number"
                   id="precoVenda"
                   name="precoVenda"
                   class="form-control"
                   step="0.01"
                   min="0"
                   required
                   value="<%= (produto != null ? String.format("%.2f", produto.getPrecoVenda()) : "") %>">
        </div>

        <%-- Procedimento 3 — Passo 3f: classes btn btn-primary --%>
        <button type="submit" class="btn btn-primary">
            <%= "incluir".equals(acao) ? "Incluir Produto" : "Salvar Alterações" %>
        </button>

        <a href="ServletProdutoFC?acao=listar" class="btn btn-secondary ms-2">Cancelar</a>
    </form>

</body>
</html>
