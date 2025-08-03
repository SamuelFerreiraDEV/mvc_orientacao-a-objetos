package projetomvc.models.entities;

public class Autor {
  private String nome;
  private String cidadeNatal;
  private Integer dataNascimento;

  public Autor() {
    this.nome = "";
    this.cidadeNatal = "";
    this.dataNascimento = null;
  }

  public Autor(String nome, String cidadeNatal, Integer dataNascimento) {
    this.nome = nome;
    this.cidadeNatal = cidadeNatal;
    this.dataNascimento = dataNascimento;
  }
  
  public Autor(Autor outro) {
    this.nome = outro.getNome();
    this.cidadeNatal = outro.getCidadeNatal();
    this.dataNascimento = outro.getDataNascimento();
  }

  public void copiar(Autor outro) {
    this.nome = outro.getNome();
    this.cidadeNatal = outro.getCidadeNatal();
    this.dataNascimento = outro.getDataNascimento();
  }
  
  @Override
  public String toString() {
    return "Autor [nome=" + nome + ", cidadeNatal=" + cidadeNatal + ", dataNascimento=" + dataNascimento + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((cidadeNatal == null) ? 0 : cidadeNatal.hashCode());
    result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
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
    Autor other = (Autor) obj;
    if (nome == null) {
      if (other.nome != null)
        return false;
    } else if (!nome.equals(other.nome))
      return false;
    if (cidadeNatal == null) {
      if (other.cidadeNatal != null)
        return false;
    } else if (!cidadeNatal.equals(other.cidadeNatal))
      return false;
    if (dataNascimento == null) {
      if (other.dataNascimento != null)
        return false;
    } else if (!dataNascimento.equals(other.dataNascimento))
      return false;
    return true;
  }

  public String getNome() {
    return nome;
  }

  public String getCidadeNatal() {
    return cidadeNatal;
  }

  public Integer getDataNascimento() {
    return dataNascimento;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCidadeNatal(String cidadeNatal) {
    this.cidadeNatal = cidadeNatal;
  }

  public void setDataNascimento(Integer dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
}
