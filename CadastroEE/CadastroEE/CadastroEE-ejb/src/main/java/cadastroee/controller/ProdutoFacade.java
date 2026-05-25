package cadastroee.controller;

import cadastroee.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Session Bean Stateless para a entidade Produto.
 * Procedimento 1 — Passo 3d/3f: gerado via New Session Beans for Entity Classes.
 * Importações javax → jakarta (Passo 4c).
 * A unidade de persistência referencia jdbc/loja via JNDI (Passo 1n).
 */
@Stateless
public class ProdutoFacade extends AbstractFacade<Produto> implements ProdutoFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    public ProdutoFacade() {
        super(Produto.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
