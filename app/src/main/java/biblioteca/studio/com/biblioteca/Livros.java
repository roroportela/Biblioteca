package biblioteca.studio.com.biblioteca;

import java.io.Serializable;

public class Livros implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer _id;
    private String nome;
    private String edicao;
    private String autor;
    private String editora;
    private String descricao;
    private String imagem;

    public Livros(String s) {
        // TODO Auto-generated constructor stub
    }

    public Livros(Integer _id, String nome, String edicao, String autor, String editora, String descricao) {
        super();
        this._id = _id;
        this.nome = nome;
        this.edicao = edicao;
        this.autor = autor;
        this.editora = editora;
        this.descricao = descricao;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        this._id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}