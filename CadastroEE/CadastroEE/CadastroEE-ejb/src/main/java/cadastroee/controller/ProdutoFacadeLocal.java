package cadastroee.controller;

import cadastroee.model.Produto;
import jakarta.ejb.Local;
import java.util.List;

/**
 * Interface local do Session Bean ProdutoFacade.
 * Procedimento 1 — Passo 3e/3f: gerada via New Session Beans for Entity Classes.
 * Todas as importações javax → jakarta (Passo 4c).
 */
@Local
public interface ProdutoFacadeLocal {

    void create(Produto produto);

    void edit(Produto produto);

    void remove(Produto produto);

    Produto find(Object id);

    List<Produto> findAll();

    int count();
}
