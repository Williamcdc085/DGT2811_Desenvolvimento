package cadastroee.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * Entidade JPA mapeada para a tabela Produto do banco loja (SQL Server).
 * Procedimento 1 — Passo 3a/3b: gerada via New Entity Classes from Database.
 * Passo 4d: tipo de precoVenda alterado de BigDecimal para Float.
 */
@Entity
@Table(name = "Produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll",   query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findById",  query = "SELECT p FROM Produto p WHERE p.id = :id"),
    @NamedQuery(name = "Produto.findByNome",query = "SELECT p FROM Produto p WHERE p.nome = :nome")
})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private Integer quantidade;

    // Passo 4d: Float em vez de BigDecimal
    @Column(name = "precoVenda")
    private Float precoVenda;

    public Produto() {}

    public Produto(Integer id) { this.id = id; }

    public Integer getId()                   { return id; }
    public void setId(Integer id)            { this.id = id; }

    public String getNome()                  { return nome; }
    public void setNome(String nome)         { this.nome = nome; }

    public Integer getQuantidade()           { return quantidade; }
    public void setQuantidade(Integer q)     { this.quantidade = q; }

    public Float getPrecoVenda()             { return precoVenda; }
    public void setPrecoVenda(Float p)       { this.precoVenda = p; }

    @Override
    public String toString() {
        return "Produto[ id=" + id + " ]";
    }
}
