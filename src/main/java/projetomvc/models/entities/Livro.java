package projetomvc.models.entities;

public class Livro {
  private String titulo;
  private Autor autor;
  private Integer ano;

  public Livro() {
    this.titulo = "";
    this.autor = null;
    this.ano = null;
  }

  public Livro(String titulo, Autor autor, Integer ano) {
    this.titulo = titulo;
    this.autor = autor;
    this.ano = ano;
  }

  public Livro(Livro outro) {
    this.titulo = outro.getTitulo();
    this.autor = outro.getAutor();
    this.ano = outro.getAno();
  }

  public void copiar(Livro outro) {
    this.titulo = outro.getTitulo();
    this.autor = outro.getAutor();
    this.ano = outro.getAno();
  }

  @Override
  public String toString() {
    return "Livro [titulo=" + titulo + ", autor=" + autor + ", ano=" + ano + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
    result = prime * result + ((autor == null) ? 0 : autor.hashCode());
    result = prime * result + ((ano == null) ? 0 : ano.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Livro other = (Livro) obj;
    if (titulo == null) {
      if (other.titulo != null)
        return false;
    } else if (!titulo.equals(other.titulo))
      return false;
    if (autor == null) {
      if (other.autor != null)
        return false;
    } else if (!autor.equals(other.autor))
      return false;
    if (ano == null) {
      if (other.ano != null)
        return false;
    } else if (!ano.equals(other.ano))
      return false;
    return true;
  }

  public String getTitulo() {
    return titulo;
  }
  
  public Autor getAutor() {
    return autor;
  }

  public Integer getAno() {
    return ano;
  }
  
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  
  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
  }
}
