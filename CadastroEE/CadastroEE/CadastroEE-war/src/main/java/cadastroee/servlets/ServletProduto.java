package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet de teste para listar produtos via EJB.
 * Procedimento 1 — Passo 5: demonstra a integração Servlet → EJB → JPA.
 * Importações javax → jakarta (Passo 6c).
 * Registrado no web.xml (Passo 6d).
 */
@WebServlet(name = "ServletProduto", urlPatterns = {"/ServletProduto"})
public class ServletProduto extends HttpServlet {

    // Passo 5d: referência injetada pelo container JEE
    @EJB
    ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Passo 5e: recupera lista via facade e apresenta como lista HTML
        List<Produto> produtos = facade.findAll();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='pt-BR'><head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Produtos — Teste</title></head><body>");
            out.println("<h2>Lista de Produtos (Servlet de Teste)</h2>");
            out.println("<ul>");
            for (Produto p : produtos) {
                out.printf("<li>#%d — %s | Qtd: %d | R$ %.2f</li>%n",
                        p.getId(), p.getNome(), p.getQuantidade(), p.getPrecoVenda());
            }
            out.println("</ul>");
            out.println("</body></html>");
        }
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
