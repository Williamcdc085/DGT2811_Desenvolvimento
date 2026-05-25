package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Front Controller para o CRUD de Produto.
 * Procedimento 2 — Passo 1: implementa o padrão MVC/Front Controller.
 *
 * Ações suportadas pelo parâmetro 'acao':
 *   listar     → carrega lista e encaminha para ProdutoLista.jsp
 *   formIncluir→ encaminha formulário vazio para ProdutoDados.jsp
 *   formAlterar→ carrega entidade e encaminha para ProdutoDados.jsp
 *   incluir    → persiste nova entidade e redireciona para listagem
 *   alterar    → atualiza entidade existente e redireciona para listagem
 *   excluir    → remove entidade e redireciona para listagem
 */
@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String acao   = request.getParameter("acao");
        String destino;

        // ── Determina destino (JSP) ──────────────────────────────────────────
        if ("formAlterar".equals(acao) || "formIncluir".equals(acao)) {
            destino = "/ProdutoDados.jsp";
        } else {
            destino = "/ProdutoLista.jsp";
        }

        // ── Lógica de negócio por ação ────────────────────────────────────────
        switch (acao == null ? "listar" : acao) {

            case "listar":
                // Passo 1e: envia lista como atributo da requisição
                request.setAttribute("produtos", facade.findAll());
                break;

            case "formIncluir":
                // Formulário vazio — nenhum atributo extra necessário
                break;

            case "formAlterar":
                // Passo 1f: carrega entidade e expõe no request
                Integer idAlt = Integer.valueOf(request.getParameter("id"));
                request.setAttribute("produto", facade.find(idAlt));
                break;

            case "excluir":
                // Passo 1g: remove e recarrega lista
                Integer idExc = Integer.valueOf(request.getParameter("id"));
                facade.remove(facade.find(idExc));
                request.setAttribute("produtos", facade.findAll());
                request.setAttribute("mensagem", "Produto excluído com sucesso.");
                break;

            case "alterar":
                // Passo 1h: atualiza entidade com dados do formulário
                Integer idUpd = Integer.valueOf(request.getParameter("id"));
                Produto pUpd  = facade.find(idUpd);
                pUpd.setNome(request.getParameter("nome"));
                pUpd.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));
                pUpd.setPrecoVenda(Float.valueOf(request.getParameter("precoVenda")));
                facade.edit(pUpd);
                request.setAttribute("produtos", facade.findAll());
                request.setAttribute("mensagem", "Produto alterado com sucesso.");
                break;

            case "incluir":
                // Passo 1i: cria nova entidade com dados do formulário
                Produto pNovo = new Produto();
                pNovo.setNome(request.getParameter("nome"));
                pNovo.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));
                pNovo.setPrecoVenda(Float.valueOf(request.getParameter("precoVenda")));
                facade.create(pNovo);
                request.setAttribute("produtos", facade.findAll());
                request.setAttribute("mensagem", "Produto incluído com sucesso.");
                break;

            default:
                request.setAttribute("produtos", facade.findAll());
        }

        // Passo 1j: encaminha para o JSP via RequestDispatcher (forward)
        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }
}
